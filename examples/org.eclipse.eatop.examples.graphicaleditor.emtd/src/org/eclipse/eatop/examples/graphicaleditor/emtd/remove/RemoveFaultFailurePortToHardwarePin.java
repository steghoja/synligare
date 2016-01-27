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

import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultFailurePort_hwTarget;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;



public class RemoveFaultFailurePortToHardwarePin extends
		DefaultRemoveFeature {

	public RemoveFaultFailurePortToHardwarePin(IFeatureProvider fp) {
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
	public void preRemove(IRemoveContext context) 
	{

		// source and target of connection
		Connection element = (Connection) context.getPictogramElement();
		
		Assert.isTrue(element.getStart().getLink().getBusinessObjects().get(0) 
				instanceof FailureOutPort, "The end element isn't a FailureOutPort!");
		FailureOutPort source = (FailureOutPort) element.getStart().getLink().getBusinessObjects().get(0);
		
		Assert.isTrue(element.getEnd().getLink().getBusinessObjects().get(0) 
				instanceof HardwarePin, "The end element isn't a HardwarePin!");
		HardwarePin target = (HardwarePin) element.getEnd().getLink().getBusinessObjects().get(0);
		
		
		// find and delete the invisible element
				EList<FaultFailurePort_hwTarget> list = source
						.getHwTarget();	
					for (int i = 0; i < list.size(); i++) 
					{
						System.out.println();
						if (list.get(i).getHardwarePort().equals(target)) 
						{
							source.getHwTarget().remove(i);
							break;
						}
					}
			

	}

}
		


