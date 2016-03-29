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
package org.eclipse.eatop.examples.graphicaleditor.depd.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;


public class DEPDImageProvider extends AbstractImageProvider {
	/** References to image RequirementsModel. */
	//public static final String IMAGE_REQUIREMENT_MODEL ="org.mourad.examples.graphiti.imageProvider";

	public static final String IMAGE_ITEM = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Item";
	public static final String IMAGE_FLAW_FEATURE = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FeatureFlaw";
	public static final String IMAGE_HAZARD = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Hazard";
	public static final String IMAGE_HAZARDOUS_EVENT = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".HazardousEvent";
	public static final String IMAGE_SAFETY_GOAL = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".SafetyGoal";
	public static final String IMAGE_REQUIREMENT = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Requirement";
	public static final String IMAGE_USE_CASE = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".UseCase";
	public static final String IMAGE_OPERATIONAL_SITUATION = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".OperationalSituation";
	public static final String IMAGE_MODE = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Mode";
	public static final String IMAGE_MODE_GROUP = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".ModeGroup";
	public static final String IMAGE_FUNCTION_BEHAVIOR = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionBehavior";
	public static final String IMAGE_FUNCTION_TRIGGER = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".FunctionTrigger";
	public static final String IMAGE_VEHICLE_FEATURE = DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".VehicleFeature";
	public static final String IMAGE_QUALITY_REQUIREMENT=DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".QualityRequireemnt";
	public static final String IMAGE_ANALYSIS_FUNCTION_PROTOTYPE=DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".AnalysisFunctionPrototype";
	public static final String IMAGE_DESIGN_FUNCTION_PROTOTYPE=DEPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".DesignFunctionPrototype";
	public DEPDImageProvider() {
	}

	@Override
	protected void addAvailableImages() {
		 addImageFilePath(IMAGE_ITEM, "/icons/obj16/Item.gif");
	     addImageFilePath(IMAGE_FLAW_FEATURE, "/icons/obj16/FeatureFlaw.gif");
	     addImageFilePath(IMAGE_HAZARD, "/icons/obj16/Hazard.gif");
	     addImageFilePath(IMAGE_HAZARDOUS_EVENT, "/icons/obj16/HazardousEvent.gif");
	     addImageFilePath(IMAGE_SAFETY_GOAL, "/icons/obj16/SafetyGoal.gif");
	     addImageFilePath(IMAGE_REQUIREMENT, "/icons/obj16/Requirement.gif");
	     addImageFilePath(IMAGE_USE_CASE, "/icons/obj16/UseCase.gif");
	     addImageFilePath(IMAGE_OPERATIONAL_SITUATION, "/icons/obj16/OperationalSituation.gif");
	     addImageFilePath(IMAGE_MODE, "/icons/obj16/Mode.gif");
	     addImageFilePath(IMAGE_MODE_GROUP, "/icons/obj16/ModeGroup.gif");
	     addImageFilePath(IMAGE_FUNCTION_BEHAVIOR, "/icons/obj16/FunctionBehavior.gif");
	     addImageFilePath(IMAGE_FUNCTION_TRIGGER, "/icons/obj16/FunctionTrigger.gif");
	     addImageFilePath(IMAGE_QUALITY_REQUIREMENT, "/icons/obj16/QualityRequirement.gif");
	     addImageFilePath(IMAGE_ANALYSIS_FUNCTION_PROTOTYPE, "/icons/obj16/AnalysisFunctionPrototype.gif");
	     addImageFilePath(IMAGE_DESIGN_FUNCTION_PROTOTYPE,"/icons/obj16/DesignFunctionPrototype.gif");
}
}