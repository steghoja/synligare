package org.eclipse.eatop.connectorcreator.eadl.ports;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface.PortDirection;
import org.eclipse.eatop.connectorcreator.ports.PortRepresentationInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwComponentPrototypeInterface;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisFunctionType;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
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
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;

public class AssemblyPortEATop extends PortRepresentationAbstract {
	public SwComponentPrototypeInterface componentPrototype;
	public boolean isConnected;
	
	public AssemblyPortEATop(PortPrototypeInterface port, SwComponentPrototypeInterface prototype, PortType type, boolean isConnected) {
		super(port, type);
		this.isConnected = isConnected;
		componentPrototype = prototype;
	}
	
	public AssemblyPortEATop(PortPrototypeInterface port, SwComponentPrototypeInterface prototype, PortType type) {
		super(port, type);
		this.isConnected = false;
		componentPrototype = prototype;
	}
	
	@Override
	public String getPortName() {
		return port.getName();
	}
	
	@Override
	public List<PortRepresentationInterface> getAllPorts() {
		List<PortRepresentationInterface> result = new ArrayList<PortRepresentationInterface>();
		for (SwComponentPrototypeInterface prototype : componentPrototype.getComposition().getPrototypes()) {
			for (PortPrototypeInterface portPrototype : prototype.getPorts()) {
				if (portPrototype.getObject() instanceof FunctionFlowPort) {
					result.add(new AssemblyPortEATop(portPrototype, prototype, PortType.FLOW));
				} else if (portPrototype.getObject() instanceof FunctionClientServerPort) {
					result.add(new AssemblyPortEATop(portPrototype, prototype, PortType.CLIENTSERVER));
				} else if (portPrototype.getObject() instanceof FaultFailurePort) {
					result.add(new AssemblyPortEATop(portPrototype, prototype, PortType.FAULTFAILURE));
				}
			}
		}
		for (PortPrototypeInterface portPrototype : componentPrototype.getComposition().getPorts()) {
			if (portPrototype.getObject() instanceof FunctionFlowPort) {
				result.add(new DelegationPortEATop(portPrototype, componentPrototype.getComposition(), PortType.FLOW));
			} else if (portPrototype.getObject() instanceof FunctionClientServerPort) {
				result.add(new DelegationPortEATop(portPrototype, componentPrototype.getComposition(), PortType.CLIENTSERVER));
			} else if (portPrototype.getObject() instanceof FaultFailurePort) {
				result.add(new DelegationPortEATop(portPrototype, componentPrototype.getComposition(), PortType.FAULTFAILURE));
			}
		}
		return result;
	}
	
	@Override
	public List<PortRepresentationInterface> getRightList(PortDirection portDirection) {
		List<PortRepresentationInterface> result = new ArrayList<PortRepresentationInterface>();
		PortDirection assemblyDirection = null;
		PortDirection delegationDirection = null;
		
		if (portDirection.equals(PortDirection.IN)) {
			assemblyDirection = PortDirection.OUT;
			delegationDirection = PortDirection.IN;
		} else if (portDirection.equals(PortDirection.OUT)) {
			assemblyDirection = PortDirection.IN;
			delegationDirection = PortDirection.OUT;
		} else if (portDirection.equals(PortDirection.INOUT)) {
			assemblyDirection = PortDirection.INOUT;
			delegationDirection = PortDirection.INOUT;
		}
		
		for (PortRepresentationInterface abstractPort : getAllPorts()) {
			if (abstractPort instanceof PortRepresentationAbstract) {
				PortRepresentationAbstract eaPort = (PortRepresentationAbstract) abstractPort;
 				if (abstractPort instanceof AssemblyPortEATop && eaPort.port.getPortDirection().equals(assemblyDirection) && type.equals(eaPort.type) && !equals(eaPort)) {
					AssemblyPortEATop assembly = (AssemblyPortEATop) abstractPort;
					boolean connected = isConnected(assembly);
					result.add(new AssemblyPortEATop(assembly.port, assembly.componentPrototype, assembly.type, connected));
				}
				if (abstractPort instanceof DelegationPortEATop && eaPort.port.getPortDirection().equals(delegationDirection) && type.equals(eaPort.type) && !equals(eaPort)) {
					DelegationPortEATop delegation = (DelegationPortEATop) abstractPort;
					boolean connected = isConnected(delegation);
					result.add(new DelegationPortEATop(delegation.port, delegation.swComposition, delegation.type, connected));
				}
			}
		}
		return result;
	}

	@Override
	public String getContainerName() {
		return componentPrototype.getName();
	}

	@Override
	public boolean getIsConnected() {
		return isConnected;
	}
	
