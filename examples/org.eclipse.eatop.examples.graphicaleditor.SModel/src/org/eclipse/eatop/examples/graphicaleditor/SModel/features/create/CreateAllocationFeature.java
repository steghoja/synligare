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
package org.eclipse.eatop.examples.graphicaleditor.SModel.features.create;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.examples.graphicaleditor.SModel.providers.SMDImageProvider;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateAllocationFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create Allocation";

	private static final String USER_QUESTION = "Enter new Allocation name";

	

	public CreateAllocationFeature(IFeatureProvider fp) {
		super(fp, "Allocation", "Create Allocation");
	}

	@Override
	public String getCreateImageId() {
		return SMDImageProvider.IMAGE_Allocation;
	}

	@Override
	public boolean canCreate(ICreateContext context) {

		if (context.getTargetContainer() instanceof ContainerShape) {
			if (context.getTargetContainer().getLink().getBusinessObjects()
					.get(0) instanceof DesignLevel) {
				return true;
			}

		}
		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		// ask user for EClass name

		//
		String newQualReqShirtName = ExampleUtil.askString(TITLE,
				USER_QUESTION, "");
		if (newQualReqShirtName == null
				|| newQualReqShirtName.trim().length() == 0) {
			return EMPTY;
		}
		// create QualityRequirement
		Allocation allocation = Eastadl21Factory.eINSTANCE.createAllocation();
		allocation.setShortName(newQualReqShirtName);

		Assert.isTrue(context.getTargetContainer().getLink()
				.getBusinessObjects().get(0) instanceof DesignLevel);
		DesignLevel parentBO = (DesignLevel) context.getTargetContainer().getLink()
				.getBusinessObjects().get(0);

		
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getDesignLevel_Allocation();
		
		String fragment = EcoreUtil.getURI(allocation).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(parentBO, referenceId, allocation);
		}

		addGraphicalRepresentation(context, allocation);
		// resource.getContents().add(allocation);

		getFeatureProvider().getDirectEditingInfo().setActive(true);

	

		return new Object[] { allocation };
	}

}
