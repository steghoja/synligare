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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;

public class GenClasses {

	private static Map<String, EClass> fTypeMap = new HashMap<String, EClass>();

	public static List<EReference> getAllNonOppositeReferences(GenClass gClass) {
		List<GenFeature> genFeatures = gClass.getDeclaredFieldGenFeatures();
		List<EReference> result = new ArrayList<EReference>();
		for (GenFeature genFeature : genFeatures) {
			if (genFeature.getEcoreFeature() instanceof EReference) {
				EReference eReference = (EReference) genFeature.getEcoreFeature();
				if (!eReference.isContainment() && !eReference.isTransient()) {
					result.add(eReference);
				}
			}
		}
		return result;
	}

	/**
	 * Does the same thing as {@link GenClass#getEInverseAddGenFeatures()} but adds all bidirectional volatile
	 * {@link GenFeature features} with memory-optimized implementations to the result.
	 * 
	 * @param genClass
	 *            The {@link GenClass class} to be investigated.
	 * @return A list of {@link GenFeature feature}s which are required to be addressed in the
	 *         {@link BasicEObjectImpl#eInverseAdd(org.eclipse.emf.ecore.InternalEObject, int, Class, org.eclipse.emf.common.notify.NotificationChain)}
	 *         method.
	 * @see #hasMemoryOptimizedImplementation(GenFeature)
	 */
	public static List<GenFeature> getEInverseAddGenFeatures(GenClass genClass) {
		List<GenFeature> result = genClass.getEInverseAddGenFeatures();
		for (GenFeature genFeature : genClass.getGenModel().isMinimalReflectiveMethods() ? genClass.getImplementedGenFeatures() : genClass
				.getAllGenFeatures()) {
			if (genFeature.isBidirectional() && genFeature.isVolatile() && GenFeatures.hasMemoryOptimizedImplementation(genFeature)) {
				if (!result.contains(genFeature)) {
					result.add(genFeature);
				}
			}
		}
		return result;
	}

	public static String getFeatureID(GenClass genClass, String featureName) {
		for (GenFeature genFeature : genClass.getAllGenFeatures()) {
			if (featureName.equals(genFeature.getName())) {
				return genClass.getFeatureID(genFeature);
			}
		}
		return "ERROR: Unknown feature ID."; //$NON-NLS-1$
	}

	public static GenFeature getGenFeature(GenClass genClass, String featureName) {
		Assert.isNotNull(featureName);
		// Try to find a GenFeature with the exact name
		for (GenFeature genFeature : genClass.getAllGenFeatures()) {
			if (featureName.equals(genFeature.getName())) {
				return genFeature;
			}
		}
		// Try to find a GenFeature which the name contains the feature name
		for (GenFeature genFeature : genClass.getAllGenFeatures()) {
			if (genFeature.getName() != null && genFeature.getName().contains(featureName)) {
				return genFeature;
			}
		}

		return null;
	}

	public static GenOperation getGenOperation(GenClass genClass, String operationName) {
		Assert.isNotNull(operationName);
		for (GenOperation genOperation : genClass.getAllGenOperations()) {
			if (operationName.equals(genOperation.getName())) {
				return genOperation;
			}
		}
		return null;
	}

	public static boolean isOfType(GenClass genClass, String typeName) {
		EClass type = getType(genClass.getGenModel(), typeName);
		return type != null && getEAllTypes(genClass).contains(type);
	}

	private static List<EClass> getEAllTypes(GenClass genClass) {
		EClass eClass = genClass.getEcoreClass();
		List<EClass> eAllTypes = new ArrayList<EClass>(eClass.getEAllSuperTypes());
		eAllTypes.add(eClass);
		return eAllTypes;
	}

	private static EClass getType(GenModel genModel, String typeName) {
		if (!fTypeMap.containsKey(typeName)) {
			GenClass genType = GenModels.findGenClass(genModel, typeName);
			EClass type = null;
			if (genType != null) {
				type = genType.getEcoreClass();
			}
			fTypeMap.put(typeName, type);
		}
		return fTypeMap.get(typeName);
	}
}
