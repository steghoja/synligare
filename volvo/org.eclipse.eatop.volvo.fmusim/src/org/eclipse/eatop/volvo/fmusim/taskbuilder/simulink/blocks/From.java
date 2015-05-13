package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class From extends Block {

	String gotoTag;
	Block fromDestination;	//The block that this from block is connected to 
	
	public From() {
		super("Goto");
		layoutColumn = 0;
		layoutRow = 1;
		setTopLeftPoint();
		width = 20;
		heigth = 20;
	
	}

	
	public String getGotoTag() {
		return gotoTag;
	}


	public void setGotoTag(String tag) {
		this.gotoTag = tag;
	}


	public Block getFromDestination() {
		return fromDestination;
	}


	public void setFromDestination(Block fromDestination) {
		this.fromDestination = fromDestination;
	}


	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Signal Routing/From', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
		writeLn("set_param ('" + getFullPath() + "', 'GotoTag', '" + gotoTag + "');");  
	}

}
