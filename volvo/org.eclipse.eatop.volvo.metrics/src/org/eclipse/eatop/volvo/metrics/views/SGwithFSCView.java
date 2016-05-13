package org.eclipse.eatop.volvo.metrics.views;

import java.util.Set;

import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.eatop.volvo.metrics.modelprocessing.SGwithFCSsMetric;
import org.eclipse.emf.ecore.EObject;

public class SGwithFSCView extends MetricsViewBase {

	SGwithFCSsMetric metric = new SGwithFCSsMetric();
	
	public SGwithFSCView() {

		
		greenPiePieceDescription = "Safety Goals with Functional Safety Concept";	 
		redPiePieceDescription = "Safety Goals without Functional Safety Concept";
		noDenominatorFoundDescription = "No Safety Goals found.";
		 greenLabeltoolTipText = metric.getDescription();
		 
		}
	

	@Override
	public Fraction calculateMetric(Set<EObject> modelSubtrees) {
		return metric.calculateMetric(modelSubtrees);
	}
}
