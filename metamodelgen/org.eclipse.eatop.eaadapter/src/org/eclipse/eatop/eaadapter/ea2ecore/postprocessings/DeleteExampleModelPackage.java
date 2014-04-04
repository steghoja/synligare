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

import java.util.Iterator;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public class DeleteExampleModelPackage extends PostProcessingTemplate {

	private void removeExampleModelPackage(EPackage parent) {
		Iterator<EObject> iter = parent.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof EPackage) {

				if (IConstants.PACKAGE_MODEL.equals(((EPackage) element).getName())) {
					EList<EPackage> ePackageList = ((EPackage) element).getESubpackages();
					for (EPackage ePackage : ePackageList) {
						if (ePackage.getName().equals(IConstants.PACKAGE_EXAMPLEMODEL)) {
							ePackageList.remove(ePackage);
							logger.info(Messages.logger_RemoveExampleModelPackage);
							return;
						}
					}
				}

			}
		}
	}

	@Override
	public void execute() {
		removeExampleModelPackage(model);
	}
}
