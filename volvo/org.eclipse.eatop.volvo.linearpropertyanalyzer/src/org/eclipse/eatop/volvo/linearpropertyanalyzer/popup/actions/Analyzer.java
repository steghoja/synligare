package org.eclipse.eatop.volvo.linearpropertyanalyzer.popup.actions;

import javax.swing.JOptionPane;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.Activator;
import org.eclipse.eatop.volvo.linearpropertyanalyzer.controllers.FlowController;

public class Analyzer implements IObjectActionDelegate {

	private Shell shell;
	public static EAXML rootEAXML = null;
	public static EAPackage rootEAPackage = null;
	
	/**
	 * Constructor for Action1.
	 */
	public Analyzer() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
			IWorkbenchPage page= Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();  
			
		     ISelection myselec= page.getSelection();
		     
		     
		     TreeSelection myselecTree= (TreeSelection) myselec;
		     TreePath[] selections = myselecTree.getPaths();
		     rootEAXML=(EAXML) selections[0].getSegment(2);
		     rootEAPackage=(EAPackage) selections[0].getSegment(3);
			StringBuilder message = new StringBuilder();
			if (selections.length == 0) {
				message.append("You have not made a selection.");
			} else if (selections.length == 1) {
				
				//Gets the element selected in the model
				EObject selectedElement = (EObject)selections[0].getLastSegment();
				
				//Calls FlowController with the selected element in order to start the analysis.
				try {
					FlowController.INSTANCE.startAnalysis(selectedElement);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please select only one element!");
			}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
