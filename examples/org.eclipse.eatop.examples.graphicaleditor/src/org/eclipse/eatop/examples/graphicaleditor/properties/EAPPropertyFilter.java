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
 * 
 */
package org.eclipse.eatop.examples.graphicaleditor.properties;

import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;

public class EAPPropertyFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {

		EObject eObject = Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(pe);
		if (eObject instanceof EAPackage) {
			return true;
		}
		return false;

	}

}
