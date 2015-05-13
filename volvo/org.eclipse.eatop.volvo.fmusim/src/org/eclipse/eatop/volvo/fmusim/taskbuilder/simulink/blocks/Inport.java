package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class Inport extends Block {
	
	int portNum; 	//number in subsystem
	String portId; 
	
	boolean suppressWrite; //if Inport in .mdl Subsystem, don't write in .m, since copyContentsToSubSystem creates port
	boolean hasGoto;
	
	String latchByDelayingOutsideSignal; //'off' / 'on'
	
	public Inport() {
	super("Inport");
		layoutColumn = 0;
		layoutRow = 2;
		setTopLeftPoint();
		width = 30;
		heigth = 14;
		suppressWrite = false;
		hasGoto = false;
	}
	
	

	public String getLatchByDelayingOutsideSignal() {
		return latchByDelayingOutsideSignal;
	}



	public void setLatchByDelayingOutsideSignal(String latchByDelayingOutsideSignal) {
		this.latchByDelayingOutsideSignal = latchByDelayingOutsideSignal;
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

/*	public void setPortId(String portId) {
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


	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		if (!isSuppressWrite())
		{
			writeLn("add_block('Simulink/Sources/In1', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
			if (latchByDelayingOutsideSignal != null)
			{
				writeLn("set_param ('" + getFullPath() + "', 'LatchByDelayingOutsideSignal', '" + latchByDelayingOutsideSignal + "');");
			}

		}
	}



	public boolean isSuppressWrite() {
		return suppressWrite;
	}



	public void setSuppressWrite(boolean suppressWrite) {
		this.suppressWrite = suppressWrite;
	}
	
}
