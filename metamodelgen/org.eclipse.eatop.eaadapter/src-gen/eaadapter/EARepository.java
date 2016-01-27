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

import java.io.File;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.sparx.Repository;

import eaadapter.abstracthierachy.EANamedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>EA Repository</b></em>'. <!-- end-user-doc -->
 * <!-- begin-model-doc --> <div class='userdoc'> Root object for an EA project.<br>
 * This object takes care of the link between the EMF model and the EA project. <br>
 * <br>
 * <i>For detailled documentation see <a href='http://www.sparxsystems.com.au/EAUserGuide/index.html' target='_blank'>EA
 * User Guide</a>!</i> </div> <!-- end-model-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link eaadapter.EARepository#getEaLink <em>Ea Link</em>}</li>
 * <li>{@link eaadapter.EARepository#getFile <em>File</em>}</li>
 * <li>{@link eaadapter.EARepository#getModels <em>Models</em>}</li>
 * </ul>
 * </p>
 * 
 * @see eaadapter.EaadapterPackage#getEARepository()
 * @model
 * @generated
 */
public interface EARepository extends EObject {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Returns the value of the '<em><b>Ea Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> <div class='userdoc'> DO NOT TOUCH THIS! This attributed is used as a link to the Enterprise
	 * Architect and is managed internally only! </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ea Link</em>' attribute.
	 * @see #setEaLink(Repository)
	 * @see eaadapter.EaadapterPackage#getEARepository_EaLink()
	 * @model dataType="eaadapter.datatypes.T_Repository" transient="true"
	 * @generated
	 */
	Repository getEaLink();

	/**
	 * Sets the value of the '{@link eaadapter.EARepository#getEaLink <em>Ea Link</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Ea Link</em>' attribute.
	 * @see #getEaLink()
	 * @generated
	 */
	void setEaLink(Repository value);

	/**
	 * Returns the value of the '<em><b>File</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
	 * begin-model-doc --> <div class='userdoc'> Specify the Enterprise Architect project file here.<br>
	 * An absolut path is required!<br>
	 * TODO: Implement support for relative paths and eclipse resource URIs. </div> <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>File</em>' attribute.
	 * @see #setFile(File)
	 * @see eaadapter.EaadapterPackage#getEARepository_File()
	 * @model dataType="eaadapter.datatypes.File"
	 * @generated
	 */
	File getFile();

	/**
	 * Sets the value of the '{@link eaadapter.EARepository#getFile <em>File</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>File</em>' attribute.
	 * @see #getFile()
	 * @generated
	 */
	void setFile(File value);

	/**
	 * Returns the value of the '<em><b>Models</b></em>' containment reference list. The list contents are of type
	 * {@link eaadapter.EAPackage}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Models</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Models</em>' containment reference list.
	 * @see eaadapter.EaadapterPackage#getEARepository_Models()
	 * @model containment="true"
	 * @generated
	 */
	EList<EAPackage> getModels();

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> <div class='userdoc'> This is the method
	 * which initializes the model.<br>
	 * The method basically operates in two sequential steps:<br>
	 * <b>1) </b> Initialize all Packages and Elements<br>
	 * <b>2) </b> Initialize all Connectors<br>
	 * This two step approach is necessary since all classes and packages must be present before the connectors can be
	 * added. </div> <!-- end-model-doc -->
	 * 
	 * @model exceptions="eaadapter.datatypes.Exception"
	 * @generated
	 */
	void load() throws Exception;

	void load(IProgressMonitor monitor) throws Exception;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> <div class='userdoc'> This method returns
	 * an <code>EANamedElement</code> by its global unique identifier <code>GUID</code>. </div> <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	EANamedElement getEANamedElementByGUID(String guid);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> <div class='userdoc'> This method returns
	 * an <code>EAElement</code> by its global unique identifier <code>GUID</code>. </div> <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	EAElement getEAElementByGUID(String guid);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> <div class='userdoc'> This method returns
	 * a list of <code>EANamedElement</code> identified by its id <code>ID</code>.<br>
	 * Note that an element's <code>ID</code> is only unique within its current model. Hence, it is possible that an
	 * <code>ID</code> can appear more than once. </div> <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	EList<EANamedElement> getEANamedElementByID(long id);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> <div class='userdoc'> This method returns
	 * a list of <code>EAElement</code> identified by its id <code>ID</code>.<br>
	 * Note that an element's <code>ID</code> is only unique within its current model. Hence, it is possible that an
	 * <code>ID</code> can appear more than once. </div> <!-- end-model-doc -->
	 * 
	 * @model
	 * @generated
	 */
	EList<EAElement> getEAElementByID(long id);

} // EARepository
