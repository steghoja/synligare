package org.eclipse.eatop.volvo.visualizer.common.graphicalmodels;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * 
 * Class representing a shape of a category or level of a graphical background
 *
 */
public class Shape {
	// TODO make at least one more shape (custom) and put "shared" variables here

	protected Color color;
	protected int zPos = 0;
	protected boolean outline;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getZPos() {
		return zPos;
	}

	public void setZPos(int zPos) {
		this.zPos = zPos;
	}

	public boolean hasOutline() {
		return outline;
	}

	public void setOutline(boolean outline) {
		this.outline = outline;
	}

	/**
	 * Finds the intersecting area of two shapes (if they intersect) and return this
	 * as an <code>IFigure</code>
	 * @param otherShape 
	 * @return A new <code>IFigure</code> representing the intersecting area or null if
	 * they don't intersect. 
	 */
	public IFigure intersectingArea(Shape otherShape) {
		IFigure intersection = null;

		// TODO calculation of custom shapes
		if (this instanceof Box && otherShape instanceof Box) {
			Box box1 = (Box)this;
			Box box2 = (Box)otherShape;
			Rectangle r1 = new Rectangle(box1.getXPos(), box1.getYPos(), box1.getXSize(),
					box1.getYSize());
			Rectangle r2 = new Rectangle(box2.getXPos(), box2.getYPos(), box2.getXSize(), 
					box2.getYSize());

			if (r1.intersects(r2)) {
				intersection = new RectangleFigure();
				intersection.setBounds(r1.intersect(r2));
			}
		}

		return intersection;
	}


	public class CustomShape extends Shape {
		// TODO
	}

	public class Box extends Shape {

		private int xPos;
		private int yPos;

		private int xSize;
		private int ySize;		

		public int getXPos() {
			return xPos;
		}

		public void setXPos(int xPos) {
			this.xPos = xPos;
		}

		public int getYPos() {
			return yPos;
		}

		public void setYPos(int yPos) {
			this.yPos = yPos;
		}

		public int getXSize() {
			return xSize;
		}

		public void setXSize(int xSize) {
			this.xSize = xSize;
		}

		public int getYSize() {
			return ySize;
		}

		public void setYSize(int ySize) {
			this.ySize = ySize;
		}

		public String toString() {
			return "Box pos (" + xPos + ", " + yPos + "), size x " + xSize + ", size y " + ySize
					+ (color != null ? ", color " + color.toString() : "") +
					" outline is " + hasOutline();
		}
	}

	public class Line extends Shape {

		private int startX;
		private int startY;
		private int endX;
		private int endY;		

		public int getStartX() {
			return startX;
		}

		public void setStartX(int startX) {
			this.startX = startX;
		}

		public int getStartY() {
			return startY;
		}

		public void setStartY(int startY) {
			this.startY = startY;
		}

		public int getEndX() {
			return endX;
		}

		public void setEndX(int endX) {
			this.endX = endX;
		}

		public int getEndY() {
			return endY;
		}

		public void setEndY(int endY) {
			this.endY = endY;
		}

		public String toString() {
			return "Line start (" + startX + ", " + startY + "), end " + endX + ", " + endY;
		}
	}
}
