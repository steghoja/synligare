/**
 * <copyright>
 * 
 * Copyright (c) 2014 Arccore and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Arccore - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.examples.explorer.properties;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.examples.explorer.dialogs.InstanceRefEditorDialog;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

public class InstanceRefPropertySourceProvider extends InstanceRefPropertySourceAbstractProvider {

	public InstanceRefPropertySourceProvider(EObject instanceRef) {
		super(instanceRef);
	}

	@Override
	protected IPropertySourceProvider createModelPropertySourceProvider(TransactionalEditingDomain editingDomain) {
		Assert.isNotNull(editingDomain);

		AdapterFactory adapterFactory = getAdapterFactory(editingDomain);
		return new TransactionalAdapterFactoryContentProvider(editingDomain, adapterFactory) {
			/**
			 * Overridden to enable insertion of custom cell editor that will be used to edit the value of the given
			 * property.
			 */
			@Override
			protected IPropertySource createPropertySource(final Object object, final IItemPropertySource itemPropertySource) {
				return wrap(run(new RunnableWithResult.Impl<IPropertySource>() {
					@Override
					public void run() {
						setResult(new PropertySource(object, itemPropertySource) {
							@Override
							protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor) {
								return new InstanceRefPropertyDescriptor(object, itemPropertyDescriptor) {
									@Override
									public CellEditor createPropertyEditor(final Composite composite) {
										CellEditor editor = InstanceRefPropertySourceProvider.this.createPropertyEditor(composite, object,
												itemPropertyDescriptor, this);
										if (editor != null) {
											return editor;
										}
										return super.createPropertyEditor(composite);
									}

									@Override
									public boolean shouldShowProperty(IPropertyDescriptor propertyDescritpor) {
										return true;
									}
								};
							}
						});
					}
				}));
			}
		};
	}

	@Override
	protected CellEditor createInstanceRefPropertyEditor(Composite composite, Object object, IItemPropertyDescriptor itemPropertyDescriptor,
			final PropertyDescriptor propertyDescriptor) {
		final EObject eObject = (EObject) object;
		final Collection<?> choiceOfValues = itemPropertyDescriptor.getChoiceOfValues(eObject);
		Object feature = itemPropertyDescriptor.getFeature(eObject);
		if (!(feature instanceof EReference)) {
			return null;
		}
		final EReference reference = (EReference) feature;
		final ILabelProvider editLabelProvider = propertyDescriptor.getLabelProvider();
		return new ExtendedDialogCellEditor(composite, editLabelProvider) {

			@Override
			protected Object openDialogBox(Control cellEditorWindow) {
				InstanceRefEditorDialog dialog = new InstanceRefEditorDialog(cellEditorWindow.getShell(), editLabelProvider, eObject, reference,
						propertyDescriptor.getDisplayName(), new ArrayList<Object>(choiceOfValues), false);
				dialog.open();
				if (dialog.getAutoresolveSelected()) {
					InstanceRefNotifier notifier = new InstanceRefNotifier();
					notifier.setTreePath(dialog.getTreePath());
					notifier.observe(eObject);
				}
				return dialog.getResult();
			}
		};
	}

}
