package org.eclipse.eatop.volvo.sgraphml.gefeditor.view;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.ColorUtil;
import org.eclipse.eatop.volvo.sgraphml.gefeditor.model.resources.ResourceManager;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import eu.synligare.sgraphml.ArrowTypeType;
import eu.synligare.sgraphml.ArrowsType;
import eu.synligare.sgraphml.EdgeLabelType;
import eu.synligare.sgraphml.HorizontalTextPositionType;
import eu.synligare.sgraphml.LineStyleType;
import eu.synligare.sgraphml.NodeLabelType;
import eu.synligare.sgraphml.VerticalTextPositionType;

public class PolylineConnectionFigure extends PolylineConnection {

	Label label;
	
	
	public PolylineConnectionFigure(){
		setConnectionRouter(new BendpointConnectionRouter());;
	
		label = new Label();
		label.setOpaque(true);
		this.add(label, new MidpointLocator(this, 0));
	}
	
	public void setLineStyle(LineStyleType lineStyle){
		int style = SWT.LINE_SOLID;
		
		setLineWidth((int)lineStyle.getWidth());
		String color = lineStyle.getColor();
		if (color == null){
			color = "#000000";
		}
		Color c = ColorUtil.decode(color);
		setForegroundColor(c);
	
		switch (lineStyle.getType())
		{			
		
			case DASHED:
				//SWT.LINE_DASH not really visible in 100% zoom
				//style = SWT.LINE_DASH;
				setLineDash(new float[] { 4.0f, 4.0f });
				setLineStyle(SWT.LINE_CUSTOM);

				break;
			case DASHED_DOTTED:
				style = SWT.LINE_DASHDOT;
				setLineStyle(style);

				break;
			case DOTTED:
				style = SWT.LINE_DOT;
				setLineStyle(style);

				break;
			case LINE:
				style = SWT.LINE_SOLID;
				setLineStyle(style);

				break;
		}

	}

	protected PolygonDecoration filledDiamond(){
		PolygonDecoration decoration = new PolygonDecoration();
		PointList decorationPointList = new PointList();
		decorationPointList.addPoint(0,0);
		decorationPointList.addPoint(-2,2);
		decorationPointList.addPoint(-4,0);
		decorationPointList.addPoint(-2,-2);
		decoration.setTemplate(decorationPointList);
		return decoration;
	}
	
	
	public void setArrows(ArrowsType arrows)
	{
		ArrowTypeType sourceType = arrows.getSource();
	
		switch (sourceType.getValue()){
		
			case ArrowTypeType.NONE_VALUE:
				break;
	
			case ArrowTypeType.PLAIN_VALUE:
				PolylineDecoration d = new PolylineDecoration();
				d.setFill(true);
				setSourceDecoration(d);
				break;
	
			case ArrowTypeType.DIAMOND_VALUE:
				PolygonDecoration diamond = filledDiamond();
				setSourceDecoration(diamond);
				break;
		}

		ArrowTypeType targetType = arrows.getTarget();
		switch (targetType.getValue()){
			
			case ArrowTypeType.NONE_VALUE:
				break;
	
			case ArrowTypeType.PLAIN_VALUE:
				PolylineDecoration d = new PolylineDecoration();
				d.setFill(true);
				setTargetDecoration(d);
				break;
	
			case ArrowTypeType.DIAMOND_VALUE:
				PolygonDecoration diamond = filledDiamond();
				setTargetDecoration(diamond);
				break;
	
		}
	}

	public void setEdgeLabel(EdgeLabelType edgeLabel){
		
		if (edgeLabel == null) return;
		
		//label text
		FeatureMap fm = edgeLabel.getMixed();
		for (FeatureMap.Entry entry : fm){
			EStructuralFeature feature = entry.getEStructuralFeature();
			if (feature.getName().equals("text")) {
				String sLabel = (String)entry.getValue();
				label.setText(sLabel);
				break;
			}
		}  

		//Font
		//SWT uses points as unit for fontHeight. In sgraphml file we have pixels. points = pixels * 72 / DPI
		int fontHeightinPoints = edgeLabel.getFontSize() * 72 / Display.getCurrent().getDPI().y;
		String fontName = edgeLabel.getFontFamily();
		int fontStyle = SWT.NORMAL;
		Font f = FontManager.INSTANCE.getFont(fontName, fontHeightinPoints, fontStyle);
		setFont(f);
		
		//Text color
		Color textColor = ColorUtil.decode(edgeLabel.getTextColor());
		setForegroundColor(textColor);

		//Visible
		label.setVisible(edgeLabel.isVisible());
		
	}
	
}
