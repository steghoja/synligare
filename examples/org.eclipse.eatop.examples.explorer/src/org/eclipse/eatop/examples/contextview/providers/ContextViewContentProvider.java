package org.eclipse.eatop.examples.contextview.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;
import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.examples.contextview.providers.TextOnlyNode.NodeType;
import org.eclipse.eatop.examples.explorer.ChildWrapper;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.viewers.Viewer;

public class ContextViewContentProvider extends BasicContextContentProvider {

	private IContextProvider secondaryProvider;
	private TextOnlyNode refNode;
	private TextOnlyNode refByNode;
	private TextOnlyNode instRefByNode;

	@Override
	public Object[] getElements(Object inputElement) {
		List<Object> elements = new ArrayList<Object>();
		if (secondaryProvider == null || !secondaryProvider.disablesGenericReferences()) {
			refNode = new TextOnlyNode(getNameForReferences(), TextOnlyNode.NodeType.REFERENCES);
			if (secondaryProvider != null) {
				String secondaryName = secondaryProvider.getNameForReferences();
				if (secondaryName.length() > 0) {
					refNode.setText(secondaryName);
				}
			}
			if (hasChildren(refNode)) {
				elements.add(refNode);
			}
		}
		if (secondaryProvider == null || !secondaryProvider.disablesGenericReferencedBy()) {
			refByNode = new TextOnlyNode(getNameForReferencedBy(), TextOnlyNode.NodeType.REFERENCED_BY);
			if (secondaryProvider != null) {
				String secondaryName = secondaryProvider.getNameForReferencedBy();
				if (secondaryName.length() > 0) {
					refByNode.setText(secondaryName);
				}
			}
			if (hasChildren(refByNode)) {
				elements.add(refByNode);
			}
		}
		if (!disablesGenericInstanceReferencedBy()) {
			if (secondaryProvider == null || !secondaryProvider.disablesGenericInstanceReferencedBy()) {
				instRefByNode = new TextOnlyNode(getNameForInstanceReferencedBy(), TextOnlyNode.NodeType.INSTANCE_REFERENCED_BY);
				if (secondaryProvider != null) {
					String secondaryName = secondaryProvider.getNameForInstanceReferencedBy();
					if (secondaryName.length() > 0) {
						instRefByNode.setText(secondaryName);
					}
				}
				if (hasChildren(instRefByNode)) {
					elements.add(instRefByNode);
				}
			}
		}
		Object[] secondaryElements = secondaryProvider != null ? secondaryProvider.getElements(input) : null;
		if (mergeArrays(elements.toArray(), secondaryElements).length > 0) {
			return mergeArrays(elements.toArray(), secondaryElements);
		} else {
			TextOnlyNode txtNode = new TextOnlyNode("No context available for this object", NodeType.OTHER);
			return new Object[] { txtNode };
		}
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] children = new Object[] {};
		if (parentElement == refByNode && input instanceof EObject) {
			List<EObject> refs = ModelSearcher.findReferences((EObject) input);
			children = refs.toArray();
		} else if (parentElement == refNode && input instanceof EObject) {
			children = getReferredObjects((EObject) input).toArray();
		} else if (parentElement == instRefByNode && input instanceof EObject) {
			List<EObject> instRefs = ModelSearcher.findInstanceReferences((EObject) input);
			children = instRefs.toArray();
		}
		Object[] secondaryChildren = secondaryProvider != null ? secondaryProvider.getChildren(parentElement) : null;
		return mergeArrays(children, secondaryChildren);
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	private Object[] mergeArrays(Object[] first, Object[] second) {
		if (second == null) {
			return first;
		}
		List<Object> lst = new ArrayList<Object>(first.length + second.length);
		lst.addAll(Arrays.asList(first));
		lst.addAll(Arrays.asList(second));
		return lst.toArray();
	}

	private Set<EObject> getReferredObjects(EObject eo) {
		Set<EObject> targets = new HashSet<EObject>();
		for (EReference ref : eo.eClass().getEAllReferences()) {
			if (isValidReference(ref, eo)) {
				if (!ref.isMany()) {
					EObject target = (EObject) eo.eGet(ref);
					targets.add(target);
				} else {
					EList<? extends EObject> lst = (EList<? extends EObject>) eo.eGet(ref);
					targets.addAll(lst);
				}
			}
		}
		return targets;
	}

	private boolean isValidReference(EReference ref, EObject eo) {
		if (!eo.eIsSet(ref)) {
			return false;
		}
		Object refValue = eo.eGet(ref);
		boolean isParent = refValue == eo.eContainer();
		return !ref.isDerived() && !isParent && !ref.isContainment();
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		input = newInput;
		if (input == null) {
			return;
		}
		if (input instanceof ChildWrapper) {
			input = ((ChildWrapper) input).getObject();
		}
		secondaryProvider = (IContextProvider) Platform.getAdapterManager().getAdapter(input, IContextProvider.class);
		if (secondaryProvider != null) {
			secondaryProvider.inputChanged(viewer, oldInput, input);
		}
	}

	public void setSecondaryProvider(IContextProvider provider) {
		secondaryProvider = provider;
	}
}
