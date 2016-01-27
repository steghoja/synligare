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
package org.eclipse.eatop.examples.graphicaleditor.depdld.instanceref;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.Satisfy_satisfiedBy;
import org.eclipse.core.runtime.Assert;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;

public class CreateSatisfytoDesignFunctionPrototypeFeature extends
		AbstractCreateConnectionFeature {
	public CreateSatisfytoDesignFunctionPrototypeFeature(IFeatureProvider fp) {
		super(fp, "Satisfy to DesignFunctionPrototype",
				"Create SatisfyToDesignFunctionPrototype");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		Satisfy source = getSatisfy(context.getSourceAnchor());
		DesignFunctionPrototype target = getDesignFunctionPrototype(context
				.getTargetAnchor());
		if (source != null && target != null && source != target) {
			return true;
		}
		return false;
	}

	private DesignFunctionPrototype getDesignFunctionPrototype(
			Anchor targetAnchor) {
		if (targetAnchor != null) {
			Object object = getBusinessObjectForPictogramElement(targetAnchor
					.getParent());
			if (object instanceof DesignFunctionPrototype) {
				return (DesignFunctionPrototype) object;
			}
		}
		return null;
	}

	private Satisfy getSatisfy(Anchor sourceAnchor) {
		if (sourceAnchor != null) {
			Object object = getBusinessObjectForPictogramElement(sourceAnchor
					.getParent());
			if (object instanceof Satisfy) {
				return (Satisfy) object;
			}
		}
		return null;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {

		Connection newConnection = null;

		Satisfy satisfy = (Satisfy) getSatisfy(context.getSourceAnchor());
		DesignFunctionPrototype afp = (DesignFunctionPrototype) getDesignFunctionPrototype(context
				.getTargetAnchor());
		Satisfy_satisfiedBy sBy = Eastadl21Factory.eINSTANCE
				.createSatisfy_satisfiedBy();
		satisfy.getSatisfiedBy().add(sBy);
		Assert.isNotNull(satisfy);
		Assert.isNotNull(afp);
		if (satisfy != null && afp != null) {

			// Satisfy_satisfiedBy s=satisfy.getSatisfiedBy().get(0);
			sBy.getIdentifiable_context().add(afp);
			sBy.setIdentifiable_target(afp.getType());

			AddConnectionContext addContext = new AddConnectionContext(
					context.getSourceAnchor(), context.getTargetAnchor());

			newConnection = (Connection) getFeatureProvider().addIfPossible(
					addContext);
		}

		return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a ComponentType
		if (getSatisfy(context.getSourceAnchor()) != null) {

			return true;

		}
		return false;
	}
}
