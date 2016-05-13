package org.eclipse.eatop.volvo.metrics.modelprocessing;

import java.util.Set;

import org.eclipse.eatop.eastadl21.FunctionalSafetyConcept;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.SafetyGoal;
import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class FSCwithTSCMetric implements IMetric {

	/***
	 * 		 									  					  #FCS Covered by TCS
	 *      Calculates Functional Safety Concepts covered by TCS = --------------------------
	 *                                                				 #Functional Safety Concepts 
	 *
	 *      A Functional Safety Concepts is covered if all its safety requirements has at least one derived 
	 *      Technical Safety Requirement.
	 *      
	 *      A Requirement is a Technical Safety Requirement if there is a TCS.technicalSafetyRequirement pointing at it
	 *
	 */
		@Override
		public Fraction calculateMetric(Set<EObject> modelSubtrees) {

			int nTotalNumberFCS = 0;
			
			Set<FunctionalSafetyConcept> fscs = ModelProcessor.findAllEAElementsOfTypeInTrees(FunctionalSafetyConcept.class, modelSubtrees);
			nTotalNumberFCS = fscs.size();
			
			int nCoveredFCS = 0;
			for (FunctionalSafetyConcept fsc : fscs){

				if (isFunctionalSafetyConceptCoveredByTSC(fsc))
					nCoveredFCS++;
			}
			
			if (nTotalNumberFCS == 0) return null;
			
			return new Fraction(nCoveredFCS, nTotalNumberFCS);
			
		}
		
		public boolean isFunctionalSafetyConceptCoveredByTSC(FunctionalSafetyConcept fcs) {

			boolean bFoundNonCovered = false;
			
			EList<Requirement> fsrs = fcs.getFunctionalSafetyRequirement();
			if (fsrs.size() == 0) return false;
			
			for (Requirement fsr : fsrs){
				
				boolean bFoundTSR = false;
				Set<Requirement> derivedRequirements = ModelProcessor.findDerivedRequirements(fsr);
				for (Requirement derivedRequirement : derivedRequirements){
					
					bFoundTSR =  ModelProcessor.isTechnicalSafetyRequirement(derivedRequirement);
					if (bFoundTSR) break;
				}
				
				bFoundNonCovered = !bFoundTSR;
				if (bFoundNonCovered) break;
			}
		
			return !bFoundNonCovered;
		}

		@Override
		public String getDescription() {
			String s = "Definition:\n" +
			           "                                                  #FSC covered by TCS\n" +
					   " Functional Safety Concepts covered by TCS (%) = ---------------------\n" +
			           "                                                       #FSC \n" +
					   "\n" +
					   "A FSC is covered by a TCS if all its requirements has at least one derived \n" +
					   "Technical Safety Requirement.\n" +
					   "A Requirement is a Technical Safety Requirement if there is a TCS specifying it\n" + 
					   "with its technicalSafetyRequirement property.";
					 return s;
		}

}
