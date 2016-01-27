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
package org.eclipse.eatop.metamodelgen.postprocessings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class AnalysisPersistencePattern extends PostProcessingTemplate {

	private Set<String> patternCaseSet = new HashSet<String>();
	private Map<String, Integer> patternCount = new HashMap<String, Integer>();

	public AnalysisPersistencePattern() {
	}

	/**
	 * Configures tagged values of a package.
	 * 
	 * @param ePackage
	 * @return ePackage
	 */
	private void analysis(EPackage ePackage) {

		for (EClassifier eClassifier : ePackage.getEClassifiers()) {
			if (eClassifier instanceof EClass) {
				analysis((EClass) eClassifier);
			}
		}

		for (EPackage epkg : ePackage.getESubpackages()) {
			analysis(epkg);
		}

	}

	/**
	 * Analysis patterns of EClass
	 * 
	 * @param eClass
	 * @return eClass
	 */
	private void analysis(EClass eClass) {

		for (EStructuralFeature feature : eClass.getEStructuralFeatures()) {
			// add pattern case
			String patternCase = getPatternCase(feature);
			patternCaseSet.add(patternCase);

			// add pattern map
			if (feature instanceof EReference) {
				String containment = getReferenceContainment((EReference) feature);
				String upperBound = getReferenceUpperBound((EReference) feature);
				String result = containment + " , " + upperBound + " , " + patternCase; //$NON-NLS-1$ //$NON-NLS-2$

				if (patternCount.containsKey(result)) {
					Integer count = patternCount.get(result);
					patternCount.put(result, count + 1);
				} else {
					patternCount.put(result, 1);
				}

			} else if (feature instanceof EAttribute) {
				String upperBound = getAttributeUpperBound((EAttribute) feature);
				String result = upperBound + " , " + patternCase; //$NON-NLS-1$
				if (patternCount.containsKey(result)) {
					Integer count = patternCount.get(result);
					patternCount.put(result, count + 1);
				} else {
					patternCount.put(result, 1);
				}
			}
		}
	}

	@Override
	public void execute() {
		analysis(model);

		for (String pattern : patternCount.keySet()) {
			System.out.print("\n Pattern (boolean) = " + pattern + " , count = " + patternCount.get(pattern)); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	private String getPatternCase(EStructuralFeature eStructuralFeature) {
		String roleWrapperElement = EcoreUtil.getAnnotation(eStructuralFeature, IConstants.XML_PERSISTENCE_MAPPING_ANNOTATION_SOURCE_URI,
				IConstants.FEATURE_WRAPPER_ELEMENT);
		String roleElement = EcoreUtil.getAnnotation(eStructuralFeature, IConstants.XML_PERSISTENCE_MAPPING_ANNOTATION_SOURCE_URI,
				IConstants.FEATURE_ELEMENT);
		String typeWrapperElement = EcoreUtil.getAnnotation(eStructuralFeature, IConstants.XML_PERSISTENCE_MAPPING_ANNOTATION_SOURCE_URI,
				IConstants.CLASSIFIER_WRAPPER_ELEMENT);
		String typeElement = EcoreUtil.getAnnotation(eStructuralFeature, IConstants.XML_PERSISTENCE_MAPPING_ANNOTATION_SOURCE_URI,
				IConstants.CLASSIFIER_ELEMENT);

		String patternCaseBoolean = "Not allowed"; //$NON-NLS-1$

		if (roleWrapperElement.equals("true")) {// 1xxx //$NON-NLS-1$
			if (roleElement.equals("true")) {// 11xx //$NON-NLS-1$
				if (typeWrapperElement.equals("true")) {// 111x //$NON-NLS-1$
					if (typeElement.equals("true")) {// 1111 //$NON-NLS-1$
						patternCaseBoolean = "1111"; //$NON-NLS-1$
					} else {// 1110
						patternCaseBoolean = "1110 (Not allowed)"; //$NON-NLS-1$
					}
				} else {// 110x
					if (typeElement.equals("true")) {// 1101 //$NON-NLS-1$
						patternCaseBoolean = "1101"; //$NON-NLS-1$
					} else {// 1100
						patternCaseBoolean = "1100"; //$NON-NLS-1$
					}
				}
			} else {// 10xx
				if (typeWrapperElement.equals("true")) {// 101x //$NON-NLS-1$
					if (typeElement.equals("true")) {// 1011 //$NON-NLS-1$
						patternCaseBoolean = "1011"; //$NON-NLS-1$
					} else {// 1010
						patternCaseBoolean = "1010 (Not allowed)"; //$NON-NLS-1$
					}
				} else {// 100x
					if (typeElement.equals("true")) {// 1001 //$NON-NLS-1$
						patternCaseBoolean = "1001"; //$NON-NLS-1$
					} else {// 1000
						patternCaseBoolean = "1000 (Not allowed)"; //$NON-NLS-1$
					}
				}
			}
		} else {// 0xxx
			if (roleElement.equals("true")) {// 01xx //$NON-NLS-1$
				if (typeWrapperElement.equals("true")) {// 011x //$NON-NLS-1$
					if (typeElement.equals("true")) {// 0111 //$NON-NLS-1$
						patternCaseBoolean = "0111"; //$NON-NLS-1$
					} else {// 0110
						patternCaseBoolean = "0110 (Not allowed)"; //$NON-NLS-1$
					}
				} else {// 010x
					if (typeElement.equals("true")) {// 0101 //$NON-NLS-1$
						patternCaseBoolean = "0101"; //$NON-NLS-1$
					} else {// 0100
						patternCaseBoolean = "0100"; //$NON-NLS-1$
					}
				}
			} else {// 00xx
				if (typeWrapperElement.equals("true")) {// 001x //$NON-NLS-1$
					if (typeElement.equals("true")) {// 0011 //$NON-NLS-1$
						patternCaseBoolean = "0011"; //$NON-NLS-1$
					} else {// 0010
						patternCaseBoolean = "0010 (Not allowed)"; //$NON-NLS-1$
					}
				} else {// 000x
					if (typeElement.equals("true")) {// 0001 //$NON-NLS-1$
						patternCaseBoolean = "0001"; //$NON-NLS-1$
					} else {// 0000
						patternCaseBoolean = "0000"; //$NON-NLS-1$
					}
				}
			}
		}

		return patternCaseBoolean;
	}

	private String getReferenceContainment(EReference feature) {
		Boolean isContainment = feature.isContainment();
		return "EReference containment=" + isContainment; //$NON-NLS-1$
	}

	private String getReferenceUpperBound(EReference feature) {
		String result;

		int upperBound = feature.getUpperBound();
		if (upperBound == 1) {
			result = "upperBound=1"; //$NON-NLS-1$
		} else {
			result = "upperBound>1"; //$NON-NLS-1$
		}
		return result;
	}

	private String getAttributeUpperBound(EAttribute feature) {
		String result;
		EAnnotation annotation = feature.getEAnnotation(IConstants.EXTENDED_METADATA_NSURI);
		String kind = annotation.getDetails().get(IConstants.KIND);
		int upperBound = feature.getUpperBound();
		if (upperBound == 1) {
			result = "EAttribute , " + kind + " , upperBound=1"; //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			result = "EAttribute , " + kind + " , upperBound>1"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return result;
	}

}
