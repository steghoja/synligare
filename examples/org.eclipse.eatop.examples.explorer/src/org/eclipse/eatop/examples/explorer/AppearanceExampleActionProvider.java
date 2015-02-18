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
package org.eclipse.eatop.examples.explorer;

import org.eclipse.eatop.examples.explorer.internal.Activator;
import org.eclipse.eatop.examples.explorer.internal.messages.Messages;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;

public class AppearanceExampleActionProvider extends CommonActionProvider {

	// Show names and types => true ; Show names => false
	static final String MODEL_ELEMENT_APPEARANCE_PROPERTY = "model_element_appearance_property"; //$NON-NLS-1$
	// Categorize elements by their metaclass type in the explorer
	static final String CATEGORIZE_ELEMENTS = "categorize_model_elements"; //$NON-NLS-1$

	private boolean contributedToViewMenu = false;

	private IAction showNamesOnlyAction = null;
	private IAction showNamesAndTypesAction = null;
	private IAction categorizeElementsAction = null;

	CommonViewer viewer;

	@Override
	public void init(ICommonActionExtensionSite aSite) {
		super.init(aSite);

		viewer = (CommonViewer) aSite.getStructuredViewer();

		createActions();

		boolean showTypeName = true;
		if (Activator.getPlugin().getDialogSettings().get(AppearanceExampleActionProvider.MODEL_ELEMENT_APPEARANCE_PROPERTY) != null) {
			showTypeName = Activator.getPlugin().getDialogSettings().getBoolean(AppearanceExampleActionProvider.MODEL_ELEMENT_APPEARANCE_PROPERTY);
		}
		setShowTypeName(showTypeName);
		boolean categorizeElements = true;
		if (Activator.getPlugin().getDialogSettings().get(AppearanceExampleActionProvider.CATEGORIZE_ELEMENTS) != null) {
			categorizeElements = Activator.getPlugin().getDialogSettings().getBoolean(AppearanceExampleActionProvider.CATEGORIZE_ELEMENTS);
		}
		setCategorizeElements(categorizeElements);
	}

	@Override
	public void fillActionBars(IActionBars actionBars) {
		if (!contributedToViewMenu) {
			try {
				super.fillActionBars(actionBars);
				contributeToViewMenu(actionBars.getMenuManager());
			} finally {
				contributedToViewMenu = true;
			}
		}
	}

	void setShowTypeName(boolean showTypeName) {
		showNamesOnlyAction.setChecked(!showTypeName);
		showNamesAndTypesAction.setChecked(showTypeName);

		Activator.getPlugin().getDialogSettings().put(MODEL_ELEMENT_APPEARANCE_PROPERTY, showTypeName);
	}

	private void createActions() {
		showNamesOnlyAction = new AppearanceAction(Messages.AppearanceAction_text_showName, this, false);
		showNamesAndTypesAction = new AppearanceAction(Messages.AppearanceAction_text_showNameAndType, this, true);
		categorizeElementsAction = new CategorizeElementsAction(Messages.AppearanceAction_text_categorize, this, false);
	}

	private void contributeToViewMenu(IMenuManager menuManager) {
		IMenuManager topLevelSubMenu = new MenuManager(Messages.AppearanceMenutext);
		topLevelSubMenu.add(new Separator());
		topLevelSubMenu.add(showNamesOnlyAction);
		topLevelSubMenu.add(showNamesAndTypesAction);
		topLevelSubMenu.add(categorizeElementsAction);

		menuManager.insertBefore(IWorkbenchActionConstants.MB_ADDITIONS, topLevelSubMenu);
	}

	public void setCategorizeElements(boolean categorize) {
		categorizeElementsAction.setChecked(categorize);
		Activator.getPlugin().getDialogSettings().put(CATEGORIZE_ELEMENTS, categorize);
	}

	public Viewer getViewer() {
		return viewer;
	}

}
