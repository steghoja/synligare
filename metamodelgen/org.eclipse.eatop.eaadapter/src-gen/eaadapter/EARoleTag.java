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

import org.sparx.RoleTag;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EA Role Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <div class='userdoc'>
 * A Role Tag.
 * <br><br><i>For detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html' target='_blank'>EA User Guide</a>!</i>
 * </div>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eaadapter.EARoleTag#getBaseClass <em>Base Class</em>}</li>
 *   <li>{@link eaadapter.EARoleTag#getElementGUID <em>Element GUID</em>}</li>
 *   <li>{@link eaadapter.EARoleTag#getPropertyGUID <em>Property GUID</em>}</li>
 *   <li>{@link eaadapter.EARoleTag#getTag <em>Tag</em>}</li>
 *   <li>{@link eaadapter.EARoleTag#getConnectorEnd <em>Connector End</em>}</li>
 *   <li>{@link eaadapter.EARoleTag#getEaLink <em>Ea Link</em>}</li>
 *   <li>{@link eaadapter.EARoleTag#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see eaadapter.EaadapterPackage#getEARoleTag()
 * @model
 * @generated
 */
public interface EARoleTag extends EANamedElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Base Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Class</em>' attribute.
	 * @see #setBaseClass(String)
	 * @see eaadapter.EaadapterPackage#getEARoleTag_BaseClass()
	 * @model
	 * @generated
	 */
	String getBaseClass();

	/**
	 * Sets the value of the '{@link eaadapter.EARoleTag#getBaseClass <em>Base Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class</em>' attribute.
	 * @see #getBaseClass()
	 * @generated
	 */
	void setBaseClass(String value);

	/**
	 * Returns the value of the '<em><b>Element GUID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element GUID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element GUID</em>' attribute.
	 * @see #setElementGUID(String)
	 * @see eaadapter.EaadapterPackage#getEARoleTag_ElementGUID()
	 * @model
	 * @generated
	 */
	String getElementGUID();

	/**
	 * Sets the value of the '{@link eaadapter.EARoleTag#getElementGUID <em>Element GUID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element GUID</em>' attribute.
	 * @see #getElementGUID()
	 * @generated
	 */
	void setElementGUID(String value);

	/**
	 * Returns the value of the '<em><b>Property GUID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property GUID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property GUID</em>' attribute.
	 * @see #setPropertyGUID(String)
	 * @see eaadapter.EaadapterPackage#getEARoleTag_PropertyGUID()
	 * @model
	 * @generated
	 */
	String getPropertyGUID();

	/**
	 * Sets the value of the '{@link eaadapter.EARoleTag#getPropertyGUID <em>Property GUID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property GUID</em>' attribute.
	 * @see #getPropertyGUID()
	 * @generated
	 */
	void setPropertyGUID(String value);

	/**
	 * Returns the value of the '<em><b>Tag</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tag</em>' attribute.
	 * @see #setTag(String)
	 * @see eaadapter.EaadapterPackage#getEARoleTag_Tag()
	 * @model
	 * @generated
	 */
	String getTag();

	/**
	 * Sets the value of the '{@link eaadapter.EARoleTag#getTag <em>Tag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tag</em>' attribute.
	 * @see #getTag()
	 * @generated
	 */
	void setTag(String value);

	/**
	 * Returns the value of the '<em><b>Connector End</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eaadapter.EAConnectorEnd#getTaggedValues <em>Tagged Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector End</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector End</em>' container reference.
	 * @see #setConnectorEnd(EAConnectorEnd)
	 * @see eaadapter.EaadapterPackage#getEARoleTag_ConnectorEnd()
	 * @see eaadapter.EAConnectorEnd#getTaggedValues
	 * @model opposite="TaggedValues" transient="false"
	 * @generated
	 */
	EAConnectorEnd getConnectorEnd();

	/**
	 * Sets the value of the '{@link eaadapter.EARoleTag#getConnectorEnd <em>Connector End</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector End</em>' container reference.
	 * @see #getConnectorEnd()
	 * @generated
	 */
	void setConnectorEnd(EAConnectorEnd value);

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ea Link</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(RoleTag)
	 * @see eaadapter.EaadapterPackage#getEARoleTag_EaLink()
	 * @model dataType="eaadapter.datatypes.T_RoleTag" transient="true"
	 * @generated
	 */
	RoleTag getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EARoleTag#getEaLink <em>Ea Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(RoleTag value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see eaadapter.EaadapterPackage#getEARoleTag_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link eaadapter.EARoleTag#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // EARoleTag
