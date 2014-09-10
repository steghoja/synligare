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
package eaadapter.impl;

import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.sparx.ConnectorConstraint;

import eaadapter.EAConnector;
import eaadapter.EAConnectorConstraint;
import eaadapter.EaadapterPackage;
import eaadapter.abstracthierachy.util.AbstracthierachyValidator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>EA Connector Constraint</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link eaadapter.impl.EAConnectorConstraintImpl#getName <em>Name</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorConstraintImpl#getNotes <em>Notes</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorConstraintImpl#getGuid <em>Guid</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorConstraintImpl#getId <em>Id</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorConstraintImpl#getConnectorID <em>Connector ID</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorConstraintImpl#getType <em>Type</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorConstraintImpl#getConnector <em>Connector</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorConstraintImpl#getEaLink <em>Ea Link</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EAConnectorConstraintImpl extends EObjectImpl implements EAConnectorConstraint {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "name";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNotes() <em>Notes</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getNotes()
	 * @generated
	 * @ordered
	 */
	protected static final String NOTES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNotes() <em>Notes</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getNotes()
	 * @generated
	 * @ordered
	 */
	protected String notes = NOTES_EDEFAULT;

	/**
	 * The default value of the '{@link #getGuid() <em>Guid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getGuid()
	 * @generated
	 * @ordered
	 */
	protected static final String GUID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGuid() <em>Guid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getGuid()
	 * @generated
	 * @ordered
	 */
	protected String guid = GUID_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final long ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected long id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectorID() <em>Connector ID</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getConnectorID()
	 * @generated
	 * @ordered
	 */
	protected static final long CONNECTOR_ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getConnectorID() <em>Connector ID</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getConnectorID()
	 * @generated
	 * @ordered
	 */
	protected long connectorID = CONNECTOR_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected static final ConnectorConstraint EA_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected ConnectorConstraint eaLink = EA_LINK_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EAConnectorConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EaadapterPackage.Literals.EA_CONNECTOR_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_CONSTRAINT__NAME, oldName, name));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getNotes() {
		return notes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setNotes(String newNotes) {
		String oldNotes = notes;
		notes = newNotes;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_CONSTRAINT__NOTES, oldNotes, notes));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getGuid() {
		return guid;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGuid(String newGuid) {
		String oldGuid = guid;
		guid = newGuid;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_CONSTRAINT__GUID, oldGuid, guid));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public long getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(long newId) {
		long oldId = id;
		id = newId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_CONSTRAINT__ID, oldId, id));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public long getConnectorID() {
		return connectorID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setConnectorID(long newConnectorID) {
		long oldConnectorID = connectorID;
		connectorID = newConnectorID;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR_ID, oldConnectorID, connectorID));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_CONSTRAINT__TYPE, oldType, type));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAConnector getConnector() {
		if (eContainerFeatureID() != EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR) {
			return null;
		}
		return (EAConnector) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetConnector(EAConnector newConnector, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newConnector, EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setConnector(EAConnector newConnector) {
		if (newConnector != eInternalContainer() || eContainerFeatureID() != EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR
				&& newConnector != null) {
			if (EcoreUtil.isAncestor(this, newConnector)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newConnector != null) {
				msgs = ((InternalEObject) newConnector).eInverseAdd(this, EaadapterPackage.EA_CONNECTOR__CONSTRAINTS, EAConnector.class, msgs);
			}
			msgs = basicSetConnector(newConnector, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR, newConnector, newConnector));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ConnectorConstraint getEaLink() {
		return eaLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEaLink(ConnectorConstraint newEaLink) {
		ConnectorConstraint oldEaLink = eaLink;
		eaLink = newEaLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_CONSTRAINT__EA_LINK, oldEaLink, eaLink));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostic != null) {
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, AbstracthierachyValidator.DIAGNOSTIC_SOURCE,
						AbstracthierachyValidator.EA_NAMED_ELEMENT__VALIDATE, EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic",
								new Object[] { "validate", EObjectValidator.getObjectLabel(this, context) }), new Object[] { this }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetConnector((EAConnector) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR:
			return basicSetConnector(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR:
			return eInternalContainer().eInverseRemove(this, EaadapterPackage.EA_CONNECTOR__CONSTRAINTS, EAConnector.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__NAME:
			return getName();
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__NOTES:
			return getNotes();
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__GUID:
			return getGuid();
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__ID:
			return getId();
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR_ID:
			return getConnectorID();
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__TYPE:
			return getType();
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR:
			return getConnector();
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__EA_LINK:
			return getEaLink();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__NAME:
			setName((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__NOTES:
			setNotes((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__GUID:
			setGuid((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__ID:
			setId((Long) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR_ID:
			setConnectorID((Long) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__TYPE:
			setType((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR:
			setConnector((EAConnector) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__EA_LINK:
			setEaLink((ConnectorConstraint) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__NOTES:
			setNotes(NOTES_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__GUID:
			setGuid(GUID_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__ID:
			setId(ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR_ID:
			setConnectorID(CONNECTOR_ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR:
			setConnector((EAConnector) null);
			return;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__EA_LINK:
			setEaLink(EA_LINK_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__NOTES:
			return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__GUID:
			return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__ID:
			return id != ID_EDEFAULT;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR_ID:
			return connectorID != CONNECTOR_ID_EDEFAULT;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__CONNECTOR:
			return getConnector() != null;
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT__EA_LINK:
			return EA_LINK_EDEFAULT == null ? eaLink != null : !EA_LINK_EDEFAULT.equals(eaLink);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", notes: ");
		result.append(notes);
		result.append(", guid: ");
		result.append(guid);
		result.append(", id: ");
		result.append(id);
		result.append(", connectorID: ");
		result.append(connectorID);
		result.append(", type: ");
		result.append(type);
		result.append(", eaLink: ");
		result.append(eaLink);
		result.append(')');
		return result.toString();
	}

} // EAConnectorConstraintImpl
