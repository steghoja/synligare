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
package org.eclipse.eatop.examples.graphicaleditor.depd.provider;

import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.FeatureFlaw;
import org.eclipse.eatop.eastadl21.Hazard;
import org.eclipse.eatop.eastadl21.HazardousEvent;
import org.eclipse.eatop.eastadl21.Item;
import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.OperationalSituation;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.SafetyGoal;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.eatop.eastadl21.VehicleFeature;
import org.eclipse.eatop.examples.graphicaleditor.bd.features.add.AddModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.DEPVLDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.DEPVLDUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.HazardousEventUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.SafetyGoalUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddEnvironmentFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddFeatureFlawFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddFeatureFlawToItemFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddHazardFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddHazardToFeatureFlawFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddHazardToItemFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddHazardousEventFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddHazardousEventToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddHazardousEventToUseCaseFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddHazardouzEventToHazardFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddItemFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddItemToVehicleFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddNonFulfilledRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddSafetyGoalFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddSafetyGoalToHazardousEventFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddSafetyGoalToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddSafetyGoalToRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.add.AddTrafficFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateEnvironmentFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateFeatureFlawFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateFeatureFlawToItemFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateHazardFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateHazardToFeatureFlawFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateHazardToItemFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateHazardousEventFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateHazardousEventToHazardFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateHazardousEventToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateHazardousEventToUseCaseFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateItemFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateItemToVehicleFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateNonFulfilledRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateSafetyGoalFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateSafetyGoalRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateSafetyGoalToHazardousEventFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateSafetyGoalToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.create.CreateTrafficFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveEnvironmentFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveFeatureFlawToItemFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveHazardToFeatureFlawFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveHazardToItemFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveHazardousEventToHazardFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveHazardousEventToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveItemToVehilceFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveNonFulfilledRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveOperationalSituationUseCaseFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveSafetyGoalRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveSafetyGoalToHazardousEventFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveSafetyGoalToModeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depd.features.remove.RemoveTrafficFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddOperationalSituationFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddUseCaseFeature;
import org.eclipse.eatop.examples.graphicaleditor.vlfd.graphiti.features.add.AddVehicleFeature;
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

public class DEPDFeatureProvider extends DefaultFeatureProvider{


	public DEPDFeatureProvider(IDiagramTypeProvider dtp) {
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
		
		if (context.getNewObject() instanceof Item) {
			return new AddItemFeature(this);
		} else if (context.getNewObject() instanceof FeatureFlaw) {
			return new AddFeatureFlawFeature(this);
		} else if (context.getNewObject() instanceof Hazard) {
			return new AddHazardFeature(this);
		} else if (context.getNewObject() instanceof HazardousEvent) {
			return new AddHazardousEventFeature(this);
		} else if (context.getNewObject() instanceof SafetyGoal) {
			return new AddSafetyGoalFeature(this);
		} else if (context.getNewObject() instanceof Mode) {
			return new AddModeFeature(this);
		} else if (context.getNewObject() instanceof VehicleFeature) {
			return new AddVehicleFeature(this);
		} 
		else if(context.getNewObject() instanceof UseCase) {
			return new AddUseCaseFeature(this);
		} else if (context.getNewObject() instanceof OperationalSituation) {
			return new AddOperationalSituationFeature(this);			
		} else if (context.getNewObject() instanceof Requirement) {
			AddRequirementFeature requirementFeature = new AddRequirementFeature(this);
			requirementFeature.changeColor();
			return requirementFeature;
		}
		
		if (context instanceof AddConnectionContext) {
			Object sourceObject = resolveSourceBusinessObject((AddConnectionContext) context);
			Object targetObject = resolveTargetBusinessObject((AddConnectionContext) context);
			
			if (sourceObject instanceof FeatureFlaw && targetObject instanceof Item) {
				return new AddFeatureFlawToItemFeature(this);
			} else if(sourceObject instanceof Hazard && targetObject instanceof Item){
				return new AddHazardToItemFeature(this);
			} else if(sourceObject instanceof Hazard && targetObject instanceof FeatureFlaw){
				return new AddHazardToFeatureFlawFeature(this);
			} else if(sourceObject instanceof HazardousEvent && targetObject instanceof Hazard){
				return new AddHazardouzEventToHazardFeature(this);
			} else if(sourceObject instanceof SafetyGoal && targetObject instanceof HazardousEvent){
				return new AddSafetyGoalToHazardousEventFeature(this);				
			} else if (sourceObject instanceof FeatureFlaw && targetObject instanceof Requirement) {
				return new AddNonFulfilledRequirementFeature(this);
			} else if (sourceObject instanceof SafetyGoal && targetObject instanceof Requirement) {
				return new AddSafetyGoalToRequirementFeature(this);
			}else if (sourceObject instanceof SafetyGoal && targetObject instanceof Mode) {
				return new AddSafetyGoalToModeFeature(this);
			}
			else if (sourceObject instanceof Item && targetObject instanceof VehicleFeature) {
				return new AddItemToVehicleFeature(this);
			}
			else if (sourceObject instanceof HazardousEvent && targetObject instanceof UseCase) {
				return new AddHazardousEventToUseCaseFeature(this);
			}else if (sourceObject instanceof HazardousEvent && targetObject instanceof Mode) {
				return new AddHazardousEventToModeFeature(this);
			}
			else if (sourceObject instanceof HazardousEvent && targetObject instanceof OperationalSituation) {
				if(CreateTrafficFeature.getLock() == 1 && CreateEnvironmentFeature.lock == 0){
					//CreateTrafficFeature.lock = 0;
					return new AddTrafficFeature(this);
				}
				if (CreateEnvironmentFeature.lock == 1){
					//CreateEnvironmentFeature.lock =0;
					return new AddEnvironmentFeature(this);
				}
				
			}
		}
		
		return super.getAddFeature(context);
	}
	
