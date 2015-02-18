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
package org.eclipse.eatop.examples.graphicaleditor.SModel.providers;

import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisLevel;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.FunctionalSafetyConcept;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.ImplementationLevel;
import org.eclipse.eatop.eastadl21.OperationalSituation;
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.TechnicalSafetyConcept;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.OpenDFADiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.OpenVLDDiagramFeature;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;

public class SMDToolBehaviorProvider extends DefaultToolBehaviorProvider {
	// OpenVLDDiagramFeature
	public SMDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		ICustomFeature openVLDDiagram = new OpenVLDDiagramFeature(getFeatureProvider());
		if (openVLDDiagram.canExecute(context)) {
			return openVLDDiagram;
		}
		ICustomFeature openDFADiagram = new OpenDFADiagramFeature(getFeatureProvider());
		if (openDFADiagram.canExecute(context)) {
			return openDFADiagram;
		}
		return super.getDoubleClickFeature(context);
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof SystemModel) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					SMDImageProvider.IMAGE_SystemModel);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof AnalysisLevel) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					SMDImageProvider.IMAGE_AnalysisLevel);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof DesignLevel) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					SMDImageProvider.IMAGE_DesignLevel);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof SystemModel) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					SMDImageProvider.IMAGE_SystemModel);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof VehicleLevel) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					SMDImageProvider.IMAGE_VehicleLevel);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof ImplementationLevel) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					SMDImageProvider.IMAGE_ImplementationLevel);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof AnalysisFunctionPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					SMDImageProvider.IMAGE_AnalysisFunctionPrototype);
			return new IDecorator[] { imageRenderingDecorator };

		} else if (bo instanceof DesignFunctionPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					SMDImageProvider.IMAGE_DesignFunctionPrototype);
			return new IDecorator[] { imageRenderingDecorator };

		} else if (bo instanceof HardwareComponentPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					SMDImageProvider.IMAGE_HardwareComponentPrototype);
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

		}
		return (String) super.getToolTip(ga);

	}
	
	@Override
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {

		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof SystemModel || bo instanceof FunctionPrototype
				|| bo instanceof HardwareComponentPrototype || bo instanceof Allocation) {
		
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
