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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sparx.Attribute;

import eaadapter.EAAttribute;
import eaadapter.EAAttributeTag;
import eaadapter.EAElement;
import eaadapter.EARepository;
import eaadapter.EaadapterPackage;
import eaadapter.abstracthierachy.AbstracthierachyPackage;
import eaadapter.abstracthierachy.EAClassifierIDLong;
import eaadapter.util.EaadapterValidator;
import eaadapter.validation.ValidationHelper;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>EA Attribute</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getName <em>Name</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getNotes <em>Notes</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getGuid <em>Guid</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getId <em>Id</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getStereotype <em>Stereotype</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getStereotypeEx <em>Stereotype Ex</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getType <em>Type</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getIsConst <em>Is Const</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getIsStatic <em>Is Static</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getClassifierID <em>Classifier ID</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getContainment <em>Containment</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getCollection <em>Collection</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getOrdered <em>Ordered</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getAllowDuplicates <em>Allow Duplicates</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getDerived <em>Derived</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getContainer <em>Container</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getScale <em>Scale</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getPrecision <em>Precision</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getLength <em>Length</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getLowerBound <em>Lower Bound</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getUpperBound <em>Upper Bound</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getDefault <em>Default</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getElement <em>Element</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getTaggedValues <em>Tagged Values</em>}</li>
 * <li>{@link eaadapter.impl.EAAttributeImpl#getVisibility <em>Visibility</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EAAttributeImpl extends EObjectImpl implements EAAttribute {
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
	 * The default value of the '{@link #getIsStatic() <em>Is Static</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsStatic()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_STATIC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsStatic() <em>Is Static</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsStatic()
	 * @generated
	 * @ordered
	 */
	protected Boolean isStatic = IS_STATIC_EDEFAULT;

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
	 * The default value of the '{@link #getContainment() <em>Containment</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getContainment()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTAINMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContainment() <em>Containment</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getContainment()
	 * @generated
	 * @ordered
	 */
	protected String containment = CONTAINMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getCollection() <em>Collection</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCollection()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean COLLECTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCollection() <em>Collection</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCollection()
	 * @generated
	 * @ordered
	 */
	protected Boolean collection = COLLECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrdered() <em>Ordered</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getOrdered()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ORDERED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOrdered() <em>Ordered</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getOrdered()
	 * @generated
	 * @ordered
	 */
	protected Boolean ordered = ORDERED_EDEFAULT;

	/**
	 * The default value of the '{@link #getAllowDuplicates() <em>Allow Duplicates</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getAllowDuplicates()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean ALLOW_DUPLICATES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAllowDuplicates() <em>Allow Duplicates</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getAllowDuplicates()
	 * @generated
	 * @ordered
	 */
	protected Boolean allowDuplicates = ALLOW_DUPLICATES_EDEFAULT;

	/**
	 * The default value of the '{@link #getDerived() <em>Derived</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDerived()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean DERIVED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDerived() <em>Derived</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDerived()
	 * @generated
	 * @ordered
	 */
	protected Boolean derived = DERIVED_EDEFAULT;

	/**
	 * The default value of the '{@link #getContainer() <em>Container</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTAINER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContainer() <em>Container</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected String container = CONTAINER_EDEFAULT;

	/**
	 * The default value of the '{@link #getScale() <em>Scale</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected static final String SCALE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getScale() <em>Scale</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getScale()
	 * @generated
	 * @ordered
	 */
	protected String scale = SCALE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPrecision() <em>Precision</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPrecision()
	 * @generated
	 * @ordered
	 */
	protected static final String PRECISION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPrecision() <em>Precision</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPrecision()
	 * @generated
	 * @ordered
	 */
	protected String precision = PRECISION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLength() <em>Length</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected static final String LENGTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLength() <em>Length</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLength()
	 * @generated
	 * @ordered
	 */
	protected String length = LENGTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected static final String LOWER_BOUND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLowerBound() <em>Lower Bound</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLowerBound()
	 * @generated
	 * @ordered
	 */
	protected String lowerBound = LOWER_BOUND_EDEFAULT;

	/**
	 * The default value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected static final String UPPER_BOUND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUpperBound() <em>Upper Bound</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getUpperBound()
	 * @generated
	 * @ordered
	 */
	protected String upperBound = UPPER_BOUND_EDEFAULT;

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
	 * The default value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected static final Attribute EA_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected Attribute eaLink = EA_LINK_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTaggedValues() <em>Tagged Values</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTaggedValues()
	 * @generated
	 * @ordered
	 */
	protected EList<EAAttributeTag> taggedValues;

	/**
	 * The default value of the '{@link #getVisibility() <em>Visibility</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected static final String VISIBILITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected String visibility = VISIBILITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EAAttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EaadapterPackage.Literals.EA_ATTRIBUTE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__NOTES, oldNotes, notes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__GUID, oldGuid, guid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__STEREOTYPE, oldStereotype, stereotype));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__STEREOTYPE_EX, oldStereotypeEx, stereotypeEx));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__IS_CONST, oldIsConst, isConst));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getIsStatic() {
		return isStatic;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsStatic(Boolean newIsStatic) {
		Boolean oldIsStatic = isStatic;
		isStatic = newIsStatic;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__IS_STATIC, oldIsStatic, isStatic));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__CLASSIFIER_ID, oldClassifierID, classifierID));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getContainment() {
		return containment;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setContainment(String newContainment) {
		String oldContainment = containment;
		containment = newContainment;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__CONTAINMENT, oldContainment, containment));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getCollection() {
		return collection;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setCollection(Boolean newCollection) {
		Boolean oldCollection = collection;
		collection = newCollection;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__COLLECTION, oldCollection, collection));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getOrdered() {
		return ordered;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setOrdered(Boolean newOrdered) {
		Boolean oldOrdered = ordered;
		ordered = newOrdered;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__ORDERED, oldOrdered, ordered));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getAllowDuplicates() {
		return allowDuplicates;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setAllowDuplicates(Boolean newAllowDuplicates) {
		Boolean oldAllowDuplicates = allowDuplicates;
		allowDuplicates = newAllowDuplicates;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__ALLOW_DUPLICATES, oldAllowDuplicates,
					allowDuplicates));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getDerived() {
		return derived;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDerived(Boolean newDerived) {
		Boolean oldDerived = derived;
		derived = newDerived;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__DERIVED, oldDerived, derived));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getContainer() {
		return container;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setContainer(String newContainer) {
		String oldContainer = container;
		container = newContainer;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__CONTAINER, oldContainer, container));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getScale() {
		return scale;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setScale(String newScale) {
		String oldScale = scale;
		scale = newScale;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__SCALE, oldScale, scale));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getPrecision() {
		return precision;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPrecision(String newPrecision) {
		String oldPrecision = precision;
		precision = newPrecision;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__PRECISION, oldPrecision, precision));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getLength() {
		return length;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setLength(String newLength) {
		String oldLength = length;
		length = newLength;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__LENGTH, oldLength, length));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getLowerBound() {
		return lowerBound;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setLowerBound(String newLowerBound) {
		String oldLowerBound = lowerBound;
		lowerBound = newLowerBound;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__LOWER_BOUND, oldLowerBound, lowerBound));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getUpperBound() {
		return upperBound;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setUpperBound(String newUpperBound) {
		String oldUpperBound = upperBound;
		upperBound = newUpperBound;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__UPPER_BOUND, oldUpperBound, upperBound));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__DEFAULT, oldDefault, default_));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAElement getElement() {
		if (eContainerFeatureID() != EaadapterPackage.EA_ATTRIBUTE__ELEMENT) {
			return null;
		}
		return (EAElement) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetElement(EAElement newElement, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newElement, EaadapterPackage.EA_ATTRIBUTE__ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setElement(EAElement newElement) {
		if (newElement != eInternalContainer() || eContainerFeatureID() != EaadapterPackage.EA_ATTRIBUTE__ELEMENT && newElement != null) {
			if (EcoreUtil.isAncestor(this, newElement)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newElement != null) {
				msgs = ((InternalEObject) newElement).eInverseAdd(this, EaadapterPackage.EA_ELEMENT__ATTRIBUTES, EAElement.class, msgs);
			}
			msgs = basicSetElement(newElement, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__ELEMENT, newElement, newElement));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Attribute getEaLink() {
		return eaLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEaLink(Attribute newEaLink) {
		Attribute oldEaLink = eaLink;
		eaLink = newEaLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__EA_LINK, oldEaLink, eaLink));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAAttributeTag> getTaggedValues() {
		if (taggedValues == null) {
			taggedValues = new EObjectContainmentEList<EAAttributeTag>(EAAttributeTag.class, this, EaadapterPackage.EA_ATTRIBUTE__TAGGED_VALUES);
		}
		return taggedValues;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setVisibility(String newVisibility) {
		String oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ATTRIBUTE__VISIBILITY, oldVisibility, visibility));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT mnick
	 */
	@Override
	public boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context) {
		boolean isValid = true;

		if (ValidationHelper.hasLeadingOrTrailingSpaces(getName())) {
			if (diagnostic != null) {
				diagnostic.add(new BasicDiagnostic(Diagnostic.WARNING, EaadapterValidator.DIAGNOSTIC_SOURCE,
						EaadapterValidator.EA_ATTRIBUTE__VALIDATE, "The attribute's name \"" + getName() + "\" has leading/trailing whitespaces!",
						new Object[] { this }));
			}
			isValid = false;
		}

		if (!ValidationHelper.isNameWellFormed(getName())) {
			if (diagnostic != null) {
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, EaadapterValidator.DIAGNOSTIC_SOURCE, EaadapterValidator.EA_ATTRIBUTE__VALIDATE,
						"The attribute's name is not well formed!", new Object[] { this }));
			}
			isValid = false;
		}

		// okay, this here is (EAST-ADL and SAFE) specific
		if (!getStereotype().equals("enum")) {

			if (getClassifierID() == 0 && getType().length() > 0) {
				if (diagnostic != null) {
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, EaadapterValidator.DIAGNOSTIC_SOURCE,
							EaadapterValidator.EA_ATTRIBUTE__VALIDATE,
							"The attribute's classifierID is set to '0', This means, that the attribute's type \"" + getType()
									+ "\" is Enterprise Architect's internal \"" + getType()
									+ "\"-type, and not the one from the \"PrimitiveTypes\" Package!", new Object[] { this }));
				}
				isValid = false;
			}

			if (getClassifierID() == 0 && getType().length() == 0) {
				if (diagnostic != null) {
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, EaadapterValidator.DIAGNOSTIC_SOURCE,
							EaadapterValidator.EA_ATTRIBUTE__VALIDATE,
							"The attribute's classifierID is set to '0', and the 'type-attribute is empty. There is something wrong!",
							new Object[] { this }));
				}
				isValid = false;
			}

			if (getClassifierID() != 0) {
				EARepository repository = (EARepository) EcoreUtil.getRootContainer(this);
				EAElement referencedClassifier = ValidationHelper.findEAElementByID(getClassifierID(), repository);

				if (referencedClassifier == null) {
					if (diagnostic != null) {
						diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, EaadapterValidator.DIAGNOSTIC_SOURCE,
								EaadapterValidator.EA_ATTRIBUTE__VALIDATE, "The attribute's classifier with id '" + getClassifierID()
										+ "' can not be found!", new Object[] { this }));
					}
					isValid = false;

				} else if (!getType().equals(referencedClassifier.getName())) {
					// this can happen if the attribute's type is float, but the classifierID points to EAString (maybe
					// an Enterprise Architect Bug)
					if (diagnostic != null) {
						diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, EaadapterValidator.DIAGNOSTIC_SOURCE,
								EaadapterValidator.EA_ATTRIBUTE__VALIDATE, "The attribute's type (\"" + getType()
										+ "\") and the referenced classifier (\"" + referencedClassifier.getName() + "\") don't match!",
								new Object[] { this }));
					}
					isValid = false;
				}
			}

		}

		return isValid;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case EaadapterPackage.EA_ATTRIBUTE__ELEMENT:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetElement((EAElement) otherEnd, msgs);
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
		case EaadapterPackage.EA_ATTRIBUTE__ELEMENT:
			return basicSetElement(null, msgs);
		case EaadapterPackage.EA_ATTRIBUTE__TAGGED_VALUES:
			return ((InternalEList<?>) getTaggedValues()).basicRemove(otherEnd, msgs);
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
		case EaadapterPackage.EA_ATTRIBUTE__ELEMENT:
			return eInternalContainer().eInverseRemove(this, EaadapterPackage.EA_ELEMENT__ATTRIBUTES, EAElement.class, msgs);
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
		case EaadapterPackage.EA_ATTRIBUTE__NAME:
			return getName();
		case EaadapterPackage.EA_ATTRIBUTE__NOTES:
			return getNotes();
		case EaadapterPackage.EA_ATTRIBUTE__GUID:
			return getGuid();
		case EaadapterPackage.EA_ATTRIBUTE__ID:
			return getId();
		case EaadapterPackage.EA_ATTRIBUTE__STEREOTYPE:
			return getStereotype();
		case EaadapterPackage.EA_ATTRIBUTE__STEREOTYPE_EX:
			return getStereotypeEx();
		case EaadapterPackage.EA_ATTRIBUTE__TYPE:
			return getType();
		case EaadapterPackage.EA_ATTRIBUTE__IS_CONST:
			return getIsConst();
		case EaadapterPackage.EA_ATTRIBUTE__IS_STATIC:
			return getIsStatic();
		case EaadapterPackage.EA_ATTRIBUTE__CLASSIFIER_ID:
			return getClassifierID();
		case EaadapterPackage.EA_ATTRIBUTE__CONTAINMENT:
			return getContainment();
		case EaadapterPackage.EA_ATTRIBUTE__COLLECTION:
			return getCollection();
		case EaadapterPackage.EA_ATTRIBUTE__ORDERED:
			return getOrdered();
		case EaadapterPackage.EA_ATTRIBUTE__ALLOW_DUPLICATES:
			return getAllowDuplicates();
		case EaadapterPackage.EA_ATTRIBUTE__DERIVED:
			return getDerived();
		case EaadapterPackage.EA_ATTRIBUTE__CONTAINER:
			return getContainer();
		case EaadapterPackage.EA_ATTRIBUTE__SCALE:
			return getScale();
		case EaadapterPackage.EA_ATTRIBUTE__PRECISION:
			return getPrecision();
		case EaadapterPackage.EA_ATTRIBUTE__LENGTH:
			return getLength();
		case EaadapterPackage.EA_ATTRIBUTE__LOWER_BOUND:
			return getLowerBound();
		case EaadapterPackage.EA_ATTRIBUTE__UPPER_BOUND:
			return getUpperBound();
		case EaadapterPackage.EA_ATTRIBUTE__DEFAULT:
			return getDefault();
		case EaadapterPackage.EA_ATTRIBUTE__ELEMENT:
			return getElement();
		case EaadapterPackage.EA_ATTRIBUTE__EA_LINK:
			return getEaLink();
		case EaadapterPackage.EA_ATTRIBUTE__TAGGED_VALUES:
			return getTaggedValues();
		case EaadapterPackage.EA_ATTRIBUTE__VISIBILITY:
			return getVisibility();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case EaadapterPackage.EA_ATTRIBUTE__NAME:
			setName((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__NOTES:
			setNotes((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__GUID:
			setGuid((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__ID:
			setId((Long) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__STEREOTYPE:
			setStereotype((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__STEREOTYPE_EX:
			setStereotypeEx((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__TYPE:
			setType((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__IS_CONST:
			setIsConst((Boolean) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__IS_STATIC:
			setIsStatic((Boolean) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__CLASSIFIER_ID:
			setClassifierID((Integer) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__CONTAINMENT:
			setContainment((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__COLLECTION:
			setCollection((Boolean) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__ORDERED:
			setOrdered((Boolean) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__ALLOW_DUPLICATES:
			setAllowDuplicates((Boolean) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__DERIVED:
			setDerived((Boolean) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__CONTAINER:
			setContainer((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__SCALE:
			setScale((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__PRECISION:
			setPrecision((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__LENGTH:
			setLength((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__LOWER_BOUND:
			setLowerBound((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__UPPER_BOUND:
			setUpperBound((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__DEFAULT:
			setDefault((String) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__ELEMENT:
			setElement((EAElement) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__EA_LINK:
			setEaLink((Attribute) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__TAGGED_VALUES:
			getTaggedValues().clear();
			getTaggedValues().addAll((Collection<? extends EAAttributeTag>) newValue);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__VISIBILITY:
			setVisibility((String) newValue);
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
		case EaadapterPackage.EA_ATTRIBUTE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__NOTES:
			setNotes(NOTES_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__GUID:
			setGuid(GUID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__ID:
			setId(ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__STEREOTYPE:
			setStereotype(STEREOTYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__STEREOTYPE_EX:
			setStereotypeEx(STEREOTYPE_EX_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__IS_CONST:
			setIsConst(IS_CONST_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__IS_STATIC:
			setIsStatic(IS_STATIC_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__CLASSIFIER_ID:
			setClassifierID(CLASSIFIER_ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__CONTAINMENT:
			setContainment(CONTAINMENT_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__COLLECTION:
			setCollection(COLLECTION_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__ORDERED:
			setOrdered(ORDERED_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__ALLOW_DUPLICATES:
			setAllowDuplicates(ALLOW_DUPLICATES_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__DERIVED:
			setDerived(DERIVED_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__CONTAINER:
			setContainer(CONTAINER_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__SCALE:
			setScale(SCALE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__PRECISION:
			setPrecision(PRECISION_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__LENGTH:
			setLength(LENGTH_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__LOWER_BOUND:
			setLowerBound(LOWER_BOUND_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__UPPER_BOUND:
			setUpperBound(UPPER_BOUND_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__DEFAULT:
			setDefault(DEFAULT_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__ELEMENT:
			setElement((EAElement) null);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__EA_LINK:
			setEaLink(EA_LINK_EDEFAULT);
			return;
		case EaadapterPackage.EA_ATTRIBUTE__TAGGED_VALUES:
			getTaggedValues().clear();
			return;
		case EaadapterPackage.EA_ATTRIBUTE__VISIBILITY:
			setVisibility(VISIBILITY_EDEFAULT);
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
		case EaadapterPackage.EA_ATTRIBUTE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case EaadapterPackage.EA_ATTRIBUTE__NOTES:
			return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
		case EaadapterPackage.EA_ATTRIBUTE__GUID:
			return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
		case EaadapterPackage.EA_ATTRIBUTE__ID:
			return id != ID_EDEFAULT;
		case EaadapterPackage.EA_ATTRIBUTE__STEREOTYPE:
			return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
		case EaadapterPackage.EA_ATTRIBUTE__STEREOTYPE_EX:
			return STEREOTYPE_EX_EDEFAULT == null ? stereotypeEx != null : !STEREOTYPE_EX_EDEFAULT.equals(stereotypeEx);
		case EaadapterPackage.EA_ATTRIBUTE__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case EaadapterPackage.EA_ATTRIBUTE__IS_CONST:
			return IS_CONST_EDEFAULT == null ? isConst != null : !IS_CONST_EDEFAULT.equals(isConst);
		case EaadapterPackage.EA_ATTRIBUTE__IS_STATIC:
			return IS_STATIC_EDEFAULT == null ? isStatic != null : !IS_STATIC_EDEFAULT.equals(isStatic);
		case EaadapterPackage.EA_ATTRIBUTE__CLASSIFIER_ID:
			return CLASSIFIER_ID_EDEFAULT == null ? classifierID != null : !CLASSIFIER_ID_EDEFAULT.equals(classifierID);
		case EaadapterPackage.EA_ATTRIBUTE__CONTAINMENT:
			return CONTAINMENT_EDEFAULT == null ? containment != null : !CONTAINMENT_EDEFAULT.equals(containment);
		case EaadapterPackage.EA_ATTRIBUTE__COLLECTION:
			return COLLECTION_EDEFAULT == null ? collection != null : !COLLECTION_EDEFAULT.equals(collection);
		case EaadapterPackage.EA_ATTRIBUTE__ORDERED:
			return ORDERED_EDEFAULT == null ? ordered != null : !ORDERED_EDEFAULT.equals(ordered);
		case EaadapterPackage.EA_ATTRIBUTE__ALLOW_DUPLICATES:
			return ALLOW_DUPLICATES_EDEFAULT == null ? allowDuplicates != null : !ALLOW_DUPLICATES_EDEFAULT.equals(allowDuplicates);
		case EaadapterPackage.EA_ATTRIBUTE__DERIVED:
			return DERIVED_EDEFAULT == null ? derived != null : !DERIVED_EDEFAULT.equals(derived);
		case EaadapterPackage.EA_ATTRIBUTE__CONTAINER:
			return CONTAINER_EDEFAULT == null ? container != null : !CONTAINER_EDEFAULT.equals(container);
		case EaadapterPackage.EA_ATTRIBUTE__SCALE:
			return SCALE_EDEFAULT == null ? scale != null : !SCALE_EDEFAULT.equals(scale);
		case EaadapterPackage.EA_ATTRIBUTE__PRECISION:
			return PRECISION_EDEFAULT == null ? precision != null : !PRECISION_EDEFAULT.equals(precision);
		case EaadapterPackage.EA_ATTRIBUTE__LENGTH:
			return LENGTH_EDEFAULT == null ? length != null : !LENGTH_EDEFAULT.equals(length);
		case EaadapterPackage.EA_ATTRIBUTE__LOWER_BOUND:
			return LOWER_BOUND_EDEFAULT == null ? lowerBound != null : !LOWER_BOUND_EDEFAULT.equals(lowerBound);
		case EaadapterPackage.EA_ATTRIBUTE__UPPER_BOUND:
			return UPPER_BOUND_EDEFAULT == null ? upperBound != null : !UPPER_BOUND_EDEFAULT.equals(upperBound);
		case EaadapterPackage.EA_ATTRIBUTE__DEFAULT:
			return DEFAULT_EDEFAULT == null ? default_ != null : !DEFAULT_EDEFAULT.equals(default_);
		case EaadapterPackage.EA_ATTRIBUTE__ELEMENT:
			return getElement() != null;
		case EaadapterPackage.EA_ATTRIBUTE__EA_LINK:
			return EA_LINK_EDEFAULT == null ? eaLink != null : !EA_LINK_EDEFAULT.equals(eaLink);
		case EaadapterPackage.EA_ATTRIBUTE__TAGGED_VALUES:
			return taggedValues != null && !taggedValues.isEmpty();
		case EaadapterPackage.EA_ATTRIBUTE__VISIBILITY:
			return VISIBILITY_EDEFAULT == null ? visibility != null : !VISIBILITY_EDEFAULT.equals(visibility);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == EAClassifierIDLong.class) {
			switch (derivedFeatureID) {
			case EaadapterPackage.EA_ATTRIBUTE__CLASSIFIER_ID:
				return AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__CLASSIFIER_ID;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == EAClassifierIDLong.class) {
			switch (baseFeatureID) {
			case AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG__CLASSIFIER_ID:
				return EaadapterPackage.EA_ATTRIBUTE__CLASSIFIER_ID;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(", isConst: ");
		result.append(isConst);
		result.append(", isStatic: ");
		result.append(isStatic);
		result.append(", classifierID: ");
		result.append(classifierID);
		result.append(", containment: ");
		result.append(containment);
		result.append(", collection: ");
		result.append(collection);
		result.append(", ordered: ");
		result.append(ordered);
		result.append(", allowDuplicates: ");
		result.append(allowDuplicates);
		result.append(", derived: ");
		result.append(derived);
		result.append(", container: ");
		result.append(container);
		result.append(", scale: ");
		result.append(scale);
		result.append(", precision: ");
		result.append(precision);
		result.append(", length: ");
		result.append(length);
		result.append(", lowerBound: ");
		result.append(lowerBound);
		result.append(", upperBound: ");
		result.append(upperBound);
		result.append(", default: ");
		result.append(default_);
		result.append(", eaLink: ");
		result.append(eaLink);
		result.append(", visibility: ");
		result.append(visibility);
		result.append(')');
		return result.toString();
	}

} // EAAttributeImpl
