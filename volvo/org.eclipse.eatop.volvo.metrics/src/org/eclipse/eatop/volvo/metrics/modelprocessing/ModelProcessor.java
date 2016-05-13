package org.eclipse.eatop.volvo.metrics.modelprocessing;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.eatop.eastadl21.DeriveRequirement;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.FunctionalSafetyConcept;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.Relationship;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.SafetyGoal;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.TechnicalSafetyConcept;
import org.eclipse.eatop.eastadl21.TraceableSpecification;
import org.eclipse.eatop.eastadl21.Verify;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * ModelProcessor performs searching tasks in the model.
 * 
 *
 */
public class ModelProcessor {


	@SuppressWarnings("unchecked")
	public static <T extends EAElement> Set<T> findAllEAElementsOfTypeInTree(Class<T> type, EObject eRoot)
	{
		if(eRoot==null)	{
			return null;
		}
		HashSet<T> eObjectsSet = new HashSet<T>();

		for (TreeIterator<EObject> iter = EcoreUtil.getAllContents(Collections.singleton(eRoot));
			     iter.hasNext(); ) {
			  EObject eObject = (EObject)iter.next();
			  
			  if (type.isInstance(eObject)){
				  eObjectsSet.add((T)eObject);
			  }
		}
		
		return eObjectsSet;
	}

	public static <T extends EAElement> Set<T> findAllEAElementsOfTypeInTrees(Class<T> type, Set <EObject> eRoots) {
		if(eRoots==null)	{
			return null;
		}
		HashSet<T> eObjectSet = new HashSet<T>();

		for (EObject root : eRoots){
			eObjectSet.addAll(findAllEAElementsOfTypeInTree(type, root));
		}
		
		return eObjectSet;
	}
	
/*
	
	public static Set<Requirement> findAllRequirements(EObject eRoot) {
		if(eRoot==null)	{
			return null;
		}
		HashSet<Requirement> requirementsSet = new HashSet<Requirement>();

		for (TreeIterator<EObject> iter = EcoreUtil.getAllContents(Collections.singleton(eRoot));
			     iter.hasNext(); ) {
			  EObject eObject = (EObject)iter.next();
			  
			  if (eObject instanceof Requirement){
				  requirementsSet.add((Requirement)eObject);
			  }
		}
		
		return requirementsSet;
	}
	
	public static Set<Requirement> findAllRequirements(Set <EObject> eRoots) {
		if(eRoots==null)	{
			return null;
		}
		HashSet<Requirement> requirementsSet = new HashSet<Requirement>();

		for (EObject root : eRoots){
			requirementsSet.addAll(findAllRequirements(root));
		}
		
		return requirementsSet;
	}
	*/

	
	/**
	 * Find the subset of elements in the provided set that has an incoming reference of type R
	 * 
	 * @param eaElements A set of elements to search
	 * @r a type to look for
	 * @return The subset of eaElements that has an incoming reference of type R
	 */
	public static <T extends EAElement, R> Set<T> findEAElementsOfTypeWithRelationship(Set<T> eaElements, Class<R> r) {
		
		HashSet<T> eaElementsWithR = new HashSet<T>();
		for (T eaElement : eaElements) {
			Set<EObject> incoming = new HashSet<EObject>();
			incoming.addAll(findIncomingReferences((EObject)eaElement));
		
			for (EObject eobj : incoming) {
				if (r.isInstance(eobj)) {
					eaElementsWithR.add(eaElement);
				}
			}
		}
		
		return eaElementsWithR;
	}

	
	/**
	 * Find the satisfied requirements in the provided set of requirements
	 * 
	 * @param reqs A set of requirements to search
	 * @return A set of the satisfied requirements from <code>reqs</code>
	 */
	public static Set<Requirement> findSatisfiedRequirements(Set<Requirement> reqs) {
		
		HashSet<Requirement> satisfiedReqs = new HashSet<Requirement>();
		for (Requirement r : reqs) {
			Set<EObject> incoming = new HashSet<EObject>();
			incoming.addAll(findIncomingReferences((EObject)r));
		
			// Find the Satisfy-references and then add the satisfied requirement to the set
			for (EObject eObject : incoming) {
				if (eObject instanceof Satisfy) {
					satisfiedReqs.add(r);
				}
			}
		}
		
		return satisfiedReqs;
	}
	
	/**
	 * Find the verified requirements in the provided set of requirements
	 * 
	 * @param reqs A set of requirements to search
	 * @return A set of the satisfied requirements from <code>reqs</code>
	 */
	public static Set<Requirement> findVerifiedRequirements(Set<Requirement> reqs) {
		
		HashSet<Requirement> verifiedReqs = new HashSet<Requirement>();
		for (Requirement r : reqs) {
			Set<EObject> incoming = new HashSet<EObject>();
			incoming.addAll(findIncomingReferences((EObject)r));
		
			// Find the Satisfy-references and then add the satisfied requirement to the set
			for (EObject eObject : incoming) {
				if (eObject instanceof Verify) {
					verifiedReqs.add(r);
				}
			}
		}
		
		return verifiedReqs;
	}
	
	/**
	 * Finds the incoming references to the given EObject
	 * 
	 * @param eObject 
	 * @return A set with the incoming references
	 */
	public static Set<EObject> findIncomingReferences(EObject eObject) {

		Collection<Setting> references = EcoreUtil.UsageCrossReferencer.find(eObject,
				eObject.eResource());

		Set<EObject> incoming = new LinkedHashSet<EObject>();
		for (Setting ref : references) {
			incoming.add(ref.getEObject());
		}

		return incoming;
	}



	/**
	 * Finds all derive relationships that is derived from the given requirement, i.e. 
	 * DeriveRequirement objects whose deriveFrom property contains R. 
	 * 
	 * @param req Requirement to find relationships for
	 * @return A list of all the derive relationships (of type <code>DeriveRequirement</code>)
	 */
	public static Set<DeriveRequirement> findDeriveFromRelationships(Requirement req) {
		Set<EObject> incoming = findIncomingReferences((EObject)req);

		Set<DeriveRequirement> deriveRelationships = new HashSet<DeriveRequirement>();
		for (EObject eObject : incoming) {
			if (eObject instanceof DeriveRequirement) {
				
				DeriveRequirement deriveRelationShip = (DeriveRequirement)eObject;
				if (deriveRelationShip.getDerivedFrom().contains(req))
					deriveRelationships.add((DeriveRequirement)eObject);
			}
		}
		return deriveRelationships;
	}
	
	public static boolean isFunctionalSafetyRequirement(Requirement requirement) {

		Set<EObject> incomingEObjects = findIncomingReferences((EObject)requirement);
		
		for (EObject eObject : incomingEObjects) {
			if (eObject instanceof FunctionalSafetyConcept) 
				return true;
		}
		
		return false;
	}

	public static boolean isTechnicalSafetyRequirement(Requirement requirement) {

		Set<EObject> incomingEObjects = findIncomingReferences((EObject)requirement);
		
		for (EObject eObject : incomingEObjects) {
			if (eObject instanceof TechnicalSafetyConcept) 
				return true;
		}
		
		return false;
	}

	
	public static Set<Requirement> findDerivedRequirements(Requirement req) {

		Set<Requirement> derivedReqs = new HashSet<Requirement>();
		Set<DeriveRequirement> derivedRelationships = findDeriveFromRelationships(req);

		for (DeriveRequirement deriveRelationship : derivedRelationships) {
			derivedReqs.addAll(deriveRelationship.getDerived());
		}
		
		return derivedReqs;
	}


}
