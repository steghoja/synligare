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
package org.eclipse.eatop.examples.graphicaleditor.SModel.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class SMDImageProvider extends AbstractImageProvider {
	/** References to image RequirementsModel. */

	public static final String IMAGE_SystemModel = SMDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".SystemModel";
	public static final String IMAGE_VehicleLevel = SMDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".VehicleLevel";
	public static final String IMAGE_AnalysisLevel = SMDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".AnalysisLevel";
	public static final String IMAGE_DesignLevel = SMDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".DesignLevel";
	public static final String IMAGE_ImplementationLevel = SMDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".ImplementationLevel";

	public static final String IMAGE_AnalysisFunctionPrototype = SMDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".AnalysisFunctionPrototype";

	public static final String IMAGE_DesignFunctionPrototype = SMDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".DesignFunctionPrototype";

	public static final String IMAGE_HardwareComponentPrototype = SMDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".HardwareComponentPrototype";
	public static final String IMAGE_Allocation = SMDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".Allocation";

	public SMDImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMAGE_SystemModel, "/icons/obj16/SystemModel.gif");
		addImageFilePath(IMAGE_VehicleLevel, "/icons/obj16/VehicleLevel.gif");
		addImageFilePath(IMAGE_AnalysisLevel, "/icons/obj16/AnalysisLevel.gif");
		addImageFilePath(IMAGE_DesignLevel, "/icons/obj16/DesignLevel.gif");
		addImageFilePath(IMAGE_ImplementationLevel,
				"/icons/obj16/ImplementationLevel.gif");
		addImageFilePath(IMAGE_AnalysisFunctionPrototype,
				"/icons/obj16/AnalysisFunctionPrototype.gif");
		addImageFilePath(IMAGE_DesignFunctionPrototype,
				"/icons/obj16/DesignFunctionPrototype.gif");
		addImageFilePath(IMAGE_HardwareComponentPrototype,
				"/icons/obj16/HardwareComponentPrototype.gif");
		addImageFilePath(IMAGE_Allocation,
				"/icons/obj16/Allocation.gif");
	}
}
