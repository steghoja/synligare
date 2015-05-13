package org.eclipse.eatop.volvo.modelprocessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisLevel;
import org.eclipse.eatop.eastadl21.DeriveRequirement;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAPackageableElement;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.FeatureModel;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.Referrable;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * ModelProcessor performs searching tasks in the model.
 * 
 * @author Joanna Svantesson
 *
 */
public class ModelProcessor {

	/** This method will search the provided EAElement and all of its children after requirements.
	 * 
	 * @param eaElement The root element of the search
	 * @return A list with all the requirements found in the provided element
	 */
	public static List<Requirement> findAllRequirements(EAElement eaElement) {
		if(eaElement==null)	{
			return null;
		}
		List<Requirement> requirementsList = new ArrayList<Requirement>();
		recursiveFindRequirements(eaElement,requirementsList);
		return requirementsList;
	}

	// Private helper method for findRequirementsInModel
	private static void recursiveFindRequirements(EAElement eaElement,List<Requirement> requirementsList) {
		if(eaElement instanceof EAPackage) {
			EAPackage eap = (EAPackage) eaElement;

			if(eap.getSubPackage() != null && eap.getSubPackage().size() > 0) { // if subpackage exists
				for(EAPackage p: eap.getSubPackage()) {
					recursiveFindRequirements(p,requirementsList);
				}
			}

			if(eap.getElement() !=null && eap.getElement().size() > 0) { // if there exist elements
				for(EAPackageableElement pE : eap.getElement())	{
					if(pE instanceof RequirementsModel) {
						RequirementsModel set = (RequirementsModel) pE;
						requirementsList.addAll(set.getRequirement());
					} else if (pE instanceof Requirement) {
						Requirement req = (Requirement) pE;
						requirementsList.add(req);
					}
				}
			}
		}
		else if (eaElement instanceof RequirementsModel) {
			RequirementsModel reqModel = (RequirementsModel) eaElement;
			requirementsList.addAll(reqModel.getRequirement());
		}
	}

	/**
	 * Search the provided EAElement and all of its children for requirements that are satisfied 
	 * (requirements with a satisfy relation to it)
	 * 
	 * @param selectedElement The root element of the search
	 * @return A list with all the satisfied requirements found in the provided element
	 */
	public static List<Requirement> findSatisfiedRequirements(EAElement selectedElement) {

		List<Requirement> reqList;

		reqList = findAllRequirements(selectedElement);

		Set<EObject> incoming = new HashSet<EObject>();
		for (Requirement r : reqList) {
			incoming.addAll(findIncomingReferences(r));
		}

		// Find the Satisfy-references and then add the satisfied requirement to list
		ArrayList<Requirement> satisfiedReqs = new ArrayList<Requirement>();

		for (EObject eObject : incoming) {
			if (eObject instanceof Satisfy) {
				Satisfy sat = (Satisfy) eObject;
				satisfiedReqs.addAll(sat.getSatisfiedRequirement());
			}

		}
		return satisfiedReqs;

	}

	/**
	 * Find the satisfied requirements in the provided list of requirements
	 * 
	 * @param reqs A list of requirements to search
	 * @return A list of the satisfied requirements from <code>reqs</code>
	 */
	public static List<Requirement> findSatisfiedRequirements(List<Requirement> reqs) {
		Set<EObject> incoming = new HashSet<EObject>();
		for (Requirement r : reqs) {
			incoming.addAll(findIncomingReferences(r));
		}
		// Find the Satisfy-references and then add the satisfied requirement to list
		ArrayList<Requirement> satisfiedReqs = new ArrayList<Requirement>();

		for (EObject eObject : incoming) {
			if (eObject instanceof Satisfy) {
				Satisfy sat = (Satisfy) eObject;
				satisfiedReqs.addAll(sat.getSatisfiedRequirement());
			}

		}
		return satisfiedReqs;
	}

	/**
	 * Find the requirements derived by the provided Requirement <code>r</code>
	 * 
	 * @param r
	 * @return
	 */
/*	public static List<Requirement> findDerivedRequirements(Requirement r) {
		// TODO
		return null;
	}*/

	/**
	 * Returns the requirements with a derived relation in the given list
	 * 
	 * @param reqs
	 * @return
	 */
	/*public static List<Requirement> findDerivedRequirements(List<Requirement> reqs) {
		// TODO
		return null;
	}*/

	/**
	 * Returns the requirements found under <code>selectedElement</code> that has a derived relation
	 * 
	 * @param selectedElement
	 * @return The requirements found under <code>selectedElement</code> that has a derived relation
	 */
	public static List<Requirement> findDerivedRequirements(EAElement selectedElement) {
		
		List<Requirement> reqList;

		reqList = findAllRequirements(selectedElement);

		Set<EObject> incoming = new HashSet<EObject>();
		for (Requirement r : reqList) {
			incoming.addAll(findIncomingReferences(r));
		}

		// Find the Derive-references and then add the derived requirement to list
		ArrayList<Requirement> derivedReqs = new ArrayList<Requirement>();

		for (EObject eObject : incoming) {
			if (eObject instanceof DeriveRequirement) {
				DeriveRequirement der = (DeriveRequirement) eObject;
				derivedReqs.addAll(der.getDerived());
			}

		}
		return derivedReqs;
	}

