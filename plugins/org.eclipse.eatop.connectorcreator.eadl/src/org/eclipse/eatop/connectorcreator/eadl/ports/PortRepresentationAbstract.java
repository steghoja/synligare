package org.eclipse.eatop.connectorcreator.eadl.ports;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.connectorcreator.ports.PortRepresentationInterface;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface.PortDirection;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;

public abstract class PortRepresentationAbstract implements PortRepresentationInterface {
	
	public enum PortType {
		CLIENTSERVER, FLOW
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
		final FunctionConnector connector = Eastadl21Factory.eINSTANCE.createFunctionConnector();
		FunctionConnector_port portProvider = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
		FunctionConnector_port portReceiver = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();

		final AssemblyPortEATop providerElement = (AssemblyPortEATop) assemblyPortEATop;
		AssemblyPortEATop receiverElement = (AssemblyPortEATop) lowerElement;
		if (providerElement.port.getObject() instanceof FunctionPort
				&& providerElement.componentPrototype.getObject() instanceof DesignFunctionPrototype
				&& receiverElement.port.getObject() instanceof FunctionPort
				&& receiverElement.componentPrototype.getObject() instanceof DesignFunctionPrototype) {
			DesignFunctionPrototype designFunctionPrototype = (DesignFunctionPrototype) providerElement.componentPrototype.getObject();
			portProvider.setFunctionPort((FunctionPort) providerElement.port.getObject());
			portProvider.setFunctionPrototype(designFunctionPrototype);

			portReceiver.setFunctionPort((FunctionPort) receiverElement.port.getObject());
			portReceiver.setFunctionPrototype((DesignFunctionPrototype) receiverElement.componentPrototype.getObject());

			connector.getPort().add(portProvider);
			connector.getPort().add(portReceiver);

			connector.setShortName(getShortName(assemblyPortEATop, lowerElement));
			
			TransactionalEditingDomain editingDomain = WorkspaceEditingDomainUtil.getEditingDomain(designFunctionPrototype);
			try {
				WorkspaceTransactionUtil.executeInWriteTransaction(editingDomain,
						new Runnable() {
					@Override
					public void run() {
						if (providerElement.componentPrototype.getObject() instanceof DesignFunctionPrototype) {
							DesignFunctionPrototype d = (DesignFunctionPrototype) providerElement.componentPrototype.getObject();
							if (d.eContainer() instanceof DesignFunctionType) {
								DesignFunctionType functionType = (DesignFunctionType) d.eContainer();
								functionType.getConnector().add(connector);
							}
						}
					}
				}, "Creating connector");
			} catch (OperationCanceledException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	private void addConnection(DelegationPortEATop delegationElement,
			AssemblyPortEATop assemblyPortEATop) {
		final AssemblyPortEATop providerElement = (AssemblyPortEATop) assemblyPortEATop;
		final DelegationPortEATop receiverElement = (DelegationPortEATop) delegationElement;
		if (providerElement.port.getObject() instanceof FunctionPort
				&& providerElement.componentPrototype.getObject() instanceof DesignFunctionPrototype
				&& receiverElement.port.getObject() instanceof FunctionPort) {

			final FunctionConnector connector = Eastadl21Factory.eINSTANCE.createFunctionConnector();
			FunctionConnector_port portProvider = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
			FunctionConnector_port portReceiver = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
			
			DesignFunctionPrototype designFunctionPrototype = (DesignFunctionPrototype) providerElement.componentPrototype.getObject();
			portProvider.setFunctionPrototype(designFunctionPrototype);
			
			portProvider.setFunctionPort((FunctionPort) providerElement.port.getObject());
			portReceiver.setFunctionPort((FunctionPort) receiverElement.port.getObject());

			connector.getPort().add(portProvider);
			connector.getPort().add(portReceiver);

			connector.setShortName(getShortName(assemblyPortEATop, delegationElement));

			TransactionalEditingDomain editingDomain = WorkspaceEditingDomainUtil.getEditingDomain(designFunctionPrototype);
			try {
				WorkspaceTransactionUtil.executeInWriteTransaction(editingDomain,
						new Runnable() {
					@Override
					public void run() {
						if (receiverElement.swComposition.getObject() instanceof DesignFunctionType) {
							DesignFunctionType d = (DesignFunctionType) receiverElement.swComposition.getObject();
							d.getConnector().add(connector);
						}
					}
				}, "Creating connector");
			} catch (OperationCanceledException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getShortName(AssemblyPortEATop assembly, DelegationPortEATop delegation) {
		return (delegation.port.getName() + "_delegate_" + assembly.componentPrototype.getName() + "_" + assembly.port.getName());
	}
	
	private String getShortName(AssemblyPortEATop assemblyPort1,
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
		} else if (this instanceof DelegationPortEATop && portRepresentation instanceof DelegationPortEATop) {
			DelegationPortEATop delegationPort1 = (DelegationPortEATop) portRepresentation;
			DelegationPortEATop delegationPort2 = (DelegationPortEATop) this;
			delegationPort1.getConnector(delegationPort2);
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
