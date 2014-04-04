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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sphinx.emf.serialization.generators.persistencemapping.XMLPersistenceMappingGenerator;

public class AddPersistenceMapping extends PostProcessingTemplate {

	protected File modelFile;
	protected IProgressMonitor monitor;

	public AddPersistenceMapping(File modelFile, IProgressMonitor monitor) {
		this.modelFile = modelFile;
		this.monitor = monitor;
	}

	protected void addXMLPersistenceMapping() throws IOException {

		XMLPersistenceMappingGenerator xmlPersistenceMappingGenerator = new XMLPersistenceMappingGenerator(model);

		if (xmlPersistenceMappingGenerator != null) {
			model = (EPackage) xmlPersistenceMappingGenerator.execute(monitor);
		}

		// Note: we have to serialize the ecore for the first time here so we can load it from file system in other
		// PostProcessings otherwise we will get an invalid genmodel
		org.eclipse.eatop.eaadapter.ea2ecore.Util.saveModel(model, modelFile);
	}

	@Override
	public void execute() {
		try {
			addXMLPersistenceMapping();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
