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
package org.eclipse.eatop.examples.graphicaleditor.depald.element;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.examples.graphicaleditor.depald.provider.DEPALDImageProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;



public class CreateSatisfyFeature extends AbstractCreateFeature {
	  private static final String TITLE = "Create Satisfy";
	 
	   private static final String USER_QUESTION = "Enter new Satisfy short name";
	   
	public CreateSatisfyFeature(IFeatureProvider fp) {
		super(fp, "Satisfy", "Create Satisfy");
	}

	@Override
	public String getCreateImageId() {
		return DEPALDImageProvider.IMAGE_SATISFY;
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
        
        Satisfy newSatisfy = Eastadl21Factory.eINSTANCE.createSatisfy();
       // Satisfy_satisfiedBy newsBy=Eastadl21Factory.eINSTANCE.createSatisfy_satisfiedBy();
       // newSatisfy.getSatisfiedBy().add(newsBy);
        newSatisfy.setShortName(userAskedString);
		
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof Dependability);		
		Dependability parentBO = (Dependability) getDiagram().getLink().getBusinessObjects().get(0);
			
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getRequirementsRelationshipGroup_RequirementsRelationship();

		String fragment = EcoreUtil.getURI((EObject) newSatisfy).fragment();
		if (DiagramUtil.getEObject((EObject) parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource((org.eclipse.emf.ecore.EObject) parentBO,
					referenceId, (org.eclipse.emf.ecore.EObject) newSatisfy);
		}		
				
		addGraphicalRepresentation(context, newSatisfy);
    
        // return newly created business object(s)
        return new Object[] {newSatisfy};
	
		
	}

}
