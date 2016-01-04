package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;


import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.ColorUtil;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.dnd.EAXMLprocessor;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ScaledIconResource;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import eu.synligare.sgraphml.HorizontalTextPositionType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PlacementType;
import eu.synligare.sgraphml.VerticalTextPositionType;

public class NodeLabelFigure extends Label {


	public NodeLabelFigure(){
		super();
		
		//Debug - red dotted border
	//	setBorder(new LineBorder(Display.getCurrent().getSystemColor(SWT.COLOR_RED), 1, SWT.LINE_DOT));
	}
	
	//Placement of icon and label relative ShapeNode
	PlacementType placement;

	//Placement of label text relative icon
	HorizontalTextPositionType horizontalTextPosition;
	VerticalTextPositionType verticalTextPosition;

	//label top_left corner relative to gradient figure rectangle
	double topleft_x;
	double topleft_y;
	
	/*
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
	*/
	/***
	 * 
	 * @param rect shapenode layout
	 */
	
	//Used to be Rectangle rect = snLayout

	
	//Calculate a content-based rectangle and set as constraint in the layoutmanager of the father figure i.e. shapnode
	public void setLayout() {

		
		//Set layout of NodeLabel figure to full extent of ShapeNodeFigure

		//ShapNodeFigure
		//    |
		//GradientFigure
		//    |
		//NodeLabelFigure
		
		Figure gradientFig = (Figure)getParent(); 
		Figure shapeNodeFig =  (Figure)gradientFig.getParent();
		
		if (shapeNodeFig != null){

			Rectangle rect = (Rectangle)shapeNodeFig.getLayoutManager().getConstraint(gradientFig);
			
			Dimension labelSize = getPreferredSize();
			int x,y;
	
			switch (placement){
			case CENTER:
				//content-fit
				x = (rect.width - labelSize.width) / 2;
				y = (rect.height - labelSize.height) / 2;
				gradientFig.setConstraint(this, new Rectangle(x, y, labelSize.width, labelSize.height));
				break;
			case TOP:
				//make a content fit rectangle for this label so we get it right
				x = (rect.width - labelSize.width) / 2;
				y = 0;
				gradientFig.setConstraint(this, new Rectangle(x, y, labelSize.width, labelSize.height));
				break;

			case FREE:
				//make a content fit rectangle for this label so we get it right
				x = (int)Math.round(topleft_x);
				y = (int)Math.round(topleft_y);

				gradientFig.setConstraint(this, new Rectangle(x, y, labelSize.width, labelSize.height));
				break;

				
			default:
				gradientFig.setConstraint(this, new Rectangle(0,0, rect.width, rect.height));
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

				if (sLabel.contains("@")){
					//Replace attribute reference with value
					int index = sLabel.indexOf('@');
					String dotPath = sLabel.substring(index + 1);
					String header = sLabel.substring(0, index);
					String value = EAXMLprocessor.getAttributeValue(dotPath);
					sLabel = header + value;
				}
				
				setText(sLabel);
				break;
			}
		}  

		//Icon image, if any
		String resourceID = nodeLabel.getIconData();
		if ((resourceID != null) && resourceID.length() > 0)
		{
			ScaledIconResource scaledIconRes = (ScaledIconResource)Utils.INSTANCE.getResourceManager().getSgraphmlResource(resourceID);
			if (scaledIconRes != null)
			{
				Image swtImage = scaledIconRes.getImage();
				setIcon(swtImage);
			}
		}


		//SWT uses points as unit for fontHeight. In sgraphml file we have pixels. points = pixels * 72 / DPI
		int fontHeightinPoints = nodeLabel.getFontSize() * 72 / Display.getCurrent().getDPI().y;
		String fontName = nodeLabel.getFontFamily();
		int fontStyle = SWT.NORMAL;
		Font f = FontManager.INSTANCE.getFont(fontName, fontHeightinPoints, fontStyle);
		setFont(f);

		//Text color
		Color textColor = ColorUtil.decode(nodeLabel.getTextColor());
		setForegroundColor(textColor);


		//IconTextGap
		setIconTextGap(nodeLabel.getIconTextGap());

		//Visible
		setVisible(nodeLabel.isVisible());


		placement = nodeLabel.getPlacement();
		horizontalTextPosition = nodeLabel.getHorizontalTextPosition();
		verticalTextPosition = nodeLabel.getVerticalTextPosition();
		topleft_x = nodeLabel.getX();
		topleft_y = nodeLabel.getY();
		
		//You should start with setTextPlacement = EAST, NORTH, SOUTH, WEST
		//Then align in the other dimension using SetTextAlignment, orthogonal values ok

		//Check that valid choices have been made for the icon & text position.
		switch (placement){
		case CENTER:
			//3 cases supported

			setIconAlignment(PositionConstants.CENTER);
			setLabelAlignment(PositionConstants.CENTER);

			if ((horizontalTextPosition == HorizontalTextPositionType.RIGHT) &&
					(verticalTextPosition == VerticalTextPositionType.CENTER))
			{
				//label text to the right of the icon. icon & label centered (x,y) in the shapenode
				setTextPlacement(PositionConstants.EAST);		 
				setTextAlignment(PositionConstants.CENTER);    
			}
			else if ((horizontalTextPosition == HorizontalTextPositionType.CENTER) &&
					(verticalTextPosition == VerticalTextPositionType.BOTTOM)){
				//label text below the icon. icon & label centered (x,y) in the shapenode
				setTextPlacement(PositionConstants.SOUTH);		 
				setTextAlignment(PositionConstants.CENTER);    
			}
			else if ((horizontalTextPosition == HorizontalTextPositionType.CENTER) &&
					(verticalTextPosition == VerticalTextPositionType.TOP)){
				//label text above the icon. icon & label centered (x,y) in the shapenode
				setTextPlacement(PositionConstants.NORTH);		 
				setTextAlignment(PositionConstants.CENTER);    
			}
			else {
				System.out.println("Unsupported combination of horisontalTextPosition & verticalTextPosition for ShapeNode. Defaulting.");

				//default to first case above

				//label text to the right of the icon. icon & label centered (x,y) in the shapenode
				setTextPlacement(PositionConstants.EAST);		 
				setTextAlignment(PositionConstants.CENTER);    
			}
			break;

		case TOP: //icon to the top, label text above it
			setIconAlignment(PositionConstants.CENTER);	 //centered in the content-fit label rectangle
			setLabelAlignment(PositionConstants.TOP);

			//2 cases supported 

			//icon to the the top label to the left and text to the right
			if ((horizontalTextPosition == HorizontalTextPositionType.RIGHT) &&
					(verticalTextPosition == VerticalTextPositionType.CENTER)){

				setTextPlacement(PositionConstants.EAST);		 
				//				label.setTextAlignment(PositionConstants.CENTER);    
				setTextAlignment(PositionConstants.TOP);    
			}

			//icon to the top, label text above it
			else if ((horizontalTextPosition == HorizontalTextPositionType.CENTER) &&
					(verticalTextPosition == VerticalTextPositionType.TOP)){

				setTextPlacement(PositionConstants.NORTH);		 
				setTextAlignment(PositionConstants.CENTER);
			}
			else {
				System.out.println("Unsupported combination of horisontalTextPosition & verticalTextPosition for GroupNode. Defaulting.");
				//Default to first case
				setTextPlacement(PositionConstants.EAST);		 
				setTextAlignment(PositionConstants.CENTER);    

			}    
			break;

		case RIGHT: 
		case LEFT:
		case BOTTOM: 
			System.out.println("placement right, left and bottom not supported for ShapeNode");

			//default to first case above

			//label text to the right of the icon. icon & label centered (x,y) in the shapenode
			setIconAlignment(PositionConstants.CENTER);
			setLabelAlignment(PositionConstants.CENTER);
			setTextPlacement(PositionConstants.EAST);		 
			setTextAlignment(PositionConstants.CENTER);   
			break;
			
			
		case FREE:	
			if (horizontalTextPosition == HorizontalTextPositionType.CENTER){
				setLabelAlignment(PositionConstants.CENTER);
				setTextPlacement(PositionConstants.CENTER);		

				switch (verticalTextPosition){
				case BOTTOM:			
					setTextAlignment(PositionConstants.SOUTH);   
					setIconAlignment(PositionConstants.NORTH);
					break;
				case CENTER:
					setTextAlignment(PositionConstants.CENTER);   
					setIconAlignment(PositionConstants.CENTER);
					break;
				case TOP:
					setTextAlignment(PositionConstants.NORTH);   
					setIconAlignment(PositionConstants.SOUTH);
					break;
				}
			}
			else if (horizontalTextPosition == HorizontalTextPositionType.LEFT){
				setIconAlignment(PositionConstants.RIGHT);
				setLabelAlignment(PositionConstants.CENTER);
				setTextPlacement(PositionConstants.WEST);		
				switch (verticalTextPosition){
				case BOTTOM:			
					setTextAlignment(PositionConstants.SOUTH);   
					break;
				case CENTER:
					setTextAlignment(PositionConstants.CENTER);   
					break;
				case TOP:
					setTextAlignment(PositionConstants.NORTH);   
					break;
				}
				
			}
			else {//horizontalTextPosition == HorizontalTextPositionType.RIGHT
				setIconAlignment(PositionConstants.LEFT);
				setLabelAlignment(PositionConstants.CENTER);
				setTextPlacement(PositionConstants.EAST);		
				switch (verticalTextPosition){
				case BOTTOM:			
					setTextAlignment(PositionConstants.SOUTH);   
					break;
				case CENTER:
					setTextAlignment(PositionConstants.CENTER);   
					break;
				case TOP:
					setTextAlignment(PositionConstants.NORTH);   
					break;
				}

			}
		default:
			break;
		}
	}
	
	//När jag flyttar 8 pixels ner från Centerläget, 
	//hamnar vi 8 pixels ner från toppen...
	//dvs x,y var förut frå¨n mitten....
	
	
	
	public Rectangle getLabelRectangle()
		{
			return new Rectangle((Rectangle)getParent().getLayoutManager().getConstraint(this));
		}
	}

