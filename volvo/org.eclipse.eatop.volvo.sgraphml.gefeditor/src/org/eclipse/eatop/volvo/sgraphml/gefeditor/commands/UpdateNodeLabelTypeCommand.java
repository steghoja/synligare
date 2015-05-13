package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.gef.commands.Command;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PortNodeType;

public class UpdateNodeLabelTypeCommand extends Command {
	 private NodeLabelType oldNodeLabel;
	  private NodeLabelType newNodeLabel;
	  
	  private BaseNodeType modelElement;
	  
	  @Override 
	  public void execute() {
		  if(oldNodeLabel == null) { 
			  oldNodeLabel = modelElement.getNodeLabel().get(0);
		}
	    modelElement.getNodeLabel().set(0, newNodeLabel);
	    }
	 
	  @Override 
	  public void undo() {
		  modelElement.getNodeLabel().set(0, oldNodeLabel);
	  }
	 
	  public void setModel(BaseNodeType modelElement) {
	    this.modelElement = modelElement;
	  }

	  public void setNewNodeLabel(NodeLabelType newNodeLabel) {
		  this.newNodeLabel  = newNodeLabel;
	  }
}
