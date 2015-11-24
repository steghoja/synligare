package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;

import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.HardwareConnector_port;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.HardwarePort;
import org.eclipse.eatop.eastadl21.HardwarePortConnector;
import org.eclipse.eatop.eastadl21.HardwarePortConnector_port;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent.HardwareComponentTypeContentProvider;

//A HardwarePortConnector connects 2 HardwarePorts
public class HardwarePortConnectorInstRefsProvider implements
		IConnectionInstRefsProvider {

	HardwarePortConnector hpc;
	HardwarePortConnector_port source;
	HardwarePortConnector_port target;
	
	public HardwarePortConnectorInstRefsProvider(HardwarePortConnector hc) {
		this.hpc = hc;
	}
		
	private void setSourceAndTarget() {
		EList<HardwarePortConnector_port> hpcPorts = hpc.getPort();
		HardwarePortConnector_port instref0 = hpcPorts.get(0);
		HardwarePortConnector_port instref1 = hpcPorts.get(1);
		
		HardwarePort port0 = (HardwarePort)instref0.getHardwarePort();
		EADirectionKind direction0 = HardwareComponentTypeContentProvider.hwPortDirectionKind(port0);
		switch (direction0){
		case IN:
			source = instref1;
			target = instref0;
			break;
 		case INOUT:
 			HardwarePort port1 = (HardwarePort)instref1.getHardwarePort();
 			EADirectionKind direction1 = HardwareComponentTypeContentProvider.hwPortDirectionKind(port1);
 			if (direction1 == EADirectionKind.OUT){
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
	public EObject getSourceInstancRef() {
		setSourceAndTarget();
		return source;
	}

	@Override
	public EObject getTargetInstanceRef() {
		setSourceAndTarget();
		return target;
	}

	@Override
	public int getNumberOfPorts() {
		return hpc.getPort().size();
	}
}
	