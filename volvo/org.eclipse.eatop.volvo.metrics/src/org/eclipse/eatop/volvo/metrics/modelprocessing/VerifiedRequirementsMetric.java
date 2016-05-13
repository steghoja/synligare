package org.eclipse.eatop.volvo.metrics.modelprocessing;

import java.util.Set;

import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Verify;
import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.emf.ecore.EObject;

public class VerifiedRequirementsMetric implements IMetric {

/***
 * 		 									  #Requirements with verify
 *      Calculates Verified Requirements = --------------------------
 *                                                #Requirements 
 */
	@Override
	public Fraction calculateMetric(Set<EObject> modelSubtrees) {

		int nVerifiedReqs = 0;
		int nTotalNumberOfReqs = 0;
		

		Set<Requirement> requirements = ModelProcessor.findAllEAElementsOfTypeInTrees(Requirement.class, modelSubtrees);
		nTotalNumberOfReqs = requirements.size();
		
		Set<Requirement> verifiedReqs = ModelProcessor.findEAElementsOfTypeWithRelationship(requirements, Verify.class);
		nVerifiedReqs = verifiedReqs.size(); 
		
		if (nTotalNumberOfReqs == 0){
			return null;
		}
		
		return new Fraction(nVerifiedReqs, nTotalNumberOfReqs);
	}

@Override
public String getDescription() {
	
	String s = "Definition:\n" +
			"                              #Requirements with at least one Verify relationship\n" +
			"   Verified Requirements (%)= ---------------------------------------------------\n" +
			"                                               #Requirements";
			
	return s;
}

	
	
}
