/**
 * <copyright>
 * 
 * Copyright (c) 10, 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */

package org.eclipse.eatop.eastadl21.validation.ocl.internal.l10n;

import org.eclipse.osgi.util.NLS;

/**
 * An accessor class for externalized strings.
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.eatop.eastadl21.validation.ocl.internal.l10n.Messages"; //$NON-NLS-1$

	public static String standard_description;
	public static String standard_message;
	public static String parsing_failure_config;
	public static String missing_meta_file;
	public static String ocl_registration_failure;
	public static String ocl_parsing_failure;
	public static String ocl_loading_failure;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
