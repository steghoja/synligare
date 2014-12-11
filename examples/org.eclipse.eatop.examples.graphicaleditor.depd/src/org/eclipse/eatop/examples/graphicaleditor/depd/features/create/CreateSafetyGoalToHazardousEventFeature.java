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

public class CreateSafetyGoalToHazardousEventFeature extends
		AbstractCreateConnectionFeature {

	public CreateSafetyGoalToHazardousEventFeature(IFeatureProvider fp) {
		super(fp, "SafetyGoal To HazardousEvent", "Create SafetyGoal To HazardousEvent");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// return true if both anchors belong to an EClass
        // and those EClasses are not identical
        SafetyGoal source = getSafetyGoal(context.getSourceAnchor());
        HazardousEvent target = getHazardousEvent(context.getTargetAnchor());
        if (source != null && target != null && source != target) {
            return true;
        }
        return false;
	}
	private HazardousEvent getHazardousEvent(Anchor targetAnchor) {
		if (targetAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(targetAnchor.getParent());
            if (object instanceof HazardousEvent) {
                return (HazardousEvent) object;
            }
        }
        return null;
	}

	private SafetyGoal getSafetyGoal(Anchor sourceAnchor) {
		if (sourceAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(sourceAnchor.getParent());
            if (object instanceof SafetyGoal) {
                return (SafetyGoal) object;
            }
        }
        return null;
	}
	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		 
        // get EClasses which should be connected
        SafetyGoal safetygoal = getSafetyGoal(context.getSourceAnchor());
        HazardousEvent hazardousevent = getHazardousEvent(context.getTargetAnchor());
 
        Assert.isNotNull(safetygoal);
        Assert.isNotNull(hazardousevent);
        
        if (safetygoal != null && hazardousevent != null) {
           
        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getSafetyGoal_DerivedFrom();
        	DiagramUtil.addReferenceToBOResource(safetygoal, referenceId, hazardousevent);
          
        	AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }
        
        return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		  // return true if start anchor belongs to a EClass
        if (getSafetyGoal(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
	}
	
}