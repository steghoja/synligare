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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EA Abstract Package</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eaadapter.abstracthierachy.EAAbstractPackage#getCodePath <em>Code Path</em>}</li>
 * <li>{@link eaadapter.abstracthierachy.EAAbstractPackage#getFlags <em>Flags</em>}</li>
 * <li>{@link eaadapter.abstracthierachy.EAAbstractPackage#getIsModel <em>Is Model</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAAbstractPackage()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface EAAbstractPackage extends EAVersiondElement {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Code Path</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code Path</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Code Path</em>' attribute.
	 * @see #setCodePath(String)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAAbstractPackage_CodePath()
	 * @model
	 * @generated
	 */
	String getCodePath();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EAAbstractPackage#getCodePath <em>Code Path</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Code Path</em>' attribute.
	 * @see #getCodePath()
	 * @generated
	 */
	void setCodePath(String value);

	/**
	 * Returns the value of the '<em><b>Flags</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flags</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Flags</em>' attribute.
	 * @see #setFlags(String)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAAbstractPackage_Flags()
	 * @model
	 * @generated
	 */
	String getFlags();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EAAbstractPackage#getFlags <em>Flags</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Flags</em>' attribute.
	 * @see #getFlags()
	 * @generated
	 */
	void setFlags(String value);

	/**
	 * Returns the value of the '<em><b>Is Model</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Model</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Is Model</em>' attribute.
	 * @see #setIsModel(Boolean)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEAAbstractPackage_IsModel()
	 * @model
	 * @generated
	 */
	Boolean getIsModel();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EAAbstractPackage#getIsModel <em>Is Model</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Model</em>' attribute.
	 * @see #getIsModel()
	 * @generated
	 */
	void setIsModel(Boolean value);

} // EAAbstractPackage
