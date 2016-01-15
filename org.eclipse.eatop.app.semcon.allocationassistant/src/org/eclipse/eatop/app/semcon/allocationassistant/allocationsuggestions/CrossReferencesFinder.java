package org.eclipse.eatop.app.semcon.allocationassistant.allocationsuggestions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.eatop.eastadl21.DeriveRequirement;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.Realization;
import org.eclipse.eatop.eastadl21.Realization_realized;
import org.eclipse.eatop.eastadl21.Realization_realizedBy;
import org.eclipse.eatop.eastadl21.Relationship;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy_satisfiedBy;
import org.eclipse.eatop.geastadl.ginfrastructure.gelements.GReferrable;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.sphinx.emf.ecore.ECrossReferenceAdapterFactory;


/**
 * This class contains methods to find relationships and referenced elements in relationships.
 *  
 *  (To do for this class to work on any models: change the contains() function with some other comparison
 *  that disregards if the elements have different addressed in the memory)
 * @author Andreea Olaru
 *
 */
public class CrossReferencesFinder {

	//Doesn't quite work so it is not used
	private static ArrayList<EObject> getRelations(EObject rowObject) {
		
		Set<EObject> requirements = new LinkedHashSet<EObject>();
		
		ECrossReferenceAdapter referenceAdapter = ECrossReferenceAdapterFactory.INSTANCE.adapt(rowObject);
		Collection<Setting> inverseReferences = referenceAdapter.getInverseReferences(rowObject);
		
		for (Setting setting : inverseReferences) {
			EStructuralFeature feature = setting.getEStructuralFeature();
			if (feature instanceof EReference) {
				EReference reference = (EReference) feature;
				if (!reference.isContainer() && !reference.isContainment()) {
					EObject eObject = setting.getEObject();
					while (eObject != null && !(eObject instanceof GReferrable)) {
						eObject = eObject.eContainer();
					}
					if (eObject != null) {
						requirements.add(eObject);
					}
					
				}
			}
		}
		
		return new ArrayList<EObject>(requirements);
	}

	/**
	 * Gets the list of existent {@link org.eclipse.eatop.eastadl21.Relationship <em>relationships</em>} 
	 * in the model that has the given root.
	 * @param root -for the model to search relationships in
	 * @return the list of all existent relationships in the model
	 * 	or an empty list of there is no relationship found.
	 */
	public static ArrayList<Relationship> getAllRelations(EObject root){
		  final TreeIterator<EObject> contentIterator=root.eAllContents();
		  final ArrayList<Relationship> result=new ArrayList<Relationship>();
		  while (contentIterator.hasNext()) {
		    final EObject next=contentIterator.next();
		    if (next instanceof Relationship){
		    	result.add((Relationship) next);
		    }
		  }
		  return result;
	}
	
	/**
	 * Gets the relationships of type {@link org.eclipse.eatop.eastadl21.DeriveRequirement <em>DeriveRequirement</em>}
	 * in a given list of various relationships types.
	 * @param allRelations
	 * @return a list of {@link org.eclipse.eatop.eastadl21.DeriveRequirement <em>DeriveRequirement</em>} elements
	 */
	public static ArrayList<DeriveRequirement> getDeriveRelations(ArrayList<Relationship> allRelations){
		ArrayList<DeriveRequirement> result = new ArrayList<DeriveRequirement>();
		for (Relationship relation : allRelations){
			if (relation instanceof DeriveRequirement){
				result.add((DeriveRequirement) relation);
			}
		}
		return result;
	}
	
	/**
	 * Gets the relationships of type {@link org.eclipse.eatop.eastadl21.Satisfy <em>Satisfy</em>}
	 * in a given list of various relationships types.
	 * @param allRelations
	 * @return a list of {@link org.eclipse.eatop.eastadl21.Satisfy <em>Satisfy</em>} elements
	 */
	public static ArrayList<Satisfy> getSatisfyRelations(ArrayList<Relationship> allRelations){
		ArrayList<Satisfy> result = new ArrayList<Satisfy>();
		for (Relationship relation : allRelations){
			if (relation instanceof Satisfy){
				result.add((Satisfy) relation);
			}
		}
		return result;
	}
	
	/**
	 * Gets the relationships of type {@link org.eclipse.eatop.eastadl21.Realization <em>Realization</em>}
	 * in a given list of various relationships types.
	 * @param allRelations
	 * @return a list of {@link org.eclipse.eatop.eastadl21.Realization <em>Realization</em>} elements
	 */
	public static ArrayList<Realization> getRealizeRelations(ArrayList<Relationship> allRelations){
		ArrayList<Realization> result = new ArrayList<Realization>();
		for (Relationship relation : allRelations){
			if (relation instanceof Realization){
				result.add((Realization) relation);
			}
		}
		return result;
	}
	
	
	/**
	 * Gets the list of {@link org.eclipse.eatop.eastadl21.DeriveRequirement <em>DeriveRequirements</em>} that refer the given requirement
	 * in their derived attribute.
	 * @param requirement - the selected requirement
	 * @param derivedRelations - a list of all DeriveRequirement elements
	 * 
	 */
	public static ArrayList<Requirement> getDerivedRequirementsList(Requirement requirement, ArrayList<DeriveRequirement> derivedRelations){
		ArrayList<Requirement> result = new ArrayList<Requirement>();
		for (DeriveRequirement deriveRel : derivedRelations){
			List<Requirement> derivedFromReq = deriveRel.getDerivedFrom();

			//!!!!!!!!! ATTENTION to this! If in the workspace are loaded models that have the same elements
			// as the elements in the requirements model or system model (or if these two contain the same elements)
			// the function contains() used here might find the same element stored at different address and the function might
			// not work.
			// A solution would be to write own contains (or compare) function that disregards the memory-address of the elements
			// and just compares their attributes!
			List<Requirement> derivedRequitement = deriveRel.getDerived();
			Boolean contains1 = derivedFromReq.contains(requirement);
			Boolean contains2 = derivedRequitement.contains(requirement);
			if ((!derivedFromReq.contains(requirement)) && (deriveRel.getDerived().contains(requirement))){
				for (Requirement req : derivedFromReq)
					result.add(req);
			}
		
		}
		return result;
	}
	
