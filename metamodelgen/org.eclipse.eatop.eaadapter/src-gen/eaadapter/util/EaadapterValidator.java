/**
 * <copyright>
 * Copyright (c) Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php
 * 
 * Contributors:
 * 	Continental AG, 2012 Matthias Nick - Initial API and implementation
 * </copyright>
 * 
 */
package eaadapter.util;

import eaadapter.*;

import eaadapter.abstracthierachy.util.AbstracthierachyValidator;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see eaadapter.EaadapterPackage
 * @generated
 */
public class EaadapterValidator extends EObjectValidator {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final EaadapterValidator INSTANCE = new EaadapterValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "eaadapter";

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'EA Attribute'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int EA_ATTRIBUTE__VALIDATE = 1;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'EA Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int EA_ELEMENT__VALIDATE = 2;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Validate' of 'EA Package'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int EA_PACKAGE__VALIDATE = 3;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 3;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstracthierachyValidator abstracthierachyValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EaadapterValidator() {
		super();
		abstracthierachyValidator = AbstracthierachyValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return EaadapterPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case EaadapterPackage.EA_ATTRIBUTE:
				return validateEAAttribute((EAAttribute)value, diagnostics, context);
			case EaadapterPackage.EA_ATTRIBUTE_TAG:
				return validateEAAttributeTag((EAAttributeTag)value, diagnostics, context);
			case EaadapterPackage.EA_CONNECTOR:
				return validateEAConnector((EAConnector)value, diagnostics, context);
			case EaadapterPackage.EA_CONNECTOR_TAG:
				return validateEAConnectorTag((EAConnectorTag)value, diagnostics, context);
			case EaadapterPackage.EA_ELEMENT:
				return validateEAElement((EAElement)value, diagnostics, context);
			case EaadapterPackage.EA_METHOD:
				return validateEAMethod((EAMethod)value, diagnostics, context);
			case EaadapterPackage.EA_METHOD_TAG:
				return validateEAMethodTag((EAMethodTag)value, diagnostics, context);
			case EaadapterPackage.EA_PACKAGE:
				return validateEAPackage((EAPackage)value, diagnostics, context);
			case EaadapterPackage.EA_PARAMETER:
				return validateEAParameter((EAParameter)value, diagnostics, context);
			case EaadapterPackage.EA_REPOSITORY:
				return validateEARepository((EARepository)value, diagnostics, context);
			case EaadapterPackage.EA_TAGGED_VALUE:
				return validateEATaggedValue((EATaggedValue)value, diagnostics, context);
			case EaadapterPackage.EA_CONNECTOR_END:
				return validateEAConnectorEnd((EAConnectorEnd)value, diagnostics, context);
			case EaadapterPackage.EA_CONNECTOR_CONSTRAINT:
				return validateEAConnectorConstraint((EAConnectorConstraint)value, diagnostics, context);
			case EaadapterPackage.EA_ROLE_TAG:
				return validateEARoleTag((EARoleTag)value, diagnostics, context);
			case EaadapterPackage.EA_CONSTRAINT:
				return validateEAConstraint((EAConstraint)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAAttribute(EAAttribute eaAttribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaAttribute, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaAttribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaAttribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaAttribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaAttribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaAttribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaAttribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaAttribute, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaAttribute, diagnostics, context);
		if (result || diagnostics != null) result &= validateEAAttribute_validate(eaAttribute, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>EA Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAAttribute_validate(EAAttribute eaAttribute, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "validate", getObjectLabel(eaAttribute, context) },
						 new Object[] { eaAttribute },
						 context));
			}
			return false;
		}
		return abstracthierachyValidator.validateEANamedElement_validate(eaAttribute, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAAttributeTag(EAAttributeTag eaAttributeTag, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaAttributeTag, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaAttributeTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaAttributeTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaAttributeTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaAttributeTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaAttributeTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaAttributeTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaAttributeTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaAttributeTag, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaAttributeTag, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAConnector(EAConnector eaConnector, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaConnector, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaConnector, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaConnector, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaConnector, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaConnector, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaConnector, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaConnector, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaConnector, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaConnector, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaConnector, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAConnectorTag(EAConnectorTag eaConnectorTag, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaConnectorTag, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaConnectorTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaConnectorTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaConnectorTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaConnectorTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaConnectorTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaConnectorTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaConnectorTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaConnectorTag, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaConnectorTag, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAElement(EAElement eaElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaElement, diagnostics, context);
		if (result || diagnostics != null) result &= validateEAElement_validate(eaElement, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>EA Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAElement_validate(EAElement eaElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "validate", getObjectLabel(eaElement, context) },
						 new Object[] { eaElement },
						 context));
			}
			return false;
		}
		return abstracthierachyValidator.validateEANamedElement_validate(eaElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAMethod(EAMethod eaMethod, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaMethod, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaMethod, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaMethod, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaMethod, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaMethod, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaMethod, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaMethod, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaMethod, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaMethod, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaMethod, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAMethodTag(EAMethodTag eaMethodTag, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaMethodTag, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaMethodTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaMethodTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaMethodTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaMethodTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaMethodTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaMethodTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaMethodTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaMethodTag, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaMethodTag, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAPackage(EAPackage eaPackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaPackage, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaPackage, diagnostics, context);
		if (result || diagnostics != null) result &= validateEAPackage_validate(eaPackage, diagnostics, context);
		return result;
	}

	/**
	 * Validates the validate constraint of '<em>EA Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAPackage_validate(EAPackage eaPackage, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "validate", getObjectLabel(eaPackage, context) },
						 new Object[] { eaPackage },
						 context));
			}
			return false;
		}
		return abstracthierachyValidator.validateEANamedElement_validate(eaPackage, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAParameter(EAParameter eaParameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaParameter, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaParameter, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaParameter, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaParameter, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEARepository(EARepository eaRepository, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(eaRepository, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEATaggedValue(EATaggedValue eaTaggedValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaTaggedValue, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaTaggedValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaTaggedValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaTaggedValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaTaggedValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaTaggedValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaTaggedValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaTaggedValue, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaTaggedValue, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaTaggedValue, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAConnectorEnd(EAConnectorEnd eaConnectorEnd, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaConnectorEnd, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaConnectorEnd, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaConnectorEnd, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaConnectorEnd, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaConnectorEnd, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaConnectorEnd, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaConnectorEnd, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaConnectorEnd, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaConnectorEnd, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaConnectorEnd, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAConnectorConstraint(EAConnectorConstraint eaConnectorConstraint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaConnectorConstraint, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaConnectorConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaConnectorConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaConnectorConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaConnectorConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaConnectorConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaConnectorConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaConnectorConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaConnectorConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaConnectorConstraint, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEARoleTag(EARoleTag eaRoleTag, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaRoleTag, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaRoleTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaRoleTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaRoleTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaRoleTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaRoleTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaRoleTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaRoleTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaRoleTag, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaRoleTag, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEAConstraint(EAConstraint eaConstraint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(eaConstraint, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(eaConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(eaConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(eaConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(eaConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(eaConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(eaConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(eaConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(eaConstraint, diagnostics, context);
		if (result || diagnostics != null) result &= abstracthierachyValidator.validateEANamedElement_validate(eaConstraint, diagnostics, context);
		return result;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //EaadapterValidator
