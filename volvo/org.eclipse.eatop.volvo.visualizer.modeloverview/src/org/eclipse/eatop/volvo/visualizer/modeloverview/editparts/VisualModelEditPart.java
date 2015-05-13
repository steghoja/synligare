package org.eclipse.eatop.volvo.visualizer.modeloverview.editparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel.VisualElement;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.ElementRepresentation;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;


public class VisualModelEditPart extends AbstractGraphicalEditPart {

	private Map<VisualElement, Boolean> drawn = new HashMap<VisualElement, Boolean>();

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

	@Override
	protected void createEditPolicies() {

	}

	protected List<Object> getModelChildren() {		
		VisualBackground background = ((VisualModel) getModel()).getBackground();
		List<VisualElement> elements = ((VisualModel) getModel()).getElements();

		// Sort the elements on z coordinate
		Comparator<VisualElement> c = new ZComparator();
		Collections.sort(elements, c);

		ArrayList<Object> children = new ArrayList<Object>();
		children.add(background);
		if (elements != null) children.addAll(elements);

		return children;
	}

	public void setDrawn(VisualElement e, boolean isDrawn) {
		drawn.put(e, isDrawn);
	}

	public boolean wereDrawn(VisualElement e) {
		if (drawn.containsKey(e)) {
			return drawn.get(e);
		}
		return false;
	}

	public void initDrawn(int size) {
		// Initialize all elements of the drawn array to false
		for (VisualElement e : ((VisualModel) getModel()).getElements()) {
			if (!drawn.containsKey(e)) {
				drawn.put(e, false);
			}
		}

	}

	private class ZComparator implements Comparator<VisualElement> {

		@Override
		public int compare(VisualElement o1, VisualElement o2) {

			VisualRepresentation vr = ((VisualModel) getModel()).getVisualRepresentation();
			ElementRepresentation er1 = vr.getElementRepresentation(o1);
			ElementRepresentation er2 = vr.getElementRepresentation(o2);
			
			return er1.getShape().getZPos() - er2.getShape().getZPos();
		}

	}

}
