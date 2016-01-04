package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.gef.commands.Command;
import org.graphdrawing.graphml.xmlns.NodeType;


public class UpdateNodeIDReferenceCommand extends Command {
	  private NodeType node;
	  String dotPath;
	  String oldDotPath;
	  
	  public String getDotPath() {
		return dotPath;
	}

	public void setDotPath(String dotPath) {
		this.dotPath = dotPath;
	}

	  
	  @Override 
	  public void execute() {
		oldDotPath = node.getId();
		node.setId(dotPath);
	  }
	 
	  @Override 
	  public void undo() {
		node.setId(oldDotPath);
	  }

	public NodeType getNode() {
		return node;
	}

	public void setNode(NodeType node) {
		this.node = node;
	}
	  
}