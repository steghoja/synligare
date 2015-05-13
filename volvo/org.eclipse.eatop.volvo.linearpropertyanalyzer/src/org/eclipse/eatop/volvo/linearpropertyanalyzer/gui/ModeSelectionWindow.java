package org.eclipse.eatop.volvo.linearpropertyanalyzer.gui; 

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.ModeGroup;
import org.eclipse.eatop.eastadl21.GenericConstraint;
import org.eclipse.eatop.eastadl21.GenericConstraintKind;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.controllers.AnalysisController;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.metamodelExchanger.FindRef_getModeGroup_mode;

@SuppressWarnings("serial")
public class ModeSelectionWindow extends JFrame implements ActionListener {
	protected JButton confirm;
	protected JButton cancel;
	protected Map<ModeGroup, List<Mode>> modeMap = new HashMap<ModeGroup, List<Mode>>();
	protected Map<GenericConstraint, List<Mode>> map;
	protected Map<GenericConstraint, List<Mode>> returnMap = new HashMap<GenericConstraint, List<Mode>>();
	protected List<ButtonGroup> buttonGroups = new LinkedList<ButtonGroup>();
	protected Iterator<GenericConstraint> it;
	protected GenericConstraint gck;
	private boolean hasModes;
	private boolean lastIt = false;
	private int nrKeys=0;
	
	public ModeSelectionWindow(Map<GenericConstraint,List<Mode>> map) throws Exception{
		nrKeys = map.keySet().size();
		it = map.keySet().iterator();
		this.map = map;
		while(it.hasNext()){
			
			gck = it.next();
			if(gck.getKind() == GenericConstraintKind.POWER_CONSUMPTION){
				hasModes = true;
				getNextWindow(gck.getShortName(),map.get(gck));
				break;
			}
			else if(gck.getKind() == GenericConstraintKind.CURRENT){
				hasModes = true;
				getNextWindow(gck.getShortName(),map.get(gck));
				break;
			}
		}
		if(!hasModes)
			AnalysisController.INSTANCE.startAnalysis((new ArrayList<GenericConstraint>( map.keySet())));
	}
	
	public void getNextWindow(String name, List<Mode> modes){
		modeMap.clear();
		this.getContentPane().removeAll();
		this.invalidate();
		this.validate();
		this.repaint();
		for(Mode mode : modes){
			FindRef_getModeGroup_mode temp = new FindRef_getModeGroup_mode();
			ModeGroup modeGroup = (ModeGroup) temp.getModeGroup_mode(mode);//mode.getModeGroup_mode();
			if(! modeMap.containsKey(modeGroup)){
				modeMap.put(modeGroup, new LinkedList<Mode>());
			}
			if(! modeMap.get(modeGroup).contains(mode)){
				modeMap.get(modeGroup).add(mode);
			}
		}
		if (modeMap.keySet().size()>0){
			setUpWindow(name);
		}
		else{
			try {
				AnalysisController.INSTANCE.startAnalysis((new ArrayList<GenericConstraint>( map.keySet())));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setUpWindow(String title) {
		
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setTitle(title);

		JPanel checkBoxArea = new JPanel(new GridBagLayout());
		JPanel confirmArea = new JPanel();
		

		//Confirm Button
		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		confirmArea.add(confirm);
		
		//Cancel button
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		confirmArea.add(cancel);
		
		int max = 0;
		for(ModeGroup modeGroup : modeMap.keySet()){
			int i = modeMap.get(modeGroup).size();
			if(i > max){
				max = i;
			}
		}
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		
		int x = 0;
		int y = 0;
		for(ModeGroup modeGroup : modeMap.keySet()){
			y = 0;
			gbc.gridy = 0;
			JLabel label = new JLabel(modeGroup.getShortName());
			checkBoxArea.add(label, gbc);
			ButtonGroup group = new ButtonGroup();
			for(Mode mode : modeMap.get(modeGroup)){
				y++;
				gbc.gridy = y;
				JRadioButton button = new JRadioButton(mode.getShortName());
				group.add(button);
				checkBoxArea.add(button, gbc);
			}
			buttonGroups.add(group);
			x++;
			gbc.gridx = x;
		}
		gbc.gridy = max + 1;

		add(checkBoxArea);
		add(confirmArea);
		
		//pack all elements within the JFrame
		pack();
		//center the window
		setLocationRelativeTo(null);
		//Display the window
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == confirm){
			List<String> names = new LinkedList<String>();
			for(ButtonGroup group : buttonGroups){
				Enumeration<AbstractButton> buttons = group.getElements();
				while(buttons.hasMoreElements()){
					AbstractButton button = buttons.nextElement();
					if(button.isSelected()){
						names.add(button.getText());
					}
				}
			}
			for(Mode mode : map.get(gck)){
				for(String name : names){
					if(mode.getShortName().equals(name)){
						if(! returnMap.containsKey(gck)){
							returnMap.put(gck, new LinkedList<Mode>());
						}
						returnMap.get(gck).add(mode);
						break;
					}
				}
			}
			while(it.hasNext()){				
				gck = it.next();
				if(gck.getKind() == GenericConstraintKind.POWER_CONSUMPTION){
					getNextWindow(gck.getShortName(),map.get(gck));
					break;
				}
				else if(gck.getKind() == GenericConstraintKind.OTHER){
					getNextWindow(gck.getShortName(),map.get(gck));
					break;
				}
				
				if(it.hasNext())
				{
					lastIt = false;
				}
			}
			if(nrKeys==1)
			{
				lastIt=true;
			}
			if(lastIt)
			{
				if(!it.hasNext())
				{
					this.dispose();
					try {
						AnalysisController.INSTANCE.prepareModeAnalysis(returnMap);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			else
			{
				lastIt = true;
			}
		}
		else if(action.getSource() == cancel){
			this.setEnabled(false);
			CancelAndTermination can = new CancelAndTermination(this);
			can.cancelation();
		}
    }
	
	public Map<GenericConstraint, List<Mode>> getReturnMap(){
		return returnMap;
	}
}
