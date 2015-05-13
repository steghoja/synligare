package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;

public class RoundRectangleGroupNodeFigure extends GroupNodeFigure {

	public RoundRectangleGroupNodeFigure(){
		super(new GradientRoundRectangle());
		((RoundedRectangle)shape).setCornerDimensions(new Dimension(24,24));
	}
	
	@Override
	public ConnectionAnchor getConnectionAnchor() {
		
	    if (connectionAnchor == null) {
	      connectionAnchor = new ChopboxAnchor(shape);
	    }
	    return connectionAnchor;
	}
}
