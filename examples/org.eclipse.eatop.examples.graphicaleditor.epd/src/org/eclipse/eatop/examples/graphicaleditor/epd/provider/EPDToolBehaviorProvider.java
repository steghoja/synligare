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
package org.eclipse.eatop.examples.graphicaleditor.epd.provider;


import org.eclipse.eatop.eastadl21.Behavior;
import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Environment;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenATPDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenBDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenDEPALDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenDEPDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenDEPDLDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenDEPEMTPDFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenDEPSCDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenDEPSConstrDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenDTPDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenEDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenEPDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenHCTPDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenREQDDiagramFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.OpenSMDDiagramFeature;
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

public class EPDToolBehaviorProvider extends DefaultToolBehaviorProvider {

	public EPDToolBehaviorProvider( IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public ICustomFeature getDoubleClickFeature(IDoubleClickContext context) {
		ICustomFeature openEPDDiagram = new OpenEPDDiagramFeature(getFeatureProvider());
		if (openEPDDiagram.canExecute(context)) {
			return openEPDDiagram;
		}
		ICustomFeature openREQDiagram=new OpenREQDDiagramFeature(getFeatureProvider());
		if (openREQDiagram.canExecute(context)) {
			return openREQDiagram;
		}
		ICustomFeature openHCTDiagram=new OpenHCTPDiagramFeature(getFeatureProvider());
		if (openHCTDiagram.canExecute(context)) {
			return openHCTDiagram;
		}
		ICustomFeature openSMDDiagram=new OpenSMDDiagramFeature(getFeatureProvider());
		if (openSMDDiagram.canExecute(context)) {
			return openSMDDiagram;
		}
		ICustomFeature openATPDDiagram=new OpenATPDDiagramFeature(getFeatureProvider());
		if (openATPDDiagram.canExecute(context)) {
			return openATPDDiagram;
		}	
		ICustomFeature openDEPDDiagram = new OpenDEPDDiagramFeature(getFeatureProvider());
		if (openDEPDDiagram.canExecute(context)) {
			return openDEPDDiagram;
		}
		ICustomFeature openDEPALDiagram=new OpenDEPALDDiagramFeature(getFeatureProvider());
		if (openDEPALDiagram.canExecute(context)) {
			return openDEPALDiagram;
		}
		ICustomFeature openDEPDLDiagram=new OpenDEPDLDDiagramFeature(getFeatureProvider());
		if (openDEPDLDiagram.canExecute(context)) {
			return openDEPDLDiagram;
		}
		ICustomFeature openDEPSCDiagram=new OpenDEPSCDDiagramFeature(getFeatureProvider());
		if (openDEPSCDiagram.canExecute(context)) {
			return openDEPSCDiagram;
		}
		//to do
		ICustomFeature openDEPEMTPD=new OpenDEPEMTPDFeature(getFeatureProvider());
		if (openDEPEMTPD.canExecute(context)) {
			return openDEPEMTPD;
		}
		ICustomFeature openBDDiagram=new OpenBDDiagramFeature(getFeatureProvider());
		if (openBDDiagram.canExecute(context)) {
			return openBDDiagram;
		}
		ICustomFeature openDTPDiagram=new OpenDTPDiagramFeature(getFeatureProvider());
		if (openDTPDiagram.canExecute(context)) {
			return openDTPDiagram;
		}
		ICustomFeature openEDDiagram=new OpenEDDiagramFeature(getFeatureProvider());
		if (openEDDiagram.canExecute(context)) {
			return openEDDiagram;
		}ICustomFeature openDEPSConstrDDiagram=new OpenDEPSConstrDDiagramFeature(getFeatureProvider());
		if (openDEPSConstrDDiagram.canExecute(context)) {
			return openDEPSConstrDDiagram;
		}
	
		return super.getDoubleClickFeature(context);
	}
	
	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		IFeatureProvider featureProvider = getFeatureProvider();
		Object bo = featureProvider.getBusinessObjectForPictogramElement(pe);
		if (bo instanceof EAPackage) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EPDImageProvider.IMAGE_EAPACKAGE);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof RequirementsModel) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EPDImageProvider.IMAGE_REQUIREMENT_MODEL);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof Dependability) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EPDImageProvider.IMAGE_DEPENDABILITY);
			return new IDecorator[] { imageRenderingDecorator };
		}else if (bo instanceof SystemModel) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EPDImageProvider.IMAGE_SYSTEM_MODEL);
			return new IDecorator[] { imageRenderingDecorator };
		} else if (bo instanceof Behavior) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EPDImageProvider.IMAGE_BEHAVIOR);
			return new IDecorator[] { imageRenderingDecorator };
		}  else if (bo instanceof Environment) {
			IDecorator imageRenderingDecorator = new ImageDecorator(
					EPDImageProvider.IMAGE_ENVIRONMENT);
			return new IDecorator[] { imageRenderingDecorator };
		}  
		return super.getDecorators(pe);
	}


	@Override
	protected void setGenericContextButtons(IContextButtonPadData data, PictogramElement pe, int identifiers) {

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
		if ((identifiers & CONTEXT_BUTTON_DELETE) != 0) {
			IContextButtonEntry deleteButton = ContextEntryHelper.createDefaultDeleteContextButton(
					getFeatureProvider(), pe);
			if (deleteButton != null) {
				data.getGenericContextButtons().add(deleteButton);
			}
		}
	}

}
