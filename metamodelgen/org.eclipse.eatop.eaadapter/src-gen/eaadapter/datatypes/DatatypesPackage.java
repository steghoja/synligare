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
package eaadapter.datatypes;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
 * @see eaadapter.datatypes.DatatypesFactory
 * @model kind="package"
 * @generated
 */
public interface DatatypesPackage extends EPackage {
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
	String eNAME = "datatypes";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://eaadapter.datatypes";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "eaadapter.datatypes";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	DatatypesPackage eINSTANCE = eaadapter.datatypes.impl.DatatypesPackageImpl.init();

	/**
	 * The meta object id for the '{@link eaadapter.datatypes.T_ConnectorAggregation <em>TConnector Aggregation</em>}'
	 * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see eaadapter.datatypes.T_ConnectorAggregation
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_ConnectorAggregation()
	 * @generated
	 */
	int TCONNECTOR_AGGREGATION = 0;

	/**
	 * The meta object id for the '<em>TAttribute</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.Attribute
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Attribute()
	 * @generated
	 */
	int TATTRIBUTE = 1;

	/**
	 * The meta object id for the '<em>TAttribute Tag</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.AttributeTag
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_AttributeTag()
	 * @generated
	 */
	int TATTRIBUTE_TAG = 2;

	/**
	 * The meta object id for the '<em>TConnector</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.Connector
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Connector()
	 * @generated
	 */
	int TCONNECTOR = 3;

	/**
	 * The meta object id for the '<em>TConnector End</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.ConnectorEnd
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_ConnectorEnd()
	 * @generated
	 */
	int TCONNECTOR_END = 4;

	/**
	 * The meta object id for the '<em>TConntector Tag</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.ConnectorTag
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_ConntectorTag()
	 * @generated
	 */
	int TCONNTECTOR_TAG = 5;

	/**
	 * The meta object id for the '<em>TElement</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.Element
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Element()
	 * @generated
	 */
	int TELEMENT = 6;

	/**
	 * The meta object id for the '<em>TMethod</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.Method
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Method()
	 * @generated
	 */
	int TMETHOD = 7;

	/**
	 * The meta object id for the '<em>TMethod Tag</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.MethodTag
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_MethodTag()
	 * @generated
	 */
	int TMETHOD_TAG = 8;

	/**
	 * The meta object id for the '<em>TPackage</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.Package
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Package()
	 * @generated
	 */
	int TPACKAGE = 9;

	/**
	 * The meta object id for the '<em>TParameter</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.Parameter
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Parameter()
	 * @generated
	 */
	int TPARAMETER = 10;

	/**
	 * The meta object id for the '<em>TRepository</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.Repository
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Repository()
	 * @generated
	 */
	int TREPOSITORY = 11;

	/**
	 * The meta object id for the '<em>TTagged Value</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.TaggedValue
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_TaggedValue()
	 * @generated
	 */
	int TTAGGED_VALUE = 12;

	/**
	 * The meta object id for the '<em>File</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.io.File
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getFile()
	 * @generated
	 */
	int FILE = 13;

	/**
	 * The meta object id for the '<em>TConnector Constraint</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.sparx.ConnectorConstraint
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_ConnectorConstraint()
	 * @generated
	 */
	int TCONNECTOR_CONSTRAINT = 14;

	/**
	 * The meta object id for the '<em>TRole Tag</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.sparx.RoleTag
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_RoleTag()
	 * @generated
	 */
	int TROLE_TAG = 15;

	/**
	 * The meta object id for the '<em>Exception</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see java.lang.Exception
	 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getException()
	 * @generated
	 */
	int EXCEPTION = 16;

	/**
	 * Returns the meta object for enum '{@link eaadapter.datatypes.T_ConnectorAggregation
	 * <em>TConnector Aggregation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>TConnector Aggregation</em>'.
	 * @see eaadapter.datatypes.T_ConnectorAggregation
	 * @generated
	 */
	EEnum getT_ConnectorAggregation();

	/**
	 * Returns the meta object for data type '{@link org.sparx.Attribute <em>TAttribute</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TAttribute</em>'.
	 * @see org.sparx.Attribute
	 * @model instanceClass="org.sparx.Attribute" serializeable="false"
	 * @generated
	 */
	EDataType getT_Attribute();

