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
import org.sparx.Connector;

import eaadapter.abstracthierachy.EATypedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EA Connector</b></em>'. <!-- end-user-doc -->
 * <!-- begin-model-doc --> <div class='userdoc'> A connector represents an association between two EAElements.<br>
 * The type defines what kind of connector it is. Examples are: <code>Association, Generalization, Dependency</code>.<br>
 * <br>
 * The <code>connector</code> has a <code>client</code> (source) and a <code>supplier</code> (target). <i>For more
 * detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html' target='_blank'>EA User
 * Guide</a>!</i> </div> <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eaadapter.EAConnector#getConnectorID <em>Connector ID</em>}</li>
 * <li>{@link eaadapter.EAConnector#getDirection <em>Direction</em>}</li>
 * <li>{@link eaadapter.EAConnector#getSubtype <em>Subtype</em>}</li>
 * <li>{@link eaadapter.EAConnector#getIsLeaf <em>Is Leaf</em>}</li>
 * <li>{@link eaadapter.EAConnector#getIsRoot <em>Is Root</em>}</li>
 * <li>{@link eaadapter.EAConnector#getIsSpec <em>Is Spec</em>}</li>
 * <li>{@link eaadapter.EAConnector#getVirtualInheritance <em>Virtual Inheritance</em>}</li>
 * <li>{@link eaadapter.EAConnector#getTransitionEvent <em>Transition Event</em>}</li>
 * <li>{@link eaadapter.EAConnector#getTransitionAction <em>Transition Action</em>}</li>
 * <li>{@link eaadapter.EAConnector#getTransitionGuard <em>Transition Guard</em>}</li>
 * <li>{@link eaadapter.EAConnector#getRouteStyle <em>Route Style</em>}</li>
 * <li>{@link eaadapter.EAConnector#getSequenceNo <em>Sequence No</em>}</li>
 * <li>{@link eaadapter.EAConnector#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.EAConnector#getTaggedValues <em>Tagged Values</em>}</li>
 * <li>{@link eaadapter.EAConnector#getClient <em>Client</em>}</li>
 * <li>{@link eaadapter.EAConnector#getSupplier <em>Supplier</em>}</li>
 * <li>{@link eaadapter.EAConnector#getConstraints <em>Constraints</em>}</li>
 * <li>{@link eaadapter.EAConnector#getClientEnd <em>Client End</em>}</li>
 * <li>{@link eaadapter.EAConnector#getClientID <em>Client ID</em>}</li>
 * <li>{@link eaadapter.EAConnector#getSupplierID <em>Supplier ID</em>}</li>
 * <li>{@link eaadapter.EAConnector#getEventFlags <em>Event Flags</em>}</li>
 * <li>{@link eaadapter.EAConnector#getStyleEx <em>Style Ex</em>}</li>
 * <li>{@link eaadapter.EAConnector#getSupplierEnd <em>Supplier End</em>}</li>
 * <li>{@link eaadapter.EAConnector#isDerived <em>Derived</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eaadapter.EaadapterPackage#getEAConnector()
 * @model
 * @generated
 */
public interface EAConnector extends EATypedElement {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Connector ID</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector ID</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Connector ID</em>' attribute.
	 * @see #setConnectorID(Integer)
	 * @see eaadapter.EaadapterPackage#getEAConnector_ConnectorID()
	 * @model
	 * @generated
	 */
	Integer getConnectorID();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getConnectorID <em>Connector ID</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Connector ID</em>' attribute.
	 * @see #getConnectorID()
	 * @generated
	 */
	void setConnectorID(Integer value);

	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see #setDirection(String)
	 * @see eaadapter.EaadapterPackage#getEAConnector_Direction()
	 * @model
	 * @generated
	 */
	String getDirection();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getDirection <em>Direction</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Direction</em>' attribute.
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(String value);

	/**
	 * Returns the value of the '<em><b>Subtype</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subtype</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Subtype</em>' attribute.
	 * @see #setSubtype(String)
	 * @see eaadapter.EaadapterPackage#getEAConnector_Subtype()
	 * @model
	 * @generated
	 */
	String getSubtype();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getSubtype <em>Subtype</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Subtype</em>' attribute.
	 * @see #getSubtype()
	 * @generated
	 */
	void setSubtype(String value);

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
	 * @see eaadapter.EaadapterPackage#getEAConnector_IsLeaf()
	 * @model
	 * @generated
	 */
	Boolean getIsLeaf();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getIsLeaf <em>Is Leaf</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Leaf</em>' attribute.
	 * @see #getIsLeaf()
	 * @generated
	 */
	void setIsLeaf(Boolean value);

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
	 * @see eaadapter.EaadapterPackage#getEAConnector_IsRoot()
	 * @model
	 * @generated
	 */
	Boolean getIsRoot();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getIsRoot <em>Is Root</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Root</em>' attribute.
	 * @see #getIsRoot()
	 * @generated
	 */
	void setIsRoot(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Spec</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Spec</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Is Spec</em>' attribute.
	 * @see #setIsSpec(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAConnector_IsSpec()
	 * @model
	 * @generated
	 */
	Boolean getIsSpec();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getIsSpec <em>Is Spec</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Spec</em>' attribute.
	 * @see #getIsSpec()
	 * @generated
	 */
	void setIsSpec(Boolean value);

	/**
	 * Returns the value of the '<em><b>Virtual Inheritance</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Virtual Inheritance</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Virtual Inheritance</em>' attribute.
	 * @see #setVirtualInheritance(String)
	 * @see eaadapter.EaadapterPackage#getEAConnector_VirtualInheritance()
	 * @model
	 * @generated
	 */
	String getVirtualInheritance();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getVirtualInheritance <em>Virtual Inheritance</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Virtual Inheritance</em>' attribute.
	 * @see #getVirtualInheritance()
	 * @generated
	 */
	void setVirtualInheritance(String value);

	/**
	 * Returns the value of the '<em><b>Transition Event</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Event</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Transition Event</em>' attribute.
	 * @see #setTransitionEvent(String)
	 * @see eaadapter.EaadapterPackage#getEAConnector_TransitionEvent()
	 * @model
	 * @generated
	 */
	String getTransitionEvent();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getTransitionEvent <em>Transition Event</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Transition Event</em>' attribute.
	 * @see #getTransitionEvent()
	 * @generated
	 */
	void setTransitionEvent(String value);

	/**
	 * Returns the value of the '<em><b>Transition Action</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Action</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Transition Action</em>' attribute.
	 * @see #setTransitionAction(String)
	 * @see eaadapter.EaadapterPackage#getEAConnector_TransitionAction()
	 * @model
	 * @generated
	 */
	String getTransitionAction();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getTransitionAction <em>Transition Action</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Transition Action</em>' attribute.
	 * @see #getTransitionAction()
	 * @generated
	 */
	void setTransitionAction(String value);

	/**
	 * Returns the value of the '<em><b>Transition Guard</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Guard</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Transition Guard</em>' attribute.
	 * @see #setTransitionGuard(String)
	 * @see eaadapter.EaadapterPackage#getEAConnector_TransitionGuard()
	 * @model
	 * @generated
	 */
	String getTransitionGuard();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getTransitionGuard <em>Transition Guard</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Transition Guard</em>' attribute.
	 * @see #getTransitionGuard()
	 * @generated
	 */
	void setTransitionGuard(String value);

	/**
	 * Returns the value of the '<em><b>Route Style</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Route Style</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Route Style</em>' attribute.
	 * @see #setRouteStyle(Integer)
	 * @see eaadapter.EaadapterPackage#getEAConnector_RouteStyle()
	 * @model
	 * @generated
	 */
	Integer getRouteStyle();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getRouteStyle <em>Route Style</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Route Style</em>' attribute.
	 * @see #getRouteStyle()
	 * @generated
	 */
	void setRouteStyle(Integer value);

	/**
	 * Returns the value of the '<em><b>Sequence No</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequence No</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Sequence No</em>' attribute.
	 * @see #setSequenceNo(Integer)
	 * @see eaadapter.EaadapterPackage#getEAConnector_SequenceNo()
	 * @model
	 * @generated
	 */
	Integer getSequenceNo();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getSequenceNo <em>Sequence No</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Sequence No</em>' attribute.
	 * @see #getSequenceNo()
	 * @generated
	 */
	void setSequenceNo(Integer value);

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> <div class='userdoc'> DO NOT TOUCH THIS! This attributed is used as a link to the Enterprise
	 * Architect and is managed internally only! </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(Connector)
	 * @see eaadapter.EaadapterPackage#getEAConnector_EaLink()
	 * @model dataType="eaadapter.datatypes.T_Connector" transient="true"
	 * @generated
	 */
	Connector getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getEaLink <em>Ea Link</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(Connector value);

	/**
	 * Returns the value of the '<em><b>Tagged Values</b></em>' containment reference list. The list contents are of
	 * type {@link eaadapter.EAConnectorTag}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tagged Values</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Tagged Values</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAConnector_TaggedValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<EAConnectorTag> getTaggedValues();

	/**
	 * Returns the value of the '<em><b>Client</b></em>' container reference. It is bidirectional and its opposite is '
	 * {@link eaadapter.EAElement#getConnectors <em>Connectors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc --> <div class='userdoc'> Reference to the target EAElement of this connector.<br>
	 * <b>Note:</b> Take care that this reference will always be valid, otherwise the EA takes care of it and 'garbage
	 * collects' it. </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Client</em>' container reference.
	 * @see #setClient(EAElement)
	 * @see eaadapter.EaadapterPackage#getEAConnector_Client()
	 * @see eaadapter.EAElement#getConnectors
	 * @model opposite="connectors" unsettable="true" required="true" transient="false"
	 * @generated
	 */
	EAElement getClient();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getClient <em>Client</em>}' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Client</em>' container reference.
	 * @see #getClient()
	 * @generated
	 */
	void setClient(EAElement value);

	/**
	 * Returns the value of the '<em><b>Supplier</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supplier</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Supplier</em>' reference.
	 * @see #setSupplier(EAElement)
	 * @see eaadapter.EaadapterPackage#getEAConnector_Supplier()
	 * @model required="true"
	 * @generated
	 */
	EAElement getSupplier();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getSupplier <em>Supplier</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Supplier</em>' reference.
	 * @see #getSupplier()
	 * @generated
	 */
	void setSupplier(EAElement value);

	/**
	 * Returns the value of the '<em><b>Constraints</b></em>' containment reference list. The list contents are of type
	 * {@link eaadapter.EAConnectorConstraint}. It is bidirectional and its opposite is '
	 * {@link eaadapter.EAConnectorConstraint#getConnector <em>Connector</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Constraints</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAConnector_Constraints()
	 * @see eaadapter.EAConnectorConstraint#getConnector
	 * @model opposite="connector" containment="true"
	 * @generated
	 */
	EList<EAConnectorConstraint> getConstraints();

	/**
	 * Returns the value of the '<em><b>Client End</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Client End</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Client End</em>' containment reference.
	 * @see #setClientEnd(EAConnectorEnd)
	 * @see eaadapter.EaadapterPackage#getEAConnector_ClientEnd()
	 * @model containment="true"
	 * @generated
	 */
	EAConnectorEnd getClientEnd();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getClientEnd <em>Client End</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Client End</em>' containment reference.
	 * @see #getClientEnd()
	 * @generated
	 */
	void setClientEnd(EAConnectorEnd value);

	/**
	 * Returns the value of the '<em><b>Client ID</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Client ID</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Client ID</em>' attribute.
	 * @see #setClientID(long)
	 * @see eaadapter.EaadapterPackage#getEAConnector_ClientID()
	 * @model
	 * @generated
	 */
	long getClientID();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getClientID <em>Client ID</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Client ID</em>' attribute.
	 * @see #getClientID()
	 * @generated
	 */
	void setClientID(long value);

	/**
	 * Returns the value of the '<em><b>Supplier ID</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supplier ID</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Supplier ID</em>' attribute.
	 * @see #setSupplierID(long)
	 * @see eaadapter.EaadapterPackage#getEAConnector_SupplierID()
	 * @model
	 * @generated
	 */
	long getSupplierID();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getSupplierID <em>Supplier ID</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Supplier ID</em>' attribute.
	 * @see #getSupplierID()
	 * @generated
	 */
	void setSupplierID(long value);

	/**
	 * Returns the value of the '<em><b>Event Flags</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Flags</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Event Flags</em>' attribute.
	 * @see #setEventFlags(String)
	 * @see eaadapter.EaadapterPackage#getEAConnector_EventFlags()
	 * @model
	 * @generated
	 */
	String getEventFlags();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getEventFlags <em>Event Flags</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Event Flags</em>' attribute.
	 * @see #getEventFlags()
	 * @generated
	 */
	void setEventFlags(String value);

	/**
	 * Returns the value of the '<em><b>Style Ex</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style Ex</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Style Ex</em>' attribute.
	 * @see #setStyleEx(String)
	 * @see eaadapter.EaadapterPackage#getEAConnector_StyleEx()
	 * @model
	 * @generated
	 */
	String getStyleEx();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getStyleEx <em>Style Ex</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Style Ex</em>' attribute.
	 * @see #getStyleEx()
	 * @generated
	 */
	void setStyleEx(String value);

	/**
	 * Returns the value of the '<em><b>Supplier End</b></em>' containment reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supplier End</em>' containment reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Supplier End</em>' containment reference.
	 * @see #setSupplierEnd(EAConnectorEnd)
	 * @see eaadapter.EaadapterPackage#getEAConnector_SupplierEnd()
	 * @model containment="true"
	 * @generated
	 */
	EAConnectorEnd getSupplierEnd();

	/**
	 * Sets the value of the '{@link eaadapter.EAConnector#getSupplierEnd <em>Supplier End</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Supplier End</em>' containment reference.
	 * @see #getSupplierEnd()
	 * @generated
	 */
	void setSupplierEnd(EAConnectorEnd value);

	/**
	 * Returns the value of the '<em><b>Derived</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Derived</em>' attribute.
	 * @see org.eclipse.eatop.eaadapter.EaadapterPackage#getEAConnector_Derived()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isDerived();

} // EAConnector
