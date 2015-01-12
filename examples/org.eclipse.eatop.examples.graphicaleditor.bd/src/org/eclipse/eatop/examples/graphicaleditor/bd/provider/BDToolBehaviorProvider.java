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
package org.eclipse.eatop.examples.graphicaleditor.bd.provider;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisFunctionType;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.FunctionBehavior;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionTrigger;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.ModeGroup;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;


public class BDToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public BDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}
	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		return super.getDoubleClickFeature(context);
	}
	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Mode) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.IMAGE_MODE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof ModeGroup) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.IMAGE_MODE_GROUP);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof FunctionBehavior) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.IMAGE_FUNCTION_BEHAVIOR);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof FunctionTrigger) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.IMAGE_FUNCTION_TRIGGER);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof AnalysisFunctionPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.ANALYSIS_FUNCTION_PROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof DesignFunctionPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.DESIGN_FUNCTION_PROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof AnalysisFunctionType) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.ANALYSIS_FUNCTION_TYPE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof DesignFunctionType) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.DESIGN_FUNCTION_TYPE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof FunctionFlowPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.FUNCTION_FLOW_PORT);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof FunctionPowerPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.FUNCTION_POWER_PORT);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof FunctionClientServerPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					BDImageProvider.FUNCTION_CLIENT_SERVER_PORT);
			return new IDecorator[] { imageRenderingDecorator };
		}
		return super.getDecorators(pe);
	}
	
	@Override
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {

		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof FunctionType || bo instanceof FunctionPrototype
				|| bo instanceof FunctionPort || bo instanceof Diagram) {
		
			data.getGenericContextButtons().clear();
			
			//delete button is not needed here
			
			// update button
			if ((identifiers & CONTEXT_BUTTON_UPDATE) != 0) {
				IContextButtonEntry updateButton = ContextEntryHelper.createDefaultUpdateContextButton(
						getFeatureProvider(), pe);
				if (updateButton != null) {
					data.getGenericContextButtons().add(updateButton);
				}
			}
	
			// remove button
			if ((identifiers & CONTEXT_BUTTON_REMOVE) != 0) {
				IContextButtonEntry removeButton = ContextEntryHelper.createDefaultRemoveContextButton(
						getFeatureProvider(), pe);
				if (removeButton != null) {
					data.getGenericContextButtons().add(removeButton);
				}
			}
	
		} else if (bo instanceof ModeGroup 
				|| bo instanceof Mode
				|| bo instanceof FunctionBehavior
				|| bo instanceof FunctionTrigger) {
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
