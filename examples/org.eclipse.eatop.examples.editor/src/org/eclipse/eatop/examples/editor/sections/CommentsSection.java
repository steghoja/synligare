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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.eatop.common.util.IdentifiableUtil;
import org.eclipse.eatop.examples.editor.internal.Activator;
import org.eclipse.eatop.examples.editor.internal.messages.Messages;
import org.eclipse.eatop.examples.editor.internal.widgets.FormWidgetFactory;
import org.eclipse.eatop.examples.editor.internal.widgets.IFormWidgetFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sphinx.emf.editors.forms.pages.AbstractFormPage;
import org.eclipse.sphinx.emf.editors.forms.sections.AbstractFieldFormSection;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;
import org.eclipse.sphinx.platform.ui.fields.IStringField;
import org.eclipse.sphinx.platform.ui.fields.StringField;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

public class CommentsSection extends AbstractFieldFormSection {

	private boolean editable;

	private boolean fRefreshingFields = false;
	private boolean fUpdatingModel = false;

	private IStringField fDescField;

	public CommentsSection(AbstractFormPage formPage, Object sectionInput) {
		this(formPage, sectionInput, SWT.NONE, true);
	}

	public CommentsSection(AbstractFormPage formPage, Object sectionInput, boolean editable) {
		this(formPage, sectionInput, SWT.NONE, editable);
	}

	public CommentsSection(AbstractFormPage formPage, Object sectionInput, int style, boolean editable) {
		super(formPage, sectionInput, style);

		this.editable = editable;

		title = Messages.CommentsSection_title;
		description = Messages.CommentsSection_description;
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

		IFormWidgetFactory widgetFactory = new FormWidgetFactory(managedForm.getToolkit());

		Layout layout = sectionClient.getLayout();
		int numColumns = ((TableWrapLayout) layout).numColumns;

		createDescField(sectionInput, widgetFactory, sectionClient, numColumns);
	}

	private void createDescField(final Object sectionInput, IFormWidgetFactory widgetFactory, Composite sectionClient, int numColumns) {
		fDescField = new StringField(widgetFactory, true);
		fDescField.setLabelText(Messages.CommentsSection_descField_label);
		fDescField.setToolTipText(Messages.CommentsSection_descField_toolTip);
		fDescField.setEnabled(true);

		String desc = IdentifiableUtil.getDesc(sectionInput);
		fDescField.setText(desc);

		fDescField.fillIntoGrid(sectionClient, numColumns);

		fDescField.getTextControl().addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent evt) {
			}

			@Override
			public void focusLost(FocusEvent evt) {
				Text text = (Text) evt.widget;
				if (!isRefreshingFields()) {
					final String newValue = text.getText();

					if (!isRefreshingFields()) {
						Object oldValue = IdentifiableUtil.getDesc(sectionInput);
						if (isPropertyValueChanged(newValue, oldValue)) {
							TransactionalEditingDomain editingDomain = WorkspaceEditingDomainUtil.getEditingDomain(sectionInput);
							if (editingDomain != null) {
								fUpdatingModel = true;
								try {
									WorkspaceTransactionUtil.executeInWriteTransaction(editingDomain, new Runnable() {

										@Override
										public void run() {
											IdentifiableUtil.setDesc(sectionInput, newValue);
										}
									}, "Set desc"); //$NON-NLS-1$
								} catch (OperationCanceledException ex) {
									PlatformLogUtil.logAsError(Activator.getPlugin(), ex);
								} catch (ExecutionException ex) {
									PlatformLogUtil.logAsError(Activator.getPlugin(), ex);
								}
								fUpdatingModel = false;
							}
						}
					}
				}
			}
		});
	}

	private void refreshDescField(final Object sectionInput) {
		if (isControlAccessible(fDescField.getTextControl())) {
			String desc = IdentifiableUtil.getDesc(sectionInput);
			fDescField.setText(desc);
		}
	}

	protected final boolean isRefreshingFields() {
		return fRefreshingFields;
	}

	protected final void setRefreshingFields(boolean refreshing) {
		fRefreshingFields = refreshing;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.sphinx.emf.editors.forms.sections.AbstractFormSection#refreshSection()
	 */
	@Override
	public void refreshSection() {
		setRefreshingFields(true);
		refreshDescField(sectionInput);
		setRefreshingFields(false);
	}

	protected final boolean isPropertyValueChanged(Object newValue, Object oldValue) {
		return oldValue == null && newValue != null || oldValue != null && !oldValue.equals(newValue);
	}
}
