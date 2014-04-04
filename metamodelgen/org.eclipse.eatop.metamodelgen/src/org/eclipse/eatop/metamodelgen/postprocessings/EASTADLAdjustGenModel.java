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
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;

public class EASTADLAdjustGenModel extends AdjustGenModel {

	private static final String GEASTADL_GENMODEL = IEASTADLConstants.GEASTADL_GENMODEL;

	public String getGenModelProperties() {
		return "../../../../../../../" + IEASTADLConstants.EASTADL_SOURCE_PATH + IEASTADLConstants.EASTADL_GENMODEL_PROPERTIES; // org.eclipse.eatop.metamodelgen.source/gmodel/genmodel_eastadl.properties //$NON-NLS-1$
	}

	@Override
	public String getModelConfigPropertiesPath() {
		return "../../../../../../../" + IEASTADLConstants.EASTADL_SOURCE_PATH + IConstants.SOURCE_MODEL_ROOT_PATH //$NON-NLS-1$
				+ IEASTADLConstants.SOURCE_REVISION_NAME_PREFIX; // "/model/eastadl-";
	}

	public EASTADLAdjustGenModel(File modelFile, String modelProject, String version, String metaModelType) throws IOException {
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
		modelSuppressGenModelAnnotations = Boolean.valueOf(props.getProperty(ITemplateConstants.SUPPRESS_GENMODEL_ANNOTATIONS));

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
}
