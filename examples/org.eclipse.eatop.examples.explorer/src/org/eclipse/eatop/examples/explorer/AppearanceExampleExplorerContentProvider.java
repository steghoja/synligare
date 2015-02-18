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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.examples.explorer.internal.Activator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.sphinx.emf.explorer.BasicExplorerContentProvider;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;

public class AppearanceExampleExplorerContentProvider extends BasicExplorerContentProvider {

	/* Keeps track of where categorization nodes are in the tree, for getParent() */
	private Map<EObject, CategorizationNode> categorizationNodeParents = new HashMap<EObject, CategorizationNode>();

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] children = null;

		try {
			// Is parent element a workspace resource?
			if (parentElement instanceof IResource) {
				// Get model root behind workspace resource (might not be loaded yet according to loading policy)
				@SuppressWarnings("deprecation")
				Object modelRoot = getModelRoot((IResource) parentElement);
				if (modelRoot != null) {
					// Get model content root for model root
					@SuppressWarnings("deprecation")
					Object modelContentRoot = getModelContentRoot(modelRoot);

					// Get model content provider of model content root
					AdapterFactoryContentProvider contentProvider = getModelContentProvider(modelContentRoot);
					if (contentProvider != null) {
						// Set model content root as model content provider input
						contentProvider.inputChanged(viewer, null, modelContentRoot);

						// Retrieve children of model content root
						children = contentProvider.getChildren(modelContentRoot);
					}
				}
			}

			// Assume that parent element is an EObject
			else if (parentElement instanceof EObject) {
				// Retrieve children of specified parent element
				AdapterFactoryContentProvider contentProvider = getModelContentProvider(parentElement);
				Object[] parentChildren = null;
				if (contentProvider != null) {
					parentChildren = contentProvider.getChildren(parentElement);
				}
				children = addTypeChildren((EObject) parentElement, parentChildren);
				if (isCategorizeElements()) {
					children = addCategorizationChildren((EObject) parentElement, children);
				}
			} else if (parentElement instanceof ChildWrapper) {
				children = getChildren(((ChildWrapper) parentElement).getObject());
			} else if (parentElement instanceof CategorizationNode) {
				children = ((CategorizationNode) parentElement).getChildren();
			}
		} catch (Exception ex) {
			PlatformLogUtil.logAsError(Activator.getPlugin(), ex);
		}
		return children != null ? children : new Object[0];
	}

	private Object[] addTypeChildren(EObject parentElement, Object[] children) {
		ArrayList<Object> childrenList = new ArrayList<Object>();
		if (children != null) {
			childrenList.addAll(Arrays.asList(children));
		}
		for (EStructuralFeature reference : getReferencesToShow(parentElement)) {
			EObject dereferenced = (EObject) parentElement.eGet(reference);
			AdapterFactoryContentProvider contentProvider = getModelContentProvider(dereferenced);
			if (contentProvider != null) {
				Object[] typeChildren = contentProvider.getChildren(dereferenced);
				if (typeChildren.length > 0) {
					for (Object o : typeChildren) {
						childrenList.add(new ChildWrapper((EObject) o));
					}
				}

			}
		}
		return childrenList.toArray();
	}

	private Object[] addCategorizationChildren(EObject parentElement, Object[] children) {
		ArrayList<Object> childrenList = new ArrayList<Object>();
		if (children != null) {
			// Add non-EObject children acquired from the model provider
			// This takes care of objects such as FeatureMap lists
			for (Object o : children) {
				if (!(o instanceof EObject)) {
					childrenList.add(o);
				}
			}
		}
		Map<EClass, List<EObject>> childrenMap = ModelSearcher.getChildrenMapByEClass(parentElement);
		for (EClass eclass : childrenMap.keySet()) {
			if (childrenMap.get(eclass).size() > 2 && !eclass.getName().equals("EAPackage")) {
				CategorizationNode catNode = CategorizationNode.create(parentElement, eclass);
				addCategorizationNode(catNode);
				childrenList.add(catNode);
			} else {
				for (EObject eo : childrenMap.get(eclass)) {
					childrenList.add(eo);
				}
			}
		}
		return childrenList.toArray();
	}

	@Override
	public Object getParent(Object object) {
		/*
		 * If an EObject should be under a categorization node, the node might not yet have been created, for instance,
		 * if the EObject is in a project or file that has not yet been expanded at all in the navigator. A call to
		 * getChildren() on the object's parent will ensure that any necessary categorization nodes are created in
		 * memory and added to the node parent map.
		 */
		if (object instanceof EObject) {
			getChildren(((EObject) object).eContainer());
		}
		if (categorizationNodeParents.get(object) != null) {
			return categorizationNodeParents.get(object);
		}
		if (object instanceof CategorizationNode) {
			return ((CategorizationNode) object).getParent();
		}
		return super.getParent(object);
	}

	private List<EStructuralFeature> getReferencesToShow(EObject parentElement) {
		List<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
		for (EStructuralFeature feature : parentElement.eClass().getEAllStructuralFeatures()) {
			if (feature instanceof EReference) {
				EStructuralFeature nameFeature = feature.eClass().getEStructuralFeature("name"); //$NON-NLS-1$
				if (nameFeature == null) {
					continue;
				}
				String name = (String) feature.eGet(nameFeature);
				if (name.equals("type")) { //$NON-NLS-1$
					result.add(feature);
				}
			}
		}
		return result;
	}

	private boolean isCategorizeElements() {
		return Activator.getDefault().getDialogSettings().getBoolean(AppearanceExampleActionProvider.CATEGORIZE_ELEMENTS);
	}

	public void addCategorizationNode(CategorizationNode categorizationNode) {
		for (Object o : categorizationNode.getChildren()) {
			if (o instanceof EObject) {
				categorizationNodeParents.put((EObject) o, categorizationNode);
			}
		}
	}

}
