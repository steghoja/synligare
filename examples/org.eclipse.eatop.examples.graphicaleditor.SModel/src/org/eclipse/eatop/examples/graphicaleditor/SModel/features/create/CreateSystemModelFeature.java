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

import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.examples.graphicaleditor.SModel.providers.SMDImageProvider;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateSystemModelFeature extends AbstractCreateFeature {
	private static final String TITLE = "Create System Model";

	private static final String USER_QUESTION = "Enter new System Model name";

	public static int lock = 0;

	public CreateSystemModelFeature(IFeatureProvider fp) {
		super(fp, "SystemModel", "Create System Model");
	}

	@Override
	public String getCreateImageId() {
		return SMDImageProvider.IMAGE_SystemModel;
	}

	@Override
	public boolean canCreate(ICreateContext context) {

		if (lock == 1) {

			return true;
		}
		return context.getTargetContainer() instanceof Diagram;
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
		SystemModel smodel = Eastadl21Factory.eINSTANCE.createSystemModel();
		smodel.setShortName(newQualReqShirtName);

		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof EAPackage);
		EAPackage parentBO = (EAPackage) getDiagram().getLink()
				.getBusinessObjects().get(0);
		
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getEAPackage_Element();

		String fragment = EcoreUtil.getURI(smodel).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(parentBO, referenceId, smodel);
		}

		addGraphicalRepresentation(context, smodel);
		// resource.getContents().add(smodel);

		getFeatureProvider().getDirectEditingInfo().setActive(true);

		lock = 1;

		return new Object[] { smodel };
	}

}
