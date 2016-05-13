package org.eclipse.eatop.volvo.metrics.modelprocessing;

import java.util.Set;

import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.emf.ecore.EObject;

public class AllocatedRequirementsMetric implements IMetric {


	@Override
	public String getDescription(){
		String s = "Definition:\n" +
				"                                 #Requirements with at least one Satisfy relationship\n" +
				"   Allocated Requirements (%) =  ----------------------------------------------------\n" +
				"                                                  #Requirements";
				
		return s;
	}
	
	@Override
	public Fraction calculateMetric(Set<EObject> modelSubtrees) {

		int nAllocatedReqs = 0;
		int nTotalNumberOfReqs = 0;
		

		Set<Requirement> requirements = ModelProcessor.findAllEAElementsOfTypeInTrees(Requirement.class, modelSubtrees);
		nTotalNumberOfReqs = requirements.size();
	
		Set<Requirement> allocatedReqs = ModelProcessor.findEAElementsOfTypeWithRelationship(requirements, Satisfy.class);
		nAllocatedReqs = allocatedReqs.size(); 
		
		if (nTotalNumberOfReqs == 0){
			return null;
		}
		
		return new Fraction(nAllocatedReqs, nTotalNumberOfReqs);
	}
	
}
