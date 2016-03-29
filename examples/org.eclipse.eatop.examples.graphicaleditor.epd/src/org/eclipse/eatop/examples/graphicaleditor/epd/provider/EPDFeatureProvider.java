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
package org.eclipse.eatop.examples.graphicaleditor.epd.provider;

import org.eclipse.eatop.eastadl21.Behavior;
import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Environment;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.CustomEAPackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.EatopLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.EatopUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddAnalysisTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddBehaviorFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddDependabilityAnalysisLevelFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddDependabilityDesignLevelFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddDependabilityErrorModelTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddDependabilitySafetyCaseFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddDependabilitySafetyConstraintsFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddDependabilityVehicleLevelFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddDesignTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddEnvironmentFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddHardwareComponentTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddRequirementModelFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.add.AddSystemModelPackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateAnalysisTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateBehaviorFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateDependabilityAnalysisLevelFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateDependabilityDesignLevelFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateDependabilityErrorModelTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateDependabilitySafetyCaseFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateDependabilitySafetyConstraintsFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateDependabilityVehicleLevelFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateDesignTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateEAPackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateEnvironmentFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateHardwareComponentTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateRequirementModelFeature;
import org.eclipse.eatop.examples.graphicaleditor.epd.features.create.CreateSystemModelPackageFeature;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class EPDFeatureProvider extends DefaultFeatureProvider{


	public EPDFeatureProvider(IDiagramTypeProvider dtp) {
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
		
		if (context.getNewObject() instanceof EAPackage) {
			EAPackage eap=(EAPackage)context.getNewObject();
			if(eap.getName().equals("SystemModel")){
					return new AddSystemModelPackageFeature(this);
			}
			if(eap.getName().equals("AnalysisTypePackage")){
				return new AddAnalysisTypePackageFeature(this);
		
			}
			if(eap.getName().equals("DesignTypePackage")){
				return new AddDesignTypePackageFeature(this);
		
			}
			if(eap.getName().equals("HardwareComponentTypePackage")){
				return new AddHardwareComponentTypePackageFeature(this);
		
			}
			if(eap.getName().equals("EnvironmentPackage")){
				return new AddEnvironmentFeature(this);
		
			}
			if(eap.getName().equals("")){
				return new AddAnalysisTypePackageFeature(this);
		
			}
		
			
	    } else if (context.getNewObject() instanceof RequirementsModel) {
	    	return new AddRequirementModelFeature(this);
	    }
	    else if (context.getNewObject() instanceof Behavior)
		{
    		return new AddBehaviorFeature(this);
		}
		else if (context.getNewObject() instanceof Dependability ) {
			Dependability dep = (Dependability) context.getNewObject();
			if (dep.getName().equals("DependabilityVehicleLevel"))
			{
				return new AddDependabilityVehicleLevelFeature(this);
			}else if (dep.getName().equals("DependabilityAnalysisLevel"))
			{
	    		return new AddDependabilityAnalysisLevelFeature(this);
			}else if (dep.getName().equals("DependabilityDesignLevel"))
			{
	    		return new AddDependabilityDesignLevelFeature(this);
			}else if (dep.getName().equals("DependabilitySafetyCase"))
			{
	    		return new AddDependabilitySafetyCaseFeature(this);
			}else if(dep.getName().equals("DependabilityErrorModelTypePackage"))
			{
	    		return new AddDependabilityErrorModelTypePackageFeature(this);
			}else if(dep.getName().equals("DependabilitySafetyConstraints"))
			{
	    		return new AddDependabilitySafetyConstraintsFeature(this);
			}
			
	    }
	   

	    return super.getAddFeature(context);

	}

	@Override
	public ICreateFeature[] getCreateFeatures() 
	{
		 return new ICreateFeature[] {	new CreateEAPackageFeature(this), 
				                        new CreateRequirementModelFeature(this),
				 						new CreateDependabilityVehicleLevelFeature(this),
		                                new CreateDependabilityAnalysisLevelFeature(this),
		                                new CreateDependabilityDesignLevelFeature(this),
		                                new CreateDependabilitySafetyCaseFeature(this),
		                                new CreateDependabilityErrorModelTypePackageFeature(this),
		                                new CreateDependabilitySafetyConstraintsFeature(this),
		                                new CreateSystemModelPackageFeature(this),
		                                new CreateAnalysisTypePackageFeature(this),
		                                new CreateBehaviorFeature(this),
		                                new CreateEnvironmentFeature(this),
		                                new CreateDesignTypePackageFeature(this),
		                                new CreateHardwareComponentTypePackageFeature(this)
		                                
		 };
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		 PictogramElement pictogramElement = context.getPictogramElement();
		    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		    if (bo instanceof EAPackage||bo instanceof Dependability||bo instanceof RequirementsModel||bo instanceof Behavior||bo instanceof Environment) {
		        return new EatopLayoutFeature(this);
		    }
		    return super.getLayoutFeature(context);

	}


	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) 
	{
		 PictogramElement pictogramElement = context.getPictogramElement();
		   if (pictogramElement instanceof ContainerShape) {
		       Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		       if (bo instanceof EAPackage||bo instanceof Dependability||bo instanceof RequirementsModel||bo instanceof Behavior||bo instanceof Environment) 
		       {
		           return new EatopUpdateFeature(this);
		       }
		   }
		   return super.getUpdateFeature(context);
	}

	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) 
	{
		return new ICustomFeature[] 
				{
				new CustomEAPackageFeature(this) 
				};
	}

	
}
