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
import org.sparx.Attribute;

import eaadapter.abstracthierachy.EAClassifierIDLong;
import eaadapter.abstracthierachy.EAModifiableElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EA Attribute</b></em>'. <!-- end-user-doc -->
 * <!-- begin-model-doc --> <div class='userdoc'> An attribute of the element. <br>
 * <br>
 * <i>For more detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html'
 * target='_blank'>EA User Guide</a>!</i> </div> <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eaadapter.EAAttribute#getContainment <em>Containment</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getCollection <em>Collection</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getOrdered <em>Ordered</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getAllowDuplicates <em>Allow Duplicates</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getDerived <em>Derived</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getContainer <em>Container</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getScale <em>Scale</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getPrecision <em>Precision</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getLength <em>Length</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getLowerBound <em>Lower Bound</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getUpperBound <em>Upper Bound</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getDefault <em>Default</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getElement <em>Element</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getTaggedValues <em>Tagged Values</em>}</li>
 * <li>{@link eaadapter.EAAttribute#getVisibility <em>Visibility</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eaadapter.EaadapterPackage#getEAAttribute()
 * @model
 * @generated
 */
public interface EAAttribute extends EAModifiableElement, EAClassifierIDLong {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Containment</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containment</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Containment</em>' attribute.
	 * @see #setContainment(String)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Containment()
	 * @model
	 * @generated
	 */
	String getContainment();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getContainment <em>Containment</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Containment</em>' attribute.
	 * @see #getContainment()
	 * @generated
	 */
	void setContainment(String value);

	/**
	 * Returns the value of the '<em><b>Collection</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Collection</em>' attribute.
	 * @see #setCollection(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Collection()
	 * @model
	 * @generated
	 */
	Boolean getCollection();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getCollection <em>Collection</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Collection</em>' attribute.
	 * @see #getCollection()
	 * @generated
	 */
	void setCollection(Boolean value);

	/**
	 * Returns the value of the '<em><b>Ordered</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordered</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Ordered</em>' attribute.
	 * @see #setOrdered(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Ordered()
	 * @model
	 * @generated
	 */
	Boolean getOrdered();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getOrdered <em>Ordered</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Ordered</em>' attribute.
	 * @see #getOrdered()
	 * @generated
	 */
	void setOrdered(Boolean value);

	/**
	 * Returns the value of the '<em><b>Allow Duplicates</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allow Duplicates</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Allow Duplicates</em>' attribute.
	 * @see #setAllowDuplicates(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_AllowDuplicates()
	 * @model
	 * @generated
	 */
	Boolean getAllowDuplicates();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getAllowDuplicates <em>Allow Duplicates</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Allow Duplicates</em>' attribute.
	 * @see #getAllowDuplicates()
	 * @generated
	 */
	void setAllowDuplicates(Boolean value);

	/**
	 * Returns the value of the '<em><b>Derived</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Derived</em>' attribute.
	 * @see #setDerived(Boolean)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Derived()
	 * @model
	 * @generated
	 */
	Boolean getDerived();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getDerived <em>Derived</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Derived</em>' attribute.
	 * @see #getDerived()
	 * @generated
	 */
	void setDerived(Boolean value);

	/**
	 * Returns the value of the '<em><b>Container</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Container</em>' attribute.
	 * @see #setContainer(String)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Container()
	 * @model
	 * @generated
	 */
	String getContainer();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getContainer <em>Container</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Container</em>' attribute.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(String value);

	/**
	 * Returns the value of the '<em><b>Scale</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scale</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Scale</em>' attribute.
	 * @see #setScale(String)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Scale()
	 * @model
	 * @generated
	 */
	String getScale();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getScale <em>Scale</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Scale</em>' attribute.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(String value);

	/**
	 * Returns the value of the '<em><b>Precision</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precision</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Precision</em>' attribute.
	 * @see #setPrecision(String)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Precision()
	 * @model
	 * @generated
	 */
	String getPrecision();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getPrecision <em>Precision</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Precision</em>' attribute.
	 * @see #getPrecision()
	 * @generated
	 */
	void setPrecision(String value);

	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(String)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Length()
	 * @model
	 * @generated
	 */
	String getLength();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getLength <em>Length</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(String value);

	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lower Bound</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(String)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_LowerBound()
	 * @model
	 * @generated
	 */
	String getLowerBound();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getLowerBound <em>Lower Bound</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(String value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Upper Bound</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #setUpperBound(String)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_UpperBound()
	 * @model
	 * @generated
	 */
	String getUpperBound();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getUpperBound <em>Upper Bound</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(String value);

	/**
	 * Returns the value of the '<em><b>Default</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Default</em>' attribute.
	 * @see #setDefault(String)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Default()
	 * @model
	 * @generated
	 */
	String getDefault();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getDefault <em>Default</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Default</em>' attribute.
	 * @see #getDefault()
	 * @generated
	 */
	void setDefault(String value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' container reference. It is bidirectional and its opposite is '
	 * {@link eaadapter.EAElement#getAttributes <em>Attributes</em>}'. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Element</em>' container reference.
	 * @see #setElement(EAElement)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Element()
	 * @see eaadapter.EAElement#getAttributes
	 * @model opposite="attributes"
	 * @generated
	 */
	EAElement getElement();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getElement <em>Element</em>}' container reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Element</em>' container reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(EAElement value);

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> <div class='userdoc'> DO NOT TOUCH THIS! This attributed is used as a link to the Enterprise
	 * Architect and is managed internally only! </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(Attribute)
	 * @see eaadapter.EaadapterPackage#getEAAttribute_EaLink()
	 * @model dataType="eaadapter.datatypes.T_Attribute" transient="true"
	 * @generated
	 */
	Attribute getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getEaLink <em>Ea Link</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(Attribute value);

	/**
	 * Returns the value of the '<em><b>Tagged Values</b></em>' containment reference list. The list contents are of
	 * type {@link eaadapter.EAAttributeTag}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tagged Values</em>' containment reference list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Tagged Values</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAAttribute_TaggedValues()
	 * @model containment="true"
	 * @generated
	 */
	EList<EAAttributeTag> getTaggedValues();

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
	 * @see eaadapter.EaadapterPackage#getEAAttribute_Visibility()
	 * @model
	 * @generated
	 */
	String getVisibility();

	/**
	 * Sets the value of the '{@link eaadapter.EAAttribute#getVisibility <em>Visibility</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Visibility</em>' attribute.
	 * @see #getVisibility()
	 * @generated
	 */
	void setVisibility(String value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	@Override
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // EAAttribute
