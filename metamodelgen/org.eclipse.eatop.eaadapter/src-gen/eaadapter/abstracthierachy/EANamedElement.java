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

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EA Named Element</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eaadapter.abstracthierachy.EANamedElement#getName <em>Name</em>}</li>
 * <li>{@link eaadapter.abstracthierachy.EANamedElement#getNotes <em>Notes</em>}</li>
 * <li>{@link eaadapter.abstracthierachy.EANamedElement#getGuid <em>Guid</em>}</li>
 * <li>{@link eaadapter.abstracthierachy.EANamedElement#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEANamedElement()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface EANamedElement extends EObject {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. The default value is <code>"name"</code>. <!--
	 * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> <div class='userdoc'> The name of the EA
	 * element. </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEANamedElement_Name()
	 * @model default="name"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EANamedElement#getName <em>Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Notes</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> <div class='userdoc'> Each EA element may have a note. </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Notes</em>' attribute.
	 * @see #setNotes(String)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEANamedElement_Notes()
	 * @model
	 * @generated
	 */
	String getNotes();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EANamedElement#getNotes <em>Notes</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Notes</em>' attribute.
	 * @see #getNotes()
	 * @generated
	 */
	void setNotes(String value);

	/**
	 * Returns the value of the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> <div class='userdoc'> Each element of the EA has a Global Unique IDendifier!<br>
	 * This guid is used as an ID for the EMF model as well!<br>
	 * Unfortunately, it is a readonly attribute for EA elements.<br>
	 * <b>Important: This attribute is set automatically if the adapter is initialized via
	 * <code>initializeAdapter</code>!</b> </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Guid</em>' attribute.
	 * @see #setGuid(String)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEANamedElement_Guid()
	 * @model id="true"
	 * @generated
	 */
	String getGuid();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EANamedElement#getGuid <em>Guid</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Guid</em>' attribute.
	 * @see #getGuid()
	 * @generated
	 */
	void setGuid(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(long)
	 * @see eaadapter.abstracthierachy.AbstracthierachyPackage#getEANamedElement_Id()
	 * @model
	 * @generated
	 */
	long getId();

	/**
	 * Sets the value of the '{@link eaadapter.abstracthierachy.EANamedElement#getId <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(long value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // EANamedElement
