package org.eclipse.eatop.volvo.fmusim.popup.actions;






import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.eatop.eastadl21.EAElement;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.VVLog;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.volvo.fmusim.FMUSimEngine;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.visualization.FMUSimResultDialog;
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
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

public class ViewFMUSimulationResultAction implements IObjectActionDelegate {

	private Shell shell;
	private IWorkbenchPage page;
	
	/**
	 * Constructor for Action1.
	 */
	public ViewFMUSimulationResultAction() {
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
  	     org.eclipse.eatop.volvo.fmusim.visualization.Highlighter highlighter = org.eclipse.eatop.volvo.fmusim.visualization.Highlighter.getInstance();
		
	     String title = "";
	     if (action.getId().equals("org.eclipse.eatop.fmusim.viewfmusimulationresultforrequirementaction")){
			 if (!(selectedElement instanceof Requirement)){
		    	 //this should not occur since we have required a VVProcedure in objectClass but who knows...
				 Utils.showInformationMessage("Please select a Requirement element.");
		 		 return;
			 }
			 Requirement R = (Requirement)selectedElement;
	 	     title  = "Highlighted FMU Simulation Results for Requirement " + R.getShortName();
		 	 FMUSimResultDialog<VVLog> d = new FMUSimResultDialog<VVLog>(Utils.getShell(), title, highlighter.getPassedVVLogs(R), highlighter.GetFailedVVLogs(R));
		 	 d.open();
	     }

	     else if (action.getId().equals("org.eclipse.eatop.fmusim.viewfmusimulationresultforfunctionflowportaction") ||
	    		  action.getId().equals("org.eclipse.eatop.fmusim.viewfmusimulationresultfordesignfunctiontypeaction") ||
	    		  action.getId().equals("org.eclipse.eatop.fmusim.viewfmusimulationresultfordesignfunctionprototypeaction"))
	  	 	    		 
	    		 {
	    	 EAElement ea = (EAElement)selectedElement;
	 	     title  = "Highlighted FMU Simulation Results for " + ea.eClass().getName() + " " + ea.getShortName();

	 	     Map<Requirement, Boolean> reqstatusMap = highlighter.getRequirementStatus(ea);
	 	     
 	     
	 	     List<Requirement> passedReq = new ArrayList<Requirement>();
	 	     List<Requirement> failedReq = new ArrayList<Requirement>();
	 	     
	 	     //convert Map to 2 individual lists passed & failed 
	 	     for (java.util.Map.Entry<Requirement, Boolean> entry : reqstatusMap.entrySet()){
	 	    	 Requirement r = entry.getKey();
	 	    	 boolean bVerStatus = entry.getValue();
	 	        if (bVerStatus){
	 	        	passedReq.add(r);
	 	        }
	 	        else{
	 	        	failedReq.add(r);
	 	        }
	 	     }
	 	     
	 	     
	 	     
	 	     FMUSimResultDialog<Requirement> d = new FMUSimResultDialog<Requirement>(Utils.getShell(), title, passedReq, failedReq);
		 	 d.open();
	     
	     }
	    	 		


		 
		

	}


	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
