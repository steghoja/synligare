package org.eclipse.eatop.app.semcon.allocationassistant.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.eatop.app.semcon.allocationassistant.editor.AllocationFormEditor;
import org.eclipse.eatop.app.semcon.allocationassistant.sections.AllocationContentsTreeSection;
import org.eclipse.eatop.app.semcon.allocationassistant.sections.AppearanceExampleColumnLabelProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sphinx.emf.editors.forms.layouts.LayoutFactory;
import org.eclipse.sphinx.emf.editors.forms.pages.GenericContentsTreePage;
import org.eclipse.sphinx.emf.util.EObjectUtil;
import org.eclipse.sphinx.emf.util.EcorePlatformUtil;
import org.eclipse.sphinx.emf.util.WorkspaceEditingDomainUtil;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Identifiable;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.Satisfy;
import org.eclipse.eatop.eastadl21.Dependability;
import org.eclipse.eatop.eastadl21.Satisfy_satisfiedBy;
import org.eclipse.eatop.eastadl21.RequirementsModel;
import org.eclipse.eatop.eastadl21.util.Eastadl21ResourceImpl;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * This is similar to EastADLContentsTreePage from {@link org.eclipse.eatop.examples <em>EastADL plugin</em>},
 * but it contains 2 {@link org.eclipse.eatop.app.semcon.allocationassistant.sections.AllocationContentsTreeSection <em>sections</em>}, therefore
 * 2 {@link org.eclipse.jface.viewers.TreeViewer <em>TreeViewers</em>}.
 *
 */
public class AllocationContentsTreePage extends GenericContentsTreePage{
	private AllocationContentsTreePage currentPage;

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
		Composite body = managedForm.getForm().getBody();
		body.setLayout(LayoutFactory.createFormBodyGridLayout(false, 3));

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
					Object root = EcorePlatformUtil.loadModelRoot(iFile);
					dropRequirements.setText(root.toString());

					//Create REQUIREMENTS tree viewer
					StructuredViewer reqViewer = loadModelsContent(managedForm, body, root, "REQUIREMENTS: "+iFile.getName());
					selectRequirementElements((TreeViewer) reqViewer);


					//Add ARROW BUTTON for ALLOCATION in between the tree vewers
					Button allocateButton = new Button(body, SWT.ARROW|SWT.RIGHT|SWT.CENTER);
					allocateButton.setSize(10, 10);


					//Create MODEL tree viewer
					StructuredViewer modelViewer;
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
					allocateButton.addListener(SWT.Selection, allocationListener);

					managedForm.refresh();
					dropRequirements.dispose();
				}else
					dropRequirements.setText("Drop here REQUIREMENTS model in .eaxml file, NOT "+iFile.getFileExtension());

			}
		});


	}
	
	protected IFile getIFileFromFile(String absolutePath){

		IWorkspace workspace= ResourcesPlugin.getWorkspace(); 
		IPath location= Path.fromOSString(absolutePath); 
		IFile iFile= workspace.getRoot().getFileForLocation(location); 
		return iFile;

	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.sphinx.emf.editors.forms.pages.AbstractFormPage#getLabelProvider()
	 */
	@Override
	public ILabelProvider getLabelProvider() {
		return new AppearanceExampleColumnLabelProvider((AllocationFormEditor) getEditor());
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
