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
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultFailurePort_hwTarget;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;

public class CreateFaultFailurePortToHardwarePinFeature extends
		AbstractCreateConnectionFeature {

	public CreateFaultFailurePortToHardwarePinFeature(IFeatureProvider fp) {
		super(fp, "FaultFailurePort to HardwarePin", "Create FaultFailurePortToHardwarePin");
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

	private HardwarePin getHardwarePin(Anchor anchor) 
	{
		if (anchor != null
        		&& anchor.getLink() != null) {
            Object object = anchor.getLink().getBusinessObjects().get(0);
            if (object instanceof HardwarePin) {
                return (HardwarePin) object;
            }
        }
        return null;
    }
	
	
	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a ComponentType
		
		if (getFaultFailurePort(context.getSourceAnchor()) != null ) {
				return true;			
		}
		
		return false;
	}
		
	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// return true if both anchors belong to an EClass
        // and those EClasses are not identical
	
        HardwarePin target = getHardwarePin(context.getTargetAnchor());
        FaultFailurePort source = getFaultFailurePort(context.getSourceAnchor());
        if (source != null && target != null && source != target) {	
        	
            return true;
        }
        return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		
		//invisible ele
		FaultFailurePort_hwTarget invisible = Eastadl21Factory.eINSTANCE.createFaultFailurePort_hwTarget();
		
		//ele at ends
		FaultFailurePort empt = (FaultFailurePort) getFaultFailurePort(context.getSourceAnchor());
		HardwarePin fpt = (HardwarePin) getHardwarePin(context.getTargetAnchor());		
			
		
		//1
		empt.getHwTarget().add(invisible);
		
		Assert.isNotNull(empt);
        Assert.isNotNull(fpt);
		if (empt != null && fpt != null) {
			
			//2 = target
			invisible.setHardwarePort(fpt);
			
			//3. = context
			EObject sAnchor = context.getTargetAnchor().eContainer(); 
			
			if (sAnchor instanceof ContainerShape) 
			{
				ContainerShape z = (ContainerShape) sAnchor;
				z.getLink().getBusinessObjects().get(0);
				
				if (z.getLink().getBusinessObjects().get(0) instanceof HardwareComponentPrototype)
				{
					invisible.getHardwareComponentPrototype().add(((HardwareComponentPrototype) z.getLink().getBusinessObjects().get(0)));
				}
			}                                                       	
			
			
			//DiagramUtil.addReferenceToBOResource(invisible, ErrormodelPackage.Literals.ERROR_MODEL_PROTOTYPE__HW_TARGET, empt);
			//DiagramUtil.addReferenceToBOResource(empt, ErrormodelPackage.Literals.ERROR_MODEL_PROTOTYPE__FUNCTION_TARGET, fpt);
			AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
						
		}
		
     		return newConnection;
	}
}
