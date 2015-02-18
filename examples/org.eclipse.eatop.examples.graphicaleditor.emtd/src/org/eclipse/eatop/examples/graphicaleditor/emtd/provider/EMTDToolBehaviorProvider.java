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
package org.eclipse.eatop.examples.graphicaleditor.emtd.provider;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.CommunicationHardwarePin;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.ErrorBehavior;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.IOHardwarePin;
import org.eclipse.eatop.eastadl21.InternalFaultPrototype;
import org.eclipse.eatop.eastadl21.PowerHardwarePin;
import org.eclipse.eatop.eastadl21.ProcessFaultPrototype;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;


public class EMTDToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public EMTDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}


	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof ErrorModelPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.ERROR_MODEL_PROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof AnalysisFunctionPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.ANALYSIS_FUNCTION_PROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof DesignFunctionPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.DESIGN_FUNCTION_PROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof HardwareComponentPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.HARDWARE_COMPONENT_PROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof IOHardwarePin) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.IO_HARDWARE_PIN);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof PowerHardwarePin) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.POWER_HARDWARE_PIN);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof CommunicationHardwarePin) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.COMMUNICATION_HARDWARE_PIN);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof FunctionFlowPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.FUNCTION_FLOW_PORT);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof FunctionPowerPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.FUNCTION_POWER_PORT);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof FunctionClientServerPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.FUNCTION_CLIENT_SERVER_PORT);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof ProcessFaultPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.PROCESS_FAULT_PROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof InternalFaultPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.INTERNAL_FAULT_PROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof FaultFailurePropagationLink) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.FAULT_FAILURE_PROPAGATION_LINK);
			return new IDecorator[] { imageRenderingDecorator };
		}
		if (bo instanceof ErrorBehavior) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EMTDImageProvider.ERROR_BEHAVIOR);
			return new IDecorator[] { imageRenderingDecorator };
		}

		return super.getDecorators(pe);
	}

	@Override
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {

		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof HardwareComponentPrototype
				|| bo instanceof FunctionPrototype
				|| bo instanceof FunctionPort
				|| bo instanceof HardwarePin) {
		
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
	
		} else {
			super.setGenericContextButtons(data, pe, identifiers);
		}
	}
}

