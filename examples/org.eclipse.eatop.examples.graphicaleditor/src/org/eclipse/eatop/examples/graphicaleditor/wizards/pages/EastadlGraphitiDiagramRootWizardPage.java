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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sphinx.graphiti.workspace.ui.wizards.pages.AbstractDiagramRootWizardPage;

public class EastadlGraphitiDiagramRootWizardPage extends
		AbstractDiagramRootWizardPage {

	public EastadlGraphitiDiagramRootWizardPage(String pageName) {
		super(pageName, "org.eclipse.eatop.examples.graphicaleditor");
	}

	@Override
	protected String doGetTitle() throws MissingResourceException {
		return "east-adl-editor";
	}

	@Override
	protected String doGetDescription() throws MissingResourceException {
		return "Okay, go ahead";
	}

	@Override
	protected IStatus isValidRootDiagram(ISelection selectedRoot) {
		if (!(selectedRoot instanceof IStructuredSelection)
				|| ((IStructuredSelection) selectedRoot).isEmpty()) {
			return createErrorStatus("Select an EAXML element from your .eaxml file");
		}
		IStructuredSelection selection = (IStructuredSelection) selectedRoot;
		if (!(selection.getFirstElement() instanceof EAXML)) {
			return createErrorStatus("EAXML is expected as root element");
		}

		return Status.OK_STATUS;
	}
}