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
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.metamodelgen.templates.internal.util;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

public class EClasses {
	public static boolean isAbstractDestClass(EClass clz) {
		EAnnotation eAnn = clz.getEAnnotation(IEASTADLTemplateConstants.ANNOTATION_TAGGED_VALUES);
		if (eAnn != null) {
			String value = eAnn.getDetails().get("eatop.abstract"); //$NON-NLS-1$
			return Boolean.parseBoolean(value);
		} else {
			return false;
		}
	}

	/**
	 * Tests if given EClass is assignment compatible with type with specified name, i.e. if it is equal to or a sub
	 * type of type with specified name.
	 * 
	 * @param eClass
	 *            The EClass to be tested.
	 * @param typeName
	 *            The name of the type.
	 * @return true if given EClass is equal or a subtype of type with specified name, false otherwise.
	 */
	public static boolean isAssignableFrom(EClass eClass, String typeName) {

		// Test if given EClass directly matches type with specified name
		if (eClass.getName().equals(typeName)) {
			return true;
		}
		// Test if one of the super types of given EClass match
		for (EClass superType : eClass.getESuperTypes()) {
			if (isAssignableFrom(superType, typeName)) {
				return true;
			}
		}

		return false;
	}

	public static String getQualifiedPackageName(EClass currentClass) {
		EPackage aPackage = currentClass.getEPackage();
		return getQualifiedPackageName(aPackage);
	}

	private static String getQualifiedPackageName(EPackage aPackage) {
		StringBuffer buffer = new StringBuffer();
		while (aPackage != null) {
			buffer.insert(0, aPackage.getName());
			buffer.insert(0, '.');
			aPackage = aPackage.getESuperPackage();
		}
		String fullyQualifiedPackageName = buffer.toString().substring(1);
		return fullyQualifiedPackageName;
	}

	public static String getFactoryQName(EClass currentClass, String fullyQualifiedPackageName) {
		String name = currentClass.getEPackage().getName();

		return fullyQualifiedPackageName + "." + name.substring(0, 1).toUpperCase() + name.substring(1) + "Factory"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static String getVarName(EClass currentClass) {
		String name = currentClass.getName();
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}
}
