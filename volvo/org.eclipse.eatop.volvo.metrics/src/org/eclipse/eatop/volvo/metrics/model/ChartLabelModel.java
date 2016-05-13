package org.eclipse.eatop.volvo.metrics.model;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class ChartLabelModel {
	
	// TODO compute the size from the label? or at least something without magic numbers
	private final int width = 500;
	private final int height = 15;
	
	private String label;
	private Color color;
	private Rectangle bounds;
	private String toolTipText;
	
	public ChartLabelModel(String label, Color color, String toolTip, int x, int y) {
		this.label = label;
		this.color = color;
		this.toolTipText = toolTip;
		bounds = new Rectangle(x, y, width, height);
	}
	
	public ChartLabelModel(String label, Color color) {
		this(label, color, "", 0, 0);
	}

	public String getLabel() {
		return label;
	}
	
	public Color getColor() {
		return color;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setXY(int x, int y) {
		bounds.setX(x);
		bounds.setY(y);
	}

	public String getToolTipText() {
		return toolTipText;
	}

	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}
	
}
