package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.eatop.eastadl21.ClampConnector;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.HardwareConnector;
import org.eclipse.eatop.eastadl21.HardwarePortConnector;

public class ConnectionInstRefsAdapterFactory implements IAdapterFactory {


	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IConnectionInstRefsProvider.class){

			if (adaptableObject instanceof FunctionConnector)
				return new FunctionConnectorConnectionInstRefsProvider((FunctionConnector)adaptableObject);

			if (adaptableObject instanceof HardwareConnector)
				return new HardwareConnectorConnectionInstRefsProvider((HardwareConnector)adaptableObject);

			if (adaptableObject instanceof FaultFailurePropagationLink)
				return new FaultFailurePropagationLinkConnectionInstRefsProvider((FaultFailurePropagationLink)adaptableObject);
			
			if (adaptableObject instanceof HardwarePortConnector)
				return new HardwarePortConnectorInstRefsProvider((HardwarePortConnector)adaptableObject);

			if (adaptableObject instanceof ClampConnector)
				return new ClampConnectorConnectionInstRefsProvider((ClampConnector)adaptableObject);

		}
		return null;
	}


	@Override
	public Class[] getAdapterList() {

		return new Class[]{ 
				FunctionConnector.class,
				HardwareConnector.class,
				FaultFailurePropagationLink.class,
				HardwarePortConnector.class 
		};
	}

}
