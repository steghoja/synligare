package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;

import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.HardwareConnector;
import org.eclipse.eatop.eastadl21.HardwareConnector_port;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;


//A HardwareConnector connects 2 HardwarePins
public class HardwareConnectorConnectionInstRefsProvider implements
		IConnectionInstRefsProvider {

	HardwareConnector hc;
	HardwareConnector_port source;
	HardwareConnector_port target;
	
	public HardwareConnectorConnectionInstRefsProvider(HardwareConnector hc) {
		this.hc = hc;
	}
		
	
	private void setSourceAndTarget() {
		EList<HardwareConnector_port> hcPorts = hc.getPort();
		HardwareConnector_port instref0 = hcPorts.get(0);
		HardwareConnector_port instref1 = hcPorts.get(1);
		
		HardwarePin pin0 = (HardwarePin)instref0.getHardwarePin();
		
		switch (pin0.getDirection()){
		case IN:
			source = instref1;
			target = instref0;
			break;
 		case INOUT:
 			HardwarePin pin1 = (HardwarePin)instref1.getHardwarePin();
 			if (pin1.getDirection() == EADirectionKind.OUT){
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
		return hc.getPort().size();
	}

}
	
		