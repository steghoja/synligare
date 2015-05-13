package org.eclipse.eatop.volvo.linearpropertyanalyzer.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.models.ModelIO;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.models.ModelProcessor;

/**
 * This class will start the flow of an analysis, by checking the element selected
 * and start the right method. Thereafter the different GUI classes will control the
 * flow 
 *
 */
public enum FlowController {
	INSTANCE;
	
	private ModelProcessor modelProcessor;
	private EAElement root = null;
	private EAElement elementSelectedInModel = null;
//	private String selectedElementShortName;
	private EObject selectedElement;
	
	/**
	 * This method will start an analysis flow. It will read what the user has selected in the editor and start the flow based on that selection.
	 * It will also load the model that the user is using
	 * @throws Exception 
	 */
	public void startAnalysis(EObject selectedElement) throws Exception{
		validateModel();
		this.selectedElement = selectedElement;
//		this.selectedElementShortName = getShortNameForSelectedElement(selectedElement);
		
		modelProcessor = new ModelProcessor();
		
		root = getRoot(this.selectedElement);
		
		if(selectedElement instanceof Requirement){
			requirementSelected((Requirement)selectedElement);
		}
		
		else if(selectedElement instanceof RequirementsModel ){
			requirementsModelSelected((RequirementsModel)selectedElement);
		}

		else{
			JOptionPane.showMessageDialog(null, "Please select a Requirement or RequirementModel.", "Analyzer Warning",  JOptionPane.INFORMATION_MESSAGE);
			
		}
	}

//	/**
//	 * Return the root element of the model
//	 * @return the root element if it is loaded, otherwise null
//	 */
//	public EAElement getRoot() {
//		return root;
//	}
//
//	/**
//	 * Will return the EAElement that the user selected in the model
//	 * @return null if it has not been found or searched for
//	 */
//	public EAElement getElementSelectedInModel() {
//		return elementSelectedInModel;
//	}
//	
//	private void elementSelected(String selection) {
//		elementSelectedInModel = modelProcessor.findElementFromName(getRoot(), selection);
//		WindowController.INSTANCE.createAnalysisOrRequirementModel(elementSelectedInModel);
//	}
//
//	private void genericConstraintSelected(String selection) {
//		// TODO Auto-generated method stub
//	}
//
//	private void genericConstraintSetSelected() {
//		// TODO Auto-generated method stub
//		
//	}
//
	private void requirementSelected(Requirement selection) throws Exception {
//		//Find all the requirements in the model
//		List<Requirement> requirementSpecObjList = modelProcessor.findRequirementsInModel(root);
//		//Array used for copying the Requirement to Requirement and used for the rest of the method to work
//		List<Requirement> requirementsList = new ArrayList<Requirement>();
//		if(requirementSpecObjList!=null){
//			//Cast all the Requirement to Requirements
//			for(Requirement reqObj:requirementSpecObjList){
//				requirementsList.add((Requirement) reqObj);
//			}
//			//Loop over all the requirements to find the one that was clicked
//			for(Requirement requirement:requirementsList){
//				//is this requirement the one that was clicked
//				if(selection.equalsIgnoreCase(requirement.getShortName())){
//					AnalysisController.INSTANCE.reqAnalysis(requirement);
//				}
//			}
//		}
		
		AnalysisController.INSTANCE.reqAnalysis(selection);
	}

	private void requirementsModelSelected(RequirementsModel selection) throws Exception {
//		//find the RequirementModels in the model
//		List<RequirementsModel> modelsList = modelProcessor.findRequirementModels(root);
//		//Get all the RequirementsModels
//		if(modelsList!=null){
//			//This for is used to find the selected RequirementsModels
//			for(RequirementsModel model: modelsList){
//				//Does the name match?
//				if(selection.equalsIgnoreCase(model.getShortName())){
//					//The RequirementsModels were found
//					//Get all the requirements in the RequirementsModel
//					List<Requirement> requirementsList = modelProcessor.findRequirementsFromReqModel(model);
//					//Create the GUI windows and buttons to be able to choose the requirements that the user wants to analyze
//					WindowController.INSTANCE.createRequirementsSelectionWindow(requirementsList);
//				}
//			}
//		}
		
		//Get all the requirements in the RequirementsModel
		List<Requirement> requirementsList = modelProcessor.findRequirementsFromReqModel(selection);
		//Create the GUI windows and buttons to be able to choose the requirements that the user wants to analyze
		WindowController.INSTANCE.createRequirementsSelectionWindow(requirementsList);
	}
	
	/**
	 * This method returns the shortName of an EObject, if one is found.
	 * Otherwise returns null.
	 * @param element
	 * @return The shortName of the EObject. Null if none is found.
	 */
	private String getShortNameForSelectedElement(EObject element){
		String elementName = null;
		
		//List of all EObject's attributes.
		EList<EAttribute> attributeList = element.eClass().getEAllAttributes();
		
		for(EAttribute eAttribute : attributeList){
			
			if(eAttribute.getName() == "shortName"){
				elementName = selectedElement.eGet(eAttribute).toString();
			}
		}
		return elementName;
	}

	/**
	 * Returns the Root EAPackage element as a EAElement.
	 * @param eObj
	 * @return
	 */
	public EAElement getRoot(EObject eObj){
		Resource resource = eObj.eResource().getResourceSet().getResources().get(0);
		ModelIO.INSTANCE.setResource(resource);
//		EAXML temp;
//		temp = (EAXML)EcoreUtil.getRootContainer(eObj);
//		return temp.getTopLevelPackage().get(0);
		
		//this is better, the way above may give wrong package
		while (!(eObj.eContainer() instanceof EAXML)){
			eObj = eObj.eContainer();
		};
		
		return (EAElement)eObj;
		
	}
	
	private void validateModel(){
//		Validation validation = new Validation();
//		if(validation.modelIsValid()){
//			JOptionPane.showMessageDialog(null, "Model seems to be valid.");
//		}
//		else
//			JOptionPane.showMessageDialog(null, "Model is NOT valid, proceed with analysis at own risk!");
	}
}