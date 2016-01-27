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
package org.eclipse.eatop.examples.graphicaleditor.bd.provider;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisFunctionType;
import org.eclipse.eatop.eastadl21.Behavior;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.FunctionBehavior;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionTrigger;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.ModeGroup;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddAnalysisFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionClientServerPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionInFlowPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionPowerPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.BDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.BDSelectLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.BDUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddFunctionBehaviorFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddFunctionBehaviorToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddFunctionTriggerFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddFunctionTriggerToFunctionPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddFunctionTriggerToFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddFunctionTriggerToFunctionTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddFunctionTriggerToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddFunctionTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddModeGroupFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.create.CreateFunctionBehaviorFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.create.CreateFunctionBehaviorToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.create.CreateFunctionTriggerFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.create.CreateFunctionTriggerToFunctionPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.create.CreateFunctionTriggerToFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.create.CreateFunctionTriggerToFunctionTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.create.CreateFunctionTriggerToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.create.CreateModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.create.CreateModeGroupFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.remove.RemoveFunctionBehaviorToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.remove.RemoveFunctionTriggerToFunctionPort;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.remove.RemoveFunctionTriggerToFunctionPrototype;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.remove.RemoveFunctionTriggerToFunctionType;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.remove.RemoveFunctionTriggerToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.dftd.features.add.AddDesignFunctionPrototypeFeature;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
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




public class BDFeatureProvider extends DefaultFeatureProvider {

	/*
	 * 0.5 second after a functionType is dragged & dropped, no other elements can be placed in the diagram.
	without the 0,5 second border the program automatically inserts all elements below in the hierarchy 
	when selecting a FunctionType
	*/
	public static long timeOfNewFunctionType;
	public static long border;
	
	public BDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}
	//public static int counter;
	
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

		border = System.currentTimeMillis();

		if (context.getNewObject() instanceof AnalysisFunctionType
				|| context.getNewObject() instanceof DesignFunctionType) {
			return new AddFunctionTypeFeature(this);
		} else if (((border - timeOfNewFunctionType) / 1000) > 0.5) {//0,5 seconds
			if (context.getNewObject() instanceof AnalysisFunctionPrototype) {
				return new AddAnalysisFunctionPrototypeFeature(this);
			} else if (context.getNewObject() instanceof DesignFunctionPrototype) {
				return new AddDesignFunctionPrototypeFeature(this);
			} else if (context.getNewObject() instanceof FunctionClientServerPort) {
				return new AddFunctionClientServerPortFeature(this);
			} else if (context.getNewObject() instanceof ModeGroup) {
				return new AddModeGroupFeature(this);
			} else if (context.getNewObject() instanceof Mode) {
				return new AddModeFeature(this);
			} else if (context.getNewObject() instanceof FunctionBehavior) {
				return new AddFunctionBehaviorFeature(this);
			} else if (context.getNewObject() instanceof FunctionTrigger) {
				return new AddFunctionTriggerFeature(this);
			} else if (context.getNewObject() instanceof FunctionFlowPort) {
				return new AddFunctionInFlowPortFeature(this);
			} else if (context.getNewObject() instanceof FunctionPowerPort) {
				return new AddFunctionPowerPortFeature(this);
			}
		}

		if (context instanceof AddConnectionContext
				&& ((AddConnectionContext) context).getSourceAnchor() != null
				&& ((AddConnectionContext) context).getTargetAnchor() != null) {
			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);
			if (sourceObject instanceof FunctionBehavior
					&& targetObject instanceof Mode) {
				return new AddFunctionBehaviorToModeFeature(this);
			} else if (sourceObject instanceof FunctionTrigger
					&& targetObject instanceof Mode) {
				return new AddFunctionTriggerToModeFeature(this);
			} else if (sourceObject instanceof FunctionTrigger
					&& targetObject instanceof FunctionType) {
				return new AddFunctionTriggerToFunctionTypeFeature(this);
			} else if (sourceObject instanceof FunctionTrigger
					&& targetObject instanceof FunctionPrototype) {
				return new AddFunctionTriggerToFunctionPrototypeFeature(this);
			} else if (sourceObject instanceof FunctionTrigger
					&& targetObject instanceof Behavior) {
				return new AddFunctionTriggerToFunctionPortFeature(this);
			}
		
		}
		return super.getAddFeature(context);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		
		return new ICreateFeature[] { 
				new CreateModeGroupFeature(this),
				new CreateModeFeature(this),
				new CreateFunctionBehaviorFeature(this),
				new CreateFunctionTriggerFeature(this)};
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {
				new CreateFunctionBehaviorToModeFeature(this),
				new CreateFunctionTriggerToModeFeature(this),
				new CreateFunctionTriggerToFunctionTypeFeature(this),
				new CreateFunctionTriggerToFunctionPrototypeFeature(this),
				new CreateFunctionTriggerToFunctionPortFeature(this)
		};
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (
			  bo instanceof Mode 
			||bo instanceof FunctionBehavior
			||bo instanceof FunctionTrigger
		) 
		{
			return new BDLayoutFeature(this);
		}
		else if 
		(
			  bo instanceof FunctionFlowPort // FunctionPort
			||bo instanceof FunctionPowerPort // FunctionPort
			||bo instanceof FunctionClientServerPort // FunctionPort
			||bo instanceof AnalysisFunctionPrototype // FunctionPrototype
			||bo instanceof DesignFunctionPrototype // FunctionPrototype
			||bo instanceof AnalysisFunctionType // FunctionPrototype
			||bo instanceof DesignFunctionType // FunctionPrototype
		)
		{
			return new BDSelectLayoutFeature(this);
		}

		return super.getLayoutFeature(context);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
		       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		       if (bo instanceof Mode||bo instanceof ModeGroup||bo instanceof FunctionBehavior||bo instanceof FunctionTrigger) {
		           return new BDUpdateFeature(this);
		       }
		       }
		return super.getUpdateFeature(context);
	}
	
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof Connection) {
			
			Object startElement = resolveSourceBusinessObject(context, (Connection) pictogramElement);
			Object endElement = resolveTargetBusinessObject(context, (Connection) pictogramElement);
			 if (startElement instanceof FunctionBehavior && endElement instanceof Mode) {
				return new RemoveFunctionBehaviorToModeFeature(this);
			} else if (startElement instanceof FunctionTrigger && endElement instanceof Mode) {
				return new RemoveFunctionTriggerToModeFeature(this);
			} else if (startElement instanceof FunctionTrigger && endElement instanceof FunctionPrototype) {
				return new RemoveFunctionTriggerToFunctionPrototype(this);
			} else if (startElement instanceof FunctionTrigger && endElement instanceof FunctionType) {
				return new RemoveFunctionTriggerToFunctionType(this);
			} else if (startElement instanceof FunctionTrigger && ((Connection) pictogramElement).getEnd().getLink().getBusinessObjects().get(0) instanceof FunctionPort) {
				return new RemoveFunctionTriggerToFunctionPort(this);
			}
			
		}
		return super.getRemoveFeature(context);
	}
	
	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) {
		return null;
	}
	
	private Object resolveSourceBusinessObject(IRemoveContext context, Connection element) {
		Object source = getBusinessObjectForPictogramElement(((Connection) element).getStart().getParent());
		if(!(source==null)){
		Assert.isNotNull(source, "Could not find BusinessObject for Connection start");
		}
		return source;
	}

	private Object resolveTargetBusinessObject(IRemoveContext context, Connection element) {
		Object target = getBusinessObjectForPictogramElement(((Connection) element).getEnd().getParent());
		if(!(target==null)){
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
	
}
