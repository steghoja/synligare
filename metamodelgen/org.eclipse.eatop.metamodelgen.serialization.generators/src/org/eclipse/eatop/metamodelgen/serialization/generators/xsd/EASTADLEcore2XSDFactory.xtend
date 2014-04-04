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

package org.eclipse.eatop.metamodelgen.serialization.generators.xsd

import java.util.ArrayList
import java.util.List
import java.util.Set
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.sphinx.emf.serialization.generators.xsd.Ecore2XSDFactory
import org.eclipse.xsd.XSDAttributeDeclaration
import org.eclipse.xsd.XSDAttributeUse
import org.eclipse.xsd.XSDAttributeUseCategory
import org.eclipse.xsd.XSDComplexTypeDefinition
import org.eclipse.xsd.XSDCompositor
import org.eclipse.xsd.XSDDerivationMethod
import org.eclipse.xsd.XSDElementDeclaration
import org.eclipse.xsd.XSDFactory
import org.eclipse.xsd.XSDModelGroup
import org.eclipse.xsd.XSDParticle
import org.eclipse.xsd.XSDSchema
import org.eclipse.xsd.XSDSimpleTypeDefinition
import org.eclipse.xsd.XSDTypeDefinition
import org.eclipse.sphinx.emf.serialization.generators.util.IGeneratorConstants
import org.eclipse.emf.ecore.ENamedElement
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.eatop.metamodelgen.serialization.generators.util.Eastadl2XSDUtil
import org.eclipse.eatop.metamodelgen.serialization.generators.util.ITaggedValueConstants
import org.eclipse.emf.ecore.EReference
import org.eclipse.sphinx.emf.serialization.generators.util.Ecore2XSDUtil
import org.eclipse.emf.ecore.EDataType
import org.eclipse.emf.ecore.util.ExtendedMetaData
import org.eclipse.core.runtime.IProgressMonitor
import java.util.Map
import org.eclipse.eatop.metamodelgen.serialization.generators.util.IEASTADLGeneratorConstants

class EASTADLEcore2XSDFactory extends Ecore2XSDFactory { 

	new(EPackage ecoreModel) {
		super(ecoreModel);
	}
	
