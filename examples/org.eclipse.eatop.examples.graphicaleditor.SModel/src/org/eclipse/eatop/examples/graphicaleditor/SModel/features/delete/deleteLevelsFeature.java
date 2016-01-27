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
package org.eclipse.eatop.examples.graphicaleditor.SModel.features.delete;

import org.eclipse.eatop.eastadl21.AnalysisLevel;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.ImplementationLevel;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.create.CreateLevelsFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.create.CreateSystemModelFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IMultiDeleteInfo;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

public class deleteLevelsFeature extends DefaultDeleteFeature {

	public deleteLevelsFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canDelete(IDeleteContext context) {

		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof VehicleLevel || bo instanceof AnalysisLevel
				|| bo instanceof DesignLevel
				|| bo instanceof ImplementationLevel) {
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

		CreateSystemModelFeature.lock = 0;
		CreateLevelsFeature.lock = 0;
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
						PictogramElement pictogramElement = context
								.getPictogramElement();
						Object bo = getBusinessObjectForPictogramElement(pictogramElement);

						if (bo instanceof SystemModel) {

							CreateSystemModelFeature.lock = 0;
						}
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

		deleteBusinessObjects(businessObjectsForPictogramElement);

		postDelete(context);
	}

	@Override
	public void preDelete(IDeleteContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);

		if (bo instanceof SystemModel) {

			CreateSystemModelFeature.lock = 0;
		}

	}

}
