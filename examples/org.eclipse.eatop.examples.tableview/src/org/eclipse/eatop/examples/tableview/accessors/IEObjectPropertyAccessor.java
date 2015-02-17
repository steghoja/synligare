package org.eclipse.eatop.examples.tableview.accessors;

import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;

public interface IEObjectPropertyAccessor {
	public static final String DELETE_ENUM = "(unset value)";
	
	public Object getDataValue(EObject rowObject);
	
	public void setDataValue(EObject rowObject, Object newValue);
	
	public String getName();
	
	public List<String> getConfigLabels();

	public List<?> getComboBoxData(EObject rowObject);
	
	/**
	 * Convert backing data value to value to be displayed
	 */
	public Object canonicalToDisplayValue(Object canonicalValue, IConfigRegistry configRegistry);

	/**
	 * Convert from display value to value in the backing data structure
	 */
	public Object displayToCanonicalValue(Object displayValue, IConfigRegistry configRegistry);

	public EObject getHeldData();

	public Comparator<EObject> getComparator(int modifier);
	
}
