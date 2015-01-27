package org.eclipse.eatop.connectorcreator.popup.actions;

import org.eclipse.eatop.connectorcreator.Activator;
import org.eclipse.eatop.connectorcreator.dialog.ConnectorDialog;
import org.eclipse.eatop.connectorcreator.swcomponents.SwComponentPrototypeInterface;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class NewAction implements IObjectActionDelegate {
	private Shell shell;
	
	/**
	 * Constructor for Action1.
	 */
	public NewAction() {
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
		ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			for (Object element : ss.toList()) {
				SwComponentPrototypeInterface result = (SwComponentPrototypeInterface) Activator.getAdapter(element, SwComponentPrototypeInterface.class, true);
				if (result.getPorts().size() < 1) {
					MessageBox messageBox = new MessageBox(shell,SWT.ICON_INFORMATION | SWT.OK); 
					messageBox.setText("Error");
					messageBox.setMessage("Selected component contains no ports");
					messageBox.open();
				} else {
					if (result.getErrorConnectors().size() > 0) {
						MessageBox messageBox = new MessageBox(shell,SWT.ICON_INFORMATION | SWT.OK); 
						messageBox.setText("Error");
						String stringResult = "There is something missing in the following (" + result.getErrorConnectors().size() + ") Connectors:\n\n";
						for (String string : result.getErrorConnectors()) {
							stringResult += string + "\n";
						}
						stringResult += "\nThese Connectors won't be taken into account in the presented dialog.";
						messageBox.setMessage(stringResult);
						messageBox.open();
					}
					new ConnectorDialog(shell, result);
				}
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {}

}
