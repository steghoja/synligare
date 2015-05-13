package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;

import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.volvo.sgraphml.testcode.InstRef;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class FunctionConnectorConnectionInstRefsProvider implements
		IConnectionInstRefsProvider {

	FunctionConnector fc;
	
	public FunctionConnectorConnectionInstRefsProvider(FunctionConnector fc) {
		this.fc = fc;
	}
	
	@Override
	public EObject getSourceInstancRef() {
		EList<FunctionConnector_port> fcPorts = fc.getPort();
		FunctionConnector_port sourcePort = fcPorts.get(0);

		return sourcePort;
		//new InstRef(sourcePort.getFunctionPrototype(), sourcePort.getFunctionPort());
	}

	@Override
	public EObject getTargetInstanceRef() {
		EList<FunctionConnector_port> fcPorts = fc.getPort();
		FunctionConnector_port targetPort = fcPorts.get(1);

		return targetPort;//new InstRef(targetPort.getFunctionPrototype(), targetPort.getFunctionPort());
	}

	@Override
	public int getNumberOfPorts() {
		return fc.getPort().size();
	}

}
