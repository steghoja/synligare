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
package org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add;

import org.eclipse.eatop.eastadl21.FunctionalDevice;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
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
//import org.eclipse.sphinx.examples.hummingbird20.typemodel.ComponentType;
//import org.eclipse.sphinx.examples.hummingbird20.typemodel.TypeModel20Package;

public class AddFunctionalDeviceFeature extends AbstractAddShapeFeature {
	private static final IColorConstant QRequirement_TEXT_FOREGROUND = IColorConstant.BLACK;

	public static final int INVISIBLE_RECT_RIGHT = 6;

	public static final IColorConstant COMPONENT_TYPE_COLOR_TEXT_FOREGROUND = new ColorConstant(
			0, 0, 0);
	public static final IColorConstant COMPONENT_TYPE_COLOR_FOREGROUND = new ColorConstant(
			98, 131, 167);
	public static final IColorConstant COMPONENT_TYPE_COLOR_BACKGROUND = new ColorConstant(
			212, 231, 248);
	public static final int COMPONENT_TYPE_WIDTH_DEFAULT = 200;
	public static final int COMPONENT_TYPE_HEIGHT_DEFAULT = 100;
	public static final int COMPONENT_TYPE_HEIGHT_UPPER_COMPARTMENT_DEFAULT = 20;
	public static final int PORT_SIZE = 10;

	public AddFunctionalDeviceFeature(IFeatureProvider fp) {

		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a FunctionalDevice
		if (context.getNewObject() instanceof FunctionalDevice) {
			// check if user wants to add to a diagram
			if (context.getTargetContainer() instanceof Diagram) {
				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		FunctionalDevice componentTypeToAdd = (FunctionalDevice) context
				.getNewObject();
		Diagram targetContainer = (Diagram) context.getTargetContainer();

		final int width = context.getWidth() <= 0 ? 200 : context.getWidth();

		IPeCreateService peCreateService = Graphiti.getPeCreateService();
		IGaService gaService = Graphiti.getGaService();
		// VISIBLE RECTANGLE INSIDE INVISIBLE RECTANGLE
		ContainerShape containerShape = peCreateService.createContainerShape(
				targetContainer, true);
		{
			// Create shape for invisible rectangle
			Rectangle invisible = gaService
					.createInvisibleRectangle(containerShape);
			gaService.setLocationAndSize(invisible, context.getX(),
					context.getY(), COMPONENT_TYPE_WIDTH_DEFAULT,
					COMPONENT_TYPE_HEIGHT_DEFAULT);
			// INTERNAL RECTANGLE
			// Create shape for rectangle
			Rectangle rectangle = gaService.createRectangle(invisible);
			rectangle
					.setForeground(manageColor(COMPONENT_TYPE_COLOR_FOREGROUND));
			rectangle
					.setBackground(manageColor(COMPONENT_TYPE_COLOR_BACKGROUND));
			rectangle.setLineWidth(2);
			gaService.setLocationAndSize(rectangle, PORT_SIZE, PORT_SIZE,
					COMPONENT_TYPE_WIDTH_DEFAULT - PORT_SIZE / 2,
					COMPONENT_TYPE_HEIGHT_DEFAULT - PORT_SIZE / 2);
			// Create link and wire it up
			link(containerShape, componentTypeToAdd);
		}
		// SHAPE WITH LINE
		{
			// Create shape for line
			Shape lineShape = peCreateService
					.createShape(containerShape, false);
			Polyline polyline = gaService.createPolyline(lineShape,
					new int[] {
							PORT_SIZE / 2,
							COMPONENT_TYPE_HEIGHT_UPPER_COMPARTMENT_DEFAULT
									+ PORT_SIZE,
							COMPONENT_TYPE_WIDTH_DEFAULT - PORT_SIZE / 2,
							COMPONENT_TYPE_HEIGHT_UPPER_COMPARTMENT_DEFAULT
									+ PORT_SIZE });
			polyline.setForeground(manageColor(COMPONENT_TYPE_COLOR_FOREGROUND));
			polyline.setLineWidth(2);
		}
		// SHAPE WITH TEXT
		{
			// Create shape for text
			Shape textShape = peCreateService
					.createShape(containerShape, false);
			// Create and set text graphics algorithm
			Text text = gaService.createText(textShape,
					componentTypeToAdd.getShortName());
			text.setForeground(manageColor(COMPONENT_TYPE_COLOR_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setVerticalAlignment(Orientation.ALIGNMENT_CENTER);
			text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
			gaService
					.setLocationAndSize(text, PORT_SIZE / 2, PORT_SIZE / 2,
							COMPONENT_TYPE_WIDTH_DEFAULT - PORT_SIZE / 2,
							COMPONENT_TYPE_HEIGHT_UPPER_COMPARTMENT_DEFAULT
									+ PORT_SIZE);
			// Create link and wire it up
			link(textShape, componentTypeToAdd);
		}

		// SHAPE WITH TEXT for EAPackageType
		{
			// create shape for text
			Shape shape = (Shape) peCreateService.createShape(containerShape,
					false);
			// add Text
			Text text1 = gaService.createText(shape, componentTypeToAdd
					.eClass().getName());
			text1.setForeground(manageColor(QRequirement_TEXT_FOREGROUND));
			text1.setHorizontalAlignment(Orientation.ALIGNMENT_CENTER);
			text1.setFont(gaService
					.manageDefaultFont(getDiagram(), true, false));
			gaService.setLocationAndSize(text1, 30, 2, width, 10);
			// create link and wire it
			link(shape, componentTypeToAdd);
		}
		// call the layout feature
		layoutPictogramElement(containerShape);
		// Create Chopbox anchor
		peCreateService.createChopboxAnchor(containerShape);
		return containerShape;

	}
}
