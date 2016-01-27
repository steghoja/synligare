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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;

public class NonEastADLFoldersFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element != null) {
			if (element instanceof IFolder) {
				try {
					return containsEastADL((IFolder) element);
				} catch (CoreException e) {
				}
			}
		}
		return true;
	}

	private boolean containsEastADL(IFolder folder) throws CoreException {
		for (IResource res : folder.members()) {
			if (res instanceof IFolder) {
				if (containsEastADL((IFolder) res)) {
					return true;
				}
			} else if (res instanceof IFile) {
				return MetaModelDescriptorRegistry.INSTANCE.getDescriptor(res) instanceof EastADLReleaseDescriptor;
			}
		}
		return false;
	}
}
