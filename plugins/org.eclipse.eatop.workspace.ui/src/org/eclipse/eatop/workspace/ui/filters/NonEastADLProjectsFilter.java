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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.eatop.workspace.natures.EastADLNature;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class NonEastADLProjectsFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element != null) {
			if (element instanceof IProject) {
				IProject prj = (IProject) element;
				if (prj.isAccessible()) {
					try {
						return prj.hasNature(EastADLNature.ID);
					} catch (CoreException e) {
					}
				}
			}
		}

		return true;
	}

}
