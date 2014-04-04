/**
 * <copyright>
 * 
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     itemis - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.examples.editor.internal.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.eatop.examples.editor.internal.messages.messages"; //$NON-NLS-1$

	public static String AppearanceAction_text;

	public static String EastADLContentsTreePage_title;

	public static String EastADLContentsTreeSection_description;

	public static String EastADLDocumentationSection_description;

	public static String EastADLDocumentationSection_title;

	public static String CommentsSection_descField_label;

	public static String CommentsSection_descField_toolTip;

	public static String CommentsSection_description;

	public static String CommentsSection_title;

	public static String GeneralInformationSection_description;

	public static String GeneralInformationSection_longNameField_label;

	public static String GeneralInformationSection_longNameField_toolTip;

	public static String GeneralInformationSection_shortNameField_label;

	public static String GeneralInformationSection_shortNameField_toolTip;

	public static String GeneralInformationSection_title;

	public static String OverviewPage_title;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
