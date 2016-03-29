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
package org.eclipse.eatop.examples.graphicaleditor.hctpd.features;

import java.util.List;

import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddCommunicationHardwarePinFeature;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.impl.LayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

public class FunctionTypeLayout extends AbstractLayoutFeature {

	public static final int MIN_HEIGHT = 100;
	public static final int MIN_WIDTH = 200;
	public static final int PORT_SIZE = 10;
	public static final int COMPONENT_TYPE_WIDTH_DEFAULT = 200;
	public static final int COMPONENT_TYPE_HEIGHT_DEFAULT = 100;
	public static final int COMPONENT_TYPE_HEIGHT_UPPER_COMPARTMENT_DEFAULT = 20;
	/** fixed text height. */
	public static final int TEXT_HEIGHT = 10;
	/** minimal width for the port container shapes. */
	public static final int MIN_CONTAINER_WIDTH = MIN_WIDTH
			+ AddCommunicationHardwarePinFeature.PORT_SIZE;
	/** minimal height for the port container shapes. */
	public static final int MIN_CONTAINER_HEIGHT = MIN_HEIGHT
			+ AddCommunicationHardwarePinFeature.PORT_SIZE;

	public FunctionTypeLayout(IFeatureProvider fp) {
		super(fp);
	}

	public boolean canLayout(ILayoutContext context) {
		// return true, if pictogram element is linked to a ComponentType
		PictogramElement pe = context.getPictogramElement();
		if (!(pe instanceof ContainerShape)) {
			return false;
		}
		EList<EObject> businessObjects = pe.getLink().getBusinessObjects();
		return businessObjects.get(0) instanceof HardwareComponentType;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean layout(final ILayoutContext context) {
		boolean anythingChanged = false;
		ContainerShape containerShape = (ContainerShape) context
				.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		IGaService gaService = Graphiti.getGaService();

		if (containerGa.getHeight() < MIN_HEIGHT) {
			containerGa.setHeight(MIN_HEIGHT);
			anythingChanged = true;
		}
		if (containerGa.getWidth() < MIN_WIDTH) {
			containerGa.setWidth(MIN_WIDTH);
			anythingChanged = true;
		}

		int containerWidth = containerGa.getWidth()
				- AddCommunicationHardwarePinFeature.PORT_SIZE;
		int containerHeight = containerGa.getHeight()
				- AddCommunicationHardwarePinFeature.PORT_SIZE;

		for (Shape shape : containerShape.getChildren()) {
			GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
			IDimension size = gaService.calculateSize(graphicsAlgorithm);
			if (containerWidth != size.getWidth()) {
				// Polyline inside
				if (graphicsAlgorithm instanceof Polyline) {
					Polyline polyline = (Polyline) graphicsAlgorithm;
					Point secondPoint = polyline.getPoints().get(1);
					Point newSecondPoint = gaService.createPoint(containerWidth
							+ AddCommunicationHardwarePinFeature.PORT_SIZE / 2,
							secondPoint.getY());
					polyline.getPoints().set(1, newSecondPoint);
					anythingChanged = true;
				} else if (!(graphicsAlgorithm instanceof Text)) {
					gaService.setWidth(graphicsAlgorithm, containerWidth);
					anythingChanged = true;
				}
				// Text inside
				if (graphicsAlgorithm instanceof Text) {
					Text text = (Text) graphicsAlgorithm;
					text.setX(PORT_SIZE / 2);
					text.setY(PORT_SIZE / 2);
					anythingChanged = true;
				} else {
					gaService.setWidth(graphicsAlgorithm, containerWidth);
					anythingChanged = true;
				}
			}
		}
		// container width initially of the invisible rectangle
		// now adjusted to the width of the normal inner rectangle
		for (GraphicsAlgorithm child : containerGa
				.getGraphicsAlgorithmChildren()) {
			gaService.setLocationAndSize(child,
					AddCommunicationHardwarePinFeature.PORT_SIZE / 2,
					AddCommunicationHardwarePinFeature.PORT_SIZE / 2,
					containerWidth, containerHeight);
			anythingChanged = true;
		}
		// layout ports to be in a valid position
		List<Anchor> anchors = containerShape.getAnchors();
		for (Anchor anchor : anchors) {
			if (anchor instanceof BoxRelativeAnchor) {
				super.getFeatureProvider().layoutIfPossible(
						new LayoutContext(anchor));
			}
		}
		return anythingChanged;
	}

	public boolean layout2(ILayoutContext context) {
		boolean anythingChanged = false;
		ContainerShape containerShape = (ContainerShape) context
				.getPictogramElement();
		GraphicsAlgorithm containerGa = containerShape.getGraphicsAlgorithm();
		// height
		if (containerGa.getHeight() < MIN_HEIGHT) {
			containerGa.setHeight(MIN_HEIGHT);
			anythingChanged = true;
		}
		// width
		if (containerGa.getWidth() < MIN_WIDTH) {
			containerGa.setWidth(MIN_WIDTH);
			anythingChanged = true;
		}

		int containerWidth = containerGa.getWidth();
		int containerHeigth = containerGa.getHeight();

		// internal rectangle is added first so will always be 0????
		Rectangle internalRectangle = (Rectangle) containerGa
				.getGraphicsAlgorithmChildren().get(0);
		// Rectangle rt = (Rectangle) containerGa;
		internalRectangle.setHeight(containerHeigth - 2 * PORT_SIZE);
		internalRectangle.setWidth(containerWidth - 2 * PORT_SIZE);

		for (Shape shape : containerShape.getChildren()) {
			GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
			IGaService gaService = Graphiti.getGaService();
			IDimension size = gaService.calculateSize(graphicsAlgorithm);
			if (containerWidth != size.getWidth()) {
				// Polyline inside
				if (graphicsAlgorithm instanceof Polyline) {
					Polyline polyline = (Polyline) graphicsAlgorithm;
					Point secondPoint = polyline.getPoints().get(1);
					Point newSecondPoint = gaService.createPoint(containerWidth
							- PORT_SIZE, secondPoint.getY());
					polyline.getPoints().set(1, newSecondPoint);
					anythingChanged = true;
				} else {
					gaService.setWidth(graphicsAlgorithm, containerWidth);
					anythingChanged = true;
				}
				// Rectangle inside
				if (graphicsAlgorithm instanceof Rectangle) {
					Rectangle rectangle = (Rectangle) graphicsAlgorithm;
					// TODO : fix that!
					rectangle.setHeight(40);
					rectangle.setWidth(40);
					anythingChanged = true;
				} else {
					gaService.setWidth(graphicsAlgorithm, containerWidth);
					anythingChanged = true;
				}
				// Text inside
				if (graphicsAlgorithm instanceof Text) {
					Text text = (Text) graphicsAlgorithm;
					text.setX(PORT_SIZE / 2);
					text.setY(PORT_SIZE / 2);
					anythingChanged = true;
				} else {
					gaService.setWidth(graphicsAlgorithm, containerWidth);
					anythingChanged = true;
				}
			}
		}
		return anythingChanged;
	}
}