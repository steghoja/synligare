package org.eclipse.eatop.volvo.sgraphml.testcode;

import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.Action;

public class ToggleGridVisibilityAction extends Action {


	/**
	 * The viewer whose grid visibility property is to be toggled
	 */
	protected GraphicalViewer diagramViewer;
		
	/**
	 * Constructor
	 * @param	diagramViewer	the viewer whose grid visibility property is to be toggled
	 */
	public ToggleGridVisibilityAction(GraphicalViewer diagramViewer) {
	
		super(GEFMessages.ToggleGrid_Label, AS_CHECK_BOX);
		this.diagramViewer = diagramViewer;
		setToolTipText(GEFMessages.ToggleGrid_Tooltip);
		setId(GEFActionConstants.TOGGLE_GRID_VISIBILITY);
		setActionDefinitionId(GEFActionConstants.TOGGLE_GRID_VISIBILITY);
		setChecked(isChecked());
	}

	/**
	 * @see org.eclipse.jface.action.IAction#isChecked()
	 */
	public boolean isChecked() {
		Boolean val = ((Boolean)diagramViewer
				.getProperty(SnapToGrid.PROPERTY_GRID_VISIBLE));
		if (val != null)
			return val.booleanValue();
		return false;
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	public void run() {
		
		diagramViewer.setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE,
								new Boolean(!isChecked()));
		
	}

	
}
