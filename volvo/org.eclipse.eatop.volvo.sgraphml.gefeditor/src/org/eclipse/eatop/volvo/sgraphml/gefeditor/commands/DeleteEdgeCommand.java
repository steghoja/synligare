package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import java.util.ArrayList;
import java.util.List;







import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.gef.commands.Command;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.NodeType;

public class DeleteEdgeCommand extends Command {
	/**
	 * Command used to delete an edge.
	 */
	    /** Link to be deleted. */
	    protected EdgeType edge;

	    protected int index;
	    
	    /** Source of the link. */
	    protected String source;
	    /** Target of the link. */
	    protected String target;
	 
	    protected List<Adapter> adapters = new ArrayList<Adapter>();
	    
	    
	    /**
	     * {@inheritDoc}
	     */
	    @Override public boolean canExecute() {
	        return edge != null;
	    }
	 
	    /**
	     * Disconnect link from source and target nodes and remove
	     * from owner graph.
	     */
	    @Override public void execute() {
	    	//Save all 2 adapters connected to edge
	//    	adapters.clear();
	//        adapters.addAll(edge.eAdapters());
	        
	        
	        //The controller listens to these changes and removes adapters acoordingly
	    	source = edge.getSource();
	        target = edge.getTarget();
	        edge.setSource(null);
	        edge.setTarget(null);

	        //remove edge from graph
	        List<EdgeType> edges = ModelProcessor.INSTANCE.getRootGraph().getEdge();
	       // index = edges.indexOf(edge);
	        edges.remove(edge);
	    }
	 
	    /**
	     * Reconnect the link to the source and target and add
	     * it to the owner graph.
	     */
	    @Override public void undo() {
	    	//setup model without listeners
	    	edge.setSource(source); 
	        edge.setTarget(target);
	        ModelProcessor.INSTANCE.getRootGraph().getEdge().add(edge); 

	        //hitta source & dest 
	        
	        
	        //add listeners & notify by writing again
	 //   	edge.eAdapters().addAll(adapters);
	 //   	edge.setSource(source); 
	 //       edge.setTarget(target);
	    }
	    
	    
	   
	    /**
	     * Set the edge that will be delete from the diagram.
	     * @param edge the edge to delete from the diagram.
	     */
	    public void setEdge(final EdgeType edge) {
	        this.edge = edge;
	    }
}
