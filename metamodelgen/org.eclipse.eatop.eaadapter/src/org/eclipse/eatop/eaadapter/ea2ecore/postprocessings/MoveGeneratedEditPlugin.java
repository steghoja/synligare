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
package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.util.IConstants;

public class MoveGeneratedEditPlugin extends PostProcessingTemplate {

	private String modelProject = null;
	private IProgressMonitor monitor = null;

	public MoveGeneratedEditPlugin(IProgressMonitor monitor, String modelProject) {
		this.modelProject = modelProject;
		this.monitor = monitor;
	}

	private boolean delete(File folder) {
		boolean ok = true;
		File[] children = folder.listFiles();
		for (File file : children) {
			if (file.isDirectory()) {
				ok = ok && delete(file);
			}
			ok = ok && file.delete();
		}
		return ok;
	}

	@Override
	public void execute() {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IProject modelProject = root.getProject(this.modelProject);
		IProject editProject = root.getProject(this.modelProject + "." + IConstants.EDIT_FOLDER); //$NON-NLS-1$

		if (modelProject.exists() && editProject.exists()) {
			IPath parentPath = modelProject.getLocation().removeLastSegments(1);
			if (editProject.getLocation().removeLastSegments(1).equals(parentPath)) {
				return;
			}

			File dest = new File(parentPath.toString() + "/" + editProject.getName()); //$NON-NLS-1$
			try {
				if (!dest.exists() || delete(dest)) {
					IProjectDescription desc = editProject.getDescription();
					desc.setLocationURI(dest.toURI());
					editProject.setDescription(desc, monitor);
					editProject.move(desc, IResource.FORCE | IResource.SHALLOW, monitor);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}
}
