/**
 * <copyright>
 * 
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.examples.common.ui.perspectives;

/**
 * Interface defining constants used for the definition of the Eatop perspective.
 */
public interface IEatopPerspectiveConstants {

	String TOP_LEFT = "topLeft"; //$NON-NLS-1$
	String BOTTOM_RIGHT = "bottomRight"; //$NON-NLS-1$

	/***************************
	 * --- Perspective IDs --- *
	 ***************************/

	/**
	 * The id for the Eclipse Resource perspective.
	 */
	String ID_RESOURCE_PERSPECTIVE = "org.eclipse.ui.resourcePerspective"; //$NON-NLS-1$

	/*********************
	 * --- Views IDs --- *
	 *********************/

	/**
	 * The view id for the Eclipse Problems view.
	 */
	String ID_PROBLEMS_VIEW = "org.eclipse.ui.views.ProblemView";//$NON-NLS-1$

	/**
	 * The view id for the Eclipse Error Log view.
	 */
	String ID_ERROR_LOG_VIEW = "org.eclipse.pde.runtime.LogView"; //$NON-NLS-1$

	/**
	 * The view id for the Eclipse Console view.
	 */
	String ID_CONSOLE_VIEW = "org.eclipse.ui.console.ConsoleView"; //$NON-NLS-1$

	/**
	 * The view id for the Eatop EAST-ADL Explorer view.
	 */
	String ID_EAST_ADL_EXPLORER = "org.eclipse.eatop.examples.explorer.views.eastadlExplorer"; //$NON-NLS-1$

	/***********************
	 * --- Wizards IDs --- *
	 ***********************/

	/**
	 * The wizard id for the Eatop new EAST-ADL project wizard.
	 */
	String ID_EAST_ADL_NEW_PROJECT = "org.eclipse.eatop.examples.common.ui.newWizards.eastadlProject"; //$NON-NLS-1$

	/**
	 * The wizard id for the Eatop new EAST-ADL file wizard.
	 */
	String ID_EAST_ADL_NEW_FILE = "org.eclipse.eatop.examples.common.ui.newWizards.eastadlFile"; //$NON-NLS-1$

	/**
	 * The wizard id for the Eclipse new file wizard.
	 */
	String ID_ECLIPSE_NEW_FILE = "org.eclipse.ui.wizards.new.file"; //$NON-NLS-1$

	/**
	 * The wizard id for the Eclipse new folder wizard.
	 */
	String ID_ECLIPSE_NEW_FOLDER = "org.eclipse.ui.wizards.new.folder"; //$NON-NLS-1$

	/**************************
	 * --- ActionSets IDs --- *
	 **************************/
	String ID_TEAM_ACTIONSET = "org.eclipse.team.ui.actionSet";//$NON-NLS-1$
	String ID_LAUNCH_ACTIONSET = "org.eclipse.debug.ui.launchActionSet";//$NON-NLS-1$
}
