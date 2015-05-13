package org.eclipse.eatop.volvo.requirementmetricsviewer.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.ChartLabelModel;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.PieChartModel;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.PiePieceModel;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;


public class PieChartEditPart extends AbstractGraphicalEditPart {

	protected IFigure createFigure() {
		Figure f = new FreeformLayer();
		f.setLayoutManager(new FreeformLayout());
 
		f.setBorder(new MarginBorder(1));
		
		// Create a layout for the graphical screen		
		XYLayout xyLayout = new XYLayout();
		f.setLayoutManager(xyLayout);
				
		f.setOpaque(true);
		return f;
	}
 
	protected void createEditPolicies() {
 
	}
	
	protected List<Object> getModelChildren() {
		ArrayList<PiePieceModel> pieces = ((PieChartModel) getModel()).getPieces();
		ArrayList<ChartLabelModel> labels = ((PieChartModel) getModel()).getLabelModels();
		
		ArrayList<Object> children = new ArrayList<Object>();
		children.addAll(pieces);

		if (labels != null) children.addAll(labels);
		
		return children;
	}
	
}
