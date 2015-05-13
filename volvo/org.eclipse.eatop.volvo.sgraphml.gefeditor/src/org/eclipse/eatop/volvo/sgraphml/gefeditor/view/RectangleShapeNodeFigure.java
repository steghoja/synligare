package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;


public class RectangleShapeNodeFigure extends ShapeNodeFigure {

	
	public RectangleShapeNodeFigure(boolean bLabel){
		super(new GradientRectangle(), bLabel);
	}


			
	public ConnectionAnchor getConnectionAnchor() {
		
	    if (connectionAnchor == null) {
	      connectionAnchor = new ChopboxAnchor(shape);
	    }
	    return connectionAnchor;
	}

	
}
