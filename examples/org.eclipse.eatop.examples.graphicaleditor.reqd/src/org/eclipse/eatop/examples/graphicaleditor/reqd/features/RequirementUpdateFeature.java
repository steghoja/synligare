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
package org.eclipse.eatop.examples.graphicaleditor.reqd.features;

import org.eclipse.eatop.eastadl21.TraceableSpecification;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;


public class RequirementUpdateFeature extends AbstractUpdateFeature {

	public RequirementUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		 // return true, if linked business object is a EClass
        Object bo =
            getBusinessObjectForPictogramElement(context.getPictogramElement());
        return (bo instanceof TraceableSpecification);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {

		// retrieve name from pictogram model
        String pictogramName1 = null;
        String pictogramName2 = null;
        PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
            	 if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(1)) {
                 	
                 	Text text=(Text) shape.getGraphicsAlgorithm();
                     pictogramName1 = text.getValue();
                     
                 }
            if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(3)) {
                	
                	Text text2=(Text) shape.getGraphicsAlgorithm();
                   
                    pictogramName2 = text2.getValue();
                }
            }
        }
 
        // retrieve name from business model
        String businessName1 = null;
        String businessName2 = null;
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof TraceableSpecification) {
        	TraceableSpecification req = (TraceableSpecification) bo;
            businessName1 = req.getShortName();
            businessName2 = req.getText();
        }
 
        // update needed, if names are different
        boolean updateNameNeeded =
            ((pictogramName1 == null && businessName1 != null) || 
                (pictogramName1 != null && !pictogramName1.equals(businessName1))||
                (pictogramName2 == null && businessName2 != null)|| (pictogramName2 != null && !pictogramName2.equals(businessName2)));

        if (updateNameNeeded) {
            return Reason.createTrueReason("Name is out of date");
        }
        else {
            return Reason.createFalseReason();
        }

	
	}

	@Override
	public boolean update(IUpdateContext context) {
		 // retrieve name from business model
        String businessName1 = null;
        String businessName2 = null;
        
        PictogramElement pictogramElement = context.getPictogramElement();
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof TraceableSpecification) {
        	TraceableSpecification req = (TraceableSpecification) bo;
            businessName1 = req.getShortName();
            businessName2 = req.getText();
           
        }
 
        // Set name in pictogram model
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
               if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(1)) {
                	
                    Text text1 = (Text) shape.getGraphicsAlgorithm();
                    text1.setValue(businessName1);
                    
                 }
                 if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(3)) {
                	
                    Text text2 = (Text) shape.getGraphicsAlgorithm();
                    text2.setValue(businessName2);
                    return true;
                }
                
            }
        }
 
        return false;

	}

}
