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
import org.eclipse.emf.ecore.EStructuralFeature;

public class MakeAllFeaturesOrdered extends PostProcessingTemplate {

	@Override
	public void execute() {
		TreeIterator<?> iter = model.eAllContents();
		while (iter.hasNext()) {
			EObject element = (EObject) iter.next();
			if (element instanceof EStructuralFeature) {
				((EStructuralFeature) element).setOrdered(true);
			}
		}
	}

}
