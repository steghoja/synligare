package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;

import org.eclipse.eatop.eastadl21.HardwarePortConnector;
import org.eclipse.eatop.eastadl21.HardwarePortConnector_port;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class HardwarePortConnectorInstRefsProvider implements
		IConnectionInstRefsProvider {

	HardwarePortConnector hpc;
	
	public HardwarePortConnectorInstRefsProvider(HardwarePortConnector hc) {
		this.hpc = hc;
	}
		
	@Override
	public EObject getSourceInstancRef() {
		EList<HardwarePortConnector_port> hcPorts = hpc.getPort();
		HardwarePortConnector_port sourcePort = hcPorts.get(0);
		return sourcePort;
	}

	@Override
	public EObject getTargetInstanceRef() {
		EList<HardwarePortConnector_port> hcPorts = hpc.getPort();
		HardwarePortConnector_port targetPort = hcPorts.get(1);

		return targetPort;
	}

	@Override
	public int getNumberOfPorts() {
		return hpc.getPort().size();
	}
}
	