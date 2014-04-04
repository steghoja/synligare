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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EObject;

public class MakeEEnumEDataTypeUnsettable extends PostProcessingTemplate {

	@Override
	public void execute() {
		TreeIterator<EObject> iterator = model.eAllContents();
		while (iterator.hasNext()) {
			EObject element = iterator.next();
			if (element instanceof EAttribute) {
				EAttribute attribute = (EAttribute) element;
				EClassifier eType = attribute.getEType();
				if (eType instanceof EEnum || eType instanceof EDataType) {
					logger.info("Making the attribute '" + attribute.getName() + "' in class : " + attribute.getEContainingClass().getName() + " unsettable"); //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$
					attribute.setUnsettable(true);
				}
			}
		}
	}
}
