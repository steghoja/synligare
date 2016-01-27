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
package org.eclipse.eatop.examples.graphicaleditor.depemtpd.remove;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class RemoveErrorModelTypeToHardwareComponentTypeFeature extends DefaultRemoveFeature {

	public RemoveErrorModelTypeToHardwareComponentTypeFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canRemove(IRemoveContext context) {
		return true;
	}

	@Override
	public void preRemove(IRemoveContext context) {
		/**
		 * Remove the item from the model as well!
		 */
		Connection element = (Connection) context.getPictogramElement();
		Assert.isTrue(getBusinessObjectForPictogramElement(element.getStart().getParent()) instanceof ErrorModelType, "The start element isn't a ErrorModelType!");
		ErrorModelType source = (ErrorModelType) getBusinessObjectForPictogramElement(element.getStart().getParent());
		Assert.isTrue(getBusinessObjectForPictogramElement(element.getEnd().getParent()) instanceof HardwareComponentType, "The start element isn't a HardwareComponentType!");
		HardwareComponentType target = (HardwareComponentType) getBusinessObjectForPictogramElement(element.getEnd().getParent());
		EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getErrorModelType_HwTarget();
		DiagramUtil.removeObjectFromBOResource(source, referenceId, target);
	}

	@Override
	public boolean canExecute(IContext context) {
		return false;
	}

}
