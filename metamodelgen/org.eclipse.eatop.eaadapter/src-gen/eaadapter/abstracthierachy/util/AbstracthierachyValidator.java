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

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;

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
 * <!-- begin-user-doc --> The <b>Validator</b> for the model. <!-- end-user-doc -->
 * 
 * @see eaadapter.abstracthierachy.AbstracthierachyPackage
 * @generated
 */
public class AbstracthierachyValidator extends EObjectValidator {
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
	public static final AbstracthierachyValidator INSTANCE = new AbstracthierachyValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic
	 * {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "eaadapter.abstracthierachy";

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'EA Named
	 * Element'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final int EA_NAMED_ELEMENT__VALIDATE = 1;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 1;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a
	 * derived class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AbstracthierachyValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
		return AbstracthierachyPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
		case AbstracthierachyPackage.EA_ABSTRACT_PACKAGE:
			return validateEAAbstractPackage((EAAbstractPackage) value, diagnostics, context);
		case AbstracthierachyPackage.EA_CLASSIFIER_ID_LONG:
			return validateEAClassifierIDLong((EAClassifierIDLong) value, diagnostics, context);
		case AbstracthierachyPackage.EA_MODIFIABLE_ELEMENT:
			return validateEAModifiableElement((EAModifiableElement) value, diagnostics, context);
		case AbstracthierachyPackage.EA_NAMED_ELEMENT:
			return validateEANamedElement((EANamedElement) value, diagnostics, context);
		case AbstracthierachyPackage.EA_OWNED_ELEMENT:
			return validateEAOwnedElement((EAOwnedElement) value, diagnostics, context);
		case AbstracthierachyPackage.EA_STEREOTYPED_ELEMENT:
			return validateEAStereotypedElement((EAStereotypedElement) value, diagnostics, context);
		case AbstracthierachyPackage.EA_TAGGED_ELEMENT:
			return validateEATaggedElement((EATaggedElement) value, diagnostics, context);
		case AbstracthierachyPackage.EA_TYPED_ELEMENT:
			return validateEATypedElement((EATypedElement) value, diagnostics, context);
		case AbstracthierachyPackage.EA_VERSIOND_ELEMENT:
			return validateEAVersiondElement((EAVersiondElement) value, diagnostics, context);
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEAAbstractPackage(EAAbstractPackage eaAbstractPackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaAbstractPackage, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms(eaAbstractPackage, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(eaAbstractPackage, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(eaAbstractPackage, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired(eaAbstractPackage, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(eaAbstractPackage, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(eaAbstractPackage, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(eaAbstractPackage, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(eaAbstractPackage, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateEANamedElement_validate(eaAbstractPackage, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEAClassifierIDLong(EAClassifierIDLong eaClassifierIDLong, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaClassifierIDLong, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms(eaClassifierIDLong, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(eaClassifierIDLong, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(eaClassifierIDLong, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired(eaClassifierIDLong, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(eaClassifierIDLong, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(eaClassifierIDLong, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(eaClassifierIDLong, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(eaClassifierIDLong, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateEANamedElement_validate(eaClassifierIDLong, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEAModifiableElement(EAModifiableElement eaModifiableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaModifiableElement, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms(eaModifiableElement, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(eaModifiableElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(eaModifiableElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired(eaModifiableElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(eaModifiableElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(eaModifiableElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(eaModifiableElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(eaModifiableElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateEANamedElement_validate(eaModifiableElement, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEANamedElement(EANamedElement eaNamedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaNamedElement, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms(eaNamedElement, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(eaNamedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(eaNamedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired(eaNamedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(eaNamedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(eaNamedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(eaNamedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(eaNamedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateEANamedElement_validate(eaNamedElement, diagnostics, context);
		}
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>EA Named Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEANamedElement_validate(EANamedElement eaNamedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return eaNamedElement.validate(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEAOwnedElement(EAOwnedElement eaOwnedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaOwnedElement, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms(eaOwnedElement, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(eaOwnedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(eaOwnedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired(eaOwnedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(eaOwnedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(eaOwnedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(eaOwnedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(eaOwnedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateEANamedElement_validate(eaOwnedElement, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEAStereotypedElement(EAStereotypedElement eaStereotypedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaStereotypedElement, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms(eaStereotypedElement, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(eaStereotypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(eaStereotypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired(eaStereotypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(eaStereotypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(eaStereotypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(eaStereotypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(eaStereotypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateEANamedElement_validate(eaStereotypedElement, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEATaggedElement(EATaggedElement eaTaggedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaTaggedElement, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms(eaTaggedElement, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(eaTaggedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(eaTaggedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired(eaTaggedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(eaTaggedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(eaTaggedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(eaTaggedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(eaTaggedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateEANamedElement_validate(eaTaggedElement, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEATypedElement(EATypedElement eaTypedElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaTypedElement, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms(eaTypedElement, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(eaTypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(eaTypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired(eaTypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(eaTypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(eaTypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(eaTypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(eaTypedElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateEANamedElement_validate(eaTypedElement, diagnostics, context);
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean validateEAVersiondElement(EAVersiondElement eaVersiondElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaVersiondElement, diagnostics, context)) {
			return false;
		}
		boolean result = validate_EveryMultiplicityConforms(eaVersiondElement, diagnostics, context);
		if (result || diagnostics != null) {
			result &= validate_EveryDataValueConforms(eaVersiondElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryReferenceIsContained(eaVersiondElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryBidirectionalReferenceIsPaired(eaVersiondElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryProxyResolves(eaVersiondElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_UniqueID(eaVersiondElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryKeyUnique(eaVersiondElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validate_EveryMapEntryUnique(eaVersiondElement, diagnostics, context);
		}
		if (result || diagnostics != null) {
			result &= validateEANamedElement_validate(eaVersiondElement, diagnostics, context);
		}
		return result;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} // AbstracthierachyValidator
