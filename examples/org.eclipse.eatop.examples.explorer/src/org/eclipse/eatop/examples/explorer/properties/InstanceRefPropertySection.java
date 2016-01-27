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
import java.util.Collections;

import org.eclipse.eatop.common.ui.util.InstanceRefAdder;
import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class InstanceRefPropertySection extends ExpandedTransactionalAdvancedPropertySection {

	@Override
	public void createControls(Composite parent, final TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		cleanExpandbar();

		Object selObject = ((TreeSelection) selection).getFirstElement();
		if (selObject instanceof EObject && ModelSearcher.canCreateInstanceRefChild((EObject) selObject)) {
			InstanceRefAdder.AddInstanceRef((EObject) selObject);
		}
		if (selObject instanceof EObject && ModelSearcher.hasInstanceRefChild((EObject) selObject)) {
			EObject target = (EObject) ((TreeSelection) selection).getFirstElement();
			ArrayList<EObject> children = ModelSearcher.getInstanceRefChildren(target);
			Collections.reverse(children);

			FormData data = new FormData();
			data.left = new FormAttachment(0, 0);
			data.right = new FormAttachment(100, 0);
			data.top = new FormAttachment(0, 0);
			data.bottom = new FormAttachment(100, 0);

			for (EObject instanceRef : children) {
				PropertySheetPage page = new PropertySheetPage();
				page.setPropertySourceProvider(new InstanceRefPropertySourceTargetProvider(instanceRef));
				Composite form = getWidgetFactory().createFlatFormComposite(expandBar);
				page.createControl(form);
				page.getControl().setLayoutData(data);

				ExpandItem item = new ExpandItem(expandBar, SWT.NONE, 0);
				item.setText(ModelSearcher.elementNameInParent(instanceRef) + " [" + ModelSearcher.getInstanceRefPathString(instanceRef) + "]"); //$NON-NLS-1$ //$NON-NLS-2$
				item.setHeight(page.getControl().computeSize(STANDARD_LABEL_WIDTH, 70).y);
				item.setControl(form);
				item.setExpanded(true);

				pages.add(page);
			}
		}

		for (PropertySheetPage page : pages) {
			page.selectionChanged(part, selection);
		}
	}

	private void cleanExpandbar() {
		if (pages != null) {
			for (PropertySheetPage page : pages) {
				page.dispose();
				page = null;
			}
			pages.clear();
		}
		for (ExpandItem item : expandBar.getItems()) {
			item.dispose();
			item = null;
		}
		for (Control item : expandBar.getChildren()) {
			item.dispose();
			item = null;
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();

		if (pages != null) {
			for (PropertySheetPage page : pages) {
				page.dispose();
				page = null;
			}
			pages.clear();
			pages = null;
		}
		if (expandBar != null) {
			expandBar.dispose();
			expandBar = null;
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	@Override
	public void refresh() {
		for (PropertySheetPage page : pages) {
			page.refresh();
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#shouldUseExtraSpace()
	 */
	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}
}
