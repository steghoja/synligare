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

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.examples.editor.internal.messages.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sphinx.emf.editors.forms.pages.AbstractFormPage;
import org.eclipse.sphinx.emf.editors.forms.sections.AbstractFieldFormSection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.TableWrapData;

public class EastADLDocumentationSection extends AbstractFieldFormSection {

	protected FormText eastADLDocumentationWidget;

	public EastADLDocumentationSection(AbstractFormPage formPage, Object sectionInput) {
		this(formPage, sectionInput, SWT.NONE);
	}

	public EastADLDocumentationSection(AbstractFormPage formPage, Object sectionInput, int style) {
		super(formPage, sectionInput, style);

		title = Messages.EastADLDocumentationSection_title;
		description = Messages.EastADLDocumentationSection_description;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.sphinx.emf.editors.forms.sections.AbstractFormSection#getDefaultSectionStyle()
	 */
	@Override
	protected int getDefaultSectionStyle() {
		return super.getDefaultSectionStyle() | ExpandableComposite.TWISTIE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.sphinx.emf.editors.forms.sections.AbstractFormSection#createSectionClientContent(org.eclipse.ui.forms
	 * .IManagedForm, org.eclipse.ui.forms.SectionPart, org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createSectionClientContent(IManagedForm managedForm, SectionPart sectionPart, Composite sectionClient) {
		Assert.isNotNull(managedForm);
		Assert.isNotNull(sectionPart);
		Assert.isNotNull(sectionClient);

		FormToolkit toolkit = managedForm.getToolkit();
		createEastADLDocumentationField(sectionInput, toolkit, sectionClient);
	}

	private void createEastADLDocumentationField(final Object sectionInput, FormToolkit toolkit, Composite sectionClient) {
		eastADLDocumentationWidget = toolkit.createFormText(sectionClient, true);
		TableWrapData data = new TableWrapData(TableWrapData.FILL);
		data.colspan = 1;
		data.grabHorizontal = true;
		eastADLDocumentationWidget.setLayoutData(data);
		eastADLDocumentationWidget.setText(getTypeDocumentation(sectionInput), false, true);
	}

	private void refreshEastADLDocumentationField() {
		if (isControlAccessible(eastADLDocumentationWidget)) {
			eastADLDocumentationWidget.setText(getTypeDocumentation(sectionInput), false, true);
		}
	}

	private String getTypeDocumentation(Object object) {
		return object instanceof EObject ? EcoreUtil.getDocumentation(((EObject) object).eClass()) : ""; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.sphinx.emf.editors.forms.sections.AbstractFormSection#refreshSection()
	 */
	@Override
	public void refreshSection() {
		// Refresh section client fields
		refreshEastADLDocumentationField();
	}
}
