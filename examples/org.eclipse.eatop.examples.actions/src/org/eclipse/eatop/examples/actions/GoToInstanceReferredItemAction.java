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

package org.eclipse.eatop.examples.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sphinx.platform.ui.util.ExtendedPlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

public class GoToInstanceReferredItemAction extends BaseSelectionListenerAction {

	protected TreeSelection activeSelection = null;
	protected EObject refObject = null;
	protected EObject refTarget = null;

	public GoToInstanceReferredItemAction(EObject refObject, IStructuredSelection selection) {
		super(extractText(refObject));
		this.refObject = refObject;
		activeSelection = (TreeSelection) selection;
		for (EObject ref : ModelSearcher.getInstanceRefChildren(refObject)) {
			EObject target = ModelSearcher.getInstanceRefTarget(ref);
			if (target != null) {
				refTarget = target;
			}
		}
	}

	@Override
	public void run() {
		if (activeSelection != null) {
			TreeSelection typeSelection = getRefSelection();
			ExtendedPlatformUI.getActivePage().getActivePart().getSite().getSelectionProvider().setSelection(typeSelection);
		}
		;
	}

	private static String extractText(EObject refObject) {
		String text = ""; //$NON-NLS-1$
		text = refObject.eClass().getName() + ": "; //$NON-NLS-1$
		EObject target = null;
		for (EObject ref : ModelSearcher.getInstanceRefChildren(refObject)) {
			target = ModelSearcher.getInstanceRefTarget(ref);
		}

		if (target != null) {
			text += target.eGet(target.eClass().getEStructuralFeature("shortName")); //$NON-NLS-1$
			text += " [" + target.eClass().getName() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return text;
	}

	private TreeSelection getRefSelection() {
		List<Object> objectList = new ArrayList<Object>();
		objectList.add(refTarget);

		EObject parent = refTarget.eContainer();
		objectList.add(parent);

		while (parent.eContainer() != null) {
			parent = parent.eContainer();
			objectList.add(parent);
		}

		addHead(objectList);
		Collections.reverse(objectList);

		TreePath treePath = new TreePath(objectList.toArray());
		TreeSelection selection = new TreeSelection(treePath);

		return selection;
	}

	private void addHead(List<Object> objectList) {
		TreePath treePath = activeSelection.getPaths()[0];

		objectList.add(treePath.getSegment(1));
		objectList.add(treePath.getSegment(0));

	}
}
