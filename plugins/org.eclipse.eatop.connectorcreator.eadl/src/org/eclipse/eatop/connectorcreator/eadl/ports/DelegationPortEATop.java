package org.eclipse.eatop.connectorcreator.eadl.ports;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface.PortDirection;
import org.eclipse.eatop.connectorcreator.ports.PortRepresentationInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwComponentPrototypeInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwCompositionInterface;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_fromPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_toPort;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class DelegationPortEATop extends PortRepresentationAbstract {
	public SwCompositionInterface swComposition;
	private boolean isConnected;
	
	public DelegationPortEATop(PortPrototypeInterface port, SwCompositionInterface composition, PortType type, boolean isConnected) {
		super(port, type);
		this.isConnected = isConnected;
		swComposition = composition;
	}

	public DelegationPortEATop(PortPrototypeInterface port, SwCompositionInterface composition, PortType type) {
		super(port, type);
		this.isConnected = false;
		swComposition = composition;
	}
	
	@Override
	public String getPortName() {
		return port.getName();
	}
	
	@Override
	public List<PortRepresentationInterface> getRightList(PortDirection direction) {
		// The selected element in the left list can never be a DelegationPortNode.
		return null;
	}
	
	@Override
	public List<PortRepresentationInterface> getAllPorts() {
		List<PortRepresentationInterface> result = new ArrayList<PortRepresentationInterface>();
		for (SwComponentPrototypeInterface componentPrototype : swComposition.getPrototypes()) {
			for (PortPrototypeInterface portPrototype : componentPrototype.getPorts()) {
				if (portPrototype.getObject() instanceof FunctionFlowPort) {
					result.add(new AssemblyPortEATop(portPrototype, componentPrototype, PortType.FLOW));
				} else if (portPrototype.getObject() instanceof FunctionClientServerPort) {
					result.add(new AssemblyPortEATop(portPrototype, componentPrototype, PortType.CLIENTSERVER));
				} else if (portPrototype.getObject() instanceof FaultFailurePort) {
					result.add(new AssemblyPortEATop(portPrototype, componentPrototype, PortType.FAULTFAILURE));
				}
			}
		}
		for (PortPrototypeInterface portRepresentationInterface : swComposition.getPorts()) {
			if (swComposition instanceof SwCompositionInterface) {
				SwCompositionInterface composition = (SwCompositionInterface) swComposition;
				if (portRepresentationInterface.getObject() instanceof FunctionFlowPort) {
					result.add(new DelegationPortEATop(portRepresentationInterface, composition, PortType.FLOW));
				} else if (portRepresentationInterface.getObject() instanceof FunctionClientServerPort) {
					result.add(new DelegationPortEATop(portRepresentationInterface, composition, PortType.CLIENTSERVER));
				} else if (portRepresentationInterface.getObject() instanceof FaultFailurePort) {
					result.add(new DelegationPortEATop(portRepresentationInterface, composition, PortType.FAULTFAILURE));
				}
			}
		}
		return result;
	}

	@Override
	public String getContainerName() {
		return swComposition.getName();
	}

	@Override
	public boolean getIsConnected() {
		return isConnected;
	}

	@Override
	public void deleteConnection(PortRepresentationInterface lowerElement) {
		//Never gets here since left list only contains AssemblyPorts
	}



	@Override
	public boolean isConnected(PortRepresentationInterface portRepresentation) {
		if (portRepresentation instanceof AssemblyPortEATop) {
			AssemblyPortEATop assembly = (AssemblyPortEATop) portRepresentation;
			return assembly.isConnected(this);
		}
		return false;
	}
}
