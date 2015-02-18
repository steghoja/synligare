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
package org.eclipse.eatop.examples.explorer.internal.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.eatop.examples.explorer.internal.messages.Messages"; //$NON-NLS-1$
	public static String AppearanceAction_text_showName;
	public static String AppearanceAction_text_showNameAndType;
	public static String AppearanceMenutext;
	public static String AppearanceAction_text_categorize;

	public static String Path_ReferenceOverlayImage;
	public static String Path_InstanceRefOverlayImage;
	public static String Path_EACountImage;

	public static String Decorator_ID;

	public static String Annotation_Stereotype;
	public static String InstanceRef_Context;
	public static String InstanceRef_Target;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
