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
package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import java.io.File;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sphinx.emf.serialization.generators.xsd.Ecore2XSDGenerator;

public class CreateXSDSchema extends PostProcessingTemplate {

	@SuppressWarnings("unused")
	private File modelFile;
	protected IFile eapFile;
	protected File schemaFile;
	protected IProgressMonitor monitor;

	public CreateXSDSchema(IProgressMonitor monitor, File modelFile, IFile eapFile) {
		this.modelFile = modelFile;
		this.eapFile = eapFile;
		schemaFile = new File(modelFile.getAbsolutePath().replace(IConstants.ECORE_FILE_POSTFIX, IConstants.XSD_FILE_POSTFIX));
		this.monitor = monitor;
	}

	protected void createXSD() throws IOException {
		URI xsdFileURI = URI.createPlatformResourceURI(
				eapFile.getFullPath().toString().replace(IConstants.EAP_FILE_POSTFIX, IConstants.XSD_FILE_POSTFIX), true);

		// xtend ecore to xsd generator
		Ecore2XSDGenerator ecore2XSDGenerator = new Ecore2XSDGenerator(xsdFileURI, model);

		if (ecore2XSDGenerator != null) {
			ecore2XSDGenerator.run(monitor);
		}
	}

	@Override
	public void execute() {
		try {
			monitor.beginTask(Messages.task_CreateXSDSchema, 100);
			createXSD();
			monitor.done();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
