package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import eu.synligare.sgraphml.PointType;
import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.SgraphmlFactory;

/**
 * Move a link bendpoint.
 
 */
public class PolyLineEdgeMoveBendpointCommand extends Command {
   
  // Old location of the moved bendpoint
  private PointType oldLocation;

  // New location of the moved bendpoint. 
  private PointType newLocation;

  // The move delta for a relative move
  private Point delta;

  //absolute or relative move 
  private boolean relativeMove = false;

  /// Index of the bendpoint in the link's bendpoint list.
  private int index;

  //  Edge that contains the bendpoint. 
  private PolyLineEdgeType edge;
  
  
  
  public void setRelativeMove(boolean relativeMove) {
	this.relativeMove = relativeMove;
}

public PolyLineEdgeMoveBendpointCommand(){
	  oldLocation = SgraphmlFactory.eINSTANCE.createPointType();
	  newLocation = SgraphmlFactory.eINSTANCE.createPointType();
  
  }
  
  /** Move the bendpoint to the new location. */
  public void execute() {
    if(!oldLocation.isSetX()) {
      oldLocation = edge.getPath().getPoint().get(index);
    }
    
    if (relativeMove){
  	  newLocation.setX(oldLocation.getX() + delta.x); 
  	  newLocation.setY(oldLocation.getY() + delta.y); 
    }

    edge.getPath().getPoint().set(index, newLocation);
  }
   
  /** Restore the old location of the bendpoint. 
  @Override public void undo() {
    edge.getPath().getPoint().set(index, oldLocation);
  }
 
  /** 
   * Set the index where the bendpoint is located in the bendpoint list.
   * @param index the index where the bendpoint is located. 
   */
  public void setIndex(final int index) {
    this.index = index;
  }
   
  /**
   * Set the link where the bendpoint is located. 
   * @param link the link where the bendpoint is located. 
   */
  public void setPolyLineEdge(final PolyLineEdgeType edge) {
    this.edge = edge;
  }
   
  /**
   * Set the new location of the bendpoint. 
   * @param newLocation the new location of the bendpoint. 
   */
  
  public void setLocation(Point location){
	  this.newLocation.setX(location.x); 
	  this.newLocation.setY(location.y); 
  }
  
  public void setMove(Point delta){
	  this.delta = delta;
  }
 }