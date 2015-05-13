package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class DetectDecrease extends Block {

	
	public DetectDecrease() {
		super("DetectDecrease");
		layoutColumn = 3;
		layoutRow = 2;
		setTopLeftPoint();
	
		width = 60;
		heigth = 30;

	}

	
	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Logic and Bit Operations/Detect Decrease', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
	}


}
