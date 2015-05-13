package org.eclipse.eatop.volvo.sgraphml.testcode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
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
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;

public class ErrorModelTypeGroupNodePartsProvider implements
		IGroupNodePartsProvider {

	ErrorModelType emt;

	public ErrorModelTypeGroupNodePartsProvider(ErrorModelType adaptableObject){
		this.emt = adaptableObject;
}
	
	@Override
	public List<EAPrototype> getParts() {
		List<EAPrototype> prototypes = new ArrayList<EAPrototype>();

		for (ErrorModelPrototype emp :  (emt.getPart())){
				prototypes.add(emp);
		}
		return prototypes;
	}
	
	
}
