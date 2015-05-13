package org.eclipse.eatop.volvo.sgraphml.testcode;

import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;

public class DropDownAction extends Action implements IMenuCreator {
	private Menu fMenu;

	public DropDownAction() {
		setText("Snap");
		setMenuCreator(this);
	}

	public void dispose() {
		if (fMenu != null) {
			fMenu.dispose();
			fMenu= null;
		}
	}

	public Menu getMenu(Menu parent) {
		return null;
	}

	public Menu getMenu(Control parent) {
		if (fMenu != null)
			fMenu.dispose();

		fMenu= new Menu(parent);
		int i= 0;
		
		//Eller måste jag stoppa in action utifrån i denna klassen.....
		//Jag kan ju lätt göra en action för att toggla snap to grid
		//och jag her en för snap to geometry....
		
		Action snapToGridAction = new Action("Snap to Grid", IAction.AS_CHECK_BOX) {
			public void run() {
				
				//se till att enabled sätts
				int kalle = 1;
			}
		};
		
		Action snapToGeometryAction = new Action("Snap to geometry", IAction.AS_DROP_DOWN_MENU){
			public void run(){
				//se till att toggle geometry körs... hur kommer jag åt den
				
				
			}
		};
		addActionToMenu(fMenu, snapToGridAction);
		addActionToMenu(fMenu, snapToGeometryAction);

		return fMenu;
	}

	protected void addActionToMenu(Menu parent, Action action) {
		ActionContributionItem item= new ActionContributionItem(action);
		item.fill(parent, -1);
	}

	public void run() {

	}

	/**
	 * Get's rid of the menu, because the menu hangs on to 
	 * the searches, etc.
	 */
	void clear() {
		dispose();
	}
}


