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

import java.util.ArrayList
import java.util.List
import org.eclipse.eatop.metamodelgen.serialization.generators.util.Eastadl2XSDUtil
import org.eclipse.eatop.metamodelgen.serialization.generators.util.ITaggedValueConstants
import org.eclipse.emf.ecore.EAnnotation
import org.eclipse.emf.ecore.EAttribute
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.ETypedElement
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.util.ExtendedMetaData
import org.eclipse.sphinx.emf.serialization.XMLPersistenceMappingExtendedMetaData
import org.eclipse.sphinx.emf.serialization.generators.persistencemapping.CreateDefaultXSDExtendedMetaData
import org.eclipse.sphinx.emf.serialization.generators.util.Ecore2XSDUtil
import org.eclipse.sphinx.emf.serialization.generators.util.IGeneratorConstants

class CreateEASTADLDefaultXSDExtendedMetaData extends CreateDefaultXSDExtendedMetaData {	
		
	new(EPackage rootModel, String globalEClassName) {
		super(rootModel, globalEClassName);
	} 
	
	//set typeAttributeName annotation to be "TYPE"
	override protected EObject configure(EPackage ePackage) {
		super.configure(ePackage)
		XMLPersistenceMappingExtendedMetaData.INSTANCE.setXMLDefaultEReferenceReferencedTypeAttributeName(ePackage, IGeneratorConstants.TOKEN);
		return ePackage
	}
	
