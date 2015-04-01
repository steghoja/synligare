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
package org.eclipse.eatop.examples.graphicaleditor.depsconstrd.instanceref;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FaultFailure;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultFailure_anomaly;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;


public class CreateFaultFailureToFaultFailurePortFeature extends AbstractCreateConnectionFeature {


	public CreateFaultFailureToFaultFailurePortFeature(IFeatureProvider fp) {
		super(fp, "FaultFailure To FaultFailurePort", "Create FaultFailure To FaultFailurePort Relationship");
	}

	private FaultFailurePort getFaultFailurePort(Anchor anchor) {
        if (anchor != null
        		&& anchor.getLink() != null) {
            Object object = anchor.getLink().getBusinessObjects().get(0);
            if (object instanceof FaultFailurePort) {
                return (FaultFailurePort) object;
            }
        }
        return null;
    }
	
	private FaultFailure getFaultFailure(Anchor targetAnchor) {
		if (targetAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(targetAnchor.getParent());
            if (object instanceof FaultFailure) {
                return (FaultFailure) object;
            }
        }
        return null;
	}
	
	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		FaultFailure source = getFaultFailure(context.getSourceAnchor());
		FaultFailurePort target = getFaultFailurePort(context.getTargetAnchor());
	        if (source != null && target != null && source != target) {
	        	
	            return true;
	        }
	        
	        return false;
	        
	}
	
	
	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a ComponentType
		
		if (getFaultFailure(context.getSourceAnchor()) != null ) {
				return true;			
		}
		
		return false;
	}
	
	
	
	@Override
	public Connection create(ICreateConnectionContext context) {

		Connection newConnection = null;
		
		FaultFailure_anomaly invisible = Eastadl21Factory.eINSTANCE.createFaultFailure_anomaly();
				
		FaultFailure source = (FaultFailure) getFaultFailure(context.getSourceAnchor());
		FaultFailurePort target = (FaultFailurePort) getBusinessObjectForPictogramElement(context.getTargetAnchor());
		
		if (source != null && target != null) {
			
		//1. = target
		invisible.setAnomaly(target);
		
				
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
		source.setAnomaly(invisible);
	
		
		
		//DiagramUtil.addReferenceToBOResource(invisible, ErrormodelPackage.Literals.ANOMALY__TYPE, newConnection);
		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
			
		
	}
	
	
	return newConnection;	
			
	}

	


   
}
