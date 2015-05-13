package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class RepeatingSequenceInterpolated extends Block{

	String outValues;
	String timeValues;
	String lookUpMethod;
	float  sampleTime;
	
	public RepeatingSequenceInterpolated(){
	super("RepeatingSequenceInterpolated");
	layoutColumn = 0;	
	layoutRow = 2;
	setTopLeftPoint();
	width = 30;
	heigth = 30;

	
	outValues = "[3 1 4 2 1].";
	timeValues = "[0 0.1 0.5 0.6 1].'";		
	lookUpMethod = "Use Input Below";
	sampleTime = 0.5f;

}



	public String getOutValues() {
	return outValues;
	}

	public void setOutValues(String outValues) {
		this.outValues = outValues;
	}
	
	public String getTimeValues() {
		return timeValues;
	}
	
	public void setTimeValues(String timeValues) {
		this.timeValues = timeValues;
	}
	
	public String getLookUpMethod() {
		return lookUpMethod;
	}
	
	public void setLookUpMethod(String lookUpMethod) {
		this.lookUpMethod = lookUpMethod;
	}
	
	public float getSampleTime() {
		return sampleTime;
	}
	
	public void setSampleTime(float sampleTime) {
		this.sampleTime = sampleTime;
	}


	@Override
	public void Write(BufferedWriter bw) throws IOException{

	writeLn("add_block('Simulink/Sources/Repeating Sequence Interpolated', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
	writeLn("set_param ('" + getFullPath() + "', 'OutValues', '" + outValues + "', 'TimeValues', '" + timeValues +"');");  
	writeLn("set_param ('" + getFullPath() + "', 'LookUpMeth', '" + lookUpMethod + "', 'tsamp', '" + sampleTime +"');");  
	}

}

