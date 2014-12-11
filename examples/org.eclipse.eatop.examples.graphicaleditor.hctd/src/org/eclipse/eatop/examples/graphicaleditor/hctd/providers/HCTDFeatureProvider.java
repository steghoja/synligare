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
package org.eclipse.eatop.examples.graphicaleditor.hctd.providers;

import org.eclipse.eatop.eastadl21.CommunicationHardwarePin;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.FunctionalDevice;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareConnector;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.IOHardwarePin;
import org.eclipse.eatop.eastadl21.PowerHardwarePin;
import org.eclipse.eatop.examples.graphicaleditor.hctd.delete.DeleteFunctionPortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.HCTDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.HCTDUpdateEClassFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.HardwareComponentPrototypeLayout;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.add.AddCommunicationHardwarePinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.add.AddFunctionConnector;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.add.AddHardwareComponentPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.add.AddIOHardwarePinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.add.AddPowerHardwarePinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.create.CreateHardwareComponentPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.create.CreateHardwareConnector;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.move.MovePortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.remove.RemoveFunctionConnectorFeature;
import org.eclipse.core.runtime.Assert;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveAnchorFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class HCTDFeatureProvider extends DefaultFeatureProvider {
	public HCTDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) 
	{
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			if (bo instanceof HardwareComponentPrototype) {
				return new HCTDUpdateEClassFeature(this);
			}
		}
		return super.getUpdateFeature(context);
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] { new CreateHardwareConnector(
				this) };
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof HardwareComponentPrototype) {

			return new HardwareComponentPrototypeLayout(this);
		}
		if (bo instanceof EAPackage || bo instanceof Text) {
			return new HCTDLayoutFeature(this);
		}

		return super.getLayoutFeature(context);

	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {

		IDeleteFeature ret = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof ContainerShape) {
			return null;
		}
		if (bo instanceof HardwarePin) {
			HardwarePin elementBo = (HardwarePin) bo;
			elementBo.getShortName();
			return new DeleteFunctionPortsFeature(this);

		}

		ret = new DefaultDeleteFeature(this);
		return ret;
	}
	
	private boolean containerAlreadyExists(IAddContext context) {
		/*
		 * the container of an object can't be dragged and dropped from the
		 * wizard if there is already a container of the same BO in the diagram
		 */
		if (context.getTargetContainer() instanceof Diagram)
			for (Shape cs : context.getTargetContainer().getChildren()) {
				if (cs.getLink() != null)
					if (cs.getLink().getBusinessObjects().size() > 0)
						if (cs.getLink().getBusinessObjects().get(0)
								.equals(context.getNewObject()))
							return true;

			}
		return false;
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {

		if(containerAlreadyExists(context))
			return super.getAddFeature(context);
		
		if (context.getNewObject() instanceof FunctionalDevice) {
			return new AddHardwareComponentPrototypeFeature(this);
		} else if (context.getNewObject() instanceof HardwareComponentPrototype) {
			return new AddHardwareComponentPrototypeFeature(this);
		} else if (context.getNewObject() instanceof CommunicationHardwarePin) {
			return new AddCommunicationHardwarePinFeature(this);
		} else if (context.getNewObject() instanceof IOHardwarePin) {
			return new AddIOHardwarePinFeature(this);
		} else if (context.getNewObject() instanceof PowerHardwarePin) {
			return new AddPowerHardwarePinFeature(this);
		}
		if (context instanceof AddConnectionContext) {
			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);

			if (context.getNewObject() instanceof HardwareConnector
					&& sourceObject instanceof HardwarePin
					&& targetObject instanceof HardwarePin) {
				return new AddFunctionConnector(this);
			}
		}

		return super.getAddFeature(context);

	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { new CreateHardwareComponentPrototypeFeature(
				this) };

	}

	@Override
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		if (getBusinessObjectForPictogramElement(context.getAnchor()) instanceof HardwarePin) {
			if (!(context.getSourceContainer() instanceof Diagram)) {
				return new MovePortsFeature(this);
			}

		}
		return super.getMoveAnchorFeature(context);
	}

	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) {
		return null;
	}

	private Object resolveSourceBusinessObject(AddConnectionContext context) {
		Object element = getBusinessObjectForPictogramElement(context
				.getSourceAnchor());
		Assert.isNotNull(element,
				"Could not resolve sourceObject from AddConnectionContext");
		return element;
	}

	private Object resolveTargetBusinessObject(AddConnectionContext context) {
		Object element = getBusinessObjectForPictogramElement(context
				.getTargetAnchor());
		Assert.isNotNull(element,
				"Could not resolve targetObject from AddConnectionContext");
		return element;
	}

	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (pictogramElement instanceof Connection) {
			return new RemoveFunctionConnectorFeature(this);
		}
		if (pictogramElement instanceof Shape) {
			return super.getRemoveFeature(context);
		}
		if (bo instanceof ContainerShape) {
			return null;
		}

		return super.getRemoveFeature(context);
	}

}
