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

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.eatop.examples.explorer.ChildWrapper;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryContentProvider;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sphinx.emf.ui.properties.BasicTransactionalAdvancedPropertySection;
import org.eclipse.sphinx.emf.ui.properties.FilteringPropertySource;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

public class AdvancedPropertySection extends BasicTransactionalAdvancedPropertySection {

	public AdvancedPropertySection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IPropertySource getPropertySource(Object object) {
		// Let EMF.Edit try to find a property source adapter
		/*
		 * !! Important Note !! Don't use WorkspaceEditingDomainUtil for retrieving editing domain here because we only
		 * want to handle objects which are eligible to EMF.Edit rather than just any object from which an editing
		 * domain can be retrieved.
		 */
		if (object != null) {
			// Try to retrieve model property source provider for given object and remember it so as to have it at hand
			// for subsequent objects for which no property source provider can be retrieved (e.g., FeatureMap.Entry
			// objects with primitive values)
			TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object);
			if (editingDomain == null && object instanceof ChildWrapper) {
				editingDomain = TransactionUtil.getEditingDomain(((ChildWrapper) object).getObject());
			}
			if (editingDomain != null) {
				lastPropertySourceProviderDelegate = createModelPropertySourceProvider(editingDomain);
			}

			// Try retrieve property source adapter for given object using model property source provider
			if (lastPropertySourceProviderDelegate != null) {
				IPropertySource propertySource = lastPropertySourceProviderDelegate.getPropertySource(object);
				if (propertySource != null) {
					return new FilteringPropertySource(propertySource);
				}
			}
		}

		// Let Eclipse Platform try to find a property source adapter for objects that are not supported by EMF.Edit
		if (object instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) object;
			return (IPropertySource) adaptable.getAdapter(IPropertySource.class);
		} else if (object instanceof ChildWrapper) {
			if (((ChildWrapper) object).getObject() instanceof IAdaptable) {
				IAdaptable adaptable = (IAdaptable) ((ChildWrapper) object).getObject();
				return (IPropertySource) adaptable.getAdapter(IPropertySource.class);
			}
		}

		return null;
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
								return new PropertyDescriptor(object, itemPropertyDescriptor) {
									@Override
									public CellEditor createPropertyEditor(final Composite composite) {
										CellEditor editor = AdvancedPropertySection.this.createPropertyEditor(composite, object,
												itemPropertyDescriptor, this);
										if (editor != null) {
											return editor;
										}
										return super.createPropertyEditor(composite);
									}
								};
							}
						});
					}
				}));
			}

			@Override
			public IPropertySource getPropertySource(Object object) {
				Class<?> IItemPropertySourceClass = IItemPropertySource.class;
				if (object instanceof ChildWrapper) {
					object = ((ChildWrapper) object).getObject();
				}
				if (object instanceof IPropertySource) {
					return (IPropertySource) object;
				} else {
					IItemPropertySource itemPropertySource = (IItemPropertySource) (object instanceof EObject && ((EObject) object).eClass() == null ? null
							: adapterFactory.adapt(object, IItemPropertySourceClass));

					return itemPropertySource != null ? createPropertySource(object, itemPropertySource) : null;
				}
			}
		};
	}
}
