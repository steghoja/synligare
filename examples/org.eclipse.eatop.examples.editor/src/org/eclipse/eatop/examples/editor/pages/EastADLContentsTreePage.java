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
package org.eclipse.eatop.examples.editor.pages;

import org.eclipse.eatop.examples.editor.EastADLFormEditor;
import org.eclipse.eatop.examples.editor.internal.messages.Messages;
import org.eclipse.eatop.examples.editor.sections.EastADLContentsTreeSection;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sphinx.emf.editors.forms.layouts.LayoutFactory;
import org.eclipse.sphinx.emf.editors.forms.pages.GenericContentsTreePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;

public class EastADLContentsTreePage extends GenericContentsTreePage {

	public EastADLContentsTreePage(EastADLFormEditor formEditor) {
		this(formEditor, Messages.EastADLContentsTreePage_title);
	}

	public EastADLContentsTreePage(EastADLFormEditor formEditor, String title) {
		super(formEditor, title);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.sphinx.emf.editors.forms.pages.GenericContentsTreePage#doCreateFormContent(org.eclipse.ui.forms.
	 * IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(final IManagedForm managedForm) {
		// Create single columned page layout
		Composite body = managedForm.getForm().getBody();
		body.setLayout(LayoutFactory.createFormBodyGridLayout(false, 1));

		// Create model contents tree section
		EastADLContentsTreeSection acts = new EastADLContentsTreeSection(this, pageInput);
		acts.createContent(managedForm, body);
		addSection(acts);
		contentsTreeSection = acts;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.sphinx.emf.editors.forms.pages.AbstractFormPage#getLabelProvider()
	 */
	@Override
	public ILabelProvider getLabelProvider() {
		return new AppearanceExampleColumnLabelProvider((EastADLFormEditor) getEditor());
	}
}
