package org.eclipse.eatop.volvo.sgraphml.gefeditor.view.anchor;

import java.security.acl.Owner;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.shape.Triangle;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

public class TriangleAnchor extends AbstractConnectionAnchor {

	/**
	 * Constructs a new TriangleAnchor.
	 */
	protected TriangleAnchor() {
	}

	/**
	 * Constructs a TriangleAnchor with the given <i>owner</i> figure.
	 */
	public TriangleAnchor(Triangle owner) {
		super(owner);
	}
	
	/**
	 * Returns a point on the triangle (defined by the owner's bounding box)
	 * where the connection should be anchored. The point is where a line from 
	 * the center of the box to the reference Point intersects the triangle.
	 * 
	 * @see org.eclipse.draw2d.ConnectionAnchor#getLocation(Point)
	 */
	@SuppressWarnings("deprecation")
	public Point getLocation(Point reference) {
		Rectangle r = Rectangle.SINGLETON;
		r.setBounds(getOwner().getBounds());
		r.translate(-1, -1);
		r.resize(1, 1);
		//take zoom into account. zoom 50% => x=100 in model => 50 absolute on screen
		getOwner().translateToAbsolute(r); 

		//Get the points from the triangle and transform them to absolute coords
		PointList pList  = ((Triangle)getOwner()).getTriangle();
		Point P0 = pList.getPoint(0);
		getOwner().translateToAbsolute(P0);
		
		Point P1 = pList.getPoint(1);
		getOwner().translateToAbsolute(P1);

		Point P2 = pList.getPoint(2);
		getOwner().translateToAbsolute(P2);

		//line: y=kx+m
		//k = (y2-y1) / (x2 - x1)
		//m = y1 - k*x1
		
		//Calculate the 3 lines in the triangle sides
		//Line 1: P0 - P1
		double k1;
		if ((P1.x - P0.x) == 0){
			k1 = 0;
		}
		else {
			k1 = (P1.y - P0.y) / (P1.x - P0.x);
		}
		double m1 = P0.y - k1*P0.x;

		
		//Line 2: P1 - P2
		double k2;
		if ((P2.x - P1.x) == 0){
			k2 = 0;
		}
		else {
			k2 = (P2.y - P1.y) / (P2.x - P1.x);
		}
		double m2 = P2.y - k2*P2.x;

		//Line 2: P0 - P2
		double k3;
		if ((P2.x - P0.x) == 0){
			k3 = 0;
		}
		else {
			k3 = (P2.y - P0.y) / (P2.x - P0.x);
		}
		double m3 = P2.y - k3*P2.x;
		
		float centerX = r.x + 0.5f * r.width;
		float centerY = r.y + 0.5f * r.height;
		
		//Let the line from center to reference have index A
		//Line A passes centerpoint (r,s) and referencepoint (p,q):
		//x = (p-r)*t + r
		//y = (q-s)*t + s
		
		//solving intersection between Line A and y=kx+m
		//t = (r*k - s + m) / ((q-s) - k *(p-r))
		//t=[0,1] interpolation, t<0 or t>1 extrapolation. We look for interpolation, with minimum t.
		
		//Intersection LineA and Line 1:
		double denom1 = (reference.y-centerY) - k1*(reference.x - centerX);
		double t1 = - 1;
		if (denom1 != 0)
		{
			t1 = (centerX*k1 - centerY + m1) / denom1;	
		}
		

		//Intersection LineA and Line 2:
		double denom2 = (reference.y-centerY) - k2*(reference.x - centerX);
		double t2 = -1;
		if (denom2 != 0)
		{
			t2 = (centerX*k2 - centerY+m2) / denom2;		
		}			
										
		//Intersection LineA and Line 3:
		double denom3 = (reference.y-centerY) - k3*(reference.x - centerX);
		double t3 = -1;
		if (denom3 != 0)
		{
			t3 = (centerX*k3 - centerY + m3) / denom3;
		}	
		
		//Check in which cases we have interpolation, i.e. [0,1]	
		boolean  bT1ok, bT2ok, bT3ok;
		bT1ok = t1>= 0 && t1 <=1;
		bT2ok = t2>= 0 && t2 <=1;
		bT3ok = t3>= 0 && t3 <=1;
	
		SortedSet<Double> set = new TreeSet<Double>();
		
		if (bT1ok) set.add(t1);
		if (bT2ok) set.add(t2);
		if (bT3ok) set.add(t3);

		
		if (set.isEmpty()){
			//strange, no intersection found, give up
			return new Point(centerX, centerY);
		}
		else{
			//Take the minimum interpolation, that's the right one
			double min = set.first();
			return new Point((reference.x - centerX)*min + centerX, 
		         (reference.y - centerY)*min + centerY);		
			}
	}
	

	/**
	 * Returns <code>true</code> if the other anchor is an TriangleAnchor with
	 * the same owner.
	 * 
	 * @param o
	 *            the other anchor
	 * @return <code>true</code> if equal
	 */
	public boolean equals(Object o) {
		if (o instanceof TriangleAnchor) {
			TriangleAnchor other = (TriangleAnchor) o;
			return other.getOwner() == getOwner();
		}
		return false;
	}

	/**
	 * The owning figure's hashcode is used since equality is approximately
	 * based on the owner.
	 * 
	 * @return the hash code.
	 */
	public int hashCode() {
		if (getOwner() != null)
			return getOwner().hashCode();
		else
			return super.hashCode();
	}


}
