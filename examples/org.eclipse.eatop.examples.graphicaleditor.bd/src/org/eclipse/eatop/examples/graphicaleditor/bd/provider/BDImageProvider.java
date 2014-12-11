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
package org.eclipse.eatop.examples.graphicaleditor.bd.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class BDImageProvider extends AbstractImageProvider {
	public static final String IMAGE_MODE = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Mode";
	public static final String IMAGE_MODE_GROUP = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".ModeGroup";
	public static final String IMAGE_FUNCTION_BEHAVIOR = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionBehavior";
	public static final String IMAGE_FUNCTION_TRIGGER = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionTrigger";
	public static final String ANALYSIS_FUNCTION_PROTOTYPE = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".AnalysisFunctionPrototype";
	public static final String DESIGN_FUNCTION_PROTOTYPE = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".DesignFunctionPrototype";
	public static final String ANALYSIS_FUNCTION_TYPE = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".AnalysisFunctionType";
	public static final String DESIGN_FUNCTION_TYPE = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".DesignFunctionType";
	public static final String FUNCTION_FLOW_PORT = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionFlowPort";
	public static final String FUNCTION_POWER_PORT = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionPowerPort";
	public static final String FUNCTION_CLIENT_SERVER_PORT = BDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionClientServerPort";
	
	public BDImageProvider() {
	}

	@Override
	protected void addAvailableImages() {
		 addImageFilePath(IMAGE_MODE, "/icons/obj16/Mode.gif");
		 addImageFilePath(IMAGE_MODE_GROUP, "/icons/obj16/ModeGroup.gif");
		 addImageFilePath(IMAGE_FUNCTION_BEHAVIOR, "/icons/obj16/FunctionBehavior.gif");
		 addImageFilePath(IMAGE_FUNCTION_TRIGGER, "/icons/obj16/FunctionTrigger.gif");
		 addImageFilePath(ANALYSIS_FUNCTION_PROTOTYPE, "/icons/obj16/AnalysisFunctionPrototype.gif");
		 addImageFilePath(DESIGN_FUNCTION_PROTOTYPE, "/icons/obj16/DesignFunctionPrototype.gif");
		 addImageFilePath(ANALYSIS_FUNCTION_TYPE, "/icons/obj16/AnalysisFunctionType.gif");
		 addImageFilePath(DESIGN_FUNCTION_TYPE, "/icons/obj16/DesignFunctionType.gif");
		 addImageFilePath(FUNCTION_FLOW_PORT, "/icons/obj16/FunctionFlowPort.gif");
		 addImageFilePath(FUNCTION_POWER_PORT, "/icons/obj16/FunctionPowerPort.gif");
		 addImageFilePath(FUNCTION_CLIENT_SERVER_PORT, "/icons/obj16/FunctionClientServerPort.gif");
	}

}
