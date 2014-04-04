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
package org.eclipse.eatop.eaadapter.util;

public interface IConstants {

	public static String MODEL_DIRECTORY = "/model"; //$NON-NLS-1$
	public static String SOURCE_MODEL_ROOT_PATH = MODEL_DIRECTORY + "/"; //$NON-NLS-1$
	public static String SRC_DIRECTORY = "/src"; //$NON-NLS-1$
	public static String BUNDLE_CLASS_PATH_ATTRIBUTE = "Bundle-Classpath"; //$NON-NLS-1$

	public static String LIB_FOLDER = "lib"; //$NON-NLS-1$
	public static String SRC_FOLDER = "src"; //$NON-NLS-1$
	public static String EDIT_FOLDER = "edit"; //$NON-NLS-1$
	public static String BIN_FOLDER = "bin"; //$NON-NLS-1$
	public static String METAINF_FOLDER = "META-INF"; //$NON-NLS-1$

	public static String ZIP = "zip"; //$NON-NLS-1$
	public static String JAR = "jar"; //$NON-NLS-1$

	public static String PROVIDER_PACKAGE_SUFFIX = "providers"; //$NON-NLS-1$

	/* Instance class name of datatype */
	public static String BOOLEAN_TYPE = "boolean"; //$NON-NLS-1$
	public static String INT_TYPE = "int"; //$NON-NLS-1$
	public static String FLOAT_TYPE = "float"; //$NON-NLS-1$
	public static String LONG_TYPE = "long"; //$NON-NLS-1$
	public static String DOUBLE_TYPE = "double"; //$NON-NLS-1$

	/* Default value */
	public static String FALSE = "false"; //$NON-NLS-1$
	public static String TRUE = "true"; //$NON-NLS-1$
	public static String ZERO = "0"; //$NON-NLS-1$
	public static String ZERO_FLOAT = "0.0"; //$NON-NLS-1$
	public static String XML_GREGORIAN_CALENDAR_DEFAULT_VALUE = "0001-01-01T00:00:00"; //$NON-NLS-1$

	/* Configure properties */
	public static String MODEL_CONFIG_PROPERTIES_NAME = "config.properties"; //$NON-NLS-1$
	public static String LINE_SEPARATOR_PROPERTY = "line.separator"; //$NON-NLS-1$

	public static String ECORE_FILE_EXTENSION = "ecore"; //$NON-NLS-1$
	public static String GENMODEL_FILE_EXTENSION = "genmodel"; //$NON-NLS-1$
	public static String XSD_FILE_EXTENSION = "xsd"; //$NON-NLS-1$
	public static String EAP_FILE_EXTENSION = "eap"; //$NON-NLS-1$

	public static String ECORE_FILE_POSTFIX = "." + ECORE_FILE_EXTENSION; //$NON-NLS-1$
	public static String GENMODEL_FILE_POSTFIX = "." + GENMODEL_FILE_EXTENSION; //$NON-NLS-1$
	public static String XSD_FILE_POSTFIX = "." + XSD_FILE_EXTENSION; //$NON-NLS-1$
	public static String EAP_FILE_POSTFIX = "." + EAP_FILE_EXTENSION; //$NON-NLS-1$

	/* Package */
	public static String PACKAGE_PRIMITIVETYPES = "PrimitiveTypes"; //$NON-NLS-1$
	public static String PACKAGE_IDENTIFIABLE = "Identifiable"; //$NON-NLS-1$
	public static String PACKAGE_MODEL = "model"; //$NON-NLS-1$
	public static String PACKAGE_EXAMPLEMODEL = "examplemodel"; //$NON-NLS-1$
	public static String PACKAGE_GENERATEDECORE = "Generated Ecore"; //$NON-NLS-1$

