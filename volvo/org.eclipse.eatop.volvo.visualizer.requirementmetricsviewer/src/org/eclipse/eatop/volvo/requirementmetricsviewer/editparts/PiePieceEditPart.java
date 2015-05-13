package org.eclipse.eatop.volvo.requirementmetricsviewer.editparts;

import org.eclipse.draw2d.IFigure;
import org.eclipse.eatop.volvo.requirementmetricsviewer.figures.PiePiece;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.PiePieceModel;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.swt.SWT;


/**
 * 
 * 
 * @author Joanna Svantesson
 *
 */
public class PiePieceEditPart extends AbstractGraphicalEditPart {
	
	@Override
	protected IFigure createFigure() {
		
		PiePieceModel model = (PiePieceModel)getModel();
		IFigure diagramPiece = new PiePiece(model.getAngle(), model.getStartAngle());
		diagramPiece.setBackgroundColor(model.getColor());
		((PiePiece) diagramPiece).setLineWidthFloat((float) 1.5); 
		((PiePiece) diagramPiece).setAntialias(SWT.ON);
		
		return diagramPiece;
	}

	@Override
	protected void createEditPolicies() {
		
	}

	protected void refreshVisuals() {
		// This is where the actual drawing is done,
		
		PiePieceModel model = (PiePieceModel)getModel();
		getFigure().setBounds(model.getBounds());		
	}

}
