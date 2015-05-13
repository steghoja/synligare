package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.gef.commands.Command;

import eu.synligare.sgraphml.PointType;
import eu.synligare.sgraphml.PolyLineEdgeType;

/**
 * Command used to delete a bendpoint from a {@link PolyLineEdgeType}
 * This class is declared final since it has a very specific functionality.

 */
public final class PolyLineEdgeDeleteEndpointCommand extends Command {
 
  /** Edge that contains the bendpoint. */
  private PolyLineEdgeType edge;
  /** Index where the bendpoint is located in the edge's bendpoint list. */
  private int index;
  /** Point in the diagram where the bendpoint is located. */
  private PointType location;
   
  /**
   * Only execute is link is not null and index is valid.
   */
  @Override public boolean canExecute() {
    return (edge != null) && (edge.getPath().getPoint().size() > index);
  }
  /** 
   * Remove the bendpoint from the link. 
   */
  @Override public void execute() {
    location = edge.getPath().getPoint().get(index);
    edge.getPath().getPoint().remove(index);
  }
 
  /**
   * Reinsert the bendpoint in the link.
   */
  @Override public void undo() {
    edge.getPath().getPoint().add(index, location);
  }
   
  /**
   * Set the index of the bendpoint that should be removed.
   * @param index the index of the bendpoint to remove.
   */
  public void setIndex(final int index) {
    this.index = index;
  }
   
  /**
   * Set the link from which the bendpoint is removed.
   * @param link the link from which the bendpoint is removed.
   */
  public void setPolyLineEdge(final PolyLineEdgeType edge) {
    this.edge = edge;
  } 
}