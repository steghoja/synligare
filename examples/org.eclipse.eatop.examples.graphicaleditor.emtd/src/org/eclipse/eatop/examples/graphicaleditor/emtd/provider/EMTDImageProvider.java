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
package org.eclipse.eatop.examples.graphicaleditor.emtd.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;



public class EMTDImageProvider extends AbstractImageProvider {
	
	
	public static final String ERROR_MODEL_PROTOTYPE = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".ErrorModelPrototype";
	public static final String FAULT_FAILURE_PROPAGATION_LINK = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FaultFailurePropagationLink";
	public static final String INTERNAL_FAULT_PROTOTYPE = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".InternalFaultPrototype";
	public static final String PROCESS_FAULT_PROTOTYPE = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".ProcessFaultPrototype";
	public static final String ERROR_BEHAVIOR = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".ErrorBehavior";
	
	public static final String ANALYSIS_FUNCTION_PROTOTYPE = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".AnalysisFunctionPrototype";
	public static final String DESIGN_FUNCTION_PROTOTYPE = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".DesignFunctionPrototype";
	public static final String HARDWARE_COMPONENT_PROTOTYPE = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".HardwareComponentPrototype";
	
	public static final String IO_HARDWARE_PIN = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".IOHardwarePin";
	public static final String POWER_HARDWARE_PIN = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".PowerHardwarePin";
	public static final String COMMUNICATION_HARDWARE_PIN = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".CommunicationHardwarePin";
	public static final String FUNCTION_FLOW_PORT = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionFlowPort";
	public static final String FUNCTION_POWER_PORT = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionPowerPort";
	public static final String FUNCTION_CLIENT_SERVER_PORT = EMTDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionClientServerPort";
	
	public EMTDImageProvider() {
	}
	
	@Override
	protected void addAvailableImages() {
		 addImageFilePath(ERROR_MODEL_PROTOTYPE, "/icons/obj16/ErrorModelPrototype.gif");
		 addImageFilePath(FAULT_FAILURE_PROPAGATION_LINK, "/icons/obj16/FaultFailurePropagationLink.gif");
		 addImageFilePath(INTERNAL_FAULT_PROTOTYPE, "/icons/obj16/InternalFaultPrototype.gif");
		 addImageFilePath(PROCESS_FAULT_PROTOTYPE, "/icons/obj16/ProcessFaultPrototype.gif");
		 addImageFilePath(ERROR_BEHAVIOR, "/icons/obj16/ErrorBehavior.gif");
		 addImageFilePath(ANALYSIS_FUNCTION_PROTOTYPE, "/icons/obj16/AnalysisFunctionPrototype.gif");
		 addImageFilePath(DESIGN_FUNCTION_PROTOTYPE, "/icons/obj16/DesignFunctionPrototype.gif");
		 addImageFilePath(HARDWARE_COMPONENT_PROTOTYPE, "/icons/obj16/HardwareComponentType.gif");
		 addImageFilePath(IO_HARDWARE_PIN, "/icons/obj16/IOHardwarePin.gif");
		 addImageFilePath(POWER_HARDWARE_PIN, "/icons/obj16/PowerHardwarePin.gif");
		 addImageFilePath(COMMUNICATION_HARDWARE_PIN, "/icons/obj16/CommunicationHardwarePin.gif");
		 addImageFilePath(FUNCTION_FLOW_PORT, "/icons/obj16/FunctionFlowPort.gif");
		 addImageFilePath(FUNCTION_POWER_PORT, "/icons/obj16/FunctionPowerPort.gif");
		 addImageFilePath(FUNCTION_CLIENT_SERVER_PORT, "/icons/obj16/FunctionClientServerPort.gif");
	}
}
