package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import eu.synligare.sgraphml.PointType;
import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.SgraphmlFactory;
 
/**
 * Command used to create a new bendpoint in a {@linkplain PolyLine}.
 * This class is declared final since it has a very specific functionality.
 *
 */
public final class PolyLineEdgeCreateBendPointCommand extends Command {
 
    /** Index on which the new bendpoint is added. */
    private int index;
    /** Location of new bendpoint. */
    private PointType location;
    /** Link to which the bendpoint is added. */
    private PolyLineEdgeType edge;
 
    
    public PolyLineEdgeCreateBendPointCommand() {
    	location = SgraphmlFactory.eINSTANCE.createPointType();
	}
    
    
    @Override public void execute() {
        edge.getPath().getPoint().add(index, location);
    }
 
    @Override public void undo() {
    	edge.getPath().getPoint().remove(index);
     }
 
    /**
     * Set the index on which the bendpoint is added.
     * @param index Index on which the bendpoint should be added.
     */
    public void setIndex(final int index) {
        this.index = index;
     }
 
    /**
     * Set the location where the new bendpoint is added.
     * @param location point in the diagram where the new bendpoint
     * is added.
     */
    public void setLocation(final Point location) {
       
    	this.location.setX(location.x); 
     	this.location.setY(location.y); 
    
    }
 
    /**
     * Set the link on which the new bendpoint is added.
     * @param link link on which the bendpoint is added.
     */
    public void setPolyLineEdge(final PolyLineEdgeType edge) {
        this.edge = edge;
    }
}