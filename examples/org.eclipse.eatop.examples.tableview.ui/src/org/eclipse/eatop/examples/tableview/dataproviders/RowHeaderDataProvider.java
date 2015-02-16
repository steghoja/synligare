package org.eclipse.eatop.examples.tableview.dataproviders;

import org.eclipse.eatop.examples.tableview.accessors.IEObjectAccessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;


public class RowHeaderDataProvider implements IDataProvider {

	EObjectListDataProvider dataProvider;
	IEObjectAccessor accessor;

	public RowHeaderDataProvider(EObjectListDataProvider dataProvider, IEObjectAccessor eoObjectAccessor) {
		this.dataProvider = dataProvider;
		this.accessor = eoObjectAccessor;
	}

	public void setColumnAccessor(IEObjectAccessor accessor) {
		this.accessor = accessor;
	}

	public int getColumnCount() {
		return 1;
	}

	public int getRowCount() {
		return dataProvider.getRowCount();
	}

	public Object getDataValue(int columnIndex, int rowIndex) {
		EObject rowObject = dataProvider.getRowObject(rowIndex);
		return accessor.getRowHeaderValue(rowObject);
	}

	public void setDataValue(int columnIndex, int rowIndex, Object newValue) {
		throw new UnsupportedOperationException();
	}

}