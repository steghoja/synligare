package org.eclipse.eatop.volvo.sgraphml.gefeditor.policy;

import java.util.List;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateEdgeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateNodeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateResourceCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.MoveChildNodeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.NodeTypeChangeConstraintCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.TouchNodeLabelCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.UpdateLabelRectangleCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.UpdateNodeLabelTypeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.GraphMLTypeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.GroupNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.NodeLabelEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeLabelEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.ShapeNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.EAXMLprocessor;
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
		    	EObject eaEObject = EAXMLprocessor.getEObjectbyDotPath(((NodeType)sgmlObject).getId());
		    	
		    	if (eaEObject == null){
		    		Utils.showErrorMessage("Failed to find EObject with dotPath = " + ((NodeType)sgmlObject).getId());;
		    	}
		    	
		    	command.setEaEObject(eaEObject);
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
	 * Note: While performing a drag operation of an element, there will be multiple calls here each one reporting one or several pixels move depending
	 * 	     on mouse speed.
	 */

	@Override 
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) 
	{

		//request has some delta information, movedelta, resize delta etc.
		if (child instanceof ShapeNodeEditPart) {
			CompoundCommand cc = new CompoundCommand();
			
			NodeTypeChangeConstraintCommand command = new NodeTypeChangeConstraintCommand();
			command.setModel((BaseNodeType) child.getModel());
			command.setNewConstraint((Rectangle) constraint); //Ok to use constraint even if zoomed since in model coordinates.
		    //TODO: update the rectangle attributes of the port label (now we update them upon save instead)
			
			cc.add(command);
			
			if (!request.getSizeDelta().equals(0,0))
			{
				//move labels due to shapenode resize 
				for (Object p : child.getChildren()){
					EditPart nodeEditPart = (EditPart)p;
					if (nodeEditPart instanceof NodeLabelEditPart){ 
						//touch label child editparts to get them updated 
							TouchNodeLabelCommand touchCommand = new TouchNodeLabelCommand();
							touchCommand.setNodeLabel((NodeLabelType)nodeEditPart.getModel());
							cc.add(touchCommand);
					}
				}					
			}
			return cc;
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
						
				//move labels and ports due to the groupnode resize 
				for (Object p : child.getChildren()){
					EditPart nodeEditPart = (EditPart)p;
					if (nodeEditPart instanceof PortNodeEditPart){
						PortNodeType node = (PortNodeType)nodeEditPart.getModel();
						MoveChildNodeCommand moveChildCommand = new MoveChildNodeCommand();
						moveChildCommand.setModel(node);
						Point moveDelta = PolicyHelpers.calculateMovePortDelta(groupNode.getGeometry(), node.getGeometry(), request, (Rectangle)constraint);
						moveChildCommand.setChange(moveDelta); 
						cc.add(moveChildCommand);
						
						
						UpdateNodeLabelTypeCommand touchLabel = new UpdateNodeLabelTypeCommand();
						touchLabel.setModel(node);
						NodeLabelType currentLabel = node.getNodeLabel().get(0);
						touchLabel.setNewNodeLabel(currentLabel);
						cc.add(touchLabel);
					}
					else if (nodeEditPart instanceof NodeLabelEditPart){ 
						//touch label child editparts to get them updated 
							TouchNodeLabelCommand touchCommand = new TouchNodeLabelCommand();
							touchCommand.setNodeLabel((NodeLabelType)nodeEditPart.getModel());
							cc.add(touchCommand);
					}
						
				}
				
				
				//Use case: expand to the right. But label is not updated (reload is needed). How force update of label - relative coordinate is the same.
				//Test by touching the nodelabel - what about ordering - this has to be done after move
					
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

/*		TODO	
			Rectangle pr = ((ShapeNodeFigure)fig).getLabelRectangle();

			//only update if the rectangles differ
			if (!compareRectangles(node.getNodeLabel().get(0), pr)){
				updateCommand.setModel(node.getNodeLabel().get(0));	
				updateCommand.setNewRectangle(pr);
				cc.add(updateCommand);		
			}
	*/	
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
