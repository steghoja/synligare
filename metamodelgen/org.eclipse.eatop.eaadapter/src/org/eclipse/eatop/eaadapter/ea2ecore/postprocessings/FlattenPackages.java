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
package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EPackage;

public class FlattenPackages extends PostProcessingTemplate {

	@Override
	public void execute() throws Exception {
		EList<EPackage> subpackages = model.getESubpackages();
		for (EPackage pkg : subpackages) {
			flattenPackage(model, pkg);
		}
		model.getESubpackages().clear();
	}

	private void flattenPackage(EPackage rootPkg, EPackage pkg) {
		final EList<EPackage> subpkgs = pkg.getESubpackages();
		for (EPackage subpkg : subpkgs) {
			flattenPackage(pkg, subpkg);
		}

		rootPkg.getEClassifiers().addAll(pkg.getEClassifiers());
		return;
	}
}
