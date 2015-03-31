package org.eclipse.eatop.connectorcreator.eadl.utils;

import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_fromPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_toPort;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.HardwarePortConnector;

public class ConnectorUtils {

	public static boolean validateConnector(FunctionConnector connector) {
		if (connector.getPort() == null || connector.getPort().size() != 2
				|| ! validatePorts(connector.getPort().get(0).getFunctionPrototype(), connector.getPort().get(1).getFunctionPrototype(), 
				connector.getPort().get(0).getFunctionPort(), connector.getPort().get(1).getFunctionPort())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateConnector(HardwarePortConnector connector) {
		if (connector.getPort() == null || connector.getPort().size() != 2
				|| ! validatePorts(connector.getPort().get(0).getHardwareComponentPrototype(), connector.getPort().get(1).getHardwareComponentPrototype(), 
				connector.getPort().get(0).getHardwarePort(), connector.getPort().get(1).getHardwarePort())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateConnector(FaultFailurePropagationLink connector) {
		if (connector.getFromPort() == null || connector.getToPort() == null 
				|| !validatePorts(connector.getFromPort(), connector.getToPort())) {
			return false;
		}
		return true;
	}
	
	private static boolean validatePorts(EAPrototype functionPrototype,
			EAPrototype functionPrototype2, EAPort functionPort,
			EAPort functionPort2) {
		if ((functionPort != null && functionPrototype != null 
				&& functionPort2 != null)
				|| (functionPort != null && functionPrototype2 != null 
				&& functionPort2 != null)) {
			return true;
		}
		return false;
	}
	
	private static boolean validatePorts(FaultFailurePropagationLink_fromPort fromPort,	FaultFailurePropagationLink_toPort toPort) {
		if ((fromPort.getFaultFailurePort() != null && !fromPort.getErrorModelPrototype().isEmpty() 
				&& toPort.getFaultFailurePort() != null)
				|| (fromPort.getFaultFailurePort() != null && !toPort.getErrorModelPrototype().isEmpty() 
				&& toPort.getFaultFailurePort() != null)) {
			return true;
		}
		return false;
	}

}
