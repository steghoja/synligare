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
package org.eclipse.eatop.examples.graphicaleditor.aftd.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;


public class AFTDImageProvider extends AbstractImageProvider {
	/** References to image RequirementsModel. */

	public static final String IMAGE_FunctionFlowPort= AFTDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionFlowPort";
	public static final String IMAGE_FunctionClientServerPort= AFTDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionClientServerPort";
	public static final String IMAGE_FunctionPowerPort= AFTDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionPowerPort";
	public static final String IMAGE_AnalysisFunctionPrototype= AFTDTypeDiagramProvider.DIAGRAM_TYPE_ID + ".AnalysisFunctionPrototype";
	
	public AFTDImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMAGE_FunctionFlowPort, "/icons/obj16/FunctionFlowPort.gif");
		addImageFilePath(IMAGE_FunctionClientServerPort, "/icons/obj16/FunctionClientServerPort.gif");
		addImageFilePath(IMAGE_FunctionPowerPort, "/icons/obj16/FunctionPowerPort.gif");
		addImageFilePath(IMAGE_AnalysisFunctionPrototype, "/icons/obj16/AnalysisFunctionPrototype.gif");
	}

}
