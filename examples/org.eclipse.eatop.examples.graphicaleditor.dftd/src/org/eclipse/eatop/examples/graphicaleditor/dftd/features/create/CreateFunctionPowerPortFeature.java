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
package org.eclipse.eatop.examples.graphicaleditor.dftd.features.create;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.examples.graphicaleditor.dftd.providers.DFTDImageProvider;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;


public class CreateFunctionPowerPortFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create Function Power Port";
	 
	   private static final String USER_QUESTION = "Enter new Function Power Port name";
	   
	public CreateFunctionPowerPortFeature(IFeatureProvider fp) {
		super(fp, "FunctionPowerPort", "Create Function Power Port");
	}

	@Override
	public String getCreateImageId() {
		return DFTDImageProvider.IMAGE_FunctionPowerPort;
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
//    	if(bo.get(0) instanceof FunctionPrototype || bo.get(0) instanceof Diagram)
//    	{
    		return true;
//    	}
//    	return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		 // ask user for EClass name
        String newFunctionPowerPortName = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (newFunctionPowerPortName == null || newFunctionPowerPortName.trim().length() == 0) {
            return EMPTY;
        }
		 // create FunctionPowerPort
        FunctionPowerPort newFunctionPowerPort = Eastadl21Factory.eINSTANCE.createFunctionPowerPort();
        // Add model element to resource.
        // We add the model element to the resource of the diagram for
        // simplicity's sake. Normally, a customer would use its own
        // model persistence layer for storing the business model separately.
       // getDiagram().eResource().getContents().add(newFunctionPowerPort);
        newFunctionPowerPort.setShortName(newFunctionPowerPortName);
        getDiagram().eResource().getContents().add(newFunctionPowerPort);
        // do the add
        addGraphicalRepresentation(context, newFunctionPowerPort);
     // activate direct editing after object creation
     getFeatureProvider().getDirectEditingInfo().setActive(true);
        // return newly created business object(s)
        return new Object[] {newFunctionPowerPort };
	}
	

}
