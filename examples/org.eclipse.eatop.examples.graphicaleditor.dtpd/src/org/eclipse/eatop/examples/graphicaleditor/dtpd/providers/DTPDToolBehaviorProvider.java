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
package org.eclipse.eatop.examples.graphicaleditor.dtpd.providers;

import org.eclipse.eatop.eastadl21.BasicSoftwareFunctionType;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.FunctionalDevice;
import org.eclipse.eatop.eastadl21.FunctionalSafetyConcept;
import org.eclipse.eatop.eastadl21.HardwareFunctionType;
import org.eclipse.eatop.eastadl21.LocalDeviceManager;
import org.eclipse.eatop.eastadl21.OperationalSituation;
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.TechnicalSafetyConcept;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.eatop.examples.graphicaleditor.dtpd.features.OpenDTPDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.dtpd.features.OpenDTPDiagramFeature;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;

public class DTPDToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public DTPDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}
	
	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		
		ICustomFeature openDFTDDiagram = new OpenDTPDiagramFeature(getFeatureProvider());
		if (openDFTDDiagram.canExecute(context)) {
			return openDFTDDiagram;
		}
		ICustomFeature openDTPDDiagram = new OpenDTPDDiagramFeature(getFeatureProvider());
		if (openDTPDDiagram.canExecute(context)) {
			return openDTPDDiagram;
		}
		
		return super.getDoubleClickFeature(context);
	}
	

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof FunctionalDevice) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DTPDDImageProvider.IMAGE_FunctionalDevice);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof EAPackage) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DTPDDImageProvider.IMAGE_EAPackage);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof HardwareFunctionType) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DTPDDImageProvider.IMAGE_HardwareFunctionTypee);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof BasicSoftwareFunctionType) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DTPDDImageProvider.IMAGE_BasicSoftwareType);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof LocalDeviceManager) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DTPDDImageProvider.IMAGE_LocalDeviceManager);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof DesignFunctionType) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DTPDDImageProvider.IMAGE_DesignFunctionType);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof BasicSoftwareFunctionType) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DTPDDImageProvider.IMAGE_BasicSoftwareType);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof FunctionFlowPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DTPDDImageProvider.IMAGE_FunctionFlowPort);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof FunctionClientServerPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DTPDDImageProvider.IMAGE_FunctionClientServerPort);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof FunctionPowerPort) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DTPDDImageProvider.IMAGE_FunctionPowerPort);
			return new IDecorator[] { imageRenderingDecorator };
		}
		return super.getDecorators(pe);

	}

	@Override
	public String getToolTip(GraphicsAlgorithm ga) {
		PictogramElement pe = ga.getPictogramElement();
		Object bo = getFeatureProvider().getBusinessObjectForPictogramElement(
				pe);
		if (bo instanceof QualityRequirement) {
			String shortName = ((QualityRequirement) bo).getShortName();
			if (shortName != null && !shortName.isEmpty()) {
				return shortName;
			}
		} else if (bo instanceof Requirement) {
			String shortName = ((Requirement) bo).getShortName();
			if (shortName != null && !shortName.isEmpty()) {
				return shortName;
			}

		} else if (bo instanceof UseCase) {
			String shortName = ((UseCase) bo).getShortName();
			if (shortName != null && !shortName.isEmpty()) {
				return shortName;
			}

		} else if (bo instanceof OperationalSituation) {
			String shortName = ((OperationalSituation) bo).getShortName();
			if (shortName != null && !shortName.isEmpty()) {
				return shortName;
			}

		} else if (bo instanceof RequirementsModel) {
			String shortName = ((RequirementsModel) bo).getShortName();
			if (shortName != null && !shortName.isEmpty()) {
				return shortName;
			}

		} else if (bo instanceof FunctionalSafetyConcept) {
			String shortName = ((FunctionalSafetyConcept) bo).getShortName();
			if (shortName != null && !shortName.isEmpty()) {
				return shortName;
			}

		} else if (bo instanceof TechnicalSafetyConcept) {
			String shortName = ((FunctionalSafetyConcept) bo).getShortName();
			if (shortName != null && !shortName.isEmpty()) {
				return shortName;
			}

		} else if (bo instanceof FunctionPort) {
	        String shortName = ((FunctionPort)bo).getShortName();
	        String eclass = ((FunctionPort) bo).eClass().getName();
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
		if (bo instanceof FunctionType
				|| bo instanceof EAPackage) {
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
