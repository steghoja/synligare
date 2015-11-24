package org.eclipse.eatop.volvo.sgraphml.gefeditor.contextmenu;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

public class GEFEditorContextMenuProvider extends ContextMenuProvider {

	
		 private ActionRegistry actionRegistry;
		 
		    public GEFEditorContextMenuProvider(EditPartViewer viewer, final ActionRegistry actionRegistry) {
		        super(viewer);
		        setActionRegistry(actionRegistry);
		    }
		    
		    
		    @Override
		    public void buildContextMenu(IMenuManager menu) {
		        GEFActionConstants.addStandardActionGroups(menu);
		 
		        IAction action;
		 
		        action = getActionRegistry().getAction(GotoModelElementAction.GOTO_MODEL_ELEMENT);
		        menu.appendToGroup(GEFActionConstants.GROUP_FIND, action);

		        action = getActionRegistry().getAction(SaveImageAction.SAVE_IMAGE);
		        menu.appendToGroup(GEFActionConstants.GROUP_SAVE, action);
		        
		        
	/*	        action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
		        menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);
		        action = getActionRegistry().getAction(ActionFactory.REDO.getId());
		        menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);
		*/
		    }
		 
		    private ActionRegistry getActionRegistry() {
		        return actionRegistry;
		    }
		 
		    private void setActionRegistry(final ActionRegistry actionRegistry) {
		        this.actionRegistry = actionRegistry;
		    }
		
		
		

	}


