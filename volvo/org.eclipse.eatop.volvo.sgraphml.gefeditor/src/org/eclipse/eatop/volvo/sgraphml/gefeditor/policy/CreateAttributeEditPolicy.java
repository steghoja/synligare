package org.eclipse.eatop.volvo.sgraphml.gefeditor.policy;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateNodeLabelCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.requests.CreateAttributeRequest;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.NodeLabelType;

public class CreateAttributeEditPolicy extends ContainerEditPolicy {

	//Mapping Requests to Commands
	
	@Override
	public EditPart getTargetEditPart(Request request) {
		if (request.getType().equals(CreateAttributeRequest.REQ_CREATE_ATTRIBUTE)){
			return getHost();
		}
		return super.getTargetEditPart(request);
		
	}
	
	@Override
	public Command getCommand(Request request) {
		if (request.getType().equals(CreateAttributeRequest.REQ_CREATE_ATTRIBUTE)){

			//also put this in shapenodes...
			CreateAttributeRequest createAttributeRequest = (CreateAttributeRequest)request;
			Object object = createAttributeRequest.getNewObject();
			if (object instanceof NodeLabelType){
				
				NodeLabelType nodeLabel = (NodeLabelType)object;
				
				CreateNodeLabelCommand command = new CreateNodeLabelCommand();
				command.setNodeLabel(nodeLabel);
				command.setNode((BaseNodeType)getHost().getModel()); 
				return command;
			}
		}
		return super.getCommand(request);
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		return null;
	}
  
}
