package org.eclipse.eatop.volvo.fmusim.taskbuilder;

public class TimeTriggeredDFTExecutionInfo extends DFTExecutionInfo {
	
	
	float executionPeriod;
	
	public TimeTriggeredDFTExecutionInfo() {
		executionPeriod = 0;
	}

	public TimeTriggeredDFTExecutionInfo(float executionTime, float executionPeriod) {
		super();
		this.executionPeriod = executionPeriod;
	}

	public float getExecutionPeriod() {
		return executionPeriod;
	}

	public void setExecutionPeriod(float executionPeriod) {
		this.executionPeriod = executionPeriod;
	}
	
	
	
	
}
