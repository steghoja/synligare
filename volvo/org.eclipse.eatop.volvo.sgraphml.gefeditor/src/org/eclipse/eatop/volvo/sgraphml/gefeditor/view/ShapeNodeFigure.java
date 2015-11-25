package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;





import java.awt.LayoutManager;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Geometry;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.ColorUtil;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ResourceManager;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ScaledIconResource;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.handlers.WizardHandler.New;

import eu.synligare.sgraphml.FillType;
import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.HorizontalTextPositionType;
import eu.synligare.sgraphml.LineStyleType;
import eu.synligare.sgraphml.LineTypeType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PlacementType;
import eu.synligare.sgraphml.SgraphmlPackage;
import eu.synligare.sgraphml.ShapeNodeType;
import eu.synligare.sgraphml.ShapeTypeType;
import eu.synligare.sgraphml.VerticalTextPositionType;

public class ShapeNodeFigure extends Figure {

	protected ConnectionAnchor connectionAnchor;

	//Figures
	protected Label label;
	protected Shape shape; 

	Rectangle snLayout;

	//Placement of icon and label relative ShapeNode
	PlacementType placement;

	//Placement of label text relative icon
	HorizontalTextPositionType horizontalTextPosition;
	VerticalTextPositionType verticalTextPosition;

	//second fill color of shape
	Color secondFillColor = null;

	
	//build figure tree
	public ShapeNodeFigure(Shape s, boolean bLabel) {
		shape = s;

		this.setLayoutManager(new XYLayout());
		shape.setLayoutManager(new XYLayout());

		this.add(shape);  //The shape and its contents (ports) are drawn first,...

		//PortNodes never have a label
		if (bLabel){
			label = new Label();
			this.add(label);  //Then the shape label on top

			//Debug - yellow label border
			//	label.setBorder(new LineBorder(Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW), 1, SWT.LINE_SOLID));

		}
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
		Color c = ColorUtil.decode(borderStyle.getColor());
		shape.setForegroundColor(c);
		
		
		

	}

