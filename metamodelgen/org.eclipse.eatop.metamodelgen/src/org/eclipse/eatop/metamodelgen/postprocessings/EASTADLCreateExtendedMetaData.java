/**
 * <copyright>
 *  
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors: 
 *     itemis - Initial API and implementation
 *  
 * </copyright>
 * 
 */
package org.eclipse.eatop.metamodelgen.postprocessings;

import java.util.Iterator;

import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.CreateExtendedMetaData;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;
import org.eclipse.eatop.metamodelgen.util.ModelUtil;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.sphinx.emf.serialization.XMLPersistenceMappingExtendedMetaData;

public class EASTADLCreateExtendedMetaData extends CreateExtendedMetaData {

	public EASTADLCreateExtendedMetaData(String version) {
		super(version);
	}

	@Override
	protected Object createExtendedMetaData(EAttribute eAttribute) {
		EAnnotation taggedValuesAnnotation = eAttribute.getEAnnotation(IEASTADLConstants.ANNOTATION_TAGGED_VALUES);

		if (taggedValuesAnnotation != null && taggedValuesAnnotation.getDetails() != null) {
			String name = ModelUtil.getTaggedValue(eAttribute, IEASTADLConstants.TAGGED_VALUE_XML_NAME);
			if (name != null) {
				XMLPersistenceMappingExtendedMetaData.INSTANCE.setName(eAttribute, name);
			}

			boolean isAttribute = IConstants.TRUE.equalsIgnoreCase(taggedValuesAnnotation.getDetails().get(
					IEASTADLConstants.TAGGED_VALUE_XML_ATTRIBUTE));

			if (!isAttribute && isComplexTypeWithSimpleContent(eAttribute.getEContainingClass())) {
				ExtendedMetaData.INSTANCE.setFeatureKind(eAttribute, ExtendedMetaData.SIMPLE_FEATURE);
			} else {
				ExtendedMetaData.INSTANCE.setFeatureKind(eAttribute, isAttribute ? ExtendedMetaData.ATTRIBUTE_FEATURE
						: ExtendedMetaData.ELEMENT_FEATURE);
			}

			if (!isAttribute) {
				ExtendedMetaData.INSTANCE.setNamespace(eAttribute, getXMLNamespace());
			}
		}

		return eAttribute;
	}

