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
package eaadapter.impl;

import eaadapter.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EaadapterFactoryImpl extends EFactoryImpl implements EaadapterFactory {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EaadapterFactory init() {
		try {
			EaadapterFactory theEaadapterFactory = (EaadapterFactory)EPackage.Registry.INSTANCE.getEFactory("http://eaadapter"); 
			if (theEaadapterFactory != null) {
				return theEaadapterFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EaadapterFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EaadapterFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case EaadapterPackage.EA_ATTRIBUTE: return createEAAttribute();
			case EaadapterPackage.EA_ATTRIBUTE_TAG: return createEAAttributeTag();
			case EaadapterPackage.EA_CONNECTOR: return createEAConnector();
			case EaadapterPackage.EA_CONNECTOR_TAG: return createEAConnectorTag();
			case EaadapterPackage.EA_ELEMENT: return createEAElement();
			case EaadapterPackage.EA_METHOD: return createEAMethod();
			case EaadapterPackage.EA_METHOD_TAG: return createEAMethodTag();
			case EaadapterPackage.EA_PACKAGE: return createEAPackage();
			case EaadapterPackage.EA_PARAMETER: return createEAParameter();
			case EaadapterPackage.EA_REPOSITORY: return createEARepository();
			case EaadapterPackage.EA_TAGGED_VALUE: return createEATaggedValue();
			case EaadapterPackage.EA_CONNECTOR_END: return createEAConnectorEnd();
			case EaadapterPackage.EA_CONNECTOR_CONSTRAINT: return createEAConnectorConstraint();
			case EaadapterPackage.EA_ROLE_TAG: return createEARoleTag();
			case EaadapterPackage.EA_CONSTRAINT: return createEAConstraint();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAAttribute createEAAttribute() {
		EAAttributeImpl eaAttribute = new EAAttributeImpl();
		return eaAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAAttributeTag createEAAttributeTag() {
		EAAttributeTagImpl eaAttributeTag = new EAAttributeTagImpl();
		return eaAttributeTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAConnector createEAConnector() {
		EAConnectorImpl eaConnector = new EAConnectorImpl();
		return eaConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAConnectorTag createEAConnectorTag() {
		EAConnectorTagImpl eaConnectorTag = new EAConnectorTagImpl();
		return eaConnectorTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAElement createEAElement() {
		EAElementImpl eaElement = new EAElementImpl();
		return eaElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAMethod createEAMethod() {
		EAMethodImpl eaMethod = new EAMethodImpl();
		return eaMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAMethodTag createEAMethodTag() {
		EAMethodTagImpl eaMethodTag = new EAMethodTagImpl();
		return eaMethodTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAPackage createEAPackage() {
		EAPackageImpl eaPackage = new EAPackageImpl();
		return eaPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAParameter createEAParameter() {
		EAParameterImpl eaParameter = new EAParameterImpl();
		return eaParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EARepository createEARepository() {
		EARepositoryImpl eaRepository = new EARepositoryImpl();
		return eaRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EATaggedValue createEATaggedValue() {
		EATaggedValueImpl eaTaggedValue = new EATaggedValueImpl();
		return eaTaggedValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAConnectorEnd createEAConnectorEnd() {
		EAConnectorEndImpl eaConnectorEnd = new EAConnectorEndImpl();
		return eaConnectorEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAConnectorConstraint createEAConnectorConstraint() {
		EAConnectorConstraintImpl eaConnectorConstraint = new EAConnectorConstraintImpl();
		return eaConnectorConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EARoleTag createEARoleTag() {
		EARoleTagImpl eaRoleTag = new EARoleTagImpl();
		return eaRoleTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAConstraint createEAConstraint() {
		EAConstraintImpl eaConstraint = new EAConstraintImpl();
		return eaConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EaadapterPackage getEaadapterPackage() {
		return (EaadapterPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EaadapterPackage getPackage() {
		return EaadapterPackage.eINSTANCE;
	}

} //EaadapterFactoryImpl
