package org.eclipse.eatop.examples.contextview.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.eastadl21.Relationship;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

public class RelationshipContentProvider extends BasicContextContentProvider {

	private TextOnlyNode satisfiedByNode;
	private TextOnlyNode satifiesNode;

	private Map<String, TextOnlyNode> elementNodes;

	@Override
	public Object[] getElements(Object inputElement) {
		List<Object> elements = new ArrayList<Object>();

		satisfiedByNode = new TextOnlyNode("Satified by", TextOnlyNode.NodeType.REFERENCED_BY);
		if (hasChildren(satisfiedByNode)) {
			elements.add(satisfiedByNode);
		}

		satifiesNode = new TextOnlyNode("Satisfies", TextOnlyNode.NodeType.REFERENCES);
		if (hasChildren(satifiesNode)) {
			elements.add(satifiesNode);
		}

		if (elementNodes == null) {
			elementNodes = new HashMap<String, TextOnlyNode>();
			Map<String, List<Object>> elementNameToChildren = getChildMap();
			for (String elementName : elementNameToChildren.keySet()) {
				elementNodes.put(elementName, new TextOnlyNode(elementName, TextOnlyNode.NodeType.REFERENCED_BY));
			}
		}

		elements.addAll(elementNodes.values());

		return elements.toArray();
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Object> children = new ArrayList<Object>();
		// if (parentElement == satisfiedByNode && input instanceof Requirement) {
		// children = getSatisfiedBy();
		// }
		// if (parentElement == satifiesNode && input instanceof EObject) {
		// children = getSatisfies();
		// }

		Map<String, List<Object>> elementNameToChildren = getChildMap();

		if (parentElement instanceof TextOnlyNode) {
			if (elementNameToChildren.get(((TextOnlyNode) parentElement).toString()) != null) {
				children.addAll(elementNameToChildren.get(((TextOnlyNode) parentElement).toString()));
			}
		}

		return children.toArray();
	}

	private Map<String, List<Object>> getChildMap() {
		Map<String, List<Object>> elementNameToChildren = new HashMap<String, List<Object>>();

		for (EObject eoWithRefToSelected : ModelSearcher.findReferences((EObject) input)) {
			if (eoWithRefToSelected instanceof Relationship) {
				if (eoWithRefToSelected.eContents().size() > 0) {
					for (EObject child : eoWithRefToSelected.eContents()) {
						for (EReference identifiableTargetRef : getReferencesByName(child, "identifiable_target")) {
							addChildOrChildren(child.eClass().getName(), child.eGet(identifiableTargetRef), elementNameToChildren);
						}
					}
				} else {
					for (EReference ref : eoWithRefToSelected.eClass().getEReferences()) {
						Object deRef = eoWithRefToSelected.eGet(ref);
						addChildOrChildren(ref.getName(), deRef, elementNameToChildren);
					}
				}
			} else if (eoWithRefToSelected.eContainer() instanceof Relationship) {
				if (eoWithRefToSelected.eContainer().eContents().size() >= 2) {
					for (EObject child : eoWithRefToSelected.eContainer().eContents()) {
						for (EReference ref : getReferencesByName(child, "identifiable_target")) {
							addChildOrChildren(child.eClass().getName(), child.eGet(ref), elementNameToChildren);
						}
					}
				} else {
					for (EReference ref : eoWithRefToSelected.eContainer().eClass().getEReferences()) {
						Object deRef = eoWithRefToSelected.eContainer().eGet(ref);
						addChildOrChildren(ref.getName(), deRef, elementNameToChildren);
					}
				}
			}
		}
		return elementNameToChildren;
	}

	private void addChildOrChildren(String elementName, Object targetChildOrChildren, Map<String, List<Object>> elementNameToChildren) {
		if (targetChildOrChildren instanceof EObject) {
			if (targetChildOrChildren != input) {
				addChildToElement(elementName, targetChildOrChildren, elementNameToChildren);
			}
		} else if (targetChildOrChildren instanceof EObjectResolvingEList<?>) {
			EObjectResolvingEList<?> deRefList = (EObjectResolvingEList<?>) targetChildOrChildren;
			if (!deRefList.contains(input)) {
				for (Object object : deRefList) {
					addChildToElement(elementName, object, elementNameToChildren);
				}
			}
		}
	}

	private void addChildToElement(String elementName, Object target, Map<String, List<Object>> elementNameToChildren) {
		if (elementNameToChildren.get(elementName) == null) {
			elementNameToChildren.put(elementName, new ArrayList<Object>());
		}
		List<Object> targets = elementNameToChildren.get(elementName);
		targets.add(target);
	}

	@Override
	public boolean hasChildren(Object element) {
		return getChildren(element).length > 0;
	}

	// private List<Object> getSatisfiedBy() {
	// List<Object> satisfiedBy = new ArrayList<Object>();
	// for (EObject eoWithRefToSelected : ModelSearcher.findReferences((EObject) input)) {
	// if (eoWithRefToSelected instanceof Satisfy) {
	// for (EObject satisfyBy : eoWithRefToSelected.eContents()) {
	// if (satisfyBy instanceof Satisfy_satisfiedBy) {
	// for (EReference identifiableTargetRef : getReferencesByName(satisfyBy, "identifiable_target")) {
	// satisfiedBy.add(satisfyBy.eGet(identifiableTargetRef));
	// }
	// }
	// }
	// }
	// }
	// return satisfiedBy;
	// }
	//
	// private List<Object> getSatisfies() {
	// Relationship r;
	// List<Object> satifies = new ArrayList<Object>();
	// for (EObject eoWithRefToSelected : ModelSearcher.findReferences((EObject) input)) {
	// if (eoWithRefToSelected instanceof Satisfy_satisfiedBy) {
	// EObject satisfy = eoWithRefToSelected.eContainer();
	// if (satisfy instanceof Satisfy) {
	// for (EReference satisfiesRequirementRef : getReferencesByName(satisfy, "satisfiedRequirement")) {
	// Object requirementList = satisfy.eGet(satisfiesRequirementRef);
	// if (requirementList instanceof EObjectResolvingEList<?>) {
	// satifies.addAll((EObjectResolvingEList<?>) requirementList);
	// }
	// }
	// }
	// }
	// }
	// return satifies;
	// }

	private List<EReference> getReferencesByName(EObject referringObject, String refName) {
		List<EReference> references = new ArrayList<EReference>();
		for (EReference ref : referringObject.eClass().getEAllReferences()) {
			if (ref.getName().equals(refName)) {
				references.add(ref);
			}
		}
		return references;
	}
}
