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
package org.eclipse.eatop.app.semcon.allocationassistant.sections;

import org.eclipse.eatop.app.semcon.allocationassistant.editor.AllocationFormEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredViewer;

class AppearanceAction extends Action {

	private final StructuredViewer viewer;
	private final AllocationFormEditor allocationEditor;

	public AppearanceAction(StructuredViewer viewer, AllocationFormEditor allocationEditor) {
		super("Show types", AS_CHECK_BOX);

		this.viewer = viewer;
		this.allocationEditor = allocationEditor;

		setChecked(allocationEditor.isShowTypeName());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {
		allocationEditor.setShowTypeName(isChecked());

		viewer.refresh();
	}
}