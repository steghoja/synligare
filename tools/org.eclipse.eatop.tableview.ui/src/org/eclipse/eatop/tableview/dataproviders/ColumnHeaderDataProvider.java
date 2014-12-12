package org.eclipse.eatop.tableview.dataproviders;

import org.eclipse.eatop.tableview.accessors.IEObjectAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;

public class ColumnHeaderDataProvider implements IDataProvider {

	IEObjectAccessor accessor;

	public ColumnHeaderDataProvider(IEObjectAccessor accessor) {
		setColumnAccessor(accessor);
	}

	public void setColumnAccessor(IEObjectAccessor accessor) {
		this.accessor = accessor;
	}

	@Override
	public Object getDataValue(int columnIndex, int rowIndex) {
		return accessor.getColumnHeaderValue(columnIndex);
	}

	@Override
	public void setDataValue(int columnIndex, int rowIndex, Object newValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getColumnCount() {
		return accessor.getColumnCount();
	}

	@Override
	public int getRowCount() {
		return 1;
	}
}