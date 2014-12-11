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

import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.core.runtime.Assert;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;



public class RemoveFaultFailurePropagationLinkToFaultInPort extends
		DefaultRemoveFeature {

	public RemoveFaultFailurePropagationLinkToFaultInPort(IFeatureProvider fp) {
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
				.getParent()) instanceof FaultFailurePropagationLink,
				"The start element isn't a FaultFailurePropagationLink!");
		FaultFailurePropagationLink source = (FaultFailurePropagationLink) getBusinessObjectForPictogramElement(element
				.getStart().getParent());
		Assert.isTrue(element.getEnd().getLink().getBusinessObjects().get(0) 
				instanceof FaultInPort, "The end element isn't a FaultInPort!");
		
		
		// find and delete the invisible element
		source.setFromPort(null);	
	}

}
		


