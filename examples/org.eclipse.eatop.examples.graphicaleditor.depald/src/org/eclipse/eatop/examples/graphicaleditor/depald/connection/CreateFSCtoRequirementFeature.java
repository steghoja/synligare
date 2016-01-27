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
package org.eclipse.eatop.examples.graphicaleditor.depald.connection;


import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionalSafetyConcept;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateFSCtoRequirementFeature extends
		AbstractCreateConnectionFeature {

	public CreateFSCtoRequirementFeature(IFeatureProvider fp) {
		super(fp, "FSC to Requirement", "Create FSCToRequirement");
	}
	
	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		FunctionalSafetyConcept source = getFunctionalSafetyConcept(context.getSourceAnchor());
		  Requirement target = getRequirement(context.getTargetAnchor());
	        if (source != null && target != null && source != target) {
	            return true;
	        }
	        return false;
	}
	private FunctionalSafetyConcept getFunctionalSafetyConcept(Anchor sourceAnchor) {
        if (sourceAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(sourceAnchor.getParent());
            if (object instanceof FunctionalSafetyConcept) {
                return (FunctionalSafetyConcept) object;
            }
        }
        return null;
    }
	private Requirement getRequirement(Anchor targetAnchor) {
        if (targetAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(targetAnchor.getParent());
            if (object instanceof Requirement) {
                return (Requirement) object;
            }
        }
        return null;
    }
	
	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a ComponentType
		if (getFunctionalSafetyConcept(context.getSourceAnchor()) != null ) {
			
				return true;
			
		}
		return false;
	}
	@Override
	public Connection create(ICreateConnectionContext context) {

		Connection newConnection = null;
	
		
		 FunctionalSafetyConcept fsc = (FunctionalSafetyConcept) getFunctionalSafetyConcept(context.getSourceAnchor());
		 Requirement requirement = (Requirement) getRequirement(context.getTargetAnchor());
		
         Assert.isNotNull(fsc);
         Assert.isNotNull(requirement);
		if (fsc != null && requirement != null) {
			EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getFunctionalSafetyConcept_FunctionalSafetyRequirement();
			DiagramUtil.addReferenceToBOResource((org.eclipse.emf.ecore.EObject) fsc,
					referenceId, (org.eclipse.emf.ecore.EObject) requirement);

			AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
		}
		
		return newConnection;
	}
}
