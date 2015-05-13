package org.eclipse.eatop.volvo.sgraphml.gefeditor.requests;

import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;

//User defined request
public class UpdateLabelRectanglesRequest extends Request {

	public static String REQ_UPDATE_LABEL_RECTANGLES = "update label rectangles";
	
	public UpdateLabelRectanglesRequest(){
		this.setType(REQ_UPDATE_LABEL_RECTANGLES);
	}

	
}