	@Override
	public void deleteConnection(PortRepresentationInterface lowerElement) {
		final Object connector = getConnector(lowerElement);
		TransactionalEditingDomain editingDomain = WorkspaceEditingDomainUtil.getEditingDomain(connector);

		try {
			WorkspaceTransactionUtil.executeInWriteTransaction(editingDomain,
					new Runnable() {
				@Override
				public void run() {
					if(componentPrototype.getObject() instanceof DesignFunctionPrototype) {
						DesignFunctionPrototype designFunction = (DesignFunctionPrototype) componentPrototype.getObject();
						boolean remove = designFunction.getType().getConnector().remove(connector);
						if (remove == false) {
							if (designFunction.eContainer() instanceof DesignFunctionType) {
								DesignFunctionType d = (DesignFunctionType) designFunction.eContainer();
								d.getConnector().remove(connector);
							}
						}
					} else if (componentPrototype.getObject() instanceof AnalysisFunctionPrototype) {
						AnalysisFunctionPrototype analysisFunction = (AnalysisFunctionPrototype) componentPrototype.getObject();
						boolean remove = analysisFunction.getType().getConnector().remove(connector);
						if (remove == false) {
							if (analysisFunction.eContainer() instanceof AnalysisFunctionType) {
								AnalysisFunctionType d = (AnalysisFunctionType) analysisFunction.eContainer();
								d.getConnector().remove(connector);
							}
						}
					} else if (componentPrototype.getObject() instanceof ErrorModelPrototype) {
						ErrorModelPrototype errorModelPrototype = (ErrorModelPrototype) componentPrototype.getObject();
						boolean remove = errorModelPrototype.getType().getFaultFailureConnector().remove(connector);
						if (remove == false) {
							if (errorModelPrototype.eContainer() instanceof ErrorModelType) {
								ErrorModelType d = (ErrorModelType) errorModelPrototype.eContainer();
								d.getFaultFailureConnector().remove(connector);
							}
						}
					}
				}
			}, "Deleting connector");
		} catch (OperationCanceledException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	protected Object getConnector(DelegationPortEATop outerPortRepresentation) {
		if (componentPrototype.getObject() instanceof FunctionPrototype) {
			FunctionPrototype component = (FunctionPrototype) componentPrototype.getObject();
			for (EObject element : component.eContainer().eContents()) {
				if (element instanceof FunctionConnector) {
					if (componentPrototype.getObject() instanceof FunctionPrototype
							&& port.getObject() instanceof FunctionPort
							&& outerPortRepresentation.port.getObject() instanceof FunctionPort) {
						FunctionConnector connector = (FunctionConnector) element;
						
						if (connector.getPort().size() != 2) {
							return null;
						}
						
						FunctionConnector_port connectorPort1 = connector.getPort().get(0);
						FunctionConnector_port connectorPort2 = connector.getPort().get(1);
						
						FunctionPort port1 = (FunctionPort) port.getObject();
						FunctionPrototype component1 = (FunctionPrototype) componentPrototype.getObject();

						FunctionPort port2 = (FunctionPort) outerPortRepresentation.port.getObject();

						FunctionPort functionPort1 = connectorPort1.getFunctionPort();
						FunctionPrototype functionPrototype1 = connectorPort1.getFunctionPrototype();
						
						FunctionPort functionPort2 = connectorPort2.getFunctionPort();
						FunctionPrototype functionPrototype2 = connectorPort2.getFunctionPrototype();
						
						if (port2.equals(functionPort1) && port1.equals(functionPort2) && component1.equals(functionPrototype2)) {
							return connector;
						} else if (port2.equals(functionPort2) && port1.equals(functionPort1) && component1.equals(functionPrototype1)) {
							return connector;
						}
					}
				}
			}
		}
		if (componentPrototype.getObject() instanceof ErrorModelPrototype) {
			ErrorModelPrototype component = (ErrorModelPrototype) componentPrototype.getObject();
			for (EObject element : component.eContainer().eContents()) {
				if (element instanceof FaultFailurePropagationLink) {
					if (componentPrototype.getObject() instanceof ErrorModelPrototype
							&& port.getObject() instanceof FaultFailurePort
							&& outerPortRepresentation.port.getObject() instanceof FaultFailurePort) {
						FaultFailurePropagationLink connector = (FaultFailurePropagationLink) element;
						FaultFailurePropagationLink_fromPort fromPort = connector.getFromPort();
						FaultFailurePropagationLink_toPort toPort = connector.getToPort();
						
						FaultFailurePort port1 = (FaultFailurePort) port.getObject();
						EObject component1 = (EObject) componentPrototype.getObject();
	
						FaultFailurePort port2 = (FaultFailurePort) outerPortRepresentation.port.getObject();
	
						FaultFailurePort fromFault = fromPort.getFaultFailurePort();
						EList<ErrorModelPrototype> fromErrorProtList = fromPort.getErrorModelPrototype();
						
						FaultFailurePort toFault = toPort.getFaultFailurePort();
						EList<ErrorModelPrototype> toErrorProtList = toPort.getErrorModelPrototype();

						if (port1.equals(fromFault) && port2.equals(toFault) && fromErrorProtList.contains(component1)) {
								return connector;
						} else if (port1.equals(toFault) && port2.equals(fromFault) && toErrorProtList.contains(component1)) {
								return connector;
						}
					}
				}
			}
		}
		return null;
	}
	
	protected Object getConnector(AssemblyPortEATop portRepresentation) {
		if (componentPrototype.getObject() instanceof EAPrototype) {
			EAPrototype component = (EAPrototype) componentPrototype.getObject();
			for (EObject element : component.eContainer().eContents()) {
				if (element instanceof FunctionConnector) {
					if (componentPrototype.getObject() instanceof FunctionPrototype
							&& portRepresentation.componentPrototype.getObject() instanceof FunctionPrototype
							&& port.getObject() instanceof FunctionPort
							&& portRepresentation.port.getObject() instanceof FunctionPort) {
						FunctionConnector connector = (FunctionConnector) element;
						
						if (connector.getPort().size() != 2) {
							return null;
						}
						
						FunctionConnector_port connectorPort1 = connector.getPort().get(0);
						FunctionConnector_port connectorPort2 = connector.getPort().get(1);
						
						FunctionPort port1 = (FunctionPort) port.getObject();
						FunctionPrototype component1 = (FunctionPrototype) componentPrototype.getObject();

						FunctionPort port2 = (FunctionPort) portRepresentation.port.getObject();
						FunctionPrototype component2 = (FunctionPrototype) portRepresentation.componentPrototype.getObject();

						FunctionPort functionPort1 = connectorPort1.getFunctionPort();
						FunctionPrototype functionPrototype1 = connectorPort1.getFunctionPrototype();
						
						FunctionPort functionPort2 = connectorPort2.getFunctionPort();
						FunctionPrototype functionPrototype2 = connectorPort2.getFunctionPrototype();

						if (port1.equals(functionPort1) && component1.equals(functionPrototype1)) {
							if(port2.equals(functionPort2) && component2.equals(functionPrototype2)) {
								return connector;
							} 
						} else if (port1.equals(functionPort2) && component1.equals(functionPrototype2)) {
							if (port2.equals(functionPort1) && component2.equals(functionPrototype1)) {
								return connector;
							}
						}
					}
				} if (element instanceof FaultFailurePropagationLink) {
					if (componentPrototype.getObject() instanceof ErrorModelPrototype
							&& portRepresentation.componentPrototype.getObject() instanceof ErrorModelPrototype
							&& port.getObject() instanceof FaultFailurePort
							&& portRepresentation.port.getObject() instanceof FaultFailurePort) {
						FaultFailurePropagationLink connector = (FaultFailurePropagationLink) element;
						FaultFailurePropagationLink_fromPort fromPort = connector.getFromPort();
						FaultFailurePropagationLink_toPort toPort = connector.getToPort();
						
						FaultFailurePort port1 = (FaultFailurePort) port.getObject();
						EObject component1 = (EObject) componentPrototype.getObject();
	
						FaultFailurePort port2 = (FaultFailurePort) portRepresentation.port.getObject();
						EObject component2 = (EObject) portRepresentation.componentPrototype.getObject();
	
						FaultFailurePort fromFault = fromPort.getFaultFailurePort();
						EList<ErrorModelPrototype> fromErrorProtList = fromPort.getErrorModelPrototype();
						
						FaultFailurePort toFault = toPort.getFaultFailurePort();
						EList<ErrorModelPrototype> toErrorProtList = toPort.getErrorModelPrototype();

						if (port1.equals(fromFault) && fromErrorProtList.contains(component1)) {
							if(port2.equals(toFault) && toErrorProtList.contains(component2)) {
								return connector;
							} 
								return connector;
						} else if (port1.equals(toFault) && toErrorProtList.contains(component1)) {
							if (port2.equals(fromFault) && fromErrorProtList.contains(component2)) {
								return connector;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public boolean equals(Object object) {
		if (object instanceof AssemblyPortEATop) {
			AssemblyPortEATop portRepresentation = (AssemblyPortEATop) object;
			if (componentPrototype.getObject() instanceof FunctionPrototype
					&& portRepresentation.componentPrototype.getObject() instanceof FunctionPrototype
					&& port.getObject() instanceof FunctionPort
					&& portRepresentation.port.getObject() instanceof FunctionPort) {
				
				FunctionPort port1 = (FunctionPort) port.getObject();
				FunctionPrototype component1 = (FunctionPrototype) componentPrototype.getObject();

				FunctionPort port2 = (FunctionPort) portRepresentation.port.getObject();
				FunctionPrototype component2 = (FunctionPrototype) portRepresentation.componentPrototype.getObject();
				
				return port1.equals(port2) && component1.equals(component2);
			}
		}
		return false;
	}
}
