package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;

import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.testcode.InstRef;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class FunctionConnectorConnectionInstRefsProvider implements
		IConnectionInstRefsProvider {

	FunctionConnector fc;
	FunctionConnector_port source;
	FunctionConnector_port target;
	
	
	public FunctionConnectorConnectionInstRefsProvider(FunctionConnector fc) {
		this.fc = fc;
	}
	
	@Override
	public EObject getSourceInstancRef() {
		setSourceAndTarget();
		return source;
	}

	private void setSourceAndTarget() {
		EList<FunctionConnector_port> fcPorts = fc.getPort();
		FunctionConnector_port instref0 = fcPorts.get(0);
		FunctionConnector_port instref1 = fcPorts.get(1);
		
		FunctionPort fp0 = instref0.getFunctionPort(); 
		FunctionPort fp1 = instref1.getFunctionPort(); 

		EADirectionKind portDir0 = functionPortDirection(fp0);
		EADirectionKind portDir1 = functionPortDirection(fp1);
		
		switch (portDir0){
		case IN:
			source = instref1;
			target = instref0;
			break;
 		case INOUT:
 			if (portDir1 == EADirectionKind.OUT){
 				source = instref1;
 				target = instref0;
 			}
 			else {
 				source = instref0;
 				target = instref1;
 			}
			break;
		case OUT:
			source = instref0;
			target = instref1;
			break;
		}
	}

	@Override
	public EObject getTargetInstanceRef() {
		setSourceAndTarget();
		return target;
	}

	@Override
	public int getNumberOfPorts() {
		return fc.getPort().size();
	}

	
	EADirectionKind functionPortDirection(FunctionPort fp){
		if (fp instanceof FunctionFlowPort){
			return ((FunctionFlowPort)fp).getDirection();
		}
		else if (fp instanceof FunctionPowerPort){
			return EADirectionKind.INOUT;
		}

		Utils.showErrorMessage("Unsupported metaclass in functionPortDirection");
		return EADirectionKind.IN;
	}
	
	
}
