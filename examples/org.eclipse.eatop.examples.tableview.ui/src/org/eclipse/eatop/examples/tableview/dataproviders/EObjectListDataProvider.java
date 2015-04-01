package org.eclipse.eatop.examples.tableview.dataproviders;

import java.util.List;

import org.eclipse.eatop.examples.tableview.accessors.IEObjectAccessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;

public class EObjectListDataProvider extends ListDataProvider<EObject> {

	public EObjectListDataProvider(List<EObject> list, IEObjectAccessor columnAccessor) {
		super(list, columnAccessor);
	}
	
	public void setColumnAccessor(IEObjectAccessor columnAccessor) {
		this.columnAccessor = columnAccessor;
	}
}
