package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;

public class GroupNodeFigure extends ShapeNodeFigure {
	
	public GroupNodeFigure(Shape s){
		super(s, true);
	}
	
		
	public Rectangle getPortNodeRectangle(PortNodeFigure port){
			return new Rectangle((Rectangle)shape.getLayoutManager().getConstraint(port));
		
	}
}
