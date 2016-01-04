package org.eclipse.eatop.volvo.sgraphml.gefeditor.policy;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.draw2d.Label;
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
import eu.synligare.sgraphml.SgraphmlFactory;
import eu.synligare.sgraphml.VerticalTextPositionType;
import eu.synligare.sgraphml.util.SgraphmlAdapterFactory;

public class ShapeNodeXYLayoutPolicy extends XYLayoutEditPolicy {

	//Mapping Requests to Commands

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		return null;
	}
	

	/**
	   * Command created the user requests to change the constraint (size, location) of an object that is
	   * part of a ShapeNodeType.
	   */
	
	//Note: The constraint rectangle of the NodeLabel, has pixel coordinates relative to the groupNode figure.
	
	//GEF calls here upon resize, when the label is selected
	//GEF calls here upon move, when the label is selected but not the parent GroupNode
	//GEF does not call here upon move, when the label is selected & parent shapenodeselected and moved
	//
	@Override 
	  protected Command createChangeConstraintCommand(ChangeBoundsRequest request, EditPart child, Object constraint) 
	  {
		  if (!(child instanceof NodeLabelEditPart)){ 
		    return null;
		  }
 			if (request.getSizeDelta().equals(0,0)){
				//move operation
				
				NodeLabelType nodeLabel = (NodeLabelType) child.getModel();
			    MoveNodeLabelCommand moveCommand = new MoveNodeLabelCommand();
			    
			    Rectangle r = (Rectangle)constraint;	    
			    Point screen_relP = new Point(r.x, r.y);
			    Point modelCoords = Utils.screenDelta2ModelDelta(screen_relP);
			    
			    moveCommand.setNewRelPosition(modelCoords);
			    moveCommand.setModel(nodeLabel);
				
				return moveCommand;
			}
			else{
				// resize

				
			
				return null;
			}
	
	  }


	
}
