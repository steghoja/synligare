package org.eclipse.eatop.volvo.metrics.views;

import java.util.Set;

import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.eatop.volvo.metrics.modelprocessing.FSCwithTSCMetric;
import org.eclipse.eatop.volvo.metrics.modelprocessing.AllocatedRequirementsMetric;
import org.eclipse.emf.ecore.EObject;



public class FSCwithTSCView extends MetricsViewBase {

	FSCwithTSCMetric metric = new FSCwithTSCMetric();
	
	public FSCwithTSCView() {
	
		
		greenPiePieceDescription = "Functional Safety Concepts with Technical Safety Concepts";	 
		redPiePieceDescription = "Functional Safety Concepts without Technical Safety Concepts";
		noDenominatorFoundDescription = "No Functional Safety Concepts found.";
		greenLabeltoolTipText = metric.getDescription();
		 
		metricFraction = new Fraction(1, 10);
	
	}


	@Override
	public Fraction calculateMetric(Set<EObject> modelSubtrees) {
		return metric.calculateMetric(modelSubtrees);
	}
}
