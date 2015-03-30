package org.eclipse.eatop.examples.tableview.export;

import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.data.convert.IDisplayConverter;
import org.eclipse.nebula.widgets.nattable.export.IExportFormatter;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;

public class ExcelExportFormatter implements IExportFormatter {

	public Object formatForExport(ILayerCell cell, IConfigRegistry configRegistry) {
		Object dataValue = cell.getDataValue();
		IDisplayConverter displayConverter = configRegistry.getConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER, cell.getDisplayMode(), cell.getConfigLabels().getLabels());
		return displayConverter.canonicalToDisplayValue(cell, configRegistry, dataValue);
	}

}