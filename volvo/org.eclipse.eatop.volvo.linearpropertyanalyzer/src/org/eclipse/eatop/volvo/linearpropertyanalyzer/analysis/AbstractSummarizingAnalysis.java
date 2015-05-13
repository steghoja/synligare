package org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EANumericalValue;
import org.eclipse.eatop.eastadl21.EAStringValue;
import org.eclipse.eatop.eastadl21.EAValue;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.eastadl21.HardwareConnector;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.HardwarePort;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.helpers.AnalysisResult;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.controllers.EnterMissingInformationController;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger.FindRef_getGenericConstraint_target;

public abstract class AbstractSummarizingAnalysis extends AbstractAnalysis {	

	/**
	 * Generic analysis for maximum values
	 * @param requirementValue limit value
	 * @param values actual values
	 * @return result of the analysis
	 */
	protected AnalysisResult maxValueAnalysis(String requirementValue, double actual){
		AnalysisResult result;
		double limit = -1; 

		//Find limit value, if any
		limit = findValue(requirementValue);

		

		//Evaluate result
		if(limit == -1){
			result = new AnalysisResult(true,unlimitedResult(actual), limit, actual, genericConstraintKind,"");
		}
		else if(limit >= actual){
			result = new AnalysisResult(true,successfulLimitedResult(limit, actual), limit, actual, genericConstraintKind,"");
		}
		else{
			result = new AnalysisResult(false,failedLimitedResult(limit, actual), limit, actual, genericConstraintKind,"");
		}
		return result;
	}

	/**
	 * Generic analysis for minimum values
	 * @param requirementValue limit value
	 * @param values actual values
	 * @return result of the analysis
	 */
	protected AnalysisResult minValueAnalysis(String requirementValue, double actual){
		AnalysisResult result;
		double limit = -10; 

		//Find limit value, if any
		limit = findValue(requirementValue);

		

		if(limit == -1){
			result = new AnalysisResult(true,unlimitedResult(actual), limit, actual, genericConstraintKind,"");
		}
		else if(limit <= actual){
			result = new AnalysisResult(true,successfulLimitedResult(limit, actual), limit, actual, genericConstraintKind,"");
		}
		else{
			result = new AnalysisResult(false,failedLimitedResult(limit, actual), limit, actual, genericConstraintKind,"");
		}
		return result;
	}

	/**
	 * Analyze elements with respect to requirement
	 * @param elements the elements to analyze
	 * @param requirement the requirement to analyze
	 * @return result of analysis
	 * @throws Exception 
	 * @throws IllegalArgumentException if elements is null
	 */
	public AnalysisResult analyze(GenericConstraint genCon, Requirement requirement) throws Exception{
//		List<String> values = new LinkedList<String>();
		double valueResult =0;
		//Parse requirement
		String requirementValue = parseRequirement(requirement);
		
		EList<Identifiable> targetElements = genCon.getTarget();
		for (Identifiable item: targetElements){
		
			if (item instanceof HardwareComponentType){
				HardwareComponentType targetType = (HardwareComponentType) item;
				valueResult += calculateValueForType(genCon,
						targetType);
			}
			else if (item instanceof HardwareComponentPrototype){
				HardwareComponentType targetType=((HardwareComponentPrototype) item).getType();
				boolean valueset = false;
				FindRef_getGenericConstraint_target temp2 = new FindRef_getGenericConstraint_target();
				EList<GenericConstraint>  GCs2= (EList<GenericConstraint>) temp2.getGenericConstraint_target(targetType);
				for (GenericConstraint GenCon2 : GCs2){
					if (GenCon2.getKind()==genCon.getKind()){
						if (GenCon2.getValue() instanceof EANumericalValue){//try to get a value from the type
							String valueString =((EANumericalValue)GenCon2.getValue()).getValue();
							double tempResult = findValue(valueString);
							if(tempResult>0){
								valueResult += tempResult;
							}
							valueset=true;
						}
						break;
					}
					
				}
				if (!valueset){//into iterate the type
					valueResult += calculateValueForType(genCon,
							targetType);
				}
			}
			
			
		}
		
		
		
		return specificAnalysis(requirementValue,valueResult);
	}

