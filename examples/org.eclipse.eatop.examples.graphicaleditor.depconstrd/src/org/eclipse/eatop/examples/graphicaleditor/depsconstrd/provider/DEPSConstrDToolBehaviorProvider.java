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
package org.eclipse.eatop.examples.graphicaleditor.depsconstrd.provider;

import org.eclipse.eatop.eastadl21.FaultFailure;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.InternalFaultPrototype;
import org.eclipse.eatop.eastadl21.ProcessFaultPrototype;
import org.eclipse.eatop.eastadl21.QuantitativeSafetyConstraint;
import org.eclipse.eatop.eastadl21.SafetyConstraint;
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


public class DEPSConstrDToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public DEPSConstrDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	
	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof FaultFailure) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPSConstrDImageProvider.FAULTFAILURE);
			return new IDecorator[] { imageRenderingDecorator };
		} if (bo instanceof QuantitativeSafetyConstraint) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPSConstrDImageProvider.QUANTITATIVESAFETYCONSTRAINT);
			return new IDecorator[] { imageRenderingDecorator };
		} if (bo instanceof SafetyConstraint) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPSConstrDImageProvider.SAFETYCONSTRAINT);
			return new IDecorator[] { imageRenderingDecorator };
		} if (bo instanceof InternalFaultPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPSConstrDImageProvider.INTERNALFAULTPROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		} if (bo instanceof ProcessFaultPrototype) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPSConstrDImageProvider.PROCESSFAULTPROTOTYPE);
			return new IDecorator[] { imageRenderingDecorator };
		} 
		return super.getDecorators(pe);
	}

	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
	/*	ICustomFeature openEMTD = new OpenEMTDFeature(getFeatureProvider());
		if (openEMTD.canExecute(context)) {
			return openEMTD;
		}*/
		return super.getDoubleClickFeature(context);
	}


	@Override
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {

		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof FaultFailure
				|| bo instanceof SafetyConstraint
				|| bo instanceof QuantitativeSafetyConstraint) {
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
			data.getGenericContextButtons().clear();
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
		}
	}

}
