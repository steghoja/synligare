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
package org.eclipse.eatop.examples.explorer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sphinx.emf.explorer.actions.providers.BasicModelEditActionProvider;
import org.eclipse.sphinx.platform.ui.util.SelectionUtil;
import org.eclipse.ui.navigator.ICommonMenuConstants;

public class ModelExampleActionProvider extends BasicModelEditActionProvider {

	public ModelExampleActionProvider() {

	}

	/*
	 * @Override protected void updateActions(ISelection selection) { // If the selection is the inherited child of a
	 * type and the selection parent is a prototype of that type, // editing the selection is not allowed if(selection
	 * instanceof TreeSelection && !isParentReal((TreeSelection)selection)){ cutAction.setEnabled(false);
	 * deleteAction.setEnabled(false); deleteAction.setEnabled(false); copyAction.setEnabled(false); } else{
	 * super.updateActions(selection); } }
	 */

	@Override
	protected void updateActions(ISelection selection) {
		// Switch actions to editing domain behind current selection
		TransactionalEditingDomain editingDomain = getEditingDomainFromSelection(selection);
		cutAction.setEditingDomain(editingDomain);
		copyAction.setEditingDomain(editingDomain);
		pasteAction.setEditingDomain(editingDomain);
		deleteAction.setEditingDomain(editingDomain);

		// Update action states according to current selection
		IStructuredSelection structuredSelection = SelectionUtil.getStructuredSelection(selection);
		cutAction.selectionChanged(structuredSelection);
		copyAction.selectionChanged(structuredSelection);
		pasteAction.selectionChanged(structuredSelection);
		deleteAction.selectionChanged(structuredSelection);

		// Query new child/sibling descriptors for current selection
		/*
		 * !! Important Note !! This need to be redone upon each time the context menu is about to be shown (but not
		 * only when the selection changes). The new child/sibling descriptors already contain the new objects to be
		 * added as new child/sibling to the selected object when the corresponding new child/sibling action gets
		 * invoked. These new objects need to be brand new instances in each context menu instance so as to make sure
		 * that users can successively create multiple new children/siblings with the same type for same selected object
		 * and feature. If new child/sibling descriptors were only recreated upon selection change it could happen that
		 * users end up with the same new child/sibling object instance being added again and again instead.
		 */
		if (shouldCreateCreateChildActions(structuredSelection.getFirstElement())) {
			Collection<?> newChildDescriptors = null;
			Collection<?> newSiblingDescriptors = null;

			if (editingDomain != null && structuredSelection.size() == 1) {
				newChildDescriptors = getNewChildDescriptors(editingDomain, structuredSelection.getFirstElement(), null);
				newSiblingDescriptors = getNewChildDescriptors(editingDomain, null, structuredSelection.getFirstElement());
			}

			// Generate new create child/sibling actions
			createChildActions = generateCreateChildActions(editingDomain, newChildDescriptors, selection);
			createChildSubmenuActions = extractSubmenuActions(createChildActions, selection);
			createSiblingActions = generateCreateSiblingActions(editingDomain, newSiblingDescriptors, selection);
			createSiblingSubmenuActions = extractSubmenuActions(createSiblingActions, selection);
		}
	}

	/*
	 * @Override public void fillContextMenu(IMenuManager menuManager) { // TODO Auto-generated method stub
	 * super.fillContextMenu(menuManager); }
	 */

	@Override
	public void fillContextMenu(IMenuManager menuManager) {
		updateActions(getContext().getSelection());

		// Add New Child sub menu
		MenuManager createChildMenuManager = new MenuManager("New Child"); //$NON-NLS-1$
		populateManager(createChildMenuManager, createChildSubmenuActions, null);
		populateManager(createChildMenuManager, createChildActions, null);
		menuManager.appendToGroup(ICommonMenuConstants.GROUP_NEW, createChildMenuManager);

		/*
		 * MenuManager addChildMetaModelManager = new MenuManager("Metamodel");
		 * populateManager(addChildMetaModelManager, createChildSubmenuActions, null);
		 * menuManager.appendToGroup(ICommonMenuConstants.GROUP_NEW, addChildMetaModelManager);
		 */

		// Add New Sibling sub menu
		MenuManager createSiblingMenuManager = new MenuManager("New Sibling"); //$NON-NLS-1$
		populateManager(createSiblingMenuManager, createSiblingSubmenuActions, null);
		populateManager(createSiblingMenuManager, createSiblingActions, null);
		menuManager.appendToGroup(ICommonMenuConstants.GROUP_NEW, createSiblingMenuManager);

		// Add the edit menu actions
		menuManager.appendToGroup(ICommonMenuConstants.GROUP_EDIT, new ActionContributionItem(cutAction));
		menuManager.appendToGroup(ICommonMenuConstants.GROUP_EDIT, new ActionContributionItem(copyAction));
		menuManager.appendToGroup(ICommonMenuConstants.GROUP_EDIT, new ActionContributionItem(pasteAction));
		menuManager.appendToGroup(ICommonMenuConstants.GROUP_EDIT, new ActionContributionItem(deleteAction));
	}

