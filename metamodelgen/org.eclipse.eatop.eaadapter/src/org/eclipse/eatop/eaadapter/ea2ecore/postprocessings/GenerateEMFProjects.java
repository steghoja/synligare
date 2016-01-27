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
import java.text.MessageFormat;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.Activator;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.DelegatingRegistry;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelFactoryImpl;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class GenerateEMFProjects extends PostProcessingTemplate {

	protected IProgressMonitor monitor;
	protected File modelFile;
	protected boolean modeling = true;
	protected boolean edit = true;
	protected String version;
	protected static String ea2ecorePluginId = Activator.ID;

	public GenerateEMFProjects(IProgressMonitor monitor, File modelFile, boolean modeling, boolean edit, String version) {
		this.monitor = monitor;
		this.modelFile = modelFile;
		this.modeling = modeling;
		this.edit = edit;
		this.version = version;
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
					progressMonitor.beginTask(MessageFormat.format(Messages.task_GenerateEMFProject, genModelFile.getName()), 2);

					ResourceSet resourceSet = new ResourceSetImpl();
					resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(false));

					// insert GenModel classes
					EPackage.Registry pr = resourceSet.getPackageRegistry();
					EPackage p = pr.getEPackage(GenModelPackage.eNS_URI);
					p.setEFactoryInstance(new GenModelFactoryImpl());

					URI uri = URI.createFileURI(genModelFile.getAbsolutePath());
					Resource r = resourceSet.getResource(uri, true);
					GenModel genModel = (GenModel) r.getContents().get(0);
					IStatus status = genModel.validate();
					if (status.isOK()) {
						Registry dr = new DelegatingRegistry(GeneratorAdapterFactory.Descriptor.Registry.INSTANCE);

						// To be provided by custom

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

	// To be override
	protected String getSourceRevisionName() {
		return version;
	}

}
