package org.eclipse.eatop.volvo.metrics.popup.actions;

import org.eclipse.eatop.eastadl21.Requirement;
import org.eclipse.eatop.volvo.metrics.Utils;
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
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class ShowMetricsViewAction implements IObjectActionDelegate {


		private Shell shell;
		private IWorkbenchPage page;
		
		/**
		 * Constructor for Action1.
		 */
		public ShowMetricsViewAction() {
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

			String viewId = "";
			if (action.getId().equals("org.eclipse.eatop.volvo.metrics.showallocatedrequirementsaction")){
				viewId = "org.eclipse.eatop.volvo.metrics.AllocatedRequirementsView";
			}
			else if (action.getId().equals("org.eclipse.eatop.volvo.metrics.showverifiedrequirementsaction")){
				viewId = "org.eclipse.eatop.volvo.metrics.VerifiedRequirementsView";
			}
			else if (action.getId().equals("org.eclipse.eatop.volvo.metrics.showrealizedfuturesaction")){
				viewId = "org.eclipse.eatop.volvo.metrics.RealizedFeaturesView";
			}
			else if (action.getId().equals("org.eclipse.eatop.volvo.metrics.showsgwithfscaction")){
				viewId = "org.eclipse.eatop.volvo.metrics.SGwithFSCView";
			}
			else if (action.getId().equals("org.eclipse.eatop.volvo.metrics.showfscwithtscaction")){
				viewId = "org.eclipse.eatop.volvo.metrics.FSCwithTSCView";
			}
			
			if (viewId.isEmpty()){
				Utils.showErrorMessage("Unknown action " + action.getId());
				return ;
			}
			
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(viewId);
			} catch (PartInitException e) {
				Utils.showErrorMessage("Failed to show view " + viewId);
				return;
			}
		
		}


		/**
		 * @see IActionDelegate#selectionChanged(IAction, ISelection)
		 */
		public void selectionChanged(IAction action, ISelection selection) {
		}
		

}