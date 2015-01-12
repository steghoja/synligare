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
package org.eclipse.eatop.examples.graphicaleditor.afp.pattern.provider;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionalSafetyConcept;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.UseCase;
import org.eclipse.eatop.examples.graphicaleditor.depald.connection.CreateFSCtoRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depald.connection.CreateSatisfytoRequirementFeature;
import org.eclipse.eatop.examples.graphicaleditor.depald.element.CreateFunctionalSafetyConceptFeature;
import org.eclipse.eatop.examples.graphicaleditor.depald.element.CreateSatisfyFeature;
import org.eclipse.eatop.examples.graphicaleditor.depald.features.DEPALDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.depald.features.FunctionProtoTypeLayout;
import org.eclipse.eatop.examples.graphicaleditor.depald.instanceref.CreateSatisfytoAnalysisFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.depald.remove.RemoveFSCToRequirement;
import org.eclipse.eatop.examples.graphicaleditor.depald.remove.RemoveSatisfyToAnalysisFunctionPrototype;
import org.eclipse.eatop.examples.graphicaleditor.depald.remove.RemoveSatisfyToRequirement;
import org.eclipse.eatop.examples.graphicaleditor.reqd.features.add.AddUseCaseFeature;
import org.eclipse.core.runtime.Assert;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class AFPDFeatureProvider extends DefaultFeatureProvider {

	public AFPDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	private boolean containerAlreadyExists(IAddContext context) {
		/*the container of an object can't be dragged and dropped from the wizard
		if there is already a container of the same BO in the diagram*/
		if (context.getTargetContainer() instanceof Diagram) 
				for( Shape cs: context.getTargetContainer().getChildren())
				{
					if(cs.getLink().getBusinessObjects().get(0).equals(context.getNewObject()))
						return true;
					
				}
		return false;
		}
	
	public IAddFeature getAddFeature(IAddContext context) 
	{
		
		if(containerAlreadyExists(context))
			return super.getAddFeature(context);
		
		
		 if(context.getNewObject() instanceof UseCase) 
		{
			return new AddUseCaseFeature(this);
		}
		 
	
	return super.getAddFeature(context);
	}
	
	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {
				new CreateSatisfyFeature(this),
				new CreateFunctionalSafetyConceptFeature(this)
				
		};
	}
	
	public ICreateConnectionFeature[] getCreateConnectionFeatures() 
	{
		return new ICreateConnectionFeature[] 
				{
					new CreateFSCtoRequirementFeature(this),
					new CreateSatisfytoRequirementFeature(this),
					new CreateSatisfytoAnalysisFunctionPrototypeFeature(this)
					
				}; 
	}
	
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof Connection) {
			Object startElement = resolveSourceBusinessObject(context,
					(Connection) pictogramElement);
			Object endElement = resolveTargetBusinessObject(context,
					(Connection) pictogramElement);

			if (startElement instanceof FunctionalSafetyConcept
					&& endElement instanceof Requirement) {
			return new RemoveFSCToRequirement(
			this);
			}
			else if (startElement instanceof Satisfy
					&& endElement instanceof Requirement) {
			return new RemoveSatisfyToRequirement(
			this);
			}
			else if (startElement instanceof Satisfy
					&& endElement instanceof AnalysisFunctionPrototype) {
			return new RemoveSatisfyToAnalysisFunctionPrototype(
			this);
			}
		}
		return super.getRemoveFeature(context);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.graphiti.pattern.DefaultFeatureProviderWithPatterns#getLayoutFeature(org.eclipse.graphiti.features.context.ILayoutContext)
	 */
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
	    Object bo = getBusinessObjectForPictogramElement(pictogramElement);
	    if (bo instanceof AnalysisFunctionPrototype) {

	    			return new FunctionProtoTypeLayout(this);
	    			
	    }else if (bo instanceof Satisfy||bo instanceof FunctionalSafetyConcept||bo instanceof Requirement) {
	        return new DEPALDLayoutFeature(this);
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
}
