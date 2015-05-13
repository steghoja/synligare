package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.shape.Triangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Pattern;

public class GradientTriangle extends Triangle implements IGradient {

	@Override
	protected void fillShape(Graphics graphics)
	{
		if (getColor2() != null) {
			Rectangle R = getBounds();
		    Pattern scaledPattern = Utils.createScaledPattern(graphics, R.getTopLeft().x,
		            R.getTopLeft().y, R.getBottomRight().x, R.getBottomRight().y, 
		            getBackgroundColor(), getColor2());
		    graphics.setBackgroundPattern(scaledPattern);

		    graphics.fillPolygon(getTriangle());

		    scaledPattern.dispose();
		}
		else{
			super.fillShape(graphics);
		}

	}
	Color color2;

	@Override
	public Color getColor2() {
		return color2;
	}

	@Override
	public void setColor2(Color color2) {
		this.color2 = color2;
	}

}