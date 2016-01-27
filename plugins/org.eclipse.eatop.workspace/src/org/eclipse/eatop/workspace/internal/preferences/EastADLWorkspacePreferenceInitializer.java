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
package org.eclipse.eatop.workspace.internal.preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.eatop.common.internal.Activator;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.common.preferences.IEastADLPreferenceConstants;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferenceConstants;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;

@SuppressWarnings("restriction")
public class EastADLWorkspacePreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences defaultPreferences = getDefaultPreferences();
		if (defaultPreferences == null) {
			RuntimeException ex = new RuntimeException(
					"Failed to retrieve default preferences for " + EastADLReleaseDescriptor.INSTANCE.getName() + "."); //$NON-NLS-1$ //$NON-NLS-2$
			PlatformLogUtil.logAsWarning(Activator.getPlugin(), ex);
		}

		// Default for release group
		defaultPreferences.put(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE,
				IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RELEASE_DEFAULT);
		defaultPreferences.put(IEastADLWorkspacePreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION,
				IEastADLPreferenceConstants.PREF_EAST_ADL_RESOURCE_VERSION_DEFAULT);

	}

	/**
	 * Returns the {@link IEclipsePreferences default preferences} for EAST-ADL.
	 * 
	 * @return The {@link IEclipsePreferences default preferences} for EAST-ADL or <code>null</code> if no such could be
	 *         determined.
	 */
	private IEclipsePreferences getDefaultPreferences() {
		return DefaultScope.INSTANCE.getNode(Activator.getPlugin().getSymbolicName());
	}

	/**
	 * Returns the id of the most recent {@link EastADLReleaseDescriptor EAST-ADL release descriptor} as default.
	 * 
	 * @return The default value for {@link IEastADLPreferenceConstants#PREF_EAST_ADL_RELEASE} or an empty string if no
	 *         such could be determined.
	 */
	public static String getEastADLReleaseDefault() {
		List<EastADLReleaseDescriptor> descriptors = MetaModelDescriptorRegistry.INSTANCE.getDescriptors(EastADLReleaseDescriptor.INSTANCE);

		if (descriptors.size() == 0) {
			return ""; //$NON-NLS-1$
		}

		List<EastADLReleaseDescriptor> sortedDescriptors = new ArrayList<EastADLReleaseDescriptor>(descriptors);

		Collections.sort(sortedDescriptors);

		return sortedDescriptors.get(sortedDescriptors.size() - 1).getIdentifier();
	}

}
