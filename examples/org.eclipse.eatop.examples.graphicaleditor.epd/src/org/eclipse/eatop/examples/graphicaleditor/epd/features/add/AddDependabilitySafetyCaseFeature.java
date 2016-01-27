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
package org.eclipse.eatop.examples.graphicaleditor.epd.features.add;

import org.eclipse.eatop.eastadl21.Dependability;
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


public class AddDependabilitySafetyCaseFeature extends AbstractAddShapeFeature {
	private static final IColorConstant E_CLASS_TEXT_FOREGROUND = IColorConstant.BLACK;
	private static final IColorConstant E_CLASS_FOREGROUND = new ColorConstant(98, 131, 167);
    private static final IColorConstant E_CLASS_BACKGROUND = new ColorConstant(187, 218, 247);
	public static final IColorConstant COMPONENT_TYPE_COLOR_TEXT_FOREGROUND = new ColorConstant(0, 0, 0);
	public static final IColorConstant COMPONENT_TYPE_COLOR_FOREGROUND = new ColorConstant(98, 131, 167);
	public static final IColorConstant COMPONENT_TYPE_COLOR_BACKGROUND = new ColorConstant(212, 231, 248);
	public static final int COMPONENT_TYPE_WIDTH_DEFAULT = 200;
	public static final int COMPONENT_TYPE_HEIGHT_DEFAULT = 100;
	public static final int COMPONENT_TYPE_HEIGHT_UPPER_COMPARTMENT_DEFAULT = 20;
	public AddDependabilitySafetyCaseFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		if (context.getNewObject() instanceof Dependability) {
			if (context.getTargetContainer() instanceof Diagram) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		Dependability addedBusinessObject = (Dependability) context.getNewObject();
		Diagram targetDiagram = (Diagram) context.getTargetContainer();
	    //ContainerShape targetContainer=(ContainerShape)context.getTargetContainer();
		final IPeCreateService peCreateService = Graphiti.getPeCreateService();
		final  ContainerShape containerShape = peCreateService.createContainerShape(targetDiagram, true);
	        
		final int width = context.getWidth() <= 0 ? 150 : context.getWidth();
		final int height = context.getHeight() <= 0 ? 50 : context.getHeight();
		final IGaService gaService = Graphiti.getGaService();

		RoundedRectangle roundedRectangle;

		{
			roundedRectangle = gaService.createRoundedRectangle(containerShape,5, 5);
			roundedRectangle.setForeground(manageColor(E_CLASS_FOREGROUND));
			roundedRectangle.setBackground(manageColor(E_CLASS_BACKGROUND));
			roundedRectangle.setLineWidth(2);
			gaService.setLocationAndSize(roundedRectangle, context.getX(), context.getY(), width, height);

			if (addedBusinessObject.eResource() == null) {
				getDiagram().eResource().getContents().add(addedBusinessObject);
			}
			link(containerShape, addedBusinessObject);
		}
		
		{
			Shape shape = (Shape) peCreateService.createShape(containerShape, false);
			Polyline polyline = gaService.createPolyline((GraphicsAlgorithmContainer) shape, new int[] { 0, 25,width, 25 });
			polyline.setForeground(manageColor(E_CLASS_FOREGROUND));
			polyline.setLineWidth(2);
		}

		
	
		{
			Shape shape = (Shape) peCreateService.createShape(containerShape, false);

			Text text = gaService.createText(shape, addedBusinessObject.getShortName());
			text.setForeground(manageColor(E_CLASS_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text.setFont(gaService.manageDefaultFont(getDiagram(), true, false));
			gaService.setLocationAndSize(text, 22, 12, width, 10);

			link(shape, addedBusinessObject);

			IDirectEditingInfo directEditingInfo = getFeatureProvider() .getDirectEditingInfo();
			directEditingInfo.setMainPictogramElement(containerShape);
			directEditingInfo.setPictogramElement(shape);
			directEditingInfo.setGraphicsAlgorithm(text);
		}

		{
			Shape shape = (Shape) peCreateService.createShape(containerShape, false);
			Text text1 = gaService.createText(shape,addedBusinessObject.getName() );
			text1.setForeground(manageColor(E_CLASS_TEXT_FOREGROUND));
			text1.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text1.setFont(gaService .manageDefaultFont(getDiagram(), false, true));
			gaService.setLocationAndSize(text1, 22, 2, width, 10);
			link(shape, addedBusinessObject);
		}


		peCreateService.createChopboxAnchor(containerShape);
		layoutPictogramElement(containerShape);

		return containerShape;
	}

}
