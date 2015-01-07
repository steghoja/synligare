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
package org.eclipse.eatop.examples.graphicaleditor.emtd.features.element;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ErrorBehavior;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.examples.graphicaleditor.emtd.provider.EMTDImageProvider;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateErrorBehaviorFeature extends AbstractCreateFeature {
	  private static final String TITLE = "Create ErrorBehavior";
	 
	   private static final String USER_QUESTION = "Enter new ErrorBehavior short name";
	   
	public CreateErrorBehaviorFeature(IFeatureProvider fp) {
		super(fp, "ErrorBehavior", "Create ErrorBehavior");
	}

	@Override
	public String getCreateImageId() {
		return EMTDImageProvider.ERROR_BEHAVIOR;
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
        
		ErrorBehavior newfb = Eastadl21Factory.eINSTANCE.createErrorBehavior();
		newfb.setShortName(userAskedString);
		
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof ErrorModelType);		
		ErrorModelType parentBO = (ErrorModelType) getDiagram().getLink().getBusinessObjects().get(0);
			
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getErrorModelType_ErrorBehaviorDescription();
		
		String fragment = EcoreUtil.getURI(newfb).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(parentBO, referenceId, newfb);
		}		
				
		addGraphicalRepresentation(context, newfb);
    
        // return newly created business object(s)
        return new Object[] {newfb};
	}

}
