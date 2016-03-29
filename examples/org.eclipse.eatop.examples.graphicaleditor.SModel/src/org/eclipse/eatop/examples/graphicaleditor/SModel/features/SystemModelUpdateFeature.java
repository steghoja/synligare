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
package org.eclipse.eatop.examples.graphicaleditor.SModel.features;

import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisLevel;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.ImplementationLevel;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;


public class SystemModelUpdateFeature extends AbstractUpdateFeature {

	public SystemModelUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		// return true, if linked business object is a EClass
		Object bo = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		return (bo instanceof SystemModel 
				|| bo instanceof AnalysisLevel 
				|| bo instanceof DesignLevel 
				|| bo instanceof VehicleLevel 
				|| bo instanceof ImplementationLevel
				|| bo instanceof AnalysisFunctionPrototype
				|| bo instanceof DesignFunctionPrototype
				|| bo instanceof HardwareComponentPrototype
				|| bo instanceof Allocation);
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {

		// retrieve name from pictogram model
		String pictogramName1 = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) pictogramElement;
			for (Shape shape : cs.getChildren()) {
				if (shape.getGraphicsAlgorithm() instanceof Text
						&& shape == cs.getChildren().get(1)) {

					Text text = (Text) shape.getGraphicsAlgorithm();
					pictogramName1 = text.getValue();
				}
			}
		}

		// retrieve name from business model
		String businessName1 = null;

		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof SystemModel) {
			SystemModel sm = (SystemModel) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof DesignLevel) {
			DesignLevel sm = (DesignLevel) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof AnalysisLevel) {
			AnalysisLevel sm = (AnalysisLevel) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof VehicleLevel) {
			VehicleLevel sm = (VehicleLevel) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof ImplementationLevel) {
			ImplementationLevel sm = (ImplementationLevel) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof AnalysisFunctionPrototype) {
			AnalysisFunctionPrototype sm = (AnalysisFunctionPrototype) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof DesignFunctionPrototype) {
			DesignFunctionPrototype sm = (DesignFunctionPrototype) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof HardwareComponentPrototype) {
			HardwareComponentPrototype sm = (HardwareComponentPrototype) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof Allocation) {
			Allocation sm = (Allocation) bo;
			businessName1 = sm.getShortName();
		}

		// update needed, if names are different
		boolean updateNameNeeded = ((pictogramName1 == null && businessName1 != null) || (pictogramName1 != null && !pictogramName1
				.equals(businessName1)));

		if (updateNameNeeded) {
			return Reason.createTrueReason("Name is out of date");
		} else {
			return Reason.createFalseReason();
		}
	}

	@Override
	public boolean update(IUpdateContext context) {
		// retrieve name from business model
		String businessName1 = null;

		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof SystemModel) {
			SystemModel sm = (SystemModel) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof DesignLevel) {
			DesignLevel sm = (DesignLevel) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof AnalysisLevel) {
			AnalysisLevel sm = (AnalysisLevel) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof VehicleLevel) {
			VehicleLevel sm = (VehicleLevel) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof ImplementationLevel) {
			ImplementationLevel sm = (ImplementationLevel) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof AnalysisFunctionPrototype) {
			AnalysisFunctionPrototype sm = (AnalysisFunctionPrototype) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof DesignFunctionPrototype) {
			DesignFunctionPrototype sm = (DesignFunctionPrototype) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof HardwareComponentPrototype) {
			HardwareComponentPrototype sm = (HardwareComponentPrototype) bo;
			businessName1 = sm.getShortName();

		}
		if (bo instanceof Allocation) {
			Allocation sm = (Allocation) bo;
			businessName1 = sm.getShortName();

		}

		// Set name in pictogram model
		if (pictogramElement instanceof ContainerShape) {
			ContainerShape cs = (ContainerShape) pictogramElement;
			for (Shape shape : cs.getChildren()) {
				if (shape.getGraphicsAlgorithm() instanceof Text
						&& shape == cs.getChildren().get(1)) {

					Text text1 = (Text) shape.getGraphicsAlgorithm();
					text1.setValue(businessName1);
					return true;
				}

			}
		}

		return false;

	}

}
