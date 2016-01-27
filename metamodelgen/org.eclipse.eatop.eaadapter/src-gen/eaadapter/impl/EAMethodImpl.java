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
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sparx.Method;

import eaadapter.EAElement;
import eaadapter.EAMethod;
import eaadapter.EAMethodTag;
import eaadapter.EAParameter;
import eaadapter.EaadapterPackage;
import eaadapter.abstracthierachy.AbstracthierachyPackage;
import eaadapter.abstracthierachy.EAClassifierIDLong;
import eaadapter.abstracthierachy.util.AbstracthierachyValidator;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>EA Method</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link eaadapter.impl.EAMethodImpl#getName <em>Name</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getNotes <em>Notes</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getGuid <em>Guid</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getId <em>Id</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getStereotype <em>Stereotype</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getStereotypeEx <em>Stereotype Ex</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getType <em>Type</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getIsConst <em>Is Const</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getIsStatic <em>Is Static</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getClassifierID <em>Classifier ID</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getReturnType <em>Return Type</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getReturnIsArray <em>Return Is Array</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getCode <em>Code</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getThrows <em>Throws</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getIsPure <em>Is Pure</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getIsRoot <em>Is Root</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getIsLeaf <em>Is Leaf</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getIsQuery <em>Is Query</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getIsSynchronized <em>Is Synchronized</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getIsAbstract <em>Is Abstract</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getConcurrency <em>Concurrency</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getElement <em>Element</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getParameters <em>Parameters</em>}</li>
 * <li>{@link eaadapter.impl.EAMethodImpl#getTaggedValues <em>Tagged Values</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EAMethodImpl extends EObjectImpl implements EAMethod {
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
	 * The default value of the '{@link #getReturnType() <em>Return Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected static final String RETURN_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getReturnType()
	 * @generated
	 * @ordered
	 */
	protected String returnType = RETURN_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getReturnIsArray() <em>Return Is Array</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getReturnIsArray()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean RETURN_IS_ARRAY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReturnIsArray() <em>Return Is Array</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getReturnIsArray()
	 * @generated
	 * @ordered
	 */
	protected Boolean returnIsArray = RETURN_IS_ARRAY_EDEFAULT;

	/**
	 * The default value of the '{@link #getCode() <em>Code</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCode() <em>Code</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getCode()
	 * @generated
	 * @ordered
	 */
	protected String code = CODE_EDEFAULT;

	/**
	 * The default value of the '{@link #getThrows() <em>Throws</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getThrows()
	 * @generated
	 * @ordered
	 */
	protected static final String THROWS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getThrows() <em>Throws</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getThrows()
	 * @generated
	 * @ordered
	 */
	protected String throws_ = THROWS_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsPure() <em>Is Pure</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsPure()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_PURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsPure() <em>Is Pure</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsPure()
	 * @generated
	 * @ordered
	 */
	protected Boolean isPure = IS_PURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsRoot() <em>Is Root</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsRoot()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ROOT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsRoot() <em>Is Root</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsRoot()
	 * @generated
	 * @ordered
	 */
	protected Boolean isRoot = IS_ROOT_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsLeaf() <em>Is Leaf</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsLeaf()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_LEAF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsLeaf() <em>Is Leaf</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsLeaf()
	 * @generated
	 * @ordered
	 */
	protected Boolean isLeaf = IS_LEAF_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsQuery() <em>Is Query</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsQuery()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_QUERY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsQuery() <em>Is Query</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsQuery()
	 * @generated
	 * @ordered
	 */
	protected Boolean isQuery = IS_QUERY_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsSynchronized() <em>Is Synchronized</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getIsSynchronized()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_SYNCHRONIZED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsSynchronized() <em>Is Synchronized</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getIsSynchronized()
	 * @generated
	 * @ordered
	 */
	protected Boolean isSynchronized = IS_SYNCHRONIZED_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsAbstract() <em>Is Abstract</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_ABSTRACT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsAbstract() <em>Is Abstract</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected Boolean isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #getConcurrency() <em>Concurrency</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getConcurrency()
	 * @generated
	 * @ordered
	 */
	protected static final String CONCURRENCY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConcurrency() <em>Concurrency</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getConcurrency()
	 * @generated
	 * @ordered
	 */
	protected String concurrency = CONCURRENCY_EDEFAULT;

	/**
	 * The default value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected static final Method EA_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected Method eaLink = EA_LINK_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<EAParameter> parameters;

	/**
	 * The cached value of the '{@link #getTaggedValues() <em>Tagged Values</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTaggedValues()
	 * @generated
	 * @ordered
	 */
	protected EList<EAMethodTag> taggedValues;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EAMethodImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EaadapterPackage.Literals.EA_METHOD;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__NOTES, oldNotes, notes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__GUID, oldGuid, guid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__STEREOTYPE, oldStereotype, stereotype));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__STEREOTYPE_EX, oldStereotypeEx, stereotypeEx));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__IS_CONST, oldIsConst, isConst));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__IS_STATIC, oldIsStatic, isStatic));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__CLASSIFIER_ID, oldClassifierID, classifierID));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getReturnType() {
		return returnType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setReturnType(String newReturnType) {
		String oldReturnType = returnType;
		returnType = newReturnType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__RETURN_TYPE, oldReturnType, returnType));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getReturnIsArray() {
		return returnIsArray;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setReturnIsArray(Boolean newReturnIsArray) {
		Boolean oldReturnIsArray = returnIsArray;
		returnIsArray = newReturnIsArray;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__RETURN_IS_ARRAY, oldReturnIsArray, returnIsArray));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCode() {
		return code;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setCode(String newCode) {
		String oldCode = code;
		code = newCode;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__CODE, oldCode, code));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getThrows() {
		return throws_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setThrows(String newThrows) {
		String oldThrows = throws_;
		throws_ = newThrows;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__THROWS, oldThrows, throws_));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getIsPure() {
		return isPure;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsPure(Boolean newIsPure) {
		Boolean oldIsPure = isPure;
		isPure = newIsPure;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__IS_PURE, oldIsPure, isPure));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getIsRoot() {
		return isRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsRoot(Boolean newIsRoot) {
		Boolean oldIsRoot = isRoot;
		isRoot = newIsRoot;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__IS_ROOT, oldIsRoot, isRoot));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getIsLeaf() {
		return isLeaf;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsLeaf(Boolean newIsLeaf) {
		Boolean oldIsLeaf = isLeaf;
		isLeaf = newIsLeaf;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__IS_LEAF, oldIsLeaf, isLeaf));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getIsQuery() {
		return isQuery;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsQuery(Boolean newIsQuery) {
		Boolean oldIsQuery = isQuery;
		isQuery = newIsQuery;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__IS_QUERY, oldIsQuery, isQuery));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getIsSynchronized() {
		return isSynchronized;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsSynchronized(Boolean newIsSynchronized) {
		Boolean oldIsSynchronized = isSynchronized;
		isSynchronized = newIsSynchronized;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__IS_SYNCHRONIZED, oldIsSynchronized, isSynchronized));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getIsAbstract() {
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsAbstract(Boolean newIsAbstract) {
		Boolean oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__IS_ABSTRACT, oldIsAbstract, isAbstract));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getConcurrency() {
		return concurrency;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setConcurrency(String newConcurrency) {
		String oldConcurrency = concurrency;
		concurrency = newConcurrency;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__CONCURRENCY, oldConcurrency, concurrency));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAElement getElement() {
		if (eContainerFeatureID() != EaadapterPackage.EA_METHOD__ELEMENT) {
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
		msgs = eBasicSetContainer((InternalEObject) newElement, EaadapterPackage.EA_METHOD__ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setElement(EAElement newElement) {
		if (newElement != eInternalContainer() || eContainerFeatureID() != EaadapterPackage.EA_METHOD__ELEMENT && newElement != null) {
			if (EcoreUtil.isAncestor(this, newElement)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newElement != null) {
				msgs = ((InternalEObject) newElement).eInverseAdd(this, EaadapterPackage.EA_ELEMENT__METHODS, EAElement.class, msgs);
			}
			msgs = basicSetElement(newElement, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__ELEMENT, newElement, newElement));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Method getEaLink() {
		return eaLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEaLink(Method newEaLink) {
		Method oldEaLink = eaLink;
		eaLink = newEaLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_METHOD__EA_LINK, oldEaLink, eaLink));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAParameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentWithInverseEList<EAParameter>(EAParameter.class, this, EaadapterPackage.EA_METHOD__PARAMETERS,
					EaadapterPackage.EA_PARAMETER__METHOD);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAMethodTag> getTaggedValues() {
		if (taggedValues == null) {
			taggedValues = new EObjectContainmentEList<EAMethodTag>(EAMethodTag.class, this, EaadapterPackage.EA_METHOD__TAGGED_VALUES);
		}
		return taggedValues;
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case EaadapterPackage.EA_METHOD__ELEMENT:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetElement((EAElement) otherEnd, msgs);
		case EaadapterPackage.EA_METHOD__PARAMETERS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getParameters()).basicAdd(otherEnd, msgs);
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
		case EaadapterPackage.EA_METHOD__ELEMENT:
			return basicSetElement(null, msgs);
		case EaadapterPackage.EA_METHOD__PARAMETERS:
			return ((InternalEList<?>) getParameters()).basicRemove(otherEnd, msgs);
		case EaadapterPackage.EA_METHOD__TAGGED_VALUES:
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
		case EaadapterPackage.EA_METHOD__ELEMENT:
			return eInternalContainer().eInverseRemove(this, EaadapterPackage.EA_ELEMENT__METHODS, EAElement.class, msgs);
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
		case EaadapterPackage.EA_METHOD__NAME:
			return getName();
		case EaadapterPackage.EA_METHOD__NOTES:
			return getNotes();
		case EaadapterPackage.EA_METHOD__GUID:
			return getGuid();
		case EaadapterPackage.EA_METHOD__ID:
			return getId();
		case EaadapterPackage.EA_METHOD__STEREOTYPE:
			return getStereotype();
		case EaadapterPackage.EA_METHOD__STEREOTYPE_EX:
			return getStereotypeEx();
		case EaadapterPackage.EA_METHOD__TYPE:
			return getType();
		case EaadapterPackage.EA_METHOD__IS_CONST:
			return getIsConst();
		case EaadapterPackage.EA_METHOD__IS_STATIC:
			return getIsStatic();
		case EaadapterPackage.EA_METHOD__CLASSIFIER_ID:
			return getClassifierID();
		case EaadapterPackage.EA_METHOD__RETURN_TYPE:
			return getReturnType();
		case EaadapterPackage.EA_METHOD__RETURN_IS_ARRAY:
			return getReturnIsArray();
		case EaadapterPackage.EA_METHOD__CODE:
			return getCode();
		case EaadapterPackage.EA_METHOD__THROWS:
			return getThrows();
		case EaadapterPackage.EA_METHOD__IS_PURE:
			return getIsPure();
		case EaadapterPackage.EA_METHOD__IS_ROOT:
			return getIsRoot();
		case EaadapterPackage.EA_METHOD__IS_LEAF:
			return getIsLeaf();
		case EaadapterPackage.EA_METHOD__IS_QUERY:
			return getIsQuery();
		case EaadapterPackage.EA_METHOD__IS_SYNCHRONIZED:
			return getIsSynchronized();
		case EaadapterPackage.EA_METHOD__IS_ABSTRACT:
			return getIsAbstract();
		case EaadapterPackage.EA_METHOD__CONCURRENCY:
			return getConcurrency();
		case EaadapterPackage.EA_METHOD__ELEMENT:
			return getElement();
		case EaadapterPackage.EA_METHOD__EA_LINK:
			return getEaLink();
		case EaadapterPackage.EA_METHOD__PARAMETERS:
			return getParameters();
		case EaadapterPackage.EA_METHOD__TAGGED_VALUES:
			return getTaggedValues();
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
		case EaadapterPackage.EA_METHOD__NAME:
			setName((String) newValue);
			return;
		case EaadapterPackage.EA_METHOD__NOTES:
			setNotes((String) newValue);
			return;
		case EaadapterPackage.EA_METHOD__GUID:
			setGuid((String) newValue);
			return;
		case EaadapterPackage.EA_METHOD__ID:
			setId((Long) newValue);
			return;
		case EaadapterPackage.EA_METHOD__STEREOTYPE:
			setStereotype((String) newValue);
			return;
		case EaadapterPackage.EA_METHOD__STEREOTYPE_EX:
			setStereotypeEx((String) newValue);
			return;
		case EaadapterPackage.EA_METHOD__TYPE:
			setType((String) newValue);
			return;
		case EaadapterPackage.EA_METHOD__IS_CONST:
			setIsConst((Boolean) newValue);
			return;
		case EaadapterPackage.EA_METHOD__IS_STATIC:
			setIsStatic((Boolean) newValue);
			return;
		case EaadapterPackage.EA_METHOD__CLASSIFIER_ID:
			setClassifierID((Integer) newValue);
			return;
		case EaadapterPackage.EA_METHOD__RETURN_TYPE:
			setReturnType((String) newValue);
			return;
		case EaadapterPackage.EA_METHOD__RETURN_IS_ARRAY:
			setReturnIsArray((Boolean) newValue);
			return;
		case EaadapterPackage.EA_METHOD__CODE:
			setCode((String) newValue);
			return;
		case EaadapterPackage.EA_METHOD__THROWS:
			setThrows((String) newValue);
			return;
		case EaadapterPackage.EA_METHOD__IS_PURE:
			setIsPure((Boolean) newValue);
			return;
		case EaadapterPackage.EA_METHOD__IS_ROOT:
			setIsRoot((Boolean) newValue);
			return;
		case EaadapterPackage.EA_METHOD__IS_LEAF:
			setIsLeaf((Boolean) newValue);
			return;
		case EaadapterPackage.EA_METHOD__IS_QUERY:
			setIsQuery((Boolean) newValue);
			return;
		case EaadapterPackage.EA_METHOD__IS_SYNCHRONIZED:
			setIsSynchronized((Boolean) newValue);
			return;
		case EaadapterPackage.EA_METHOD__IS_ABSTRACT:
			setIsAbstract((Boolean) newValue);
			return;
		case EaadapterPackage.EA_METHOD__CONCURRENCY:
			setConcurrency((String) newValue);
			return;
		case EaadapterPackage.EA_METHOD__ELEMENT:
			setElement((EAElement) newValue);
			return;
		case EaadapterPackage.EA_METHOD__EA_LINK:
			setEaLink((Method) newValue);
			return;
		case EaadapterPackage.EA_METHOD__PARAMETERS:
			getParameters().clear();
			getParameters().addAll((Collection<? extends EAParameter>) newValue);
			return;
		case EaadapterPackage.EA_METHOD__TAGGED_VALUES:
			getTaggedValues().clear();
			getTaggedValues().addAll((Collection<? extends EAMethodTag>) newValue);
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
		case EaadapterPackage.EA_METHOD__NAME:
			setName(NAME_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__NOTES:
			setNotes(NOTES_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__GUID:
			setGuid(GUID_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__ID:
			setId(ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__STEREOTYPE:
			setStereotype(STEREOTYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__STEREOTYPE_EX:
			setStereotypeEx(STEREOTYPE_EX_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__IS_CONST:
			setIsConst(IS_CONST_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__IS_STATIC:
			setIsStatic(IS_STATIC_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__CLASSIFIER_ID:
			setClassifierID(CLASSIFIER_ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__RETURN_TYPE:
			setReturnType(RETURN_TYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__RETURN_IS_ARRAY:
			setReturnIsArray(RETURN_IS_ARRAY_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__CODE:
			setCode(CODE_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__THROWS:
			setThrows(THROWS_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__IS_PURE:
			setIsPure(IS_PURE_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__IS_ROOT:
			setIsRoot(IS_ROOT_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__IS_LEAF:
			setIsLeaf(IS_LEAF_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__IS_QUERY:
			setIsQuery(IS_QUERY_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__IS_SYNCHRONIZED:
			setIsSynchronized(IS_SYNCHRONIZED_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__IS_ABSTRACT:
			setIsAbstract(IS_ABSTRACT_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__CONCURRENCY:
			setConcurrency(CONCURRENCY_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__ELEMENT:
			setElement((EAElement) null);
			return;
		case EaadapterPackage.EA_METHOD__EA_LINK:
			setEaLink(EA_LINK_EDEFAULT);
			return;
		case EaadapterPackage.EA_METHOD__PARAMETERS:
			getParameters().clear();
			return;
		case EaadapterPackage.EA_METHOD__TAGGED_VALUES:
			getTaggedValues().clear();
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
		case EaadapterPackage.EA_METHOD__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case EaadapterPackage.EA_METHOD__NOTES:
			return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
		case EaadapterPackage.EA_METHOD__GUID:
			return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
		case EaadapterPackage.EA_METHOD__ID:
			return id != ID_EDEFAULT;
		case EaadapterPackage.EA_METHOD__STEREOTYPE:
			return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
		case EaadapterPackage.EA_METHOD__STEREOTYPE_EX:
			return STEREOTYPE_EX_EDEFAULT == null ? stereotypeEx != null : !STEREOTYPE_EX_EDEFAULT.equals(stereotypeEx);
		case EaadapterPackage.EA_METHOD__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case EaadapterPackage.EA_METHOD__IS_CONST:
			return IS_CONST_EDEFAULT == null ? isConst != null : !IS_CONST_EDEFAULT.equals(isConst);
		case EaadapterPackage.EA_METHOD__IS_STATIC:
			return IS_STATIC_EDEFAULT == null ? isStatic != null : !IS_STATIC_EDEFAULT.equals(isStatic);
		case EaadapterPackage.EA_METHOD__CLASSIFIER_ID:
			return CLASSIFIER_ID_EDEFAULT == null ? classifierID != null : !CLASSIFIER_ID_EDEFAULT.equals(classifierID);
		case EaadapterPackage.EA_METHOD__RETURN_TYPE:
			return RETURN_TYPE_EDEFAULT == null ? returnType != null : !RETURN_TYPE_EDEFAULT.equals(returnType);
		case EaadapterPackage.EA_METHOD__RETURN_IS_ARRAY:
			return RETURN_IS_ARRAY_EDEFAULT == null ? returnIsArray != null : !RETURN_IS_ARRAY_EDEFAULT.equals(returnIsArray);
		case EaadapterPackage.EA_METHOD__CODE:
			return CODE_EDEFAULT == null ? code != null : !CODE_EDEFAULT.equals(code);
		case EaadapterPackage.EA_METHOD__THROWS:
			return THROWS_EDEFAULT == null ? throws_ != null : !THROWS_EDEFAULT.equals(throws_);
		case EaadapterPackage.EA_METHOD__IS_PURE:
			return IS_PURE_EDEFAULT == null ? isPure != null : !IS_PURE_EDEFAULT.equals(isPure);
		case EaadapterPackage.EA_METHOD__IS_ROOT:
			return IS_ROOT_EDEFAULT == null ? isRoot != null : !IS_ROOT_EDEFAULT.equals(isRoot);
		case EaadapterPackage.EA_METHOD__IS_LEAF:
			return IS_LEAF_EDEFAULT == null ? isLeaf != null : !IS_LEAF_EDEFAULT.equals(isLeaf);
		case EaadapterPackage.EA_METHOD__IS_QUERY:
			return IS_QUERY_EDEFAULT == null ? isQuery != null : !IS_QUERY_EDEFAULT.equals(isQuery);
		case EaadapterPackage.EA_METHOD__IS_SYNCHRONIZED:
			return IS_SYNCHRONIZED_EDEFAULT == null ? isSynchronized != null : !IS_SYNCHRONIZED_EDEFAULT.equals(isSynchronized);
		case EaadapterPackage.EA_METHOD__IS_ABSTRACT:
			return IS_ABSTRACT_EDEFAULT == null ? isAbstract != null : !IS_ABSTRACT_EDEFAULT.equals(isAbstract);
		case EaadapterPackage.EA_METHOD__CONCURRENCY:
			return CONCURRENCY_EDEFAULT == null ? concurrency != null : !CONCURRENCY_EDEFAULT.equals(concurrency);
		case EaadapterPackage.EA_METHOD__ELEMENT:
			return getElement() != null;
		case EaadapterPackage.EA_METHOD__EA_LINK:
			return EA_LINK_EDEFAULT == null ? eaLink != null : !EA_LINK_EDEFAULT.equals(eaLink);
		case EaadapterPackage.EA_METHOD__PARAMETERS:
			return parameters != null && !parameters.isEmpty();
		case EaadapterPackage.EA_METHOD__TAGGED_VALUES:
			return taggedValues != null && !taggedValues.isEmpty();
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
			case EaadapterPackage.EA_METHOD__CLASSIFIER_ID:
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
				return EaadapterPackage.EA_METHOD__CLASSIFIER_ID;
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
		result.append(", returnType: ");
		result.append(returnType);
		result.append(", returnIsArray: ");
		result.append(returnIsArray);
		result.append(", code: ");
		result.append(code);
		result.append(", throws: ");
		result.append(throws_);
		result.append(", isPure: ");
		result.append(isPure);
		result.append(", isRoot: ");
		result.append(isRoot);
		result.append(", isLeaf: ");
		result.append(isLeaf);
		result.append(", isQuery: ");
		result.append(isQuery);
		result.append(", isSynchronized: ");
		result.append(isSynchronized);
		result.append(", isAbstract: ");
		result.append(isAbstract);
		result.append(", concurrency: ");
		result.append(concurrency);
		result.append(", eaLink: ");
		result.append(eaLink);
		result.append(')');
		return result.toString();
	}

} // EAMethodImpl
