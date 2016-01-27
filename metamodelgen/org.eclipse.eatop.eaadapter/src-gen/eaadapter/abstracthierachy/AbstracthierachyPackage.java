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
package eaadapter.abstracthierachy;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see eaadapter.abstracthierachy.AbstracthierachyFactory
 * @model kind="package"
 * @generated
 */
public interface AbstracthierachyPackage extends EPackage {
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
	String eNAME = "abstracthierachy";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://eaadapter.abstracthierachy";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "eaadapter.abstracthierachy";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	AbstracthierachyPackage eINSTANCE = eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl.init();

	/**
	 * The meta object id for the '{@link eaadapter.abstracthierachy.EANamedElement <em>EA Named Element</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.abstracthierachy.EANamedElement
	 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEANamedElement()
	 * @generated
	 */
	int EA_NAMED_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_NAMED_ELEMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_NAMED_ELEMENT__NOTES = 1;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_NAMED_ELEMENT__GUID = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_NAMED_ELEMENT__ID = 3;

	/**
	 * The number of structural features of the '<em>EA Named Element</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_NAMED_ELEMENT_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link eaadapter.abstracthierachy.EAVersiondElement <em>EA Versiond Element</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.abstracthierachy.EAVersiondElement
	 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAVersiondElement()
	 * @generated
	 */
	int EA_VERSIOND_ELEMENT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_VERSIOND_ELEMENT__NAME = EA_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_VERSIOND_ELEMENT__NOTES = EA_NAMED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_VERSIOND_ELEMENT__GUID = EA_NAMED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_VERSIOND_ELEMENT__ID = EA_NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_VERSIOND_ELEMENT__VERSION = EA_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EA Versiond Element</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_VERSIOND_ELEMENT_FEATURE_COUNT = EA_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eaadapter.abstracthierachy.EAAbstractPackage <em>EA Abstract Package</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.abstracthierachy.EAAbstractPackage
	 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAAbstractPackage()
	 * @generated
	 */
	int EA_ABSTRACT_PACKAGE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ABSTRACT_PACKAGE__NAME = EA_VERSIOND_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ABSTRACT_PACKAGE__NOTES = EA_VERSIOND_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ABSTRACT_PACKAGE__GUID = EA_VERSIOND_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ABSTRACT_PACKAGE__ID = EA_VERSIOND_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ABSTRACT_PACKAGE__VERSION = EA_VERSIOND_ELEMENT__VERSION;

