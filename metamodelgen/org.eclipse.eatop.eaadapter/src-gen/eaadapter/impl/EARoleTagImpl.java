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
import org.sparx.RoleTag;

import eaadapter.EAConnectorEnd;
import eaadapter.EARoleTag;
import eaadapter.EaadapterPackage;
import eaadapter.abstracthierachy.util.AbstracthierachyValidator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>EA Role Tag</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getName <em>Name</em>}</li>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getNotes <em>Notes</em>}</li>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getGuid <em>Guid</em>}</li>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getId <em>Id</em>}</li>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getBaseClass <em>Base Class</em>}</li>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getElementGUID <em>Element GUID</em>}</li>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getPropertyGUID <em>Property GUID</em>}</li>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getTag <em>Tag</em>}</li>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getConnectorEnd <em>Connector End</em>}</li>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.impl.EARoleTagImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EARoleTagImpl extends EObjectImpl implements EARoleTag {
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
	 * The default value of the '{@link #getBaseClass() <em>Base Class</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getBaseClass()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBaseClass() <em>Base Class</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getBaseClass()
	 * @generated
	 * @ordered
	 */
	protected String baseClass = BASE_CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getElementGUID() <em>Element GUID</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getElementGUID()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_GUID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementGUID() <em>Element GUID</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getElementGUID()
	 * @generated
	 * @ordered
	 */
	protected String elementGUID = ELEMENT_GUID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPropertyGUID() <em>Property GUID</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getPropertyGUID()
	 * @generated
	 * @ordered
	 */
	protected static final String PROPERTY_GUID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPropertyGUID() <em>Property GUID</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getPropertyGUID()
	 * @generated
	 * @ordered
	 */
	protected String propertyGUID = PROPERTY_GUID_EDEFAULT;

	/**
	 * The default value of the '{@link #getTag() <em>Tag</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getTag()
	 * @generated
	 * @ordered
	 */
	protected static final String TAG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTag() <em>Tag</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTag()
	 * @generated
	 * @ordered
	 */
	protected String tag = TAG_EDEFAULT;

	/**
	 * The default value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected static final RoleTag EA_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected RoleTag eaLink = EA_LINK_EDEFAULT;

	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EARoleTagImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EaadapterPackage.Literals.EA_ROLE_TAG;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__NOTES, oldNotes, notes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__GUID, oldGuid, guid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__ID, oldId, id));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getBaseClass() {
		return baseClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setBaseClass(String newBaseClass) {
		String oldBaseClass = baseClass;
		baseClass = newBaseClass;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__BASE_CLASS, oldBaseClass, baseClass));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getElementGUID() {
		return elementGUID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setElementGUID(String newElementGUID) {
		String oldElementGUID = elementGUID;
		elementGUID = newElementGUID;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__ELEMENT_GUID, oldElementGUID, elementGUID));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getPropertyGUID() {
		return propertyGUID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPropertyGUID(String newPropertyGUID) {
		String oldPropertyGUID = propertyGUID;
		propertyGUID = newPropertyGUID;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__PROPERTY_GUID, oldPropertyGUID, propertyGUID));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getTag() {
		return tag;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setTag(String newTag) {
		String oldTag = tag;
		tag = newTag;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__TAG, oldTag, tag));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAConnectorEnd getConnectorEnd() {
		if (eContainerFeatureID() != EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END) {
			return null;
		}
		return (EAConnectorEnd) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetConnectorEnd(EAConnectorEnd newConnectorEnd, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newConnectorEnd, EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setConnectorEnd(EAConnectorEnd newConnectorEnd) {
		if (newConnectorEnd != eInternalContainer() || eContainerFeatureID() != EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END
				&& newConnectorEnd != null) {
			if (EcoreUtil.isAncestor(this, newConnectorEnd)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newConnectorEnd != null) {
				msgs = ((InternalEObject) newConnectorEnd).eInverseAdd(this, EaadapterPackage.EA_CONNECTOR_END__TAGGED_VALUES, EAConnectorEnd.class,
						msgs);
			}
			msgs = basicSetConnectorEnd(newConnectorEnd, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END, newConnectorEnd, newConnectorEnd));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public RoleTag getEaLink() {
		return eaLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEaLink(RoleTag newEaLink) {
		RoleTag oldEaLink = eaLink;
		eaLink = newEaLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__EA_LINK, oldEaLink, eaLink));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ROLE_TAG__VALUE, oldValue, value));
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
		case EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetConnectorEnd((EAConnectorEnd) otherEnd, msgs);
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
		case EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END:
			return basicSetConnectorEnd(null, msgs);
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
		case EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END:
			return eInternalContainer().eInverseRemove(this, EaadapterPackage.EA_CONNECTOR_END__TAGGED_VALUES, EAConnectorEnd.class, msgs);
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
		case EaadapterPackage.EA_ROLE_TAG__NAME:
			return getName();
		case EaadapterPackage.EA_ROLE_TAG__NOTES:
			return getNotes();
		case EaadapterPackage.EA_ROLE_TAG__GUID:
			return getGuid();
		case EaadapterPackage.EA_ROLE_TAG__ID:
			return getId();
		case EaadapterPackage.EA_ROLE_TAG__BASE_CLASS:
			return getBaseClass();
		case EaadapterPackage.EA_ROLE_TAG__ELEMENT_GUID:
			return getElementGUID();
		case EaadapterPackage.EA_ROLE_TAG__PROPERTY_GUID:
			return getPropertyGUID();
		case EaadapterPackage.EA_ROLE_TAG__TAG:
			return getTag();
		case EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END:
			return getConnectorEnd();
		case EaadapterPackage.EA_ROLE_TAG__EA_LINK:
			return getEaLink();
		case EaadapterPackage.EA_ROLE_TAG__VALUE:
			return getValue();
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
		case EaadapterPackage.EA_ROLE_TAG__NAME:
			setName((String) newValue);
			return;
		case EaadapterPackage.EA_ROLE_TAG__NOTES:
			setNotes((String) newValue);
			return;
		case EaadapterPackage.EA_ROLE_TAG__GUID:
			setGuid((String) newValue);
			return;
		case EaadapterPackage.EA_ROLE_TAG__ID:
			setId((Long) newValue);
			return;
		case EaadapterPackage.EA_ROLE_TAG__BASE_CLASS:
			setBaseClass((String) newValue);
			return;
		case EaadapterPackage.EA_ROLE_TAG__ELEMENT_GUID:
			setElementGUID((String) newValue);
			return;
		case EaadapterPackage.EA_ROLE_TAG__PROPERTY_GUID:
			setPropertyGUID((String) newValue);
			return;
		case EaadapterPackage.EA_ROLE_TAG__TAG:
			setTag((String) newValue);
			return;
		case EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END:
			setConnectorEnd((EAConnectorEnd) newValue);
			return;
		case EaadapterPackage.EA_ROLE_TAG__EA_LINK:
			setEaLink((RoleTag) newValue);
			return;
		case EaadapterPackage.EA_ROLE_TAG__VALUE:
			setValue((String) newValue);
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
		case EaadapterPackage.EA_ROLE_TAG__NAME:
			setName(NAME_EDEFAULT);
			return;
		case EaadapterPackage.EA_ROLE_TAG__NOTES:
			setNotes(NOTES_EDEFAULT);
			return;
		case EaadapterPackage.EA_ROLE_TAG__GUID:
			setGuid(GUID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ROLE_TAG__ID:
			setId(ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ROLE_TAG__BASE_CLASS:
			setBaseClass(BASE_CLASS_EDEFAULT);
			return;
		case EaadapterPackage.EA_ROLE_TAG__ELEMENT_GUID:
			setElementGUID(ELEMENT_GUID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ROLE_TAG__PROPERTY_GUID:
			setPropertyGUID(PROPERTY_GUID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ROLE_TAG__TAG:
			setTag(TAG_EDEFAULT);
			return;
		case EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END:
			setConnectorEnd((EAConnectorEnd) null);
			return;
		case EaadapterPackage.EA_ROLE_TAG__EA_LINK:
			setEaLink(EA_LINK_EDEFAULT);
			return;
		case EaadapterPackage.EA_ROLE_TAG__VALUE:
			setValue(VALUE_EDEFAULT);
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
		case EaadapterPackage.EA_ROLE_TAG__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case EaadapterPackage.EA_ROLE_TAG__NOTES:
			return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
		case EaadapterPackage.EA_ROLE_TAG__GUID:
			return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
		case EaadapterPackage.EA_ROLE_TAG__ID:
			return id != ID_EDEFAULT;
		case EaadapterPackage.EA_ROLE_TAG__BASE_CLASS:
			return BASE_CLASS_EDEFAULT == null ? baseClass != null : !BASE_CLASS_EDEFAULT.equals(baseClass);
		case EaadapterPackage.EA_ROLE_TAG__ELEMENT_GUID:
			return ELEMENT_GUID_EDEFAULT == null ? elementGUID != null : !ELEMENT_GUID_EDEFAULT.equals(elementGUID);
		case EaadapterPackage.EA_ROLE_TAG__PROPERTY_GUID:
			return PROPERTY_GUID_EDEFAULT == null ? propertyGUID != null : !PROPERTY_GUID_EDEFAULT.equals(propertyGUID);
		case EaadapterPackage.EA_ROLE_TAG__TAG:
			return TAG_EDEFAULT == null ? tag != null : !TAG_EDEFAULT.equals(tag);
		case EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END:
			return getConnectorEnd() != null;
		case EaadapterPackage.EA_ROLE_TAG__EA_LINK:
			return EA_LINK_EDEFAULT == null ? eaLink != null : !EA_LINK_EDEFAULT.equals(eaLink);
		case EaadapterPackage.EA_ROLE_TAG__VALUE:
			return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
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
		result.append(", baseClass: ");
		result.append(baseClass);
		result.append(", elementGUID: ");
		result.append(elementGUID);
		result.append(", propertyGUID: ");
		result.append(propertyGUID);
		result.append(", tag: ");
		result.append(tag);
		result.append(", eaLink: ");
		result.append(eaLink);
		result.append(", value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

} // EARoleTagImpl
