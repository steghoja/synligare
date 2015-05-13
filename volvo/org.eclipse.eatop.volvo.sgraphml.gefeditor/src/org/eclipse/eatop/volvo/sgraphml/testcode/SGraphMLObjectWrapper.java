package org.eclipse.eatop.volvo.sgraphml.testcode;

import org.eclipse.emf.ecore.EObject;

public class SGraphMLObjectWrapper{
	public EObject parent; 				//The graph/resourceblock that holds the sgraphMLObject
	public EObject sgraphMLObject;		//the object that shall be written to the model
	
	public SGraphMLObjectWrapper(EObject parent, EObject sGraphMLObj){
		this.parent = parent;
		this.sgraphMLObject = sGraphMLObj;
	}
}
