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
package org.eclipse.eatop.examples.graphicaleditor.dfad.features.create;

import org.eclipse.eatop.eastadl21.AllocateableElement;
import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.AllocationTarget;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionAllocation;
import org.eclipse.eatop.eastadl21.FunctionAllocation_allocatedElement;
import org.eclipse.eatop.eastadl21.FunctionAllocation_target;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateFunctionAllocationConnectorFeature extends
		AbstractCreateConnectionFeature {

	public CreateFunctionAllocationConnectorFeature(IFeatureProvider fp) {
		super(fp, "FunctionAllocationRelationship", "Create FunctionAllocationRelationship");
	}
	private DesignFunctionPrototype getDesignFunctionPrototype(Anchor sourceAnchor) {
        if (sourceAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(sourceAnchor.getParent());
            if (object instanceof DesignFunctionPrototype) {
                return (DesignFunctionPrototype) object;
            }
        }
        return null;
    }
	private HardwareComponentPrototype getHardwareComponentPrototype(Anchor targetAnchor) {
        if (targetAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(targetAnchor.getParent());
            if (object instanceof HardwareComponentPrototype) 
            {
                return (HardwareComponentPrototype) object;
            }
        }
        return null;
    }
	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		DesignFunctionPrototype source =getDesignFunctionPrototype(context.getSourceAnchor());
		HardwareComponentPrototype target = getHardwareComponentPrototype(context.getTargetAnchor());
		
		if (source != null && target != null && source != target) {
	            return true;
	        }
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		FunctionAllocation ereference=Eastadl21Factory.eINSTANCE.createFunctionAllocation();
		DesignFunctionPrototype source =getDesignFunctionPrototype(context.getSourceAnchor());
		HardwareComponentPrototype target = getHardwareComponentPrototype(context.getTargetAnchor());
		
		FunctionAllocation_allocatedElement fCP1 = Eastadl21Factory.eINSTANCE.createFunctionAllocation_allocatedElement();
		ereference.setAllocatedElement(fCP1);
		FunctionAllocation_target fCP2 = Eastadl21Factory.eINSTANCE.createFunctionAllocation_target();
		ereference.setTarget(fCP2);
		 Assert.isNotNull(source);
         Assert.isNotNull(target);
         if (source != null && target != null) {
        	 
        	 fCP1.setAllocateableElement((AllocateableElement) source);
 			fCP1.getAllocateableElement_context().add((AllocateableElement) source);
 			
 			fCP2.setAllocationTarget((AllocationTarget) target);
			fCP2.getAllocationTarget_context().add((AllocationTarget) target);
			Allocation allocation = (Allocation) getDiagram().getLink().getBusinessObjects().get(0);
			
			EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getAllocation_FunctionAllocation();
			DiagramUtil.addReferenceToBOResource(allocation,referenceId,ereference);
			
			
			AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
			addContext.setNewObject(ereference);
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
         }
		return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		if (getDesignFunctionPrototype(context.getSourceAnchor()) != null ) {
			
			return true;
		
	}
	return false;
	}

}
