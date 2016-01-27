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
package org.eclipse.eatop.examples.graphicaleditor.dfad.remove;

import java.util.List;

import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionAllocation;
import org.eclipse.eatop.eastadl21.FunctionAllocation_allocatedElement;
import org.eclipse.eatop.eastadl21.FunctionAllocation_target;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class RemoveFunctionAllocation extends DefaultRemoveFeature {

	public RemoveFunctionAllocation(IFeatureProvider fp) {
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
	Connection element = (Connection) context.getPictogramElement();
		Allocation allocation = (Allocation) getDiagram().getLink().getBusinessObjects().get(0);
		
		List<FunctionAllocation> fas = allocation.getFunctionAllocation();
		for(FunctionAllocation fa:fas){
			FunctionAllocation_allocatedElement alo = fa.getAllocatedElement();
			FunctionAllocation_target tar = fa.getTarget();
			DesignFunctionPrototype source=(DesignFunctionPrototype) getBusinessObjectForPictogramElement(element.getStart().getParent());
			HardwareComponentPrototype target= (HardwareComponentPrototype) getBusinessObjectForPictogramElement(element.getEnd().getParent());
			String dspString =source.getShortName();
			 String alloElementString = ((DesignFunctionPrototype) alo.getAllocateableElement()).getShortName();
			if(dspString.equals(alloElementString)){
				String hwpString=target.getShortName();
				String hwAlloString = (String)tar.getAllocationTarget().getShortName();
				
				if(hwpString.equals(hwAlloString)){
					EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getAllocation_FunctionAllocation();
					DiagramUtil.removeObjectFeature(allocation,referenceId,fa);
					break;
				}
			}
		}
					
	}

}