	/**
	 * Finds all derive relationships for the given requirement
	 * 
	 * @param req Requirement to find relationships for
	 * @return A list of all the derive relationships (of type <code>DeriveRequirement</code>)
	 */
	public static List<DeriveRequirement> findDeriveRelationships(Requirement req) {
		Set<EObject> incoming = new HashSet<EObject>();
		incoming.addAll(findIncomingReferences(req));

		ArrayList<DeriveRequirement> deriveRelationships = new ArrayList<DeriveRequirement>();
		for (EObject eObject : incoming) {
			if (eObject instanceof DeriveRequirement) {
				deriveRelationships.add((DeriveRequirement)eObject);
			}
		}

		return deriveRelationships;
	}

	/**
	 * Find the requirements from the given list that may be allocated. 
	 * A Requirement is not "allocatable" if it has other requirements derived
	 * from it in <I>the same package</I>. Otherwise, if it has derived requirements
	 * in other packages or no derived requirements it may be allocated.
	 * 
	 * @param reqs List of requirements
	 * @return A list of the allocatable requirements from <code>reqs</code>
	 */
	public static List<Requirement> findAllocatableReqs(List<Requirement> reqs) {
		boolean isAllocatable;
		ArrayList<Requirement> allocatable = new ArrayList<Requirement>();

		for (Requirement r : reqs) {
			isAllocatable = true;
			ArrayList<DeriveRequirement> deriveRelations = new ArrayList<DeriveRequirement>();
			deriveRelations.addAll(findDeriveRelationships(r));

			for (DeriveRequirement dr : deriveRelations) {

				if (dr.getDerivedFrom().contains(r)) { // If this requirement has other requirements derived from it
					// Check if any of these requirements occur in the 
					// same package as this

					// Find the EAPackage for r
					EObject eObject = r.eContainer();
					while (!(eObject instanceof EAPackage)) {
						if (eObject == null) {
							System.out.println("Package for requirement could not be found.");
							break;
						}
						eObject = eObject.eContainer();
					}

					// If package was found
					// Check if any of the derived requirements are in this package
					// In that case r will not be allocatable
					if (eObject != null) {
						EAPackage eaPackage = (EAPackage)eObject;
						List<Requirement> reqsInPackage = findAllRequirements(eaPackage);

						for (Requirement derivedReq : dr.getDerived()) {
							if (reqsInPackage.contains(derivedReq)) {
								isAllocatable = false;
								break;
							}
						}

					}

				} 
			}

			if (isAllocatable) {
				allocatable.add(r);
			}
		}

		return allocatable;
	}

	/**
	 * Finds the incoming references to the given EObject
	 * 
	 * @param eObject 
	 * @return A set with the incoming references
	 */
	public static Set<EObject> findIncomingReferences(EObject eObject) {

		Collection<Setting> references = EcoreUtil.UsageCrossReferencer.find(eObject,
				eObject.eResource().getResourceSet());

		Set<EObject> incoming = new LinkedHashSet<EObject>();
		for (Setting ref : references) {
			incoming.add(ref.getEObject());
		}

		return incoming;
	}

	/**
	 * Search the given package and all its children for a SystemModel
	 * 
	 * @param eaPackage The root of the search
	 * @return The first SystemModel found or null if no SystemModel was found
	 */
	public static SystemModel findSystemModel(EAPackage eaPackage) {
		if(eaPackage == null)
			return null;

		if(eaPackage.getElement() != null && eaPackage.getElement().size() > 0) { // if there exist elements
			for(EAPackageableElement pE : eaPackage.getElement())	{
				if(pE instanceof SystemModel) 
					return (SystemModel) pE;
			}
		}
		if(eaPackage.getSubPackage() != null && eaPackage.getSubPackage().size() > 0) { // if subpackage exists
			for(EAPackage p: eaPackage.getSubPackage()) {
				findSystemModel(p);
			}
		}

		return null;
	}

	public static ArrayList<EAElement> getVehicleLevelElements(SystemModel systemModel) {
		ArrayList<EAElement> elements = new ArrayList<EAElement>();
		VehicleLevel vl = systemModel.getVehicleLevel();
		List<FeatureModel> TFM = vl.getTechnicalFeatureModel();
		for (FeatureModel fm : TFM) 
			elements.add(fm);

		return elements;
	}

	public static ArrayList<EAElement> getAnalysisLevelElements(SystemModel systemModel) {
		ArrayList<EAElement> elements = new ArrayList<EAElement>();
		AnalysisLevel al = systemModel.getAnalysisLevel();
		AnalysisFunctionPrototype FAA = al.getFunctionalAnalysisArchitecture();

		elements.add(FAA);

		return elements;
	}

	public static ArrayList<EAElement> getDesignLevelElements(SystemModel systemModel) {
		ArrayList<EAElement> elements = new ArrayList<EAElement>();
		DesignLevel dl = systemModel.getDesignLevel();
		DesignFunctionPrototype FDA = dl.getFunctionalDesignArchitecture();
		elements.add(FDA);
		HardwareComponentPrototype HDA = dl.getHardwareDesignArchitecture();
		elements.add(HDA);

		return elements;
	}