	/**
	 * The feature id for the '<em><b>Code Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ABSTRACT_PACKAGE__CODE_PATH = EA_VERSIOND_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Flags</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ABSTRACT_PACKAGE__FLAGS = EA_VERSIOND_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Model</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ABSTRACT_PACKAGE__IS_MODEL = EA_VERSIOND_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>EA Abstract Package</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_ABSTRACT_PACKAGE_FEATURE_COUNT = EA_VERSIOND_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link eaadapter.abstracthierachy.EAStereotypedElement
	 * <em>EA Stereotyped Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.abstracthierachy.EAStereotypedElement
	 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAStereotypedElement()
	 * @generated
	 */
	int EA_STEREOTYPED_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_STEREOTYPED_ELEMENT__NAME = EA_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_STEREOTYPED_ELEMENT__NOTES = EA_NAMED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_STEREOTYPED_ELEMENT__GUID = EA_NAMED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_STEREOTYPED_ELEMENT__ID = EA_NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_STEREOTYPED_ELEMENT__STEREOTYPE = EA_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Stereotype Ex</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_STEREOTYPED_ELEMENT__STEREOTYPE_EX = EA_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>EA Stereotyped Element</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_STEREOTYPED_ELEMENT_FEATURE_COUNT = EA_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eaadapter.abstracthierachy.EATypedElement <em>EA Typed Element</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.abstracthierachy.EATypedElement
	 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEATypedElement()
	 * @generated
	 */
	int EA_TYPED_ELEMENT = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TYPED_ELEMENT__NAME = EA_STEREOTYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TYPED_ELEMENT__NOTES = EA_STEREOTYPED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TYPED_ELEMENT__GUID = EA_STEREOTYPED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TYPED_ELEMENT__ID = EA_STEREOTYPED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TYPED_ELEMENT__STEREOTYPE = EA_STEREOTYPED_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Stereotype Ex</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TYPED_ELEMENT__STEREOTYPE_EX = EA_STEREOTYPED_ELEMENT__STEREOTYPE_EX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TYPED_ELEMENT__TYPE = EA_STEREOTYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EA Typed Element</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TYPED_ELEMENT_FEATURE_COUNT = EA_STEREOTYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eaadapter.abstracthierachy.EAClassifierIDLong <em>EA Classifier ID Long</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.abstracthierachy.EAClassifierIDLong
	 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAClassifierIDLong()
	 * @generated
	 */
	int EA_CLASSIFIER_ID_LONG = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CLASSIFIER_ID_LONG__NAME = EA_TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CLASSIFIER_ID_LONG__NOTES = EA_TYPED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CLASSIFIER_ID_LONG__GUID = EA_TYPED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CLASSIFIER_ID_LONG__ID = EA_TYPED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CLASSIFIER_ID_LONG__STEREOTYPE = EA_TYPED_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Stereotype Ex</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CLASSIFIER_ID_LONG__STEREOTYPE_EX = EA_TYPED_ELEMENT__STEREOTYPE_EX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CLASSIFIER_ID_LONG__TYPE = EA_TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Classifier ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CLASSIFIER_ID_LONG__CLASSIFIER_ID = EA_TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EA Classifier ID Long</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_CLASSIFIER_ID_LONG_FEATURE_COUNT = EA_TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link eaadapter.abstracthierachy.EAModifiableElement <em>EA Modifiable Element</em>}
	 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.abstracthierachy.EAModifiableElement
	 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAModifiableElement()
	 * @generated
	 */
	int EA_MODIFIABLE_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_MODIFIABLE_ELEMENT__NAME = EA_TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_MODIFIABLE_ELEMENT__NOTES = EA_TYPED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_MODIFIABLE_ELEMENT__GUID = EA_TYPED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_MODIFIABLE_ELEMENT__ID = EA_TYPED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_MODIFIABLE_ELEMENT__STEREOTYPE = EA_TYPED_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Stereotype Ex</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_MODIFIABLE_ELEMENT__STEREOTYPE_EX = EA_TYPED_ELEMENT__STEREOTYPE_EX;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_MODIFIABLE_ELEMENT__TYPE = EA_TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Is Const</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_MODIFIABLE_ELEMENT__IS_CONST = EA_TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Static</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_MODIFIABLE_ELEMENT__IS_STATIC = EA_TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>EA Modifiable Element</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_MODIFIABLE_ELEMENT_FEATURE_COUNT = EA_TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eaadapter.abstracthierachy.EAOwnedElement <em>EA Owned Element</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.abstracthierachy.EAOwnedElement
	 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAOwnedElement()
	 * @generated
	 */
	int EA_OWNED_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_OWNED_ELEMENT__NAME = EA_VERSIOND_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_OWNED_ELEMENT__NOTES = EA_VERSIOND_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_OWNED_ELEMENT__GUID = EA_VERSIOND_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_OWNED_ELEMENT__ID = EA_VERSIOND_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_OWNED_ELEMENT__VERSION = EA_VERSIOND_ELEMENT__VERSION;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_OWNED_ELEMENT__AUTHOR = EA_VERSIOND_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Locked</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_OWNED_ELEMENT__IS_LOCKED = EA_VERSIOND_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>EA Owned Element</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_OWNED_ELEMENT_FEATURE_COUNT = EA_VERSIOND_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link eaadapter.abstracthierachy.EATaggedElement <em>EA Tagged Element</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.abstracthierachy.EATaggedElement
	 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEATaggedElement()
	 * @generated
	 */
	int EA_TAGGED_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_ELEMENT__NAME = EA_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_ELEMENT__NOTES = EA_NAMED_ELEMENT__NOTES;

	/**
	 * The feature id for the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_ELEMENT__GUID = EA_NAMED_ELEMENT__GUID;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_ELEMENT__ID = EA_NAMED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_ELEMENT__VALUE = EA_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EA Tagged Element</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int EA_TAGGED_ELEMENT_FEATURE_COUNT = EA_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * Returns the meta object for class '{@link eaadapter.abstracthierachy.EAAbstractPackage
	 * <em>EA Abstract Package</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Abstract Package</em>'.
	 * @see eaadapter.abstracthierachy.EAAbstractPackage
	 * @generated
	 */
	EClass getEAAbstractPackage();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EAAbstractPackage#getCodePath
	 * <em>Code Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Code Path</em>'.
	 * @see eaadapter.abstracthierachy.EAAbstractPackage#getCodePath()
	 * @see #getEAAbstractPackage()
	 * @generated
	 */
	EAttribute getEAAbstractPackage_CodePath();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EAAbstractPackage#getFlags
	 * <em>Flags</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Flags</em>'.
	 * @see eaadapter.abstracthierachy.EAAbstractPackage#getFlags()
	 * @see #getEAAbstractPackage()
	 * @generated
	 */
	EAttribute getEAAbstractPackage_Flags();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EAAbstractPackage#getIsModel
	 * <em>Is Model</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Model</em>'.
	 * @see eaadapter.abstracthierachy.EAAbstractPackage#getIsModel()
	 * @see #getEAAbstractPackage()
	 * @generated
	 */
	EAttribute getEAAbstractPackage_IsModel();

	/**
	 * Returns the meta object for class '{@link eaadapter.abstracthierachy.EAClassifierIDLong
	 * <em>EA Classifier ID Long</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Classifier ID Long</em>'.
	 * @see eaadapter.abstracthierachy.EAClassifierIDLong
	 * @generated
	 */
	EClass getEAClassifierIDLong();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EAClassifierIDLong#getClassifierID
	 * <em>Classifier ID</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Classifier ID</em>'.
	 * @see eaadapter.abstracthierachy.EAClassifierIDLong#getClassifierID()
	 * @see #getEAClassifierIDLong()
	 * @generated
	 */
	EAttribute getEAClassifierIDLong_ClassifierID();

	/**
	 * Returns the meta object for class '{@link eaadapter.abstracthierachy.EAModifiableElement
	 * <em>EA Modifiable Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Modifiable Element</em>'.
	 * @see eaadapter.abstracthierachy.EAModifiableElement
	 * @generated
	 */
	EClass getEAModifiableElement();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EAModifiableElement#getIsConst
	 * <em>Is Const</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Const</em>'.
	 * @see eaadapter.abstracthierachy.EAModifiableElement#getIsConst()
	 * @see #getEAModifiableElement()
	 * @generated
	 */
	EAttribute getEAModifiableElement_IsConst();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EAModifiableElement#getIsStatic
	 * <em>Is Static</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Static</em>'.
	 * @see eaadapter.abstracthierachy.EAModifiableElement#getIsStatic()
	 * @see #getEAModifiableElement()
	 * @generated
	 */
	EAttribute getEAModifiableElement_IsStatic();

	/**
	 * Returns the meta object for class '{@link eaadapter.abstracthierachy.EANamedElement <em>EA Named Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Named Element</em>'.
	 * @see eaadapter.abstracthierachy.EANamedElement
	 * @generated
	 */
	EClass getEANamedElement();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EANamedElement#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eaadapter.abstracthierachy.EANamedElement#getName()
	 * @see #getEANamedElement()
	 * @generated
	 */
	EAttribute getEANamedElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EANamedElement#getNotes
	 * <em>Notes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Notes</em>'.
	 * @see eaadapter.abstracthierachy.EANamedElement#getNotes()
	 * @see #getEANamedElement()
	 * @generated
	 */
	EAttribute getEANamedElement_Notes();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EANamedElement#getGuid
	 * <em>Guid</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Guid</em>'.
	 * @see eaadapter.abstracthierachy.EANamedElement#getGuid()
	 * @see #getEANamedElement()
	 * @generated
	 */
	EAttribute getEANamedElement_Guid();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EANamedElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see eaadapter.abstracthierachy.EANamedElement#getId()
	 * @see #getEANamedElement()
	 * @generated
	 */
	EAttribute getEANamedElement_Id();

	/**
	 * Returns the meta object for class '{@link eaadapter.abstracthierachy.EAOwnedElement <em>EA Owned Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Owned Element</em>'.
	 * @see eaadapter.abstracthierachy.EAOwnedElement
	 * @generated
	 */
	EClass getEAOwnedElement();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EAOwnedElement#getAuthor
	 * <em>Author</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see eaadapter.abstracthierachy.EAOwnedElement#getAuthor()
	 * @see #getEAOwnedElement()
	 * @generated
	 */
	EAttribute getEAOwnedElement_Author();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EAOwnedElement#getIsLocked
	 * <em>Is Locked</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Is Locked</em>'.
	 * @see eaadapter.abstracthierachy.EAOwnedElement#getIsLocked()
	 * @see #getEAOwnedElement()
	 * @generated
	 */
	EAttribute getEAOwnedElement_IsLocked();

	/**
	 * Returns the meta object for class '{@link eaadapter.abstracthierachy.EAStereotypedElement
	 * <em>EA Stereotyped Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Stereotyped Element</em>'.
	 * @see eaadapter.abstracthierachy.EAStereotypedElement
	 * @generated
	 */
	EClass getEAStereotypedElement();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EAStereotypedElement#getStereotype
	 * <em>Stereotype</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Stereotype</em>'.
	 * @see eaadapter.abstracthierachy.EAStereotypedElement#getStereotype()
	 * @see #getEAStereotypedElement()
	 * @generated
	 */
	EAttribute getEAStereotypedElement_Stereotype();

	/**
	 * Returns the meta object for the attribute '
	 * {@link eaadapter.abstracthierachy.EAStereotypedElement#getStereotypeEx <em>Stereotype Ex</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Stereotype Ex</em>'.
	 * @see eaadapter.abstracthierachy.EAStereotypedElement#getStereotypeEx()
	 * @see #getEAStereotypedElement()
	 * @generated
	 */
	EAttribute getEAStereotypedElement_StereotypeEx();

	/**
	 * Returns the meta object for class '{@link eaadapter.abstracthierachy.EATaggedElement <em>EA Tagged Element</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Tagged Element</em>'.
	 * @see eaadapter.abstracthierachy.EATaggedElement
	 * @generated
	 */
	EClass getEATaggedElement();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EATaggedElement#getValue
	 * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see eaadapter.abstracthierachy.EATaggedElement#getValue()
	 * @see #getEATaggedElement()
	 * @generated
	 */
	EAttribute getEATaggedElement_Value();

	/**
	 * Returns the meta object for class '{@link eaadapter.abstracthierachy.EATypedElement <em>EA Typed Element</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Typed Element</em>'.
	 * @see eaadapter.abstracthierachy.EATypedElement
	 * @generated
	 */
	EClass getEATypedElement();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EATypedElement#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eaadapter.abstracthierachy.EATypedElement#getType()
	 * @see #getEATypedElement()
	 * @generated
	 */
	EAttribute getEATypedElement_Type();

	/**
	 * Returns the meta object for class '{@link eaadapter.abstracthierachy.EAVersiondElement
	 * <em>EA Versiond Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>EA Versiond Element</em>'.
	 * @see eaadapter.abstracthierachy.EAVersiondElement
	 * @generated
	 */
	EClass getEAVersiondElement();

	/**
	 * Returns the meta object for the attribute '{@link eaadapter.abstracthierachy.EAVersiondElement#getVersion
	 * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see eaadapter.abstracthierachy.EAVersiondElement#getVersion()
	 * @see #getEAVersiondElement()
	 * @generated
	 */
	EAttribute getEAVersiondElement_Version();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AbstracthierachyFactory getAbstracthierachyFactory();

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
		 * The meta object literal for the '{@link eaadapter.abstracthierachy.EAAbstractPackage
		 * <em>EA Abstract Package</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.abstracthierachy.EAAbstractPackage
		 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAAbstractPackage()
		 * @generated
		 */
		EClass EA_ABSTRACT_PACKAGE = eINSTANCE.getEAAbstractPackage();

		/**
		 * The meta object literal for the '<em><b>Code Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ABSTRACT_PACKAGE__CODE_PATH = eINSTANCE.getEAAbstractPackage_CodePath();

		/**
		 * The meta object literal for the '<em><b>Flags</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ABSTRACT_PACKAGE__FLAGS = eINSTANCE.getEAAbstractPackage_Flags();

		/**
		 * The meta object literal for the '<em><b>Is Model</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_ABSTRACT_PACKAGE__IS_MODEL = eINSTANCE.getEAAbstractPackage_IsModel();

		/**
		 * The meta object literal for the '{@link eaadapter.abstracthierachy.EAClassifierIDLong
		 * <em>EA Classifier ID Long</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.abstracthierachy.EAClassifierIDLong
		 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAClassifierIDLong()
		 * @generated
		 */
		EClass EA_CLASSIFIER_ID_LONG = eINSTANCE.getEAClassifierIDLong();

		/**
		 * The meta object literal for the '<em><b>Classifier ID</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_CLASSIFIER_ID_LONG__CLASSIFIER_ID = eINSTANCE.getEAClassifierIDLong_ClassifierID();

		/**
		 * The meta object literal for the '{@link eaadapter.abstracthierachy.EAModifiableElement
		 * <em>EA Modifiable Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.abstracthierachy.EAModifiableElement
		 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAModifiableElement()
		 * @generated
		 */
		EClass EA_MODIFIABLE_ELEMENT = eINSTANCE.getEAModifiableElement();

		/**
		 * The meta object literal for the '<em><b>Is Const</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_MODIFIABLE_ELEMENT__IS_CONST = eINSTANCE.getEAModifiableElement_IsConst();

		/**
		 * The meta object literal for the '<em><b>Is Static</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_MODIFIABLE_ELEMENT__IS_STATIC = eINSTANCE.getEAModifiableElement_IsStatic();

		/**
		 * The meta object literal for the '{@link eaadapter.abstracthierachy.EANamedElement <em>EA Named Element</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.abstracthierachy.EANamedElement
		 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEANamedElement()
		 * @generated
		 */
		EClass EA_NAMED_ELEMENT = eINSTANCE.getEANamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_NAMED_ELEMENT__NAME = eINSTANCE.getEANamedElement_Name();

		/**
		 * The meta object literal for the '<em><b>Notes</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_NAMED_ELEMENT__NOTES = eINSTANCE.getEANamedElement_Notes();

		/**
		 * The meta object literal for the '<em><b>Guid</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_NAMED_ELEMENT__GUID = eINSTANCE.getEANamedElement_Guid();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_NAMED_ELEMENT__ID = eINSTANCE.getEANamedElement_Id();

		/**
		 * The meta object literal for the '{@link eaadapter.abstracthierachy.EAOwnedElement <em>EA Owned Element</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.abstracthierachy.EAOwnedElement
		 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAOwnedElement()
		 * @generated
		 */
		EClass EA_OWNED_ELEMENT = eINSTANCE.getEAOwnedElement();

		/**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_OWNED_ELEMENT__AUTHOR = eINSTANCE.getEAOwnedElement_Author();

		/**
		 * The meta object literal for the '<em><b>Is Locked</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_OWNED_ELEMENT__IS_LOCKED = eINSTANCE.getEAOwnedElement_IsLocked();

		/**
		 * The meta object literal for the '{@link eaadapter.abstracthierachy.EAStereotypedElement
		 * <em>EA Stereotyped Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.abstracthierachy.EAStereotypedElement
		 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAStereotypedElement()
		 * @generated
		 */
		EClass EA_STEREOTYPED_ELEMENT = eINSTANCE.getEAStereotypedElement();

		/**
		 * The meta object literal for the '<em><b>Stereotype</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_STEREOTYPED_ELEMENT__STEREOTYPE = eINSTANCE.getEAStereotypedElement_Stereotype();

		/**
		 * The meta object literal for the '<em><b>Stereotype Ex</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_STEREOTYPED_ELEMENT__STEREOTYPE_EX = eINSTANCE.getEAStereotypedElement_StereotypeEx();

		/**
		 * The meta object literal for the '{@link eaadapter.abstracthierachy.EATaggedElement
		 * <em>EA Tagged Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.abstracthierachy.EATaggedElement
		 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEATaggedElement()
		 * @generated
		 */
		EClass EA_TAGGED_ELEMENT = eINSTANCE.getEATaggedElement();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_TAGGED_ELEMENT__VALUE = eINSTANCE.getEATaggedElement_Value();

		/**
		 * The meta object literal for the '{@link eaadapter.abstracthierachy.EATypedElement <em>EA Typed Element</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.abstracthierachy.EATypedElement
		 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEATypedElement()
		 * @generated
		 */
		EClass EA_TYPED_ELEMENT = eINSTANCE.getEATypedElement();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_TYPED_ELEMENT__TYPE = eINSTANCE.getEATypedElement_Type();

		/**
		 * The meta object literal for the '{@link eaadapter.abstracthierachy.EAVersiondElement
		 * <em>EA Versiond Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.abstracthierachy.EAVersiondElement
		 * @see eaadapter.abstracthierachy.impl.AbstracthierachyPackageImpl#getEAVersiondElement()
		 * @generated
		 */
		EClass EA_VERSIOND_ELEMENT = eINSTANCE.getEAVersiondElement();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute EA_VERSIOND_ELEMENT__VERSION = eINSTANCE.getEAVersiondElement_Version();

	}

} // AbstracthierachyPackage
