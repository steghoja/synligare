package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.ColorUtil;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.GroupNodeFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.draw2d.XYLayout;

import eu.synligare.sgraphml.FillType;
import eu.synligare.sgraphml.NodeLabelType;


public class PortNodeFigure extends RectangleShapeNodeFigure {

	public PortNodeFigure(){
		super(false);
		
		//Debug - green dotted border
	//	setBorder(new LineBorder(Display.getCurrent().getSystemColor(SWT.COLOR_GREEN), 1, SWT.LINE_DOT));
	
}
	
	public void setFill(FillType f, boolean useFill){
		
		shape.setFill(useFill);

		if (useFill){
			//fill the port with a color, no label image is used so we won't hide it
			String color = f.getColor();

			if (color == null){
				color = "#FFFFFF";
			}
			Color c = ColorUtil.decode(color);
			//skip gradients for ports
			shape.setBackgroundColor(c);
		}			
	}

	
	@Override				
	public void setShapeLayout(Rectangle port) {
		Rectangle rPort  = new Rectangle(port);
		
		setConstraint(shape, new Rectangle(0,0,port.width, port.height));
	}

	/* TODO FIX THIS LATER
	
	@Override			
	//rect = the rectangle of the groupnode label rectangel, useless
	public void setLabelLayout(Rectangle rect) {
	//The label is in a separate editpart so do nothing
	}
	
	@Override
	public void setNodeLabel(NodeLabelType nodeLabel){
		//do nothing
	}
*/
}

