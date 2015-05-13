/*package examples;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.Shape;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.swt.SWT;



public class DiagramConnectionEditPart extends AbstractConnectionEditPart{
	
	protected void refreshVisuals() {
		super.refreshVisuals();
		
		IFigure f = getFigure();
		DiagramConnector c = (DiagramConnector)getModel();
		
		if (f instanceof Shape) {
			((Shape)f).setAntialias(SWT.ON);
		}
		
		if (f instanceof Polyline) {
			// If want to change the start point or end point or add
			// points it can be done here! Or can it?
			// TODO how change the position or add points to a connection?
			/*Polyline pl = (Polyline)f;

			if (c.getStartX() != -1 && c.getStartY() != -1) {
				pl.setStart(new Point(c.getStartX(), c.getStartY()));
			}
			if (c.getEndX() != -1 && c.getEndY() != -1) {
				pl.setEnd(new Point(c.getEndX(), c.getEndY()));
			}*/
			
/*		}
	}
	
	protected void createEditPolicies() {  }
} 
*/