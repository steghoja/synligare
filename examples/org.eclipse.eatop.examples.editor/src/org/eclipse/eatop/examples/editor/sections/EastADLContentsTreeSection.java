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
import org.eclipse.eatop.examples.editor.pages.EastADLContentsTreePage;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.sphinx.emf.editors.forms.sections.GenericContentsTreeSection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;

public class EastADLContentsTreeSection extends GenericContentsTreeSection {

	public EastADLContentsTreeSection(EastADLContentsTreePage formPage, Object sectionInput) {
		this(formPage, sectionInput, SWT.NONE);
	}

	public EastADLContentsTreeSection(EastADLContentsTreePage formPage, Object sectionInput, int style) {
		super(formPage, sectionInput, style);

		description = Messages.EastADLContentsTreeSection_description;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.sphinx.emf.editors.forms.sections.GenericContentsTreeSection#createSectionClientContent(org.eclipse
	 * .ui.forms.IManagedForm, org.eclipse.ui.forms.SectionPart, org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createSectionClientContent(final IManagedForm managedForm, final SectionPart sectionPart, Composite sectionClient) {
		super.createSectionClientContent(managedForm, sectionPart, sectionClient);
		ColumnViewerToolTipSupport.enableFor((ColumnViewer) viewer);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.sphinx.emf.editors.forms.sections.AbstractFormSection#fillSectionToolBarActions(org.eclipse.jface
	 * .action.ToolBarManager)
	 */
	@Override
	protected void fillSectionToolBarActions(ToolBarManager toolBarManager) {
		super.fillSectionToolBarActions(toolBarManager);
		toolBarManager.add(new AppearanceAction(viewer, (EastADLFormEditor) formPage.getEditor()));
	}
}
