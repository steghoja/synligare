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
package org.eclipse.eatop.examples.graphicaleditor.hctd.features.create;

import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.IOHardwarePin;
import org.eclipse.eatop.examples.graphicaleditor.hctd.providers.HCTDImageProvider;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;

public class CreateIOHardwareOutPinFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create Power IOHardware Out Pin";
	 
	   private static final String USER_QUESTION = "Enter new IOHardware Pin name";
	   
	public CreateIOHardwareOutPinFeature(IFeatureProvider fp) {
		super(fp, "IOHardwareOutPin", "Create IOHardware Out Pin");
	}

	@Override
	public String getCreateImageId() {
		return HCTDImageProvider.IMAGE_IOHardwarePin;
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
    		return true;
	}

	@Override
	public Object[] create(ICreateContext context) {
		 // ask user for EClass name
        String newQualReqShirtName = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (newQualReqShirtName == null || newQualReqShirtName.trim().length() == 0) {
            return EMPTY;
        }
		 // create QualityRequirement
        IOHardwarePin newQualReq = Eastadl21Factory.eINSTANCE.createIOHardwarePin();
        newQualReq.setDirection(EADirectionKind.OUT);
        newQualReq.setShortName(newQualReqShirtName);
        addGraphicalRepresentation(context, newQualReq);
     // activate direct editing after object creation
     getFeatureProvider().getDirectEditingInfo().setActive(true);
        // return newly created business object(s)
        return new Object[] {newQualReq };
	}

}
