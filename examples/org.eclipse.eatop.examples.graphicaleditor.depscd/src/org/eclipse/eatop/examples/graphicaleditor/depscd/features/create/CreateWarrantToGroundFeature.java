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

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Ground;
import org.eclipse.eatop.eastadl21.Warrant;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateWarrantToGroundFeature extends
		AbstractCreateConnectionFeature {

	public CreateWarrantToGroundFeature(IFeatureProvider fp) {
		super(fp, "Warrant To Ground", "Create Warrant To Ground");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// return true if both anchors belong to an EClass
        // and those EClasses are not identical
        Warrant source = getWarrant(context.getSourceAnchor());
        Ground target = getGround(context.getTargetAnchor());
        if (source != null && target != null && source != target) {
            return true;
        }
        return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		 
        // get EClasses which should be connected
         Warrant warrant = getWarrant(context.getSourceAnchor());
        Ground ground = getGround(context.getTargetAnchor());
 
        if (warrant != null && ground != null) {
          
        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getWarrant_Evidence();
        	DiagramUtil.addReferenceToBOResource(warrant, referenceId, ground);
        	
        	AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }
        
        return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		  // return true if start anchor belongs to a EClass
        if (getWarrant(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
	}
	private Warrant getWarrant(Anchor sourceAnchor) {
		if (sourceAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(sourceAnchor.getParent());
            if (object instanceof Warrant) {
                return (Warrant) object;
            }
        }
        return null;
	}

	private Ground getGround(Anchor targetAnchor) {
		if (targetAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(targetAnchor.getParent());
            if (object instanceof Ground) {
                return (Ground) object;
            }
        }
        return null;
	}
}
