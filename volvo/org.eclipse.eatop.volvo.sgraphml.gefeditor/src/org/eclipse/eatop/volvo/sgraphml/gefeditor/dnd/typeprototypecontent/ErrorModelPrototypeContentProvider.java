package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAConnector;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FailureOutPort;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;

public class ErrorModelPrototypeContentProvider implements
		ITypePrototypeContentProvider {

	
	ErrorModelTypeContentProvider typeProvider;

	public ErrorModelPrototypeContentProvider(ErrorModelPrototype adaptableObject){
		if (adaptableObject.getType() == null){
			Utils.showErrorMessage("ErrorModelPrototype " + adaptableObject.getShortName() + " has no type defined!");
		}
		this.typeProvider = new ErrorModelTypeContentProvider(adaptableObject.getType());
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
		return typeProvider.getParts();	}

	@Override
	public List<EAConnector> getConnectors() {
		return typeProvider.getConnectors();	
	}

}
