package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.EastAdlSgraphmlSynchronizer;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.graphdrawing.graphml.xmlns.GraphType;
import org.graphdrawing.graphml.xmlns.NodeType;

public class CreateNodeCommand extends Command {
	
	 protected NodeType node; 
	 protected GraphType parentGraph;
	 protected EObject  eaEObject;
	 
	  public EObject getEaEObject() {
		return eaEObject;
	}

	public void setEaEObject(EObject eaEObject) {
		this.eaEObject = eaEObject;
	}

	@Override 
	  public void execute() {
		  parentGraph.getNode().add(node);
		  Utils.INSTANCE.getModelSynchronizer().addEastAdlObjectAdapter(eaEObject, node.getId(), node);
	  }

	  @Override 
	  public void undo() {
		   parentGraph.getNode().remove(node);
		  Utils.INSTANCE.getModelSynchronizer().removeEastAdlObjectAdapter(eaEObject, node);
	  }

	  public void setNode(NodeType node) {
		this.node = node;
	}

	public void setParentGraph(GraphType parentGraph) {
		this.parentGraph = parentGraph;
	}
	  
}
