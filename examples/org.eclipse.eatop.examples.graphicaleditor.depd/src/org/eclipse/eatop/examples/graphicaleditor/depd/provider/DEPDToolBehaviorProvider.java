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
package org.eclipse.eatop.examples.graphicaleditor.depd.provider;

import org.eclipse.eatop.eastadl21.FeatureFlaw;
import org.eclipse.eatop.eastadl21.FunctionBehavior;
import org.eclipse.eatop.eastadl21.FunctionTrigger;
import org.eclipse.eatop.eastadl21.Hazard;
import org.eclipse.eatop.eastadl21.HazardousEvent;
import org.eclipse.eatop.eastadl21.Item;
import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.ModeGroup;
import org.eclipse.eatop.eastadl21.OperationalSituation;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsRelationship;
import org.eclipse.eatop.eastadl21.SafetyGoal;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.eatop.eastadl21.VehicleFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.views.SafetyGoalTitleAreaDialogview;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.ui.PlatformUI;

public class DEPDToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public DEPDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		PictogramElement[] elements = context.getPictogramElements();
		PictogramElement clickElement = elements[0];
		 EObject clickBusinessObject = clickElement.getLink().getBusinessObjects().get(0);
		if(clickBusinessObject instanceof SafetyGoal)
		{
			TitleAreaDialog tad=new SafetyGoalTitleAreaDialogview(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell() , clickBusinessObject);
			tad.open();
			
		}

		return super.getDoubleClickFeature(context);
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof FeatureFlaw) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_FLAW_FEATURE);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof Hazard) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_HAZARD);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof HazardousEvent) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_HAZARDOUS_EVENT);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof Item) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_ITEM);
			return new IDecorator[] { imageRenderingDecorator };
		}

		else if (bo instanceof SafetyGoal) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_SAFETY_GOAL);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof Requirement) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_REQUIREMENT);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof UseCase) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_USE_CASE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		else if (bo instanceof OperationalSituation) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_OPERATIONAL_SITUATION);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof Mode) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_MODE);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof ModeGroup) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_MODE_GROUP);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof FunctionBehavior) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_FUNCTION_BEHAVIOR);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof FunctionTrigger) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_FUNCTION_TRIGGER);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof VehicleFeature) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					DEPDImageProvider.IMAGE_VEHICLE_FEATURE);
			return new IDecorator[] { imageRenderingDecorator };
		}
		return super.getDecorators(pe);
	}

	@Override
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {

		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof OperationalSituation
				|| bo instanceof UseCase
				|| bo instanceof Requirement
				|| bo instanceof Mode
				|| bo instanceof VehicleFeature
				|| bo instanceof RequirementsRelationship) {
		
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
	
		} else if (bo instanceof Item
				|| bo instanceof FeatureFlaw
				|| bo instanceof HazardousEvent
				|| bo instanceof Hazard
				|| bo instanceof SafetyGoal) {
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
