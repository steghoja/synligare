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


import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ErrorBehavior;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.core.runtime.Assert;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class RemoveErrorBehaviorToFailureOutPort extends DefaultRemoveFeature {

	public RemoveErrorBehaviorToFailureOutPort(IFeatureProvider fp) {
		super(fp);
	}
	@Override
	public void preRemove(IRemoveContext context) 
	{
		Connection element = (Connection) context.getPictogramElement();
		Assert.isTrue(getBusinessObjectForPictogramElement(element.getStart().getParent()) 
				instanceof ErrorBehavior, "The start element isn't a ErrorBehavior!");
		ErrorBehavior source = (ErrorBehavior) getBusinessObjectForPictogramElement(element.getStart().getParent());
		Assert.isTrue(element.getEnd().getLink().getBusinessObjects().get(0) 
				instanceof FailureOutPort, "The end element isn't a FailureOutPort!");
		FailureOutPort target = (FailureOutPort) element.getEnd().getLink().getBusinessObjects().get(0);
		

		FreeFormConnection ffc = (FreeFormConnection) context
				.getPictogramElement();
		Text t = null;
		if (ffc.getConnectionDecorators().size() > 0)
			t = (Text) ffc.getConnectionDecorators().get(0)
					.getGraphicsAlgorithm();

		if (t != null && t.getValue().equals("+externalFailure")) {
			DiagramUtil
					.removeObjectFromBOResource(
							source,
							Eastadl21Factory.eINSTANCE.getEastadl21Package().getErrorBehavior_ExternalFailure(),
							target);
		} else if (t != null && t.getValue().equals("+internalFailure"))

		{
			
			DiagramUtil
					.removeObjectFromBOResource(
							source,
							Eastadl21Factory.eINSTANCE.getEastadl21Package().getErrorBehavior_InternalFailure(),
							target);
		}
		
		
		}
	
	@Override
	public boolean canRemove(IRemoveContext context) 
	{
		return true;
	}
	
	
	
	@Override
	public boolean canExecute(IContext context) 
	{
		return false;
	}
	
	
	
}
