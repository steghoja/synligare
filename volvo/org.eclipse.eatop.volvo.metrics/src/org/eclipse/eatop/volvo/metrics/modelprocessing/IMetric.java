package org.eclipse.eatop.volvo.metrics.modelprocessing;

import java.util.Set;
import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.emf.ecore.EObject;


public interface IMetric {
	/***
	 * If metric cannot be calculated, Null is returned.
	 */
	public Fraction calculateMetric(Set<EObject> modelSubtrees);
	
	public String getDescription();
}
