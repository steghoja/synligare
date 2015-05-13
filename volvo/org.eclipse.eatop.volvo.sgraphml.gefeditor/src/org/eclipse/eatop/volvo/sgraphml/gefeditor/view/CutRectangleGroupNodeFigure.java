package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.EllipseAnchor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.anchor.CutRectangleAnchor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.shape.CutRectangle;

/***
 *
 */
public class CutRectangleGroupNodeFigure extends GroupNodeFigure {

	public CutRectangleGroupNodeFigure(){
		super(new GradientCutRectangle());
	}
	
	@Override
	public ConnectionAnchor getConnectionAnchor() {
		
	    if (connectionAnchor == null) {
	    	connectionAnchor = new CutRectangleAnchor((CutRectangle)shape);
	    }
	    return connectionAnchor;
	}
}
