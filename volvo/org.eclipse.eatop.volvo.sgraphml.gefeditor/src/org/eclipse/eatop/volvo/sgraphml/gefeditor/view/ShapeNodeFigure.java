package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.ColorUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

import eu.synligare.sgraphml.FillType;
import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.LineStyleType;

public class ShapeNodeFigure extends Figure {

	protected ConnectionAnchor connectionAnchor;

	//Figures
//	protected Label label; - moved to subfigures
	protected Shape shape; 

	Rectangle snLayout;


	//second fill color of shape
	Color secondFillColor = null;

	
	//build figure tree
	public ShapeNodeFigure(Shape s, boolean bLabel) {
		shape = s;

		this.setLayoutManager(new XYLayout());
		shape.setLayoutManager(new XYLayout());

		this.add(shape);  

		//         ShapeNodeFigure
		//               |
		//         GradientFigure       (=shape=ContentsPane to which editparts figure are added by GEF)
		//       |               \
		//     NodeLabelFigure1 NodeLabelFigure2
		//
		// This structure means that labels are drawn on top of the gradient.
		
	/*	//PortNodes never have a label
		if (bLabel){
			label = new Label();
			this.add(label);  //Then the shape label on top
*/
			//Debug - yellow label border
			//	label.setBorder(new LineBorder(Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW), 1, SWT.LINE_SOLID));

	//	}
	}

	//This is where the child editparts will put their graphs
	public IFigure getContentsPane() {
		return shape; 
	}
	
	public void setBorderStyle(LineStyleType borderStyle){
		int style = SWT.LINE_SOLID;
		switch (borderStyle.getType())
		{			
		case DASHED:
			style = SWT.LINE_DASH;
			break;
		case DASHED_DOTTED:
			style = SWT.LINE_DASHDOT;
			break;
		case DOTTED:
			style = SWT.LINE_DOT;
			break;
		case LINE:
			style = SWT.LINE_SOLID;
			break;
		}
		shape.setLineStyle(style);
		shape.setLineWidth((int)borderStyle.getWidth());
		String color = borderStyle.getColor();
		if (color == null){
			color = "#000000";
		}
		Color c = ColorUtil.decode(color);
		shape.setForegroundColor(c);
		
		
		

	}

	//rect - in absolute coordinates
	//
	//sets the layout of this figure in the parent
	public void setParentLayout(Rectangle rect) {

		//gives null if there is no parent, i.e. for root figure
		Figure contents = (Figure)getParent();

		if (contents != null && rect != null){
			Point pRel = ViewHelpers.absToRel(contents, rect.getLocation()); 
	
			//Calculate width of total portfigure, port + label 
			int w = rect.width;
			int h = rect.height;
	
			contents.setConstraint(this, new Rectangle(pRel.x, pRel.y, w, h));
		}
	}

	public void setShapeLayout(Rectangle rect) {
		setConstraint(shape, new Rectangle(0,0, rect.width, rect.height));
	}

	

	public void setGeometry(GeometryType g){

		//shape layout from model is in absolute coordinates.
		snLayout = new Rectangle((int)Math.round(g.getX()), (int)Math.round(g.getY()), 
				(int)Math.round(g.getWidth()), (int)Math.round(g.getHeight()));
	}

	public void setFill(FillType f){
		shape.setFill(true);
		
		String color = f.getColor();
		if (color == null){
			color = "#ffffff";
		}
		
		Color c = ColorUtil.decode(color);
		shape.setBackgroundColor(c);
		
		if (shape instanceof IGradient){
			String sc2 = f.getColor2();
			if ((sc2 == null) || (sc2.isEmpty())){
				((IGradient)shape).setColor2(null);
			}
			else{
				((IGradient)shape).setColor2(ColorUtil.decode(sc2));
			}
		}
	}

	
	
	
	
	//After all attributes set (font etc), set the layout that is dependant on the attributes (font etc)
	public void setLayouts(){
		setParentLayout(snLayout); 
		setShapeLayout(snLayout);
//		setLabelLayout(snLayout);

	}

	public ConnectionAnchor getConnectionAnchor() {
		return null;
	}

	
	public Rectangle getShapeRectangle()
	{
		return new Rectangle((Rectangle)getLayoutManager().getConstraint(shape));
	}


}
