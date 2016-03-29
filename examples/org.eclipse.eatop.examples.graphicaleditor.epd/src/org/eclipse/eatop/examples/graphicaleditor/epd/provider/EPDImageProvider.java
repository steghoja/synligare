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
package org.eclipse.eatop.examples.graphicaleditor.epd.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;


public class EPDImageProvider extends AbstractImageProvider {
	public static final String IMAGE_EAPACKAGE = EPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".EAPackage";
	public static final String IMAGE_REQUIREMENT_MODEL = EPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".RequirementsModel";
	public static final String IMAGE_DEPENDABILITY = EPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Dependability";
	public static final String IMAGE_SYSTEM_MODEL = EPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".SystemModel";
	public static final String IMAGE_BEHAVIOR = EPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Behavior";
	public static final String IMAGE_ENVIRONMENT = EPDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Environment";
	
	public EPDImageProvider() {
		super();
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMAGE_EAPACKAGE, "/icons/obj16/EAPackage.gif");
		addImageFilePath(IMAGE_REQUIREMENT_MODEL, "/icons/obj16/RequirementsModel.gif");
		addImageFilePath(IMAGE_DEPENDABILITY, "/icons/obj16/Dependability.gif");
		addImageFilePath(IMAGE_SYSTEM_MODEL, "/icons/obj16/SystemModel.gif");
		addImageFilePath(IMAGE_BEHAVIOR, "/icons/obj16/Behavior.gif");
		addImageFilePath(IMAGE_ENVIRONMENT, "/icons/obj16/Environment.gif");
	}

}
