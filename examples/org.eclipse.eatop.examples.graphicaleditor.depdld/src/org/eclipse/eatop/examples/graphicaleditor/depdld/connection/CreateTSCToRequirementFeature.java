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
package org.eclipse.eatop.examples.graphicaleditor.depdld.connection;


import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.TechnicalSafetyConcept;
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


public class CreateTSCToRequirementFeature extends
		AbstractCreateConnectionFeature {

	public CreateTSCToRequirementFeature(IFeatureProvider fp) {
		super(fp, "TSC to Requirement", "Create TSCToRequirement");
	}
	
	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		TechnicalSafetyConcept source = getTechnicalSafetyConcept(context.getSourceAnchor());
		  Requirement target = getRequirement(context.getTargetAnchor());
	        if (source != null && target != null && source != target) {
	            return true;
	        }
	        return false;
	}
	private TechnicalSafetyConcept getTechnicalSafetyConcept(Anchor sourceAnchor) {
        if (sourceAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(sourceAnchor.getParent());
            if (object instanceof TechnicalSafetyConcept) {
                return (TechnicalSafetyConcept) object;
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
		if (getTechnicalSafetyConcept(context.getSourceAnchor()) != null ) {
			
				return true;
			
		}
		return false;
	}
	@Override
	public Connection create(ICreateConnectionContext context) {

		Connection newConnection = null;
	
		
		 TechnicalSafetyConcept TSC = (TechnicalSafetyConcept) getTechnicalSafetyConcept(context.getSourceAnchor());
		 Requirement requirement = (Requirement) getRequirement(context.getTargetAnchor());
		
         Assert.isNotNull(TSC);
         Assert.isNotNull(requirement);
		if (TSC != null && requirement != null) {
			EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getTechnicalSafetyConcept_TechnicalSafetyRequirement();
			DiagramUtil.addReferenceToBOResource((org.eclipse.emf.ecore.EObject) TSC,
					referenceId, (org.eclipse.emf.ecore.EObject) requirement);

			AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
		}
		
		return newConnection;
	}
}
