package org.eclipse.eatop.volvo.metrics.modelprocessing;

import java.util.Set;

import org.eclipse.eatop.eastadl21.Feature;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.SafetyGoal;
import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class SGwithFCSsMetric implements IMetric {

/***
 * 		 									  #Covered Safety Goals
 *      Calculates Safety Goals covered by FCS = --------------------------
 *                                                #Safety Goals 
 *
 *      A Safety Goal is covered if all requirements has at least one derived Safety Requirement.
 *      A Requirement is a Safety Requirement if there is a FCS.functionalSafetyRequirement pointing at it
 *
 */
	@Override
	public Fraction calculateMetric(Set<EObject> modelSubtrees) {

		int nTotalNumberSG = 0;
		
		Set<SafetyGoal> safetyGoals = ModelProcessor.findAllEAElementsOfTypeInTrees(SafetyGoal.class, modelSubtrees);
		nTotalNumberSG = safetyGoals.size();
		
		int nCoveredSG = 0;
		for (SafetyGoal sg : safetyGoals){

			if (isSafetyGoalCoveredByFSC(sg))
				nCoveredSG++;
		}
		
		if (nTotalNumberSG == 0) return null;
		
		return new Fraction(nCoveredSG, nTotalNumberSG);
		
	}
	
	public boolean isSafetyGoalCoveredByFSC(SafetyGoal sg) {

		boolean bFoundNonCovered = false;
		
		EList<Requirement> reqs = sg.getRequirement();
		if (reqs.size() == 0) return false;
		
		for (Requirement req : reqs){
			
			boolean bFoundSafetyRequirement = false;
			Set<Requirement> derivedRequirements = ModelProcessor.findDerivedRequirements(req);
			for (Requirement derivedRequirement : derivedRequirements){
				
				bFoundSafetyRequirement =  ModelProcessor.isFunctionalSafetyRequirement(derivedRequirement);
				if (bFoundSafetyRequirement) break;
			}
			
			bFoundNonCovered = !bFoundSafetyRequirement;
			if (bFoundNonCovered) break;
		}
	
		return !bFoundNonCovered;
	}

	@Override
	public String getDescription() {
		String s = "Definition:\n" +
		           "                                   #Safety Goals covered by FCS\n" +
				   " Safety Goals covered by FCS (%) = -----------------------------\n" +
		           "                                          #Safety Goals\n" +
				   "\n" +
				   "A Safety Goal is covered by FCS if all its requirements has at least one \n" +
				   "derived Functional Safety Requirement.\n" +
				   "A Requirement is a Functional Safety Requirement if there is an FCS specifying it\n" +
				   "with its functionalSafetyRequirement property.";
				 return s;
	}

}
