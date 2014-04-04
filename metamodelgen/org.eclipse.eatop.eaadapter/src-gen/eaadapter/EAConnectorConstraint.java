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

import eaadapter.abstracthierachy.EANamedElement;

import org.sparx.ConnectorConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EA Connector Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <div class='userdoc'>
 * A Connector Constraint.
 * <br><br><i>For detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html' target='_blank'>EA User Guide</a>!</i>
 * </div>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eaadapter.EAConnectorConstraint#getConnectorID <em>Connector ID</em>}</li>
 *   <li>{@link eaadapter.EAConnectorConstraint#getType <em>Type</em>}</li>
 *   <li>{@link eaadapter.EAConnectorConstraint#getConnector <em>Connector</em>}</li>
 *   <li>{@link eaadapter.EAConnectorConstraint#getEaLink <em>Ea Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see eaadapter.EaadapterPackage#getEAConnectorConstraint()
 * @model
 * @generated
 */
public interface EAConnectorConstraint extends EANamedElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Connector ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector ID</em>' attribute.
	 * @see #setConnectorID(long)
	 * @see eaadapter.EaadapterPackage#getEAConnectorConstraint_ConnectorID()
	 * @model
	 * @generated
	 */
	long getConnectorID();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorConstraint#getConnectorID <em>Connector ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector ID</em>' attribute.
	 * @see #getConnectorID()
	 * @generated
	 */
	void setConnectorID(long value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorConstraint_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorConstraint#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Connector</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eaadapter.EAConnector#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector</em>' container reference.
	 * @see #setConnector(EAConnector)
	 * @see eaadapter.EaadapterPackage#getEAConnectorConstraint_Connector()
	 * @see eaadapter.EAConnector#getConstraints
	 * @model opposite="constraints" transient="false"
	 * @generated
	 */
	EAConnector getConnector();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorConstraint#getConnector <em>Connector</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector</em>' container reference.
	 * @see #getConnector()
	 * @generated
	 */
	void setConnector(EAConnector value);

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ea Link</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(ConnectorConstraint)
	 * @see eaadapter.EaadapterPackage#getEAConnectorConstraint_EaLink()
	 * @model dataType="eaadapter.datatypes.T_ConnectorConstraint" transient="true"
	 * @generated
	 */
	ConnectorConstraint getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorConstraint#getEaLink <em>Ea Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(ConnectorConstraint value);

} // EAConnectorConstraint
