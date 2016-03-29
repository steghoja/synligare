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

import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.eatop.eaadapter.util.SchemaUtil;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;
import org.eclipse.eatop.metamodelgen.util.ModelUtil;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.util.EcoreSwitch;

public class ConfigureInstanceRefAssociation extends PostProcessingTemplate {

	private static final String SUFFIX_NAME = IEASTADLConstants.SUFFIX_NAME;

	private static final String SUFFIX_NAME_PLURAL = IEASTADLConstants.SUFFIX_NAME_PLURAL;

	private EClassifier identifiable = null;

	private HashSet<EReference> instanceRef = new HashSet<EReference>();

	private EcoreSwitch<Object> eSwitch = new EcoreSwitch<Object>() {

		@Override
		public Object caseEClass(EClass object) {
			return search(object);
		}

		@Override
		public Object caseEPackage(EPackage object) {
			return search(object);
		}

		@Override
		public Object caseEReference(EReference object) {
			return search(object);
		}

	};

	/**
	 * Iterate through classifiers and subpackages of a package.
	 * 
	 * @param ePackage
	 * @return ePackage
	 */
	private Object search(EPackage ePackage) {

		Iterator<EClassifier> clsifierIter = ePackage.getEClassifiers().iterator();
		while (clsifierIter.hasNext()) {
			eSwitch.doSwitch(clsifierIter.next());
		}

		Iterator<EPackage> pckIter = ePackage.getESubpackages().iterator();
		while (pckIter.hasNext()) {
			eSwitch.doSwitch(pckIter.next());
		}

		return ePackage;
	}

	/**
	 * Search EClass of Identifiable and iterate through structural features of a EClass.
	 * 
	 * @param eClass
	 * @return eClass
	 */
	private Object search(EClass eClass) {

		// find Identifiable class
		if (eClass.getName().equals(IEASTADLConstants.IDENTIFIABLE)) {
			identifiable = eClass;
		}

		// iterate over Attributes and References
		Iterator<EStructuralFeature> iter = eClass.getEStructuralFeatures().iterator();
		while (iter.hasNext()) {
			eSwitch.doSwitch(iter.next());
		}

		return eClass;
	}

	/**
	 * Search instance references
	 * 
	 * @param eRef
	 * @return eRef
	 */
	private Object search(EReference eRef) {

		// has Stereotype = ModelConstants.STEREOTYPE_INSTANCE_REF
		if (ModelUtil.getStereotype(eRef).equals(IEASTADLConstants.STEREOTYPE_INSTANCE_REF)) {
			instanceRef.add(eRef);
		}

		return eRef;
	}

	@Override
	public void execute() {

		// search all instanceRefs from the model
		search(model);

		while (instanceRef.iterator().hasNext()) {
			EReference eRef = instanceRef.iterator().next();
			transformInstanceRef(eRef);
			setTaggedValues(eRef);
		}

	}

	/**
	 * Set tagged values of an transformed instance reference
	 * 
	 * @param eRef
	 */
	private void setTaggedValues(EReference eRef) {

		ModelUtil.setTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_WRAPPER_ELEMENT, IConstants.FALSE);
		ModelUtil.setTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_ELEMENT, IConstants.TRUE);
		ModelUtil.setTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_ELEMENT, IConstants.FALSE);

		ModelUtil.setTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_NAME_PLURAL, SchemaUtil.buildXmlName(eRef.getName()) + SUFFIX_NAME_PLURAL);
		ModelUtil.setTaggedValue(eRef, IEASTADLConstants.TAGGED_VALUE_XML_NAME, SchemaUtil.buildXmlName(eRef.getName()) + SUFFIX_NAME);
	}

	/**
	 * Transforms an instance reference into a set of normal references
	 * 
	 * @param eRef
	 */
	private void transformInstanceRef(EReference eRef) {

		EClass sourceClass = eRef.getEContainingClass();
		EClassifier targetClass = eRef.getEType();

		// create generic InstanceRef class
		// create new class in the package of the sourceclass
		EClass instanceRefClass = EcoreFactory.eINSTANCE.createEClass();
		instanceRefClass.setName(getInstanceRefClassName(eRef));
		ModelUtil.setStereotype(instanceRefClass, IEASTADLConstants.STEREOTYPE_INSTANCE_REF);
		sourceClass.getEPackage().getEClassifiers().add(instanceRefClass);

		// add composite reference from sourceClass to instanceRefClass
		// modify existing reference
		eRef.setContainment(true);
		eRef.setEType(instanceRefClass);

		// add reference from instanceRefClass to target
		EReference targetReference = EcoreFactory.eINSTANCE.createEReference();
		targetReference.setName(targetClass.getName().substring(0, 1).toLowerCase() + targetClass.getName().substring(1));
		targetReference.setLowerBound(1);
		targetReference.setUpperBound(1);
		targetReference.setContainment(false);
		targetReference.setOrdered(false);
		targetReference.setEType(targetClass);
		ModelUtil.setStereotype(targetReference, IEASTADLConstants.STEREOTYPE_INSTANCE_REF_TARGET);
		instanceRefClass.getEStructuralFeatures().add(targetReference);

		// add reference from instanceRefClass to context
		EReference contextReference = EcoreFactory.eINSTANCE.createEReference();
		contextReference.setName("identifiable"); //$NON-NLS-1$
		contextReference.setLowerBound(0);
		contextReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
		contextReference.setContainment(false);
		contextReference.setOrdered(true);
		contextReference.setEType(identifiable);
		ModelUtil.setTaggedValue(contextReference, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_WRAPPER_ELEMENT, Boolean.FALSE.toString());
		ModelUtil.setTaggedValue(contextReference, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_ELEMENT, Boolean.TRUE.toString());
		ModelUtil.setStereotype(contextReference, IEASTADLConstants.STEREOTYPE_INSTANCE_REF_CONTEXT);
		instanceRefClass.getEStructuralFeatures().add(contextReference);
	}

	/**
	 * Returns the name of the class which represents the instance references.
	 * 
	 * @param eRef
	 * @return
	 */
	private String getInstanceRefClassName(EReference eRef) {
		return eRef.getEContainingClass().getName() + "_" + eRef.getName(); //$NON-NLS-1$
	}
}
