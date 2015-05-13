package org.eclipse.eatop.volvo.fmusim.popup.actions;






import java.util.List;

import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.eastadl21.VVLog;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.volvo.fmusim.ExtStyledText;
import org.eclipse.eatop.volvo.fmusim.FMUSimEngine;
import org.eclipse.eatop.volvo.fmusim.StyleRangeXML;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.VVLogTextXML;
import org.eclipse.eatop.volvo.fmusim.VVLogTextXMLManager;
import org.eclipse.eatop.volvo.fmusim.visualization.FMUSimLogOffLineDialog;
import org.eclipse.eatop.volvo.fmusim.visualization.FMUSimResultDialog;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;

public class ViewFMUsimLogOfflineAction implements IObjectActionDelegate {

	private Shell shell;
	private IWorkbenchPage page;
	
	/**
	 * Constructor for Action1.
	 */
	public ViewFMUsimLogOfflineAction() {
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
	 * Callback for User selecting "View Log" on a VVLog node.
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
		
		 //is it a Requirement node?
		 if (!(selectedElement instanceof VVLog))
		 {
			 Utils.showInformationMessage("Please select a VVLog element.");
	 		 return;
			 
		 }
		 VVLog vvLog = (VVLog)selectedElement;
	     
 	    String title  = "FMU Simulation Results in VVLog " + vvLog.getShortName();
 	    VVLogTextXML vvLogText = VVLogTextXMLManager.getInstance().GetVVLogTextXML(vvLog);
 	    String sPlainText = vvLogText.getLogText();
 	    List<StyleRangeXML> styles = vvLogText.getStyleRangeXMLs();

 	    String simulinkInputfilename = vvLogText.getGenModelScriptFilename();
 	    String simulinkOutputFilename = vvLogText.getSimulationResultFilename();
 	    
 	    FMUSimLogOffLineDialog d = new FMUSimLogOffLineDialog(Utils.getShell(), title, sPlainText, simulinkInputfilename, simulinkOutputFilename, styles);
		d.open();

	}


	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
