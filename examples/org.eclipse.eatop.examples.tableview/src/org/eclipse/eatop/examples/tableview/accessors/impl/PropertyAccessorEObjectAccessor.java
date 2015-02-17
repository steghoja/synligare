package org.eclipse.eatop.examples.tableview.accessors.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

import org.eclipse.eatop.examples.tableview.accessors.IEObjectAccessor;
import org.eclipse.eatop.examples.tableview.accessors.IEObjectPropertyAccessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;

/**
 * IEObjectAccessor where each column delegates to an IEObjectPropertyAccessor
 */
public class PropertyAccessorEObjectAccessor extends Observable implements IEObjectAccessor {

	List<IEObjectPropertyAccessor> accessors = new ArrayList<IEObjectPropertyAccessor>();
	IEObjectPropertyAccessor rowHeaderAccessor;
	
	public PropertyAccessorEObjectAccessor(List<IEObjectPropertyAccessor> propertyAccessors, IEObjectPropertyAccessor rowHeaderAccessor) {
		this.accessors.addAll(propertyAccessors);
		this.rowHeaderAccessor = rowHeaderAccessor;
	}
	
	@Override
	public Object getDataValue(EObject rowObject, int columnIndex) {
		IEObjectPropertyAccessor accessor = getIndexedValue(columnIndex);
		if (accessor != null) {
			return accessor.getDataValue(rowObject);
		} else {
			return null;
		}
	}
	
	@Override
	public void setDataValue(EObject rowObject, int columnIndex, Object newValue) {
		IEObjectPropertyAccessor accessor = getIndexedValue(columnIndex);
		if (accessor != null) {
			accessor.setDataValue(rowObject, newValue);
			setChanged();
		}
		notifyObservers();
		
	}

	@Override
	public int getColumnCount() {
		return accessors.size();
	}

	@Override
	public String getColumnHeaderValue(int columnIndex) {
		IEObjectPropertyAccessor accessor = getIndexedValue(columnIndex);
		if (accessor != null) {
			return accessor.getName();
		} else {
			return null;
		}
	}

	@Override
	public List<String> getConfigLabels(int columnIndex) {
		IEObjectPropertyAccessor accessor = getIndexedValue(columnIndex);
		if (accessor != null) {
			return accessor.getConfigLabels();
		} else {
			return null;
		}
	}

	@Override
	public List<?> getComboBoxData(EObject rowObject, int columnIndex) {
		IEObjectPropertyAccessor accessor = getIndexedValue(columnIndex);
		if (accessor != null) {
			return accessor.getComboBoxData(rowObject);
		} else {
			return null;
		}
	}

	@Override
	public Object getRowHeaderValue(EObject rowObject) {
		return rowHeaderAccessor.getDataValue(rowObject);
	}

	@Override
	public Object canonicalToDisplayValue(Object canonicalValue, int columnIndex, IConfigRegistry configRegistry) {
		IEObjectPropertyAccessor accessor = getIndexedValue(columnIndex);
		if (accessor != null) {
			return accessor.canonicalToDisplayValue(canonicalValue, configRegistry);
		} else {
			return null;
		}
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue, int columnIndex, IConfigRegistry configRegistry) {
		IEObjectPropertyAccessor accessor = getIndexedValue(columnIndex);
		if (accessor != null) {
			return accessor.displayToCanonicalValue(displayValue, configRegistry);
		} else {
			return null;
		}
	}
	
	public List<IEObjectPropertyAccessor> getPropertyAccessors() {
		return accessors;
	}
	
	public IEObjectPropertyAccessor getIndexedValue(int columnIndex) {
		IEObjectPropertyAccessor accessor = null;
		if (!accessors.isEmpty()) {
			accessor = accessors.get(columnIndex);
		}
		return accessor;
	}

	@Override
	public EObject getHeldData(int columnIndex) {
		IEObjectPropertyAccessor accessor = getIndexedValue(columnIndex);
		if (accessor != null) {
			return accessor.getHeldData();
		}
		return null;
	}

	@Override
	public Comparator<EObject> getComparator(int columnIndex, int modifier) {
		IEObjectPropertyAccessor accessor = getIndexedValue(columnIndex);
		if (accessor != null) {
			return accessor.getComparator(modifier);
		}
		return null;
	}
	
}