	override protected EObject configure(EClass eClass) {
		// configure default name
		configureClassifierNames(eClass)
		
		// configure namespace
		configureNamespace(eClass);
		
		// configure global element
		if(eClass.getName().equals(globalEClassName)){
			XMLPersistenceMappingExtendedMetaData.INSTANCE.setXMLGlobalElement(eClass, true);
		}
		
		// configure kind
		if (XMLPersistenceMappingExtendedMetaData.INSTANCE.getContentKind(eClass) != ExtendedMetaData.UNSPECIFIED_CONTENT) {
			// content kind is already set, so do not overwrite
		}
		else{ 			
			var int kind = ExtendedMetaData.ELEMENT_ONLY_CONTENT;
//			if (Ecore2XSDUtil::isComplexTypeWithSimpleContent(eClass)) {
//				kind = ExtendedMetaData.SIMPLE_CONTENT;
//			}
			XMLPersistenceMappingExtendedMetaData.INSTANCE.setContentKind(eClass, kind);
		}
		
		// configure features
		val String stereotype = EcoreUtil.getAnnotation(eClass, ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
		if (ITaggedValueConstants.STEREOTYPE_ATPMIXED.equalsIgnoreCase(stereotype)
			|| ITaggedValueConstants.STEREOTYPE_ATPMIXEDSTRING.equalsIgnoreCase(stereotype)) {

			eClass.getEStructuralFeatures().forEach[
				it.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
				it.setLowerBound(0);
				XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLPersistenceMappingStrategy(it, XMLPersistenceMappingExtendedMetaData.XML_PERSISTENCE_MAPPING_STRATEGY__0100__FEATURE_ELEMENT);
				val EClassifier eClassifier = it.getEType();
				if ((eClassifier instanceof EClass) && Ecore2XSDUtil::hasConcreteSubclasses(eClassifier as EClass, rootModel)) {
					// subclasses exists
					XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLPersistenceMappingStrategy(it, XMLPersistenceMappingExtendedMetaData.XML_PERSISTENCE_MAPPING_STRATEGY__0101__FEATURE_ELEMENT__CLASSIFIER_ELEMENT);
				} else {
					// subclasses not exists
					XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLPersistenceMappingStrategy(it, XMLPersistenceMappingExtendedMetaData.XML_PERSISTENCE_MAPPING_STRATEGY__0100__FEATURE_ELEMENT);
				}
			]
		}
		
		eClass.getEStructuralFeatures().forEach[doSwitch(it)]	
		return eClass;
	}
	
	/**
	 * Configures tagged values of a primitive Datatype
	 * 
	 * @param dataType
	 * @return
	 */
	override protected EObject configure(EDataType dataType) {
		// configure default Name
		configureClassifierNames(dataType)
		
		// configure default custom simple type		
		if(isPrimitive(dataType)){
			val String xsdSimpleType = EcoreUtil.getAnnotation(dataType, ITaggedValueConstants.ANNOTATION_TAGGED_VALUES, ITaggedValueConstants.TAGGED_VALUE_XML_XSD_TYPE);	
			XMLPersistenceMappingExtendedMetaData.INSTANCE.setXMLXsdSimpleType(dataType, xsdSimpleType);
			
			// by default: if custompattern is defined, then customType is also defined
			if(isCustomTypeDefined(dataType) && isCustomPatternDefined(dataType)){
				XMLPersistenceMappingExtendedMetaData.INSTANCE.setXMLCustomSimpleType(dataType, Boolean.TRUE.toString());
				val String customPattern = EcoreUtil.getAnnotation(dataType, ITaggedValueConstants.ANNOTATION_TAGGED_VALUES, ITaggedValueConstants.TAGGED_VALUE_XML_XSD_PATTERN);	
				// set pattern
				val List<String> pattern = new ArrayList<String>();
				pattern.add(customPattern)
				ExtendedMetaData.INSTANCE.setPatternFacet(dataType, pattern)
			}	
			else{
				XMLPersistenceMappingExtendedMetaData.INSTANCE.setXMLCustomSimpleType(dataType, Boolean.FALSE.toString());
			}	
		}
		else{
			XMLPersistenceMappingExtendedMetaData.INSTANCE.setXMLCustomSimpleType(dataType, Boolean.FALSE.toString());
		}

		return dataType;
	}
	
	override protected EObject configure(EAttribute eAttribute) {

		// cofigure as EStructuralFeature
		configure(eAttribute as EStructuralFeature);
		
		// set name
		XMLPersistenceMappingExtendedMetaData.INSTANCE.setName(eAttribute, XMLPersistenceMappingExtendedMetaData.INSTANCE.getXMLName(eAttribute));
	
		// set default feature kind = element
		ExtendedMetaData.INSTANCE.setFeatureKind(eAttribute, ExtendedMetaData.ELEMENT_FEATURE);
		
		val EAnnotation taggedValuesAnnotation = eAttribute.getEAnnotation(ITaggedValueConstants.ANNOTATION_TAGGED_VALUES);
		if (taggedValuesAnnotation != null && taggedValuesAnnotation.getDetails() != null) {
			val boolean isAttribute = "true".equalsIgnoreCase(taggedValuesAnnotation.getDetails().get(ITaggedValueConstants.TAGGED_VALUE_XML_ATTRIBUTE));
		
//			if (!isAttribute && Ecore2XSDUtil::isComplexTypeWithSimpleContent(eAttribute.getEContainingClass())) { 
//				ExtendedMetaData.INSTANCE.setFeatureKind(eAttribute, ExtendedMetaData.SIMPLE_FEATURE);
//			} else {
				var int kind = ExtendedMetaData.ELEMENT_FEATURE;
				if(isAttribute){
					kind = ExtendedMetaData.ATTRIBUTE_FEATURE;
				}
				ExtendedMetaData.INSTANCE.setFeatureKind(eAttribute, kind);
			//}
	
			if (!isAttribute) {
			//ExtendedMetaData.INSTANCE.setNamespace(eAttribute, getXMLNamespace());
			}
		}
			
		return eAttribute;
	}
	
	override protected void configureFeatureNames(EStructuralFeature feature) {
		// configure default Name
		XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLName(feature, Eastadl2XSDUtil::getSingularName(feature));
		XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLWrapperName(feature, Eastadl2XSDUtil::getPluralName(feature));		
	}
	
	override protected void configureClassifierNames(EClassifier eClass){
		XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLName(eClass, Eastadl2XSDUtil::getSingularName(eClass));
		XMLPersistenceMappingExtendedMetaData::INSTANCE.setXMLWrapperName(eClass, Eastadl2XSDUtil::getPluralName(eClass));
	}
	
	// features with explicitly set namespace are serialized qualified.
	override protected void configureNamespace(EStructuralFeature feature) {
		// set namespace only for features but not for attributes
		if(!(feature instanceof EAttribute)){
			val String namespace = XMLPersistenceMappingExtendedMetaData.INSTANCE.getNamespace(feature);
				if (null == namespace) {
					EcoreUtil.setAnnotation(feature, ExtendedMetaData.ANNOTATION_URI, "namespace", feature.EContainingClass.EPackage.nsURI);
			}			
		}
	}
	
	override protected void configureXMLAttribute(EStructuralFeature eStructuralFeature){
		
		var boolean isXMLAttribute = false;
		val EAnnotation taggedValuesAnnotation = eStructuralFeature.getEAnnotation(ITaggedValueConstants.ANNOTATION_TAGGED_VALUES);
		if (taggedValuesAnnotation != null && taggedValuesAnnotation.getDetails() != null) {
			val boolean isAttribute = "true".equalsIgnoreCase(taggedValuesAnnotation.getDetails().get(ITaggedValueConstants.TAGGED_VALUE_XML_ATTRIBUTE));
			if(eStructuralFeature instanceof EAttribute){
//				val EAttribute eAttribute = eStructuralFeature as EAttribute;
//				if (!isAttribute && Ecore2XSDUtil::isComplexTypeWithSimpleContent(eAttribute.getEContainingClass())) { 
//					isXMLAttribute = false;
//				} else 
				if(isAttribute){
						isXMLAttribute = true;
					}
			}
		}
		XMLPersistenceMappingExtendedMetaData.INSTANCE.setXMLAttribute(eStructuralFeature, isXMLAttribute);
	}
	
	def private Boolean isPrimitive(EDataType dataType){

		val String steroType = EcoreUtil.getAnnotation(dataType, ITaggedValueConstants.ANNOTATION_STEREOTYPE,
				ITaggedValueConstants.ANNOTATION_STEREOTYPE);
		if( steroType!= null && steroType.equals(ITaggedValueConstants.STEREOTYPE_PRIMITIVE))
        {
            return true;
        }
        return false;
	}
	
	/**
	 * is TaggedValue "xml.xsd.customType" defined
	 */
	def private Boolean isCustomTypeDefined(EDataType dataType){
		// get the tagged value of "xml.xsd.customType";
		val String customType = EcoreUtil.getAnnotation(dataType, ITaggedValueConstants.ANNOTATION_TAGGED_VALUES, ITaggedValueConstants.TAGGED_VALUE_XML_XSD_CUSTOM_TYPE );
        if( (customType != null) && (customType.length() != 0) ) {
            return true;
        }
        return false;
	}
	
	/**
	 * is TaggedValue "xml.xsd.pattern" defined
	 */
	def private Boolean isCustomPatternDefined(EDataType dataType){
		val String customPattern = EcoreUtil.getAnnotation(dataType, ITaggedValueConstants.ANNOTATION_TAGGED_VALUES, ITaggedValueConstants.TAGGED_VALUE_XML_XSD_PATTERN);
		if( (customPattern != null) && (customPattern.length() != 0) ) {
            return true;
        }
        return false;
	}
}