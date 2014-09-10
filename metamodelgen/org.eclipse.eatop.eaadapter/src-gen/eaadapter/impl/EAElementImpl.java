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

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.sparx.Element;

import eaadapter.EAAttribute;
import eaadapter.EAConnector;
import eaadapter.EAConstraint;
import eaadapter.EAElement;
import eaadapter.EAMethod;
import eaadapter.EAPackage;
import eaadapter.EARepository;
import eaadapter.EATaggedValue;
import eaadapter.EaadapterPackage;
import eaadapter.abstracthierachy.AbstracthierachyPackage;
import eaadapter.abstracthierachy.EANamedElement;
import eaadapter.abstracthierachy.EAOwnedElement;
import eaadapter.abstracthierachy.EAVersiondElement;
import eaadapter.util.EaadapterValidator;
import eaadapter.validation.ValidationHelper;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>EA Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link eaadapter.impl.EAElementImpl#getName <em>Name</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getNotes <em>Notes</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getGuid <em>Guid</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getId <em>Id</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getStereotype <em>Stereotype</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getStereotypeEx <em>Stereotype Ex</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getType <em>Type</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getClassifierID <em>Classifier ID</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getVersion <em>Version</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getAuthor <em>Author</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getIsLocked <em>Is Locked</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getClassifierName <em>Classifier Name</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getClassifierType <em>Classifier Type</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getComplexity <em>Complexity</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getDifficulty <em>Difficulty</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getExtensionPoints <em>Extension Points</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getGenlinks <em>Genlinks</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getGenfile <em>Genfile</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getGentype <em>Gentype</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getMultiplicity <em>Multiplicity</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getPhase <em>Phase</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getPriority <em>Priority</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getPropertyType <em>Property Type</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getStatus <em>Status</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getSubtype <em>Subtype</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getTablespace <em>Tablespace</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getTag <em>Tag</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getElement <em>Element</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getPackage <em>Package</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getAttributes <em>Attributes</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getConnectors <em>Connectors</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getElements <em>Elements</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getMethods <em>Methods</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getTaggedValues <em>Tagged Values</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getParentID <em>Parent ID</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getPackageID <em>Package ID</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getAllConnectors <em>All Connectors</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getVisibility <em>Visibility</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#isIsAbstract <em>Is Abstract</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#isIsActive <em>Is Active</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getConstraints <em>Constraints</em>}</li>
 * <li>{@link eaadapter.impl.EAElementImpl#getMetaType <em>Meta Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EAElementImpl extends EObjectImpl implements EAElement {
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
	 * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected String author = AUTHOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getIsLocked() <em>Is Locked</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsLocked()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean IS_LOCKED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIsLocked() <em>Is Locked</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIsLocked()
	 * @generated
	 * @ordered
	 */
	protected Boolean isLocked = IS_LOCKED_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassifierName() <em>Classifier Name</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getClassifierName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASSIFIER_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassifierName() <em>Classifier Name</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getClassifierName()
	 * @generated
	 * @ordered
	 */
	protected String classifierName = CLASSIFIER_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getClassifierType() <em>Classifier Type</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getClassifierType()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASSIFIER_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassifierType() <em>Classifier Type</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getClassifierType()
	 * @generated
	 * @ordered
	 */
	protected String classifierType = CLASSIFIER_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getComplexity() <em>Complexity</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComplexity()
	 * @generated
	 * @ordered
	 */
	protected static final String COMPLEXITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getComplexity() <em>Complexity</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getComplexity()
	 * @generated
	 * @ordered
	 */
	protected String complexity = COMPLEXITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getDifficulty() <em>Difficulty</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDifficulty()
	 * @generated
	 * @ordered
	 */
	protected static final String DIFFICULTY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDifficulty() <em>Difficulty</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDifficulty()
	 * @generated
	 * @ordered
	 */
	protected String difficulty = DIFFICULTY_EDEFAULT;

	/**
	 * The default value of the '{@link #getExtensionPoints() <em>Extension Points</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getExtensionPoints()
	 * @generated
	 * @ordered
	 */
	protected static final String EXTENSION_POINTS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExtensionPoints() <em>Extension Points</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getExtensionPoints()
	 * @generated
	 * @ordered
	 */
	protected String extensionPoints = EXTENSION_POINTS_EDEFAULT;

	/**
	 * The default value of the '{@link #getGenlinks() <em>Genlinks</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGenlinks()
	 * @generated
	 * @ordered
	 */
	protected static final String GENLINKS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGenlinks() <em>Genlinks</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGenlinks()
	 * @generated
	 * @ordered
	 */
	protected String genlinks = GENLINKS_EDEFAULT;

	/**
	 * The default value of the '{@link #getGenfile() <em>Genfile</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGenfile()
	 * @generated
	 * @ordered
	 */
	protected static final String GENFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGenfile() <em>Genfile</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGenfile()
	 * @generated
	 * @ordered
	 */
	protected String genfile = GENFILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getGentype() <em>Gentype</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGentype()
	 * @generated
	 * @ordered
	 */
	protected static final String GENTYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGentype() <em>Gentype</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGentype()
	 * @generated
	 * @ordered
	 */
	protected String gentype = GENTYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected static final String MULTIPLICITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected String multiplicity = MULTIPLICITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPhase() <em>Phase</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPhase()
	 * @generated
	 * @ordered
	 */
	protected static final String PHASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPhase() <em>Phase</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPhase()
	 * @generated
	 * @ordered
	 */
	protected String phase = PHASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIORITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected String priority = PRIORITY_EDEFAULT;

	/**
	 * The default value of the '{@link #getPropertyType() <em>Property Type</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getPropertyType()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PROPERTY_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPropertyType() <em>Property Type</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getPropertyType()
	 * @generated
	 * @ordered
	 */
	protected Integer propertyType = PROPERTY_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final String STATUS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected String status = STATUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getSubtype() <em>Subtype</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getSubtype()
	 * @generated
	 * @ordered
	 */
	protected static final Integer SUBTYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubtype() <em>Subtype</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getSubtype()
	 * @generated
	 * @ordered
	 */
	protected Integer subtype = SUBTYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTablespace() <em>Tablespace</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getTablespace()
	 * @generated
	 * @ordered
	 */
	protected static final String TABLESPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTablespace() <em>Tablespace</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getTablespace()
	 * @generated
	 * @ordered
	 */
	protected String tablespace = TABLESPACE_EDEFAULT;

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
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<EAAttribute> attributes;

	/**
	 * The cached value of the '{@link #getConnectors() <em>Connectors</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<EAConnector> connectors;

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
	 * The cached value of the '{@link #getMethods() <em>Methods</em>}' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getMethods()
	 * @generated
	 * @ordered
	 */
	protected EList<EAMethod> methods;

	/**
	 * The cached value of the '{@link #getTaggedValues() <em>Tagged Values</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTaggedValues()
	 * @generated
	 * @ordered
	 */
	protected EList<EATaggedValue> taggedValues;

	/**
	 * The default value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected static final Element EA_LINK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEaLink() <em>Ea Link</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEaLink()
	 * @generated
	 * @ordered
	 */
	protected Element eaLink = EA_LINK_EDEFAULT;

	/**
	 * The default value of the '{@link #getParentID() <em>Parent ID</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getParentID()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PARENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParentID() <em>Parent ID</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getParentID()
	 * @generated
	 * @ordered
	 */
	protected Integer parentID = PARENT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPackageID() <em>Package ID</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPackageID()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PACKAGE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageID() <em>Package ID</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPackageID()
	 * @generated
	 * @ordered
	 */
	protected Integer packageID = PACKAGE_ID_EDEFAULT;

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
	 * The default value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ABSTRACT_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsAbstract() <em>Is Abstract</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isIsAbstract()
	 * @generated
	 * @ordered
	 */
	protected boolean isAbstract = IS_ABSTRACT_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsActive() <em>Is Active</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isIsActive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ACTIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsActive() <em>Is Active</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isIsActive()
	 * @generated
	 * @ordered
	 */
	protected boolean isActive = IS_ACTIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getConstraints() <em>Constraints</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<EAConstraint> constraints;

	/**
	 * The default value of the '{@link #getMetaType() <em>Meta Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMetaType()
	 * @generated
	 * @ordered
	 */
	protected static final String META_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMetaType() <em>Meta Type</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMetaType()
	 * @generated
	 * @ordered
	 */
	protected String metaType = META_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EAElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EaadapterPackage.Literals.EA_ELEMENT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__NOTES, oldNotes, notes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__GUID, oldGuid, guid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__STEREOTYPE, oldStereotype, stereotype));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__STEREOTYPE_EX, oldStereotypeEx, stereotypeEx));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__CLASSIFIER_ID, oldClassifierID, classifierID));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__VERSION, oldVersion, version));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getAuthor() {
		return author;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setAuthor(String newAuthor) {
		String oldAuthor = author;
		author = newAuthor;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__AUTHOR, oldAuthor, author));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Boolean getIsLocked() {
		return isLocked;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsLocked(Boolean newIsLocked) {
		Boolean oldIsLocked = isLocked;
		isLocked = newIsLocked;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__IS_LOCKED, oldIsLocked, isLocked));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getClassifierName() {
		return classifierName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setClassifierName(String newClassifierName) {
		String oldClassifierName = classifierName;
		classifierName = newClassifierName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__CLASSIFIER_NAME, oldClassifierName, classifierName));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getClassifierType() {
		return classifierType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getComplexity() {
		return complexity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setComplexity(String newComplexity) {
		String oldComplexity = complexity;
		complexity = newComplexity;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__COMPLEXITY, oldComplexity, complexity));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getDifficulty() {
		return difficulty;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDifficulty(String newDifficulty) {
		String oldDifficulty = difficulty;
		difficulty = newDifficulty;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__DIFFICULTY, oldDifficulty, difficulty));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getExtensionPoints() {
		return extensionPoints;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setExtensionPoints(String newExtensionPoints) {
		String oldExtensionPoints = extensionPoints;
		extensionPoints = newExtensionPoints;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__EXTENSION_POINTS, oldExtensionPoints, extensionPoints));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getGenlinks() {
		return genlinks;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGenlinks(String newGenlinks) {
		String oldGenlinks = genlinks;
		genlinks = newGenlinks;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__GENLINKS, oldGenlinks, genlinks));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getGenfile() {
		return genfile;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGenfile(String newGenfile) {
		String oldGenfile = genfile;
		genfile = newGenfile;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__GENFILE, oldGenfile, genfile));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getGentype() {
		return gentype;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGentype(String newGentype) {
		String oldGentype = gentype;
		gentype = newGentype;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__GENTYPE, oldGentype, gentype));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getMultiplicity() {
		return multiplicity;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMultiplicity(String newMultiplicity) {
		String oldMultiplicity = multiplicity;
		multiplicity = newMultiplicity;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__MULTIPLICITY, oldMultiplicity, multiplicity));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getPhase() {
		return phase;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPhase(String newPhase) {
		String oldPhase = phase;
		phase = newPhase;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__PHASE, oldPhase, phase));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPriority(String newPriority) {
		String oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__PRIORITY, oldPriority, priority));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Integer getPropertyType() {
		return propertyType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPropertyType(Integer newPropertyType) {
		Integer oldPropertyType = propertyType;
		propertyType = newPropertyType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__PROPERTY_TYPE, oldPropertyType, propertyType));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setStatus(String newStatus) {
		String oldStatus = status;
		status = newStatus;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__STATUS, oldStatus, status));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Integer getSubtype() {
		return subtype;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSubtype(Integer newSubtype) {
		Integer oldSubtype = subtype;
		subtype = newSubtype;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__SUBTYPE, oldSubtype, subtype));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getTablespace() {
		return tablespace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setTablespace(String newTablespace) {
		String oldTablespace = tablespace;
		tablespace = newTablespace;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__TABLESPACE, oldTablespace, tablespace));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__TAG, oldTag, tag));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAElement getElement() {
		if (eContainerFeatureID() != EaadapterPackage.EA_ELEMENT__ELEMENT) {
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
		msgs = eBasicSetContainer((InternalEObject) newElement, EaadapterPackage.EA_ELEMENT__ELEMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setElement(EAElement newElement) {
		if (newElement != eInternalContainer() || eContainerFeatureID() != EaadapterPackage.EA_ELEMENT__ELEMENT && newElement != null) {
			if (EcoreUtil.isAncestor(this, newElement)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newElement != null) {
				msgs = ((InternalEObject) newElement).eInverseAdd(this, EaadapterPackage.EA_ELEMENT__ELEMENTS, EAElement.class, msgs);
			}
			msgs = basicSetElement(newElement, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__ELEMENT, newElement, newElement));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAPackage getPackage() {
		if (eContainerFeatureID() != EaadapterPackage.EA_ELEMENT__PACKAGE) {
			return null;
		}
		return (EAPackage) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetPackage(EAPackage newPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newPackage, EaadapterPackage.EA_ELEMENT__PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPackage(EAPackage newPackage) {
		if (newPackage != eInternalContainer() || eContainerFeatureID() != EaadapterPackage.EA_ELEMENT__PACKAGE && newPackage != null) {
			if (EcoreUtil.isAncestor(this, newPackage)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newPackage != null) {
				msgs = ((InternalEObject) newPackage).eInverseAdd(this, EaadapterPackage.EA_PACKAGE__ELEMENTS, EAPackage.class, msgs);
			}
			msgs = basicSetPackage(newPackage, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		} else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__PACKAGE, newPackage, newPackage));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAAttribute> getAttributes() {
		if (attributes == null) {
			attributes = new EObjectContainmentWithInverseEList<EAAttribute>(EAAttribute.class, this, EaadapterPackage.EA_ELEMENT__ATTRIBUTES,
					EaadapterPackage.EA_ATTRIBUTE__ELEMENT);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAConnector> getConnectors() {
		if (connectors == null) {
			connectors = new EObjectContainmentWithInverseEList<EAConnector>(EAConnector.class, this, EaadapterPackage.EA_ELEMENT__CONNECTORS,
					EaadapterPackage.EA_CONNECTOR__CLIENT);
		}
		return connectors;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAElement> getElements() {
		if (elements == null) {
			elements = new EObjectContainmentWithInverseEList<EAElement>(EAElement.class, this, EaadapterPackage.EA_ELEMENT__ELEMENTS,
					EaadapterPackage.EA_ELEMENT__ELEMENT);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAMethod> getMethods() {
		if (methods == null) {
			methods = new EObjectContainmentWithInverseEList<EAMethod>(EAMethod.class, this, EaadapterPackage.EA_ELEMENT__METHODS,
					EaadapterPackage.EA_METHOD__ELEMENT);
		}
		return methods;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EATaggedValue> getTaggedValues() {
		if (taggedValues == null) {
			taggedValues = new EObjectContainmentEList<EATaggedValue>(EATaggedValue.class, this, EaadapterPackage.EA_ELEMENT__TAGGED_VALUES);
		}
		return taggedValues;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Element getEaLink() {
		return eaLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEaLink(Element newEaLink) {
		Element oldEaLink = eaLink;
		eaLink = newEaLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__EA_LINK, oldEaLink, eaLink));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Integer getParentID() {
		return parentID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setParentID(Integer newParentID) {
		Integer oldParentID = parentID;
		parentID = newParentID;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__PARENT_ID, oldParentID, parentID));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Integer getPackageID() {
		return packageID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPackageID(Integer newPackageID) {
		Integer oldPackageID = packageID;
		packageID = newPackageID;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__PACKAGE_ID, oldPackageID, packageID));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT mnick
	 */
	@Override
	public EList<EAConnector> getAllConnectors() {
		final EList<EAConnector> clientSupplierConnectors = getConnectors();

		EList<EAConnector> all = new BasicEList<EAConnector>();

		// add all client -> supplier connectors
		all.addAll(clientSupplierConnectors);

		// add all supplier -> client connectors
		for (TreeIterator iter = EcoreUtil.getAllContents(getRepository().getModels()); iter.hasNext();) {
			EANamedElement element = (EANamedElement) iter.next();
			if (element instanceof EAConnector) {
				EAConnector c = (EAConnector) element;
				// just add those, where the actual element is the supplier
				if (c.getSupplierID() == getId()) {
					all.add(c);
				}
			}
		}

		return all;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__VISIBILITY, oldVisibility, visibility));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isIsAbstract() {
		return isAbstract;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsAbstract(boolean newIsAbstract) {
		boolean oldIsAbstract = isAbstract;
		isAbstract = newIsAbstract;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__IS_ABSTRACT, oldIsAbstract, isAbstract));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isIsActive() {
		return isActive;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIsActive(boolean newIsActive) {
		boolean oldIsActive = isActive;
		isActive = newIsActive;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__IS_ACTIVE, oldIsActive, isActive));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EAConstraint> getConstraints() {
		if (constraints == null) {
			constraints = new EObjectContainmentEList<EAConstraint>(EAConstraint.class, this, EaadapterPackage.EA_ELEMENT__CONSTRAINTS);
		}
		return constraints;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getMetaType() {
		return metaType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMetaType(String newMetaType) {
		String oldMetaType = metaType;
		metaType = newMetaType;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, EaadapterPackage.EA_ELEMENT__META_TYPE, oldMetaType, metaType));
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

		if (!shouldIConsiderElement(this)) {
			return false;
		}

		if (ValidationHelper.hasLeadingOrTrailingSpaces(getName())) {
			if (diagnostic != null) {
				diagnostic.add(new BasicDiagnostic(Diagnostic.WARNING, EaadapterValidator.DIAGNOSTIC_SOURCE, EaadapterValidator.EA_ELEMENT__VALIDATE,
						"The element's name \"" + getName() + "\" has leading/trailing whitespaces!", new Object[] { this }));
			}
			isValid = false;
		}

		if (!ValidationHelper.isNameWellFormed(getName())) {
			if (diagnostic != null) {
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, EaadapterValidator.DIAGNOSTIC_SOURCE, EaadapterValidator.EA_ELEMENT__VALIDATE,
						"The element's name is not well formed!", new Object[] { this }));
			}
			isValid = false;
		}

		if (elementHasInvalidSubElements(this)) {
			if (diagnostic != null) {
				diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, EaadapterValidator.DIAGNOSTIC_SOURCE, EaadapterValidator.EA_ELEMENT__VALIDATE,
						String.format("Element '%s' has %d sub-element(s): (%s)! Is this a modelling fault?! We don't know how to handle it!",
								getName(), getElements().size(), invalidElementsString(this)), new Object[] { this }));
			}
			isValid = false;
		}

		return isValid;
	}

	private boolean elementHasInvalidSubElements(EAElement element) {
		if (getElements().size() == 0) {
			return false;
		}

		/**
		 * UseCase, Requirement, Note,... can have sub-elements
		 */
		if (!shouldIConsiderElement(element)) {
			return false;
		}

		for (EAElement subElement : element.getElements()) {
			if (shouldIConsiderElement(subElement)) {
				return true;
			}
		}

		return false;
	}

	private String invalidElementsString(EAElement element) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < element.getElements().size(); i++) {
			b.append(String.format("'%s'", element.getElements().get(i).getName()));
			if (i < element.getElements().size() - 1) {
				b.append(", ");
			}
		}
		return b.toString();
	}

	private boolean shouldIConsiderElement(EAElement e) {
		String[] elementsNotToConsider = new String[] { "Boundary", "Note", "Text", "Requirement", "UseCase" };
		if (Arrays.asList(elementsNotToConsider).contains(e.getMetaType())) {
			return false;
		}
		return true;
	}

	/*
	 * This function returns the root repository element
	 */
	private EARepository getRepository() {
		EObject e = this;
		while (e.eContainer() != null) {
			e = e.eContainer();
			if (e instanceof EARepository) {
				return (EARepository) e;
			}
		}
		return null;
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
		case EaadapterPackage.EA_ELEMENT__ELEMENT:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetElement((EAElement) otherEnd, msgs);
		case EaadapterPackage.EA_ELEMENT__PACKAGE:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetPackage((EAPackage) otherEnd, msgs);
		case EaadapterPackage.EA_ELEMENT__ATTRIBUTES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getAttributes()).basicAdd(otherEnd, msgs);
		case EaadapterPackage.EA_ELEMENT__CONNECTORS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getConnectors()).basicAdd(otherEnd, msgs);
		case EaadapterPackage.EA_ELEMENT__ELEMENTS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getElements()).basicAdd(otherEnd, msgs);
		case EaadapterPackage.EA_ELEMENT__METHODS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getMethods()).basicAdd(otherEnd, msgs);
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
		case EaadapterPackage.EA_ELEMENT__ELEMENT:
			return basicSetElement(null, msgs);
		case EaadapterPackage.EA_ELEMENT__PACKAGE:
			return basicSetPackage(null, msgs);
		case EaadapterPackage.EA_ELEMENT__ATTRIBUTES:
			return ((InternalEList<?>) getAttributes()).basicRemove(otherEnd, msgs);
		case EaadapterPackage.EA_ELEMENT__CONNECTORS:
			return ((InternalEList<?>) getConnectors()).basicRemove(otherEnd, msgs);
		case EaadapterPackage.EA_ELEMENT__ELEMENTS:
			return ((InternalEList<?>) getElements()).basicRemove(otherEnd, msgs);
		case EaadapterPackage.EA_ELEMENT__METHODS:
			return ((InternalEList<?>) getMethods()).basicRemove(otherEnd, msgs);
		case EaadapterPackage.EA_ELEMENT__TAGGED_VALUES:
			return ((InternalEList<?>) getTaggedValues()).basicRemove(otherEnd, msgs);
		case EaadapterPackage.EA_ELEMENT__CONSTRAINTS:
			return ((InternalEList<?>) getConstraints()).basicRemove(otherEnd, msgs);
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
		case EaadapterPackage.EA_ELEMENT__ELEMENT:
			return eInternalContainer().eInverseRemove(this, EaadapterPackage.EA_ELEMENT__ELEMENTS, EAElement.class, msgs);
		case EaadapterPackage.EA_ELEMENT__PACKAGE:
			return eInternalContainer().eInverseRemove(this, EaadapterPackage.EA_PACKAGE__ELEMENTS, EAPackage.class, msgs);
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
		case EaadapterPackage.EA_ELEMENT__NAME:
			return getName();
		case EaadapterPackage.EA_ELEMENT__NOTES:
			return getNotes();
		case EaadapterPackage.EA_ELEMENT__GUID:
			return getGuid();
		case EaadapterPackage.EA_ELEMENT__ID:
			return getId();
		case EaadapterPackage.EA_ELEMENT__STEREOTYPE:
			return getStereotype();
		case EaadapterPackage.EA_ELEMENT__STEREOTYPE_EX:
			return getStereotypeEx();
		case EaadapterPackage.EA_ELEMENT__TYPE:
			return getType();
		case EaadapterPackage.EA_ELEMENT__CLASSIFIER_ID:
			return getClassifierID();
		case EaadapterPackage.EA_ELEMENT__VERSION:
			return getVersion();
		case EaadapterPackage.EA_ELEMENT__AUTHOR:
			return getAuthor();
		case EaadapterPackage.EA_ELEMENT__IS_LOCKED:
			return getIsLocked();
		case EaadapterPackage.EA_ELEMENT__CLASSIFIER_NAME:
			return getClassifierName();
		case EaadapterPackage.EA_ELEMENT__CLASSIFIER_TYPE:
			return getClassifierType();
		case EaadapterPackage.EA_ELEMENT__COMPLEXITY:
			return getComplexity();
		case EaadapterPackage.EA_ELEMENT__DIFFICULTY:
			return getDifficulty();
		case EaadapterPackage.EA_ELEMENT__EXTENSION_POINTS:
			return getExtensionPoints();
		case EaadapterPackage.EA_ELEMENT__GENLINKS:
			return getGenlinks();
		case EaadapterPackage.EA_ELEMENT__GENFILE:
			return getGenfile();
		case EaadapterPackage.EA_ELEMENT__GENTYPE:
			return getGentype();
		case EaadapterPackage.EA_ELEMENT__MULTIPLICITY:
			return getMultiplicity();
		case EaadapterPackage.EA_ELEMENT__PHASE:
			return getPhase();
		case EaadapterPackage.EA_ELEMENT__PRIORITY:
			return getPriority();
		case EaadapterPackage.EA_ELEMENT__PROPERTY_TYPE:
			return getPropertyType();
		case EaadapterPackage.EA_ELEMENT__STATUS:
			return getStatus();
		case EaadapterPackage.EA_ELEMENT__SUBTYPE:
			return getSubtype();
		case EaadapterPackage.EA_ELEMENT__TABLESPACE:
			return getTablespace();
		case EaadapterPackage.EA_ELEMENT__TAG:
			return getTag();
		case EaadapterPackage.EA_ELEMENT__ELEMENT:
			return getElement();
		case EaadapterPackage.EA_ELEMENT__PACKAGE:
			return getPackage();
		case EaadapterPackage.EA_ELEMENT__ATTRIBUTES:
			return getAttributes();
		case EaadapterPackage.EA_ELEMENT__CONNECTORS:
			return getConnectors();
		case EaadapterPackage.EA_ELEMENT__ELEMENTS:
			return getElements();
		case EaadapterPackage.EA_ELEMENT__METHODS:
			return getMethods();
		case EaadapterPackage.EA_ELEMENT__TAGGED_VALUES:
			return getTaggedValues();
		case EaadapterPackage.EA_ELEMENT__EA_LINK:
			return getEaLink();
		case EaadapterPackage.EA_ELEMENT__PARENT_ID:
			return getParentID();
		case EaadapterPackage.EA_ELEMENT__PACKAGE_ID:
			return getPackageID();
		case EaadapterPackage.EA_ELEMENT__ALL_CONNECTORS:
			return getAllConnectors();
		case EaadapterPackage.EA_ELEMENT__VISIBILITY:
			return getVisibility();
		case EaadapterPackage.EA_ELEMENT__IS_ABSTRACT:
			return isIsAbstract();
		case EaadapterPackage.EA_ELEMENT__IS_ACTIVE:
			return isIsActive();
		case EaadapterPackage.EA_ELEMENT__CONSTRAINTS:
			return getConstraints();
		case EaadapterPackage.EA_ELEMENT__META_TYPE:
			return getMetaType();
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
		case EaadapterPackage.EA_ELEMENT__NAME:
			setName((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__NOTES:
			setNotes((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__GUID:
			setGuid((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__ID:
			setId((Long) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__STEREOTYPE:
			setStereotype((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__STEREOTYPE_EX:
			setStereotypeEx((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__TYPE:
			setType((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__CLASSIFIER_ID:
			setClassifierID((Integer) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__VERSION:
			setVersion((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__AUTHOR:
			setAuthor((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__IS_LOCKED:
			setIsLocked((Boolean) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__CLASSIFIER_NAME:
			setClassifierName((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__COMPLEXITY:
			setComplexity((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__DIFFICULTY:
			setDifficulty((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__EXTENSION_POINTS:
			setExtensionPoints((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__GENLINKS:
			setGenlinks((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__GENFILE:
			setGenfile((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__GENTYPE:
			setGentype((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__MULTIPLICITY:
			setMultiplicity((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__PHASE:
			setPhase((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__PRIORITY:
			setPriority((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__PROPERTY_TYPE:
			setPropertyType((Integer) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__STATUS:
			setStatus((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__SUBTYPE:
			setSubtype((Integer) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__TABLESPACE:
			setTablespace((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__TAG:
			setTag((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__ELEMENT:
			setElement((EAElement) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__PACKAGE:
			setPackage((EAPackage) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__ATTRIBUTES:
			getAttributes().clear();
			getAttributes().addAll((Collection<? extends EAAttribute>) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__CONNECTORS:
			getConnectors().clear();
			getConnectors().addAll((Collection<? extends EAConnector>) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__ELEMENTS:
			getElements().clear();
			getElements().addAll((Collection<? extends EAElement>) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__METHODS:
			getMethods().clear();
			getMethods().addAll((Collection<? extends EAMethod>) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__TAGGED_VALUES:
			getTaggedValues().clear();
			getTaggedValues().addAll((Collection<? extends EATaggedValue>) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__EA_LINK:
			setEaLink((Element) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__PARENT_ID:
			setParentID((Integer) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__PACKAGE_ID:
			setPackageID((Integer) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__VISIBILITY:
			setVisibility((String) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__IS_ABSTRACT:
			setIsAbstract((Boolean) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__IS_ACTIVE:
			setIsActive((Boolean) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__CONSTRAINTS:
			getConstraints().clear();
			getConstraints().addAll((Collection<? extends EAConstraint>) newValue);
			return;
		case EaadapterPackage.EA_ELEMENT__META_TYPE:
			setMetaType((String) newValue);
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
		case EaadapterPackage.EA_ELEMENT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__NOTES:
			setNotes(NOTES_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__GUID:
			setGuid(GUID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__ID:
			setId(ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__STEREOTYPE:
			setStereotype(STEREOTYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__STEREOTYPE_EX:
			setStereotypeEx(STEREOTYPE_EX_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__TYPE:
			setType(TYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__CLASSIFIER_ID:
			setClassifierID(CLASSIFIER_ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__AUTHOR:
			setAuthor(AUTHOR_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__IS_LOCKED:
			setIsLocked(IS_LOCKED_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__CLASSIFIER_NAME:
			setClassifierName(CLASSIFIER_NAME_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__COMPLEXITY:
			setComplexity(COMPLEXITY_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__DIFFICULTY:
			setDifficulty(DIFFICULTY_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__EXTENSION_POINTS:
			setExtensionPoints(EXTENSION_POINTS_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__GENLINKS:
			setGenlinks(GENLINKS_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__GENFILE:
			setGenfile(GENFILE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__GENTYPE:
			setGentype(GENTYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__MULTIPLICITY:
			setMultiplicity(MULTIPLICITY_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__PHASE:
			setPhase(PHASE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__PRIORITY:
			setPriority(PRIORITY_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__PROPERTY_TYPE:
			setPropertyType(PROPERTY_TYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__STATUS:
			setStatus(STATUS_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__SUBTYPE:
			setSubtype(SUBTYPE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__TABLESPACE:
			setTablespace(TABLESPACE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__TAG:
			setTag(TAG_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__ELEMENT:
			setElement((EAElement) null);
			return;
		case EaadapterPackage.EA_ELEMENT__PACKAGE:
			setPackage((EAPackage) null);
			return;
		case EaadapterPackage.EA_ELEMENT__ATTRIBUTES:
			getAttributes().clear();
			return;
		case EaadapterPackage.EA_ELEMENT__CONNECTORS:
			getConnectors().clear();
			return;
		case EaadapterPackage.EA_ELEMENT__ELEMENTS:
			getElements().clear();
			return;
		case EaadapterPackage.EA_ELEMENT__METHODS:
			getMethods().clear();
			return;
		case EaadapterPackage.EA_ELEMENT__TAGGED_VALUES:
			getTaggedValues().clear();
			return;
		case EaadapterPackage.EA_ELEMENT__EA_LINK:
			setEaLink(EA_LINK_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__PARENT_ID:
			setParentID(PARENT_ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__PACKAGE_ID:
			setPackageID(PACKAGE_ID_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__VISIBILITY:
			setVisibility(VISIBILITY_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__IS_ABSTRACT:
			setIsAbstract(IS_ABSTRACT_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__IS_ACTIVE:
			setIsActive(IS_ACTIVE_EDEFAULT);
			return;
		case EaadapterPackage.EA_ELEMENT__CONSTRAINTS:
			getConstraints().clear();
			return;
		case EaadapterPackage.EA_ELEMENT__META_TYPE:
			setMetaType(META_TYPE_EDEFAULT);
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
		case EaadapterPackage.EA_ELEMENT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case EaadapterPackage.EA_ELEMENT__NOTES:
			return NOTES_EDEFAULT == null ? notes != null : !NOTES_EDEFAULT.equals(notes);
		case EaadapterPackage.EA_ELEMENT__GUID:
			return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
		case EaadapterPackage.EA_ELEMENT__ID:
			return id != ID_EDEFAULT;
		case EaadapterPackage.EA_ELEMENT__STEREOTYPE:
			return STEREOTYPE_EDEFAULT == null ? stereotype != null : !STEREOTYPE_EDEFAULT.equals(stereotype);
		case EaadapterPackage.EA_ELEMENT__STEREOTYPE_EX:
			return STEREOTYPE_EX_EDEFAULT == null ? stereotypeEx != null : !STEREOTYPE_EX_EDEFAULT.equals(stereotypeEx);
		case EaadapterPackage.EA_ELEMENT__TYPE:
			return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
		case EaadapterPackage.EA_ELEMENT__CLASSIFIER_ID:
			return CLASSIFIER_ID_EDEFAULT == null ? classifierID != null : !CLASSIFIER_ID_EDEFAULT.equals(classifierID);
		case EaadapterPackage.EA_ELEMENT__VERSION:
			return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
		case EaadapterPackage.EA_ELEMENT__AUTHOR:
			return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
		case EaadapterPackage.EA_ELEMENT__IS_LOCKED:
			return IS_LOCKED_EDEFAULT == null ? isLocked != null : !IS_LOCKED_EDEFAULT.equals(isLocked);
		case EaadapterPackage.EA_ELEMENT__CLASSIFIER_NAME:
			return CLASSIFIER_NAME_EDEFAULT == null ? classifierName != null : !CLASSIFIER_NAME_EDEFAULT.equals(classifierName);
		case EaadapterPackage.EA_ELEMENT__CLASSIFIER_TYPE:
			return CLASSIFIER_TYPE_EDEFAULT == null ? classifierType != null : !CLASSIFIER_TYPE_EDEFAULT.equals(classifierType);
		case EaadapterPackage.EA_ELEMENT__COMPLEXITY:
			return COMPLEXITY_EDEFAULT == null ? complexity != null : !COMPLEXITY_EDEFAULT.equals(complexity);
		case EaadapterPackage.EA_ELEMENT__DIFFICULTY:
			return DIFFICULTY_EDEFAULT == null ? difficulty != null : !DIFFICULTY_EDEFAULT.equals(difficulty);
		case EaadapterPackage.EA_ELEMENT__EXTENSION_POINTS:
			return EXTENSION_POINTS_EDEFAULT == null ? extensionPoints != null : !EXTENSION_POINTS_EDEFAULT.equals(extensionPoints);
		case EaadapterPackage.EA_ELEMENT__GENLINKS:
			return GENLINKS_EDEFAULT == null ? genlinks != null : !GENLINKS_EDEFAULT.equals(genlinks);
		case EaadapterPackage.EA_ELEMENT__GENFILE:
			return GENFILE_EDEFAULT == null ? genfile != null : !GENFILE_EDEFAULT.equals(genfile);
		case EaadapterPackage.EA_ELEMENT__GENTYPE:
			return GENTYPE_EDEFAULT == null ? gentype != null : !GENTYPE_EDEFAULT.equals(gentype);
		case EaadapterPackage.EA_ELEMENT__MULTIPLICITY:
			return MULTIPLICITY_EDEFAULT == null ? multiplicity != null : !MULTIPLICITY_EDEFAULT.equals(multiplicity);
		case EaadapterPackage.EA_ELEMENT__PHASE:
			return PHASE_EDEFAULT == null ? phase != null : !PHASE_EDEFAULT.equals(phase);
		case EaadapterPackage.EA_ELEMENT__PRIORITY:
			return PRIORITY_EDEFAULT == null ? priority != null : !PRIORITY_EDEFAULT.equals(priority);
		case EaadapterPackage.EA_ELEMENT__PROPERTY_TYPE:
			return PROPERTY_TYPE_EDEFAULT == null ? propertyType != null : !PROPERTY_TYPE_EDEFAULT.equals(propertyType);
		case EaadapterPackage.EA_ELEMENT__STATUS:
			return STATUS_EDEFAULT == null ? status != null : !STATUS_EDEFAULT.equals(status);
		case EaadapterPackage.EA_ELEMENT__SUBTYPE:
			return SUBTYPE_EDEFAULT == null ? subtype != null : !SUBTYPE_EDEFAULT.equals(subtype);
		case EaadapterPackage.EA_ELEMENT__TABLESPACE:
			return TABLESPACE_EDEFAULT == null ? tablespace != null : !TABLESPACE_EDEFAULT.equals(tablespace);
		case EaadapterPackage.EA_ELEMENT__TAG:
			return TAG_EDEFAULT == null ? tag != null : !TAG_EDEFAULT.equals(tag);
		case EaadapterPackage.EA_ELEMENT__ELEMENT:
			return getElement() != null;
		case EaadapterPackage.EA_ELEMENT__PACKAGE:
			return getPackage() != null;
		case EaadapterPackage.EA_ELEMENT__ATTRIBUTES:
			return attributes != null && !attributes.isEmpty();
		case EaadapterPackage.EA_ELEMENT__CONNECTORS:
			return connectors != null && !connectors.isEmpty();
		case EaadapterPackage.EA_ELEMENT__ELEMENTS:
			return elements != null && !elements.isEmpty();
		case EaadapterPackage.EA_ELEMENT__METHODS:
			return methods != null && !methods.isEmpty();
		case EaadapterPackage.EA_ELEMENT__TAGGED_VALUES:
			return taggedValues != null && !taggedValues.isEmpty();
		case EaadapterPackage.EA_ELEMENT__EA_LINK:
			return EA_LINK_EDEFAULT == null ? eaLink != null : !EA_LINK_EDEFAULT.equals(eaLink);
		case EaadapterPackage.EA_ELEMENT__PARENT_ID:
			return PARENT_ID_EDEFAULT == null ? parentID != null : !PARENT_ID_EDEFAULT.equals(parentID);
		case EaadapterPackage.EA_ELEMENT__PACKAGE_ID:
			return PACKAGE_ID_EDEFAULT == null ? packageID != null : !PACKAGE_ID_EDEFAULT.equals(packageID);
		case EaadapterPackage.EA_ELEMENT__ALL_CONNECTORS:
			return !getAllConnectors().isEmpty();
		case EaadapterPackage.EA_ELEMENT__VISIBILITY:
			return VISIBILITY_EDEFAULT == null ? visibility != null : !VISIBILITY_EDEFAULT.equals(visibility);
		case EaadapterPackage.EA_ELEMENT__IS_ABSTRACT:
			return isAbstract != IS_ABSTRACT_EDEFAULT;
		case EaadapterPackage.EA_ELEMENT__IS_ACTIVE:
			return isActive != IS_ACTIVE_EDEFAULT;
		case EaadapterPackage.EA_ELEMENT__CONSTRAINTS:
			return constraints != null && !constraints.isEmpty();
		case EaadapterPackage.EA_ELEMENT__META_TYPE:
			return META_TYPE_EDEFAULT == null ? metaType != null : !META_TYPE_EDEFAULT.equals(metaType);
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
		if (baseClass == EAVersiondElement.class) {
			switch (derivedFeatureID) {
			case EaadapterPackage.EA_ELEMENT__VERSION:
				return AbstracthierachyPackage.EA_VERSIOND_ELEMENT__VERSION;
			default:
				return -1;
			}
		}
		if (baseClass == EAOwnedElement.class) {
			switch (derivedFeatureID) {
			case EaadapterPackage.EA_ELEMENT__AUTHOR:
				return AbstracthierachyPackage.EA_OWNED_ELEMENT__AUTHOR;
			case EaadapterPackage.EA_ELEMENT__IS_LOCKED:
				return AbstracthierachyPackage.EA_OWNED_ELEMENT__IS_LOCKED;
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
		if (baseClass == EAVersiondElement.class) {
			switch (baseFeatureID) {
			case AbstracthierachyPackage.EA_VERSIOND_ELEMENT__VERSION:
				return EaadapterPackage.EA_ELEMENT__VERSION;
			default:
				return -1;
			}
		}
		if (baseClass == EAOwnedElement.class) {
			switch (baseFeatureID) {
			case AbstracthierachyPackage.EA_OWNED_ELEMENT__AUTHOR:
				return EaadapterPackage.EA_ELEMENT__AUTHOR;
			case AbstracthierachyPackage.EA_OWNED_ELEMENT__IS_LOCKED:
				return EaadapterPackage.EA_ELEMENT__IS_LOCKED;
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
		result.append(", classifierID: ");
		result.append(classifierID);
		result.append(", version: ");
		result.append(version);
		result.append(", author: ");
		result.append(author);
		result.append(", isLocked: ");
		result.append(isLocked);
		result.append(", classifierName: ");
		result.append(classifierName);
		result.append(", classifierType: ");
		result.append(classifierType);
		result.append(", complexity: ");
		result.append(complexity);
		result.append(", difficulty: ");
		result.append(difficulty);
		result.append(", extensionPoints: ");
		result.append(extensionPoints);
		result.append(", genlinks: ");
		result.append(genlinks);
		result.append(", genfile: ");
		result.append(genfile);
		result.append(", gentype: ");
		result.append(gentype);
		result.append(", multiplicity: ");
		result.append(multiplicity);
		result.append(", phase: ");
		result.append(phase);
		result.append(", priority: ");
		result.append(priority);
		result.append(", propertyType: ");
		result.append(propertyType);
		result.append(", status: ");
		result.append(status);
		result.append(", subtype: ");
		result.append(subtype);
		result.append(", tablespace: ");
		result.append(tablespace);
		result.append(", tag: ");
		result.append(tag);
		result.append(", eaLink: ");
		result.append(eaLink);
		result.append(", parentID: ");
		result.append(parentID);
		result.append(", packageID: ");
		result.append(packageID);
		result.append(", visibility: ");
		result.append(visibility);
		result.append(", isAbstract: ");
		result.append(isAbstract);
		result.append(", isActive: ");
		result.append(isActive);
		result.append(", metaType: ");
		result.append(metaType);
		result.append(')');
		return result.toString();
	}

} // EAElementImpl
