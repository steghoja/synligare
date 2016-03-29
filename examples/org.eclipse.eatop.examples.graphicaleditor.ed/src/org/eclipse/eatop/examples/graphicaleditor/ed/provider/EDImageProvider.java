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
package org.eclipse.eatop.examples.graphicaleditor.ed.provider;

import org.eclipse.graphiti.ui.platform.AbstractImageProvider;


public class EDImageProvider extends AbstractImageProvider {
	public static final String ENVIRONMENT = EDDiagramTypeProvider.DIAGRAM_TYPE_ID + ".Environment";
	
	public EDImageProvider() {
	}
	
	@Override
	protected void addAvailableImages() {
		addImageFilePath(ENVIRONMENT, "/icons/obj16/Environment.gif");
	}
}
