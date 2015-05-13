package org.eclipse.eatop.volvo.fmusim.popup.actions;




import java.awt.geom.IllegalPathStateException;
import java.util.List;

import org.eclipse.eatop.common.resource.EastADLURIFactory;
import org.eclipse.eatop.eastadl21.DesignFunctionType;
import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.VVCase;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.eastadl21.VVStimuli;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sphinx.emf.util.EcorePlatformUtil;
import org.eclipse.swt.internal.win32.MENUBARINFO;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.eatop.volvo.fmusim.FMUSimEngine;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelProcessor;
import org.eclipse.eatop.volvo.fmusim.taskbuilder.FMUTaskBuilder;
import org.eclipse.eatop.volvo.fmusim.userconfig.FMUSimulationConfigDialog;
import org.eclipse.eatop.volvo.fmusim.userconfig.VVStimuliDataManager;
import org.eclipse.jface.window.Window;
import org.eclipse.emf.common.util.URI;

public class FmuSimulationAction implements IObjectActionDelegate {

	private Shell shell;
	private IWorkbenchPage page;
	
	/**
	 * Constructor for Action1.
	 */
	public FmuSimulationAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	    page = targetPart.getSite().getPage();   	    
	    Utils.setShell(shell);
	 
	    
	}

	/**
	 * Callback for User selecting "FMU Simulation..." on a VVProcedure node.
	 * 
	 * @see IActionDelegate#run(IAction)
	 *  
	 */
	public void run(IAction action) {

 		//Obtain user node selection
		ISelection myselec= page.getSelection();
        TreeSelection myselecTree= (TreeSelection) myselec;
	    TreePath[] selections = myselecTree.getPaths();

	  
	    

	    
	    //make sure user selected only one node
	     if (selections.length != 1) 
	     {
	    	 //this should not occur since we have EnabledFor = 1 but who knows
	    	 MessageDialog.openInformation(shell, "Fmusim", "Please select only one element...");
	 		return;
	     }
      
	     EObject selectedElement = (EObject)selections[0].getLastSegment();
		
		 //is it a VVProcedure node?
		 if (!(selectedElement instanceof VVProcedure))
		 {
	    	 //this should not occur since we have required a VVProcedure in objectClass but who knows...
			 Utils.showInformationMessage("Please select a VVProcedure element.");
	 		 return;
			 
		 }
		 VVProcedure vp = (VVProcedure)selectedElement;
	     Utils.setDateTime();

	     
		 FMUSimEngine engine = new FMUSimEngine();
		 engine.Go(vp);

	}


	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
