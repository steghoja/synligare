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
package org.eclipse.eatop.examples.graphicaleditor.depemtpd.provider;

import org.eclipse.eatop.eastadl21.AnalysisFunctionType;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.add.AddAnalysisFunctionTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.DEPEMTPDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.DEPEMTPDUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.add.AddErrorModelTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.add.AddErrorModelTypeToFunctionTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.add.AddErrorModelTypeToHardwareComponentTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.create.CreateErrorModelTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.create.CreateErrorModelTypeToFunctionTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.create.CreateErrorModelTypeToHardwareComponentTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.port.AddFailureOutPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.port.AddFaultInPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.port.CreateFailureOutPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.port.CreateFaultInPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.port.MovePortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.remove.DeleteFaultFailurePortFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.remove.RemoveErrorModelTypeToFunctionTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depemtpd.remove.RemoveErrorModelTypeToHardwareComponentTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.dtpd.features.add.AddDesignFunctionTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.hctpd.features.add.AddHardwareComponentTypeFeature;
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

public class DEPEMTPDFeatureProvider extends DefaultFeatureProvider {

	public DEPEMTPDFeatureProvider(IDiagramTypeProvider dtp) {
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
		if(containerAlreadyExists(context))
			return super.getAddFeature(context);
		
		if (context.getNewObject() instanceof ErrorModelType) {
			return new AddErrorModelTypeFeature(this);
		} else if (context.getNewObject() instanceof AnalysisFunctionType) {
			return new AddAnalysisFunctionTypeFeature(this);
		} else if (context.getNewObject() instanceof DesignFunctionType) {
			return new AddDesignFunctionTypeFeature(this);
		} else if (context.getNewObject() instanceof HardwareComponentType) {
			return new AddHardwareComponentTypeFeature(this);
		} else if (context.getNewObject() instanceof FaultInPort) {
			return new AddFaultInPortFeature(this);
		} else if (context.getNewObject() instanceof FailureOutPort) {
			return new AddFailureOutPortFeature(this);
		}
		
		
		if (context instanceof AddConnectionContext
				&& ((AddConnectionContext) context).getSourceAnchor() != null
				&& ((AddConnectionContext) context).getTargetAnchor() != null) {
			
			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);
			if (sourceObject instanceof ErrorModelType
					&& targetObject instanceof FunctionType) {
				return new AddErrorModelTypeToFunctionTypeFeature(this);
			} else if (sourceObject instanceof ErrorModelType
					&& targetObject instanceof HardwareComponentType) {
				return new AddErrorModelTypeToHardwareComponentTypeFeature(this);
			}
		}
		return super.getAddFeature(context);
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {
				new CreateErrorModelTypeFeature(this),
				new CreateFaultInPortFeature(this),
				new CreateFailureOutPortFeature(this) };
	}

	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();

		if (pictogramElement instanceof Connection) {
			Object startElement = resolveSourceBusinessObject(context, (Connection) pictogramElement);
			Object endElement = resolveTargetBusinessObject(context, (Connection) pictogramElement);
			
			if (startElement instanceof ErrorModelType && endElement instanceof FunctionType) {
				return new RemoveErrorModelTypeToFunctionTypeFeature(this);
			} else if (startElement instanceof ErrorModelType && endElement instanceof HardwareComponentType) {
				return new RemoveErrorModelTypeToHardwareComponentTypeFeature(this);}
			}
		
			return super.getRemoveFeature(context);
		}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {

		IDeleteFeature ret = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof ContainerShape ) {
			return null;
		}
		
		if (bo instanceof FaultFailurePort) {
			FaultFailurePort elementBo = (FaultFailurePort) bo;
			elementBo.getShortName();
			return new DeleteFaultFailurePortFeature(this);

		}
		ret = new DefaultDeleteFeature(this);
		return ret;
	}
	
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
		       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		       if (bo instanceof  ErrorModelType) {
		           return new DEPEMTPDUpdateFeature(this);
		       }   
		   }
		return super.getUpdateFeature(context);
	}
	
	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		    if (bo instanceof ErrorModelType||bo instanceof  AnalysisFunctionType ||bo instanceof  DesignFunctionType||bo  instanceof  HardwareComponentType)
		    {
		    	return new DEPEMTPDLayoutFeature(this);
		    }
		return super.getLayoutFeature(context);
	}
	
	
	/*Connections*/
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() 
	{
		return new ICreateConnectionFeature[] 
				{
				new CreateErrorModelTypeToFunctionTypeFeature(this),
				new CreateErrorModelTypeToHardwareComponentTypeFeature(this)
				};
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
	public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
		return null;
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
	
	private Object resolveTargetBusinessObject(AddConnectionContext context) 
	{
		Object element = getBusinessObjectForPictogramElement(context.getTargetAnchor().getParent());
		Assert.isNotNull(element, "Could not resolve targetObject from AddConnectionContext");
		return element;
	}	
	
	@Override
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) 
	{
		if (getBusinessObjectForPictogramElement(context.getAnchor()) instanceof FaultInPort ||
			getBusinessObjectForPictogramElement(context.getAnchor()) instanceof FailureOutPort) 
	{
			return new MovePortsFeature(this);
	}
		return super.getMoveAnchorFeature(context);
	}

}
