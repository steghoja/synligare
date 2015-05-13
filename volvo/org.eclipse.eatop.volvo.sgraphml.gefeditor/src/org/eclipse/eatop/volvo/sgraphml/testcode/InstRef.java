package org.eclipse.eatop.volvo.sgraphml.testcode;

import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;

public class InstRef {

	EAPrototype prototype;
	EAPort port;
	
	public InstRef(EAPrototype prototype, EAPort port) {

		this.prototype = prototype;
		this.port = port;
	}
	
	public EAPrototype getPrototype() {
		return prototype;
	}
	public void setPrototype(EAPrototype prototype) {
		this.prototype = prototype;
	}
	public EAPort getPort() {
		return port;
	}
	public void setPort(EAPort port) {
		this.port = port;
	}
}
