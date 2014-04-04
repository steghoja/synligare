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
package eaadapter.abstracthierachy;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EA Modifiable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eaadapter.abstracthierachy.EAModifiableElement#getIsConst <em>Is Const</em>}</li>
 *   <li>{@link eaadapter.abstracthierachy.EAModifiableElement#getIsStatic <em>Is Static</em>}</li>
 * </ul>
 * </p>
 *
 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAModifiableElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface EAModifiableElement extends EATypedElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

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
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAModifiableElement_IsConst()
	 * @model
	 * @generated
	 */
	Boolean getIsConst();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EAModifiableElement#getIsConst <em>Is Const</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Const</em>' attribute.
	 * @see #getIsConst()
	 * @generated
	 */
	void setIsConst(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Static</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Static</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Static</em>' attribute.
	 * @see #setIsStatic(Boolean)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAModifiableElement_IsStatic()
	 * @model
	 * @generated
	 */
	Boolean getIsStatic();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EAModifiableElement#getIsStatic <em>Is Static</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Static</em>' attribute.
	 * @see #getIsStatic()
	 * @generated
	 */
	void setIsStatic(Boolean value);

} // EAModifiableElement
