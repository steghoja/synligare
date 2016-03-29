/**
 * <copyright>
 * 
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class CreateGenModel extends PostProcessingTemplate {

	private File modelFile;
	private String modelProject;

	public CreateGenModel(File modelFile, String modelProject) {
		this.modelFile = modelFile;
		this.modelProject = modelProject;
	}

	protected void createGenModel() throws IOException {
		IPath ecorePath = new Path(modelFile.getAbsolutePath());
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(false));
		EPackage ePackage = model;

		URI ecoreURI = URI.createFileURI(modelFile.getAbsolutePath().toString());
		Resource ecoreResource = Resource.Factory.Registry.INSTANCE.getFactory(ecoreURI).createResource(ecoreURI);
		ecoreResource.getContents().add(model);

		String fileName = modelFile.getPath().replace(IConstants.ECORE_FILE_POSTFIX, IConstants.GENMODEL_FILE_POSTFIX);
		File genModelFile = new File(fileName);
		IPath genModelPath = new Path(genModelFile.getAbsolutePath());
		URI genModelURI = URI.createFileURI(genModelPath.toString());
		Resource genModelResource = Resource.Factory.Registry.INSTANCE.getFactory(genModelURI).createResource(genModelURI);
		GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
		genModelResource.getContents().add(genModel);
		resourceSet.getResources().add(genModelResource);
		genModel.setModelDirectory("/" + modelProject + IConstants.SRC_DIRECTORY); //$NON-NLS-1$
		genModel.getForeignModel().add(ecorePath.toString());
		genModel.initialize(Collections.singleton(ePackage));
		genModel.setModelName(genModelURI.trimFileExtension().lastSegment());
		genModelResource.save(Collections.EMPTY_MAP);
		// we have to serialize the ecore for the first time here so we can load it from file system in other
		// PostProcessings, otherwise we will get an invalid genmodel
		org.eclipse.eatop.eaadapter.ea2ecore.Util.saveModel(model, modelFile);

	}

	@Override
	public void execute() {
		try {
			createGenModel();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}
