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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sphinx.platform.ui.util.ExtendedPlatformUI;

public class FindReferencesAction extends Action {

	protected TreeSelection activeSelection = null;
	protected EObject refObject = null;

	public FindReferencesAction(EObject refObject, IStructuredSelection selection) {
		super(extractText(refObject));
		this.refObject = refObject;
		activeSelection = (TreeSelection) selection;
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
		List<EObject> objectList = new ArrayList<EObject>();
		EStructuralFeature eStructuralFeature = refObject.eClass().getEStructuralFeature("shortName"); //$NON-NLS-1$
		String text = ""; //$NON-NLS-1$
		text += eStructuralFeature != null ? refObject.eGet(eStructuralFeature) : ""; //$NON-NLS-1$
		text += " ["; //$NON-NLS-1$

		EObject parent = refObject.eContainer();
		objectList.add(parent);

		while (parent.eContainer() != null) {
			parent = parent.eContainer();
			objectList.add(parent);
		}
		Collections.reverse(objectList);

		for (EObject obj : objectList) {
			eStructuralFeature = obj.eClass().getEStructuralFeature("shortName"); //$NON-NLS-1$
			text += eStructuralFeature != null ? obj.eGet(eStructuralFeature) : ""; //$NON-NLS-1$
			text += "/"; //$NON-NLS-1$
		}

		text += "]"; //$NON-NLS-1$

		return text;
	}

	private TreeSelection getRefSelection() {
		List<Object> objectList = new ArrayList<Object>();
		objectList.add(refObject);

		EObject parent = refObject.eContainer();
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
