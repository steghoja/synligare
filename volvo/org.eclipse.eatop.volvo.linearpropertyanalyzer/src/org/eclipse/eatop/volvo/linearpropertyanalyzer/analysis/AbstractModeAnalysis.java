package org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.Mode;
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

public abstract class AbstractModeAnalysis extends AbstractAnalysis {

	private double createAnalysisData(
			List<Mode> modes,
			GenericConstraint genCon) throws Exception {
		double valueResult =0;
		//Parse requirement
		
		
		EList<Identifiable> targetElements = genCon.getTarget();
		for (Identifiable item: targetElements){
		
			if (item instanceof HardwareComponentType){
				HardwareComponentType targetType = (HardwareComponentType) item;
				valueResult += calculateValueForType(genCon,
						targetType,modes);
			}
			else if (item instanceof HardwareComponentPrototype){
				HardwareComponentType targetType=((HardwareComponentPrototype) item).getType();
				boolean valueset = false;
				FindRef_getGenericConstraint_target temp2 = new FindRef_getGenericConstraint_target();
				EList<GenericConstraint>  GCs2= (EList<GenericConstraint>) temp2.getGenericConstraint_target(targetType);
				for (GenericConstraint GenCon2 : GCs2){
					if (GenCon2.getKind()==genCon.getKind()){
						boolean modeMatch = false;
						
						if (GenCon2.getMode().isEmpty())
							modeMatch = true;
						else{
							for (Mode m: modes){
								if(GenCon2.getMode().contains(m))
									modeMatch = true;
							}
						}
						if (modeMatch & GenCon2.getValue() instanceof EANumericalValue){//try to get a value from the type
							String valueString =((EANumericalValue)GenCon2.getValue()).getValue();
							double tempResult = findValue(valueString);
							if(tempResult>0){
								valueResult += tempResult;
							}
							valueset=true;
							break;
						}
						
					}
					
				}
				if (!valueset){//into iterate the type
					valueResult += calculateValueForType(genCon,
							targetType,modes);
				}
			}
			
			
		}
		
		return valueResult;
		//loop over modes and elements, merge results
		//call analysis
	}
	private double calculateValueForType(GenericConstraint genCon,
			 HardwareComponentType targetType,List<Mode> modes)
			throws Exception {
		double valueResult =0;
		if(targetType!=null){//calculate a type
			for (HardwareComponentPrototype subPro: targetType.getPart()){
				boolean valueset = false;
				FindRef_getGenericConstraint_target temp = new FindRef_getGenericConstraint_target();
				EList<GenericConstraint>  GCs= (EList<GenericConstraint>) temp.getGenericConstraint_target(subPro);
				for (GenericConstraint GenCon : GCs){
					if (GenCon.getKind()==genCon.getKind()){
						boolean modeMatch = false;
						
						if (GenCon.getMode().isEmpty())
							modeMatch = true;
						else{
							for (Mode m: modes){
								if(GenCon.getMode().contains(m))
									modeMatch = true;
							}
						}
						if (modeMatch & GenCon.getValue() instanceof EANumericalValue){//try to get a value from the prototype
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
							boolean modeMatch = false;
							
							if (GenCon2.getMode().isEmpty())
								modeMatch = true;
							else{
								for (Mode m: modes){
									if(GenCon2.getMode().contains(m))
										modeMatch = true;
								}
							}
							if (modeMatch & GenCon2.getValue() instanceof EANumericalValue){//try to get a value from the type
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
								subType,modes);
					}
					
				}

			}
			
			for (HardwareConnector subElement: targetType.getConnector()){
				FindRef_getGenericConstraint_target temp = new FindRef_getGenericConstraint_target();
				EList<GenericConstraint>  GCs= (EList<GenericConstraint>) temp.getGenericConstraint_target(subElement);
				for (GenericConstraint GenCon : GCs){
					if (GenCon.getKind()==genCon.getKind()){
						boolean modeMatch = false;
						
						if (GenCon.getMode().isEmpty())
							modeMatch = true;
						else{
							for (Mode m: modes){
								if(GenCon.getMode().contains(m))
									modeMatch = true;
							}
						}
						if (modeMatch & GenCon.getValue() instanceof EANumericalValue){//try to get a value from the prototype
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
						boolean modeMatch = false;
						if (GenCon.getMode().isEmpty())
							modeMatch = true;
						else{
							for (Mode m: modes){
								if(GenCon.getMode().contains(m))
									modeMatch = true;
							}
						}
						if (modeMatch & GenCon.getValue() instanceof EANumericalValue){//try to get a value from the prototype
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
						boolean modeMatch = false;
						if (GenCon.getMode().isEmpty())
							modeMatch = true;
						else{
							for (Mode m: modes){
								if(GenCon.getMode().contains(m))
									modeMatch = true;
							}
						}
						if (modeMatch & GenCon.getValue() instanceof EANumericalValue){//try to get a value from the prototype
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
	/**
	 * 
	 * @param elements
	 * @param requirement
	 * @param modes
	 * @return
	 * @throws Exception 
	 */
	public AnalysisResult analyze(GenericConstraint genCon, Requirement requirement, List<Mode> modes) throws Exception{
		double value = createAnalysisData(modes, genCon);
		return analyze(value, requirement);
	}
	
	/**
	 * Analyze elements with respect to requirement
	 * @param elements the elements to analyze
	 * @param requirement the requirement to analyze
	 * @return result of analysis
	 * @throws Exception 
	 * @throws IllegalArgumentException if elements is null
	 */
	protected AnalysisResult analyze(double value, Requirement requirement) throws Exception{
		//Parse requirement
		String requirementValue = parseRequirement(requirement);
		
		//Perform analysis specified by subclass, return result of analysis
		return specificAnalysis(requirementValue, value);
	}
	
}