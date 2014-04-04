/**
 * <copyright>
 * Copyright (c) Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php
 * 
 * Contributors:
 * 	Continental AG, 2012 Matthias Nick - Initial API and implementation
 * </copyright>
 * 
 */
package eaadapter;

import eaadapter.abstracthierachy.EAClassifierIDLong;

import org.sparx.Parameter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EA Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <div class='userdoc'>
 * A Parameter of a method.
 * <br><br><i>For detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html' target='_blank'>EA User Guide</a>!</i>
 * </div>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eaadapter.EAParameter#getDefault <em>Default</em>}</li>
 *   <li>{@link eaadapter.EAParameter#getPosition <em>Position</em>}</li>
 *   <li>{@link eaadapter.EAParameter#getIsConst <em>Is Const</em>}</li>
 *   <li>{@link eaadapter.EAParameter#getKind <em>Kind</em>}</li>
 *   <li>{@link eaadapter.EAParameter#getOperationID <em>Operation ID</em>}</li>
 *   <li>{@link eaadapter.EAParameter#getMethod <em>Method</em>}</li>
 *   <li>{@link eaadapter.EAParameter#getEaLink <em>Ea Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see eaadapter.EaadapterPackage#getEAParameter()
 * @model
 * @generated
 */
public interface EAParameter extends EAClassifierIDLong {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Default</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default</em>' attribute.
	 * @see #setDefault(String)
	 * @see eaadapter.EaadapterPackage#getEAParameter_Default()
	 * @model
	 * @generated
	 */
	String getDefault();

	/**
	 * Sets the value of the '{@link eaadapter.EAParameter#getDefault <em>Default</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default</em>' attribute.
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(String value);

	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Position</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Position</em>' attribute.
	 * @see #setPosition(Integer)
	 * @see eaadapter.EaadapterPackage#getEAParameter_Position()
	 * @model
	 * @generated
	 */
	Integer getPosition();

	/**
	 * Sets the value of the '{@link eaadapter.EAParameter#getPosition <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(Integer value);

	/**
	 * Returns the value of the '<em><b>Is Const</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Const</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Const</em>' attribute.
	 * @see #setIsConst(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAParameter_IsConst()
	 * @model
	 * @generated
	 */
	Boolean getIsConst();

	/**
	 * Sets the value of the '{@link eaadapter.EAParameter#getIsConst <em>Is Const</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Const</em>' attribute.
	 * @see #getIsConst()
	 * @generated
	 */
	void setIsConst(Boolean value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see #setKind(String)
	 * @see eaadapter.EaadapterPackage#getEAParameter_Kind()
	 * @model
	 * @generated
	 */
	String getKind();

	/**
	 * Sets the value of the '{@link eaadapter.EAParameter#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see #getKind()
	 * @generated
	 */
	void setKind(String value);

	/**
	 * Returns the value of the '<em><b>Operation ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation ID</em>' attribute.
	 * @see eaadapter.EaadapterPackage#getEAParameter_OperationID()
	 * @model changeable="false"
	 * @generated
	 */
	Integer getOperationID();

	/**
	 * Returns the value of the '<em><b>Method</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eaadapter.EAMethod#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Method</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Method</em>' container reference.
	 * @see #setMethod(EAMethod)
	 * @see eaadapter.EaadapterPackage#getEAParameter_Method()
	 * @see eaadapter.EAMethod#getParameters
	 * @model opposite="parameters"
	 * @generated
	 */
	EAMethod getMethod();

	/**
	 * Sets the value of the '{@link eaadapter.EAParameter#getMethod <em>Method</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Method</em>' container reference.
	 * @see #getMethod()
	 * @generated
	 */
	void setMethod(EAMethod value);

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <div class='userdoc'>
	 * DO NOT TOUCH THIS! This attributed is used as a link to the Enterprise Architect and is managed internally only!
	 * </div>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(Parameter)
	 * @see eaadapter.EaadapterPackage#getEAParameter_EaLink()
	 * @model dataType="eaadapter.datatypes.T_Parameter" transient="true"
	 * @generated
	 */
	Parameter getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EAParameter#getEaLink <em>Ea Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(Parameter value);

} // EAParameter
