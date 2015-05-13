package org.eclipse.eatop.volvo.linearpropertyanalyzer.controllers; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.eclipse.emf.common.util.EList;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.GenericConstraintKind;
//HL: Unknown element GenericconstraintsFactoryImpl import org.eclipse.eatop.eastadl21.GenericconstraintsFactoryImpl;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EANumericalValue;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.eastadl21.HardwareConnector;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.HardwarePort;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.AnalysisFactory;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.helpers.AnalysisResult;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.analysis.helpers.SearchResult;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger.FindRef_getGenericConstraint_target;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger.FindRef_getSatisfy_satisfiedRequirement;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.models.ModelProcessor;

public enum AnalysisController {
	INSTANCE;
	
	private ModelProcessor modelProcessor = new ModelProcessor();
	private List<EAElement> reqModelSelection;
	private Requirement requirement;
	private List<GenericConstraint> genConList;
	private SearchResult tempResult;
	Map<GenericConstraint, List<EAElement>> selectedElements;
	private boolean reqModelSelectionIndicator=false;
	
//	/**
//	 * Return reqModelSelectionIndicator, that indicates cancel or terminate
//	 * @return reqModelSelectionIndicator
//	 */
//	public boolean isReqModelSelectionIndicator() {
//		return reqModelSelectionIndicator;
//	}
//
	/**
	 * Set the ReqModelSelectionIndicator boolean to whether cancel or terminate
	 * @param reqModelSelectionIndicator
	 */
	public void setReqModelSelectionIndicator(boolean reqModelSelectionIndicator) {
		this.reqModelSelectionIndicator = reqModelSelectionIndicator;
	}

	/**
	 * Prepare for a requirementModelAnalysis
	 * @param list with EAElements
	 * @throws Exception 
	 */
	public void reqModelAnalysis(List<EAElement> list) throws Exception{
		reqModelSelection = new LinkedList<EAElement>(list);
		if (reqModelSelection != null && reqModelSelection.size() > 0) {
			nextReqModelAnalysis();
		}
		//Analysis finished
	}

	/**
	 * Prepare for a requirementAnalysis
	 * @param req - the requirement that will be analyzed
	 * @throws Exception 
	 */
	public void reqAnalysis(Requirement req) throws Exception {
		genConList = modelProcessor.findAllGenConKindsInRequirement(req);
		requirement = req;
		if (reqModelSelectionIndicator == true) {
			WindowController.INSTANCE
					.setReqModelSelectionIndicator(reqModelSelectionIndicator);
		} else if (reqModelSelectionIndicator == false) {
			WindowController.INSTANCE
					.setReqModelSelectionIndicator(reqModelSelectionIndicator);
		}
		WindowController.INSTANCE
				.createGenericConstraintKindSelectionWindow(genConList);
	}

