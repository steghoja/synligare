package org.eclipse.eatop.volvo.requirementmetricsviewer.models;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class PiePieceModel {

	Color color;
	Rectangle bounds;
	
	int startAngle, angle;
	
	/**
	 * Creates a diagram piece with the proportion 100 %
	 * 
	 * @param bounds Specifies the size and location of this piece
	 */
	public PiePieceModel(Rectangle bounds) {
		color = new Color(null, 255, 0, 0);
		this.bounds = bounds;
		this.startAngle = 0;
		this.angle = 360;
	}
	
	public PiePieceModel(Color color, int angle, int startAngle, Rectangle bounds) {
		this.color = color;
		this.startAngle = startAngle;
		this.angle = angle;
		this.bounds = bounds;
	}

	public Color getColor() {
		return color;
	}
	
	public int getStartAngle() {
		return startAngle;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