  	/**
	 * provide the actual referenced type xml name
	 * in general case, it is the upper case of the fetched xml name
	 * in EAST-ADL, it may also be +"-IREF" or +"-TREF"
	 * */
	override protected String getTypeXMLName(String xmlName, ENamedElement eElement){
		val EPackage rootPackage = EcoreUtil.getRootContainer(eElement) as EPackage;
		val String nsURI = rootPackage.getNsURI();
		
		// if the type is created in the same xsd, return the fetched xmlName
		if(nsURI.equals(xsdExtendedMetaData.getNamespace(ecoreModel))){
			return xmlName;
		}
		// if it is Ecore element
		else if(nsURI.equals(IGeneratorConstants.DEFAULT_ECORE_NAMESPACE)){
			return xmlName;
		} 
		// if it is in referenced ecores
		else if(referencedEcores.contains(rootPackage)){
			//return XSDExtendedMetaDataUtil::buildXmlName(xmlName);
			// singular name by default
			var String result = Eastadl2XSDUtil::getSingularName(eElement);
			if(eElement instanceof EClass){
				
				val String stereotype = EcoreUtil.getAnnotation(eElement, ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
				if(ITaggedValueConstants.STEREOTYPE_INSTANCE_REF.equals(stereotype)){
					result = Eastadl2XSDUtil::getInstanceRefClassNameSingle(eElement as EClass);
				}
			}
			else if(eElement instanceof EReference){
				val String stereotype = EcoreUtil.getAnnotation(eElement, ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
				
				if (eElement != null && (eElement as EReference).getEType() != null)  {
					// has stereotype instance reference?
					val String stereotype2 = EcoreUtil.getAnnotation((eElement as EReference).getEType(), ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
					if (ITaggedValueConstants.STEREOTYPE_INSTANCE_REF.equals(stereotype2)) {
				      // the aggregation from source to instanceRef class is found	            
	           			result = Ecore2XSDUtil::buildXmlName(eElement.getName()) + IGeneratorConstants.SUFFIX_NAME;
					}
				}
				else if(ITaggedValueConstants.STEREOTYPE_INSTANCE_REF.equals(stereotype)){
					result = Ecore2XSDUtil::buildXmlName(eElement.getName())+ IGeneratorConstants.SUFFIX_NAME;
				}
			}
			return result;
		}
		
		//return XSDExtendedMetaDataUtil::buildXmlName(xmlName);
		return Eastadl2XSDUtil::getSingularName(eElement);
	}	
	
  override protected String getRootNsPrefix(EPackage ePackage){
		var String nsPrefix = ePackage.getNsPrefix();
		if(nsPrefix == ""){
			nsPrefix = "EA";
		}
		return nsPrefix;
	}
	
	override protected void initSchemaImports(EPackage ePackage, XSDSchema xsdSchema, IProgressMonitor monitor) {
		val Map<String, String> externalSchemaLocations = xsdExtendedMetaData.getXMLExternalSchemaLocations(ePackage);
		
		// get referenced ecore root packages and import them
		val Set<EPackage> referencedEcores = getReferencedEcoreRootPackages(monitor);	

		// import for all referenced EPackage
		// no import is required for references to simple Ecore and GEASTADL
		referencedEcores.forEach[
			val String namespace = getGlobalXSDSchemaNamespace(it)
			if(!namespace.equals(IEASTADLGeneratorConstants.GEASTADL_NAMESPACE)){
				val String schemaLocation = getSchemaLocation(it)
				nsSchemaLocations.put(namespace, schemaLocation)
			}
		]

		// add additional imports if the "externalSchemaLocations" annotation of EPackage is configured
		nsSchemaLocations.putAll(externalSchemaLocations)
		
		// the list of imports are ordered alphabetically by the nsURI
		nsSchemaLocations.forEach[key, value | 
			xsdSchema.getContents.add(createXSDImport(key, value))]
	}
	
	override protected void loadImportReferencedXSD(EPackage referencedRootPackage, XSDSchema xsdSchema, ResourceSet resourceSet){
		// get the XSD namespace of the referenced root package
		val String namespace = getGlobalXSDSchemaNamespace(referencedRootPackage);
		
		// if geastadl.xsd
		if(namespace.equals(IEASTADLGeneratorConstants.GEASTADL_NAMESPACE)){
			return
		}
		else{
			super.loadImportReferencedXSD(referencedRootPackage, xsdSchema, resourceSet)
		}				
	}
	
  override protected URI getXSDSchemaFileURIFromNsURI(String nsURI){
  	return null;
  }
  
  override protected def List<EStructuralFeature> getEAllRelevantStructuralFeatures(EClass eClass){
		var List<EStructuralFeature> features = new ArrayList<EStructuralFeature>()
		for(EStructuralFeature feature : eClass.EAllStructuralFeatures){
			val EClassifier type = feature.EType
			if(!type.EPackage.nsURI.startsWith(IEASTADLGeneratorConstants.GEASTADL_NAMESPACE)){
				features.add(feature)
			}
		}
		
		return features
	}
	
	/**
	 * create xsd:element for a EReference with rule 5l.EReference_referenced1100Many of RMF 
	 * EReference & !containment & upperBound>1 && TTFF (1100) 
	 * 
	 * 5l:EReference_referenced1100Many ::= 
	 *	<xsd:element name="//roleXmlNamePlural//" minOccurs="//lowerMultiplicity//" maxOccurs="1">
     *	  <xsd:complexType>
	 *		<xsd:choice minOccurs="//lowerMultiplicity//" maxOccurs="//upperMultiplicity//">
     *		   <xsd:element name="//roleXmlName//">
	 *			</xsd:complexType>
     *			   <xsd:simpleContent>
	 *				<xsd:extension base="EA:REF">
     *				   <xsd:attribute name="TYPE" type="//typeXmlNsPrefix : typeXmlName//-
-SUBTYPE-ENUM" use="required"/>
	 *				</xsd:extension>
     *			   </xsd:simpleContent>
	 *			</xsd:complexType>
     *		   </xsd:element>
	 *		</xsd:choice>
     *   </xsd:complexType>
	 *	</xsd:element>
	 */
	override XSDParticle create_EReference_referenced1100Many_5l(
				EStructuralFeature feature, EClass eClass, XSDSchema xsdSchema, ArrayList<EClass> referencedClass) {

		var xsdParticle = xsdFactory.createXSDParticle()
		val String roleName = xsdExtendedMetaData.getXMLWrapperName(feature); 

		// <xsd:element name="//roleXmlNamePlural//" minOccurs="0 | 1" maxOccurs="1">
		var int lowerBound = feature.getLowerBound();
		if(lowerBound >1){
			lowerBound = 1;
		} 
		xsdParticle.setMinOccurs(lowerBound);
		xsdParticle.setMaxOccurs(1);

		val XSDElementDeclaration xsdElement = XSDFactory.eINSTANCE.createXSDElementDeclaration();
		xsdElement.setName(roleName); //

		// <xsd:complexType>
		val XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();

		// <xsd:choice minOccurs="//lowerMultiplicity//" maxOccurs="//upperMultiplicity//">
		val XSDParticle xsdParticle2 = XSDFactory.eINSTANCE.createXSDParticle();
		xsdParticle2.setMaxOccurs(feature.getUpperBound());
		xsdParticle2.setMinOccurs(feature.getLowerBound());
		val XSDModelGroup xsdModelGroup2 = XSDFactory.eINSTANCE.createXSDModelGroup();

		// set to "choice"
		xsdModelGroup2.setCompositor(XSDCompositor.CHOICE_LITERAL);
		
		// create a xsd:element for type
		val XSDParticle xsdParticle3 = XSDFactory.eINSTANCE.createXSDParticle();
		
		// <xsd:element name="//roleXmlName//">
		val XSDElementDeclaration xsdElement2 = XSDFactory.eINSTANCE.createXSDElementDeclaration();
		xsdElement2.setName(xsdExtendedMetaData.getXMLName(feature)); 
		
		// </xsd:complexType>
		val XSDComplexTypeDefinition xsdComplexTypeDefinition2 = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();		
		xsdComplexTypeDefinition2.setDerivationMethod(XSDDerivationMethod.EXTENSION_LITERAL);
		
		// Create a simple type definition, set its base type to be "EA:REF",
    	// and set it to be the content type of the complex type.
    	// <xsd:simpleContent>
    	// <xsd:extension base="EA:REF">
    	val XSDSimpleTypeDefinition simpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    	//xsdComplexTypeDefinition2.setBaseTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("REF"));
    	val XSDTypeDefinition baseTypeDef = xsdSchema.resolveTypeDefinitionURI(defaultUserSchemaNamespace+"#REF");
    	xsdComplexTypeDefinition2.setBaseTypeDefinition(baseTypeDef);
    	xsdComplexTypeDefinition2.setContent(simpleTypeDefinition);
			
    	// Create an attribute use to hold the reference, set its use to be optional,
    	// and add it to the complex type's attribute contents
	    val XSDAttributeUse simpleAttributeUse = xsdFactory.createXSDAttributeUse();
	    
	    // Create an attribute reference to simpleAttributeDeclaration in this schema.
    	val XSDAttributeDeclaration xsdAttribute = xsdFactory.createXSDAttributeDeclaration();
    	xsdAttribute.setName(IGeneratorConstants.TOKEN);
    	
		// set attribute type
		val EClassifier baseType = feature.getEType();
		// add to the list of referenced classes
		referencedClass.add(baseType as EClass);
    	val String uri = getElementXSDURI(baseType) + IGeneratorConstants.SUFFIX_SUBTPES_ENUM;
		var XSDTypeDefinition xsdTypeDefinition = xsdSchema.resolveTypeDefinitionURI(uri); 	
    	xsdAttribute.setTypeDefinition(xsdTypeDefinition as XSDSimpleTypeDefinition);
    	
    	simpleAttributeUse.setContent(xsdAttribute);
    	simpleAttributeUse.setUse(XSDAttributeUseCategory.REQUIRED_LITERAL);
    	
    	xsdComplexTypeDefinition2.getAttributeContents().add(simpleAttributeUse);			
		
		xsdElement2.setAnonymousTypeDefinition(xsdComplexTypeDefinition2);
		xsdParticle3.setContent(xsdElement2);
		xsdModelGroup2.getContents().add(xsdParticle3);
				
		xsdParticle2.setContent(xsdModelGroup2);
		xsdComplexTypeDefinition.setContent(xsdParticle2);
		xsdElement.setAnonymousTypeDefinition(xsdComplexTypeDefinition);
		xsdParticle.setContent(xsdElement);
		
		return xsdParticle
	}
	
	/**
	 * create xsd:element for a EReference with rule 5m.EReference_referenced0100Many of RMF 
	 * EReference & !containment & upperBound>1 && FTFF (0100) 
	 * 
	 * 5m:EReference_referenced0100Many ::= 
	 *	<xsd:element name="//roleXmlName//" minOccurs="//lowerMultiplicity//" maxOccurs="//upperMultiplicity//">
     *   <xsd:complexType>
	 *		<xsd:simpleContent>
     *		  <xsd:extension base="EA:REF">
	 *			<xsd:attribute name="TYPE" type="//typeXmlNsPrefix : typeXmlName//--SUBTYPES-ENUM" use="required"/>
     *	      </xsd:extension>
	 * 		</xsd:simpleContent>
     *   </xsd:complexType>
	 *  </xsd:element>
	 */
	override XSDParticle create_EReference_referenced0100Many_5m(
				EStructuralFeature feature, EClass eClass, XSDSchema xsdSchema, ArrayList<EClass> referencedClass) {

		var xsdParticle = xsdFactory.createXSDParticle()
		val String roleName = xsdExtendedMetaData.getXMLName(feature); 

		//<xsd:element name="//roleXmlName//" minOccurs="//lowerMultiplicity//" maxOccurs="//upperMultiplicity//">
		xsdParticle.setMinOccurs(feature.getLowerBound());
		xsdParticle.setMaxOccurs(feature.getUpperBound());

		val XSDElementDeclaration xsdElement = XSDFactory.eINSTANCE.createXSDElementDeclaration();
		xsdElement.setName(roleName); //
		
		// </xsd:complexType>
		val XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();		
		xsdComplexTypeDefinition.setDerivationMethod(XSDDerivationMethod.EXTENSION_LITERAL);
		
		// Create a simple type definition, set its base type to be "EA:REF",
    	// and set it to be the content type of the complex type.
    	// <xsd:simpleContent>
    	// <xsd:extension base="EA:REF">
    	val XSDSimpleTypeDefinition simpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    	val XSDTypeDefinition baseTypeDef = xsdSchema.resolveTypeDefinitionURI(defaultUserSchemaNamespace+"#REF");
    	xsdComplexTypeDefinition.setBaseTypeDefinition(baseTypeDef);
    	xsdComplexTypeDefinition.setContent(simpleTypeDefinition);
			
    	// Create an attribute use to hold the reference, set its use to be optional,
    	// and add it to the complex type's attribute contents
    	//<xsd:attribute name="TYPE" type="//typeXmlNsPrefix : typeXmlName//--SUBTYPES-ENUM" use="required"/>
	    val XSDAttributeUse simpleAttributeUse = xsdFactory.createXSDAttributeUse();
	    
	    // Create an attribute reference to simpleAttributeDeclaration in this schema.
    	val XSDAttributeDeclaration xsdAttribute = xsdFactory.createXSDAttributeDeclaration();
    	xsdAttribute.setName(IGeneratorConstants.TOKEN);
    	
		// set attribute type
		val EClassifier baseType = feature.getEType();
		// add to the list of referenced classes
		referencedClass.add(baseType as EClass);
    	val String uri = getElementXSDURI(baseType) + IGeneratorConstants.SUFFIX_SUBTPES_ENUM;
		var XSDTypeDefinition xsdTypeDefinition = xsdSchema.resolveTypeDefinitionURI(uri); 	
    	xsdAttribute.setTypeDefinition(xsdTypeDefinition as XSDSimpleTypeDefinition);
    	
    	simpleAttributeUse.setContent(xsdAttribute);
    	simpleAttributeUse.setUse(XSDAttributeUseCategory.REQUIRED_LITERAL);
    	
    	xsdComplexTypeDefinition.getAttributeContents().add(simpleAttributeUse);					
		xsdElement.setAnonymousTypeDefinition(xsdComplexTypeDefinition);		
		xsdParticle.setContent(xsdElement);
		
		return xsdParticle
	}
	
	/**
	 * create xsd:element for a EReference with rule 5n.EReference_referenced0100Single of RMF 
	 * EReference & !containment & upperBound=1 && FTFF (0100) 
	 * same as 5m:EReference_referenced0100Many
	 * 
	 * 5n:EReference_referenced0100Single ::= 
	 *	<xsd:element name="//roleXmlName//" minOccurs="//lowerMultiplicity//" maxOccurs="//upperMultiplicity//">
     *   <xsd:complexType>
	 *		<xsd:simpleContent>
     *		  <xsd:extension base="EA:REF">
	 *			<xsd:attribute name="TYPE" type="//typeXmlNsPrefix : typeXmlName//--SUBTYPES-ENUM" use="required"/>
     *	      </xsd:extension>
	 * 		</xsd:simpleContent>
     *   </xsd:complexType>
	 *  </xsd:element>
	 */
	override XSDParticle create_EReference_referenced0100Single_5n(
				EStructuralFeature feature, EClass eClass, XSDSchema xsdSchema, ArrayList<EClass> referencedClass) {
 		
 		var xsdParticle = xsdFactory.createXSDParticle()
		val String roleName = xsdExtendedMetaData.getXMLName(feature); 

		//<xsd:element name="//roleXmlName//" minOccurs="//lowerMultiplicity//" maxOccurs="//upperMultiplicity//">
		xsdParticle.setMinOccurs(feature.getLowerBound());
		xsdParticle.setMaxOccurs(feature.getUpperBound());

		val XSDElementDeclaration xsdElement = XSDFactory.eINSTANCE.createXSDElementDeclaration();
		xsdElement.setName(roleName); //
		
		// </xsd:complexType>
		val XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();		
		xsdComplexTypeDefinition.setDerivationMethod(XSDDerivationMethod.EXTENSION_LITERAL);
		
		// Create a simple type definition, set its base type to be "EA:REF",
    	// and set it to be the content type of the complex type.
    	// <xsd:simpleContent>
    	// <xsd:extension base="EA:REF">
    	val XSDSimpleTypeDefinition simpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    	val XSDTypeDefinition baseTypeDef = xsdSchema.resolveTypeDefinitionURI(defaultUserSchemaNamespace+"#REF");
    	xsdComplexTypeDefinition.setBaseTypeDefinition(baseTypeDef);
    	xsdComplexTypeDefinition.setContent(simpleTypeDefinition);
			
    	// Create an attribute use to hold the reference, set its use to be optional,
    	// and add it to the complex type's attribute contents
    	//<xsd:attribute name="TYPE" type="//typeXmlNsPrefix : typeXmlName//--SUBTYPES-ENUM" use="required"/>
	    val XSDAttributeUse simpleAttributeUse = xsdFactory.createXSDAttributeUse();
	    
	    // Create an attribute reference to simpleAttributeDeclaration in this schema.
    	val XSDAttributeDeclaration xsdAttribute = xsdFactory.createXSDAttributeDeclaration();
    	xsdAttribute.setName(IGeneratorConstants.TOKEN);
    	
		// set attribute type
		val EClassifier baseType = feature.getEType();
		// add to the list of referenced classes
		referencedClass.add(baseType as EClass);
    	val String uri = getElementXSDURI(baseType) + IGeneratorConstants.SUFFIX_SUBTPES_ENUM;
		var XSDTypeDefinition xsdTypeDefinition = xsdSchema.resolveTypeDefinitionURI(uri); 	
    	xsdAttribute.setTypeDefinition(xsdTypeDefinition as XSDSimpleTypeDefinition);
    	
    	simpleAttributeUse.setContent(xsdAttribute);
    	simpleAttributeUse.setUse(XSDAttributeUseCategory.REQUIRED_LITERAL);
    	
    	xsdComplexTypeDefinition.getAttributeContents().add(simpleAttributeUse);					
		xsdElement.setAnonymousTypeDefinition(xsdComplexTypeDefinition);		
		xsdParticle.setContent(xsdElement);
		
		return xsdParticle
	}
	
  	//==================================
	override Boolean isXMLPrimitiveXsdType(EDataType dataType) {
 
		// If it is EAST-ADL eastadl21.Boolean, return true 
		if(dataType.getEPackage().getName().equals("eastadl21") && dataType.getName().equals("Boolean")){
			return true;
		}
		
		val String stereotype = EcoreUtil.getAnnotation(dataType, ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
		val Boolean isPrimitive = ITaggedValueConstants.STEREOTYPE_PRIMITIVE.equals(stereotype);
		val Boolean isXsdTypeDefined = xsdExtendedMetaData.getXMLXsdSimpleType(dataType) != IGeneratorConstants.UNINITIALIZED_STRING;
		val Boolean isCustomSimpleTypeDefined = IGeneratorConstants.BOOLEAN_TRUE.equals(xsdExtendedMetaData.getXMLCustomSimpleType(dataType));
		var Boolean isPatternDefined = false;
		val List<String> patterns = ExtendedMetaData.INSTANCE.getPatternFacet(dataType);
		if (!patterns.isEmpty()) {
			isPatternDefined = patterns.get(0) != null;
		}

		// if is primitive, xsdType is defined, customType (custom simple type) is not defined, and pattern is not
		// defined
		if (isPrimitive && isXsdTypeDefined && !isCustomSimpleTypeDefined && !isPatternDefined) {
			return true;
		}

		return false;
	}
		
	override String getXsdSimpleType(EClassifier typeeClassifier){
		// If it is EAST-ADL eastadl21.Boolean, return "boolean", neverthless the custom simple type "string" is defined 
		if(typeeClassifier.getEPackage().getName().equals("eastadl21") && typeeClassifier.getName().equals("Boolean")){
			return "boolean";
		}
		
		super.getXsdSimpleType(typeeClassifier)
	}
}