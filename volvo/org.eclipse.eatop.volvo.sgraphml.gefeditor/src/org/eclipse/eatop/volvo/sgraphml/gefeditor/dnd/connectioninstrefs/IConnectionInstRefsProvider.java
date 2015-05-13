package org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.connectioninstrefs;


import org.eclipse.emf.ecore.EObject;


public interface IConnectionInstRefsProvider {
	
	public EObject getSourceInstancRef();
	public EObject getTargetInstanceRef();
	
	public int getNumberOfPorts();
	
}


