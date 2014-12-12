package org.eclipse.eatop.tableview.dataproviders;

import org.eclipse.eatop.tableview.accessors.IEObjectAccessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;

public class EObjectAccessorReferenceDataProvider implements IDataProvider{
	IEObjectAccessor accessor;
	EObjectListDataProvider dataProvider;
	
	public EObjectAccessorReferenceDataProvider(IEObjectAccessor accessor, EObjectListDataProvider dataProvider) {
		this.accessor = accessor;
		this.dataProvider = dataProvider;
	}
	
	public void setEObjectAccessorReferenceAccessor(IEObjectAccessor accessor) {
		this.accessor = accessor;
	}
	
	public EObject getEObject(int rowIndex) {
		return dataProvider.getRowObject(rowIndex);
	}
	@Override
	public Object getDataValue(int columnIndex, int rowIndex) {
		EObject rowObject = dataProvider.getRowObject(rowIndex);
		return accessor.getDataValue(rowObject, columnIndex);
	}

	@Override
	public void setDataValue(int columnIndex, int rowIndex, Object newValue) {
		EObject rowObject = dataProvider.getRowObject(rowIndex);
		accessor.setDataValue(rowObject, columnIndex, newValue);	
	}

	@Override
	public int getColumnCount() {
		return dataProvider.getColumnCount();
	}

	@Override
	public int getRowCount() {
		return dataProvider.getRowCount();
	}
	
	public EObject getReferenceType(int columnIndex) {
		return accessor.getHeldData(columnIndex);
	}
	
}