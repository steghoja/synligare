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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.eatop.metamodelgen.templates.internal.util.IEASTADLTemplateConstants;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EcoreFactory;

public class GAwareGenFeature extends GenFeatureImpl {

	public enum GAFType {
		GET, SET
	}

	// ###################### GEASTADL ########################
	private static final TargetFeaturePath NO_MAPPING_PATH = new NoMappingTargetFeaturePath();
	private static Map<String, GAFType> fPrefixTypeMap = new HashMap<String, GAFType>();

	static {
		fPrefixTypeMap.put(IEASTADLTemplateConstants.G_GETTER_PREFIX, GAFType.GET);
		fPrefixTypeMap.put(IEASTADLTemplateConstants.G_SETTER_PREFIX, GAFType.SET);
	}

	private String getAttributeNameBase() {
		return getFeatureName().substring(1);
	}

	private String gGetGetAccessor() {

		String result = isBooleanType() ? "Is" + getAttributeNameBaseUpperInitial() : "gGet" + getAttributeNameBaseUpperInitial(); //$NON-NLS-1$ //$NON-NLS-2$

		return result;
	}

	@Override
	public String getGetAccessor() {
		if (isGGenFeature()) {
			return gGetGetAccessor();
		}
		return super.getGetAccessor();
	}

	public String getIsSetAccessorName() {
		if (isGGenFeature()) {
			return "gIsSet" + getAttributeNameBaseUpperInitial(); //$NON-NLS-1$
		}
		return "isSet" + getAccessorName(); //$NON-NLS-1$		
	}

	public String getUnsetAccessorName() {
		if (isGGenFeature()) {
			return "gUnset" + getAttributeNameBaseUpperInitial(); //$NON-NLS-1$
		}
		return "unset" + getAccessorName(); //$NON-NLS-1$		
	}

	public String getSetAccessorName() {
		if (isGGenFeature()) {
			return "gSet" + getAttributeNameBaseUpperInitial(); //$NON-NLS-1$
		}
		return "set" + getAccessorName(); //$NON-NLS-1$		
	}

	private String getFeatureName() {
		return getEcoreFeature().getName();
	}

	public TargetFeaturePath getTargetFeaturePath(GenClass implGenClass) {
		Iterable<String[]> segmentedPaths = getSegmentedTgtFeaturePaths(implGenClass.getGenModel());
		for (String[] segmentedPath : segmentedPaths) {
			TargetFeaturePath tgtFeaturePath = getTargetFeaturePath(implGenClass, segmentedPath);
			if (tgtFeaturePath != NO_MAPPING_PATH) {
				return tgtFeaturePath;
			}
		}
		return NO_MAPPING_PATH;
	}

	private TargetFeaturePath getTargetFeaturePath(GenClass implGenClass, String[] tgtFeatureNames) {
		GenClass ownerGenClass = implGenClass;
		TargetFeaturePath featurePath = new TargetFeaturePath();
		for (String featureName : tgtFeatureNames) {
			GAwareGenFeature feature = findFeatureByName(featureName, ownerGenClass);
			if (feature == null) {
				return NO_MAPPING_PATH;
			}
			featurePath.append(feature);
			ownerGenClass = feature.getTypeGenClass();
		}
		return featurePath;
	}

	private GAwareGenFeature findFeatureByName(String featureName, GenClass ownerGenClass) {
		if (IEASTADLTemplateConstants.MIXED_TEXT.equalsIgnoreCase(featureName)) {
			return new GMixedTextGenFeature(getGenModel());
		}
		GAwareGenFeature feature = findFeatureByName(featureName, ownerGenClass.getAllGenFeatures());
		if (feature == null) {
			feature = findOperationFeatureByName(featureName, ownerGenClass.getAllGenOperations());
		}
		return feature;
	}

	private GAwareGenFeature findFeatureByName(String name, Iterable<GenFeature> featureCandidates) {
		for (GenFeature feature : featureCandidates) {
			if (name.equals(feature.getName())) {
				return (GAwareGenFeature) feature;
			}
		}
		return null;
	}

	private GAwareGenFeature findOperationFeatureByName(String name, Iterable<GenOperation> operations) {
		GenOperation setter = findOperationByName(IEASTADLTemplateConstants.SETTER_PREFIX + CodeGenUtil.capName(name), operations);
		if (setter != null) {
			GenOperation getter = findOperationByName(IEASTADLTemplateConstants.GETTER_PREFIX + CodeGenUtil.capName(name), operations);
			if (getter != null) {
				return new GenOperationFeature(setter, getter);
			}
		}
		return null;
	}

