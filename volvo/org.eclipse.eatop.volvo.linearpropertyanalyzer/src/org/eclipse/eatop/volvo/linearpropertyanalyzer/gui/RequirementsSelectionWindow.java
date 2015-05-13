package org.eclipse.eatop.volvo.linearpropertyanalyzer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.controllers.AnalysisController;

/**
 * This class represents a Pop-up Window containing the information regarding
 * the requirements included in a RequirementsModel. It has the same structure as
 * a standard AnalysisWindow.
 * @author CodenameA
 *
 */
@SuppressWarnings("serial")
public class RequirementsSelectionWindow extends AnalysisWindow implements ActionListener {	
	/**
	 * The constructor creates a standard AnalysisWindow and
	 * adds listeners to the Confirm and Cancel buttons.
	 * @param elemList
	 */

	public RequirementsSelectionWindow(List<EAElement> elemList) {
		super(elemList);
		
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		setTitle("Select the Requirements to analyze");
		setVisible(true);
	}
	
	/**
	 * Will return the confirm button of the window
	 * @return JButton that is the confirm button in the window
	 */
	public JButton getConfirm() {
		return confirm;
	}

	/**
	 * Will return the cancel button of the window
	 * @return JButton that is the cancel button in the window
	 */
	public JButton getCancel() {
		return cancel;
	}

	/**
	 * This method defines what happens when a user clicks either
	 * the cancel or confirm button.
	 */
	public void actionPerformed(ActionEvent e) {  
	    if(e.getSource() == confirm) {
	    	if(selectionList.size()==0)
			{
				JOptionPane.showMessageDialog(null, "Select atleast one GenericConstraintKind", "Selection Warning", JOptionPane.WARNING_MESSAGE);
				
			}
			else
			{
				this.setVisible(false);
				AnalysisController.INSTANCE.setReqModelSelectionIndicator(true);
				try {
					AnalysisController.INSTANCE.reqModelAnalysis(createSelectionList());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Analyzer Warning",  JOptionPane.INFORMATION_MESSAGE);
				}
			}
	    }
	    else if(e.getSource() == cancel) {
	    	this.setEnabled(false);
	    	CancelAndTermination canter = new CancelAndTermination(this);
	    	canter.termination();
	    }
	}

	private List<EAElement> createSelectionList() {
		List<EAElement> result = new LinkedList<EAElement>();
		for(String selection : selectionList){
			for(EAElement eAE: elementSelectionList){
				if(eAE.getShortName().equals(selection)){
					result.add(eAE);
				}
			}
		}
		return result;
	}
}
