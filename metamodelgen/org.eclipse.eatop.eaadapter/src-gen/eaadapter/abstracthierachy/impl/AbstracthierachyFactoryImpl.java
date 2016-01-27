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
package eaadapter.abstracthierachy.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import eaadapter.abstracthierachy.AbstracthierachyFactory;
import eaadapter.abstracthierachy.AbstracthierachyPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class AbstracthierachyFactoryImpl extends EFactoryImpl implements AbstracthierachyFactory {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String copyright = "<copyright>\r\nCopyright (c) Continental AG and others.\r\nAll rights reserved. This program and the accompanying materials are made navailable under the terms of the Eclipse Public License \r\nwhich accompanies this distribution, and is navailable at http://www.eclipse.org/org/documents/epl-v10.php\r\n\r\nContributors:\r\n\tContinental AG, 2012 Matthias Nick - Initial API and implementation\r\n</copyright>\r\n";

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static AbstracthierachyFactory init() {
		try {
			AbstracthierachyFactory theAbstracthierachyFactory = (AbstracthierachyFactory) EPackage.Registry.INSTANCE
					.getEFactory("http://eaadapter.abstracthierachy");
			if (theAbstracthierachyFactory != null) {
				return theAbstracthierachyFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AbstracthierachyFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AbstracthierachyFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AbstracthierachyPackage getAbstracthierachyPackage() {
		return (AbstracthierachyPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AbstracthierachyPackage getPackage() {
		return AbstracthierachyPackage.eINSTANCE;
	}

} // AbstracthierachyFactoryImpl
