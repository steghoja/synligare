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
package org.eclipse.eatop.examples.graphicaleditor.depemtpd.provider;

import org.eclipse.eatop.eastadl21.AnalysisFunctionType;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.OpenEMTDFeature;
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

public class DEPEMTPDToolBehaviorProvider extends DefaultToolBehaviorProvider 
{

	public DEPEMTPDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}
	
	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof ErrorModelType) 
		{
			IDecorator imageRenderingDecorator = new ImageDecorator(DEPEMTPDImageProvider.IMAGE_ERROR_MODEL_TYPE);
			return new IDecorator[] 
			{imageRenderingDecorator};} 
		if (bo instanceof AnalysisFunctionType) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPEMTPDImageProvider.IMAGE_ANALYSIS_FUNCTION_TYPE);
			return new IDecorator[] { imageRenderingDecorator };
		} if (bo instanceof DesignFunctionType) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPEMTPDImageProvider.IMAGE_DESIGN_FUNCTION_TYPE);
			return new IDecorator[] { imageRenderingDecorator };
		} if (bo instanceof HardwareComponentType) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPEMTPDImageProvider.IMAGE_HARDWARE_COMPONENT_TYPE);
			return new IDecorator[] { imageRenderingDecorator };
		} 
		return super.getDecorators(pe);		
	
	}

	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) 
	{
		ICustomFeature openEMTD = new OpenEMTDFeature(getFeatureProvider());
		if (openEMTD.canExecute(context)) {
			return openEMTD;
		}
		return super.getDoubleClickFeature(context);
	}
	
	@Override
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {

		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof FunctionType || bo instanceof FunctionPrototype
				|| bo instanceof HardwareComponentType) {
		
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
	
		} else if (bo instanceof ErrorModelType) {
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
