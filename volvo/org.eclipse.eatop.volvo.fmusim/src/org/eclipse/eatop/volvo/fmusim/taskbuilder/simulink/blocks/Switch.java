package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;


import java.io.BufferedWriter;
import java.io.IOException;


public class Switch extends MultipleInputsBlock {


	float threshold;
	
	public Switch() {
		super("Switch", 3);
		layoutColumn = 3;
		layoutRow = 1;
		setTopLeftPoint();
		width = 25;
		heigth = 80;

		threshold = 0.0f;
		
	}

	
	public float getThreshold() {
		return threshold;
	}


	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}


	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Signal Routing/Switch', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");"); 
		writeLn("set_param ('" + getFullPath() + "', 'threshold', '" + threshold + "');");  

	}

	}
