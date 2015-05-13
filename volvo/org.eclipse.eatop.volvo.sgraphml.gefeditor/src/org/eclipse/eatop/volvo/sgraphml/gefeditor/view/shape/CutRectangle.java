package org.eclipse.eatop.volvo.sgraphml.gefeditor.view.shape;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;


public class CutRectangle extends Shape {
	
	  public PointList getCutRectanglePoints() {
		  if (cutRectanglePoints.size() == 0){
			  Rectangle r = getBounds().getCopy();
			  calculateRectanglePoints(r);			  
		  }
		  return cutRectanglePoints;
	}

	protected Dimension corner = new Dimension(8, 8);
	  
	  //                                           
	  //                  7 ------------------------- 0
	  //                   /                         \ 
	  //                6 /                           \  1
	  //                  |                           |
	  //                  |                           |
	  //                  |                           |
	  //                  |                           |
	  //                  |                           |
	  //                  |                           |
	  //                5 \                           /  2
	  //                   \                         /
	  //                  4 -------------------------  3
	  //                     
	  
	  protected PointList cutRectanglePoints = new PointList();
	   
	@Override 
	  public void primTranslate(int dx, int dy) {
	    super.primTranslate(dx, dy);
	    cutRectanglePoints.translate(dx, dy);
	  } 
	   
	  @Override 
	  protected void outlineShape(Graphics graphics) {
	   //make sure the line width fits in the bounds of the figure
		float lineInset = Math.max(1.0f, getLineWidthFloat()) / 2.0f;
		int inset1 = (int) Math.floor(lineInset);
		int inset2 = (int) Math.ceil(lineInset);

		Rectangle r = Rectangle.SINGLETON.setBounds(getBounds());
		r.x += inset1;
		r.y += inset1;
		r.width -= inset1 + inset2;
		r.height -= inset1 + inset2;

		calculateRectanglePoints(r);
		graphics.drawPolygon((getCutRectanglePoints()).toIntArray());
	  
	  }
	  
	  
	  @Override 
	  protected void fillShape(Graphics graphics) {
		  Rectangle r = getBounds();
		  calculateRectanglePoints(r);
		  graphics.fillPolygon(getCutRectanglePoints());
	  }


	  public void calculateRectanglePoints(Rectangle r){
		  
		  	Point P0 = new Point(r.x + r.width - corner.width, r.y);
		    Point P1 = new Point(r.x + r.width, r.y + corner.height);
		    Point P2 = new Point(r.x + r.width, r.y + r.height - corner.height);
		    Point P3 = new Point(r.x + r.width - corner.width, r.y + r.height);
		    Point P4 = new Point(r.x + corner.width, r.y + r.height);
		    Point P5 = new Point(r.x, r.y + r.height - corner.height);
		    Point P6 = new Point(r.x, r.y + corner.height);
		    Point P7 = new Point(r.x + corner.width, r.y);

		    cutRectanglePoints.removeAllPoints();
		    cutRectanglePoints.addPoint(P0);
		    cutRectanglePoints.addPoint(P1);
		    cutRectanglePoints.addPoint(P2);
		    cutRectanglePoints.addPoint(P3);
		    cutRectanglePoints.addPoint(P4);
		    cutRectanglePoints.addPoint(P5);
		    cutRectanglePoints.addPoint(P6);
		    cutRectanglePoints.addPoint(P7);
	  }
	  
	  
	  
	  
	  /**
	   * Validates the figure, drawing a cutcorner rectangle filling the 
	   * figure's bounds.
	   */
	  /*
	  @Override
	  public void validate() {
		    super.validate();
		    Rectangle r = getBounds().getCopy();
		    r.shrink(getInsets());
		    r.resize(-1, -1);
		    
		    Point P0 = new Point(r.x + r.width - corner.width, r.y);
		    Point P1 = new Point(r.x + r.width, r.y + corner.height);
		    Point P2 = new Point(r.x + r.width, r.y + r.height - corner.height);
		    Point P3 = new Point(r.x + r.width - corner.width, r.y + r.height);
		    Point P4 = new Point(r.x + corner.width, r.y + r.height);
		    Point P5 = new Point(r.x, r.y + r.height - corner.height);
		    Point P6 = new Point(r.x, r.y + corner.height);
		    Point P7 = new Point(r.x + corner.width, r.y);

		    cutRectanglePoints.removeAllPoints();
		    cutRectanglePoints.addPoint(P0);
		    cutRectanglePoints.addPoint(P1);
		    cutRectanglePoints.addPoint(P2);
		    cutRectanglePoints.addPoint(P3);
		    cutRectanglePoints.addPoint(P4);
		    cutRectanglePoints.addPoint(P5);
		    cutRectanglePoints.addPoint(P6);
		    cutRectanglePoints.addPoint(P7);
		    
		}
	  */
		/**
		 * Sets the dimensions of each corner. 
		 * 
		 * @param d
		 *            the dimensions of the corner
		 */
		public void setCornerDimensions(Dimension d) {
			corner.width = d.width;
			corner.height = d.height;
		}

		/**
		 * Returns the dimensions used for each corner.
		 */
		public Dimension getCornerDimensions() {
			return corner.getCopy();
		}
	}

