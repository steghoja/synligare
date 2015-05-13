package org.eclipse.eatop.volvo.sgraphml.gefeditor.policy;

import java.util.List;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateEdgeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateNodeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateResourceCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.MoveChildNodeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.NodeTypeChangeConstraintCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.UpdateLabelRectangleCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.UpdateNodeLabelTypeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.GraphMLTypeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.GroupNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeLabelEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.ShapeNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.requests.UpdateLabelRectanglesRequest;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.GroupNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.PortNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.PortNodeLabelFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.ShapeNodeFigure;
import org.eclipse.eatop.volvo.sgraphml.testcode.MovePortRequest;
import org.eclipse.eatop.volvo.sgraphml.testcode.SGraphMLObjectWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Geometry;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.NodeType;
import org.graphdrawing.graphml.xmlns.provider.GraphmlTypeItemProvider;

import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.ResourceBlockType;
import eu.synligare.sgraphml.ResourceType;
import eu.synligare.sgraphml.ShapeNodeType;

public class GraphMLTypeXYLayoutPolicy extends XYLayoutEditPolicy {

	boolean updatedOptionalLabelRectangles = false;

	//Mapping Requests to Commands
	
	@Override 
	protected Command getCreateCommand(CreateRequest request) {
	    CompoundCommand compCommand = new CompoundCommand();

	    Object obj = request.getNewObject();
	    
	    List<EObject> sGraphmlObjects = (List<EObject>)(obj);
		    
		    for (EObject sgmlObject : sGraphmlObjects) {

	    	if (sgmlObject instanceof NodeType){
		    	CreateNodeCommand command = new CreateNodeCommand();
		    	command.setNode((NodeType)sgmlObject);
		    	command.setParentGraph(ModelProcessor.INSTANCE.getRootGraph());
		    	compCommand.add(command);
		    }
	    	
	    	else if (sgmlObject instanceof EdgeType){
	    		CreateEdgeCommand command = new CreateEdgeCommand();
	    		command.setEdge((EdgeType)sgmlObject);
		    	command.setParentGraph(ModelProcessor.INSTANCE.getRootGraph());
		    	compCommand.add(command);
	    	}

	    	else if (sgmlObject instanceof ResourceType){
	    		CreateResourceCommand command = new CreateResourceCommand();
	    		command.setResource((ResourceType)sgmlObject);
	    		command.setParentResourceBlock((ResourceBlockType)ModelProcessor.INSTANCE.getResourceBlock());    		
		    	compCommand.add(command);
	    	}

	    }
	    
	    return compCommand;
	  }
	
	
	/**
	 * Command created the user requests to change the constraint (size, location) of an object that is
	 * part of an GraphMLType.
	 *
	 * Note that constraint is in the model coordinate system (ie before zoom), and ChangeBoundsRequest is screen absolute. 
	 * 
	 */

