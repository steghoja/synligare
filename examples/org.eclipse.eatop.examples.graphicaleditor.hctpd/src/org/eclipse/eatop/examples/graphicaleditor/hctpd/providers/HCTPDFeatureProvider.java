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
package org.eclipse.eatop.examples.graphicaleditor.hctpd.providers;

import org.eclipse.eatop.eastadl21.Actuator;
import org.eclipse.eatop.eastadl21.CommunicationHardwarePin;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.ElectricalComponent;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.IOHardwarePin;
import org.eclipse.eatop.eastadl21.Node;
import org.eclipse.eatop.eastadl21.PowerHardwarePin;
import org.eclipse.eatop.eastadl21.Sensor;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.delete.DeleteFunctionPortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.FunctionTypeLayout;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.HCTPDLayoutEClassFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.HCTPDUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddActuatorFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddCommunicationHardwarePinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddElectricalComponentFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddHardwareComponentTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddHardwareComponentTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddIOHardwarePinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddNodeFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddPortsNameBoxFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddPowerHardwarePinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddSensorFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateActuatorFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateCommunicationHardwareInOutPinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateCommunicationHardwareInPinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateCommunicationHardwareOutPinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateElectricalComponentFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateHardwareComponentTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateHardwareComponentTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateIOHardwareInOutPinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateIOHardwareInPinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateIOHardwareOutPinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateNodeFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreatePowerHardwareInOutPinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreatePowerHardwareInPinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreatePowerHardwareOutPinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.create.CreateSensorFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.move.MovePortsFeature;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveAnchorFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;


public class HCTPDFeatureProvider extends DefaultFeatureProvider {
	public HCTPDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
			   Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			   if (bo instanceof FunctionType) {
		           return new HCTPDUpdateFeature(this);
		       }
		   }
		return super.getUpdateFeature(context);
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof HardwareComponentType) {

			return new FunctionTypeLayout(this);
		}
		if (bo instanceof EAPackage || bo instanceof Text) {
			return new HCTPDLayoutEClassFeature(this);
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

		if (context.getNewObject() instanceof Node) {
			return new AddNodeFeature(this);
		}else if (context.getNewObject() instanceof Actuator) {
			return new AddActuatorFeature(this);
		}else if (context.getNewObject() instanceof Sensor) {
			return new AddSensorFeature(this);
		}else if (context.getNewObject() instanceof ElectricalComponent) {
			return new AddElectricalComponentFeature(this);
		}else if (context.getNewObject() instanceof HardwareComponentType) {
			return new AddHardwareComponentTypeFeature(this);
		} else if (context.getNewObject() instanceof CommunicationHardwarePin) {
			return new AddCommunicationHardwarePinFeature(this);
		} else if (context.getNewObject() instanceof PowerHardwarePin) {
			return new AddPowerHardwarePinFeature(this);
		} else if (context.getNewObject() instanceof IOHardwarePin) {
			return new AddIOHardwarePinFeature(this);
		} else if (context.getNewObject() instanceof EAPackage) {
			return new AddHardwareComponentTypePackageFeature(this);
		} else if (context.getNewObject() instanceof Text) {
			return new AddPortsNameBoxFeature(this);
		}
		return super.getAddFeature(context);

	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {
				new CreateHardwareComponentTypePackageFeature(this),
				new CreateHardwareComponentTypeFeature(this),
				new CreateNodeFeature(this),
				new CreateElectricalComponentFeature(this),
				new CreateSensorFeature(this),
				new CreateActuatorFeature(this),
				new CreateCommunicationHardwareOutPinFeature(this),
				new CreateCommunicationHardwareInPinFeature(this),
				new CreateCommunicationHardwareInOutPinFeature(this),
				new CreatePowerHardwareInOutPinFeature(this),
				new CreatePowerHardwareInPinFeature(this),
				new CreatePowerHardwareOutPinFeature(this),
				new CreateIOHardwareInOutPinFeature(this),
				new CreateIOHardwareInPinFeature(this),
				new CreateIOHardwareOutPinFeature(this) };

	}
	
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {

		IDeleteFeature ret = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof ContainerShape ) {
			return null;
		}
		
		if(bo instanceof HardwarePin)
		{
			HardwarePin elementBo = (HardwarePin) bo ;
			elementBo.getShortName();
			return new DeleteFunctionPortsFeature(this);
			 
		}
		
		
		ret = new DefaultDeleteFeature(this);
		return ret;
	}
	
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof ContainerShape ) {
			return null;
		}
		return new DefaultRemoveFeature(this);

	}


	@Override
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		if (getBusinessObjectForPictogramElement(context.getAnchor()) instanceof HardwarePin) {
			return new MovePortsFeature(this);
		}
		return super.getMoveAnchorFeature(context);
	}

	@Override
	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		// simply return all create connection features
		return getCreateConnectionFeatures();

	}

}
