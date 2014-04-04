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
package eaadapter.impl;

import eaadapter.EAConnector;
import eaadapter.EAConnectorConstraint;
import eaadapter.EAConnectorEnd;
import eaadapter.EAConnectorTag;
import eaadapter.EAElement;
import eaadapter.EaadapterPackage;

import eaadapter.abstracthierachy.util.AbstracthierachyValidator;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.sparx.Connector;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EA Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getName <em>Name</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getNotes <em>Notes</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getGuid <em>Guid</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getId <em>Id</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getStereotype <em>Stereotype</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getStereotypeEx <em>Stereotype Ex</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getType <em>Type</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getConnectorID <em>Connector ID</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getSubtype <em>Subtype</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getIsLeaf <em>Is Leaf</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getIsRoot <em>Is Root</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getIsSpec <em>Is Spec</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getVirtualInheritance <em>Virtual Inheritance</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getTransitionEvent <em>Transition Event</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getTransitionAction <em>Transition Action</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getTransitionGuard <em>Transition Guard</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getRouteStyle <em>Route Style</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getSequenceNo <em>Sequence No</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getEaLink <em>Ea Link</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getTaggedValues <em>Tagged Values</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getClient <em>Client</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getSupplier <em>Supplier</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getConstraints <em>Constraints</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getClientEnd <em>Client End</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getClientID <em>Client ID</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getSupplierID <em>Supplier ID</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getEventFlags <em>Event Flags</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getStyleEx <em>Style Ex</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#getSupplierEnd <em>Supplier End</em>}</li>
 *   <li>{@link eaadapter.impl.EAConnectorImpl#isDerived <em>Derived</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EAConnectorImpl extends EObjectImpl implements EAConnector {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "name";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNotes() <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotes()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNotes() <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNotes()
	 * @generated
	 * @ordered
	 */
	protected String notes = NOTES_EDEFAULT;

	/**
	 * The default value of the '{@link #getGuid() <em>Guid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuid()
	 * @generated
	 * @ordered
	 */
	protected static final String GUID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGuid() <em>Guid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuid()
	 * @generated
	 * @ordered
	 */
	protected String guid = GUID_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final long ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected long id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotype()
	 * @generated
	 * @ordered
	 */
	protected static final String STEREOTYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotype()
	 * @generated
	 * @ordered
	 */
	protected String stereotype = STEREOTYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStereotypeEx() <em>Stereotype Ex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotypeEx()
	 * @generated
	 * @ordered
	 */
	protected static final String STEREOTYPE_EX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStereotypeEx() <em>Stereotype Ex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStereotypeEx()
	 * @generated
	 * @ordered
	 */
	protected String stereotypeEx = STEREOTYPE_EX_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectorID() <em>Connector ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorID()
	 * @generated
	 * @ordered
	 */
	protected static final Integer CONNECTOR_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectorID() <em>Connector ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorID()
	 * @generated
	 * @ordered
	 */
	protected Integer connectorID = CONNECTOR_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final String DIRECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected String direction = DIRECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSubtype() <em>Subtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubtype()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBTYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubtype() <em>Subtype</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubtype()
	 * @generated
	 * @ordered
	 */
	protected String subtype = SUBTYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsLeaf() <em>Is Leaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsLeaf()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_LEAF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsLeaf() <em>Is Leaf</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsLeaf()
	 * @generated
	 * @ordered
	 */
	protected Boolean isLeaf = IS_LEAF_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsRoot() <em>Is Root</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsRoot()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ROOT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsRoot() <em>Is Root</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsRoot()
	 * @generated
	 * @ordered
	 */
	protected Boolean isRoot = IS_ROOT_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsSpec() <em>Is Spec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSpec()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_SPEC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsSpec() <em>Is Spec</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsSpec()
	 * @generated
	 * @ordered
	 */
	protected Boolean isSpec = IS_SPEC_EDEFAULT;

	/**
	 * The default value of the '{@link #getVirtualInheritance() <em>Virtual Inheritance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVirtualInheritance()
	 * @generated
	 * @ordered
	 */
	protected static final String VIRTUAL_INHERITANCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVirtualInheritance() <em>Virtual Inheritance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVirtualInheritance()
	 * @generated
	 * @ordered
	 */
	protected String virtualInheritance = VIRTUAL_INHERITANCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransitionEvent() <em>Transition Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionEvent()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSITION_EVENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransitionEvent() <em>Transition Event</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionEvent()
	 * @generated
	 * @ordered
	 */
	protected String transitionEvent = TRANSITION_EVENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransitionAction() <em>Transition Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionAction()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSITION_ACTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransitionAction() <em>Transition Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionAction()
	 * @generated
	 * @ordered
	 */
	protected String transitionAction = TRANSITION_ACTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransitionGuard() <em>Transition Guard</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionGuard()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSITION_GUARD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransitionGuard() <em>Transition Guard</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionGuard()
	 * @generated
	 * @ordered
	 */
	protected String transitionGuard = TRANSITION_GUARD_EDEFAULT;

	/**
	 * The default value of the '{@link #getRouteStyle() <em>Route Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRouteStyle()
	 * @generated
	 * @ordered
	 */
	protected static final Integer ROUTE_STYLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRouteStyle() <em>Route Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRouteStyle()
	 * @generated
	 * @ordered
	 */
	protected Integer routeStyle = ROUTE_STYLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSequenceNo() <em>Sequence No</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequenceNo()
	 * @generated
	 * @ordered
	 */
	protected static final Integer SEQUENCE_NO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSequenceNo() <em>Sequence No</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequenceNo()
	 * @generated
	 * @ordered
	 */
	protected Integer sequenceNo = SEQUENCE_NO_EDEFAULT;

	/**
	 * The default value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected static final Connector EA_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected Connector eaLink = EA_LINK_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTaggedValues() <em>Tagged Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTaggedValues()
	 * @generated
	 * @ordered
	 */
	protected EList<EAConnectorTag> taggedValues;

	/**
	 * The cached value of the '{@link #getSupplier() <em>Supplier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupplier()
	 * @generated
	 * @ordered
	 */
	protected EAElement supplier;

	/**
	 * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<EAConnectorConstraint> constraints;

	/**
	 * The cached value of the '{@link #getClientEnd() <em>Client End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientEnd()
	 * @generated
	 * @ordered
	 */
	protected EAConnectorEnd clientEnd;

	/**
	 * The default value of the '{@link #getClientID() <em>Client ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientID()
	 * @generated
	 * @ordered
	 */
	protected static final long CLIENT_ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getClientID() <em>Client ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientID()
	 * @generated
	 * @ordered
	 */
	protected long clientID = CLIENT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getSupplierID() <em>Supplier ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupplierID()
	 * @generated
	 * @ordered
	 */
	protected static final long SUPPLIER_ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getSupplierID() <em>Supplier ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupplierID()
	 * @generated
	 * @ordered
	 */
	protected long supplierID = SUPPLIER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getEventFlags() <em>Event Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventFlags()
	 * @generated
	 * @ordered
	 */
	protected static final String EVENT_FLAGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEventFlags() <em>Event Flags</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEventFlags()
	 * @generated
	 * @ordered
	 */
	protected String eventFlags = EVENT_FLAGS_EDEFAULT;

	/**
	 * The default value of the '{@link #getStyleEx() <em>Style Ex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyleEx()
	 * @generated
	 * @ordered
	 */
	protected static final String STYLE_EX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStyleEx() <em>Style Ex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyleEx()
	 * @generated
	 * @ordered
	 */
	protected String styleEx = STYLE_EX_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSupplierEnd() <em>Supplier End</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupplierEnd()
	 * @generated
	 * @ordered
	 */
	protected EAConnectorEnd supplierEnd;

	/**
	 * The default value of the '{@link #isDerived() <em>Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDerived()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DERIVED_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EAConnectorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EaadapterPackage.Literals.EA_CONNECTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNotes(String newNotes) {
		String oldNotes = notes;
		notes = newNotes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__NOTES, oldNotes, notes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGuid(String newGuid) {
		String oldGuid = guid;
		guid = newGuid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__GUID, oldGuid, guid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(long newId) {
		long oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStereotype() {
		return stereotype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStereotype(String newStereotype) {
		String oldStereotype = stereotype;
		stereotype = newStereotype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__STEREOTYPE, oldStereotype, stereotype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStereotypeEx() {
		return stereotypeEx;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStereotypeEx(String newStereotypeEx) {
		String oldStereotypeEx = stereotypeEx;
		stereotypeEx = newStereotypeEx;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__STEREOTYPE_EX, oldStereotypeEx, stereotypeEx));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getConnectorID() {
		return connectorID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorID(Integer newConnectorID) {
		Integer oldConnectorID = connectorID;
		connectorID = newConnectorID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__CONNECTOR_ID, oldConnectorID, connectorID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirection(String newDirection) {
		String oldDirection = direction;
		direction = newDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__DIRECTION, oldDirection, direction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubtype() {
		return subtype;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubtype(String newSubtype) {
		String oldSubtype = subtype;
		subtype = newSubtype;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__SUBTYPE, oldSubtype, subtype));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsLeaf() {
		return isLeaf;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsLeaf(Boolean newIsLeaf) {
		Boolean oldIsLeaf = isLeaf;
		isLeaf = newIsLeaf;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__IS_LEAF, oldIsLeaf, isLeaf));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsRoot() {
		return isRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsRoot(Boolean newIsRoot) {
		Boolean oldIsRoot = isRoot;
		isRoot = newIsRoot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__IS_ROOT, oldIsRoot, isRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getIsSpec() {
		return isSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSpec(Boolean newIsSpec) {
		Boolean oldIsSpec = isSpec;
		isSpec = newIsSpec;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__IS_SPEC, oldIsSpec, isSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVirtualInheritance() {
		return virtualInheritance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVirtualInheritance(String newVirtualInheritance) {
		String oldVirtualInheritance = virtualInheritance;
		virtualInheritance = newVirtualInheritance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__VIRTUAL_INHERITANCE, oldVirtualInheritance, virtualInheritance));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransitionEvent() {
		return transitionEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitionEvent(String newTransitionEvent) {
		String oldTransitionEvent = transitionEvent;
		transitionEvent = newTransitionEvent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__TRANSITION_EVENT, oldTransitionEvent, transitionEvent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransitionAction() {
		return transitionAction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitionAction(String newTransitionAction) {
		String oldTransitionAction = transitionAction;
		transitionAction = newTransitionAction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__TRANSITION_ACTION, oldTransitionAction, transitionAction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransitionGuard() {
		return transitionGuard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitionGuard(String newTransitionGuard) {
		String oldTransitionGuard = transitionGuard;
		transitionGuard = newTransitionGuard;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__TRANSITION_GUARD, oldTransitionGuard, transitionGuard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getRouteStyle() {
		return routeStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRouteStyle(Integer newRouteStyle) {
		Integer oldRouteStyle = routeStyle;
		routeStyle = newRouteStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__ROUTE_STYLE, oldRouteStyle, routeStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getSequenceNo() {
		return sequenceNo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSequenceNo(Integer newSequenceNo) {
		Integer oldSequenceNo = sequenceNo;
		sequenceNo = newSequenceNo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__SEQUENCE_NO, oldSequenceNo, sequenceNo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connector getEaLink() {
		return eaLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEaLink(Connector newEaLink) {
		Connector oldEaLink = eaLink;
		eaLink = newEaLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__EA_LINK, oldEaLink, eaLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EAConnectorTag> getTaggedValues() {
		if (taggedValues == null) {
			taggedValues = new EObjectContainmentEList<EAConnectorTag>(EAConnectorTag.class, this, EaadapterPackage.EA_CONNECTOR__TAGGED_VALUES);
		}
		return taggedValues;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAElement getClient() {
		if (eContainerFeatureID() != EaadapterPackage.EA_CONNECTOR__CLIENT) return null;
		return (EAElement)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClient(EAElement newClient, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newClient, EaadapterPackage.EA_CONNECTOR__CLIENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClient(EAElement newClient) {
		if (newClient != eInternalContainer() || (eContainerFeatureID() != EaadapterPackage.EA_CONNECTOR__CLIENT && newClient != null)) {
			if (EcoreUtil.isAncestor(this, newClient))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newClient != null)
				msgs = ((InternalEObject)newClient).eInverseAdd(this, EaadapterPackage.EA_ELEMENT__CONNECTORS, EAElement.class, msgs);
			msgs = basicSetClient(newClient, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__CLIENT, newClient, newClient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAElement getSupplier() {
		if (supplier != null && supplier.eIsProxy()) {
			InternalEObject oldSupplier = (InternalEObject)supplier;
			supplier = (EAElement)eResolveProxy(oldSupplier);
			if (supplier != oldSupplier) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EaadapterPackage.EA_CONNECTOR__SUPPLIER, oldSupplier, supplier));
			}
		}
		return supplier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAElement basicGetSupplier() {
		return supplier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupplier(EAElement newSupplier) {
		EAElement oldSupplier = supplier;
		supplier = newSupplier;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__SUPPLIER, oldSupplier, supplier));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EAConnectorConstraint> getConstraints() {
		if (constraints == null) {
			constraints = new EObjectContainmentWithInverseEList<EAConnectorConstraint>(EAConnectorConstraint.class, this, EaadapterPackage.EA_CONNECTOR__CONSTRAINTS, EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR);
		}
		return constraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAConnectorEnd getClientEnd() {
		return clientEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetClientEnd(EAConnectorEnd newClientEnd, NotificationChain msgs) {
		EAConnectorEnd oldClientEnd = clientEnd;
		clientEnd = newClientEnd;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__CLIENT_END, oldClientEnd, newClientEnd);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClientEnd(EAConnectorEnd newClientEnd) {
		if (newClientEnd != clientEnd) {
			NotificationChain msgs = null;
			if (clientEnd != null)
				msgs = ((InternalEObject)clientEnd).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EaadapterPackage.EA_CONNECTOR__CLIENT_END, null, msgs);
			if (newClientEnd != null)
				msgs = ((InternalEObject)newClientEnd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EaadapterPackage.EA_CONNECTOR__CLIENT_END, null, msgs);
			msgs = basicSetClientEnd(newClientEnd, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__CLIENT_END, newClientEnd, newClientEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getClientID() {
		return clientID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClientID(long newClientID) {
		long oldClientID = clientID;
		clientID = newClientID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__CLIENT_ID, oldClientID, clientID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getSupplierID() {
		return supplierID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupplierID(long newSupplierID) {
		long oldSupplierID = supplierID;
		supplierID = newSupplierID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__SUPPLIER_ID, oldSupplierID, supplierID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEventFlags() {
		return eventFlags;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEventFlags(String newEventFlags) {
		String oldEventFlags = eventFlags;
		eventFlags = newEventFlags;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__EVENT_FLAGS, oldEventFlags, eventFlags));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStyleEx() {
		return styleEx;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyleEx(String newStyleEx) {
		String oldStyleEx = styleEx;
		styleEx = newStyleEx;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__STYLE_EX, oldStyleEx, styleEx));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAConnectorEnd getSupplierEnd() {
		return supplierEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSupplierEnd(EAConnectorEnd newSupplierEnd, NotificationChain msgs) {
		EAConnectorEnd oldSupplierEnd = supplierEnd;
		supplierEnd = newSupplierEnd;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__SUPPLIER_END, oldSupplierEnd, newSupplierEnd);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupplierEnd(EAConnectorEnd newSupplierEnd) {
		if (newSupplierEnd != supplierEnd) {
			NotificationChain msgs = null;
			if (supplierEnd != null)
				msgs = ((InternalEObject)supplierEnd).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EaadapterPackage.EA_CONNECTOR__SUPPLIER_END, null, msgs);
			if (newSupplierEnd != null)
				msgs = ((InternalEObject)newSupplierEnd).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EaadapterPackage.EA_CONNECTOR__SUPPLIER_END, null, msgs);
			msgs = basicSetSupplierEnd(newSupplierEnd, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR__SUPPLIER_END, newSupplierEnd, newSupplierEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT mnick
	 */
	public boolean isDerived() {
		if(this.getClientEnd().isDerived() == true) 
			return true;
		
		if (this.getSupplierEnd().isDerived() == true)
			return true;
		
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT mnick
	 */
	public boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context) {
		boolean isValid = true;
		
		/**
		 * Do this only for the SAFE MM
		 */
//		if (isAggregation(this) && EaadapterModelType.isSAFE(this)) {
//			if (!this.getDirection().equals("Unspecified")) {
//				if (diagnostic != null) {
//					diagnostic.add
//						(new BasicDiagnostic
//							(Diagnostic.WARNING,
//							 AbstracthierachyValidator.DIAGNOSTIC_SOURCE,
//							 AbstracthierachyValidator.EA_NAMED_ELEMENT__VALIDATE,
//							 String.format("The direction of an aggregation shall be 'Unspecified' but is '%s'", this.getDirection()),
//							 new Object [] { this }));
//				}
//				isValid = false;
//			}
//		}
		
		if (isAssociation(this)) {
			if (!(this.getDirection().equals("Source -> Destination") || this.getDirection().equals("Destination -> Source"))) {
				if (diagnostic != null) {
					diagnostic.add
						(new BasicDiagnostic
							(Diagnostic.WARNING,
							 AbstracthierachyValidator.DIAGNOSTIC_SOURCE,
							 AbstracthierachyValidator.EA_NAMED_ELEMENT__VALIDATE,
							 String.format("The direction of an association shall be 'Source -> Destination' but is '%s'", this.getDirection()),
							 new Object [] { this }));
				}
				isValid = false;
			}
		}		
				
		if (!isGeneralization(this) && connectorReferencesEnumeration(this)) {
			if (diagnostic != null) {
				diagnostic.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 AbstracthierachyValidator.DIAGNOSTIC_SOURCE,
						 AbstracthierachyValidator.EA_NAMED_ELEMENT__VALIDATE,
						 "An association can't reference an Enumeration",
						 new Object [] { this }));
			}
			isValid = false;
		}
		
		return isValid;
	}
	
	private boolean connectorReferencesEnumeration(EAConnectorImpl connector) {		
		if (connector.getClient().getStereotype().equalsIgnoreCase("enumeration"))
			return true;
		if (connector.getSupplier().getStereotype().equalsIgnoreCase("enumeration"))
			return true;
		
		return false;
	}

	private boolean isAssociation(EAConnector c) {
		if (c.getType().equals("Association"))
			return true;
		return false;
	}
	
	private boolean isAggregation(EAConnector c) {
		if (c.getType().equals("Aggregation"))
			return true;
		return false;
	}
	
	private boolean isGeneralization(EAConnector c) {
		if (c.getType().equals("Generalization"))
			return true;
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EaadapterPackage.EA_CONNECTOR__CLIENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetClient((EAElement)otherEnd, msgs);
			case EaadapterPackage.EA_CONNECTOR__CONSTRAINTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConstraints()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EaadapterPackage.EA_CONNECTOR__TAGGED_VALUES:
				return ((InternalEList<?>)getTaggedValues()).basicRemove(otherEnd, msgs);
			case EaadapterPackage.EA_CONNECTOR__CLIENT:
				return basicSetClient(null, msgs);
			case EaadapterPackage.EA_CONNECTOR__CONSTRAINTS:
				return ((InternalEList<?>)getConstraints()).basicRemove(otherEnd, msgs);
			case EaadapterPackage.EA_CONNECTOR__CLIENT_END:
				return basicSetClientEnd(null, msgs);
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER_END:
				return basicSetSupplierEnd(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case EaadapterPackage.EA_CONNECTOR__CLIENT:
				return eInternalContainer().eInverseRemove(this, EaadapterPackage.EA_ELEMENT__CONNECTORS, EAElement.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EaadapterPackage.EA_CONNECTOR__NAME:
				return getName();
			case EaadapterPackage.EA_CONNECTOR__NOTES:
				return getNotes();
			case EaadapterPackage.EA_CONNECTOR__GUID:
				return getGuid();
			case EaadapterPackage.EA_CONNECTOR__ID:
				return getId();
			case EaadapterPackage.EA_CONNECTOR__STEREOTYPE:
				return getStereotype();
			case EaadapterPackage.EA_CONNECTOR__STEREOTYPE_EX:
				return getStereotypeEx();
			case EaadapterPackage.EA_CONNECTOR__TYPE:
				return getType();
			case EaadapterPackage.EA_CONNECTOR__CONNECTOR_ID:
				return getConnectorID();
			case EaadapterPackage.EA_CONNECTOR__DIRECTION:
				return getDirection();
			case EaadapterPackage.EA_CONNECTOR__SUBTYPE:
				return getSubtype();
			case EaadapterPackage.EA_CONNECTOR__IS_LEAF:
				return getIsLeaf();
			case EaadapterPackage.EA_CONNECTOR__IS_ROOT:
				return getIsRoot();
			case EaadapterPackage.EA_CONNECTOR__IS_SPEC:
				return getIsSpec();
			case EaadapterPackage.EA_CONNECTOR__VIRTUAL_INHERITANCE:
				return getVirtualInheritance();
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_EVENT:
				return getTransitionEvent();
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_ACTION:
				return getTransitionAction();
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_GUARD:
				return getTransitionGuard();
			case EaadapterPackage.EA_CONNECTOR__ROUTE_STYLE:
				return getRouteStyle();
			case EaadapterPackage.EA_CONNECTOR__SEQUENCE_NO:
				return getSequenceNo();
			case EaadapterPackage.EA_CONNECTOR__EA_LINK:
				return getEaLink();
			case EaadapterPackage.EA_CONNECTOR__TAGGED_VALUES:
				return getTaggedValues();
			case EaadapterPackage.EA_CONNECTOR__CLIENT:
				return getClient();
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER:
				if (resolve) return getSupplier();
				return basicGetSupplier();
			case EaadapterPackage.EA_CONNECTOR__CONSTRAINTS:
				return getConstraints();
			case EaadapterPackage.EA_CONNECTOR__CLIENT_END:
				return getClientEnd();
			case EaadapterPackage.EA_CONNECTOR__CLIENT_ID:
				return getClientID();
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER_ID:
				return getSupplierID();
			case EaadapterPackage.EA_CONNECTOR__EVENT_FLAGS:
				return getEventFlags();
			case EaadapterPackage.EA_CONNECTOR__STYLE_EX:
				return getStyleEx();
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER_END:
				return getSupplierEnd();
			case EaadapterPackage.EA_CONNECTOR__DERIVED:
				return isDerived();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EaadapterPackage.EA_CONNECTOR__NAME:
				setName((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__NOTES:
				setNotes((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__GUID:
				setGuid((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__ID:
				setId((Long)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__STEREOTYPE:
				setStereotype((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__STEREOTYPE_EX:
				setStereotypeEx((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__TYPE:
				setType((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__CONNECTOR_ID:
				setConnectorID((Integer)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__DIRECTION:
				setDirection((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__SUBTYPE:
				setSubtype((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__IS_LEAF:
				setIsLeaf((Boolean)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__IS_ROOT:
				setIsRoot((Boolean)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__IS_SPEC:
				setIsSpec((Boolean)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__VIRTUAL_INHERITANCE:
				setVirtualInheritance((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_EVENT:
				setTransitionEvent((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_ACTION:
				setTransitionAction((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_GUARD:
				setTransitionGuard((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__ROUTE_STYLE:
				setRouteStyle((Integer)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__SEQUENCE_NO:
				setSequenceNo((Integer)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__EA_LINK:
				setEaLink((Connector)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__TAGGED_VALUES:
				getTaggedValues().clear();
				getTaggedValues().addAll((Collection<? extends EAConnectorTag>)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__CLIENT:
				setClient((EAElement)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER:
				setSupplier((EAElement)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__CONSTRAINTS:
				getConstraints().clear();
				getConstraints().addAll((Collection<? extends EAConnectorConstraint>)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__CLIENT_END:
				setClientEnd((EAConnectorEnd)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__CLIENT_ID:
				setClientID((Long)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER_ID:
				setSupplierID((Long)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__EVENT_FLAGS:
				setEventFlags((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__STYLE_EX:
				setStyleEx((String)newValue);
				return;
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER_END:
				setSupplierEnd((EAConnectorEnd)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EaadapterPackage.EA_CONNECTOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__NOTES:
				setNotes(NOTES_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__GUID:
				setGuid(GUID_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__ID:
				setId(ID_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__STEREOTYPE:
				setStereotype(STEREOTYPE_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__STEREOTYPE_EX:
				setStereotypeEx(STEREOTYPE_EX_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__CONNECTOR_ID:
				setConnectorID(CONNECTOR_ID_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__SUBTYPE:
				setSubtype(SUBTYPE_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__IS_LEAF:
				setIsLeaf(IS_LEAF_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__IS_ROOT:
				setIsRoot(IS_ROOT_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__IS_SPEC:
				setIsSpec(IS_SPEC_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__VIRTUAL_INHERITANCE:
				setVirtualInheritance(VIRTUAL_INHERITANCE_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_EVENT:
				setTransitionEvent(TRANSITION_EVENT_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_ACTION:
				setTransitionAction(TRANSITION_ACTION_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_GUARD:
				setTransitionGuard(TRANSITION_GUARD_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__ROUTE_STYLE:
				setRouteStyle(ROUTE_STYLE_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__SEQUENCE_NO:
				setSequenceNo(SEQUENCE_NO_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__EA_LINK:
				setEaLink(EA_LINK_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__TAGGED_VALUES:
				getTaggedValues().clear();
				return;
			case EaadapterPackage.EA_CONNECTOR__CLIENT:
				setClient((EAElement)null);
				return;
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER:
				setSupplier((EAElement)null);
				return;
			case EaadapterPackage.EA_CONNECTOR__CONSTRAINTS:
				getConstraints().clear();
				return;
			case EaadapterPackage.EA_CONNECTOR__CLIENT_END:
				setClientEnd((EAConnectorEnd)null);
				return;
			case EaadapterPackage.EA_CONNECTOR__CLIENT_ID:
				setClientID(CLIENT_ID_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER_ID:
				setSupplierID(SUPPLIER_ID_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__EVENT_FLAGS:
				setEventFlags(EVENT_FLAGS_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__STYLE_EX:
				setStyleEx(STYLE_EX_EDEFAULT);
				return;
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER_END:
				setSupplierEnd((EAConnectorEnd)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EaadapterPackage.EA_CONNECTOR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EaadapterPackage.EA_CONNECTOR__NOTES:
				return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
			case EaadapterPackage.EA_CONNECTOR__GUID:
				return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
			case EaadapterPackage.EA_CONNECTOR__ID:
				return id != ID_EDEFAULT;
			case EaadapterPackage.EA_CONNECTOR__STEREOTYPE:
				return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
			case EaadapterPackage.EA_CONNECTOR__STEREOTYPE_EX:
				return STEREOTYPE_EX_EDEFAULT == null ? stereotypeEx != null : !STEREOTYPE_EX_EDEFAULT.equals(stereotypeEx);
			case EaadapterPackage.EA_CONNECTOR__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case EaadapterPackage.EA_CONNECTOR__CONNECTOR_ID:
				return CONNECTOR_ID_EDEFAULT == null ? connectorID != null : !CONNECTOR_ID_EDEFAULT.equals(connectorID);
			case EaadapterPackage.EA_CONNECTOR__DIRECTION:
				return DIRECTION_EDEFAULT == null ? direction != null : !DIRECTION_EDEFAULT.equals(direction);
			case EaadapterPackage.EA_CONNECTOR__SUBTYPE:
				return SUBTYPE_EDEFAULT == null ? subtype != null : !SUBTYPE_EDEFAULT.equals(subtype);
			case EaadapterPackage.EA_CONNECTOR__IS_LEAF:
				return IS_LEAF_EDEFAULT == null ? isLeaf != null : !IS_LEAF_EDEFAULT.equals(isLeaf);
			case EaadapterPackage.EA_CONNECTOR__IS_ROOT:
				return IS_ROOT_EDEFAULT == null ? isRoot != null : !IS_ROOT_EDEFAULT.equals(isRoot);
			case EaadapterPackage.EA_CONNECTOR__IS_SPEC:
				return IS_SPEC_EDEFAULT == null ? isSpec != null : !IS_SPEC_EDEFAULT.equals(isSpec);
			case EaadapterPackage.EA_CONNECTOR__VIRTUAL_INHERITANCE:
				return VIRTUAL_INHERITANCE_EDEFAULT == null ? virtualInheritance != null : !VIRTUAL_INHERITANCE_EDEFAULT.equals(virtualInheritance);
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_EVENT:
				return TRANSITION_EVENT_EDEFAULT == null ? transitionEvent != null : !TRANSITION_EVENT_EDEFAULT.equals(transitionEvent);
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_ACTION:
				return TRANSITION_ACTION_EDEFAULT == null ? transitionAction != null : !TRANSITION_ACTION_EDEFAULT.equals(transitionAction);
			case EaadapterPackage.EA_CONNECTOR__TRANSITION_GUARD:
				return TRANSITION_GUARD_EDEFAULT == null ? transitionGuard != null : !TRANSITION_GUARD_EDEFAULT.equals(transitionGuard);
			case EaadapterPackage.EA_CONNECTOR__ROUTE_STYLE:
				return ROUTE_STYLE_EDEFAULT == null ? routeStyle != null : !ROUTE_STYLE_EDEFAULT.equals(routeStyle);
			case EaadapterPackage.EA_CONNECTOR__SEQUENCE_NO:
				return SEQUENCE_NO_EDEFAULT == null ? sequenceNo != null : !SEQUENCE_NO_EDEFAULT.equals(sequenceNo);
			case EaadapterPackage.EA_CONNECTOR__EA_LINK:
				return EA_LINK_EDEFAULT == null ? eaLink != null : !EA_LINK_EDEFAULT.equals(eaLink);
			case EaadapterPackage.EA_CONNECTOR__TAGGED_VALUES:
				return taggedValues != null && !taggedValues.isEmpty();
			case EaadapterPackage.EA_CONNECTOR__CLIENT:
				return getClient() != null;
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER:
				return supplier != null;
			case EaadapterPackage.EA_CONNECTOR__CONSTRAINTS:
				return constraints != null && !constraints.isEmpty();
			case EaadapterPackage.EA_CONNECTOR__CLIENT_END:
				return clientEnd != null;
			case EaadapterPackage.EA_CONNECTOR__CLIENT_ID:
				return clientID != CLIENT_ID_EDEFAULT;
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER_ID:
				return supplierID != SUPPLIER_ID_EDEFAULT;
			case EaadapterPackage.EA_CONNECTOR__EVENT_FLAGS:
				return EVENT_FLAGS_EDEFAULT == null ? eventFlags != null : !EVENT_FLAGS_EDEFAULT.equals(eventFlags);
			case EaadapterPackage.EA_CONNECTOR__STYLE_EX:
				return STYLE_EX_EDEFAULT == null ? styleEx != null : !STYLE_EX_EDEFAULT.equals(styleEx);
			case EaadapterPackage.EA_CONNECTOR__SUPPLIER_END:
				return supplierEnd != null;
			case EaadapterPackage.EA_CONNECTOR__DERIVED:
				return isDerived() != DERIVED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", notes: ");
		result.append(notes);
		result.append(", guid: ");
		result.append(guid);
		result.append(", id: ");
		result.append(id);
		result.append(", stereotype: ");
		result.append(stereotype);
		result.append(", stereotypeEx: ");
		result.append(stereotypeEx);
		result.append(", type: ");
		result.append(type);
		result.append(", connectorID: ");
		result.append(connectorID);
		result.append(", direction: ");
		result.append(direction);
		result.append(", subtype: ");
		result.append(subtype);
		result.append(", isLeaf: ");
		result.append(isLeaf);
		result.append(", isRoot: ");
		result.append(isRoot);
		result.append(", isSpec: ");
		result.append(isSpec);
		result.append(", virtualInheritance: ");
		result.append(virtualInheritance);
		result.append(", transitionEvent: ");
		result.append(transitionEvent);
		result.append(", transitionAction: ");
		result.append(transitionAction);
		result.append(", transitionGuard: ");
		result.append(transitionGuard);
		result.append(", routeStyle: ");
		result.append(routeStyle);
		result.append(", sequenceNo: ");
		result.append(sequenceNo);
		result.append(", eaLink: ");
		result.append(eaLink);
		result.append(", clientID: ");
		result.append(clientID);
		result.append(", supplierID: ");
		result.append(supplierID);
		result.append(", eventFlags: ");
		result.append(eventFlags);
		result.append(", styleEx: ");
		result.append(styleEx);
		result.append(')');
		return result.toString();
	}

} //EAConnectorImpl
