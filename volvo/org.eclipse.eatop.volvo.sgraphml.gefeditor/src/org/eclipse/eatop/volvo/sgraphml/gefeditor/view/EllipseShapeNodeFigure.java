package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Shape;

public class EllipseShapeNodeFigure extends ShapeNodeFigure {

	public EllipseShapeNodeFigure() {
		super(new GradientEllipse(), true);
	}

	
	public ConnectionAnchor getConnectionAnchor() {
		
	    if (connectionAnchor == null) {
	      connectionAnchor = new EllipseAnchor(shape);
	    }
	    return connectionAnchor;
	}
}
