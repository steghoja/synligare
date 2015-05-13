package org.eclipse.eatop.volvo.sgraphml.testcode;

import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;

//User defined request
public class MovePortRequest extends Request {

	public static String REQ_MOVE_PORT = "move port request";
	ChangeBoundsRequest changeBoundsRequest;
	
	public MovePortRequest(ChangeBoundsRequest cbr){
		this.changeBoundsRequest = cbr;

	this.setType(REQ_MOVE_PORT);
	}

	public ChangeBoundsRequest getChangeBoundsRequest() {
		return this.changeBoundsRequest;
	}

	
	
	
}
