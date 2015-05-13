package org.eclipse.eatop.volvo.requirementmetricsviewer.figures;


import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Defines one piece of a circle diagram
 * 
 * @author Joanna Svantesson
 *
 */
public class PiePiece extends Shape {

	// How large angle of a circle this piece has
	// 0 < angle <= 360
	private int angle;
	// The angle to start at (0 is 3 o'clock)
	private int startAngle;
	
	public PiePiece(int angle, int startAngle) {
		super();
		if (angle < 0 || angle > 360) {
			throw new IllegalArgumentException("The angle is too small or to big. It must be between 0"
					+ " and 360. (It now is " + angle + ")");
		} else if (startAngle < 0 || startAngle >= 360) {
			throw new IllegalArgumentException("The start angle is too small or to big. It must be between 0"
					+ "(included) and 360 (excluded).");
		}
		this.angle = angle;
		this.startAngle = startAngle;

	}
	
	@Override
	protected void fillShape(Graphics graphics) {		
		graphics.fillArc(getBounds(), startAngle, angle);
	}

	@Override
	protected void outlineShape(Graphics graphics) {		
		graphics.drawArc(getOptimizedBounds(), startAngle, angle);
				
		if (angle != 360) {
			PrecisionPoint origin = getOrigin();
			PrecisionPoint startPoint = getStartPoint(startAngle);
			PrecisionPoint endPoint = rotateAroundPoint(startPoint, origin, angle);
			graphics.drawLine(origin, startPoint);
			graphics.drawLine(origin, endPoint);
		}
	}
	
	/**
	 * Rotate the point <code>p</code> around the point <code>origin</code> by the angle <code>a</code>
	 * degrees in clockwise direction.
	 * 
	 * @param p
	 * @param origin
	 * @return The result of the rotation
	 */
	private PrecisionPoint rotateAroundPoint(PrecisionPoint p, PrecisionPoint origin, double a) {
		a = Math.toRadians(a); // Need to convert to radians
		double newX = origin.x() + ( Math.cos(a) * (p.x() - origin.x()) 
				+ Math.sin(a) * (p.y() - origin.y()) );
		double newY = origin.y() + ( -Math.sin(a) * (p.x() - origin.x()) 
				+ Math.cos(a) * (p.y() - origin.y()));
		
		return new PrecisionPoint(newX, newY);
	}
	
	/**
	 * Calculate the position of the origin
	 * 
	 * @return The origin as a PrecisionPoint
	 */
	private PrecisionPoint getOrigin() {
		double x = getBounds().getCenter().x();
		double y = getBounds().getCenter().y();
		return new PrecisionPoint(x, y);
	}
	
	/**
	 * Calculate the actual position from knowing the start angle, the radius and the origin
	 * 
	 * @param startAngle
	 * @return The start point for a diagram piece given its start angle
	 */
	private PrecisionPoint getStartPoint(double startAngle) {
		double radius = getOptimizedBounds().height()/2;
		PrecisionPoint origin = getOrigin();
		
		Point p = origin.getTranslated(radius, 0);
		return rotateAroundPoint(new PrecisionPoint(p.x(), p.y()), origin, startAngle);
	}
	
	private Rectangle getOptimizedBounds() {
		float lineInset = Math.max(1.0f, getLineWidthFloat()) / 2.0f;
		int inset1 = (int) Math.floor(lineInset);
		int inset2 = (int) Math.ceil(lineInset);

		Rectangle r = Rectangle.SINGLETON.setBounds(getBounds());
		r.x += inset1;
		r.y += inset1;
		r.width -= inset1 + inset2;
		r.height -= inset1 + inset2;
		return r;
	}
}