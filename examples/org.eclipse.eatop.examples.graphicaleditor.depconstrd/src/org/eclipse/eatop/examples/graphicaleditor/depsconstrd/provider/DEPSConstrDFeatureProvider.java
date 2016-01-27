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
package org.eclipse.eatop.examples.graphicaleditor.depsconstrd.provider;


import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultFailure;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.eatop.eastadl21.InternalFaultPrototype;
import org.eclipse.eatop.eastadl21.ProcessFaultPrototype;
import org.eclipse.eatop.eastadl21.QuantitativeSafetyConstraint;
import org.eclipse.eatop.eastadl21.SafetyConstraint;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.connection.AddQuantitativeSafetyConstraintToFaultFailureFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.connection.AddSafetyConstraintToFaultFailureFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.connection.CreateQuantitativeSafetyConstraintToFaultFailureFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.connection.CreateSafetyConstraintToFaultFailureFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.element.AddFaultFailureFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.element.AddQuantitativeSafetyConstraintFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.element.AddSafetyConstraintFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.element.CreateFaultFailureFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.element.CreateQuantitativeSafetyConstraintFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.element.CreateSafetyConstraintFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.features.DEPSConstrDDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.features.DEPSConstrDDUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.features.PrototypeLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.instanceref.AddFaultFailureToFaultFailurePortFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.instanceref.CreateFaultFailureToFaultFailurePortFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.port.AddFailureOutPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.port.AddFaultInPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.port.MovePortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.prototype.AddErrorModelPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.remove.RemoveFaultFailureToFaultFailurePort;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.remove.RemoveQuantitativeSafetyConstraintToFaultFailure;
import org.eclipse.eatop.examples.graphicaleditor.depsconstrd.remove.RemoveSafetyConstraintToFaultFailure;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.prototypes.AddInternalFaultPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.emtd.features.prototypes.AddProcessFaultPrototypeFeature;
import org.eclipse.core.runtime.Assert;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveAnchorFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
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
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;


public class DEPSConstrDFeatureProvider extends DefaultFeatureProvider {

	public DEPSConstrDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
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
	
