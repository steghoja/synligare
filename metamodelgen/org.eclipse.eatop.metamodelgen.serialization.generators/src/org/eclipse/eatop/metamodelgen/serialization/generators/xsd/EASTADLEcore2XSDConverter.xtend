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

import org.eclipse.sphinx.emf.serialization.generators.xsd.Ecore2XSDConverter
import org.eclipse.sphinx.emf.serialization.generators.xsd.Ecore2XSDFactory
import org.eclipse.xsd.XSDSchema

class EASTADLEcore2XSDConverter extends Ecore2XSDConverter{
	
	new(Ecore2XSDFactory xsdFactory, XSDSchema xsdSchema) {
		super(xsdFactory, xsdSchema)
	}
	
}