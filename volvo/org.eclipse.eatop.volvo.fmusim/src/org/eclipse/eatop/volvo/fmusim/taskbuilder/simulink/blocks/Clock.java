package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class Clock extends Block {

	
	public Clock() {
		super("Clock");
		layoutColumn = 0;
		layoutRow = 1;
		setTopLeftPoint();
		width = 20;
		heigth = 20;
	
	}

	
	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Sources/Clock', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
	}

}
