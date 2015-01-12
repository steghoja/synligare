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
package org.eclipse.eatop.examples.graphicaleditor.hctd.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class HCTDImageProvider extends AbstractImageProvider {
	/** References to image RequirementsModel. */
	// public static final String IMAGE_REQUIREMENT_MODEL
	// ="org.mourad.examples.graphiti.imageProvider";

	public static final String IMAGE_FunctionFlowPort = HCTDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".FunctionFlowPort";
	public static final String IMAGE_FunctionClientServerPort = HCTDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".FunctionClientServerPort";
	public static final String IMAGE_FunctionPowerPort = HCTDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".FunctionPowerPort";
	public static final String IMAGE_AnalysisFunctionPrototype = HCTDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".AnalysisFunctionPrototype";
	public static final String IMAGE_HardwareComponentPrototype = HCTDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".HardwareComponentPrototype";
	public static final String IMAGE_PowerHardwarePin = HCTDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".PowerHardwarePin";
	public static final String IMAGE_IOHardwarePin = HCTDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".IOHardwarePin";
	public static final String IMAGE_CommunicationHardwarePin = HCTDTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".CommunicationHardwarePin";

	// public static final String IMAGE_REQUIREMENT_LINK =
	// DemoTypeDiagramProvider.DIAGRAM_TYPE_ID + ".RequirementsLink";
	// public static final String IMAGE_DERIVE_REQUIREMENT =
	// DemoTypeDiagramProvider.DIAGRAM_TYPE_ID + ".DeriveRequirement";

	public HCTDImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMAGE_FunctionFlowPort,
				"/icons/obj16/FunctionFlowPort.gif");
		addImageFilePath(IMAGE_FunctionClientServerPort,
				"/icons/obj16/FunctionClientServerPort.gif");
		addImageFilePath(IMAGE_FunctionPowerPort,
				"/icons/obj16/FunctionPowerPort.gif");
		addImageFilePath(IMAGE_AnalysisFunctionPrototype,
				"/icons/obj16/AnalysisFunctionPrototype.gif");
		addImageFilePath(IMAGE_HardwareComponentPrototype,
				"/icons/obj16/HardwareComponentPrototype.gif");
		addImageFilePath(IMAGE_PowerHardwarePin,
				"/icons/obj16/PowerHardwarePin.gif");
		addImageFilePath(IMAGE_IOHardwarePin, "/icons/obj16/IOHardwarePin.gif");
		addImageFilePath(IMAGE_CommunicationHardwarePin,
				"/icons/obj16/CommunicationHardwarePin.gif");

	}

}
