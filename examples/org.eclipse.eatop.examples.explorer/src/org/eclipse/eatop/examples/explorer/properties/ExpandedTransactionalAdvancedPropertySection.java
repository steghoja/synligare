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

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.platform.ui.util.SelectionUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class ExpandedTransactionalAdvancedPropertySection extends AbstractPropertySection {

	protected TabbedPropertySheetPage tabbedPropertySheetPage;

	protected ResourceSetListenerImpl selectedObjectChangedListener = null;

	protected Object lastSelectedObject = null;

	protected ArrayList<PropertySheetPage> pages = new ArrayList<PropertySheetPage>();

	protected ExpandBar expandBar;

	/**
	 * Required to make sure that Properties view gets refreshed when attributes of currently selected model element are
	 * changed (because the {@link TabbedPropertySheetPage}'s
	 * {@link org.eclipse.jface.viewers.ISelectionChangedListener} does nothing in this case)
	 */
	protected ISelectionListener selectionListener = new ISelectionListener() {
		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			if (tabbedPropertySheetPage != null) {
				IStructuredSelection structuredSelection = SelectionUtil.getStructuredSelection(selection);
				if (!structuredSelection.isEmpty()) {
					Object selectedObject = structuredSelection.getFirstElement();
					if (selectedObject != lastSelectedObject) {
						// Remove existing selected object changed listener for previously selected object if any
						/*
						 * !! Important Note !! Don't use WorkspaceEditingDomainUtil for retrieving editing domain here
						 * because we only want to handle objects which are eligible to EMF Edit rather than just any
						 * object from which an editing domain can be retrieved.
						 */
						Object unwrapped = AdapterFactoryEditingDomain.unwrap(lastSelectedObject);
						if (unwrapped != null) {
							TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(unwrapped);
							if (editingDomain != null) {
								editingDomain.removeResourceSetListener(selectedObjectChangedListener);
							}
						}

						// Remember currently selected object as for future removals of selected object changed listener
						lastSelectedObject = selectedObject;

						// Install new selected object changed listener for currently selected object
						/*
						 * !! Important Note !! Don't use WorkspaceEditingDomainUtil for retrieving editing domain here
						 * because we only want to handle objects which are eligible to EMF Edit rather than just any
						 * object from which an editing domain can be retrieved.
						 */
						unwrapped = AdapterFactoryEditingDomain.unwrap(selectedObject);
						if (unwrapped != null) {
							TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(unwrapped);
							if (editingDomain != null) {
								selectedObjectChangedListener = createSelectedObjectChangedListener(selectedObject);
								editingDomain.addResourceSetListener(selectedObjectChangedListener);
							}
						}
					}
				}
			}
		}
	};

	protected ResourceSetListenerImpl createSelectedObjectChangedListener(Object selectedObject) {
		Assert.isNotNull(selectedObject);

		return new ResourceSetListenerImpl(NotificationFilter.createNotifierFilter(selectedObject)) {
			/*
			 * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#resourceSetChanged(ResourceSetChangeEvent)
			 */
			@Override
			public void resourceSetChanged(ResourceSetChangeEvent event) {
				if (tabbedPropertySheetPage != null) {
					IPageSite site = tabbedPropertySheetPage.getSite();
					if (site != null) {
						site.getShell().getDisplay().asyncExec(new Runnable() {
							@Override
							public void run() {
								// Refresh property section content
								if (pages.size() > 0) {
									refresh();
									// Refresh property sheet title through this indirect call to private
									// TabbedPropertySheetPage#refreshTitleBar() method
									tabbedPropertySheetPage.labelProviderChanged(new LabelProviderChangedEvent(new BaseLabelProvider()));
								}

							}
						});
					}
				}
			}
		};
	}

	/*
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.AdvancedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent, final TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		this.tabbedPropertySheetPage = tabbedPropertySheetPage;

		pages.clear();
		expandBar = new ExpandBar(parent, SWT.V_SCROLL);
		expandBar.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WHITE));

		// Install selection listener and invoke it once in order to make sure that we are in phase with current
		// selection
		ISelectionService selectionService = tabbedPropertySheetPage.getSite().getWorkbenchWindow().getSelectionService();
		selectionService.addSelectionListener(selectionListener);
		selectionListener.selectionChanged(getPart(), selectionService.getSelection());
	}

	@Override
	public void dispose() {
		// Unregister remaining selected object changed listener from all editing domains
		if (selectedObjectChangedListener != null) {
			for (TransactionalEditingDomain editingDomain : WorkspaceEditingDomainUtil.getAllEditingDomains()) {
				editingDomain.removeResourceSetListener(selectedObjectChangedListener);
			}
		}
		// Uninstall selection listener
		if (selectionListener != null) {
			ISelectionService selectionService = null;
			if (tabbedPropertySheetPage != null) {
				IPageSite site = tabbedPropertySheetPage.getSite();
				if (site != null) {
					selectionService = site.getWorkbenchWindow().getSelectionService();
				}
			}
			if (selectionService == null) {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				if (window == null) {
					IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
					if (windows.length > 0) {
						window = windows[0];
					}
				}
				if (window != null) {
					selectionService = window.getSelectionService();
				}
			}
			if (selectionService != null) {
				selectionService.removeSelectionListener(selectionListener);
			}
		}
		super.dispose();
	}

}
