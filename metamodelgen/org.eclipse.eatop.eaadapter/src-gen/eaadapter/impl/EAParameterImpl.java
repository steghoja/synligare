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
import org.sparx.Parameter;

import eaadapter.EAMethod;
import eaadapter.EAParameter;
import eaadapter.EaadapterPackage;
import eaadapter.abstracthierachy.util.AbstracthierachyValidator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>EA Parameter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link eaadapter.impl.EAParameterImpl#getName <em>Name</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getNotes <em>Notes</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getGuid <em>Guid</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getId <em>Id</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getStereotype <em>Stereotype</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getStereotypeEx <em>Stereotype Ex</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getType <em>Type</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getClassifierID <em>Classifier ID</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getDefault <em>Default</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getPosition <em>Position</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getIsConst <em>Is Const</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getKind <em>Kind</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getOperationID <em>Operation ID</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getMethod <em>Method</em>}</li>
 * <li>{@link eaadapter.impl.EAParameterImpl#getEaLink <em>Ea Link</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EAParameterImpl extends EObjectImpl implements EAParameter {
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
	 * The default value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStereotype()
	 * @generated
	 * @ordered
	 */
	protected static final String STEREOTYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStereotype() <em>Stereotype</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStereotype()
	 * @generated
	 * @ordered
	 */
	protected String stereotype = STEREOTYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStereotypeEx() <em>Stereotype Ex</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getStereotypeEx()
	 * @generated
	 * @ordered
	 */
	protected static final String STEREOTYPE_EX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStereotypeEx() <em>Stereotype Ex</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getStereotypeEx()
	 * @generated
	 * @ordered
	 */
	protected String stereotypeEx = STEREOTYPE_EX_EDEFAULT;

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
	 * The default value of the '{@link #getClassifierID() <em>Classifier ID</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getClassifierID()
	 * @generated
	 * @ordered
	 */
	protected static final Integer CLASSIFIER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassifierID() <em>Classifier ID</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getClassifierID()
	 * @generated
	 * @ordered
	 */
	protected Integer classifierID = CLASSIFIER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefault() <em>Default</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefault() <em>Default</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDefault()
	 * @generated
	 * @ordered
	 */
	protected String default_ = DEFAULT_EDEFAULT;

	/**
	 * The default value of the '{@link #getPosition() <em>Position</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected static final Integer POSITION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected Integer position = POSITION_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsConst() <em>Is Const</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsConst()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_CONST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsConst() <em>Is Const</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsConst()
	 * @generated
	 * @ordered
	 */
	protected Boolean isConst = IS_CONST_EDEFAULT;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final String KIND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected String kind = KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getOperationID() <em>Operation ID</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getOperationID()
	 * @generated
	 * @ordered
	 */
	protected static final Integer OPERATION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOperationID() <em>Operation ID</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getOperationID()
	 * @generated
	 * @ordered
	 */
	protected Integer operationID = OPERATION_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected static final Parameter EA_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected Parameter eaLink = EA_LINK_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EAParameterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EaadapterPackage.Literals.EA_PARAMETER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__NOTES, oldNotes, notes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__GUID, oldGuid, guid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__ID, oldId, id));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getStereotype() {
		return stereotype;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setStereotype(String newStereotype) {
		String oldStereotype = stereotype;
		stereotype = newStereotype;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__STEREOTYPE, oldStereotype, stereotype));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getStereotypeEx() {
		return stereotypeEx;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setStereotypeEx(String newStereotypeEx) {
		String oldStereotypeEx = stereotypeEx;
		stereotypeEx = newStereotypeEx;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__STEREOTYPE_EX, oldStereotypeEx, stereotypeEx));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__TYPE, oldType, type));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Integer getClassifierID() {
		return classifierID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setClassifierID(Integer newClassifierID) {
		Integer oldClassifierID = classifierID;
		classifierID = newClassifierID;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__CLASSIFIER_ID, oldClassifierID, classifierID));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getDefault() {
		return default_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDefault(String newDefault) {
		String oldDefault = default_;
		default_ = newDefault;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__DEFAULT, oldDefault, default_));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Integer getPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPosition(Integer newPosition) {
		Integer oldPosition = position;
		position = newPosition;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__POSITION, oldPosition, position));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getIsConst() {
		return isConst;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsConst(Boolean newIsConst) {
		Boolean oldIsConst = isConst;
		isConst = newIsConst;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__IS_CONST, oldIsConst, isConst));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setKind(String newKind) {
		String oldKind = kind;
		kind = newKind;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__KIND, oldKind, kind));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Integer getOperationID() {
		return operationID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAMethod getMethod() {
		if (eContainerFeatureID() != EaadapterPackage.EA_PARAMETER__METHOD) {
			return null;
		}
		return (EAMethod) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetMethod(EAMethod newMethod, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newMethod, EaadapterPackage.EA_PARAMETER__METHOD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMethod(EAMethod newMethod) {
		if (newMethod != eInternalContainer() || eContainerFeatureID() != EaadapterPackage.EA_PARAMETER__METHOD && newMethod != null) {
			if (EcoreUtil.isAncestor(this, newMethod)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newMethod != null) {
				msgs = ((InternalEObject) newMethod).eInverseAdd(this, EaadapterPackage.EA_METHOD__PARAMETERS, EAMethod.class, msgs);
			}
			msgs = basicSetMethod(newMethod, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__METHOD, newMethod, newMethod));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Parameter getEaLink() {
		return eaLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEaLink(Parameter newEaLink) {
		Parameter oldEaLink = eaLink;
		eaLink = newEaLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PARAMETER__EA_LINK, oldEaLink, eaLink));
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
		case EaadapterPackage.EA_PARAMETER__METHOD:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetMethod((EAMethod) otherEnd, msgs);
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
		case EaadapterPackage.EA_PARAMETER__METHOD:
			return basicSetMethod(null, msgs);
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
		case EaadapterPackage.EA_PARAMETER__METHOD:
			return eInternalContainer().eInverseRemove(this, EaadapterPackage.EA_METHOD__PARAMETERS, EAMethod.class, msgs);
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
		case EaadapterPackage.EA_PARAMETER__NAME:
			return getName();
		case EaadapterPackage.EA_PARAMETER__NOTES:
			return getNotes();
		case EaadapterPackage.EA_PARAMETER__GUID:
			return getGuid();
		case EaadapterPackage.EA_PARAMETER__ID:
			return getId();
		case EaadapterPackage.EA_PARAMETER__STEREOTYPE:
			return getStereotype();
		case EaadapterPackage.EA_PARAMETER__STEREOTYPE_EX:
			return getStereotypeEx();
		case EaadapterPackage.EA_PARAMETER__TYPE:
			return getType();
		case EaadapterPackage.EA_PARAMETER__CLASSIFIER_ID:
			return getClassifierID();
		case EaadapterPackage.EA_PARAMETER__DEFAULT:
			return getDefault();
		case EaadapterPackage.EA_PARAMETER__POSITION:
			return getPosition();
		case EaadapterPackage.EA_PARAMETER__IS_CONST:
			return getIsConst();
		case EaadapterPackage.EA_PARAMETER__KIND:
			return getKind();
		case EaadapterPackage.EA_PARAMETER__OPERATION_ID:
			return getOperationID();
		case EaadapterPackage.EA_PARAMETER__METHOD:
			return getMethod();
		case EaadapterPackage.EA_PARAMETER__EA_LINK:
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
		case EaadapterPackage.EA_PARAMETER__NAME:
			setName((String) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__NOTES:
			setNotes((String) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__GUID:
			setGuid((String) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__ID:
			setId((Long) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__STEREOTYPE:
			setStereotype((String) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__STEREOTYPE_EX:
			setStereotypeEx((String) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__TYPE:
			setType((String) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__CLASSIFIER_ID:
			setClassifierID((Integer) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__DEFAULT:
			setDefault((String) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__POSITION:
			setPosition((Integer) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__IS_CONST:
			setIsConst((Boolean) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__KIND:
			setKind((String) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__METHOD:
			setMethod((EAMethod) newValue);
			return;
		case EaadapterPackage.EA_PARAMETER__EA_LINK:
			setEaLink((Parameter) newValue);
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
		case EaadapterPackage.EA_PARAMETER__NAME:
			setName(NAME_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__NOTES:
			setNotes(NOTES_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__GUID:
			setGuid(GUID_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__ID:
			setId(ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__STEREOTYPE:
			setStereotype(STEREOTYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__STEREOTYPE_EX:
			setStereotypeEx(STEREOTYPE_EX_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__CLASSIFIER_ID:
			setClassifierID(CLASSIFIER_ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__DEFAULT:
			setDefault(DEFAULT_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__POSITION:
			setPosition(POSITION_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__IS_CONST:
			setIsConst(IS_CONST_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__KIND:
			setKind(KIND_EDEFAULT);
			return;
		case EaadapterPackage.EA_PARAMETER__METHOD:
			setMethod((EAMethod) null);
			return;
		case EaadapterPackage.EA_PARAMETER__EA_LINK:
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
		case EaadapterPackage.EA_PARAMETER__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case EaadapterPackage.EA_PARAMETER__NOTES:
			return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
		case EaadapterPackage.EA_PARAMETER__GUID:
			return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
		case EaadapterPackage.EA_PARAMETER__ID:
			return id != ID_EDEFAULT;
		case EaadapterPackage.EA_PARAMETER__STEREOTYPE:
			return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
		case EaadapterPackage.EA_PARAMETER__STEREOTYPE_EX:
			return STEREOTYPE_EX_EDEFAULT == null ? stereotypeEx != null : !STEREOTYPE_EX_EDEFAULT.equals(stereotypeEx);
		case EaadapterPackage.EA_PARAMETER__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case EaadapterPackage.EA_PARAMETER__CLASSIFIER_ID:
			return CLASSIFIER_ID_EDEFAULT == null ? classifierID != null : !CLASSIFIER_ID_EDEFAULT.equals(classifierID);
		case EaadapterPackage.EA_PARAMETER__DEFAULT:
			return DEFAULT_EDEFAULT == null ? default_ != null : !DEFAULT_EDEFAULT.equals(default_);
		case EaadapterPackage.EA_PARAMETER__POSITION:
			return POSITION_EDEFAULT == null ? position != null : !POSITION_EDEFAULT.equals(position);
		case EaadapterPackage.EA_PARAMETER__IS_CONST:
			return IS_CONST_EDEFAULT == null ? isConst != null : !IS_CONST_EDEFAULT.equals(isConst);
		case EaadapterPackage.EA_PARAMETER__KIND:
			return KIND_EDEFAULT == null ? kind != null : !KIND_EDEFAULT.equals(kind);
		case EaadapterPackage.EA_PARAMETER__OPERATION_ID:
			return OPERATION_ID_EDEFAULT == null ? operationID != null : !OPERATION_ID_EDEFAULT.equals(operationID);
		case EaadapterPackage.EA_PARAMETER__METHOD:
			return getMethod() != null;
		case EaadapterPackage.EA_PARAMETER__EA_LINK:
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
		result.append(", stereotype: ");
		result.append(stereotype);
		result.append(", stereotypeEx: ");
		result.append(stereotypeEx);
		result.append(", type: ");
		result.append(type);
		result.append(", classifierID: ");
		result.append(classifierID);
		result.append(", default: ");
		result.append(default_);
		result.append(", position: ");
		result.append(position);
		result.append(", isConst: ");
		result.append(isConst);
		result.append(", kind: ");
		result.append(kind);
		result.append(", operationID: ");
		result.append(operationID);
		result.append(", eaLink: ");
		result.append(eaLink);
		result.append(')');
		return result.toString();
	}

} // EAParameterImpl
