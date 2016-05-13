package org.eclipse.eatop.volvo.metrics.controller;



import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.metrics.model.ChartLabelModel;
import org.eclipse.eatop.volvo.metrics.viewfigure.ChartLabel;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;



public class ChartLabelEditPart extends AbstractGraphicalEditPart {
	
	@Override
	protected IFigure createFigure() {		
		
		ChartLabelModel model = (ChartLabelModel)getModel();
		
		IFigure label = new ChartLabel();
		label.setBackgroundColor(model.getColor());

		String toolTipText = model.getToolTipText();
		if ((toolTipText != null) && !toolTipText.isEmpty()){
			IFigure tipLabel = new Label(toolTipText);
			Font font = new Font(Display.getCurrent(), "Courier", 10, SWT.NORMAL);
			tipLabel.setFont(font);

			label.setToolTip(tipLabel);
			
		}
		return label;
	}

	@Override
	protected void createEditPolicies() {
			
	}

	protected void refreshVisuals() {
		// This is where the actual drawing is done,
		
		ChartLabelModel model = (ChartLabelModel)getModel();
		
		
		Rectangle labelBounds = new Rectangle();
		Label label = new Label(model.getLabel());
		
		if (getParent() instanceof PieChartEditPart) {
		
			getFigure().setBounds(model.getBounds());
			
			int labelX = model.getBounds().x() + model.getBounds().height() + 10;
			labelBounds = new Rectangle(model.getBounds());
			labelBounds.setX(labelX);
						
		}
		

		
		label.setBounds(labelBounds);
		label.setLabelAlignment(PositionConstants.LEFT);
		getFigure().add(label);

	}

}
