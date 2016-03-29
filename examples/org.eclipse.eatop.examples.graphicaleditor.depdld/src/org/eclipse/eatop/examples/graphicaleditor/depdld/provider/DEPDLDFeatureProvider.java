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
package org.eclipse.eatop.examples.graphicaleditor.depdld.provider;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.TechnicalSafetyConcept;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.eatop.examples.graphicaleditor.depdld.connection.AddSatisfytoRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.connection.AddTSCToRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.connection.CreateSatisfytoRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.connection.CreateTSCToRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.element.AddSatisfyFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.element.AddTechnicalSafetyConceptFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.element.CreateSatisfyFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.element.CreateTechnicalSafetyConceptFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.features.DEPDLDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.features.DEPDLDUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.features.DesignFunctionProtoTypeLayout;
import org.eclipse.eatop.examples.graphicaleditor.depdld.instanceref.AddSatisfytoDesignFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.instanceref.CreateSatisfytoDesignFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depdld.remove.RemoveSatisfyToDesignFunctionPrototype;
import org.eclipse.eatop.examples.graphicaleditor.depdld.remove.RemoveSatisfyToRequirement;
import org.eclipse.eatop.examples.graphicaleditor.depdld.remove.RemoveTSCToRequirement;
import org.eclipse.eatop.examples.graphicaleditor.dftd.features.add.AddDesignFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddRequirementFeature;
import org.eclipse.core.runtime.Assert;
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

public class DEPDLDFeatureProvider extends DefaultFeatureProvider {

	public DEPDLDFeatureProvider(IDiagramTypeProvider dtp) {
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
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
		       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		       if (bo instanceof TechnicalSafetyConcept
		    		   || bo instanceof Satisfy) {
		           return new DEPDLDUpdateFeature(this);
		       }
		   }
		   return super.getUpdateFeature(context);

	}
	
	public IAddFeature getAddFeature(IAddContext context) 
 {

		if (containerAlreadyExists(context))
			return super.getAddFeature(context);

		/* TODO if (context.getNewObject() instanceof UseCase) {
			return new AddUseCaseFeature(this);
		} else */if (context.getNewObject() instanceof Requirement) {
			AddRequirementFeature requirementFeature = new AddRequirementFeature(
					this);
			requirementFeature.changeColor();
			return requirementFeature;
		} else if (context.getNewObject() instanceof DesignFunctionPrototype) {
			AddDesignFunctionPrototypeFeature afpFeature = new AddDesignFunctionPrototypeFeature(this);
			return afpFeature;
		} else if (context.getNewObject() instanceof Satisfy) {
			return new AddSatisfyFeature(this);
		} else if (context.getNewObject() instanceof TechnicalSafetyConcept) {
			return new AddTechnicalSafetyConceptFeature(this);
		}

		if (context instanceof AddConnectionContext) {

			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);
			if (sourceObject instanceof TechnicalSafetyConcept
					&& targetObject instanceof Requirement) {
				return new AddTSCToRequirementFeature(this);
			} else if (sourceObject instanceof Satisfy
					&& targetObject instanceof Requirement) {
				return new AddSatisfytoRequirementFeature(this);
			} else if (sourceObject instanceof Satisfy
					&& targetObject instanceof DesignFunctionPrototype) {
				return new AddSatisfytoDesignFunctionPrototypeFeature(this);
			}
		}

		return super.getAddFeature(context);
	}
	
	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {
				new CreateSatisfyFeature(this),
				new CreateTechnicalSafetyConceptFeature(this)
				
		};
	}
	
	public ICreateConnectionFeature[] getCreateConnectionFeatures() 
	{
		return new ICreateConnectionFeature[] 
				{
					new CreateTSCToRequirementFeature(this),
					new CreateSatisfytoRequirementFeature(this),
					new CreateSatisfytoDesignFunctionPrototypeFeature(this)
					
				}; 
	}
	
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof Connection) {
			Object startElement = resolveSourceBusinessObject(context,
					(Connection) pictogramElement);
			Object endElement = resolveTargetBusinessObject(context,
					(Connection) pictogramElement);

			if (startElement instanceof TechnicalSafetyConcept
					&& endElement instanceof Requirement) {
			return new RemoveTSCToRequirement(
			this);
			}
			else if (startElement instanceof Satisfy
					&& endElement instanceof Requirement) {
			return new RemoveSatisfyToRequirement(
			this);
			}
			else if (startElement instanceof Satisfy
					&& endElement instanceof DesignFunctionPrototype) {
			return new RemoveSatisfyToDesignFunctionPrototype(
			this);
			}
		}
		return super.getRemoveFeature(context);
	}
	
	
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
	    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
	    if (bo instanceof DesignFunctionPrototype) {

	    			return new DesignFunctionProtoTypeLayout(this);
	    			
	    }else if (bo instanceof Satisfy || bo instanceof TechnicalSafetyConcept
	    		|| bo instanceof Requirement || bo instanceof UseCase) {
	        return new DEPDLDLayoutFeature(this);
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
		Assert.isNotNull(source, "Could not find BusinessObject for Connection start");
		return source;
	}

	private Object resolveTargetBusinessObject(IRemoveContext context, Connection element) {
		Object source = getBusinessObjectForPictogramElement(((Connection) element).getEnd().getParent());
		Assert.isNotNull(source, "Could not find BusinessObject for Connection end");
		return source;
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
