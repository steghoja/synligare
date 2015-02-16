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
package org.eclipse.eatop.examples.graphicaleditor.depemtpd.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class DEPEMTPDImageProvider extends AbstractImageProvider {
	public static final String IMAGE_ERROR_MODEL_TYPE = DEPEMTPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".ErrorModelType";
	public static final String IMAGE_ANALYSIS_FUNCTION_TYPE = DEPEMTPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".AnalysisFunctionType";
	public static final String IMAGE_DESIGN_FUNCTION_TYPE = DEPEMTPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".DesignFunctionType";
	public static final String IMAGE_HARDWARE_COMPONENT_TYPE = DEPEMTPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".HardwareComponentType";
	
	public DEPEMTPDImageProvider() {
	}
	
	@Override
	protected void addAvailableImages() {
		 addImageFilePath(IMAGE_ERROR_MODEL_TYPE, "/icons/obj16/ErrorModelType.gif");
		 addImageFilePath(IMAGE_ANALYSIS_FUNCTION_TYPE, "/icons/obj16/AnalysisFunctionType.gif");
		 addImageFilePath(IMAGE_DESIGN_FUNCTION_TYPE, "/icons/obj16/DesignFunctionType.gif");
		 addImageFilePath(IMAGE_HARDWARE_COMPONENT_TYPE, "/icons/obj16/HardwareComponentType.gif");
	}
}
