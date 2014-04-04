/**
 * <copyright>
 * 
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import java.util.Iterator;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.sphinx.emf.serialization.XMLPersistenceMappingExtendedMetaData;

public class CreateExtendedMetaData extends PostProcessingTemplate {

	protected EcoreSwitch<Object> eSwitch = new EcoreSwitch<Object>() {
		@Override
		public Object caseEClass(EClass object) {
			return createExtendedMetaData(object);
		}

		@Override
		public Object caseEPackage(EPackage object) {
			return createExtendedMetaData(object);
		}

		@Override
		public Object caseEAttribute(EAttribute object) {
			return createExtendedMetaData(object);
		}

		@Override
		public Object caseEReference(EReference object) {
			return createExtendedMetaData(object);
		}
	};

	protected String version = null;

	public CreateExtendedMetaData(String version) {
		super();
		this.version = version;
	}

	protected Object createExtendedMetaData(EAttribute eAttribute) {
		// ExtendedMetaData.INSTANCE.setNamespace(eAttribute, getXMLNamespace());
		return eAttribute;
	}

	/**
	 * Creates ExtendedMetaData for an EPackage and iterates through all subpackages and classifier
	 * 
	 * @param ePackage
	 * @return ePackage
	 */
	private Object createExtendedMetaData(EPackage ePackage) {

		XMLPersistenceMappingExtendedMetaData.INSTANCE.setQualified(ePackage, false);

		Iterator<EClassifier> iterCls = ePackage.getEClassifiers().iterator();
		while (iterCls.hasNext()) {
			eSwitch.doSwitch(iterCls.next());
		}

		// iterate through subpackages
		Iterator<EPackage> iter = ePackage.getESubpackages().iterator();
		while (iter.hasNext()) {
			eSwitch.doSwitch(iter.next());
		}

		return ePackage;
	}

	/**
	 * Creates ExtendedMetaData for an EReference
	 * 
	 * @param eRef
	 * @return
	 */
	protected Object createExtendedMetaData(EReference eRef) {
		ExtendedMetaData.INSTANCE.setName(eRef, eRef.getName());
		ExtendedMetaData.INSTANCE.setFeatureKind(eRef, ExtendedMetaData.ELEMENT_FEATURE);
		ExtendedMetaData.INSTANCE.setNamespace(eRef, getXMLNamespace());

		return eRef;
	}

	/**
	 * Creates ExtendedMetaData for an EClass and iterates through all its features
	 * 
	 * @param eClass
	 * @return eClass
	 */
	protected Object createExtendedMetaData(EClass eClass) {

		XMLPersistenceMappingExtendedMetaData.INSTANCE.setName(eClass, eClass.getName());

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
	public void execute() {
		createExtendedMetaData(model);
	}

	/**
	 * Returns true if currentClass satisfies the criteria of APRXML0024.
	 * 
	 * @param currentClass
	 * @return As specified aboved.
	 */
	protected boolean isComplexTypeWithSimpleContent(EClass currentClass) {
		boolean isComplexTypeWithSimpleContent = false;
		return isComplexTypeWithSimpleContent;
	}

	protected String getXMLNamespace() {
		StringBuilder namespace = new StringBuilder(IConstants.DEFAULT_NSURI);
		if (version != null) {
			namespace.append("/"); //$NON-NLS-1$
			namespace.append(version);
		}
		return namespace.toString();
	}

}
