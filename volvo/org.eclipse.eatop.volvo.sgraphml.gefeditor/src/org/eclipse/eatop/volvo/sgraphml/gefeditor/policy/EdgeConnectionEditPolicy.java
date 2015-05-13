package org.eclipse.eatop.volvo.sgraphml.gefeditor.policy;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.DeleteEdgeCommand;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.graphdrawing.graphml.xmlns.EdgeType;

import eu.synligare.sgraphml.PolyLineEdgeType;

public class EdgeConnectionEditPolicy extends ConnectionEditPolicy {

		
	/**
	   * Create a {@link DeleteEdgeCommand} and fill its details.
	   * @param request the request that requires treatment.
	   * @return a {@link DeleteEdgeCommand} that deletes an edge from the model.
	   */
	  @Override protected DeleteEdgeCommand getDeleteCommand(GroupRequest request) {
	    DeleteEdgeCommand command = new DeleteEdgeCommand();
	    PolyLineEdgeType ple = (PolyLineEdgeType)(getHost().getModel());
	    command.setEdge((EdgeType)ple.eContainer().eContainer());
	    return command;
	  
	  
	  }
	  
	  
	  @Override
  	 public boolean understandsRequest(Request req) {
		// TODO Auto-generated method stub
		return super.understandsRequest(req);
	}
	 
	  @Override
	public Command getCommand(Request request) {
		// TODO Auto-generated method stub
		return super.getCommand(request);
	}
	  
	  
}
