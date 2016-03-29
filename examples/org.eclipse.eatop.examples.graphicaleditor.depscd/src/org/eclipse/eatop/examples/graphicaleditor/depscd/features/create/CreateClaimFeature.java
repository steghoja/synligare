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
package org.eclipse.eatop.examples.graphicaleditor.depscd.features.create;

import org.eclipse.eatop.eastadl21.Claim;
import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.SafetyCase;
import org.eclipse.eatop.examples.graphicaleditor.depscd.provider.DEPSCDImageProvider;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateClaimFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create Claim";
	private static final String USER_QUESTION = "Enter new Claim short name";
	public CreateClaimFeature(IFeatureProvider fp) {
		super(fp, "Claim", "Create Claim");
	}

	@Override
	public String getCreateImageId() {
		
		return DEPSCDImageProvider.IMAGE_CLAIM;
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
        
		Claim newClaim = Eastadl21Factory.eINSTANCE.createClaim();
		newClaim.setShortName(userAskedString);
		
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof Dependability);		
		Dependability parentBO = (Dependability) getDiagram().getLink().getBusinessObjects().get(0);
		SafetyCase sc=parentBO.getSafetyCase().get(0);
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getSafetyCase_Claim();


		String fragment = EcoreUtil.getURI(newClaim).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(sc, referenceId, newClaim);
		}		
				
		addGraphicalRepresentation(context, newClaim);
    
        // return newly created business object(s)
        return new Object[] {newClaim };
	}

}
