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

import org.sparx.ConnectorTag;

import eaadapter.abstracthierachy.EATaggedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EA Connector Tag</b></em>'. <!-- end-user-doc
 * --> <!-- begin-model-doc --> <div class='userdoc'> A Tagged Value. <br>
 * <br>
 * <i>For detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html' target='_blank'>EA
 * User Guide</a>!</i> </div> <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eaadapter.EAConnectorTag#getEaLink <em>Ea Link</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eaadapter.EaadapterPackage#getEAConnectorTag()
 * @model
 * @generated
 */
public interface EAConnectorTag extends EATaggedElement {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> <div class='userdoc'> DO NOT TOUCH THIS! This attributed is used as a link to the Enterprise
	 * Architect and is managed internally only! </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(ConnectorTag)
	 * @see eaadapter.EaadapterPackage#getEAConnectorTag_EaLink()
	 * @model dataType="eaadapter.datatypes.T_ConntectorTag" transient="true"
	 * @generated
	 */
	ConnectorTag getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorTag#getEaLink <em>Ea Link</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(ConnectorTag value);

} // EAConnectorTag
