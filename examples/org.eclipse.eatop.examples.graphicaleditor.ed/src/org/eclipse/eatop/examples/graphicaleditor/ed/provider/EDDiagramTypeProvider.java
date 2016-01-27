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

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

public class EDDiagramTypeProvider extends AbstractDiagramTypeProvider {

	public static String DIAGRAM_TYPE_ID = "org.eclipse.eatop.examples.graphicaleditor.ed.diagramType";

	private IToolBehaviorProvider[] toolBehaviorProviders;

	public EDDiagramTypeProvider() {
		super();
		setFeatureProvider(new EDFeatureProvider(this));
	}

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
			toolBehaviorProviders = new IToolBehaviorProvider[] { new EDToolBehaviorProvider(this) };
		}
		return toolBehaviorProviders;
	}
}