	@Override
	protected Map<String, Collection<IAction>> extractSubmenuActions(Collection<IAction> createActions, ISelection selection) {
		// Default Sphinx implementation uses a LinkedHashMap here, but we use a TreeMap to have sorted keys
		// That lets the many submenus appear in a nicely sorted order
		Map<String, Collection<IAction>> createSubmenuActions = new TreeMap<String, Collection<IAction>>();
		if (createActions != null) {
			for (Iterator<IAction> actions = createActions.iterator(); actions.hasNext();) {
				IAction action = actions.next();
				if (action.getText() != null) {
					String txt = action.getText();
					Command c = getCommandFromAction((StaticSelectionCommandAction) action);
					if (c != null) {
						CreateChildCommand childCommand = (CreateChildCommand) c;
						txt = prepareAddChildCommandText(childCommand, txt);
						txt = formatOriginalPackageNameText(childCommand, txt);
					}
					StringTokenizer st = new StringTokenizer(txt, "|"); //$NON-NLS-1$
					if (st.countTokens() >= 2) {
						String first = txt.substring(0, txt.lastIndexOf('|'));
						String packagePart = first.lastIndexOf('|') > -1 ? first.substring(0, first.lastIndexOf('|')).trim() : first.trim();
						first = ModelSearcher.getPrettyPackageName(packagePart);
						// The above removes one section in cases like usecase | Element | elementname
						// This gets rid of the extra submenu level for "Element"
						String tail = txt.substring(txt.lastIndexOf('|') + 1);
						IStructuredSelection structuredSelection = SelectionUtil.getStructuredSelection(selection);
						if (isTransient(structuredSelection.getFirstElement())) {
							// Suppress submenus if we are on an intermediate category node
							action.setText(first.trim());
						} else {
							// Decompose qualified action label in to submenu item and simple action text
							Collection<IAction> submenuActions = createSubmenuActions.get(first.trim());
							if (submenuActions == null) {
								createSubmenuActions.put(first.trim(), submenuActions = new ArrayList<IAction>());
							}
							action.setText(tail.trim());
							submenuActions.add(action);
							// Also add each action to a submenu called All
							submenuActions = createSubmenuActions.get("All"); //$NON-NLS-1$
							if (submenuActions == null) {
								createSubmenuActions.put("All", submenuActions = new ArrayList<IAction>()); //$NON-NLS-1$
							}
							submenuActions.add(action);
							actions.remove();
						}
					}
				}
			}
		}
		return createSubmenuActions;
	}

	private String prepareAddChildCommandText(CreateChildCommand childCommand, String oldText) {
		Object o = childCommand.getResult();
		String text = oldText;
		if (o instanceof Collection<?>) {
			o = ((Collection<?>) o).iterator().next();
		}
		if (o instanceof EObject) {
			EObject eo = (EObject) o;
			if (ModelSearcher.isInstanceRef(eo)) {
				text = text.replace("|", "(Instance Ref) |"); //$NON-NLS-1$ //$NON-NLS-2$
			}
			EStructuralFeature nameFeature = eo.eClass().eContainer().eClass().getEStructuralFeature("name"); //$NON-NLS-1$
			String packageName = eo.eClass().eContainer().eGet(nameFeature).toString();
			if (packageName != null) {
				text = packageName + " | " + text; //$NON-NLS-1$
			}
			return text;
		}
		return oldText;
	}

	private String formatOriginalPackageNameText(CreateChildCommand childCommand, String oldText) {
		Object o = childCommand.getResult();
		String text = oldText;
		if (o instanceof Collection<?>) {
			o = ((Collection<?>) o).iterator().next();
		}
		if (o instanceof EObject) {
			String annotation = EcoreUtil.getAnnotation(((EObject) o).eClass(), "http://www.eclipse.org/emf/2002/GenModel", "documentation"); //$NON-NLS-1$ //$NON-NLS-2$
			String qualifiedName = annotation.substring(annotation.indexOf("<em><b>") + "<em><b>".length(), annotation.lastIndexOf("</b></em>")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			String packageName = qualifiedName.substring(qualifiedName.indexOf("EAST-ADL.") + "EAST-ADL.".length()); //$NON-NLS-1$ //$NON-NLS-2$
			packageName = packageName.substring(0, packageName.indexOf(".")); //$NON-NLS-1$
			text = text.substring(text.lastIndexOf("|")); //$NON-NLS-1$
			text = packageName + " " + text; //$NON-NLS-1$
			return text;
		}
		return text;
	}

	private Command getCommandFromAction(StaticSelectionCommandAction action) {
		try {
			Field f = action.getClass().getSuperclass().getDeclaredField("command"); //$NON-NLS-1$
			f.setAccessible(true);
			Command c = (Command) f.get(action);
			return c;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void populateManager(IContributionManager manager, Map<String, Collection<IAction>> submenuActions, String contributionId) {
		if (submenuActions != null) {
			for (Map.Entry<String, Collection<IAction>> entry : submenuActions.entrySet()) {
				StringTokenizer st = new StringTokenizer(entry.getKey(), "|"); //$NON-NLS-1$
				int count = st.countTokens();
				IContributionManager lastManager = manager;
				for (int i = 0; i < count; i++) {
					MenuManager submenuManager = new MenuManager(st.nextToken());
					if (contributionId != null) {
						lastManager.insertBefore(contributionId, submenuManager);
					} else {
						lastManager.add(submenuManager);
					}
					lastManager = submenuManager;
					if (i == count - 1) {
						populateManager(submenuManager, entry.getValue(), null);
					}
				}
			}
		}
	}

}
