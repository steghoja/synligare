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

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;



public class DEPDLDDiagramTypeProvider extends AbstractDiagramTypeProvider implements IDiagramTypeProvider  {
	public static String DIAGRAM_TYPE_ID = "org.eclipse.eatop.examples.graphicaleditor.depdld.diagramType";
	private IToolBehaviorProvider[] toolBehaviorProviders;
	public DEPDLDDiagramTypeProvider() {
		super();
		setFeatureProvider(new DEPDLDFeatureProvider(this));
	}
	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
			toolBehaviorProviders = new IToolBehaviorProvider[] { new DEPDLDToolBehaviorProvider(
					this) };
		}
		return toolBehaviorProviders;
	}
}
