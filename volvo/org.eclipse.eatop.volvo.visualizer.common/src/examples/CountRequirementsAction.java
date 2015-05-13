/*package examples;

import java.util.List;

import javax.swing.JOptionPane;

import modelprocessor.ModelProcessor;

import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;



public class CountRequirementsAction implements IObjectActionDelegate {
	
	private Shell shell;
	public static EAXML root = null;
	
	int numberOfReqs = 0;
	
	public CountRequirementsAction() {
		super();
	}
	
	@Override
	public void run(IAction action) {
		IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
		
	    ISelection selec= page.getSelection();
	    
	    TreeSelection treeSelec= (TreeSelection) selec;
	    TreePath[] selections = treeSelec.getPaths();
	    
	    
	    // Get the element selected in the model
	 	EObject selectedElement = (EObject)selections[0].getLastSegment();
	    
		if (selections.length == 0 || !(selectedElement instanceof EAPackage)) { 
			JOptionPane.showMessageDialog(null, "You have not selected a package. (Do it)");
		} else {
			
			countReqs(selectedElement);
		} 
		
	}
	
	private void countReqs(EObject selectedElement) {
				
		
		
		EAPackage topLevelPackage = root.getTopLevelPackage().get(0);
		
		// Find all requirements in model
		List<Requirement> requirementSpecObjList = modelProcessor.findRequirementsInModel(topLevelPackage);

	if (selectedElement instanceof EAPackage) {
			List<Requirement> reqs = ModelProcessor.findAllRequirements((EAElement)selectedElement);
			JOptionPane.showMessageDialog(null, "Number of requirements: " + reqs.size());
			
			try {
				SatisfiedRequirementsView view = (SatisfiedRequirementsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().
						getActivePage().showView("visualizer.views.SatisfiedRequirementsView", 
								"1", IWorkbenchPage.VIEW_ACTIVATE);
				
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
	}
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();		
	}

}*/
