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
package org.eclipse.eatop.examples.graphicaleditor.depdld.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;



public class DEPDLDImageProvider extends AbstractImageProvider {
	public static final String IMAGE_SATISFY = DEPDLDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Satisfy";
	public static final String IMAGE_TSC = DEPDLDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".TechnicalSafetyConcept";
	public static final String IMAGE_REQUIREMENT = DEPDLDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Requirement";
	public static final String IMAGE_DESIGN_FUNCTION_PROTOTYPE = DEPDLDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".DesignFunctionPrototype";
	public DEPDLDImageProvider() {
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMAGE_SATISFY, "/icons/obj16/Satisfy.gif");
		addImageFilePath(IMAGE_TSC, "/icons/obj16/TechnicalSafetyConcept.gif");
		addImageFilePath(IMAGE_REQUIREMENT, "/icons/obj16/Requirement.gif");
		addImageFilePath(IMAGE_DESIGN_FUNCTION_PROTOTYPE, "/icons/obj16/DesignFunctionPrototype.gif");
	}

}
