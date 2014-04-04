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
package org.eclipse.eatop.workspace.internal.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.eatop.workspace.internal.messages.Messages"; //$NON-NLS-1$

	public static String EastADLProjectJob_createEASTADLProject;
	public static String EastADLProjectJob_AddNatures;

	public static String EastADLTypeName;
	public static String EAPackageTypeName;
	public static String ShortName_FeatureName;
	public static String TopLevelPackages_FeatureName;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
