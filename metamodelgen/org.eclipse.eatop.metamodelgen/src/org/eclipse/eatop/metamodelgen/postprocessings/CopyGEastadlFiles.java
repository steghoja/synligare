/**
 * <copyright>
 *  
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors: 
 *     itemis - Initial API and implementation
 *  
 * </copyright>
 * 
 */
package org.eclipse.eatop.metamodelgen.postprocessings;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;

public class CopyGEastadlFiles extends PostProcessingTemplate {

	private File ecoreModelFile = null;
	private String geastadlRootDir = null;

	public CopyGEastadlFiles(File ecoreModelFile, String geastadlRootDir) {
		this.ecoreModelFile = ecoreModelFile;
		this.geastadlRootDir = geastadlRootDir;
	}

	@Override
	public void execute() throws CoreException {
		IPath genModelPath = new Path(ecoreModelFile.getAbsolutePath());
		IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(genModelPath);
		IContainer destFolder = iFile.getParent();

		// copy ecore
		IFolder geastadlRootFolder = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(geastadlRootDir));
		IFile geastadlEcoreFile = geastadlRootFolder.getFile(IEASTADLConstants.GEASTADL_ECORE);
		geastadlEcoreFile.copy(destFolder.getFullPath().append(IEASTADLConstants.GEASTADL_ECORE), true, null);

		// copy genmodel
		IFile geastadlGenmodelFile = geastadlRootFolder.getFile(IEASTADLConstants.GEASTADL_GENMODEL);
		geastadlGenmodelFile.copy(destFolder.getFullPath().append(IEASTADLConstants.GEASTADL_GENMODEL), true, null);
	}
}
