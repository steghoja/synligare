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

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class GenPackages {

	public static String getBaseContentType(GenModel genModel, GenPackage genPackage) {
		return IEASTADLTemplateConstants.EASTADL_CONTENT_TYPE;
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

		String result = EcoreUtil.getAnnotation(eElement, IEASTADLTemplateConstants.EAADAPTER, tag);
		if (result != null && result.length() == 0) {
			result = null;
		}
		return result;
	}
}
