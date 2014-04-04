/**
 * <copyright>
 * 
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.examples.common.ui.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class EatopPerspectiveFactory implements IPerspectiveFactory {

	private IPageLayout layout;

	@Override
	public void createInitialLayout(IPageLayout layout) {
		this.layout = layout;

		createLayout(layout);
		addNewWizardShortcuts();
		addShowViewShortcuts();
		addPerspectiveShortcuts();
		addActionSets();
	}

	private void createLayout(IPageLayout layout) {

		// Editors are placed for free.
		String editorArea = layout.getEditorArea();

		int relativePos = IPageLayout.LEFT;

		IFolderLayout topLeft = layout.createFolder(IEatopPerspectiveConstants.TOP_LEFT, relativePos, 0.2f, editorArea);
		topLeft.addView(IEatopPerspectiveConstants.ID_EAST_ADL_EXPLORER);
		topLeft.addView(IPageLayout.ID_PROJECT_EXPLORER);

		relativePos = IPageLayout.BOTTOM;

		IFolderLayout bottomRight = layout.createFolder(IEatopPerspectiveConstants.BOTTOM_RIGHT, relativePos, 0.65f, editorArea);
		bottomRight.addView(IPageLayout.ID_PROP_SHEET);
		bottomRight.addView(IEatopPerspectiveConstants.ID_ERROR_LOG_VIEW);
		bottomRight.addPlaceholder(IEatopPerspectiveConstants.ID_PROBLEMS_VIEW);
		bottomRight.addPlaceholder(IEatopPerspectiveConstants.ID_CONSOLE_VIEW);
	}

	// New actions - EAST-ADL project creation wizard
	private void addNewWizardShortcuts() {
		layout.addNewWizardShortcut(IEatopPerspectiveConstants.ID_EAST_ADL_NEW_PROJECT);
		layout.addNewWizardShortcut(IEatopPerspectiveConstants.ID_EAST_ADL_NEW_FILE);
		layout.addNewWizardShortcut(IEatopPerspectiveConstants.ID_ECLIPSE_NEW_FOLDER);
		layout.addNewWizardShortcut(IEatopPerspectiveConstants.ID_ECLIPSE_NEW_FILE);
	}

	// Add "show views".
	private void addShowViewShortcuts() {
		layout.addShowViewShortcut(IEatopPerspectiveConstants.ID_EAST_ADL_EXPLORER);
		layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IEatopPerspectiveConstants.ID_ERROR_LOG_VIEW);
		layout.addShowViewShortcut(IEatopPerspectiveConstants.ID_PROBLEMS_VIEW);
		layout.addShowViewShortcut(IEatopPerspectiveConstants.ID_CONSOLE_VIEW);
	}

	private void addPerspectiveShortcuts() {
		layout.addPerspectiveShortcut(IEatopPerspectiveConstants.ID_RESOURCE_PERSPECTIVE);
	}

	private void addActionSets() {
		layout.addActionSet(IEatopPerspectiveConstants.ID_LAUNCH_ACTIONSET);
		layout.addActionSet(IEatopPerspectiveConstants.ID_TEAM_ACTIONSET);
	}

}
