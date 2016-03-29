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

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.GenerateEMFProjects;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.eatop.metamodelgen.postprocessings.generator.ExtendedGenModelGeneratorAdapterFactory;
import org.eclipse.eatop.metamodelgen.templates.genmodel.EastadlGenModelFactory;
import org.eclipse.eatop.metamodelgen.templates.source.EastADLSourceRevisions;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.DelegatingRegistry;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.osgi.framework.Bundle;

public class EASTADLGenerateEMFProjects extends GenerateEMFProjects {

	protected static String MMGenSourcePluginId = IEASTADLConstants.EASTADL_SOURCE_PATH;

	public EASTADLGenerateEMFProjects(IProgressMonitor monitor, File modelFile, boolean modeling, boolean edit, String version) {
		super(monitor, modelFile, modeling, edit, version);
	}

	@Override
	public void execute() {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRunnable runnable = new IWorkspaceRunnable() {

			@Override
			public void run(IProgressMonitor progressMonitor) throws CoreException {
				try {
					String fileName = modelFile.getPath().replace(IConstants.ECORE_FILE_POSTFIX, IConstants.GENMODEL_FILE_POSTFIX);
					File genModelFile = new File(fileName);
					;
					progressMonitor.beginTask("Generating " + genModelFile.getName(), 2); //$NON-NLS-1$

					ResourceSet resourceSet = new ResourceSetImpl();
					resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(false));

					// insert EASTADL specific GenModel classes
					EPackage.Registry pr = resourceSet.getPackageRegistry();
					EPackage p = pr.getEPackage(GenModelPackage.eNS_URI);
					p.setEFactoryInstance(new EastadlGenModelFactory());

					URI uri = URI.createFileURI(genModelFile.getAbsolutePath());
					Resource r = resourceSet.getResource(uri, true);
					GenModel genModel = (GenModel) r.getContents().get(0);
					IStatus status = genModel.validate();
					if (status.isOK()) {

						Bundle bundle = Platform.getBundle(MMGenSourcePluginId);
						Registry dr = new DelegatingRegistry(GeneratorAdapterFactory.Descriptor.Registry.INSTANCE);
						EastADLSourceRevisions.initialize(bundle, IConstants.SOURCE_MODEL_ROOT_PATH, getSourceRevisionName());
						dr.addDescriptor(GenModelPackage.eNS_URI, ExtendedGenModelGeneratorAdapterFactory.DESCRIPTOR);

						Generator generator = new Generator(dr);
						generator.setInput(genModel);
						genModel.reconcile();
						genModel.setCanGenerate(true);
						genModel.setUpdateClasspath(true);

						Monitor monitor = CodeGenUtil.EclipseUtil.createMonitor(progressMonitor, 1);
						if (modeling) {
							generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, monitor);
						}
						if (edit) {
							generator.generate(genModel, GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, monitor);
						}
					} else {
						System.err.println(status);
						throw new CoreException(status);
					}
				} catch (Exception exception) {
					if (exception instanceof CoreException) {
						throw (CoreException) exception;
					} else {
						exception.printStackTrace();
						throw new CoreException(new Status(IStatus.ERROR, CodeGenEcorePlugin.getPlugin().getBundle().getSymbolicName(), 0,
								"EMF Error: " + exception.toString(), exception)); //$NON-NLS-1$
					}
				} finally {
					progressMonitor.done();
				}
			}
		};
		try {
			workspace.run(runnable, monitor);
		} catch (CoreException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	protected String getSourceRevisionName() {
		return IEASTADLConstants.SOURCE_REVISION_NAME_PREFIX + version;
	}

}
