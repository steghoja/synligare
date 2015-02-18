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
package org.eclipse.eatop.examples.graphicaleditor.reqd.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class REQDImageProvider extends AbstractImageProvider {
	/** References to image RequirementsModel. */
	// public static final String IMAGE_REQUIREMENT_MODEL
	// ="org.mourad.examples.graphiti.imageProvider";

	public static final String IMAGE_REQUIREMENT_MODEL = REQDDiagramTypeProvider.DIAGRAM_TYPE_ID
			+ ".RequirementsModel";
	public static final String IMAGE_REQUIREMENT = REQDDiagramTypeProvider.DIAGRAM_TYPE_ID
			+ ".Requirement";
	public static final String IMAGE_USE_CASE = REQDDiagramTypeProvider.DIAGRAM_TYPE_ID
			+ ".UseCase";
	public static final String IMAGE_OPERATIONAL_SITUATION = REQDDiagramTypeProvider.DIAGRAM_TYPE_ID
			+ ".OperationalSituation";
	public static final String IMAGE_QualityREQUIREMENT = REQDDiagramTypeProvider.DIAGRAM_TYPE_ID
			+ ".QualityRequirement";
	public static final String IMAGE_FSC = REQDDiagramTypeProvider.DIAGRAM_TYPE_ID
			+ ".FunctionalSafetyConcept";
	public static final String IMAGE_TSC = REQDDiagramTypeProvider.DIAGRAM_TYPE_ID
			+ ".TechnicalSafetyConcept";

	public REQDImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMAGE_REQUIREMENT_MODEL,
				"/icons/obj16/RequirementsModel.gif");
		addImageFilePath(IMAGE_REQUIREMENT, "/icons/obj16/Requirement.gif");
		addImageFilePath(IMAGE_USE_CASE, "/icons/obj16/UseCase.gif");
		addImageFilePath(IMAGE_OPERATIONAL_SITUATION,
				"/icons/obj16/OperationalSituation.gif");
		addImageFilePath(IMAGE_QualityREQUIREMENT,
				"/icons/obj16/QualityRequirement.gif");
		addImageFilePath(IMAGE_FSC, "/icons/obj16/FunctionalSafetyConcept.gif");
		addImageFilePath(IMAGE_TSC, "/icons/obj16/TechnicalSafetyConcept.gif");
	}

}
