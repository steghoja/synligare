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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.AddPersistenceMapping;
import org.eclipse.eatop.metamodelgen.serialization.generators.persistencemapping.EASTADLXMLPersistenceMappingGenerator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sphinx.emf.serialization.generators.persistencemapping.XMLPersistenceMappingGenerator;

public class EASTADLAddPersistenceMapping extends AddPersistenceMapping {

	public EASTADLAddPersistenceMapping(File modelFile, IProgressMonitor monitor) {
		super(modelFile, monitor);
	}

	@Override
	protected void addXMLPersistenceMapping() throws IOException {

		XMLPersistenceMappingGenerator xmlPersistenceMappingGenerator = new EASTADLXMLPersistenceMappingGenerator(model);

		if (xmlPersistenceMappingGenerator != null) {
			model = (EPackage) xmlPersistenceMappingGenerator.execute(monitor);
		}

		// serialize the ecore for the first time here so we can load it from file system in other PostProcessings,
		// otherwise we will get an invalid genmodel
		org.eclipse.eatop.eaadapter.ea2ecore.Util.saveModel(model, modelFile);
	}

}
