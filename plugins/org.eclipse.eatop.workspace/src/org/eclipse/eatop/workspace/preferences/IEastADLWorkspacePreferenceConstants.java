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

import org.eclipse.eatop.workspace.internal.preferences.EastADLWorkspacePreferenceInitializer;

/**
 * Constants for the EAST-ADL preferences which can be set for the platform and their default values.
 */
public interface IEastADLWorkspacePreferenceConstants {

	/**
	 * The ID of the EAST-ADL preference page.
	 */
	String EAST_ADL_PREFERENCE_PAGE_ID = "org.eclipse.eatop.workspace.ui.preferencePages.eastadl"; //$NON-NLS-1$

	/**
	 * The ID of the EAST-ADL release page.
	 */
	public static final String EAST_ADL_RELEASE_PREFERENCE_PAGE_ID = "org.eclipse.eatop.workspace.ui.preferencePages.eastadl.release"; //$NON-NLS-1$

	/**
	 * The version defined at work-space level; shows up in EASTADLPreferencePage.
	 */
	String PREF_EAST_ADL_RELEASE = "east_adl_release"; //$NON-NLS-1$

	/**
	 * The default value for {@link #PREF_EAST_ADL_RELEASE}.
	 */
	String PREF_EAST_ADL_RELEASE_DEFAULT = EastADLWorkspacePreferenceInitializer.getEastADLReleaseDefault();

	/**
	 * The minor release/revision used when saving EAST-ADL XML files
	 */
	String PREF_EAST_ADL_RESOURCE_VERSION = "east_adl_resource_version"; //$NON-NLS-1$
}
