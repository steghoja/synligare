package org.eclipse.eatop.examples.tableview.parts;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.eatop.examples.tableview.TableViewPlugin;
import org.eclipse.eatop.examples.tableview.core.Table;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;


/**
 * Holds some classes which will only be used in the case that a ViewPart should be available 
 */
public class TableView extends ViewPart{
	public static final String ID = "org.eclipse.eatop.examples.tableview.ui.tableView";
	
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
				parent.layout();
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
			setImageDescriptor(TableViewPlugin.getImageDescriptor("resize"));
		}

		@Override public void run() {
			refresh();
		}
	};
	
	public class MultiLineToggleAction extends Action {
		public MultiLineToggleAction() {
			super("Multi line rows", IAction.AS_CHECK_BOX);
			setImageDescriptor(TableViewPlugin.getImageDescriptor("columns"));
		}
		
		@Override
		public void run() {
			if (isChecked()) {
				table.setMultiLine(true);
			} else {
				table.setMultiLine(false);
			}
			table.refresh(autoResize.isChecked());
		}
	}
	
	public class SaveToExcelAction extends Action {
		public SaveToExcelAction() {
			super("Export to Excel", IAction.AS_PUSH_BUTTON);
			setImageDescriptor(PlatformUI.getWorkbench()
			        .getSharedImages()
	        		.getImageDescriptor(ISharedImages.IMG_ETOOL_SAVE_EDIT));
		}
		
		@Override
		public void run() {
			table.saveToExcel();
		}
	}
	
	Action lockSelection = new LockSelectionToggleAction();
	Action autoResize = new AutoResizeToggleAction();
	Action multiLine = new MultiLineToggleAction();
	Action saveToExcelAction = new SaveToExcelAction();
	
	private ISelectionService selectionService;
	private Table table;
	private Composite parent;
	
	public TableView() { }

	@Override
	public void createPartControl(Composite parent) {
		this.parent = parent;
		StackLayout stackLayout = new StackLayout();
		parent.setLayout(stackLayout);
		table = new Table(parent, stackLayout);
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
		
		autoResize.setChecked(true);
		toolBar.add(multiLine);
		
		toolBar.add(saveToExcelAction);	
	}

	public void refresh() {
		table.refresh(autoResize.isChecked());
		parent.layout();
	}
}
