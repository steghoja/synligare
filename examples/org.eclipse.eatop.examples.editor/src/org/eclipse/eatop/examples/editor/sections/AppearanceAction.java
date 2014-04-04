/**
 * <copyright>
 * 
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     itemis - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.examples.editor.sections;

import org.eclipse.eatop.examples.editor.EastADLFormEditor;
import org.eclipse.eatop.examples.editor.internal.messages.Messages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredViewer;

class AppearanceAction extends Action {

	private final StructuredViewer viewer;
	private final EastADLFormEditor eastadlFormEditor;

	public AppearanceAction(StructuredViewer viewer, EastADLFormEditor eastadlFormEditor) {
		super(Messages.AppearanceAction_text, AS_CHECK_BOX);

		this.viewer = viewer;
		this.eastadlFormEditor = eastadlFormEditor;

		setChecked(eastadlFormEditor.isShowTypeName());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		eastadlFormEditor.setShowTypeName(isChecked());

		viewer.refresh();
	}
}