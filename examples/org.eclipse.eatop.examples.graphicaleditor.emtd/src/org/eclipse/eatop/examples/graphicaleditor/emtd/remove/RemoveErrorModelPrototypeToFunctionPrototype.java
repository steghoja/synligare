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
package org.eclipse.eatop.examples.graphicaleditor.emtd.remove;

import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype_functionTarget;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;



public class RemoveErrorModelPrototypeToFunctionPrototype extends
		DefaultRemoveFeature {

	public RemoveErrorModelPrototypeToFunctionPrototype(IFeatureProvider fp) {
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
				.getParent()) instanceof ErrorModelPrototype,
				"The start element isn't a ErrorModelPrototype!");
		ErrorModelPrototype source = (ErrorModelPrototype) getBusinessObjectForPictogramElement(element
				.getStart().getParent());
		Assert.isTrue(getBusinessObjectForPictogramElement(element.getEnd()
				.getParent()) instanceof FunctionPrototype,
				"The start element isn't a FunctionPrototype!");
		FunctionPrototype target = (FunctionPrototype) getBusinessObjectForPictogramElement(element
				.getEnd().getParent());

		// find and delete the invisible element
		EList<ErrorModelPrototype_functionTarget> list = source
				.getFunctionTarget();	
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getFunctionPrototype().equals(target)) {
					source.getFunctionTarget().remove(i);
					break;
				}
			}

		
	}

}
		


