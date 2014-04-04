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

import org.eclipse.eatop.examples.editor.internal.messages.Messages;
import org.eclipse.eatop.examples.editor.sections.CommentsSection;
import org.eclipse.eatop.examples.editor.sections.EastADLDocumentationSection;
import org.eclipse.eatop.examples.editor.sections.GeneralInformationSection;
import org.eclipse.sphinx.emf.editors.forms.layouts.LayoutFactory;
import org.eclipse.sphinx.emf.editors.forms.pages.AbstractFormPage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;

public class OverviewPage extends AbstractFormPage {

	protected GeneralInformationSection generalInformationSection;
	protected EastADLDocumentationSection eastADLDocumentationSection;
	protected CommentsSection commentsSection;

	public OverviewPage(FormEditor editor) {
		super(editor, Messages.OverviewPage_title);
	}

	public OverviewPage(FormEditor editor, String title) {
		super(editor, title);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.sphinx.emf.editors.forms.pages.AbstractFormPage#doRefreshPage()
	 */
	@Override
	protected void doRefreshPage() {
		if (generalInformationSection != null) {
			generalInformationSection.refreshSection();
		}
		if (eastADLDocumentationSection != null) {
			eastADLDocumentationSection.refreshSection();
		}
		if (commentsSection != null) {
			commentsSection.refreshSection();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.sphinx.emf.editors.forms.pages.AbstractFormPage#doCreateFormContent(org.eclipse.ui.forms.IManagedForm
	 * )
	 */
	@Override
	protected void doCreateFormContent(IManagedForm managedForm) {

		// Create double columned page layout
		Composite body = managedForm.getForm().getBody();
		body.setLayout(LayoutFactory.createFormBodyTableWrapLayout(true, 2));

		// Create left pane
		FormToolkit toolkit = managedForm.getToolkit();
		Composite leftPane = toolkit.createComposite(body);
		leftPane.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		leftPane.setLayout(LayoutFactory.createFormPaneTableWrapLayout(false, 1));

		// Create General Information section
		generalInformationSection = new GeneralInformationSection(this, pageInput);
		generalInformationSection.createContent(managedForm, leftPane);

		commentsSection = new CommentsSection(this, pageInput);
		commentsSection.createContent(managedForm, leftPane);

		// Create right pane
		Composite rightPane = toolkit.createComposite(body);
		rightPane.setLayoutData(new TableWrapData(TableWrapData.FILL_GRAB));
		rightPane.setLayout(LayoutFactory.createFormPaneTableWrapLayout(false, 1));

		// Create EASTADL Documentation section
		eastADLDocumentationSection = new EastADLDocumentationSection(this, pageInput);
		eastADLDocumentationSection.createContent(managedForm, rightPane);
	}
}
