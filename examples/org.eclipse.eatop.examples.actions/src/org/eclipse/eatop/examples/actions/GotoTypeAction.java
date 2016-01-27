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

import org.eclipse.eatop.examples.actions.internal.messages.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sphinx.platform.ui.util.ExtendedPlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

public class GotoTypeAction extends BaseSelectionListenerAction {

	protected EObject typeObject = null;
	protected TreeSelection activeSelection = null;

	public GotoTypeAction() {
		super(Messages.act_gotoType_label);
	}

	@Override
	public boolean updateSelection(IStructuredSelection selection) {

		activeSelection = null;
		if (selection.getFirstElement() instanceof EObject) {
			activeSelection = (TreeSelection) selection;
			return hasType((EObject) selection.getFirstElement());
		}
		return false;
	}

	@Override
	public void run() {
		if (typeObject != null && activeSelection != null) {
			TreeSelection typeSelection = getTypeSelection();
			ExtendedPlatformUI.getActivePage().getActivePart().getSite().getSelectionProvider().setSelection(typeSelection);
		}
		;
	}

	private Boolean hasType(EObject parentElement) {
		EStructuralFeature eStructuralFeature = parentElement.eClass().getEStructuralFeature("type"); //$NON-NLS-1$

		if (eStructuralFeature != null) {
			Object eGet = parentElement.eGet(eStructuralFeature);

			if (eGet != null && eGet instanceof EObject) {
				typeObject = (EObject) eGet;
				return true;
			}
		}

		activeSelection = null;
		typeObject = null;
		return false;
	}

	private TreeSelection getTypeSelection() {
		List<Object> objectList = new ArrayList<Object>();
		objectList.add(typeObject);

		EObject parent = typeObject.eContainer();
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
