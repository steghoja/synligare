package org.eclipse.eatop.examples.tableview.accessors.impl;

import java.util.Comparator;
import java.util.List;

import org.eclipse.eatop.examples.tableview.accessors.IEObjectAccessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;

public class EmptyEObjectAccessor implements IEObjectAccessor {

	@Override
	public Object getDataValue(EObject rowObject, int columnIndex) {
		return null;
	}

	@Override
	public void setDataValue(EObject rowObject, int columnIndex, Object newValue) {
	}

	@Override
	public int getColumnCount() {
		return 0;
	}

	@Override
	public String getColumnHeaderValue(int columnIndex) {
		return null;
	}

	@Override
	public Object getRowHeaderValue(EObject rowObject) {
		return null;
	}

	@Override
	public List<String> getConfigLabels(int columnIndex) {
		return null;
	}

	@Override
	public List<?> getComboBoxData(EObject rowObject, int columnIndex) {
		return null;
	}
	
	@Override
	public Object canonicalToDisplayValue(Object canonicalValue, int columnIndex, IConfigRegistry configRegistry) {
		return null;
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue, int columnIndex, IConfigRegistry configRegistry) {
		return null;
	}

	@Override
	public EObject getHeldData(int columnIndex) {
		return null;
	}

	@Override
	public Comparator<EObject> getComparator(int columnIndex, int modifier) {
		return null;
	}
}
