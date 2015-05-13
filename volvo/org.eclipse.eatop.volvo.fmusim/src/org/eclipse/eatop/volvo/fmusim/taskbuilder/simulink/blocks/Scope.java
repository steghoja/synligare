package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.awt.datatransfer.DataFlavor;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import java.util.zip.DataFormatException;

public class Scope extends Block {

	String saveToWorkspace;
	//String saveName;
	String dataFormat;
	
	public Scope() {
		super("Scope");
		layoutColumn = 3;
		layoutRow = 2 + (int)(Math.random() * 5);
		setTopLeftPoint();
		width = 30;
		heigth = 34;

		
		saveToWorkspace = "on";
		dataFormat = "Array"; //Gives matrix, with two vectors (time & values)
	}

	
	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Sinks/Scope', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
		writeLn("set_param ('" + getFullPath() + "', 'saveToWorkspace', '" + saveToWorkspace + "', 'dataFormat', '" + dataFormat + "');");  
		writeLn("set_param ('" + getFullPath() + "', 'saveName', '" + getName() + "', 'limitDataPoints', 'off');");  
	}

}
