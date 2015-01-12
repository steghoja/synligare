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
package org.eclipse.eatop.examples.graphicaleditor.depald.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class DEPALDImageProvider extends AbstractImageProvider {
	public static final String IMAGE_FSC = DEPALDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionalSafetyConcept";
	public static final String IMAGE_SATISFY = DEPALDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Satisfy";
	public static final String IMAGE_ANALYSIS_FUNCTION_PROTOTYPE = DEPALDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".AnalysisFunctionPrototype";
	public static final String IMAGE_REQUIREMENT = DEPALDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Requirement";
	
	public DEPALDImageProvider() {
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMAGE_FSC, "/icons/obj16/FunctionalSafetyConcept.gif");
		addImageFilePath(IMAGE_SATISFY, "/icons/obj16/Satisfy.gif");
		addImageFilePath(IMAGE_REQUIREMENT, "/icons/obj16/Requirement.gif");
		addImageFilePath(IMAGE_ANALYSIS_FUNCTION_PROTOTYPE, "/icons/obj16/AnalysisFunctionPrototype.gif");
	}
}
