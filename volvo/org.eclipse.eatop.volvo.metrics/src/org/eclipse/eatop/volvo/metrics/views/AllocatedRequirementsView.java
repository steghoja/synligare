package org.eclipse.eatop.volvo.metrics.views;

import java.util.Set;

import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.eatop.volvo.metrics.modelprocessing.AllocatedRequirementsMetric;
import org.eclipse.emf.ecore.EObject;

public class AllocatedRequirementsView extends MetricsViewBase {

	
	AllocatedRequirementsMetric metric = new AllocatedRequirementsMetric();
	
	public AllocatedRequirementsView() {
	
		 greenPiePieceDescription = "Allocated Requirements";	 
		 redPiePieceDescription = "Unallocated Requirements";
		 noDenominatorFoundDescription = "No Requirements found.";
		 greenLabeltoolTipText = metric.getDescription();
	}

	@Override
	public Fraction calculateMetric(Set<EObject> modelSubtrees) {
		return metric.calculateMetric(modelSubtrees);
	}
	
}
