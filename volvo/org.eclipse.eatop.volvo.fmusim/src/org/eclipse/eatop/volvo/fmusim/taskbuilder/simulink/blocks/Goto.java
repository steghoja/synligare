package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class Goto extends Block {

	String gotoTag;
	String tagVisibility;
	
	Block gotoSource;			//The "Source" block that has a line to goto
	
	public Goto() {
		super("Goto");
		layoutColumn = 0;
		layoutRow = 4;
		setTopLeftPoint();
		width = 20;
		heigth = 20;
		tagVisibility = "global";
	
	}

	
	public String getGotoStringTag() {
		return gotoTag;
	}


	public void setGotoTag(String gotoTag) {
		this.gotoTag = gotoTag;
	}


	public Block getGotoSource() {
		return gotoSource;
	}


	public void setGotoSource(Block gotoSource) {
		this.gotoSource = gotoSource;
	}


	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Signal Routing/Goto', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
		writeLn("set_param ('" + getFullPath() + "', 'GotoTag', '" + gotoTag + "', 'TagVisibility', '"+tagVisibility+ "');");  
	}

}
