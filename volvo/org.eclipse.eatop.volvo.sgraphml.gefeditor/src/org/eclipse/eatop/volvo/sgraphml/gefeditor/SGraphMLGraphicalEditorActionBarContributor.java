package org.eclipse.eatop.volvo.sgraphml.gefeditor;

import org.eclipse.eatop.volvo.sgraphml.testcode.DropDownAction;
import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.application.ActionBarAdvisor;

public class SGraphMLGraphicalEditorActionBarContributor extends
		ActionBarContributor {

	@Override
	protected void buildActions() {
		 addRetargetAction(new UndoRetargetAction());
		    addRetargetAction(new RedoRetargetAction());
		    addRetargetAction(new DeleteRetargetAction());
			addRetargetAction(new GridRetargetAction());   
			addRetargetAction(new RetargetAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY, GEFMessages.ToggleSnapToGeometry_Label, IAction.AS_CHECK_BOX));
			addGlobalActionKey(ActionFactory.SELECT_ALL.getId());	
	}
  @Override
  public void contributeToToolBar(IToolBarManager toolBarManager) {
    super.contributeToToolBar(toolBarManager);
    toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
    toolBarManager.add(getAction(ActionFactory.REDO.getId()));
    toolBarManager.add(getAction(ActionFactory.DELETE.getId()));
    toolBarManager.add(getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
    toolBarManager.add(getAction(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY));    
    toolBarManager.add(new ZoomComboContributionItem(getPage()));
  }
 
	

	@Override
	protected void declareGlobalActionKeys() {
		// TODO Auto-generated method stub
	}

}
