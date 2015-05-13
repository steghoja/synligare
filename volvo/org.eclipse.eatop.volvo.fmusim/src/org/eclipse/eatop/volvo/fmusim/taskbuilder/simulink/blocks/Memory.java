package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class Memory extends Block {


	public Memory() {
		super("Memory");
		layoutColumn = 3;
		layoutRow = 2;
		setTopLeftPoint();
		width = 30;
		heigth = 30;

	
	}

	
	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Discrete/Memory', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");"); 
	}
}
