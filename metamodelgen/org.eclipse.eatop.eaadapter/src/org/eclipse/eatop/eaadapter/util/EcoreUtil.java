/**
 * <copyright>
 *  
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors: 
 *     itemis - Initial API and implementation
 *  
 * </copyright>
 * 
 */
package org.eclipse.eatop.eaadapter.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

public class EcoreUtil {
	/**
	 * Returns all EClasses in the given ECore resource.
	 * 
	 * @param eResource
	 */
	public static List<EClass> getEAllEClasses(Resource resource) {
		List<EClass> result = new ArrayList<EClass>();
		for (EPackage ePackage : getEAllEPackages(resource)) {
			for (EClassifier eClassifier : ePackage.getEClassifiers()) {
				if (eClassifier instanceof EClass) {
					result.add((EClass) eClassifier);
				}
			}
		}
		return result;
	}

	/**
	 * Returns all EPackages in the given ECore resource.
	 * 
	 * @param resource
	 */
	public static List<EPackage> getEAllEPackages(Resource resource) {
		List<EPackage> result = new ArrayList<EPackage>();
		for (EPackage ePackage : getEAllRootEPackages(resource)) {
			result.add(ePackage);
			result.addAll(getEAllSubpackages(ePackage));
		}
		return result;
	}

	/**
	 * Returns the list of root EPakages in the ECore resource.
	 * 
	 * @param resource
	 * @return
	 */
	public static List<EPackage> getEAllRootEPackages(Resource resource) {
		List<EPackage> result = new ArrayList<EPackage>();
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof EPackage) {
				result.add((EPackage) eObject);
			}
		}
		return result;
	}

	/**
	 * Returns all ESubpackages recursively
	 * 
	 * @param ePackage
	 * @return
	 */
	private static Collection<? extends EPackage> getEAllSubpackages(EPackage rootEPackage) {
		List<EPackage> result = new ArrayList<EPackage>();
		result.addAll(rootEPackage.getESubpackages());
		for (EPackage ePackage : rootEPackage.getESubpackages()) {
			result.addAll(getEAllSubpackages(ePackage));
		}
		return result;
	}

	public static EClass findEClass(String className, Resource ecoreResource) {
		EClass result = null;
		List<EPackage> rootEPackages = getEAllRootEPackages(ecoreResource);
		for (EObject eObject : rootEPackages) {
			result = findEClassInEPackage(className, (EPackage) eObject);
		}
		return result;
	}

	public static EClass findEClassInEPackage(String className, EPackage ePackage) {
		// Search in classifiers
		for (EClassifier classifier : ePackage.getEClassifiers()) {
			if (classifier instanceof EClass) {
				EClass eClazz = (EClass) classifier;
				if (eClazz.getName().equals(className)) {
					return eClazz;
				}
			}
		}

		// Search in sub-packages
		for (EPackage eSubPackage : ePackage.getESubpackages()) {
			EClass eClazz = findEClassInEPackage(className, eSubPackage);
			if (eClazz != null) {
				return eClazz;
			}
		}
		return null;
	}
}