	//rect - in absolute coordinates
	//
	//sets the layout of this figure in the parent
	public void setParentLayout(Rectangle rect) {

		//gives null if there is no parent, i.e. for root figure
		Figure contents = (Figure)getParent();

		if (contents != null){
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

	/***
	 * 
	 * @param rect shapenode layout
	 */
	public void setLabelLayout(Rectangle rect) {

		Dimension labelSize = label.getPreferredSize();
		int x,y;

		switch (placement){
		case CENTER:
			//content-fit
			x = (rect.width - labelSize.width) / 2;
			y = (rect.height - labelSize.height) / 2;
			setConstraint(label, new Rectangle(x, y, labelSize.width, labelSize.height));
			break;
		case TOP:
			//make a content fit rectangle for this label so we get it right
			x = (rect.width - labelSize.width) / 2;
			y = 0;
			setConstraint(label, new Rectangle(x, y, labelSize.width, labelSize.height));
			break;

		default:
			setConstraint(label, new Rectangle(0,0, rect.width, rect.height));
		}
	}


	public void setGeometry(GeometryType g){

		//shape layout from model is in absolute coordinates.
		snLayout = new Rectangle((int)Math.round(g.getX()), (int)Math.round(g.getY()), 
				(int)Math.round(g.getWidth()), (int)Math.round(g.getHeight()));
	}

	public void setFill(FillType f){
		shape.setFill(true);
		Color c = ColorUtil.decode(f.getColor());
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

	
	
	
	public void setNodeLabel(NodeLabelType nodeLabel){

		//label text
		FeatureMap fm = nodeLabel.getMixed();
		for (FeatureMap.Entry entry : fm){
			EStructuralFeature feature = entry.getEStructuralFeature();
			if (feature.getName().equals("text")) {
				String sLabel = (String)entry.getValue();
				label.setText(sLabel);
				break;
			}
		}  

		//Icon image, if any
		String resourceID = nodeLabel.getIconData();
		if ((resourceID != null) && resourceID.length() > 0)
		{
			ScaledIconResource scaledIconRes = (ScaledIconResource)Utils.INSTANCE.getResourceManager().getSgraphmlResource(resourceID);
			Image swtImage = scaledIconRes.getImage();
			label.setIcon(swtImage);
		}


		//SWT uses points as unit for fontHeight. In sgraphml file we have pixels. points = pixels * 72 / DPI
		int fontHeightinPoints = nodeLabel.getFontSize() * 72 / Display.getCurrent().getDPI().y;
		String fontName = nodeLabel.getFontFamily();
		int fontStyle = SWT.NORMAL;
		Font f = FontManager.INSTANCE.getFont(fontName, fontHeightinPoints, fontStyle);
		label.setFont(f);

		//Text color
		Color textColor = ColorUtil.decode(nodeLabel.getTextColor());
		label.setForegroundColor(textColor);


		//IconTextGap
		label.setIconTextGap(nodeLabel.getIconTextGap());

		//Visible
		setVisible(nodeLabel.isVisible());


		placement = nodeLabel.getPlacement();
		horizontalTextPosition = nodeLabel.getHorizontalTextPosition();
		verticalTextPosition = nodeLabel.getVerticalTextPosition();


		//You should start with setTextPlacement = EAST, NORTH, SOUTH, WEST
		//Then align in the other dimension using SetTextAlignment

		//Check that valid choices have been made for the icon & text position.
		switch (placement){
		case CENTER:
			//3 cases supported

			label.setIconAlignment(PositionConstants.CENTER);
			label.setLabelAlignment(PositionConstants.CENTER);

			if ((horizontalTextPosition == HorizontalTextPositionType.RIGHT) &&
					(verticalTextPosition == VerticalTextPositionType.CENTER))
			{
				//label text to the right of the icon. icon & label centered (x,y) in the shapenode
				label.setTextPlacement(PositionConstants.EAST);		 
				label.setTextAlignment(PositionConstants.CENTER);    
			}
			else if ((horizontalTextPosition == HorizontalTextPositionType.CENTER) &&
					(verticalTextPosition == VerticalTextPositionType.BOTTOM)){
				//label text below the icon. icon & label centered (x,y) in the shapenode
				label.setTextPlacement(PositionConstants.SOUTH);		 
				label.setTextAlignment(PositionConstants.CENTER);    
			}
			else if ((horizontalTextPosition == HorizontalTextPositionType.CENTER) &&
					(verticalTextPosition == VerticalTextPositionType.TOP)){
				//label text above the icon. icon & label centered (x,y) in the shapenode
				label.setTextPlacement(PositionConstants.NORTH);		 
				label.setTextAlignment(PositionConstants.CENTER);    
			}
			else {
				System.out.println("Unsupported combination of horisontalTextPosition & verticalTextPosition for ShapeNode. Defaulting.");

				//default to first case above

				//label text to the right of the icon. icon & label centered (x,y) in the shapenode
				label.setTextPlacement(PositionConstants.EAST);		 
				label.setTextAlignment(PositionConstants.CENTER);    
			}
			break;

		case TOP: //icon to the top, label text above it
			label.setIconAlignment(PositionConstants.CENTER);	 //centered in the content-fit label rectangle
			label.setLabelAlignment(PositionConstants.TOP);

			//2 cases supported 

			//icon to the the top label to the left and text to the right
			if ((horizontalTextPosition == HorizontalTextPositionType.RIGHT) &&
					(verticalTextPosition == VerticalTextPositionType.CENTER)){

				label.setTextPlacement(PositionConstants.EAST);		 
				//				label.setTextAlignment(PositionConstants.CENTER);    
				label.setTextAlignment(PositionConstants.TOP);    
			}

			//icon to the top, label text above it
			else if ((horizontalTextPosition == HorizontalTextPositionType.CENTER) &&
					(verticalTextPosition == VerticalTextPositionType.TOP)){

				label.setTextPlacement(PositionConstants.NORTH);		 
				label.setTextAlignment(PositionConstants.CENTER);
			}
			else {
				System.out.println("Unsupported combination of horisontalTextPosition & verticalTextPosition for GroupNode. Defaulting.");
				//Default to first case
				label.setTextPlacement(PositionConstants.EAST);		 
				label.setTextAlignment(PositionConstants.CENTER);    

			}    
			break;

		case RIGHT: 
		case LEFT:
		case BOTTOM: 
			System.out.println("placement right, left and bottom not supported for ShapeNode");

			//default to first case above

			//label text to the right of the icon. icon & label centered (x,y) in the shapenode
			label.setIconAlignment(PositionConstants.CENTER);
			label.setLabelAlignment(PositionConstants.CENTER);
			label.setTextPlacement(PositionConstants.EAST);		 
			label.setTextAlignment(PositionConstants.CENTER);   
			break;
		default:
			break;
		}

	}

	//After all attributes set (font etc), set the layout that is dependant on the attributes (font etc)
	public void setLayouts(){
		setParentLayout(snLayout); 
		setShapeLayout(snLayout);
		setLabelLayout(snLayout);

	}

	public ConnectionAnchor getConnectionAnchor() {
		return null;
	}

	public Rectangle getLabelRectangle()
	{
		return new Rectangle((Rectangle)getLayoutManager().getConstraint(label));
	}

	public Rectangle getShapeRectangle()
	{
		return new Rectangle((Rectangle)getLayoutManager().getConstraint(shape));
	}


}
