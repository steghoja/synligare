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
package org.eclipse.eatop.examples.graphicaleditor.depsconstrd.element;

import org.eclipse.eatop.eastadl21.QuantitativeSafetyConstraint;
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


public class AddQuantitativeSafetyConstraintFeature extends AbstractAddShapeFeature {
	public static final IColorConstant COMPONENT_TYPE_COLOR_TEXT_FOREGROUND = new ColorConstant(0, 0, 0);
	public static final IColorConstant COMPONENT_TYPE_COLOR_FOREGROUND = new ColorConstant(98, 131, 167);
	public static final IColorConstant COMPONENT_TYPE_COLOR_BACKGROUND = new ColorConstant(212, 231, 248);
	public static final int COMPONENT_TYPE_WIDTH_DEFAULT = 200;
	public static final int COMPONENT_TYPE_HEIGHT_DEFAULT = 100;
	public static final int COMPONENT_TYPE_HEIGHT_UPPER_COMPARTMENT_DEFAULT = 20;
	public static final int PORT_SIZE = 10;
	private static final IColorConstant E_CLASS_TEXT_FOREGROUND = IColorConstant.BLACK;
	private static final IColorConstant E_CLASS_FOREGROUND = new ColorConstant(98, 131, 167);
    private static final IColorConstant E_CLASS_BACKGROUND = new ColorConstant(187, 218, 247);
	
	public AddQuantitativeSafetyConstraintFeature(IFeatureProvider fp) {

		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a FaultFailure
		if (context.getNewObject() instanceof QuantitativeSafetyConstraint) {
			// check if user wants to add to a diagram
			if (context.getTargetContainer() instanceof Diagram) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		QuantitativeSafetyConstraint addedClaim = (QuantitativeSafetyConstraint) context.getNewObject();
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
            roundedRectangle.setForeground(manageColor(E_CLASS_FOREGROUND));
            roundedRectangle.setBackground(manageColor(E_CLASS_BACKGROUND));
            roundedRectangle.setLineWidth(2);
            gaService.setLocationAndSize(roundedRectangle,
                context.getX(), context.getY(), width, height);
 
            // if added Class has no resource we add it to the resource 
            // of the diagram
            // in a real scenario the business model would have its own resource
            if (((EObject) addedClaim).eResource() == null) {
                     getDiagram().eResource().getContents().add((EObject) addedClaim);
            }
            // create link and wire it
            link(containerShape, addedClaim);
        }
 
        // SHAPE WITH LINE
        {
            // create shape for line
            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
 
            // create and set graphics algorithm
            Polyline polyline =
                gaService.createPolyline((GraphicsAlgorithmContainer) shape, new int[] { 0, 25, width, 25 });
            polyline.setForeground(manageColor(E_CLASS_FOREGROUND));
            polyline.setLineWidth(2);
        }
      
 
        // SHAPE WITH TEXT for ShortName
        {
            // create shape for text
            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
            
            // create and set text graphics algorithm
            Text text = gaService.createText(shape, addedClaim.getShortName());
            text.setForeground(manageColor(E_CLASS_TEXT_FOREGROUND));
            text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT); 
            // vertical alignment has as default value "center"
            text.setFont(gaService.manageDefaultFont(getDiagram(), true, false));
            gaService.setLocationAndSize(text, 22,12, width, 10);
          
            
            // create link and wire it
            link(shape, addedClaim);
            
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
     // SHAPE WITH TEXT for ItemType
        {
        	 // create shape for text  
            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
        	//add Text 
             Text text1=gaService.createText(shape, ((EObject) addedClaim).eClass().getName());
             text1.setForeground(manageColor(E_CLASS_TEXT_FOREGROUND));
             text1.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT );
             text1.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
             gaService.setLocationAndSize(text1, 22, 2, width, 10);
             // create link and wire it
	            link(shape, addedClaim);
        }
     
     // add a chopbox anchor to the shape 
	      
        peCreateService.createChopboxAnchor(containerShape);
        // call the layout feature
        layoutPictogramElement(containerShape);


		return containerShape;

	}
}