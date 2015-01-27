package org.eclipse.eatop.connectorcreator.eadl.swcomponents;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.eatop.connectorcreator.eadl.ports.AssemblyPortEATop;
import org.eclipse.eatop.connectorcreator.eadl.ports.PortPrototypeEATop;
import org.eclipse.eatop.connectorcreator.eadl.ports.PortRepresentationAbstract.PortType;
import org.eclipse.eatop.connectorcreator.eadl.utils.ConnectorUtils;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.connectorcreator.ports.PortRepresentationInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwComponentPrototypeInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwCompositionInterface;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.emf.ecore.EObject;

public class SwComponentPrototypeEATop implements SwComponentPrototypeInterface {
	FunctionPrototype prototype;
	SwCompositionEATop composition;
	
	public SwComponentPrototypeEATop(FunctionPrototype prototype) {
		this.prototype = prototype;
		this.composition = new SwCompositionEATop(prototype.eContainer());
	}
	
	@Override
	public String getName() {
		return prototype.getShortName();
	}
	
	@Override
	public Object getObject() {
		return prototype;
	}
	
	@Override
	public SwCompositionInterface getComposition() {
		return composition;
	}

	@Override
	public List<PortRepresentationInterface> getInitialList() {
		List<PortRepresentationInterface> result = new ArrayList<PortRepresentationInterface>();			
		for (PortPrototypeInterface portPrototype : getPorts()) {
			if (portPrototype.getObject() instanceof FunctionFlowPort) {
				result.add(new AssemblyPortEATop(portPrototype, this, PortType.FLOW));
			} else if (portPrototype.getObject() instanceof FunctionClientServerPort) {
				result.add(new AssemblyPortEATop(portPrototype, this, PortType.CLIENTSERVER));
			}
		}
		return result;
	}

	@Override
	public List<PortPrototypeInterface> getPorts() {
		List<PortPrototypeInterface> result = new ArrayList<PortPrototypeInterface>();
		if (prototype instanceof DesignFunctionPrototype) {
			DesignFunctionPrototype designFunction = (DesignFunctionPrototype) prototype;
			for (FunctionPort port : designFunction.getType().getPort()) {
				result.add(new PortPrototypeEATop(port));
			}
		} else if (prototype instanceof AnalysisFunctionPrototype) {
			AnalysisFunctionPrototype analysisFunction = (AnalysisFunctionPrototype) prototype;
			for (FunctionPort port : analysisFunction.getType().getPort()) {
				result.add(new PortPrototypeEATop(port));
			}
		}
		return result;
	}

	@Override
	public List<String> getErrorConnectors() {
		List<String> result = new ArrayList<String>();
		for (EObject element : prototype.eContainer().eContents()) {
			if (element instanceof FunctionConnector) {
				FunctionConnector connector = (FunctionConnector) element;
				if (! ConnectorUtils.validateConnector(connector)) {
					result.add(connector.getShortName());
				}
			} 
		}
		return result;
	}
}
