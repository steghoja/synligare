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

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.sparx.Element;

import eaadapter.abstracthierachy.EAClassifierIDLong;
import eaadapter.abstracthierachy.EAOwnedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EA Element</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> <div class='userdoc'> An Element can, for instance, be a class, an interface or a UML Note.<br>
 * <br>
 * <br>
 * <i>For detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html' target='_blank'>EA
 * User Guide</a>!</i> </div> <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eaadapter.EAElement#getClassifierName <em>Classifier Name</em>}</li>
 * <li>{@link eaadapter.EAElement#getClassifierType <em>Classifier Type</em>}</li>
 * <li>{@link eaadapter.EAElement#getComplexity <em>Complexity</em>}</li>
 * <li>{@link eaadapter.EAElement#getDifficulty <em>Difficulty</em>}</li>
 * <li>{@link eaadapter.EAElement#getExtensionPoints <em>Extension Points</em>}</li>
 * <li>{@link eaadapter.EAElement#getGenlinks <em>Genlinks</em>}</li>
 * <li>{@link eaadapter.EAElement#getGenfile <em>Genfile</em>}</li>
 * <li>{@link eaadapter.EAElement#getGentype <em>Gentype</em>}</li>
 * <li>{@link eaadapter.EAElement#getMultiplicity <em>Multiplicity</em>}</li>
 * <li>{@link eaadapter.EAElement#getPhase <em>Phase</em>}</li>
 * <li>{@link eaadapter.EAElement#getPriority <em>Priority</em>}</li>
 * <li>{@link eaadapter.EAElement#getPropertyType <em>Property Type</em>}</li>
 * <li>{@link eaadapter.EAElement#getStatus <em>Status</em>}</li>
 * <li>{@link eaadapter.EAElement#getSubtype <em>Subtype</em>}</li>
 * <li>{@link eaadapter.EAElement#getTablespace <em>Tablespace</em>}</li>
 * <li>{@link eaadapter.EAElement#getTag <em>Tag</em>}</li>
 * <li>{@link eaadapter.EAElement#getElement <em>Element</em>}</li>
 * <li>{@link eaadapter.EAElement#getPackage <em>Package</em>}</li>
 * <li>{@link eaadapter.EAElement#getAttributes <em>Attributes</em>}</li>
 * <li>{@link eaadapter.EAElement#getConnectors <em>Connectors</em>}</li>
 * <li>{@link eaadapter.EAElement#getElements <em>Elements</em>}</li>
 * <li>{@link eaadapter.EAElement#getMethods <em>Methods</em>}</li>
 * <li>{@link eaadapter.EAElement#getTaggedValues <em>Tagged Values</em>}</li>
 * <li>{@link eaadapter.EAElement#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.EAElement#getParentID <em>Parent ID</em>}</li>
 * <li>{@link eaadapter.EAElement#getPackageID <em>Package ID</em>}</li>
 * <li>{@link eaadapter.EAElement#getAllConnectors <em>All Connectors</em>}</li>
 * <li>{@link eaadapter.EAElement#getVisibility <em>Visibility</em>}</li>
 * <li>{@link eaadapter.EAElement#isIsAbstract <em>Is Abstract</em>}</li>
 * <li>{@link eaadapter.EAElement#isIsActive <em>Is Active</em>}</li>
 * <li>{@link eaadapter.EAElement#getConstraints <em>Constraints</em>}</li>
 * <li>{@link eaadapter.EAElement#getMetaType <em>Meta Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eaadapter.EaadapterPackage#getEAElement()
 * @model
 * @generated
 */
public interface EAElement extends EAClassifierIDLong, EAOwnedElement {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Classifier Name</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classifier Name</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Classifier Name</em>' attribute.
	 * @see #setClassifierName(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_ClassifierName()
	 * @model
	 * @generated
	 */
	String getClassifierName();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getClassifierName <em>Classifier Name</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Classifier Name</em>' attribute.
	 * @see #getClassifierName()
	 * @generated
	 */
	void setClassifierName(String value);

	/**
	 * Returns the value of the '<em><b>Classifier Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Classifier Type</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Classifier Type</em>' attribute.
	 * @see eaadapter.EaadapterPackage#getEAElement_ClassifierType()
	 * @model changeable="false"
	 * @generated
	 */
	String getClassifierType();

	/**
	 * Returns the value of the '<em><b>Complexity</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Complexity</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Complexity</em>' attribute.
	 * @see #setComplexity(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Complexity()
	 * @model
	 * @generated
	 */
	String getComplexity();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getComplexity <em>Complexity</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Complexity</em>' attribute.
	 * @see #getComplexity()
	 * @generated
	 */
	void setComplexity(String value);

	/**
	 * Returns the value of the '<em><b>Difficulty</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Difficulty</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Difficulty</em>' attribute.
	 * @see #setDifficulty(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Difficulty()
	 * @model
	 * @generated
	 */
	String getDifficulty();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getDifficulty <em>Difficulty</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Difficulty</em>' attribute.
	 * @see #getDifficulty()
	 * @generated
	 */
	void setDifficulty(String value);

	/**
	 * Returns the value of the '<em><b>Extension Points</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension Points</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Extension Points</em>' attribute.
	 * @see #setExtensionPoints(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_ExtensionPoints()
	 * @model
	 * @generated
	 */
	String getExtensionPoints();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getExtensionPoints <em>Extension Points</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Extension Points</em>' attribute.
	 * @see #getExtensionPoints()
	 * @generated
	 */
	void setExtensionPoints(String value);

	/**
	 * Returns the value of the '<em><b>Genlinks</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Genlinks</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Genlinks</em>' attribute.
	 * @see #setGenlinks(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Genlinks()
	 * @model
	 * @generated
	 */
	String getGenlinks();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getGenlinks <em>Genlinks</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Genlinks</em>' attribute.
	 * @see #getGenlinks()
	 * @generated
	 */
	void setGenlinks(String value);

	/**
	 * Returns the value of the '<em><b>Genfile</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Genfile</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Genfile</em>' attribute.
	 * @see #setGenfile(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Genfile()
	 * @model
	 * @generated
	 */
	String getGenfile();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getGenfile <em>Genfile</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Genfile</em>' attribute.
	 * @see #getGenfile()
	 * @generated
	 */
	void setGenfile(String value);

	/**
	 * Returns the value of the '<em><b>Gentype</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gentype</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Gentype</em>' attribute.
	 * @see #setGentype(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Gentype()
	 * @model
	 * @generated
	 */
	String getGentype();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getGentype <em>Gentype</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Gentype</em>' attribute.
	 * @see #getGentype()
	 * @generated
	 */
	void setGentype(String value);

	/**
	 * Returns the value of the '<em><b>Multiplicity</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiplicity</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Multiplicity</em>' attribute.
	 * @see #setMultiplicity(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Multiplicity()
	 * @model
	 * @generated
	 */
	String getMultiplicity();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getMultiplicity <em>Multiplicity</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Multiplicity</em>' attribute.
	 * @see #getMultiplicity()
	 * @generated
	 */
	void setMultiplicity(String value);

	/**
	 * Returns the value of the '<em><b>Phase</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Phase</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Phase</em>' attribute.
	 * @see #setPhase(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Phase()
	 * @model
	 * @generated
	 */
	String getPhase();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getPhase <em>Phase</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Phase</em>' attribute.
	 * @see #getPhase()
	 * @generated
	 */
	void setPhase(String value);

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Priority()
	 * @model
	 * @generated
	 */
	String getPriority();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getPriority <em>Priority</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(String value);

	/**
	 * Returns the value of the '<em><b>Property Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Type</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Property Type</em>' attribute.
	 * @see #setPropertyType(Integer)
	 * @see eaadapter.EaadapterPackage#getEAElement_PropertyType()
	 * @model
	 * @generated
	 */
	Integer getPropertyType();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getPropertyType <em>Property Type</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Property Type</em>' attribute.
	 * @see #getPropertyType()
	 * @generated
	 */
	void setPropertyType(Integer value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see #setStatus(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Status()
	 * @model
	 * @generated
	 */
	String getStatus();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getStatus <em>Status</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Status</em>' attribute.
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(String value);

	/**
	 * Returns the value of the '<em><b>Subtype</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subtype</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Subtype</em>' attribute.
	 * @see #setSubtype(Integer)
	 * @see eaadapter.EaadapterPackage#getEAElement_Subtype()
	 * @model
	 * @generated
	 */
	Integer getSubtype();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getSubtype <em>Subtype</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Subtype</em>' attribute.
	 * @see #getSubtype()
	 * @generated
	 */
	void setSubtype(Integer value);

	/**
	 * Returns the value of the '<em><b>Tablespace</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tablespace</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Tablespace</em>' attribute.
	 * @see #setTablespace(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Tablespace()
	 * @model
	 * @generated
	 */
	String getTablespace();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getTablespace <em>Tablespace</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Tablespace</em>' attribute.
	 * @see #getTablespace()
	 * @generated
	 */
	void setTablespace(String value);

	/**
	 * Returns the value of the '<em><b>Tag</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tag</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Tag</em>' attribute.
	 * @see #setTag(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Tag()
	 * @model
	 * @generated
	 */
	String getTag();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getTag <em>Tag</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Tag</em>' attribute.
	 * @see #getTag()
	 * @generated
	 */
	void setTag(String value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' container reference. It is bidirectional and its opposite is '
	 * {@link eaadapter.EAElement#getElements <em>Elements</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Element</em>' container reference.
	 * @see #setElement(EAElement)
	 * @see eaadapter.EaadapterPackage#getEAElement_Element()
	 * @see eaadapter.EAElement#getElements
	 * @model opposite="elements"
	 * @generated
	 */
	EAElement getElement();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getElement <em>Element</em>}' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Element</em>' container reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(EAElement value);

	/**
	 * Returns the value of the '<em><b>Package</b></em>' container reference. It is bidirectional and its opposite is '
	 * {@link eaadapter.EAPackage#getElements <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> <div class='userdoc'> The parent package. </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Package</em>' container reference.
	 * @see #setPackage(EAPackage)
	 * @see eaadapter.EaadapterPackage#getEAElement_Package()
	 * @see eaadapter.EAPackage#getElements
	 * @model opposite="elements"
	 * @generated
	 */
	EAPackage getPackage();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getPackage <em>Package</em>}' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Package</em>' container reference.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(EAPackage value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list. The list contents are of type
	 * {@link eaadapter.EAAttribute}. It is bidirectional and its opposite is '{@link eaadapter.EAAttribute#getElement
	 * <em>Element</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAElement_Attributes()
	 * @see eaadapter.EAAttribute#getElement
	 * @model opposite="element" containment="true"
	 * @generated
	 */
	EList<EAAttribute> getAttributes();

	/**
	 * Returns the value of the '<em><b>Connectors</b></em>' containment reference list. The list contents are of type
	 * {@link eaadapter.EAConnector}. It is bidirectional and its opposite is '{@link eaadapter.EAConnector#getClient
	 * <em>Client</em>}'. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> <div class='userdoc'>
	 * The containment reference <code>connectors</code> contains all connectors from the Enterprise Architect model
	 * which have the current element as their 'source'. <br>
	 * Thus, connectors which have the current element as <code>supplier</code> are not contained! </div> <!--
	 * end-model-doc -->
	 * 
	 * @return the value of the '<em>Connectors</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAElement_Connectors()
	 * @see eaadapter.EAConnector#getClient
	 * @model opposite="client" containment="true"
	 * @generated
	 */
	EList<EAConnector> getConnectors();

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list. The list contents are of type
	 * {@link eaadapter.EAElement}. It is bidirectional and its opposite is '{@link eaadapter.EAElement#getElement
	 * <em>Element</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAElement_Elements()
	 * @see eaadapter.EAElement#getElement
	 * @model opposite="element" containment="true"
	 * @generated
	 */
	EList<EAElement> getElements();

	/**
	 * Returns the value of the '<em><b>Methods</b></em>' containment reference list. The list contents are of type
	 * {@link eaadapter.EAMethod}. It is bidirectional and its opposite is '{@link eaadapter.EAMethod#getElement
	 * <em>Element</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Methods</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Methods</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAElement_Methods()
	 * @see eaadapter.EAMethod#getElement
	 * @model opposite="element" containment="true"
	 * @generated
	 */
	EList<EAMethod> getMethods();

	/**
	 * Returns the value of the '<em><b>Tagged Values</b></em>' containment reference list. The list contents are of
	 * type {@link eaadapter.EATaggedValue}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tagged Values</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Tagged Values</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAElement_TaggedValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<EATaggedValue> getTaggedValues();

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> <div class='userdoc'> DO NOT TOUCH THIS! This attributed is used as a link to the Enterprise
	 * Architect and is managed internally only! </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(Element)
	 * @see eaadapter.EaadapterPackage#getEAElement_EaLink()
	 * @model dataType="eaadapter.datatypes.T_Element" transient="true"
	 * @generated
	 */
	Element getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getEaLink <em>Ea Link</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(Element value);

	/**
	 * Returns the value of the '<em><b>Parent ID</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent ID</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Parent ID</em>' attribute.
	 * @see #setParentID(Integer)
	 * @see eaadapter.EaadapterPackage#getEAElement_ParentID()
	 * @model
	 * @generated
	 */
	Integer getParentID();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getParentID <em>Parent ID</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Parent ID</em>' attribute.
	 * @see #getParentID()
	 * @generated
	 */
	void setParentID(Integer value);

	/**
	 * Returns the value of the '<em><b>Package ID</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package ID</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Package ID</em>' attribute.
	 * @see #setPackageID(Integer)
	 * @see eaadapter.EaadapterPackage#getEAElement_PackageID()
	 * @model
	 * @generated
	 */
	Integer getPackageID();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getPackageID <em>Package ID</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Package ID</em>' attribute.
	 * @see #getPackageID()
	 * @generated
	 */
	void setPackageID(Integer value);

	/**
	 * Returns the value of the '<em><b>All Connectors</b></em>' reference list. The list contents are of type
	 * {@link eaadapter.EAConnector}. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> <div
	 * class='userdoc'> The containment reference <code>allConnectors</code> contains all connectors from the Enterprise
	 * Architect model which references or are referenced by the current element. <br>
	 * Note that the containment reference is a derived attribute! </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>All Connectors</em>' reference list.
	 * @see eaadapter.EaadapterPackage#getEAElement_AllConnectors()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<EAConnector> getAllConnectors();

	/**
	 * Returns the value of the '<em><b>Visibility</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Visibility</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Visibility</em>' attribute.
	 * @see #setVisibility(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_Visibility()
	 * @model
	 * @generated
	 */
	String getVisibility();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getVisibility <em>Visibility</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Visibility</em>' attribute.
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(String value);

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(boolean)
	 * @see eaadapter.EaadapterPackage#getEAElement_IsAbstract()
	 * @model
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#isIsAbstract <em>Is Abstract</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Active</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Active</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Is Active</em>' attribute.
	 * @see #setIsActive(boolean)
	 * @see eaadapter.EaadapterPackage#getEAElement_IsActive()
	 * @model
	 * @generated
	 */
	boolean isIsActive();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#isIsActive <em>Is Active</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Is Active</em>' attribute.
	 * @see #isIsActive()
	 * @generated
	 */
	void setIsActive(boolean value);

	/**
	 * Returns the value of the '<em><b>Constraints</b></em>' containment reference list. The list contents are of type
	 * {@link eaadapter.EAConstraint}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Constraints</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAElement_Constraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<EAConstraint> getConstraints();

	/**
	 * Returns the value of the '<em><b>Meta Type</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta Type</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Meta Type</em>' attribute.
	 * @see #setMetaType(String)
	 * @see eaadapter.EaadapterPackage#getEAElement_MetaType()
	 * @model
	 * @generated
	 */
	String getMetaType();

	/**
	 * Sets the value of the '{@link eaadapter.EAElement#getMetaType <em>Meta Type</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Meta Type</em>' attribute.
	 * @see #getMetaType()
	 * @generated
	 */
	void setMetaType(String value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	@Override
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // EAElement
