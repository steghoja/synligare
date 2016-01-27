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

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.eatop.examples.graphicaleditor.SModel.providers.SMDImageProvider;
import org.eclipse.core.runtime.Assert;
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

public class CreateVehicleLevelFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create Vehicle Level";

	private static final String USER_QUESTION = "Enter new Vehic leLevel name";

	public CreateVehicleLevelFeature(IFeatureProvider fp) {
		super(fp, "VehicleLevel", "Create Vehicle Level");
	}

	@Override
	public String getCreateImageId() {
		return SMDImageProvider.IMAGE_VehicleLevel;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		ContainerShape container = context.getTargetContainer();
		PictogramLink link = container.getLink();
		if (link == null) {
			return false;
		}
		EList<EObject> bo = link.getBusinessObjects();
		if (bo.isEmpty()) {
			return false;
		}

		if (bo.get(0) instanceof SystemModel) {
			return true;
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
		VehicleLevel newVL = Eastadl21Factory.eINSTANCE.createVehicleLevel();
		newVL.setShortName(newQualReqShirtName);
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof SystemModel);
		SystemModel parentBO = (SystemModel) getDiagram().getLink()
				.getBusinessObjects().get(0);
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getSystemModel_VehicleLevel();

		String fragment = EcoreUtil.getURI(newVL).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(parentBO, referenceId, newVL);
		}

		addGraphicalRepresentation(context, newVL);
		// activate direct editing after object creation
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		// return newly created business object(s)
		return new Object[] { newVL };
	}

	public SystemModel getSModel() {
		return null;
	}

}
