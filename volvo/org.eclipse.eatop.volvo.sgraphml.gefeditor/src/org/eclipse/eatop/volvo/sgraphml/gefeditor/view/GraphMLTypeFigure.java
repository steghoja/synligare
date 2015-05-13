package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;

public class GraphMLTypeFigure extends FreeformLayer {

	public GraphMLTypeFigure(){

		this.setLayoutManager(new FreeformLayout());
	    this.setBorder(new MarginBorder(1));
	}
}
