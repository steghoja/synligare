package org.eclipse.eatop.volvo.sgraphml.gefeditor.policy;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.commands.DeleteNodeTypeCommand;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.PortNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.controller.ShapeNodeEditPart;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.NodeType;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.PortNodeType;

public class NodeTypeComponentEditPolicy extends ComponentEditPolicy {

	 @Override 
	 protected Command createDeleteCommand(GroupRequest deleteRequest) {
		   
		 DeleteNodeTypeCommand nodeDeleteCommand = new DeleteNodeTypeCommand();
		    
		    NodeType graphMLnode = (NodeType)((BaseNodeType)getHost().getModel()).eContainer().eContainer();
		    nodeDeleteCommand.setNode(graphMLnode);
		    
			    
		    AbstractGraphicalEditPart ep = (AbstractGraphicalEditPart)getHost();

//TODO: how handle delete groupnode 
//Need to go through all children EditParts 
//and need to deactivate all of them to remove listeners (port on graphtype etc). 
		    

		    if (ep instanceof ShapeNodeEditPart ||
		    	ep instanceof PortNodeEditPart)
		    {
		    	//Node may have connections
		    	List<EditPart> outgoing = ep.getSourceConnections();
			    List<EditPart> incoming = ep.getTargetConnections();
			    
			    //convert List of EditPart to List of EdgeType instead
			    List<EdgeType> incominggML = new ArrayList<EdgeType>();
			    for (EditPart sourceEditPart : incoming) {
			    	EObject sgraphmlNode = (EObject)sourceEditPart.getModel();
					incominggML.add((EdgeType)sgraphmlNode.eContainer().eContainer());
				}

			    List<EdgeType> outgoinggML = new ArrayList<EdgeType>();
			    for (EditPart targetEditPart : outgoing) {
			    	EObject sgraphmlNode = (EObject)targetEditPart.getModel();
					outgoinggML.add((EdgeType)sgraphmlNode.eContainer().eContainer());
				}
			    
			    nodeDeleteCommand.setIncomingEdges(incominggML);
			    nodeDeleteCommand.setOutgoingEdges(outgoinggML);
		    }
		    
		    return nodeDeleteCommand;
		  }
	
}
