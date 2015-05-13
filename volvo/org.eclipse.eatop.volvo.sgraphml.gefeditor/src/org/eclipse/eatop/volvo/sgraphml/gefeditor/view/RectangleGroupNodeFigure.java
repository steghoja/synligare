package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.RectangleFigure;

public class RectangleGroupNodeFigure extends GroupNodeFigure {

	public RectangleGroupNodeFigure(){
		super(new GradientRectangle());
	}

	@Override
	public ConnectionAnchor getConnectionAnchor() {
		
	    if (connectionAnchor == null) {
	      connectionAnchor = new ChopboxAnchor(shape);
	    }
	    return connectionAnchor;
	}
}
