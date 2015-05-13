package org.eclipse.eatop.volvo.requirementmetricsviewer.figures;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A figure representing a diagram label which means it has a colored box in front of a string
 * 
 * @author Joanna Svantesson
 *
 */
public class ChartLabel extends Shape {
		
	public ChartLabel() {
		super();
	}
	
	@Override
	protected void fillShape(Graphics graphics) {
		int side = getBounds().height() - 5; 
		graphics.fillRectangle(new Rectangle(getBounds().x(), getBounds().y() + 2, 
				side, side)); 
	}

	@Override
	protected void outlineShape(Graphics graphics) {
		int side = getBounds().height() - 5;
		graphics.drawRectangle(new Rectangle(getBounds().x(), getBounds().y() + 2,
				side, side)); 
	}
	
}
