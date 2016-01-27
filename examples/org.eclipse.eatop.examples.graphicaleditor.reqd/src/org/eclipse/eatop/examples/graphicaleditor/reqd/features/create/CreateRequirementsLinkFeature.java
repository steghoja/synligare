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
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsLink;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateRequirementsLinkFeature extends AbstractCreateConnectionFeature{

	public CreateRequirementsLinkFeature(IFeatureProvider fp) {
		super(fp, "RequirementsLink", "Create RequirementsLink Relationship");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		 Requirement source = getRequirement(context.getSourceAnchor());
		 Requirement target = getRequirement(context.getTargetAnchor());
	        if (source != null && target != null && source != target) {
	            return true;
	        }
	        return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {

		Connection newConnection = null;
	
		
		Requirement source = (Requirement) getRequirement(context.getSourceAnchor());
		Requirement target = (Requirement) getRequirement(context.getTargetAnchor());
		
         Assert.isNotNull(source);
         Assert.isNotNull(target);
		if (source != null && target != null) {
			// get new business object
			RequirementsLink eReference = createReference(source, target);
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
		// return true if start anchor belongs to a ComponentType
		if (getRequirement(context.getSourceAnchor()) != null ) {
			
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
	private RequirementsLink createReference(Requirement source, Requirement target) {
		RequirementsLink reL=Eastadl21Factory.eINSTANCE.createRequirementsLink();
		reL.getSource().add(source);
		reL.getTarget().add(target);
		return reL;
	}


   
}
