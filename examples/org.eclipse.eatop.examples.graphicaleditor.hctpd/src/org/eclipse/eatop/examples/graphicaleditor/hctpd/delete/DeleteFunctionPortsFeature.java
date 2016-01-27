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
package org.eclipse.eatop.examples.graphicaleditor.hctpd.delete;

import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IMultiDeleteInfo;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

public class DeleteFunctionPortsFeature extends DefaultDeleteFeature {

	public DeleteFunctionPortsFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canDelete(IDeleteContext context) {

		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (!(bo instanceof HardwarePin)) {
			return false;
		}

		IRemoveContext rc = new RemoveContext(pictogramElement);
		IRemoveFeature removeFeature = getFeatureProvider()
				.getRemoveFeature(rc);
		boolean ret = (removeFeature != null) && removeFeature.canRemove(rc);
		return ret;
	}

	@Override
	public void delete(IDeleteContext context) {

		setDoneChanges(false);

		IMultiDeleteInfo multiDeleteInfo = context.getMultiDeleteInfo();
		if (multiDeleteInfo != null && multiDeleteInfo.isDeleteCanceled()) {
			return;
		}
		PictogramElement pe = context.getPictogramElement();
		Object[] businessObjectsForPictogramElement = getAllBusinessObjectsForPictogramElement(pe);
		if (businessObjectsForPictogramElement != null
				&& businessObjectsForPictogramElement.length > 0) {
			if (multiDeleteInfo == null) {
				if (!getUserDecision(context)) {
					return;
				}
			} else {
				if (multiDeleteInfo.isShowDialog()) {
					boolean okPressed = getUserDecision(context);
					if (okPressed) {
						// don't show further dialogs
						multiDeleteInfo.setShowDialog(false);
					} else {
						multiDeleteInfo.setDeleteCanceled(true);
						return;
					}
				}
			}
		}

		preDelete(context);
		IRemoveContext rc = new RemoveContext(pe);
		IFeatureProvider featureProvider = getFeatureProvider();
		IRemoveFeature removeFeature = featureProvider.getRemoveFeature(rc);
		if (removeFeature != null) {
			removeFeature.remove(rc);
			// Bug 347421: Set hasDoneChanges flag only after first modification
			setDoneChanges(true);
		}

		BoxRelativeAnchor ggggg = (BoxRelativeAnchor) context
				.getPictogramElement();
		ContainerShape econtainer = (ContainerShape) ggggg
				.getReferencedGraphicsAlgorithm().eContainer().eContainer();
		EList<Shape> children = econtainer.getChildren();
		Shape[] childrenArray = (Shape[]) children.toArray();
		for (Shape b : childrenArray) {
			GraphicsAlgorithm aa = b.getGraphicsAlgorithm();
			if (aa instanceof Text) {
				Text deleteText = (Text) aa;
				// FunctionPort fp = (FunctionPort)
				// pe.getLink().getBusinessObjects().get(0);
				HardwarePin fp = (HardwarePin) businessObjectsForPictogramElement[0];
				if ((deleteText.getValue().endsWith(fp.getShortName()))) {

					IRemoveContext remContext = new RemoveContext(b);
					if (removeFeature != null) {
						removeFeature.remove(remContext);
						// Bug 347421: Set hasDoneChanges flag only after first
						// modification
						setDoneChanges(true);
					}
				}
			}
		}

		deleteBusinessObjects(businessObjectsForPictogramElement);
		postDelete(context);
	}

}
