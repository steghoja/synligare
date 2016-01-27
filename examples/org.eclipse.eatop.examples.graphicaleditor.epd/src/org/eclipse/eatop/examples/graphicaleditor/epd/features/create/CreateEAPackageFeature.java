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
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.examples.graphicaleditor.epd.provider.EPDImageProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.examples.common.ExampleUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateEAPackageFeature extends AbstractCreateFeature {
	public static String CREATE_CONNECTION_NAME = "EAPackage"; //$NON-NLS-1$
	public static String CREATE_CONNECTION_DESCRIPTION = "Create EAPackage"; //$NON-NLS-1$
	public static final String TITLE = "Create EApackage"; //$NON-NLS-1$
	public static final String USER_QUESTION = "Enter name"; //$NON-NLS-1$ //$NON-NLS-2$

	public CreateEAPackageFeature(IFeatureProvider fp) {
		super(fp, CREATE_CONNECTION_NAME, CREATE_CONNECTION_DESCRIPTION);
	}

	@Override
	public String getCreateImageId() {
		return EPDImageProvider.IMAGE_EAPACKAGE;
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

		EAPackage newEAPackage = Eastadl21Factory.eINSTANCE.createEAPackage();
		newEAPackage.setShortName(userAskedString);

		// Get the parent Business Object
		EObject parentBO =  getDiagram().getLink().getBusinessObjects().get(0);
		Assert.isTrue(parentBO instanceof EAPackage, "parentBO is not a EAPackage element!");
				
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getEAPackage_SubPackage();
		
		String fragment = EcoreUtil.getURI(newEAPackage).fragment();
		if (DiagramUtil.getEObject(parentBO, fragment) != null) {
			// the object already exist (Drag & Drop)
		} else {
			DiagramUtil.addObjectToBOResource(parentBO, referenceId, newEAPackage);
		}		
				
		addGraphicalRepresentation(context, newEAPackage);
		return new Object[] { newEAPackage };
	}
}
