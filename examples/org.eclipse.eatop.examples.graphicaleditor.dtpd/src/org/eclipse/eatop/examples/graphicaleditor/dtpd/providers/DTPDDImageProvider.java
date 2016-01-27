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
package org.eclipse.eatop.examples.graphicaleditor.dtpd.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;


public class DTPDDImageProvider extends AbstractImageProvider {
	/** References to image RequirementsModel. */

	public static final String IMAGE_DesignLevel = DTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".DesignLevel";
	public static final String IMAGE_EAPackage= DTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".EAPackage";
	public static final String IMAGE_DesignFunctionType= DTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".DesignFunctionType";
	public static final String IMAGE_BasicSoftwareType= DTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".BasicSoftwareFunctionType";
	public static final String IMAGE_FunctionalDevice= DTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionalDevice";
	public static final String IMAGE_FunctionFlowPort= DTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionFlowPort";
	public static final String IMAGE_FunctionClientServerPort= DTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionClientServerPort";
	public static final String IMAGE_FunctionPowerPort= DTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionPowerPort";
	public static final String IMAGE_LocalDeviceManager= DTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".LocalDeviceManager";
	public static final String IMAGE_HardwareFunctionTypee= DTPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".HardwareFunctionType";

	public DTPDDImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
     
     addImageFilePath(IMAGE_DesignLevel, "/icons/obj16/DesignLevel.gif");
     addImageFilePath(IMAGE_EAPackage, "/icons/obj16/EAPackage.gif");
     addImageFilePath(IMAGE_DesignFunctionType, "/icons/obj16/DesignFunctionType.gif");
     addImageFilePath(IMAGE_FunctionalDevice, "/icons/obj16/FunctionalDevice.gif");
     addImageFilePath(IMAGE_FunctionFlowPort, "/icons/obj16/FunctionFlowPort.gif");
     addImageFilePath(IMAGE_FunctionClientServerPort, "/icons/obj16/FunctionClientServerPort.gif");
     addImageFilePath(IMAGE_FunctionPowerPort, "/icons/obj16/FunctionPowerPort.gif");
     addImageFilePath(IMAGE_BasicSoftwareType, "/icons/obj16/BasicSoftwareFunctionType.gif");
     addImageFilePath(IMAGE_LocalDeviceManager, "/icons/obj16/LocalDeviceManager.gif");
     addImageFilePath(IMAGE_HardwareFunctionTypee, "/icons/obj16/HardwareFunctionType.gif");
	}

}
