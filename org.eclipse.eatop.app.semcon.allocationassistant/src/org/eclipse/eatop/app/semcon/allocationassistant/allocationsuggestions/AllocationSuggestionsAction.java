package org.eclipse.eatop.app.semcon.allocationassistant.allocationsuggestions;

import java.util.ArrayList;



import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.DeriveRequirement;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.Realization;
import org.eclipse.eatop.eastadl21.Relationship;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.TreeViewer;

public class AllocationSuggestionsAction {

	private final StructuredViewer requirementsViewer;
	private final StructuredViewer modelViewer;
	private final Requirement selectedRequirement;
	
	Object modelRoot;
	public AllocationSuggestionsAction(StructuredViewer requirementsViewer, StructuredViewer modelViewer) {
		this.requirementsViewer = requirementsViewer;
		this.modelViewer = modelViewer;
		IStructuredSelection requirementsSelection = (IStructuredSelection) ((TreeViewer)requirementsViewer).getSelection();
		IStructuredSelection modelSelection = (IStructuredSelection) ((TreeViewer)modelViewer).getSelection();
		if (requirementsSelection.isEmpty() || requirementsSelection.size()>1){
			//Requirements not selected
			selectedRequirement = null;
		}
		else{
			EObject selectedElement = (EObject) requirementsSelection.getFirstElement();
			if (selectedElement instanceof Requirement)
				selectedRequirement = (Requirement) selectedElement;
			else{
				selectedRequirement = null;
				//the selected element is not a requirement
				}
		}
	}
	
	public boolean selectSuggestions() {
		if (selectedRequirement != null){
			EObject requirementsRoot = ((Resource)((TreeViewer) requirementsViewer).getInput()).getAllContents().next();
			ArrayList<Relationship> requirementsRelationshipList = CrossReferencesFinder.getAllRelations(requirementsRoot);
			ArrayList<DeriveRequirement> deriveRelationships = CrossReferencesFinder.getDeriveRelations(requirementsRelationshipList);
			ArrayList<Requirement> requirementsList = CrossReferencesFinder.getDerivedRequirementsList((Requirement) selectedRequirement, deriveRelationships);
			ArrayList<Satisfy> SatisfyList = CrossReferencesFinder.getSatisfyRelations(requirementsRelationshipList);
			ArrayList<Identifiable> modelElements = CrossReferencesFinder.getModelElementsFromSatisfy(requirementsList, SatisfyList);
			
			EObject modelRoot = ((Resource) ((TreeViewer) modelViewer).getInput()).getAllContents().next();
			ArrayList<Relationship> modelRelationshipList = CrossReferencesFinder.getAllRelations(modelRoot);
			ArrayList<Realization> realizeList = CrossReferencesFinder.getRealizeRelations(modelRelationshipList);
			ArrayList<Identifiable> modelElementsHigher = CrossReferencesFinder.getModelElementsFromRealizationRelationships(modelElements, realizeList);
			if (!modelElementsHigher.isEmpty()){
				modelViewer.resetFilters();
				modelViewer.addFilter(new SuggestionsFilter(modelElementsHigher));
				for (EObject modelElem : modelElementsHigher)
					modelViewer.reveal(modelElem);
				return true;
			}
			return false;
		}
		return false;
	}
	

}
