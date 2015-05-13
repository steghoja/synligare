package org.eclipse.eatop.volvo.sgraphml.gefeditor.commands;

import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.BaseNodeType;
import eu.synligare.sgraphml.SgraphmlFactory;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;

import eu.synligare.sgraphml.ShapeNodeType;

public class NodeTypeChangeConstraintCommand extends Command {
	 
	  private GeometryType oldConstraint;
	  private GeometryType newConstraint;
	  
	  private BaseNodeType modelElement;
	  private boolean selected;				//whether the corresponding editpart was selected
	  
	  @Override public void execute() {
		  if(oldConstraint == null) { 
			  oldConstraint = modelElement.getGeometry();
		}
	    modelElement.setGeometry(newConstraint);
	  }
	 
	  @Override public void undo() {
		  modelElement.setGeometry(oldConstraint);
	  }
	 
	  public void setModel(BaseNodeType modelElement) {
	    this.modelElement = modelElement;
	  }

	  public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setNewConstraint(Rectangle newConstraint) {
		  //Rectangle -> Geometry
		   GeometryType newGeometry = SgraphmlFactory.eINSTANCE.createGeometryType();
		    newGeometry.setX(newConstraint.x);
		    newGeometry.setY(newConstraint.y);
		    newGeometry.setHeight(newConstraint.height);
		    newGeometry.setWidth(newConstraint.width);
		  
		  this.newConstraint = newGeometry;    
	  }
	  
	  
	  
}
