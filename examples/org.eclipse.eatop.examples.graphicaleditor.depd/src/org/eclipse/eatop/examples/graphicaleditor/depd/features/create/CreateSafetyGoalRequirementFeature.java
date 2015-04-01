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
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.SafetyGoal;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateSafetyGoalRequirementFeature extends AbstractCreateConnectionFeature {
 
    public CreateSafetyGoalRequirementFeature (IFeatureProvider fp) {
        super(fp, "SafetyGoal to Requirement", "SafetyGoal to Requirement");
    }
 
    public boolean canCreate(ICreateConnectionContext context) {
        SafetyGoal source = getSafetyGoal(context.getSourceAnchor());
        Requirement target = getRequirement(context.getTargetAnchor());
        if (source != null && target != null && source != target) {
            return true;
        }
        return false;
    }
 
    public boolean canStartConnection(ICreateConnectionContext context) {
        if (getSafetyGoal(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
    }
 
    public Connection create(ICreateConnectionContext context) {
        Connection newConnection = null;
 
        SafetyGoal safetyGoal = getSafetyGoal(context.getSourceAnchor()); //this is source
        Requirement requirement = getRequirement(context.getTargetAnchor()); //this is target
        
        Assert.isNotNull(safetyGoal);
        Assert.isNotNull(requirement);
 
        if (safetyGoal != null && requirement != null) {

        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getSafetyGoal_Requirement();
        	DiagramUtil.addObjectToBOResource(safetyGoal, referenceId, requirement);
        	
            AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }
       
        return newConnection;
    }
 
    private SafetyGoal getSafetyGoal(Anchor anchor) {
        if (anchor != null) {
            Object object = getBusinessObjectForPictogramElement(anchor.getParent());
            if (object instanceof SafetyGoal) {
                return (SafetyGoal) object;
            }
        }
        return null;
    }
    
    private Requirement getRequirement(Anchor anchor) {
        if (anchor != null) {
            Object object = getBusinessObjectForPictogramElement(anchor.getParent());
            if (object instanceof Requirement) {
                return (Requirement) object;
            }
        }
        return null;
    }    
}