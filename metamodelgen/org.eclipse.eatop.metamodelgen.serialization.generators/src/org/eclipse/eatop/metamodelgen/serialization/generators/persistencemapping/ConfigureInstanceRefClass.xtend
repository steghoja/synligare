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
 
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EReference
import org.eclipse.sphinx.emf.serialization.XMLPersistenceMappingExtendedMetaData
import org.eclipse.sphinx.emf.serialization.generators.util.Ecore2XSDUtil
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.core.runtime.SubMonitor
import org.eclipse.core.runtime.OperationCanceledException
import org.eclipse.sphinx.emf.serialization.generators.util.IGeneratorConstants
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.eatop.metamodelgen.serialization.generators.util.ITaggedValueConstants
import org.eclipse.eatop.metamodelgen.serialization.generators.util.Eastadl2XSDUtil

class ConfigureInstanceRefClass {
	EPackage rootModel;
	  
	String SUFFIX_NAME = IGeneratorConstants.SUFFIX_NAME;
	String SUFFIX_NAME_PLURAL = IGeneratorConstants.SUFFIX_NAME_PLURAL;
	
	new(EPackage rootModel) {
		this.rootModel = rootModel;
	}   
	
	def EPackage execute(IProgressMonitor monitor) {		
		monitor.subTask("Configure Instance Ref Class");
		
		// configure tagged values
		configure(rootModel, monitor);		
		return rootModel;
	}
	
	def protected EObject doSwitch(EObject object, IProgressMonitor monitor){
	 	return switch object {	 		
	 		case object instanceof EClass : configure(object as EClass, monitor)
	 		case object instanceof EPackage : configure(object as EPackage, monitor)
	 		case object instanceof EReference : configure(object as EReference, monitor)
	 	}
	 }	
	
	/**
	 * Iterate through EClassifier and Sub-EPackage's of a EPackage
	 * 
	 * @param ePackage
	 * @return ePackage
	 */
	def protected EObject configure(EPackage ePackage, IProgressMonitor monitor) {		
		val SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}
		
		// for eClassifiers
		val SubMonitor progress1 = progress.newChild(50).setWorkRemaining(ePackage.getEClassifiers().size());
		ePackage.getEClassifiers().forEach[
			doSwitch(it, progress1);
			
			progress1.worked(1);
			if (progress1.isCanceled()) {
				throw new OperationCanceledException();
			}
		]

		// for subpackages
		val SubMonitor progress2 = progress.newChild(50).setWorkRemaining(ePackage.getESubpackages().size());
		ePackage.getESubpackages().forEach[
			doSwitch(it, progress2);
			
			progress2.worked(1);
			if (progress2.isCanceled()) {
				throw new OperationCanceledException();
			}
		]

		return ePackage;
	}
	
	/**
	 * 
	 * @param eClass
	 * @return eClass
	 */
	def protected EObject configure(EClass eClass, IProgressMonitor monitor) {
		val SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}
				
		//is stereotype instanceRef applied to eClass
		val String stereotype = EcoreUtil.getAnnotation(eClass, ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
		if (ITaggedValueConstants.STEREOTYPE_INSTANCE_REF.equals(stereotype)) {
			XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLName(eClass, Eastadl2XSDUtil::getInstanceRefClassNameSingle(eClass));
            XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLWrapperName(eClass, Eastadl2XSDUtil::getInstanceRefClassNamePlural(eClass));
            	
			eClass.getEStructuralFeatures().forEach[
				XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLPersistenceMappingStrategy(it, XMLPersistenceMappingExtendedMetaData.XML_PERSISTENCE_MAPPING_STRATEGY__0100__FEATURE_ELEMENT)				
				//MBR Proposal: move setting of attributes and tags to configure(EReference)		
				if (it instanceof EReference && (it as EReference).isContainer()) {
					(it as EReference).setTransient(true);
				}
			]
		}
		
		progress.worked(40);
		val SubMonitor featureProgress =  progress.newChild(60).setWorkRemaining(eClass.getEStructuralFeatures().size());
		eClass.getEStructuralFeatures().forEach[
			doSwitch(it, featureProgress);
			
			featureProgress.worked(1);
			if (featureProgress.isCanceled()) {
				throw new OperationCanceledException();
			}
		]	
		return eClass;
	}
	
	
	/**
	 * Set TaggedValues for a EReference with stereotype = STEREOTYPE_INSTANCE_REF
	 * 
	 * @param eRef
	 * @return eRef
	 */
	def protected EObject configure(EReference eRef, IProgressMonitor monitor) {
		
		if (eRef != null && eRef.getEType() != null)  {
			// has stereotype instance reference?
			val String stereotype = EcoreUtil.getAnnotation(eRef.getEType(), ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
			if (ITaggedValueConstants.STEREOTYPE_INSTANCE_REF.equals(stereotype)) {
				// the aggregation from source to instanceRef class is found
	            
	            XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLName(eRef, Ecore2XSDUtil::buildXmlName(eRef.getName()) + SUFFIX_NAME);
            	XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLWrapperName(eRef, Ecore2XSDUtil::buildXmlName(eRef.getName()) + SUFFIX_NAME_PLURAL);
            	
            	if (eRef.isMany()) {
            		XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLPersistenceMappingStrategy(eRef, XMLPersistenceMappingExtendedMetaData.XML_PERSISTENCE_MAPPING_STRATEGY__1100__FEATURE_WRAPPER_ELEMENT__FEATURE_ELEMENT);
            	} else {
            		XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLPersistenceMappingStrategy(eRef, XMLPersistenceMappingExtendedMetaData.XML_PERSISTENCE_MAPPING_STRATEGY__0100__FEATURE_ELEMENT);
            		
            	}
				eRef.setResolveProxies(false);
			}
		} else {
			System.out.print("Null value detected reference " + eRef);
		}
		
		monitor.done();
		return eRef;
	}
		
}
