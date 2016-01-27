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
package org.eclipse.eatop.examples.graphicaleditor.epd.features.create;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.Behavior;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.examples.graphicaleditor.epd.provider.EPDImageProvider;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateBehaviorFeature extends AbstractCreateFeature {
	public static String CREATE_CONNECTION_NAME = "Behavior"; //$NON-NLS-1$
	public static String CREATE_CONNECTION_DESCRIPTION = "Create Behavior"; //$NON-NLS-1$
	public static final String TITLE = "Create Behavior"; //$NON-NLS-1$
	public static final String USER_QUESTION = "Enter name"; //$NON-NLS-1$ //$NON-NLS-2$
	public CreateBehaviorFeature(IFeatureProvider fp) {
		super(fp, CREATE_CONNECTION_NAME, CREATE_CONNECTION_DESCRIPTION);
	}

	@Override
	public String getCreateImageId() {
		return EPDImageProvider.IMAGE_BEHAVIOR;
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

		Behavior newBehavior = Eastadl21Factory.eINSTANCE.createBehavior();
		newBehavior.setShortName(userAskedString);
		
		Assert.isTrue(getDiagram().getLink().getBusinessObjects().get(0) instanceof EAPackage);		
		EAPackage parentBO = (EAPackage) getDiagram().getLink().getBusinessObjects().get(0);
				
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getEAPackage_Element();
		
		String fragment = EcoreUtil.getURI(newBehavior).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(parentBO, referenceId, newBehavior);
		}		
				
		addGraphicalRepresentation(context, newBehavior);
		return new Object[] { newBehavior };
	}

}
