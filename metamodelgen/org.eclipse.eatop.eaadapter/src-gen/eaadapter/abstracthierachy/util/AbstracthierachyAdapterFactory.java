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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 * 
 * @see eaadapter.abstracthierachy.AbstracthierachyPackage
 * @generated
 */
public class AbstracthierachyAdapterFactory extends AdapterFactoryImpl {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static AbstracthierachyPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AbstracthierachyAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = AbstracthierachyPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
	 * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
	 * the model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AbstracthierachySwitch<Adapter> modelSwitch = new AbstracthierachySwitch<Adapter>() {
		@Override
		public Adapter caseEAAbstractPackage(EAAbstractPackage object) {
			return createEAAbstractPackageAdapter();
		}

		@Override
		public Adapter caseEAClassifierIDLong(EAClassifierIDLong object) {
			return createEAClassifierIDLongAdapter();
		}

		@Override
		public Adapter caseEAModifiableElement(EAModifiableElement object) {
			return createEAModifiableElementAdapter();
		}

		@Override
		public Adapter caseEANamedElement(EANamedElement object) {
			return createEANamedElementAdapter();
		}

		@Override
		public Adapter caseEAOwnedElement(EAOwnedElement object) {
			return createEAOwnedElementAdapter();
		}

		@Override
		public Adapter caseEAStereotypedElement(EAStereotypedElement object) {
			return createEAStereotypedElementAdapter();
		}

		@Override
		public Adapter caseEATaggedElement(EATaggedElement object) {
			return createEATaggedElementAdapter();
		}

		@Override
		public Adapter caseEATypedElement(EATypedElement object) {
			return createEATypedElementAdapter();
		}

		@Override
		public Adapter caseEAVersiondElement(EAVersiondElement object) {
			return createEAVersiondElementAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link eaadapter.abstracthierachy.EAAbstractPackage
	 * <em>EA Abstract Package</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eaadapter.abstracthierachy.EAAbstractPackage
	 * @generated
	 */
	public Adapter createEAAbstractPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eaadapter.abstracthierachy.EAClassifierIDLong
	 * <em>EA Classifier ID Long</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eaadapter.abstracthierachy.EAClassifierIDLong
	 * @generated
	 */
	public Adapter createEAClassifierIDLongAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eaadapter.abstracthierachy.EAModifiableElement
	 * <em>EA Modifiable Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eaadapter.abstracthierachy.EAModifiableElement
	 * @generated
	 */
	public Adapter createEAModifiableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eaadapter.abstracthierachy.EANamedElement
	 * <em>EA Named Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eaadapter.abstracthierachy.EANamedElement
	 * @generated
	 */
	public Adapter createEANamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eaadapter.abstracthierachy.EAOwnedElement
	 * <em>EA Owned Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eaadapter.abstracthierachy.EAOwnedElement
	 * @generated
	 */
	public Adapter createEAOwnedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eaadapter.abstracthierachy.EAStereotypedElement
	 * <em>EA Stereotyped Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eaadapter.abstracthierachy.EAStereotypedElement
	 * @generated
	 */
	public Adapter createEAStereotypedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eaadapter.abstracthierachy.EATaggedElement
	 * <em>EA Tagged Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eaadapter.abstracthierachy.EATaggedElement
	 * @generated
	 */
	public Adapter createEATaggedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eaadapter.abstracthierachy.EATypedElement
	 * <em>EA Typed Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eaadapter.abstracthierachy.EATypedElement
	 * @generated
	 */
	public Adapter createEATypedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link eaadapter.abstracthierachy.EAVersiondElement
	 * <em>EA Versiond Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see eaadapter.abstracthierachy.EAVersiondElement
	 * @generated
	 */
	public Adapter createEAVersiondElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // AbstracthierachyAdapterFactory
