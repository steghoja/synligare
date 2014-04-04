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

import java.util.Iterator;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class GenModels {
	static protected String ANNOTATION_EAADAPTER = "EAAdapter"; //$NON-NLS-1$
	static protected String EAADAPTER_METAMODEL_TYPE = "metaModelType"; //$NON-NLS-1$
	static protected String METAMODEL_TYPE_EASTADL = "EAST-ADL"; //$NON-NLS-1$

	public static GenPackage getRootGenPackage(GenModel genModel) {
		return genModel.getGenPackages().get(0);
	}

	public static String getUtilityClassSimpleName(GenModel genModel, String classNamePostfix) {
		return getRootGenPackage(genModel).getPrefix() + classNamePostfix;
	}

	public static String getUtilityClassName(GenModel genModel, String classNamePostfix) {
		return getRootGenPackage(genModel).getUtilitiesPackageName() + "." + getUtilityClassSimpleName(genModel, classNamePostfix); //$NON-NLS-1$
	}

	public static String getQualifiedFactoryName(GenModel genModel) {
		return genModel.getModelName() + ".util." + getFactoryName(genModel); //$NON-NLS-1$
	}

	public static String getFactoryName(GenModel genModel) {
		return getCapModelName(genModel) + "Factory"; //$NON-NLS-1$
	}

	private static String getCapModelName(GenModel genModel) {
		String modelName = genModel.getModelName();
		return modelName.substring(0, 1).toUpperCase() + modelName.substring(1);
	}

	public static String getImportedFactoryName(GenModel genModel) {
		return genModel.getImportedName(getQualifiedFactoryName(genModel));
	}

	public static String getNormalizedModelName(GenModel genModel) {
		String modelName = genModel.getModelName();
		return modelName.substring(0, 1) + modelName.substring(1);
	}

	public static String getModelPluginVersion(GenModel genModel) {
		EPackage rootEPackage = getRootGenPackage(genModel).getEcorePackage();
		String version = EcoreUtil.getAnnotation(rootEPackage, GenModelPackage.eNS_URI, "modelPluginVersion"); //$NON-NLS-1$
		return version != null ? version : "0.5.0"; //$NON-NLS-1$
	}

	public static String getEditPluginVersion(GenModel genModel) {
		EPackage rootEPackage = getRootGenPackage(genModel).getEcorePackage();
		String version = EcoreUtil.getAnnotation(rootEPackage, GenModelPackage.eNS_URI, "editPluginVersion"); //$NON-NLS-1$
		return version != null ? version : "0.5.0"; //$NON-NLS-1$
	}

	public static String findInterfacePackageName(GenModel genModel, String className) {
		String interfacePackageName = ""; //$NON-NLS-1$
		for (Iterator<EObject> iterator = genModel.eAllContents(); iterator.hasNext();) {
			EObject type = iterator.next();
			if (type instanceof GenClass) {
				GenClass genClass = (GenClass) type;
				if (genClass.getName().equals(className)) {
					interfacePackageName = genClass.getGenPackage().getInterfacePackageName();
					break;
				}
			}

		}
		return interfacePackageName;
	}

	public static GenClass findGenClass(GenModel genModel, String className) {
		GenClass genClass = null;
		for (Iterator<EObject> iterator = genModel.eAllContents(); iterator.hasNext();) {
			EObject type = iterator.next();
			if (type instanceof GenClass) {
				GenClass genClazz = (GenClass) type;
				if (genClazz.getName().equals(className)) {
					genClass = genClazz;
					break;
				}
			}
		}
		return genClass;
	}

	/**
	 * creates the "id" attribute for the extension point "org.eclipse.sphinx.emf.validation.registration"
	 */
	public static String createValidationId(GenModel genModel) {
		return String.format("%s.%s.validation", genModel.getModelPluginID(), getRootGenPackage(genModel).getPrefix()); //$NON-NLS-1$

	}

	/**
	 * creates the "NSURI" attribute for the extension point "org.eclipse.sphinx.emf.validation.registration"
	 */
	public static String createValidationNSURI(GenModel genModel) {
		GenClass referrable = findGenClass(genModel, "Referrable"); //$NON-NLS-1$
		Assert.isNotNull(referrable);
		return referrable.getGenPackage().getNSURI();
	}

	/**
	 * creates the "class" attribute for the extension point "org.eclipse.sphinx.emf.validation.registration"
	 */
	public static String createValidationClass(GenModel genModel) {
		GenClass referrable = findGenClass(genModel, "Referrable"); //$NON-NLS-1$
		Assert.isNotNull(referrable);
		return referrable.getQualifiedInterfaceName();
	}

	/**
	 * Returns the EAAdapter of the given model element or null if none is set.
	 * 
	 * @param eElement
	 *            Model element to examine.
	 * @param tag
	 *            Tag to look for.
	 * @return Value for given tag. If not <code>null</code> it's not empty.
	 */
	static protected String getEAAdapterAnnotation(EModelElement eElement, String tag) {

		if (eElement == null) {
			throw new IllegalArgumentException("eElement is null"); //$NON-NLS-1$
		}
		if (tag == null) {
			throw new IllegalArgumentException("tag is null"); //$NON-NLS-1$
		}

		String result = EcoreUtil.getAnnotation(eElement, ANNOTATION_EAADAPTER, tag);
		if (result != null && result.length() == 0) {
			result = null;
		}
		return result;
	}

}
