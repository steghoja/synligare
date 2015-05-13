package org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.helpers;

import org.eclipse.eatop.eastadl21.GenericConstraintKind;


public class AnalysisResult {
	private boolean passed;
	private String result;
	private double target, actual;
	private String type;
	private GenericConstraintKind genericConstraintKind;
	
	/**
	 * Creates a new AnalysisResult that contains all the information about how the result of an analysis
	 * @param passed boolean indicating if the analysis passed or not
	 * @param result message saying more exactly what happened
	 * @param target the maximum target that the analysis must go under
	 * @param actual the total number of what all the elements needed
	 * @param genericConstraintKind what kind of analysis that was done
	 */
	public AnalysisResult(boolean passed, String result, double target, double actual, GenericConstraintKind genericConstraintKind, String type){
		this.passed = passed;
		this.result = result;
		this.target = target;
		this.actual = actual;
		this.type = type;
		this.genericConstraintKind = genericConstraintKind;
	}

	/**
	 * Returns a boolean indicating of the result of the analysis passed or failed
	 * @return true of passed, otherwise false
	 */
	public boolean hasPassed() {
		return passed;
	}
	
	/**
	 * Returns the string saying how the analysis went
	 * @return the string that contains the result and could be used to display it in a window
	 */
	public String getResult(){
		return result;
	}
	
	/**
	 * Returns the target of what the analysis should go under.
	 * @return double with the maximum
	 */
	public double getTarget(){
		return target;
	}
	
	/**
	 * Returns the actual result of the analysis and how much all elements needed
	 * @return double with how much resources the model needed
	 */
	public double getActual(){
		return actual;
	}
	
	/**
	 * Returns the GenericConstraintKind that was used
	 * @return The GenericConstraintKind that was used in the analysis
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * This method overrides the ordinary equals method. It compares all the class variables to see that all match
	 */
	@Override
	public boolean equals(Object o){
		if(o instanceof AnalysisResult){
			AnalysisResult ar = (AnalysisResult) o;
			if(ar.hasPassed() == this.hasPassed() && 
					ar.getResult().equals(this.getResult()) &&
					ar.getActual() == this.getActual() && 
					ar.getTarget() == this.getTarget() &&
					ar.getGenericConstraintKind() == this.getGenericConstraintKind() &&
					ar.getType().equals(this.getType())){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return getResult().hashCode();
	}

	public GenericConstraintKind getGenericConstraintKind() {
		return genericConstraintKind;
	}
}
