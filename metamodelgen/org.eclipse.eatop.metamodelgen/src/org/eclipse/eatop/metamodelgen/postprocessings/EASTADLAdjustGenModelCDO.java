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
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.AdjustGenModel;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;
import org.eclipse.eatop.metamodelgen.util.ITemplateConstants;
import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class EASTADLAdjustGenModelCDO extends AdjustGenModel {

	private static final String GEASTADL_GENMODEL = IEASTADLConstants.GEASTADL_GENMODEL;
	protected String rootExtendsInterface = null;
	protected String featureDelegation = null;

	public String getGenModelProperties() {
		return "../../../../../../../" + IEASTADLConstants.EASTADL_SOURCE_PATH + IEASTADLConstants.EASTADL_CDO_GENMODEL_PROPERTIES; // org.eclipse.eatop.metamodelgen.source/gmodel/genmodel_eastadl.properties //$NON-NLS-1$
	}

	@Override
	public String getModelConfigPropertiesPath() {
		return "../../../../../../../" + IEASTADLConstants.EASTADL_SOURCE_PATH + IConstants.SOURCE_MODEL_ROOT_PATH //$NON-NLS-1$
				+ IEASTADLConstants.SOURCE_REVISION_NAME_PREFIX; // "/model/eastadl-";
	}

	public EASTADLAdjustGenModelCDO(File modelFile, String modelProject, String version, String metaModelType) throws IOException {
		super(modelFile, modelProject, version);

		// load GenModel-Properties from properties file
		Properties props = new Properties();
		InputStream in;
		URL url = getClass().getResource(getGenModelProperties());
		in = url.openStream();
		props.load(in);
		in.close();
		fileExtensions = props.getProperty(ITemplateConstants.FILE_EXTENSIONS);
		complianceLevel = props.getProperty(ITemplateConstants.COMPLIANCE_LEVEL);
		nonNLSMarkers = Boolean.valueOf(props.getProperty(ITemplateConstants.NON_NLS_MARKERS));
		runtimeVersion = props.getProperty(ITemplateConstants.RUNTIME_VERSION);
		modelPluginClass = props.getProperty(ITemplateConstants.MODEL_PLUGIN_CLASS);
		modelPluginVariables = props.getProperty(ITemplateConstants.MODEL_PLUGIN_VARIABLES);
		editPluginClass = props.getProperty(ITemplateConstants.EDIT_PLUGIN_CLASS);
		editPluginVariables = props.getProperty(ITemplateConstants.EDIT_PLUGIN_VARIABLES);
		creationIcons = Boolean.valueOf(props.getProperty(ITemplateConstants.CREATION_ICONS));
		providerRootExtendsClass = props.getProperty(ITemplateConstants.PROVIDER_ROOT_EXTENDS_CLASS);
		rootExtendsClass = props.getProperty(ITemplateConstants.ROOT_EXTENDS_CLASS);
		rootPackageBasePackage = props.getProperty(ITemplateConstants.ROOT_PACKAGE_BASE_PACKAGE);
		rootExtendsInterface = props.getProperty(ITemplateConstants.ROOT_EXTENDS_INTERFACE);
		featureDelegation = props.getProperty(ITemplateConstants.FEATURE_DELEGATION);

		if (version != null && version.length() > 0) {

			URL modelCfgUrl = getClass().getResource(getModelConfigPropertiesPath() + version + "/" + MODEL_CONFIG_PROPERTIES_NAME); //$NON-NLS-1$
			in = modelCfgUrl.openStream();
			props.load(in);
			in.close();
			rootPackageContentTypeId = props.getProperty(ITemplateConstants.ROOT_PACKAGE_CONTENT_TYPE_ID);
		}
	}

	@Override
	protected GenModel addUsedGenPackage(GenModel genModel, IFile iFile, ResourceSet resourceSet) {
		IContainer folder = iFile.getParent();

		// Add used gen package: GEASTADL package
		IResource geastadlGenModel = folder.findMember(GEASTADL_GENMODEL);
		if (geastadlGenModel != null && geastadlGenModel instanceof IFile && geastadlGenModel.exists()) {
			UsedGenModel usedGenModel = new UsedGenModel();
			usedGenModel.setFile((IFile) geastadlGenModel);
			List<GenPackage> genPackageList = usedGenModel.load(resourceSet);
			genModel.getUsedGenPackages().addAll(genPackageList);
		}

		return genModel;
	}

	@Override
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

		if (featureDelegation != null && "Reflective".equals(featureDelegation)) { //$NON-NLS-1$
			genModel.setFeatureDelegation(GenDelegationKind.REFLECTIVE_LITERAL);
		}

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

		if (rootExtendsInterface != null) {
			genModel.setRootExtendsInterface(rootExtendsInterface);
		}

		genModel.setCreationSubmenus(true);
		genModel.setCreationIcons(creationIcons);

		genModel.setUpdateClasspath(true);

		// Disable Editor and Tests generation
		genModel.setEditorDirectory(""); //$NON-NLS-1$
		genModel.setTestsDirectory(""); //$NON-NLS-1$

		genModel.setCanGenerate(true);
	}
}
