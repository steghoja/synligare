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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class AdjustGenModel extends PostProcessingTemplate {

	protected static final String MODEL_CONFIG_PROPERTIES_NAME = IConstants.MODEL_CONFIG_PROPERTIES_NAME;

	protected File modelFile;
	protected String modelProject;

	/** GenModel-Properties */
	protected String fileExtensions = null;
	protected String complianceLevel = null;
	protected String copyrightTextFile = null;
	protected boolean nonNLSMarkers = false;
	protected String runtimeVersion = null;
	protected String modelPluginClass = null;
	protected String modelPluginVariables = null;
	protected String editProject = null;
	protected String editPluginClass = null;
	protected String editPluginVariables = null;
	protected String providerRootExtendsClass = null;
	protected boolean creationIcons = true;
	protected String rootPackageContentTypeId = null;
	protected String rootExtendsClass = null;

	protected String rootPackageBasePackage = null;
	protected boolean modelSuppressGenModelAnnotations = true;

	public static class UsedGenModel {

		private IFile file;

		public void setFile(IFile file) {
			this.file = file;
		}

		public List<GenPackage> load(ResourceSet resourceSet) {
			URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource resource = resourceSet.getResource(uri, true);
			EObject eObject = resource.getContents().get(0);
			if (eObject instanceof GenModel) {
				GenModel genModel = (GenModel) eObject;
				return genModel.getAllGenPackagesWithClassifiers();
			} else {
				throw new IllegalArgumentException("Used GenModel file '" + file + "' must contain a GenModel instance."); //$NON-NLS-1$//$NON-NLS-2$
			}
		}

		public List<GenPackage> loadRootGenPackage(ResourceSet resourceSet) {
			URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
			Resource resource = resourceSet.getResource(uri, true);
			EObject eObject = resource.getContents().get(0);
			if (eObject instanceof GenModel) {
				GenModel genModel = (GenModel) eObject;
				return genModel.getGenPackages();
			} else {
				throw new IllegalArgumentException("Used GenModel file '" + file + "' must contain a GenModel instance."); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
	}

	/**
	 * To be override Get the MODEL_CONFIG_PROPERTIES_PATH depends on the type of metamodel
	 * 
	 * @return
	 */
	public String getModelConfigPropertiesPath() {
		return IConstants.SOURCE_MODEL_ROOT_PATH; // "/model/";
	}

	public AdjustGenModel(File modelFile, String modelProject, String version) {
		super();
		this.modelFile = modelFile;
		this.modelProject = modelProject;
		editProject = modelProject + "." + IConstants.EDIT_FOLDER; //$NON-NLS-1$
	}

	@Override
	public void execute() {
		String fileName = modelFile.getPath().replace(IConstants.ECORE_FILE_POSTFIX, IConstants.GENMODEL_FILE_POSTFIX);
		File genModelFile = new File(fileName);

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(false));
		URI uri;
		try {
			uri = URI.createFileURI(genModelFile.getAbsoluteFile().getCanonicalPath());
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return;
		}
		Resource resource = resourceSet.getResource(uri, true);
		EObject eObject = resource.getContents().get(0);
		GenModel genModel;
		if (eObject instanceof GenModel) {
			genModel = (GenModel) eObject;
		} else {
			logger.error(MessageFormat.format(Messages.logger_GenModelFileMustContainInstance, genModelFile));
			return;
		}

		IPath genModelPath = new Path(modelFile.getAbsolutePath());
		IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(genModelPath);

		List<GenPackage> before = genModel.getUsedGenPackages();
		for (GenPackage usedGenPackage : before) {
			logger.info(MessageFormat.format(Messages.logger_UsedGenPackage, usedGenPackage.getPackageName()));
		}

		genModel = addUsedGenPackage(genModel, iFile, resourceSet);

		try {
			resource.save(null);
		} catch (IOException e1) {
			logger.error(Messages.logger_ResourceSaveError);
		}

		IStatus status = genModel.validate();
		if (status.isOK()) {
			// Adjust GenModel settings
			adjustGenModel(genModel);

			// Adjust GenPackage settings
			for (GenPackage genPackage : genModel.getGenPackages()) {
				adjustGenPackage(genPackage);
			}

			try {
				resource.save(null);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.error(MessageFormat.format(Messages.logger_GenModelStatus, status));
		}
	}

	// To be provided by customer
	protected GenModel addUsedGenPackage(GenModel genModel, IFile iFile, ResourceSet resourceSet) {
		return genModel;
	}

	protected void adjustGenPackage(GenPackage genPackage) {

		// Leave resource/resource factory generation enabled for root package only;
		// disable it for all sub packages
		if (genPackage.eContainer() != genPackage.getGenModel()) {
			genPackage.setResource(GenResourceKind.NONE_LITERAL);
			genPackage.setFileExtensions(""); //$NON-NLS-1$
		} else {
			genPackage.setFileExtensions(fileExtensions);
			if (rootPackageContentTypeId != null) {
				genPackage.setContentTypeIdentifier(rootPackageContentTypeId);
			}
			if (rootPackageBasePackage != null) {
				genPackage.setBasePackage(rootPackageBasePackage);
			}
		}

		// Package Suffixes section
		genPackage.setProviderPackageSuffix(IConstants.EDIT_FOLDER + "." + IConstants.PROVIDER_PACKAGE_SUFFIX); //$NON-NLS-1$

		// Adjust sub Package settings
		for (GenPackage subGenPackage : genPackage.getNestedGenPackages()) {
			adjustGenPackage(subGenPackage);
		}
	}

	protected void adjustGenModel(GenModel genModel) {

		// All section
		if (complianceLevel != null) {
			genModel.setComplianceLevel(GenJDKLevel.get(complianceLevel));
		}

		if (copyrightTextFile != null) {
			genModel.setCopyrightText(readTextFileContent(copyrightTextFile));
		}

		genModel.setNonNLSMarkers(nonNLSMarkers);

		if (runtimeVersion != null) {
			genModel.setRuntimeVersion(GenRuntimeVersion.get(runtimeVersion));
		}

		// Model & Model Class Defaults sections
		genModel = setModelAndEditDirectory(genModel);

		if (modelPluginClass != null) {
			genModel.setModelPluginClass(rootPackageBasePackage + "." + genModel.getGenPackages().get(0).getPackageName() + "." + modelPluginClass); //$NON-NLS-1$ //$NON-NLS-2$
		}
		genModel.setModelPluginID(modelProject);
		if (modelPluginVariables != null) {
			for (String variable : modelPluginVariables.split(",")) { //$NON-NLS-1$
				if (variable.length() > 0) {
					genModel.getModelPluginVariables().add(variable);
				}
			}
		}

		// Edit section
		if (editPluginClass != null) {
			genModel.setEditPluginClass(rootPackageBasePackage
					+ "." + genModel.getGenPackages().get(0).getPackageName() + "." + IConstants.EDIT_FOLDER + "." + editPluginClass); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		genModel.setEditPluginID(editProject);
		if (editPluginVariables != null) {
			for (String variable : editPluginVariables.split(",")) { //$NON-NLS-1$
				if (variable.length() > 0) {
					genModel.getEditPluginVariables().add(variable);
				}
			}
		}

		if (providerRootExtendsClass != null) {
			genModel.setProviderRootExtendsClass(providerRootExtendsClass);
		}

		if (rootExtendsClass != null) {
			genModel.setRootExtendsClass(rootExtendsClass);
		}

		genModel.setCreationSubmenus(true);
		genModel.setCreationIcons(creationIcons);

		genModel.setUpdateClasspath(true);
		genModel.setSuppressGenModelAnnotations(modelSuppressGenModelAnnotations);

		// Disable Editor and Tests generation
		genModel.setEditorDirectory(""); //$NON-NLS-1$
		genModel.setTestsDirectory(""); //$NON-NLS-1$

		genModel.setCanGenerate(true);
	}

	// To be overriden
	protected GenModel setModelAndEditDirectory(GenModel genModel) {
		// Model & Model Class Defaults sections
		genModel.setModelDirectory("/" + modelProject + IConstants.SRC_DIRECTORY); //$NON-NLS-1$
		// Edit section
		genModel.setEditDirectory("/" + editProject + IConstants.SRC_DIRECTORY); //$NON-NLS-1$

		return genModel;
	}

	protected String readTextFileContent(String path) {
		StringBuilder content = new StringBuilder();
		BufferedReader input = null;
		try {
			input = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path)));
			String line = null;
			while ((line = input.readLine()) != null) {
				content.append(line);
				content.append(System.getProperty("line.separator")); //$NON-NLS-1$
			}
		} catch (Exception ex) {
			System.err.println("Unable to read content from file " + path + ": " + ex.getMessage()); //$NON-NLS-1$ //$NON-NLS-2$
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (Exception ex) {
				// Do nothing
			}
		}

		return content.toString();
	}
}
