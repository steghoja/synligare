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
package org.eclipse.eatop.eaadapter.internal.messages;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.eatop.eaadapter.internal.messages.Messages"; //$NON-NLS-1$

	public static String error_Message;
	public static String success_Message;
	public static String error_GeneratedEcoreContainsErrors;
	public static String error_ResourceNotSpecified;
	public static String error_Resource;

	public static String exception_LoadModel;
	public static String qualified_Name_Message;

	public static String logger_AddOriginalQualifiedNameAnnotation;
	public static String logger_GenModelFileMustContainInstance;
	public static String logger_UsedGenPackage;
	public static String logger_ResourceSaveError;
	public static String logger_GenModelStatus;
	public static String logger_FileNotDelete;
	public static String logger_RemoveEmptyIdentifiablePackage;
	public static String logger_RemoveExampleModelPackage;
	public static String logger_NotRemoveGeneratedEcorePackage;
	public static String logger_StartNameChanger;
	public static String logger_SetDefaultValue;
	public static String logger_SetDdataType;
	public static String logger_NoDdataTypeMappingAvailable;
	public static String logger_SkipIrrelevantConnector;
	public static String logger_ConnectorTypeNotImplemented;
	public static String logger_Attribute;
	public static String logger_Type;
	public static String logger_Class;
	public static String logger_Package;

	public static String task_CreateReleaseArchives;
	public static String task_CreateXSDSchema;
	public static String task_GenerateEMFProject;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
