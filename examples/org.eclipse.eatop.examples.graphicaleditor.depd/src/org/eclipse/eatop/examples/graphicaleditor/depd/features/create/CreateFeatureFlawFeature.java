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
package org.eclipse.eatop.examples.graphicaleditor.depd.features.create;

import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FeatureFlaw;
import org.eclipse.eatop.examples.graphicaleditor.depd.provider.DEPDImageProvider;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateFeatureFlawFeature extends AbstractCreateFeature{
	private static final String TITLE = "Create FeatureFlaw";
	 
	   private static final String USER_QUESTION = "Enter new FlawFeature short name";
	public CreateFeatureFlawFeature(IFeatureProvider fp) {
		super(fp, "FeatureFlaw", "Create FeatureFlaw");
	}


	@Override
	public String getCreateImageId() {
		return DEPDImageProvider.IMAGE_FLAW_FEATURE;
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
        
		FeatureFlaw newFeatureFlaw = Eastadl21Factory.eINSTANCE.createFeatureFlaw();
		newFeatureFlaw.setShortName(userAskedString);
		
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof Dependability);		
		Dependability parentBO = (Dependability) getDiagram().getLink().getBusinessObjects().get(0);
		
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getDependability_FeatureFlaw();


		String fragment = EcoreUtil.getURI(newFeatureFlaw).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(parentBO, referenceId, newFeatureFlaw);
		}		
				
		addGraphicalRepresentation(context, newFeatureFlaw);
    
        // return newly created business object(s)
        return new Object[] {newFeatureFlaw };
	}

}
