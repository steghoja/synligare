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
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.ModeGroup;
import org.eclipse.eatop.examples.graphicaleditor.bd.provider.BDImageProvider;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateModeFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create Mode";
	public ModeGroup mg2 =null;
	   private static final String USER_QUESTION = "Enter new mode short name";
	public CreateModeFeature(IFeatureProvider fp) {
		super(fp, "Mode", "Create Mode");
	}

	@Override
	public String getCreateImageId() {
		return BDImageProvider.IMAGE_MODE;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		// Create a port only for a ComponentType
				ContainerShape container = context.getTargetContainer();
				PictogramLink link = container.getLink();
				EList<EObject> bo = link.getBusinessObjects();
				if (bo.get(0) instanceof ModeGroup){
					return true;
				} else if (bo.get(0) instanceof EAPackage) {
					System.out.println("EAPackage");
				}
		 return false;
			}

	@Override
	public Object[] create(ICreateContext context) {
		 // ask user for EClass name
        String userAskedString = ExampleUtil.askString(TITLE, USER_QUESTION, "");
        if (userAskedString == null || userAskedString.trim().length() == 0) {
            return EMPTY;
        }
        
		Mode newMode = Eastadl21Factory.eINSTANCE.createMode();
		newMode.setShortName(userAskedString);
		
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof Behavior);		
		Behavior parentBO = (Behavior) getDiagram().getLink().getBusinessObjects().get(0);
			 EList<ModeGroup> mgList = parentBO.getModeGroup();
			 ModeGroup mg1 =null;
			 for(ModeGroup mg : mgList)
			 {
				mg1 = mg ;
				 String shortname = mg1.getShortName();
				 ModeGroup model = (ModeGroup) context.getTargetContainer().getLink().getBusinessObjects().get(0);
				 String modelShortname = model.getShortName();
				 if ( shortname.equals(modelShortname)){
					 mg2 = mg1;
				 }
			 }
		
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getBehavior_ModeGroup();
		

		String fragment = EcoreUtil.getURI((EObject) newMode).fragment();
		if (DiagramUtil.getEObject((EObject) parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			if(mg2 != null){
				DiagramUtil.addObjectToBOResource((org.eclipse.emf.ecore.EObject) mg2,
						referenceId, (org.eclipse.emf.ecore.EObject) newMode );
			}
			
		}		
				
		addGraphicalRepresentation(context, newMode);
    
        // return newly created business object(s)
        return new Object[] {newMode};
	}

}
