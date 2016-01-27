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
package org.eclipse.eatop.examples.graphicaleditor.dfad.providers;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;


public class DFADImageProvider extends AbstractImageProvider {
	/** References to image RequirementsModel. */
	//public static final String IMAGE_REQUIREMENT_MODEL ="org.mourad.examples.graphiti.imageProvider";

	public static final String IMAGE_FunctionAllocation = DFADTypeDiagramProvider.DIAGRAM_TYPE_ID + ".FunctionAllocation";
	public static final String IMAGE_AnalysisFunctionPrototype= DFADTypeDiagramProvider.DIAGRAM_TYPE_ID + ".AnalysisFunctionPrototype";
	public static final String IMAGE_DesignFunctionPrototype= DFADTypeDiagramProvider.DIAGRAM_TYPE_ID + ".DesignFunctionPrototype";
	public static final String IMAGE_HardwareComponentPrototype = DFADTypeDiagramProvider.DIAGRAM_TYPE_ID
			+ ".HardwareComponentPrototype";
	public DFADImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
	    addImageFilePath(IMAGE_FunctionAllocation, "/icons/obj16/FunctionAllocation.gif");
	    addImageFilePath(IMAGE_AnalysisFunctionPrototype, "/icons/obj16/AnalysisFunctionPrototype.gif");
	    addImageFilePath(IMAGE_DesignFunctionPrototype, "/icons/obj16/DesignFunctionPrototype.gif");
	    addImageFilePath(IMAGE_HardwareComponentPrototype,"/icons/obj16/HardwareComponentPrototype.gif");
	}
}
