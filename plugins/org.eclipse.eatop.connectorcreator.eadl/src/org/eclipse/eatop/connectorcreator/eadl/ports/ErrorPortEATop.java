package org.eclipse.eatop.connectorcreator.eadl.ports;

import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultInPort;


public class ErrorPortEATop implements PortPrototypeInterface {
	private FaultFailurePort port;

	public ErrorPortEATop(FaultFailurePort port) {
		this.port = port;
	}

	@Override
	public String getName() {
		return port.getShortName();
	}

	@Override
	public Object getObject() {
		return port;
	}

	@Override
	public PortDirection getPortDirection() {
		if (port instanceof FaultInPort) {
			return PortDirection.IN;
		} else if (port instanceof FailureOutPort) {
			return PortDirection.OUT;
		}
		return null;
	}
}
