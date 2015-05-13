package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class Trigger extends Block {

	String triggerType;
	
	public Trigger(){
		super("TriggerPort");

		layoutColumn = 2;
		layoutRow = 0;
		setTopLeftPoint();
		width = 20;
		heigth = 20;

		
		triggerType = "Rising";
		
	}
	

	public String getTriggerType() {
		return triggerType;
	}


	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}


	
	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Ports & Subsystems/Trigger', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
		writeLn("set_param ('" + getFullPath() + "', 'TriggerType', '" + triggerType + "');");  
		
	}

		
	@Override
	public String getPortId() {
		return "trigger";
	}

	
	
}