	/**
	 * Returns the meta object for data type '{@link org.sparx.AttributeTag <em>TAttribute Tag</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TAttribute Tag</em>'.
	 * @see org.sparx.AttributeTag
	 * @model instanceClass="org.sparx.AttributeTag" serializeable="false"
	 * @generated
	 */
	EDataType getT_AttributeTag();

	/**
	 * Returns the meta object for data type '{@link org.sparx.Connector <em>TConnector</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TConnector</em>'.
	 * @see org.sparx.Connector
	 * @model instanceClass="org.sparx.Connector" serializeable="false"
	 * @generated
	 */
	EDataType getT_Connector();

	/**
	 * Returns the meta object for data type '{@link org.sparx.ConnectorEnd <em>TConnector End</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TConnector End</em>'.
	 * @see org.sparx.ConnectorEnd
	 * @model instanceClass="org.sparx.ConnectorEnd" serializeable="false"
	 * @generated
	 */
	EDataType getT_ConnectorEnd();

	/**
	 * Returns the meta object for data type '{@link org.sparx.ConnectorTag <em>TConntector Tag</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TConntector Tag</em>'.
	 * @see org.sparx.ConnectorTag
	 * @model instanceClass="org.sparx.ConnectorTag" serializeable="false"
	 * @generated
	 */
	EDataType getT_ConntectorTag();

	/**
	 * Returns the meta object for data type '{@link org.sparx.Element <em>TElement</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TElement</em>'.
	 * @see org.sparx.Element
	 * @model instanceClass="org.sparx.Element" serializeable="false"
	 * @generated
	 */
	EDataType getT_Element();

	/**
	 * Returns the meta object for data type '{@link org.sparx.Method <em>TMethod</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TMethod</em>'.
	 * @see org.sparx.Method
	 * @model instanceClass="org.sparx.Method" serializeable="false"
	 * @generated
	 */
	EDataType getT_Method();

	/**
	 * Returns the meta object for data type '{@link org.sparx.MethodTag <em>TMethod Tag</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TMethod Tag</em>'.
	 * @see org.sparx.MethodTag
	 * @model instanceClass="org.sparx.MethodTag" serializeable="false"
	 * @generated
	 */
	EDataType getT_MethodTag();

	/**
	 * Returns the meta object for data type '{@link org.sparx.Package <em>TPackage</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TPackage</em>'.
	 * @see org.sparx.Package
	 * @model instanceClass="org.sparx.Package" serializeable="false"
	 * @generated
	 */
	EDataType getT_Package();

	/**
	 * Returns the meta object for data type '{@link org.sparx.Parameter <em>TParameter</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TParameter</em>'.
	 * @see org.sparx.Parameter
	 * @model instanceClass="org.sparx.Parameter" serializeable="false"
	 * @generated
	 */
	EDataType getT_Parameter();

	/**
	 * Returns the meta object for data type '{@link org.sparx.Repository <em>TRepository</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TRepository</em>'.
	 * @see org.sparx.Repository
	 * @model instanceClass="org.sparx.Repository" serializeable="false"
	 * @generated
	 */
	EDataType getT_Repository();

	/**
	 * Returns the meta object for data type '{@link org.sparx.TaggedValue <em>TTagged Value</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TTagged Value</em>'.
	 * @see org.sparx.TaggedValue
	 * @model instanceClass="org.sparx.TaggedValue" serializeable="false"
	 * @generated
	 */
	EDataType getT_TaggedValue();

	/**
	 * Returns the meta object for data type '{@link java.io.File <em>File</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>File</em>'.
	 * @see java.io.File
	 * @model instanceClass="java.io.File"
	 * @generated
	 */
	EDataType getFile();

	/**
	 * Returns the meta object for data type '{@link org.sparx.ConnectorConstraint <em>TConnector Constraint</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TConnector Constraint</em>'.
	 * @see org.sparx.ConnectorConstraint
	 * @model instanceClass="org.sparx.ConnectorConstraint" serializeable="false"
	 * @generated
	 */
	EDataType getT_ConnectorConstraint();

	/**
	 * Returns the meta object for data type '{@link org.sparx.RoleTag <em>TRole Tag</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>TRole Tag</em>'.
	 * @see org.sparx.RoleTag
	 * @model instanceClass="org.sparx.RoleTag" serializeable="false"
	 * @generated
	 */
	EDataType getT_RoleTag();

