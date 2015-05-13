package org.eclipse.eatop.volvo.fmusim.taskbuilder.simulink.blocks;

import java.io.BufferedWriter;
import java.io.IOException;

public class PulseGenerator extends Block {

	String pulseType;
	String timeSource;
	float amplitude;
	float period;
	float pulseWidth; //percentage of period
	float phaseDelay;
	
	public PulseGenerator(){
		super("DiscretePulseGenerator");

		layoutColumn = 2;
		layoutRow = 2;
		setTopLeftPoint();
		width = 30;
		heigth = 32;
		
		pulseType = "Time based";
		timeSource = "Use simulation time";
		amplitude = 1;
		period = 0.5f;
		pulseWidth = 30;
		phaseDelay = 0;


	}
	


	public String getPulseType() {
		return pulseType;
	}


	public void setPulseType(String pulseType) {
		this.pulseType = pulseType;
	}


	public String getTimeSource() {
		return timeSource;
	}


	public void setTimeSource(String timeSource) {
		this.timeSource = timeSource;
	}


	public float getAmplitude() {
		return amplitude;
	}



	public void setAmplitude(float amplitude) {
		this.amplitude = amplitude;
	}



	public float getPeriod() {
		return period;
	}


	public void setPeriod(float period) {
		this.period = period;
	}



	public float getPulseWidth() {
		return pulseWidth;
	}



	public void setPulseWidth(float pulseWidth) {
		this.pulseWidth = pulseWidth;
	}


	public float getPhaseDelay() {
		return phaseDelay;
	}



	public void setPhaseDelay(float phaseDelay) {
		this.phaseDelay = phaseDelay;
	}


	
	@Override
	public void Write(BufferedWriter bw) throws IOException{
		
		writeLn("add_block('Simulink/Sources/Pulse Generator', '" + getFullPath() + "', 'Position', " + GetPositionVector() +");");
		writeLn("set_param ('" + getFullPath() + "', 'PulseType', '" + pulseType + "', 'TimeSource', '" + timeSource + "');");  
		writeLn("set_param ('" + getFullPath() + "', 'Amplitude', '" + amplitude + "', 'Period', '" + period + "');");  
		writeLn("set_param ('" + getFullPath() + "', 'PulseWidth', '" + pulseWidth + "', 'PhaseDelay', '" + phaseDelay + "');");  
		
	}
	
	
	
	
}
