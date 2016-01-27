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
import org.eclipse.eatop.eastadl21.Item;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateHazardToItemFeature extends AbstractCreateConnectionFeature {

	public CreateHazardToItemFeature(IFeatureProvider fp) {
		super(fp, "Hazard to Item", "Create Hazard to Item");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		Hazard source = getHazard(context.getSourceAnchor());
        Item target = getItem(context.getTargetAnchor());
        if (source != null && target != null && source != target) {
            return true;
        }
        return false;
	}
	 private Hazard getHazard(Anchor anchor) {
	        if (anchor != null) {
	            Object object = getBusinessObjectForPictogramElement(anchor.getParent());
	            if (object instanceof Hazard) {
	                return (Hazard) object;
	            }
	        }
	        return null;
	    }
	    
	    private Item getItem(Anchor anchor) {
	        if (anchor != null) {
	            Object object = getBusinessObjectForPictogramElement(anchor.getParent());
	            if (object instanceof Item) {
	                return (Item) object;
	            }
	        }
	        return null;
	    }    
	@Override
	public Connection create(ICreateConnectionContext context) {
		 Connection newConnection = null;
		 
	        Hazard hazard = getHazard(context.getSourceAnchor()); //this is source
	        Item item = getItem(context.getTargetAnchor()); //this is target
	        
	        Assert.isNotNull(hazard);
	        Assert.isNotNull(item);
	 
	        if (hazard != null && item != null) {	    
	        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getHazard_Item();
	        	DiagramUtil.addReferenceToBOResource(hazard, referenceId, item);
	        	
	            AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
	            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
	        }
	       
	        return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		if (getHazard(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
	}

}
