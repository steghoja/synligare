package org.eclipse.eatop.volvo.sgraphml.gefeditor.policy;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.DeleteNodeLabelCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.NodeLabelType;

public class DeleteNodeLabelEditPolicy extends ComponentEditPolicy {

	 @Override 
	 protected Command createDeleteCommand(GroupRequest deleteRequest) {
		   
		 DeleteNodeLabelCommand deleteCommand = new DeleteNodeLabelCommand();
		 
		 NodeLabelType nodeLabel = (NodeLabelType)getHost().getModel();
		 BaseNodeType node = (BaseNodeType)nodeLabel.eContainer();

		 

		 
		 deleteCommand.setLabel(nodeLabel);
		 deleteCommand.setNode(node);
		 
		 
		 return deleteCommand;
	 }
}
