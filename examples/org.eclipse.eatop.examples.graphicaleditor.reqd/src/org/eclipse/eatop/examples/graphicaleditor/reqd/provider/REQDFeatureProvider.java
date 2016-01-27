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
package org.eclipse.eatop.examples.graphicaleditor.reqd.provider;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.DeriveRequirement;
import org.eclipse.eatop.eastadl21.OperationalSituation;
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsLink;
import org.eclipse.eatop.eastadl21.TraceableSpecification;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.QualityRequirementUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.RequirementLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.RequirementUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddDeriveRequirement;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddOperationalSituationFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddQualityRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddRequirementsLinkFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddUseCaseFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.create.CreateDeriveRequirement;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.create.CreateOperationalSituationFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.create.CreateQualityRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.create.CreateRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.create.CreateRequirementsLinkFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.create.CreateUseCaseFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.remove.RemoveDeriveRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.remove.RemoveRequirementLinkFeature;
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

public class REQDFeatureProvider extends DefaultFeatureProvider {

	public REQDFeatureProvider(IDiagramTypeProvider dtp) {
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
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		
		if(containerAlreadyExists(context))
			return super.getAddFeature(context);
		
		if (context.getNewObject() instanceof QualityRequirement) {
			return new AddQualityRequirementFeature(this);
		} else if (context.getNewObject() instanceof Requirement) {
	        return new AddRequirementFeature(this);
	    } else if (context.getNewObject() instanceof UseCase) {
	        return new AddUseCaseFeature(this);
	    } else if (context.getNewObject() instanceof OperationalSituation){ 
	        return new AddOperationalSituationFeature(this);
	    } if (context instanceof AddConnectionContext) {
			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);
			
			if (context.getNewObject() instanceof RequirementsLink&&sourceObject instanceof Requirement && targetObject instanceof Requirement) {
				return new AddRequirementsLinkFeature(this);
			}else if(context.getNewObject() instanceof DeriveRequirement&&sourceObject instanceof Requirement && targetObject instanceof Requirement){
				return new AddDeriveRequirement(this);
			}
		}
	    
	    return super.getAddFeature(context);

	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { 
				new CreateRequirementFeature(this),
				new CreateUseCaseFeature(this),
				new CreateOperationalSituationFeature(this),
				new CreateQualityRequirementFeature(this)
			};
	}

	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		 return new ICreateConnectionFeature[] { 
				 new CreateDeriveRequirement(this),
				 new CreateRequirementsLinkFeature(this)
		};
	}
	

	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		
		if (pictogramElement instanceof Connection) {
			Object startElement = resolveSourceBusinessObject(context, (Connection) pictogramElement);
			Object endElement = resolveTargetBusinessObject(context, (Connection) pictogramElement);
			
			if (context.getPictogramElement() instanceof RequirementsLink&&startElement instanceof Requirement && endElement instanceof Requirement) {
				return new RemoveRequirementLinkFeature(this);
			}else if (context.getPictogramElement() instanceof DeriveRequirement&&startElement instanceof Requirement && endElement instanceof Requirement) {
				return new RemoveDeriveRequirementFeature(this);
			}
		}
		return super.getRemoveFeature(context);
	}	
	
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
		       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
            if (bo instanceof QualityRequirement) {
		    	   return new QualityRequirementUpdateFeature(this);
		       }
		       if (bo instanceof TraceableSpecification) {
		           return new RequirementUpdateFeature(this);
		       }
		   }
		   return super.getUpdateFeature(context);
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
	    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
	    if (bo instanceof TraceableSpecification) {
	        return new RequirementLayoutFeature(this);
	    }
	    return super.getLayoutFeature(context);

	}
	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) {
		return null;
	}

	private Object resolveSourceBusinessObject(IRemoveContext context, Connection element) {
		Object source = getBusinessObjectForPictogramElement(((Connection) element).getStart().getParent());
		if(!(source == null)){
			Assert.isNotNull(source, "Could not find BusinessObject for Connection start");
		}
		
		return source;
	}

	private Object resolveTargetBusinessObject(IRemoveContext context, Connection element) {
		Object target = getBusinessObjectForPictogramElement(((Connection) element).getEnd().getParent());
		if(!(target == null)){
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
