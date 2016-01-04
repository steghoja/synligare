package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class ViewHelpers {

//father = getParent()	
	static public Point absToRel(IFigure father, Point pAbs){
		IFigure fig = father;
		Point point = new Point(pAbs);

		while (!(fig instanceof GraphMLTypeFigure)) {
			if (fig.getParent() == null){
				//father figure may just have been deleted
				return new Point(0,0);
			}
			Rectangle r = (Rectangle)fig.getParent().getLayoutManager().getConstraint(fig);
			Point dP = r.getLocation().negate();
			point.translate(dP);
			fig = fig.getParent();
		}

		return point;
	}
}
