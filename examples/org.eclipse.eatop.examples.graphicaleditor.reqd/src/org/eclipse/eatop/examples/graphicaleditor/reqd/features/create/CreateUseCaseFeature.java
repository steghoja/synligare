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
package org.eclipse.eatop.examples.graphicaleditor.reqd.features.create;


import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.eatop.examples.graphicaleditor.reqd.provider.REQDImageProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateUseCaseFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create use case";
	 
	   private static final String USER_QUESTION = "Enter new use case name";
	   
	public CreateUseCaseFeature(IFeatureProvider fp) {
		super(fp, "UseCase", "Create Use Case");
	}

	@Override
	public String getCreateImageId() {
		return REQDImageProvider.IMAGE_USE_CASE;
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
        
		UseCase newUseCase = Eastadl21Factory.eINSTANCE.createUseCase();
		newUseCase.setShortName(userAskedString);
		
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof RequirementsModel);		
		RequirementsModel parentBO = (RequirementsModel) getDiagram().getLink().getBusinessObjects().get(0);
				
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getRequirementsModel_UseCase();
		

		String fragment = EcoreUtil.getURI((EObject) newUseCase).fragment();
		if (DiagramUtil.getEObject((EObject) parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource((org.eclipse.emf.ecore.EObject) parentBO,
					referenceId, (org.eclipse.emf.ecore.EObject) newUseCase);
		}		
				
		addGraphicalRepresentation(context, newUseCase);
    
        // return newly created business object(s)
        return new Object[] {newUseCase};
	}

}
