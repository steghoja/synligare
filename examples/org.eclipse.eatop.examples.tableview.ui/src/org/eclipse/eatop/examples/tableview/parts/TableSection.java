package org.eclipse.eatop.examples.tableview.parts;


import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.eatop.examples.tableview.core.Table;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;



public class TableSection extends AbstractPropertySection  {

	public static final String ID = "org.eclipse.eatop.examples.tableview.parts.TableSection";
	private Table table;

	public TableSection() {
	}

	@Override
	public void dispose() {
		if (table != null)
			table.dispose();
		super.dispose();
	}

	Composite parent = null;

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		this.parent = parent;
		table = new Table(parent);
		super.createControls(parent, aTabbedPropertySheetPage);
	}


	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		ArrayList<EObject> data = new ArrayList<EObject>();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			for (Iterator<?> iterator = ss.iterator(); iterator.hasNext();) {
				Object o = iterator.next();
				if (o instanceof EObject) {
					EObject eo = (EObject) o;
					data.add(eo);
				}
//				if (o instanceof IEObjectContainer) {
//					for (Object obj : (((IEObjectContainer) o).getObjects())) {
//						if (obj instanceof EObject) data.add((EObject) obj);
//					}
//				}
			}
		}
		table.updateTable(data, true);
		super.setInput(part, selection);
	}

	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	@Override
	public void refresh() {
		table.refresh(true);
	}


}

