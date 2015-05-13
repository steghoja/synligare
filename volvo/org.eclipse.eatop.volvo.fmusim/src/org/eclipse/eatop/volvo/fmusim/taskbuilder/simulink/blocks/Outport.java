package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class Outport extends Block {
	int portNum;
	String portId;
	boolean suppressWrite; //if Outport in .mdl Subsystem, don't write in .m, since copyContentsToSubSystem creates port
	String initialOutput; 
	boolean hasGoto; 		//if there is a goto block connected to this outport
	
	public Outport() {
	super("Outport");	
	layoutColumn = 3;
	layoutRow = 2;
	setTopLeftPoint();
	width = 30;
	heigth = 14;
	suppressWrite = false;
	hasGoto = false;
}


public boolean hasGoto() {
		return hasGoto;
	}


	public void setHasGoto(boolean hasGoto) {
		this.hasGoto = hasGoto;
	}


@Override
public String getPortId() {
	return portId;
}
/*
public void setPortId(String portId) {
	this.portId = portId;
}
*/

public int getPortNum() {
	return portNum;
}


public void setPortNum(int portNum) {
	this.portNum = portNum;
	layoutRow = 2 + portNum;
	setTopLeftPoint();
	portId = Integer.toString(portNum);
}


public boolean isSuppressWrite() {
	return suppressWrite;
}


public void setSuppressWrite(boolean suppressWrite) {
	this.suppressWrite = suppressWrite;
}


public String getInitialOutput() {
	return initialOutput;
}


public void setInitialOutput(String initialOutput) {
	this.initialOutput = initialOutput;
}


@Override
public void Write(BufferedWriter bw) throws IOException{
	
	if (!suppressWrite){
		writeLn("add_block('Simulink/Sinks/Out1', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
		if (initialOutput != null){
			writeLn("set_param ('" + getFullPath() + "', 'InitialOutput', '" + initialOutput + "');");  
		}
		
	}
}

}
