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
package org.eclipse.eatop.examples.graphicaleditor.reqd.provider;



import org.eclipse.eatop.eastadl21.FunctionalSafetyConcept;
import org.eclipse.eatop.eastadl21.OperationalSituation;
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.TechnicalSafetyConcept;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ContextEntryHelper;
import org.eclipse.graphiti.tb.DefaultToolBehaviorProvider;
import org.eclipse.graphiti.tb.IContextButtonEntry;
import org.eclipse.graphiti.tb.IContextButtonPadData;
import org.eclipse.graphiti.tb.IDecorator;
import org.eclipse.graphiti.tb.ImageDecorator;


public class REQDToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public REQDToolBehaviorProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
	    Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
	     if (bo instanceof QualityRequirement) {
	           IDecorator imageRenderingDecorator =
		                new ImageDecorator(
		                    REQDImageProvider.IMAGE_QualityREQUIREMENT);
		            return new IDecorator[] { imageRenderingDecorator };
		        	    }
	     else if (bo instanceof Requirement) {
	           IDecorator imageRenderingDecorator =
	                new ImageDecorator(
	                		REQDImageProvider.IMAGE_REQUIREMENT);
	            return new IDecorator[] { imageRenderingDecorator };
	        	    } 
	    else if (bo instanceof RequirementsModel) {
	           IDecorator imageRenderingDecorator =
		                new ImageDecorator(
		                		REQDImageProvider.IMAGE_REQUIREMENT_MODEL);
		            return new IDecorator[] { imageRenderingDecorator };
		        	    }
	    else if (bo instanceof UseCase) {
	     	           IDecorator imageRenderingDecorator =
	     		                new ImageDecorator(
	     		                		REQDImageProvider.IMAGE_USE_CASE);
	     		            return new IDecorator[] { imageRenderingDecorator };
	     		        	    }
	      
	       else if (bo instanceof OperationalSituation) {
  	           IDecorator imageRenderingDecorator =
  		                new ImageDecorator(
  		                		REQDImageProvider.IMAGE_OPERATIONAL_SITUATION);
  		            return new IDecorator[] { imageRenderingDecorator };
  		        	    }
	       else if (bo instanceof TechnicalSafetyConcept) {
  	           IDecorator imageRenderingDecorator =
  		                new ImageDecorator(
  		                		REQDImageProvider.IMAGE_TSC);
  		            return new IDecorator[] { imageRenderingDecorator };
  		        	    }
	       else if (bo instanceof FunctionalSafetyConcept) {
  	           IDecorator imageRenderingDecorator =
  		                new ImageDecorator(
  		                		REQDImageProvider.IMAGE_FSC);
  		            return new IDecorator[] { imageRenderingDecorator };
  		        	    }
	    return super.getDecorators(pe);
	}


	@Override
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {

		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof Requirement
				|| bo instanceof QualityRequirement
				|| bo instanceof UseCase
				|| bo instanceof OperationalSituation) {
		
			data.getGenericContextButtons().clear();

			// update button
			if ((identifiers & CONTEXT_BUTTON_UPDATE) != 0) {
				IContextButtonEntry updateButton = ContextEntryHelper.
						createDefaultUpdateContextButton(getFeatureProvider(), pe);
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
