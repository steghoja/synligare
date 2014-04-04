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

import eaadapter.datatypes.T_ConnectorAggregation;

import org.eclipse.emf.common.util.EList;

import org.sparx.ConnectorEnd;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EA Connector End</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <div class='userdoc'>
 * A Connector End.
 * <br><br><i>For detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html' target='_blank'>EA User Guide</a>!</i>
 * </div>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eaadapter.EAConnectorEnd#getTaggedValues <em>Tagged Values</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getEnd <em>End</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getRole <em>Role</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getRoleType <em>Role Type</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getRoleNote <em>Role Note</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getContainment <em>Containment</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getAggregation <em>Aggregation</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getOrdering <em>Ordering</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getQualifier <em>Qualifier</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#isIsNavigable <em>Is Navigable</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getIsChangable <em>Is Changable</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#getEaLink <em>Ea Link</em>}</li>
 *   <li>{@link eaadapter.EAConnectorEnd#isDerived <em>Derived</em>}</li>
 * </ul>
 * </p>
 *
 * @see eaadapter.EaadapterPackage#getEAConnectorEnd()
 * @model
 * @generated
 */
public interface EAConnectorEnd extends EANamedElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Tagged Values</b></em>' containment reference list.
	 * The list contents are of type {@link eaadapter.EARoleTag}.
	 * It is bidirectional and its opposite is '{@link eaadapter.EARoleTag#getConnectorEnd <em>Connector End</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tagged Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tagged Values</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_TaggedValues()
	 * @see eaadapter.EARoleTag#getConnectorEnd
	 * @model opposite="connectorEnd" containment="true"
	 * @generated
	 */
	EList<EARoleTag> getTaggedValues();

	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_End()
	 * @model
	 * @generated
	 */
	String getEnd();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getEnd <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' attribute.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(String value);

	/**
	 * Returns the value of the '<em><b>Cardinality</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cardinality</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cardinality</em>' attribute.
	 * @see #setCardinality(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_Cardinality()
	 * @model
	 * @generated
	 */
	String getCardinality();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getCardinality <em>Cardinality</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cardinality</em>' attribute.
	 * @see #getCardinality()
	 * @generated
	 */
	void setCardinality(String value);

	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see #setVisibility(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_Visibility()
	 * @model
	 * @generated
	 */
	String getVisibility();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getVisibility <em>Visibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visibility</em>' attribute.
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(String value);

	/**
	 * Returns the value of the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' attribute.
	 * @see #setRole(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_Role()
	 * @model
	 * @generated
	 */
	String getRole();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getRole <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' attribute.
	 * @see #getRole()
	 * @generated
	 */
	void setRole(String value);

	/**
	 * Returns the value of the '<em><b>Role Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Type</em>' attribute.
	 * @see #setRoleType(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_RoleType()
	 * @model
	 * @generated
	 */
	String getRoleType();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getRoleType <em>Role Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Type</em>' attribute.
	 * @see #getRoleType()
	 * @generated
	 */
	void setRoleType(String value);

	/**
	 * Returns the value of the '<em><b>Role Note</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Note</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Note</em>' attribute.
	 * @see #setRoleNote(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_RoleNote()
	 * @model
	 * @generated
	 */
	String getRoleNote();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getRoleNote <em>Role Note</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Note</em>' attribute.
	 * @see #getRoleNote()
	 * @generated
	 */
	void setRoleNote(String value);

	/**
	 * Returns the value of the '<em><b>Containment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containment</em>' attribute.
	 * @see #setContainment(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_Containment()
	 * @model
	 * @generated
	 */
	String getContainment();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getContainment <em>Containment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containment</em>' attribute.
	 * @see #getContainment()
	 * @generated
	 */
	void setContainment(String value);

	/**
	 * Returns the value of the '<em><b>Aggregation</b></em>' attribute.
	 * The literals are from the enumeration {@link eaadapter.datatypes.T_ConnectorAggregation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aggregation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aggregation</em>' attribute.
	 * @see eaadapter.datatypes.T_ConnectorAggregation
	 * @see #setAggregation(T_ConnectorAggregation)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_Aggregation()
	 * @model
	 * @generated
	 */
	T_ConnectorAggregation getAggregation();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getAggregation <em>Aggregation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aggregation</em>' attribute.
	 * @see eaadapter.datatypes.T_ConnectorAggregation
	 * @see #getAggregation()
	 * @generated
	 */
	void setAggregation(T_ConnectorAggregation value);

	/**
	 * Returns the value of the '<em><b>Ordering</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordering</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordering</em>' attribute.
	 * @see #setOrdering(long)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_Ordering()
	 * @model
	 * @generated
	 */
	long getOrdering();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getOrdering <em>Ordering</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ordering</em>' attribute.
	 * @see #getOrdering()
	 * @generated
	 */
	void setOrdering(long value);

	/**
	 * Returns the value of the '<em><b>Qualifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualifier</em>' attribute.
	 * @see #setQualifier(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_Qualifier()
	 * @model
	 * @generated
	 */
	String getQualifier();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getQualifier <em>Qualifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualifier</em>' attribute.
	 * @see #getQualifier()
	 * @generated
	 */
	void setQualifier(String value);

	/**
	 * Returns the value of the '<em><b>Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint</em>' attribute.
	 * @see #setConstraint(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_Constraint()
	 * @model
	 * @generated
	 */
	String getConstraint();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getConstraint <em>Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint</em>' attribute.
	 * @see #getConstraint()
	 * @generated
	 */
	void setConstraint(String value);

	/**
	 * Returns the value of the '<em><b>Is Navigable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Navigable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Navigable</em>' attribute.
	 * @see #setIsNavigable(boolean)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_IsNavigable()
	 * @model
	 * @generated
	 */
	boolean isIsNavigable();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#isIsNavigable <em>Is Navigable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Navigable</em>' attribute.
	 * @see #isIsNavigable()
	 * @generated
	 */
	void setIsNavigable(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Changable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Changable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Changable</em>' attribute.
	 * @see #setIsChangable(String)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_IsChangable()
	 * @model
	 * @generated
	 */
	String getIsChangable();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getIsChangable <em>Is Changable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Changable</em>' attribute.
	 * @see #getIsChangable()
	 * @generated
	 */
	void setIsChangable(String value);

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ea Link</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(ConnectorEnd)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_EaLink()
	 * @model dataType="eaadapter.datatypes.T_ConnectorEnd" transient="true"
	 * @generated
	 */
	ConnectorEnd getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#getEaLink <em>Ea Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(ConnectorEnd value);

	/**
	 * Returns the value of the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived</em>' attribute.
	 * @see #setDerived(boolean)
	 * @see eaadapter.EaadapterPackage#getEAConnectorEnd_Derived()
	 * @model
	 * @generated
	 */
	boolean isDerived();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnectorEnd#isDerived <em>Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Derived</em>' attribute.
	 * @see #isDerived()
	 * @generated
	 */
	void setDerived(boolean value);

} // EAConnectorEnd
