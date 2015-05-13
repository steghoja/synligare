package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent;

import java.util.List;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAConnector;
import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentType;

public class HardwareComponentPrototypeContentProvider implements
		ITypePrototypeContentProvider {

	HardwareComponentTypeContentProvider typeProvider;
	
	public HardwareComponentPrototypeContentProvider(HardwareComponentPrototype adaptableObject){
			this.typeProvider = new HardwareComponentTypeContentProvider(adaptableObject.getType());
	}

	@Override
	public List<EAPort> getInports() {
		return typeProvider.getInports();
	}

	@Override
	public List<EAPort> getOutports() {
		return typeProvider.getOutports();
	}

	@Override
	public List<EAPort> getInOutports() {
		return typeProvider.getInOutports();
	}

	@Override
	public List<EAPrototype> getParts() {
		return typeProvider.getParts();
	}

	@Override
	public List<EAConnector> getConnectors() {
		return typeProvider.getConnectors();
	}
	
}
