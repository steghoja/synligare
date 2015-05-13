package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FaultFailurePropagationLink;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.eastadl21.HardwareConnector;
import org.eclipse.eatop.eastadl21.HardwarePortConnector;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;

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
