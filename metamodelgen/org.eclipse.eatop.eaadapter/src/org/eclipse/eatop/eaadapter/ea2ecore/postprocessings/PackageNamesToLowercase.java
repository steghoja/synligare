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

package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public class PackageNamesToLowercase extends PostProcessingTemplate {

	@Override
	public void execute() {
		TreeIterator<EObject> iter = model.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof EPackage) {
				String oldName = ((EPackage) element).getName();
				String newName = oldName.toLowerCase();
				((EPackage) element).setName(newName);

				String oldNSUri = ((EPackage) element).getNsURI();
				String newNSUri = oldNSUri.toLowerCase();
				((EPackage) element).setNsURI(newNSUri);

				String oldNSPrefix = ((EPackage) element).getNsPrefix();
				String newNSPrefix = oldNSPrefix.toLowerCase();
				((EPackage) element).setNsPrefix(newNSPrefix);
			}
		}
	}
}
