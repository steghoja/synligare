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

package org.eclipse.eatop.examples.validation.ui.internal.l10n;

import org.eclipse.osgi.util.NLS;

/**
 * An accessor class for externalized strings.
 */
public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.eatop.examples.validation.ui.internal.l10n.Messages"; //$NON-NLS-1$

	public static String marker_exception;
	public static String message_exception;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
