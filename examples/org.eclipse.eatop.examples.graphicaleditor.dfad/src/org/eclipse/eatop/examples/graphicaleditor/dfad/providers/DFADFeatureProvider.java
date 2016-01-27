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
package org.eclipse.eatop.examples.graphicaleditor.dfad.providers;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.FunctionAllocation;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.examples.graphicaleditor.dfad.delete.DeleteFunctionPortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.dfad.features.DFADLayoutEClassFeature;
import org.eclipse.eatop.examples.graphicaleditor.dfad.features.DFADUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.dfad.features.FunctionTypeLayout;
import org.eclipse.eatop.examples.graphicaleditor.dfad.features.add.AddFunctionAllocationFeature;
import org.eclipse.eatop.examples.graphicaleditor.dfad.features.add.AddPortsNameBoxFeature;
import org.eclipse.eatop.examples.graphicaleditor.dfad.features.create.CreateFunctionAllocationConnectorFeature;
import org.eclipse.eatop.examples.graphicaleditor.dfad.remove.RemoveFunctionAllocation;
import org.eclipse.eatop.examples.graphicaleditor.dftd.features.add.AddDesignFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctd.features.add.AddHardwareComponentPrototypeFeature;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;


public class DFADFeatureProvider extends DefaultFeatureProvider {
	public DFADFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}
	
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
			   Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			   if (bo instanceof FunctionType || bo instanceof FunctionAllocation) {
		           return new DFADUpdateFeature(this);
		       }
		   }
		return super.getUpdateFeature(context);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.features.impl.AbstractFeatureProvider#getCreateConnectionFeatures()
	 */
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] { 
				 new CreateFunctionAllocationConnectorFeature(this)
		};
	}

	
	
	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		
		
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof AnalysisFunctionPrototype

		|| bo instanceof DesignFunctionPrototype || bo instanceof HardwareComponentPrototype || bo instanceof FunctionAllocation) {

			return new FunctionTypeLayout(this);
		}
		if (bo instanceof EAPackage || bo instanceof Text) {
			return new DFADLayoutEClassFeature(this);
		}

		return super.getLayoutFeature(context);

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
		
		
//		if (context.getNewObject() instanceof FunctionAllocation) {
//			return new AddFunctionAllocationTypeFeature(this);
//		} else 
		if (context.getNewObject() instanceof Text) {
			return new AddPortsNameBoxFeature(this);
		} else if (context.getNewObject() instanceof DesignFunctionPrototype) {
			return new AddDesignFunctionPrototypeFeature(this);
		} else if (context.getNewObject() instanceof HardwareComponentPrototype) {
			return new AddHardwareComponentPrototypeFeature(this);
		}if (context instanceof AddConnectionContext) {
			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);
			
			if (context.getNewObject() instanceof FunctionAllocation && sourceObject instanceof DesignFunctionPrototype && targetObject instanceof HardwareComponentPrototype) {
				return new AddFunctionAllocationFeature(this);
			}
			if (context.getNewObject() instanceof FunctionAllocation && sourceObject instanceof HardwareComponentPrototype && targetObject instanceof DesignFunctionPrototype) {
				return new AddFunctionAllocationFeature(this);
			}
		}
		return super.getAddFeature(context);

	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {
				};

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
	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) {
		return null;
	}
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof ContainerShape ) {
			return null;
		}
		if (pictogramElement instanceof Connection) {
			return new RemoveFunctionAllocation(this);
			}
		return new DefaultRemoveFeature(this);

	}


	@Override
	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		// simply return all create connection features
		return getCreateConnectionFeatures();

	}
	private Object resolveSourceBusinessObject(AddConnectionContext context) {
		ContainerShape econ = (ContainerShape) context.getSourceAnchor().eContainer();
		EObject object = econ.getLink().getBusinessObjects().get(0);
		
		Assert.isNotNull(object, "Could not resolve sourceObject from AddConnectionContext");
		return object;
	}
	
	private Object resolveTargetBusinessObject(AddConnectionContext context) {
		ContainerShape econ2 = (ContainerShape) context.getTargetAnchor().eContainer();
		EObject object2 = econ2.getLink().getBusinessObjects().get(0);
		Assert.isNotNull(object2, "Could not resolve targetObject from AddConnectionContext");
		return object2;
	}	
}
