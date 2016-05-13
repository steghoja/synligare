package org.eclipse.eatop.volvo.metrics.modelprocessing;

import java.util.Set;

import org.eclipse.eatop.eastadl21.Feature;
import org.eclipse.eatop.eastadl21.Realization;
import org.eclipse.eatop.eastadl21.Realization_realized;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.emf.ecore.EObject;

public class RealizedFeaturesMetric implements IMetric {

/***
 * 		 								#Features with Realization
 *      Calculates Realized Features = ---------------------------
 *                                         #Features 
 */
	@Override
	public Fraction calculateMetric(Set<EObject> modelSubtrees) {

		int nRealizedFeatures = 0;
		int nTotalNumberOfFeatures = 0;
		

		Set<Feature> features = ModelProcessor.findAllEAElementsOfTypeInTrees(Feature.class, modelSubtrees);
		nTotalNumberOfFeatures = features.size();
		
		//Note that the Realization object does not point directly at a Feature, instead there is a Realization_realized object between
		Set<Feature> realizedFeatures = ModelProcessor.findEAElementsOfTypeWithRelationship(features, Realization_realized.class);
		nRealizedFeatures = realizedFeatures.size(); 
		
		if (nTotalNumberOfFeatures == 0){
			return null;
		}
		
		return new Fraction(nRealizedFeatures, nTotalNumberOfFeatures);
	}

@Override
public String getDescription() {
	
	String s = "Definition:\n" +
			"                            #Features with at least one Realize relationship\n" +
			"   Realized Features (%) =  -------------------------------------------------\n" +
			"                                              #Features";
			
	return s;
}
}


