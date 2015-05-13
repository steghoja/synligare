package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.shape.CutRectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;

public class GradientCutRectangle extends CutRectangle implements IGradient {

	Color color2;

	@Override
	public Color getColor2() {
		return color2;
	}

	@Override
	public void setColor2(Color color2) {
		this.color2 = color2;
	}


	@Override
	protected void fillShape(Graphics graphics)
	{
		if (getColor2()!= null) {
			Rectangle r = getBounds();
		    Pattern scaledPattern = Utils.createScaledPattern(graphics, r.getTopLeft().x,
		            r.getTopLeft().y, r.getBottomRight().x, r.getBottomRight().y, 
		            getBackgroundColor(), getColor2());
		    graphics.setBackgroundPattern(scaledPattern);
		    calculateRectanglePoints(r);
		    graphics.fillPolygon(getCutRectanglePoints());
		    scaledPattern.dispose();
		}
		else{
			super.fillShape(graphics);
		}
	}
	}
	
