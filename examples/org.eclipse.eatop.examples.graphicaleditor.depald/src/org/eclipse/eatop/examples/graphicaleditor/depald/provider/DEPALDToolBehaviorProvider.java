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
package org.eclipse.eatop.examples.graphicaleditor.depald.provider;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionalSafetyConcept;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDoubleClickContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;


public class DEPALDToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public DEPALDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
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
		if (bo instanceof Satisfy) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPALDImageProvider.IMAGE_SATISFY);
			return new IDecorator[] { imageRenderingDecorator };
		} 
		if (bo instanceof FunctionalSafetyConcept) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPALDImageProvider.IMAGE_FSC);
			return new IDecorator[] { imageRenderingDecorator };
		} if (bo instanceof Requirement) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPALDImageProvider.IMAGE_REQUIREMENT);
			return new IDecorator[] { imageRenderingDecorator };
		} if (bo instanceof AnalysisFunctionPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPALDImageProvider.IMAGE_ANALYSIS_FUNCTION_PROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		} 
		return super.getDecorators(pe);
	}

	
	@Override
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {

		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Requirement || bo instanceof FunctionPrototype) {
		
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
	
		} else if (bo instanceof FunctionalSafetyConcept
				|| bo instanceof Satisfy) {
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
