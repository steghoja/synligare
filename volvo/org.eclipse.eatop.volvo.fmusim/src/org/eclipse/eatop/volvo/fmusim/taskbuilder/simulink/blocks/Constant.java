package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class Constant extends Block {

	float value;
	
	public Constant() {
		super("Constant");
		layoutColumn = 0;
		layoutRow = 1;
		setTopLeftPoint();
		width = 30;
		heigth = 30;

	
		value = 0.0f;
	}

	
	public float getValue() {
		return value;
	}


	public void setValue(float value) {
		this.value = value;
	}


	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Sources/Constant', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");"); 
		writeLn("set_param ('" + getFullPath() + "', 'value', '" + value + "');");  
	}

}
