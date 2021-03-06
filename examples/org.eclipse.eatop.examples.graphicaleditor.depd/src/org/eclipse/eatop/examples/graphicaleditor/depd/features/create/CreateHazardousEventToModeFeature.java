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
import org.eclipse.eatop.eastadl21.HazardousEvent;
import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateHazardousEventToModeFeature extends
		AbstractCreateConnectionFeature {

	public CreateHazardousEventToModeFeature(IFeatureProvider fp) {
		super(fp, "HazardousEvent To Mode", "Create OperatingMode");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
        HazardousEvent source = getHazardousEvent(context.getSourceAnchor());
        Mode target = getMode(context.getTargetAnchor());
        if (source != null && target != null && source != target) {
            return true;
        }
        return false;
    }

	@Override
	public Connection create(ICreateConnectionContext context) {
        Connection newConnection = null;
 
        HazardousEvent hazardousEvent = getHazardousEvent(context.getSourceAnchor()); //this is source
        Mode mode = getMode(context.getTargetAnchor()); //this is target
        
        Assert.isNotNull(hazardousEvent);
        Assert.isNotNull(mode);
 
        if (hazardousEvent != null && mode != null) {

        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getHazardousEvent_OperatingMode();
        	DiagramUtil.addReferenceToBOResource(hazardousEvent, referenceId, mode);
        	
            AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }
       
        return newConnection;
    }

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {

        if (getHazardousEvent(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
    
	}
	 private HazardousEvent getHazardousEvent(Anchor anchor) {
	        if (anchor != null) {
	            Object object = getBusinessObjectForPictogramElement(anchor.getParent());
	            if (object instanceof HazardousEvent) {
	                return (HazardousEvent) object;
	            }
	        }
	        return null;
	    }  
	    private Mode getMode(Anchor anchor) {
	        if (anchor != null) {
	            Object object = getBusinessObjectForPictogramElement(anchor.getParent());
	            if (object instanceof Mode) {
	                return (Mode) object;
	            }
	        }
	        return null;
	    }
}
