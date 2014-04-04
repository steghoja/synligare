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
package org.eclipse.eatop.workspace.natures;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eatop.workspace.internal.Activator;
import org.eclipse.sphinx.platform.util.ExtendedPlatform;

/**
 * {@link IProjectNature project nature} for {@link IProject}s containing EAST-ADL models.
 */
public class EastADLNature implements IProjectNature {

	/**
	 * The id of this {@link IProjectNature project nature}.
	 */
	public static final String ID = Activator.getPlugin().getSymbolicName() + ".eastadlnature"; //$NON-NLS-1$

	/**
	 * The project to which this {@link IProjectNature project nature} applies.
	 */
	private IProject project;

	/**
	 * Default constructor.
	 */
	public EastADLNature() {
		// Do nothing
	}

	/**
	 * Creates an instance of this {@link IProjectNature project nature} and associates it with the given
	 * {@link IProject project}.
	 * 
	 * @param project
	 *            The {@link IProject project} to be handled.
	 */
	public EastADLNature(IProject project) {
		setProject(project);
	}

	/**
	 * Adds a {@link EastADLNature EAST-ADL nature} to the given {@link IProject project}.
	 * 
	 * @param project
	 *            The {@link IProject project} to be handled.
	 * @param monitor
	 *            A {@link IProgressMonitor progress monitor}, or <code>null</code> if progress reporting is not
	 *            desired.
	 * @throws CoreException
	 *             If the {@link IProject project} does not exist or is not open.
	 */
	public static void addTo(IProject project, IProgressMonitor monitor) throws CoreException {
		ExtendedPlatform.addNature(project, ID, monitor);
	}

	/**
	 * Removes the {@link EastADLNature EAST-ADL nature} from the given {@link IProject project}.
	 * 
	 * @param project
	 *            The {@link IProject project} to be handled.
	 * @param monitor
	 *            A {@link IProgressMonitor progress monitor}, or <code>null</code> if progress reporting is not
	 *            desired.
	 * @throws CoreException
	 *             If the {@link IProject project} does not exist or is not open.
	 */
	public static void removeFrom(IProject project, IProgressMonitor monitor) throws CoreException {
		ExtendedPlatform.removeNature(project, ID, monitor);
	}

	/*
	 * @see org.eclipse.core.resources.IProjectNature#configure()
	 */
	@Override
	public void configure() throws CoreException {
		// Do nothing
	}

	/*
	 * @see org.eclipse.core.resources.IProjectNature#deconfigure()
	 */
	@Override
	public void deconfigure() throws CoreException {
		// Do nothing
	}

	/*
	 * @see org.eclipse.core.resources.IProjectNature#getProject()
	 */
	@Override
	public IProject getProject() {
		return project;
	}

	/*
	 * @see org.eclipse.core.resources.IProjectNature#setProject(org.eclipse.core .resources.IProject)
	 */
	@Override
	public void setProject(IProject project) {
		this.project = project;
	}
}
