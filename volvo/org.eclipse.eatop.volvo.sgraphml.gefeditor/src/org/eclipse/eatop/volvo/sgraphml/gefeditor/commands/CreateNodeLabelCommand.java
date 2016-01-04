package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.gef.commands.Command;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.NodeLabelType;
 

public final class CreateNodeLabelCommand extends Command {
 
    private int index;
    private BaseNodeType node;
    private NodeLabelType nodeLabel;
    
     public NodeLabelType getNodeLabel() {
		return nodeLabel;
	}

	public void setNodeLabel(NodeLabelType nodeLabel) {
		this.nodeLabel = nodeLabel;
	}

	public CreateNodeLabelCommand() {
	}
    
    @Override public void execute() {
    	node.getNodeLabel().add(nodeLabel);
    	index = node.getNodeLabel().size() - 1; 
    }
 

	public BaseNodeType getNode() {
		return node;
	}


	public void setNode(BaseNodeType node) {
		this.node = node;
	}


	@Override public void undo() {
		node.getNodeLabel().remove(index);
     }
 
}