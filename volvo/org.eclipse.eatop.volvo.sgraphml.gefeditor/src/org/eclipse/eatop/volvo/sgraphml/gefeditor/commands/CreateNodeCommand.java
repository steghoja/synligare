package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.gef.commands.Command;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.NodeType;

public class CreateNodeCommand extends Command {
	
	 protected NodeType node; 
	 protected GraphType parentGraph;
	 
	  @Override 
	  public void execute() {
		  parentGraph.getNode().add(node);
	  }

	  @Override 
	  public void undo() {
		   parentGraph.getNode().remove(node);
	  }

	  public void setNode(NodeType node) {
		this.node = node;
	}

	public void setParentGraph(GraphType parentGraph) {
		this.parentGraph = parentGraph;
	}
	  
}
