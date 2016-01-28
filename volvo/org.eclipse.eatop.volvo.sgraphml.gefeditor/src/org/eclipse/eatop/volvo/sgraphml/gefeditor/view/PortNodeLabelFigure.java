package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;


import javax.tools.JavaFileManager.Location;

import org.eclipse.core.internal.registry.ThirdLevelConfigurationElementHandle;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.ColorUtil;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.Utils;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ResourceManager;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ScaledIconResource;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.view.GroupNodeFigure;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import eu.synligare.sgraphml.GeometryType;
import eu.synligare.sgraphml.HorizontalTextPositionType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.PlacementType;
import eu.synligare.sgraphml.VerticalTextPositionType;

public class PortNodeLabelFigure extends Label {

	Rectangle portLayout; //absolute coordinates

	PlacementType placement;
	HorizontalTextPositionType horizontalTextPosition;
	VerticalTextPositionType verticalTextPosition;
	
	
	
	
	public PortNodeLabelFigure(){
		super();
		
		//Debug - red dotted border
	//	setBorder(new LineBorder(Display.getCurrent().getSystemColor(SWT.COLOR_RED), 1, SWT.LINE_DOT));
}
	
	public void setPortGeometry(GeometryType g){
		//shape layout from model is in absolute coordinates.
		 portLayout = new Rectangle((int)Math.round(g.getX()), (int)Math.round(g.getY()), 
				(int)Math.round(g.getWidth()), (int)Math.round(g.getHeight()));
	}
	
	public void setNodeLabel(NodeLabelType nodeLabel){
		//label text
		FeatureMap fm = nodeLabel.getMixed();
		for (FeatureMap.Entry entry : fm){
			EStructuralFeature feature = entry.getEStructuralFeature();
			if (feature.getName().equals("text")) {
				String sLabel = (String)entry.getValue();
				setText(sLabel);
				break;
			}
		}  

		//Icon image
		String resourceID = nodeLabel.getIconData();
		if ((resourceID != null) && resourceID.length() > 0)
		{
			ScaledIconResource scaledIconRes = (ScaledIconResource)Utils.INSTANCE.getResourceManager().getSgraphmlResource(resourceID);
			
			if (scaledIconRes != null){
				Image swtImage = scaledIconRes.getImage();
				setIcon(swtImage);
			}
			else{
				Utils.showErrorMessage("Internal Error: SetNodeLabel - Failed to get Resource for id = " + resourceID);
			}
		}
		else
		{
			System.out.print ("No icondata defined");
		}
		
		//Font
		//SWT uses points as unit for fontHeight. In sgraphml file we have pixels. points = pixels * 72 / DPI
		int fontHeightinPoints = nodeLabel.getFontSize() * 72 / Display.getCurrent().getDPI().y;
		
		
		String fontName = nodeLabel.getFontFamily();
		if (fontName == null){
			fontName = "Dialog";
		}
		
		int fontStyle = SWT.NORMAL;
		Font f = FontManager.INSTANCE.getFont(fontName, fontHeightinPoints, fontStyle);
		setFont(f);
		
		//Text color
		String color = nodeLabel.getTextColor();
		if (color == null){
			color = "#000000";
		}
		Color textColor = ColorUtil.decode(color);
		setForegroundColor(textColor);
		
		//IconTextGap
		setIconTextGap(nodeLabel.getIconTextGap());
			
		//Visible
		setVisible(nodeLabel.isVisible());
		
		placement = nodeLabel.getPlacement();
		horizontalTextPosition = nodeLabel.getHorizontalTextPosition();
		verticalTextPosition = nodeLabel.getVerticalTextPosition();
		
		
		//You should start with setTextPlacement = EAST, NORTH, SOUTH, WEST
		//Then align in the other dimension usint SetTextAlignment
		
		//Check that valid choices have been made for the icon & text position.
		switch (placement){
		case CENTER:
			System.out.println("Placement center not supported for port labels.");
			break;
		case RIGHT: //icon to the right
			if (horizontalTextPosition != HorizontalTextPositionType.LEFT)
			{
				System.out.println("horizontalTextPosition LEFT required for placement right");
			}
			else if (verticalTextPosition != VerticalTextPositionType.CENTER){
				System.out.println("verticalTextPosition center required for placement right");
			}
			setLabelAlignment(PositionConstants.RIGHT);
			setIconAlignment(PositionConstants.RIGHT);
			setTextPlacement(PositionConstants.WEST);		//use TextPlacement=Horizontal dimension
			setTextAlignment(PositionConstants.CENTER);         				//and TextAlignment=Vertical dimension
			break;
		case LEFT:
			if (horizontalTextPosition != HorizontalTextPositionType.RIGHT)
			{
				System.out.println("horizontalTextPosition RIGHT required for placement left");
			}
			else if (verticalTextPosition != VerticalTextPositionType.CENTER){
				System.out.println("verticalTextPosition center required for placement left");
			}
			setLabelAlignment(PositionConstants.LEFT);
			setIconAlignment(PositionConstants.LEFT);
			setTextPlacement(PositionConstants.EAST);		  
			setTextAlignment(PositionConstants.CENTER);         
			break;
		case TOP: //icon to the top
			if (horizontalTextPosition != HorizontalTextPositionType.CENTER)
			{
				System.out.println("horizontalTextPosition center required for placement top");
			}
			else if (verticalTextPosition != VerticalTextPositionType.BOTTOM){
				System.out.println("verticalTextPosition bottom required for placement top");
			}
			setLabelAlignment(PositionConstants.TOP);
			setIconAlignment(PositionConstants.TOP);
			setTextPlacement(PositionConstants.SOUTH);		 
			setTextAlignment(PositionConstants.CENTER);         

			
			break;
		case BOTTOM: //icon to the bottom
			if (horizontalTextPosition != HorizontalTextPositionType.CENTER)
			{
				System.out.println("horizontalTextPosition center required for placement bottom");
			}
			else if (verticalTextPosition != VerticalTextPositionType.TOP){
				System.out.println("verticalTextPosition top required for placement top");
			}
			setLabelAlignment(PositionConstants.BOTTOM);
			setIconAlignment(PositionConstants.BOTTOM);
			setTextPlacement(PositionConstants.NORTH); 
			setTextAlignment(PositionConstants.CENTER);   

			break;
		default:
			break;
		}
		
	}
	
