package org.eclipse.eatop.examples.tableview.accessors.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.eatop.examples.tableview.accessors.IEObjectPropertyAccessor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;

public class EmptyPropertyAccessor implements IEObjectPropertyAccessor {

	@Override
	public Object getDataValue(EObject rowObject) {
		return null;
	}

	@Override
	public void setDataValue(EObject rowObject, Object newValue) {
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public List<String> getConfigLabels() {
		return Collections.emptyList();
	}

	@Override
	public List<?> getComboBoxData(EObject rowObject) {
		return Collections.emptyList();
	}

	@Override
	public Object canonicalToDisplayValue(Object canonicalValue, IConfigRegistry configRegistry) {
		return null;
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue, IConfigRegistry configRegistry) {
		return null;
	}

	@Override
	public EObject getHeldData() {
		return null;
	}

	@Override
	public Comparator<EObject> getComparator(int modifier) {
		return null;
	}

}
