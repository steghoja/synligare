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

import org.eclipse.eatop.eastadl21.AnalysisLevel;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ImplementationLevel;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.create.CreateSystemModelFeature;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.impl.AddContext;
import org.eclipse.graphiti.features.impl.AbstractAddShapeFeature;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.ICreateService;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;

public class AddSystemModelFeature extends AbstractAddShapeFeature {
	private static final IColorConstant FeatureModel_TEXT_FOREGROUND = IColorConstant.BLACK;

	private static final IColorConstant FeatureModel_FOREGROUND = new ColorConstant(
			0, 0, 139);

	private static final IColorConstant FeatureModel_BACKGROUND = new ColorConstant(
			0, 191, 255);

	public static int sModelLock = 0;

	public AddSystemModelFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		// check if user wants to add a SystemModel
		if (context.getNewObject() instanceof SystemModel) {
			// check if user wants to add to a diagram
			if (context.getTargetContainer() instanceof Diagram) {

				return true;
			}
		}
		return false;
	}

	@Override
	public PictogramElement add(IAddContext context) {

		CreateSystemModelFeature.lock = 1;

		final SystemModel addedSystemModel = (SystemModel) context
				.getNewObject();
		final ContainerShape targetDiagram = (ContainerShape) context
				.getTargetContainer();

		// CONTAINER SHAPE WITH ROUNDED RECTANGLE
		final ICreateService peCreateService = Graphiti.getCreateService();
		final ContainerShape containerShape = peCreateService
				.createContainerShape(targetDiagram, true);

		// check whether the context has a size (e.g. from a create feature)
		// otherwise define a default size for the shape
		final int width = context.getWidth() <= 0 ? 300 : context.getWidth();
		final int height = context.getHeight() <= 0 ? 710 : context.getHeight();

		final IGaService gaService = Graphiti.getGaService();
		RoundedRectangle roundedRectangleQR; // need to access it later

		{
			// create and set graphics algorithm

			roundedRectangleQR = gaService.createRoundedRectangle(
					containerShape, 5, 5);
			roundedRectangleQR
					.setForeground(manageColor(FeatureModel_FOREGROUND));
			roundedRectangleQR
					.setBackground(manageColor(FeatureModel_BACKGROUND));
			roundedRectangleQR.setLineWidth(2);
			gaService.setLocationAndSize(roundedRectangleQR, context.getX(),
					context.getY(), width, height);

			// if added Class has no resource we add it to the resource
			// of the diagram
			// in a real scenario the business model would have its own resource
			if (addedSystemModel.eResource() == null) {
				getDiagram().eResource().getContents().add(addedSystemModel);
			}
			// create link and wire it
			link(containerShape, addedSystemModel);
		}

		{
			// create shape for text
			Shape shape = (Shape) peCreateService.createShape(containerShape,
					false);
			// add Text
			Text text1 = gaService.createText(shape, addedSystemModel.eClass()
					.getName());
			text1.setForeground(manageColor(FeatureModel_TEXT_FOREGROUND));
			text1.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
			text1.setFont(gaService
					.manageDefaultFont(getDiagram(), true, false));
			gaService.setLocationAndSize(text1, 22, 2, width, 10);
			// create link and wire it
			link(shape, addedSystemModel);
		}
		// SHAPE WITH ShortName
				{
					// create shape for text
					Shape shape = (Shape) peCreateService.createShape(containerShape,
							false);

					// create and set text graphics algorithm
					Text text = gaService.createText(shape,
							addedSystemModel.getShortName());
					text.setForeground(manageColor(FeatureModel_TEXT_FOREGROUND));
					text.setHorizontalAlignment(Orientation.ALIGNMENT_LEFT);
					// vertical alignment has as default value "center"
					text.setFont(gaService.manageDefaultFont(getDiagram(), false, true));
					gaService.setLocationAndSize(text, 22, 12, width, 10);

					// create link and wire it
					link(shape, addedSystemModel);

					// provide information to support direct-editing directly
					// after object creation (must be actived additionally)
					IDirectEditingInfo directEditingInfo = getFeatureProvider()
							.getDirectEditingInfo();
					// set container shape for direct editing after object creation
					directEditingInfo.setMainPictogramElement(containerShape);
					// set container shape for direct and graphics algorithm where the
					// editor for
					// direct editing shall be opened after object creation
					directEditingInfo.setPictogramElement(shape);
					directEditingInfo.setGraphicsAlgorithm(text);
				}

		// add a chopbox anchor to the shape

		peCreateService.createChopboxAnchor(containerShape);

		// call the layout feature
		layoutPictogramElement(containerShape);

		CreateSystemModelFeature.lock = 1;

		{
			VehicleLevel fp = Eastadl21Factory.eINSTANCE.createVehicleLevel();
			addedSystemModel.setVehicleLevel(fp);
			fp.setShortName("VehicleLevel");
			AddContext context2 = new AddContext();
			context2.setLocation(15, 30);
			context2.setTargetContainer((ContainerShape) containerShape);
			context2.setNewObject(fp);
			getFeatureProvider().addIfPossible(new AddContext(context2, fp));
		}

		{
			AnalysisLevel fp = Eastadl21Factory.eINSTANCE.createAnalysisLevel();
			addedSystemModel.setAnalysisLevel(fp);
			fp.setShortName("AnalysisLevel");
			AddContext context2 = new AddContext();
			context2.setLocation(15, 165);
			context2.setTargetContainer((ContainerShape) containerShape);
			context2.setNewObject(fp);
			getFeatureProvider().addIfPossible(new AddContext(context2, fp));
		}

		{
			DesignLevel fp = Eastadl21Factory.eINSTANCE.createDesignLevel();
			addedSystemModel.setDesignLevel(fp);
			fp.setShortName("DesignLevel");
			AddContext context2 = new AddContext();
			context2.setLocation(15, 300);
			context2.setTargetContainer((ContainerShape) containerShape);
			context2.setNewObject(fp);
			getFeatureProvider().addIfPossible(new AddContext(context2, fp));
		}

		{
			ImplementationLevel fp = Eastadl21Factory.eINSTANCE
					.createImplementationLevel();
			addedSystemModel.setImplementationLevel(fp);
			fp.setShortName("ImplementationLevel");
			AddContext context2 = new AddContext();
			context2.setLocation(15, 570);
			context2.setTargetContainer((ContainerShape) containerShape);
			context2.setNewObject(fp);
			getFeatureProvider().addIfPossible(new AddContext(context2, fp));
		}

		return containerShape;
	}

}
