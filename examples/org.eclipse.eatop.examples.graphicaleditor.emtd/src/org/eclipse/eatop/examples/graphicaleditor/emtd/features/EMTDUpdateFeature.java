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
package org.eclipse.eatop.examples.graphicaleditor.emtd.features;

import org.eclipse.eatop.eastadl21.ErrorBehavior;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.InternalFaultPrototype;
import org.eclipse.eatop.eastadl21.ProcessFaultPrototype;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class EMTDUpdateFeature extends AbstractUpdateFeature {

	public EMTDUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		 // return true, if linked business object is a EClass
        Object bo =
            getBusinessObjectForPictogramElement(context.getPictogramElement());
        return (bo instanceof ErrorModelPrototype
        		|| bo instanceof ProcessFaultPrototype
        		|| bo instanceof InternalFaultPrototype
        		|| bo instanceof ErrorBehavior
        		|| bo instanceof FaultFailurePropagationLink);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		// retrieve name from pictogram model
        String pictogramName = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
                if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(1)) {
                	Text text=(Text) shape.getGraphicsAlgorithm();
                    pictogramName = text.getValue();
                }
            }
        }
 
        // retrieve name from business model
        String businessName = null;
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof ErrorModelPrototype) {
        	ErrorModelPrototype afp = (ErrorModelPrototype) bo;
            businessName = afp.getShortName();
        } else if (bo instanceof ProcessFaultPrototype) {
        	ProcessFaultPrototype afp = (ProcessFaultPrototype) bo;
            businessName = afp.getShortName();
        } else if (bo instanceof InternalFaultPrototype) {
        	InternalFaultPrototype afp = (InternalFaultPrototype) bo;
            businessName = afp.getShortName();
        } else if (bo instanceof ErrorBehavior) {
        	ErrorBehavior afp = (ErrorBehavior) bo;
            businessName = afp.getShortName();
        } else if (bo instanceof FaultFailurePropagationLink) {
        	FaultFailurePropagationLink afp = (FaultFailurePropagationLink) bo;
            businessName = afp.getShortName();
        } else if (bo instanceof FaultFailurePort) {
        	FaultFailurePort afp = (FaultFailurePort) bo;
            businessName = afp.getShortName();
        }
        
        // update needed, if names are different
        boolean updateNameNeeded =
            ((pictogramName == null && businessName != null) || 
                (pictogramName != null && !pictogramName.equals(businessName)));
        if (updateNameNeeded) {
            return Reason.createTrueReason("Name is out of date");
        } else {
            return Reason.createFalseReason();
        }
	}

	@Override
	public boolean update(IUpdateContext context) {
		 // retrieve name from business model
        String businessName = null;
        
        PictogramElement pictogramElement = context.getPictogramElement();
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof ErrorModelPrototype) {
        	ErrorModelPrototype req = (ErrorModelPrototype) bo;
            businessName = req.getShortName();
        } else if (bo instanceof ProcessFaultPrototype) {
        	ProcessFaultPrototype req = (ProcessFaultPrototype) bo;
            businessName = req.getShortName();
        } else if (bo instanceof InternalFaultPrototype) {
        	InternalFaultPrototype req = (InternalFaultPrototype) bo;
            businessName = req.getShortName();
        } else if (bo instanceof ErrorBehavior) {
        	ErrorBehavior req = (ErrorBehavior) bo;
            businessName = req.getShortName();
        } else if (bo instanceof FaultFailurePropagationLink) {
        	FaultFailurePropagationLink req = (FaultFailurePropagationLink) bo;
            businessName = req.getShortName();
        } else if (bo instanceof FaultFailurePort) {
        	FaultFailurePort req = (FaultFailurePort) bo;
            businessName = req.getShortName();
        }
        
        // Set name in pictogram model
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
            	
                if (shape.getGraphicsAlgorithm() instanceof Text
                		&& shape == cs.getChildren().get(1)) {
                	
                    Text text = (Text) shape.getGraphicsAlgorithm();
                    text.setValue(businessName);
                    return true;
                }
            }
        }
        return false;
	}
}
