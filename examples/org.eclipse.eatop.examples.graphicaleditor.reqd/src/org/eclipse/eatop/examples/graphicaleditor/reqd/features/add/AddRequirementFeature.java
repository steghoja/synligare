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
package org.eclipse.eatop.examples.graphicaleditor.reqd.features.add;

import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;



public class AddRequirementFeature extends AbstractAddShapeFeature {
	private IColorConstant Requirement_TEXT_FOREGROUND = IColorConstant.BLACK;
	private IColorConstant Requirement_FOREGROUND = new ColorConstant(98, 131, 167);
	private IColorConstant Requirement_BACKGROUND = new ColorConstant(187, 218, 247);
		 
	public AddRequirementFeature(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a EClass
        if (context.getNewObject() instanceof Requirement) {
            // check if user wants to add to a diagram
            if (context.getTargetContainer() instanceof Diagram) {
                return true;
            }
        }
        return false;

	}

	@Override
	public PictogramElement add(IAddContext context) {
		  Requirement addedRequirement = (Requirement) context.getNewObject();
	        Diagram targetDiagram = (Diagram) context.getTargetContainer();
	 
	      
	       
	        // CONTAINER SHAPE WITH ROUNDED RECTANGLE
	       final IPeCreateService peCreateService = Graphiti.getPeCreateService();
	      final  ContainerShape containerShape =
	             peCreateService.createContainerShape(targetDiagram, true);
	        
	        // check whether the context has a size (e.g. from a create feature)
	        // otherwise define a default size for the shape
	        final int width = context.getWidth() <= 0 ? 100 : context.getWidth();
	        final int height = context.getHeight() <= 0 ? 50 : context.getHeight();


	       final IGaService gaService = Graphiti.getGaService();
	        RoundedRectangle roundedRectangle; // need to access it later
	 
	        {
	            // create and set graphics algorithm
	        	

	        	
	             roundedRectangle =
	                gaService.createRoundedRectangle(containerShape, 5, 5);
	             roundedRectangle.setForeground(manageColor(Requirement_FOREGROUND));
	             roundedRectangle.setBackground(manageColor(Requirement_BACKGROUND));
	             roundedRectangle.setLineWidth(2);
	            gaService.setLocationAndSize(roundedRectangle,
	                context.getX(), context.getY(), width, height);
	 
	            // if added Class has no resource we add it to the resource 
	            // of the diagram
	            // in a real scenario the business model would have its own resource
	            if (((EObject) addedRequirement).eResource() == null) {
	                     getDiagram().eResource().getContents().add((EObject) addedRequirement);
	            }
	            // create link and wire it
	            link(containerShape, addedRequirement);
	        }
	 
	        // SHAPE WITH LINE
	        {
	            // create shape for line
	            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
	 
	            // create and set graphics algorithm
	            Polyline polyline =
	                gaService.createPolyline((GraphicsAlgorithmContainer) shape, new int[] { 0, 25, width, 25 });
	            polyline.setForeground(manageColor(Requirement_FOREGROUND));
	            polyline.setLineWidth(2);
	        }
	      
	 
	        // SHAPE WITH TEXT for ShortName
	        {
	            // create shape for text
	            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
	            
	            // create and set text graphics algorithm
	            Text text = gaService.createText(shape, addedRequirement.getShortName());
	            text.setForeground(manageColor(Requirement_TEXT_FOREGROUND));
	            text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT); 
	            // vertical alignment has as default value "center"
	            text.setFont(gaService.manageDefaultFont(getDiagram(), true, false));
	            gaService.setLocationAndSize(text, 22,12, width, 10);
	          
	            
	            // create link and wire it
	            link(shape, addedRequirement);	 
	            
	            //provide information to support direct-editing directly
	            //after object creation (must be actived additionally)
	            IDirectEditingInfo directEditingInfo=getFeatureProvider().getDirectEditingInfo();
	            // set container shape for direct editing after object creation
	            directEditingInfo.setMainPictogramElement(containerShape);
	            //set container shape for direct and graphics algorithm where the editor for
	            //direct editing shall be opened after object creation
	            directEditingInfo.setPictogramElement(shape);
	            directEditingInfo.setGraphicsAlgorithm(text);
	          
	        }
	     // SHAPE WITH TEXT for RequirementType
	        {
	        	 // create shape for text  
	            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
	        	//add Text 
	             Text text1=gaService.createText(shape, ((EObject) addedRequirement).eClass().getName());
	             text1.setForeground(manageColor(Requirement_TEXT_FOREGROUND));
	             text1.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
	             text1.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
	             gaService.setLocationAndSize(text1, 22, 2, width, 10);
	             // create link and wire it
		            link(shape, addedRequirement);
	        }
	     // SHAPE WITH TEXT for TextRequirement
	        {
	        	 // create shape for text  
	            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
	        	//add Text 2
	             Text text2=gaService.createText(shape, addedRequirement.getText());
	             text2.setForeground(manageColor(Requirement_TEXT_FOREGROUND));
	             text2.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT );
	             text2.setFont(gaService.manageDefaultFont(getDiagram(), true, false));
	             gaService.setLocationAndSize(text2, 5, 22, width, 25);
	             // create link and wire it
		            link(shape, addedRequirement);
	        }
	        

	        
	        // add a chopbox anchor to the shape 
	      
	        peCreateService.createChopboxAnchor(containerShape);
	        // call the layout feature
	        layoutPictogramElement(containerShape);

	    

	        return containerShape;

	}
	
	public void changeColor() {
		this.setBackground(new ColorConstant(255, 215, 0));
		this.setForeground(new ColorConstant(135, 206, 250));
	}
	
	public void setForeground(IColorConstant requirement_FOREGROUND) {
		Requirement_FOREGROUND = requirement_FOREGROUND;
	}

	public void setBackground(IColorConstant requirement_BACKGROUND) {
		Requirement_BACKGROUND = requirement_BACKGROUND;
	}	

}
