/**
 * <copyright>
 * 
 * Copyright (c) 2014 Arccore and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Arccore - Initial API and implementation
 * 
 * </copyright>
 */

package org.eclipse.eatop.examples.actions.providers;

import org.eclipse.eatop.examples.actions.internal.messages.Messages;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.sphinx.emf.ui.actions.providers.BasicActionProvider;
import org.eclipse.ui.navigator.ICommonMenuConstants;

public abstract class AbstractEatopExampleActionProvider extends BasicActionProvider {

	@Override
	protected IMenuManager addSubMenu(IMenuManager contextMenuManager) {
		IMenuManager subMenuManager = contextMenuManager.findMenuUsingPath(IEatopExampleMenuConstants.MENU_EATOP_EXAMPLES_ID);

		if (subMenuManager == null) {
			subMenuManager = new MenuManager(Messages.menu_references_label, IEatopExampleMenuConstants.MENU_EATOP_EXAMPLES_ID);
			contextMenuManager.appendToGroup(ICommonMenuConstants.GROUP_ADDITIONS, subMenuManager);

			subMenuManager.add(new Separator(IEatopExampleMenuConstants.GROUP_EATOP_EXAMPLES));
		}

		return subMenuManager;
	}

	public interface IEatopExampleMenuConstants {

		/**
		 * Identifier of the EATOP Examples sub menu.
		 */
		String MENU_EATOP_EXAMPLES_ID = "eatop.examples.menu";//$NON-NLS-1$

		/**
		 * Identifier of the EATOP Examples menu item group.
		 */
		String GROUP_EATOP_EXAMPLES = "eatop.examples.group";//$NON-NLS-1$
	}

}
