package org.eclipse.eatop.volvo.fmusim.taskbuilder;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;

public class EventTriggeredDFTExecutionInfo extends DFTExecutionInfo {

	//port = port @ dft that is eventtriggered
	FunctionFlowPort port;
	
	
	public FunctionFlowPort getPort() {
		return port;
	}

	public void setPort(FunctionFlowPort port) {
		this.port = port;
	}

		
	
}
