package org.eclipse.eatop.volvo.sgraphml.gefeditor.policy;

import java.util.List;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.PolyLineEdgeCreateBendPointCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.PolyLineEdgeDeleteEndpointCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.PolyLineEdgeMoveBendpointCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PolyLineEdgeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.PolylineConnectionFigure;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.BendpointEditPolicy;
import org.eclipse.gef.requests.BendpointRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import eu.synligare.sgraphml.PolyLineEdgeType;

import org.eclipse.draw2d.AbsoluteBendpoint;
import org.eclipse.draw2d.geometry.Point;

public class PolyLineEdgeEditPolicy extends BendpointEditPolicy {

	@Override
	protected Command getCreateBendpointCommand(BendpointRequest request) {
		 PolyLineEdgeCreateBendPointCommand command = new PolyLineEdgeCreateBendPointCommand();
	     
		    //get location in screen coordinates
		    Point p = request.getLocation();
		     
		    //change to model coordinates
		    Point modelPoint = Utils.screenPoint2ModelPoint(p);
		    //double zoom = Utils.getZoomLevel();
			//p.scale(1 / zoom);
		     
		    command.setPolyLineEdge((PolyLineEdgeType) request.getSource().getModel());
		    command.setLocation(modelPoint);
		    command.setIndex(request.getIndex());
		     
		    return command;
		    
		    
		    
	}

	@Override
	protected Command getDeleteBendpointCommand(BendpointRequest request) {
	    PolyLineEdgeDeleteEndpointCommand command = new PolyLineEdgeDeleteEndpointCommand();
	     
	    command.setPolyLineEdge((PolyLineEdgeType) request.getSource().getModel());
	   
	    command.setIndex(request.getIndex());
	     
	    return command;
	}

	@Override
	protected Command getMoveBendpointCommand(BendpointRequest request) {
		  PolyLineEdgeMoveBendpointCommand command = new PolyLineEdgeMoveBendpointCommand();
		     
		    //get location in screen coordinates
		    Point p = request.getLocation();
		     
		    //change to model coordinates
		    Point modelPoint = Utils.screenPoint2ModelPoint(p);
		    
		    command.setPolyLineEdge((PolyLineEdgeType) request.getSource().getModel());
		    command.setLocation(modelPoint);
		    command.setRelativeMove(false);
		    command.setIndex(request.getIndex());
		    
		    return command;
	}

	protected Command getMoveAllBendPointsCommand(ChangeBoundsRequest request){
		CompoundCommand cc = new CompoundCommand();
		
		PolyLineEdgeEditPart editPart = (PolyLineEdgeEditPart)getHost();

		//Find the number of bendpoints
		List<AbsoluteBendpoint> constraint = (List<AbsoluteBendpoint>)((PolylineConnectionFigure)getHostFigure()).getRoutingConstraint();
		if (constraint.isEmpty())
		{
			return null;
		}

		//And move all of them
		for (int i = 0; i < constraint.size(); i++) {
			PolyLineEdgeMoveBendpointCommand command = new PolyLineEdgeMoveBendpointCommand();

			//get move delta in screen coordinates
		    Point delta = request.getMoveDelta();
		     
		    //change to model coordinates
		    Point modelDelta = Utils.screenDelta2ModelDelta(delta);
		    
		    command.setPolyLineEdge((PolyLineEdgeType)editPart.getModel());
		    command.setMove(modelDelta);
		    command.setRelativeMove(true);
		    command.setIndex(i);
			
			cc.add(command);
		}

		return cc;
		
	}
	
	@Override
	public boolean understandsRequest(Request req) {
		
		if (REQ_MOVE.equals(req.getType()))
				{
					return true;
				}
		return super.understandsRequest(req);
	};
	
	@Override
	public Command getCommand(Request request) {
		if (REQ_MOVE.equals(request.getType()))
		{
			return getMoveAllBendPointsCommand((ChangeBoundsRequest)request);
		}
		
		return super.getCommand(request);
	}
	
	
}
