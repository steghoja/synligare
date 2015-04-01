package org.eclipse.eatop.connectorcreator.eadl.ports;

import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.eastadl21.ClientServerKind;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;


public class FunctionPortEATop implements PortPrototypeInterface {
	private FunctionPort port;

	public FunctionPortEATop(FunctionPort port) {
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
		if (port instanceof FunctionFlowPort) {
			FunctionFlowPort funcPort = (FunctionFlowPort) port;
			if (funcPort.getDirection().equals(EADirectionKind.IN)) {
				return PortDirection.IN;
			} else if (funcPort.getDirection().equals(EADirectionKind.OUT)) {
				return PortDirection.OUT;
			} else if (funcPort.getDirection().equals(EADirectionKind.INOUT)) {
				return PortDirection.INOUT;
			}
		} else if (port instanceof FunctionClientServerPort) {
			FunctionClientServerPort funcPort = (FunctionClientServerPort) port;
			if (funcPort.getKind().equals(ClientServerKind.CLIENT)) {
				return PortDirection.IN;
			} else if (funcPort.getKind().equals(ClientServerKind.SERVER)) {
				return PortDirection.OUT;
			}
		}
		return null;
	}

}
