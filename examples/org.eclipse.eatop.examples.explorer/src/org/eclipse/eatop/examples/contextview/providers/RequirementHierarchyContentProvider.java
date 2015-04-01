package org.eclipse.eatop.examples.contextview.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsHierarchy;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.examples.contextview.providers.TextOnlyNode.NodeType;

public class RequirementHierarchyContentProvider extends BasicContextContentProvider {

	private Map<Requirement, RequirementsHierarchy> parentChildMap = new HashMap<Requirement, RequirementsHierarchy>();

	@Override
	public String getNameForReferences() {
		return "Requirement Hierarchy";
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		List<Object> result = new ArrayList<Object>();
		if (parentElement instanceof TextOnlyNode && ((TextOnlyNode) parentElement).getType() == NodeType.REFERENCES) {
			if (input instanceof RequirementsModel) {
				RequirementsModel requirementsModel = (RequirementsModel) input;
				return requirementsModel.getRequirementsHierarchy().toArray();
			}
		} else if (parentElement instanceof RequirementsHierarchy) {
			for (RequirementsHierarchy reqsHi : ((RequirementsHierarchy) parentElement).getChildHierarchy()) {
				fetchRequirementChildren(reqsHi, result);
			}
		} else if (parentElement instanceof Requirement) {
			RequirementsHierarchy requirementsHierarchy = parentChildMap.get(parentElement);
			for (RequirementsHierarchy reqsHi : requirementsHierarchy.getChildHierarchy()) {
				fetchRequirementChildren(reqsHi, result);
			}
		}

		return result.toArray();
	}

	private void fetchRequirementChildren(final RequirementsHierarchy reqsHi, final List<Object> result) {
		Requirement containedRequirement = reqsHi.getContainedRequirement();
		if (containedRequirement != null) {
			result.add(containedRequirement);
			parentChildMap.put(containedRequirement, reqsHi);
		} else {
			result.add(reqsHi);
		}

	}

	@Override
	public Object getParent(Object element) {
		return null;
	}
}
