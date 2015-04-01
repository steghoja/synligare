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
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.examples.graphicaleditor.SModel.providers.SMDImageProvider;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateHardwareComponentPrototypeFeature extends
		AbstractCreateFeature {
	private static final String TITLE = "Create Hardware Component Prototype ";

	private static final String USER_QUESTION = "Enter new Hardware Component Prototype name";

	public CreateHardwareComponentPrototypeFeature(IFeatureProvider fp) {
		super(fp, "HardwareComponentPrototype ",
				"Create Hardware Component Prototype");
	}

	@Override
	public String getCreateImageId() {
		return SMDImageProvider.IMAGE_HardwareComponentPrototype;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		if (context.getTargetContainer() instanceof ContainerShape) {
			if (context.getTargetContainer().getLink().getBusinessObjects()
					.get(0) instanceof DesignLevel) {
				DesignLevel dl = (DesignLevel) context.getTargetContainer().getLink().getBusinessObjects()
						.get(0);
				if(dl.getHardwareDesignArchitecture() == null)
				return true;
			}

		}

		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		String newQualReqShirtName = ExampleUtil.askString(TITLE,
				USER_QUESTION, "");
		if (newQualReqShirtName == null
				|| newQualReqShirtName.trim().length() == 0) {
			return EMPTY;
		}
		// create QualityRequirement
		HardwareComponentPrototype newQualReq = Eastadl21Factory.eINSTANCE
				.createHardwareComponentPrototype();
		newQualReq.setShortName(newQualReqShirtName);
		Assert.isTrue(context.getTargetContainer().getLink()
				.getBusinessObjects().get(0) instanceof DesignLevel);
		DesignLevel parentBO = (DesignLevel) context.getTargetContainer()
				.getLink().getBusinessObjects().get(0);

		
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getDesignLevel_HardwareDesignArchitecture();
		
		String fragment = EcoreUtil.getURI(newQualReq).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil
					.addObjectToBOResource(parentBO, referenceId, newQualReq);
		}

		addGraphicalRepresentation(context, newQualReq);
		// activate direct editing after object creation
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		// return newly created business object(s)
		return new Object[] { newQualReq };
	}

}
