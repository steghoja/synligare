package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import org.eclipse.gef.commands.Command;

import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.HorizontalTextPositionType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PlacementType;
import eu.synligare.sgraphml.PortNodeType;
import eu.synligare.sgraphml.VerticalTextPositionType;

public class UpdateNodeLabelTypeCommand extends Command {
	// private NodeLabelType oldNodeLabel;
	//  private NodeLabelType newNodeLabel;
	  
	  PlacementType oldPlacement, placement;
	  HorizontalTextPositionType oldHorizontalTextPosition, horizontalTextPosition;
	  VerticalTextPositionType oldVerticalTextPosition, verticalTextPosition;
	  
	  
	  
	  private NodeLabelType nodeLabel;
	  
	  @Override 
	  public void execute() {
		oldPlacement = nodeLabel.getPlacement();
		oldHorizontalTextPosition = nodeLabel.getHorizontalTextPosition();
		oldVerticalTextPosition = nodeLabel.getVerticalTextPosition();
		
		nodeLabel.setPlacement(placement);
		nodeLabel.setHorizontalTextPosition(horizontalTextPosition);
		nodeLabel.setVerticalTextPosition(verticalTextPosition);
		
	    }
	 
	  @Override 
	  public void undo() {
			nodeLabel.setPlacement(oldPlacement);
			nodeLabel.setHorizontalTextPosition(oldHorizontalTextPosition);
			nodeLabel.setVerticalTextPosition(oldVerticalTextPosition);
	  }
	 
	  public void setNodeLabel(NodeLabelType nodeLabel) {
	    this.nodeLabel = nodeLabel;
	  }

	 
	public void setPlacement(PlacementType placement) {
		this.placement = placement;
	}

	public void setHorizontalTextPosition(
			HorizontalTextPositionType horizontalTextPosition) {
		this.horizontalTextPosition = horizontalTextPosition;
	}

	public void setVerticalTextPosition(
			VerticalTextPositionType verticalTextPosition) {
		this.verticalTextPosition = verticalTextPosition;
	}
	  
	  
	  
}
