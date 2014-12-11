package org.eclipse.eatop.examples.explorer;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class JumpToAction extends Action {

	private EObject target;

	public JumpToAction(EObject target) {
		this.target = target;
	}

	public JumpToAction(EObject target, String text) {
		super(text);
		this.target = target;
	}

	@Override
	public void run() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IViewerProvider viewer = (IViewerProvider) page.showView("org.eclipse.eatop.examples.explorer.views.eastadlExplorer"); //$NON-NLS-1$
			viewer.getViewer().setData("remembered_selection", viewer.getViewer().getSelection()); //$NON-NLS-1$
			viewer.getViewer().setSelection(new StructuredSelection(target));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
