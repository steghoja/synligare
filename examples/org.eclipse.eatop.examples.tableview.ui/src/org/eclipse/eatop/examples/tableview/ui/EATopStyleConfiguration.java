package org.eclipse.eatop.examples.tableview.ui;

import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.DefaultNatTableStyleConfiguration;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.TextPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.decorator.CustomLineBorderDecorator;
import org.eclipse.nebula.widgets.nattable.painter.cell.decorator.PaddingDecorator;
import org.eclipse.nebula.widgets.nattable.style.BorderStyle;
import org.eclipse.nebula.widgets.nattable.style.BorderStyle.LineStyleEnum;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.ConfigAttribute;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.style.IStyle;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.style.VerticalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;

public class EATopStyleConfiguration extends DefaultNatTableStyleConfiguration {
	@Override
	public void configureRegistry(IConfigRegistry configRegistry) {
		super.configureRegistry(configRegistry);
		org.eclipse.swt.graphics.Color headerColor = GUIHelper.getColor(210, 225, 240);

		Font headerFont = GUIHelper.getFont(new FontData("Segoe UI", 9, SWT.NORMAL));
		Font cornerFont = GUIHelper.getFont(new FontData("Segoe UI", 9, SWT.BOLD));
		Font bodyFont = GUIHelper.getFont(new FontData("Segoe UI", 9, SWT.NORMAL));
		cellStyle(configRegistry,
				new Styler()
		.set(CellStyleAttributes.FONT, headerFont)
		.set(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.CENTER)
		.set(CellStyleAttributes.VERTICAL_ALIGNMENT, VerticalAlignmentEnum.BOTTOM)
		.set(CellStyleAttributes.BORDER_STYLE, new BorderStyle(1, GUIHelper.COLOR_BLACK, LineStyleEnum.SOLID))
		.set(CellStyleAttributes.BACKGROUND_COLOR, headerColor)
		.style(),
		GridRegion.COLUMN_HEADER);
		
		cellStyle(configRegistry,
				new Styler()
		.set(CellStyleAttributes.BACKGROUND_COLOR, GUIHelper.COLOR_WHITE)
		.set(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT)
		.set(CellStyleAttributes.BACKGROUND_COLOR, headerColor)
		.set(CellStyleAttributes.FONT, cornerFont)
		.set(CellStyleAttributes.BORDER_STYLE, new BorderStyle(1, GUIHelper.COLOR_BLACK, LineStyleEnum.SOLID))
		.style(),
		GridRegion.CORNER);
		cellStyle(configRegistry,
				new Styler()
		.set(CellStyleAttributes.FONT, headerFont)
		.set(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT)
		.set(CellStyleAttributes.VERTICAL_ALIGNMENT, VerticalAlignmentEnum.BOTTOM)
		.set(CellStyleAttributes.BORDER_STYLE, new BorderStyle(1, GUIHelper.COLOR_BLACK, LineStyleEnum.SOLID))
		.set(CellStyleAttributes.BACKGROUND_COLOR, headerColor)
		.style(),
		GridRegion.ROW_HEADER);

		cellStyle(configRegistry, 
				new Styler()
		.set(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT)
		.set(CellStyleAttributes.VERTICAL_ALIGNMENT, VerticalAlignmentEnum.TOP)
		.set(CellStyleAttributes.FONT, bodyFont)
		.style(), 
		GridRegion.BODY);
		
		ICellPainter normalTextPainter = 
						new CustomLineBorderDecorator(
										new PaddingDecorator(
														new TextPainter(false, false, false, true),
										0, 3, 0, 3));
		configRegistry.registerConfigAttribute(
				CellConfigAttributes.CELL_PAINTER, 
				normalTextPainter,
				DisplayMode.NORMAL, 
				GridRegion.COLUMN_HEADER);
		
		configRegistry.registerConfigAttribute(
				CellConfigAttributes.CELL_PAINTER, 
				new CustomLineBorderDecorator(new PaddingDecorator(new TextPainter(), 0, 3, 0, 3)),
				DisplayMode.NORMAL, 
				GridRegion.CORNER);
		
		configRegistry.registerConfigAttribute(
				CellConfigAttributes.CELL_PAINTER, 
				normalTextPainter,
				DisplayMode.NORMAL,
				GridRegion.BODY);

		configRegistry.registerConfigAttribute(
				CellConfigAttributes.CELL_PAINTER, 
				normalTextPainter,
				DisplayMode.NORMAL,
				GridRegion.ROW_HEADER);

	}
	
	private void cellStyle(IConfigRegistry reg, IStyle style, String... labels) {
		for (String label : labels) {
			reg.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, style, DisplayMode.NORMAL, label);
			reg.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, style, DisplayMode.SELECT, label);
			reg.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, style, DisplayMode.EDIT, label);
		}
	}

	private class Styler {
		private Style style;

		public Styler() {
			this.style = new Style();
		}

		public <T> Styler set(ConfigAttribute<T> styleAttribute, T value) {
			style.setAttributeValue(styleAttribute, value);
			return this;
		}

		public Style style() {
			return style;
		}

	}
}
