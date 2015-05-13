package org.eclipse.eatop.volvo.linearpropertyanalyzer.controllers; 

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.gui.ModeSelectionWindow;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.gui.RequirementsSelectionWindow;


/**
 * This class is responsible for the creating of all the graphical communication towards the user.
 * All the different types of AnalysisWindows are created here and the final ResultWindow as well. 
 * @author CodenameA
 *
 */
public enum WindowController {
	INSTANCE;

	/**
	 * This method creates a RequirementsSelection window.
	 * @param requirementsInReqModelList
	 */
	private boolean reqModelSelectionIndicator;
	
	
//	public boolean isReqModelSelectionIndicator() {
//		return reqModelSelectionIndicator;
//	}
//
	public void setReqModelSelectionIndicator(boolean reqModelSelectionIndicator) {
		this.reqModelSelectionIndicator = reqModelSelectionIndicator;
	}

	public void createRequirementsSelectionWindow(
			List<Requirement> requirementsInReqModelList) throws Exception {
			
		List<EAElement> elemList = new ArrayList<EAElement>(requirementsInReqModelList);
		if(elemList.size() == 1){
			AnalysisController.INSTANCE.reqModelAnalysis(elemList);
		}
		else{
			new RequirementsSelectionWindow(elemList);
		}
	}
		
	public void createGenericConstraintKindSelectionWindow(
			List<GenericConstraint> genConList) throws Exception{
		if(genConList.size() == 1){
			AnalysisController.INSTANCE.elementAnalysis(genConList);
			
		}
		else{
//			GenericConstraintKindSelectionWindow ww = new GenericConstraintKindSelectionWindow(genConList);
//			ww.setReqModelSelectionIndicator(reqModelSelectionIndicator);
			JOptionPane.showMessageDialog(null, "Each Requirement should have only one GenericConstraint.", "Analyzer Warning",  JOptionPane.INFORMATION_MESSAGE);

		}
	}
//
//	public Map<GenericConstraint, List<EAElement>> createElementSelectionWindow(SearchResult searchResult) {
//		ChooseElementsWindow chooseEleWindow = new ChooseElementsWindow(searchResult);
//		return chooseEleWindow.getSelectedAnalysisAndElements();
//	}
//
//	public void createAnalysisOrRequirementModel(EAElement elementSelectedInModel) {
//		new AnalysisWORequirementWindow(elementSelectedInModel);
//	}
//	
	public void createModeSelectionWindow(Map<GenericConstraint, List<Mode>> map) throws Exception{
		new ModeSelectionWindow(map);
	}
}
