package org.eclipse.eatop.volvo.sgraphml.gefeditor.requests;

import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

//User defined request
public class CreateAttributeRequest extends CreateRequest {

	public static String REQ_CREATE_ATTRIBUTE = "create attribute";
	
	public CreateAttributeRequest(){
		this.setType(REQ_CREATE_ATTRIBUTE);
	}

	
}
