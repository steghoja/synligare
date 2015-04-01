package org.eclipse.eatop.examples.tableview.export;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.extension.poi.HSSFExcelExporter;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;

public class ExcelExporter extends HSSFExcelExporter {
	
	@Override
	public void exportEnd(OutputStream outputStream) throws IOException {
		for (int i = 0; i < xlRow.getLastCellNum(); i++) {
			xlSheet.autoSizeColumn(i);
		}
		super.exportEnd(outputStream);
	}
	
	@Override
	public void exportCell(OutputStream outputStream,
			Object exportDisplayValue, ILayerCell cell,
			IConfigRegistry configRegistry) throws IOException {
		super.exportCell(outputStream, exportDisplayValue, cell, configRegistry);
		Cell xlCell = xlRow.getCell(cell.getColumnPosition());
		if (xlCell != null) {
			CellStyle cellStyle = xlCell.getCellStyle();
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		}
	}
	
}
