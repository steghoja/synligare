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

import org.eclipse.graphiti.dt.AbstractDiagramTypeProvider;
import org.eclipse.graphiti.tb.IToolBehaviorProvider;

public class DFADTypeDiagramProvider extends AbstractDiagramTypeProvider {

	private IToolBehaviorProvider[] toolBehaviorProviders;
	public static String DIAGRAM_TYPE_ID = "org.eclipse.eatop.examples.graphicaleditor.dfad.diagramType";

	public DFADTypeDiagramProvider() {
		setFeatureProvider(new DFADFeatureProvider(this));
	}

	@Override
	public IToolBehaviorProvider[] getAvailableToolBehaviorProviders() {
		if (toolBehaviorProviders == null) {
			toolBehaviorProviders = new IToolBehaviorProvider[] { new DFADToolBehaviorProvider(
					this) };
		}
		return toolBehaviorProviders;

	}

	/**
	 * If isAutoUpdateAtRuntime returns true, then the diagram will be updated,
	 * when it is already open in the graphical editor, but only if the editor
	 * is already dirty.
	 */
	@Override
	public boolean isAutoUpdateAtRuntime() {
		return true;
	}

	/**
	 * If isAutoUpdateAtStartup returns true, then the diagram will be updated,
	 * when it is initially opened in the graphical editor. This will make the
	 * editor dirty.
	 */
	@Override
	public boolean isAutoUpdateAtStartup() {
		return true;
	}

	/**
	 * If the editor is already dirty and the user chooses to discard his
	 * changes (reset the diagram), when a change from outside the diagram
	 * occurs.
	 */
	@Override
	public boolean isAutoUpdateAtReset() {
		return true;
	}

}
