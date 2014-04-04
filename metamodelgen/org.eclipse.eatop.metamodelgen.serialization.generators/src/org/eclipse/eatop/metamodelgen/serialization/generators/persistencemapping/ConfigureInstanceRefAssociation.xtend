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
 package org.eclipse.eatop.metamodelgen.serialization.generators.persistencemapping

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.sphinx.emf.serialization.generators.util.Ecore2XSDUtil
import java.util.HashSet
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.emf.ecore.ETypedElement
import org.eclipse.sphinx.emf.serialization.XMLPersistenceMappingExtendedMetaData
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.SubMonitor
import org.eclipse.core.runtime.OperationCanceledException
import org.eclipse.sphinx.emf.serialization.generators.util.IGeneratorConstants
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.eatop.metamodelgen.serialization.generators.util.ITaggedValueConstants
import org.eclipse.eatop.metamodelgen.serialization.generators.util.Eastadl2XSDUtil

class ConfigureInstanceRefAssociation {
	EPackage rootModel;
	EClassifier identifiable = null;
	HashSet<EReference> instanceRef = new HashSet<EReference>();
	
	String SUFFIX_NAME = IGeneratorConstants.SUFFIX_NAME;
	String SUFFIX_NAME_PLURAL = IGeneratorConstants.SUFFIX_NAME_PLURAL;
	
	new(EPackage rootModel) {
		this.rootModel = rootModel;
	}   
	  
	def protected EObject doSwitch(EObject object){
	 	return switch object {	 		
	 		case object instanceof EClass : search(object as EClass)
	 		case object instanceof EPackage : search(object as EPackage)
	 		case object instanceof EReference : search(object as EReference)
	 	}
	 }	
	
	/**
	 * Iterate through classifiers and subpackages of a package.
	 * 
	 * @param ePackage
	 * @return ePackage
	 */
	def protected EObject search(EPackage ePackage) {
		
		// for eClassifiers
		ePackage.getEClassifiers().forEach[doSwitch(it)]

		// for subpackages
		ePackage.getESubpackages().forEach[doSwitch(it)]

		return ePackage;
	}
	
	/**
	 * Search EClass of Identifiable and 
	 * iterate through structural features of a EClass.
	 * 
	 * @param eClass
	 * @return eClass
	 */
	def protected EObject search(EClass eClass) {
		
		//find Identifiable class
		if (eClass.getName().equals("Identifiable")) {
			identifiable = eClass;
		}
		
		//iterate over Attributes and References
		eClass.getEStructuralFeatures().forEach[doSwitch(it)]	
		return eClass;
	}
	
	/**
     * Search instance references
     * 
	 * @param eRef
	 * @return eRef
	 */
	def protected EObject search(EReference eRef) {
		
		//has Stereotype = ModelConstants.STEREOTYPE_INSTANCE_REF
		val String stereotype = EcoreUtil.getAnnotation(eRef, ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
		if (ITaggedValueConstants.STEREOTYPE_INSTANCE_REF.equals(stereotype)) {
			instanceRef.add(eRef);
		}
		
		return eRef;
	}
	
	def EPackage execute(IProgressMonitor monitor) {
		monitor.subTask("Configure Instance Ref Association");
		val SubMonitor progress = SubMonitor.convert(monitor, 100);
			if (progress.isCanceled()) {
				throw new OperationCanceledException();
			}
		
		// search all instanceRefs from the model
		search(rootModel);
		progress.worked(40);
		
		if(instanceRef != null){
			
			val SubMonitor subProgress = progress.newChild(60).setWorkRemaining(instanceRef.size());
			instanceRef.forEach[
				transformInstanceRef(it);
				setTaggedValues(it);
				
				subProgress.worked(1);
				if (subProgress.isCanceled()) {
					throw new OperationCanceledException();
				}
			]
		}
	
		progress.done();
		return rootModel;
	}
	
	/**
	 * Transforms an instance reference into a set of normal references
	 * 
	 * @param eRef
	 */
	def protected void transformInstanceRef(EReference eRef) {

		val EClass sourceClass = eRef.getEContainingClass();
		val EClassifier targetClass = eRef.getEType();

		// create generic InstanceRef class
		// create new class in the package of the sourceclass
		val EClass instanceRefClass = EcoreFactory.eINSTANCE.createEClass();
		instanceRefClass.setName(getInstanceRefClassName(eRef));
		Eastadl2XSDUtil::setStereotype(instanceRefClass, ITaggedValueConstants.STEREOTYPE_INSTANCE_REF);
		sourceClass.getEPackage().getEClassifiers().add(instanceRefClass);

		// add composite reference from sourceClass to instanceRefClass modify existing reference
		eRef.setContainment(true);
		eRef.setEType(instanceRefClass);

		// add reference from instanceRefClass to target
		val EReference targetReference = EcoreFactory.eINSTANCE.createEReference();
		targetReference.setName(targetClass.getName().substring(0, 1).toLowerCase() + targetClass.getName().substring(1));
		targetReference.setLowerBound(1);
		targetReference.setUpperBound(1);
		targetReference.setContainment(false);
		targetReference.setOrdered(false);
		targetReference.setEType(targetClass);
		Eastadl2XSDUtil::setStereotype(targetReference, ITaggedValueConstants.STEREOTYPE_INSTANCE_REF_TARGET);
		instanceRefClass.getEStructuralFeatures().add(targetReference);

		// add reference from instanceRefClass to context
		val EReference contextReference = EcoreFactory.eINSTANCE.createEReference();
		contextReference.setName("identifiable");
		contextReference.setLowerBound(0);
		contextReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
		contextReference.setContainment(false);
		contextReference.setOrdered(true);
		contextReference.setEType(identifiable);

		XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLPersistenceMappingStrategy(contextReference, XMLPersistenceMappingExtendedMetaData.XML_PERSISTENCE_MAPPING_STRATEGY__0100__FEATURE_ELEMENT);
		Eastadl2XSDUtil::setStereotype(contextReference, ITaggedValueConstants.STEREOTYPE_INSTANCE_REF_CONTEXT);

		instanceRefClass.getEStructuralFeatures().add(contextReference);
	}
	
	/**
     * Returns the name of the class which represents the instance references.
     * @param eRef
     * @return
     */
    def private String getInstanceRefClassName(EReference eRef) {
        return eRef.getEContainingClass().getName() + "_" + eRef.getName();
    }
    
    /**
	 * Set tagged values of an transformed instance reference
	 * 
	 * @param eRef
	 */
	def protected void setTaggedValues(EReference eRef) {
        XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLPersistenceMappingStrategy(eRef, XMLPersistenceMappingExtendedMetaData.XML_PERSISTENCE_MAPPING_STRATEGY__0100__FEATURE_ELEMENT)        
   		XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLWrapperName(eRef, Ecore2XSDUtil::buildXmlName(eRef.getName())+ SUFFIX_NAME_PLURAL);
   		XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLName(eRef, Ecore2XSDUtil::buildXmlName(eRef.getName())+ SUFFIX_NAME);
	}
	
}
