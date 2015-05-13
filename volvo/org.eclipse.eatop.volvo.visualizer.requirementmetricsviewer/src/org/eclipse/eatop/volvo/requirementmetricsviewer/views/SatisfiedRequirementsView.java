package org.eclipse.eatop.volvo.requirementmetricsviewer.views;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.*;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.volvo.modelprocessor.ModelProcessor;
import org.eclipse.eatop.volvo.requirementmetricsviewer.ChartColor;
import org.eclipse.eatop.volvo.requirementmetricsviewer.GraphicalPartFactory;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.BarChartModel;
import org.eclipse.eatop.volvo.requirementmetricsviewer.models.PieChartModel;
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

import org.eclipse.eatop.volvo.visualizer.common.Activator;

/**
 * 
 * @author Joanna Svantesson
 *
 */
public class SatisfiedRequirementsView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "visualizer.metricsviewer.views.SatisfiedRequirementsView";


	// Use a standard Viewer for the Draw2d canvas
	private ScrollingGraphicalViewer viewer = new ScrollingGraphicalViewer();

	// Use standard RootEditPart as holder for all other edit parts
	private RootEditPart rootEditPart = new FreeformGraphicalRootEditPart();

	// Custom made EditPartFactory, will automatically be called to create edit
	// parts for model elements
	private EditPartFactory editPartFactory = new GraphicalPartFactory();

	// The model
	private PieChartModel pieModel;
	private BarChartModel barModel;
 
	private List<Requirement> allReqs = new ArrayList<Requirement>();
	private List<Requirement> allocatableReqs = new ArrayList<Requirement>();
	private List<Requirement> satisfiedReqs_all = new ArrayList<Requirement>();
	private List<Requirement> satisfiedReqs_allocatable = new ArrayList<Requirement>();

	private boolean pie = true;

	Action lockSelection = new LockSelectionToggleAction();
	Action switchChart = new SwitchChartAction();

	ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart part, ISelection sel) {
			// If the lock toggle button is checked we do not want to change the view
			if (lockSelection.isChecked()) return;

			if (!(sel instanceof IStructuredSelection)) return;

			IStructuredSelection ss = (IStructuredSelection) sel;

			setModel(ss);
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

	class SwitchChartAction extends Action {
		String text = "";

		public SwitchChartAction() {
			super(null, IAction.AS_PUSH_BUTTON);
			text = pie ? "Show Bar Chart" : "Show Pie Chart";
			setText(text);
			// TODO fix icon
			setImageDescriptor(Activator.getImageDescriptor("lock"));
		}

		@Override public void run() {
			pie = !pie; 

			if (pie) {
				setPie();
				text = "Show Bar Chart";
			} else {
				setBar();
				text = "Show Pie Chart";
			}
			setText(text);

		}
	};

	/**
	 * The constructor.
	 */
	public SatisfiedRequirementsView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
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

	public void dispose() {
		getSite().getPage().removeSelectionListener(listener);
	}


	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private void createToolBar() {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();

		toolBar.add(switchChart);

		lockSelection.setChecked(false);
		toolBar.add(lockSelection);
	}

	// Update the model for this view. 
	public void setModel(IStructuredSelection ss) {		
		boolean isEAElement = false;
		
		// Find requirements for the whole selection
		// Set the model to a pie chart or a bar chart
		List<Requirement> temp = new ArrayList<Requirement>();
		for (Object o : ss.toArray()) {
			if (o instanceof EAElement) {
				// Get all requirements
				temp.addAll(ModelProcessor.findAllRequirements((EAElement)o));
				isEAElement = true;
			} 
		}
		if (!isEAElement) { // if no EAElement were selected we do nothing
			return;
		}
		
		allReqs = temp;
		// Get the elements that might be allocated
		allocatableReqs = ModelProcessor.findAllocatableReqs(allReqs);
		// Get the satisfied requirements
		
		// The satisfied requirements are calculated for all requirements
		// as well as for only the allocatable ones. 
		satisfiedReqs_all = ModelProcessor.findSatisfiedRequirements(allReqs);
		satisfiedReqs_allocatable = ModelProcessor.findSatisfiedRequirements(allocatableReqs);


		if (pie) {
			setPie();
		} else {
			setBar();
		}

	}

	private void setPie() {

		// Set the proportions and the labels for the model
		ArrayList<Double> proportions = new ArrayList<Double>();
		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<Color> colors = new ArrayList<Color>();
		if (allocatableReqs.size() > 0) { 

			DecimalFormat df = new DecimalFormat("#.##");

			double satProportion = ((double)(satisfiedReqs_allocatable.size())/(double)allocatableReqs.size()) * 100;
			proportions.add(satProportion);
			labels.add("Satisfied Requirements, (" + satisfiedReqs_allocatable.size() + "), " + 
					df.format(satProportion) + "%");
			colors.add(ChartColor.GREEN.toColor());
			proportions.add(100.0 - satProportion);
			labels.add("Not satisfied Requirements, (" + (allocatableReqs.size() - satisfiedReqs_allocatable.size()) + 
					"), " + df.format(100.0 - satProportion) + "%");
			colors.add(ChartColor.RED.toColor());

			pieModel = new PieChartModel(proportions, labels, colors);
			viewer.setContents(pieModel);

		} else {
			// No requirements were found, draw nothing 
			System.out.println("No requirements found");
			viewer.setContents(null);
		}
	}
	
	private void setBar() {
		if (allReqs.size() > 0) {
			ArrayList<String> colorLabels = new ArrayList<String>();
		
			colorLabels.add("Not satisfied requirements");
			colorLabels.add("Satisfied requirements");
			
			ArrayList<Color> colors = new ArrayList<Color>();
			colors.add(ChartColor.RED.toColor());
			colors.add(ChartColor.GREEN.toColor());
			
			barModel = new BarChartModel(colorLabels, colors, allReqs.size());
			
			ArrayList<Integer> quantities = new ArrayList<Integer>();
			quantities.add(allReqs.size() - satisfiedReqs_all.size());
			quantities.add(satisfiedReqs_all.size());
			
			barModel.addBar("All requirements", quantities, colors);
			quantities.clear();
			
			quantities.add(allocatableReqs.size() - satisfiedReqs_allocatable.size());
			quantities.add(satisfiedReqs_allocatable.size());
			
			barModel.addBar("Requirements to be allocated", quantities, colors);
					
			viewer.setContents(barModel);
		} else {
			// No requirements were found, draw nothing 
			System.out.println("No requirements found");
			viewer.setContents(null);
		}
	}

}