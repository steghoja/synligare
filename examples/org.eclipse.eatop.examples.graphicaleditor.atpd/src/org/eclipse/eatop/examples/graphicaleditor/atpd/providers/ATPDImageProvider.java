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
package org.eclipse.eatop.examples.graphicaleditor.atpd.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;








public class ATPDImageProvider extends AbstractImageProvider {
	/** References to image RequirementsModel. */
	//public static final String IMAGE_REQUIREMENT_MODEL ="org.mourad.examples.graphiti.imageProvider";

	public static final String IMAGE_AnalysisLevel = ATPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".AnalysisLevel";
	public static final String IMAGE_EAPackage= ATPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".EAPackage";
	public static final String IMAGE_AnalysisFunctionType= ATPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".AnalysisFunctionType";
	public static final String IMAGE_FunctionalDevice= ATPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionalDevice";
	public static final String IMAGE_FunctionFlowPort= ATPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionFlowPort";
	public static final String IMAGE_FunctionClientServerPort= ATPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionClientServerPort";
	public static final String IMAGE_FunctionPowerPort= ATPDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionPowerPort";

	public ATPDImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
     addImageFilePath(IMAGE_AnalysisLevel, "/icons/obj16/AnalysisLevel.gif");
     addImageFilePath(IMAGE_EAPackage, "/icons/obj16/EAPackage.gif");
     addImageFilePath(IMAGE_AnalysisFunctionType, "/icons/obj16/AnalysisFunctionType.gif");
     addImageFilePath(IMAGE_FunctionalDevice, "/icons/obj16/FunctionalDevice.gif");
     addImageFilePath(IMAGE_FunctionFlowPort, "/icons/obj16/FunctionFlowPort.gif");
     addImageFilePath(IMAGE_FunctionClientServerPort, "/icons/obj16/FunctionClientServerPort.gif");
     addImageFilePath(IMAGE_FunctionPowerPort, "/icons/obj16/FunctionPowerPort.gif");
	}

}
