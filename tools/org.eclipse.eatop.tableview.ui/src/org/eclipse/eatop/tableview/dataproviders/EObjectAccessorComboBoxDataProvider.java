package org.eclipse.eatop.tableview.dataproviders;

import java.util.List;

import org.eclipse.eatop.tableview.accessors.IEObjectAccessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.edit.editor.IComboBoxDataProvider;

public class EObjectAccessorComboBoxDataProvider implements IComboBoxDataProvider {

	EObjectListDataProvider dataProvider;
	IEObjectAccessor columnAccessor;

	public EObjectAccessorComboBoxDataProvider(EObjectListDataProvider dataProvider, IEObjectAccessor columnAccessor) {
		this.dataProvider = dataProvider;
		this.columnAccessor = columnAccessor;
	}

	public void setEObjectAccessor(IEObjectAccessor columnAccessor) {
		this.columnAccessor = columnAccessor;
	}

	@Override
	public List<?> getValues(int columnIndex, int rowIndex) {
		EObject rowObject = dataProvider.getRowObject(rowIndex);
		return columnAccessor.getComboBoxData(rowObject, columnIndex);
	}
}