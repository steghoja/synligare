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
package org.eclipse.eatop.workspace.ui.messages;

import org.eclipse.osgi.util.NLS;

public class ErrorUIMessages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.eatop.workspace.ui.messages.ErrorUIMessages"; //$NON-NLS-1$

	public static String error_newProjectOpeningWindow;
	public static String error_unableToFindPerspective;
	public static String error_newProjectExistsMessage;
	public static String error_newProjectCaseVariantExists;
	public static String error_fileNameExtension;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ErrorUIMessages.class);
	}
}