	@Override
	protected Object createExtendedMetaData(EReference eRef) {
		boolean isRoleElement = IConstants.TRUE.equalsIgnoreCase(ModelUtil.getTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_ELEMENT));
		boolean isTypeElement = IConstants.TRUE.equalsIgnoreCase(ModelUtil.getTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_ELEMENT));
		boolean isRoleWrapperElement = IConstants.TRUE.equalsIgnoreCase(ModelUtil.getTaggedValue(eRef,
				IEASTADLConstants.TAGGED_VALUE_XML_ROLE_WRAPPER_ELEMENT));
		boolean isTypeWrapperElement = IConstants.TRUE.equalsIgnoreCase(ModelUtil.getTaggedValue(eRef,
				IEASTADLConstants.TAGGED_VALUE_XML_TYPE_WRAPPER_ELEMENT));

		// There are some cases where roleWrapper=true and the upperMultiplicity is 1. Therefore the
		// upperMultiplicity is not checked when determining the xml.name.
		String name;
		if (isRoleWrapperElement) {
			name = ModelUtil.getTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_NAME_PLURAL);
		} else {
			name = ModelUtil.getTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_NAME);
		}

		// 1000 ! This case is not allowed by the spec, but occurs
		if (isRoleWrapperElement && !isRoleElement && !isTypeElement && !isTypeWrapperElement) {
			name = ModelUtil.getTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_NAME);
		}

		XMLPersistenceMappingExtendedMetaData.INSTANCE.setName(eRef, name);
		XMLPersistenceMappingExtendedMetaData.INSTANCE.setFeatureKind(eRef, ExtendedMetaData.ELEMENT_FEATURE);
		XMLPersistenceMappingExtendedMetaData.INSTANCE.setNamespace(eRef, getXMLNamespace());

		return eRef;
	}

	@Override
	protected Object createExtendedMetaData(EClass eClass) {

		XMLPersistenceMappingExtendedMetaData.INSTANCE.setName(eClass, ModelUtil.getTaggedValue(eClass, IEASTADLConstants.TAGGED_VALUE_XML_NAME));

		if (XMLPersistenceMappingExtendedMetaData.INSTANCE.getContentKind(eClass) != ExtendedMetaData.UNSPECIFIED_CONTENT) {
			// content kind is already set, so do not overwrite
			return eClass;
		}

		int kind = ExtendedMetaData.ELEMENT_ONLY_CONTENT;
		if (isComplexTypeWithSimpleContent(eClass)) {
			kind = ExtendedMetaData.SIMPLE_CONTENT;
		}
		XMLPersistenceMappingExtendedMetaData.INSTANCE.setContentKind(eClass, kind);

		// iterate through features
		Iterator<EStructuralFeature> iter = eClass.getEStructuralFeatures().iterator();
		while (iter.hasNext()) {
			eSwitch.doSwitch(iter.next());
		}

		return eClass;
	}

	@Override
	protected boolean isComplexTypeWithSimpleContent(EClass eClass) {
		boolean isComplexTypeWithSimpleContent = false;

		if (eClass != null) {
			// If the class is not abstract
			if (!eClass.isAbstract()) {
				int notAttributePropertyCount = 0;
				EStructuralFeature candidateAttribute = null;
				// If the class contains exactly one property that is not mapped to an xml.attribute, and this property
				// is not represented by any xml.*Element elements, then a xsd:complexType with simpleContent (The
				// simpleContent contains the data of the property which is not mapped to an
				// xml.attribute) should be generated.
				for (EStructuralFeature property : eClass.getEAllStructuralFeatures()) {
					notAttributePropertyCount++;
					boolean isRoleElement = IConstants.TRUE.equalsIgnoreCase(ModelUtil.getTaggedValue(property,
							IEASTADLConstants.TAGGED_VALUE_XML_ROLE_ELEMENT));
					boolean isTypeElement = IConstants.TRUE.equalsIgnoreCase(ModelUtil.getTaggedValue(property,
							IEASTADLConstants.TAGGED_VALUE_XML_TYPE_ELEMENT));
					boolean isRoleWrapperElement = IConstants.TRUE.equalsIgnoreCase(ModelUtil.getTaggedValue(property,
							IEASTADLConstants.TAGGED_VALUE_XML_ROLE_WRAPPER_ELEMENT));
					boolean isTypeWrapperElement = IConstants.TRUE.equalsIgnoreCase(ModelUtil.getTaggedValue(property,
							IEASTADLConstants.TAGGED_VALUE_XML_TYPE_WRAPPER_ELEMENT));
					// If type of property that is not mapped to xml attribute
					if (!isRoleElement && !isTypeElement && !isRoleWrapperElement && !isTypeWrapperElement) {
						// and upper multiplicity of property that is not mapped to xml attribute is 1
						if (property.getUpperBound() != 1) {
							continue;
						}
						// and EEnum extends EDataType ...
						if (property.getEType() instanceof EDataType) {
							candidateAttribute = property;
						}
					}
					if (notAttributePropertyCount > 1) {
						return false;
					}
				}
				if (notAttributePropertyCount == 1 && candidateAttribute != null) {
					return true;
				}
			}
		}

		return isComplexTypeWithSimpleContent;
	}

	@Override
	protected String getXMLNamespace() {
		StringBuilder namespace = new StringBuilder(IEASTADLConstants.DEFAULT_NSURI);
		if (version != null) {
			namespace.append("/"); //$NON-NLS-1$
			namespace.append(version);
		}
		return namespace.toString();
	}

}
