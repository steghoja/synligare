package org.eclipse.eatop.volvo.sgraphml.gefeditor.controller;

import java.awt.Rectangle;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;

import eu.synligare.sgraphml.GroupNodeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.ShapeNodeType;
import eu.synligare.sgraphml.ShapeTypeType;

public class SGraphMLEditPartFactory implements EditPartFactory {

	
	@Override public EditPart createEditPart(EditPart context, Object model) {
	    EditPart part = null;
	     
	    if(model instanceof GraphmlType) {
	      part = new GraphMLTypeEditPart();
	    } 
	    else if(model instanceof GroupNodeType) {
	    	part = new GroupNodeEditPart();
		    }
	    else if(model instanceof ShapeNodeType) {
	    	part = new ShapeNodeEditPart();
	    }
	    else if (model instanceof PortNodeType){
	    	part = new PortNodeEditPart();
	    }
	    else if (model instanceof NodeLabelType){
	    	part = new PortNodeLabelEditPart();
	    }

	    else if (model instanceof PolyLineEdgeType){
	    	part = new PolyLineEdgeEditPart();
	    }
	    	
	     
	    if(part != null) {
	      part.setModel(model);
	    }
	     
	    return part;
	  }
}
