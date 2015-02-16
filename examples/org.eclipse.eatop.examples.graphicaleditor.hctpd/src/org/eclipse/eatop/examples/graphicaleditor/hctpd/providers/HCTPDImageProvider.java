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
package org.eclipse.eatop.examples.graphicaleditor.hctpd.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;








public class HCTPDImageProvider extends AbstractImageProvider {
	/** References to image RequirementsModel. */
	//public static final String IMAGE_REQUIREMENT_MODEL ="org.mourad.examples.graphiti.imageProvider";

	public static final String IMAGE_AnalysisLevel = HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".AnalysisLevel";
	public static final String IMAGE_EAPackage= HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".EAPackage";
	public static final String IMAGE_HardwareComponentType= HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".HardwareComponentType";
	public static final String IMAGE_FunctionalDevice= HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionalDevice";
	public static final String IMAGE_FunctionFlowPort= HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionFlowPort";
	public static final String IMAGE_FunctionClientServerPort= HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionClientServerPort";
	public static final String IMAGE_FunctionPowerPort= HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionPowerPort";
	public static final String IMAGE_CommunicationHardwarePin= HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".CommunicationHardwarePin";
	public static final String IMAGE_Node= HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".Node";
	public static final String IMAGE_Sensor = HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".Sensor";
	public static final String IMAGE_Actuator = HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".Actuator";
	public static final String IMAGE_ElectricalComponent = HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".ElectricalComponent";
	public static final String IMAGE_PowerHardwarePin= HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".PowerHardwarePin";
	public static final String IMAGE_IOHardwarePin= HCTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".IOHardwarePin";
	
	
	public HCTPDImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
     
     addImageFilePath(IMAGE_AnalysisLevel, "/icons/obj16/AnalysisLevel.gif");
     addImageFilePath(IMAGE_EAPackage, "/icons/obj16/EAPackage.gif");
     addImageFilePath(IMAGE_HardwareComponentType, "/icons/obj16/HardwareComponentType.gif");
     addImageFilePath(IMAGE_FunctionalDevice, "/icons/obj16/FunctionalDevice.gif");
     addImageFilePath(IMAGE_FunctionFlowPort, "/icons/obj16/FunctionFlowPort.gif");
     addImageFilePath(IMAGE_FunctionClientServerPort, "/icons/obj16/FunctionClientServerPort.gif");
     addImageFilePath(IMAGE_FunctionPowerPort, "/icons/obj16/FunctionPowerPort.gif");
     addImageFilePath(IMAGE_CommunicationHardwarePin, "/icons/obj16/CommunicationHardwarePin.gif");
     addImageFilePath(IMAGE_Node, "/icons/obj16/Node.gif");
     addImageFilePath(IMAGE_Sensor, "/icons/obj16/Sensor.gif");
     addImageFilePath(IMAGE_ElectricalComponent, "/icons/obj16/ElectricalComponent.gif");
     addImageFilePath(IMAGE_PowerHardwarePin, "/icons/obj16/PowerHardwarePin.gif");
     addImageFilePath(IMAGE_IOHardwarePin, "/icons/obj16/IOHardwarePin.gif");
	}

}
