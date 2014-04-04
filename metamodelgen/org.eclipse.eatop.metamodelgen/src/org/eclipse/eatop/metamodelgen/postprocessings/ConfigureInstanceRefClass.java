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
package org.eclipse.eatop.metamodelgen.postprocessings;

import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.util.SchemaUtil;
import org.eclipse.eatop.metamodelgen.internal.messages.Messages;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;
import org.eclipse.eatop.metamodelgen.util.ModelUtil;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreSwitch;

public class ConfigureInstanceRefClass extends PostProcessingTemplate {

	private static final String SUFFIX_NAME = IEASTADLConstants.SUFFIX_NAME;

	private static final String SUFFIX_NAME_PLURAL = IEASTADLConstants.SUFFIX_NAME_PLURAL;

	private EcoreSwitch<Object> eSwitch = new EcoreSwitch<Object>() {

		@Override
		public Object caseEClass(EClass object) {
			return configure(object);
		}

		@Override
		public Object caseEPackage(EPackage object) {
			return configure(object);
		}

		@Override
		public Object caseEReference(EReference object) {
			return configure(object);
		}

	};

	/**
	 * Iterate through EClassifier and Sub-EPackage's of a EPackage
	 * 
	 * @param ePackage
	 * @return ePackage
	 */
	private Object configure(EPackage ePackage) {

		Iterator<EClassifier> iterCls = ePackage.getEClassifiers().iterator();
		while (iterCls.hasNext()) {
			eSwitch.doSwitch(iterCls.next());
		}

		Iterator<EPackage> iterPackage = ePackage.getESubpackages().iterator();
		while (iterPackage.hasNext()) {
			eSwitch.doSwitch(iterPackage.next());
		}

		return ePackage;
	}

	/**
	 * Set TaggedValues for a EReference with stereotype = STEREOTYPE_INSTANCE_REF
	 * 
	 * @param eRef
	 * @return eRef
	 */
	private Object configure(EReference eRef) {

		if (eRef != null && eRef.getEType() != null) {
			// has stereotype instance reference?
			if (ModelUtil.getStereotype(eRef.getEType()).equals(IEASTADLConstants.STEREOTYPE_INSTANCE_REF)) {
				// the aggregation from source to instanceRef class is found

				ModelUtil.setTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_NAME, SchemaUtil.buildXmlName(eRef.getName()) + SUFFIX_NAME);
				ModelUtil.setTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_NAME_PLURAL, SchemaUtil.buildXmlName(eRef.getName())
						+ SUFFIX_NAME_PLURAL);
				ModelUtil.setTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_ELEMENT, Boolean.TRUE.toString());
				ModelUtil.setTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_ELEMENT, Boolean.FALSE.toString());
				ModelUtil.setTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_WRAPPER_ELEMENT, Boolean.FALSE.toString());
				eRef.setResolveProxies(false);
			}
		} else {
			logger.error(MessageFormat.format(Messages.logger_NullValue, eRef));
		}

		return eRef;
	}

	/**
	 * @param eClass
	 * @return eClass
	 */
	private Object configure(EClass eClass) {

		Iterator<EStructuralFeature> elementIter;

		// is stereotype instanceRef applied to eClass
		if (ModelUtil.getStereotype(eClass).equals(IEASTADLConstants.STEREOTYPE_INSTANCE_REF)) {
			// set XML name of instance ref class
			// split at "_"
			String name = eClass.getName();
			if (null != name) {
				String[] parts = name.split("_"); //$NON-NLS-1$
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < parts.length; i++) {
					buffer.append(SchemaUtil.buildXmlName(parts[i]));
					if (i != parts.length - 1) {
						// to not add "--" for last element
						buffer.append("--"); //$NON-NLS-1$
					}
				}
				buffer.append(SUFFIX_NAME);
				name = buffer.toString();
			}

			ModelUtil.addDefaultTaggedValue(eClass, IEASTADLConstants.TAGGED_VALUE_XML_NAME, name);

			elementIter = eClass.getEStructuralFeatures().iterator();

			while (elementIter.hasNext()) {

				EStructuralFeature feature = elementIter.next();
				ModelUtil.setTaggedValue(feature, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_ELEMENT, Boolean.TRUE.toString());
				ModelUtil.setTaggedValue(feature, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_WRAPPER_ELEMENT, Boolean.FALSE.toString());

				if (feature instanceof EReference && ((EReference) feature).isContainer()) {
					feature.setTransient(true);
				}

			}
		}

		elementIter = eClass.getEStructuralFeatures().iterator();
		while (elementIter.hasNext()) {
			eSwitch.doSwitch(elementIter.next());
		}

		return eClass;
	}

	@Override
	public void execute() {
		configure(model);
	}

}
