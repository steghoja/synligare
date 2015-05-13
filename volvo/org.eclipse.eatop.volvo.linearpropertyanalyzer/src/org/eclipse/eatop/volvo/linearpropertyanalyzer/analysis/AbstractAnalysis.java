package org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis;

import java.util.List;
import java.util.Scanner;

import org.eclipse.emf.common.util.EList;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.GenericConstraintKind;
import org.eclipse.eatop.eastadl21.EANumericalValue;
import org.eclipse.eatop.eastadl21.EAStringValue;
import org.eclipse.eatop.eastadl21.EAValue;
import org.eclipse.eatop.eastadl21.Refine;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Refine_refinedBy;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.helpers.AnalysisResult;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger.FindRef_getRefine_refinedRequirement;

public abstract class AbstractAnalysis {

	//Type of generic constraints to analyze
	protected GenericConstraintKind genericConstraintKind;
	
	/**
	 * Output string for successful result with limit
	 * @param limit the limit value
	 * @param value the actual value
	 * @return output string for result
	 */
	protected abstract String successfulLimitedResult(double limit, double value);

	/**
	 * Output string for failed result with limit
	 * @param limit the limit value
	 * @param value the actual value
	 * @return output string for result
	 */
	protected abstract String failedLimitedResult(double limit, double value);

	/**
	 * Output string for successful result without limit
	 * @param value the actual value
	 * @return output string for result
	 */
	protected abstract String unlimitedResult(double value);

	/**
	 * The specific analysis to perform
	 * @param requirementValue Value of requirement associated with analysis
	 * @param values Values for elements associated with analysis
	 * @return result of analysis
	 */
	protected abstract AnalysisResult specificAnalysis(String requirementValue, double resultValue);

	/**
	 * Check if value of constraint is correct
	 * @param value the value to check
	 * @return true if correct, otherwise false
	 */
	protected abstract boolean correctValue(String value);
	
	protected String parseRequirement(Requirement requirement) throws Exception{
		if(requirement != null)
		{
			FindRef_getRefine_refinedRequirement temp= new FindRef_getRefine_refinedRequirement();
			EList<Refine> resultRefine = temp.getRefine_refinedRequirement(requirement); 
			if(resultRefine!=null && resultRefine.size()>0)
			{
				for(Refine refine:resultRefine)
				{
					if(refine.getRefinedBy()!=null && refine.getRefinedBy().size()>0)
					{
						for(Refine_refinedBy refine_refinedBy: refine.getRefinedBy())
						{
							GenericConstraint gc = (GenericConstraint) refine_refinedBy.getIdentifiable_target();
							if(gc.getKind().compareTo(genericConstraintKind) == 0){
								EAValue temps= gc.getValue();
								if (temps instanceof EANumericalValue)
									return ((EANumericalValue) temps).getValue();
								else return null;
							}
						}
					}
					else
					{
						throw new IllegalArgumentException("Requirement had no refined_by link");
					}
				}
			}
			else 
			{
				throw new IllegalArgumentException("Requirement had no refine links");
			}
		}
		else{
			return "";
		}
		return "";
	}
	
	protected double findValue(String value){
		Scanner scanner = null;
		double result = -1; 

		//Find value, if any
		if(value != null && ! value.equals("") ){
			scanner = new Scanner(value);
			if(scanner.hasNextDouble()){
				result = scanner.nextDouble();
			}
			else if(scanner.hasNextInt()){
				result = (double)scanner.nextInt();
			}
		}
		return result;
	}
	
	protected double findOtherValue(String value){
		//testing if this works
		Scanner scanner = null;
		double result = -1; 
		

		//Find value, if any
		if(value != null && ! value.equals("") ){
			scanner = new Scanner(value);
			if(scanner.hasNext()){
				scanner.next();
			}
			if(scanner.hasNextDouble()){
				result = scanner.nextDouble();
			}
			else if(scanner.hasNextInt()){
				result = (double)scanner.nextInt();
			}
		}
		return result;
	}
}