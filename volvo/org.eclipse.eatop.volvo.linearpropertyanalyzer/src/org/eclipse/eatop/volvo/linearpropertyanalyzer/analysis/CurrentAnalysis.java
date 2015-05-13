package org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis;

import java.util.List;

import org.eclipse.eatop.eastadl21.GenericConstraintKind;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.helpers.AnalysisResult;

public class CurrentAnalysis extends AbstractModeAnalysis{

	/**
	 * Default constructor
	 */
	public CurrentAnalysis() {
		genericConstraintKind = GenericConstraintKind.CURRENT;
	}
	
	@Override
	protected String successfulLimitedResult(double limit, double value) {
		return "Current: " + value + " / " + limit;
	}

	@Override
	protected String failedLimitedResult(double limit, double value) {
		return "Current: " + value + " / " + limit;
	}

	@Override
	protected String unlimitedResult(double value) {
		return "Total current: " + value;
	}

	@Override
	protected AnalysisResult specificAnalysis(String requirementValue,
			double actual) {
		AnalysisResult result;
		double limit = -1;
		String type = "";
		
		//Find limit value, if any
		limit = findValue(requirementValue);

//		//Sum actual values
//		if(values != null){
//			double temp;
//			Scanner sc;
//			for(String value : values){
//				sc = new Scanner(value);
//				if(sc.hasNext()){
//					type = sc.next();
//				}
//				if(sc.hasNextDouble()){
//					power = sc.nextDouble();
//				}
//				else if(sc.hasNextInt()){
//					power = (double)sc.nextInt();
//				}
//				if(sc.hasNextDouble()){
//					voltage = sc.nextDouble();
//				}
//				else if(sc.hasNextInt()){
//					power = (double)sc.nextInt();
//				}
//				temp = power / voltage;
//				if(temp >= 0){
//					actual += temp;
//				}
//			}
//		}
	
		//Evaluate result
		if(limit == -1){
			result = new AnalysisResult(true,unlimitedResult(actual), limit, actual, genericConstraintKind,type);
		}
		else if(limit >= actual){
			result = new AnalysisResult(true,successfulLimitedResult(limit, actual), limit, actual, genericConstraintKind,type);
		}
		else{
			result = new AnalysisResult(false,failedLimitedResult(limit, actual), limit, actual, genericConstraintKind,type);
		}
		return result;
	}

	@Override
	protected boolean correctValue(String value) {
		if(findValue(value) > 0)
			return true;
		return false;
	}
}
