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

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;

public class GMixedTextGenFeature extends GAwareGenFeature {

	private final GenModel genModel;

	public GMixedTextGenFeature(GenModel genModel) {
		this.genModel = genModel;

		EAttribute mixedTextAttr = EcoreFactory.eINSTANCE.createEAttribute();
		mixedTextAttr.setName("mixedText"); //$NON-NLS-1$
		mixedTextAttr.setEType(EcorePackage.Literals.ESTRING);

		setEcoreFeature(mixedTextAttr);
	}

	/**
	 * Overridden to make sure that methods relying on the GenModel work even though this class is not contained in a
	 * GenClass which would usually be used to retrieve the GenModel.
	 */
	@Override
	public GenModel getGenModel() {
		return genModel;
	}

	@Override
	public String getGetAccessor() {
		return "getMixedText"; //$NON-NLS-1$
	}

	@Override
	public String getSetAccessorName() {
		return "setMixedText"; //$NON-NLS-1$
	}

	@Override
	public String getIsSetAccessorName() {
		return "isSetMixed"; //$NON-NLS-1$
	}

	@Override
	public String getUnsetAccessorName() {
		return "unsetMixed"; //$NON-NLS-1$
	}

	@Override
	public String getAccessorName() {
		return "MixedText"; //$NON-NLS-1$
	}

	@Override
	public boolean isJavaMappedType() {
		return true;
	}

	@Override
	public boolean isEnumType() {
		return false;
	}

	@Override
	public boolean isListType() {
		return false;
	}

	@Override
	public boolean isGGenFeature() {
		return false;
	}
}
