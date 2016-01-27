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
package org.eclipse.eatop.workspace.ui.internal.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.eatop.workspace.ui.internal.messages.Messages"; //$NON-NLS-1$

	public static String error_failedToSaveModelsInWorkbench;
	public static String error_selectedFolderIsNotInEastADLProject;
	public static String error_selectedProjectIsNoEastADLProject;

	public static String job_creatingEastADLProject;
	public static String job_creatingEastADLFile_name;

	public static String page_EastADLPreference_releaseGroupText;

	public static String EastADLProjectWizardFirstPage_location;
	public static String EastADLProjectWizardFirstPage_defaultRelease;
	public static Object EastADLProjectWizardFirstPage_configureSettings;
	public static String EastADLProjectWizardFirstPage_label_alternateRelease;

	public static String Warning_TitleText;
	public static String Description_TitleText;

	static {
		/* Load message values from bundle file. */
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
