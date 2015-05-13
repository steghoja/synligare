package org.eclipse.eatop.volvo.visualizer.modeloverview;

import org.eclipse.eatop.volvo.visualizer.modeloverview.editparts.BackgroundEditPart;
import org.eclipse.eatop.volvo.visualizer.modeloverview.editparts.VisualElementEditPart;
import org.eclipse.eatop.volvo.visualizer.modeloverview.editparts.VisualModelEditPart;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel.VisualElement;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
 
public class GraphicalPartFactory implements EditPartFactory {
 
	public EditPart createEditPart(EditPart iContext, Object iModel) {
 
		EditPart editPart = null;
		if (iModel instanceof VisualBackground) {
			editPart = new BackgroundEditPart();
		} else if (iModel instanceof VisualModel) {
			editPart = new VisualModelEditPart();
		} else if (iModel instanceof VisualElement) {
			editPart = new VisualElementEditPart();
		}
 
		if (editPart != null) {
			editPart.setModel(iModel);
		}
		return editPart;
	}
} 

