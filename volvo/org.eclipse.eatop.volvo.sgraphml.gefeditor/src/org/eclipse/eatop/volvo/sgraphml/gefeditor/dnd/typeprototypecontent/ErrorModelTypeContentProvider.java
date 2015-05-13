package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAConnector;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;

public class ErrorModelTypeContentProvider implements
		ITypePrototypeContentProvider {

	ErrorModelType emt;
	
	public ErrorModelTypeContentProvider(ErrorModelType adaptableObject){
			this.emt = adaptableObject;
	}
	
	@Override
	public List<EAPort> getInports() {
		List<EAPort> inports = new ArrayList<EAPort>();
		for (FaultInPort fip : emt.getExternalFault()) {
			inports.add((EAPort)fip);
		}
		return inports;
	}
	
	@Override
	public List<EAPort> getOutports() {
		List<EAPort> outports = new ArrayList<EAPort>();
		for (FailureOutPort fop : emt.getFailure()) {
			outports.add((EAPort)fop);
		}
		return outports;
	}

	@Override
	public List<EAPort> getInOutports() {
		return new ArrayList<EAPort>();
	}
	
	@Override
	public List<EAPrototype> getParts() {
		List<EAPrototype> prototypes = new ArrayList<EAPrototype>();
		for (ErrorModelPrototype emp :  (emt.getPart())){
				prototypes.add(emp);
		}
		return prototypes;
	}

	@Override
	public List<EAConnector> getConnectors() {
		List<EAConnector> connectors = new ArrayList<EAConnector>();
		for (EAConnector emp :  (emt.getFaultFailureConnector())){
			connectors.add(emp);
		}
		return connectors;
	}
	
}
