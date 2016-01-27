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
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;

public class NonEastADLFilesFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element != null) {
			if (element instanceof IFile) {
				return MetaModelDescriptorRegistry.INSTANCE.getDescriptor(element) instanceof EastADLReleaseDescriptor;
			}
		}
		return true;
	}

}
