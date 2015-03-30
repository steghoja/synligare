package org.eclipse.eatop.examples.tableview.accessors;

import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.IColumnAccessor;

public interface IEObjectAccessor extends IColumnAccessor<EObject> {

	public Object getColumnHeaderValue(int columnIndex);
	
	public Object getRowHeaderValue(EObject rowObject);
	
	public List<String> getConfigLabels(int columnIndex);
	
	public List<?> getComboBoxData(EObject rowObject, int columnIndex);
	
	/**
	 * Convert backing data value to value to be displayed
	 * @param configRegistry 
	 */
	public Object canonicalToDisplayValue(Object canonicalValue, int columnIndex, IConfigRegistry configRegistry);

	/**
	 * Convert from display value to value in the backing data structure
	 * @param configRegistry 
	 */
	public Object displayToCanonicalValue(Object displayValue, int columnIndex, IConfigRegistry configRegistry);
	
	public EObject getHeldData(int columnIndex);

	public Comparator<EObject> getComparator(int columnIndex, int modifier);
	
}
