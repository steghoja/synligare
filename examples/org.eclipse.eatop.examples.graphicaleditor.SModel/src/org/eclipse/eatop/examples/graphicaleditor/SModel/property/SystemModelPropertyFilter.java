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
package org.eclipse.eatop.examples.graphicaleditor.SModel.property;

import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisLevel;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.ImplementationLevel;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;


public class SystemModelPropertyFilter extends AbstractPropertySectionFilter {

	@Override
	protected boolean accept(PictogramElement pe) {
		EObject eObject = Graphiti.getLinkService()
				.getBusinessObjectForLinkedPictogramElement(pe);
		if (eObject instanceof SystemModel 
				|| eObject instanceof VehicleLevel 
				|| eObject instanceof AnalysisLevel 
				|| eObject instanceof DesignLevel 
				|| eObject instanceof ImplementationLevel
				|| eObject instanceof AnalysisFunctionPrototype
				|| eObject instanceof DesignFunctionPrototype
				|| eObject instanceof HardwareComponentPrototype
				|| eObject instanceof Allocation) {
			return true;
		}
		return false;

	}

}
