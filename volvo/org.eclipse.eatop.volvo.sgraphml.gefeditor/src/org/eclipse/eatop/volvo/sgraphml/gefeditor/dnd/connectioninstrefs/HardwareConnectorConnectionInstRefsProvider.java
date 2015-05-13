package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;

import org.eclipse.eatop.eastadl21.EAConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.HardwareConnector;
import org.eclipse.eatop.eastadl21.HardwareConnector_port;
import org.eclipse.eatop.volvo.sgraphml.testcode.InstRef;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class HardwareConnectorConnectionInstRefsProvider implements
		IConnectionInstRefsProvider {

	HardwareConnector hc;
	
	public HardwareConnectorConnectionInstRefsProvider(HardwareConnector hc) {
		this.hc = hc;
	}
		
	@Override
	public EObject getSourceInstancRef() {
		EList<HardwareConnector_port> hcPorts = hc.getPort();
		HardwareConnector_port sourcePort = hcPorts.get(0);

		return sourcePort;
		//new InstRef(sourcePort.getFunctionPrototype(), sourcePort.getFunctionPort());
	}

	@Override
	public EObject getTargetInstanceRef() {
		EList<HardwareConnector_port> hcPorts = hc.getPort();
		HardwareConnector_port targetPort = hcPorts.get(1);

		return targetPort;
	}

	@Override
	public int getNumberOfPorts() {
		return hc.getPort().size();
	}

}
	
		