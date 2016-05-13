package org.eclipse.eatop.volvo.metrics.controller;

import org.eclipse.eatop.volvo.metrics.controller.ChartLabelEditPart;
import org.eclipse.eatop.volvo.metrics.controller.PieChartEditPart;
import org.eclipse.eatop.volvo.metrics.controller.PiePieceEditPart;
import org.eclipse.eatop.volvo.metrics.model.ChartLabelModel;
import org.eclipse.eatop.volvo.metrics.model.PieChartModel;
import org.eclipse.eatop.volvo.metrics.model.PiePieceModel;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;





 
public class GraphicalPartFactory implements EditPartFactory {
 
	public EditPart createEditPart(EditPart iContext, Object iModel) {
 
		EditPart editPart = null;
		if (iModel instanceof PieChartModel) {
			editPart = new PieChartEditPart();
		} else if (iModel instanceof PiePieceModel) {
			editPart = new PiePieceEditPart();
		} else if (iModel instanceof ChartLabelModel) {
			editPart = new ChartLabelEditPart();
		}
 
		if (editPart != null) {
			editPart.setModel(iModel);
		}
		return editPart;
	}
} 

