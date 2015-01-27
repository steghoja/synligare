package org.eclipse.eatop.connectorcreator.eadl.utils;

import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;

public class ConnectorUtils {

	public static boolean validateConnector(FunctionConnector connector) {
		if (connector.getPort() == null || connector.getPort().size() != 2
				|| ! validatePorts(connector.getPort().get(0), connector.getPort().get(1))) {
			return false;
		}
		return true;
	}
	
	private static boolean validatePorts(FunctionConnector_port port1, FunctionConnector_port port2) {
		if ((port1.getFunctionPort() != null && port1.getFunctionPrototype() != null 
				&& port2.getFunctionPort() != null)
				|| (port1.getFunctionPort() != null && port2.getFunctionPort() != null 
				&& port2.getFunctionPrototype() != null)) {
			return true;
		}
		return false;
	}
}
