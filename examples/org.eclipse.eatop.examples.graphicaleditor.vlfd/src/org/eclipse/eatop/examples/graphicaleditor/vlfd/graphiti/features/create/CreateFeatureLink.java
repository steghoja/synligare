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
import org.eclipse.eatop.eastadl21.FeatureLink;
import org.eclipse.eatop.eastadl21.FeatureModel;
import org.eclipse.eatop.eastadl21.VehicleFeature;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.providers.VLDImageProvider;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateFeatureLink extends AbstractCreateConnectionFeature {

	public CreateFeatureLink(IFeatureProvider fp) {
		super(fp, "FeatureLink", "Create FeatureLink Relationship");
	}

	@Override
	public String getCreateImageId() {
		return VLDImageProvider.IMAGE_FeatureLink;
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		VehicleFeature source = getVehicleFeature(context.getSourceAnchor());
		VehicleFeature target = getVehicleFeature(context.getTargetAnchor());
		if (source != null && target != null && source != target) {
			return true;
		}
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {

		Connection newConnection = null;
		VehicleFeature source = (VehicleFeature) getVehicleFeature(context
				.getSourceAnchor());
		VehicleFeature target = (VehicleFeature) getVehicleFeature(context
				.getTargetAnchor());
		Assert.isNotNull(source);
		Assert.isNotNull(target);
		if (source != null && target != null) {
			// get new business object
			FeatureLink eReference = createReference(source, target);
			// add connection for business object
			AddConnectionContext addContext = new AddConnectionContext(
					context.getSourceAnchor(), context.getTargetAnchor());
			addContext.setNewObject(eReference);

			VehicleLevel vl = (VehicleLevel) getDiagram().getLink()
					.getBusinessObjects().get(0);
			FeatureModel fm = vl.getTechnicalFeatureModel().get(0);
			EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getFeatureModel_FeatureLink();
			DiagramUtil
					.addReferenceToBOResource(
							fm,
							
							referenceId,
							eReference);

			newConnection = (Connection) getFeatureProvider().addIfPossible(
					addContext);
		}

		return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {

		// return true if start anchor belongs to a ComponentType
		if (getVehicleFeature(context.getSourceAnchor()) != null) {

			return true;

		}
		return false;

	}

	private VehicleFeature getVehicleFeature(Anchor anchor) {
		if (anchor != null) {
			Object object = getBusinessObjectForPictogramElement(anchor
					.getParent());
			if (object instanceof VehicleFeature) {
				return (VehicleFeature) object;
			}
		}
		return null;
	}

	private FeatureLink createReference(VehicleFeature source,
			VehicleFeature target) {
		FeatureLink reL = Eastadl21Factory.eINSTANCE.createFeatureLink();
		reL.setShortName(reL.getKind().getName());
		reL.setStart(source);
		reL.setEnd(target);
		return reL;
	}

}
