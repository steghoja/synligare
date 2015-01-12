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
package org.eclipse.eatop.examples.graphicaleditor.depd.features.create;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Hazard;
import org.eclipse.eatop.eastadl21.HazardousEvent;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateHazardousEventToHazardFeature extends
		AbstractCreateConnectionFeature {

	public CreateHazardousEventToHazardFeature(IFeatureProvider fp) {
		super(fp, "HazardousEvent To Hazard", "Create HazardousEvent To Hazard");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// return true if both anchors belong to an EClass
        // and those EClasses are not identical
        HazardousEvent source = getHazardousEvent(context.getSourceAnchor());
        Hazard target = getHazard(context.getTargetAnchor());
        if (source != null && target != null && source != target) {
            return true;
        }
        return false;
	}
	private Hazard getHazard(Anchor targetAnchor) {
		if (targetAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(targetAnchor.getParent());
            if (object instanceof Hazard) {
                return (Hazard) object;
            }
        }
        return null;
	}

	private HazardousEvent getHazardousEvent(Anchor sourceAnchor) {
		if (sourceAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(sourceAnchor.getParent());
            if (object instanceof HazardousEvent) {
                return (HazardousEvent) object;
            }
        }
        return null;
	}
	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		 
        // get EClasses which should be connected
         HazardousEvent hazardousevent = getHazardousEvent(context.getSourceAnchor());
        Hazard hazard = getHazard(context.getTargetAnchor());
 
        if (hazardousevent != null && hazard != null) {
          
        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getHazardousEvent_Hazard();
        	DiagramUtil.addReferenceToBOResource(hazardousevent, referenceId, hazard);
        	
        	AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }
        
        return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		  // return true if start anchor belongs to a EClass
        if (getHazardousEvent(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
	}
	 
}
