package org.eclipse.eatop.volvo.requirementmetricsviewer;

import org.eclipse.eatop.volvo.requirementmetricsviewer.editparts.BarChartEditPart;
import org.eclipse.eatop.volvo.requirementmetricsviewer.editparts.BarEditPart;
import org.eclipse.eatop.volvo.requirementmetricsviewer.editparts.ChartLabelEditPart;
import org.eclipse.eatop.volvo.requirementmetricsviewer.editparts.PieChartEditPart;
import org.eclipse.eatop.volvo.requirementmetricsviewer.editparts.PiePieceEditPart;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.BarChartModel;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.ChartLabelModel;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.PieChartModel;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.PiePieceModel;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.BarChartModel.BarModel;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;





 
public class GraphicalPartFactory implements EditPartFactory {
 
	public EditPart createEditPart(EditPart iContext, Object iModel) {
 
		EditPart editPart = null;
		if (iModel instanceof PieChartModel) {
			editPart = new PieChartEditPart();
		} else if (iModel instanceof PiePieceModel) {
			editPart = new PiePieceEditPart();
		} else if (iModel instanceof BarChartModel) {
			editPart = new BarChartEditPart();
		} else if (iModel instanceof BarModel) {
			editPart = new BarEditPart();
		} else if (iModel instanceof ChartLabelModel) {
			editPart = new ChartLabelEditPart();
		}
 
		if (editPart != null) {
			editPart.setModel(iModel);
		}
		return editPart;
	}
} 

