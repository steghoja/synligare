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

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.CreateXSDSchema;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.eatop.metamodelgen.serialization.generators.xsd.EASTADLEcore2XSDGenerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sphinx.emf.serialization.generators.xsd.Ecore2XSDGenerator;

public class EASTADLCreateXSDSchema extends CreateXSDSchema {
	public EASTADLCreateXSDSchema(IProgressMonitor monitor, File modelFile, IFile eapFile) {
		super(monitor, modelFile, eapFile);
	}

	@Override
	protected void createXSD() throws IOException {
		URI xsdFileURI = URI.createPlatformResourceURI(
				eapFile.getFullPath().toString().replace(IConstants.EAP_FILE_POSTFIX, IConstants.XSD_FILE_POSTFIX), true);

		// xtend ecore to xsd generator
		Ecore2XSDGenerator ecore2XSDGenerator = new EASTADLEcore2XSDGenerator(xsdFileURI, schemaFile, model);

		if (ecore2XSDGenerator != null) {
			ecore2XSDGenerator.run(monitor);
		}
	}
}
