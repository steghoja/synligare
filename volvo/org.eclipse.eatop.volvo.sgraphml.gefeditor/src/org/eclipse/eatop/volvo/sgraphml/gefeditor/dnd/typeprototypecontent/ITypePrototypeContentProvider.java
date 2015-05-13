package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.typeprototypecontent;

import java.util.List;

import org.eclipse.eatop.eastadl21.EAConnector;
import org.eclipse.eatop.eastadl21.EAPort;
import org.eclipse.eatop.eastadl21.EAPrototype;

public interface ITypePrototypeContentProvider {
	
	public List<EAPort> getInports();
	
	public List<EAPort> getOutports();

	public List<EAPort> getInOutports();

	public List<EAPrototype> getParts();
	
	public List<EAConnector> getConnectors();
	
}


