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

import org.eclipse.eatop.metamodelgen.templates.internal.util.IEASTADLTemplateConstants;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.ecore.EClassifier;

public class GenOperationFeature extends GAwareGenFeature {

	@SuppressWarnings("unused")
	private GenOperation fSetter;
	private GenOperation fGetter;

	public GenOperationFeature(GenOperation setter, GenOperation getter) {
		fSetter = setter;
		fGetter = getter;
	}

	@Override
	public boolean isListType() {
		return fGetter.isListType();
	}

	@Override
	public String getImportedElementType(GenClass context) {
		if (isListType()) {
			return getGenModel().getImportedName(getERawGenericType().getName());
		}
		return fGetter.getImportedType(context);
	}

	@Override
	public EClassifier getERawGenericType() {
		return fGetter.getEcoreOperation().getEGenericType().getERawType();
	}

	@Override
	public String getGetAccessor() {
		return fGetter.getName();
	}

	@Override
	public GenClass getTypeGenClass() {
		return fGetter.getTypeGenClass();
	}

	@Override
	public String getCapName() {
		return fGetter.getName().substring(IEASTADLTemplateConstants.GETTER_PREFIX.length());
	}

	@Override
	public String getAccessorName() {
		return getCapName();
	}

	@Override
	public boolean hasDelegateFeature() {
		return false;
	}

	@Override
	public boolean isResolveProxies() {
		return false;
	}

	@Override
	public boolean isVolatile() {
		return true;
	}

	@Override
	public String getType() {
		return getType(getGenClass());
	}

	@Override
	public String getType(GenClass context) {
		return fGetter.getType(context);
	}

	@Override
	public GenClass getGenClass() {
		return fGetter.getGenClass();
	}

	@Override
	public GenModel getGenModel() {
		return fGetter.getGenModel();
	}

}
