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

import java.net.URI;

import org.eclipse.core.resources.IProject;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferenceConstants;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.eatop.workspace.ui.internal.Activator;
import org.eclipse.eatop.workspace.ui.messages.WorkspaceUIMessages;
import org.eclipse.eatop.workspace.ui.wizards.pages.NewEastADLProjectCreationPage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sphinx.emf.workspace.jobs.CreateNewModelProjectJob;
import org.eclipse.sphinx.emf.workspace.ui.wizards.AbstractNewModelProjectWizard;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;

/**
 * A wizard that creates a new project resource in the workspace. Pages are added. A
 * {@linkplain CreateNewModelProjectJob new model project job} is created to create the model project in the workspace.
 */
public class NewEastADLProjectWizard extends AbstractNewModelProjectWizard<EastADLReleaseDescriptor> {

	/**
	 * The project-relative path of the wizard banner file.
	 */
	private static final String WIZBAN_ICON_FILE = "full/wizban/neweaprj_wiz.png"; //$NON-NLS-1$

	public NewEastADLProjectWizard() {
		super(false, EastADLReleaseDescriptor.INSTANCE, IEastADLWorkspacePreferences.EAST_ADL_RELEASE,
				IEastADLWorkspacePreferenceConstants.EAST_ADL_RELEASE_PREFERENCE_PAGE_ID);
	}

	/*
	 * @see org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard#initializeDefaultPageImageDescriptor()
	 */
	@Override
	protected void initializeDefaultPageImageDescriptor() {
		ImageDescriptor descriptor = Activator.getPlugin().getImageDescriptor(WIZBAN_ICON_FILE);
		setDefaultPageImageDescriptor(descriptor);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.sphinx.emf.workspace.ui.wizards.BasicNewModelProjectWizard#createMainPage(boolean)
	 */
	@Override
	protected WizardNewProjectCreationPage createMainPage() {
		return new NewEastADLProjectCreationPage(WorkspaceUIMessages.page_newEastADLProjectCreation_Name);
	}

	/*
	 * @see
	 * org.eclipse.sphinx.emf.workspace.ui.wizards.BasicNewModelProjectWizard#createCreateNewProjectJob(java.lang.String
	 * , org.eclipse.core.resources.IProject, java.net.URI)
	 */
	@Override
	protected CreateNewModelProjectJob<EastADLReleaseDescriptor> createCreateNewModelProjectJob(String jobName, IProject project, URI location) {
		CreateNewModelProjectJob<EastADLReleaseDescriptor> job = new CreateNewModelProjectJob<EastADLReleaseDescriptor>(jobName, project, location,
				((NewEastADLProjectCreationPage) mainPage).getMetaModelVersionDescriptor(), IEastADLWorkspacePreferences.EAST_ADL_RELEASE);
		return job;
	}
}
