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

import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.Satisfy_satisfiedBy;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;



public class RemoveSatisfyToAnalysisFunctionPrototype extends
		DefaultRemoveFeature {

	public RemoveSatisfyToAnalysisFunctionPrototype(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canRemove(IRemoveContext context) {
		return true;
	}

	@Override
	public boolean canExecute(IContext context) {
		return false;
	}

	@Override
	public void preRemove(IRemoveContext context) {

		// source and target of connection
		Connection element = (Connection) context.getPictogramElement();
		Assert.isTrue(getBusinessObjectForPictogramElement(element.getStart()
				.getParent()) instanceof Satisfy,
				"The start element isn't a Satisfy!");
		Satisfy source = (Satisfy) getBusinessObjectForPictogramElement(element
				.getStart().getParent());
		Assert.isTrue(getBusinessObjectForPictogramElement(element.getEnd()
				.getParent()) instanceof AnalysisFunctionPrototype,
				"The start element isn't a AnalysisFunctionPrototype!");
		AnalysisFunctionPrototype target = (AnalysisFunctionPrototype) getBusinessObjectForPictogramElement(element
				.getEnd().getParent());

		// find and delete the invisible element
		EList<Satisfy_satisfiedBy> list = source
				.getSatisfiedBy();	
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getIdentifiable_context().get(0).equals(target)) {
					source.getSatisfiedBy().remove(i);
					break;
				}
			}

		// delete the pointer from source to target(doesn't exist here)
		//DiagramUtil.removeObjectFromBOResource(source,ErrormodelPackage.Literals.ERROR_MODEL_PROTOTYPE__TARGET,target);
	}

}
		


