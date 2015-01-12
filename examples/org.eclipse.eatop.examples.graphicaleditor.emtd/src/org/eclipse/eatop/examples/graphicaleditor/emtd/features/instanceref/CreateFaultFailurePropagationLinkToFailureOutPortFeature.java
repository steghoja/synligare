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
package org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_toPort;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateFaultFailurePropagationLinkToFailureOutPortFeature extends AbstractCreateConnectionFeature {


	public CreateFaultFailurePropagationLinkToFailureOutPortFeature(IFeatureProvider fp) {
		super(fp, "FaultFailurePropagationLink to FailureOutPort", "Create FaultFailurePropagationLink to FailureOutPort Relationship");
	}

	private FailureOutPort getFailureOutPort(Anchor anchor) {
        if (anchor != null
        		&& anchor.getLink() != null) {
            Object object = anchor.getLink().getBusinessObjects().get(0);
            if (object instanceof FailureOutPort) {
                return (FailureOutPort) object;
            }
        }
        return null;
    }
	
	private FaultFailurePropagationLink getFaultFailurePropagationLink(Anchor targetAnchor) {
		if (targetAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(targetAnchor.getParent());
            if (object instanceof FaultFailurePropagationLink) {
                return (FaultFailurePropagationLink) object;
            }
        }
        return null;
	}
	
	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		FaultFailurePropagationLink source = getFaultFailurePropagationLink(context.getSourceAnchor());
		FailureOutPort target = getFailureOutPort(context.getTargetAnchor());
	        if (source != null && target != null && source != target&& context.getTargetAnchor().getParent().getLink().getBusinessObjects().get(0) instanceof ErrorModelType) {
	        	
	            return true;
	        }
	        
	        return false;
	        
	}
	
	
	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a ComponentType
		
		if (getFaultFailurePropagationLink(context.getSourceAnchor()) != null ) {
				return true;			
		}
		
		return false;
	}
	
	
	
	@Override
	public Connection create(ICreateConnectionContext context) {

		Connection newConnection = null;
		
		//invisible ele
		FaultFailurePropagationLink_toPort invisible = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_toPort();
				
		//ele at ends
		FaultFailurePropagationLink source = (FaultFailurePropagationLink) getFaultFailurePropagationLink(context.getSourceAnchor());
		FailureOutPort target = (FailureOutPort) getBusinessObjectForPictogramElement(context.getTargetAnchor());
		
		
		if (source != null && target != null) {
			
		//1. = target
		invisible.setFaultFailurePort(target);
				
		//2. = context
		EObject sAnchor = context.getTargetAnchor().eContainer(); 
		if (sAnchor instanceof ContainerShape) 
		{
			ContainerShape z = (ContainerShape) sAnchor;
			z.getLink().getBusinessObjects().get(0);
			if (z.getLink().getBusinessObjects().get(0) instanceof ErrorModelPrototype)
			{
				invisible.getErrorModelPrototype().add(((ErrorModelPrototype) z.getLink().getBusinessObjects().get(0)));
			}
		}
		
		//3 = invi at source
		source.setToPort(invisible);
		
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getFaultFailurePropagationLink_ToPort();
		DiagramUtil.addReferenceToBOResource(invisible, referenceId, newConnection);
		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
			
		
	}
	
	
	return newConnection;	
			
	}

	


   
}
