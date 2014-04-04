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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.common.util.EList;

public class TargetFeaturePath {

	private List<GAwareGenFeature> fFeatures = new ArrayList<GAwareGenFeature>();

	public void append(GAwareGenFeature feature) {
		fFeatures.add(feature);
	}

	public boolean isEastadlType() {
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return lastFeature.isEastadlType();
	}

	public boolean isEastadlEnumType() {
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return lastFeature.isEastadlEnumType() && lastFeature.isEnumType();
	}

	public boolean isEastadlElementType() {
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return lastFeature.isEastadlElementType() && !lastFeature.isEnumType();
	}

	public GAwareGenFeature getLastFeature() {
		if (fFeatures.isEmpty()) {
			return null;
		}
		return fFeatures.get(fFeatures.size() - 1);
	}

	public boolean isListType() {
		for (GAwareGenFeature feature : fFeatures) {
			if (feature.isListType()) {
				return true;
			}
		}
		return false;
	}

	public boolean isJavaType() {
		if (isListType()) {
			return false;
		}
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return !lastFeature.isEastadlType();
	}

	public boolean isPrimitiveType() {
		if (isListType()) {
			return false;
		}
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return lastFeature.isPrimitiveType();
	}

	public boolean isEnumType() {
		if (isListType()) {
			return false;
		}
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return lastFeature.isEnumType();
	}

	public boolean isFeatureMapType() {
		if (isListType()) {
			return false;
		}
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return lastFeature.isFeatureMapType();
	}

	public boolean isEmpty() {
		return fFeatures.isEmpty();
	}

	public int length() {
		return fFeatures.size();
	}

	public Iterable<GAwareGenFeature> getFeatures() {
		return fFeatures;
	}

	public String getImportedType() {
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return null;
		}
		if (isListType() && !lastFeature.isListType()) {
			String listTypeName = EList.class.getName();
			if (!lastFeature.isEffectiveSuppressEMFTypes()) {
				listTypeName = List.class.getName();
			}
			listTypeName += "<" + lastFeature.getImportedType(lastFeature.getGenClass()) + ">"; //$NON-NLS-1$ //$NON-NLS-2$
			return lastFeature.getGenModel().getImportedName(listTypeName);
		}
		return lastFeature.getImportedType(lastFeature.getGenClass());
	}

	public String getGetAccessor() {
		return null;
	}

	public GAwareGenFeature getFirstFeature() {
		if (fFeatures.isEmpty()) {
			return null;
		}
		return fFeatures.get(0);
	}

	public List<GAwareGenFeature> getTailFeatures() {
		if (fFeatures.size() < 2) {
			return Collections.<GAwareGenFeature> emptyList();
		}
		return fFeatures.subList(1, fFeatures.size());
	}

	public List<GAwareGenFeature> getHeadFeatures() {
		if (fFeatures.size() < 2) {
			return Collections.<GAwareGenFeature> emptyList();
		}
		return fFeatures.subList(0, fFeatures.size() - 1);
	}

	public boolean isLong() {
		if (isListType()) {
			return false;
		}
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return lastFeature.isLong();
	}

	public boolean isInteger() {
		if (isListType()) {
			return false;
		}
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return lastFeature.isInteger();
	}

	public boolean isString() {
		if (isListType()) {
			return false;
		}
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return lastFeature.isStringBasedType();
	}

	public int getSegmentCount() {
		return fFeatures.size();
	}

	public boolean isJavaMappedType() {
		if (isListType()) {
			return false;
		}
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			return false;
		}
		return lastFeature.isJavaMappedType();
	}

	public String getImportedType(GenClass context) {
		GAwareGenFeature lastFeature = getLastFeature();
		if (lastFeature == null) {
			throw new IllegalStateException("The target feature path is empty! No feature has been added!"); //$NON-NLS-1$
		}
		return lastFeature.getImportedType(context);
	}
}