	@Override
	public ICreateConnectionFeature[] getCreateConnectionFeatures() {
		return new ICreateConnectionFeature[] {
				new CreateFeatureFlawToItemFeature(this),
				new CreateHazardToItemFeature(this),
				new CreateItemToVehicleFeature(this),
				new CreateNonFulfilledRequirementFeature(this),
				new CreateSafetyGoalRequirementFeature(this),
				new CreateSafetyGoalToModeFeature(this),
				new CreateHazardToFeatureFlawFeature(this),
				new CreateHazardousEventToHazardFeature(this),
				new CreateSafetyGoalToHazardousEventFeature(this),
				new CreateHazardousEventToUseCaseFeature(this),
				new CreateHazardousEventToModeFeature(this),
				new CreateTrafficFeature(this),
				new CreateEnvironmentFeature(this)	
		};
	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { 
				new CreateItemFeature(this),
				new CreateFeatureFlawFeature(this),
				new CreateHazardFeature(this),
				new CreateHazardousEventFeature(this),
				new CreateSafetyGoalFeature(this)};
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		    if (bo instanceof EAElement||bo instanceof Requirement||bo instanceof UseCase
		    		||bo instanceof OperationalSituation||bo instanceof Mode||bo instanceof VehicleFeature) {
		        return new DEPVLDLayoutFeature(this);
		    }
		    return super.getLayoutFeature(context);

	}


	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
		       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		       if (bo instanceof SafetyGoal) {
		           return new SafetyGoalUpdateFeature(this);
		       }if (bo instanceof HazardousEvent) {
		           return new HazardousEventUpdateFeature(this);
		       }
		       if (bo instanceof Item||bo instanceof FeatureFlaw
		    		   ||bo instanceof Hazard) {
		           return new DEPVLDUpdateFeature(this);
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
			
			if (startElement instanceof FeatureFlaw && endElement instanceof Item) {
				return new RemoveFeatureFlawToItemFeature(this);
			} else if (startElement instanceof Hazard && endElement instanceof Item) {
				return new RemoveHazardToItemFeature(this);
			} else if (startElement instanceof Hazard && endElement instanceof FeatureFlaw) {
				return new RemoveHazardToFeatureFlawFeature(this);
			} else if (startElement instanceof HazardousEvent && endElement instanceof Hazard) {
				return new RemoveHazardousEventToHazardFeature(this);
			} else if (startElement instanceof HazardousEvent && endElement instanceof Mode) {
				return new RemoveHazardousEventToModeFeature(this);
			} else if (startElement instanceof Item && endElement instanceof VehicleFeature) {
				return new RemoveItemToVehilceFeature(this);
			} 
			else if (startElement instanceof SafetyGoal && endElement instanceof HazardousEvent) {
				return new RemoveSafetyGoalToHazardousEventFeature(this);				
			} else if (startElement instanceof FeatureFlaw && endElement instanceof Requirement) {
				return new RemoveNonFulfilledRequirementFeature(this);
			} else if (startElement instanceof SafetyGoal && endElement instanceof Requirement) {
				return new RemoveSafetyGoalRequirementFeature(this);
			}else if (startElement instanceof SafetyGoal && endElement instanceof Mode) {
				return new RemoveSafetyGoalToModeFeature(this);
			}
			else if (startElement instanceof HazardousEvent && endElement instanceof UseCase) {
				return new RemoveOperationalSituationUseCaseFeature(this);
			}
			else if (startElement instanceof HazardousEvent && endElement instanceof OperationalSituation) {
				if(CreateTrafficFeature.getLock() == 1 && CreateEnvironmentFeature.lock == 0){
					//CreateTrafficFeature.lock = 0;
					return new RemoveTrafficFeature(this);
				}
				if (CreateEnvironmentFeature.lock == 1){
					//CreateEnvironmentFeature.lock =0;
					return new RemoveEnvironmentFeature(this);
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

	@Override
	public IReconnectionFeature getReconnectionFeature(
			IReconnectionContext context) {
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
	
	private Object resolveTargetBusinessObject(AddConnectionContext context) {
		Object element = getBusinessObjectForPictogramElement(context.getTargetAnchor().getParent());
		Assert.isNotNull(element, "Could not resolve targetObject from AddConnectionContext");
		return element;
	}	
}
