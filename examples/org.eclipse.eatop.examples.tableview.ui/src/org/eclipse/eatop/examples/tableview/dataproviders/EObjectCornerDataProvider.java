package org.eclipse.eatop.examples.tableview.dataproviders;

import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultCornerDataProvider;

public class EObjectCornerDataProvider extends DefaultCornerDataProvider {
	EObjectListDataProvider dataProvider;
	public EObjectCornerDataProvider(IDataProvider columnHeaderDataProvider, IDataProvider rowHeaderDataProvider, EObjectListDataProvider dataProvider) {
		super(columnHeaderDataProvider, rowHeaderDataProvider);
		this.dataProvider = dataProvider;
	}
	
	@Override
	public Object getDataValue(int columnIndex, int rowIndex) {
		if (!dataProvider.getList().isEmpty()) {
			return dataProvider.getRowObject(0).eClass().getName();
		}
		return "";
	}
}
