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
package org.eclipse.eatop.examples.graphicaleditor.vlfd.providers;

import org.eclipse.eatop.eastadl21.Claim;
import org.eclipse.eatop.eastadl21.FeatureConstraint;
import org.eclipse.eatop.eastadl21.FeatureLink;
import org.eclipse.eatop.eastadl21.FeatureModel;
import org.eclipse.eatop.eastadl21.FunctionalSafetyConcept;
import org.eclipse.eatop.eastadl21.Ground;
import org.eclipse.eatop.eastadl21.OperationalSituation;
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.TechnicalSafetyConcept;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.eatop.eastadl21.VehicleFeature;
import org.eclipse.eatop.eastadl21.Warrant;
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


public class VLDToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public VLDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);

		if (bo instanceof FeatureModel) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					VLDImageProvider.IMAGE_FeatureModel);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof VehicleFeature) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					VLDImageProvider.IMAGE_VehicleFeature);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof FeatureConstraint) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					VLDImageProvider.IMAGE_FeatureConstraint);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof FeatureLink) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					VLDImageProvider.IMAGE_FeatureLink);
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
		if (bo instanceof FeatureModel
				|| bo instanceof VehicleFeature
				|| bo instanceof FeatureConstraint) {
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
