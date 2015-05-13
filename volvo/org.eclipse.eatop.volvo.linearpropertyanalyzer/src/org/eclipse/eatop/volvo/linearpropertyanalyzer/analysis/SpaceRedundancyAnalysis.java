package org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis;

import java.util.List;

import org.eclipse.eatop.eastadl21.GenericConstraintKind;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.helpers.AnalysisResult;

public class SpaceRedundancyAnalysis extends AbstractSummarizingAnalysis {
	
	/**
	 * Default constructor
	 */
	public SpaceRedundancyAnalysis() {
		genericConstraintKind = GenericConstraintKind.SPACE_REDUNDANCY;
	}

	/**
	 * The specific analysis to perform
	 * @param requirementValue Value of requirement associated with analysis
	 * @param values Values for elements associated with analysis
	 * @return result of analysis
	 */
	@Override
	protected AnalysisResult specificAnalysis(String requirementValue, double resultValue) {
		return minValueAnalysis(requirementValue, resultValue);
	}

	/**
	 * Output string for successful result with limit
	 * @param limit the limit value
	 * @param value the actual value
	 * @return output string for result
	 */
	@Override
	protected String successfulLimitedResult(double limit, double value) {
		return "Space redundancy: " + value + " / " + limit;
	}

	/**
	 * Output string for failed result with limit
	 * @param limit the limit value
	 * @param value the actual value
	 * @return output string for result
	 */
	@Override
	protected String failedLimitedResult(double limit, double value) {
		return "Space redundancy: " + value + " / " + limit;
	}

	/**
	 * Output string for successful result without limit
	 * @param value the actual value
	 * @return output string for result
	 */
	@Override
	protected String unlimitedResult(double value) {
		return "Total space redundancy: " + value;
	}
	
	/**
	 * Checks if the string value provided is correct
	 * @param value the value provided
	 * @return true if correct, otherwise false
	 */
	@Override
	protected boolean correctValue(String value) {
		if(findValue(value) > 0)
			return true;
		return false;
	}
}