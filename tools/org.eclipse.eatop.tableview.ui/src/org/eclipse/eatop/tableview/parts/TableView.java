package org.eclipse.eatop.tableview.parts;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.eatop.tableview.TableViewPlugin;
import org.eclipse.eatop.tableview.core.Table;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;


/**
 * Holds some classes which will only be used in the case that a ViewPart should be available 
 */
public class TableView extends ViewPart{
	public static final String ID = "org.eclipse.eatop.tableview.ui.tableView";
	
	ISelectionListener listener = new ISelectionListener() {
		@Override public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			if (lockSelection != null && !lockSelection.isChecked()) {
				ArrayList<EObject> data = new ArrayList<EObject>();
				if (selection instanceof IStructuredSelection) {
					IStructuredSelection ss = (IStructuredSelection) selection;
					for (Iterator<?> iterator = ss.iterator(); iterator.hasNext();) {
						Object o = iterator.next();
						if (o instanceof EObject) {
							EObject eo = (EObject) o;
							data.add(eo);
						}
					}
				}
				table.updateTable(data, autoResize.isChecked());
			}
		}
	};
	
	private class LockSelectionToggleAction extends Action {
		public LockSelectionToggleAction() {
			super("Link with selection", IAction.AS_CHECK_BOX);
			setImageDescriptor(TableViewPlugin.getImageDescriptor("lock"));
		}

		@Override public void run() {
			if (isChecked()) {
				listener.selectionChanged(TableView.this, selectionService.getSelection());
			}
		}
	};

	private class AutoResizeToggleAction extends Action {
		public AutoResizeToggleAction() {
			super("Automatic resize", IAction.AS_CHECK_BOX);
			setImageDescriptor(TableViewPlugin.getImageDescriptor("columns"));
		}

		@Override public void run() {
			refresh();
		}
	};
	
	Action lockSelection = new LockSelectionToggleAction();
	Action autoResize = new AutoResizeToggleAction();
	
	private ISelectionService selectionService;
	private Table table;
	public TableView() { }

	@Override
	public void createPartControl(Composite parent) {
		table = new Table(parent);
		selectionService = getSite().getWorkbenchWindow().getSelectionService();
		selectionService.addSelectionListener(listener);
		createToolBar();
		
		// Fake selection changed to get initial data
		listener.selectionChanged(this, selectionService.getSelection());		
	}

	@Override
	public void setFocus() {
		table.setFocus();
	}
	
	@Override
	public void dispose() {
		selectionService.removeSelectionListener(listener);
		table.dispose();
		super.dispose();
	}
	
	private void createToolBar() {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();

		lockSelection.setChecked(false);
		toolBar.add(lockSelection);

		autoResize.setChecked(true);
		toolBar.add(autoResize);		
	}

	public void refresh() {
		table.refresh(autoResize.isChecked());
	}
}
