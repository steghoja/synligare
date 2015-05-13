package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.RoundedRectangleAnchor;
import org.eclipse.draw2d.Shape;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.anchor.ChopboxAnchor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.anchor.CutRectangleAnchor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.shape.CutRectangle;

public class CutRectangleShapeNodeFigure extends ShapeNodeFigure {

	public CutRectangleShapeNodeFigure() {
		super(new GradientCutRectangle(), true);
	}

	public ConnectionAnchor getConnectionAnchor() {
		
	    if (connectionAnchor == null) {
	    	connectionAnchor = new CutRectangleAnchor((CutRectangle)shape);
	    }
	    return connectionAnchor;
	}

	
}
