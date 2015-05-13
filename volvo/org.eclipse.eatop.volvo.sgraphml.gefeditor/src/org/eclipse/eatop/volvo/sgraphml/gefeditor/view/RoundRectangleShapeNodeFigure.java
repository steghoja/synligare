package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.RoundedRectangleAnchor;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;

public class RoundRectangleShapeNodeFigure extends ShapeNodeFigure {

	public RoundRectangleShapeNodeFigure() {
		super(new GradientRoundRectangle(), true);
		((RoundedRectangle)shape).setCornerDimensions(new Dimension(24, 24));

	}

	public ConnectionAnchor getConnectionAnchor() {
		
	    if (connectionAnchor == null) {
	      connectionAnchor = new RoundedRectangleAnchor((RoundedRectangle)shape);
	    }
	    return connectionAnchor;
	}


	
}
