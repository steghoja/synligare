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
import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryContentProvider;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sphinx.emf.explorer.actions.providers.BasicModelEditActionProvider;
import org.eclipse.sphinx.emf.ui.properties.BasicTransactionalAdvancedPropertySection;
import org.eclipse.sphinx.emf.ui.properties.FilteringPropertySource;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

public abstract class InstanceRefPropertySourceAbstractProvider implements IPropertySourceProvider {

	protected EObject instanceRef;
	protected IPropertySourceProvider lastPropertySourceProviderDelegate = null;

	public InstanceRefPropertySourceAbstractProvider(EObject instanceRef) {
		this.instanceRef = instanceRef;
	}

	@Override
	public IPropertySource getPropertySource(Object object) {

		if (object instanceof EObject) {
			EObject parent = (EObject) object;

			if (ModelSearcher.hasInstanceRefChild(parent)) {
				for (EObject instanceRefChild : ModelSearcher.getInstanceRefChildren(parent)) {
					if (instanceRefChild.equals(instanceRef)) {
						return getPropertySourceForInstanceRef();
					}
				}
			}
		}
		return null;
	}

	private IPropertySource getPropertySourceForInstanceRef() {
		// Let EMF.Edit try to find a property source adapter
		Object unwrapped = AdapterFactoryEditingDomain.unwrap(instanceRef);
		if (unwrapped != null) {
			// Try to retrieve model property source provider for given object and remember it so as to have it at hand
			// for subsequent objects for which no property source provider can be retrieved (e.g., FeatureMap.Entry
			// objects with primitive values)
			TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(unwrapped);
			if (editingDomain != null) {
				lastPropertySourceProviderDelegate = createModelPropertySourceProvider(editingDomain);
			}

			// Try retrieve property source adapter for given object using model property source provider
			if (lastPropertySourceProviderDelegate != null) {
				IPropertySource propertySource = lastPropertySourceProviderDelegate.getPropertySource(instanceRef);
				if (propertySource != null) {
					return new FilteringPropertySource(propertySource);
				}
			}
		}

		// Let Eclipse Platform try to find a property source adapter for objects that are not supported by EMF.Edit
		if (instanceRef instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) instanceRef;
			return (IPropertySource) adaptable.getAdapter(IPropertySource.class);
		}

		return null;
	}

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
										CellEditor editor = InstanceRefPropertySourceAbstractProvider.this.createPropertyEditor(composite, object,
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
		};
	}

	/**
	 * Returns the {@link AdapterFactory adapter factory} to be used by this {@link BasicModelEditActionProvider action
	 * provider} for creating {@link ItemProviderAdapter item provider}s which control the way how {@link EObject model
	 * element}s from given <code>editingDomain</code> are displayed and can be edited.
	 * <p>
	 * This implementation returns the {@link AdapterFactory adapter factory} which is embedded in the given
	 * <code>editingDomain</code> by default. Clients which want to use an alternative {@link AdapterFactory adapter
	 * factory} (e.g., an {@link AdapterFactory adapter factory} that creates {@link ItemProviderAdapter item provider}s
	 * which are specifically designed for the {@link IEditorPart editor} in which this
	 * {@link BasicModelEditActionProvider action provider} is used) may override {@link #getCustomAdapterFactory()} and
	 * return any {@link AdapterFactory adapter factory} of their choice. This custom {@link AdapterFactory adapter
	 * factory} will then be returned as result by this method.
	 * </p>
	 * 
	 * @param editingDomain
	 *            The {@link TransactionalEditingDomain editing domain} whose embedded {@link AdapterFactory adapter
	 *            factory} is to be returned as default. May be left <code>null</code> if
	 *            {@link #getCustomAdapterFactory()} has been overridden and returns a non-<code>null</code> result.
	 * @return The {@link AdapterFactory adapter factory} that will be used by this {@link BasicModelEditActionProvider
	 *         action provider}. <code>null</code> if no custom {@link AdapterFactory adapter factory} is provided
	 *         through {@link #getCustomAdapterFactory()} and no <code>editingDomain</code> has been specified.
	 * @see #getCustomAdapterFactory()
	 */
	protected AdapterFactory getAdapterFactory(TransactionalEditingDomain editingDomain) {
		AdapterFactory customAdapterFactory = getCustomAdapterFactory();
		if (customAdapterFactory != null) {
			return customAdapterFactory;
		} else if (editingDomain != null) {
			return ((AdapterFactoryEditingDomain) editingDomain).getAdapterFactory();
		}
		return null;
	}

	/**
	 * Returns a custom {@link AdapterFactory adapter factory} to be used by this
	 * {@link BasicTransactionalAdvancedPropertySection advanced property section} for creating
	 * {@link ItemProviderAdapter item provider}s which control the way how {@link EObject model element}s from given
	 * <code>editingDomain</code> are displayed and can be edited.
	 * <p>
	 * This implementation returns <code>null</code> as default. Clients which want to use their own
	 * {@link AdapterFactory adapter factory} (e.g., an {@link AdapterFactory adapter factory} that creates
	 * {@link ItemProviderAdapter item provider}s which are specifically designed for the {@link IEditorPart editor} in
	 * which this {@link BasicModelEditActionProvider action provider} is used) may override this method and return any
	 * {@link AdapterFactory adapter factory} of their choice. This custom {@link AdapterFactory adapter factory} will
	 * then be returned as result by {@link #getAdapterFactory(TransactionalEditingDomain)}.
	 * </p>
	 * 
	 * @return The custom {@link AdapterFactory adapter factory} that is to be used by this
	 *         {@link BasicModelEditActionProvider action provider}. <code>null</code> the default
	 *         {@link AdapterFactory adapter factory} returned by {@link #getAdapterFactory(TransactionalEditingDomain)}
	 *         should be used instead.
	 * @see #getAdapterFactory(TransactionalEditingDomain)
	 */
	protected AdapterFactory getCustomAdapterFactory() {
		return null;
	}

	/**
	 * Return a custom {@link CellEditor cell editor} to be used for editing the value of given property.
	 * 
	 * @param composite
	 *            The parent control of the {@link CellEditor cell editor} to be created.
	 * @param object
	 *            The owner of the {@link IItemPropertyDescriptor property} to be edited.
	 * @param itemPropertyDescriptor
	 *            The {@link IItemPropertyDescriptor item descriptor} of the property to be edited.
	 * @param propertyDescriptor
	 *            The {@link PropertyDescriptor descriptor} of the property to be edited.
	 * @return A newly created custom {@link CellEditor cell editor} to be used or <code>null</code> to indicate that
	 *         default {@link CellEditor cell editor} created by EMF.Edit should be used.
	 */
	protected CellEditor createPropertyEditor(Composite composite, Object object, final IItemPropertyDescriptor itemPropertyDescriptor,
			final PropertyDescriptor propertyDescriptor) {
		if (object instanceof EObject) {
			return createInstanceRefPropertyEditor(composite, object, itemPropertyDescriptor, propertyDescriptor);
		}

		return null;
	}

	protected abstract CellEditor createInstanceRefPropertyEditor(Composite composite, Object object,
			final IItemPropertyDescriptor itemPropertyDescriptor, final PropertyDescriptor propertyDescriptor);
}
