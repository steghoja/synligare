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
package org.eclipse.eatop.metamodelgen.templates.internal.util;

import org.eclipse.eatop.metamodelgen.templates.source.EastADLSourceRevisions;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;

////////EAST-ADL specific
public class EASTADLGenModels extends GenModels {

	public static boolean IsLibJarCreated(GenModel genModel) {
		return true;
	}

	public static boolean IsLibSrcZipCreated(GenModel genModel) {
		return true;
	}

	public static String getEastADLReleaseNamespace(GenModel genModel) {
		String nsURI = GenModels.getRootGenPackage(genModel).getNSURI();
		return nsURI; // nsURI.substring(0, nsURI.lastIndexOf("/"));
	}

	public static String getClassSimpleName(GenModel genModel, String classNamePostfix) {
		return getRootGenPackage(genModel).getPrefix().toLowerCase() + "." + getRootGenPackage(genModel).getPrefix() + classNamePostfix; //$NON-NLS-1$
	}

	public static String getEastADLMMDescriptorID(GenModel genModel) {
		return IEASTADLTemplateConstants.EATOP_PROJECT_PREFIX + GenModels.getRootGenPackage(genModel).getPackageName();
	}

	public static boolean hasRootUtilitiesGenPackage(GenModel genModel) {
		return true;
	}

	public static String getFileExtension(GenModel genModel) {
		return IEASTADLTemplateConstants.DEFAULT_EASTADL_FILE_EXTENSION;
	}

	public static String getNamespacePattern(GenModel genModel) {
		return IEASTADLTemplateConstants.DEFAULT_NSURI + "/" + EastADLSourceRevisions.get().major + "\\.[0-9]\\.[0-9]+$"; //$NON-NLS-1$ //$NON-NLS-2$ 
	}

	/**
	 * creates the "filter" attribute for the extension point "org.eclipse.sphinx.emf.validation.registration"
	 */
	public static String createValidationFilter(GenModel genModel) {
		return "_" + EastADLSourceRevisions.get().major + EastADLSourceRevisions.get().minor; //$NON-NLS-1$
	}

	/**
	 * creates the "Name" attribute for the extension point "org.eclipse.sphinx.emf.validation.registration"
	 */
	public static String createValidationName(GenModel genModel) {
		return "EAST-ADL " + EastADLSourceRevisions.get().major + EastADLSourceRevisions.get().minor; //$NON-NLS-1$
	}
}
