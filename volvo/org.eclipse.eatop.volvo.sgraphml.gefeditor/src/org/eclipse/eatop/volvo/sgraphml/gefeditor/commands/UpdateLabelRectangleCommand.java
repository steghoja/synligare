package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.LabelType;
import eu.synligare.sgraphml.SgraphmlFactory;

import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import eu.synligare.sgraphml.ShapeNodeType;

public class UpdateLabelRectangleCommand extends Command {
	 
	 private boolean first = true;
	
	  private double oldX;
	  private double oldY;
	  private double oldWidth;
	  private double oldHeight;
	  
	  private Rectangle rNew;
	  
	  
	  private LabelType modelElement;
	  
	  @Override public void execute() {
		if (first){
  		    oldX = modelElement.getX();
		    oldY = modelElement.getY();
		    oldWidth = modelElement.getWidth();
		    oldHeight = modelElement.getHeight();
		    first = false;
		}

		modelElement.setX(rNew.x);  
		modelElement.setY(rNew.y);  
		modelElement.setWidth(rNew.width);  
		modelElement.setHeight(rNew.height);  
	  }
	 
	  @Override public void undo() {
		  modelElement.setX(oldX);
		  modelElement.setY(oldY);
		  modelElement.setWidth(oldWidth);
		  modelElement.setHeight(oldHeight);
	  }
	 
	  public void setModel(LabelType modelElement) {
	    this.modelElement = modelElement;
	  }


	public void setNewRectangle(Rectangle r) {
		rNew = r;
	  }
	  
	  
	  
}
