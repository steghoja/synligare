package examples;

import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.volvo.modelprocessor.TreeModelProcessor;
import org.eclipse.eatop.volvo.visualizer.common.Activator;
import org.eclipse.eatop.volvo.visualizer.common.AutoTaggingHandler;
import org.eclipse.eatop.volvo.visualizer.common.VisualTagsHandler;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

public class AutoTaggingAction implements IObjectActionDelegate {

	private Shell shell;
	private ISelection selection;
	private EAXML root;

	//EAPackage tagsPackage, taggedElementsPackage, typesPackage;

	private String tagsPackageName = "";


	@Override
	public void run(IAction action) {
		tagsPackageName = VisualTagsHandler.TAGS_PACKAGE_NAME; 
		IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  

		selection = page.getSelection();	

		root = TreeModelProcessor.findRoot(selection);

		// Get the file name. 
		// This is used as a suffix in the tags package name.
		// Because of the way the elements are found problems may occur if the tags packages
		// has the same name (the same path). Elements from different east-adl models might 
		// be mixed up.
		String name = TreeModelProcessor.findFileName(selection, page);
		
		//Get the EditingDomain for the first element of the selected EObjects.
		EditingDomain ed = AdapterFactoryEditingDomain.getEditingDomainFor(root);
		if(ed == null){
			// TODO exception
			System.out.println("Cannot create EditingDomain for object " + root);
			return;
		}

		// TODO remove white space from name
		tagsPackageName += "_"+name;

		AutoTaggingHandler handler = new AutoTaggingHandler();
		handler.createAutoTags(ed, root, tagsPackageName);
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();		
	}

}
