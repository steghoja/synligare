package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.graph.Edge;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.ModelProcessor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.graphdrawing.graphml.xmlns.EdgeType;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.GraphmlType;
import org.graphdrawing.graphml.xmlns.NodeType;

import eu.synligare.sgraphml.PolyLineEdgeType;
import eu.synligare.sgraphml.ShapeNodeType;

public class DeleteNodeTypeCommand extends Command {
	  private NodeType node;
	  private int index;
	  private GraphType graph;
	  //private EList<NodeType> nodes;
	 
	  private List<EdgeType> incoming = Collections.EMPTY_LIST;
	  private List<EdgeType> outgoing = Collections.EMPTY_LIST;
	  
	  
	  @Override
	  public void execute() {
	    detachConnections();
		  graph.getNode().remove(node);
	  }
	 
	  @Override
	  public void undo() {
	    reattachConnections();
	    graph.getNode().add(index, node);
	  }
	 
	  public void setNode(NodeType node) {
	    this.node = node;
	    this.graph = (GraphType)node.eContainer();
	    this.index = graph.getNode().indexOf(node);
	  }
	  
	  
	  protected void detachConnections(){
		  GraphType root = ModelProcessor.INSTANCE.getRootGraph();
		  
		  //use loop instead of removeAll to simplify for adapters
		  for (EdgeType e : incoming) {
			root.getEdge().remove(e);
		  }
		  
		  for (EdgeType e : outgoing) {
				root.getEdge().remove(e);
			  }

	  }
	  
	  protected void reattachConnections(){
		  GraphType root = ModelProcessor.INSTANCE.getRootGraph();

		  for (EdgeType e : incoming) {
			root.getEdge().add(e);
		  }

		  for (EdgeType e : outgoing) {
			root.getEdge().add(e);
		  }
	  }

	public void setIncomingEdges(List<EdgeType> incoming) {
		this.incoming = incoming;
	}
	public void setOutgoingEdges(List<EdgeType> outgoing) {
		this.outgoing = outgoing;
	}

	
	  
}
