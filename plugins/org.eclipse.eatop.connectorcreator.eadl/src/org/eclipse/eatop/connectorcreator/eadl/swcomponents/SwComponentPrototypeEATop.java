package org.eclipse.eatop.connectorcreator.eadl.swcomponents;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.connectorcreator.eadl.ports.AssemblyPortEATop;
import org.eclipse.eatop.connectorcreator.eadl.ports.ErrorPortEATop;
import org.eclipse.eatop.connectorcreator.eadl.ports.FunctionPortEATop;
import org.eclipse.eatop.connectorcreator.eadl.ports.PortRepresentationAbstract.PortType;
import org.eclipse.eatop.connectorcreator.eadl.utils.ConnectorUtils;
import org.eclipse.eatop.connectorcreator.ports.PortPrototypeInterface;
import org.eclipse.eatop.connectorcreator.ports.PortRepresentationInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwComponentPrototypeInterface;
import org.eclipse.eatop.connectorcreator.swcomponents.SwCompositionInterface;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultFailurePort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.emf.ecore.EObject;

public class SwComponentPrototypeEATop implements SwComponentPrototypeInterface {
	EAPrototype prototype;
	SwCompositionEATop composition;
	
	public SwComponentPrototypeEATop(EAPrototype prototype) {
		this.prototype = prototype;
		this.composition = new SwCompositionEATop(prototype.eContainer());
	}
	
	@Override
	public String getName() {
		if (prototype instanceof FunctionPrototype) {
			return ((FunctionPrototype) prototype).getShortName();
		}
		if (prototype instanceof ErrorModelPrototype) {
			return ((ErrorModelPrototype) prototype).getShortName();
		}
		return "";
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
			} else if (portPrototype.getObject() instanceof FaultFailurePort) {
				result.add(new AssemblyPortEATop(portPrototype, this, PortType.FAULTFAILURE));
			}
		}
		return result;
	}

	@Override
	public List<PortPrototypeInterface> getPorts() {
		List<PortPrototypeInterface> result = new ArrayList<PortPrototypeInterface>();
		if (prototype instanceof DesignFunctionPrototype) {
			DesignFunctionPrototype designFunction = (DesignFunctionPrototype) prototype;
			if (designFunction.getType() != null) {
				for (FunctionPort port : designFunction.getType().getPort()) {
					result.add(new FunctionPortEATop(port));
				}	
			}
		} else if (prototype instanceof AnalysisFunctionPrototype) {
			AnalysisFunctionPrototype analysisFunction = (AnalysisFunctionPrototype) prototype;
			if (analysisFunction.getType() != null) {
				for (FunctionPort port : analysisFunction.getType().getPort()) {
					result.add(new FunctionPortEATop(port));
				}
			}
		} else if (prototype instanceof ErrorModelPrototype) {
			ErrorModelPrototype errorPrototype = (ErrorModelPrototype) prototype;
			if (errorPrototype.getType() != null) {
				for (FaultInPort port : errorPrototype.getType().getExternalFault()) {
					result.add(new ErrorPortEATop(port));
				}
				for (FailureOutPort port : errorPrototype.getType().getFailure()) {
					result.add(new ErrorPortEATop(port));
				}
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
