package org.eclipse.eatop.volvo.linearpropertyanalyzer.models; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.GenericConstraintKind;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAStringValue;
import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPackageableElement;
import org.eclipse.eatop.eastadl21.EAValue;
import org.eclipse.eatop.eastadl21.Refine;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.Refine_refinedBy;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger.FindRef_getGenericConstraint_target;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger.FindRef_getRefine_refinedRequirement;

/** This class have methods which will traverse the model/tree and find different information. Example would be find all the RequirementsModels in the model. 
 * 
 * @author Erik
 *
 */
public class ModelProcessor { 

	private EAElement returnElement;
	private boolean elementMatched;
	private boolean typeAdded;
	//	private DefaultMutableTreeNode elementNode;

	/** This method will search the provided  EAElement and all of its children after requirements.
	 * 
	 * @param eaPackage - root element of the model
	 * @return requirementsList -the list contains all the requirements found in the provided element
	 */
	public List<Requirement> findRequirementsInModel(EAElement eaPackage)
	{
		if(eaPackage==null)
		{
			return null;
		}
		List<Requirement> requirementsList = new ArrayList<Requirement>();;
		recursiveFindRequirements(eaPackage,requirementsList);
		return requirementsList;
	}

	//Private method for findRequirementsInModel
	private void recursiveFindRequirements(EAElement eaPackage,List<Requirement> requirementsList)
	{
		if(eaPackage.eClass().toString().contains("EAPackage"))
		{
			EAPackage eap = (EAPackage) eaPackage;
			if(eap.getSubPackage()!=null && eap.getSubPackage().size()>0)
			{
				for(EAPackage p: eap.getSubPackage())
				{
					recursiveFindRequirements(p,requirementsList);
				}
			}
			if(eap.getElement()!=null && eap.getElement().size()>0)
			{
				for(EAPackageableElement pE : eap.getElement())
				{
					if(pE.eClass().toString().contains("RequirementsModel"))
					{
						RequirementsModel set = (RequirementsModel) pE;
						requirementsList.addAll(set.getRequirement());
					}
				}
			}
		}
		else
		{
			//inte ett paket
		}
	}

