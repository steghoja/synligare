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
package org.eclipse.eatop.metamodelgen.internal.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.eatop.metamodelgen.internal.messages.Messages"; //$NON-NLS-1$

	public static String logger_MissingEclass;
	public static String logger_NullValue;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
