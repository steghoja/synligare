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
package org.eclipse.eatop.examples.graphicaleditor.depscd.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;

public class DEPSCDImageProvider extends AbstractImageProvider {
	public static final String IMAGE_WARRANT = DEPSCDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Warrant";
	public static final String IMAGE_CLAIM = DEPSCDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Claim";
	public static final String IMAGE_Ground = DEPSCDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Ground";
	public static final String IMAGE_QUALITY_REQUIREMENT = DEPSCDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".QualityRequirement";
	public DEPSCDImageProvider() {
	}

	@Override
	protected void addAvailableImages() {
		addImageFilePath(IMAGE_WARRANT, "/icons/obj16/Warrant.gif");
		addImageFilePath(IMAGE_CLAIM, "/icons/obj16/Claim.gif");
		addImageFilePath(IMAGE_Ground, "/icons/obj16/Ground.gif");
		addImageFilePath(IMAGE_QUALITY_REQUIREMENT, "/icons/obj16/QualityRequirement.gif");
	}
}
