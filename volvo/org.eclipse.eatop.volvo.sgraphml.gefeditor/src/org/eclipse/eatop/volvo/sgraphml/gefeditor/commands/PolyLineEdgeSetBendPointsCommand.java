package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;

import eu.synligare.sgraphml.PointType;
import eu.synligare.sgraphml.PolyLineEdgeType;

/**
 * Replaces all bendpoints of an edge with a new set.
 
 */
public class PolyLineEdgeSetBendPointsCommand extends Command {
   
 
  private EList<PointType> oldPoints;
  private EList<PointType> newPoints;

  
  //  Edge that contains the bendpoint. 
  private PolyLineEdgeType edge;
  
  
  
 public EList<PointType> getNewPoints() {
	return newPoints;
}

public void setNewPoints(EList<PointType> newPoints) {
	this.newPoints = newPoints;
}

public PolyLineEdgeType getEdge() {
	return edge;
}



public PolyLineEdgeSetBendPointsCommand(){ 
	oldPoints = null;
  }
  
 
  public void execute() {
    if(oldPoints == null) {
    	oldPoints = ECollections.newBasicEList();
    	oldPoints.addAll(edge.getPath().getPoint());
    }
  
    edge.getPath().getPoint().clear();
    edge.getPath().getPoint().addAll(newPoints);
  }
   
  @Override
  public void undo() {
	  edge.getPath().getPoint().clear();
	  edge.getPath().getPoint().addAll(oldPoints);
	  oldPoints = null;
  };
    
  /**
   * Set the link where the bendpoint is located. 
   * @param link the link where the bendpoint is located. 
   */
  public void setPolyLineEdge(final PolyLineEdgeType edge) {
    this.edge = edge;
  }
   
   }