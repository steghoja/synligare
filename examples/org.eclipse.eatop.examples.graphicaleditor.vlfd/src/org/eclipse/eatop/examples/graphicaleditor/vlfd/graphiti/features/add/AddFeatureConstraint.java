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
package org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.add;

import org.eclipse.eatop.eastadl21.FeatureConstraint;
import org.eclipse.eatop.eastadl21.FeatureModel;
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


public class AddFeatureConstraint extends AbstractAddShapeFeature {
	private static final IColorConstant FeatureConstraint_TEXT_FOREGROUND = IColorConstant.BLACK;

	private static final IColorConstant FeatureConstraint_FOREGROUND = new ColorConstant(
			0, 0, 139);

	private static final IColorConstant FeatureConstraint_BACKGROUND = new ColorConstant(
			0, 191, 255);

	public AddFeatureConstraint(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		if (context.getTargetContainer().getLink().getBusinessObjects().get(0) instanceof FeatureModel) {
			FeatureModel fm = (FeatureModel) context.getTargetContainer()
					.getLink().getBusinessObjects().get(0);

			// Any FeatureModel contains container of new VehiceFeature or
			// FeatureConstraint
			// FeatureModels
			for (Shape cs : context.getTargetContainer().getContainer()
					.getChildren()) {
				// VehiceFeatures and FeatureConstraints
				for (Shape cs2 : ((ContainerShape) cs).getChildren()) {
					if (cs2.getLink() != null)
						if (cs2.getLink().getBusinessObjects().get(0)
								.equals(context.getNewObject())) {
							return false;
						}
				}
			}

			// Any FeatureModel contains business object of new VehiceFeature
			// or FeatureConstraint
			for (Shape cs : context.getTargetContainer().getContainer()
					.getChildren()) {
				FeatureModel currentfm = (FeatureModel) cs.getLink()
						.getBusinessObjects().get(0);
				if (currentfm.getFeatureConstraint().contains(
						context.getNewObject())
						|| currentfm.getRootFeature().contains(
								context.getNewObject()))
					// Target FeatureModel contains business object of new
					// VehiceFeature or FeatureConstraint
					if (fm.getFeatureConstraint().contains(
							context.getNewObject())
							|| (fm.getRootFeature().contains(context
									.getNewObject())))
						return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		final FeatureConstraint addedQualReq = (FeatureConstraint) context
				.getNewObject();
		final ContainerShape targetDiagram = (ContainerShape) context
				.getTargetContainer();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		final IPeCreateService peCreateService = Graphiti.getPeCreateService();
		final ContainerShape containerShape = peCreateService
				.createContainerShape(targetDiagram, true);

		// check whether the context has a size (e.g. from a create feature)
		// otherwise define a default size for the shape
		final int width = context.getWidth() <= 0 ? 100 : context.getWidth();
		final int height = context.getHeight() <= 0 ? 60 : context.getHeight();

		final IGaService gaService = Graphiti.getGaService();
		RoundedRectangle roundedRectangleQR; // need to access it later

		{
			// create and set graphics algorithm

			roundedRectangleQR = gaService.createRoundedRectangle(
					containerShape, 5, 5);
			roundedRectangleQR
					.setForeground(manageColor(FeatureConstraint_FOREGROUND));
			roundedRectangleQR
					.setBackground(manageColor(FeatureConstraint_BACKGROUND));
			roundedRectangleQR.setLineWidth(2);
			gaService.setLocationAndSize(roundedRectangleQR, context.getX(),
					context.getY(), width, height);

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
			Shape shape = (Shape) peCreateService.createShape(containerShape,
					false);

			// create and set graphics algorithm
			Polyline polyline = gaService.createPolyline(
					(GraphicsAlgorithmContainer) shape, new int[] { 0, 25,
							width, 25 });
			polyline.setForeground(manageColor(FeatureConstraint_FOREGROUND));
			polyline.setLineWidth(2);
		}

		// SHAPE WITH ShortName
		{
			// create shape for text
			Shape shape = (Shape) peCreateService.createShape(containerShape,
					false);

			// create and set text graphics algorithm
			Text text = gaService
					.createText(shape, addedQualReq.getShortName());
			text.setForeground(manageColor(FeatureConstraint_TEXT_FOREGROUND));
			text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			// vertical alignment has as default value "center"
			text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
			gaService.setLocationAndSize(text, 22, 12, width, 10);

			// create link and wire it
			link(shape, addedQualReq);

			// //provide information to support direct-editing directly
			// //after object creation (must be actived additionally)
			// IDirectEditingInfo
			// directEditingInfo=getFeatureProvider().getDirectEditingInfo();
			// // set container shape for direct editing after object creation
			// directEditingInfo.setMainPictogramElement(containerShape);
			// //set container shape for direct and graphics algorithm where the
			// editor for
			// //direct editing shall be opened after object creation
			// directEditingInfo.setPictogramElement(shape);
			// directEditingInfo.setGraphicsAlgorithm(text);
		}
		// SHAPE WITH TEXT for QualityRequirementType
		{
			// create shape for text
			Shape shape = (Shape) peCreateService.createShape(containerShape,
					false);
			// add Text
			Text text1 = gaService.createText(shape, addedQualReq.eClass()
					.getName());
			text1.setForeground(manageColor(FeatureConstraint_TEXT_FOREGROUND));
			text1.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text1.setFont(gaService
					.manageDefaultFont(getDiagram(), true, false));
			gaService.setLocationAndSize(text1, 22, 2, width, 10);
			// create link and wire it
			link(shape, addedQualReq);
		}

		// add a chopbox anchor to the shape

		peCreateService.createChopboxAnchor(containerShape);

		// call the layout feature
		layoutPictogramElement(containerShape);

		return containerShape;
	}

}