	public static EAPackage findPackageWithName(String name, EAXML root) {
		for (EAPackage p : root.getTopLevelPackage()) {
			EAPackage res = recursiveFindPackageWithName(name, p);
			if (res != null) return res;
		}
		return null;
	}

	private static EAPackage recursiveFindPackageWithName(String name, EAPackage p) {
		if (p.getShortName().equals(name)) {
			return p;
		} 

		for (EAPackage sp : p.getSubPackage()) {
			EAPackage res = recursiveFindPackageWithName(name, sp);
			if (res != null) return res;
		}

		return null;
	}

	public static EAPackage findPackageBeginningWithName(String name, EAXML root) {
		for (EAPackage p : root.getTopLevelPackage()) {
			EAPackage res = recursiveFindPackageBeginningWithName(name, p);
			if (res != null) return res;
		}
		return null;
	}

	private static EAPackage recursiveFindPackageBeginningWithName(String name, EAPackage p) {
		if (p.getShortName().startsWith(name)) {
			return p;
		} 

		for (EAPackage sp : p.getSubPackage()) {
			EAPackage res = recursiveFindPackageWithName(name, sp);
			if (res != null) return res;
		}

		return null;
	}

	/** 
	 * Returns all the elements in the given package and all of its subpackages 
	 * @param eaPackage
	 * @return A list with all elements in the given package and its subpackages
	 */
	public static ArrayList<EAElement> getElements(EAPackage eaPackage) {
		ArrayList<EAElement> elements = new ArrayList<EAElement>();
		elements.addAll(eaPackage.getElement());
		for (EAPackage sp : eaPackage.getSubPackage()) {
			elements.addAll(getElements(sp));
		}
		return elements;
	}

	/**
	 * Finds the path in the model to the given element and returns it as a string
	 * 
	 * @param element 
	 * @return the path to this element as a string
	 */
	public static String findPath(EAElement element) {
		StringBuilder path = new StringBuilder(element.getShortName());
		EObject parent = (EObject)element.eContainer();

		while (parent != null) {
			if (parent instanceof Referrable) {
				path.insert(0, ((Referrable)parent).getShortName() + "/");
			}
			parent = parent.eContainer();
		}
		return path.toString();
	}

	/**
	 * Returns the EObject with the model root <code>root</code> and the path in the
	 * model <code>path</code> or <code>null</code> if the element wasn't found.
	 * Note that the path must be well formed! For example "name1/name2/objectShortName". 
	 * 
	 * @param root The root for the model
	 * @param path The path in the model where we expect to find the element
	 * @return The EAElement in the model with root <code>root</code> and path <code>path</code>
	 * or null if the element wasn't found
	 */
	public static EObject getEObject(EAXML root, String path) {
		String[] parts = path.split("/");

		for (EAPackage p : root.getTopLevelPackage()) {
			if (p.getShortName().equals(parts[0])) {
				if (parts.length == 1) { // It can only be a package that we want to return
					return p;
				}
				List<String> partsList = new ArrayList<String>();
				for (int i = 1; i < parts.length; i++) {
					partsList.add(parts[i]);
				}
				return recursiveGetEObject(p, partsList);
			}
		}

		return null;
	}

	private static EObject recursiveGetEObject(EAPackage rootPackage, List<String> path) {

		for (EAPackage sp : rootPackage.getSubPackage()) {
			if (sp.getShortName().equals(path.get(0))) {
				if (path.size() > 1) {
					// If the path list are bigger than 1 we continue searching
					// in the found subpackage
					path.remove(0);
					return recursiveGetEObject(sp, path);
				}
				// If there is only one name in the path this found package is
				// what we want to return
				return sp;
			}
		}
		// If no subpackage were found with the given name 
		// we search the elements for any with the name in the path
		for (EAElement e : rootPackage.getElement()) {
			if (e.getShortName().equals(path.get(0))) {
				// If we are not at the end of the path we look at this element's content
				if (path.size() > 1) {
					EAElement last = e;
					for (int i = 1; i < path.size(); i++) {
						EAElement res = findElementWithShortName(last, path.get(i));
						if (res == null) {
							return last;
						} else if (i == path.size()-1) { 
							// It's the last element in the path so we do not want to 
							// continue the search
							return res;
						} else {
							last = res;
						}
					}
				}
				return e;
			}
		}
		// If no subpackage or element with the same name as the 
		// first name in the given path was found we return null
		return null;

	}

	/**
	 * Look at the content of the given object and return the element with the
	 * given shortname or null if none was found
	 * 
	 * @param object
	 * @param shortName
	 * @return The element with the given shortname or null if no such element exist
	 */
	private static EAElement findElementWithShortName(EObject object, String shortName) {
		for (EObject o : object.eContents()) {
			if (o instanceof EAElement && ((EAElement)o).getShortName().equals(shortName)) {
				return (EAElement)o;
			}
		}

		return null;
	}
}
