package org.eclipse.eatop.volvo.sgraphml.testcode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisFunctionType;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.HardwarePort;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.emf.common.util.EList;

public class HardwareComponentTypeGroupNodePartsProvider implements
		IGroupNodePartsProvider {

	HardwareComponentType hct;

	public HardwareComponentTypeGroupNodePartsProvider(HardwareComponentType adaptableObject){
			this.hct = adaptableObject;
	}

	@Override
	public List<EAPrototype> getParts() {
		List<EAPrototype> prototypes = new ArrayList<EAPrototype>();

		for (HardwareComponentPrototype hcp :  (hct.getPart())){
				prototypes.add(hcp);
		}
		return prototypes;
	}
	
}