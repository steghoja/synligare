package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.EllipseAnchor;

/***
 * This class is not intended to have ports, the geometry will be wrong.
 *
 */
public class EllipseGroupNodeFigure extends GroupNodeFigure {

	public EllipseGroupNodeFigure(){
		super(new GradientEllipse());
	}
	
	@Override
	public ConnectionAnchor getConnectionAnchor() {
		
	    if (connectionAnchor == null) {
	      connectionAnchor = new EllipseAnchor(shape);
	    }
	    return connectionAnchor;
	}
}
