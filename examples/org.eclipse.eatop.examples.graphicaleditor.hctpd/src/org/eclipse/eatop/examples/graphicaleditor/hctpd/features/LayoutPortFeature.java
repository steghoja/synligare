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
package org.eclipse.eatop.examples.graphicaleditor.hctpd.features;

import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.impl.AbstractLayoutFeature;
import org.eclipse.graphiti.mm.pictograms.BoxRelativeAnchor;

public class LayoutPortFeature extends AbstractLayoutFeature {

	public LayoutPortFeature(final IFeatureProvider fp) {
		super(fp);
	}

	public boolean canLayout(final ILayoutContext context) {
		Object object = getBusinessObjectForPictogramElement(context
				.getPictogramElement());
		return object instanceof FunctionPort;
	}

	public boolean layout(final ILayoutContext context) {
		BoxRelativeAnchor boxAnchor = (BoxRelativeAnchor) context
				.getPictogramElement();
		float widthPercent = (float) boxAnchor.getRelativeWidth();
		float heightPercent = (float) boxAnchor.getRelativeHeight();
		float deltaY = heightPercent < 1.0f / 2.0f ? heightPercent
				: 1 - heightPercent;
		float deltaX = widthPercent < 1.0f / 2.0f ? widthPercent
				: 1 - widthPercent;
		if (deltaY < deltaX) {
			heightPercent = Math.round(heightPercent);
		} else {
			widthPercent = Math.round(widthPercent);
		}
		boxAnchor.setRelativeWidth(widthPercent);
		boxAnchor.setRelativeHeight(heightPercent);
		return true;
	}
}