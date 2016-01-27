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
package org.eclipse.eatop.examples.graphicaleditor.wizards;

import org.eclipse.eatop.examples.graphicaleditor.wizards.pages.EastadlEditorDiagramContainerWizardPage;
import org.eclipse.eatop.examples.graphicaleditor.wizards.pages.EastadlGraphitiDiagramRootWizardPage;
import org.eclipse.sphinx.graphiti.workspace.metamodel.GraphitiMMDescriptor;
import org.eclipse.sphinx.graphiti.workspace.ui.wizards.AbstractCreateDiagramWizard;
import org.eclipse.sphinx.graphiti.workspace.ui.wizards.pages.AbstractDiagramContainerWizardPage;
import org.eclipse.sphinx.graphiti.workspace.ui.wizards.pages.AbstractDiagramRootWizardPage;

public class EastadlGraphitiModelDiagramWizard extends
		AbstractCreateDiagramWizard {

	@Override
	protected AbstractDiagramContainerWizardPage createDiagramContainerWizardPage() {
		return new EastadlEditorDiagramContainerWizardPage(
				EastadlEditorDiagramContainerWizardPage.class.getSimpleName(),
				selection,
				GraphitiMMDescriptor.GRAPHITI_DIAGRAM_DEFAULT_FILE_EXTENSION);
	}

	@Override
	protected AbstractDiagramRootWizardPage createDiagramRootWizardPage() {
		return new EastadlGraphitiDiagramRootWizardPage(
				EastadlGraphitiDiagramRootWizardPage.class.getSimpleName());
	}
}