	/**
	 * Returns the meta object for data type '{@link java.lang.Exception <em>Exception</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Exception</em>'.
	 * @see java.lang.Exception
	 * @model instanceClass="java.lang.Exception"
	 * @generated
	 */
	EDataType getException();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DatatypesFactory getDatatypesFactory();

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
		 * The meta object literal for the '{@link eaadapter.datatypes.T_ConnectorAggregation
		 * <em>TConnector Aggregation</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see eaadapter.datatypes.T_ConnectorAggregation
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_ConnectorAggregation()
		 * @generated
		 */
		EEnum TCONNECTOR_AGGREGATION = eINSTANCE.getT_ConnectorAggregation();

		/**
		 * The meta object literal for the '<em>TAttribute</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.sparx.Attribute
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Attribute()
		 * @generated
		 */
		EDataType TATTRIBUTE = eINSTANCE.getT_Attribute();

		/**
		 * The meta object literal for the '<em>TAttribute Tag</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.sparx.AttributeTag
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_AttributeTag()
		 * @generated
		 */
		EDataType TATTRIBUTE_TAG = eINSTANCE.getT_AttributeTag();

		/**
		 * The meta object literal for the '<em>TConnector</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.sparx.Connector
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Connector()
		 * @generated
		 */
		EDataType TCONNECTOR = eINSTANCE.getT_Connector();

		/**
		 * The meta object literal for the '<em>TConnector End</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.sparx.ConnectorEnd
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_ConnectorEnd()
		 * @generated
		 */
		EDataType TCONNECTOR_END = eINSTANCE.getT_ConnectorEnd();

		/**
		 * The meta object literal for the '<em>TConntector Tag</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.sparx.ConnectorTag
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_ConntectorTag()
		 * @generated
		 */
		EDataType TCONNTECTOR_TAG = eINSTANCE.getT_ConntectorTag();

		/**
		 * The meta object literal for the '<em>TElement</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.sparx.Element
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Element()
		 * @generated
		 */
		EDataType TELEMENT = eINSTANCE.getT_Element();

		/**
		 * The meta object literal for the '<em>TMethod</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.sparx.Method
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Method()
		 * @generated
		 */
		EDataType TMETHOD = eINSTANCE.getT_Method();

		/**
		 * The meta object literal for the '<em>TMethod Tag</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.sparx.MethodTag
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_MethodTag()
		 * @generated
		 */
		EDataType TMETHOD_TAG = eINSTANCE.getT_MethodTag();

		/**
		 * The meta object literal for the '<em>TPackage</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.sparx.Package
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Package()
		 * @generated
		 */
		EDataType TPACKAGE = eINSTANCE.getT_Package();

		/**
		 * The meta object literal for the '<em>TParameter</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.sparx.Parameter
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Parameter()
		 * @generated
		 */
		EDataType TPARAMETER = eINSTANCE.getT_Parameter();

		/**
		 * The meta object literal for the '<em>TRepository</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.sparx.Repository
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_Repository()
		 * @generated
		 */
		EDataType TREPOSITORY = eINSTANCE.getT_Repository();

		/**
		 * The meta object literal for the '<em>TTagged Value</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.sparx.TaggedValue
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_TaggedValue()
		 * @generated
		 */
		EDataType TTAGGED_VALUE = eINSTANCE.getT_TaggedValue();

		/**
		 * The meta object literal for the '<em>File</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.io.File
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getFile()
		 * @generated
		 */
		EDataType FILE = eINSTANCE.getFile();

		/**
		 * The meta object literal for the '<em>TConnector Constraint</em>' data type. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.sparx.ConnectorConstraint
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_ConnectorConstraint()
		 * @generated
		 */
		EDataType TCONNECTOR_CONSTRAINT = eINSTANCE.getT_ConnectorConstraint();

		/**
		 * The meta object literal for the '<em>TRole Tag</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.sparx.RoleTag
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getT_RoleTag()
		 * @generated
		 */
		EDataType TROLE_TAG = eINSTANCE.getT_RoleTag();

		/**
		 * The meta object literal for the '<em>Exception</em>' data type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see java.lang.Exception
		 * @see eaadapter.datatypes.impl.DatatypesPackageImpl#getException()
		 * @generated
		 */
		EDataType EXCEPTION = eINSTANCE.getException();

	}

} // DatatypesPackage