	/**
	 * Prepares for an elementAnalysis
	 * @param genConList - list with the genericconstraints that will be used in the analysis
	 * @throws Exception 
	 */
	public void elementAnalysis(List<GenericConstraint> genConList) throws Exception {
		//Here we will need the element clicked also
		ArrayList<EAElement> selectedElements = new ArrayList<EAElement>();
		GenericConstraint genCon =genConList.get(0);
		EList<Identifiable> targetElements = genCon.getTarget();
		for (Identifiable item: targetElements){
		
			if (item instanceof HardwareComponentType){
				HardwareComponentType targetType = (HardwareComponentType) item;
				selectedElements.addAll( traverseType(genCon,	targetType)); 
			}
			else if (item instanceof HardwareComponentPrototype){
				selectedElements.add((EAElement) item);
				HardwareComponentType targetType=((HardwareComponentPrototype) item).getType();
				selectedElements.addAll( traverseType(genCon,	targetType)); 
			
			}
			
			
		}
		
		
		
		HashMap<GenericConstraint, List<EAElement>> selectedAnalysisAndElements = new HashMap<GenericConstraint, List<EAElement>>();
		selectedAnalysisAndElements.put(genCon, selectedElements);
		AnalysisController.INSTANCE.modeSelection(genConList,selectedAnalysisAndElements);
	}
	private List<EAElement> traverseType(GenericConstraint genCon,
			 HardwareComponentType targetType)
			throws Exception {
		ArrayList<EAElement> selectedElements = new ArrayList<EAElement>();
		if(targetType!=null){//calculate a type
			for (HardwareComponentPrototype subPro: targetType.getPart()){
				selectedElements.add(subPro);
				
				
					HardwareComponentType subType = subPro.getType();//go to its type
					selectedElements.add(subType);
					selectedElements.addAll( traverseType(genCon,	subType)); 
					
					
					
				

			}
			
			for (HardwareConnector subElement: targetType.getConnector()){
				selectedElements.add(subElement);
			}
			for (HardwarePin subElement: targetType.getPin()){
				selectedElements.add(subElement);
			}
			for (HardwarePort subElement: targetType.getPort()){
				selectedElements.add(subElement);
			}
		}
		return selectedElements;
	}
//	private Map<GenericConstraint, List<EAElement>> noSatisfyLink() {
//		JOptionPane.showMessageDialog(null, "The requirement: "+requirement.getShortName()+" is missing a satisfylink", "Model invalid", JOptionPane.WARNING_MESSAGE);
//		return null;
//	}
//
	public boolean nextReqModelAnalysis() throws Exception{
		if (reqModelSelection != null && reqModelSelection.size() > 0) {
			reqAnalysis((Requirement)reqModelSelection.remove(0));
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * 
	 * @param selectedAnalysisAndElements
	 * @throws Exception 
	 */
	public void doTheAnalysis(List<GenericConstraint> genConList) throws Exception{
		for(GenericConstraint genCon : genConList){
			AnalysisResult result = AnalysisFactory.INSTANCE.create(genCon, requirement);
			JOptionPane.showMessageDialog(null, result.getResult(), "Analysis Result", (result.hasPassed()) ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
		}
		//Check to see if there are more things to do
		if(reqModelSelection != null && reqModelSelection.size() > 0){
			nextReqModelAnalysis();
		}
	}
	
	/**
	 * Method that will based on what analysis provide the Analysisfactory with a list with modes or not
	 * @param selectedAnalysisAndElements - map with selected elements and genericconstraint as key
	 * @param modeMap - map with a list of modes and a genericconstraint as key
	 * @throws Exception 
	 */
	public void doTheAnalysis(Map<GenericConstraint, List<Mode>> modeMap) throws Exception{
		for(GenericConstraint genCon : modeMap.keySet()){
			AnalysisResult result = null;
			List<Mode> modes;
			switch(genCon.getKind()){
			case POWER_CONSUMPTION:
				modes = modeMap.get(genCon);
//				if(modes != null){
					result = AnalysisFactory.INSTANCE.create(genCon,  requirement, modes);
//				}
				break;
			case CURRENT:
				modes = modeMap.get(genCon);
//				if(modes != null){
					result = AnalysisFactory.INSTANCE.create(genCon,  requirement, modes);
//				}
				break;
//			case OTHER:
//				modes = modeMap.get(genCon);
//				if(modes != null){
//					result = AnalysisFactory.INSTANCE.create(genCon, selectedAnalysisAndElements.get(genCon), requirement, modes);
//				}
//				break;
			default:
				result = AnalysisFactory.INSTANCE.create(genCon,  requirement, null);
				break;
			}
			if(result != null){
				JOptionPane.showMessageDialog(null, result.getResult(), "Analysis Result", (result.hasPassed()) ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "There was an error running analysis", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		//Check to see if there are more things to do
		if(reqModelSelection != null && reqModelSelection.size() > 0){
			nextReqModelAnalysis();
		}
	}
//
//	/**
//	 * This method will handle an analysis with a element not a requirement or requirementmodels
//	 * @param element - the element selected in the model
//	 * @throws Exception 
//	 */
//	public void handleElementWithoutRequirement(EAElement element) throws Exception {
//		ModelProcessor modelProcessor = new ModelProcessor();
//		tempResult = modelProcessor.findAllElementsBySatisfy(element, getListWithAllGenConKinds());
//		Iterator<Map.Entry<GenericConstraint, List<EAElement>>> it = tempResult.getResultMapFindEleBySatisfy().entrySet().iterator();
//		while(it.hasNext()){
//			Map.Entry<GenericConstraint, List<EAElement>> entry = it.next();
//			if(tempResult.getResultMapFindEleBySatisfy().get(entry.getKey()).size() == 0){
//				it.remove();
//				tempResult.getResultTreeMapFindEleBySatisfy().remove(entry.getKey());
//			}
//		}
//		List<GenericConstraint> resultList = new ArrayList<GenericConstraint>(tempResult.getResultMapFindEleBySatisfy().keySet());
//		WindowController.INSTANCE.createGenericConstraintKindSelectionWindow(resultList);
//	}
//	
//	private  List<GenericConstraint> getListWithAllGenConKinds() {
//		List<GenericConstraint> genConList = new ArrayList<GenericConstraint>();
//		genConList.add(createGenConByKind(GenericConstraintKind.CABLE_LENGTH));
//		genConList.add(createGenConByKind(GenericConstraintKind.DEVELOPMENT_COST));
//		GenericConstraint currentGenCon = createGenConByKind(GenericConstraintKind.OTHER);
////		currentGenCon.setGenericConstraintValue("CURRENT");
////		genConList.add(currentGenCon);
//		genConList.add(createGenConByKind(GenericConstraintKind.CURRENT));
//		GenericConstraint busGenCon = createGenConByKind(GenericConstraintKind.OTHER);
////		busGenCon.setGenericConstraintValue("BUS");
//		genConList.add(busGenCon);
//		GenericConstraint cpuGenCon = createGenConByKind(GenericConstraintKind.OTHER);
////		cpuGenCon.setGenericConstraintValue("CPU");
//		genConList.add(cpuGenCon);
//		genConList.add(createGenConByKind(GenericConstraintKind.PIECE_COST));
//		genConList.add(createGenConByKind(GenericConstraintKind.POWER_CONSUMPTION));
//		genConList.add(createGenConByKind(GenericConstraintKind.SPACE_REDUNDANCY));
//		genConList.add(createGenConByKind(GenericConstraintKind.TIME_REDUNDANCY));
//		genConList.add(createGenConByKind(GenericConstraintKind.WEIGHT));
//		return genConList;
//	}
//
//	private GenericConstraint createGenConByKind(GenericConstraintKind genConKind) {
//		GenericConstraint genCon = GenericconstraintsFactoryImpl.eINSTANCE.createGenericConstraint();
//		genCon.setKind(genConKind);
//		return genCon;
//	}
//
//	/**
//	 * Generates a list with GenericConstraint based on a list with strings with the names of all genericconstraints selected
//	 * @param sList - list with strings that represents genericconstraints
//	 * @param genConList - list with all the genericconstraints that will be matched with the string list
//	 * @return
//	 */
//	public static List<GenericConstraint> generateGenConFromStringList(List<String> sList, List<GenericConstraint> genConList)
//	{
//		List<GenericConstraint> returnList = new ArrayList<GenericConstraint>();
//		if(sList!=null)
//		{
//			for(String s : sList)
//			{
//				if(s.contains("cableLength"))
//				{
//					for(GenericConstraint genCon:genConList)
//					{
//						if(genCon.getKind().compareTo(GenericConstraintKind.CABLE_LENGTH)==0)
//						{
//							returnList.add(genConList.get(genConList.indexOf(genCon)));	
//						}
//					}
//				}
//				else if(s.contains("developmentCost"))
//				{
//					for(GenericConstraint genCon:genConList)
//					{
//						if(genCon.getKind().compareTo(GenericConstraintKind.DEVELOPMENT_COST)==0)
//						{
//							returnList.add(genConList.get(genConList.indexOf(genCon)));	
//						}
//					}
//				}
////				else if(s.contains("FUNCTION_ALLOCATION_DIFFERENT_NODES"))
////				{
////					for(GenericConstraint genCon:genConList)
////					{
////						if(genCon.getKind().compareTo(GenericConstraintKind.FUNCTION_ALLOCATION_DIFFERENT_NODES)==0)
////						{
////							returnList.add(genConList.get(genConList.indexOf(genCon)));	
////						}
////					}
////				}
////				else if(s.contains("functionAllocationSameNode"))
////				{
////					for(GenericConstraint genCon:genConList)
////					{
////						if(genCon.getKind().compareTo(GenericConstraintKind.FUNCTION_ALLOCATION_SAME_NODE)==0)
////						{
////							returnList.add(genConList.get(genConList.indexOf(genCon)));	
////						}
////					}
////				}
////				else if(s.contains("BUS"))
////				{
////					for(GenericConstraint genCon:genConList)
////					{
////						if(genCon.getKind().compareTo(GenericConstraintKind.OTHER)==0 && genCon.getGenericConstraintValue().startsWith("BUS"))
////						{
////							returnList.add(genConList.get(genConList.indexOf(genCon)));	
////						}
////					}
////				}
////				else if(s.contains("CPU"))
////				{
////					for(GenericConstraint genCon:genConList)
////					{
////						if(genCon.getKind().compareTo(GenericConstraintKind.OTHER)==0 && genCon.getGenericConstraintValue().startsWith("CPU"))
////						{
////							returnList.add(genConList.get(genConList.indexOf(genCon)));	
////						}
////					}
////				}
//				else if(s.contains("CURRENT"))
//				{
//					for(GenericConstraint genCon:genConList)
//					{
//						if(genCon.getKind().compareTo(GenericConstraintKind.CURRENT)==0 )
//						{
//							returnList.add(genConList.get(genConList.indexOf(genCon)));	
//						}
//					}
//				}
//				else if(s.contains("pieceCost"))
//				{
//					for(GenericConstraint genCon:genConList)
//					{
//						if(genCon.getKind().compareTo(GenericConstraintKind.PIECE_COST)==0)
//						{
//							returnList.add(genConList.get(genConList.indexOf(genCon)));	
//						}
//					}
//				}
//				else if(s.contains("powerConsumption"))
//				{
//					for(GenericConstraint genCon:genConList)
//					{
//						if(genCon.getKind().compareTo(GenericConstraintKind.POWER_CONSUMPTION)==0)
//						{
//							returnList.add(genConList.get(genConList.indexOf(genCon)));	
//						}
//					}
//				}
//				else if(s.contains("powerSupplyIndependent"))
//				{
//					for(GenericConstraint genCon:genConList)
//					{
//						if(genCon.getKind().compareTo(GenericConstraintKind.POWER_SUPPLY_INDEPENDENT)==0)
//						{
//							returnList.add(genConList.get(genConList.indexOf(genCon)));	
//						}
//					}
//				}
//				else if(s.contains("spaceRedundancy"))
//				{
//					for(GenericConstraint genCon:genConList)
//					{
//						if(genCon.getKind().compareTo(GenericConstraintKind.SPACE_REDUNDANCY)==0)
//						{
//							returnList.add(genConList.get(genConList.indexOf(genCon)));	
//						}
//					}
//				}
//				else if(s.contains("standard"))
//				{
//					for(GenericConstraint genCon:genConList)
//					{
//						if(genCon.getKind().compareTo(GenericConstraintKind.STANDARD)==0)
//						{
//							returnList.add(genConList.get(genConList.indexOf(genCon)));	
//						}
//					}
//				}
//				else if(s.contains("timeRedundancy"))
//				{
//					for(GenericConstraint genCon:genConList)
//					{
//						if(genCon.getKind().compareTo(GenericConstraintKind.TIME_REDUNDANCY)==0)
//						{
//							returnList.add(genConList.get(genConList.indexOf(genCon)));	
//						}
//					}
//				}
//				else if(s.contains("weight"))
//				{
//					for(GenericConstraint genCon:genConList)
//					{
//						if(genCon.getKind().compareTo(GenericConstraintKind.WEIGHT)==0)
//						{
//							returnList.add(genConList.get(genConList.indexOf(genCon)));	
//						}
//					}
//				}
//			}
//		}
//		return returnList;
//	}
//
	/**
	 * Creates a ModeSelectionWindow that the user will use to filter modes
	 * @param selectedAnalysisAndElements - list with selected elements
	 * @throws Exception 
	 */
	public void modeSelection(List<GenericConstraint> genConList,
			Map<GenericConstraint, List<EAElement>> selectedAnalysisAndElements) throws Exception {
		selectedElements = selectedAnalysisAndElements;
//		AnalysisController.INSTANCE.startAnalysis(genConList);
		WindowController.INSTANCE.createModeSelectionWindow(modelProcessor.findModesFromMatchedElements(selectedAnalysisAndElements));
	}
	
	/**
	 * starts an analysis with modes
	 * @param modes
	 * @throws Exception 
	 */
	public void prepareModeAnalysis(Map<GenericConstraint, List<Mode>> modes) throws Exception{
		doTheAnalysis(modes);
	}
	
	/**
	 * Starts an analysis without modes
	 * @throws Exception 
	 */
	public void startAnalysis(List<GenericConstraint> genConList) throws Exception{
		doTheAnalysis(genConList);
	}

}
