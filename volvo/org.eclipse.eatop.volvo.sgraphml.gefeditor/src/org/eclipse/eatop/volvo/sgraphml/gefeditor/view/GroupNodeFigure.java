package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class GroupNodeFigure extends ShapeNodeFigure {
	
	public GroupNodeFigure(Shape s){
		super(s, true);
	}
	
	//This is where the child editparts will put their graphs
	public IFigure getContentsPane() {
		return shape; 
	}
	
	
	public Rectangle getPortNodeRectangle(PortNodeFigure port){
			return new Rectangle((Rectangle)shape.getLayoutManager().getConstraint(port));
		
	}
}
