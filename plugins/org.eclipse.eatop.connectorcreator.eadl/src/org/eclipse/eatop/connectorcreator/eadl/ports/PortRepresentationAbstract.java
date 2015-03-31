package org.eclipse.eatop.connectorcreator.eadl.ports;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface.PortDirection;
import org.eclipse.eatop.connectorcreator.ports.PortRepresentationInterface;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_fromPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_toPort;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;

public abstract class PortRepresentationAbstract implements PortRepresentationInterface {
	
	public enum PortType {
		CLIENTSERVER, FLOW, FAULTFAILURE
	}
	
	public PortPrototypeInterface port;
	public PortType type;
	
	public PortRepresentationAbstract (PortPrototypeInterface port, PortType type) {
		this.port = port;
		this.type = type;
	}
	
	@Override
	public void addConnection(PortRepresentationInterface lowerElement) {
		if (this instanceof AssemblyPortEATop && lowerElement instanceof AssemblyPortEATop) {
			addConnection((AssemblyPortEATop) this, (AssemblyPortEATop) lowerElement);
		} else if (this instanceof DelegationPortEATop && lowerElement instanceof AssemblyPortEATop) {
			addConnection((DelegationPortEATop) this, (AssemblyPortEATop) lowerElement);
		} else if (this instanceof AssemblyPortEATop && lowerElement instanceof DelegationPortEATop) {
			addConnection((DelegationPortEATop) lowerElement, (AssemblyPortEATop) this);
		}
	}

	private void addConnection(AssemblyPortEATop assemblyPortEATop,
			AssemblyPortEATop lowerElement) {
		final AssemblyPortEATop providerElement = (AssemblyPortEATop) assemblyPortEATop;
		AssemblyPortEATop receiverElement = (AssemblyPortEATop) lowerElement;
		Runnable run = null;
		EAPrototype edit = null;
		if (providerElement.port.getObject() instanceof FunctionPort
				&& providerElement.componentPrototype.getObject() instanceof FunctionPrototype
				&& receiverElement.port.getObject() instanceof FunctionPort
				&& receiverElement.componentPrototype.getObject() instanceof FunctionPrototype) {
			final FunctionConnector connector = Eastadl21Factory.eINSTANCE.createFunctionConnector();
			FunctionConnector_port portProvider = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
			FunctionConnector_port portReceiver = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
			
			FunctionPrototype functionPrototype = (FunctionPrototype) providerElement.componentPrototype.getObject();
			portProvider.setFunctionPort((FunctionPort) providerElement.port.getObject());
			portProvider.setFunctionPrototype(functionPrototype);

			portReceiver.setFunctionPort((FunctionPort) receiverElement.port.getObject());
			portReceiver.setFunctionPrototype((FunctionPrototype) receiverElement.componentPrototype.getObject());

			connector.getPort().add(portProvider);
			connector.getPort().add(portReceiver);

			connector.setShortName(getAssemblyConnectorName(assemblyPortEATop, lowerElement));
			edit = functionPrototype;
			run = new Runnable() {
				@Override
				public void run() {
					if (providerElement.componentPrototype.getObject() instanceof FunctionPrototype) {
						FunctionPrototype d = (FunctionPrototype) providerElement.componentPrototype.getObject();
						if (d.eContainer() instanceof FunctionType) {
							FunctionType functionType = (FunctionType) d.eContainer();
							functionType.getConnector().add(connector);
						}
					}
				}
			};
		}
		if (providerElement.port.getObject() instanceof FaultFailurePort
				&& providerElement.componentPrototype.getObject() instanceof ErrorModelPrototype
				&& receiverElement.port.getObject() instanceof FaultFailurePort
				&& receiverElement.componentPrototype.getObject() instanceof ErrorModelPrototype) {
			final FaultFailurePropagationLink connector = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink();
			FaultFailurePropagationLink_fromPort portProvider = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_fromPort();
			FaultFailurePropagationLink_toPort portReceiver = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_toPort();
			
			ErrorModelPrototype errorModelPrototype = (ErrorModelPrototype) providerElement.componentPrototype.getObject();
			portProvider.setFaultFailurePort((FaultFailurePort) providerElement.port.getObject());
			portProvider.getErrorModelPrototype().add(errorModelPrototype);

			portReceiver.setFaultFailurePort((FaultFailurePort) receiverElement.port.getObject());
			portReceiver.getErrorModelPrototype().add((ErrorModelPrototype) receiverElement.componentPrototype.getObject());

			connector.setFromPort(portProvider);
			connector.setToPort(portReceiver);

			connector.setShortName(getAssemblyConnectorName(assemblyPortEATop, lowerElement));
			edit = errorModelPrototype;
			run = new Runnable() {
				@Override
				public void run() {
					if (providerElement.componentPrototype.getObject() instanceof ErrorModelPrototype) {
						ErrorModelPrototype d = (ErrorModelPrototype) providerElement.componentPrototype.getObject();
						if (d.eContainer() instanceof ErrorModelType) {
							ErrorModelType functionType = (ErrorModelType) d.eContainer();
							functionType.getFaultFailureConnector().add(connector);
						}
					}
				}
			};
		}
		if (edit != null && run != null) {
			executeWrite(run, edit);
		}
	}

