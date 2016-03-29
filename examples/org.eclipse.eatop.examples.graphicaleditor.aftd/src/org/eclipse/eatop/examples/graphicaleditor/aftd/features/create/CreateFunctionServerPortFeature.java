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
package org.eclipse.eatop.examples.graphicaleditor.aftd.features.create;

import org.eclipse.eatop.eastadl21.ClientServerKind;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.examples.graphicaleditor.aftd.providers.AFTDImageProvider;
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
		return AFTDImageProvider.IMAGE_FunctionClientServerPort;
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
        String newFunctionClientServerPortName = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (newFunctionClientServerPortName == null || newFunctionClientServerPortName.trim().length() == 0) {
            return EMPTY;
        }
		 // create FunctionClientServerPort
        FunctionClientServerPort newFunctionClientServerPort = Eastadl21Factory.eINSTANCE.createFunctionClientServerPort();
        newFunctionClientServerPort.setKind(ClientServerKind.SERVER);
        // Add model element to resource.
        // We add the model element to the resource of the diagram for
        // simplicity's sake. Normally, a customer would use its own
        // model persistence layer for storing the business model separately.
      //  getDiagram().eResource().getContents().add(newFunctionClientServerPort);
        newFunctionClientServerPort.setShortName(newFunctionClientServerPortName);
        getDiagram().eResource().getContents().add(newFunctionClientServerPort);
        // do the add
        addGraphicalRepresentation(context, newFunctionClientServerPort);
     // activate direct editing after object creation
     getFeatureProvider().getDirectEditingInfo().setActive(true);
        // return newly created business object(s)
        return new Object[] {newFunctionClientServerPort };
	}
	

}
