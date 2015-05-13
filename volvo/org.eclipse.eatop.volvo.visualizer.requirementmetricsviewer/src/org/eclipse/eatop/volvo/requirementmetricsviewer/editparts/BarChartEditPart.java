package org.eclipse.eatop.volvo.requirementmetricsviewer.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.BarChartModel;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.ChartLabelModel;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.BarChartModel.BarModel;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public class BarChartEditPart extends AbstractGraphicalEditPart {
	
	private Rectangle chartBounds;
	private int nrOfUnits = 0;
	
	protected IFigure createFigure() {
		Figure f = new FreeformLayer();
		f.setLayoutManager(new FreeformLayout());
 
		f.setBorder(new MarginBorder(1));
		// Create a layout for the graphical screen
		
		XYLayout xyLayout = new XYLayout();
		f.setLayoutManager(xyLayout);
				
		f.setOpaque(true);
		
		// Add chart axis
		
		ArrayList<BarModel> bars = ((BarChartModel) getModel()).getBars();
		int nrOfBars = bars.size();
		nrOfUnits = ((BarChartModel) getModel()).getNrOfUnits();
		
		Polyline axis = new Polyline();
		int posX = 20;
		int posY = 20;
		int height = 170;
		int width = 200;
		axis.addPoint(new Point(posX,posY));
		axis.addPoint(new Point(posX,posY + height));
		axis.addPoint(new Point(Math.max(posX + width, posX + 50 * nrOfBars), posY + height));
		
		chartBounds = new Rectangle(posX, posY, width, height);

		f.add(axis);
		
		Polyline axisTip = new Polyline();
		axisTip.addPoint(new Point(posX - 5, posY + 5));
		axisTip.addPoint(new Point(posX, posY));
		axisTip.addPoint(new Point(posX + 5, posY + 5));
		
		f.add(axisTip);
		
		// add grading on axis
		int grading = Math.min(nrOfUnits, (int)Math.round((double)height/10.0)); // the number of grading "units"
		int diff = (int)Math.round((double)height/(double)grading);
		for (int i = 1; i < grading; i++) {
			Polyline line = new Polyline();
			posY += diff;
			line.addPoint(new Point(posX - 5, posY));
			line.addPoint(new Point(posX + 5, posY));
			f.add(line);
			
			Label label = new Label(Integer.toString(nrOfUnits - (i * (nrOfUnits/grading))));
			label.setBounds(new Rectangle(posX - 17, posY - 5, 15, 15));
			f.add(label);
		}
		
		char ch = 'a';
		posX += 30;
		posY += diff + 5;
		int labelY = posY + 20;
		for (int i = 0; i < nrOfBars; i++) {
			Label barChar = new Label(Character.toString(ch));
			barChar.setBounds(new Rectangle(posX + (50*i), posY, 15, 15));
			barChar.setLabelAlignment(PositionConstants.LEFT);
			f.add(barChar);
			
			Label barLabel = new Label(Character.toString(ch) + ". " + bars.get(i).getBarLabel());
			barLabel.setBounds(new Rectangle(posX, labelY += (20 * i), 200, 15));
			barLabel.setLabelAlignment(PositionConstants.LEFT);
			f.add(barLabel);
			
			ch++;
		}
		
		return f;
	}
 
	protected void createEditPolicies() {
 
	}
	
	protected List<Object> getModelChildren() {
		ArrayList<BarModel> barModels = ((BarChartModel) getModel()).getBars();
		ArrayList<ChartLabelModel> labels = ((BarChartModel) getModel()).getLabels();
		
		ArrayList<Object> children = new ArrayList<Object>();
		children.addAll(barModels);

		if (labels != null) children.addAll(labels);

		return children;
	}

	public Rectangle getChartBounds() {
		return chartBounds;
	}
	
	public int getNrOfUnits() {
		return nrOfUnits;
	}
}
