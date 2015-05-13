package org.eclipse.eatop.volvo.visualizer.modeloverview.editparts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.eatop.volvo.visualizer.common.Activator;
import org.eclipse.eatop.volvo.visualizer.common.Constants;
import org.eclipse.eatop.volvo.visualizer.common.TreeNavigator;
import org.eclipse.eatop.volvo.visualizer.common.graphicalmodels.Shape.Box;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel.VisualElement;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.ElementRepresentation;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground.Category;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation.VisualBackground.Level;
import org.eclipse.eatop.volvo.visualizer.modeloverview.views.VisualModelView;

public class VisualElementEditPart extends AbstractGraphicalEditPart {

	private IFigure figure;
	private boolean overCrowded = false;

	MouseListener listener = new MouseListener() {

		@Override
		public void mousePressed(MouseEvent me) {
			// Do nothing

		}

		@Override
		public void mouseReleased(MouseEvent me) {
			// Do nothing

		}

		@Override
		public void mouseDoubleClicked(MouseEvent me) {

			VisualElement element = null;

			if (overCrowded) {

				element = (VisualElement)getModel();
				List<VisualElement> elementSiblings = ((VisualModel)getParent().getModel()).
						getElements(element.getCategory(), element.getLevel());

				String[] names = ((Label)getFigure().getToolTip()).getText().split("\n");

				Shell shell = Display.getCurrent().getActiveShell();
				ElementListSelectionDialog dialog = 
						new ElementListSelectionDialog(shell, new LabelProvider());
				dialog.setElements(names);
				dialog.setTitle("Elements");
				dialog.setMessage("Choose an element");
				dialog.setMultipleSelection(false);
				dialog.setHelpAvailable(false);
				
				String selected = null;
				if (dialog.open() == Window.OK) {
					selected = (String)dialog.getFirstResult();
				} // else user pressed cancel

				element = null;
				for (VisualElement e : elementSiblings) {
					if (e.getName().equals(selected)) {
						element = e;
					}
				}


			} else {
				element = (VisualElement)getModel();
			}

			if (element != null) {
				VisualModel parentModel = (VisualModel)getParent().getModel();
				String fileName = parentModel.getModelFile();

				IViewPart vModelView = Activator.getDefault().getWorkbench().
						getActiveWorkbenchWindow().getActivePage().findView("visualizer.modeloverview.views.VisualModelView");
				EAXML root = null;
				if (vModelView != null) {
					root = ((VisualModelView) vModelView).getModelRoot();
				}
				
				TreeNavigator.selectInEASTADLExplorer(element.getPath(), fileName, root);
			}
		}
	};

	@Override
	protected IFigure createFigure() {

		VisualElement element = (VisualElement)getModel();

		figure = createRepresentation(element);

		figure.addMouseListener(listener);


		return figure;
	}

	@Override
	protected void createEditPolicies() {

	}

	protected void refreshVisuals() {

	}

	/**
	 * 
	 * @param element
	 * @return An IFigure representing the given element or null if no 
	 * representation were defined.
	 */
	private IFigure createRepresentation(VisualElement element) {
		// Depending on the element create a figure

		figure = new RectangleFigure();

		// Get the background
		List<Object> modelSiblings = ((VisualModelEditPart)getParent()).getModelChildren();
		VisualBackground background = (VisualBackground)modelSiblings.get(0);

		// Get the corresponding visual representation for this element
		ElementRepresentation elementRepresentation = findRepresentation(element.getType());

		// Get the element siblings
		List<VisualElement> elementSiblings = ((VisualModel)getParent().getModel()).
				getElements(element.getCategory(), element.getLevel(), elementRepresentation.getShape().getZPos());

		if (elementRepresentation != null) {
			//visualizer.graphics.models.Shape shape = null;
			org.eclipse.eatop.volvo.visualizer.common.graphicalmodels.Shape shape = null;
			
			Category c = background.getCategory(element.getCategory());
			Level l = background.getLevel(element.getLevel());

			shape = elementRepresentation.getShape();
			if (shape instanceof Box) {
				Box box = (Box)shape;

				// Set size / bounds
				setBoxFigureBounds(element, elementRepresentation, c, l, elementSiblings);

				if (!overCrowded) {
					// Set color
					if (box.getColor() != null) {
						figure.setBackgroundColor(box.getColor());
					} else {
						((RectangleFigure)figure).setFill(false);
					}

					// Set label
					if (elementRepresentation.hasLabel()) {
						Label label = new Label(element.getName());
						label.setBounds(new Rectangle(figure.getBounds().x() + 5, figure.getBounds().y() + 1, 
								figure.getBounds().width(), 15));
						label.setLabelAlignment(PositionConstants.LEFT);

						figure.add(label);
					}

					// Set tooltip
					if (elementRepresentation.hasTooltip()) {
						figure.setToolTip(new Label(element.getName()));
					}

					// Set outline
					((RectangleFigure)figure).setOutline(box.hasOutline());
				}				

			} else { // TODO

			}

		}

		return figure; 
	}

