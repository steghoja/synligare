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
package org.eclipse.eatop.examples.graphicaleditor.depemtpd.features.create;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

public class CreateErrorModelTypeToFunctionTypeFeature extends
		AbstractCreateConnectionFeature {

	public CreateErrorModelTypeToFunctionTypeFeature(IFeatureProvider fp) {
		super(fp, "ErrorModelType To FunctionType",
				"Create ErrorModelType To FunctionType");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// return true if both anchors belong to an EClass
		// and those EClasses are not identical
		ErrorModelType source = getErrorModelType(context.getSourceAnchor());
		FunctionType target = getFunctionType(context.getTargetAnchor());
		if (source != null && target != null && source != target) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a EClass
		if (getErrorModelType(context.getSourceAnchor()) != null) {
			return true;
		}
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;

		// get EClasses which should be connected
		ErrorModelType errorModelType = getErrorModelType(context
				.getSourceAnchor());
		FunctionType functionType = getFunctionType(context.getTargetAnchor());
		Assert.isNotNull(errorModelType);
		Assert.isNotNull(functionType);

		if (errorModelType != null && functionType != null) {
			EReference referenceId = Eastadl21Factory.eINSTANCE
					.getEastadl21Package().getErrorModelType_Target();
			DiagramUtil.addReferenceToBOResource(errorModelType, referenceId,
					functionType);

			AddConnectionContext addContext = new AddConnectionContext(
					context.getSourceAnchor(), context.getTargetAnchor());
			newConnection = (Connection) getFeatureProvider().addIfPossible(
					addContext);
		}

		return newConnection;
	}

	private FunctionType getFunctionType(Anchor targetAnchor) {
		if (targetAnchor != null) {
			Object object = getBusinessObjectForPictogramElement(targetAnchor
					.getParent());
			if (object instanceof FunctionType) {
				return (FunctionType) object;
			}
		}
		return null;
	}

	private ErrorModelType getErrorModelType(Anchor sourceAnchor) {
		if (sourceAnchor != null) {
			Object object = getBusinessObjectForPictogramElement(sourceAnchor
					.getParent());
			if (object instanceof ErrorModelType) {
				return (ErrorModelType) object;
			}
		}
		return null;
	}

}
