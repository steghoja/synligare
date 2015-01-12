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
package org.eclipse.eatop.examples.graphicaleditor.bd.features.remove;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionTrigger;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class RemoveFunctionTriggerToFunctionPort extends DefaultRemoveFeature {

	public RemoveFunctionTriggerToFunctionPort(IFeatureProvider fp) {
		super(fp);
	}
	@Override
	public void preRemove(IRemoveContext context) {
		Connection element = (Connection) context.getPictogramElement();
		Assert.isTrue(getBusinessObjectForPictogramElement(element.getStart().getParent()) 
				instanceof FunctionTrigger, "The start element isn't a FunctionTrigger!");
		FunctionTrigger source = (FunctionTrigger) getBusinessObjectForPictogramElement(element.getStart().getParent());
		Assert.isTrue(element.getEnd().getLink().getBusinessObjects().get(0) 
				instanceof FunctionPort, "The end element isn't a FunctionPort!");
		FunctionPort target = (FunctionPort) element.getEnd().getLink().getBusinessObjects().get(0);

		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getFunctionTrigger_Port();
		DiagramUtil.removeObjectFromBOResource(source, referenceId, target);
		
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
