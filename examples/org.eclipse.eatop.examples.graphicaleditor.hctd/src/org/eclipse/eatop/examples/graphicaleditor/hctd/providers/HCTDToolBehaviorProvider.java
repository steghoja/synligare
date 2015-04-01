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
package org.eclipse.eatop.examples.graphicaleditor.hctd.providers;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;


public class HCTDToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public HCTDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof FunctionFlowPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					HCTDImageProvider.IMAGE_FunctionFlowPort);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof FunctionClientServerPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					HCTDImageProvider.IMAGE_FunctionClientServerPort);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof FunctionPowerPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					HCTDImageProvider.IMAGE_FunctionPowerPort);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof AnalysisFunctionPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					HCTDImageProvider.IMAGE_AnalysisFunctionPrototype);
			return new IDecorator[] { imageRenderingDecorator };
		}
		return super.getDecorators(pe);

	}

	@Override
	public String getToolTip(GraphicsAlgorithm ga) {
		PictogramElement pe = ga.getPictogramElement();
		Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(
				pe);
		if (bo instanceof HardwarePin) {
			String shortName = ((HardwarePin) bo).getShortName();
			String eclass = ((HardwarePin) bo).eClass().getName();
			if (shortName != null && !shortName.isEmpty()) {
				String toolTip = shortName + " : " + eclass;
				return toolTip;
			}

		}
		return (String) super.getToolTip(ga);
	}
	
	
	@Override
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {

		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof HardwareComponentPrototype) {
			data.getGenericContextButtons().clear();
			// update button
			if ((identifiers & CONTEXT_BUTTON_UPDATE) != 0) {
				IContextButtonEntry updateButton = ContextEntryHelper.createDefaultUpdateContextButton(
						getFeatureProvider(), pe);
				if (updateButton != null) {
					data.getGenericContextButtons().add(updateButton);
				}
			}
	
			// delete button
			if ((identifiers & CONTEXT_BUTTON_DELETE) != 0) {
				IContextButtonEntry deleteButton = ContextEntryHelper.createDefaultDeleteContextButton(
						getFeatureProvider(), pe);
				if (deleteButton != null) {
					data.getGenericContextButtons().add(deleteButton);
				}
			}
		} else {
			super.setGenericContextButtons(data, pe, identifiers);
		}
	}

}
