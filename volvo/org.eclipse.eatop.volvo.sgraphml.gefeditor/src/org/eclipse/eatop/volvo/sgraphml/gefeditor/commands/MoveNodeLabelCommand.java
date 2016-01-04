package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PlacementType;
import eu.synligare.sgraphml.PortNodeType;

public class MoveNodeLabelCommand extends Command {
	  
	  private double oldX; //vs topLeft point
	  private double oldY; //vs topLeft point
	  private PlacementType oldPlacement;
	  boolean first = true;
	  private Point newRelPosition; //vs rectangle of owner figure
	  
	  
	  public Point getNewRelPosition() {
		return newRelPosition;
	}

	public void setNewRelPosition(Point newRelPosition) {
		this.newRelPosition = newRelPosition;
	}

	private NodeLabelType nodeLabel;
	  
	  @Override 
	  public void execute() {
	
  
		  if (first){
	  		    oldX = nodeLabel.getX();
			    oldY = nodeLabel.getY();
			    oldPlacement = nodeLabel.getPlacement();
			    first = false;
			}

			nodeLabel.setX(newRelPosition.x);  
			nodeLabel.setY(newRelPosition.y);  
			nodeLabel.setPlacement(PlacementType.FREE); 
	  }
	 
	  @Override 
	  public void undo() {
			nodeLabel.setX(oldX);  
			nodeLabel.setY(oldY);  
			nodeLabel.setPlacement(oldPlacement);
	  }
	 
	  public void setModel(NodeLabelType modelElement) {
	    this.nodeLabel = modelElement;
	  }


}