	private void setBoxFigureBounds(VisualElement element, ElementRepresentation elementRepresentation, 
			Category c, Level l, List<VisualElement> elementSiblings) {
		Box box = (Box)elementRepresentation.getShape();

		if (box.getXSize() == 0 && box.getYSize() == 0) {
			// set the size to be the whole level-category intersection

			IFigure intersection = c.getShape().intersectingArea(l.getShape());
			Rectangle intersectingBounds = intersection.getBounds();

			int xPos = 3 + intersectingBounds.x();
			int yPos = intersectingBounds.y();
			int xSize = intersectingBounds.width() - 6;
			int ySize = intersectingBounds.height();

			figure.setBounds(new Rectangle(xPos, yPos, xSize, ySize));

		} else {					
			int xPos;
			int yPos;
			int xSize = box.getXSize();
			int ySize = box.getYSize();

			IFigure intersection = c.getShape().intersectingArea(l.getShape());
			Rectangle intersectingBounds = intersection.getBounds();

			// Since we don't want to draw to close to edges we make the intersecting 
			// bounds a bit smaller
			intersectingBounds.setX(intersectingBounds.x() + 5);
			intersectingBounds.setY(intersectingBounds.y() + 5);
			intersectingBounds.setSize(intersectingBounds.width() - 10, 
					intersectingBounds.height() - 10);


			// groupBounds are used in case the area gets overcrowded
			Rectangle groupBounds = new Rectangle();

			// Set the position depending on the alignment
			// First calculate the placement and then depending on which element
			// it is in the order of elements we move it the appropriate
			// distance (also depending on if it is allowed to overlap or not) in
			// the specified direction.

			// Placement
			switch (elementRepresentation.getPlacement()) {
			case Constants.TOPLEFT:
				xPos = intersectingBounds.x();
				yPos = intersectingBounds.y();
				break;
			case Constants.TOP: 
				xPos = intersectingBounds.x() + intersectingBounds.width()/2 - (int)Math.round((double)xSize/2);
				yPos = intersectingBounds.y();
				break;
			case Constants.TOPRIGHT:
				xPos = intersectingBounds.right() - xSize;
				yPos = intersectingBounds.y();
				break;
			case Constants.LEFT: 
				xPos = intersectingBounds.x();
				yPos = intersectingBounds.y() + intersectingBounds.height()/2 - (int)Math.round((double)ySize/2);
				break;
			case Constants.CENTER:
				xPos = intersectingBounds.x() + intersectingBounds.width()/2 - (int)Math.round((double)xSize/2);
				yPos = intersectingBounds.y() + intersectingBounds.height()/2 - (int)Math.round((double)ySize/2);
				break;
			case Constants.RIGHT:
				xPos = intersectingBounds.right() - xSize;
				yPos = intersectingBounds.y() + intersectingBounds.height()/2 - (int)Math.round((double)ySize/2);
				break;
			case Constants.BOTTOMLEFT:
				xPos = intersectingBounds.x();
				yPos = intersectingBounds.bottom() - ySize;
				break;
			case Constants.BOTTOM:
				xPos = intersectingBounds.x() + intersectingBounds.width()/2 - (int)Math.round((double)xSize/2);
				yPos = intersectingBounds.bottom() - ySize;
				break;
			case Constants.BOTTOMRIGHT:
				xPos = intersectingBounds.right() - xSize;
				yPos = intersectingBounds.bottom() - ySize;
				break;
			default: 
				xPos = 0;
				yPos = 0;
				break;
			}

			groupBounds.setX(xPos);
			groupBounds.setY(yPos);

			// Direction
			List<ElementRepresentation> reps = new ArrayList<ElementRepresentation>();
			int index = 0;
			for (VisualElement e : elementSiblings) {
				if (e.equals(element)) { // if it is this element
					break;
				}
				reps.add(findRepresentation(e.getType()));
				index++;
			}

			switch (elementRepresentation.getDirection()) {
			case Constants.RIGHT:
				if (!elementRepresentation.getOverlapping()) {
					int sum = 0;
					for (ElementRepresentation er : reps) {
						if (er != null) {
							sum += ((Box)er.getShape()).getXSize();
						}
					}
					xPos += sum + index*2;
				} else {
					xPos += index*5;
				}
				
				break;
			case Constants.DIAGONAL:
				if (!elementRepresentation.getOverlapping()) {
					int sumX = 0;
					int sumY = 0;
					for (ElementRepresentation er : reps) {
						if (er != null) {
							sumX += ((Box)er.getShape()).getXSize();
							sumY += ((Box)er.getShape()).getYSize();
						}
					}
					xPos += sumX + index*2;
					yPos += sumY + index*2;
				} else {
					xPos += index*5;
					yPos += index*5;
				}
				break;
			case Constants.DOWN:
				if (!elementRepresentation.getOverlapping()) {
					int sum = 0;
					for (ElementRepresentation er : reps) {
						if (er != null) {
							sum += ((Box)er.getShape()).getYSize();
						}
					}
					yPos += sum + index*2;
				} else {
					yPos += index*5;
				}
				break;
			}

			groupBounds.setWidth(xPos + xSize - groupBounds.x());
			groupBounds.setHeight(yPos + ySize - groupBounds.y());

			// Check that position is not outside the intersecting area
			Rectangle figureBounds = new Rectangle(xPos, yPos, xSize, ySize);


			if (intersectingBounds.contains(figureBounds)) {
				figure.setBounds(figureBounds);
				((VisualModelEditPart)getParent()).setDrawn(element, true);
			} else { // We have to many (or to big) elements to fit visually
				((VisualModelEditPart)getParent()).setDrawn(element, false);
				overCrowded = true;
				if (index > 0 && ((VisualModelEditPart)getParent()).wereDrawn(elementSiblings.get(index-1))) {
					// The last element were drawn which means we should draw an overcrowded element
					figure = overCrowded(groupBounds.intersect(intersectingBounds), elementSiblings);
				} else {
					// The last element was not drawn which means that this should not be drawn either
					figure = new RectangleFigure(); // an empty rectangle with no bounds
				}

			}

		}
	}

	private IFigure overCrowded(Rectangle bounds, List<VisualElement> elementSiblings) {
		RectangleFigure fig = new RectangleFigure();
		fig.setBounds(bounds);
		fig.setFill(false);
		fig.setOutline(false);

		StringBuilder sb = new StringBuilder();
		for (VisualElement e : elementSiblings) {
			sb.append(e.getName());
			if (!(e.equals(elementSiblings.get(elementSiblings.size()-1)))) {
				sb.append("\n");
			}
		}

		fig.setToolTip(new Label(sb.toString()));

		return fig;
	}

	private ElementRepresentation findRepresentation(String type) {
		ElementRepresentation elementRepresentation = null;

		VisualRepresentation representation = ((VisualModel)getParent().getModel()).getVisualRepresentation();
		List<ElementRepresentation> elementReps = representation.getElementRepresentations();
		for (ElementRepresentation er : elementReps) {
			for (String t : er.getTypes()) {
				if (t.equals(type)) {
					elementRepresentation = er;
					break;
				} else if (t.equals("default")) {
					elementRepresentation = er;
				}
			}
		}
		return elementRepresentation;
	}

}
