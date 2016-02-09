package org.eclipse.eatop.app.semcon.allocationassistant.pages;


import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.eatop.app.semcon.allocationassistant.allocationsuggestions.AllocationSuggestionsAction;
import org.eclipse.eatop.app.semcon.allocationassistant.allocationsuggestions.SuggestionsFilter;
import org.eclipse.eatop.app.semcon.allocationassistant.editor.AllocationFormEditor;
import org.eclipse.eatop.app.semcon.allocationassistant.sections.AllocationContentsTreeSection;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sphinx.emf.editors.forms.layouts.LayoutFactory;
import org.eclipse.sphinx.emf.editors.forms.pages.GenericContentsTreePage;
import org.eclipse.sphinx.emf.util.EcorePlatformUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.eatop.eastadl21.util.Eastadl21ResourceImpl;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * This is similar to EastADLContentsTreePage from {@link org.eclipse.eatop.examples <em>EastADL plugin</em>},
 * but it contains 2 {@link org.eclipse.eatop.app.semcon.allocationassistant.sections.AllocationContentsTreeSection <em>sections</em>}, therefore
 * 2 {@link org.eclipse.jface.viewers.TreeViewer <em>TreeViewers</em>}.
 *
 */
public class AllocationContentsTreePage extends GenericContentsTreePage{
	private AllocationContentsTreePage currentPage;
	private final String showHints = "Hints!";
	private final String removeHints = "Remove\n Hints";
	//private ToolTip suggestionTip = null;
	//private ToolTip allocationTip = null;

	public AllocationContentsTreePage(AllocationFormEditor formEditor) {
		super(formEditor);
		currentPage = this;
		// TODO Auto-generated constructor stub

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.sphinx.emf.editors.forms.pages.GenericContentsTreePage#doCreateFormContent(org.eclipse.ui.forms.
	 * IManagedForm)
	 */
	@Override
	protected void doCreateFormContent(final IManagedForm managedForm) {
		
		// Create 3-columned page layout, for requirements treeview, allocation button 
		// and model treeview
		final Composite body = managedForm.getForm().getBody();
		body.setLayout(LayoutFactory.createFormBodyGridLayout(false, 3));
		
		/*suggestionTip = new ToolTip(body.getShell(), SWT.BALLOON | SWT.ICON_INFORMATION);
		suggestionTip.setMessage("The suggestions will be shown in the model tree view based on relationships already existent on higher level. "
				+ "If no suggestions are found, the model tree view will not change.");
		suggestionTip.setText("Hint! - Select a requirement in the requirements tree view and press Hint! ");
		allocationTip = new ToolTip(body.getShell(), SWT.BALLOON | SWT.ICON_INFORMATION);
		allocationTip.setText("Allocate - select at least one requirement and one model element and press Allocate");
		allocationTip.setMessage("Select at least one requirement in the requirements tree view and at least one model element in the "
				+ "model tree view, then press Allocate. This will create a Satisfy relationship in the requirements tree view."
				+ "If the selected elements are incorrect (e.g. no requirement is selected on the left side) the allocation will not be performed.");
		*/
		
		// Create the text field
		final Label dropRequirements = new Label(body, SWT.SHADOW_IN|SWT.CENTER);
		dropRequirements.setBounds(10, 10, 100, 100);
		dropRequirements.setText("Drop \n Requirements file \n HERE!");
		DropTarget dt = new DropTarget(dropRequirements, DND.DROP_MOVE);

	

		dt.setTransfer(new Transfer[] {FileTransfer.getInstance()});
		dt.addDropListener(new DropTargetAdapter() {
			public void drop(DropTargetEvent event) {

				String[] filesDroped = (String[]) event.data;
				IFile iFile = getIFileFromFile( (String) filesDroped[0]);
				String iFileExtension = iFile.getFileExtension();
				if (iFileExtension!=null && iFileExtension.equals("eaxml")){
					final Object root = EcorePlatformUtil.loadModelRoot(iFile);
					dropRequirements.setText(root.toString());

					//Create REQUIREMENTS tree viewer
					final StructuredViewer reqViewer = loadModelsContent(managedForm, body, root, "REQUIREMENTS: "+iFile.getName());
					selectRequirementElements((TreeViewer) reqViewer);
					
					//create 
					Composite middleContainer = new Composite(body, SWT.TOP|SWT.NO_BACKGROUND);
					middleContainer.setLayout(new GridLayout(1, false));
					Button suggestionsButton = new Button(middleContainer,SWT.NULL);
					suggestionsButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
					suggestionsButton.setText("Hints!");
					Button removeSuggestionsButton = new Button(middleContainer,SWT.NULL);
					removeSuggestionsButton.setText("Remove Hints");
					removeSuggestionsButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
					Button allocateButton = new Button(middleContainer, SWT.NULL);
					allocateButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
					allocateButton.setText("Allocate");


					//Create MODEL tree viewer
					final StructuredViewer modelViewer;
					if (pageInput instanceof Eastadl21ResourceImpl){
						modelViewer = loadModelsContent(managedForm, body, pageInput, "MODEL: "+((Eastadl21ResourceImpl)pageInput).getURI().lastSegment());
					} else
						modelViewer = loadModelsContent(managedForm, body, pageInput, "MODEL: "+((EObject)pageInput).eResource().getURI().lastSegment());

					//Create a listener for the ALLOCATION BUTTON
					Listener allocationListener = new Listener() {
						@Override
						public void handleEvent(Event event) {

							AllocationFunctionality.allocateRequirements(root, reqViewer,modelViewer);

						}

					};
					
					
					/*
					Listener allocationTipListener = new Listener() {
						@Override
						public void handleEvent(Event event) {
								allocationTip.setVisible(true);
						}

					};
					
					allocateButton.addListener(SWT.MouseHover, allocationTipListener);*/
					
					allocateButton.addListener(SWT.Selection, allocationListener);
					
					
					/*Listener suggestionsTipListener = new Listener() {
						@Override
						public void handleEvent(Event event) {
								suggestionTip.setVisible(true);
						}

					};*/
					
					Listener suggestionsListener = new Listener() {
						@Override
						public void handleEvent(Event event) {
							boolean suggestionsSelected = new AllocationSuggestionsAction(reqViewer, modelViewer).selectSuggestions();
							//if (suggestionsSelected)
								//((TreeViewer) modelViewer).expandAll();
						}

					};
					Listener removeSuggestionsListener = new Listener() {
						@Override
						public void handleEvent(Event event) {
							
							removeSuggestionsFilter(modelViewer);
						}

					};
					
					suggestionsButton.addListener(SWT.Selection, suggestionsListener);
					//suggestionsButton.addListener(SWT.MouseHover, suggestionsTipListener);
					removeSuggestionsButton.addListener(SWT.Selection, removeSuggestionsListener);
					managedForm.refresh();
					dropRequirements.dispose();
				}else
					dropRequirements.setText("Drop here REQUIREMENTS model in .eaxml file, NOT "+iFile.getFileExtension());

			}
		});


	}
	
