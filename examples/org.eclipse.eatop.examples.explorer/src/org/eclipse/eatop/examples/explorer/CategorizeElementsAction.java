/**
 * <copyright>
 *
 * Copyright (c) See4sys, BMW Car IT and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Artop Software License Based on AUTOSAR
 * Released Material (ASLR) which accompanies this distribution, and is
 * available at http://www.artop.org/aslr.html
 *
 * Contributors:
 *     See4sys - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.eatop.examples.explorer;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

public class CategorizeElementsAction extends Action {

	private final AppearanceExampleActionProvider actionProvider;

	public CategorizeElementsAction(String text, AppearanceExampleActionProvider actionProvider, boolean categorize) {
		super(text, IAction.AS_CHECK_BOX);

		this.actionProvider = actionProvider;
		setChecked(categorize);
	}

	@Override
	public void run() {
		actionProvider.setCategorizeElements(isChecked());
		actionProvider.getViewer().refresh();
	}
}