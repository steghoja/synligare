package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.gef.commands.Command;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.NodeType;

public class CreateEdgeCommand extends Command {
	
	 protected EdgeType edge; 
	 protected GraphType parentGraph;
	 
	  @Override 
	  public void execute() {
		  parentGraph.getEdge().add(edge);
	  }

	  @Override 
	  public void undo() {
		   parentGraph.getEdge().remove(edge);
	  }

	  public void setEdge(EdgeType edge) {
		this.edge = edge;
	}

	public void setParentGraph(GraphType parentGraph) {
		this.parentGraph = parentGraph;
	}
	  
}
