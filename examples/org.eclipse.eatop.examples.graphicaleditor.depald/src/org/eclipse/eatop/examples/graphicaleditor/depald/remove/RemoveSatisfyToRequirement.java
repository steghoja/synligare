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
package org.eclipse.eatop.examples.graphicaleditor.depald.remove;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class RemoveSatisfyToRequirement extends DefaultRemoveFeature {

	public RemoveSatisfyToRequirement(IFeatureProvider fp) {
		super(fp);
	}
	
	@Override
	public void preRemove(IRemoveContext context) {
		Connection element = (Connection) context.getPictogramElement();
		Assert.isTrue(getBusinessObjectForPictogramElement(element.getStart().getParent()) 
				instanceof Satisfy, "The start element isn't a Satisfy!");
		Satisfy source = (Satisfy) getBusinessObjectForPictogramElement(element.getStart().getParent());
		Assert.isTrue(getBusinessObjectForPictogramElement(element.getEnd().getParent()) 
				instanceof Requirement, "The start element isn't a Requirement!");
		Requirement target = (Requirement) getBusinessObjectForPictogramElement(element.getEnd().getParent());

		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getSatisfy_SatisfiedRequirement();
		DiagramUtil.removeObjectFromBOResource((org.eclipse.emf.ecore.EObject) source,
				referenceId, (org.eclipse.emf.ecore.EObject) target);
		
	}
	
	@Override
	public boolean canRemove(IRemoveContext context) {
		return true;
	}
	
	
	
	@Override
	public boolean canExecute(IContext context) {
		return false;
	}
	
	
	
}
