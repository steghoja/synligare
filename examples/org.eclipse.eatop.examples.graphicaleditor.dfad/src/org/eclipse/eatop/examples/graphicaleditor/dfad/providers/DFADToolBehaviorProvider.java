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
package org.eclipse.eatop.examples.graphicaleditor.dfad.providers;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionAllocation;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionalSafetyConcept;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.OperationalSituation;
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.TechnicalSafetyConcept;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.eatop.examples.graphicaleditor.dfad.features.OpenAFTDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.dfad.features.OpenATPDDiagramFeature;
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

public class DFADToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public DFADToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}
	
	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		
		ICustomFeature openAFTDDiagram = new OpenAFTDiagramFeature(getFeatureProvider());
		if (openAFTDDiagram.canExecute(context)) {
			return openAFTDDiagram;
		}
		ICustomFeature openATPDDiagram = new OpenATPDDiagramFeature(getFeatureProvider());
		if (openATPDDiagram.canExecute(context)) {
			return openATPDDiagram;
		}
		
		return super.getDoubleClickFeature(context);
	}
	

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof FunctionAllocation) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DFADImageProvider.IMAGE_FunctionAllocation);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof AnalysisFunctionPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DFADImageProvider.IMAGE_AnalysisFunctionPrototype);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof DesignFunctionPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DFADImageProvider.IMAGE_DesignFunctionPrototype);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof HardwareComponentPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DFADImageProvider.IMAGE_HardwareComponentPrototype);
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
		if (bo instanceof HardwareComponentPrototype
				|| bo instanceof DesignFunctionPrototype) {
		
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