	@Override 
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) 
	{

		//request has some delta information, movedelta, resize delta etc.
		if (child instanceof ShapeNodeEditPart) {
			NodeTypeChangeConstraintCommand command = new NodeTypeChangeConstraintCommand();
			command.setModel((BaseNodeType) child.getModel());
			command.setNewConstraint((Rectangle) constraint); //Ok to use constraint even if zoomed since in model coordinates.
		    //TODO: update the rectangle attributes of the port label (now we update them upon save instead)
			return command;
		}
		else if (child instanceof GroupNodeEditPart){
			if (request.getSizeDelta().equals(0,0))
			{
				//This is a move request
				CompoundCommand cc = new CompoundCommand();				
				moveNodeAndChildren_recursive(child, cc, request);
				return cc;
			}
			else {
				//This is a resize
				CompoundCommand cc = new CompoundCommand();

				GroupNodeType groupNode = (GroupNodeType) child.getModel();
				NodeTypeChangeConstraintCommand groupNodeCommand = new NodeTypeChangeConstraintCommand();
				groupNodeCommand.setModel(groupNode);
				groupNodeCommand.setNewConstraint((Rectangle) constraint);
				groupNodeCommand.setSelected(true);
			    
				cc.add(groupNodeCommand);
				
				//TODO: update the rectangle attributes of the port label (now we update them upon save instead)
				
/*
 TODO: Fix code below. Note that we get resize calls now and then even before mouse has been clicked.
				//calculate resize scale from resize
				Dimension sd = request.getSizeDelta();
				Rectangle r1 = (Rectangle)constraint;
				double resizeScaleX = ((double)(r1.width)) / (r1.width - sd.width);
				double resizeScaleY = ((double)(r1.height)) / (r1.height - sd.height);
				ChangeBoundsRequest cbr = new ChangeBoundsRequest();
				
				//move ports due to the groupnode resize 
				for (Object p : child.getChildren()){
					EditPart nodeEditPart = (EditPart)p;
					if (nodeEditPart.getModel() instanceof PortNodeType){
						PortNodeType node = (PortNodeType)nodeEditPart.getModel();
						MoveChildNodeCommand moveChildCommand = new MoveChildNodeCommand();
						moveChildCommand.setModel(node);
						moveChildCommand.setSelected(true);
						Point moveDelta = calculateMovePortDelta(groupNode.getGeometry(), node.getGeometry(), request, resizeScaleX, resizeScaleY);
						cbr.setMoveDelta(moveDelta);
						moveChildCommand.setChange(cbr); 
						cc.add(moveChildCommand);
					}
				}
*/				
				return cc;
			}
		}

		//unknown editpart
		return null;
	}

	 /***
	   * Generates commands to translate the model coordinates of a node and all its children.
	   * 
	   * @param node
	   * @param cc
	   * @param request
	   */
	  protected void moveNodeAndChildren_recursive(EditPart node, CompoundCommand cc, ChangeBoundsRequest request){
		  if ((node instanceof ShapeNodeEditPart) ||
		      (node instanceof PortNodeEditPart) ||
		      (node instanceof GroupNodeEditPart)){
			  
				  //move node
				MoveChildNodeCommand nodeCommand = new MoveChildNodeCommand();
				nodeCommand.setModel((BaseNodeType)node.getModel());
				Point delta = Utils.screenDelta2ModelDelta(request.getMoveDelta());
				nodeCommand.setChange(delta ); //,  resizeScaleX, resizeScaleY);
				cc.add(nodeCommand);
		  	}
		  
		  if (node instanceof GroupNodeEditPart){
			  //call move for all content
			  for (Object child : node.getChildren()) {
				  moveNodeAndChildren_recursive((EditPart)child, cc, request);
			  }

		    }
		 }
	protected Point calculateMovePortDelta(GeometryType groupnode, GeometryType port, ChangeBoundsRequest request, double resizeScaleX, double resizeScaleY  ){

		Point moveDelta = new Point(0,0);
		
		//Which side is the port attached to?
		int dxW = (int)(port.getX() - groupnode.getX());
		int dxE = (int)(groupnode.getX() + groupnode.getWidth() - (port.getX() + port.getWidth()));
		int dyN = (int)(port.getY() - groupnode.getY());
		int dyS = (int)(port.getY() + groupnode.getHeight() - (groupnode.getY() + groupnode.getHeight()));
		
	
		int side;
		if (Math.abs(dxW) <= 2) {
	   		side = PositionConstants.WEST;
		}
		else if (Math.abs(dxE) <= 2) {
	   		side = PositionConstants.EAST;
		}
		else if (Math.abs(dyN) <= 2) {
	   		side = PositionConstants.NORTH;
		}
		else if (Math.abs(dyS) <= 2) {
			side = PositionConstants.SOUTH;
		}
		else{
			//This port is not attached to any side - don't move it
			return moveDelta;
		}
		
		//Is "my" edge moved due to resize? Then move port in same way.
		if ((request.getResizeDirection() & side) > 0){
			if ((side == PositionConstants.WEST) || (side == PositionConstants.EAST)){
				moveDelta.x = request.getSizeDelta().width;
			}
			else{
				moveDelta.y = request.getSizeDelta().height;
			}
		}

		//Is the groupnode resized in the dimension opposite to my edge? Then rescale.
	
		if ((side == PositionConstants.WEST) || (side == PositionConstants.EAST)){
			if (request.getSizeDelta().height != 0){
				moveDelta.y = (int) Math.round((resizeScaleY - 1.0)* (port.getY() - groupnode.getY()));
			}
		}
		if ((side == PositionConstants.NORTH) || (side == PositionConstants.SOUTH)){
			if (request.getSizeDelta().width != 0){
				moveDelta.x = (int) Math.round((resizeScaleX - 1.0) * (port.getX() - groupnode.getX()));
			}
		}
/*
 * DEBUG		
		System.out.println("resizeScaleX = " + resizeScaleX);
		System.out.println("resizeScaleY = " + resizeScaleY);
		System.out.println("moveDelta = " + moveDelta);
*/		
		
		return moveDelta;
	}
	
	
	@Override
	public Command getCommand(Request request) {
		if (request.getType() == UpdateLabelRectanglesRequest.REQ_UPDATE_LABEL_RECTANGLES){
			CompoundCommand cc = new CompoundCommand();
			UpdateAllLabelRectangles_recursive(cc, (GraphMLTypeEditPart)getHost());
			return cc;
			
		}
		return super.getCommand(request);
	}
	
	/***
	 * Issues a command for each nodeLabel in the view, to write the rectangle coordinates.
	 * 
	 * @param commands - the compound command where to add the update commands
	 */
	protected void UpdateAllLabelRectangles_recursive(CompoundCommand cc, EditPart editPart){

		UpdateLabelRectangleCommand updateCommand = new UpdateLabelRectangleCommand();

		//Update this editPart
		if (editPart instanceof GraphMLTypeEditPart){
			//do nothing - no nodelabel
		}
		if ((editPart instanceof ShapeNodeEditPart) ||
				(editPart instanceof GroupNodeEditPart))
		{
			BaseNodeType node = (BaseNodeType)(editPart.getModel());
			IFigure fig = ((AbstractGraphicalEditPart)editPart).getFigure();
			Rectangle pr = ((ShapeNodeFigure)fig).getLabelRectangle();

			//only update if the rectangles differ
			if (!compareRectangles(node.getNodeLabel().get(0), pr)){
				updateCommand.setModel(node.getNodeLabel().get(0));	
				updateCommand.setNewRectangle(pr);
				cc.add(updateCommand);		
			}
		
		}
		else if (editPart instanceof PortNodeEditPart) {
			//do nothing - no nodelabel, no children
			return;
		}
		else if (editPart instanceof PortNodeLabelEditPart) {
			NodeLabelType nodeLabel = (NodeLabelType)(editPart.getModel());
			PortNodeLabelFigure fig = (PortNodeLabelFigure)((PortNodeLabelEditPart) editPart).getFigure();

			PortNodeType portNode = (PortNodeType)nodeLabel.eContainer();	
			Rectangle rPort = null;
			boolean bFound = false;

			//find the editPart for the corresponding port
			List<EditPart> siblings = editPart.getParent().getChildren();
			for (EditPart sibling : siblings) {
				if ((sibling instanceof PortNodeEditPart) &&
						sibling.getModel().equals(portNode))
				{

					PortNodeFigure pnFigure = (PortNodeFigure)((PortNodeEditPart)sibling).getFigure();
					GroupNodeFigure gnFigure = (GroupNodeFigure)pnFigure.getParent().getParent();
					rPort = gnFigure.getPortNodeRectangle(pnFigure);
					bFound = true;
					break;
				}
			}
			if (bFound)
			{
				Rectangle pr = fig.getLabelRectangle(new org.eclipse.draw2d.geometry.Point (rPort.x, rPort.y));

				if (!compareRectangles(nodeLabel, pr)){
					updateCommand.setModel(nodeLabel);

					updateCommand.setNewRectangle(pr);
					cc.add(updateCommand);
				}

			}
			else {
				System.out.println ("Internal error: Failed to find editPart for corresponding Port");
			}


		}
		//skip PolyLineEdgeEditPart - not important to update the rectangle for those labels

		for (Object p : editPart.getChildren()){
			EditPart child = (EditPart)p;

			UpdateAllLabelRectangles_recursive(cc, child);

		}

	}


/***
 * 
 * @return Compares the rectangle held in a nodeLabel with another rectangle.
 */
	private boolean compareRectangles(NodeLabelType nodeLabel, Rectangle r) {
		
		if (nodeLabel == null) return false;
		
	
		if ((!nodeLabel.isSetX()) ||
				(!nodeLabel.isSetY()) ||
				(!nodeLabel.isSetWidth()) ||
				(!nodeLabel.isSetHeight()))
		{		
			return false;
		}
		
		return ((nodeLabel.getX() == r.x) &&
				(nodeLabel.getY() == r.y) &&
				(nodeLabel.getWidth() == r.width() &&
				(nodeLabel.getHeight() == r.height)));
				
	}

	/*
	@Override
	protected Command getMoveChildrenCommand(Request request){
	return super.getMoveChildrenCommand(request);
	}

	@Override
	protected Command getResizeChildrenCommand(ChangeBoundsRequest request){
		return super.getResizeChildrenCommand(request);
	}
	 */


}
