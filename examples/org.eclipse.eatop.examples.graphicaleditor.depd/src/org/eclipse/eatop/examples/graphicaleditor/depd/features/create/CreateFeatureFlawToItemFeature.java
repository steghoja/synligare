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
import org.eclipse.eatop.eastadl21.FeatureFlaw;
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


public class CreateFeatureFlawToItemFeature extends AbstractCreateConnectionFeature {
 
    public CreateFeatureFlawToItemFeature (IFeatureProvider fp) {
        super(fp, "FeatureFlaw to Item", "Create FeatureFlaw to Item");
    }
 
    public boolean canCreate(ICreateConnectionContext context) {
        FeatureFlaw source = getFeatureFlaw(context.getSourceAnchor());
        Item target = getItem(context.getTargetAnchor());
        if (source != null && target != null && source != target) {
            return true;
        }
        return false;
    }
 
    public boolean canStartConnection(ICreateConnectionContext context) {
        if (getFeatureFlaw(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
    }
 
    public Connection create(ICreateConnectionContext context) {
        Connection newConnection = null;
 
        FeatureFlaw featureFlaw = getFeatureFlaw(context.getSourceAnchor()); //this is source
        Item item = getItem(context.getTargetAnchor()); //this is target
        
        Assert.isNotNull(featureFlaw);
        Assert.isNotNull(item);
 
        if (featureFlaw != null && item != null) {

        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getFeatureFlaw_Item();
        	DiagramUtil.addReferenceToBOResource(featureFlaw, referenceId, item);
        	
            AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }
       
        return newConnection;
    }
 
    private FeatureFlaw getFeatureFlaw(Anchor anchor) {
        if (anchor != null) {
            Object object = getBusinessObjectForPictogramElement(anchor.getParent());
            if (object instanceof FeatureFlaw) {
                return (FeatureFlaw) object;
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
}