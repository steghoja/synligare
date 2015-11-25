package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;

import org.eclipse.eatop.eastadl21.ClampConnector;
import org.eclipse.eatop.eastadl21.ClampConnector_port;
import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class ClampConnectorConnectionInstRefsProvider implements
		IConnectionInstRefsProvider {

	ClampConnector cc;
	ClampConnector_port source;
	ClampConnector_port target;
	
	
	public ClampConnectorConnectionInstRefsProvider(ClampConnector fc) {
		this.cc = fc;
	}
	
	@Override
	public EObject getSourceInstancRef() {
		setSourceAndTarget();
		return source;
	}

	private void setSourceAndTarget() {
		EList<ClampConnector_port> ccPorts = cc.getPort();
		ClampConnector_port instref0 = ccPorts.get(0);
		ClampConnector_port instref1 = ccPorts.get(1);
		
		FunctionFlowPort port0 = (FunctionFlowPort)instref0.getFunctionPort();
		switch (port0.getDirection()){
		case IN:
			source = instref1;
			target = instref0;
			break;
 		case INOUT:
 			FunctionFlowPort port1 = (FunctionFlowPort)instref1.getFunctionPort();
 			if (port1.getDirection() == EADirectionKind.OUT){
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
		return cc.getPort().size();
	}

}
