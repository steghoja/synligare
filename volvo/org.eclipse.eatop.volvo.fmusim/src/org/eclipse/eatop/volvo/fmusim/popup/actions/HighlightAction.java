package org.eclipse.eatop.volvo.fmusim.popup.actions;


import org.eclipse.eatop.eastadl21.VVCase;
import org.eclipse.eatop.eastadl21.VVLog;
import org.eclipse.eatop.eastadl21.VVProcedure;
import org.eclipse.eatop.eastadl21.VerificationValidation;
import org.eclipse.eatop.volvo.fmusim.Utils;
import org.eclipse.eatop.volvo.fmusim.ecore.ModelUpdate;
import org.eclipse.eatop.volvo.fmusim.visualization.Highlighter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;


public class HighlightAction implements IObjectActionDelegate {

	private Shell shell;
	private IWorkbenchPage page;
	
	/**
	 * Constructor for Action1.
	 */
	public HighlightAction() {
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
	 * Callback for User selecting "Highlight..." on a VVLog/VVCase node.
	 * 
	 * @see IActionDelegate#run(IAction)
	 *  
	 */
	public void run(IAction action) {

 		//Obtain user node selection
		ISelection myselec= page.getSelection();
        TreeSelection myselecTree= (TreeSelection) myselec;
	    TreePath[] selections = myselecTree.getPaths();

	    EObject selectedElement = (EObject)selections[0].getLastSegment();
	    
	    if (selectedElement instanceof VVLog)
		 {
	    	 
			 VVLog vvLog = (VVLog)selectedElement;
		
			 
			 if (action.getId().equals("org.eclipse.eatop.fmusim.highlightvvlogaction"))
			 {	 
				 Highlighter.getInstance().Highlight(vvLog);
				 Highlighter.getInstance().SaveWorkspaceHighlighting();
			 }
			 else if (action.getId().equals("org.eclipse.eatop.fmusim.unhighlightvvlogaction")){
				 Highlighter.getInstance().UnHighLight(vvLog);
				 Highlighter.getInstance().SaveWorkspaceHighlighting();

			 }
		 }
	    else if (selectedElement instanceof VVProcedure)
	    {
	    	VVProcedure vvProcedure = (VVProcedure)selectedElement;
		
			 
			 if (action.getId().equals("org.eclipse.eatop.fmusim.highlightvvprocedureaction"))
			 {	 
				 Highlighter.getInstance().Highlight(vvProcedure);
				 Highlighter.getInstance().SaveWorkspaceHighlighting();

			 }
			 else if (action.getId().equals("org.eclipse.eatop.fmusim.unhighlightvvprocedureaction")){
				 Highlighter.getInstance().UnHighlight(vvProcedure);
				 Highlighter.getInstance().SaveWorkspaceHighlighting();

			 }
	    }
	    else if (selectedElement instanceof VVCase)
	    {
	    	VVCase vvCase = (VVCase)selectedElement;
		
			 
			 if (action.getId().equals("org.eclipse.eatop.fmusim.highlightvvcaseaction"))
			 {	 
				 Highlighter.getInstance().Highlight(vvCase);
				 Highlighter.getInstance().SaveWorkspaceHighlighting();

			 }
			 else if (action.getId().equals("org.eclipse.eatop.fmusim.unhighlightvvcaseaction")){
				 Highlighter.getInstance().UnHighlight(vvCase);
				 Highlighter.getInstance().SaveWorkspaceHighlighting();

			 }
	    }
			
	    
	    else if (selectedElement instanceof VerificationValidation)
	    {
	    	VerificationValidation vv = (VerificationValidation)selectedElement;
		
			if (!vv.getShortName().equals("Analysis"))
			{
				Utils.showInformationMessage("Only supported on VerificationValidation element labeled 'Analysis'");

				//Better solution: use enablement with an IActionFilter adapter to disable menu alternative, see 
				//http://xpomul.bloggles.info/2008/07/24/using-advanced-features-in-eclipse-popup-menus/
				return;
			}
	    	
			 if (action.getId().equals("org.eclipse.eatop.fmusim.highlightverificationvalidationaction"))
			 {	 
				 Highlighter.getInstance().Highlight(vv);
				 Highlighter.getInstance().SaveWorkspaceHighlighting();

			 }
			 else if (action.getId().equals("org.eclipse.eatop.fmusim.unhighlightverificationvalidationaction")){
				 Highlighter.getInstance().UnHighlight(vv);
				 Highlighter.getInstance().SaveWorkspaceHighlighting();

			 }
	    }
	
	    	    
	    ModelUpdate modelUpdate = new ModelUpdate();
	    modelUpdate.updateDecorators();
	    
	}


	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

}
