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
package org.eclipse.eatop.examples.graphicaleditor.ed.port;

import org.eclipse.eatop.eastadl21.ClientServerKind;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.tutorial.StyleUtil;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class AddFunctionClientServerPortFeature extends AbstractAddShapeFeature {
	public String direction ;
	public static final int PORT_SIZE = 10;
	
	private static final IColorConstant E_REFERENCE_FOREGROUND = new ColorConstant(98, 131, 167);

	public AddFunctionClientServerPortFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a FunctionClientServerPort
		if (context.getNewObject() instanceof FunctionClientServerPort) {
			// check if user wants to add to a diagram
			ContainerShape container = context.getTargetContainer();
        	PictogramLink link = container.getLink();
        	if (link == null)
        	{
        		return false;
        	}
        	EList<EObject> bo = link.getBusinessObjects();
        	if(bo.isEmpty())
        	{
        		return false;
        	}
        	if(bo.get(0) instanceof FunctionPrototype)
        	{
        		return true;
        	}
		}
		return false;
	}

//	@Override
//	public PictogramElement add(IAddContext context) {
//		FunctionClientServerPort portToAdd = (FunctionClientServerPort) context.getNewObject();
//		ContainerShape parentContainer = context.getTargetContainer();
//		PictogramLink link = parentContainer.getLink();
//
//		EObject parentBO = null;
//		PictogramElement newElement = null;
//		// Does the context come from Drag & Drop ? see: BasicGraphitiObjectTransferDropTargetListener
//		if (context.getProperty("container") != null) { //$NON-NLS-1$
//			parentBO = (EObject) context.getProperty("container"); //$NON-NLS-1$
//			String fragment = EcoreUtil.getURI(portToAdd).fragment();
//			if (DiagramUtil.getEObject(parentBO, fragment) != null) {
//				// the object already exist (Drag & Drop)
//				EList<Shape> children = context.getTargetContainer().getChildren();
//				for (Shape candidateShape : children) {
//					if (candidateShape.getLink().getBusinessObjects().get(0) != null) {
//						EObject candidateObject = candidateShape.getLink().getBusinessObjects().get(0);
//						if (EcoreUtil.equals(candidateObject, parentBO)) {
//							// Get eObject2 shape
//							newElement = createBoundPort((ContainerShape) candidateShape, 0, 0 , context);
//							link(newElement, context.getNewObject());
//							break;
//						}
//					}
//				}
//			}
//		} else {
//			// Get the parent Business Object
//			parentBO = link.getBusinessObjects().get(0);
//			// Get the reference Id
//			
//			EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getFunctionType_Port();
//			// Add object to BO resource
//			DiagramUtil.addObjectToBOResource(parentBO, referenceId, portToAdd);
//			if (!(context.getTargetContainer() instanceof Diagram)) {
//				newElement = createBoundPort(context.getTargetContainer(), context.getX(), context.getY() , context);
//				link(newElement, context.getNewObject());
//			}
//		}
//		return newElement;
//	}
	


	@Override
	public PictogramElement add(IAddContext context) {
		PictogramElement newElement = null;
		newElement = createBoundPort(context.getTargetContainer(), context.getX(), context.getY(), context);
		link(newElement, context.getNewObject());
		return newElement;
	}
	
	private PictogramElement createBoundPort(final ContainerShape container, final int xpos, final int ypos , IAddContext context) {
		int nodeWidth = container.getGraphicsAlgorithm().getWidth();
		int nodeHeight = container.getGraphicsAlgorithm().getHeight();
		float widthPercent = (float) (xpos - 2) / nodeWidth;
		float heightPercent = (float) (ypos - 2) / nodeHeight;
		float deltaY = heightPercent < 1.0f / 2.0f ? heightPercent : 1 - heightPercent;
		float deltaX = widthPercent < 1.0f / 2.0f ? widthPercent : 1 - widthPercent;
		if (deltaY < deltaX) {
			heightPercent = Math.round(heightPercent);
		} else {
			widthPercent = Math.round(widthPercent);
		}
		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		BoxRelativeAnchor boxAnchor = peCreateService.createBoxRelativeAnchor(container);
		boxAnchor.setRelativeWidth(widthPercent);
		boxAnchor.setRelativeHeight(heightPercent);
		boxAnchor.setActive(true);
		FunctionClientServerPort functionClientServerPort = (FunctionClientServerPort) context.getNewObject();
		IGaService gaService = Graphiti.getGaService();
		
		if( functionClientServerPort.getKind() == ClientServerKind.CLIENT)
		{
			direction = "FCSP : C";
		}
		if( functionClientServerPort.getKind() == ClientServerKind.SERVER)
		{
			direction = "FCSP : S";
		}
		
		{
			// create shape for text
			
			final Shape shape = peCreateService.createShape(context.getTargetContainer(), true);
			
			// create and set text graphics algorithm
			final Text text = gaService.createPlainText(shape, direction +" : "+ functionClientServerPort.getShortName());
			text.setStyle(StyleUtil.getStyleForEClassText(getDiagram()));
			gaService.setLocationAndSize(text, context.getX(), context.getY(), 60, 20);
			
			AddContext textContext = new AddContext();
			textContext.setNewObject(text);
			textContext.setTargetContainer(context.getTargetContainer());
			getFeatureProvider().addIfPossible(
					new AddContext(context, textContext));
						
			Anchor textanchor = peCreateService.createChopboxAnchor(shape);
			Connection connection = peCreateService.createFreeFormConnection(getDiagram());
		    connection.setStart(boxAnchor);
		    connection.setEnd(textanchor);
		    

			
			Polyline polyline = gaService.createPolyline(connection);
	        polyline.setLineStyle(LineStyle.DASH);
	        polyline.setLineWidth(1);
	        polyline.setForeground(manageColor(E_REFERENCE_FOREGROUND));
	        
		
			// create link and wire it
			link(shape, context.getTargetContainer());

			// provide information to support direct-editing directly
			// after object creation (must be activated additionally)
			final IDirectEditingInfo directEditingInfo = getFeatureProvider().getDirectEditingInfo();
			// set container shape for direct editing after object creation
			directEditingInfo.setMainPictogramElement(context.getTargetContainer());
			// set shape and graphics algorithm where the editor for
			// direct editing shall be opened after object creation
			directEditingInfo.setPictogramElement(shape);
			directEditingInfo.setGraphicsAlgorithm(text);
		}
		
		// look for the actual rectangle that represents the parent entity
		for (GraphicsAlgorithm ga : container.getGraphicsAlgorithm().getGraphicsAlgorithmChildren()) {
			if (ga instanceof Rectangle) {
				boxAnchor.setReferencedGraphicsAlgorithm(ga);
				break;
			}
		}
		Rectangle rectangleShape = gaService.createRectangle(boxAnchor);
		gaService.setLocationAndSize(rectangleShape, -PORT_SIZE / 2, -PORT_SIZE / 2, PORT_SIZE, PORT_SIZE);
		return boxAnchor;
	}

}
