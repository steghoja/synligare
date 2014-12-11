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
package org.eclipse.eatop.examples.graphicaleditor.atpd.features.create;

import org.eclipse.eatop.eastadl21.ClientServerKind;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.examples.graphicaleditor.atpd.providers.ATPDImageProvider;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;


public class CreateFunctionServerPortFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create Function Server Port";
	 
	   private static final String USER_QUESTION = "Enter new Function Server Port name";
	   
	public CreateFunctionServerPortFeature(IFeatureProvider fp) {
		super(fp, "FunctionServerPort", "Create Function Server Port");
	}

	@Override
	public String getCreateImageId() {
		return ATPDImageProvider.IMAGE_FunctionClientServerPort;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		ContainerShape container = context.getTargetContainer();
    	PictogramLink link = container.getLink();
    	if (link == null)
    	{
    		return false;
    	}
    	EList<EObject> bo = link.getBusinessObjects();
    	if (bo.isEmpty())
    	{
    		return false;
    	}
    	if(bo.get(0) instanceof FunctionType)
    	{
    		return true;
    	}
    	return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		 // ask user for EClass name
        String newQualReqShirtName = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (newQualReqShirtName == null || newQualReqShirtName.trim().length() == 0) {
            return EMPTY;
        }
		 // create QualityRequirement
        FunctionClientServerPort newQualReq = Eastadl21Factory.eINSTANCE.createFunctionClientServerPort();
        newQualReq.setKind(ClientServerKind.SERVER);
        newQualReq.setShortName(newQualReqShirtName);
        addGraphicalRepresentation(context, newQualReq);
     // activate direct editing after object creation
     getFeatureProvider().getDirectEditingInfo().setActive(true);
        // return newly created business object(s)
        return new Object[] {newQualReq };
	}
	

}
