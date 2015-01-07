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
package org.eclipse.eatop.examples.graphicaleditor.depscd.provider;

import org.eclipse.eatop.eastadl21.Claim;
import org.eclipse.eatop.eastadl21.Ground;
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.eatop.eastadl21.TraceableSpecification;
import org.eclipse.eatop.eastadl21.Warrant;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.DEPSCDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.DEPSCDUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.add.AddClaimFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.add.AddClaimToGroundFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.add.AddClaimToQualityRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.add.AddGoalDecompositionStrategyFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.add.AddGroundFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.add.AddSupportedArgumentFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.add.AddWarrantFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.add.AddWarrantToClaimFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.add.AddWarrantToGroundFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.create.CreateClaimFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.create.CreateClaimToGroundFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.create.CreateClaimToQualityRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.create.CreateGoalDecompositionStrategy;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.create.CreateGroundFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.create.CreateSupportedArgument;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.create.CreateWarrantFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.create.CreateWarrantToClaimFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.create.CreateWarrantToGroundFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.remove.RemoveClaimToGroundFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.remove.RemoveClaimToQualityRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.remove.RemoveGoalDecompositionStrategyFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.remove.RemoveSupportedArgumentFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.remove.RemoveWarrantToClaimFeature;
import org.eclipse.eatop.examples.graphicaleditor.depscd.features.remove.RemoveWarrantToGroundFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddQualityRequirementFeature;
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

public class DEPSCDFeatureProvider extends DefaultFeatureProvider {

	public DEPSCDFeatureProvider(IDiagramTypeProvider dtp) {
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
		
		if (context.getNewObject() instanceof Warrant) {
			return new AddWarrantFeature(this);
		}else if (context.getNewObject() instanceof Claim) {
			return new AddClaimFeature(this);
		} else if (context.getNewObject() instanceof Ground) {
			return new AddGroundFeature(this);
		}  else if (context.getNewObject() instanceof QualityRequirement) {
			return new AddQualityRequirementFeature(this);
		}
		if (context instanceof AddConnectionContext) {
			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);
			
			if (sourceObject instanceof Warrant && targetObject instanceof Ground) {
				return new AddWarrantToGroundFeature(this);
			}else if (sourceObject instanceof Warrant && targetObject instanceof Claim) {
			return new AddWarrantToClaimFeature(this);
			
			}else if (sourceObject instanceof Claim && targetObject instanceof Ground) {
				return new AddClaimToGroundFeature(this);
			}else if (sourceObject instanceof Claim && targetObject instanceof QualityRequirement) {
				return new AddClaimToQualityRequirementFeature(this);
			}
			else if (sourceObject instanceof Claim && targetObject instanceof Warrant) {
				if(CreateGoalDecompositionStrategy.lock == 1 && CreateSupportedArgument.lock == 0){
					
					return new AddGoalDecompositionStrategyFeature(this);
				}
				if (CreateSupportedArgument.lock == 1){
					
					return new AddSupportedArgumentFeature(this);
				}
			}
		}
		return super.getAddFeature(context);
	}

	

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {
				new CreateClaimFeature(this),
				new CreateGroundFeature(this),
		        new CreateWarrantFeature(this),
		};
	}
	
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {
				new CreateWarrantToGroundFeature(this),
				new CreateWarrantToClaimFeature(this),
				new CreateClaimToGroundFeature(this),
				new CreateClaimToQualityRequirementFeature(this),
				new CreateGoalDecompositionStrategy(this),
				new CreateSupportedArgument(this)
		};
	}
	
	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		    if (bo instanceof Warrant||bo instanceof Claim||bo instanceof Ground||bo instanceof QualityRequirement){
		    	return new DEPSCDLayoutFeature(this);
		    }
		return super.getLayoutFeature(context);
	}
	
	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
		       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		       if (bo instanceof TraceableSpecification) {
		           return new DEPSCDUpdateFeature(this);
		       }   
		   }
		return super.getUpdateFeature(context);
	}
	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) {
		return null;
	}

	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof Connection) {
			
			Object startElement = resolveSourceBusinessObject(context, (Connection) pictogramElement);
			Object endElement = resolveTargetBusinessObject(context, (Connection) pictogramElement);
			
			if (startElement instanceof Claim && endElement instanceof Ground) {
				return new RemoveClaimToGroundFeature(this);
			} else if(startElement instanceof Claim && endElement instanceof QualityRequirement){
				new RemoveClaimToQualityRequirementFeature(this);
			} else if(startElement instanceof Warrant && endElement instanceof Claim){
				new RemoveWarrantToClaimFeature(this);
			} else if(startElement instanceof Warrant && endElement instanceof Ground){
				new RemoveWarrantToGroundFeature(this);	
			} else if(startElement instanceof Claim && endElement instanceof Warrant){
				
				if (CreateGoalDecompositionStrategy.lock == 1 && CreateSupportedArgument.lock == 0){
					new RemoveGoalDecompositionStrategyFeature(this);
				} 
				
				if (CreateSupportedArgument.lock == 1){
					new RemoveSupportedArgumentFeature(this);
				}
			}
		}
		return super.getRemoveFeature(context);
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
