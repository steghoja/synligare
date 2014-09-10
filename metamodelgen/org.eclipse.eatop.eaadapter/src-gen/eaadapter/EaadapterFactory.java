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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 * 
 * @see eaadapter.EaadapterPackage
 * @generated
 */
public interface EaadapterFactory extends EFactory {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	EaadapterFactory eINSTANCE = eaadapter.impl.EaadapterFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>EA Attribute</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Attribute</em>'.
	 * @generated
	 */
	EAAttribute createEAAttribute();

	/**
	 * Returns a new object of class '<em>EA Attribute Tag</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Attribute Tag</em>'.
	 * @generated
	 */
	EAAttributeTag createEAAttributeTag();

	/**
	 * Returns a new object of class '<em>EA Connector</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Connector</em>'.
	 * @generated
	 */
	EAConnector createEAConnector();

	/**
	 * Returns a new object of class '<em>EA Connector Tag</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Connector Tag</em>'.
	 * @generated
	 */
	EAConnectorTag createEAConnectorTag();

	/**
	 * Returns a new object of class '<em>EA Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Element</em>'.
	 * @generated
	 */
	EAElement createEAElement();

	/**
	 * Returns a new object of class '<em>EA Method</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Method</em>'.
	 * @generated
	 */
	EAMethod createEAMethod();

	/**
	 * Returns a new object of class '<em>EA Method Tag</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Method Tag</em>'.
	 * @generated
	 */
	EAMethodTag createEAMethodTag();

	/**
	 * Returns a new object of class '<em>EA Package</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Package</em>'.
	 * @generated
	 */
	EAPackage createEAPackage();

	/**
	 * Returns a new object of class '<em>EA Parameter</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Parameter</em>'.
	 * @generated
	 */
	EAParameter createEAParameter();

	/**
	 * Returns a new object of class '<em>EA Repository</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Repository</em>'.
	 * @generated
	 */
	EARepository createEARepository();

	/**
	 * Returns a new object of class '<em>EA Tagged Value</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Tagged Value</em>'.
	 * @generated
	 */
	EATaggedValue createEATaggedValue();

	/**
	 * Returns a new object of class '<em>EA Connector End</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Connector End</em>'.
	 * @generated
	 */
	EAConnectorEnd createEAConnectorEnd();

	/**
	 * Returns a new object of class '<em>EA Connector Constraint</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Connector Constraint</em>'.
	 * @generated
	 */
	EAConnectorConstraint createEAConnectorConstraint();

	/**
	 * Returns a new object of class '<em>EA Role Tag</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Role Tag</em>'.
	 * @generated
	 */
	EARoleTag createEARoleTag();

	/**
	 * Returns a new object of class '<em>EA Constraint</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return a new object of class '<em>EA Constraint</em>'.
	 * @generated
	 */
	EAConstraint createEAConstraint();

	/**
	 * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the package supported by this factory.
	 * @generated
	 */
	EaadapterPackage getEaadapterPackage();

} // EaadapterFactory