	private double calculateValueForType(GenericConstraint genCon,
			 HardwareComponentType targetType)
			throws Exception {
		double valueResult =0;
		if(targetType!=null){//calculate a type
			for (HardwareComponentPrototype subPro: targetType.getPart()){
				boolean valueset = false;
				FindRef_getGenericConstraint_target temp = new FindRef_getGenericConstraint_target();
				EList<GenericConstraint>  GCs= (EList<GenericConstraint>) temp.getGenericConstraint_target(subPro);
				for (GenericConstraint GenCon : GCs){
					if (GenCon.getKind()==genCon.getKind()){
						if (GenCon.getValue() instanceof EANumericalValue){//try to get a value from the prototype
							String valueString =((EANumericalValue)GenCon.getValue()).getValue();
							double tempResult = findValue(valueString);
							if(tempResult>0){
								valueResult+=tempResult;
							}
							valueset = true;
						}
						break;
					}
				}
				if(!valueset){ 
					HardwareComponentType subType = subPro.getType();//go to its type
					boolean valueset2 = false;
					FindRef_getGenericConstraint_target temp2 = new FindRef_getGenericConstraint_target();
					EList<GenericConstraint>  GCs2= (EList<GenericConstraint>) temp2.getGenericConstraint_target(subType);
					for (GenericConstraint GenCon2 : GCs2){
						if (GenCon2.getKind()==genCon.getKind()){
							if (GenCon2.getValue() instanceof EANumericalValue){//try to get a value from the type
								String valueString =((EANumericalValue)GenCon2.getValue()).getValue();
								double tempResult = findValue(valueString);
								if(tempResult>0){
									valueResult+=tempResult;
								}
								valueset2 = true;
							}
							break;
						}
						
					}
					if (!valueset2){//into iteration
						valueResult += calculateValueForType(genCon,
								subType);
					}
					
				}

			}
			
			for (HardwareConnector subElement: targetType.getConnector()){
				FindRef_getGenericConstraint_target temp = new FindRef_getGenericConstraint_target();
				EList<GenericConstraint>  GCs= (EList<GenericConstraint>) temp.getGenericConstraint_target(subElement);
				for (GenericConstraint GenCon : GCs){
					if (GenCon.getKind()==genCon.getKind()){
						if (GenCon.getValue() instanceof EANumericalValue){//try to get a value from the prototype
							String valueString =((EANumericalValue)GenCon.getValue()).getValue();
							double tempResult = findValue(valueString);
							if(tempResult>0){
								valueResult+=tempResult;
							}
							
						}
						break;
					}
				}
			}
			for (HardwarePin subElement: targetType.getPin()){
				FindRef_getGenericConstraint_target temp = new FindRef_getGenericConstraint_target();
				EList<GenericConstraint>  GCs= (EList<GenericConstraint>) temp.getGenericConstraint_target(subElement);
				for (GenericConstraint GenCon : GCs){
					if (GenCon.getKind()==genCon.getKind()){
						if (GenCon.getValue() instanceof EANumericalValue){//try to get a value from the prototype
							String valueString =((EANumericalValue)GenCon.getValue()).getValue();
							double tempResult = findValue(valueString);
							if(tempResult>0){
								valueResult+=tempResult;
							}
							
						}
						break;
					}
				}
			}
			for (HardwarePort subElement: targetType.getPort()){
				FindRef_getGenericConstraint_target temp = new FindRef_getGenericConstraint_target();
				EList<GenericConstraint>  GCs= (EList<GenericConstraint>) temp.getGenericConstraint_target(subElement);
				for (GenericConstraint GenCon : GCs){
					if (GenCon.getKind()==genCon.getKind()){
						if (GenCon.getValue() instanceof EANumericalValue){//try to get a value from the prototype
							String valueString =((EANumericalValue)GenCon.getValue()).getValue();
							double tempResult = findValue(valueString);
							if(tempResult>0){
								valueResult+=tempResult;
							}
							
						}
						break;
					}
				}
			}
		}
		return valueResult;
	}
}