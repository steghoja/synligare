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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import eaadapter.EAElement;
import eaadapter.EAPackage;
import eaadapter.EaadapterPackage;
import eaadapter.util.EaadapterValidator;
import eaadapter.validation.ValidationHelper;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>EA Package</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link eaadapter.impl.EAPackageImpl#getName <em>Name</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getNotes <em>Notes</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getGuid <em>Guid</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getId <em>Id</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getVersion <em>Version</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getCodePath <em>Code Path</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getFlags <em>Flags</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getIsModel <em>Is Model</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getElements <em>Elements</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getPackages <em>Packages</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getSuperPackage <em>Super Package</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.impl.EAPackageImpl#getStereotype <em>Stereotype</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EAPackageImpl extends EObjectImpl implements EAPackage {
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
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getCodePath() <em>Code Path</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCodePath()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCodePath() <em>Code Path</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCodePath()
	 * @generated
	 * @ordered
	 */
	protected String codePath = CODE_PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getFlags() <em>Flags</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFlags()
	 * @generated
	 * @ordered
	 */
	protected static final String FLAGS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFlags() <em>Flags</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getFlags()
	 * @generated
	 * @ordered
	 */
	protected String flags = FLAGS_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsModel() <em>Is Model</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsModel()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_MODEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsModel() <em>Is Model</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsModel()
	 * @generated
	 * @ordered
	 */
	protected Boolean isModel = IS_MODEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList<EAElement> elements;

	/**
	 * The cached value of the '{@link #getPackages() <em>Packages</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<EAPackage> packages;

	/**
	 * The default value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected static final org.sparx.Package EA_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected org.sparx.Package eaLink = EA_LINK_EDEFAULT;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EAPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EaadapterPackage.Literals.EA_PACKAGE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__NOTES, oldNotes, notes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__GUID, oldGuid, guid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__ID, oldId, id));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__VERSION, oldVersion, version));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCodePath() {
		return codePath;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setCodePath(String newCodePath) {
		String oldCodePath = codePath;
		codePath = newCodePath;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__CODE_PATH, oldCodePath, codePath));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getFlags() {
		return flags;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setFlags(String newFlags) {
		String oldFlags = flags;
		flags = newFlags;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__FLAGS, oldFlags, flags));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getIsModel() {
		return isModel;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsModel(Boolean newIsModel) {
		Boolean oldIsModel = isModel;
		isModel = newIsModel;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__IS_MODEL, oldIsModel, isModel));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAElement> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentWithInverseEList<EAElement>(EAElement.class, this, EaadapterPackage.EA_PACKAGE__ELEMENTS,
					EaadapterPackage.EA_ELEMENT__PACKAGE);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAPackage> getPackages() {
		if (packages == null) {
			packages = new EObjectContainmentWithInverseEList<EAPackage>(EAPackage.class, this, EaadapterPackage.EA_PACKAGE__PACKAGES,
					EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE);
		}
		return packages;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAPackage getSuperPackage() {
		if (eContainerFeatureID() != EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE) {
			return null;
		}
		return (EAPackage) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetSuperPackage(EAPackage newSuperPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newSuperPackage, EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSuperPackage(EAPackage newSuperPackage) {
		if (newSuperPackage != eInternalContainer() || eContainerFeatureID() != EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE && newSuperPackage != null) {
			if (EcoreUtil.isAncestor(this, newSuperPackage)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newSuperPackage != null) {
				msgs = ((InternalEObject) newSuperPackage).eInverseAdd(this, EaadapterPackage.EA_PACKAGE__PACKAGES, EAPackage.class, msgs);
			}
			msgs = basicSetSuperPackage(newSuperPackage, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE, newSuperPackage, newSuperPackage));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public org.sparx.Package getEaLink() {
		return eaLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEaLink(org.sparx.Package newEaLink) {
		org.sparx.Package oldEaLink = eaLink;
		eaLink = newEaLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__EA_LINK, oldEaLink, eaLink));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_PACKAGE__STEREOTYPE, oldStereotype, stereotype));
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
				diagnostic.add(new BasicDiagnostic(Diagnostic.WARNING, EaadapterValidator.DIAGNOSTIC_SOURCE, EaadapterValidator.EA_PACKAGE__VALIDATE,
						"The package's name \"" + getName() + "\" has leading/trailing whitespaces!", new Object[] { this }));
			}
			isValid = false;
		}

		if (!ValidationHelper.isNameWellFormed(getName())) {
			if (diagnostic != null) {
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, EaadapterValidator.DIAGNOSTIC_SOURCE, EaadapterValidator.EA_PACKAGE__VALIDATE,
						"The package's name is not well formed!", new Object[] { this }));
			}
			isValid = false;
		}

		if (hasDuplicatePackages()) {
			for (String duplicateName : getDuplicatePackages()) {
				if (diagnostic != null) {
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, EaadapterValidator.DIAGNOSTIC_SOURCE,
							EaadapterValidator.EA_PACKAGE__VALIDATE, "There may not be two packages named '" + duplicateName + "'!",
							new Object[] { this }));
				}
				isValid = false;
			}
		}

		if (hasDuplicateElements()) {
			for (String duplicateName : getDuplicateElements()) {
				if (diagnostic != null) {
					diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, EaadapterValidator.DIAGNOSTIC_SOURCE,
							EaadapterValidator.EA_PACKAGE__VALIDATE, "There may not be two elements named '" + duplicateName + "'!",
							new Object[] { this }));
				}
				isValid = false;
			}
		}

		return isValid;
	}

	private boolean hasDuplicatePackages() {
		if (getDuplicatePackages().size() > 0) {
			return true;
		}
		return false;
	}

	private List<String> getDuplicatePackages() {
		List<String> names = new ArrayList<String>();
		for (EAPackage p : getPackages()) {
			names.add(p.getName());
		}
		return ValidationHelper.getDuplicates(names);
	}

	private boolean hasDuplicateElements() {
		if (getDuplicateElements().size() > 0) {
			return true;
		}
		return false;
	}

	private List<String> getDuplicateElements() {
		List<String> names = new ArrayList<String>();

		for (EAElement element : getElements()) {
			if (shouldIConsiderElement(element)) {
				names.add(element.getName());
			}
		}
		return ValidationHelper.getDuplicates(names);
	}

	private boolean shouldIConsiderElement(EAElement e) {
		String[] elementsNotToConsider = new String[] { "Boundary", "Note", "Text", "Requirement" };
		if (Arrays.asList(elementsNotToConsider).contains(e.getMetaType())) {
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
		case EaadapterPackage.EA_PACKAGE__ELEMENTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getElements()).basicAdd(otherEnd, msgs);
		case EaadapterPackage.EA_PACKAGE__PACKAGES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getPackages()).basicAdd(otherEnd, msgs);
		case EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetSuperPackage((EAPackage) otherEnd, msgs);
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
		case EaadapterPackage.EA_PACKAGE__ELEMENTS:
			return ((InternalEList<?>) getElements()).basicRemove(otherEnd, msgs);
		case EaadapterPackage.EA_PACKAGE__PACKAGES:
			return ((InternalEList<?>) getPackages()).basicRemove(otherEnd, msgs);
		case EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE:
			return basicSetSuperPackage(null, msgs);
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
		case EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE:
			return eInternalContainer().eInverseRemove(this, EaadapterPackage.EA_PACKAGE__PACKAGES, EAPackage.class, msgs);
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
		case EaadapterPackage.EA_PACKAGE__NAME:
			return getName();
		case EaadapterPackage.EA_PACKAGE__NOTES:
			return getNotes();
		case EaadapterPackage.EA_PACKAGE__GUID:
			return getGuid();
		case EaadapterPackage.EA_PACKAGE__ID:
			return getId();
		case EaadapterPackage.EA_PACKAGE__VERSION:
			return getVersion();
		case EaadapterPackage.EA_PACKAGE__CODE_PATH:
			return getCodePath();
		case EaadapterPackage.EA_PACKAGE__FLAGS:
			return getFlags();
		case EaadapterPackage.EA_PACKAGE__IS_MODEL:
			return getIsModel();
		case EaadapterPackage.EA_PACKAGE__ELEMENTS:
			return getElements();
		case EaadapterPackage.EA_PACKAGE__PACKAGES:
			return getPackages();
		case EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE:
			return getSuperPackage();
		case EaadapterPackage.EA_PACKAGE__EA_LINK:
			return getEaLink();
		case EaadapterPackage.EA_PACKAGE__STEREOTYPE:
			return getStereotype();
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
		case EaadapterPackage.EA_PACKAGE__NAME:
			setName((String) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__NOTES:
			setNotes((String) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__GUID:
			setGuid((String) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__ID:
			setId((Long) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__VERSION:
			setVersion((String) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__CODE_PATH:
			setCodePath((String) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__FLAGS:
			setFlags((String) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__IS_MODEL:
			setIsModel((Boolean) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__ELEMENTS:
			getElements().clear();
			getElements().addAll((Collection<? extends EAElement>) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__PACKAGES:
			getPackages().clear();
			getPackages().addAll((Collection<? extends EAPackage>) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE:
			setSuperPackage((EAPackage) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__EA_LINK:
			setEaLink((org.sparx.Package) newValue);
			return;
		case EaadapterPackage.EA_PACKAGE__STEREOTYPE:
			setStereotype((String) newValue);
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
		case EaadapterPackage.EA_PACKAGE__NAME:
			setName(NAME_EDEFAULT);
			return;
		case EaadapterPackage.EA_PACKAGE__NOTES:
			setNotes(NOTES_EDEFAULT);
			return;
		case EaadapterPackage.EA_PACKAGE__GUID:
			setGuid(GUID_EDEFAULT);
			return;
		case EaadapterPackage.EA_PACKAGE__ID:
			setId(ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_PACKAGE__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case EaadapterPackage.EA_PACKAGE__CODE_PATH:
			setCodePath(CODE_PATH_EDEFAULT);
			return;
		case EaadapterPackage.EA_PACKAGE__FLAGS:
			setFlags(FLAGS_EDEFAULT);
			return;
		case EaadapterPackage.EA_PACKAGE__IS_MODEL:
			setIsModel(IS_MODEL_EDEFAULT);
			return;
		case EaadapterPackage.EA_PACKAGE__ELEMENTS:
			getElements().clear();
			return;
		case EaadapterPackage.EA_PACKAGE__PACKAGES:
			getPackages().clear();
			return;
		case EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE:
			setSuperPackage((EAPackage) null);
			return;
		case EaadapterPackage.EA_PACKAGE__EA_LINK:
			setEaLink(EA_LINK_EDEFAULT);
			return;
		case EaadapterPackage.EA_PACKAGE__STEREOTYPE:
			setStereotype(STEREOTYPE_EDEFAULT);
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
		case EaadapterPackage.EA_PACKAGE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case EaadapterPackage.EA_PACKAGE__NOTES:
			return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
		case EaadapterPackage.EA_PACKAGE__GUID:
			return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
		case EaadapterPackage.EA_PACKAGE__ID:
			return id != ID_EDEFAULT;
		case EaadapterPackage.EA_PACKAGE__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case EaadapterPackage.EA_PACKAGE__CODE_PATH:
			return CODE_PATH_EDEFAULT == null ? codePath != null : !CODE_PATH_EDEFAULT.equals(codePath);
		case EaadapterPackage.EA_PACKAGE__FLAGS:
			return FLAGS_EDEFAULT == null ? flags != null : !FLAGS_EDEFAULT.equals(flags);
		case EaadapterPackage.EA_PACKAGE__IS_MODEL:
			return IS_MODEL_EDEFAULT == null ? isModel != null : !IS_MODEL_EDEFAULT.equals(isModel);
		case EaadapterPackage.EA_PACKAGE__ELEMENTS:
			return elements != null && !elements.isEmpty();
		case EaadapterPackage.EA_PACKAGE__PACKAGES:
			return packages != null && !packages.isEmpty();
		case EaadapterPackage.EA_PACKAGE__SUPER_PACKAGE:
			return getSuperPackage() != null;
		case EaadapterPackage.EA_PACKAGE__EA_LINK:
			return EA_LINK_EDEFAULT == null ? eaLink != null : !EA_LINK_EDEFAULT.equals(eaLink);
		case EaadapterPackage.EA_PACKAGE__STEREOTYPE:
			return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
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
		result.append(", version: ");
		result.append(version);
		result.append(", codePath: ");
		result.append(codePath);
		result.append(", flags: ");
		result.append(flags);
		result.append(", isModel: ");
		result.append(isModel);
		result.append(", eaLink: ");
		result.append(eaLink);
		result.append(", stereotype: ");
		result.append(stereotype);
		result.append(')');
		return result.toString();
	}

} // EAPackageImpl
