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
package org.eclipse.eatop.workspace.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.internal.Activator;
import org.eclipse.eatop.workspace.natures.EastADLNature;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.sphinx.emf.internal.metamodel.InternalMetaModelDescriptorRegistry;
import org.eclipse.sphinx.emf.model.IModelDescriptor;
import org.eclipse.sphinx.emf.model.ModelDescriptorRegistry;
import org.eclipse.sphinx.platform.util.ExtendedPlatform;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;
import org.eclipse.sphinx.platform.util.StatusUtil;

/**
 * An utility class which provides helper methods for resolving EAST-ADL projects from the workspace.
 */
@SuppressWarnings("restriction")
public class EastADLPlatformUtil {

	/**
	 * Collects EAST-ADL projects in one group. This method recursively searches all referenced projects for the current
	 * visited project and also the projects that reference the current visited project if flag
	 * <code>includeReferencingProjects</code> is set to <code>true</code>.
	 * 
	 * @param project
	 *            The project being visited.
	 * @param includeReferencingProjects
	 *            Flag indicating if referencing projects must be considered or not.
	 * @param projectGroup
	 *            The project group to fill.
	 * @param visitedProjects
	 *            The already visited projects.
	 */
	private static void collectEastADLProjectsInGroup(IProject project, boolean includeReferencingProjects, Set<IProject> projectGroup,
			Set<IProject> visitedProjects) {
		if (project.isAccessible() && visitedProjects.add(project)) {
			if (hasEastADLNature(project)) {
				projectGroup.add(project);
			}
			for (IProject p : ExtendedPlatform.getReferencedProjectsSafely(project)) {
				if (p.isAccessible() && hasEastADLNature(p)) {
					collectEastADLProjectsInGroup(p, includeReferencingProjects, projectGroup, visitedProjects);
				}
			}
			if (includeReferencingProjects) {
				for (IProject p : ExtendedPlatform.getReferencingProjectsSafely(project)) {
					if (p.isAccessible() && hasEastADLNature(p)) {
						collectEastADLProjectsInGroup(p, includeReferencingProjects, projectGroup, visitedProjects);
					}
				}
			}
		}
	}

	/**
	 * Checks if the specified {@linkplain IProject project} has the {@linkplain EastADLNature#EAST-ADL_NATURE_ID
	 * EAST-ADL nature}; that given project is not supposed to be <code>null</code> and is supposed to be
	 * {@linkplain org.eclipse.core.resources.IResource#isAccessible() accessible}. Logs an error is case of
	 * {@linkplain CoreException exception}.
	 * 
	 * @param project
	 *            A project that may have the EAST-ADL nature; must not be <code>null</code> and must be
	 *            <em>accessible</em>.
	 * @return <ul>
	 *         <li><tt><b>true</b>&nbsp;&nbsp;</tt> if the specified project has the EAST-ADL nature;</li>
	 *         <li><tt><b>false</b>&nbsp;</tt> otherwise.</li>
	 *         </ul>
	 */
	public static boolean hasEastADLNature(IProject project) {
		Assert.isNotNull(project);
		Assert.isTrue(project.isAccessible());
		boolean hasEastADLNature = false;
		try {
			hasEastADLNature = project.hasNature(EastADLNature.ID);
		} catch (CoreException ex) {
			PlatformLogUtil.logAsError(Activator.getDefault(), ex);
		}
		return hasEastADLNature;
	}

	/**
	 * Computes a group of EAST-ADL projects (see {@link EastADLNature}) referencing each other. This method will search
	 * recursively all referenced projects of the given project and if wanted also the projects that reference the given
	 * project.
	 * 
	 * @param project
	 *            An EAST-ADL project whose scope will be computed; must not be <code>null</code>.
	 * @param includeReferencingProjects
	 *            If <code>true</code> also includes projects that reference the given project.
	 * @return A collection of EAST-ADL projects which reference one another.
	 */
	public static Collection<IProject> getProjectGroup(IProject project, boolean includeReferencingProjects) {
		Assert.isNotNull(project);
		// A set to which all EAST-ADL projects in scope will be added
		Set<IProject> projectGroup = new HashSet<IProject>();
		// Collect EAST-ADL projects in group safely
		collectEastADLProjectsInGroup(project, includeReferencingProjects, projectGroup, new HashSet<IProject>());
		return projectGroup;
	}

	public static Collection<IProject> getProjectGroup(IModelDescriptor modelDescriptor) {
		Assert.isNotNull(modelDescriptor);
		// A set to which all EAST-ADL projects in scope will be added
		Set<IProject> projectGroup = new HashSet<IProject>();
		// Collect EAST-ADL projects behind given model descriptor
		IResource root = modelDescriptor.getRoot();
		if (root != null) {
			IProject project = root.getProject();
			if (project.isAccessible() && hasEastADLNature(project)) {
				projectGroup.add(project);
			}
		}
		for (IResource resource : modelDescriptor.getReferencedRoots()) {
			IProject project = resource.getProject();
			if (project.isAccessible() && hasEastADLNature(project)) {
				projectGroup.add(project);
			}
		}
		return projectGroup;
	}

	/**
	 * Converts the given project to an EAST-ADL project and configures it.
	 * 
	 * @param project
	 *            the project to convert.
	 * @param eastadlRelease
	 *            the release of the EAST-ADL metamodel that will be used by this project.
	 * @param monitor
	 *            a progress monitor.
	 */
	public static IStatus convertToEastADLProject(IProject project, EastADLReleaseDescriptor eastadlRelease, IProgressMonitor monitor) {
		Assert.isNotNull(project);
		Assert.isNotNull(eastadlRelease);

		SubMonitor progress = SubMonitor.convert(monitor, 100);
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}

		// Sets the EAST-ADL nature to project
		if (!hasEastADLNature(project)) {
			progress.subTask("Set EAST-ADL nature to project"); //$NON-NLS-1$
			try {
				EastADLNature.addTo(project, progress);
			} catch (CoreException ex) {
				return StatusUtil.createErrorStatus(Activator.getPlugin(), ex);
			}
		}

		// Adds release descriptor and sets the RESOURCE_VERSION
		IEastADLWorkspacePreferences.RESOURCE_VERSION.set(project, eastadlRelease);
		EastADLReleaseDescriptor currentReleaseDescriptor = IEastADLWorkspacePreferences.EAST_ADL_RELEASE.get(project);
		if (currentReleaseDescriptor == null) {
			IEastADLWorkspacePreferences.EAST_ADL_RELEASE.setInProject(project, eastadlRelease);
		}

		// Updates cached descriptors by removed the cache in order to avoid obtaining null as model descriptor
		for (IFile file : ExtendedPlatform.getAllFiles(project, false)) {
			if (EastADLReleaseDescriptor.EAXML_DEFAULT_FILE_EXTENSION.equals(file.getFileExtension())) {
				InternalMetaModelDescriptorRegistry.INSTANCE.removeCachedDescriptor(file);
				InternalMetaModelDescriptorRegistry.INSTANCE.addCachedDescriptor(file, eastadlRelease);
			}
		}

		// Adds model descriptors in registry
		ModelDescriptorRegistry.INSTANCE.addModels(project);
		return Status.OK_STATUS;
	}
}
