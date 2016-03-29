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
 * 
 */
package org.eclipse.eatop.examples.graphicaleditor.wizards.pages;

import java.util.MissingResourceException;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sphinx.graphiti.workspace.ui.wizards.pages.AbstractDiagramContainerWizardPage;

public class EastadlEditorDiagramContainerWizardPage extends
		AbstractDiagramContainerWizardPage {

	public EastadlEditorDiagramContainerWizardPage(String pageName,
			IStructuredSelection selection, String extension) {
		super(pageName, selection, extension);
	}

	@Override
	protected String doGetTitle() throws MissingResourceException {
		return "east-adl-editor";
	}

	@Override
	protected String doGetDescription() throws MissingResourceException {
		return "Select folder";
	}
}
