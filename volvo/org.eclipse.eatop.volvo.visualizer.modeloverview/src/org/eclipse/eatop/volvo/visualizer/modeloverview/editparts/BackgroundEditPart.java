package org.eclipse.eatop.volvo.visualizer.modeloverview.editparts;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.eatop.volvo.visualizer.common.graphicalmodels.Shape;
import org.eclipse.eatop.volvo.visualizer.common.graphicalmodels.Shape.Box;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground.SuperCategory;

public class BackgroundEditPart extends AbstractGraphicalEditPart {

	private List<SuperCategory> cats = new ArrayList<SuperCategory>();
	
	@Override
	protected IFigure createFigure() {

		VisualBackground background = (VisualBackground)getModel();

		Figure f = new FreeformLayer();
		f.setLayoutManager(new FreeformLayout());

		f.setBorder(new MarginBorder(1));
		// Create a layout for the graphical screen

		XYLayout xyLayout = new XYLayout();
		f.setLayoutManager(xyLayout);

		f.setOpaque(true);


		IFigure part;
		cats = new ArrayList<SuperCategory>();
		cats.addAll(background.getLevels());
		cats.addAll(background.getCategories());
		
		// Sort the SuperCategories on z-value
		Comparator<SuperCategory> comp = new ZComparator();
		Collections.sort(cats, comp);
		
		for (SuperCategory c : cats) {
			Shape shape = c.getShape();
			if (shape instanceof Box) {
				part = new RectangleFigure();
			} else { //TODO other shapes than box
				part = new RectangleFigure();
			}

			part.setBackgroundColor(shape.getColor());

			f.add(part);
		}

		return f;
	}

	@Override
	protected void createEditPolicies() {
	}

	protected void refreshVisuals() {
		// This is where the actual drawing is done

		IFigure f = getFigure();		
		
		int i = 0;
		for (Object o : f.getChildren()) {
			SuperCategory c = cats.get(i);
			if (o instanceof RectangleFigure) {
				RectangleFigure part = (RectangleFigure)o;
				Box b = (Box)c.getShape();

				part.setBounds(new Rectangle(b.getXPos(), b.getYPos(),
						b.getXSize(), b.getYSize()));	

				part.setOutline(b.hasOutline());
				
				if (c.isTitle()) {
					Label l = new Label(((VisualModel)getParent().getModel()).getTitle());
					l.setBounds(new Rectangle(part.getBounds().x() + 5, part.getBounds().y() + 1,
							part.getBounds().width(), 15));
					l.setLabelAlignment(PositionConstants.LEFT);
					
					Font font = new Font(null, "Segoe UI", 9, SWT.BOLD);
					l.setFont(font);
					
					part.add(l);
				}
				
				if (c.hasLabel()) {
					Label l = new Label(c.getName());
					l.setBounds(new Rectangle(part.getBounds().x() + 5, part.getBounds().y() + 1,
							part.getBounds().width(), 15));
					l.setLabelAlignment(PositionConstants.LEFT);
					part.add(l);
				}
				if (c.hasTooltip()) {
					part.setToolTip(new Label(c.getName()));
				}

				if (c.getShape().getColor() == null) { 
					part.setFill(false);
				}
			}
			i++;
		}
	}
	
	private class ZComparator implements Comparator<SuperCategory> {

		@Override
		public int compare(SuperCategory o1, SuperCategory o2) {

			return o1.getShape().getZPos() - o2.getShape().getZPos();
		}

	}
	
}
