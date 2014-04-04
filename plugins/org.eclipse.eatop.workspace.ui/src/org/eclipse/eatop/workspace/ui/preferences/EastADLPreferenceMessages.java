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
package org.eclipse.eatop.workspace.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class EastADLPreferenceMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.eatop.workspace.ui.preferences.EastADLPreferenceMessages"; //$NON-NLS-1$

	public static String EastADLPreferencePage_configureProjectSpecificSettings;
	public static String EastADLPreferencePage_configureWorkspaceSettings;
	public static String EastADLPreferencePage_enableProjectSpecificSettings;
	public static String EastADLPreferencePage_description;

	public static String ReleasePreferencePage_detectedRelease;

	public static String ReleasePreferencePage_releaseGroupText;
	public static String ReleasePreferencePage_releaseFieldLabel;
	public static String ReleasePreferencePage_resourceVersionLabel;

	public static String ReleasePreferencePage_sameAsInOriginalButtonLabel;
	public static String ReleasePreferencePage_otherResourceVersionButtonLabel;

	public static String EastADLPreferencePage_loadingGroupText;
	public static String EastADLPreferencePage_schemaValidationFieldLabel;
	public static String EastADLPreferencePage_schemaValidationSeverityLevel;
	public static String EastADLPreferencePage_ErrorLabel;
	public static String EastADLPreferencePage_WarningLabel;
	public static String EastADLPreferencePage_InfoLabel;

	public static String EastADLPreferencePage_problemIndicationFieldLabel;
	public static String EastADLPreferencePage_maxProblemEditorlabel;

	public static String EastADLPreferencePage_uuidHandlingGroupText;
	public static String EastADLPreferencePage_syncUuidFieldLabel;
	public static String EastADLPreferencePage_useBinaryResources;

	static {
		/* Load message values from bundle file. */
		NLS.initializeMessages(BUNDLE_NAME, EastADLPreferenceMessages.class);
	}
}
