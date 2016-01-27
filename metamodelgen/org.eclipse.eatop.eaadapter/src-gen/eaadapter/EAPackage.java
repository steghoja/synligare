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

import eaadapter.abstracthierachy.EAAbstractPackage;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EA Package</b></em>'. <!-- end-user-doc --> <!--
 * begin-model-doc --> <div class='userdoc'> A package in EA is a container for other elements.<br>
 * A package can, for instance, be a UML package. <br>
 * <br>
 * <i>For detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html' target='_blank'>EA
 * User Guide</a>!</i> </div> <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eaadapter.EAPackage#getElements <em>Elements</em>}</li>
 * <li>{@link eaadapter.EAPackage#getPackages <em>Packages</em>}</li>
 * <li>{@link eaadapter.EAPackage#getSuperPackage <em>Super Package</em>}</li>
 * <li>{@link eaadapter.EAPackage#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.EAPackage#getStereotype <em>Stereotype</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eaadapter.EaadapterPackage#getEAPackage()
 * @model
 * @generated
 */
public interface EAPackage extends EAAbstractPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list. The list contents are of type
	 * {@link eaadapter.EAElement}. It is bidirectional and its opposite is '{@link eaadapter.EAElement#getPackage
	 * <em>Package</em>}'. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> <div class='userdoc'>
	 * Set of contained elements. </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAPackage_Elements()
	 * @see eaadapter.EAElement#getPackage
	 * @model opposite="package" containment="true"
	 * @generated
	 */
	EList<EAElement> getElements();

	/**
	 * Returns the value of the '<em><b>Packages</b></em>' containment reference list. The list contents are of type
	 * {@link eaadapter.EAPackage}. It is bidirectional and its opposite is '{@link eaadapter.EAPackage#getSuperPackage
	 * <em>Super Package</em>}'. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> <div
	 * class='userdoc'> Subpackages of this package. </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Packages</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEAPackage_Packages()
	 * @see eaadapter.EAPackage#getSuperPackage
	 * @model opposite="superPackage" containment="true"
	 * @generated
	 */
	EList<EAPackage> getPackages();

	/**
	 * Returns the value of the '<em><b>Super Package</b></em>' container reference. It is bidirectional and its
	 * opposite is '{@link eaadapter.EAPackage#getPackages <em>Packages</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc --> <div class='userdoc'> The parent package. </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Super Package</em>' container reference.
	 * @see #setSuperPackage(EAPackage)
	 * @see eaadapter.EaadapterPackage#getEAPackage_SuperPackage()
	 * @see eaadapter.EAPackage#getPackages
	 * @model opposite="packages" transient="false"
	 * @generated
	 */
	EAPackage getSuperPackage();

	/**
	 * Sets the value of the '{@link eaadapter.EAPackage#getSuperPackage <em>Super Package</em>}' container reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Super Package</em>' container reference.
	 * @see #getSuperPackage()
	 * @generated
	 */
	void setSuperPackage(EAPackage value);

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> DO NOT TOUCH THIS! This attributed is used as a link to the Enterprise Architect and is
	 * managed internally only! <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(org.sparx.Package)
	 * @see eaadapter.EaadapterPackage#getEAPackage_EaLink()
	 * @model dataType="eaadapter.datatypes.T_Package" transient="true"
	 * @generated
	 */
	org.sparx.Package getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EAPackage#getEaLink <em>Ea Link</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(org.sparx.Package value);

	/**
	 * Returns the value of the '<em><b>Stereotype</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc --> <div class='userdoc'> The stereotype attribute is part of an object of type
	 * <code>EAElement</code> which is referenced by each package.<br>
	 * This object causes a lot of trouble because it has the same <i>guid</i> as the package, so a wrapper getter and
	 * setter are generated by the code generation templates.<br>
	 * As a consequence, the stereotype attribute can be used as a usual attribute. <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Stereotype</em>' attribute.
	 * @see #setStereotype(String)
	 * @see eaadapter.EaadapterPackage#getEAPackage_Stereotype()
	 * @model
	 * @generated
	 */
	String getStereotype();

	/**
	 * Sets the value of the '{@link eaadapter.EAPackage#getStereotype <em>Stereotype</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Stereotype</em>' attribute.
	 * @see #getStereotype()
	 * @generated
	 */
	void setStereotype(String value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	@Override
	boolean validate(DiagnosticChain diagnostic, Map<Object, Object> context);

} // EAPackage
