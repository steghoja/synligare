package org.eclipse.eatop.volvo.linearpropertyanalyzer.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.eclipse.eatop.volvo.linearpropertyanalyzer.controllers.AnalysisController;

@SuppressWarnings("serial")
public class CancelAndTermination extends JFrame implements ActionListener {
	private JButton btnYes;
	private JButton btnNoBack;
	private JButton btnQuit;
	private JPanel cancelationButtonsArea;
	private JPanel labelArea;
	private JLabel lblMessage;
	private int selectedWindow;
	private int previousWin;
	private int counter;
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}

	RequirementsSelectionWindow reqWindow;
//	GenericConstraintKindSelectionWindow genConKindWindow;
//	ChooseElementsWindow chooseEleWindow;
	ModeSelectionWindow modeWindow;
//	AnalysisWORequirementWindow withOutReqWindow;
	
	public CancelAndTermination(){
	}
	public CancelAndTermination(RequirementsSelectionWindow req){
		this.previousWin=1;
		this.reqWindow=req;
		setVisible(true);
		selectedWindow = 0;
	}
//	public CancelAndTermination(GenericConstraintKindSelectionWindow gen){
//		this.previousWin=2;
//		this.genConKindWindow=gen;
//		setVisible(true);
//		selectedWindow = 0;
//	}
//	public CancelAndTermination(ChooseElementsWindow choEle){
//		this.previousWin=3;
//		this.chooseEleWindow=choEle;
//		setVisible(true);
//		selectedWindow = 0;
//	}
	public CancelAndTermination(ModeSelectionWindow modeEle){
		this.previousWin=4;
		this.modeWindow=modeEle;
		setVisible(true);
		selectedWindow = 0;
	}
//	public CancelAndTermination(AnalysisWORequirementWindow woReqEle){
//		this.previousWin=5;
//		this.withOutReqWindow=woReqEle;
//		setVisible(true);
//		selectedWindow = 0;
//	}
	
	public void cancelation(){
		
		selectedWindow = 1;
		
		setLayout(new GridLayout(2,1));
		
		labelArea = new JPanel();
		add(labelArea);
		
		lblMessage = new JLabel("Do you want to cancel this analysis?");
		labelArea.add(lblMessage);
		
		cancelationButtonsArea=new JPanel();
		add(cancelationButtonsArea);
		
		
		
		btnYes = new JButton("Yes, Go to Next");
		btnYes.addActionListener(this);
		cancelationButtonsArea.add(btnYes);
		
		btnNoBack = new JButton("No, Back");
		btnNoBack.addActionListener(this);
		cancelationButtonsArea.add(btnNoBack);
		
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(this);
		cancelationButtonsArea.add(btnQuit);
		
		pack();		
		setLocationRelativeTo(null);
	}
	public void termination(){
		
		selectedWindow=2;
		
		setLayout(new GridLayout(2,1));
		
		labelArea = new JPanel();
		add(labelArea);
		

		lblMessage = new JLabel("Are you sure you want to terminate?");
		labelArea.add(lblMessage);
		
		cancelationButtonsArea=new JPanel();
		add(cancelationButtonsArea);		
		
		btnYes = new JButton("Yes");
		btnYes.addActionListener(this);
		cancelationButtonsArea.add(btnYes);
		
		btnNoBack = new JButton("No, Back");
		btnNoBack.addActionListener(this);
		cancelationButtonsArea.add(btnNoBack);
		
		pack();		
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent a) {
		  if(a.getSource() == btnYes) {
		    	if(selectedWindow==1){
				setVisible(false);
//		    	if (previousWin==2){
//		    		this.genConKindWindow.setVisible(true);
//		    		this.genConKindWindow.setEnabled(true);
//		    	}
//		    	else if (previousWin==3){
//		    		this.chooseEleWindow.setVisible(true);
//		    		this.chooseEleWindow.setEnabled(true);
//		    	}
//		    	else 
		    	if (previousWin==4){
		    		this.modeWindow.setVisible(true);
		    		this.modeWindow.setEnabled(true);
		    	}
		    	boolean result=false;
				try {
					result = AnalysisController.INSTANCE.nextReqModelAnalysis();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	if(result ==false)
		    	{
		    		this.dispose();
//		    		if(this.chooseEleWindow!=null)
//		    		{
//		    			this.chooseEleWindow.dispose();
//		    		}
		    		if(this.modeWindow!=null)
		    		{
		    			this.modeWindow.dispose();
		    		}
		    		
		    		JOptionPane.showMessageDialog(null, "There are no more analyses to run", "No more analyses", JOptionPane.INFORMATION_MESSAGE);
		    	}
		    }
			  else if(selectedWindow==2){
				  	setVisible(false);
			    	if(previousWin==1){
			    		reqWindow.setVisible(false);
			    	}
//			    	else if (previousWin==2){
//			    		this.genConKindWindow.setVisible(false);
//			    	}
//			    	else if (previousWin==5){
//			    		this.withOutReqWindow.setVisible(false);
//			    	}
				  }
		    	
		    	
		    	
		    }
		    else if(a.getSource() == btnNoBack) {
		    	setVisible(false);
		    	if(previousWin==1){
		    		reqWindow.setEnabled(true);
		    		reqWindow.setFocusable(true);
		    		reqWindow.setVisible(true);
		    	}
//		    	else if (previousWin==2){
//		    		this.genConKindWindow.setEnabled(true);
//		    		genConKindWindow.setFocusable(true);
//		    		genConKindWindow.setVisible(true);
//		    	}
//		    	else if (previousWin==3){
//		    		this.chooseEleWindow.setEnabled(true);
//		    		chooseEleWindow.setFocusable(true);
//		    		chooseEleWindow.setVisible(true);
//		    	}
		    	else if (previousWin==4){
		    		this.modeWindow.setEnabled(true);
		    		modeWindow.setFocusable(true);
		    		modeWindow.setVisible(true);
		    	}
//		    	else if (previousWin==5){
//		    		this.withOutReqWindow.setEnabled(true);
//		    		withOutReqWindow.setFocusable(true);
//		    		withOutReqWindow.setVisible(true);
//		    	}
		    }
		    else if(a.getSource() == btnQuit) {
		    	setVisible(false);	
//		    	if(previousWin==2){
//		    		this.genConKindWindow.setVisible(false);
//		    	}
//		    	else if(previousWin==3){
//		    		this.chooseEleWindow.setVisible(false);
//		    	}
//		    	else 
		    		if(previousWin==4){
		    		this.modeWindow.setVisible(false);		    	
		    	}
		    }
		
	}

}
