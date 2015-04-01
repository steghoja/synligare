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
package org.eclipse.eatop.examples.graphicaleditor.emtd.provider;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.CommunicationHardwarePin;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.ErrorBehavior;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.IOHardwarePin;
import org.eclipse.eatop.eastadl21.InternalFaultPrototype;
import org.eclipse.eatop.eastadl21.PowerHardwarePin;
import org.eclipse.eatop.eastadl21.ProcessFaultPrototype;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddAnalysisFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionClientServerPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionInFlowPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionPowerPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.add.AddFunctionOutFlowPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.dftd.features.add.AddDesignFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.DeleteFaultFailurePortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.EMTDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.EMTDUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection.AddErrorBehaviorToFailureOutPortFeatureExternal;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection.AddErrorBehaviorToFailureOutPortFeatureInternal;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection.AddErrorBehaviorToFaultInPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection.AddErrorBehaviorToInternalFaultPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection.AddErrorBehaviorToProcessFaultPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection.CreateErrorBehaviorToFailureOutPortFeatureExternal;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection.CreateErrorBehaviorToFailureOutPortFeatureInternal;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection.CreateErrorBehaviorToFaultInPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection.CreateErrorBehaviorToInternalFaultPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection.CreateErrorBehaviorToProcessFaultPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.element.AddErrorBehaviorFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.element.AddFaultFailurePropagationLinkFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.element.CreateErrorBehaviorFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.element.CreateFaultFailurePropagationLinkFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.AddErrorModelPrototypeToFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.AddErrorModelPrototypeToHardwareComponentPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.AddFaultFailurePortToFunctionPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.AddFaultFailurePortToHardwarePinFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.AddFaultFailurePropagationLinkToFailureOutPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.AddFaultFailurePropagationLinkToFaultInPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.CreateErrorModelPrototypeToFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.CreateErrorModelPrototypeToHardwareComponentPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.CreateFaultFailurePortToFunctionPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.CreateFaultFailurePortToHardwarePinFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.CreateFaultFailurePropagationLinkToFailureOutPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref.CreateFaultFailurePropagationLinkToFaultInPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.port.AddFailureOutPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.port.AddFaultInPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.port.MovePortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.prototypes.AddErrorModelPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.prototypes.AddInternalFaultPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.prototypes.AddProcessFaultPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.prototypes.CreateErrorModelPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.prototypes.CreateInternalFaultPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.prototypes.CreateProcessFaultPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.remove.RemoveErrorBehaviorToFailureOutPort;
import org.eclipse.eatop.examples.graphicaleditor.emtd.remove.RemoveErrorBehaviorToFaultInPort;
import org.eclipse.eatop.examples.graphicaleditor.emtd.remove.RemoveErrorBehaviorToInternalFaultPrototype;
import org.eclipse.eatop.examples.graphicaleditor.emtd.remove.RemoveErrorBehaviorToProcessFaultPrototype;
import org.eclipse.eatop.examples.graphicaleditor.emtd.remove.RemoveErrorModelPrototypeToFunctionPrototype;
import org.eclipse.eatop.examples.graphicaleditor.emtd.remove.RemoveErrorModelPrototypeToHardwareComponentPrototype;
import org.eclipse.eatop.examples.graphicaleditor.emtd.remove.RemoveFaultFailurePortToFunctionPort;
import org.eclipse.eatop.examples.graphicaleditor.emtd.remove.RemoveFaultFailurePortToHardwarePin;
import org.eclipse.eatop.examples.graphicaleditor.emtd.remove.RemoveFaultFailurePropagationLinkToFailureOutPort;
import org.eclipse.eatop.examples.graphicaleditor.emtd.remove.RemoveFaultFailurePropagationLinkToFaultInPort;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.add.AddCommunicationHardwarePinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.add.AddHardwareComponentPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.add.AddIOHardwarePinFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.add.AddPowerHardwarePinFeature;
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
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class EMTDFeatureProvider extends DefaultFeatureProvider {

	public static char internalexternal = '-';

	public EMTDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
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

	public IAddFeature getAddFeature(IAddContext context) {

		if (containerAlreadyExists(context))
			return super.getAddFeature(context);

		if (context.getNewObject() instanceof ErrorModelPrototype) {
			return new AddErrorModelPrototypeFeature(this);
		} else if (context.getNewObject() instanceof ProcessFaultPrototype) {
			return new AddProcessFaultPrototypeFeature(this);
		} else if (context.getNewObject() instanceof InternalFaultPrototype) {
			return new AddInternalFaultPrototypeFeature(this);
		} else if (context.getNewObject() instanceof FaultFailurePropagationLink) {
			return new AddFaultFailurePropagationLinkFeature(this);
		} else if (context.getNewObject() instanceof ErrorBehavior) {
			return new AddErrorBehaviorFeature(this);
		} else if (context.getNewObject() instanceof AnalysisFunctionPrototype) {
			return new AddAnalysisFunctionPrototypeFeature(this);
		} else if (context.getNewObject() instanceof DesignFunctionPrototype) {
			return new AddDesignFunctionPrototypeFeature(this);
		} else if (context.getNewObject() instanceof HardwareComponentPrototype) {
			return new AddHardwareComponentPrototypeFeature(this);
		} else if (context.getNewObject() instanceof IOHardwarePin) {
			return new AddIOHardwarePinFeature(this);
		} else if (context.getNewObject() instanceof PowerHardwarePin) {
			return new AddPowerHardwarePinFeature(this);
		} else if (context.getNewObject() instanceof CommunicationHardwarePin) {
			return new AddCommunicationHardwarePinFeature(this);
		} else if (context.getNewObject() instanceof FunctionFlowPort) {
			return new AddFunctionInFlowPortFeature(this);
		} else if (context.getNewObject() instanceof FunctionFlowPort) {
			return new AddFunctionOutFlowPortFeature(this);
		} else if (context.getNewObject() instanceof FunctionClientServerPort) {
			return new AddFunctionClientServerPortFeature(this);
		} else if (context.getNewObject() instanceof FunctionPowerPort) {
			return new AddFunctionPowerPortFeature(this);
		} else if (context.getNewObject() instanceof FaultInPort) {
			return new AddFaultInPortFeature(this);
		} else if (context.getNewObject() instanceof FailureOutPort) {
			return new AddFailureOutPortFeature(this);
		}
		if (context instanceof AddConnectionContext) {
			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);

			if (((AddConnectionContext) context).getTargetAnchor().getLink()// only
																			// for
																			// TargetPorts
			!= null) {
				if (sourceObject instanceof ErrorBehavior
						&& ((AddConnectionContext) context).getTargetAnchor()
								.getLink().getBusinessObjects().get(0) instanceof FaultInPort) {
					return new AddErrorBehaviorToFaultInPortFeature(this);
				} else if (sourceObject instanceof ErrorBehavior
						&& ((AddConnectionContext) context).getTargetAnchor()
								.getLink().getBusinessObjects().get(0) instanceof FailureOutPort
						&& internalexternal == 'e') {
					return new AddErrorBehaviorToFailureOutPortFeatureExternal(
							this);
				} else if (sourceObject instanceof ErrorBehavior
						&& ((AddConnectionContext) context).getTargetAnchor()
								.getLink().getBusinessObjects().get(0) instanceof FailureOutPort
						&& internalexternal == 'i') {
					return new AddErrorBehaviorToFailureOutPortFeatureInternal(
							this);
				} else if (sourceObject instanceof FaultFailurePropagationLink
						&& ((AddConnectionContext) context).getTargetAnchor()
								.getLink().getBusinessObjects().get(0) instanceof FaultInPort) {
					return new AddFaultFailurePropagationLinkToFaultInPortFeature(
							this);
				} else if (sourceObject instanceof FaultFailurePropagationLink
						&& ((AddConnectionContext) context).getTargetAnchor()
								.getLink().getBusinessObjects().get(0) instanceof FailureOutPort) {
					return new AddFaultFailurePropagationLinkToFailureOutPortFeature(
							this);
				}
				if (((AddConnectionContext) context).getSourceAnchor()
						.getLink() != null) {// only for SourcePorts
					if (((AddConnectionContext) context).getSourceAnchor()
							.getLink().getBusinessObjects().get(0) instanceof FaultFailurePort
							&& ((AddConnectionContext) context)
									.getTargetAnchor().getLink()
									.getBusinessObjects().get(0) instanceof HardwarePin) {
						return new AddFaultFailurePortToHardwarePinFeature(this);
					} else if (((AddConnectionContext) context)
							.getSourceAnchor().getLink().getBusinessObjects()
							.get(0) instanceof FaultFailurePort
							&& ((AddConnectionContext) context)
									.getTargetAnchor().getLink()
									.getBusinessObjects().get(0) instanceof FunctionPort) {
						return new AddFaultFailurePortToFunctionPortFeature(
								this);
					}
				}
			} else if (sourceObject instanceof ErrorBehavior
					&& targetObject instanceof InternalFaultPrototype) {
				return new AddErrorBehaviorToInternalFaultPrototypeFeature(this);
			} else if (sourceObject instanceof ErrorBehavior
					&& targetObject instanceof ProcessFaultPrototype) {

				return new AddErrorBehaviorToProcessFaultPrototypeFeature(this);
			} else if (sourceObject instanceof ErrorModelPrototype
					&& targetObject instanceof FunctionPrototype) {
				return new AddErrorModelPrototypeToFunctionPrototypeFeature(
						this);
			} else if (sourceObject instanceof ErrorModelPrototype
					&& targetObject instanceof HardwareComponentPrototype) {
				return new AddErrorModelPrototypeToHardwareComponentPrototypeFeature(
						this);
			}

		}
		return super.getAddFeature(context);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {
				new CreateErrorModelPrototypeFeature(this),
				new CreateProcessFaultPrototypeFeature(this),
				new CreateInternalFaultPrototypeFeature(this),
				new CreateFaultFailurePropagationLinkFeature(this),
				new CreateErrorBehaviorFeature(this)

		};
	}

	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {
				new CreateErrorBehaviorToFaultInPortFeature(this),
				new CreateErrorBehaviorToFailureOutPortFeatureExternal(this),
				new CreateErrorBehaviorToFailureOutPortFeatureInternal(this),
				new CreateErrorBehaviorToInternalFaultPrototypeFeature(this),
				new CreateErrorBehaviorToProcessFaultPrototypeFeature(this),
				new CreateErrorModelPrototypeToFunctionPrototypeFeature(this),
				new CreateErrorModelPrototypeToHardwareComponentPrototypeFeature(
						this),
				new CreateFaultFailurePropagationLinkToFaultInPortFeature(this),
				new CreateFaultFailurePropagationLinkToFailureOutPortFeature(
						this),
				new CreateFaultFailurePortToHardwarePinFeature(this),
				new CreateFaultFailurePortToFunctionPortFeature(this)

		};
	}

	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof Connection) {
			Object startElement = resolveSourceBusinessObject(context,
					(Connection) pictogramElement);
			Object endElement = resolveTargetBusinessObject(context,
					(Connection) pictogramElement);

			if (((Connection) pictogramElement).getEnd().getLink() != null) {// only
																				// for
																				// TargetPorts
				if (startElement instanceof ErrorBehavior
						&& ((Connection) pictogramElement).getEnd().getLink()
								.getBusinessObjects().get(0) instanceof FailureOutPort) {
					return new RemoveErrorBehaviorToFailureOutPort(this);
				} else if (startElement instanceof ErrorBehavior
						&& ((Connection) pictogramElement).getEnd().getLink()
								.getBusinessObjects().get(0) instanceof FaultInPort) {
					return new RemoveErrorBehaviorToFaultInPort(this);
				} else if (startElement instanceof FaultFailurePropagationLink
						&& ((Connection) pictogramElement).getEnd().getLink()
								.getBusinessObjects().get(0) instanceof FaultInPort) {
					return new RemoveFaultFailurePropagationLinkToFaultInPort(
							this);
				} else if (startElement instanceof FaultFailurePropagationLink
						&& ((Connection) pictogramElement).getEnd().getLink()
								.getBusinessObjects().get(0) instanceof FailureOutPort) {
					return new RemoveFaultFailurePropagationLinkToFailureOutPort(
							this);
				}
				if (((Connection) pictogramElement).getStart().getLink() != null) {// only
																					// for
																					// SourcePorts

					if (((Connection) pictogramElement).getStart().getLink()
							.getBusinessObjects().get(0) instanceof FaultFailurePort
							&& ((Connection) pictogramElement).getEnd()
									.getLink().getBusinessObjects().get(0) instanceof FunctionPort) {
						return new RemoveFaultFailurePortToFunctionPort(this);
					} else if (((Connection) pictogramElement).getStart()
							.getLink().getBusinessObjects().get(0) instanceof FaultFailurePort
							&& ((Connection) pictogramElement).getEnd()
									.getLink().getBusinessObjects().get(0) instanceof HardwarePin) {
						return new RemoveFaultFailurePortToHardwarePin(this);
					}
				}
			} else if (startElement instanceof ErrorBehavior
					&& endElement instanceof InternalFaultPrototype) {
				return new RemoveErrorBehaviorToInternalFaultPrototype(this);
			} else if (startElement instanceof ErrorBehavior
					&& endElement instanceof ProcessFaultPrototype) {
				return new RemoveErrorBehaviorToProcessFaultPrototype(this);
			} else if (startElement instanceof ErrorModelPrototype
					&& endElement instanceof FunctionPrototype) {
				return new RemoveErrorModelPrototypeToFunctionPrototype(this);
			} else if (startElement instanceof ErrorModelPrototype
					&& endElement instanceof HardwareComponentPrototype) {
				return new RemoveErrorModelPrototypeToHardwareComponentPrototype(
						this);
			}
		}
		return super.getRemoveFeature(context);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			if (bo instanceof ErrorModelPrototype
	        		|| bo instanceof ProcessFaultPrototype
	        		|| bo instanceof InternalFaultPrototype
	        		|| bo instanceof ErrorBehavior
	        		|| bo instanceof FaultFailurePropagationLink) {
				return new EMTDUpdateFeature(this);
			}
		}
		return super.getUpdateFeature(context);

	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof ErrorModelPrototype
				|| bo instanceof ProcessFaultPrototype
				|| bo instanceof InternalFaultPrototype
				|| bo instanceof FaultFailurePropagationLink
				|| bo instanceof ErrorBehavior
				|| bo instanceof AnalysisFunctionPrototype
				|| // FunctionPrototype
				bo instanceof DesignFunctionPrototype
				|| // FunctionPrototype
				bo instanceof IOHardwarePin
				|| // HardwarePin
				bo instanceof PowerHardwarePin
				|| // HardwarePin
				bo instanceof CommunicationHardwarePin
				|| // HardwarePin
				bo instanceof FunctionFlowPort
				|| // FunctionPort
				bo instanceof FunctionPowerPort
				|| // FunctionPort
				bo instanceof FunctionClientServerPort
				|| // FunctionPort
				bo instanceof HardwareComponentPrototype
				|| bo instanceof FaultInPort || // FaultFailurePort
				bo instanceof FailureOutPort // FaultFailurePort
		) {
			return new EMTDLayoutFeature(this);
		}
		return super.getLayoutFeature(context);
	}

	private Object resolveSourceBusinessObject(IRemoveContext context,
			Connection element) {
		Object source = getBusinessObjectForPictogramElement(((Connection) element)
				.getStart().getParent());
		if (!(source == null)) {
			Assert.isNotNull(source,
					"Could not find BusinessObject for Connection start");
		}
		return source;
	}

	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) {
		return null;
	}

	private Object resolveTargetBusinessObject(IRemoveContext context,
			Connection element) {
		Object target = getBusinessObjectForPictogramElement(((Connection) element)
				.getEnd().getParent());
		if (!(target == null)) {
			Assert.isNotNull(target,
					"Could not find BusinessObject for Connection end");
		}
		return target;
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

	@Override
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		if (getBusinessObjectForPictogramElement(context.getAnchor()) instanceof FaultInPort
				|| getBusinessObjectForPictogramElement(context.getAnchor()) instanceof FailureOutPort) {
			if (!(context.getSourceContainer() instanceof Diagram))
				return new MovePortsFeature(this);
		}
		return super.getMoveAnchorFeature(context);
	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {

		IDeleteFeature ret = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof ContainerShape) {
			return null;
		}
		if (bo instanceof FaultFailurePort) {
			FaultFailurePort elementBo = (FaultFailurePort) bo;
			elementBo.getShortName();
			return new DeleteFaultFailurePortsFeature(this);

		}

		ret = new DefaultDeleteFeature(this);
		return ret;
	}
}