	/** This method will take a RequirementsModel as parameter and find all the requirements in it. 
	 *
	 * @param model - the RequirementsModel that will be used to search for requirements.
	 * @return requirementsInReqModelList - list containing all the requirements found in the model
	 */
	public List<Requirement> findRequirementsFromReqModel(RequirementsModel model)
	{
		if(model==null)
		{
			return null;
		}
		List<Requirement> requirementsInReqModelList = new ArrayList<Requirement>();
		recursiveRequirementsFromReqModel(model,requirementsInReqModelList);
		return requirementsInReqModelList;
	}
	//private method to be used by findRequirementsFromReqModel
	private void recursiveRequirementsFromReqModel(RequirementsModel model,List<Requirement> requirementsInReqModelList)
	{
		if(model.getRequirement()!=null)
		{
			if(model.getRequirement().size()>0)
			{
				for(Requirement reqSpec: model.getRequirement())
				{
					//This may not be possible
					Requirement req = (Requirement) reqSpec;
					requirementsInReqModelList.add(req);
				}
			}
		}
	}

//	/** This method will find all the ModeGroup in the provided EAElement
//	 * 
//	 * @param eaElement 
//	 * @return a list with all the ModeGroup located in the model/EAElement
//	 */
//	public List<ModeGroup> findModeGroups(EAElement eaElement)
//	{
//		if(eaElement==null)
//		{
//			return null;
//		}
//		List<ModeGroup> requirementsModelsList = new ArrayList<ModeGroup>();
//		recursiveFindModeGroups(eaElement,requirementsModelsList);
//		return requirementsModelsList;
//	}
//	//private method to be used by findModeGroups
//	private void recursiveFindModeGroups(EAElement eaElement,List<ModeGroup> requirementsModelsList)
//	{
//		if(eaElement.eClass().toString().contains("EAPackage"))
//		{
//			EAPackage eap = (EAPackage) eaElement;
//			if(eap.getSubPackage()!=null && eap.getSubPackage().size()>0)
//			{
//				for(EAPackage p: eap.getSubPackage())
//				{
//					//recursive call for the sub-package
//					recursiveFindModeGroups(p,requirementsModelsList);
//				}
//			}
//			if(eap.getElement()!=null && eap.getElement().size()>0)
//			{
//				for(EAPackageableElement pE : eap.getElement())
//				{
//					if(pE.eClass().toString().contains("ModeGroup"))
//					{
//						requirementsModelsList.add((ModeGroup) pE);
//					}
//				}
//			}
//		}
//		else
//		{
//			//inte ett paket
//		}
//	}
//
//	/** This method will take a ModeGroup as parameter and find all the modes in it. 
//	 *
//	 * @param modeGroup - the ModeGroup that will be used to search for modes.
//	 * @return modesInModeGroup - list containing all the modes found in the model
//	 */
//	public List<Mode> findModesFromModeGroup(ModeGroup modeGroup)
//	{
//		if(modeGroup==null)
//		{
//			return null;
//		}
//		List<Mode> modesInModeGroup = new ArrayList<Mode>();
//		recursiveModesFromModeGroup(modeGroup,modesInModeGroup);
//		return modesInModeGroup;
//	}
//	//private method to be used by findModesFromModeGroup
//	private void recursiveModesFromModeGroup(ModeGroup modeGroup,List<Mode> requirementsInReqModelList)
//	{
//		if(modeGroup.getMode()!=null)
//		{
//			if(modeGroup.getMode().size()>0)
//			{
//				for(Mode reqSpec: modeGroup.getMode())
//				{
//					//This may not be possible
//					Mode req = (Mode) reqSpec;
//					requirementsInReqModelList.add(req);
//				}
//			}
//		}
//	}
//	
//	/**
//	 * This method will find RequirementModels in the EAElement provided
//	 * @param eaElement - the element that will be searched for RequirementModels
//	 * @return List with all the found RequirementModels
//	 */
	public List<RequirementsModel> findRequirementModels(EAElement eaElement)
	{
		if(eaElement==null)
		{
			return null;
		}
		List<RequirementsModel> requirementsModelsList = new ArrayList<RequirementsModel>();
		recursiveFindRequirementModels(eaElement,requirementsModelsList);
		return requirementsModelsList;
	}
	//private method to be used by findRequirementModels
	private void recursiveFindRequirementModels(EAElement eaElement,List<RequirementsModel> requirementsModelsList)
	{
		if(eaElement.eClass().toString().contains("EAPackage"))
		{
			EAPackage eap = (EAPackage) eaElement;
			if(eap.getSubPackage()!=null && eap.getSubPackage().size()>0)
			{
				for(EAPackage p: eap.getSubPackage())
				{
					//recursive call for the sub-package
					recursiveFindRequirementModels(p,requirementsModelsList);
				}
			}
			if(eap.getElement()!=null && eap.getElement().size()>0)
			{
				for(EAPackageableElement pE : eap.getElement())
				{
					if(pE.eClass().toString().contains("RequirementsModel"))
					{
						requirementsModelsList.add((RequirementsModel) pE);
					}
				}
			}
		}
		else
		{
			//inte ett paket
		}
	}

//	/** This method will take the EAElement and search for all the GenericConstraintSets in the element and its children.
//	 * 
//	 * @param eaElement - the root element in the model that will be searched
//	 * @return a list with all the GenericConstraintSets found in the model
//	 */
//	public List<GenericConstraintSet> findGenericConstraintSets(EAElement eaElement)
//	{
//		if(eaElement==null)
//		{
//			return null;
//		}
//		ArrayList<GenericConstraintSet> genericConstraintSetList = new ArrayList<GenericConstraintSet>();
//		recursiveFindGenericConstraintSets(eaElement,genericConstraintSetList);
//		return genericConstraintSetList;
//	}
//
//	//private method to be used by findGenericConstraintSets
//	private void recursiveFindGenericConstraintSets(EAElement eaElement,ArrayList<GenericConstraintSet> genericConstraintSetList)
//	{
//		if(eaElement.eClass().toString().contains("EAPackage"))
//		{
//			EAPackage eap = (EAPackage) eaElement;
//			if(eap.getSubPackage()!=null && eap.getSubPackage().size()>0)
//			{
//				for(EAPackage p: eap.getSubPackage())
//				{
//					recursiveFindGenericConstraintSets(p,genericConstraintSetList);
//				}
//			}
//			if(eap.getElement()!=null)
//			{
//				for(EAPackageableElement pE : eap.getElement())
//				{
//					if(pE.eClass().toString().contains("GenericConstraintSet"))
//					{
//						genericConstraintSetList.add((GenericConstraintSet) pE);
//					}
//				}
//			}
//		}
//		else
//		{
//			//inte ett paket
//		}
//		return;
//	}
//
//	/** This method will search for the provided string in the EAElement/model. The provided string 
//	 * should be: "element.toString()"
//	 * 
//	 * @param eaElement
//	 * @param classString
//	 * @return
//	 */
//	public EAElement findElementFromName(EAElement eaElement,String classString)
//	{
//		if(eaElement==null)
//		{
//			return null;
//		}
//		else if(classString != null && classString.length()<=0){
//			return null;
//		}
//		else if(classString == null){
//			return null;
//		}
//		returnElement = null;
//		recursiveFindElementFromName(eaElement,classString);
//		return returnElement;
//	}
//
//	//private method to be used by findElementFromName
//	private void recursiveFindElementFromName(EAElement eaElement,String classString)
//	{
//		EAElement eaElem = eaElement;
//		if(classString.contains("(name: " + eaElem.getShortName() + ")"))
//		{
//			returnElement = eaElem;
//
//		}
//		if(eaElement.eClass().toString().contains("EAPackage"))
//		{
//			EAPackage eap = (EAPackage) eaElement;
//			if(eap.getSubPackage()!=null)
//			{
//				for(EAPackage p: eap.getSubPackage())
//				{
//					//recursive call for the sub-package
//					recursiveFindElementFromName(p,classString);
//				}
//			}
//			if(eap.getElement()!=null)
//			{
//				for(EAPackageableElement pE : eap.getElement())
//				{
//					/******************************************************************/
//					//Here starts 3 SystemModeling
//					/******************************************************************/
//					if(pE.eClass().toString().contains("AnalysisLevel"))
//					{
//						AnalysisLevel analysisLevel = (AnalysisLevel) pE;
//						if(classString.contains("(name: " + analysisLevel.getShortName() + ")"))
//						{
//							returnElement = analysisLevel;
//
//						}
//						if(analysisLevel.getOwnedRelationship()!=null)
//						{
//							for(Relationship relationship:analysisLevel.getOwnedRelationship())
//							{
//								if(classString.contains("(name: " + relationship.getShortName()  + ")"))
//								{
//									returnElement = relationship;
//
//								}
//							}
//						}
//						if(analysisLevel.getTraceableSpecification()!=null)
//						{
//							for(TraceableSpecification traceableSpecification:analysisLevel.getTraceableSpecification())
//							{
//								if(classString.contains("(name: " + traceableSpecification.getShortName()  + ")"))
//								{
//									returnElement = traceableSpecification;
//
//								}
//							}
//						}
//
//					}
//					else if(pE.eClass().toString().contains("GenericConstraintSet"))
//					{
//						GenericConstraintSet genericConstraintSet = (GenericConstraintSet) pE;
//						if(classString.contains("(name: " + genericConstraintSet.getShortName()  + ")"))
//						{
//							returnElement = genericConstraintSet;
//
//						}
//						if(genericConstraintSet.getGenericConstraint()!=null)
//						{
//							for(GenericConstraint genericConstraint:genericConstraintSet.getGenericConstraint())
//							{
//								if(classString.contains("(name: " + genericConstraint.getShortName()  + ")"))
//								{
//									returnElement = genericConstraint;
//
//								}
//							}
//						}
//					}
//					else if(pE.eClass().toString().contains("DesignLevel"))
//					{
//						DesignLevel designLevel = (DesignLevel) pE;
//						if(classString.contains("(name: " + designLevel.getShortName()  + ")"))
//						{
//							returnElement = designLevel;
//
//						}
//
//					}
//					else if(pE.eClass().toString().contains("ImplementationLevel"))
//					{
//						ImplementationLevel implementationLevel = (ImplementationLevel)pE;
//						if(classString.contains("(name: " + implementationLevel.getShortName()  + ")"))
//						{
//							returnElement = implementationLevel;
//
//						}
//					}
//					else if(pE.eClass().toString().contains("SystemModel"))
//					{
//						SystemModel model = (SystemModel) pE;
//						if(classString.contains("(name: " + model.getShortName()  + ")"))
//						{
//							returnElement = model;
//
//						}
//						if(model.getAnalysisLevel()!=null)
//						{
//							AnalysisLevel analysisLevel = model.getAnalysisLevel();
//							//This is how it is supposed to be according to the specification
//							if(analysisLevel.getFunctionalAnalysisArchitecture()!=null)
//							{
//
//							}
//						}
//						if(model.getDesignLevel()!=null)
//						{
//						}
//						model.getAnalysisLevel();
////						model.getAtpFeature();
//						model.getDesignLevel();
//
//
//					}
//					else if(pE.eClass().toString().contains("VehicleLevel"))
//					{
//						VehicleLevel vehicleLevel = (VehicleLevel)pE;
//						if(classString.contains("(name: " + vehicleLevel.getShortName()  + ")"))
//						{
//							returnElement = vehicleLevel;
//
//						}
//					}
//
//					else if(pE.eClass().toString().contains("DesignFunctionType"))
//					{
//						DesignFunctionType designFunctionType = (DesignFunctionType) pE;
//						if(classString.contains("(name: " + designFunctionType.getShortName()  + ")"))
//						{
//							returnElement = designFunctionType;
//
//						}
//					}
//					else if(pE.eClass().toString().contains("VehicleLevel"))
//					{
//						VehicleLevel vehicleLevel = (VehicleLevel) pE;
//						if(classString.contains("(name: " + vehicleLevel.getShortName()  + ")"))
//						{
//							returnElement = vehicleLevel;
//
//						}
//					}					
//					/******************************************************************/
//					//Here starts 4 FeatureModeling
//					/******************************************************************/			
//					else if(pE.eClass().toString().contains("BindingTime"))
//					{
//						BindingTime bindingTime = (BindingTime)pE;
//						if(classString.contains("(name: " + bindingTime.getShortName()  + ")"))
//						{
//							returnElement = bindingTime;
//
//						}
//					}
//					/*
//					 * BindingTimeKind enum
//					 */
//					else if(pE.eClass().toString().contains("Feature"))
//					{
//						Feature feature = (Feature)pE;
//						if(classString.contains("(name: " + feature.getShortName()  + ")"))
//						{
//							returnElement = feature;
//
//						}
//					}
//					else if(pE.eClass().toString().contains("FeatureConstraint"))
//					{
//						FeatureConstraint featureConstraint = (FeatureConstraint)pE;
//						if(classString.contains("(name: " + featureConstraint.getShortName()  + ")"))
//						{
//							returnElement = featureConstraint;
//
//						}
//					}
//					else if(pE.eClass().toString().contains("FeatureGroup"))
//					{
//						FeatureGroup featureGroup = (FeatureGroup)pE;
//						if(classString.contains("(name: " + featureGroup.getShortName()  + ")"))
//						{
//							returnElement = featureGroup;
//
//						}
//					}
//					else if(pE.eClass().toString().contains("FeatureModel"))
//					{
//						FeatureModel featureModel = (FeatureModel)pE;
//						if(classString.contains("(name: " + featureModel.getShortName()  + ")"))
//						{
//							returnElement = featureModel;
//
//						}
//					}
//					else if(pE.eClass().toString().contains("FeatureTreeNode"))
//					{
//						FeatureTreeNode featureTreeNode = (FeatureTreeNode)pE;
//						if(classString.contains("(name: " + featureTreeNode.getShortName()  + ")"))
//						{
//							returnElement = featureTreeNode;
//
//						}
//					}
//					/*
//					 * VariabilityDependencyKind enum
//					 */
//					/******************************************************************/
//					//Here starts 5 VehicleFeatureModeling
//					/******************************************************************/	
//					else if(pE.eClass().toString().contains("DeviationAttributeSet"))
//					{
//						DeviationAttributeSet deviationAttributeSet = (DeviationAttributeSet)pE;
//						if(classString.contains("(name: " + deviationAttributeSet.getShortName()  + ")"))
//						{
//							returnElement = deviationAttributeSet;
//
//						}
//					}
//					/*
//					 * DeviationPermissionKind enum
//					 */
//					else if(pE.eClass().toString().contains("VehicleFeature"))
//					{
//						VehicleFeature vehicleFeature = (VehicleFeature)pE;
//						if(classString.contains("(name: " + vehicleFeature.getShortName()  + ")"))
//						{
//							returnElement = vehicleFeature;
//
//						}
//					}
//					/****************************************************************/
//					//Here starts the 6 FunctionModeling types:
//					/****************************************************************/			
//					else if(pE.eClass().toString().contains("AllocateableElement"))
//					{
//
//					}
//					else if(pE.eClass().toString().contains("Allocation"))
//					{
//
//					}
//					else if(pE.eClass().toString().contains("AnalysisFunctionPrototype"))
//					{
//
//					}
//
//					else if(pE.eClass().toString().contains("AnalysisFunctionType"))
//					{
//						AnalysisFunctionType functionType = (AnalysisFunctionType)pE;
//						if(classString.contains("(name: " + functionType.getShortName()  + ")"))
//						{
//							returnElement = functionType;
//
//						}
//						if(functionType.getPort()!=null)
//						{
//							for(FunctionPort functionPort: functionType.getPort())
//							{
//								if(classString.contains("(name: " + functionPort.getShortName()  + ")"))
//								{
//									returnElement = functionPort;
//
//								}
//							}
//						}
//						if(functionType.getConnector()!=null)
//						{
//							for( FunctionConnector connector: functionType.getConnector())
//							{
//								if(classString.contains("(name: " + connector.getShortName()  + ")"))
//								{
//									returnElement = connector;
//
//								}
//							}
//						}
//						if(functionType.getPart()!=null)
//						{
//							for(AnalysisFunctionPrototype analysisFunctionPrototype: functionType.getPart())
//							{
//								if(classString.contains("(name: " + analysisFunctionPrototype.getShortName()  + ")"))
//								{
//									returnElement = analysisFunctionPrototype;
//
//								}
//							}
//						}
//					}
//					else if(pE.eClass().toString().contains("BasicSoftwareFunctionType"))
//					{
//						BasicSoftwareFunctionType basicSoftwareFunctionType = (BasicSoftwareFunctionType)pE;
//						if(classString.contains("(name: " + basicSoftwareFunctionType.getShortName()  + ")"))
//						{
//							returnElement = basicSoftwareFunctionType;
//
//						}
//					}
//					else if(pE.eClass().toString().contains("DesignFunctionPrototype"))
//					{
//						DesignFunctionPrototype designFunctionPrototype = (DesignFunctionPrototype)pE;
//						if(classString.contains("(name: " + designFunctionPrototype.getShortName()  + ")"))
//						{
//							returnElement = designFunctionPrototype;
//
//						}
//					}
//					else if(pE.eClass().toString().contains("DesignFunctionType"))
//					{
//						DesignFunctionType designFunctionType = (DesignFunctionType)pE;
//						if(classString.contains("(name: " + designFunctionType.getShortName()  + ")"))
//						{
//							returnElement = designFunctionType;
//
//						}
//					}
//					else if(pE.eClass().toString().contains("FunctionalDevice"))
//					{
//						FunctionalDevice functionType = (FunctionalDevice)pE;
//						if(classString.contains("(name: " + functionType.getShortName()  + ")"))
//						{
//							returnElement = functionType;
//
//						}
//					}
//					
//					/******************************************************************/
//					//Here starts 7 HardwareModeling
//					/******************************************************************/
////					else if(pE.eClass().toString().contains("Actuator"))
////					{
////						Actuator actuator = (Actuator) pE;
////						if(classString.contains("(name: " + actuator.getShortName()  + ")"))
////						{
////
////							returnElement = actuator;
////							return;
////						}
////						if(actuator.getBus()!=null)
////						{
////							//This will get all the loop over all the buses
////							for(LogicalBus bus:actuator.getBus())
////							{
////								if(classString.contains("(name: " + bus.getShortName()  + ")"))
////								{
////									returnElement = bus;
////
////								}
////							}
////						}
////						if(actuator.getPart()!=null)
////						{
////							//This will get all the loop over all the prototypes
////							for(HardwareComponentPrototype hardwarePrototype: actuator.getPart())
////							{
////								if(classString.contains("(name: " + hardwarePrototype.getShortName()  + ")"))
////								{
////									returnElement = hardwarePrototype;
////
////								}
////							}
////						}
////						if(actuator.getConnector()!=null)
////						{
////							//This will get all the loop over all the ports
////							for(HardwareConnector connector:actuator.getConnector())
////							{
////								if(classString.contains("(name: " + connector.getShortName()  + ")"))
////								{
////									returnElement = connector;
////
////								}
////							}
////
////						}
////						if(actuator.getPortGroup()!=null)
////						{
////							//This will get all the loop over all the PortGroups
////							for(HardwarePinGroup hardwarePinGroup:actuator.getPortGroup())
////							{
////								if(classString.contains("(name: " + hardwarePinGroup.getShortName()  + ")"))
////								{
////									returnElement = hardwarePinGroup;
////
////								}
////							}
////
////						}
////					}
////					else if(pE.eClass().toString().contains("AllocationTarget"))
////					{
////						AllocationTarget target = (AllocationTarget) pE;
////						if(classString.contains("(name: " + target.getShortName()  + ")"))
////						{
////							returnElement = target;
////
////						}
////					}
////					else if(pE.eClass().toString().contains("CommunicationHardwarePin"))
////					{
////						CommunicationHardwarePin hardwarePin = (CommunicationHardwarePin) pE;
////						if(classString.contains("(name: " + hardwarePin.getShortName()  + ")"))
////						{
////							returnElement = hardwarePin;
////
////						}
////					}
////					else if(pE.eClass().toString().contains("HardwareComponentPrototype"))
////					{
////						HardwareComponentPrototype componenetPrototype = (HardwareComponentPrototype) pE;
////						if(classString.contains("(name: " + componenetPrototype.getShortName()  + ")"))
////						{
////							returnElement = componenetPrototype;
////
////						}
////					}
////					else if(pE.eClass().toString().contains("HardwareComponentType"))
////					{
////						HardwareComponentType hardwareType = (HardwareComponentType) pE;
////						if(classString.contains("(name: " + hardwareType.getShortName()  + ")"))
////						{
////							returnElement = hardwareType;
////
////						}
////						if(hardwareType.getBus()!=null)
////						{
////							//This will get all the loop over all the buses
////							for(LogicalBus bus:hardwareType.getBus())
////							{
////								if(classString.contains("(name: " + bus.getShortName()  + ")"))
////								{
////									returnElement = bus;
////
////								}
////							}
////						}
////						if(hardwareType.getPart()!=null)
////						{
////							//This will get all the loop over all the prototypes
////							for(HardwareComponentPrototype hardwarePrototype: hardwareType.getPart())
////							{
////								if(classString.contains("(name: " + hardwarePrototype.getShortName()  + ")"))
////								{
////									returnElement = hardwarePrototype;
////
////								}
////							}
////						}
////						if(hardwareType.getConnector()!=null)
////						{
////							//This will get all the loop over all the ports
////							for(HardwareConnector connector:hardwareType.getConnector())
////							{
////								if(classString.contains("(name: " + connector.getShortName()  + ")"))
////								{
////									returnElement = connector;
////
////								}
////							}
////
////						}
////						if(hardwareType.getPortGroup()!=null)
////						{
////							//This will get all the loop over all the PortGroups
////							for(HardwarePinGroup hardwarePinGroup:hardwareType.getPortGroup())
////							{
////								if(classString.contains("(name: " + hardwarePinGroup.getShortName()  + ")"))
////								{
////									returnElement = hardwarePinGroup;
////
////								}
////							}
////
////						}
////					}
////					else if(pE.eClass().toString().contains("HardwareConnector"))
////					{
////						HardwareConnector connector = (HardwareConnector) pE;
////						if(classString.contains("(name: " + connector.getShortName()  + ")"))
////						{
////							returnElement = connector;
////
////						}
////						if(connector.getPort()!=null)
////						{
////							for(HardwareConnector_port port : connector.getPort())
////							{
////								if(port.getHardwarePin()!=null)
////								{
////									if(classString.contains("(name: " + port.getHardwarePin().getShortName()  + ")"))
////									{
////										returnElement = port.getHardwarePin();
////
////									}
////								}
////
////							}
////						}
////					}
////					else if(pE.eClass().toString().contains("HardwarePin"))
////					{
////						HardwarePin hardwarePin = (HardwarePin) pE;
////						if(classString.contains("(name: " + hardwarePin.getShortName()  + ")"))
////						{
////							returnElement = hardwarePin;
////
////						}
////					}
////					else if(pE.eClass().toString().contains("HardwarePinGroup"))
////					{
////						HardwarePinGroup hardwarePinGroup = (HardwarePinGroup) pE;
////						if(classString.contains("(name: " + hardwarePinGroup.getShortName()  + ")"))
////						{
////							returnElement = hardwarePinGroup;
////
////						}
////						if(hardwarePinGroup.getPortGroup()!=null)
////						{
////							
////						}
////						if(hardwarePinGroup.getPort()!=null)
////						{
////							for(HardwarePin hardwarePin:hardwarePinGroup.getPort())
////							{
////								if(classString.contains("(name: " + hardwarePin.getShortName()  + ")"))
////								{
////									returnElement = hardwarePin;
////
////								}
////							}
////						}
////					}
////					else if(pE.eClass().toString().contains("IOHardwarePin"))
////					{
////						IOHardwarePin hardwarePin = (IOHardwarePin) pE;
////						if(classString.contains("(name: " + hardwarePin.getShortName()  + ")"))
////						{
////							returnElement = hardwarePin;
////
////						}
////					}
////					else if(pE.eClass().toString().contains("LogicalBus"))
////					{
////						LogicalBus logicalBus = (LogicalBus) pE;
////						if(classString.contains("(name: " + logicalBus.getShortName()  + ")"))
////						{
////							returnElement = logicalBus;
////
////						}
////						if(logicalBus.getWire()!=null)
////						{
////							for(HardwareConnector connector:logicalBus.getWire())
////							{
////								if(classString.contains("(name: " + connector.getShortName()  + ")"))
////								{
////									returnElement = connector;
////
////								}
////							}
////						}
////					}
////					else if(pE.eClass().toString().contains("Node"))
////					{
////						HardwareComponentType node = (HardwareComponentType) pE;
////						if(classString.contains("(name: " + node.getShortName()  + ")"))
////						{
////							returnElement = node;
////
////						}
////						if(node.getBus()!=null)
////						{
////							//This will get all the loop over all the buses
////							for(LogicalBus bus:node.getBus())
////							{
////								if(classString.contains("(name: " + bus.getShortName()  + ")"))
////								{
////									returnElement = bus;
////
////								}
////							}
////						}
////						if(node.getPart()!=null)
////						{
////							//This will get all the loop over all the prototypes
////							for(HardwareComponentPrototype hardwarePrototype: node.getPart())
////							{
////								if(classString.contains("(name: " + hardwarePrototype.getShortName()  + ")"))
////								{
////									returnElement = hardwarePrototype;
////
////								}
////							}
////						}
////						if(node.getConnector()!=null)
////						{
////							//This will get all the loop over all the ports
////							for(HardwareConnector connector:node.getConnector())
////							{
////								if(classString.contains("(name: " + connector.getShortName()  + ")"))
////								{
////									returnElement = connector;
////
////								}
////							}
////
////						}
////						if(node.getPortGroup()!=null)
////						{
////							//This will get all the loop over all the PortGroups
////							for(HardwarePinGroup hardwarePinGroup:node.getPortGroup())
////							{
////								if(classString.contains("(name: " + hardwarePinGroup.getShortName()  + ")"))
////								{
////									returnElement = hardwarePinGroup;
////
////								}
////							}
////
////						}
////					}
////					else if(pE.eClass().toString().contains("PowerHardwarePin"))
////					{
////						PowerHardwarePin hardwarePin = (PowerHardwarePin) pE;
////						if(classString.contains("(name: " + hardwarePin.getShortName()  + ")"))
////						{
////							returnElement = hardwarePin;
////
////						}
////					}
////					else if(pE.eClass().toString().contains("PowerSupply"))
////					{
////						HardwareComponentType powerSupply = (HardwareComponentType) pE;
////						if(classString.contains("(name: " + powerSupply.getShortName()  + ")"))
////						{
////							returnElement = powerSupply;
////
////						}
////						if(powerSupply.getBus()!=null)
////						{
////							//This will get all the loop over all the buses
////							for(LogicalBus bus:powerSupply.getBus())
////							{
////								if(classString.contains("(name: " + bus.getShortName()  + ")"))
////								{
////									returnElement = bus;
////
////								}
////							}
////						}
////						if(powerSupply.getPart()!=null)
////						{
////							//This will get all the loop over all the prototypes
////							for(HardwareComponentPrototype hardwarePrototype: powerSupply.getPart())
////							{
////								if(classString.contains("(name: " + hardwarePrototype.getShortName()  + ")"))
////								{
////									returnElement = hardwarePrototype;
////
////								}
////							}
////						}
////						if(powerSupply.getConnector()!=null)
////						{
////							//This will get all the loop over all the ports
////							for(HardwareConnector connector:powerSupply.getConnector())
////							{
////								if(classString.contains("(name: " + connector.getShortName()  + ")"))
////								{
////									returnElement = connector;
////
////								}
////							}
////
////						}
////						if(powerSupply.getPortGroup()!=null)
////						{
////							//This will get all the loop over all the PortGroups
////							for(HardwarePinGroup hardwarePinGroup:powerSupply.getPortGroup())
////							{
////								if(classString.contains("(name: " + hardwarePinGroup.getShortName()  + ")"))
////								{
////									returnElement = hardwarePinGroup;
////
////								}
////							}
////
////						}
////					}
////					else if(pE.eClass().toString().contains("Sensor"))
////					{
////						HardwareComponentType sensor = (HardwareComponentType) pE;
////						if(classString.contains("(name: " + sensor.getShortName()  + ")"))
////						{
////							returnElement = sensor;
////
////						}
////						if(sensor.getBus()!=null)
////						{
////							//This will get all the loop over all the buses
////							for(LogicalBus bus:sensor.getBus())
////							{
////								if(classString.contains("(name: " + bus.getShortName()  + ")"))
////								{
////									returnElement = bus;
////
////								}
////							}
////						}
////						if(sensor.getPart()!=null)
////						{
////							//This will get all the loop over all the prototypes
////							for(HardwareComponentPrototype hardwarePrototype: sensor.getPart())
////							{
////								if(classString.contains("(name: " + hardwarePrototype.getShortName()  + ")"))
////								{
////									returnElement = hardwarePrototype;
////
////								}
////							}
////						}
////						if(sensor.getConnector()!=null)
////						{
////							//This will get all the loop over all the ports
////							for(HardwareConnector connector:sensor.getConnector())
////							{
////								if(classString.contains("(name: " + connector.getShortName()  + ")"))
////								{
////									returnElement = connector;
////
////								}
////							}
////
////						}
////						if(sensor.getPortGroup()!=null)
////						{
////							//This will get all the loop over all the PortGroups
////							for(HardwarePinGroup portGroup:sensor.getPortGroup())
////							{
////								if(classString.contains("(name: " + portGroup.getShortName()  + ")"))
////								{
////									returnElement = portGroup;
////
////								}
////							}
////
////						}
////					}
//					/******************************************************************/
//					//Here starts 8 Environment
//					/******************************************************************/	
//					/******************************************************************/
//					//Here starts 9 Behavior
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 10 Variability
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 11 Requirements 
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 12 UseCases 
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 13 VerificationValidation
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 14 Interchange
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 15 Timing
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 16 TimingConstraints
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 17 Events
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 18 Dependability
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 19 ErrorModel
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 20 SafetyConstraints
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 21 SafetyRequirement
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 22 SafetyCase
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 23 GenericConstraints
//					/******************************************************************/
//					else if(pE.eClass().toString().contains("GenericConstraint"))
//					{
//						//Must be in a GenericConstraintSet
//
//					}
//					/* GenericConstraintKind enum */
//
//					else if(pE.eClass().toString().contains("TakeRateConstraint"))
//					{
//						TakeRateConstraint takeRateConstraint = (TakeRateConstraint) pE;
//						if(classString.contains("(name: " + takeRateConstraint.getShortName()  + ")"))
//						{
//							returnElement = takeRateConstraint;
//
//						}
//					}
//					/******************************************************************/
//					//Here starts 24 Datatypes MIGHT NOT BE NECESSARY
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 25 Elements MIGHT NOT BE NECESSARY
//					/******************************************************************/
//					/******************************************************************/
//					//Here starts 26 UserAttributes MIGHT NOT BE NECESSARY
//					/******************************************************************/
//				}
//			}
//		}
//		else
//		{
//			//inte ett paket
//		}
//	}
//
	/** This method will find all the GenericConstraintKinds in the Requirement provided. 
	 * 
	 * @param req - the requirement that will be used for finding all of its GenericConstraintKind
	 * @return list with all of the Requirements GenericConstraintKinds
	 * @throws Exception 
	 */
	public List<GenericConstraint> findAllGenConKindsInRequirement(Requirement req) throws Exception
	{
		if(req==null)
		{
			return null;
		}
		List<GenericConstraint> genConKindsList = new ArrayList<GenericConstraint>();
		Set<GenericConstraint> setOfKinds = new HashSet<GenericConstraint>();
		List<Refine_refinedBy> result = new ArrayList<Refine_refinedBy>();
		FindRef_getRefine_refinedRequirement temp= new FindRef_getRefine_refinedRequirement();
		EList<Refine> resultRefine = temp.getRefine_refinedRequirement(req); 
		if(resultRefine!=null)
		{
			for(Refine refine: resultRefine)
			{
//				FindRef_getRefine_refinedBy_identifiable_context temp2 = new FindRef_getRefine_refinedBy_identifiable_context();
//				EList<Refine_refinedBy> result = temp2.getRefine_refinedBy_identifiable_context(refine);
				if (refine.getRefinedBy() != null)
				{
					result.addAll(refine.getRefinedBy());
				}
				

			}
			if(result!=null)
			{
				for(Refine_refinedBy refineBy:result)
				{
					if(refineBy.getIdentifiable_target()!=null)
					{
						setOfKinds.add(((GenericConstraint)refineBy.getIdentifiable_target()));
					}
				}
			}
			else
			{
				//TODO: error message or something
			}
		}
		else
		{
			//TODO: error message or something
		}
		genConKindsList.addAll(setOfKinds);
		return genConKindsList;
	}
//
//	private boolean checkElementForGenConKindMatch(EAElement pE, GenericConstraint genericConstraint) throws Exception
//	{
//		FindRef_getGenericConstraint_target temp= new FindRef_getGenericConstraint_target();
//		EList<GenericConstraint> resultGenericConstraint = temp.getGenericConstraint_target(pE); 
//		if(resultGenericConstraint!=null)
//		{
//			for(GenericConstraint gc : resultGenericConstraint)
//			{
//				if(gc.getKind().compareTo(genericConstraint.getKind())==0)
//				{
//					if(gc.getKind().compareTo(GenericConstraintKind.OTHER)==0)
//					{
//						EAValue temps= genericConstraint.getValue();
//						String value =null;
//						if (temps instanceof EAStringValue)
//							value = ((EAStringValue) temps).getValue();
//						Scanner scanner = new Scanner(value);
//						String type ="";
//						if(scanner.hasNext())
//						{
//							type = scanner.next();
//							EAValue temps2= gc.getValue();
//							String value2 =null;
//							if (temps2 instanceof EAStringValue)
//								value2 = ((EAStringValue) temps2).getValue();
//							
//							if(value2.startsWith(type))
//							{
//								return true;
//							}
//						}
//					}
//					else
//					{
//						return true;
//					}
//
//				}
//			}
//		}
//		return false;
//	}
//
	public Map<GenericConstraint, List<Mode>> findModesFromMatchedElements(Map<GenericConstraint, List<EAElement>> mapList) throws Exception
	{
		Map<GenericConstraint, List<Mode>> resultMap = new HashMap<GenericConstraint, List<Mode>>();
		Iterator<GenericConstraint> it = mapList.keySet().iterator();
		while(it.hasNext())
		{
			GenericConstraint genCon = it.next();
			List<EAElement> matchedElementList = mapList.get(genCon);
			List<Mode> foundModes = new ArrayList<Mode>();
//			if(genCon.getKind().compareTo(GenericConstraintKind.OTHER)==0)
//			{
//				EAValue temps= genCon.getValue();
//				String value =null;
//				if (temps instanceof EAStringValue)
//					value = ((EAStringValue) temps).getValue();
//				if(value.startsWith("CPU"))
//				{
//					recuresiveModesFromNodeElement(matchedElementList,foundModes,genCon);
//				}
//				else
//				{
//					recursiveModesFromMatchedElements(matchedElementList,foundModes,genCon);
//				}
//			}
//			else
			{
				recursiveModesFromMatchedElements(matchedElementList,foundModes,genCon);
			}
			resultMap.put(genCon, foundModes);
		}
		return resultMap;
	}

//	private List<Mode> recuresiveModesFromNodeElement(List<EAElement> matchedElementList, List<Mode> modeList, GenericConstraint correctGenCon)
//	{
//		for(EAElement element:matchedElementList)
//		if(element instanceof Node)
//		{
//			Node node = (Node) element;
//			for(HardwareComponentPrototype prototype : node.getPart())
//			{
//				//I hate this solution... 
//				//No, you actually love it!
////				for(HardwareComponentPrototype hct : prototype.getHardwareComponentType_part().getHardwareComponentPrototype_type())
////				{
////					for(FunctionAllocation_target fat : hct.getFunctionAllocation_target_allocationTarget())
////					{
////						AllocateableElement allocateableElement = fat.getFunctionAllocation_target().getAllocatedElement().getAllocateableElement();
////						if(allocateableElement instanceof DesignFunctionPrototype)
////						{
////							DesignFunctionPrototype function = (DesignFunctionPrototype)allocateableElement;
////							//Execution time constraints
////							List<ExecutionTimeConstraint> executionTime = function.getExecutionTimeConstraint_targetDesignFunctionPrototype();
////							for(ExecutionTimeConstraint constraint : executionTime)
////							{
////								for(Mode mode : constraint.getMode())
////								{
////									for(GenericConstraint genC:mode.getGenericConstraint_mode())
////									{
////										if(genC.getKind().compareTo(GenericConstraintKind.OTHER)==0)
////										{
////											Scanner scanner = new Scanner(correctGenCon.getGenericConstraintValue());
////											String type ="";
////											if(scanner.hasNext())
////											{
////												type = scanner.next();
////												if(genC.getGenericConstraintValue().startsWith(type))
////												{
////													modeList.add(mode);
////												}
////											}
////										}
////
////										else
////										{
////											if(genC.getKind().compareTo(correctGenCon.getKind())==0)
////											{
////											modeList.add(mode);
////											}
////										}
////									}
////								}
////							}
////						}
////					}
////				}
//				System.out.print("Removed by Gongpei in ModelProcessor line 1372.\n");
//			}
//
//		}
//		return null;
//	}
//
	private List<Mode> recursiveModesFromMatchedElements(List<EAElement> matchedElementList, List<Mode> modeList, GenericConstraint correctGenCon) throws Exception
	{
		if(matchedElementList!=null)
		{
			for(EAElement element : matchedElementList)
			{
				FindRef_getGenericConstraint_target temp= new FindRef_getGenericConstraint_target();
				EList<GenericConstraint> resultGenericConstraint = temp.getGenericConstraint_target(element); 
				if(resultGenericConstraint!=null)
				{
					for(GenericConstraint genCon:resultGenericConstraint)
					{
						if(genCon.getKind().compareTo(correctGenCon.getKind())==0)
						{
//							if(genCon.getKind().compareTo(GenericConstraintKind.OTHER)==0)
//							{
//								EAValue temps= correctGenCon.getValue();
//								String value =null;
//								if (temps instanceof EAStringValue)
//									value = ((EAStringValue) temps).getValue();
//								
//								Scanner scanner = new Scanner(value);
//								String type ="";
//								if(scanner.hasNext())
//								{
//									type = scanner.next();
//									EAValue temps2= genCon.getValue();
//									String value2 =null;
//									if (temps2 instanceof EAStringValue)
//										value2 = ((EAStringValue) temps2).getValue();
//									
//									if(value2.startsWith(type))
//									{
//										if(genCon.getMode()!=null)
//										{
//											for(Mode mode : genCon.getMode())
//											{
//												if(modeList.contains(mode)==false)
//												{
//													modeList.add(mode);
//												}
//											}
//										}
//									}
//								}
//							}
//
//							else
							{
								if(genCon.getMode()!=null)
								{
									for(Mode mode : genCon.getMode())

										if(modeList.contains(mode)==false)
										{
											modeList.add(mode);
										}
								}
							}
						}
					}


				}
			}

		}
		return modeList;
	}
//
//	/** This method will find all the elements that could be used for an analyze and is linked to the requirement provided.
//	 *  The method will search for elements that have a genericconstraint linked to it and match the GenericConstraintKind
//	 * 
//	 * * In the case below Requirement1 is provided and the method will then return the list {Element1, Element3}
//	 * 
//	 *  Package
//	 * 		Element1
//	 * 			Element3
//	 * 		Element2
//	 * 		RequirementsModel
//	 * 			Requirement1 --satisfy link to Element1->Element1
//	 * 
//	 * @param satisfyTarget - The requirement that will be used for searching after elements
//	 * @param constraintKind - The elements have to be linked to a GenericConstraint that has this GenericConstraintKind
//	 * @throws Exception 
//	 */
//	public SearchResult findAllElementsBySatisfy(EAElement satisfyTarget, List<GenericConstraint> constraintList) throws Exception
//	{
//		if(satisfyTarget==null || constraintList==null)
//		{
//			return null;
//		}
//		if(constraintList.size()==0)
//		{
//			return null;
//		}
//		Map<GenericConstraint,List<EAElement>> resultMapFindEleBySatisfy = new HashMap<GenericConstraint,List<EAElement>>();
//		Map<GenericConstraint,JTree> resultTreeMapFindEleBySatisfy = new HashMap<GenericConstraint,JTree>();
//		List<EAElement> matchedElementsList = null;
//		List<HardwareComponentType> typesWithPrototypes = null;
//		DefaultMutableTreeNode top = null;
//		if(constraintList!=null)
//		{
//			for(GenericConstraint gc:constraintList)
//			{
//				top = new DefaultMutableTreeNode(gc.getKind().toString());
//				matchedElementsList= new ArrayList<EAElement>();
//				typesWithPrototypes = new ArrayList<HardwareComponentType>();
//				recursiveFindElements(satisfyTarget,gc,matchedElementsList, top, typesWithPrototypes);
//				/*
//				during the method save types that have prototypes in a list (typesFoundList)
//				after it returns check if the types(in typesFoundList) prototypes is in the matchedElementList,
//				if at least one is don't care about the type and add text to the tree node saying that it wont affect
//				the analysis,
//				if no prototype is in the list then add the type to matchedElementList and write nothing to the tree.
//				in the end matchedElement should contain all the element that is necessary for the analysis and the tree
//				should be updated with text saying if types is in that list or not
//				 */
//				boolean found = false;
//				for(HardwareComponentType type:typesWithPrototypes)
//				{
////					for(HardwareComponentPrototype prototype : type.getHardwareComponentPrototype_type())
////					{
////						for(EAElement matchedElement:matchedElementsList)
////						{
////							if(prototype.getShortName().equalsIgnoreCase(matchedElement.getShortName())==true)
////							{								
////								found = true;
////								break;
////							}
////						}
////						if(found ==true)
////						{
////							break;
////						}
////					}
////					if(found == false)
////					{
////
////
////					}
////					else
////					{
////						Enumeration enumeration = top.breadthFirstEnumeration();
////						while(enumeration.hasMoreElements())
////						{
////							DefaultMutableTreeNode node = (DefaultMutableTreeNode)enumeration.nextElement();
////							if((node.getUserObject().toString()).equalsIgnoreCase(type.getShortName()))
////							{
////								node.setUserObject(node.getUserObject()+" there is a prototype of this type");
////							}
////						}
////					}
//					found = false;
//
//				}
//
//				JTree yourTree = new JTree(top);
//				resultMapFindEleBySatisfy.put(gc, matchedElementsList);
//				resultTreeMapFindEleBySatisfy.put(gc, yourTree);
//			}
//		}
//		SearchResult result = new SearchResult(resultMapFindEleBySatisfy, resultTreeMapFindEleBySatisfy);
//		return result;
//	}
//
//	/* Skapa map med kind och tree
//	 * Skapa ett root node typ matchedelements
//	 * skicka med det som parent till recursiveFindElements
//	 * Om först if satsen är true har vi en ny parent för resten som antingen ska skickas med till sub paket eller användas som parent till 
//	 * actuators och sånt som hittas for(EAPackageableElement pE : eap.getElement())
//	 * Steg1: när ett element hittas som ska vara med lägg till som node(kallar den elementNode) till parent
//	 * Steg2: har det hittade elementet andra element/prototyper/typer under sig lägg till dom till noden(elementNode)
//	 * 
//	 * om inte elementet på Steg1 ska med men element vid Steg2 måste en (elemenetNode) skapas
//	 */
//
//	//Method is used from FindAllElementsBySatisfy
//	private boolean recursiveFindElements(EAElement eaPackage, GenericConstraint genericConstraint,List<EAElement> matchedElementsList, DefaultMutableTreeNode parent, List<HardwareComponentType> typesWithPrototypes) throws Exception
//	{
//		boolean result = false;
//		DefaultMutableTreeNode packageNode = null;
//		//Does the package contain other packages?
//		if(eaPackage.eClass().toString().contains("EAPackage"))
//		{
//			EAPackage eap = (EAPackage) eaPackage;
//			if(eap.getSubPackage()!=null)
//			{
//				for(EAPackage p: eap.getSubPackage())
//				{
//					if(packageNode==null)
//					{
//						packageNode = new DefaultMutableTreeNode(eaPackage.getShortName());
//					}
//					boolean tempResult = recursiveFindElements(p,genericConstraint,matchedElementsList,packageNode,typesWithPrototypes);
//					if(tempResult==true && packageNode.getParent()==null)
//					{
//						parent.add(packageNode);
//						result = true;
//					}
//				}
//			}
//			if(eap.getElement()!=null && eap.getElement().size()>0)
//			{
//				for(EAPackageableElement pE : eap.getElement())
//				{
//					if(pE.eClass().toString().contains("DesignLevel"))
//					{
//					}
//					else if(pE.eClass().toString().contains("Actuator")||pE.eClass().toString().contains("Node")||pE.eClass().toString().contains("Sensor")||pE.eClass().toString().contains("HardwareComponentType"))
//					{					
//						HardwareComponentType hardwareCompType = (HardwareComponentType) pE;
//						DefaultMutableTreeNode elementNode = checkOneHWCT(
//								genericConstraint, matchedElementsList, pE,
//								hardwareCompType);
//						elementMatched = checkElementForGenConKindMatch(hardwareCompType, genericConstraint);
//						if(elementMatched == true)
//						{
//							//Noden har en genCon som matchar
//							matchedElementsList.add(hardwareCompType);
//							if(elementNode ==null)
//							{
//								elementNode = new DefaultMutableTreeNode(pE.getShortName());
//							}
//							typeAdded = true;
//						}
//						if(elementNode !=null && elementNode.getLeafCount()>0)
//						{
//							//Ett element har lagts till däför måste noden finnas med i trädet
//							if(packageNode==null)
//							{
//								packageNode = new DefaultMutableTreeNode(eaPackage.getShortName());
//							}
//							//add the actuator node to the packageNode
//							packageNode.add(elementNode);
//							if(packageNode.getParent()==null)
//							{
//								//add packageNode to parent
//								parent.add(packageNode);
//							}
//							result = true;
//						}
//						else
//						{
//							//There is no children but the type might need to be added
//							if(typeAdded==true && elementNode !=null)
//							{
//								//The type/element should be added to the tree
//								if(packageNode==null)
//								{
//									packageNode = new DefaultMutableTreeNode(eaPackage.getShortName());
//								}
//								//add the actuator node to the packageNode
//								packageNode.add(elementNode);							
//								if(packageNode.getParent()==null)
//								{
//									//add packageNode to parent
//									parent.add(packageNode);
//								}
//								result = true;
//							}
//						}
//					}
//				}
//			}
//		}
//		else
//		{
//			EAPackageableElement pE = (EAPackageableElement) eaPackage;
//
//			if(pE.eClass().toString().contains("DesignLevel"))
//			{
//			}
//			else if(pE.eClass().toString().contains("Actuator")||pE.eClass().toString().contains("Node")||pE.eClass().toString().contains("Sensor")||pE.eClass().toString().contains("HardwareComponentType"))
//			{					
//				HardwareComponentType hardwareCompType = (HardwareComponentType) pE;
//				DefaultMutableTreeNode elementNode = checkOneHWCT(
//						genericConstraint, matchedElementsList, pE,
//						hardwareCompType);
//				elementMatched = checkElementForGenConKindMatch(hardwareCompType, genericConstraint);
//				if(elementMatched == true)
//				{
//					//Noden har en genCon som matchar
//					matchedElementsList.add(hardwareCompType);
//					if(elementNode ==null)
//					{
//						elementNode = new DefaultMutableTreeNode(pE.getShortName());
//					}
//					typeAdded = true;
//				}
//				if(elementNode !=null && elementNode.getLeafCount()>0)
//				{
//					//Ett element har lagts till däför måste noden finnas med i trädet
//					if(packageNode==null)
//					{
//						packageNode = new DefaultMutableTreeNode(eaPackage.getShortName());
//					}
//					//add the actuator node to the packageNode
//					packageNode.add(elementNode);
//					if(packageNode.getParent()==null)
//					{
//						//add packageNode to parent
//						parent.add(packageNode);
//					}
//					result = true;
//				}
//				else
//				{
//					//There is no children but the type might need to be added
//					if(typeAdded==true && elementNode !=null)
//					{
//						//The type/element should be added to the tree
//						if(packageNode==null)
//						{
//							packageNode = new DefaultMutableTreeNode(eaPackage.getShortName());
//						}
//						//add the actuator node to the packageNode
//						packageNode.add(elementNode);							
//						if(packageNode.getParent()==null)
//						{
//							//add packageNode to parent
//							parent.add(packageNode);
//						}
//						result = true;
//					}
//				}
//			}
//		}
//		return result;
//	}
//
//	private DefaultMutableTreeNode checkOneHWCT(
//			GenericConstraint genericConstraint,
//			List<EAElement> matchedElementsList, EAPackageableElement pE,
//			HardwareComponentType hardwareCompType) throws Exception {
//		DefaultMutableTreeNode elementNode = null;
//		//Check after prototypes, if any is present this type is not necessary and should not be in the model
//		if(hardwareCompType.getPart()!=null)
//		{
//			for(HardwareComponentPrototype prototype:hardwareCompType.getPart())
//			{
//				elementMatched = checkElementForGenConKindMatch(prototype, genericConstraint);
//				if(elementMatched==true)
//				{
//					if(elementNode ==null)
//					{
//						elementNode = new DefaultMutableTreeNode(pE.getShortName());
//					}
//					//prototype can be used for analysis
//					elementNode.add(new DefaultMutableTreeNode(prototype.getShortName()));
//					matchedElementsList.add(prototype);
//				}
//				else
//				{
//
//					//gå till typen och kolla ifall den har en GenCon som kan användas
//					if(prototype.getType()!=null)
//					{
//						FindRef_getGenericConstraint_target temp= new FindRef_getGenericConstraint_target();
//						EList<GenericConstraint> resultGenericConstraint = temp.getGenericConstraint_target(prototype.getType()); 
//						if(resultGenericConstraint!=null)
//						{
//							if(resultGenericConstraint.size()>0)
//							{
//								//Loop over all the types GenCons
//								for(GenericConstraint genCon :resultGenericConstraint)
//								{
//									if(genCon.getKind().compareTo(genericConstraint.getKind())==0)
//									{
//										if(genCon.getKind().compareTo(GenericConstraintKind.OTHER)==0)
//										{
//											EAValue temps= genericConstraint.getValue();
//											String value =null;
//											if (temps instanceof EAStringValue)
//												value = ((EAStringValue) temps).getValue();
//											Scanner scanner = new Scanner(value);
//											String type ="";
//											if(scanner.hasNext())
//											{
//												type = scanner.next();
//												EAValue temps2= genCon.getValue();
//												String value2 =null;
//												if (temps2 instanceof EAStringValue)
//													value2 = ((EAStringValue) temps2).getValue();
//												if(value2.startsWith(type))
//												{
//													if(elementNode ==null)
//													{
//														elementNode = new DefaultMutableTreeNode(pE.getShortName());
//													}
//													//the type has a GenericConstraint that can be used
//													elementNode.add(new DefaultMutableTreeNode(prototype.getShortName()));
//													EList<GenericConstraint> resultGenericConstraint2 = temp.getGenericConstraint_target(prototype); 
//													resultGenericConstraint2.add(genCon);
//													matchedElementsList.add(prototype);
//													break;
//												}
//											}
//										}
//										else
//										{
//											if(elementNode ==null)
//											{
//												elementNode = new DefaultMutableTreeNode(pE.getShortName());
//											}
//											//the type has a GenericConstraint that can be used
//											elementNode.add(new DefaultMutableTreeNode(prototype.getShortName()));
//											EList<GenericConstraint> resultGenericConstraint2 = temp.getGenericConstraint_target(prototype); 
//											resultGenericConstraint2.add(genCon);
//											matchedElementsList.add(prototype);
//											break;
//										}
//									}
//								}
//							}
//							else
//							{
//								//No GenericCOnstraint on the type
//							}
//						}
//					}
//					else
//					{
//						//the prototype has no type //TODO: ERROR MASSAGE?
//					}
//				}
//			}
//		}
////						if(hardwareCompType.getBus()!=null)
////						{
////							//This will get all the loop over all the buses
////							for(LogicalBus bus:hardwareCompType.getBus())
////							{
////								elementMatched = checkElementForGenConKindMatch(bus, genericConstraint);
////								if(elementMatched==true)
////								{
////									if(elementNode ==null)
////									{
////										elementNode = new DefaultMutableTreeNode(pE.getShortName());
////									}
////									matchedElementsList.add(bus);
////									elementNode.add(new DefaultMutableTreeNode(bus.getShortName()));
////								}
////							}
////						}
//		if(hardwareCompType.getConnector()!=null)
//		{
//			//This will get all the loop over all the ports
//			for(HardwareConnector connector:hardwareCompType.getConnector())
//			{
//				elementMatched = checkElementForGenConKindMatch(connector, genericConstraint);
//				if(elementMatched==true)
//				{
//					if(elementNode ==null)
//					{
//						elementNode = new DefaultMutableTreeNode(pE.getShortName());
//					}
//					matchedElementsList.add(connector);
//					elementNode.add(new DefaultMutableTreeNode(connector.getShortName()));
//				}
//			}
//
//		}
//		//////////////////////////////////////////// group is strange! Yes it is!
////						if(hardwareCompType.getPortGroup()!=null)
////						{
////							//This will get all the loop over all the PortGroups
////							for(HardwarePinGroup hardwarePinGroup:hardwareCompType.getPortGroup())
////							{
////								if(hardwarePinGroup.getPort()!=null)
////								{
////									for(HardwarePin pin : hardwarePinGroup.getPort())
////									{
////										elementMatched = checkElementForGenConKindMatch(pin, genericConstraint);
////										if(elementMatched == true)
////										{
////											if(elementNode ==null)
////											{
////												elementNode = new DefaultMutableTreeNode(pE.getShortName());
////											}
////											matchedElementsList.add(pin);
////											elementNode.add(new DefaultMutableTreeNode(pin.getShortName()));
////										}
////									}
////								}
////							}
////						}
////						if(hardwareCompType.getPort()!=null)
////						{
////							for(HardwarePin pin:hardwareCompType.getPort())
////							{
////								elementMatched = checkElementForGenConKindMatch(pin, genericConstraint);
////								if(elementMatched == true)
////								{
////									if(elementNode ==null)
////									{
////										elementNode = new DefaultMutableTreeNode(pE.getShortName());
////									}
////									matchedElementsList.add(pin);
////									elementNode.add(new DefaultMutableTreeNode(pin.getShortName()));
////								}
////							}
////						}
////						if(hardwareCompType.getHardwareComponentPrototype_type()!=null)
////						{
////							if(hardwareCompType.getHardwareComponentPrototype_type().size()==0)
////							{
////							}
////							else
////							{
////								for( HardwareComponentPrototype proto : hardwareCompType.getHardwareComponentPrototype_type())
////								{
////									if(proto.getGenericConstraint_target()!=null)
////									{
////										if(proto.getGenericConstraint_target().size()>0)
////										{
////											for(GenericConstraint genCon:proto.getGenericConstraint_target())
////											{
////												if(genCon.getKind().compareTo(genericConstraint.getKind())==0)
////												{
////													//There is atleast one prototype
////													//The type should not be in the elementMatched
////													typesWithPrototypes.add(hardwareCompType);
////												}
////											}
////
////										}
////									}
////								}
////							}
////
////						}
//		
//		return elementNode;
//	}
}
