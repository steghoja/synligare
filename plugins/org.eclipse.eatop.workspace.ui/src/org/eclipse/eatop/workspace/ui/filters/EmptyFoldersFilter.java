/**
 * <copyright>
 * 
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.workspace.ui.filters;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class EmptyFoldersFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IFolder) {
			IFolder folder = (IFolder) element;
			try {
				return containsAtLeastOneFileRecursively(folder);
			} catch (CoreException e) {
			}
		}

		return true;
	}

	private boolean containsAtLeastOneFileRecursively(IContainer container) throws CoreException {
		for (IResource res : container.members()) {
			if (res instanceof IFile) {
				return true;
			}
		}

		for (IResource res : container.members()) {
			if (res instanceof IContainer) {
				if (containsAtLeastOneFileRecursively((IContainer) res)) {
					return true;
				}
			}
		}

		return false;
	}
}