	/* Instance Class */
	public static String STRING = "String"; //$NON-NLS-1$
	public static String INTEGER = "Integer"; //$NON-NLS-1$
	public static String FLOAT = "Float"; //$NON-NLS-1$
	public static String NUMERICAL = "Numerical"; //$NON-NLS-1$
	public static String UNLIMITED_INTEGER = "UnlimitedInteger"; //$NON-NLS-1$
	public static String UNLIMITED_NATURAL = "UnlimitedNatural"; //$NON-NLS-1$
	public static String POSITIVE_INTEGER = "PositiveInteger"; //$NON-NLS-1$
	public static String BOOLEAN = "Boolean"; //$NON-NLS-1$
	public static String DATE_TIME = "DateTime"; //$NON-NLS-1$
	public static String DATE = "Date"; //$NON-NLS-1$
	public static String NAME_TOKEN = "NameToken"; //$NON-NLS-1$
	public static String EXT_ID_CLASS_ENUM = "ExtIdClassEnum"; //$NON-NLS-1$
	public static String REGULAR_EXPRESSION = "RegularExpression"; //$NON-NLS-1$
	public static String IDENTIFIER = "Identifier"; //$NON-NLS-1$
	public static String ADDRESS = "Address"; //$NON-NLS-1$
	public static String BASE_TYPE_ENCODING_STRING = "BaseTypeEncodingString"; //$NON-NLS-1$
	public static String DISPLAY_FORMAT_STRING = "DisplayFormatString"; //$NON-NLS-1$
	public static String MIME_TYPE_STRING = "MimeTypeString"; //$NON-NLS-1$
	public static String URI_STRING = "UriString"; //$NON-NLS-1$
	public static String URL_STRING = "UrlString"; //$NON-NLS-1$
	public static String IS_STRING = "IsString"; //$NON-NLS-1$
	public static String NUMERICAL_VALUE = "NumericalValue"; //$NON-NLS-1$
	public static String NATIVE_DECLARATION_STRING = "NativeDeclarationString"; //$NON-NLS-1$
	public static String REF = "Ref"; //$NON-NLS-1$
	public static String REVISION_LABEL_STRING = "RevisionLabelString"; //$NON-NLS-1$
	public static String VERBATIM_STRING = "VerbatimString"; //$NON-NLS-1$
	public static String SUPER_SCRIPT = "Superscript"; //$NON-NLS-1$
	public static String MIXED_CONTENT_FOR_PLAINE_TEXT = "MixedContentForPlainText"; //$NON-NLS-1$
	public static String AXIS_INDEX_TYPE = "AxisIndexType"; //$NON-NLS-1$
	public static String RECORD_LAYOUT_ITERATOR_POINT = "RecordLayoutIteratorPoint"; //$NON-NLS-1$
	public static String DIAG_REQUIREMENT_ID_STRING = "DiagRequirementIdString"; //$NON-NLS-1$
	public static String NAME_TOKENS = "NameTokens"; //$NON-NLS-1$
	public static String CIDENTIFIER = "CIdentifier"; //$NON-NLS-1$
	public static String CSE_CODE_TYPE = "CseCodeType"; //$NON-NLS-1$
	public static String ASAM_RECORD_LAYOUT_SEMANTICS = "AsamRecordLayoutSemantics"; //$NON-NLS-1$
	public static String TABLE_SEPARATOR_STRING = "TableSeparatorString"; //$NON-NLS-1$
	public static String VIEW_TOKENS = "ViewTokens"; //$NON-NLS-1$
	public static String ALIGNMENT_TYPE = "AlignmentType"; //$NON-NLS-1$
	public static String CATEGORY_STRING = "CategoryString"; //$NON-NLS-1$
	public static String SECTION_INITIALIZATION_POLICY_TYPE = "SectionInitializationPolicyType"; //$NON-NLS-1$
	public static String REFERRABLE_SUBTYPE_ENUM = "ReferrableSubtypesEnum"; //$NON-NLS-1$
	public static String TIME_VALUE = "TimeValue"; //$NON-NLS-1$

	/* URI */
	public static String DEFAULT_NSURI = "http://"; //$NON-NLS-1$
	public static String GENERATED_ECORE_NSURI = "http://generated-ecore"; //$NON-NLS-1$
	public static String GENMODEL_NSURI = "http://www.eclipse.org/emf/2002/GenModel"; //$NON-NLS-1$
	public static String EXTENDED_METADATA_NSURI = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData"; //$NON-NLS-1$

	public static String MANIFEST_FILE_DEFAULT = "MANIFEST.MF"; //$NON-NLS-1$
	public static String NAME_WAS_NOT_SET = "NameWasNotSet_"; //$NON-NLS-1$

	/* Connector */
	public static String ASSOCIATION_CONNECTOR = "Association"; //$NON-NLS-1$
	public static String GENERALIZATION_CONNECTOR = "Generalization"; //$NON-NLS-1$
	public static String AGGREGATION_CONNECTOR = "Aggregation"; //$NON-NLS-1$
	public static String DEPENDENCY_CONNECTOR = "Dependency"; //$NON-NLS-1$
	public static String NOTELINE_CONNECTOR = "NoteLink"; //$NON-NLS-1$

	public static String DOCUMENTATION = "documentation"; //$NON-NLS-1$
	public static String KIND = "kind"; //$NON-NLS-1$

	/* Meta types */
	public static String BOUNDARY = "Boundary"; //$NON-NLS-1$
	public static String TEXT = "Text"; //$NON-NLS-1$
	public static String NOTE = "Note"; //$NON-NLS-1$

	/* Annotation */
	public static String STEREOTYPE = "Stereotype"; //$NON-NLS-1$
	public static String TAGGED_VALUES = "TaggedValues"; //$NON-NLS-1$

	/* Stereotype */
	public static String ENUMERATION = "enumeration"; //$NON-NLS-1$
	public static String PRIMITIVE = "primitive"; //$NON-NLS-1$

	/* Meta data */
	public static String METADATA = "MetaData"; //$NON-NLS-1$
	public static String GUID = "guid"; //$NON-NLS-1$
	public static String ID = "id"; //$NON-NLS-1$
	public static String EA_NAME = "EA name"; //$NON-NLS-1$

	public static String XML_PERSISTENCE_MAPPING_ANNOTATION_SOURCE_URI = "http:///org/eclipse/sphinx/emf/serialization/XMLPersistenceMappingExtendedMetaData"; //$NON-NLS-1$
	public static String FEATURE_WRAPPER_ELEMENT = "featureWrapperElement"; //$NON-NLS-1$
	public static String FEATURE_ELEMENT = "featureElement"; //$NON-NLS-1$
	public static String CLASSIFIER_WRAPPER_ELEMENT = "classifierWrapperElement"; //$NON-NLS-1$
	public static String CLASSIFIER_ELEMENT = "classifierElement"; //$NON-NLS-1$

}
