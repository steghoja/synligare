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

import org.eclipse.emf.common.util.EList;
import org.sparx.Method;

import eaadapter.abstracthierachy.EAClassifierIDLong;
import eaadapter.abstracthierachy.EAModifiableElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EA Method</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> <div class='userdoc'> An operation of the element it belongs to. <br>
 * <br>
 * <i>For detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html' target='_blank'>EA
 * User Guide</a>!</i> </div> <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eaadapter.EAMethod#getReturnType <em>Return Type</em>}</li>
 * <li>{@link eaadapter.EAMethod#getReturnIsArray <em>Return Is Array</em>}</li>
 * <li>{@link eaadapter.EAMethod#getCode <em>Code</em>}</li>
 * <li>{@link eaadapter.EAMethod#getThrows <em>Throws</em>}</li>
 * <li>{@link eaadapter.EAMethod#getIsPure <em>Is Pure</em>}</li>
 * <li>{@link eaadapter.EAMethod#getIsRoot <em>Is Root</em>}</li>
 * <li>{@link eaadapter.EAMethod#getIsLeaf <em>Is Leaf</em>}</li>
 * <li>{@link eaadapter.EAMethod#getIsQuery <em>Is Query</em>}</li>
 * <li>{@link eaadapter.EAMethod#getIsSynchronized <em>Is Synchronized</em>}</li>
 * <li>{@link eaadapter.EAMethod#getIsAbstract <em>Is Abstract</em>}</li>
 * <li>{@link eaadapter.EAMethod#getConcurrency <em>Concurrency</em>}</li>
 * <li>{@link eaadapter.EAMethod#getElement <em>Element</em>}</li>
 * <li>{@link eaadapter.EAMethod#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.EAMethod#getParameters <em>Parameters</em>}</li>
 * <li>{@link eaadapter.EAMethod#getTaggedValues <em>Tagged Values</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eaadapter.EaadapterPackage#getEAMethod()
 * @model
 * @generated
 */
public interface EAMethod extends EAModifiableElement, EAClassifierIDLong {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Return Type</em>' attribute.
	 * @see #setReturnType(String)
	 * @see eaadapter.EaadapterPackage#getEAMethod_ReturnType()
	 * @model
	 * @generated
	 */
	String getReturnType();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getReturnType <em>Return Type</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Return Type</em>' attribute.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(String value);

	/**
	 * Returns the value of the '<em><b>Return Is Array</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Is Array</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Return Is Array</em>' attribute.
	 * @see #setReturnIsArray(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAMethod_ReturnIsArray()
	 * @model
	 * @generated
	 */
	Boolean getReturnIsArray();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getReturnIsArray <em>Return Is Array</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Return Is Array</em>' attribute.
	 * @see #getReturnIsArray()
	 * @generated
	 */
	void setReturnIsArray(Boolean value);

	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(String)
	 * @see eaadapter.EaadapterPackage#getEAMethod_Code()
	 * @model
	 * @generated
	 */
	String getCode();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getCode <em>Code</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Code</em>' attribute.
	 * @see #getCode()
	 * @generated
	 */
	void setCode(String value);

	/**
	 * Returns the value of the '<em><b>Throws</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Throws</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Throws</em>' attribute.
	 * @see #setThrows(String)
	 * @see eaadapter.EaadapterPackage#getEAMethod_Throws()
	 * @model
	 * @generated
	 */
	String getThrows();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getThrows <em>Throws</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Throws</em>' attribute.
	 * @see #getThrows()
	 * @generated
	 */
	void setThrows(String value);

	/**
	 * Returns the value of the '<em><b>Is Pure</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Pure</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Is Pure</em>' attribute.
	 * @see #setIsPure(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAMethod_IsPure()
	 * @model
	 * @generated
	 */
	Boolean getIsPure();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getIsPure <em>Is Pure</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Pure</em>' attribute.
	 * @see #getIsPure()
	 * @generated
	 */
	void setIsPure(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Root</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Root</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Is Root</em>' attribute.
	 * @see #setIsRoot(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAMethod_IsRoot()
	 * @model
	 * @generated
	 */
	Boolean getIsRoot();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getIsRoot <em>Is Root</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Root</em>' attribute.
	 * @see #getIsRoot()
	 * @generated
	 */
	void setIsRoot(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Leaf</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Leaf</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Is Leaf</em>' attribute.
	 * @see #setIsLeaf(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAMethod_IsLeaf()
	 * @model
	 * @generated
	 */
	Boolean getIsLeaf();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getIsLeaf <em>Is Leaf</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Leaf</em>' attribute.
	 * @see #getIsLeaf()
	 * @generated
	 */
	void setIsLeaf(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Query</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Query</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Is Query</em>' attribute.
	 * @see #setIsQuery(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAMethod_IsQuery()
	 * @model
	 * @generated
	 */
	Boolean getIsQuery();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getIsQuery <em>Is Query</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Query</em>' attribute.
	 * @see #getIsQuery()
	 * @generated
	 */
	void setIsQuery(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Synchronized</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Synchronized</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Is Synchronized</em>' attribute.
	 * @see #setIsSynchronized(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAMethod_IsSynchronized()
	 * @model
	 * @generated
	 */
	Boolean getIsSynchronized();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getIsSynchronized <em>Is Synchronized</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Synchronized</em>' attribute.
	 * @see #getIsSynchronized()
	 * @generated
	 */
	void setIsSynchronized(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAMethod_IsAbstract()
	 * @model
	 * @generated
	 */
	Boolean getIsAbstract();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getIsAbstract <em>Is Abstract</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #getIsAbstract()
	 * @generated
	 */
	void setIsAbstract(Boolean value);

	/**
	 * Returns the value of the '<em><b>Concurrency</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Concurrency</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Concurrency</em>' attribute.
	 * @see #setConcurrency(String)
	 * @see eaadapter.EaadapterPackage#getEAMethod_Concurrency()
	 * @model
	 * @generated
	 */
	String getConcurrency();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getConcurrency <em>Concurrency</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Concurrency</em>' attribute.
	 * @see #getConcurrency()
	 * @generated
	 */
	void setConcurrency(String value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' container reference. It is bidirectional and its opposite is '
	 * {@link eaadapter.EAElement#getMethods <em>Methods</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Element</em>' container reference.
	 * @see #setElement(EAElement)
	 * @see eaadapter.EaadapterPackage#getEAMethod_Element()
	 * @see eaadapter.EAElement#getMethods
	 * @model opposite="methods"
	 * @generated
	 */
	EAElement getElement();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getElement <em>Element</em>}' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Element</em>' container reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(EAElement value);

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> <div class='userdoc'> DO NOT TOUCH THIS! This attributed is used as a link to the Enterprise
	 * Architect and is managed internally only! </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(Method)
	 * @see eaadapter.EaadapterPackage#getEAMethod_EaLink()
	 * @model dataType="eaadapter.datatypes.T_Method" transient="true"
	 * @generated
	 */
	Method getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EAMethod#getEaLink <em>Ea Link</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(Method value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list. The list contents are of type
	 * {@link eaadapter.EAParameter}. It is bidirectional and its opposite is '{@link eaadapter.EAParameter#getMethod
	 * <em>Method</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAMethod_Parameters()
	 * @see eaadapter.EAParameter#getMethod
	 * @model opposite="method" containment="true"
	 * @generated
	 */
	EList<EAParameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Tagged Values</b></em>' containment reference list. The list contents are of
	 * type {@link eaadapter.EAMethodTag}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tagged Values</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Tagged Values</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAMethod_TaggedValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<EAMethodTag> getTaggedValues();

} // EAMethod
