package org.eclipse.eatop.tableview.core;

import org.eclipse.eatop.tableview.accessors.IEObjectAccessor;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.convert.IDisplayConverter;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;

public class EObjectAccessorDisplayConverter implements IDisplayConverter {

	IEObjectAccessor accessor;

	public EObjectAccessorDisplayConverter(IEObjectAccessor accessor) {
		this.accessor = accessor;
	}

	public void setEObjectAccessor(IEObjectAccessor accessor) {
		this.accessor = accessor;
	}

	@Override
	public Object canonicalToDisplayValue(Object canonicalValue) {
		return canonicalValue;
	}

	@Override
	public Object displayToCanonicalValue(Object displayValue) {
		return displayValue;
	}

	@Override
	public Object canonicalToDisplayValue(ILayerCell cell,
			IConfigRegistry configRegistry, Object canonicalValue) {
		return accessor.canonicalToDisplayValue(canonicalValue, cell.getColumnIndex());
	}

	@Override
	public Object displayToCanonicalValue(ILayerCell cell,
			IConfigRegistry configRegistry, Object displayValue) {
		return accessor.displayToCanonicalValue(displayValue, cell.getColumnIndex());
	}

}