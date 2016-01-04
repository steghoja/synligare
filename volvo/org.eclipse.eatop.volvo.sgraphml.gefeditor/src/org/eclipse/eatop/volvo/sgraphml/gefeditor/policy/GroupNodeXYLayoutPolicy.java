package org.eclipse.eatop.volvo.sgraphml.gefeditor.policy;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateEdgeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateNodeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateNodeLabelCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.CreateResourceCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.MoveChildNodeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.MoveNodeLabelCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.NodeTypeChangeConstraintCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.ResizeScaledIconResourceCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.TouchNodeLabelCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.UpdateNodeLabelTypeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.GraphMLTypeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.GroupNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.NodeLabelEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeLabelEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.ShapeNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.EAXMLprocessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.requests.CreateAttributeRequest;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.requests.UpdateLabelRectanglesRequest;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.NodeType;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.HorizontalTextPositionType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PlacementType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.ResourceBlockType;
import eu.synligare.sgraphml.ResourceType;
import eu.synligare.sgraphml.VerticalTextPositionType;

public class GroupNodeXYLayoutPolicy extends XYLayoutEditPolicy {

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
				GroupNodeType groupNode = (GroupNodeType)getHost().getModel();
				NodeType gmlNode = (NodeType)groupNode.eContainer().eContainer();
				GraphType graph = gmlNode.getGraph().get(0);
				command.setParentGraph(graph);
				EObject eaEObject = EAXMLprocessor.getEObjectbyDotPath(((NodeType)sgmlObject).getId());
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
	 * part of an GroupNodeType.
	 */

	//Note: The constraint rectangle of the PortNodefigure (i.e. Port+Label), has coordinates relative to the groupNode figure.

