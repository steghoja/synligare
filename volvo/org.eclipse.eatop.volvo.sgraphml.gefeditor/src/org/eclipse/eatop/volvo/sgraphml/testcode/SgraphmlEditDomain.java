package org.eclipse.eatop.volvo.sgraphml.testcode;

import org.eclipse.gef.DefaultEditDomain;


//A singleton class that saves the GEF editdomain so it can be used in other classes then the application
public class SgraphmlEditDomain {
	
	//Singleton
	public static SgraphmlEditDomain iNSTANCE = new SgraphmlEditDomain(); 
	private SgraphmlEditDomain(){};
	
	
	DefaultEditDomain editDomain;

	public DefaultEditDomain getEditDomain() {
		return editDomain;
	}

	public void setEditDomain(DefaultEditDomain ed) {
		this.editDomain = ed;
	}
	
}

