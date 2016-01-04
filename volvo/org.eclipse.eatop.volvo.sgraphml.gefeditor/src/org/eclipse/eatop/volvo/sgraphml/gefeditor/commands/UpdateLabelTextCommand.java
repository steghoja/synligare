package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.gef.commands.Command;
import org.graphdrawing.graphml.xmlns.NodeType;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.NodeLabelType;


public class UpdateLabelTextCommand extends Command {
	  private BaseNodeType node;
	  String oldText;
	  String text;
	  
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override 
	  public void execute() {
		NodeLabelType nodeLabel = node.getNodeLabel().get(0);		
		String oldText = Utils.getLabelText(nodeLabel);
		Utils.setLabelText(nodeLabel, text);
	  }
	 
	  @Override 
	  public void undo() {
		  	NodeLabelType nodeLabel = node.getNodeLabel().get(0);
			Utils.setLabelText(nodeLabel, oldText);
	  }

	public BaseNodeType getNode() {
		return node;
	}

	public void setNode(BaseNodeType node) {
		this.node = node;
	}
	  
}