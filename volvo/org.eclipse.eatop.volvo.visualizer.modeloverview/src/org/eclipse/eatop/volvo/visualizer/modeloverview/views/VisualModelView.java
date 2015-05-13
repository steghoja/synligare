package org.eclipse.eatop.volvo.visualizer.modeloverview.views;

import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.volvo.modelprocessor.TreeModelProcessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.FreeformGraphicalRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.eatop.volvo.visualizer.common.Activator;
import org.eclipse.eatop.volvo.visualizer.modeloverview.GraphicalPartFactory;
import org.eclipse.eatop.volvo.visualizer.modeloverview.VisualizationGenerator;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualModel;
import org.eclipse.eatop.volvo.visualizer.modeloverview.models.VisualRepresentation;
import org.eclipse.eatop.volvo.visualizer.modeloverview.ui.ChooseRepresentationDialog;

public class VisualModelView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "visualizer.views.VisualModelView";

	private VisualModelView self = this;
	
	// Use a standard Viewer for the Draw2d canvas
	private ScrollingGraphicalViewer viewer = new ScrollingGraphicalViewer();

	// Use standard RootEditPart as holder for all other edit parts
	private RootEditPart rootEditPart = new FreeformGraphicalRootEditPart();

	// Custom made EditPartFactory, will automatically be called to create edit
	// parts for model elements
	private EditPartFactory editPartFactory = new GraphicalPartFactory();

	//private VisualRepresentation graphicsModel;

	private EAXML root;
	// lastPath is used to know if we should generate the visualization when 
	// a new object is selected or if it is the same visualization as before
	private String lastPath = VisualRepresentation.DEFAULT_REPRESENTATION_PATH;
	private String representationPath = VisualRepresentation.DEFAULT_REPRESENTATION_PATH;

	private Action lockSelection = new LockSelectionToggleAction();
	private Action changeRepresentation = new ChangeVisualRepresentationAction();

	ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart part, ISelection sel) {			
			
			// If the lock toggle button is checked we do not want to change the view
			if (lockSelection.isChecked()) return;

			if (!(sel instanceof IStructuredSelection)) return; 

			IStructuredSelection ss = (IStructuredSelection) sel;
			if (!(ss.getFirstElement() instanceof EObject)) { // Not an EObject
				return;
			}

			EAXML lastRoot = root;			
			root = TreeModelProcessor.findRoot(sel);
			if (root != null && root.equals(lastRoot)
					&& lastPath.equals(representationPath)) {
				// The root has not changed
				// And the representationFile has not changed either
				// Do nothing
				return;
			}
			lastPath = representationPath;
			
			VisualizationGenerator visGen = new VisualizationGenerator();
			setInput(visGen.createVisualModel(sel, self));
		}
	};

	class LockSelectionToggleAction extends Action {
		public LockSelectionToggleAction() {
			super("Lock view", IAction.AS_CHECK_BOX);
			// TODO fix icon
			setImageDescriptor(Activator.getImageDescriptor("lock"));
		}

		@Override public void run() {

		}
	};

	class ChangeVisualRepresentationAction extends Action {
		public ChangeVisualRepresentationAction() {
			super("Change Representation", IAction.AS_PUSH_BUTTON);
			// TODO fix icon
			setImageDescriptor(Activator.getImageDescriptor("lock"));
		}

		@Override public void run() {
			// Show dialog
			Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(); 
			ChooseRepresentationDialog dialog = new ChooseRepresentationDialog(shell);
			dialog.open();
			
			lastPath = representationPath;
			representationPath = dialog.getRepresentationFile();
		}
	};

	@Override
	public void createPartControl(Composite parent) {
		getSite().setSelectionProvider(viewer);
		getSite().getPage().addSelectionListener(listener);

		// Initialize the viewer
		viewer.createControl(parent);
		viewer.setRootEditPart(rootEditPart);
		viewer.setEditPartFactory(editPartFactory);

		// Set the views' background to white
		viewer.getControl().setBackground(new Color(null, 255, 255, 255));

		createToolBar();
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();		
	}

	private void createToolBar() {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();

		toolBar.add(changeRepresentation);
		
		lockSelection.setChecked(false);
		toolBar.add(lockSelection);
	}

	public void setInput(VisualModel model) {
		viewer.setContents(model);
	}

	public void setModelRoot(EAXML root) {
		this.root = root;
	}

	public EAXML getModelRoot() {
		return root;
	}
	
	public String getRepresentationPath() {
		return representationPath;
	}
}
