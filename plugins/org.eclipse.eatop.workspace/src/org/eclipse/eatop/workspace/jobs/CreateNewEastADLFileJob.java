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
package org.eclipse.eatop.workspace.jobs;

import org.eclipse.core.resources.IFile;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.internal.messages.Messages;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sphinx.emf.metamodel.IMetaModelDescriptor;
import org.eclipse.sphinx.emf.workspace.jobs.CreateNewModelFileJob;

/**
 * A {@linkplain CreateNewEastADLFileJob create new eastADL job} capable of creating and saving an initial model to be
 * added to the new model compliant {@link IFile file} in the workspace. The initial model is based on the given
 * {@link IMetaModelDescriptor metamodel descriptor}. The initial model will create a root object using a
 * {@linkplain EClassifier root object classifier}, and a {@linkplain EPackage root object package} for the model to be
 * contained. A sub package will be created inside the file if the given eaPackageName is not null.
 * <p>
 * This job is set by default the priority to Job.BUILD and the rule to the workspace root to be run with the job
 * manager.
 */
public class CreateNewEastADLFileJob extends CreateNewModelFileJob {
	protected static final String EAST_ADL_TYPE_NAME = Messages.EastADLTypeName;
	protected static final String EAPACKAGE_TYPE_NAME = Messages.EAPackageTypeName;
	protected static final String SHORTNAME_FEATURE_NAME = Messages.ShortName_FeatureName;
	protected static final String TOPLEVELPACKAGES_FEATURE_NAME = Messages.TopLevelPackages_FeatureName;

	private String initialEAPackageName;

	/**
	 * Creates a new instance of CreateNewEastADLFileJob.
	 * 
	 * @param jobName
	 *            the name of the job
	 * @param eastADLFile
	 *            the EastADL file that will be created, should not be null
	 * @param eastADLRelease
	 *            the {@linkplain IMetaModelDescriptor descriptor} of metamodel the eastADL file should be based on
	 * @param initialEAPackageName
	 *            the name of the package that should be created inside the file. If null no package will be created
	 */
	public CreateNewEastADLFileJob(String jobName, IFile eastADLFile, EastADLReleaseDescriptor eastADLRelease, String initialEAPackageName) {
		super(jobName, eastADLFile, eastADLRelease, eastADLRelease.getRootEPackage(), eastADLRelease.getRootEPackage().getEClassifier(
				EAST_ADL_TYPE_NAME));
		this.initialEAPackageName = initialEAPackageName;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.sphinx.emf.workspace.jobs.CreateNewModelFileJob#createInitialModel()
	 */
	@Override
	public EObject createInitialModel() {
		// Create the initial model object
		EClass eastadlType = (EClass) metaModelDescriptor.getRootEPackage().getEClassifier(EAST_ADL_TYPE_NAME);
		EObject rootObject = metaModelDescriptor.getRootEFactory().create(eastadlType);

		// Add an initial root EAPackage to the newly created EASTADL model
		if (initialEAPackageName != null) {
			EClass eaPackageType = (EClass) metaModelDescriptor.getRootEPackage().getEClassifier(EAPACKAGE_TYPE_NAME);
			EObject eaPackage = metaModelDescriptor.getRootEFactory().create(eaPackageType);
			EStructuralFeature shortNameFeature = eaPackageType.getEStructuralFeature(SHORTNAME_FEATURE_NAME);
			eaPackage.eSet(shortNameFeature, initialEAPackageName);

			EStructuralFeature eastadlPackagesFeature = eastadlType.getEStructuralFeature(TOPLEVELPACKAGES_FEATURE_NAME);
			@SuppressWarnings("unchecked")
			EList<EObject> eastadlPackages = (EList<EObject>) rootObject.eGet(eastadlPackagesFeature);
			eastadlPackages.add(eaPackage);
		}
		return rootObject;

	}
}
