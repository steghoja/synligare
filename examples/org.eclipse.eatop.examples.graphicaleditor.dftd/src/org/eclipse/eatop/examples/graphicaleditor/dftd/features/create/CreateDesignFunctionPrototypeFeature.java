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

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.examples.graphicaleditor.dftd.providers.DFTDImageProvider;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateDesignFunctionPrototypeFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create Functional Device ";
	 
	   private static final String USER_QUESTION = "Enter new Design Function Prototype name";
	   
	public CreateDesignFunctionPrototypeFeature(IFeatureProvider fp) {
		super(fp, "DesignFunctionPrototype ", "Create  Design Function Prototype");
	}

	@Override
	public String getCreateImageId() {
		return DFTDImageProvider.IMAGE_DesignFunctionPrototype;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		 return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
        String newQualReqShortName = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (newQualReqShortName == null || newQualReqShortName.trim().length() == 0) {
            return EMPTY;
        }
        DesignFunctionPrototype  newQualReq = Eastadl21Factory.eINSTANCE.createDesignFunctionPrototype ();
        newQualReq.setShortName(newQualReqShortName);
        Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof DesignFunctionType);		
		DesignFunctionType parentBO = (DesignFunctionType) getDiagram().getLink().getBusinessObjects().get(0);
				
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getDesignFunctionType_Part();
		
		String fragment = EcoreUtil.getURI(newQualReq).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(parentBO, referenceId, newQualReq);
		}	

        addGraphicalRepresentation(context, newQualReq);
     // activate direct editing after object creation
     getFeatureProvider().getDirectEditingInfo().setActive(true);
        // return newly created business object(s)
        return new Object[] {newQualReq };
	}

}
