package org.eclipse.eatop.volvo.metrics.views;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.*;
//import org.eclipse.eatop.eastadl21.EAElement;
//import org.eclipse.eatop.eastadl21.Requirement;
//import org.eclipse.eatop.volvo.modelprocessor.ModelProcessor;
import org.eclipse.eatop.volvo.metrics.ChartColor;
import org.eclipse.eatop.volvo.metrics.Fraction;
import org.eclipse.eatop.volvo.metrics.controller.GraphicalPartFactory;
import org.eclipse.eatop.volvo.metrics.model.PieChartModel;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.FreeformGraphicalRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Color;
import org.eclipse.eatop.volvo.metrics.Activator;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @author Henrik Kaijser
 *
 */
public abstract class MetricsViewBase extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
//	public static final String ID = "visualizer.metricsviewer.views.RequirementsView";


	// Use a standard Viewer for the Draw2d canvas
	private ScrollingGraphicalViewer viewer = new ScrollingGraphicalViewer();

	// Use standard RootEditPart as holder for all other edit parts
	private RootEditPart rootEditPart = new FreeformGraphicalRootEditPart();

	// Custom made EditPartFactory, will automatically be called to create edit
	// parts for model elements
	private EditPartFactory editPartFactory = new GraphicalPartFactory();

	// The model
	private PieChartModel pieModel;
	
	protected String greenPiePieceDescription;	// N/D 
	protected String redPiePieceDescription;	// (N-D)/D
	protected String noDenominatorFoundDescription;	// D=0
	protected Fraction metricFraction;				//N/D, Null if D=0
	
	protected String greenLabeltoolTipText;
	
	Action lockSelection = new LockSelectionToggleAction();

	ISelectionListener listener = new ISelectionListener() {
		public void selectionChanged(IWorkbenchPart part, ISelection sel) {

			//make sure selection change is from navigator
			if (part.getSite().getId().equals("org.eclipse.eatop.examples.explorer.views.eastadlExplorer")){
				
				// If the lock toggle button is checked we do not want to change the view
				if (lockSelection.isChecked()) return;
	
				if (!(sel instanceof IStructuredSelection)) return;
	
				IStructuredSelection ss = (IStructuredSelection) sel;
	
				setModel(ss);
			
			}
		}
	};

	class LockSelectionToggleAction extends Action {
		public LockSelectionToggleAction() {
			super("Lock View", IAction.AS_CHECK_BOX);
			ImageDescriptor image = Activator.getImageDescriptor("icons/lock.png");
			setImageDescriptor(image);

			
		}
		@Override public void run() {
			if (lockSelection.isChecked()){
				setText("Unlock View");
			}
			else{
				setText("Lock View");
			}
		}
	};


	/**
	 * The constructor.
	 */
	public MetricsViewBase() {
		
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
	@Override
	public void setFocus() {
		//find explorer selection and update model
		ISelectionService selectionService = getSite().getPage();
	    ISelection selection = selectionService.getSelection("org.eclipse.eatop.examples.explorer.views.eastadlExplorer");    
	    if(selection instanceof IStructuredSelection) {    
	    	setModel((IStructuredSelection)selection);
	    }		
		
		viewer.getControl().setFocus();
	}

	private void createToolBar() {
		IActionBars actionBars = getViewSite().getActionBars();
		IToolBarManager toolBar = actionBars.getToolBarManager();
		
		lockSelection.setChecked(false);
		toolBar.add(lockSelection);
	}

	// Update the model for this view. 
	public void setModel(IStructuredSelection ss) {		

		Set<EObject> modelSubtrees = new HashSet<EObject>();
		for (Object object : ss.toArray()){

			if (object instanceof EObject){
				modelSubtrees.add((EObject)object);
			}
		}
		
		metricFraction = calculateMetric(modelSubtrees);
		
		setPie();
	}

	
	abstract public Fraction calculateMetric(Set<EObject> modelSubtrees);
	
	
	private void setPie() {

		// Set the proportions and the labels for the model
		List<Double> proportions = new ArrayList<Double>();
		List<String> labels = new ArrayList<String>();
		List<Color> colors = new ArrayList<Color>();
		List<String> toolTipTexts = new ArrayList<String>();
		
		if (metricFraction != null) { 
			DecimalFormat df = new DecimalFormat("#.##");

			double okProportion = metricFraction.doubleValue() * 100;

			proportions.add(okProportion);
			labels.add(greenPiePieceDescription + " (" + metricFraction.getNumerator() + "), " + df.format(okProportion) + "%");
			colors.add(ChartColor.GREEN.toColor());
			toolTipTexts.add(greenLabeltoolTipText);
			
			proportions.add(100.0 - okProportion);
			labels.add(redPiePieceDescription + " (" + (metricFraction.getDenominator() - metricFraction.getNumerator()) + 
					"), " + df.format(100.0 - okProportion) + "%");
			colors.add(ChartColor.RED.toColor());
			toolTipTexts.add("");

			pieModel = new PieChartModel(proportions, labels, colors, toolTipTexts);
			viewer.setContents(pieModel);

		} else {
			// No requirements were found, draw nothing 
			System.out.println(noDenominatorFoundDescription);
			viewer.setContents(null);
		}
	}
	
	
}