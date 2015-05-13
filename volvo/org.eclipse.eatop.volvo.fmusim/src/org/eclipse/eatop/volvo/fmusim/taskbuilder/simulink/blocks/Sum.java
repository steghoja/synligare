package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class Sum extends MultipleInputsBlock {

	String sInputs;
	
	public Sum(int _inputs) {
		super("Sum", _inputs);
		layoutColumn = 2;
		layoutRow = 4;
		setTopLeftPoint();
		width = 30;
		heigth = 50;

		
		sInputs = "++";
	}


	//"++"
	public String getsInputs() {
		return sInputs;
	}

	public void setsInputs(String inputs) {
		this.sInputs = inputs;
	}


	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Math Operations/Sum', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");"); 
		writeLn("set_param ('" + getFullPath() + "', 'inputs', '" + sInputs + "');");  
		writeLn("set_param ('" + getFullPath() + "', 'iconshape', 'rect');");  
	
	}
}
