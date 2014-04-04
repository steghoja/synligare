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
package org.eclipse.eatop.metamodelgen.util;

public interface IEASTADLConstants {

	/* GEASTADL */
	public static String GEASTADL_ROOT_PKG_NAME = "geastadl"; //$NON-NLS-1$
	public static String GEASTADL_ECORE = "geastadl.ecore"; //$NON-NLS-1$
	public static String GEASTADL_GENMODEL = "geastadl.genmodel"; //$NON-NLS-1$
	public static String GEASTADL_PLUGIN_ID = "org.eclipse.eatop.geastadl"; //$NON-NLS-1$

	/* GEastadl Super Classes */
	public static String MERGE_CONFIG = "merge.config"; //$NON-NLS-1$
	public static String MODE = "mode"; //$NON-NLS-1$
	public static String REPLACE = "replace"; //$NON-NLS-1$
	public static String CLASS_MAPPING = "class.mapping"; //$NON-NLS-1$
	public static String EA_MODEL_LAST_SGMT_PREFIX = "/eastadl"; //$NON-NLS-1$
	public static String NONE = "none"; //$NON-NLS-1$
	public static String TGT_ECLASS_NAME_SEPARATOR = ","; //$NON-NLS-1$

	/* EASTADL */
	public static String EASTADL = "eastadl"; //$NON-NLS-1$
	public static String EASTADL21_ROOT_PACKAGE = EASTADL + "21"; //$NON-NLS-1$
	public static String EASTADL21_VERSION = "2.1.12"; //$NON-NLS-1$
	public static String DEFAULT_NSURI = "http://east-adl.info"; //$NON-NLS-1$
	public static String DEFAULT_NSPREFIX = "EA"; //$NON-NLS-1$
	public static String EASTADL_SOURCE_PATH = "org.eclipse.eatop.metamodelgen.source"; //$NON-NLS-1$
	public static String EASTADL_GENMODEL_PROPERTIES = "/gmodel/genmodel_eastadl.properties"; //$NON-NLS-1$
	public static String EASTADL_CDO_GENMODEL_PROPERTIES = "/gmodel/genmodel_cdo_eastadl.properties"; //$NON-NLS-1$
	public static String SOURCE_REVISION_NAME_PREFIX = "eastadl-"; //$NON-NLS-1$

	/* EClassifier */
	public static String EAPACKAGEABLE_ELEMENT = "EAPackageableElement"; //$NON-NLS-1$
	public static String EAPACAKGE = "EAPackage"; //$NON-NLS-1$
	public static String ELEMENT = "element"; //$NON-NLS-1$
	public static String EAPACKAGE_ELEMENT = "EAPackage_element"; //$NON-NLS-1$
	public static String IDENTIFIABLE = "Identifiable"; //$NON-NLS-1$

	/* metamodel type */
	public static String METAMODEL_TYPE_EASTADL = "EAST-ADL"; //$NON-NLS-1$

	/* Annotations types */
	public static String ANNOTATION_STEREOTYPE = "Stereotype"; //$NON-NLS-1$
	public static String ANNOTATION_TAGGED_VALUES = "TaggedValues"; //$NON-NLS-1$

	/* Stereotypes */
	public static String STEREOTYPE_INSTANCE_REF = "instanceRef"; //$NON-NLS-1$
	public static String STEREOTYPE_INSTANCE_REF_TARGET = "instanceRef.target"; //$NON-NLS-1$
	public static String STEREOTYPE_INSTANCE_REF_CONTEXT = "instanceRef.context"; //$NON-NLS-1$
	public static String STEREOTYPE_IS_OF_TYPE = "isOfType"; //$NON-NLS-1$
	public static String STEREOTYPE_ATPMIXED = "atpMixed"; //$NON-NLS-1$
	public static String STEREOTYPE_ATPMIXEDSTRING = "atpMixedString"; //$NON-NLS-1$

	/* Suffix */
	public static String SUFFIX_NAME = "-IREF"; //$NON-NLS-1$
	public static String SUFFIX_NAME_PLURAL = "-IREFS"; //$NON-NLS-1$
	public static String SUFFIX_SINGULAR_TYPE_REF = "-TREF"; //$NON-NLS-1$
	public static String SUFFIX_PLURAL_TYPE_REF = "-TREFS"; //$NON-NLS-1$
	public static String SUFFIX_SINGULAR_REF = "-REF"; //$NON-NLS-1$
	public static String SUFFIX_PLURAL_REF = "-REFS"; //$NON-NLS-1$

	/* Tagged Value keys */
	public static String TAGGED_VALUE_XML_NAME = "xml.name"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_NAME_PLURAL = "xml.namePlural"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_ROLE_ELEMENT = "xml.roleElement"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_ROLE_WRAPPER_ELEMENT = "xml.roleWrapperElement"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_TYPE_ELEMENT = "xml.typeElement"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_TYPE_WRAPPER_ELEMENT = "xml.typeWrapperElement"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_NS_PREFIX = "xml.nsPrefix"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_NS_URI = "xml.nsUri"; //$NON-NLS-1$
	public static String TAGGED_VALUE_XML_ATTRIBUTE = "xml.attribute"; //$NON-NLS-1$
	public static String TAGGED_VALUE_EXTENSION_POINT = "xml.extensionPoint"; //$NON-NLS-1$

	/* JETEmitterDescriptor */
	public static String TRAVERSAL_HELPER_JAVAJET = "model/TraversalHelper.javajet"; //$NON-NLS-1$
	public static String TRAVERSAL_HELPER_CLASS = "org.eclipse.emf.codegen.ecore.templates.model.TraversalHelper"; //$NON-NLS-1$
	public static String RELEASE_DESCRIPTOR_JAVAJET = "model/ReleaseDescriptor.javajet"; //$NON-NLS-1$
	public static String RELEASE_DESCRIPTOR_CLASS = "org.eclipse.emf.codegen.ecore.templates.model.ReleaseDescriptor"; //$NON-NLS-1$
	public static String ITEM_LABEL_PROVIDER_ADAPTER_FACTORY_JAVAJET = "edit/ItemLabelProviderAdapterFactory.javajet"; //$NON-NLS-1$
	public static String ITEM_LABEL_PROVIDER_ADAPTER_FACTORY_CLASS = "org.eclipse.emf.codegen.ecore.templates.edit.ItemLabelProviderAdapterFactory"; //$NON-NLS-1$
	public static String RELEASE_DESCRIPTOR_ITEM_LABEL_PROVIDER_JAVAJET = "edit/ReleaseDescriptorItemLabelProvider.javajet"; //$NON-NLS-1$
	public static String RELEASE_DESCRIPTOR_ITEM_LABEL_PROVIDER_CLASS = "org.eclipse.emf.codegen.ecore.templates.edit.ReleaseDescriptorItemLabelProvider"; //$NON-NLS-1$
	public static String FACTORY_CLASS_JAVAJET = "model/FactoryClass.javajet"; //$NON-NLS-1$
	public static String FACTORY_CLASS = "org.eclipse.emf.codegen.ecore.templates.model.Factory"; //$NON-NLS-1$
}
