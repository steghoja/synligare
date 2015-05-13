package org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.deferred.SetModel;

import eu.synligare.sgraphml.ResourceType;

public interface ISgraphmlResource {

	public String getID();
	
	public ResourceType getModelElement();
	public void setModelElement(ResourceType modelElement);
	
	//public String getType();
}
