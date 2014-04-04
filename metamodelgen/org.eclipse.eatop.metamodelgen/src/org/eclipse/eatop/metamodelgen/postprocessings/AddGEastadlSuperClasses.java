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

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.util.EcoreUtil;
import org.eclipse.eatop.metamodelgen.internal.messages.Messages;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class AddGEastadlSuperClasses extends PostProcessingTemplate {

	private File modelFile = null;

	private Map<EClass, Collection<String>> fMissingEClasses = new HashMap<EClass, Collection<String>>();

	public AddGEastadlSuperClasses(File modelFile) {
		this.modelFile = modelFile;
	}

	@Override
	public void execute() {

		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(false));

		IPath genModelPath = new Path(modelFile.getAbsolutePath());
		IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(genModelPath);
		IContainer folder = iFile.getParent();
		IResource geastadlEcoreFile = folder.findMember(IEASTADLConstants.GEASTADL_ECORE);

		if (geastadlEcoreFile == null || !geastadlEcoreFile.exists()) {
			return;
		}

		URI ecoreURI = URI.createPlatformResourceURI(geastadlEcoreFile.getFullPath().toString(), true);
		Resource geastadlEcoreRes = resourceSet.getResource(ecoreURI, true);

		ecoreURI = URI.createFileURI(modelFile.getAbsolutePath().toString());
		Resource targetEcore = Resource.Factory.Registry.INSTANCE.getFactory(ecoreURI).createResource(ecoreURI);
		targetEcore.getContents().add(model);

		resourceSet.getResources().add(targetEcore);

		List<EClass> gEClasses = EcoreUtil.getEAllEClasses(geastadlEcoreRes);
		String modelId = getModelId(targetEcore);
		for (EClass gEClass : gEClasses) {
			Iterable<EClass> tgtEClasses = getTargetEClasses(gEClass, modelId, targetEcore);
			for (EClass tgtEClass : tgtEClasses) {
				EAnnotation mergeConfig = gEClass.getEAnnotation(IEASTADLConstants.MERGE_CONFIG);
				if (mergeConfig != null) {
					String mode = mergeConfig.getDetails().get(IEASTADLConstants.MODE);
					if (IEASTADLConstants.REPLACE.equals(mode)) {
						tgtEClass.getESuperTypes().clear();
					}
				}
				tgtEClass.getESuperTypes().add(gEClass);
			}
		}
		handleMissingEClasses();
	}

	private Collection<EClass> getTargetEClasses(EClass gEClass, String modelId, Resource targetModel) {
		Collection<String> tgtEClassNames = getTargetEClassNames(gEClass, modelId);
		Collection<EClass> tgtEClasses = new ArrayList<EClass>();
		if (isNoneMapping(tgtEClassNames)) {
			return Collections.<EClass> singleton(new NullEClass());
		}
		for (String tgtEClassName : tgtEClassNames) {
			EClass tgtEClass = EcoreUtil.findEClass(tgtEClassName, targetModel);
			if (tgtEClass != null) {
				tgtEClasses.add(tgtEClass);
			} else {
				if (!fMissingEClasses.containsKey(gEClass)) {
					fMissingEClasses.put(gEClass, new ArrayList<String>());
				}
				fMissingEClasses.get(gEClass).add(tgtEClassName);
			}
		}
		return tgtEClasses;
	}

	private Collection<String> getTargetEClassNames(EClass gEClass, String modelId) {
		Collection<String> mappedNames = getMappedTargetEClassNames(gEClass, modelId);
		if (!mappedNames.iterator().hasNext()) {
			mappedNames.add(gEClass.getName().substring(1));
		}
		return mappedNames;
	}

	private Collection<String> getMappedTargetEClassNames(EClass gEClass, String modelId) {
		Collection<String> mappedTgtNames = new ArrayList<String>();
		EAnnotation mapping = gEClass.getEAnnotation(IEASTADLConstants.CLASS_MAPPING);
		if (mapping != null) {
			String mappedTgtNameString = mapping.getDetails().get(modelId);
			if (mappedTgtNameString != null) {
				StringTokenizer tokenizer = new StringTokenizer(mappedTgtNameString, IEASTADLConstants.TGT_ECLASS_NAME_SEPARATOR);
				while (tokenizer.hasMoreTokens()) {
					mappedTgtNames.add(tokenizer.nextToken().trim());
				}
			}
		}
		return mappedTgtNames;
	}

	private boolean isNoneMapping(Collection<String> tgtEClassNames) {
		return tgtEClassNames.size() == 1 && IEASTADLConstants.NONE.equalsIgnoreCase(tgtEClassNames.iterator().next());
	}

	private String getModelId(Resource targetModel) {
		return getLastSegment(getNsURI(targetModel));
	}

	private String getNsURI(Resource targetModel) {
		EObject eObj = targetModel.getContents().get(0);
		if (!(eObj instanceof EPackage)) {
			throw new IllegalArgumentException("Could not identify target-model id. No root EPackage found for target-model!"); //$NON-NLS-1$
		}
		return ((EPackage) eObj).getNsURI();
	}

	private String getLastSegment(String nsURI) {
		int index = nsURI.lastIndexOf(IEASTADLConstants.EA_MODEL_LAST_SGMT_PREFIX);
		if (index == -1) {
			throw new IllegalArgumentException("Target-model is not an AUTOSAR meta-model!"); //$NON-NLS-1$
		}
		return nsURI.substring(index + 1);
	}

	private void handleMissingEClasses() {
		logMissingEClasses();
	}

	private void logMissingEClasses() {
		for (Entry<EClass, Collection<String>> missingEClasses : fMissingEClasses.entrySet()) {
			for (String tgtEClassName : missingEClasses.getValue()) {
				String logMsg = MessageFormat.format(Messages.logger_MissingEclass, tgtEClassName, missingEClasses.getKey().getName());
				getLogger().info(logMsg);
			}
		}
	}

	private class NullEClass extends EClassImpl {

	}
}
