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
package org.eclipse.eatop.workspace.ui.messages;

import org.eclipse.osgi.util.NLS;

public class WorkspaceUIMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.eatop.workspace.ui.messages.WorkspaceUIMessages"; //$NON-NLS-1$

	public static String label_newProjectPerspSwitchMessage;
	public static String label_newProjectPerspSwitchMessageWithDesc;
	public static String label_newProjectPerspSwitchTitle;
	public static String label_newProjectReferenceTitle;
	public static String label_newProjectReferenceDescription;
	public static String label_newProjectNameEmpty;
	public static String label_newProjectCreationPageName;
	public static String label_EAPackageCreation;
	public static String label_WithEAPackage;
	public static String label_eastadlFileWizard;

	public static String wizard_newEastADLProject_title;
	public static String wizard_newEastadlFile_title;

	public static String page_newEastADLProjectCreation_Name;
	public static String page_newEastADLProjectCreation_title;
	public static String page_neweEastADLProjectCreation_description;

	public static String text_defaultEaPackageName;
	public static String description_eastadlFileWizard;

	public static String defaultFileName;
	public static String defaultFileExtension;

	public static String label_metaModelRelease;

	static {
		NLS.initializeMessages(BUNDLE_NAME, WorkspaceUIMessages.class);
	}
}