	public IAddFeature getAddFeature(IAddContext context) {
		
		if(containerAlreadyExists(context))
			return super.getAddFeature(context);

		if (context.getNewObject() instanceof SafetyConstraint) {
			return new AddSafetyConstraintFeature(this);
		} else if (context.getNewObject() instanceof QuantitativeSafetyConstraint) {
			return new AddQuantitativeSafetyConstraintFeature(this);
		} else if (context.getNewObject() instanceof FaultFailure) {
			return new AddFaultFailureFeature(this);
		//Anomaly
		} else if (context.getNewObject() instanceof ProcessFaultPrototype) {
			return new AddProcessFaultPrototypeFeature(this);
		} else if (context.getNewObject() instanceof InternalFaultPrototype) {
			return new AddInternalFaultPrototypeFeature(this);
		} else if (context.getNewObject() instanceof ErrorModelPrototype) {
			return new AddErrorModelPrototypeFeature(this);
		}else if (context.getNewObject() instanceof FailureOutPort) {
			return new AddFailureOutPortFeature(this);
		} else if (context.getNewObject() instanceof FaultInPort) {
				return new AddFaultInPortFeature(this);
		}
		if (context instanceof AddConnectionContext) {
			
			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);
			if (sourceObject instanceof QuantitativeSafetyConstraint && targetObject instanceof FaultFailure) {
				return new AddQuantitativeSafetyConstraintToFaultFailureFeature(this);
			} else if (sourceObject instanceof SafetyConstraint && targetObject instanceof FaultFailure) {
				return new AddSafetyConstraintToFaultFailureFeature(this);
			}
			if (((AddConnectionContext) context).getTargetAnchor().getLink()!=null){
			if (sourceObject instanceof FaultFailure
					&& ((AddConnectionContext) context).getTargetAnchor()
					.getLink().getBusinessObjects().get(0) instanceof FaultFailurePort) {
				return new AddFaultFailureToFaultFailurePortFeature(
				this);}
	}
		}
		return super.getAddFeature(context);
	}

	

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {
				new CreateSafetyConstraintFeature(this),
				new CreateQuantitativeSafetyConstraintFeature(this),
				new CreateFaultFailureFeature(this)
		};
	}
	
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof Connection) {
			Object startElement = resolveSourceBusinessObject(context,
					(Connection) pictogramElement);
			Object endElement = resolveTargetBusinessObject(context,
					(Connection) pictogramElement);

			if (startElement instanceof QuantitativeSafetyConstraint
					&& endElement instanceof FaultFailure) {
				return new RemoveQuantitativeSafetyConstraintToFaultFailure(
						this);
			} else if (startElement instanceof SafetyConstraint
					&& endElement instanceof FaultFailure) {
				return new RemoveSafetyConstraintToFaultFailure(this);
			}
			if (((Connection) pictogramElement).getEnd().getLink() != null) {
				if (startElement instanceof FaultFailure
						&& ((Connection) pictogramElement).getEnd().getLink()
								.getBusinessObjects().get(0) instanceof FaultFailurePort) {
					return new RemoveFaultFailureToFaultFailurePort(this);
				}
			}
		}
		return super.getRemoveFeature(context);
	}
	
	
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
	PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
		       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		       if (bo instanceof FaultFailure
			    		||bo instanceof SafetyConstraint 
			    		||bo instanceof QuantitativeSafetyConstraint
			    		||bo instanceof ErrorModelPrototype) {
		           return new DEPSConstrDDUpdateFeature(this);
		         
		       }   
		   }
		return super.getUpdateFeature(context);
	}
	
	
		@Override
		public ILayoutFeature getLayoutFeature(ILayoutContext context) {
			 PictogramElement pictogramElement = context.getPictogramElement();
			    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			    if (
			    	bo instanceof SafetyConstraint 
			    	||bo instanceof QuantitativeSafetyConstraint
			    	)
			    {
			    return new DEPSConstrDDLayoutFeature(this);
			    }
			    else if
			    (bo instanceof FaultFailure
			    ||bo instanceof ErrorModelPrototype)
			    return new PrototypeLayoutFeature(this);
			    
			return super.getLayoutFeature(context);
		}
		
		
	
	/*Connections*/
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {
				new CreateQuantitativeSafetyConstraintToFaultFailureFeature(this),
				new CreateSafetyConstraintToFaultFailureFeature(this),
				new CreateFaultFailureToFaultFailurePortFeature(this)
		};
	}
	


	private Object resolveSourceBusinessObject(IRemoveContext context, Connection element) {
		Object source = getBusinessObjectForPictogramElement(((Connection) element).getStart().getParent());
		if(!(source==null))
		{
		Assert.isNotNull(source, "Could not find BusinessObject for Connection start");
		}
		return source;
	}

	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) 
	{
		return null;
	}

	private Object resolveTargetBusinessObject(IRemoveContext context, Connection element) 
	{
		Object target = getBusinessObjectForPictogramElement(((Connection) element).getEnd().getParent());
		if(!(target==null))
		{
		Assert.isNotNull(target, "Could not find BusinessObject for Connection end");
		}
		return target;
	}
	
	private Object resolveSourceBusinessObject(AddConnectionContext context) {
		Object element = getBusinessObjectForPictogramElement(context.getSourceAnchor().getParent());
		Assert.isNotNull(element, "Could not resolve sourceObject from AddConnectionContext");
		return element;
	}
	
	private Object resolveTargetBusinessObject(AddConnectionContext context) {
		Object element = getBusinessObjectForPictogramElement(context.getTargetAnchor().getParent());
		Assert.isNotNull(element, "Could not resolve targetObject from AddConnectionContext");
		return element;
	}	
	
	
	@Override
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		if (getBusinessObjectForPictogramElement(context.getAnchor()) instanceof FaultInPort || getBusinessObjectForPictogramElement(context.getAnchor()) instanceof FailureOutPort) {
			if(!(context.getSourceContainer() instanceof Diagram))
			return new MovePortsFeature(this);
		}
		return super.getMoveAnchorFeature(context);
	}

}
