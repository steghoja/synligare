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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.examples.actions.FindInstanceRefsAction;
import org.eclipse.eatop.examples.actions.FindReferencesAction;
import org.eclipse.eatop.examples.actions.GoToInstanceReferredItemAction;
import org.eclipse.eatop.examples.actions.GotoTypeAction;
import org.eclipse.eatop.examples.actions.internal.messages.Messages;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sphinx.platform.ui.util.SelectionUtil;
import org.eclipse.ui.navigator.ICommonMenuConstants;

public class EastadlExampleActionProvider extends AbstractEatopExampleActionProvider {

	protected FindReferencesAction findReferencesAction;
	protected GotoTypeAction gotoTypeAction;

	protected Collection<IAction> createinstanceRefsActions;
	protected Collection<IAction> createReferenceActions;
	protected Collection<IAction> followAssociationActions;

	@Override
	protected void doInit() {
		gotoTypeAction = new GotoTypeAction();

		if (selectionProvider != null) {
			selectionProvider.addSelectionChangedListener(gotoTypeAction);

			ISelection selection = selectionProvider.getSelection();
			IStructuredSelection structuredSelection = SelectionUtil.getStructuredSelection(selection);
			gotoTypeAction.updateSelection(structuredSelection);
		}
	}

	@Override
	public void fillContextMenu(IMenuManager contextMenuManager) {
		contextMenuManager.appendToGroup(ICommonMenuConstants.GROUP_GOTO, new ActionContributionItem(gotoTypeAction));
		super.fillContextMenu(contextMenuManager);

		updateActions(getContext().getSelection());

		MenuManager findInstanceRefMenuManager = new MenuManager(Messages.act_findInstanceRefs_label);
		populateManager(findInstanceRefMenuManager, createinstanceRefsActions, null);
		contextMenuManager.appendToGroup(ICommonMenuConstants.GROUP_GOTO, findInstanceRefMenuManager);

		MenuManager findRefMenuManager = new MenuManager(Messages.act_findReferences_label);
		populateManager(findRefMenuManager, createReferenceActions, null);
		populateManager(findRefMenuManager, followAssociationActions, null);
		contextMenuManager.appendToGroup(ICommonMenuConstants.GROUP_GOTO, findRefMenuManager);

		MenuManager associationMenuManager = new MenuManager("Associations"); //$NON-NLS-1$
		populateManager(associationMenuManager, followAssociationActions, null);
		contextMenuManager.appendToGroup(ICommonMenuConstants.GROUP_GOTO, associationMenuManager);
	}

	@Override
	public void dispose() {
		if (selectionProvider != null && gotoTypeAction != null) {
			selectionProvider.removeSelectionChangedListener(gotoTypeAction);
		}
		super.dispose();
	}

	protected void updateActions(ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			createinstanceRefsActions = generateCreateInstanceRefActions((IStructuredSelection) selection);
			createReferenceActions = generateCreateRefActions((IStructuredSelection) selection);
			followAssociationActions = generateJumpToReferredActions((IStructuredSelection) selection);
		}
	}

	protected Collection<IAction> generateCreateInstanceRefActions(IStructuredSelection selection) {
		List<IAction> actions = new ArrayList<IAction>();
		if (selection.getFirstElement() instanceof EObject) {
			List<EObject> refs = ModelSearcher.findInstanceReferences((EObject) selection.getFirstElement());

			for (EObject ref : refs) {
				actions.add(new FindInstanceRefsAction(ref, selection));
			}

			Collections.sort(actions, new Comparator<IAction>() {
				@Override
				public int compare(IAction a1, IAction a2) {
					if (a1.getText() == null && a2.getText() != null) {
						return -1;
					} else if (a1.getText() == null && a2.getText() == null) {
						return 0;
					} else if (a1.getText() != null && a2.getText() == null) {
						return 1;
					} else {
						return CommonPlugin.INSTANCE.getComparator().compare(a1.getText(), a2.getText());
					}
				}
			});
		}
		return actions;
	}

	protected Collection<IAction> generateCreateRefActions(IStructuredSelection selection) {
		List<IAction> actions = new ArrayList<IAction>();
		if (selection.getFirstElement() instanceof EObject) {
			List<EObject> refs = ModelSearcher.findReferences((EObject) selection.getFirstElement());

			for (EObject ref : refs) {
				actions.add(new FindReferencesAction(ref, selection));
			}

			Collections.sort(actions, new Comparator<IAction>() {
				@Override
				public int compare(IAction a1, IAction a2) {
					if (a1.getText() == null && a2.getText() != null) {
						return -1;
					} else if (a1.getText() == null && a2.getText() == null) {
						return 0;
					} else if (a1.getText() != null && a2.getText() == null) {
						return 1;
					} else {
						return CommonPlugin.INSTANCE.getComparator().compare(a1.getText(), a2.getText());
					}
				}
			});
		}
		return actions;
	}

	protected Collection<IAction> generateJumpToReferredActions(IStructuredSelection selection) {
		List<IAction> actions = new ArrayList<IAction>();
		if (selection.getFirstElement() instanceof EObject) {
			for (EObject ref : ModelSearcher.getAssociationChildren((EObject) selection.getFirstElement())) {
				actions.add(new GoToInstanceReferredItemAction(ref, selection));
			}

			Collections.sort(actions, new Comparator<IAction>() {
				@Override
				public int compare(IAction a1, IAction a2) {
					if (a1.getText() == null && a2.getText() != null) {
						return -1;
					} else if (a1.getText() == null && a2.getText() == null) {
						return 0;
					} else if (a1.getText() != null && a2.getText() == null) {
						return 1;
					} else {
						return CommonPlugin.INSTANCE.getComparator().compare(a1.getText(), a2.getText());
					}
				}
			});
		}
		return actions;
	}
}
