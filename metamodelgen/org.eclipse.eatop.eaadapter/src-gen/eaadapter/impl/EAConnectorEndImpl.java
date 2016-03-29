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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sparx.ConnectorEnd;

import eaadapter.EAConnectorEnd;
import eaadapter.EARoleTag;
import eaadapter.EaadapterPackage;
import eaadapter.abstracthierachy.util.AbstracthierachyValidator;
import eaadapter.datatypes.T_ConnectorAggregation;
import eaadapter.validation.ValidationHelper;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>EA Connector End</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getName <em>Name</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getNotes <em>Notes</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getGuid <em>Guid</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getId <em>Id</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getTaggedValues <em>Tagged Values</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getEnd <em>End</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getCardinality <em>Cardinality</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getVisibility <em>Visibility</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getRole <em>Role</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getRoleType <em>Role Type</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getRoleNote <em>Role Note</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getContainment <em>Containment</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getAggregation <em>Aggregation</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getOrdering <em>Ordering</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getQualifier <em>Qualifier</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getConstraint <em>Constraint</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#isIsNavigable <em>Is Navigable</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getIsChangable <em>Is Changable</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.impl.EAConnectorEndImpl#isDerived <em>Derived</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EAConnectorEndImpl extends EObjectImpl implements EAConnectorEnd {
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
	 * The cached value of the '{@link #getTaggedValues() <em>Tagged Values</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTaggedValues()
	 * @generated
	 * @ordered
	 */
	protected EList<EARoleTag> taggedValues;

	/**
	 * The default value of the '{@link #getEnd() <em>End</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected static final String END_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnd() <em>End</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected String end = END_EDEFAULT;

	/**
	 * The default value of the '{@link #getCardinality() <em>Cardinality</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
	protected static final String CARDINALITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCardinality() <em>Cardinality</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCardinality()
	 * @generated
	 * @ordered
	 */
	protected String cardinality = CARDINALITY_EDEFAULT;

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
	 * The default value of the '{@link #getRole() <em>Role</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRole() <em>Role</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected String role = ROLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRoleType() <em>Role Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRoleType()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRoleType() <em>Role Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRoleType()
	 * @generated
	 * @ordered
	 */
	protected String roleType = ROLE_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getRoleNote() <em>Role Note</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRoleNote()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_NOTE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRoleNote() <em>Role Note</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRoleNote()
	 * @generated
	 * @ordered
	 */
	protected String roleNote = ROLE_NOTE_EDEFAULT;

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
	 * The default value of the '{@link #getAggregation() <em>Aggregation</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAggregation()
	 * @generated
	 * @ordered
	 */
	protected static final T_ConnectorAggregation AGGREGATION_EDEFAULT = T_ConnectorAggregation.NONE;

	/**
	 * The cached value of the '{@link #getAggregation() <em>Aggregation</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAggregation()
	 * @generated
	 * @ordered
	 */
	protected T_ConnectorAggregation aggregation = AGGREGATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getOrdering() <em>Ordering</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getOrdering()
	 * @generated
	 * @ordered
	 */
	protected static final long ORDERING_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getOrdering() <em>Ordering</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getOrdering()
	 * @generated
	 * @ordered
	 */
	protected long ordering = ORDERING_EDEFAULT;

	/**
	 * The default value of the '{@link #getQualifier() <em>Qualifier</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getQualifier()
	 * @generated
	 * @ordered
	 */
	protected static final String QUALIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getQualifier() <em>Qualifier</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getQualifier()
	 * @generated
	 * @ordered
	 */
	protected String qualifier = QUALIFIER_EDEFAULT;

	/**
	 * The default value of the '{@link #getConstraint() <em>Constraint</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getConstraint()
	 * @generated
	 * @ordered
	 */
	protected static final String CONSTRAINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getConstraint()
	 * @generated
	 * @ordered
	 */
	protected String constraint = CONSTRAINT_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsNavigable() <em>Is Navigable</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isIsNavigable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NAVIGABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNavigable() <em>Is Navigable</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isIsNavigable()
	 * @generated
	 * @ordered
	 */
	protected boolean isNavigable = IS_NAVIGABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsChangable() <em>Is Changable</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getIsChangable()
	 * @generated
	 * @ordered
	 */
	protected static final String IS_CHANGABLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsChangable() <em>Is Changable</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsChangable()
	 * @generated
	 * @ordered
	 */
	protected String isChangable = IS_CHANGABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected static final ConnectorEnd EA_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected ConnectorEnd eaLink = EA_LINK_EDEFAULT;

	/**
	 * The default value of the '{@link #isDerived() <em>Derived</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isDerived()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DERIVED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDerived() <em>Derived</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isDerived()
	 * @generated
	 * @ordered
	 */
	protected boolean derived = DERIVED_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EAConnectorEndImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EaadapterPackage.Literals.EA_CONNECTOR_END;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__NOTES, oldNotes, notes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__GUID, oldGuid, guid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__ID, oldId, id));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EARoleTag> getTaggedValues() {
		if (taggedValues == null) {
			taggedValues = new EObjectContainmentWithInverseEList<EARoleTag>(EARoleTag.class, this, EaadapterPackage.EA_CONNECTOR_END__TAGGED_VALUES,
					EaadapterPackage.EA_ROLE_TAG__CONNECTOR_END);
		}
		return taggedValues;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getEnd() {
		return end;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEnd(String newEnd) {
		String oldEnd = end;
		end = newEnd;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__END, oldEnd, end));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCardinality() {
		return cardinality;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setCardinality(String newCardinality) {
		String oldCardinality = cardinality;
		cardinality = newCardinality;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__CARDINALITY, oldCardinality, cardinality));
		}
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__VISIBILITY, oldVisibility, visibility));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setRole(String newRole) {
		String oldRole = role;
		role = newRole;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__ROLE, oldRole, role));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getRoleType() {
		return roleType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setRoleType(String newRoleType) {
		String oldRoleType = roleType;
		roleType = newRoleType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__ROLE_TYPE, oldRoleType, roleType));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getRoleNote() {
		return roleNote;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setRoleNote(String newRoleNote) {
		String oldRoleNote = roleNote;
		roleNote = newRoleNote;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__ROLE_NOTE, oldRoleNote, roleNote));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__CONTAINMENT, oldContainment, containment));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public T_ConnectorAggregation getAggregation() {
		return aggregation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setAggregation(T_ConnectorAggregation newAggregation) {
		T_ConnectorAggregation oldAggregation = aggregation;
		aggregation = newAggregation == null ? AGGREGATION_EDEFAULT : newAggregation;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__AGGREGATION, oldAggregation, aggregation));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public long getOrdering() {
		return ordering;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setOrdering(long newOrdering) {
		long oldOrdering = ordering;
		ordering = newOrdering;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__ORDERING, oldOrdering, ordering));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getQualifier() {
		return qualifier;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setQualifier(String newQualifier) {
		String oldQualifier = qualifier;
		qualifier = newQualifier;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__QUALIFIER, oldQualifier, qualifier));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getConstraint() {
		return constraint;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setConstraint(String newConstraint) {
		String oldConstraint = constraint;
		constraint = newConstraint;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__CONSTRAINT, oldConstraint, constraint));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isIsNavigable() {
		return isNavigable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsNavigable(boolean newIsNavigable) {
		boolean oldIsNavigable = isNavigable;
		isNavigable = newIsNavigable;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__IS_NAVIGABLE, oldIsNavigable, isNavigable));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getIsChangable() {
		return isChangable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsChangable(String newIsChangable) {
		String oldIsChangable = isChangable;
		isChangable = newIsChangable;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__IS_CHANGABLE, oldIsChangable, isChangable));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ConnectorEnd getEaLink() {
		return eaLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEaLink(ConnectorEnd newEaLink) {
		ConnectorEnd oldEaLink = eaLink;
		eaLink = newEaLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__EA_LINK, oldEaLink, eaLink));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isDerived() {
		return derived;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDerived(boolean newDerived) {
		boolean oldDerived = derived;
		derived = newDerived;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_CONNECTOR_END__DERIVED, oldDerived, derived));
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

		if (ValidationHelper.hasLeadingOrTrailingSpaces(getRole())) {
			if (diagnostic != null) {
				diagnostic.add(new BasicDiagnostic(Diagnostic.WARNING, AbstracthierachyValidator.DIAGNOSTIC_SOURCE,
						AbstracthierachyValidator.EA_NAMED_ELEMENT__VALIDATE, "Connector role \"" + getRole()
								+ "\" has leading/trailing whitespaced!", new Object[] { this }));
			}
			isValid = false;
		}

		if (hasRolename() && !hasCardinality()) {
			if (diagnostic != null) {
				diagnostic.add(new BasicDiagnostic(Diagnostic.WARNING, AbstracthierachyValidator.DIAGNOSTIC_SOURCE,
						AbstracthierachyValidator.EA_NAMED_ELEMENT__VALIDATE,
						"You have a rolename '" + getRole() + "', but there is no cardinality!", new Object[] { this }));
			}
			isValid = false;
		}

		if (isContainmentReference() && !hasRolename()) {
			if (getCardinality().length() == 0) {
				if (diagnostic != null) {
					diagnostic.add(new BasicDiagnostic(Diagnostic.WARNING, AbstracthierachyValidator.DIAGNOSTIC_SOURCE,
							AbstracthierachyValidator.EA_NAMED_ELEMENT__VALIDATE, String
									.format("The cardinality on containment references shall be [0..1] but it is not set"), new Object[] { this }));
				}
				isValid = false;
			}
		}

		return isValid;
	}

	private boolean isContainmentReference() {
		if (getAggregation() == T_ConnectorAggregation.COMPOSITE) {
			return true;
		} else {
			return false;
		}
	}

	private boolean hasRolename() {
		if (getRole().length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean hasCardinality() {
		if (getCardinality().length() > 0) {
			return true;
		} else {
			return false;
		}
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
		case EaadapterPackage.EA_CONNECTOR_END__TAGGED_VALUES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getTaggedValues()).basicAdd(otherEnd, msgs);
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
		case EaadapterPackage.EA_CONNECTOR_END__TAGGED_VALUES:
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case EaadapterPackage.EA_CONNECTOR_END__NAME:
			return getName();
		case EaadapterPackage.EA_CONNECTOR_END__NOTES:
			return getNotes();
		case EaadapterPackage.EA_CONNECTOR_END__GUID:
			return getGuid();
		case EaadapterPackage.EA_CONNECTOR_END__ID:
			return getId();
		case EaadapterPackage.EA_CONNECTOR_END__TAGGED_VALUES:
			return getTaggedValues();
		case EaadapterPackage.EA_CONNECTOR_END__END:
			return getEnd();
		case EaadapterPackage.EA_CONNECTOR_END__CARDINALITY:
			return getCardinality();
		case EaadapterPackage.EA_CONNECTOR_END__VISIBILITY:
			return getVisibility();
		case EaadapterPackage.EA_CONNECTOR_END__ROLE:
			return getRole();
		case EaadapterPackage.EA_CONNECTOR_END__ROLE_TYPE:
			return getRoleType();
		case EaadapterPackage.EA_CONNECTOR_END__ROLE_NOTE:
			return getRoleNote();
		case EaadapterPackage.EA_CONNECTOR_END__CONTAINMENT:
			return getContainment();
		case EaadapterPackage.EA_CONNECTOR_END__AGGREGATION:
			return getAggregation();
		case EaadapterPackage.EA_CONNECTOR_END__ORDERING:
			return getOrdering();
		case EaadapterPackage.EA_CONNECTOR_END__QUALIFIER:
			return getQualifier();
		case EaadapterPackage.EA_CONNECTOR_END__CONSTRAINT:
			return getConstraint();
		case EaadapterPackage.EA_CONNECTOR_END__IS_NAVIGABLE:
			return isIsNavigable();
		case EaadapterPackage.EA_CONNECTOR_END__IS_CHANGABLE:
			return getIsChangable();
		case EaadapterPackage.EA_CONNECTOR_END__EA_LINK:
			return getEaLink();
		case EaadapterPackage.EA_CONNECTOR_END__DERIVED:
			return isDerived();
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
		case EaadapterPackage.EA_CONNECTOR_END__NAME:
			setName((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__NOTES:
			setNotes((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__GUID:
			setGuid((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__ID:
			setId((Long) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__TAGGED_VALUES:
			getTaggedValues().clear();
			getTaggedValues().addAll((Collection<? extends EARoleTag>) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__END:
			setEnd((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__CARDINALITY:
			setCardinality((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__VISIBILITY:
			setVisibility((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__ROLE:
			setRole((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__ROLE_TYPE:
			setRoleType((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__ROLE_NOTE:
			setRoleNote((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__CONTAINMENT:
			setContainment((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__AGGREGATION:
			setAggregation((T_ConnectorAggregation) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__ORDERING:
			setOrdering((Long) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__QUALIFIER:
			setQualifier((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__CONSTRAINT:
			setConstraint((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__IS_NAVIGABLE:
			setIsNavigable((Boolean) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__IS_CHANGABLE:
			setIsChangable((String) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__EA_LINK:
			setEaLink((ConnectorEnd) newValue);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__DERIVED:
			setDerived((Boolean) newValue);
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
		case EaadapterPackage.EA_CONNECTOR_END__NAME:
			setName(NAME_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__NOTES:
			setNotes(NOTES_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__GUID:
			setGuid(GUID_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__ID:
			setId(ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__TAGGED_VALUES:
			getTaggedValues().clear();
			return;
		case EaadapterPackage.EA_CONNECTOR_END__END:
			setEnd(END_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__CARDINALITY:
			setCardinality(CARDINALITY_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__VISIBILITY:
			setVisibility(VISIBILITY_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__ROLE:
			setRole(ROLE_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__ROLE_TYPE:
			setRoleType(ROLE_TYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__ROLE_NOTE:
			setRoleNote(ROLE_NOTE_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__CONTAINMENT:
			setContainment(CONTAINMENT_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__AGGREGATION:
			setAggregation(AGGREGATION_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__ORDERING:
			setOrdering(ORDERING_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__QUALIFIER:
			setQualifier(QUALIFIER_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__CONSTRAINT:
			setConstraint(CONSTRAINT_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__IS_NAVIGABLE:
			setIsNavigable(IS_NAVIGABLE_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__IS_CHANGABLE:
			setIsChangable(IS_CHANGABLE_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__EA_LINK:
			setEaLink(EA_LINK_EDEFAULT);
			return;
		case EaadapterPackage.EA_CONNECTOR_END__DERIVED:
			setDerived(DERIVED_EDEFAULT);
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
		case EaadapterPackage.EA_CONNECTOR_END__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case EaadapterPackage.EA_CONNECTOR_END__NOTES:
			return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
		case EaadapterPackage.EA_CONNECTOR_END__GUID:
			return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
		case EaadapterPackage.EA_CONNECTOR_END__ID:
			return id != ID_EDEFAULT;
		case EaadapterPackage.EA_CONNECTOR_END__TAGGED_VALUES:
			return taggedValues != null && !taggedValues.isEmpty();
		case EaadapterPackage.EA_CONNECTOR_END__END:
			return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
		case EaadapterPackage.EA_CONNECTOR_END__CARDINALITY:
			return CARDINALITY_EDEFAULT == null ? cardinality != null : !CARDINALITY_EDEFAULT.equals(cardinality);
		case EaadapterPackage.EA_CONNECTOR_END__VISIBILITY:
			return VISIBILITY_EDEFAULT == null ? visibility != null : !VISIBILITY_EDEFAULT.equals(visibility);
		case EaadapterPackage.EA_CONNECTOR_END__ROLE:
			return ROLE_EDEFAULT == null ? role != null : !ROLE_EDEFAULT.equals(role);
		case EaadapterPackage.EA_CONNECTOR_END__ROLE_TYPE:
			return ROLE_TYPE_EDEFAULT == null ? roleType != null : !ROLE_TYPE_EDEFAULT.equals(roleType);
		case EaadapterPackage.EA_CONNECTOR_END__ROLE_NOTE:
			return ROLE_NOTE_EDEFAULT == null ? roleNote != null : !ROLE_NOTE_EDEFAULT.equals(roleNote);
		case EaadapterPackage.EA_CONNECTOR_END__CONTAINMENT:
			return CONTAINMENT_EDEFAULT == null ? containment != null : !CONTAINMENT_EDEFAULT.equals(containment);
		case EaadapterPackage.EA_CONNECTOR_END__AGGREGATION:
			return aggregation != AGGREGATION_EDEFAULT;
		case EaadapterPackage.EA_CONNECTOR_END__ORDERING:
			return ordering != ORDERING_EDEFAULT;
		case EaadapterPackage.EA_CONNECTOR_END__QUALIFIER:
			return QUALIFIER_EDEFAULT == null ? qualifier != null : !QUALIFIER_EDEFAULT.equals(qualifier);
		case EaadapterPackage.EA_CONNECTOR_END__CONSTRAINT:
			return CONSTRAINT_EDEFAULT == null ? constraint != null : !CONSTRAINT_EDEFAULT.equals(constraint);
		case EaadapterPackage.EA_CONNECTOR_END__IS_NAVIGABLE:
			return isNavigable != IS_NAVIGABLE_EDEFAULT;
		case EaadapterPackage.EA_CONNECTOR_END__IS_CHANGABLE:
			return IS_CHANGABLE_EDEFAULT == null ? isChangable != null : !IS_CHANGABLE_EDEFAULT.equals(isChangable);
		case EaadapterPackage.EA_CONNECTOR_END__EA_LINK:
			return EA_LINK_EDEFAULT == null ? eaLink != null : !EA_LINK_EDEFAULT.equals(eaLink);
		case EaadapterPackage.EA_CONNECTOR_END__DERIVED:
			return derived != DERIVED_EDEFAULT;
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
		result.append(", end: ");
		result.append(end);
		result.append(", cardinality: ");
		result.append(cardinality);
		result.append(", visibility: ");
		result.append(visibility);
		result.append(", role: ");
		result.append(role);
		result.append(", roleType: ");
		result.append(roleType);
		result.append(", roleNote: ");
		result.append(roleNote);
		result.append(", containment: ");
		result.append(containment);
		result.append(", aggregation: ");
		result.append(aggregation);
		result.append(", ordering: ");
		result.append(ordering);
		result.append(", qualifier: ");
		result.append(qualifier);
		result.append(", constraint: ");
		result.append(constraint);
		result.append(", isNavigable: ");
		result.append(isNavigable);
		result.append(", isChangable: ");
		result.append(isChangable);
		result.append(", eaLink: ");
		result.append(eaLink);
		result.append(", derived: ");
		result.append(derived);
		result.append(')');
		return result.toString();
	}

} // EAConnectorEndImpl
