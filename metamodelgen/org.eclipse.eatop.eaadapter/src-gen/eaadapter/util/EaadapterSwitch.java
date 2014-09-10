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
package eaadapter.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import eaadapter.EAAttribute;
import eaadapter.EAAttributeTag;
import eaadapter.EAConnector;
import eaadapter.EAConnectorConstraint;
import eaadapter.EAConnectorEnd;
import eaadapter.EAConnectorTag;
import eaadapter.EAConstraint;
import eaadapter.EAElement;
import eaadapter.EAMethod;
import eaadapter.EAMethodTag;
import eaadapter.EAPackage;
import eaadapter.EAParameter;
import eaadapter.EARepository;
import eaadapter.EARoleTag;
import eaadapter.EATaggedValue;
import eaadapter.EaadapterPackage;
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
 * @see eaadapter.EaadapterPackage
 * @generated
 */
public class EaadapterSwitch<T> extends Switch<T> {
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
	protected static EaadapterPackage modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EaadapterSwitch() {
		if (modelPackage == null) {
			modelPackage = EaadapterPackage.eINSTANCE;
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
		case EaadapterPackage.EA_ATTRIBUTE: {
			EAAttribute eaAttribute = (EAAttribute) theEObject;
			T result = caseEAAttribute(eaAttribute);
			if (result == null) {
				result = caseEAModifiableElement(eaAttribute);
			}
			if (result == null) {
				result = caseEAClassifierIDLong(eaAttribute);
			}
			if (result == null) {
				result = caseEATypedElement(eaAttribute);
			}
			if (result == null) {
				result = caseEAStereotypedElement(eaAttribute);
			}
			if (result == null) {
				result = caseEANamedElement(eaAttribute);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_ATTRIBUTE_TAG: {
			EAAttributeTag eaAttributeTag = (EAAttributeTag) theEObject;
			T result = caseEAAttributeTag(eaAttributeTag);
			if (result == null) {
				result = caseEATaggedElement(eaAttributeTag);
			}
			if (result == null) {
				result = caseEANamedElement(eaAttributeTag);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_CONNECTOR: {
			EAConnector eaConnector = (EAConnector) theEObject;
			T result = caseEAConnector(eaConnector);
			if (result == null) {
				result = caseEATypedElement(eaConnector);
			}
			if (result == null) {
				result = caseEAStereotypedElement(eaConnector);
			}
			if (result == null) {
				result = caseEANamedElement(eaConnector);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_CONNECTOR_TAG: {
			EAConnectorTag eaConnectorTag = (EAConnectorTag) theEObject;
			T result = caseEAConnectorTag(eaConnectorTag);
			if (result == null) {
				result = caseEATaggedElement(eaConnectorTag);
			}
			if (result == null) {
				result = caseEANamedElement(eaConnectorTag);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_ELEMENT: {
			EAElement eaElement = (EAElement) theEObject;
			T result = caseEAElement(eaElement);
			if (result == null) {
				result = caseEAClassifierIDLong(eaElement);
			}
			if (result == null) {
				result = caseEAOwnedElement(eaElement);
			}
			if (result == null) {
				result = caseEATypedElement(eaElement);
			}
			if (result == null) {
				result = caseEAVersiondElement(eaElement);
			}
			if (result == null) {
				result = caseEAStereotypedElement(eaElement);
			}
			if (result == null) {
				result = caseEANamedElement(eaElement);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_METHOD: {
			EAMethod eaMethod = (EAMethod) theEObject;
			T result = caseEAMethod(eaMethod);
			if (result == null) {
				result = caseEAModifiableElement(eaMethod);
			}
			if (result == null) {
				result = caseEAClassifierIDLong(eaMethod);
			}
			if (result == null) {
				result = caseEATypedElement(eaMethod);
			}
			if (result == null) {
				result = caseEAStereotypedElement(eaMethod);
			}
			if (result == null) {
				result = caseEANamedElement(eaMethod);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_METHOD_TAG: {
			EAMethodTag eaMethodTag = (EAMethodTag) theEObject;
			T result = caseEAMethodTag(eaMethodTag);
			if (result == null) {
				result = caseEATaggedElement(eaMethodTag);
			}
			if (result == null) {
				result = caseEANamedElement(eaMethodTag);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_PACKAGE: {
			EAPackage eaPackage = (EAPackage) theEObject;
			T result = caseEAPackage(eaPackage);
			if (result == null) {
				result = caseEAAbstractPackage(eaPackage);
			}
			if (result == null) {
				result = caseEAVersiondElement(eaPackage);
			}
			if (result == null) {
				result = caseEANamedElement(eaPackage);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_PARAMETER: {
			EAParameter eaParameter = (EAParameter) theEObject;
			T result = caseEAParameter(eaParameter);
			if (result == null) {
				result = caseEAClassifierIDLong(eaParameter);
			}
			if (result == null) {
				result = caseEATypedElement(eaParameter);
			}
			if (result == null) {
				result = caseEAStereotypedElement(eaParameter);
			}
			if (result == null) {
				result = caseEANamedElement(eaParameter);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_REPOSITORY: {
			EARepository eaRepository = (EARepository) theEObject;
			T result = caseEARepository(eaRepository);
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_TAGGED_VALUE: {
			EATaggedValue eaTaggedValue = (EATaggedValue) theEObject;
			T result = caseEATaggedValue(eaTaggedValue);
			if (result == null) {
				result = caseEATaggedElement(eaTaggedValue);
			}
			if (result == null) {
				result = caseEANamedElement(eaTaggedValue);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_CONNECTOR_END: {
			EAConnectorEnd eaConnectorEnd = (EAConnectorEnd) theEObject;
			T result = caseEAConnectorEnd(eaConnectorEnd);
			if (result == null) {
				result = caseEANamedElement(eaConnectorEnd);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_CONNECTOR_CONSTRAINT: {
			EAConnectorConstraint eaConnectorConstraint = (EAConnectorConstraint) theEObject;
			T result = caseEAConnectorConstraint(eaConnectorConstraint);
			if (result == null) {
				result = caseEANamedElement(eaConnectorConstraint);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_ROLE_TAG: {
			EARoleTag eaRoleTag = (EARoleTag) theEObject;
			T result = caseEARoleTag(eaRoleTag);
			if (result == null) {
				result = caseEANamedElement(eaRoleTag);
			}
			if (result == null) {
				result = defaultCase(theEObject);
			}
			return result;
		}
		case EaadapterPackage.EA_CONSTRAINT: {
			EAConstraint eaConstraint = (EAConstraint) theEObject;
			T result = caseEAConstraint(eaConstraint);
			if (result == null) {
				result = caseEANamedElement(eaConstraint);
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
	 * Returns the result of interpreting the object as an instance of '<em>EA Attribute</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAAttribute(EAAttribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Attribute Tag</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Attribute Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAAttributeTag(EAAttributeTag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Connector</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAConnector(EAConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Connector Tag</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Connector Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAConnectorTag(EAConnectorTag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Element</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAElement(EAElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Method</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAMethod(EAMethod object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Method Tag</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Method Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAMethodTag(EAMethodTag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Package</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAPackage(EAPackage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Parameter</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAParameter(EAParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Repository</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEARepository(EARepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Tagged Value</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Tagged Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEATaggedValue(EATaggedValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Connector End</em>'. <!-- begin-user-doc
	 * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Connector End</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAConnectorEnd(EAConnectorEnd object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Connector Constraint</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Connector Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAConnectorConstraint(EAConnectorConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Role Tag</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Role Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEARoleTag(EARoleTag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EA Constraint</em>'. <!-- begin-user-doc -->
	 * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EA Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEAConstraint(EAConstraint object) {
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

} // EaadapterSwitch
