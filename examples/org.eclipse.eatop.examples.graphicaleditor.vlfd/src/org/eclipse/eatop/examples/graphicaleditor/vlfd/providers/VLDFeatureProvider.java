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
package org.eclipse.eatop.examples.graphicaleditor.vlfd.providers;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.FeatureConstraint;
import org.eclipse.eatop.eastadl21.FeatureLink;
import org.eclipse.eatop.eastadl21.FeatureModel;
import org.eclipse.eatop.eastadl21.VehicleFeature;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.VLDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.VLDReconnectionFeature;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.VLDUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.add.AddFeatureConstraint;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.add.AddFeatureLink;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.add.AddFeatureModel;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.add.AddVehicleFeature;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.create.CreateFeatureConstraint;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.create.CreateFeatureLink;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.create.CreateFeatureModel;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.create.CreateVehicleFeature;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class VLDFeatureProvider extends DefaultFeatureProvider {
	public VLDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			if (bo instanceof EAElement) {
				return new VLDUpdateFeature(this);
			}
		}
		return super.getUpdateFeature(context);

	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof VehicleFeature || bo instanceof FeatureModel
				|| bo instanceof FeatureConstraint) {
			return new VLDLayoutFeature(this);
		}
		return super.getLayoutFeature(context);

	}
	
	private boolean containerAlreadyExists(IAddContext context) {
		/*
		 * the container of an object can't be dragged and dropped from the
		 * wizard if there is already a container of the same BO in the diagram
		 */
		if (context.getTargetContainer() instanceof Diagram) {
			for (Shape cs : context.getTargetContainer().getChildren()) {
				if (cs.getLink() != null)
					if (cs.getLink().getBusinessObjects().size() > 0)
						if (cs.getLink().getBusinessObjects().get(0)
								.equals(context.getNewObject()))
							return true;
			}
		}
		return false;

	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		
		if(containerAlreadyExists(context))
			return super.getAddFeature(context);
		
		if (context.getNewObject() instanceof FeatureModel) {
			return new AddFeatureModel(this);
		} else if (context.getNewObject() instanceof VehicleFeature) {
			return new AddVehicleFeature(this);
		} else if (context.getNewObject() instanceof FeatureConstraint) {
			return new AddFeatureConstraint(this);
		}
		if (context instanceof AddConnectionContext) {
			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);

			if (context.getNewObject() instanceof FeatureLink
					&& sourceObject instanceof VehicleFeature
					&& targetObject instanceof VehicleFeature) {
				return new AddFeatureLink(this);
			}

		}
		return super.getAddFeature(context);

	}

	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		return super.getRemoveFeature(context);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { new CreateFeatureModel(this),
				new CreateVehicleFeature(this),
				new CreateFeatureConstraint(this) };

	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] { new CreateFeatureLink(this) };

	}

	@Override
	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		// simply return all create connection features
		return getCreateConnectionFeatures();

	}

	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) {
		return new VLDReconnectionFeature(this);
	}

	private Object resolveSourceBusinessObject(AddConnectionContext context) {
		Object element = getBusinessObjectForPictogramElement(context
				.getSourceAnchor().getParent());
		Assert.isNotNull(element,
				"Could not resolve sourceObject from AddConnectionContext");
		return element;
	}

	private Object resolveTargetBusinessObject(AddConnectionContext context) {
		Object element = getBusinessObjectForPictogramElement(context
				.getTargetAnchor().getParent());
		Assert.isNotNull(element,
				"Could not resolve targetObject from AddConnectionContext");
		return element;
	}

}
