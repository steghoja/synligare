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
 * A representation of the model object '<em><b>EA Owned Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eaadapter.abstracthierachy.EAOwnedElement#getAuthor <em>Author</em>}</li>
 *   <li>{@link eaadapter.abstracthierachy.EAOwnedElement#getIsLocked <em>Is Locked</em>}</li>
 * </ul>
 * </p>
 *
 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAOwnedElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface EAOwnedElement extends EAVersiondElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Author</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Author</em>' attribute.
	 * @see #setAuthor(String)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAOwnedElement_Author()
	 * @model
	 * @generated
	 */
	String getAuthor();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EAOwnedElement#getAuthor <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author</em>' attribute.
	 * @see #getAuthor()
	 * @generated
	 */
	void setAuthor(String value);

	/**
	 * Returns the value of the '<em><b>Is Locked</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Locked</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Locked</em>' attribute.
	 * @see #setIsLocked(Boolean)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAOwnedElement_IsLocked()
	 * @model
	 * @generated
	 */
	Boolean getIsLocked();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EAOwnedElement#getIsLocked <em>Is Locked</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Locked</em>' attribute.
	 * @see #getIsLocked()
	 * @generated
	 */
	void setIsLocked(Boolean value);

} // EAOwnedElement
