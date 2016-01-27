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
package org.eclipse.eatop.workspace.preferences;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.common.preferences.IEastADLPreferenceConstants;
import org.eclipse.eatop.workspace.internal.Activator;
import org.eclipse.eatop.workspace.natures.EastADLNature;
import org.eclipse.sphinx.emf.metamodel.IMetaModelDescriptor;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;
import org.eclipse.sphinx.platform.preferences.AbstractProjectPreference;
import org.eclipse.sphinx.platform.preferences.AbstractProjectWorkspacePreference;
import org.eclipse.sphinx.platform.preferences.IProjectPreference;
import org.eclipse.sphinx.platform.preferences.IProjectWorkspacePreference;

/**
 * A set of preferences through which users can adjust the way how they work with EAST-ADL models in the workspace.
 */
public interface IEastADLWorkspacePreferences {

	/**
	 * The {@link East-ADLReleaseDescriptor EAST-ADL release} to be used. Can be defined globally at workspace level and
	 * may be individually redefined, i.e., overridden, by each project.
	 */
	IProjectWorkspacePreference<EastADLReleaseDescriptor> EAST_ADL_RELEASE = new AbstractProjectWorkspacePreference<EastADLReleaseDescriptor>(
			EastADLNature.ID, Activator.getPlugin().getSymbolicName(), IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE,
			IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE_DEFAULT) {
		/*
		 * @see org.eclipse.sphinx.platform.preferences. AbstractProjectWorkspacePreference
		 * #toObject(org.eclipse.core.resources .IProject, java.lang.String)
		 */
		@Override
		protected EastADLReleaseDescriptor toObject(final IProject project, String valueAsString) {
			// Assume that given value string is the id of an EAST-ADL release descriptor and try to retrieve the latter
			IMetaModelDescriptor mmDescriptor = MetaModelDescriptorRegistry.INSTANCE.getDescriptor(valueAsString);
			if (mmDescriptor instanceof EastADLReleaseDescriptor) {
				return (EastADLReleaseDescriptor) mmDescriptor;
			}

			// Go for the EAST-ADL release preference default
			return null;
		}

		/*
		 * @see org.eclipse.sphinx.platform.preferences. AbstractProjectWorkspacePreference#toString(java.lang.Object)
		 */
		@Override
		protected String toString(EastADLReleaseDescriptor valueAsObject) {
			return valueAsObject != null ? valueAsObject.getIdentifier() : null;
		}
	};

	/**
	 * The {@link EastADLReleaseDescriptor EAST-ADL resource version} to be used for serializing the resources of an
	 * EAST-ADL model. The {@link EastADLReleaseDescriptor EAST-ADL resource version} may be the same or older than the
	 * release of the metamodel implementation used for manipulating the EAST-ADL model in question. Can be defined
	 * individually by each project.
	 */
	IProjectPreference<EastADLReleaseDescriptor> RESOURCE_VERSION = new AbstractProjectPreference<EastADLReleaseDescriptor>(EastADLNature.ID,
			Activator.getPlugin().getSymbolicName(), IEastADLPreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION_DEFAULT,
			IEastADLPreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION_DEFAULT) {

		/*
		 * @see org.eclipse.sphinx.platform.preferences.AbstractProjectPreference
		 * #toObject(org.eclipse.core.resources.IProject , java.lang.String)
		 */
		@Override
		protected EastADLReleaseDescriptor toObject(IProject project, String valueAsString) {
			if (IEastADLPreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION_DEFAULT.equals(valueAsString)) {
				return null;
			}

			EastADLReleaseDescriptor eastadlRelease = EAST_ADL_RELEASE.get(project);
			if (eastadlRelease.getIdentifier().equals(valueAsString)) {
				return eastadlRelease;
			}
			Collection<IMetaModelDescriptor> resourceVersions = eastadlRelease.getCompatibleResourceVersionDescriptors();
			for (IMetaModelDescriptor resourceVersion : resourceVersions) {
				if (resourceVersion instanceof EastADLReleaseDescriptor && resourceVersion.getIdentifier().equals(valueAsString)) {
					return (EastADLReleaseDescriptor) resourceVersion;
				}
			}

			throw new RuntimeException("Resource version " //$NON-NLS-1$
					+ valueAsString + " is not compatible with EAST-ADL release " + eastadlRelease.getName() //$NON-NLS-1$
					+ " set in project " //$NON-NLS-1$
					+ project.getName() + "."); //$NON-NLS-1$
		}

		/*
		 * @see org.eclipse.sphinx.platform.preferences.AbstractProjectPreference
		 * #toString(org.eclipse.core.resources.IProject , java.lang.Object)
		 */
		@Override
		protected String toString(IProject project, EastADLReleaseDescriptor valueAsObject) {
			if (valueAsObject == null) {
				return IEastADLPreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION_DEFAULT;
			}

			EastADLReleaseDescriptor eastadlRelease = EAST_ADL_RELEASE.get(project);
			Collection<IMetaModelDescriptor> resourceVersions = eastadlRelease.getCompatibleResourceVersionDescriptors();
			if (valueAsObject != eastadlRelease && !resourceVersions.contains(valueAsObject)) {
				throw new RuntimeException("Resource version " //$NON-NLS-1$
						+ valueAsObject.getIdentifier() + " is not compatible with EAST-ADL release " + eastadlRelease.getName() //$NON-NLS-1$
						+ " set in project " //$NON-NLS-1$
						+ project.getName() + "."); //$NON-NLS-1$
			}

			return valueAsObject.getIdentifier();
		}
	};
}
