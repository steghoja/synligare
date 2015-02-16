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
package org.eclipse.eatop.examples.graphicaleditor.reqd.features.create;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.DeriveRequirement;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateDeriveRequirement extends AbstractCreateConnectionFeature {

	public CreateDeriveRequirement(IFeatureProvider fp) {
		super(fp, "DeriveRequirement", "Create DeriveRequirement");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		Requirement derivedFrom = getRequirement(context.getSourceAnchor());
		 Requirement derived = getRequirement(context.getTargetAnchor());
	        if (derivedFrom != null && derived != null && derivedFrom != derived) {
	            return true;
	        }
	        return false;
	}
	private Requirement getRequirement(Anchor anchor) {
        if (anchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(anchor.getParent());
            if (object instanceof Requirement) {
                return (Requirement) object;
            }
        }
        return null;
    }
	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		// get ComponentType which should be connected
		Anchor sourceAnchor = context.getSourceAnchor();
		Requirement derivedFrom = (Requirement) getBusinessObjectForPictogramElement(sourceAnchor.getParent());
		// get Interface which should be connected
		Anchor targetAnchor = context.getTargetAnchor();
		Requirement derived = (Requirement) getBusinessObjectForPictogramElement(targetAnchor.getParent());
		 Assert.isNotNull(derivedFrom);
         Assert.isNotNull(derived);
		if (derivedFrom != null && derived != null) {
			// get new business object
			DeriveRequirement eReference = createReference(derivedFrom, derived);
			// add connection for business object
			AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
			addContext.setNewObject(eReference);
			
			RequirementsModel model = (RequirementsModel) getDiagram().getLink().getBusinessObjects().get(0);
			
			EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getContext_OwnedRelationship();
        	DiagramUtil.addReferenceToBOResource((org.eclipse.emf.ecore.EObject) model,
        			referenceId, (org.eclipse.emf.ecore.EObject) eReference);
			
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
		}
		

		return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		if (getRequirement(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;


		
	}
	private DeriveRequirement createReference(Requirement derivedFrom, Requirement derived) {
		DeriveRequirement der=Eastadl21Factory.eINSTANCE.createDeriveRequirement();
		der.getDerived().add(derivedFrom);
		der.getDerivedFrom().add(derived);
		return der;
	}
}
