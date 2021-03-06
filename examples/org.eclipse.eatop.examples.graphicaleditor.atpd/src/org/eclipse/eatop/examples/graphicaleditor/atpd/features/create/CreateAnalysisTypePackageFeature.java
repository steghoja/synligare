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
package org.eclipse.eatop.examples.graphicaleditor.atpd.features.create;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.examples.graphicaleditor.atpd.providers.ATPDImageProvider;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateAnalysisTypePackageFeature extends AbstractCreateFeature {
	private static final String TITLE = "Analysis Type Package";
	 
	   private static final String USER_QUESTION = "Enter new Analysis Type Package name";
	   
	public CreateAnalysisTypePackageFeature(IFeatureProvider fp) {
		super(fp, "AnalysisTypePackage", "Create Anlaysis Type Package");
	}

	@Override
	public String getCreateImageId() {
		return ATPDImageProvider.IMAGE_EAPackage;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
    	return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) {
		 
		String userAskedString = ExampleUtil.askString(TITLE, USER_QUESTION, ""); //$NON-NLS-1$
		if (userAskedString == null || userAskedString.trim().length() == 0) {
			return EMPTY;
		}

		EAPackage newAnalysisLevel = Eastadl21Factory.eINSTANCE.createEAPackage();
		newAnalysisLevel.setName("AnalysisTypePackage");
		newAnalysisLevel.setShortName(userAskedString);
		
        Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof EAPackage);		
		EAPackage parentBO = (EAPackage) getDiagram().getLink().getBusinessObjects().get(0);
				
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getEAPackage_SubPackage();
		
		String fragment = EcoreUtil.getURI(newAnalysisLevel).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(parentBO, referenceId, newAnalysisLevel);
		}	
		addGraphicalRepresentation(context, newAnalysisLevel);
		// activate direct editing after object creation
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		// return newly created business object(s)
        return new Object[] {newAnalysisLevel };
	}

}
