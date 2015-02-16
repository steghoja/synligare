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
import org.eclipse.eatop.eastadl21.ErrorModelPrototype_hwTarget;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.core.runtime.Assert;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;


public class CreateErrorModelPrototypeToHardwareComponentPrototypeFeature extends
		AbstractCreateConnectionFeature {

	public CreateErrorModelPrototypeToHardwareComponentPrototypeFeature(IFeatureProvider fp) {
		super(fp, "ErrorModelPrototype to HardwareComponentPrototype", "Create ErrorModelPrototypeToHardwareComponentPrototype");
	}

	private ErrorModelPrototype getErrorModelPrototype(Anchor targetAnchor) {
		if (targetAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(targetAnchor.getParent());
            if (object instanceof ErrorModelPrototype) {
                return (ErrorModelPrototype) object;
            }
        }
        return null;
	}

	private HardwareComponentPrototype getHardwareComponentPrototype(Anchor sourceAnchor) 
	{
		if (sourceAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(sourceAnchor.getParent());
            if (object instanceof HardwareComponentPrototype) {
                return (HardwareComponentPrototype) object;}}
        return null;
    }
	
	
	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a ComponentType
		
		if (getErrorModelPrototype(context.getSourceAnchor()) != null ) {
				return true;			
		}
		
		return false;
	}
		
	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// return true if both anchors belong to an EClass
        // and those EClasses are not identical
	
        HardwareComponentPrototype target = getHardwareComponentPrototype(context.getTargetAnchor());
        ErrorModelPrototype source = getErrorModelPrototype(context.getSourceAnchor());
        if (source != null && target != null && source != target) {	
            return true;
        }
        return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		
		
		ErrorModelPrototype_hwTarget invisible = Eastadl21Factory.eINSTANCE.createErrorModelPrototype_hwTarget();
		
		
		ErrorModelPrototype empt = (ErrorModelPrototype) getErrorModelPrototype(context.getSourceAnchor());
		HardwareComponentPrototype fpt = (HardwareComponentPrototype) getHardwareComponentPrototype(context.getTargetAnchor());		
			
		
		empt.getHwTarget().add(invisible);
		
		Assert.isNotNull(empt);
        Assert.isNotNull(fpt);
		if (empt != null && fpt != null) {
			
			invisible.getHardwareComponentPrototype_context().add(fpt);
			
			invisible.setHardwareComponentPrototype(fpt);

			//DiagramUtil.addReferenceToBOResource(empt, ErrormodelPackage.Literals.ERROR_MODEL_PROTOTYPE__TARGET, fpt);
			AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
						
		}
		
     		return newConnection;
	}
}
