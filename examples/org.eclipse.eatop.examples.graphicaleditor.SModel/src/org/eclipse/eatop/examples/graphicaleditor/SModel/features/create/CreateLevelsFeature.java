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

import org.eclipse.eatop.eastadl21.AnalysisLevel;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.eatop.examples.graphicaleditor.SModel.providers.SMDImageProvider;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.PictogramLink;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateLevelsFeature extends AbstractCreateFeature {

	public static int lock = 0;

	public CreateLevelsFeature(IFeatureProvider fp) {
		super(fp, "Create Levels", "Create All Levels");
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

		if (lock == 1 && CreateSystemModelFeature.lock == 1) {
			return false;
		}

		if (!isempty(context)) {
			return false;
		}

		if (bo.get(0) instanceof SystemModel) {
			return true;
		}
		return false;
	}

	@Override
	public Object[] create(ICreateContext context) {
		VehicleLevel vl = Eastadl21Factory.eINSTANCE.createVehicleLevel();
		AnalysisLevel al = Eastadl21Factory.eINSTANCE.createAnalysisLevel();
		DesignLevel dl = Eastadl21Factory.eINSTANCE.createDesignLevel();
		vl.setShortName("VehicleLevel");
		al.setShortName("AnaylysisLevel");
		dl.setShortName("DesignLevel");
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof EAPackage);
		EAPackage parentBO = (EAPackage) getDiagram().getLink()
				.getBusinessObjects().get(0);
		SystemModel sm = (SystemModel) parentBO.getElement().get(0);

		
		EReference referenceId1 = Eastadl21Factory.eINSTANCE.getEastadl21Package().getSystemModel_VehicleLevel();
		EReference referenceId2 = Eastadl21Factory.eINSTANCE.getEastadl21Package().getSystemModel_AnalysisLevel();
		EReference referenceId3 = Eastadl21Factory.eINSTANCE.getEastadl21Package().getSystemModel_DesignLevel();
		String fragment1 = EcoreUtil.getURI(vl).fragment();
		String fragment2 = EcoreUtil.getURI(al).fragment();
		String fragment3 = EcoreUtil.getURI(dl).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment1) != null
				|| DiagramUtil.getEObject(parentBO, fragment2) != null
				|| DiagramUtil.getEObject(parentBO, fragment3) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(sm, referenceId1, vl);
			DiagramUtil.addObjectToBOResource(sm, referenceId2, al);
			DiagramUtil.addObjectToBOResource(sm, referenceId3, dl);
		}

		addGraphicalRepresentation(context, vl);
		addGraphicalRepresentation(context, al);
		addGraphicalRepresentation(context, dl);
		// activate direct editing after object creation
		getFeatureProvider().getDirectEditingInfo().setActive(true);
		// return newly created business object(s)
		lock = 1;
		CreateSystemModelFeature.lock = 1;
		return new Object[] { vl, al, dl };

	}

	public boolean isempty(ICreateContext context) {
		PictogramElement t = context.getTargetContainer().getLink()
				.getPictogramElement();
		EObject bo = (EObject) getBusinessObjectForPictogramElement(t);

		if (bo instanceof VehicleLevel || bo instanceof AnalysisLevel
				|| bo instanceof DesignLevel) {
			return false;
		}

		return true;
	}

}
