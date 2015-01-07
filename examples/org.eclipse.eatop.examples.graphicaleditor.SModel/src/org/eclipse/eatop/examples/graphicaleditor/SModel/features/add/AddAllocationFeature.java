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
package org.eclipse.eatop.examples.graphicaleditor.SModel.features.add;

import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

public class AddAllocationFeature extends AbstractAddShapeFeature {
	 private static final IColorConstant QRequirement_TEXT_FOREGROUND =
		        IColorConstant.BLACK;
		 
		    private static final IColorConstant QRequirement_FOREGROUND =
		    		new ColorConstant(98, 131, 167);
		    		
		    private static final IColorConstant QRequirement_BACKGROUND =
		    		new ColorConstant(212, 231, 248);


	public AddAllocationFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) 
	{
		if (context.getNewObject() instanceof Allocation) 
	{
			if (context.getTargetContainer().getLink().getBusinessObjects()
					.get(0) instanceof DesignLevel) 
	 {
				
				
				
				DesignLevel targetfm = (DesignLevel) context.getTargetContainer()
						.getLink().getBusinessObjects().get(0);
				// Any DesignLevel contains container of new Allocation
				// Systemmodels
				for (Shape cs : context.getTargetContainer().getContainer()
						.getContainer().getChildren()) {
					// Designlevel
					for (Shape cs2 : ((ContainerShape) cs).getChildren()) {
						if (cs2.getLink() != null) {
							if (cs2.getLink().getBusinessObjects().get(0) instanceof DesignLevel) {
								// Allocation
								for (Shape cs3 : ((ContainerShape) cs2)
										.getChildren()) {
									if (cs3.getLink() != null) {
										if (cs3.getLink().getBusinessObjects()
												.get(0) instanceof Allocation) {
											if (cs3.getLink().getBusinessObjects().get(0)
													.equals(context.getNewObject())) {
												return false;
										}}}}}}}}
				
				// Any DesignLevel contains business object of new Allocation
				// Systemmodels
				for (Shape cs : context.getTargetContainer().getContainer()
						.getContainer().getChildren()) {
					// Designlevel
					for (Shape cs2 : ((ContainerShape) cs).getChildren()) {
						if (cs2.getLink() != null) {
							if (cs2.getLink().getBusinessObjects().get(0) instanceof DesignLevel) {
								
								DesignLevel currentfm = (DesignLevel) cs2.getLink()
										.getBusinessObjects().get(0);
								if (currentfm.getAllocation().contains(
										context.getNewObject())
										)
									// Target DesignLevel contains business object of new
									// Allocation
									if (targetfm.getAllocation().contains(
											context.getNewObject())
											)
										return true;
							}}}}
				
	 }}
									
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		final Allocation addedQualReq = (Allocation) context.getNewObject();
		final ContainerShape targetDiagram = (ContainerShape) context.getTargetContainer();
		     
	        // CONTAINER SHAPE WITH ROUNDED RECTANGLE
	       final IPeCreateService peCreateService = Graphiti.getPeCreateService();
	      final  ContainerShape containerShape =
	             peCreateService.createContainerShape(targetDiagram, true);
	        
	        // check whether the context has a size (e.g. from a create feature)
	        // otherwise define a default size for the shape
	        final int width = context.getWidth() <= 0 ? 200 : context.getWidth();
	        final int height = context.getHeight() <= 0 ? 60 : context.getHeight();


	       final IGaService gaService = Graphiti.getGaService();
	        RoundedRectangle roundedRectangleQR; // need to access it later
	 
	        {
	            // create and set graphics algorithm
	        		
	             roundedRectangleQR =
	                gaService.createRoundedRectangle(containerShape, 5, 5);
	             roundedRectangleQR.setForeground(manageColor(QRequirement_FOREGROUND));
	             roundedRectangleQR.setBackground(manageColor(QRequirement_BACKGROUND));
	             roundedRectangleQR.setLineWidth(2);
	            gaService.setLocationAndSize(roundedRectangleQR,
	                context.getX(), context.getY(), width, height);
	 
	            // if added Class has no resource we add it to the resource 
	            // of the diagram
	            // in a real scenario the business model would have its own resource
	            if (addedQualReq.eResource() == null) {
	                     getDiagram().eResource().getContents().add(addedQualReq);
	            }
	            // create link and wire it
	            link(containerShape, addedQualReq);
	        }
	 
	        // SHAPE WITH LINE
	        {
	            // create shape for line
	            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
	 
	            // create and set graphics algorithm
	            Polyline polyline =
	                gaService.createPolyline((GraphicsAlgorithmContainer) shape, new int[] { 0, 25, 800, 25 });
	            polyline.setForeground(manageColor(QRequirement_FOREGROUND));
	            polyline.setLineWidth(2);
	        }
	       
	 
	        // SHAPE WITH ShortName 
	        {
	            // create shape for text
	            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
	 
	            // create and set text graphics algorithm
	            Text text = gaService.createText(shape, addedQualReq.getShortName());
	            text.setForeground(manageColor(QRequirement_TEXT_FOREGROUND));
	            text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT ); 
	            // vertical alignment has as default value "center"
	            text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
	            gaService.setLocationAndSize(text, 22, 12, width, 10);
	 
	            // create link and wire it
	            link(shape, addedQualReq);
	            
//	            //provide information to support direct-editing directly
//	            //after object creation (must be actived additionally)
//	            IDirectEditingInfo directEditingInfo=getFeatureProvider().getDirectEditingInfo();
//	            // set container shape for direct editing after object creation
//	            directEditingInfo.setMainPictogramElement(containerShape);
//	            //set container shape for direct and graphics algorithm where the editor for
//	            //direct editing shall be opened after object creation
//	            directEditingInfo.setPictogramElement(shape);
//	            directEditingInfo.setGraphicsAlgorithm(text);
	        }
	       //  SHAPE WITH TEXT for EAPackageType
	        {
	        	 // create shape for text  
	            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
	        	//add Text 
	             Text text1=gaService.createText(shape, addedQualReq.eClass().getName());
	             text1.setForeground(manageColor(QRequirement_TEXT_FOREGROUND));
	             text1.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT );
	             text1.setFont(gaService.manageDefaultFont(getDiagram(), true, false));
	             gaService.setLocationAndSize(text1, 22, 2, width, 10);
	             // create link and wire it
		            link(shape, addedQualReq);
	        }
	        // SHAPE WITH TEXT for TextEAPackage
	        {
	        	 // create shape for text  
	            Shape shape = (Shape) peCreateService.createShape(containerShape, false);
	        	//add Text 2
	             Text text2=gaService.createText(shape, addedQualReq.getName());
	             text2.setForeground(manageColor(QRequirement_TEXT_FOREGROUND));
	             text2.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT );
	             text2.setFont(gaService.manageDefaultFont(getDiagram(), true, false));
	             gaService.setLocationAndSize(text2, 5, 22, width, 25);
	             // create link and wire it
		            link(shape, addedQualReq);
	        }
	       
	        
	        
	     // SHAPE WITH TEXT for EAPackageKind
	        

	       
	        // add a chopbox anchor to the shape 
		      
	        peCreateService.createChopboxAnchor(containerShape);
	        
	        // call the layout feature
	        layoutPictogramElement(containerShape);

	    

	        return containerShape;
	}

}