	private void addConnection(DelegationPortEATop delegationElement,
			AssemblyPortEATop assemblyPortEATop) {
		final AssemblyPortEATop providerElement = (AssemblyPortEATop) assemblyPortEATop;
		final DelegationPortEATop receiverElement = (DelegationPortEATop) delegationElement;
		Runnable run = null;
		EAPrototype edit = null;
		if (providerElement.port.getObject() instanceof FunctionPort
				&& providerElement.componentPrototype.getObject() instanceof FunctionPrototype
				&& receiverElement.port.getObject() instanceof FunctionPort) {

			final FunctionConnector connector = Eastadl21Factory.eINSTANCE.createFunctionConnector();
			FunctionConnector_port portProvider = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
			FunctionConnector_port portReceiver = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
			
			FunctionPrototype functionPrototype = (FunctionPrototype) providerElement.componentPrototype.getObject();
			portProvider.setFunctionPrototype(functionPrototype);
			
			portProvider.setFunctionPort((FunctionPort) providerElement.port.getObject());
			portReceiver.setFunctionPort((FunctionPort) receiverElement.port.getObject());

			connector.getPort().add(portProvider);
			connector.getPort().add(portReceiver);

			connector.setShortName(getDelegateConnectorName(assemblyPortEATop, delegationElement));
			edit = functionPrototype;
			run = new Runnable() {
				@Override
				public void run() {
					if (receiverElement.swComposition.getObject() instanceof FunctionType) {
						FunctionType d = (FunctionType) receiverElement.swComposition.getObject();
						d.getConnector().add(connector);
					}
				}
			};
		}
		
		if (providerElement.port.getObject() instanceof FaultFailurePort
				&& providerElement.componentPrototype.getObject() instanceof ErrorModelPrototype
				&& receiverElement.port.getObject() instanceof FaultFailurePort) {

			final FaultFailurePropagationLink connector = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink();
			FaultFailurePropagationLink_fromPort portProvider = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_fromPort();
			FaultFailurePropagationLink_toPort portReceiver = Eastadl21Factory.eINSTANCE.createFaultFailurePropagationLink_toPort();
			
			ErrorModelPrototype errorModelPrototype = (ErrorModelPrototype) providerElement.componentPrototype.getObject();
			portProvider.getErrorModelPrototype().add(errorModelPrototype);
			
			portProvider.setFaultFailurePort((FaultFailurePort) providerElement.port.getObject());
			portReceiver.setFaultFailurePort((FaultFailurePort) receiverElement.port.getObject());

			connector.setFromPort(portProvider);
			connector.setToPort(portReceiver);

			connector.setShortName(getDelegateConnectorName(assemblyPortEATop, delegationElement));
			edit = errorModelPrototype;
			run = new Runnable() {
				@Override
				public void run() {
					if (receiverElement.swComposition.getObject() instanceof ErrorModelType) {
						ErrorModelType d = (ErrorModelType) receiverElement.swComposition.getObject();
						d.getFaultFailureConnector().add(connector);
					}
				}
			};
		}
		
		if (run != null && edit != null) {
			executeWrite(run, edit);
		}
	}
	
	private void executeWrite(final Runnable run, final EAPrototype edit) {
		TransactionalEditingDomain editingDomain = WorkspaceEditingDomainUtil.getEditingDomain(edit);
		try {
			WorkspaceTransactionUtil.executeInWriteTransaction(editingDomain, run , "Creating connector");
		} catch (OperationCanceledException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

	private String getDelegateConnectorName(AssemblyPortEATop assembly, DelegationPortEATop delegation) {
		return (delegation.port.getName() + "_delegate_" + assembly.componentPrototype.getName() + "_" + assembly.port.getName());
	}
	
	private String getAssemblyConnectorName(AssemblyPortEATop assemblyPort1,
			AssemblyPortEATop assemblyPort2) {
		AssemblyPortEATop provider;
		AssemblyPortEATop receiver;
		if (assemblyPort1.getPortDirection().equals(PortDirection.IN)) {
			provider = assemblyPort2;
			receiver = assemblyPort1;
		} else {
			provider = assemblyPort1;
			receiver = assemblyPort2;
		}
		
		return provider.componentPrototype.getName()
				+ "_" + provider.port.getName() + "_to_"
				+ receiver.componentPrototype.getName() + "_"
				+ receiver.port.getName();
	}
	
	@Override
	public Object getConnector(PortRepresentationInterface portRepresentation) {
		if (this instanceof AssemblyPortEATop && portRepresentation instanceof AssemblyPortEATop) {
			AssemblyPortEATop assemblyPort1 = (AssemblyPortEATop) this;
			AssemblyPortEATop assemblyPort2 = (AssemblyPortEATop) portRepresentation;
			return assemblyPort1.getConnector(assemblyPort2);
		} else if (this instanceof AssemblyPortEATop && portRepresentation instanceof DelegationPortEATop) {
			AssemblyPortEATop assemblyPort = (AssemblyPortEATop) this;
			DelegationPortEATop delegationPort = (DelegationPortEATop) portRepresentation;
			return assemblyPort.getConnector(delegationPort);
		} else if (this instanceof DelegationPortEATop && portRepresentation instanceof AssemblyPortEATop) {
			AssemblyPortEATop assemblyPort = (AssemblyPortEATop) portRepresentation;
			DelegationPortEATop delegationPort = (DelegationPortEATop) this;
			return assemblyPort.getConnector(delegationPort);
		} 
		return null;
	}
	
	@Override
	public boolean isConnected(PortRepresentationInterface portRepresentation) {
		return getConnector(portRepresentation) != null;
	}
	
	@Override
	public PortDirection getPortDirection() {
		return port.getPortDirection();
	}
}
