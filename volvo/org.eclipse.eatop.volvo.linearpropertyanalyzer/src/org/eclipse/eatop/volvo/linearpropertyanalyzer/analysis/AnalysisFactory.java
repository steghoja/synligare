package org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis;  

import java.util.List;

import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.helpers.AnalysisResult;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.models.ModelIO;

public enum AnalysisFactory {
	INSTANCE;

	public AnalysisResult create(GenericConstraint genCon,Requirement requirement) throws Exception{
		return create(genCon,requirement,null);
	}
	
	//TODO: Ändra till AnalysisTypes
	/**
	 * This method will based on the parameter GenericConstraintKind do the different analysis and return a AnalysisResult with the result of the analysis
	 * @param genCon the GenericConstraintKind that the analysis is of
	 * @param elements all the elements that should be in the analysis
	 * @param requirement the requirement that will be used to evaluate if the analysis passed or failed
	 * @return returns a AnalysisResult with all the necessary information about the analysis
	 * @throws Exception 
	 */
	public AnalysisResult create(GenericConstraint genCon,  Requirement requirement, List<Mode> modes) throws Exception{
		AnalysisResult result = null;
		switch(genCon.getKind()){
		case PIECE_COST:
			result = new PieceCostAnalysis().analyze(genCon, requirement);
			break;
		case CABLE_LENGTH:
			result = new CableLengthAnalysis().analyze(genCon, requirement);
			break;
		case DEVELOPMENT_COST:
			result = new DevelopmentCostAnalysis().analyze(genCon, requirement);
			break;
		case WEIGHT:
			result = new WeightAnalysis().analyze(genCon, requirement);
			break;
		case SPACE_REDUNDANCY:
			result = new SpaceRedundancyAnalysis().analyze(genCon, requirement);
			break;
		case TIME_REDUNDANCY:
			result = new TimeRedundancyAnalysis().analyze(genCon, requirement);
			break;
		case POWER_CONSUMPTION:
//			if(modes != null){
				result = new PowerConsumptionAnalysis().analyze(genCon, requirement, modes);
//			}
			break;
		case CURRENT:
			result = new CurrentAnalysis().analyze(genCon, requirement, modes);
			break;
//		case OTHER:
//			EAValue temps= genCon.getValue();
//			String type =null;
//			if (temps instanceof EAStringValue)
//				type = ((EAStringValue) temps).getValue();
//			
//			
//			if(type.startsWith("CPU")){
//				result = new CPULoadAnalysis().analyze(elements, requirement, modes);
//			}
//			break;
		}
		if(result!=null){
			ModelIO.INSTANCE.writeResultToModel(result);
		}
		else{
			//TODO:fail message
		}
		return result;
	}
}
