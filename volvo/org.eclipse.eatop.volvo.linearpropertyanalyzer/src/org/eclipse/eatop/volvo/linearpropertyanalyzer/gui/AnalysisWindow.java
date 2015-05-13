package org.eclipse.eatop.volvo.linearpropertyanalyzer.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.eclipse.eatop.eastadl21.EAElement;

/**
 * This class represent a generic Pop-up Window which can be modeled to represent different types
 * of information during the analysis process. It contains a JFrame with two JPanels; one JPanel on
 * top for the checkBoxes and one at the bottom for the buttons.
 * @author Andrei Blom
 */
@SuppressWarnings("serial")
public abstract class AnalysisWindow extends JFrame{
	protected JButton confirm;
	protected JButton cancel;
	protected JPanel checkBoxArea;
	protected JPanel buttonArea;
	protected List<String> selectionList = new ArrayList<String>();
	protected List<EAElement> elementSelectionList = new ArrayList<EAElement>();

	/**
	 * Will return a list that contains all the EAElements that is selected and stored in selectionList
	 * @return
	 */
	public List<EAElement> getElementSelectedList() {
		List<EAElement> returnList = new ArrayList<EAElement>();
		for(EAElement element:elementSelectionList)
		{
			boolean found = false;
			for(String s:selectionList)
			{
				if(element.getShortName().equalsIgnoreCase(s))
				{
					found = true;
					break;
				}
			}
			if(found ==true)
			{
				returnList.add(element);
			}
		}
		return returnList;
	}

	/**
	 * Creates an AnalysisWindow with checkBoxes according to the names of the elements in elementList.
	 * The checkBox Listeners are handled in the constructor and the selections are saved in 
	 * selectionList. The Listeners for the buttons need to be set by the developer in the class
	 * representing a more specific window type. (See RequierementsSelection) 
	 * @param elementList
	 */
	public AnalysisWindow(List<EAElement> elementList){
		setUpWindow(elementList);
	}
	
	protected AnalysisWindow(){
		
	}

	private void setUpWindow(List<EAElement> elementList) {
		//GridLayout with 2 rows and 1 column
		setLayout(new GridLayout(2,1));

		//Creating and adding the JPanel which acts as the container for the check-boxes
		checkBoxArea = new JPanel();
		add(checkBoxArea);

		//Creating and adding the JPanel which acts as the container for the buttons
		buttonArea = new JPanel();
		add(buttonArea);

		//Confirm Button
		confirm = new JButton("Confirm");
		buttonArea.add(confirm);
		
		//Cancel button
		cancel = new JButton("Cancel");
		buttonArea.add(cancel);		

		elementSelectionList.addAll(elementList);
		
		for(EAElement elem: elementList){
			//Add a checkBox with the elements name
			JCheckBox checkBox = new JCheckBox(elem.getShortName());

			//Set the checkbox name to the name of the element
			checkBox.setName(elem.getShortName());

			//Add an ActionListener
			checkBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JCheckBox checkBox = (JCheckBox) e.getSource();
					if (checkBox.isSelected()) {

						//Add the name of the selected checkBox to SelectionList
						if(!selectionList.contains(checkBox.getName()))
							selectionList.add(checkBox.getName());

					}
					else{

						//Remove the name of the UNselected checkBox from the SelectionList
						selectionList.remove(checkBox.getName());

					}
				}
			});
			checkBoxArea.add(checkBox);
		}

		//pack all elements within the JFrame
		pack();
		//center the window
		setLocationRelativeTo(null);
	}

	/**
	 * The list contains the check-box selections made by the user
	 * @return
	 */
	public List<String> getSelectionList() {
		return selectionList;
	}

	/**
	 * Will return the confirm button of the window
	 * @return JButton that is the confirm button in the window
	 */
	public JButton getConfirmButton(){
		return confirm;
	}

	/**
	 * Will return the cancel button of the window
	 * @return JButton that is the cancel button in the window
	 */
	public JButton getCancelButton() {
		return cancel;
	}
	
	/**
	 * Will add an actionlistner to the confirm button
	 * @param ActionListener that will be added to the confirm button
	 */
	public void addConfirmActionListener(ActionListener al){
		confirm.addActionListener(al);
	}
	
	/**
	 * Will add an actionlistner to the cancel button
	 * @param ActionListener that will be added to the cancel button
	 */
	public void addCancelActionListener(ActionListener al){
		cancel.addActionListener(al);
	}
}
