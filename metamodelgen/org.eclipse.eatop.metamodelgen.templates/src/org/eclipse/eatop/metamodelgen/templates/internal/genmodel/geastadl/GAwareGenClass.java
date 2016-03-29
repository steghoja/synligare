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
package org.eclipse.eatop.metamodelgen.templates.internal.genmodel.geastadl;

import java.util.Locale;

import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.impl.GenAnnotationImpl;
import org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassImpl;

@SuppressWarnings("nls")
public class GAwareGenClass extends GenClassImpl implements GenClass {

	// ####################### GAUTOSAR ########################

	private static final String DEPRECATION_ANNO = "deprecated";
	private static final String JAVADOC_DETAIL = "javadoc";

	public boolean isDeprecated() {
		return getDeprecationAnnotation().getClass() != NullEAnnotation.class;
	}

	public boolean hasDeprecationJavadoc() {
		return getDeprecationJavadoc() != null;
	}

	public String getDeprecationJavadoc() {
		String javadocDetails = getDeprecationAnnotation().getDetails().get(JAVADOC_DETAIL);
		if (javadocDetails == null) {
			return null;
		}
		return getDeprecationAnnotation().getDetails().get(JAVADOC_DETAIL).replaceAll("\n", "\n * ");

	}

	private GenAnnotation getDeprecationAnnotation() {
		return getEAnnotation(DEPRECATION_ANNO);
	}

	private GenAnnotation getEAnnotation(String source) {
		GenAnnotation annotation = getGenAnnotation(source);
		if (annotation == null) {
			annotation = new NullEAnnotation();
			annotation.setSource(source);
		}
		return annotation;
	}

	private static class NullEAnnotation extends GenAnnotationImpl {

	}

	@Override
	public GenFeature getLabelFeature() {
		GenFeature labelFeature = getLabelFeatureGen();
		if (labelFeature != null) {
			return labelFeature;
		}

		Locale locale = getGenModel().getLocale();
		// FB TBD can we come up with a better algorithm for choosing the
		// default label feature?
		for (GenFeature feature : getLabelFeatureCandidates()) {
			if (!feature.isListType()) {
				String featureName = feature.getName();
				if (featureName != null) {
					if (featureName.equalsIgnoreCase("name")) {
						if (labelFeature == null || !labelFeature.getName().equalsIgnoreCase("shortname")) {
							labelFeature = feature;
						}
					} else if (featureName.equalsIgnoreCase("shortname")) {
						labelFeature = feature;
					} else if (featureName.equalsIgnoreCase("id")) {
						if (labelFeature == null || !labelFeature.getName().toLowerCase(locale).endsWith("name")) {
							labelFeature = feature;
						}
					} else if (featureName.toLowerCase(locale).endsWith("name")) {
						if (labelFeature == null || !labelFeature.getName().toLowerCase(locale).endsWith("name")
								&& !labelFeature.getName().equalsIgnoreCase("id")) {
							labelFeature = feature;
						}
					} else if (featureName.toLowerCase(locale).indexOf("name") != -1) {
						if (labelFeature == null || labelFeature.getName().toLowerCase(locale).indexOf("name") == -1
								&& !labelFeature.getName().equalsIgnoreCase("id")) {
							labelFeature = feature;
						}
					} else if (labelFeature == null) {
						labelFeature = feature;
					}
				}
			}
		}

		return labelFeature;
	}

	// ####################### GAUTOSAR END ####################
}
