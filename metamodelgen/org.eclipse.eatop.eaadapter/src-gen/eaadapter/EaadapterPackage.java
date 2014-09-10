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
 * 		Continental AG, Matthias Nick - Initial API and implementation
 * </copyright>
 * 
 */
package eaadapter;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import eaadapter.abstracthierachy.AbstracthierachyPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc --> <div class='userdoc'> The package for the EA metamodel.<br>
 * <code>EARepository</code> is the root element for all EA models.<br>
 * </div> <!-- end-model-doc -->
 * 
 * @see eaadapter.EaadapterFactory
 * @model kind="package"
 * @generated
 */
public interface EaadapterPackage extends EPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "eaadapter";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://eaadapter";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "eaadapter";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	EaadapterPackage eINSTANCE = eaadapter.impl.EaadapterPackageImpl.init();

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAAttributeImpl <em>EA Attribute</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAAttributeImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAAttribute()
	 * @generated
	 */
	int EA_ATTRIBUTE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__NAME = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__NOTES = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__GUID = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__ID = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__STEREOTYPE = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Stereotype Ex</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__STEREOTYPE_EX = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__STEREOTYPE_EX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__TYPE = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Is Const</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__IS_CONST = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__IS_CONST;

	/**
	 * The feature id for the '<em><b>Is Static</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__IS_STATIC = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__IS_STATIC;

	/**
	 * The feature id for the '<em><b>Classifier ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__CLASSIFIER_ID = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Containment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__CONTAINMENT = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Collection</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__COLLECTION = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__ORDERED = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Allow Duplicates</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__ALLOW_DUPLICATES = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__DERIVED = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Container</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__CONTAINER = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Scale</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__SCALE = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Precision</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__PRECISION = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__LENGTH = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__LOWER_BOUND = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__UPPER_BOUND = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__DEFAULT = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Element</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__ELEMENT = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__EA_LINK = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Tagged Values</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__TAGGED_VALUES = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE__VISIBILITY = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The number of structural features of the '<em>EA Attribute</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE_FEATURE_COUNT = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAAttributeTagImpl <em>EA Attribute Tag</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAAttributeTagImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAAttributeTag()
	 * @generated
	 */
	int EA_ATTRIBUTE_TAG = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE_TAG__NAME = AbstracthierachyPackage.EA_TAGGED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE_TAG__NOTES = AbstracthierachyPackage.EA_TAGGED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE_TAG__GUID = AbstracthierachyPackage.EA_TAGGED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE_TAG__ID = AbstracthierachyPackage.EA_TAGGED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE_TAG__VALUE = AbstracthierachyPackage.EA_TAGGED_ELEMENT__VALUE;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE_TAG__EA_LINK = AbstracthierachyPackage.EA_TAGGED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EA Attribute Tag</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ATTRIBUTE_TAG_FEATURE_COUNT = AbstracthierachyPackage.EA_TAGGED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAConnectorImpl <em>EA Connector</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAConnectorImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAConnector()
	 * @generated
	 */
	int EA_CONNECTOR = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__NAME = AbstracthierachyPackage.EA_TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__NOTES = AbstracthierachyPackage.EA_TYPED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__GUID = AbstracthierachyPackage.EA_TYPED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__ID = AbstracthierachyPackage.EA_TYPED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__STEREOTYPE = AbstracthierachyPackage.EA_TYPED_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Stereotype Ex</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__STEREOTYPE_EX = AbstracthierachyPackage.EA_TYPED_ELEMENT__STEREOTYPE_EX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__TYPE = AbstracthierachyPackage.EA_TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Connector ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__CONNECTOR_ID = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__DIRECTION = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Subtype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__SUBTYPE = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Is Leaf</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__IS_LEAF = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Is Root</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__IS_ROOT = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Is Spec</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__IS_SPEC = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Virtual Inheritance</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__VIRTUAL_INHERITANCE = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Transition Event</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__TRANSITION_EVENT = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Transition Action</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__TRANSITION_ACTION = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Transition Guard</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__TRANSITION_GUARD = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Route Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__ROUTE_STYLE = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Sequence No</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__SEQUENCE_NO = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__EA_LINK = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Tagged Values</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__TAGGED_VALUES = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Client</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__CLIENT = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Supplier</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__SUPPLIER = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__CONSTRAINTS = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Client End</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__CLIENT_END = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Client ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__CLIENT_ID = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Supplier ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__SUPPLIER_ID = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Event Flags</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__EVENT_FLAGS = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Style Ex</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__STYLE_EX = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Supplier End</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__SUPPLIER_END = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR__DERIVED = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 23;

	/**
	 * The number of structural features of the '<em>EA Connector</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_FEATURE_COUNT = AbstracthierachyPackage.EA_TYPED_ELEMENT_FEATURE_COUNT + 24;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAConnectorTagImpl <em>EA Connector Tag</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAConnectorTagImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAConnectorTag()
	 * @generated
	 */
	int EA_CONNECTOR_TAG = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_TAG__NAME = AbstracthierachyPackage.EA_TAGGED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_TAG__NOTES = AbstracthierachyPackage.EA_TAGGED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_TAG__GUID = AbstracthierachyPackage.EA_TAGGED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_TAG__ID = AbstracthierachyPackage.EA_TAGGED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_TAG__VALUE = AbstracthierachyPackage.EA_TAGGED_ELEMENT__VALUE;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_TAG__EA_LINK = AbstracthierachyPackage.EA_TAGGED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EA Connector Tag</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_TAG_FEATURE_COUNT = AbstracthierachyPackage.EA_TAGGED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAElementImpl <em>EA Element</em>}' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAElementImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAElement()
	 * @generated
	 */
	int EA_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__NAME = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__NOTES = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__GUID = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__ID = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__STEREOTYPE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Stereotype Ex</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__STEREOTYPE_EX = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__STEREOTYPE_EX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__TYPE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__TYPE;

	/**
	 * The feature id for the '<em><b>Classifier ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__CLASSIFIER_ID = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__VERSION = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__AUTHOR = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Locked</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__IS_LOCKED = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Classifier Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__CLASSIFIER_NAME = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Classifier Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__CLASSIFIER_TYPE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Complexity</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__COMPLEXITY = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Difficulty</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__DIFFICULTY = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Extension Points</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__EXTENSION_POINTS = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Genlinks</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__GENLINKS = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Genfile</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__GENFILE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Gentype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__GENTYPE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Multiplicity</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__MULTIPLICITY = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Phase</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__PHASE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__PRIORITY = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Property Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__PROPERTY_TYPE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__STATUS = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 15;

	/**
	 * The feature id for the '<em><b>Subtype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__SUBTYPE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Tablespace</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__TABLESPACE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 17;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__TAG = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 18;

	/**
	 * The feature id for the '<em><b>Element</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__ELEMENT = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 19;

	/**
	 * The feature id for the '<em><b>Package</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__PACKAGE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 20;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__ATTRIBUTES = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 21;

	/**
	 * The feature id for the '<em><b>Connectors</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__CONNECTORS = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 22;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__ELEMENTS = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 23;

	/**
	 * The feature id for the '<em><b>Methods</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__METHODS = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 24;

	/**
	 * The feature id for the '<em><b>Tagged Values</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__TAGGED_VALUES = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 25;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__EA_LINK = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 26;

	/**
	 * The feature id for the '<em><b>Parent ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__PARENT_ID = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 27;

	/**
	 * The feature id for the '<em><b>Package ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__PACKAGE_ID = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 28;

	/**
	 * The feature id for the '<em><b>All Connectors</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__ALL_CONNECTORS = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 29;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__VISIBILITY = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 30;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__IS_ABSTRACT = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 31;

	/**
	 * The feature id for the '<em><b>Is Active</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__IS_ACTIVE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 32;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__CONSTRAINTS = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 33;

	/**
	 * The feature id for the '<em><b>Meta Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT__META_TYPE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 34;

	/**
	 * The number of structural features of the '<em>EA Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ELEMENT_FEATURE_COUNT = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 35;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAMethodImpl <em>EA Method</em>}' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAMethodImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAMethod()
	 * @generated
	 */
	int EA_METHOD = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__NAME = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__NOTES = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__GUID = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__ID = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__STEREOTYPE = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Stereotype Ex</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__STEREOTYPE_EX = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__STEREOTYPE_EX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__TYPE = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Is Const</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__IS_CONST = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__IS_CONST;

	/**
	 * The feature id for the '<em><b>Is Static</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__IS_STATIC = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT__IS_STATIC;

	/**
	 * The feature id for the '<em><b>Classifier ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__CLASSIFIER_ID = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__RETURN_TYPE = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Return Is Array</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__RETURN_IS_ARRAY = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__CODE = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Throws</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__THROWS = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Is Pure</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__IS_PURE = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Is Root</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__IS_ROOT = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Is Leaf</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__IS_LEAF = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Is Query</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__IS_QUERY = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Is Synchronized</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__IS_SYNCHRONIZED = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__IS_ABSTRACT = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Concurrency</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__CONCURRENCY = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Element</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__ELEMENT = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__EA_LINK = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__PARAMETERS = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Tagged Values</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD__TAGGED_VALUES = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>EA Method</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD_FEATURE_COUNT = AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAMethodTagImpl <em>EA Method Tag</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAMethodTagImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAMethodTag()
	 * @generated
	 */
	int EA_METHOD_TAG = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD_TAG__NAME = AbstracthierachyPackage.EA_TAGGED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD_TAG__NOTES = AbstracthierachyPackage.EA_TAGGED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD_TAG__GUID = AbstracthierachyPackage.EA_TAGGED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD_TAG__ID = AbstracthierachyPackage.EA_TAGGED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD_TAG__VALUE = AbstracthierachyPackage.EA_TAGGED_ELEMENT__VALUE;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD_TAG__EA_LINK = AbstracthierachyPackage.EA_TAGGED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EA Method Tag</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_METHOD_TAG_FEATURE_COUNT = AbstracthierachyPackage.EA_TAGGED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAPackageImpl <em>EA Package</em>}' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAPackageImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAPackage()
	 * @generated
	 */
	int EA_PACKAGE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__NAME = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__NOTES = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__GUID = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__ID = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__VERSION = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE__VERSION;

	/**
	 * The feature id for the '<em><b>Code Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__CODE_PATH = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE__CODE_PATH;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__FLAGS = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE__FLAGS;

	/**
	 * The feature id for the '<em><b>Is Model</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__IS_MODEL = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE__IS_MODEL;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__ELEMENTS = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Packages</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__PACKAGES = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Super Package</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__SUPER_PACKAGE = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__EA_LINK = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE__STEREOTYPE = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>EA Package</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PACKAGE_FEATURE_COUNT = AbstracthierachyPackage.EA_ABSTRACT_PACKAGE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAParameterImpl <em>EA Parameter</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAParameterImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAParameter()
	 * @generated
	 */
	int EA_PARAMETER = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__NAME = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__NOTES = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__GUID = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__ID = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__STEREOTYPE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Stereotype Ex</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__STEREOTYPE_EX = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__STEREOTYPE_EX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__TYPE = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__TYPE;

	/**
	 * The feature id for the '<em><b>Classifier ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__CLASSIFIER_ID = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Default</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__DEFAULT = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__POSITION = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Const</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__IS_CONST = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__KIND = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Operation ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__OPERATION_ID = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Method</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__METHOD = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER__EA_LINK = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>EA Parameter</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_PARAMETER_FEATURE_COUNT = AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EARepositoryImpl <em>EA Repository</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EARepositoryImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEARepository()
	 * @generated
	 */
	int EA_REPOSITORY = 9;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_REPOSITORY__EA_LINK = 0;

	/**
	 * The feature id for the '<em><b>File</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_REPOSITORY__FILE = 1;

	/**
	 * The feature id for the '<em><b>Models</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_REPOSITORY__MODELS = 2;

	/**
	 * The number of structural features of the '<em>EA Repository</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_REPOSITORY_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EATaggedValueImpl <em>EA Tagged Value</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EATaggedValueImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEATaggedValue()
	 * @generated
	 */
	int EA_TAGGED_VALUE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_VALUE__NAME = AbstracthierachyPackage.EA_TAGGED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_VALUE__NOTES = AbstracthierachyPackage.EA_TAGGED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_VALUE__GUID = AbstracthierachyPackage.EA_TAGGED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_VALUE__ID = AbstracthierachyPackage.EA_TAGGED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_VALUE__VALUE = AbstracthierachyPackage.EA_TAGGED_ELEMENT__VALUE;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_VALUE__EA_LINK = AbstracthierachyPackage.EA_TAGGED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EA Tagged Value</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_VALUE_FEATURE_COUNT = AbstracthierachyPackage.EA_TAGGED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAConnectorEndImpl <em>EA Connector End</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAConnectorEndImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAConnectorEnd()
	 * @generated
	 */
	int EA_CONNECTOR_END = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__NAME = AbstracthierachyPackage.EA_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__NOTES = AbstracthierachyPackage.EA_NAMED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__GUID = AbstracthierachyPackage.EA_NAMED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__ID = AbstracthierachyPackage.EA_NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Tagged Values</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__TAGGED_VALUES = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__END = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cardinality</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__CARDINALITY = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__VISIBILITY = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__ROLE = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Role Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__ROLE_TYPE = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Role Note</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__ROLE_NOTE = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Containment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__CONTAINMENT = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Aggregation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__AGGREGATION = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__ORDERING = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Qualifier</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__QUALIFIER = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__CONSTRAINT = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Is Navigable</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__IS_NAVIGABLE = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Is Changable</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__IS_CHANGABLE = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__EA_LINK = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END__DERIVED = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>EA Connector End</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_END_FEATURE_COUNT = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 16;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAConnectorConstraintImpl <em>EA Connector Constraint</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAConnectorConstraintImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAConnectorConstraint()
	 * @generated
	 */
	int EA_CONNECTOR_CONSTRAINT = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_CONSTRAINT__NAME = AbstracthierachyPackage.EA_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_CONSTRAINT__NOTES = AbstracthierachyPackage.EA_NAMED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_CONSTRAINT__GUID = AbstracthierachyPackage.EA_NAMED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_CONSTRAINT__ID = AbstracthierachyPackage.EA_NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Connector ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_CONSTRAINT__CONNECTOR_ID = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_CONSTRAINT__TYPE = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Connector</b></em>' container reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_CONSTRAINT__CONNECTOR = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_CONSTRAINT__EA_LINK = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>EA Connector Constraint</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONNECTOR_CONSTRAINT_FEATURE_COUNT = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EARoleTagImpl <em>EA Role Tag</em>}' class. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EARoleTagImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEARoleTag()
	 * @generated
	 */
	int EA_ROLE_TAG = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__NAME = AbstracthierachyPackage.EA_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__NOTES = AbstracthierachyPackage.EA_NAMED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__GUID = AbstracthierachyPackage.EA_NAMED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__ID = AbstracthierachyPackage.EA_NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Base Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__BASE_CLASS = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Element GUID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__ELEMENT_GUID = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Property GUID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__PROPERTY_GUID = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__TAG = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Connector End</b></em>' container reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__CONNECTOR_END = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__EA_LINK = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG__VALUE = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>EA Role Tag</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ROLE_TAG_FEATURE_COUNT = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link eaadapter.impl.EAConstraintImpl <em>EA Constraint</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.impl.EAConstraintImpl
	 * @see eaadapter.impl.EaadapterPackageImpl#getEAConstraint()
	 * @generated
	 */
	int EA_CONSTRAINT = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONSTRAINT__NAME = AbstracthierachyPackage.EA_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONSTRAINT__NOTES = AbstracthierachyPackage.EA_NAMED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONSTRAINT__GUID = AbstracthierachyPackage.EA_NAMED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONSTRAINT__ID = AbstracthierachyPackage.EA_NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONSTRAINT__STATUS = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONSTRAINT__TYPE = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONSTRAINT__WEIGHT = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>EA Constraint</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CONSTRAINT_FEATURE_COUNT = AbstracthierachyPackage.EA_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * Returns the meta object for class '{@link eaadapter.EAAttribute <em>EA Attribute</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Attribute</em>'.
	 * @see eaadapter.EAAttribute
	 * @generated
	 */
	EClass getEAAttribute();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getContainment <em>Containment</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Containment</em>'.
	 * @see eaadapter.EAAttribute#getContainment()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_Containment();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getCollection <em>Collection</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Collection</em>'.
	 * @see eaadapter.EAAttribute#getCollection()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_Collection();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getOrdered <em>Ordered</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ordered</em>'.
	 * @see eaadapter.EAAttribute#getOrdered()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_Ordered();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getAllowDuplicates
	 * <em>Allow Duplicates</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Allow Duplicates</em>'.
	 * @see eaadapter.EAAttribute#getAllowDuplicates()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_AllowDuplicates();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getDerived <em>Derived</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Derived</em>'.
	 * @see eaadapter.EAAttribute#getDerived()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_Derived();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getContainer <em>Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Container</em>'.
	 * @see eaadapter.EAAttribute#getContainer()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_Container();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getScale <em>Scale</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Scale</em>'.
	 * @see eaadapter.EAAttribute#getScale()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_Scale();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getPrecision <em>Precision</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Precision</em>'.
	 * @see eaadapter.EAAttribute#getPrecision()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_Precision();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getLength <em>Length</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see eaadapter.EAAttribute#getLength()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_Length();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Lower Bound</em>'.
	 * @see eaadapter.EAAttribute#getLowerBound()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_LowerBound();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Upper Bound</em>'.
	 * @see eaadapter.EAAttribute#getUpperBound()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_UpperBound();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getDefault <em>Default</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see eaadapter.EAAttribute#getDefault()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_Default();

	/**
	 * Returns the meta object for the container reference '{@link eaadapter.EAAttribute#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Element</em>'.
	 * @see eaadapter.EAAttribute#getElement()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EReference getEAAttribute_Element();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAAttribute#getEaLink()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_EaLink();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAAttribute#getTaggedValues
	 * <em>Tagged Values</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Tagged Values</em>'.
	 * @see eaadapter.EAAttribute#getTaggedValues()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EReference getEAAttribute_TaggedValues();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttribute#getVisibility <em>Visibility</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Visibility</em>'.
	 * @see eaadapter.EAAttribute#getVisibility()
	 * @see #getEAAttribute()
	 * @generated
	 */
	EAttribute getEAAttribute_Visibility();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAAttributeTag <em>EA Attribute Tag</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Attribute Tag</em>'.
	 * @see eaadapter.EAAttributeTag
	 * @generated
	 */
	EClass getEAAttributeTag();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAAttributeTag#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAAttributeTag#getEaLink()
	 * @see #getEAAttributeTag()
	 * @generated
	 */
	EAttribute getEAAttributeTag_EaLink();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAConnector <em>EA Connector</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Connector</em>'.
	 * @see eaadapter.EAConnector
	 * @generated
	 */
	EClass getEAConnector();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getConnectorID <em>Connector ID</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Connector ID</em>'.
	 * @see eaadapter.EAConnector#getConnectorID()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_ConnectorID();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getDirection <em>Direction</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see eaadapter.EAConnector#getDirection()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_Direction();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getSubtype <em>Subtype</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Subtype</em>'.
	 * @see eaadapter.EAConnector#getSubtype()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_Subtype();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getIsLeaf <em>Is Leaf</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Leaf</em>'.
	 * @see eaadapter.EAConnector#getIsLeaf()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_IsLeaf();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getIsRoot <em>Is Root</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Root</em>'.
	 * @see eaadapter.EAConnector#getIsRoot()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_IsRoot();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getIsSpec <em>Is Spec</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Spec</em>'.
	 * @see eaadapter.EAConnector#getIsSpec()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_IsSpec();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getVirtualInheritance
	 * <em>Virtual Inheritance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Virtual Inheritance</em>'.
	 * @see eaadapter.EAConnector#getVirtualInheritance()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_VirtualInheritance();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getTransitionEvent
	 * <em>Transition Event</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Transition Event</em>'.
	 * @see eaadapter.EAConnector#getTransitionEvent()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_TransitionEvent();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getTransitionAction
	 * <em>Transition Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Transition Action</em>'.
	 * @see eaadapter.EAConnector#getTransitionAction()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_TransitionAction();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getTransitionGuard
	 * <em>Transition Guard</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Transition Guard</em>'.
	 * @see eaadapter.EAConnector#getTransitionGuard()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_TransitionGuard();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getRouteStyle <em>Route Style</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Route Style</em>'.
	 * @see eaadapter.EAConnector#getRouteStyle()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_RouteStyle();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getSequenceNo <em>Sequence No</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Sequence No</em>'.
	 * @see eaadapter.EAConnector#getSequenceNo()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_SequenceNo();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAConnector#getEaLink()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_EaLink();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAConnector#getTaggedValues
	 * <em>Tagged Values</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Tagged Values</em>'.
	 * @see eaadapter.EAConnector#getTaggedValues()
	 * @see #getEAConnector()
	 * @generated
	 */
	EReference getEAConnector_TaggedValues();

	/**
	 * Returns the meta object for the container reference '{@link eaadapter.EAConnector#getClient <em>Client</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Client</em>'.
	 * @see eaadapter.EAConnector#getClient()
	 * @see #getEAConnector()
	 * @generated
	 */
	EReference getEAConnector_Client();

	/**
	 * Returns the meta object for the reference '{@link eaadapter.EAConnector#getSupplier <em>Supplier</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Supplier</em>'.
	 * @see eaadapter.EAConnector#getSupplier()
	 * @see #getEAConnector()
	 * @generated
	 */
	EReference getEAConnector_Supplier();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAConnector#getConstraints
	 * <em>Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Constraints</em>'.
	 * @see eaadapter.EAConnector#getConstraints()
	 * @see #getEAConnector()
	 * @generated
	 */
	EReference getEAConnector_Constraints();

	/**
	 * Returns the meta object for the containment reference '{@link eaadapter.EAConnector#getClientEnd
	 * <em>Client End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Client End</em>'.
	 * @see eaadapter.EAConnector#getClientEnd()
	 * @see #getEAConnector()
	 * @generated
	 */
	EReference getEAConnector_ClientEnd();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getClientID <em>Client ID</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Client ID</em>'.
	 * @see eaadapter.EAConnector#getClientID()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_ClientID();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getSupplierID <em>Supplier ID</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Supplier ID</em>'.
	 * @see eaadapter.EAConnector#getSupplierID()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_SupplierID();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getEventFlags <em>Event Flags</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Event Flags</em>'.
	 * @see eaadapter.EAConnector#getEventFlags()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_EventFlags();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#getStyleEx <em>Style Ex</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Style Ex</em>'.
	 * @see eaadapter.EAConnector#getStyleEx()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_StyleEx();

	/**
	 * Returns the meta object for the containment reference '{@link eaadapter.EAConnector#getSupplierEnd
	 * <em>Supplier End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Supplier End</em>'.
	 * @see eaadapter.EAConnector#getSupplierEnd()
	 * @see #getEAConnector()
	 * @generated
	 */
	EReference getEAConnector_SupplierEnd();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnector#isDerived <em>Derived</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Derived</em>'.
	 * @see eaadapter.EAConnector#isDerived()
	 * @see #getEAConnector()
	 * @generated
	 */
	EAttribute getEAConnector_Derived();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAConnectorTag <em>EA Connector Tag</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Connector Tag</em>'.
	 * @see eaadapter.EAConnectorTag
	 * @generated
	 */
	EClass getEAConnectorTag();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorTag#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAConnectorTag#getEaLink()
	 * @see #getEAConnectorTag()
	 * @generated
	 */
	EAttribute getEAConnectorTag_EaLink();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAElement <em>EA Element</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Element</em>'.
	 * @see eaadapter.EAElement
	 * @generated
	 */
	EClass getEAElement();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getClassifierName <em>Classifier Name</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Classifier Name</em>'.
	 * @see eaadapter.EAElement#getClassifierName()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_ClassifierName();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getClassifierType <em>Classifier Type</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Classifier Type</em>'.
	 * @see eaadapter.EAElement#getClassifierType()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_ClassifierType();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getComplexity <em>Complexity</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Complexity</em>'.
	 * @see eaadapter.EAElement#getComplexity()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Complexity();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getDifficulty <em>Difficulty</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Difficulty</em>'.
	 * @see eaadapter.EAElement#getDifficulty()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Difficulty();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getExtensionPoints
	 * <em>Extension Points</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Extension Points</em>'.
	 * @see eaadapter.EAElement#getExtensionPoints()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_ExtensionPoints();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getGenlinks <em>Genlinks</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Genlinks</em>'.
	 * @see eaadapter.EAElement#getGenlinks()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Genlinks();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getGenfile <em>Genfile</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Genfile</em>'.
	 * @see eaadapter.EAElement#getGenfile()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Genfile();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getGentype <em>Gentype</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Gentype</em>'.
	 * @see eaadapter.EAElement#getGentype()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Gentype();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getMultiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Multiplicity</em>'.
	 * @see eaadapter.EAElement#getMultiplicity()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Multiplicity();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getPhase <em>Phase</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Phase</em>'.
	 * @see eaadapter.EAElement#getPhase()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Phase();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getPriority <em>Priority</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see eaadapter.EAElement#getPriority()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Priority();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getPropertyType <em>Property Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Property Type</em>'.
	 * @see eaadapter.EAElement#getPropertyType()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_PropertyType();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getStatus <em>Status</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see eaadapter.EAElement#getStatus()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Status();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getSubtype <em>Subtype</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Subtype</em>'.
	 * @see eaadapter.EAElement#getSubtype()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Subtype();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getTablespace <em>Tablespace</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Tablespace</em>'.
	 * @see eaadapter.EAElement#getTablespace()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Tablespace();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getTag <em>Tag</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see eaadapter.EAElement#getTag()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Tag();

	/**
	 * Returns the meta object for the container reference '{@link eaadapter.EAElement#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Element</em>'.
	 * @see eaadapter.EAElement#getElement()
	 * @see #getEAElement()
	 * @generated
	 */
	EReference getEAElement_Element();

	/**
	 * Returns the meta object for the container reference '{@link eaadapter.EAElement#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Package</em>'.
	 * @see eaadapter.EAElement#getPackage()
	 * @see #getEAElement()
	 * @generated
	 */
	EReference getEAElement_Package();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAElement#getAttributes
	 * <em>Attributes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see eaadapter.EAElement#getAttributes()
	 * @see #getEAElement()
	 * @generated
	 */
	EReference getEAElement_Attributes();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAElement#getConnectors
	 * <em>Connectors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Connectors</em>'.
	 * @see eaadapter.EAElement#getConnectors()
	 * @see #getEAElement()
	 * @generated
	 */
	EReference getEAElement_Connectors();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAElement#getElements
	 * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see eaadapter.EAElement#getElements()
	 * @see #getEAElement()
	 * @generated
	 */
	EReference getEAElement_Elements();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAElement#getMethods
	 * <em>Methods</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Methods</em>'.
	 * @see eaadapter.EAElement#getMethods()
	 * @see #getEAElement()
	 * @generated
	 */
	EReference getEAElement_Methods();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAElement#getTaggedValues
	 * <em>Tagged Values</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Tagged Values</em>'.
	 * @see eaadapter.EAElement#getTaggedValues()
	 * @see #getEAElement()
	 * @generated
	 */
	EReference getEAElement_TaggedValues();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAElement#getEaLink()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_EaLink();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getParentID <em>Parent ID</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Parent ID</em>'.
	 * @see eaadapter.EAElement#getParentID()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_ParentID();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getPackageID <em>Package ID</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Package ID</em>'.
	 * @see eaadapter.EAElement#getPackageID()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_PackageID();

	/**
	 * Returns the meta object for the reference list '{@link eaadapter.EAElement#getAllConnectors
	 * <em>All Connectors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>All Connectors</em>'.
	 * @see eaadapter.EAElement#getAllConnectors()
	 * @see #getEAElement()
	 * @generated
	 */
	EReference getEAElement_AllConnectors();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getVisibility <em>Visibility</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Visibility</em>'.
	 * @see eaadapter.EAElement#getVisibility()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_Visibility();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#isIsAbstract <em>Is Abstract</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see eaadapter.EAElement#isIsAbstract()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_IsAbstract();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#isIsActive <em>Is Active</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Active</em>'.
	 * @see eaadapter.EAElement#isIsActive()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_IsActive();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAElement#getConstraints
	 * <em>Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Constraints</em>'.
	 * @see eaadapter.EAElement#getConstraints()
	 * @see #getEAElement()
	 * @generated
	 */
	EReference getEAElement_Constraints();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAElement#getMetaType <em>Meta Type</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Meta Type</em>'.
	 * @see eaadapter.EAElement#getMetaType()
	 * @see #getEAElement()
	 * @generated
	 */
	EAttribute getEAElement_MetaType();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAMethod <em>EA Method</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Method</em>'.
	 * @see eaadapter.EAMethod
	 * @generated
	 */
	EClass getEAMethod();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getReturnType <em>Return Type</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Return Type</em>'.
	 * @see eaadapter.EAMethod#getReturnType()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_ReturnType();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getReturnIsArray <em>Return Is Array</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Return Is Array</em>'.
	 * @see eaadapter.EAMethod#getReturnIsArray()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_ReturnIsArray();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getCode <em>Code</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see eaadapter.EAMethod#getCode()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_Code();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getThrows <em>Throws</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Throws</em>'.
	 * @see eaadapter.EAMethod#getThrows()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_Throws();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getIsPure <em>Is Pure</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Pure</em>'.
	 * @see eaadapter.EAMethod#getIsPure()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_IsPure();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getIsRoot <em>Is Root</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Root</em>'.
	 * @see eaadapter.EAMethod#getIsRoot()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_IsRoot();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getIsLeaf <em>Is Leaf</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Leaf</em>'.
	 * @see eaadapter.EAMethod#getIsLeaf()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_IsLeaf();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getIsQuery <em>Is Query</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Query</em>'.
	 * @see eaadapter.EAMethod#getIsQuery()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_IsQuery();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getIsSynchronized <em>Is Synchronized</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Synchronized</em>'.
	 * @see eaadapter.EAMethod#getIsSynchronized()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_IsSynchronized();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getIsAbstract <em>Is Abstract</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see eaadapter.EAMethod#getIsAbstract()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_IsAbstract();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getConcurrency <em>Concurrency</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Concurrency</em>'.
	 * @see eaadapter.EAMethod#getConcurrency()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_Concurrency();

	/**
	 * Returns the meta object for the container reference '{@link eaadapter.EAMethod#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Element</em>'.
	 * @see eaadapter.EAMethod#getElement()
	 * @see #getEAMethod()
	 * @generated
	 */
	EReference getEAMethod_Element();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethod#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAMethod#getEaLink()
	 * @see #getEAMethod()
	 * @generated
	 */
	EAttribute getEAMethod_EaLink();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAMethod#getParameters
	 * <em>Parameters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see eaadapter.EAMethod#getParameters()
	 * @see #getEAMethod()
	 * @generated
	 */
	EReference getEAMethod_Parameters();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAMethod#getTaggedValues
	 * <em>Tagged Values</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Tagged Values</em>'.
	 * @see eaadapter.EAMethod#getTaggedValues()
	 * @see #getEAMethod()
	 * @generated
	 */
	EReference getEAMethod_TaggedValues();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAMethodTag <em>EA Method Tag</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Method Tag</em>'.
	 * @see eaadapter.EAMethodTag
	 * @generated
	 */
	EClass getEAMethodTag();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAMethodTag#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAMethodTag#getEaLink()
	 * @see #getEAMethodTag()
	 * @generated
	 */
	EAttribute getEAMethodTag_EaLink();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAPackage <em>EA Package</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Package</em>'.
	 * @see eaadapter.EAPackage
	 * @generated
	 */
	EClass getEAPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAPackage#getElements
	 * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see eaadapter.EAPackage#getElements()
	 * @see #getEAPackage()
	 * @generated
	 */
	EReference getEAPackage_Elements();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAPackage#getPackages
	 * <em>Packages</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Packages</em>'.
	 * @see eaadapter.EAPackage#getPackages()
	 * @see #getEAPackage()
	 * @generated
	 */
	EReference getEAPackage_Packages();

	/**
	 * Returns the meta object for the container reference '{@link eaadapter.EAPackage#getSuperPackage
	 * <em>Super Package</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Super Package</em>'.
	 * @see eaadapter.EAPackage#getSuperPackage()
	 * @see #getEAPackage()
	 * @generated
	 */
	EReference getEAPackage_SuperPackage();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAPackage#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAPackage#getEaLink()
	 * @see #getEAPackage()
	 * @generated
	 */
	EAttribute getEAPackage_EaLink();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAPackage#getStereotype <em>Stereotype</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Stereotype</em>'.
	 * @see eaadapter.EAPackage#getStereotype()
	 * @see #getEAPackage()
	 * @generated
	 */
	EAttribute getEAPackage_Stereotype();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAParameter <em>EA Parameter</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Parameter</em>'.
	 * @see eaadapter.EAParameter
	 * @generated
	 */
	EClass getEAParameter();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAParameter#getDefault <em>Default</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see eaadapter.EAParameter#getDefault()
	 * @see #getEAParameter()
	 * @generated
	 */
	EAttribute getEAParameter_Default();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAParameter#getPosition <em>Position</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see eaadapter.EAParameter#getPosition()
	 * @see #getEAParameter()
	 * @generated
	 */
	EAttribute getEAParameter_Position();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAParameter#getIsConst <em>Is Const</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Const</em>'.
	 * @see eaadapter.EAParameter#getIsConst()
	 * @see #getEAParameter()
	 * @generated
	 */
	EAttribute getEAParameter_IsConst();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAParameter#getKind <em>Kind</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see eaadapter.EAParameter#getKind()
	 * @see #getEAParameter()
	 * @generated
	 */
	EAttribute getEAParameter_Kind();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAParameter#getOperationID <em>Operation ID</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Operation ID</em>'.
	 * @see eaadapter.EAParameter#getOperationID()
	 * @see #getEAParameter()
	 * @generated
	 */
	EAttribute getEAParameter_OperationID();

	/**
	 * Returns the meta object for the container reference '{@link eaadapter.EAParameter#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Method</em>'.
	 * @see eaadapter.EAParameter#getMethod()
	 * @see #getEAParameter()
	 * @generated
	 */
	EReference getEAParameter_Method();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAParameter#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAParameter#getEaLink()
	 * @see #getEAParameter()
	 * @generated
	 */
	EAttribute getEAParameter_EaLink();

	/**
	 * Returns the meta object for class '{@link eaadapter.EARepository <em>EA Repository</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Repository</em>'.
	 * @see eaadapter.EARepository
	 * @generated
	 */
	EClass getEARepository();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EARepository#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EARepository#getEaLink()
	 * @see #getEARepository()
	 * @generated
	 */
	EAttribute getEARepository_EaLink();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EARepository#getFile <em>File</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>File</em>'.
	 * @see eaadapter.EARepository#getFile()
	 * @see #getEARepository()
	 * @generated
	 */
	EAttribute getEARepository_File();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EARepository#getModels
	 * <em>Models</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Models</em>'.
	 * @see eaadapter.EARepository#getModels()
	 * @see #getEARepository()
	 * @generated
	 */
	EReference getEARepository_Models();

	/**
	 * Returns the meta object for class '{@link eaadapter.EATaggedValue <em>EA Tagged Value</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Tagged Value</em>'.
	 * @see eaadapter.EATaggedValue
	 * @generated
	 */
	EClass getEATaggedValue();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EATaggedValue#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EATaggedValue#getEaLink()
	 * @see #getEATaggedValue()
	 * @generated
	 */
	EAttribute getEATaggedValue_EaLink();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAConnectorEnd <em>EA Connector End</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Connector End</em>'.
	 * @see eaadapter.EAConnectorEnd
	 * @generated
	 */
	EClass getEAConnectorEnd();

	/**
	 * Returns the meta object for the containment reference list '{@link eaadapter.EAConnectorEnd#getTaggedValues
	 * <em>Tagged Values</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Tagged Values</em>'.
	 * @see eaadapter.EAConnectorEnd#getTaggedValues()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EReference getEAConnectorEnd_TaggedValues();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getEnd <em>End</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see eaadapter.EAConnectorEnd#getEnd()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_End();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getCardinality <em>Cardinality</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Cardinality</em>'.
	 * @see eaadapter.EAConnectorEnd#getCardinality()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_Cardinality();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getVisibility <em>Visibility</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Visibility</em>'.
	 * @see eaadapter.EAConnectorEnd#getVisibility()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_Visibility();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getRole <em>Role</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Role</em>'.
	 * @see eaadapter.EAConnectorEnd#getRole()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_Role();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getRoleType <em>Role Type</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Role Type</em>'.
	 * @see eaadapter.EAConnectorEnd#getRoleType()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_RoleType();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getRoleNote <em>Role Note</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Role Note</em>'.
	 * @see eaadapter.EAConnectorEnd#getRoleNote()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_RoleNote();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getContainment <em>Containment</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Containment</em>'.
	 * @see eaadapter.EAConnectorEnd#getContainment()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_Containment();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getAggregation <em>Aggregation</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Aggregation</em>'.
	 * @see eaadapter.EAConnectorEnd#getAggregation()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_Aggregation();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getOrdering <em>Ordering</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ordering</em>'.
	 * @see eaadapter.EAConnectorEnd#getOrdering()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_Ordering();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getQualifier <em>Qualifier</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Qualifier</em>'.
	 * @see eaadapter.EAConnectorEnd#getQualifier()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_Qualifier();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Constraint</em>'.
	 * @see eaadapter.EAConnectorEnd#getConstraint()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_Constraint();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#isIsNavigable <em>Is Navigable</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Navigable</em>'.
	 * @see eaadapter.EAConnectorEnd#isIsNavigable()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_IsNavigable();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getIsChangable <em>Is Changable</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Changable</em>'.
	 * @see eaadapter.EAConnectorEnd#getIsChangable()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_IsChangable();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAConnectorEnd#getEaLink()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_EaLink();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorEnd#isDerived <em>Derived</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Derived</em>'.
	 * @see eaadapter.EAConnectorEnd#isDerived()
	 * @see #getEAConnectorEnd()
	 * @generated
	 */
	EAttribute getEAConnectorEnd_Derived();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAConnectorConstraint <em>EA Connector Constraint</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Connector Constraint</em>'.
	 * @see eaadapter.EAConnectorConstraint
	 * @generated
	 */
	EClass getEAConnectorConstraint();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorConstraint#getConnectorID
	 * <em>Connector ID</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Connector ID</em>'.
	 * @see eaadapter.EAConnectorConstraint#getConnectorID()
	 * @see #getEAConnectorConstraint()
	 * @generated
	 */
	EAttribute getEAConnectorConstraint_ConnectorID();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorConstraint#getType <em>Type</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eaadapter.EAConnectorConstraint#getType()
	 * @see #getEAConnectorConstraint()
	 * @generated
	 */
	EAttribute getEAConnectorConstraint_Type();

	/**
	 * Returns the meta object for the container reference '{@link eaadapter.EAConnectorConstraint#getConnector
	 * <em>Connector</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Connector</em>'.
	 * @see eaadapter.EAConnectorConstraint#getConnector()
	 * @see #getEAConnectorConstraint()
	 * @generated
	 */
	EReference getEAConnectorConstraint_Connector();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConnectorConstraint#getEaLink <em>Ea Link</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EAConnectorConstraint#getEaLink()
	 * @see #getEAConnectorConstraint()
	 * @generated
	 */
	EAttribute getEAConnectorConstraint_EaLink();

	/**
	 * Returns the meta object for class '{@link eaadapter.EARoleTag <em>EA Role Tag</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Role Tag</em>'.
	 * @see eaadapter.EARoleTag
	 * @generated
	 */
	EClass getEARoleTag();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EARoleTag#getBaseClass <em>Base Class</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Base Class</em>'.
	 * @see eaadapter.EARoleTag#getBaseClass()
	 * @see #getEARoleTag()
	 * @generated
	 */
	EAttribute getEARoleTag_BaseClass();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EARoleTag#getElementGUID <em>Element GUID</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Element GUID</em>'.
	 * @see eaadapter.EARoleTag#getElementGUID()
	 * @see #getEARoleTag()
	 * @generated
	 */
	EAttribute getEARoleTag_ElementGUID();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EARoleTag#getPropertyGUID <em>Property GUID</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Property GUID</em>'.
	 * @see eaadapter.EARoleTag#getPropertyGUID()
	 * @see #getEARoleTag()
	 * @generated
	 */
	EAttribute getEARoleTag_PropertyGUID();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EARoleTag#getTag <em>Tag</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see eaadapter.EARoleTag#getTag()
	 * @see #getEARoleTag()
	 * @generated
	 */
	EAttribute getEARoleTag_Tag();

	/**
	 * Returns the meta object for the container reference '{@link eaadapter.EARoleTag#getConnectorEnd
	 * <em>Connector End</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Connector End</em>'.
	 * @see eaadapter.EARoleTag#getConnectorEnd()
	 * @see #getEARoleTag()
	 * @generated
	 */
	EReference getEARoleTag_ConnectorEnd();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EARoleTag#getEaLink <em>Ea Link</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ea Link</em>'.
	 * @see eaadapter.EARoleTag#getEaLink()
	 * @see #getEARoleTag()
	 * @generated
	 */
	EAttribute getEARoleTag_EaLink();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EARoleTag#getValue <em>Value</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see eaadapter.EARoleTag#getValue()
	 * @see #getEARoleTag()
	 * @generated
	 */
	EAttribute getEARoleTag_Value();

	/**
	 * Returns the meta object for class '{@link eaadapter.EAConstraint <em>EA Constraint</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Constraint</em>'.
	 * @see eaadapter.EAConstraint
	 * @generated
	 */
	EClass getEAConstraint();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConstraint#getStatus <em>Status</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see eaadapter.EAConstraint#getStatus()
	 * @see #getEAConstraint()
	 * @generated
	 */
	EAttribute getEAConstraint_Status();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConstraint#getType <em>Type</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eaadapter.EAConstraint#getType()
	 * @see #getEAConstraint()
	 * @generated
	 */
	EAttribute getEAConstraint_Type();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.EAConstraint#getWeight <em>Weight</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see eaadapter.EAConstraint#getWeight()
	 * @see #getEAConstraint()
	 * @generated
	 */
	EAttribute getEAConstraint_Weight();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EaadapterFactory getEaadapterFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAAttributeImpl <em>EA Attribute</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAAttributeImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAAttribute()
		 * @generated
		 */
		EClass EA_ATTRIBUTE = eINSTANCE.getEAAttribute();

		/**
		 * The meta object literal for the '<em><b>Containment</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__CONTAINMENT = eINSTANCE.getEAAttribute_Containment();

		/**
		 * The meta object literal for the '<em><b>Collection</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__COLLECTION = eINSTANCE.getEAAttribute_Collection();

		/**
		 * The meta object literal for the '<em><b>Ordered</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__ORDERED = eINSTANCE.getEAAttribute_Ordered();

		/**
		 * The meta object literal for the '<em><b>Allow Duplicates</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__ALLOW_DUPLICATES = eINSTANCE.getEAAttribute_AllowDuplicates();

		/**
		 * The meta object literal for the '<em><b>Derived</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__DERIVED = eINSTANCE.getEAAttribute_Derived();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__CONTAINER = eINSTANCE.getEAAttribute_Container();

		/**
		 * The meta object literal for the '<em><b>Scale</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__SCALE = eINSTANCE.getEAAttribute_Scale();

		/**
		 * The meta object literal for the '<em><b>Precision</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__PRECISION = eINSTANCE.getEAAttribute_Precision();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__LENGTH = eINSTANCE.getEAAttribute_Length();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__LOWER_BOUND = eINSTANCE.getEAAttribute_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__UPPER_BOUND = eINSTANCE.getEAAttribute_UpperBound();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__DEFAULT = eINSTANCE.getEAAttribute_Default();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' container reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ATTRIBUTE__ELEMENT = eINSTANCE.getEAAttribute_Element();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__EA_LINK = eINSTANCE.getEAAttribute_EaLink();

		/**
		 * The meta object literal for the '<em><b>Tagged Values</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ATTRIBUTE__TAGGED_VALUES = eINSTANCE.getEAAttribute_TaggedValues();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE__VISIBILITY = eINSTANCE.getEAAttribute_Visibility();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAAttributeTagImpl <em>EA Attribute Tag</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAAttributeTagImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAAttributeTag()
		 * @generated
		 */
		EClass EA_ATTRIBUTE_TAG = eINSTANCE.getEAAttributeTag();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ATTRIBUTE_TAG__EA_LINK = eINSTANCE.getEAAttributeTag_EaLink();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAConnectorImpl <em>EA Connector</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAConnectorImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAConnector()
		 * @generated
		 */
		EClass EA_CONNECTOR = eINSTANCE.getEAConnector();

		/**
		 * The meta object literal for the '<em><b>Connector ID</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__CONNECTOR_ID = eINSTANCE.getEAConnector_ConnectorID();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__DIRECTION = eINSTANCE.getEAConnector_Direction();

		/**
		 * The meta object literal for the '<em><b>Subtype</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__SUBTYPE = eINSTANCE.getEAConnector_Subtype();

		/**
		 * The meta object literal for the '<em><b>Is Leaf</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__IS_LEAF = eINSTANCE.getEAConnector_IsLeaf();

		/**
		 * The meta object literal for the '<em><b>Is Root</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__IS_ROOT = eINSTANCE.getEAConnector_IsRoot();

		/**
		 * The meta object literal for the '<em><b>Is Spec</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__IS_SPEC = eINSTANCE.getEAConnector_IsSpec();

		/**
		 * The meta object literal for the '<em><b>Virtual Inheritance</b></em>' attribute feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__VIRTUAL_INHERITANCE = eINSTANCE.getEAConnector_VirtualInheritance();

		/**
		 * The meta object literal for the '<em><b>Transition Event</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__TRANSITION_EVENT = eINSTANCE.getEAConnector_TransitionEvent();

		/**
		 * The meta object literal for the '<em><b>Transition Action</b></em>' attribute feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__TRANSITION_ACTION = eINSTANCE.getEAConnector_TransitionAction();

		/**
		 * The meta object literal for the '<em><b>Transition Guard</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__TRANSITION_GUARD = eINSTANCE.getEAConnector_TransitionGuard();

		/**
		 * The meta object literal for the '<em><b>Route Style</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__ROUTE_STYLE = eINSTANCE.getEAConnector_RouteStyle();

		/**
		 * The meta object literal for the '<em><b>Sequence No</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__SEQUENCE_NO = eINSTANCE.getEAConnector_SequenceNo();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__EA_LINK = eINSTANCE.getEAConnector_EaLink();

		/**
		 * The meta object literal for the '<em><b>Tagged Values</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_CONNECTOR__TAGGED_VALUES = eINSTANCE.getEAConnector_TaggedValues();

		/**
		 * The meta object literal for the '<em><b>Client</b></em>' container reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_CONNECTOR__CLIENT = eINSTANCE.getEAConnector_Client();

		/**
		 * The meta object literal for the '<em><b>Supplier</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_CONNECTOR__SUPPLIER = eINSTANCE.getEAConnector_Supplier();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_CONNECTOR__CONSTRAINTS = eINSTANCE.getEAConnector_Constraints();

		/**
		 * The meta object literal for the '<em><b>Client End</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_CONNECTOR__CLIENT_END = eINSTANCE.getEAConnector_ClientEnd();

		/**
		 * The meta object literal for the '<em><b>Client ID</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__CLIENT_ID = eINSTANCE.getEAConnector_ClientID();

		/**
		 * The meta object literal for the '<em><b>Supplier ID</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__SUPPLIER_ID = eINSTANCE.getEAConnector_SupplierID();

		/**
		 * The meta object literal for the '<em><b>Event Flags</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__EVENT_FLAGS = eINSTANCE.getEAConnector_EventFlags();

		/**
		 * The meta object literal for the '<em><b>Style Ex</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__STYLE_EX = eINSTANCE.getEAConnector_StyleEx();

		/**
		 * The meta object literal for the '<em><b>Supplier End</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_CONNECTOR__SUPPLIER_END = eINSTANCE.getEAConnector_SupplierEnd();

		/**
		 * The meta object literal for the '<em><b>Derived</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR__DERIVED = eINSTANCE.getEAConnector_Derived();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAConnectorTagImpl <em>EA Connector Tag</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAConnectorTagImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAConnectorTag()
		 * @generated
		 */
		EClass EA_CONNECTOR_TAG = eINSTANCE.getEAConnectorTag();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_TAG__EA_LINK = eINSTANCE.getEAConnectorTag_EaLink();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAElementImpl <em>EA Element</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAElementImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAElement()
		 * @generated
		 */
		EClass EA_ELEMENT = eINSTANCE.getEAElement();

		/**
		 * The meta object literal for the '<em><b>Classifier Name</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__CLASSIFIER_NAME = eINSTANCE.getEAElement_ClassifierName();

		/**
		 * The meta object literal for the '<em><b>Classifier Type</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__CLASSIFIER_TYPE = eINSTANCE.getEAElement_ClassifierType();

		/**
		 * The meta object literal for the '<em><b>Complexity</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__COMPLEXITY = eINSTANCE.getEAElement_Complexity();

		/**
		 * The meta object literal for the '<em><b>Difficulty</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__DIFFICULTY = eINSTANCE.getEAElement_Difficulty();

		/**
		 * The meta object literal for the '<em><b>Extension Points</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__EXTENSION_POINTS = eINSTANCE.getEAElement_ExtensionPoints();

		/**
		 * The meta object literal for the '<em><b>Genlinks</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__GENLINKS = eINSTANCE.getEAElement_Genlinks();

		/**
		 * The meta object literal for the '<em><b>Genfile</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__GENFILE = eINSTANCE.getEAElement_Genfile();

		/**
		 * The meta object literal for the '<em><b>Gentype</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__GENTYPE = eINSTANCE.getEAElement_Gentype();

		/**
		 * The meta object literal for the '<em><b>Multiplicity</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__MULTIPLICITY = eINSTANCE.getEAElement_Multiplicity();

		/**
		 * The meta object literal for the '<em><b>Phase</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__PHASE = eINSTANCE.getEAElement_Phase();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__PRIORITY = eINSTANCE.getEAElement_Priority();

		/**
		 * The meta object literal for the '<em><b>Property Type</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__PROPERTY_TYPE = eINSTANCE.getEAElement_PropertyType();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__STATUS = eINSTANCE.getEAElement_Status();

		/**
		 * The meta object literal for the '<em><b>Subtype</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__SUBTYPE = eINSTANCE.getEAElement_Subtype();

		/**
		 * The meta object literal for the '<em><b>Tablespace</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__TABLESPACE = eINSTANCE.getEAElement_Tablespace();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__TAG = eINSTANCE.getEAElement_Tag();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' container reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ELEMENT__ELEMENT = eINSTANCE.getEAElement_Element();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' container reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ELEMENT__PACKAGE = eINSTANCE.getEAElement_Package();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ELEMENT__ATTRIBUTES = eINSTANCE.getEAElement_Attributes();

		/**
		 * The meta object literal for the '<em><b>Connectors</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ELEMENT__CONNECTORS = eINSTANCE.getEAElement_Connectors();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ELEMENT__ELEMENTS = eINSTANCE.getEAElement_Elements();

		/**
		 * The meta object literal for the '<em><b>Methods</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ELEMENT__METHODS = eINSTANCE.getEAElement_Methods();

		/**
		 * The meta object literal for the '<em><b>Tagged Values</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ELEMENT__TAGGED_VALUES = eINSTANCE.getEAElement_TaggedValues();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__EA_LINK = eINSTANCE.getEAElement_EaLink();

		/**
		 * The meta object literal for the '<em><b>Parent ID</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__PARENT_ID = eINSTANCE.getEAElement_ParentID();

		/**
		 * The meta object literal for the '<em><b>Package ID</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__PACKAGE_ID = eINSTANCE.getEAElement_PackageID();

		/**
		 * The meta object literal for the '<em><b>All Connectors</b></em>' reference list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ELEMENT__ALL_CONNECTORS = eINSTANCE.getEAElement_AllConnectors();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__VISIBILITY = eINSTANCE.getEAElement_Visibility();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__IS_ABSTRACT = eINSTANCE.getEAElement_IsAbstract();

		/**
		 * The meta object literal for the '<em><b>Is Active</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__IS_ACTIVE = eINSTANCE.getEAElement_IsActive();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ELEMENT__CONSTRAINTS = eINSTANCE.getEAElement_Constraints();

		/**
		 * The meta object literal for the '<em><b>Meta Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ELEMENT__META_TYPE = eINSTANCE.getEAElement_MetaType();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAMethodImpl <em>EA Method</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAMethodImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAMethod()
		 * @generated
		 */
		EClass EA_METHOD = eINSTANCE.getEAMethod();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__RETURN_TYPE = eINSTANCE.getEAMethod_ReturnType();

		/**
		 * The meta object literal for the '<em><b>Return Is Array</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__RETURN_IS_ARRAY = eINSTANCE.getEAMethod_ReturnIsArray();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__CODE = eINSTANCE.getEAMethod_Code();

		/**
		 * The meta object literal for the '<em><b>Throws</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__THROWS = eINSTANCE.getEAMethod_Throws();

		/**
		 * The meta object literal for the '<em><b>Is Pure</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__IS_PURE = eINSTANCE.getEAMethod_IsPure();

		/**
		 * The meta object literal for the '<em><b>Is Root</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__IS_ROOT = eINSTANCE.getEAMethod_IsRoot();

		/**
		 * The meta object literal for the '<em><b>Is Leaf</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__IS_LEAF = eINSTANCE.getEAMethod_IsLeaf();

		/**
		 * The meta object literal for the '<em><b>Is Query</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__IS_QUERY = eINSTANCE.getEAMethod_IsQuery();

		/**
		 * The meta object literal for the '<em><b>Is Synchronized</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__IS_SYNCHRONIZED = eINSTANCE.getEAMethod_IsSynchronized();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__IS_ABSTRACT = eINSTANCE.getEAMethod_IsAbstract();

		/**
		 * The meta object literal for the '<em><b>Concurrency</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__CONCURRENCY = eINSTANCE.getEAMethod_Concurrency();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' container reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_METHOD__ELEMENT = eINSTANCE.getEAMethod_Element();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD__EA_LINK = eINSTANCE.getEAMethod_EaLink();

		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_METHOD__PARAMETERS = eINSTANCE.getEAMethod_Parameters();

		/**
		 * The meta object literal for the '<em><b>Tagged Values</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_METHOD__TAGGED_VALUES = eINSTANCE.getEAMethod_TaggedValues();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAMethodTagImpl <em>EA Method Tag</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAMethodTagImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAMethodTag()
		 * @generated
		 */
		EClass EA_METHOD_TAG = eINSTANCE.getEAMethodTag();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_METHOD_TAG__EA_LINK = eINSTANCE.getEAMethodTag_EaLink();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAPackageImpl <em>EA Package</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAPackageImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAPackage()
		 * @generated
		 */
		EClass EA_PACKAGE = eINSTANCE.getEAPackage();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_PACKAGE__ELEMENTS = eINSTANCE.getEAPackage_Elements();

		/**
		 * The meta object literal for the '<em><b>Packages</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_PACKAGE__PACKAGES = eINSTANCE.getEAPackage_Packages();

		/**
		 * The meta object literal for the '<em><b>Super Package</b></em>' container reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_PACKAGE__SUPER_PACKAGE = eINSTANCE.getEAPackage_SuperPackage();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_PACKAGE__EA_LINK = eINSTANCE.getEAPackage_EaLink();

		/**
		 * The meta object literal for the '<em><b>Stereotype</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_PACKAGE__STEREOTYPE = eINSTANCE.getEAPackage_Stereotype();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAParameterImpl <em>EA Parameter</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAParameterImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAParameter()
		 * @generated
		 */
		EClass EA_PARAMETER = eINSTANCE.getEAParameter();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_PARAMETER__DEFAULT = eINSTANCE.getEAParameter_Default();

		/**
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_PARAMETER__POSITION = eINSTANCE.getEAParameter_Position();

		/**
		 * The meta object literal for the '<em><b>Is Const</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_PARAMETER__IS_CONST = eINSTANCE.getEAParameter_IsConst();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_PARAMETER__KIND = eINSTANCE.getEAParameter_Kind();

		/**
		 * The meta object literal for the '<em><b>Operation ID</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_PARAMETER__OPERATION_ID = eINSTANCE.getEAParameter_OperationID();

		/**
		 * The meta object literal for the '<em><b>Method</b></em>' container reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_PARAMETER__METHOD = eINSTANCE.getEAParameter_Method();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_PARAMETER__EA_LINK = eINSTANCE.getEAParameter_EaLink();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EARepositoryImpl <em>EA Repository</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EARepositoryImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEARepository()
		 * @generated
		 */
		EClass EA_REPOSITORY = eINSTANCE.getEARepository();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_REPOSITORY__EA_LINK = eINSTANCE.getEARepository_EaLink();

		/**
		 * The meta object literal for the '<em><b>File</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_REPOSITORY__FILE = eINSTANCE.getEARepository_File();

		/**
		 * The meta object literal for the '<em><b>Models</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_REPOSITORY__MODELS = eINSTANCE.getEARepository_Models();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EATaggedValueImpl <em>EA Tagged Value</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EATaggedValueImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEATaggedValue()
		 * @generated
		 */
		EClass EA_TAGGED_VALUE = eINSTANCE.getEATaggedValue();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_TAGGED_VALUE__EA_LINK = eINSTANCE.getEATaggedValue_EaLink();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAConnectorEndImpl <em>EA Connector End</em>}' class.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAConnectorEndImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAConnectorEnd()
		 * @generated
		 */
		EClass EA_CONNECTOR_END = eINSTANCE.getEAConnectorEnd();

		/**
		 * The meta object literal for the '<em><b>Tagged Values</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_CONNECTOR_END__TAGGED_VALUES = eINSTANCE.getEAConnectorEnd_TaggedValues();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__END = eINSTANCE.getEAConnectorEnd_End();

		/**
		 * The meta object literal for the '<em><b>Cardinality</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__CARDINALITY = eINSTANCE.getEAConnectorEnd_Cardinality();

		/**
		 * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__VISIBILITY = eINSTANCE.getEAConnectorEnd_Visibility();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__ROLE = eINSTANCE.getEAConnectorEnd_Role();

		/**
		 * The meta object literal for the '<em><b>Role Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__ROLE_TYPE = eINSTANCE.getEAConnectorEnd_RoleType();

		/**
		 * The meta object literal for the '<em><b>Role Note</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__ROLE_NOTE = eINSTANCE.getEAConnectorEnd_RoleNote();

		/**
		 * The meta object literal for the '<em><b>Containment</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__CONTAINMENT = eINSTANCE.getEAConnectorEnd_Containment();

		/**
		 * The meta object literal for the '<em><b>Aggregation</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__AGGREGATION = eINSTANCE.getEAConnectorEnd_Aggregation();

		/**
		 * The meta object literal for the '<em><b>Ordering</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__ORDERING = eINSTANCE.getEAConnectorEnd_Ordering();

		/**
		 * The meta object literal for the '<em><b>Qualifier</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__QUALIFIER = eINSTANCE.getEAConnectorEnd_Qualifier();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__CONSTRAINT = eINSTANCE.getEAConnectorEnd_Constraint();

		/**
		 * The meta object literal for the '<em><b>Is Navigable</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__IS_NAVIGABLE = eINSTANCE.getEAConnectorEnd_IsNavigable();

		/**
		 * The meta object literal for the '<em><b>Is Changable</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__IS_CHANGABLE = eINSTANCE.getEAConnectorEnd_IsChangable();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__EA_LINK = eINSTANCE.getEAConnectorEnd_EaLink();

		/**
		 * The meta object literal for the '<em><b>Derived</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_END__DERIVED = eINSTANCE.getEAConnectorEnd_Derived();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAConnectorConstraintImpl
		 * <em>EA Connector Constraint</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAConnectorConstraintImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAConnectorConstraint()
		 * @generated
		 */
		EClass EA_CONNECTOR_CONSTRAINT = eINSTANCE.getEAConnectorConstraint();

		/**
		 * The meta object literal for the '<em><b>Connector ID</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_CONSTRAINT__CONNECTOR_ID = eINSTANCE.getEAConnectorConstraint_ConnectorID();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_CONSTRAINT__TYPE = eINSTANCE.getEAConnectorConstraint_Type();

		/**
		 * The meta object literal for the '<em><b>Connector</b></em>' container reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_CONNECTOR_CONSTRAINT__CONNECTOR = eINSTANCE.getEAConnectorConstraint_Connector();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONNECTOR_CONSTRAINT__EA_LINK = eINSTANCE.getEAConnectorConstraint_EaLink();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EARoleTagImpl <em>EA Role Tag</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EARoleTagImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEARoleTag()
		 * @generated
		 */
		EClass EA_ROLE_TAG = eINSTANCE.getEARoleTag();

		/**
		 * The meta object literal for the '<em><b>Base Class</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ROLE_TAG__BASE_CLASS = eINSTANCE.getEARoleTag_BaseClass();

		/**
		 * The meta object literal for the '<em><b>Element GUID</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ROLE_TAG__ELEMENT_GUID = eINSTANCE.getEARoleTag_ElementGUID();

		/**
		 * The meta object literal for the '<em><b>Property GUID</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ROLE_TAG__PROPERTY_GUID = eINSTANCE.getEARoleTag_PropertyGUID();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ROLE_TAG__TAG = eINSTANCE.getEARoleTag_Tag();

		/**
		 * The meta object literal for the '<em><b>Connector End</b></em>' container reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference EA_ROLE_TAG__CONNECTOR_END = eINSTANCE.getEARoleTag_ConnectorEnd();

		/**
		 * The meta object literal for the '<em><b>Ea Link</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ROLE_TAG__EA_LINK = eINSTANCE.getEARoleTag_EaLink();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ROLE_TAG__VALUE = eINSTANCE.getEARoleTag_Value();

		/**
		 * The meta object literal for the '{@link eaadapter.impl.EAConstraintImpl <em>EA Constraint</em>}' class. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.impl.EAConstraintImpl
		 * @see eaadapter.impl.EaadapterPackageImpl#getEAConstraint()
		 * @generated
		 */
		EClass EA_CONSTRAINT = eINSTANCE.getEAConstraint();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONSTRAINT__STATUS = eINSTANCE.getEAConstraint_Status();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONSTRAINT__TYPE = eINSTANCE.getEAConstraint_Type();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CONSTRAINT__WEIGHT = eINSTANCE.getEAConstraint_Weight();

	}

} // EaadapterPackage
