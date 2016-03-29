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
package org.eclipse.eatop.examples.graphicaleditor.depdld.features;

import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.TechnicalSafetyConcept;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;


public class DEPDLDUpdateFeature extends AbstractUpdateFeature {

	public DEPDLDUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		 // return true, if linked business object is a EClass
        Object bo =
            getBusinessObjectForPictogramElement(context.getPictogramElement());
        return (bo instanceof Satisfy||bo instanceof TechnicalSafetyConcept);
	}

	@Override
	public boolean update(IUpdateContext context) {
		 // retrieve name from business model
        String businessName = null;
        
        PictogramElement pictogramElement = context.getPictogramElement();
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof Satisfy) {
        	Satisfy st = (Satisfy) bo;
            businessName = st.getShortName();
        }if (bo instanceof TechnicalSafetyConcept) {
        	TechnicalSafetyConcept tsc = (TechnicalSafetyConcept) bo;
            businessName = tsc.getShortName();
        }
 
        // Set name in pictogram model
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
            	
                if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(1)) {
                	
                    Text text = (Text) shape.getGraphicsAlgorithm();
                    text.setValue(businessName);
                    return true;
                }
            }
        }
 
        return false;

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
        if (bo instanceof Satisfy) {
        	Satisfy st = (Satisfy) bo;
            businessName = st.getShortName();
        } if (bo instanceof TechnicalSafetyConcept) {
        	TechnicalSafetyConcept tsc = (TechnicalSafetyConcept) bo;
            businessName = tsc.getShortName();
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
}
