package org.eclipse.eatop.volvo.fmusim.ecore;

import javax.sound.sampled.Port;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;

public class PortPrototype {

	FunctionFlowPort  port;
    DesignFunctionPrototype functionPrototype;

    public PortPrototype() {
	}    

    public PortPrototype(DesignFunctionPrototype dfp, FunctionFlowPort ffp) {
    	functionPrototype = dfp;
    	port = ffp;
	}    
    public FunctionFlowPort getPort() {
		return port;
	}
	public void setPort(FunctionFlowPort port) {
		this.port = port;
	}
	public DesignFunctionPrototype getFunctionPrototype() {
		return functionPrototype;
	}
	public void setFunctionPrototype(DesignFunctionPrototype functionPrototype) {
		this.functionPrototype = functionPrototype;
	} 

    
	
}
