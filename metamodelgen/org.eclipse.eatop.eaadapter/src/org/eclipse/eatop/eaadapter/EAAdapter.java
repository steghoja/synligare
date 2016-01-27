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

package org.eclipse.eatop.eaadapter;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eatop.eaadapter.ea2ecore.EA2Ecore;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

import eaadapter.EARepository;
import eaadapter.EaadapterFactory;

/**
 * This class adapters the EAP file to Ecore file.
 */
public class EAAdapter {
	@SuppressWarnings("unused")
	private String metaModelType;

	@SuppressWarnings("unused")
	private IFile eapFile;
	@SuppressWarnings("unused")
	private File ecoreFile;
	private org.eclipse.emf.common.util.URI ecoreFileURI;
	private EPackage ecoreRootPkg;

	private EARepository eaRepository;
	private EA2Ecore ea2ecore;

	public EAAdapter(String metaModelType, IFile eapFile) {
		this.metaModelType = metaModelType;
		this.eapFile = eapFile;
		eaRepository = initEARepositoryModel(eapFile);

		ecoreFileURI = URI.createPlatformResourceURI(
				eapFile.getFullPath().toString().replace(IConstants.EAP_FILE_POSTFIX, IConstants.ECORE_FILE_POSTFIX), true);
		ecoreFile = new File(eapFile.getLocation().toFile().getAbsolutePath().replace(IConstants.EAP_FILE_POSTFIX, IConstants.ECORE_FILE_POSTFIX));
	}

	/**
	 * Create a new EARepository model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT mnick
	 */
	protected EARepository initEARepositoryModel(IFile eapFile) {
		File file = new File(eapFile.getLocation().toFile().getAbsolutePath());
		eaRepository = EaadapterFactory.eINSTANCE.createEARepository();
		eaRepository.setFile(file);
		return eaRepository;
	}

	/**
	 * Load EAP file. Construct the elements in EARepository.
	 * 
	 * @param monitor
	 */
	public void loadEapFile(IProgressMonitor monitor) {

		final long before = System.currentTimeMillis(); // starting time

		try {
			eaRepository.load(monitor);
		} catch (final Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					MessageDialog.openError(null, "Error", MessageFormat.format(Messages.error_Message, e.getMessage())); //$NON-NLS-1$
				}
			});
		}

		final long after = System.currentTimeMillis(); // stopping time
		System.out.print(MessageFormat.format(Messages.success_Message, (after - before) / 1000));

	}

	/**
	 * create EA2Ecore
	 * 
	 * @param rootPckName
	 * @return
	 * @throws Exception
	 */
	public EA2Ecore createEA2Ecore(String rootPckName) throws Exception {
		EARepository copy = EcoreUtil.copy(eaRepository);
		if (copy.getModels().size() == 0) {
			throw new Exception(Messages.exception_LoadModel);
		}

		// set root package name
		copy.getModels().get(0).setName(rootPckName);
		ea2ecore = new EA2Ecore(copy);
		return ea2ecore;
	}

	public EARepository getEARepository() {
		return eaRepository;
	}

	public List<PostProcessingTemplate> getPostProcessings() {
		return ea2ecore.getPostProcessings();
	}

	public void addPostProcessing(PostProcessingTemplate pp) {
		ea2ecore.getPostProcessings().add(pp);
	}

	/**
	 * Execute the postprocessings in EA2Ecore, and generate the Ecore
	 * 
	 * @param monitor
	 * @return
	 * @throws Exception
	 */
	public EPackage executeEA2Ecore(IProgressMonitor monitor) throws Exception {
		ea2ecore.setEnableLoggingMapper(false);
		ea2ecore.setEnableLoggingPostProcessing(true);

		ea2ecore.generateEcore(monitor);
		ecoreRootPkg = ea2ecore.getEcore();

		Diagnostic generatedEcoreDiagnostic = Diagnostician.INSTANCE.validate(ecoreRootPkg);
		Assert.isNotNull(generatedEcoreDiagnostic);
		if (generatedEcoreDiagnostic.getSeverity() == Diagnostic.ERROR) {
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					MessageDialog.openError(Display.getDefault().getActiveShell(), "Error", Messages.error_GeneratedEcoreContainsErrors); //$NON-NLS-1$
				}
			});
		}

		return ecoreRootPkg;
	}

	/**
	 * Save the generated Ecore
	 * 
	 * @param ecoreRootPkg
	 *            the root EPackage of the generated Ecore
	 */
	public void saveEcore(EPackage ecoreRootPkg) {

		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.createResource(ecoreFileURI);

		if (ecoreRootPkg != null) {
			resource.getContents().add(ecoreRootPkg);
			try {
				resource.save(null);
			} catch (IOException e) {
				PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
					@Override
					public void run() {
						MessageDialog.openError(Display.getDefault().getActiveShell(), "Error", "Save Ecore file error !!"); //$NON-NLS-1$ //$NON-NLS-2$
					}
				});
			}
		} else {
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					MessageDialog.openError(Display.getDefault().getActiveShell(), "Error", "Generated Ecore null !!"); //$NON-NLS-1$//$NON-NLS-2$
				}
			});
		}
	}

}
