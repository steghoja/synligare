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
package org.eclipse.eatop.metamodelgen.templates.genmodel;

import org.eclipse.eatop.metamodelgen.templates.internal.genmodel.geastadl.GAwareGenClass;
import org.eclipse.eatop.metamodelgen.templates.internal.genmodel.geastadl.GAwareGenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelFactoryImpl;

/**
 * Customized GenModel factory class
 */
public class EastadlGenModelFactory extends GenModelFactoryImpl {

	@Override
	public org.eclipse.emf.codegen.ecore.genmodel.GenClass createGenClass() {
		return new GAwareGenClass();
	};

	@Override
	public org.eclipse.emf.codegen.ecore.genmodel.GenFeature createGenFeature() {
		return new GAwareGenFeature();
	};
}
