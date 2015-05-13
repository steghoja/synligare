package org.eclipse.eatop.volvo.sgraphml.gefeditor.view.anchor;

import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.shape.CutRectangle;

/**
 * Anchor for cut rectangles which is always on a line between the center
 * and the reference point.
 * 
 * @author Henrik Kaijser, inspired by GEF RoundedRectangleAnchor
 */
public class CutRectangleAnchor extends ChopboxAnchor {

	private static final int LEFT = 1;
	private static final int MIDDLE = 2;
	private static final int RIGHT = 4;
	private static final int TOP = 8;
	private static final int CENTER = 16;
	private static final int BOTTOM = 32;

	private final Dimension dimension;

	/**
	 * Rounded Rectangle getCornerDimension should be public #302836 then
	 * Rounded Rectangle would be sufficient.
	 */
	public CutRectangleAnchor(final CutRectangle figure) {
		super(figure);
		dimension = null;
	}


	/**
	 * Calculates the position with ChopboxAnchor#getLocation() and if the
	 * anchor is not at the cut corners, the result is returned. If the
	 * anchor point should be at a corner, the intersection point of the 
	 * corner line and the line from center to the ref point is calculated.	 * 
	 * 
	 * @return The anchor location
	 */
	public Point getLocation(final Point ref) {
		Dimension corner = ((CutRectangle)getOwner()).getCornerDimensions();
		getOwner().translateToAbsolute(corner);
		
		final Point location = super.getLocation(ref);
		final Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getOwner().getBounds());
		r.translate(-1, -1);
		r.resize(1, 1);
		getOwner().translateToAbsolute(r);
		final int yTop = r.y + corner.height;
		final int yBottom = r.y + r.height - corner.height;
		final int xLeft = r.x + corner.width;
		final int xRight = r.x + r.width - corner.width;
		int pos = 0;
		if (location.x < xLeft) {
			pos = LEFT;
		} else if (location.x > xRight) {
			pos = RIGHT;
		} else {
			pos = MIDDLE;
		}
		if (location.y < yTop) {
			pos |= TOP;
		} else if (location.y > yBottom) {
			pos |= BOTTOM;
		} else {
			pos += CENTER;
		}

		PointList points = ((CutRectangle)getOwner()).getCutRectanglePoints();
		Point center = getOwner().getBounds().getCenter();
		getOwner().translateToAbsolute(center);
		
		
		switch (pos) {
		case TOP | MIDDLE:
		case CENTER | LEFT:
		case CENTER | RIGHT:
		case BOTTOM | MIDDLE:
			return new Point(location.x, location.y);
		case TOP | LEFT:
		{
			Point t1 = points.getPoint(6);
			getOwner().translateToAbsolute(t1);
			Point t2 = points.getPoint(7);
			getOwner().translateToAbsolute(t2);
			return getLocationOfIntersection(center, ref, t1, t2); 
		}
		case TOP | RIGHT:
		{
			Point t1 = points.getPoint(0);
			getOwner().translateToAbsolute(t1);
			Point t2 = points.getPoint(1);
			getOwner().translateToAbsolute(t2);
			return getLocationOfIntersection(center, ref, t1, t2); 
		}
		case CENTER | MIDDLE:
			// default for reference inside Figure
			return new Point(r.x, r.y + r.height / 2);
		case BOTTOM | LEFT:
		{
			Point t1 = points.getPoint(4);
			getOwner().translateToAbsolute(t1);
			Point t2 = points.getPoint(5);
			getOwner().translateToAbsolute(t2);
			return getLocationOfIntersection(center, ref, t1, t2); 
		}
		case BOTTOM | RIGHT:
		{
			Point t1 = points.getPoint(2);
			getOwner().translateToAbsolute(t1);
			Point t2 = points.getPoint(3);
			getOwner().translateToAbsolute(t2);
			return getLocationOfIntersection(center, ref, t1, t2); 
		}
		default:
			throw new IllegalStateException(
					"Calculation of CutRectangleAnchor missed. Rect: " + r 
					+ " Point: " + location); 
		}
	}

	/***
	 * Calculates the intersection of two lines s and t, given that 
	 * 1) they are not parallel, i..e ks /= kt
	 * 2) s2.x - s1.x not zero, i.e s is not vertical
	 * 3) t2.x - t1.x not zero, i.e t is not vertical
	 */

	protected Point getLocationOfIntersection (Point s1, Point s2, Point t1, Point t2){

		//Find equation of line s: y = ks*x + ms
		double ks =  (double)(s2.y - s1.y) / (double)(s2.x - s1.x);
		double ms = s1.y - ks * s1.x;

		//Find equation of line t: y = kt*x + mt
		double kt =  (double)(t2.y - t1.y) / (double)(t2.x - t1.x);
		double mt = t1.y - kt *t1.x;

		//Point of intersection is: x = (mt - ms) / (ks - kt), y = (ks*mt - kt*ms) / (ks - kt) 
		double x = (mt - ms) / (ks - kt);
		double y = (ks*mt - kt*ms) / (ks - kt);

		return new Point((int)Math.round(x), (int)Math.round(y));
	}
}