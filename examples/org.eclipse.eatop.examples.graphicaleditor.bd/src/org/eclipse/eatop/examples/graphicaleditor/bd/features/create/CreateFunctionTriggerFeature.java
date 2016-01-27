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
package org.eclipse.eatop.examples.graphicaleditor.bd.features.create;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.Behavior;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionTrigger;
import org.eclipse.eatop.examples.graphicaleditor.bd.provider.BDImageProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateFunctionTriggerFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create FunctionTrigger";
	 
	   private static final String USER_QUESTION = "Enter new FunctionTrigger short name";
	public CreateFunctionTriggerFeature(IFeatureProvider fp) {
		super(fp, "FunctionTrigger", "Create FunctionTrigger");
	}

	@Override
	public String getCreateImageId() {
		return BDImageProvider.IMAGE_FUNCTION_TRIGGER;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		 return context.getTargetContainer() instanceof Diagram;
			}

	@Override
	public Object[] create(ICreateContext context) {
		 // ask user for EClass name
        String userAskedString = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (userAskedString == null || userAskedString.trim().length() == 0) {
            return EMPTY;
        }
        
		FunctionTrigger newft = Eastadl21Factory.eINSTANCE.createFunctionTrigger();
		newft.setShortName(userAskedString);
		
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof Behavior);		
		Behavior parentBO = (Behavior) getDiagram().getLink().getBusinessObjects().get(0);
			
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getBehavior_FunctionTrigger();
		

		String fragment = EcoreUtil.getURI(newft).fragment();
		if (DiagramUtil.getEObject((EObject) parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource((EObject) parentBO, referenceId, newft);
		}		
				
		addGraphicalRepresentation(context, newft);
    
        // return newly created business object(s)
        return new Object[] {newft};
	}

}
