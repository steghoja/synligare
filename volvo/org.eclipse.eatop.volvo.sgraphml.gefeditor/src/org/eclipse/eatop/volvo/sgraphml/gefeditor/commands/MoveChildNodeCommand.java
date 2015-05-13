package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import java.awt.Rectangle;

import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;


import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.SgraphmlFactory;

//Command to move/resize a child node (a contained node), for example a port in a groupnode 

public class MoveChildNodeCommand extends Command {
	 
	  private GeometryType oldConstraint;
	  private Point delta;
	  private BaseNodeType modelElement;
 


	@Override public void execute() {
		  if(oldConstraint == null) { 
			  oldConstraint = modelElement.getGeometry();
		}

		  //change the absolute model coordinates, 
		  //view will then update the relative position within parent to the same as before since
		  //parent moves the same
		  GeometryType newGeometry = SgraphmlFactory.eINSTANCE.createGeometryType();
		   
		  newGeometry.setX(oldConstraint.getX() + delta.x);
		  newGeometry.setY(oldConstraint.getY() + delta.y);
		  newGeometry.setHeight(oldConstraint.getHeight());
		  newGeometry.setWidth(oldConstraint.getWidth());
		  
		  modelElement.setGeometry(newGeometry);
	  }
	 
	  @Override public void undo() {
		  modelElement.setGeometry(oldConstraint);
	  }
	 
	  public void setModel(BaseNodeType modelElement) {
	    this.modelElement = modelElement;
	
	  }

	  public void setChange(Point delta) /*, double resizeScaleX, double resizeScaleY)*/ {
		  this.delta = delta;
	  }
}

