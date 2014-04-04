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
package org.eclipse.eatop.metamodelgen.serialization.generators.util;

public interface ITaggedValueConstants {

	/* Annotations types */
	public static String ANNOTATION_STEREOTYPE = "Stereotype"; //$NON-NLS-1$
	public static String ANNOTATION_TAGGED_VALUES = "TaggedValues"; //$NON-NLS-1$

	/* Tagged values */
	public static String TAGGED_VALUE_XML_ATTRIBUTE = "xml.attribute"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_XSD_CUSTOM_TYPE = "xml.xsd.customType"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_XSD_PATTERN = "xml.xsd.pattern"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_XSD_TYPE = "xml.xsd.type"; //$NON-NLS-1$

	/* Stereotypes */
	public static String STEREOTYPE_INSTANCE_REF = "instanceRef"; //$NON-NLS-1$
	public static String STEREOTYPE_INSTANCE_REF_TARGET = "instanceRef.target"; //$NON-NLS-1$
	public static String STEREOTYPE_INSTANCE_REF_CONTEXT = "instanceRef.context"; //$NON-NLS-1$
	public static String STEREOTYPE_IS_OF_TYPE = "isOfType"; //$NON-NLS-1$
	public static String STEREOTYPE_ATPMIXED = "atpMixed"; //$NON-NLS-1$
	public static String STEREOTYPE_ATPMIXEDSTRING = "atpMixedString"; //$NON-NLS-1$
	public static String STEREOTYPE_PRIMITIVE = "primitive"; //$NON-NLS-1$
}
