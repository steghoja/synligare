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

import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;


public class AddDesignLevelFeature extends AbstractAddShapeFeature {
	private static final IColorConstant DesignLevel_TEXT_FOREGROUND = IColorConstant.BLACK;

	private static final IColorConstant DesignLevel_FOREGROUND = new ColorConstant(
			0, 0, 139);

	private static final IColorConstant DesignLevel_BACKGROUND = new ColorConstant(
			0, 191, 255);

	public AddDesignLevelFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a DesignLevel
		if (context.getNewObject() instanceof DesignLevel) {
			// check if user wants to add to a diagram
			ContainerShape container = context.getTargetContainer();
			PictogramLink link = container.getLink();
			EList<EObject> bo = link.getBusinessObjects();
			if (bo.get(0) instanceof SystemModel) {
				ContainerShape diaContainer = context.getTargetContainer()
						.getContainer();
				for (Shape smC : diaContainer.getChildren())
					if (smC.getLink() != null)
						if (smC.getLink().getBusinessObjects().get(0) instanceof SystemModel)
							for (Shape s : ((ContainerShape) smC).getChildren())
								if (s.getLink() != null)
									if (s.getLink().getBusinessObjects().get(0) instanceof DesignLevel)
										if (s.getLink().getBusinessObjects()
												.get(0)
												.equals(context.getNewObject()))
											return false;

				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		final DesignLevel addedDesignLevel = (DesignLevel) context
				.getNewObject();
		final ContainerShape targetDiagram = (ContainerShape) context
				.getTargetContainer();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		final IPeCreateService peCreateService = Graphiti.getPeCreateService();
		final ContainerShape containerShape = peCreateService
				.createContainerShape(targetDiagram, true);

		// check whether the context has a size (e.g. from a create feature)
		// otherwise define a default size for the shape
		final int width = context.getWidth() <= 0 ? 270 : context.getWidth();
		final int height = context.getHeight() <= 0 ? 260 : context.getHeight();

		final IGaService gaService = Graphiti.getGaService();
		RoundedRectangle roundedRectangleQR; // need to access it later

		{
			// create and set graphics algorithm

			roundedRectangleQR = gaService.createRoundedRectangle(
					containerShape, 5, 5);
			roundedRectangleQR
					.setForeground(manageColor(DesignLevel_FOREGROUND));
			roundedRectangleQR
					.setBackground(manageColor(DesignLevel_BACKGROUND));
			roundedRectangleQR.setLineWidth(2);
			gaService.setLocationAndSize(roundedRectangleQR, context.getX(),
					context.getY(), width, height);

			// if added Class has no resource we add it to the resource
			// of the diagram
			// in a real scenario the business model would have its own resource
			if (addedDesignLevel.eResource() == null) {
				getDiagram().eResource().getContents().add(addedDesignLevel);
			}
			// create link and wire it
			link(containerShape, addedDesignLevel);
		}

		// SHAPE WITH LINE
		{
			// create shape for line
			Shape shape = (Shape) peCreateService.createShape(containerShape,
					false);

			// create and set graphics algorithm
			Polyline polyline = gaService.createPolyline(
					(GraphicsAlgorithmContainer) shape, new int[] { 0, 25,
							width, 25 });
			polyline.setForeground(manageColor(DesignLevel_FOREGROUND));
			polyline.setLineWidth(2);
		}

		// SHAPE WITH ShortName
		{
			// create shape for text
			Shape shape = (Shape) peCreateService.createShape(containerShape,
					false);

			// create and set text graphics algorithm
			Text text = gaService.createText(shape,
					addedDesignLevel.getShortName());
			text.setForeground(manageColor(DesignLevel_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			// vertical alignment has as default value "center"
			text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
			gaService.setLocationAndSize(text, 22, 12, width, 10);

			// create link and wire it
			link(shape, addedDesignLevel);
		}
		// SHAPE WITH TEXT for DesignLevelType
		{
			// create shape for text
			Shape shape = (Shape) peCreateService.createShape(containerShape,
					false);
			// add Text
			Text text1 = gaService.createText(shape, addedDesignLevel.eClass()
					.getName());
			text1.setForeground(manageColor(DesignLevel_TEXT_FOREGROUND));
			text1.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text1.setFont(gaService
					.manageDefaultFont(getDiagram(), true, false));
			gaService.setLocationAndSize(text1, 22, 2, width, 10);
			// create link and wire it
			link(shape, addedDesignLevel);
		}
		// SHAPE WITH TEXT for TextDesignLevel
		{
			// create shape for text
			Shape shape = (Shape) peCreateService.createShape(containerShape,
					false);
			// add Text 2
			Text text2 = gaService
					.createText(shape, addedDesignLevel.getName());
			text2.setForeground(manageColor(DesignLevel_TEXT_FOREGROUND));
			text2.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text2.setFont(gaService
					.manageDefaultFont(getDiagram(), true, false));
			gaService.setLocationAndSize(text2, 5, 22, width, 25);
			// create link and wire it
			link(shape, addedDesignLevel);
		}

		// SHAPE WITH TEXT for DesignLevelKind

		// add a chopbox anchor to the shape

		peCreateService.createChopboxAnchor(containerShape);

		// call the layout feature
		layoutPictogramElement(containerShape);

		return containerShape;
	}

}
