package org.eclipse.eatop.examples.contextview;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.eatop.examples.contextview.providers.ContextViewContentProvider;
import org.eclipse.eatop.examples.contextview.providers.ContextViewLabelProvider;
import org.eclipse.eatop.examples.contextview.providers.IContextProvider;
import org.eclipse.eatop.examples.explorer.JumpToAction;
import org.eclipse.eatop.examples.explorer.internal.Activator;
import org.eclipse.eatop.examples.explorer.internal.Activator.Implementation;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class ExplorerContextView extends ViewPart implements IViewerProvider, ITabbedPropertySheetPageContributor {

	private TreeViewer viewer;
	private Object input;
	private ISelectionService selectionService;

	protected Set<IPropertySheetPage> propertySheetPages = new HashSet<IPropertySheetPage>();

	static final String SHOW_INSTANCE_REFERENCES = "contextview_show_instrefs"; //$NON-NLS-1$
	static final String SHOW_MODEL_PATHS = "contextview_show_paths"; //$NON-NLS-1$
	static final String SHOW_TYPES = "contextview_show_object_types"; //$NON-NLS-1$

	private static final String LOCK_ICON_FILE = "icons/full/obj16/lock.png"; //$NON-NLS-1$
	private static final String COLLAPSE_ICON_FILE = "icons/full/obj16/collapseall.gif"; //$NON-NLS-1$

	ISelectionListener listener = new ISelectionListener() {
		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			if (!part.getSite().getId().startsWith("org.eclipse.eatop.examples.explorer.views.eastadlExplorer")) { //$NON-NLS-1$
				return;
			}
			if (lockSelection != null && lockSelection.isChecked()) {
				return;
			}
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection ss = (IStructuredSelection) selection;
				if (ss.size() == 1) {
					input = ss.getFirstElement();
				} else {

				}
				refresh();
				viewer.expandAll();
			}
		}
	};

	private Action lockSelection;
	private Action collapseAll;
	private Action showInstRefs;
	private Action showPaths;
	private Action showTypes;

	private ContextViewLabelProvider labelProvider = new ContextViewLabelProvider();
	private IContextProvider contentProvider = new ContextViewContentProvider();

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(labelProvider);
		viewer.setInput(input);
		viewer.setComparator(new ViewerSorter());
		getSite().setSelectionProvider(viewer);

		selectionService = getSite().getWorkbenchWindow().getSelectionService();
		selectionService.addSelectionListener(listener);

		hookContextMenu();
		createActions();
		applyPreferences();
		createToolBar();

		// Fake selection changed to get initial data
		listener.selectionChanged(this, selectionService.getSelection());
		viewer.expandAll();
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public void refresh() {
		viewer.setInput(input);
		viewer.refresh();
	}

	@Override
	public Viewer getViewer() {
		return viewer;
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				ExplorerContextView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void createActions() {
		lockSelection = new LockSelectionToggleAction();
		collapseAll = new CollapseAllAction();
		showInstRefs = new ShowInstanceReferencesAction();
		showTypes = new ShowTypesAction();
		showPaths = new ShowModelPathsAction();
	}

	private void applyPreferences() {
		boolean showInstRefs = Activator.getDefault().getPreferences().getBoolean(SHOW_INSTANCE_REFERENCES, true);
		boolean showPaths = Activator.getDefault().getPreferences().getBoolean(SHOW_MODEL_PATHS, false);
		boolean showTypes = Activator.getDefault().getPreferences().getBoolean(SHOW_TYPES, true);
		setShowInstRefs(showInstRefs);
		setShowModelPaths(showPaths);
		setShowTypes(showTypes);
	}

	private void fillContextMenu(IMenuManager manager) {
		ISelection sel = viewer.getSelection();
		if (sel instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) sel;
			if (selection.getFirstElement() instanceof EObject) {
				JumpToAction jumpAction = new JumpToAction((EObject) ((IStructuredSelection) sel).getFirstElement());
				jumpAction.setText("Show in Explorer"); //$NON-NLS-1$
				manager.add(jumpAction);
			}
		}
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void createToolBar() {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();

		lockSelection.setChecked(false);
		toolBar.add(lockSelection);

		toolBar.add(collapseAll);

		IMenuManager manager = actionBars.getMenuManager();
		manager.add(showInstRefs);
		manager.add(showTypes);
		manager.add(showPaths);
		actionBars.updateActionBars();
	}

	@Override
	public String getContributorId() {
		return "org.eclipse.eatop.examples.explorer.views.eastadlExplorer"; //$NON-NLS-1$
	};

	public IPropertySheetPage getPropertySheetPage() {
		IPropertySheetPage propertySheetPage = new TabbedPropertySheetPage(this);
		propertySheetPages.add(propertySheetPage);
		return propertySheetPage;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class adapter) {
		if (IPropertySheetPage.class == adapter) {
			return getPropertySheetPage();
		}
		return super.getAdapter(adapter);
	}

	@Override
	public void dispose() {
		for (IPropertySheetPage page : propertySheetPages) {
			page.dispose();
		}
		selectionService.removeSelectionListener("org.eclipse.eatop.examples.explorer.views.eastadlExplorer", listener); //$NON-NLS-1$
		super.dispose();
	}

	public void setShowInstRefs(boolean show) {
		showInstRefs.setChecked(show);
		Activator.getDefault().getPreferences().putBoolean(SHOW_INSTANCE_REFERENCES, show);
		try {
			Activator.getDefault().getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		showInstRefs.run();
	}

	public void setShowModelPaths(boolean show) {
		showPaths.setChecked(show);
		Activator.getDefault().getPreferences().putBoolean(SHOW_MODEL_PATHS, show);
		try {
			Activator.getDefault().getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		showPaths.run();
	}

	public void setShowTypes(boolean show) {
		showTypes.setChecked(show);
		Activator.getDefault().getPreferences().putBoolean(SHOW_TYPES, show);
		try {
			Activator.getDefault().getPreferences().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		showTypes.run();
	}

	private class LockSelectionToggleAction extends Action {
		public LockSelectionToggleAction() {
			super("Lock input", IAction.AS_CHECK_BOX); //$NON-NLS-1$
			ImageDescriptor descriptor = Implementation.getImageDescriptor(LOCK_ICON_FILE);
			setImageDescriptor(descriptor);
		}

		@Override
		public void run() {
			if (!isChecked()) {
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IViewPart navigator = page.findView("org.eclipse.eatop.examples.explorer.views.eastadlExplorer"); //$NON-NLS-1$
				if (navigator != null) {
					listener.selectionChanged(navigator, selectionService.getSelection("org.eclipse.eatop.examples.explorer.views.eastadlExplorer")); //$NON-NLS-1$
				}
				refresh();
				viewer.expandAll();
			}
		}
	}

	private class CollapseAllAction extends Action {

		private boolean expanded;
		private ImageDescriptor collapsedDescriptor;

		public CollapseAllAction() {
			super("Collapse all", IAction.AS_PUSH_BUTTON);
			collapsedDescriptor = Implementation.getImageDescriptor(COLLAPSE_ICON_FILE);
			setImageDescriptor(collapsedDescriptor);
		}

		@Override
		public void run() {
			viewer.collapseAll();
			setImageDescriptor(collapsedDescriptor);
			expanded = false;
			refresh();
		}
	}

	private class ShowInstanceReferencesAction extends Action {
		public ShowInstanceReferencesAction() {
			super("Show incoming instance references", IAction.AS_CHECK_BOX); //$NON-NLS-1$
		}

		@Override
		public void run() {
			contentProvider.setDisablesGenericInstanceReferencedBy(!isChecked());
			refresh();
			viewer.expandAll();
		}
	}

	private class ShowModelPathsAction extends Action {
		public ShowModelPathsAction() {
			super("Show paths to objects", IAction.AS_CHECK_BOX); //$NON-NLS-1$
		}

		@Override
		public void run() {
			labelProvider.setShowPaths(isChecked());
			refresh();
			viewer.expandAll();
		}
	}

	private class ShowTypesAction extends Action {
		public ShowTypesAction() {
			super("Show types", IAction.AS_CHECK_BOX); //$NON-NLS-1$
		}

		@Override
		public void run() {
			labelProvider.setShowTypes(isChecked());
			refresh();
			viewer.expandAll();
		}
	}

}