	public void setLayout(){
		
		//The label only needs to set the layout in the parent
		
		Rectangle labelRect = new Rectangle(); //coordinates relative to groupNode

		//Get location of GroupNode in absolute coords
		Figure contents = (Figure)getParent();
		
		Point portRelLoc = ViewHelpers.absToRel(contents, portLayout.getLocation());
		Rectangle portRelative = new Rectangle(portRelLoc, portLayout.getSize());
		
		Dimension labelSize = getPreferredSize();
		
		switch (placement){ //icon placement
		case RIGHT: //icon to the right
			//label text to the left
			labelRect.x = portRelative.x + portRelative.width - labelSize.width;
			labelRect.y = portRelative.y + portRelative.height/2 - labelSize.height/2;
			labelRect.setSize(labelSize);
			
			break;

		case LEFT: //icon to the left
			//label to the right
			labelRect.x = portRelative.x;
			labelRect.y = portRelative.y + portRelative.height/2 - labelSize.height/2;
			labelRect.setSize(labelSize);

			break;

			
		case TOP:
			//icon to the top
			labelRect.x = portRelative.x + portRelative.width/2  - labelSize.width/2;
			labelRect.y = portRelative.y;
			labelRect.setSize(labelSize);

			break;

		case BOTTOM:
			//icon to the bottom
			labelRect.x = portRelative.x + portRelative.width/2  - labelSize.width/2;
			labelRect.y = portRelative.y + portRelative.height - labelSize.height;
			labelRect.setSize(labelSize);

			break;

		default:
			//error
			break;
		
		}
		
		getParent().setConstraint(this, labelRect); 
	}
	
	/***
	 * 
	 * @param portLocation - the top-left corner of the corresponding port relative to the groupnode
	 * @return the rectangle of the label for the port, in the port coordinate system
	 */
	public Rectangle getLabelRectangle(org.eclipse.draw2d.geometry.Point portLocation){
		Rectangle rLabel = new Rectangle((Rectangle)getParent().getLayoutManager().getConstraint(this));
		rLabel.translate(portLocation.negate());
		return rLabel; 
	}
		 
}
