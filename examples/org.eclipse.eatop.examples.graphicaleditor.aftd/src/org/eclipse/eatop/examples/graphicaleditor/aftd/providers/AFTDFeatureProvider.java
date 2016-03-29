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
package org.eclipse.eatop.examples.graphicaleditor.aftd.providers;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.FunctionalDevice;
import org.eclipse.eatop.examples.graphicaleditor.aftd.delete.DeleteFunctionPortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.AFTDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.AFTDUpdateEClassFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.AnalysisFunctionPrototypeLayout;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddAnalysisFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionClientServerPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionConnector;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionInFlowPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.add.AddFunctionPowerPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.create.CreateAnalysisFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.create.CreateFunctionConnector;
import org.eclipse.eatop.examples.graphicaleditor.aftd.features.move.MovePortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.aftd.remove.RemoveFunctionConnectorFeature;
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






public class AFTDFeatureProvider extends DefaultFeatureProvider{
	public AFTDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
		       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		       if (bo instanceof AnalysisFunctionPrototype) {
		           return new AFTDUpdateEClassFeature(this);
		       }
		   }
		   return super.getUpdateFeature(context);

	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		 return new ICreateConnectionFeature[] { 
				 new CreateFunctionConnector(this)
		};
	}
	

	@Override 
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
	    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
	    if (bo instanceof AnalysisFunctionPrototype) {
	    		
	        return new AnalysisFunctionPrototypeLayout(this);
	    }
	    if (bo instanceof EAPackage||bo instanceof Text)
	    {
	    	return new AFTDLayoutFeature(this);
	    }
	    return super.getLayoutFeature(context);
	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {

		IDeleteFeature ret = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof ContainerShape ) {
			return null;
		}
		if(bo instanceof FunctionPort)
		{
			FunctionPort elementBo = (FunctionPort) bo ;
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
	        return new AddAnalysisFunctionPrototypeFeature(this);
	    } else if (context.getNewObject() instanceof AnalysisFunctionPrototype) {
	        return new AddAnalysisFunctionPrototypeFeature(this);
	    } else if (context.getNewObject() instanceof FunctionFlowPort) {
	        return new AddFunctionInFlowPortFeature(this);
	    } else if (context.getNewObject() instanceof FunctionClientServerPort) {
	        return new AddFunctionClientServerPortFeature(this);
	    } else if (context.getNewObject() instanceof FunctionPowerPort) {
	        return new AddFunctionPowerPortFeature(this);
	    }
		
		if (context instanceof AddConnectionContext) {
				Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
				Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);
				if (context.getNewObject() instanceof FunctionConnector && sourceObject instanceof FunctionPort && targetObject instanceof FunctionPort) {
					return new AddFunctionConnector(this);
				}
			}
		
	    return super.getAddFeature(context);
	}

	
	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {new CreateAnalysisFunctionPrototypeFeature(this)
		};
	}


	
	@Override
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		if (getBusinessObjectForPictogramElement(context.getAnchor()) instanceof FunctionPort ) {
			if(!(context.getSourceContainer() instanceof Diagram)){
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
		Object element = getBusinessObjectForPictogramElement(context.getSourceAnchor());
		Assert.isNotNull(element, "Could not resolve sourceObject from AddConnectionContext");
		return element;
	}
	
	private Object resolveTargetBusinessObject(AddConnectionContext context) {
		Object element = getBusinessObjectForPictogramElement(context.getTargetAnchor());
		Assert.isNotNull(element, "Could not resolve targetObject from AddConnectionContext");
		return element;
	}	
	
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (pictogramElement instanceof Connection) {
			return new RemoveFunctionConnectorFeature(this);
		}
		if (pictogramElement instanceof Shape)
		{
			return super.getRemoveFeature(context);
		}
		if (bo instanceof ContainerShape ) {
			return null;
		}
		
				
			
		
		return super.getRemoveFeature(context);
	}

	
	
	
	

}
