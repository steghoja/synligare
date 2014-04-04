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
package org.eclipse.eatop.workspace.ui.wizards.pages;

import org.eclipse.core.resources.IProject;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferenceConstants;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.eatop.workspace.ui.messages.WorkspaceUIMessages;
import org.eclipse.sphinx.emf.workspace.ui.wizards.groups.BasicMetaModelVersionGroup;
import org.eclipse.sphinx.emf.workspace.ui.wizards.pages.NewModelProjectCreationPage;
import org.eclipse.swt.widgets.Composite;

/**
 * A main page for a wizard that creates a {@linkplain IProject project} containing model files.
 */
public class NewEastADLProjectCreationPage extends NewModelProjectCreationPage<EastADLReleaseDescriptor> {

	/**
	 * Creates a new instance of NewEastADLProjectCreationPage.
	 * 
	 * @param pageName
	 *            the name of this page
	 */
	public NewEastADLProjectCreationPage(String pageName) {
		super(pageName, EastADLReleaseDescriptor.INSTANCE, IEastADLWorkspacePreferences.EAST_ADL_RELEASE,
				IEastADLWorkspacePreferenceConstants.EAST_ADL_RELEASE_PREFERENCE_PAGE_ID);
	}

	/*
	 * @see
	 * org.eclipse.sphinx.emf.workspace.ui.wizards.pages.NewModelProjectCreationPage#createMetaModelVersionGroup(org
	 * .eclipse.swt.widgets.Composite, org.eclipse.sphinx.platform.preferences.IProjectWorkspacePreference,
	 * org.eclipse.sphinx.emf.metamodel.IMetaModelDescriptor)
	 */
	@Override
	public void createMetaModelVersionGroup(Composite parent) {
		metaModelVersionGroup = new BasicMetaModelVersionGroup<EastADLReleaseDescriptor>("EastADLReleaseGroup", baseMetaModelDescriptor, //$NON-NLS-1$
				metaModelVersionPreference, metaModelVersionPreferencePageId);
		metaModelVersionGroup.setMetaModelVersionLabel(WorkspaceUIMessages.label_metaModelRelease);
		metaModelVersionGroup.createContent(parent, 3, false);
	}

}
