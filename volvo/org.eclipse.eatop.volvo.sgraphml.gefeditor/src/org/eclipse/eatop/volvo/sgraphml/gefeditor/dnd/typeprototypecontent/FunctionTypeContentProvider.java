package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisFunctionType;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAConnector;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.EAType;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;

public class FunctionTypeContentProvider implements
		ITypePrototypeContentProvider {


	FunctionType ft;
	
	public FunctionTypeContentProvider(FunctionType adaptableObject){
			this.ft = adaptableObject;
	}
	
	@Override
	public List<EAPort> getInports() {
		return getPorts(EADirectionKind.IN);
	}

	@Override
	public List<EAPort> getOutports() {
		return getPorts(EADirectionKind.OUT);
	}

	@Override
	public List<EAPort> getInOutports() {
		return getPorts(EADirectionKind.INOUT);
	}

	protected List<EAPort> getPorts(EADirectionKind kind) {
		List<FunctionPort>ports = ft.getPort();
		List<EAPort> foundports = new ArrayList<EAPort>();
		
		for (FunctionPort fp : ports) {
			if (!(fp instanceof FunctionFlowPort)){
				Utils.showErrorMessage("Only FunctionFlowPort supported");
			}
			else {
				FunctionFlowPort ffp = (FunctionFlowPort)fp;

				if (ffp.getDirection() == kind){
					foundports.add(ffp);
				}
			}
		}
		return foundports;
	}
	
	@Override
	public List<EAPrototype> getParts() {
		List<EAPrototype> prototypes = new ArrayList<EAPrototype>();

		if (ft instanceof DesignFunctionType){
			for (DesignFunctionPrototype dfp :  ((DesignFunctionType)ft).getPart()){
				prototypes.add(dfp);
			}
		}
		else if (ft instanceof AnalysisFunctionType){
				for (AnalysisFunctionPrototype afp :  ((AnalysisFunctionType)ft).getPart()){
					prototypes.add(afp);
				}
		}				 		
		return prototypes;
	}

	@Override
	public List<EAConnector> getConnectors() {
		List<EAConnector> connectors = new ArrayList<EAConnector>();
		for (EAConnector emp :  (ft.getConnector())){
			connectors.add(emp);
		}
		return connectors;
	}


}
