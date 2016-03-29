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
package org.eclipse.eatop.examples.graphicaleditor.depd.features;

import org.eclipse.eatop.eastadl21.HazardousEvent;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;


public class HazardousEventUpdateFeature extends AbstractUpdateFeature {

	public HazardousEventUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		 // return true, if linked business object is a EClass
        Object bo =
            getBusinessObjectForPictogramElement(context.getPictogramElement());
        return (bo instanceof HazardousEvent);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {

		// retrieve name from pictogram model
        String pictogramName1 = null;
        String pictogramName2 = null;
        String pictogramName3 = null;
        String pictogramName4 = null;
        String pictogramName5 = null;
       
      
        
        PictogramElement pictogramElement = context.getPictogramElement();
        if (pictogramElement instanceof ContainerShape) {
            ContainerShape cs = (ContainerShape) pictogramElement;
            for (Shape shape : cs.getChildren()) {
            	 if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(1)) {
                 	
                 	Text text=(Text) shape.getGraphicsAlgorithm();
                     pictogramName1 = text.getValue();
                     
                 }
            	 else if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(3)) {
                	
                	Text text2=(Text) shape.getGraphicsAlgorithm();
                   
                    pictogramName2 = text2.getValue();
                } else if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(4)) {
                	
                	Text text3=(Text) shape.getGraphicsAlgorithm();
                   
                    pictogramName3 = text3.getValue();
                } else if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(5)) {
                	
                	Text text4=(Text) shape.getGraphicsAlgorithm();
                   
                    pictogramName4 = text4.getValue();
                } else if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(6)) {
                	
                	Text text5=(Text) shape.getGraphicsAlgorithm();
                   
                    pictogramName5 = text5.getValue();
                }
            	
               
            }
        }
 
        // retrieve name from business model
        String businessName1 = null;
        String businessName2 = null;
        String businessName3 = null;
        String businessName4 = null;
        String businessName5 = null;
       
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof HazardousEvent) {
        	HazardousEvent he = (HazardousEvent) bo;
            businessName1 = he.getShortName();
            businessName2 = he.getControllability().toString();
            businessName3 = he.getExposure().toString();
            businessName4 = he.getSeverity().toString();
            businessName5 = he.getHazardClassification().toString();
            
        }
 
        // update needed, if names are different
        boolean updateNameNeeded =
               ((pictogramName1 == null && businessName1 != null) || 
                (pictogramName1 != null && !pictogramName1.equals(businessName1))||
                (pictogramName2 == null && businessName2 != null)||
                (pictogramName2 != null && !pictogramName2.equals(businessName2))||
                (pictogramName3 == null && businessName3 != null)||
                (pictogramName3 != null && !pictogramName3.equals(businessName3))||
                (pictogramName4 == null && businessName4 != null)||
                (pictogramName4 != null && !pictogramName4.equals(businessName4))||
                (pictogramName5 == null && businessName5 != null)||
                (pictogramName5 != null && !pictogramName5.equals(businessName5)));
                

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
        String businessName3 = null;
        String businessName4 = null;
        String businessName5 = null;
        
        PictogramElement pictogramElement = context.getPictogramElement();
        Object bo = getBusinessObjectForPictogramElement(pictogramElement);
        if (bo instanceof HazardousEvent) {
        	HazardousEvent he = (HazardousEvent) bo;
            businessName1 = he.getShortName();
            businessName2 = he.getControllability().toString();
            businessName3 = he.getExposure().toString();
            businessName4 = he.getSeverity().toString();
            businessName5 = he.getHazardClassification().toString();
            
         
           
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
                }
                 if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(4)) {
                 	
                     Text text3 = (Text) shape.getGraphicsAlgorithm();
                     text3.setValue(businessName3); 
                 }
                 if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(5)) {
                 	
                     Text text4 = (Text) shape.getGraphicsAlgorithm();
                     text4.setValue(businessName4); 
                 }
                 if (shape.getGraphicsAlgorithm() instanceof Text&&shape==cs.getChildren().get(6)) {
                 	
                     Text text5 = (Text) shape.getGraphicsAlgorithm();
                     text5.setValue(businessName5); 
                     return true;
                 }
            }
        }
 
        return false;

	}

}
