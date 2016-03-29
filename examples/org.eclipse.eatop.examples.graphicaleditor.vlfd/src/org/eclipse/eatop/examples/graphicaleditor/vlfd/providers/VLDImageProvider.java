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
package org.eclipse.eatop.examples.graphicaleditor.vlfd.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class VLDImageProvider extends AbstractImageProvider {
	/** References to image RequirementsModel. */
	public static final String IMAGE_VehicleFeature = VLDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".VehicleFeature";
	public static final String IMAGE_FeatureModel = VLDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".FeatureModel";
	public static final String IMAGE_FeatureConstraint = VLDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".FeatureConstraint";
	public static final String IMAGE_FeatureLink = VLDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".FeatureLink";

	public VLDImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMAGE_VehicleFeature,
				"/icons/obj16/VehicleFeature.gif");
		addImageFilePath(IMAGE_FeatureModel, "/icons/obj16/FeatureModel.gif");
		addImageFilePath(IMAGE_FeatureConstraint,
				"/icons/obj16/FeatureConstraint.gif");
		addImageFilePath(IMAGE_FeatureLink, "/icons/obj16/FeatureLink.gif");
	}

}