	private void removeSuggestionsFilter(StructuredViewer viewer){
		ViewerFilter[] filterArray = viewer.getFilters();
		ArrayList<ViewerFilter> newFilterList = new ArrayList<ViewerFilter>();
		for (ViewerFilter vf : filterArray){
			if (!(vf instanceof SuggestionsFilter))
				newFilterList.add(vf);
		}
		viewer.resetFilters();
		viewer.setFilters( newFilterList.toArray(new ViewerFilter[newFilterList.size()]));
		viewer.refresh();
	}
	
	protected IFile getIFileFromFile(String absolutePath){

		IWorkspace workspace= ResourcesPlugin.getWorkspace(); 
		IPath location= Path.fromOSString(absolutePath); 
		IFile iFile= workspace.getRoot().getFileForLocation(location); 
		return iFile;

	}
	
	protected StructuredViewer loadModelsContent(final IManagedForm managedForm, Composite body, Object input, String name){
		AllocationContentsTreeSection acts = new AllocationContentsTreeSection(currentPage, input);
		acts.setTitle(name);
		acts.createContent(managedForm, body);
		StructuredViewer viewer = (StructuredViewer) acts.getViewer();
		addSection(acts);
		contentsTreeSection = acts;
		return viewer;

	}

	protected void selectRequirementElements(TreeViewer reqViewer){
		/*Add Filter to remove all non-requirement related elements
		It has to be discussed what elements to keep: requirements, use cases, 
		requirements relationships (Satisfy, Derive...)
		 */
	}

	
}
