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
package org.eclipse.eatop.examples.graphicaleditor.reqd.features.add;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.mm.GraphicsAlgorithmContainer;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService;
import org.eclipse.graphiti.util.ColorConstant;
import org.eclipse.graphiti.util.IColorConstant;


public class AddRequirementsLinkFeature extends AbstractAddFeature {
	private static final IColorConstant E_REFERENCE_FOREGROUND = new ColorConstant(98, 131, 167);
	

	
	public AddRequirementsLinkFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canAdd(IAddContext context) {

		 // return true if given business object is an EReference
        // note, that the context must be an instance of IAddConnectionContext
        if (context instanceof IAddConnectionContext
           /* && context.getNewObject() instanceof RequirementsLink*/) {
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
	        
	       
	        // set reference name in the text decorator
	        //RequirementsLink reqL=(RequirementsLink) context.getNewObject();
	        //text.setValue(reqL.getShortName());

	        
	 ConnectionDecorator cd;
	 cd=peCreateService.createConnectionDecorator(connection, false, 1.0, true);
	 
	 createArrow(cd);
	
	        return connection;

	} 
	private Polyline createArrow(GraphicsAlgorithmContainer gaContainer) {
	    IGaService gaService = Graphiti.getGaService();
	    Polyline polyline =
	        gaService.createPolyline(gaContainer, new int[] { -15, 10, 0, 0, -15,
	                -10 });
	    polyline.setForeground(manageColor(E_REFERENCE_FOREGROUND));
	    polyline.setLineWidth(2);
	    return polyline;
	} 



}
