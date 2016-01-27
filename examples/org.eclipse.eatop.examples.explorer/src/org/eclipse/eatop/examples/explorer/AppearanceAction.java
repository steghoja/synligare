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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

public class AppearanceAction extends Action implements IAction {
	private final AppearanceExampleActionProvider exampleAppearanceActionProvider;
	private final boolean isShowNamesAndTypes;

	public AppearanceAction(String text, AppearanceExampleActionProvider exampleAppearanceActionProvider, boolean isShowNamesAndTypes) {
		super(text, AS_RADIO_BUTTON);

		this.exampleAppearanceActionProvider = exampleAppearanceActionProvider;
		this.isShowNamesAndTypes = isShowNamesAndTypes;
	}

	@Override
	public void run() {
		exampleAppearanceActionProvider.setShowTypeName(isShowNamesAndTypes);
		exampleAppearanceActionProvider.viewer.refresh();
	}
}
