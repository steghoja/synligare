package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Shape;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.anchor.TriangleAnchor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.shape.Triangle;

public class TriangleShapeNodeFigure extends ShapeNodeFigure {

	public TriangleShapeNodeFigure() {
		super(new GradientTriangle(), true);
	}

	public ConnectionAnchor getConnectionAnchor() {
		
	    if (connectionAnchor == null) {
	      connectionAnchor = new TriangleAnchor((Triangle)shape);
	    }
	    return connectionAnchor;
	}
	
}
