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

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EA Classifier ID Long</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eaadapter.abstracthierachy.EAClassifierIDLong#getClassifierID <em>Classifier ID</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAClassifierIDLong()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface EAClassifierIDLong extends EATypedElement {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Classifier ID</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classifier ID</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Classifier ID</em>' attribute.
	 * @see #setClassifierID(Integer)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAClassifierIDLong_ClassifierID()
	 * @model
	 * @generated
	 */
	Integer getClassifierID();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EAClassifierIDLong#getClassifierID
	 * <em>Classifier ID</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Classifier ID</em>' attribute.
	 * @see #getClassifierID()
	 * @generated
	 */
	void setClassifierID(Integer value);

} // EAClassifierIDLong
