package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.gef.commands.Command;

import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PlacementType;

/***
 * 
 * A touch command for nodeLabelType that can be used to force update in the View
 * This is useful when the label refers to an East-ADL object E with an attribute A,
 * and the attribute A has been changed.
 *
 */
public class TouchNodeLabelCommand extends Command {
	  private NodeLabelType nodeLabel;
	  
	  @Override 
	  public void execute() {
		 PlacementType placement = nodeLabel.getPlacement();
		 nodeLabel.setPlacement(placement);
	  }
	 
	  @Override 
	  public void undo() {
			 PlacementType placement = nodeLabel.getPlacement();
			 nodeLabel.setPlacement(placement);
	  }

	public NodeLabelType getNodeLabel() {
		return nodeLabel;
	}

	public void setNodeLabel(NodeLabelType nodeLabel) {
		this.nodeLabel = nodeLabel;
	}

}