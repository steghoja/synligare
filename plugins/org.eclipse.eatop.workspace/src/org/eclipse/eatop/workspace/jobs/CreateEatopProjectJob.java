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
package org.eclipse.eatop.workspace.jobs;

import java.net.URI;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.eatop.common.internal.Activator;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.internal.messages.Messages;
import org.eclipse.eatop.workspace.natures.EastADLNature;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.sphinx.platform.util.StatusUtil;

/**
 * A job capable to create an Eatop project.<br/>
 * It will set by default the priority to Job.BUILD and the rule to the workspace root<br/>
 */
@SuppressWarnings("restriction")
public class CreateEatopProjectJob extends WorkspaceJob {

	private IProject project;

	private URI location;

	private IProject[] referencedProjects;

	private IAdaptable uiInfoAdaptable;

	private EastADLReleaseDescriptor eastadlRelease;

	/**
	 * Creates the job
	 * 
	 * @param name
	 *            the name of the job
	 */
	public CreateEatopProjectJob(String name) {
		super(name);
		setPriority(Job.BUILD);
		setRule(ResourcesPlugin.getWorkspace().getRoot());
	}

	/**
	 * Creates the job
	 * 
	 * @param name
	 *            the name of the job, should not be null
	 * @param project
	 *            the project, should not be null
	 * @param location
	 *            the location where the project will be created. If null the default location will be used.
	 * @param eastadlRelease
	 *            the release of the EAST-ADL MM that will be used by this project
	 */
	public CreateEatopProjectJob(String name, IProject project, URI location, EastADLReleaseDescriptor eastadlRelease) {
		this(name);
		this.project = project;
		this.location = location;
		this.eastadlRelease = eastadlRelease;
	}

	/**
	 * @return the project
	 */
	public IProject getProject() {
		return project;
	}

	/**
	 * Sets the project. The project does not have to exists.
	 * 
	 * @param project
	 *            the project to set, should not be null
	 */
	public void setProject(IProject project) {
		this.project = project;
	}

	/**
	 * @return the location
	 */
	public URI getLocation() {
		return location;
	}

	/**
	 * Sets the location where the project should be created. If null, the default location will be used
	 * 
	 * @param location
	 *            the location to set
	 */
	public void setLocation(URI location) {
		this.location = location;
	}

	/**
	 * Set an adaptable to be used by
	 * {@link IOperationHistory#execute(org.eclipse.core.commands.operations.IUndoableOperation, IProgressMonitor, IAdaptable)}
	 * <br/>
	 * At a minimum the adaptable should be able to adapt to org.eclipse.swt.widgets.Shell.<br/>
	 * Having a shell, such an adaptable can be obtained by <code>WorkspaceUndoUtil.getUIInfoAdapter(shell)</code><br/>
	 * If null, a default shell will be created to ask the user for confirmation.
	 * 
	 * @param uiInfoAdaptable
	 *            the uiInfoAdaptable to set
	 */
	public void setUiInfoAdaptable(IAdaptable uiInfoAdaptable) {
		this.uiInfoAdaptable = uiInfoAdaptable;
	}

	/**
	 * @return the uiInfoAdaptable
	 */
	public IAdaptable getUiInfoAdaptable() {
		return uiInfoAdaptable;
	}

	/**
	 * @return the referencedProjects
	 */
	public IProject[] getReferencedProjects() {
		return referencedProjects;
	}

	/**
	 * Sets the referenced project. Can be null or an empty array.
	 * 
	 * @param referencedProjects
	 *            the referencedProjects to set
	 */
	public void setReferencedProjects(IProject[] referencedProjects) {
		this.referencedProjects = referencedProjects;
	}

	/**
	 * @return the eastadlRelease
	 */
	public EastADLReleaseDescriptor getEastADLRelease() {
		return eastadlRelease;
	}

	/**
	 * @param eastadlRelease
	 *            the eastadlRelease to set
	 */
	public void setEastADLRelease(EastADLReleaseDescriptor eastadlRelease) {
		this.eastadlRelease = eastadlRelease;
	}

	/*
	 * @see org.eclipse.core.resources.WorkspaceJob#runInWorkspace(org.eclipse.core .runtime.IProgressMonitor)
	 */
	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		monitor.beginTask(getName(), 4);
		monitor.setTaskName(getName());
		IStatus result = Status.OK_STATUS;
		try {
			result = createNewProject(monitor);
			if (result.getSeverity() != IStatus.OK) {
				return result;
			}

			result = addNatures(monitor);

			if (result.getSeverity() != IStatus.OK) {
				return result;
			}

			if (eastadlRelease != null) {
				IEastADLWorkspacePreferences.EAST_ADL_RELEASE.setInProject(project, eastadlRelease);
			}

		} finally {
			monitor.done();
		}
		return result;
	}

	/**
	 * Sets the EAST-ADL nature to the project created by the job.
	 * 
	 * @param monitor
	 * @see {@link EastADLNature#addEastADLNature(IProject, IProgressMonitor)}
	 * @return OK_STATUS if everything is ok
	 */
	protected IStatus addNatures(IProgressMonitor monitor) {
		monitor.subTask(Messages.EastADLProjectJob_AddNatures);
		try {
			EastADLNature.addTo(project, new SubProgressMonitor(monitor, 1));
		} catch (CoreException ex) {
			return StatusUtil.createErrorStatus(Activator.getPlugin(), ex);
		}
		return Status.OK_STATUS;
	}

	/**
	 * Create the project on disk
	 * 
	 * @param monitor
	 * @return
	 */
	protected IStatus createNewProject(IProgressMonitor monitor) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		monitor.subTask(Messages.EastADLProjectJob_createEASTADLProject);
		final IProjectDescription description = workspace.newProjectDescription(project.getName());
		description.setLocationURI(location);

		// update the referenced project if provided
		if (referencedProjects != null && referencedProjects.length > 0) {
			description.setReferencedProjects(referencedProjects);
		}

		IStatus result;
		try {
			project.create(description, new SubProgressMonitor(monitor, 1));
			project.open(IResource.NONE, new SubProgressMonitor(monitor, 1));
			result = Status.OK_STATUS;
		} catch (CoreException ex) {
			result = StatusUtil.createErrorStatus(Activator.getPlugin(), ex);
		}
		return result;
	}
}