	private GenOperation findOperationByName(String name, Iterable<GenOperation> operations) {
		for (GenOperation operation : operations) {
			if (name.equals(operation.getName())) {
				return operation;
			}
		}
		return null;
	}

	private Iterable<String[]> getSegmentedTgtFeaturePaths(GenModel genModel) {
		String implModel = genModel.getModelName();
		EMap<String, String> featureMappings = getFeatureMappings(implModel);
		Collection<String[]> tgtFeaturePaths = new ArrayList<String[]>();
		String[] paths = featureMappings.get(implModel).split(IEASTADLTemplateConstants.TGT_SEPARATOR);
		for (String path : paths) {
			tgtFeaturePaths.add(path.trim().split(IEASTADLTemplateConstants.TGT_FEATURE_PATH_SEGMENT_SEPARATOR));
		}
		return tgtFeaturePaths;
	}

	private EMap<String, String> getFeatureMappings(String implModel) {
		EMap<String, String> featureMappings = getFeatureMappingAnno().getDetails();
		if (!featureMappings.containsKey(implModel)) {
			if (featureMappings.containsKey(IEASTADLTemplateConstants.EASTADL_RELEASE)) {
				featureMappings.put(implModel, featureMappings.get(IEASTADLTemplateConstants.EASTADL_RELEASE));
				featureMappings.removeKey(IEASTADLTemplateConstants.EASTADL_RELEASE);
			} else {
				featureMappings.put(implModel, getAttributeNameBaseLowerInitial());
			}
		}
		return featureMappings;
	}

	private EAnnotation getFeatureMappingAnno() {
		EAnnotation featureMappingAnno = getEAnnotation(IEASTADLTemplateConstants.FEATURE_MAPPING);
		if (featureMappingAnno == null) {
			featureMappingAnno = EcoreFactory.eINSTANCE.createEAnnotation();
			featureMappingAnno.setSource(IEASTADLTemplateConstants.FEATURE_MAPPING);
		}
		return featureMappingAnno;
	}

	private EAnnotation getEAnnotation(String source) {
		return getEcoreFeature().getEAnnotation(source);
	}

	private String getAttributeNameBaseLowerInitial() {
		String nameBase = getAttributeNameBase();
		String initial = nameBase.substring(0, 1);
		return initial.toLowerCase() + nameBase.substring(1);
	}

	private String getAttributeNameBaseUpperInitial() {
		String nameBase = getAttributeNameBase();
		String initial = nameBase.substring(0, 1);
		return initial.toUpperCase() + nameBase.substring(1);
	}

	public boolean isGGenFeature() {
		// TODO check if it actually is a feature(attribute)?
		return IEASTADLTemplateConstants.GEASTADL_MODEL_NAME.equals(getGenModel().getModelName());
	}

	public boolean isEastadlType() {
		EClassifier type = getERawGenericType();
		return type.getInstanceClass() == null; // The EAST-ADL Classes being generated do not have an InstanceClass
	}

	public EClassifier getERawGenericType() {
		return getEcoreFeature().getEGenericType().getERawType();
	}

	public boolean isEastadlElementType() {
		return isEastadlType() && !isEnumType();
	}

	public boolean isEastadlEnumType() {
		return isEastadlType() && isEnumType();
	}

	public String getImportedElementType(GenClass context) {
		if (isListType()) {
			return getGenModel().getImportedName(getEcoreFeature().getEGenericType().getEClassifier().getName());
		}
		return getImportedType(context);
	}

	public boolean isInteger() {
		return isOfInstanceType(Integer.class);
	}

	public boolean isLong() {
		return isOfInstanceType(Long.class);
	}

	public boolean isDouble() {
		return isOfInstanceType(Double.class);
	}

	public boolean isBigDecimal() {
		return isOfInstanceType(BigDecimal.class);
	}

	public boolean isJavaMappedType() {
		if (isReferenceType()) {
			return false;
		}
		GenDataType dataType = getTypeGenDataType();
		if (dataType == null) {
			return false;
		}
		return dataType.getQualifiedInstanceClassName().startsWith("java."); //$NON-NLS-1$
	}

	private boolean isOfInstanceType(Class<?> instanceClass) {
		GenDataType dataType = getTypeGenDataType();
		if (dataType == null) {
			return false;
		}
		return instanceClass.getName().equals(dataType.getQualifiedInstanceClassName());
	}

	// ###################### GEASTADL END ############################
}
