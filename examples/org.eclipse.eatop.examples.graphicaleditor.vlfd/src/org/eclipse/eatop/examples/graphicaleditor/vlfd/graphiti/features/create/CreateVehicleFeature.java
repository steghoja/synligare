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
package org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.create;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FeatureModel;
import org.eclipse.eatop.eastadl21.VehicleFeature;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.providers.VLDImageProvider;
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

public class CreateVehicleFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create Vehicle Feature";

	private static final String USER_QUESTION = "Enter new vehicel feature name";

	public CreateVehicleFeature(IFeatureProvider fp) {
		super(fp, "VehicleFeature", "Create Vehicle Feature");
	}

	@Override
	public String getCreateImageId() {
		return VLDImageProvider.IMAGE_VehicleFeature;
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		ContainerShape container = context.getTargetContainer();
		PictogramLink link = container.getLink();
		if (link == null) {
			return false;
		}
		EList<EObject> bo = link.getBusinessObjects();
		if (bo.get(0) instanceof FeatureModel) {
			return true;
		}
		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		// ask user for EClass name
		String newQualReqShirtName = ExampleUtil.askString(TITLE,
				USER_QUESTION, "");
		if (newQualReqShirtName == null
				|| newQualReqShirtName.trim().length() == 0) {
			return EMPTY;
		}
		// create QualityRequirement
		VehicleFeature newQualReq = Eastadl21Factory.eINSTANCE
				.createVehicleFeature();

		// getDiagram().eResource().getContents().add(newQualReq);
		newQualReq.setShortName(newQualReqShirtName);

		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof VehicleLevel);

		
		FeatureModel fm = (FeatureModel) context.getTargetContainer().getLink().getBusinessObjects().get(0);
		
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getFeatureModel_RootFeature();

		String fragment = EcoreUtil.getURI(newQualReq).fragment();
		if (DiagramUtil.getEObject(fm, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(fm, referenceId, newQualReq);
		}

		addGraphicalRepresentation(context, newQualReq);
		// activate direct editing after object creation
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		// return newly created business object(s)
		return new Object[] { newQualReq };
	}

}
