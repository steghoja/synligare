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
package eaadapter.abstracthierachy.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import eaadapter.abstracthierachy.AbstracthierachyPackage;
import eaadapter.abstracthierachy.EAAbstractPackage;
import eaadapter.abstracthierachy.EAClassifierIDLong;
import eaadapter.abstracthierachy.EAModifiableElement;
import eaadapter.abstracthierachy.EANamedElement;
import eaadapter.abstracthierachy.EAOwnedElement;
import eaadapter.abstracthierachy.EAStereotypedElement;
import eaadapter.abstracthierachy.EATaggedElement;
import eaadapter.abstracthierachy.EATypedElement;
import eaadapter.abstracthierachy.EAVersiondElement;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 * 
 * @see eaadapter.abstracthierachy.AbstracthierachyPackage
 * @generated
 */
public class AbstracthierachySwitch<T> extends Switch<T> {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static AbstracthierachyPackage modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AbstracthierachySwitch() {
		if (modelPackage == null) {
			modelPackage = AbstracthierachyPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
	 * result. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case AbstracthierachyPackage.EA_ABSTRACT_PACKAGE: {
			EAAbstractPackage eaAbstractPackage = (EAAbstractPackage) theEObject;
			T result = caseEAAbstractPackage(eaAbstractPackage);
			if (result == null) {
				result = caseEAVersiondElement(eaAbstractPackage);
			}
			if (result == null) {
				result = caseEANamedElement(eaAbstractPackage);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG: {
			EAClassifierIDLong eaClassifierIDLong = (EAClassifierIDLong) theEObject;
			T result = caseEAClassifierIDLong(eaClassifierIDLong);
			if (result == null) {
				result = caseEATypedElement(eaClassifierIDLong);
			}
			if (result == null) {
				result = caseEAStereotypedElement(eaClassifierIDLong);
			}
			if (result == null) {
				result = caseEANamedElement(eaClassifierIDLong);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT: {
			EAModifiableElement eaModifiableElement = (EAModifiableElement) theEObject;
			T result = caseEAModifiableElement(eaModifiableElement);
			if (result == null) {
				result = caseEATypedElement(eaModifiableElement);
			}
			if (result == null) {
				result = caseEAStereotypedElement(eaModifiableElement);
			}
			if (result == null) {
				result = caseEANamedElement(eaModifiableElement);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case AbstracthierachyPackage.EA_NAMED_ELEMENT: {
			EANamedElement eaNamedElement = (EANamedElement) theEObject;
			T result = caseEANamedElement(eaNamedElement);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case AbstracthierachyPackage.EA_OWNED_ELEMENT: {
			EAOwnedElement eaOwnedElement = (EAOwnedElement) theEObject;
			T result = caseEAOwnedElement(eaOwnedElement);
			if (result == null) {
				result = caseEAVersiondElement(eaOwnedElement);
			}
			if (result == null) {
				result = caseEANamedElement(eaOwnedElement);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case AbstracthierachyPackage.EA_STEREOTYPED_ELEMENT: {
			EAStereotypedElement eaStereotypedElement = (EAStereotypedElement) theEObject;
			T result = caseEAStereotypedElement(eaStereotypedElement);
			if (result == null) {
				result = caseEANamedElement(eaStereotypedElement);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case AbstracthierachyPackage.EA_TAGGED_ELEMENT: {
			EATaggedElement eaTaggedElement = (EATaggedElement) theEObject;
			T result = caseEATaggedElement(eaTaggedElement);
			if (result == null) {
				result = caseEANamedElement(eaTaggedElement);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case AbstracthierachyPackage.EA_TYPED_ELEMENT: {
			EATypedElement eaTypedElement = (EATypedElement) theEObject;
			T result = caseEATypedElement(eaTypedElement);
			if (result == null) {
				result = caseEAStereotypedElement(eaTypedElement);
			}
			if (result == null) {
				result = caseEANamedElement(eaTypedElement);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case AbstracthierachyPackage.EA_VERSIOND_ELEMENT: {
			EAVersiondElement eaVersiondElement = (EAVersiondElement) theEObject;
			T result = caseEAVersiondElement(eaVersiondElement);
			if (result == null) {
				result = caseEANamedElement(eaVersiondElement);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Abstract Package</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Abstract Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAAbstractPackage(EAAbstractPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Classifier ID Long</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Classifier ID Long</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAClassifierIDLong(EAClassifierIDLong object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Modifiable Element</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Modifiable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAModifiableElement(EAModifiableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Named Element</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEANamedElement(EANamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Owned Element</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Owned Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAOwnedElement(EAOwnedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Stereotyped Element</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Stereotyped Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAStereotypedElement(EAStereotypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Tagged Element</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Tagged Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEATaggedElement(EATaggedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Typed Element</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEATypedElement(EATypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Versiond Element</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Versiond Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAVersiondElement(EAVersiondElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // AbstracthierachySwitch
