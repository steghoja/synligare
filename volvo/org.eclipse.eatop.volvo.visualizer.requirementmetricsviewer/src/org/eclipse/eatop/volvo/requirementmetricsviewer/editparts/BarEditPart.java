package org.eclipse.eatop.volvo.requirementmetricsviewer.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.BarChartModel.BarModel;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;


public class BarEditPart extends AbstractGraphicalEditPart {
			
	@Override
	protected IFigure createFigure() {
		
		BarModel model = (BarModel)getModel();
		IFigure bar = new RectangleFigure();

		bar.setBackgroundColor(new Color(null,0,0,0));
		
		for (int i = 0; i < model.getColors().size(); i++) {
			RectangleFigure part = new RectangleFigure();
			part.setBackgroundColor(model.getColors().get(i));

			bar.add(part);
		}
		
		return bar;
	}

	@Override
	protected void createEditPolicies() {
		
	}

	protected void refreshVisuals() {
		// This is where the actual drawing is done,
		
		BarModel model = (BarModel)getModel();
		IFigure bar = getFigure();		
		
		((RectangleFigure)bar).setOutline(false);
		((RectangleFigure)bar).setAntialias(SWT.ON);
		
		int nrOfUnits = 0;
		for (int i = 0; i < model.getQuantities().size(); i++) {
			nrOfUnits += model.getQuantities().get(i);
		}
		
		// Calculate bounds
		int nrOfUnitsOnChart = ((BarChartEditPart)getParent()).getNrOfUnits();
		List<Object> siblings = ((BarChartEditPart)getParent()).getModelChildren();

		int index = 0;
		for (Object o : siblings) {
			if (o instanceof BarModel) {
				if (((BarModel)o).equals(getModel())) { // if it is this model
					break;
				}
				index++;
			}
		}
		
		Rectangle parentBounds = ((BarChartEditPart)getParent()).getChartBounds();

		int barWidth = 30;
		int barHeight = (int)Math.round(((double)nrOfUnits/(double)nrOfUnitsOnChart) * 
				parentBounds.height());

		int barX = parentBounds.x() + 20 + index * (barWidth + 20);
		int barY = parentBounds.y() + (parentBounds.height() - barHeight);
		
		bar.setBounds(new Rectangle(barX, barY, barWidth, barHeight)); 
		int y = bar.getBounds().y();
		ArrayList<Integer> quantities = model.getQuantities();
		
		for (int i = 0; i < quantities.size(); i++) {
			RectangleFigure part = (RectangleFigure)bar.getChildren().get(i);
			Rectangle partBounds = bar.getBounds();

			int h = (int)Math.round(partBounds.height() * 
					((double)quantities.get(i)/(double)nrOfUnits));
	
			part.setOutline(false);
			part.setBounds(optimizeBounds(new Rectangle(barX, y, barWidth, h)));

			y += part.getBounds().height();
		}

	}

	private Rectangle optimizeBounds(Rectangle bounds) {
		int lineWidth = ((RectangleFigure)getFigure()).getLineWidth();
		
		return new Rectangle(bounds.x() + lineWidth, bounds.y() + lineWidth, 
				bounds.width() - (2*lineWidth), bounds.height());
	}
	
	// This is not used at the moment, but I'm leaving it here in case it gets useful
	private Rectangle optimizeBounds(Rectangle bounds, boolean middle) {
		int lineWidth = ((RectangleFigure)getFigure()).getLineWidth();
		
		return new Rectangle(bounds.x() + lineWidth, bounds.y() + lineWidth, 
				bounds.width() - (2*lineWidth), bounds.height() - (middle ? 2*lineWidth : lineWidth));
	}
	
}
