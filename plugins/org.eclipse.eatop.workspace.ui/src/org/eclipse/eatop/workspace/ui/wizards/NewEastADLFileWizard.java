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

package org.eclipse.eatop.workspace.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.jobs.CreateNewEastADLFileJob;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.eatop.workspace.ui.wizards.pages.NewEastADLFileCreationPage;
import org.eclipse.sphinx.emf.workspace.ui.wizards.AbstractNewModelFileWizard;
import org.eclipse.sphinx.emf.workspace.ui.wizards.pages.NewModelFileCreationPage;

/**
 * A wizard that creates a new eastADL file in the workspace. One pages is added for the new model file. A
 * {@linkplain CreateNewEastADLFileJob new eastADL file job} is created to create and save the model file in the
 * workspace.
 */
public class NewEastADLFileWizard extends AbstractNewModelFileWizard<EastADLReleaseDescriptor> {

	public NewEastADLFileWizard() {
		super(EastADLReleaseDescriptor.BASE_NAME);
	}

	/*
	 * @see org.eclipse.sphinx.emf.workspace.ui.wizards.AbstractNewModelFileWizard#createMainPage()
	 */
	@Override
	protected NewModelFileCreationPage<EastADLReleaseDescriptor> createMainPage() {
		return new NewEastADLFileCreationPage("NewEastADLFileCreationPage", selection); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.sphinx.emf.workspace.ui.wizards.BasicNewModelFileWizard#createCreateNewModelFileJob(org.eclipse.core
	 * .resources.IFile)
	 */
	@Override
	public Job createCreateNewModelFileJob(String jobName, IFile file) {
		return new CreateNewEastADLFileJob(jobName, file, IEastADLWorkspacePreferences.EAST_ADL_RELEASE.get(file.getProject()),
				((NewEastADLFileCreationPage) mainPage).getEAPackageName());
	}

}