	//GEF calls here upon resize, when portnode / label is selected
	//GEF calls here upon move, when the portnode / label is selected but not the parent GroupNode
	//GEF does not call here upon move, when portnode is selected & parent GroupNode selected and moved
	//
	@Override 
	protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) 
	{
		if (!((child instanceof ShapeNodeEditPart) ||
				(child instanceof PortNodeEditPart) ||
				(child instanceof GroupNodeEditPart) ||
				(child instanceof NodeLabelEditPart))){
			return null;
		}

		Rectangle r = (Rectangle)constraint;
		CompoundCommand cc = new CompoundCommand();

		if (request.getSizeDelta().equals(0,0)){
			//move operation
			if (child instanceof NodeLabelEditPart){
				NodeLabelType nodeLabel = (NodeLabelType) child.getModel();
				MoveNodeLabelCommand moveCommand = new MoveNodeLabelCommand();
			    Point screen_relP = new Point(r.x, r.y);
			    Point modelCoords = Utils.screenDelta2ModelDelta(screen_relP);
				moveCommand.setModel(nodeLabel);
				moveCommand.setNewRelPosition(modelCoords);
				cc.add(moveCommand);
			}

			
			moveNodeAndChildren_recursive(child, cc, request);

			if (child instanceof PortNodeEditPart){

				//update the placement attributes of the port label
				//if the corresponding portnode was just moved, the view will pick up the new position from it
				BaseNodeType basenode = (BaseNodeType) child.getModel();
				NodeLabelType nodeLabel = basenode.getNodeLabel().get(0);

				UpdateNodeLabelTypeCommand updateLabelCommand = new UpdateNodeLabelTypeCommand();
				updateLabelCommand.setModel(basenode);
				Rectangle rGroupAbs = (Rectangle)getCurrentConstraintFor((GraphicalEditPart)child.getParent()); //absolute (x,y,w,h)
				Rectangle rGroupRel = new Rectangle(0,0,rGroupAbs.width, rGroupAbs.height);					    //relative (0,0,w,h)
				CalculatePortNodeLabel(nodeLabel, rGroupRel, r);
				updateLabelCommand.setNewNodeLabel(nodeLabel);
				cc.add(updateLabelCommand);

			}

			return cc;
		}
		else{
			//resize command

			//convert the constraint (which has coordinates relative the group node) to absolute coordinates
			Point pOrigin = getLayoutOrigin();

			Rectangle nodeConstraintAbsolute = new Rectangle((Rectangle)constraint);
			nodeConstraintAbsolute.translate(pOrigin);			
			nodeConstraintAbsolute.height = r.height;
			nodeConstraintAbsolute.width = r.width;

			//define the move/resize command for the Node
			NodeTypeChangeConstraintCommand changeConstraintCommand = new NodeTypeChangeConstraintCommand();
			changeConstraintCommand.setModel((BaseNodeType) child.getModel());
			changeConstraintCommand.setNewConstraint((Rectangle) nodeConstraintAbsolute);

			//resize
			cc.add(changeConstraintCommand);

			if (child instanceof PortNodeEditPart){
				//Update image in resource 
				ResizeScaledIconResourceCommand resizeIconCommand = new ResizeScaledIconResourceCommand();
				PortNodeType portNode = (PortNodeType)((PortNodeEditPart)child).getModel();
				NodeLabelType nodeLabel = portNode.getNodeLabel().get(0);
				String resourceID = nodeLabel.getIconData();
				resizeIconCommand.setResourceID(resourceID);
				double xScale = (double)r.width / (double)(r.width - request.getSizeDelta().width);
				double yScale = (double)r.height / (double)(r.height - request.getSizeDelta().height);
				resizeIconCommand.setNewSize(xScale, yScale);
				cc.add(resizeIconCommand);
			}

			//skip resizing contents

			if (child instanceof GroupNodeEditPart){
				
			GroupNodeEditPart groupNodeEP = (GroupNodeEditPart)child;
			GroupNodeType groupNode = (GroupNodeType)groupNodeEP.getModel();
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
			}
	
			
			
			//TODO: update the rectangle attributes of the port label (now we update them upon save instead)
			return cc;
		}

	}

	/***
	 * Generates commands to translate the model coordinates of a node and all its children.
	 * Since some children has absolute coordinates, they need to be updated when a parent container is moved
	 * @param node
	 * @param cc
	 * @param request
	 */
	protected void moveNodeAndChildren_recursive(EditPart node, CompoundCommand cc, ChangeBoundsRequest request){
		if ((node instanceof ShapeNodeEditPart) ||
				(node instanceof PortNodeEditPart) ||
				(node instanceof GroupNodeEditPart)){

			//NodeLabelEditPart has relative coordinates => need not be moved (Maybe touched?)
		
			//move Shape/Port/Groupnode
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


	/***
	 * 
	 * Updates the nodeLabel attributes for placement, verticalTextPosition & horizontalTextPosition.
	 * 
	 * @param Container = (0,0,w,h)
	 * @param port = relative coordinates
	 * 
	 */
	protected void CalculatePortNodeLabel(NodeLabelType nodeLabel, Rectangle container, Rectangle port){


		//Calculate the distance between each port side and the container side
		int dLeft = port.x;
		int dRight = container.width - (port.x + port.width);
		int dTop = port.y;
		int dBottom = container.height - (port.y + port.height);

		//find min distance
		SortedSet<Integer> set = new TreeSet<Integer>();

		set.add(dLeft);
		set.add(dRight);
		set.add(dTop);
		set.add(dBottom);

		int min = set.first();

		if (min == dLeft){
			nodeLabel.setPlacement(PlacementType.LEFT);  //Icon to the left in the Label rectangle
			nodeLabel.setHorizontalTextPosition(HorizontalTextPositionType.RIGHT);
			nodeLabel.setVerticalTextPosition(VerticalTextPositionType.CENTER);

		}
		else if (min == dRight){
			nodeLabel.setPlacement(PlacementType.RIGHT);  //Icon to the right in Label rectangle
			nodeLabel.setHorizontalTextPosition(HorizontalTextPositionType.LEFT);
			nodeLabel.setVerticalTextPosition(VerticalTextPositionType.CENTER);

		}
		else if (min == dTop){ //put label below
			nodeLabel.setPlacement(PlacementType.TOP);  //Icon in the top of the Label rectangle
			nodeLabel.setHorizontalTextPosition(HorizontalTextPositionType.CENTER);
			nodeLabel.setVerticalTextPosition(VerticalTextPositionType.BOTTOM);
		}
		else {
			//dBottom //put label above
			nodeLabel.setPlacement(PlacementType.BOTTOM);  //Icon in the bottom of the Label rectangle
			nodeLabel.setHorizontalTextPosition(HorizontalTextPositionType.CENTER);
			nodeLabel.setVerticalTextPosition(VerticalTextPositionType.TOP);
		}

		/*		System.out.println("Container: " + container);
		System.out.println("Port: " + port);
		System.out.println("New placement: " + placement);
		System.out.println("");
		 */	
	}






	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {

		if (child instanceof PortNodeLabelEditPart){
			//make the labels non selectable
			return null;
		}
		else
		{
			return super.createChildEditPolicy(child);
		}
	}

}
