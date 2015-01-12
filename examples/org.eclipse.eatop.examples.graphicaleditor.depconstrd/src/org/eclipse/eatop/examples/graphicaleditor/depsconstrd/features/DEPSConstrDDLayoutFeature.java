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
package org.eclipse.eatop.examples.graphicaleditor.depsconstrd.features;

import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FaultFailure;
import org.eclipse.eatop.eastadl21.QuantitativeSafetyConstraint;
import org.eclipse.eatop.eastadl21.SafetyConstraint;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionInFlowPortFeature;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;


public class DEPSConstrDDLayoutFeature extends AbstractLayoutFeature {
	private static final int MIN_HEIGHT = 50;

	public static final int MIN_WIDTH = 200;
	public static final int PORT_SIZE = 10;
	public static final int COMPONENT_TYPE_WIDTH_DEFAULT = 200;
	public static final int COMPONENT_TYPE_HEIGHT_DEFAULT = 100;
	public static final int COMPONENT_TYPE_HEIGHT_UPPER_COMPARTMENT_DEFAULT = 20;
	/** fixed text height. */
	public static final int TEXT_HEIGHT = 10;
	/** minimal width for the port container shapes. */
	public static final int MIN_CONTAINER_WIDTH = MIN_WIDTH + AddFunctionInFlowPortFeature.PORT_SIZE;
	/** minimal height for the port container shapes. */
	public static final int MIN_CONTAINER_HEIGHT = MIN_HEIGHT + AddFunctionInFlowPortFeature.PORT_SIZE;

   
	public DEPSConstrDDLayoutFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canLayout(ILayoutContext context) {
		  PictogramElement pe = context.getPictogramElement();
	       if (!(pe instanceof ContainerShape))
	           return false;
	       EList<EObject> businessObjects = pe.getLink().getBusinessObjects();
	       
		return businessObjects.size() == 1 
	              && businessObjects.get(0) instanceof SafetyConstraint
	            	        || businessObjects.get(0) instanceof QuantitativeSafetyConstraint
	            	        ||businessObjects.get(0) instanceof FaultFailure
	            	        ||businessObjects.get(0) instanceof ErrorModelPrototype;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean anythingChanged = false;
        ContainerShape containerShape =
            (ContainerShape) context.getPictogramElement();
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
        
        for (Shape shape : containerShape.getChildren()){
            GraphicsAlgorithm graphicsAlgorithm = shape.getGraphicsAlgorithm();
            IGaService gaService = Graphiti.getGaService();
            IDimension size = 
                 gaService.calculateSize(graphicsAlgorithm);
            if (containerWidth != size.getWidth()) {
                if (graphicsAlgorithm instanceof Polyline) {
                    Polyline polyline = (Polyline) graphicsAlgorithm;
                    Point secondPoint = polyline.getPoints().get(1);
                    Point newSecondPoint =
                        gaService.createPoint(containerWidth, secondPoint.getY()); 
                    polyline.getPoints().set(1, newSecondPoint);
                    anythingChanged = true;
                } else {
                    gaService.setWidth(graphicsAlgorithm,
                        containerWidth);
                    anythingChanged = true;
                }
            }
        }
        return anythingChanged;
	}
}