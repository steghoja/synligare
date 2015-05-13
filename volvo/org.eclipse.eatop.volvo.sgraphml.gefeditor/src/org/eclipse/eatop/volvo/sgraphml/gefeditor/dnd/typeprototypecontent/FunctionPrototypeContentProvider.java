package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent;

import java.util.List;

import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAConnector;
import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.eatop.eastadl21.FunctionType;

public class FunctionPrototypeContentProvider implements
		ITypePrototypeContentProvider {

	FunctionTypeContentProvider typeProvider;

	public FunctionPrototypeContentProvider(FunctionPrototype adaptableObject){
		
		FunctionType ft = null;
		
		if (adaptableObject instanceof DesignFunctionPrototype){
			ft = ((DesignFunctionPrototype)adaptableObject).getType();
		}
		else if (adaptableObject instanceof AnalysisFunctionPrototype){
			ft = ((AnalysisFunctionPrototype)adaptableObject).getType();
		}
		
		this.typeProvider = new FunctionTypeContentProvider(ft);
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
