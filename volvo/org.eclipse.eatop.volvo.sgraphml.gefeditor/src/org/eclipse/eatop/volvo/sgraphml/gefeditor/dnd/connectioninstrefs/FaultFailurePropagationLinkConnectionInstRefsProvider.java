package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;


import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_fromPort;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink_toPort;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.emf.ecore.EObject;

public class FaultFailurePropagationLinkConnectionInstRefsProvider implements
		IConnectionInstRefsProvider {


	FaultFailurePropagationLink ffpl;
	
	public FaultFailurePropagationLinkConnectionInstRefsProvider(FaultFailurePropagationLink ffpl) {
		this.ffpl = ffpl;
	}
		
	@Override
	public EObject getSourceInstancRef() {
		FaultFailurePropagationLink_fromPort port = ffpl.getFromPort();
		
		if(port.getErrorModelPrototype().size() > 1)
		{
			Utils.showErrorMessage("Not implemented support for inst refs with more than one prototype.");
		}
//		ErrorModelPrototype prototype = port.getErrorModelPrototype().get(0);
		return (EObject)port; //new InstRef(prototype, port.getFaultFailurePort());
	}

	@Override
	public EObject getTargetInstanceRef() {
	FaultFailurePropagationLink_toPort port = ffpl.getToPort();
		
		if(port.getErrorModelPrototype().size() > 1)
		{
			Utils.showErrorMessage("Not implemented support for inst refs with more than one prototype.");
		}
//		ErrorModelPrototype prototype = port.getErrorModelPrototype().get(0);
		return (EObject)port; //new InstRef(prototype, port.getFaultFailurePort());
	}

	@Override
	public int getNumberOfPorts() {
		int nPorts = 0;
		if (ffpl.getFromPort() != null){
			nPorts++;
		}
		if (ffpl.getToPort() != null){
			nPorts++;
		}
		
		return nPorts;
	}
}
