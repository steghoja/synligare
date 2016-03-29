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
package org.eclipse.eatop.examples.graphicaleditor.aftd.features.add;

import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;


public class AddFunctionConnector extends AbstractAddFeature {
	private static final IColorConstant E_REFERENCE_FOREGROUND = new ColorConstant(98, 131, 167);
	

	
	public AddFunctionConnector(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		 // return true if given business object is an EReference
        // note, that the context must be an instance of IAddConnectionContext
        if (context instanceof IAddConnectionContext
            && context.getNewObject() instanceof FunctionConnector) {
            return true;
        }
        return false;
	}
	
	@Override
	public PictogramElement add(IAddContext context) {
			IAddConnectionContext addConContext = (IAddConnectionContext) context;
	        //RequirementsLink addedReqLink = (RequirementsLink) context.getNewObject();
	        IPeCreateService peCreateService = Graphiti.getPeCreateService();
	        
	        // CONNECTION WITH POLYLINE
	        Connection connection = peCreateService
	            .createFreeFormConnection(getDiagram());
	        connection.setStart(addConContext.getSourceAnchor());
	        connection.setEnd(addConContext.getTargetAnchor());
	 
	        IGaService gaService = Graphiti.getGaService();
	        Polyline polyline = gaService.createPolyline(connection);
	        polyline.setLineStyle(LineStyle.SOLID);
	        polyline.setLineWidth(2);
	        polyline.setForeground(manageColor(E_REFERENCE_FOREGROUND));
	       
	        // create link and wire it
	        //link(connection, addedReqLink);
	        
	        // add dynamic text decorator for the association name 
	        ConnectionDecorator textDecorator =
	            peCreateService.createConnectionDecorator(connection, true,
	            0.5, true);
	        Text text = gaService.createDefaultText(getDiagram(),textDecorator);
	        text.setForeground(manageColor(IColorConstant.BLACK));
	        gaService.setLocation(text, 10, 0);
	        // set reference name in the text decorator
	        FunctionConnector reqL=(FunctionConnector) context.getNewObject();
	        text.setValue(reqL.getShortName());
	        
	        peCreateService.createConnectionDecorator(connection, false, 1.0, true);
	        return connection;
	} 
}
