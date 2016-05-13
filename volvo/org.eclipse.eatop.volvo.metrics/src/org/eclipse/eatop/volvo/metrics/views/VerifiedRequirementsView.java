package org.eclipse.eatop.volvo.metrics.views;

import java.util.Set;

import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.eatop.volvo.metrics.modelprocessing.VerifiedRequirementsMetric;
import org.eclipse.emf.ecore.EObject;

public class VerifiedRequirementsView extends MetricsViewBase {

	VerifiedRequirementsMetric metric = new VerifiedRequirementsMetric();
	public VerifiedRequirementsView() {
		
		greenPiePieceDescription = "Verified Requirements";	 
		redPiePieceDescription = "Unverified Requirements";
 	    noDenominatorFoundDescription = "No Requirements found.";
		greenLabeltoolTipText = metric.getDescription();
		 
	}
	

	@Override
	public Fraction calculateMetric(Set<EObject> modelSubtrees) {
		return metric.calculateMetric(modelSubtrees);
	}
}
