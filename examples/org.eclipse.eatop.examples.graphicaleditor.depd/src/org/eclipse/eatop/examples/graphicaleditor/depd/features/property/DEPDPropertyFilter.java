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
package org.eclipse.eatop.examples.graphicaleditor.depd.features.property;

import org.eclipse.eatop.eastadl21.FeatureFlaw;
import org.eclipse.eatop.eastadl21.Hazard;
import org.eclipse.eatop.eastadl21.HazardousEvent;
import org.eclipse.eatop.eastadl21.Item;
import org.eclipse.eatop.eastadl21.SafetyGoal;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;


public class DEPDPropertyFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {

		EObject eObject =
	            Graphiti.getLinkService()
	            .getBusinessObjectForLinkedPictogramElement(pe);
	        if (eObject instanceof Item||eObject instanceof Hazard||
	        		eObject instanceof HazardousEvent||eObject instanceof FeatureFlaw
                 ||eObject instanceof SafetyGoal ) {
	            return true;
	        }
	        return false;
	}

}
