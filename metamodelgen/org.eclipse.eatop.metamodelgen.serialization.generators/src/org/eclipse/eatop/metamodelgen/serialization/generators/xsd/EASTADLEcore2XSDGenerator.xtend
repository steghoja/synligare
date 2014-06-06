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

import org.eclipse.emf.common.util.URI
import java.io.File
import org.eclipse.emf.ecore.EPackage
import java.util.Map
import org.eclipse.sphinx.emf.serialization.generators.util.Ecore2XSDUtil
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.sphinx.emf.serialization.generators.xsd.Ecore2XSDFactory
import org.eclipse.sphinx.emf.serialization.generators.xsd.Ecore2XSDGenerator
import org.eclipse.sphinx.emf.serialization.generators.xsd.Ecore2XSDConverter
import org.eclipse.xsd.XSDSchema
import org.eclipse.eatop.metamodelgen.serialization.generators.util.IEASTADLGeneratorConstants

class EASTADLEcore2XSDGenerator extends Ecore2XSDGenerator {
	 
	new(URI xsdFileURI, EPackage ecoreModel) {
		super(xsdFileURI, ecoreModel)
	}
	
	// provide EAST-ADL ecore2xsd converter
	override protected Ecore2XSDConverter createEcore2XSDConverter(Ecore2XSDFactory xsdFactory, XSDSchema xsdSchema){
		return new EASTADLEcore2XSDConverter(xsdFactory, xsdSchema)
	}
	
	// provide EAST-ADL ecore2xsd generator
	override protected Ecore2XSDFactory createEcore2XSDFactory(){				
		return new EASTADLEcore2XSDFactory(ecoreModel);
	}
	
	override protected void refineXSDSchemaNamespace(IProgressMonitor monitor){
			
		val Map<String, String> namespaces = xsdSchema.getQNamePrefixToNamespaceMap();
		
		// change to global namesapce
		if(namespaces.containsValue(IEASTADLGeneratorConstants.EA_ROOT_PACKAGE_NS)){
			val String prefix = Ecore2XSDUtil::getNsPrefixFromQNamePrefixToNamespaceMap(namespaces, IEASTADLGeneratorConstants.EA_ROOT_PACKAGE_NS);
			if(prefix != null){
				namespaces.remove(prefix);
				namespaces.put(prefix, IEASTADLGeneratorConstants.EA_GLOBAL_NS);
			}
		}
		
		// change target namespace to global
		xsdSchema.setTargetNamespace(IEASTADLGeneratorConstants.EA_GLOBAL_NS);
		
		monitor.done();
	}
}