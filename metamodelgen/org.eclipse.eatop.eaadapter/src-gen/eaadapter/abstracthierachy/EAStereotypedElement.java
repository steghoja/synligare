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
 * A representation of the model object '<em><b>EA Stereotyped Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eaadapter.abstracthierachy.EAStereotypedElement#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link eaadapter.abstracthierachy.EAStereotypedElement#getStereotypeEx <em>Stereotype Ex</em>}</li>
 * </ul>
 * </p>
 *
 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAStereotypedElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface EAStereotypedElement extends EANamedElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Stereotype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stereotype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stereotype</em>' attribute.
	 * @see #setStereotype(String)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAStereotypedElement_Stereotype()
	 * @model
	 * @generated
	 */
	String getStereotype();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EAStereotypedElement#getStereotype <em>Stereotype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stereotype</em>' attribute.
	 * @see #getStereotype()
	 * @generated
	 */
	void setStereotype(String value);

	/**
	 * Returns the value of the '<em><b>Stereotype Ex</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stereotype Ex</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stereotype Ex</em>' attribute.
	 * @see #setStereotypeEx(String)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAStereotypedElement_StereotypeEx()
	 * @model
	 * @generated
	 */
	String getStereotypeEx();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EAStereotypedElement#getStereotypeEx <em>Stereotype Ex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stereotype Ex</em>' attribute.
	 * @see #getStereotypeEx()
	 * @generated
	 */
	void setStereotypeEx(String value);

} // EAStereotypedElement
