package org.eclipse.eatop.volvo.requirementmetricsviewer.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.volvo.requirementmetricsviewer.figures.ChartLabel;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.ChartLabelModel;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;



public class ChartLabelEditPart extends AbstractGraphicalEditPart {
	
	@Override
	protected IFigure createFigure() {		
		
		ChartLabelModel model = (ChartLabelModel)getModel();
		
		IFigure label = new ChartLabel();
		label.setBackgroundColor(model.getColor());
		
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
						
		} else if (getParent() instanceof BarChartEditPart) {
						
			BarChartEditPart parent = (BarChartEditPart)getParent();
			Rectangle parentBounds = parent.getChartBounds();

			int x = parentBounds.getTopRight().x() + 40;
			int y = parentBounds.getTop().y() + 20;
			int width = 500;
			int height = 15;

			// find own index among label children
			int index = 0;
			for (Object o : ((BarChartEditPart)getParent()).getModelChildren()) {
				if (o instanceof ChartLabelModel) {
					if (((ChartLabelModel)o).equals(getModel())) { // if it is this model
						break;
					}
					index++;
				}
			}

			getFigure().setBounds(new Rectangle(x, y += (index * (height + 15)), width, height));

			labelBounds = new Rectangle(x + height + 10, y, width, height);

		}
		
		label.setBounds(labelBounds);
		label.setLabelAlignment(PositionConstants.LEFT);
		getFigure().add(label);

	}

}