	/**
	 * Gets the list of {@link org.eclipse.eatop.eastadl21.Satisfy_satisfiedBy <em>Satisfy_satisfiedBy</em>} are placed in
	 * a {@link org.eclipse.eatop.eastadl21.Satisfy <em>Satisfy</em>} relationship which references the given requirement.
	 * @param requirement - the selected requirement
	 * @param satisfyList - a list of all {@link org.eclipse.eatop.eastadl21.Satisfy <em>Satisfy</em>} elements
	 */
	public static ArrayList<Satisfy_satisfiedBy> getSatisfiedByList(Requirement requirement, ArrayList<Satisfy> satisfyList){
		ArrayList<Satisfy_satisfiedBy> result = new ArrayList<Satisfy_satisfiedBy>();
		for (Satisfy satisfy : satisfyList){
			if (satisfy.getSatisfiedRequirement().contains(requirement)){
					EList<Satisfy_satisfiedBy> satisfyByList = satisfy.getSatisfiedBy();
					for (Satisfy_satisfiedBy satisfyBy : satisfyByList)
						result.add(satisfyBy);
			}
		}
		return result;
	}
	
	/**
	 * Gets the model elements that satisfy at least one of the requirements in the given requirements list.
	 * @param requirementsList
	 * @param satisfyRelations - a list of Satisfy relations in the model
	 * @return
	 */
	public static ArrayList<Identifiable> getModelElementsFromSatisfy(ArrayList<Requirement> requirementsList, ArrayList<Satisfy> satisfyRelations){
		ArrayList<Identifiable> result = new ArrayList<Identifiable>();
		for (Requirement req: requirementsList)
			result.addAll(getModelElementsFromSatisfy(req, satisfyRelations));
		return result;
	}
	
	/**
	 * Gets the model elements that satisfy the given requirement.
	 * @param requirement
	 * @param satisfyRelations - a list of Satisfy relations in the model
	 * @return
	 */
	public static ArrayList<Identifiable> getModelElementsFromSatisfy(Requirement requirement, ArrayList<Satisfy> satisfyRelations){			
		ArrayList<Satisfy_satisfiedBy> listOfSatisfiedBy = getSatisfiedByList(requirement, satisfyRelations);
		
		ArrayList<Identifiable> result = new ArrayList<Identifiable>();
		for (Satisfy_satisfiedBy satisfyBy : listOfSatisfiedBy)
			if (satisfyBy.getIdentifiable_target() != null)
				result.add(satisfyBy.getIdentifiable_target());

		return result;
	}
	
	/**
	 * Gets the list of Realization_realizedBy elements that reference elements which realize the given element.
	 * @param element
	 * @param realizationList
	 */
	public static ArrayList<Realization_realizedBy> getRealizedByList(Identifiable element, ArrayList<Realization> realizationList){
		ArrayList<Realization_realizedBy> result = new ArrayList<Realization_realizedBy>();
		for (Realization realization : realizationList){
			
			// Get all Realization_realized elements from the current Realization element
			// and find out if any of them references the given model element 
			EList<Realization_realized> RealizationRealizedList = realization.getRealized();
			boolean elementFoundInRealization = false;
			for (Realization_realized realizationReal : RealizationRealizedList){
				if (realizationReal.getIdentifiable_target()!= null && realizationReal.getIdentifiable_target().equals(element)){
					elementFoundInRealization = true;
				}
			}
			
			// If the model element is referenced in any Realization_realized on the current Realization
			// get the list of Realization_realizedBy elements from this Realization.
			if (elementFoundInRealization){
				EList<Realization_realizedBy> realizeByList = realization.getRealizedBy();
				for (Realization_realizedBy realizeBy : realizeByList)
					result.add(realizeBy);
			}
		}
		return result;
	}
	
	/**
	 * Gets the model elements that realize at least one element in the given list.
	 * @param elementsList
	 * @param realizationList
	 */
	public static ArrayList<Identifiable> getModelElementsFromRealizationRelationships(ArrayList<Identifiable> elementsList, ArrayList<Realization> realizationList){
		ArrayList<Identifiable> result = new ArrayList<Identifiable>();
		for (Identifiable element: elementsList)
			result.addAll(getModelElementsFromRealizationRelationships(element, realizationList));
		return result;
	}
	
	/**
	 * Gets the model elements that realize a given element.
	 * @param element
	 * @param realizationList
	 */
	public static ArrayList<Identifiable> getModelElementsFromRealizationRelationships(Identifiable element, ArrayList<Realization> realizationList){			
		ArrayList<Realization_realizedBy> listOfRealizedBy = getRealizedByList(element, realizationList);
		ArrayList<Identifiable> result = new ArrayList<Identifiable>();
		for (Realization_realizedBy realizedBy : listOfRealizedBy)
			if (realizedBy.getIdentifiable_target() != null)
				result.add(realizedBy.getIdentifiable_target());

		return result;
	}
}
