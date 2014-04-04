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
package org.eclipse.eatop.workspace.jobs;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sphinx.emf.util.EcorePlatformUtil;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.platform.util.ExtendedPlatform;

/**
 * A job capable of creating an EAST-ADL compliant file.
 */
public class CreateEastADLFileJob extends WorkspaceJob {

	protected static final String EAST_ADL_TYPE_NAME = "EAXML";//"EAST-ADL"; //$NON-NLS-1$
	protected static final String EAPACKAGE_TYPE_NAME = "EAPackage"; //$NON-NLS-1$
	protected static final String SHORTNAME_FEATURE_NAME = "shortName"; //$NON-NLS-1$
	protected static final String TOPLEVELPACKAGES_FEATURE_NAME = "topLevelPackage"; //$NON-NLS-1$
	protected static final String TOPLEVELPACKAGES_TYPE_NAME = "EAPackage"; //$NON-NLS-1$
	protected static final String EAPACKAGES_FEATURE_NAME = "subPackage"; //$NON-NLS-1$

	private final IFile eastadlFile;
	private final String eaPackageName;
	private final EastADLReleaseDescriptor eastadlRelease;

	/**
	 * Creates an CreateEastADLFileJob
	 * 
	 * @param jobName
	 *            the name of the job
	 * @param eastadlFile
	 *            the EAST-ADL file that will be created, should not be null
	 * @param eastadlRelease
	 *            the identifier of the EAST-ADL file's content type. If null the default content type specified by the
	 *            release descriptor behind the EAST-ADL file's project will be used
	 * @param eaPackageName
	 *            the name of the package that should be created inside the file. If null no package will be created
	 */
	public CreateEastADLFileJob(String jobName, IFile eastadlFile, EastADLReleaseDescriptor eastadlRelease, String eaPackageName) {
		super(jobName);
		Assert.isNotNull(eastadlFile);

		if (eastadlRelease == null) {
			eastadlRelease = IEastADLWorkspacePreferences.EAST_ADL_RELEASE.get(eastadlFile.getProject());
		}

		this.eastadlFile = eastadlFile;
		this.eastadlRelease = eastadlRelease;
		this.eaPackageName = eaPackageName;

		setPriority(Job.BUILD);
		setRule(ExtendedPlatform.createSaveNewSchedulingRule(eastadlFile));
	}

	/**
	 * Creates an CreateEastADLFileJob. The default content type specified by the release descriptor behind the EAST-ADL
	 * file's project will be used.
	 * 
	 * @param jobName
	 *            the name of the job
	 * @param eastadlFile
	 *            the EAST-ADL file that will be created, should not be null
	 * @param eaPackageName
	 *            the name of the package that should be created inside the file. If null no package will be created
	 */
	public CreateEastADLFileJob(String jobName, IFile eastadlFile, String eaPackageName) {
		this(jobName, eastadlFile, (EastADLReleaseDescriptor) null, eaPackageName);
	}

	protected EastADLReleaseDescriptor getEastADLRelease() {
		return eastadlRelease;
	}

	/*
	 * @see org.eclipse.core.resources.WorkspaceJob#runInWorkspace(org.eclipse.core .runtime.IProgressMonitor)
	 */
	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		// Create the initial model object
		EClass eastadlType = (EClass) eastadlRelease.getRootEPackage().getEClassifier(EAST_ADL_TYPE_NAME);
		EObject rootObject = eastadlRelease.getRootEFactory().create(eastadlType);

		// Add a Root EAPackage
		if (eaPackageName != null) {
			EClass eaPackageType = (EClass) eastadlRelease.getRootEPackage().getEClassifier(EAPACKAGE_TYPE_NAME);
			EObject eaPackage = eastadlRelease.getRootEFactory().create(eaPackageType);
			EStructuralFeature shortNameFeature = eaPackageType.getEStructuralFeature(SHORTNAME_FEATURE_NAME);
			eaPackage.eSet(shortNameFeature, eaPackageName);

			EStructuralFeature topLevelPackagesFeature = eastadlType.getEStructuralFeature(TOPLEVELPACKAGES_FEATURE_NAME);
			EClass topLevelPackageType = (EClass) eastadlRelease.getRootEPackage().getEClassifier(TOPLEVELPACKAGES_TYPE_NAME);
			EObject topLevelPackage = eastadlRelease.getRootEFactory().create(topLevelPackageType);
			@SuppressWarnings("unchecked")
			EList<EObject> topLevelPackages = (EList<EObject>) rootObject.eGet(topLevelPackagesFeature);
			topLevelPackages.add(topLevelPackage);

			EStructuralFeature eastadlPackagesFeature = topLevelPackageType.getEStructuralFeature(EAPACKAGES_FEATURE_NAME);
			@SuppressWarnings("unchecked")
			EList<EObject> eastadlPackages = (EList<EObject>) topLevelPackage.eGet(eastadlPackagesFeature);
			eastadlPackages.add(eaPackage);
		}

		// FIXME Make sure that undo/redo works (currently it doesn't)
		TransactionalEditingDomain editingDomain = WorkspaceEditingDomainUtil.getEditingDomain(eastadlFile.getProject(), eastadlRelease);
		EcorePlatformUtil.saveNewModelResource(editingDomain, eastadlFile.getFullPath(), eastadlRelease.getDefaultContentTypeId(), rootObject, false,
				new NullProgressMonitor());

		return Status.OK_STATUS;
	}

}
