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
package org.eclipse.eatop.examples.graphicaleditor.depscd.features.create;

import org.eclipse.eatop.eastadl21.Claim;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateClaimToQualityRequirementFeature extends
		AbstractCreateConnectionFeature {

	public CreateClaimToQualityRequirementFeature(IFeatureProvider fp) {
		super(fp, "Claim To QualityRequirement", "Create Claim To QualityRequirement");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// return true if both anchors belong to an EClass
        // and those EClasses are not identical
        Claim source = getClaim(context.getSourceAnchor());
        QualityRequirement target = getQualityRequirement(context.getTargetAnchor());
        if (source != null && target != null && source != target) {
            return true;
        }
        return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		 
        // get EClasses which should be connected
        Claim claim = getClaim(context.getSourceAnchor());
        QualityRequirement qualityRequirement = getQualityRequirement(context.getTargetAnchor());
        Assert.isNotNull(claim);
        Assert.isNotNull(qualityRequirement);
        
        if (claim != null && qualityRequirement != null) {
           
        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getClaim_SafetyRequirement();
        	DiagramUtil.addReferenceToBOResource(claim, referenceId, qualityRequirement);
        	
        	AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }
        
        return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		  // return true if start anchor belongs to a EClass
        if (getClaim(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
	}
	private QualityRequirement getQualityRequirement(Anchor targetAnchor) {
		if (targetAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(targetAnchor.getParent());
            if (object instanceof QualityRequirement) {
                return (QualityRequirement) object;
            }
        }
        return null;
	}

	private Claim getClaim(Anchor sourceAnchor) {
		if (sourceAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(sourceAnchor.getParent());
            if (object instanceof Claim) {
                return (Claim) object;
            }
        }
        return null;
	}
}
