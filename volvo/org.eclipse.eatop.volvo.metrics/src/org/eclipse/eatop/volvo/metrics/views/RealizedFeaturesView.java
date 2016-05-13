package org.eclipse.eatop.volvo.metrics.views;

import java.util.Set;

import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.eatop.volvo.metrics.modelprocessing.RealizedFeaturesMetric;

import org.eclipse.emf.ecore.EObject;

public class RealizedFeaturesView extends MetricsViewBase {

	RealizedFeaturesMetric metric = new RealizedFeaturesMetric();
	
	public RealizedFeaturesView() {
		 greenPiePieceDescription = "Realized Features";	 
		 redPiePieceDescription = "Unrealized Features";
		 noDenominatorFoundDescription = "No Features found.";
		 greenLabeltoolTipText = metric.getDescription();
		 
		metricFraction = new Fraction(2, 10);

	}

	@Override
	public Fraction calculateMetric(Set<EObject> modelSubtrees) {
		return metric.calculateMetric(modelSubtrees);
	}
	
}
