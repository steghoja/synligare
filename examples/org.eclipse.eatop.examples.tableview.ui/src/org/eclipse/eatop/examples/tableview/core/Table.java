package org.eclipse.eatop.examples.tableview.core;

import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.eatop.examples.tableview.AccessorManager;
import org.eclipse.eatop.examples.tableview.IConfigLabels;
import org.eclipse.eatop.examples.tableview.accessors.IEObjectAccessor;
import org.eclipse.eatop.examples.tableview.accessors.impl.EmptyEObjectAccessor;
import org.eclipse.eatop.examples.tableview.accessors.impl.PropertyAccessorEObjectAccessor;
import org.eclipse.eatop.examples.tableview.celleditors.EATopMultiReferenceCellEditor;
import org.eclipse.eatop.examples.tableview.dataproviders.ColumnHeaderDataProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.EObjectAccessorComboBoxDataProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.EObjectAccessorReferenceDataProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.EObjectListDataProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.RowHeaderDataProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.config.AbstractUiBindingConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.ConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.DefaultNatTableStyleConfiguration;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.coordinate.PositionCoordinate;
import org.eclipse.nebula.widgets.nattable.data.IColumnAccessor;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.edit.command.UpdateDataCommand;
import org.eclipse.nebula.widgets.nattable.edit.config.DefaultEditBindings;
import org.eclipse.nebula.widgets.nattable.edit.editor.ComboBoxCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.TextCellEditor;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultCornerDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.CornerLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.GridLayer;
import org.eclipse.nebula.widgets.nattable.grid.layer.RowHeaderLayer;
import org.eclipse.nebula.widgets.nattable.hideshow.ColumnHideShowLayer;
import org.eclipse.nebula.widgets.nattable.layer.AbstractLayerTransform;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.layer.LabelStack;
import org.eclipse.nebula.widgets.nattable.layer.cell.IConfigLabelAccumulator;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.TextPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.VerticalTextPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.decorator.LineBorderDecorator;
import org.eclipse.nebula.widgets.nattable.painter.cell.decorator.PaddingDecorator;
import org.eclipse.nebula.widgets.nattable.reorder.ColumnReorderLayer;
import org.eclipse.nebula.widgets.nattable.resize.action.AutoResizeColumnAction;
import org.eclipse.nebula.widgets.nattable.resize.action.AutoResizeRowAction;
import org.eclipse.nebula.widgets.nattable.resize.action.ColumnResizeCursorAction;
import org.eclipse.nebula.widgets.nattable.resize.action.RowResizeCursorAction;
import org.eclipse.nebula.widgets.nattable.resize.command.InitializeAutoResizeColumnsCommand;
import org.eclipse.nebula.widgets.nattable.resize.command.InitializeAutoResizeRowsCommand;
import org.eclipse.nebula.widgets.nattable.resize.event.ColumnResizeEventMatcher;
import org.eclipse.nebula.widgets.nattable.resize.event.RowResizeEventMatcher;
import org.eclipse.nebula.widgets.nattable.resize.mode.ColumnResizeDragMode;
import org.eclipse.nebula.widgets.nattable.resize.mode.RowResizeDragMode;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectCellCommand;
import org.eclipse.nebula.widgets.nattable.sort.SortHeaderLayer;
import org.eclipse.nebula.widgets.nattable.sort.config.SingleClickSortConfiguration;
import org.eclipse.nebula.widgets.nattable.style.BorderStyle;
import org.eclipse.nebula.widgets.nattable.style.BorderStyle.LineStyleEnum;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.ConfigAttribute;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.style.IStyle;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.style.VerticalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.ui.action.ClearCursorAction;
import org.eclipse.nebula.widgets.nattable.ui.action.IKeyAction;
import org.eclipse.nebula.widgets.nattable.ui.action.NoOpMouseAction;
import org.eclipse.nebula.widgets.nattable.ui.binding.UiBindingRegistry;
import org.eclipse.nebula.widgets.nattable.ui.matcher.KeyEventMatcher;
import org.eclipse.nebula.widgets.nattable.ui.matcher.MouseEventMatcher;
import org.eclipse.nebula.widgets.nattable.util.GCFactory;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.SortedList;

public class Table implements Observer {
	private NatTable natTable = null;
	private EObjectListDataProvider dataProvider = null;
	private ColumnHeaderDataProvider colHeaderDataProvider = null;
	private RowHeaderDataProvider rowHeaderDataProvider;
	private EObjectAccessorConfigLabelAccumulator configLabelAccumulator = null;
	private EObjectAccessorComboBoxDataProvider comboBoxDataProvider = null;
	private EObjectAccessorDisplayConverter displayConverter = null;
	private SortedList<EObject> sortedList = null;
	private SortModel sortModel = null;
	private EObjectAccessorReferenceDataProvider referenceDataProvider;

	public Table(Composite parent) {
		createTable(parent);
	}

	public void dispose() {
		natTable.dispose();
	}

	public void setFocus() {
		if (natTable != null) {
			natTable.setFocus();			
		}
	}

	public void updateTable(List<EObject> data, boolean resize) {
		IEObjectAccessor accessor = AccessorManager.INSTANCE.createAccessor(data);

		if (accessor != null) {

			if (accessor instanceof PropertyAccessorEObjectAccessor) {
				((PropertyAccessorEObjectAccessor) accessor).addObserver(this);
			}

			if (dataProvider != null) {
				dataProvider.setColumnAccessor(accessor);
			}

			if (sortedList != null && data != null) {
				GlazedLists.replaceAll(sortedList, data, false);
			}
			if (sortModel != null) {
				sortModel.setAccessor(accessor);
			}

			if (colHeaderDataProvider != null) {
				colHeaderDataProvider.setColumnAccessor(accessor);
			}

			if (rowHeaderDataProvider != null) {
				rowHeaderDataProvider.setColumnAccessor(accessor);
			}

			if (configLabelAccumulator != null) {
				configLabelAccumulator.setEObjectAccessor(accessor);
			}

			if (comboBoxDataProvider != null) {
				comboBoxDataProvider.setEObjectAccessor(accessor);
			}

			if (referenceDataProvider != null) {
				referenceDataProvider.setEObjectAccessorReferenceAccessor(accessor);
			}

			if (displayConverter != null) {
				displayConverter.setEObjectAccessor(accessor);
			}

			if (natTable != null) {
				refresh(resize);
			}

		}
	}

	public void refresh(boolean resize) {
		if (resize) {
			natTable.addListener(SWT.Paint, new Resizer());
		}
		natTable.refresh();
	}

	private void createTable(Composite parent) {
		List<EObject> emptyList = Collections.<EObject>emptyList();
		EventList<EObject> eventList = GlazedLists.eventList(emptyList);
		sortedList = new SortedList<EObject>(eventList, null);
		EmptyEObjectAccessor emptyAccessor = new EmptyEObjectAccessor();

		dataProvider = new EObjectListDataProvider(sortedList, emptyAccessor);
		colHeaderDataProvider = new ColumnHeaderDataProvider(emptyAccessor);
		rowHeaderDataProvider = new RowHeaderDataProvider(dataProvider, emptyAccessor);
		sortModel = new SortModel(sortedList);
		sortModel.addObserver(this);

		// --- Layers
		BodyLayerStack bodyLayer = new BodyLayerStack(dataProvider);
		ConfigRegistry configRegistry = new ConfigRegistry();
		ColumnHeaderLayerStack columnHeaderLayer = new ColumnHeaderLayerStack(colHeaderDataProvider, bodyLayer, sortedList, emptyAccessor);
		RowHeaderLayerStack rowHeaderLayer = new RowHeaderLayerStack(rowHeaderDataProvider, bodyLayer);

		DefaultCornerDataProvider cornerDataProvider = new DefaultCornerDataProvider(colHeaderDataProvider, rowHeaderDataProvider);
		CornerLayer cornerLayer = new CornerLayer(new DataLayer(cornerDataProvider), rowHeaderLayer, columnHeaderLayer);
		GridLayer gridLayer = new GridLayer(bodyLayer, columnHeaderLayer, rowHeaderLayer, cornerLayer);
		// Fetches cell labels used to bind specific configuration
		configLabelAccumulator = new EObjectAccessorConfigLabelAccumulator(bodyLayer);
		bodyLayer.setConfigLabelAccumulator(configLabelAccumulator);
		// The table widget
		natTable = new NatTable(parent, gridLayer, false);
		natTable.addListener(SWT.Paint, new Resizer());
		natTable.addConfiguration(new DefaultNatTableStyleConfiguration());
		natTable.addConfiguration(new RowHeaderResizeBindings());
		natTable.addConfiguration(new EditBindings());
		natTable.setConfigRegistry(configRegistry);
		natTable.configure();
		natTable.setBackground(GUIHelper.COLOR_WHITE);

		// --- Editing
		setupEditing(configRegistry, emptyAccessor);

		// --- Styling
		setupTableStyle(configRegistry, emptyAccessor);
	}

	private void setupTableStyle(ConfigRegistry configRegistry, EmptyEObjectAccessor emptyAccessor) {
		displayConverter = new EObjectAccessorDisplayConverter(emptyAccessor);



		configRegistry.registerConfigAttribute(
				CellConfigAttributes.DISPLAY_CONVERTER,
				displayConverter);

		Font font = GUIHelper.getFont(new FontData("Segoe UI", 9, SWT.BOLD));
		cellStyle(configRegistry,
				new Styler()
		.set(CellStyleAttributes.FONT, font)
		.set(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.CENTER)
		.set(CellStyleAttributes.VERTICAL_ALIGNMENT, VerticalAlignmentEnum.BOTTOM)
		.set(CellStyleAttributes.BORDER_STYLE, new BorderStyle(1, GUIHelper.COLOR_GRAY, LineStyleEnum.SOLID))
		.style(),
		GridRegion.COLUMN_HEADER, GridRegion.CORNER);

		cellStyle(configRegistry,
				new Styler()
		.set(CellStyleAttributes.FONT, font)
		.set(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT)
		.set(CellStyleAttributes.VERTICAL_ALIGNMENT, VerticalAlignmentEnum.BOTTOM)
		.set(CellStyleAttributes.BORDER_STYLE, new BorderStyle(1, GUIHelper.COLOR_GRAY, LineStyleEnum.SOLID))
		.style(),
		GridRegion.ROW_HEADER);

		cellStyle(configRegistry, 
				new Styler()
		.set(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT)
		.set(CellStyleAttributes.VERTICAL_ALIGNMENT, VerticalAlignmentEnum.TOP)
		.set(CellStyleAttributes.FONT, GUIHelper.DEFAULT_FONT)
		.style(), 
		GridRegion.BODY);

		cellStyle(configRegistry, 
				new Styler()
		.set(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.RIGHT)
		.style(), 
		IConfigLabels.ALIGN_RIGHT);



	}

	private void setupEditing(ConfigRegistry configRegistry, IEObjectAccessor emptyAccessor) {
		setupForAllModes(configRegistry,
				EditConfigAttributes.CELL_EDITABLE_RULE,
				IEditableRule.ALWAYS_EDITABLE,
				IConfigLabels.EDITOR_TEXT);
		
		setupForAllModes(configRegistry,
				EditConfigAttributes.CELL_EDITABLE_RULE,
				IEditableRule.ALWAYS_EDITABLE,
				IConfigLabels.EDITOR_COMBO);
		
		setupForAllModes(configRegistry,
				EditConfigAttributes.CELL_EDITABLE_RULE,
				IEditableRule.ALWAYS_EDITABLE,
				IConfigLabels.REFERENCE_MULTI);
		
		// Enums
		comboBoxDataProvider = new EObjectAccessorComboBoxDataProvider(dataProvider, emptyAccessor);
		ComboBoxCellEditor comboBoxCellEditor = new ComboBoxCellEditor(comboBoxDataProvider);

		setupForAllModes(configRegistry,
						EditConfigAttributes.CELL_EDITOR, 
						comboBoxCellEditor, 
						IConfigLabels.EDITOR_COMBO);
		
		// Reference editing
		referenceDataProvider = new EObjectAccessorReferenceDataProvider(emptyAccessor, dataProvider);
		
		EATopMultiReferenceCellEditor eaTopMultiReferenceCellEditor = new EATopMultiReferenceCellEditor(referenceDataProvider);
		
		configRegistry.registerConfigAttribute(
				EditConfigAttributes.CELL_EDITOR, 
				eaTopMultiReferenceCellEditor,
				DisplayMode.EDIT,
				IConfigLabels.REFERENCE_MULTI);


		configRegistry.registerConfigAttribute(
				EditConfigAttributes.CELL_EDITOR, 
				new TextCellEditor(),
				DisplayMode.EDIT,
				IConfigLabels.EDITOR_TEXT); 

		ICellPainter verticalTextPainter = new LineBorderDecorator(new PaddingDecorator(new VerticalTextPainter(true, true, 5, true), 0, 0, 5, 0));
		configRegistry.registerConfigAttribute(
				CellConfigAttributes.CELL_PAINTER, 
				verticalTextPainter,
				DisplayMode.NORMAL, 
				GridRegion.COLUMN_HEADER);

		ICellPainter textPainter = new PaddingDecorator(new TextPainter(), 0, 1, 0, 3);

		configRegistry.registerConfigAttribute(
				CellConfigAttributes.CELL_PAINTER, 
				textPainter,
				DisplayMode.NORMAL,
				GridRegion.BODY);

		configRegistry.registerConfigAttribute(
				CellConfigAttributes.CELL_PAINTER, 
				textPainter,
				DisplayMode.NORMAL,
				GridRegion.ROW_HEADER);

	}

	private <T> void setupForAllModes(ConfigRegistry configRegistry, ConfigAttribute<T> configAttribute, T attributeValue, String configLabel) {
		configRegistry.registerConfigAttribute(
				configAttribute, 
				attributeValue,
				DisplayMode.EDIT,
				configLabel);
		configRegistry.registerConfigAttribute(
				configAttribute, 
				attributeValue,
				DisplayMode.NORMAL,
				configLabel);
		configRegistry.registerConfigAttribute(
				configAttribute, 
				attributeValue,
				DisplayMode.SELECT,
				configLabel);
		configRegistry.registerConfigAttribute(
				configAttribute, 
				attributeValue,
				DisplayMode.HOVER,
				configLabel);
		configRegistry.registerConfigAttribute(
				configAttribute, 
				attributeValue,
				DisplayMode.SELECT_HOVER,
				configLabel);
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

	private class EObjectAccessorConfigLabelAccumulator implements IConfigLabelAccumulator {

		private IEObjectAccessor accessor;
		private ILayer layer;

		public EObjectAccessorConfigLabelAccumulator(ILayer layer) {
			this.layer = layer;
		}

		public void setEObjectAccessor(IEObjectAccessor accessor) {
			this.accessor = accessor;
		}

		@Override
		public void accumulateConfigLabels(LabelStack configLabels, int columnPosition, int rowPosition) {
			int columnIndex = layer.getColumnIndexByPosition(columnPosition);
			if (accessor != null) {
				List<String> labels = accessor.getConfigLabels(columnIndex);
				if (labels != null) {
					for (String label : labels) {
						configLabels.addLabel(label);
					}
				}
			}
		}

	}



	// =========================================================================================

	private class Resizer implements Listener { 

		@Override public void handleEvent(Event arg0) { 
			for (int i=0; i < natTable.getColumnCount(); i++) { 
				InitializeAutoResizeColumnsCommand columnCommand = 
						new InitializeAutoResizeColumnsCommand(
								natTable, i, natTable.getConfigRegistry(), 
								new GCFactory(natTable)); 
				natTable.doCommand(columnCommand); 
			} 

			for (int i=0; i < natTable.getRowCount(); i++) { 
				InitializeAutoResizeRowsCommand rowCommand = 
						new InitializeAutoResizeRowsCommand(natTable, i, 
								natTable.getConfigRegistry(), 
								new GCFactory(natTable)); 
				natTable.doCommand(rowCommand); 
			} 
			natTable.removeListener(SWT.Paint, this);
		} 
	}


	//	Table stacks
	private class BodyLayerStack extends AbstractLayerTransform {

		private SelectionLayer selectionLayer;

		public BodyLayerStack(IDataProvider dataProvider) {
			DataLayer bodyDataLayer = new DataLayer(dataProvider);
			ColumnReorderLayer columnReorderLayer = new ColumnReorderLayer(bodyDataLayer);
			ColumnHideShowLayer columnHideShowLayer = new ColumnHideShowLayer(columnReorderLayer);
			selectionLayer = new SelectionLayer(columnHideShowLayer);
			selectionLayer.addConfiguration(new EditBindings());
			ViewportLayer viewportLayer = new ViewportLayer(selectionLayer);
			setUnderlyingLayer(viewportLayer);
		}

		public SelectionLayer getSelectionLayer() {
			return selectionLayer;
		}

	}

	private class ColumnHeaderLayerStack extends AbstractLayerTransform {
		public ColumnHeaderLayerStack(IDataProvider dataProvider, BodyLayerStack bodyLayer, SortedList<EObject> sortedList, IColumnAccessor<EObject> emptyAccessor) {
			DataLayer dataLayer = new DataLayer(dataProvider);
			ColumnHeaderLayer colHeaderLayer = new ColumnHeaderLayer(dataLayer, bodyLayer, bodyLayer.getSelectionLayer());
			SortHeaderLayer<EObject> sortHeaderLayer = new SortHeaderLayer<EObject>(colHeaderLayer, sortModel, false);
			sortHeaderLayer.addConfiguration(new SingleClickSortConfiguration());
			setUnderlyingLayer(sortHeaderLayer);
		}
	}

	private class RowHeaderLayerStack extends AbstractLayerTransform {
		public RowHeaderLayerStack(IDataProvider dataProvider, BodyLayerStack bodyLayer) {
			DataLayer dataLayer = new DataLayer(dataProvider, 100, 20);
			RowHeaderLayer rowHeaderLayer = new RowHeaderLayer(dataLayer, bodyLayer, bodyLayer.getSelectionLayer());
			setUnderlyingLayer(rowHeaderLayer);

		}
	}


	private class RowHeaderResizeBindings extends AbstractUiBindingConfiguration {

		public void configureUiBindings(UiBindingRegistry uiBindingRegistry) {
			uiBindingRegistry.registerMouseMoveBinding(new MouseEventMatcher(), new ClearCursorAction());
			configureColumnResize(uiBindingRegistry, GridRegion.ROW_HEADER);
			configureColumnResize(uiBindingRegistry, GridRegion.CORNER);
			configureRowResize(uiBindingRegistry, GridRegion.COLUMN_HEADER);
			configureRowResize(uiBindingRegistry, GridRegion.CORNER);
		}

		private void configureRowResize(UiBindingRegistry uiBindingRegistry, String region) {
			uiBindingRegistry.registerFirstMouseMoveBinding(new RowResizeEventMatcher(SWT.NONE, region, 0), new RowResizeCursorAction());
			uiBindingRegistry.registerFirstMouseDragMode(new RowResizeEventMatcher(SWT.NONE, region, 1), new RowResizeDragMode());
			uiBindingRegistry.registerDoubleClickBinding(new RowResizeEventMatcher(SWT.NONE, region, 1), new AutoResizeRowAction());
			uiBindingRegistry.registerSingleClickBinding(new RowResizeEventMatcher(SWT.NONE, region, 1), new NoOpMouseAction());
		}

		private void configureColumnResize(UiBindingRegistry uiBindingRegistry, String region) {
			uiBindingRegistry.registerFirstMouseMoveBinding(new ColumnResizeEventMatcher(SWT.NONE, region, 0), new ColumnResizeCursorAction());
			uiBindingRegistry.registerFirstMouseDragMode(new ColumnResizeEventMatcher(SWT.NONE, region, 1), new ColumnResizeDragMode());
			uiBindingRegistry.registerDoubleClickBinding(new ColumnResizeEventMatcher(SWT.NONE, region, 1), new AutoResizeColumnAction());
			uiBindingRegistry.registerSingleClickBinding(new ColumnResizeEventMatcher(SWT.NONE, region, 1), new NoOpMouseAction());
		}

	}

	private class EditBindings extends DefaultEditBindings {
		@Override
		public void configureUiBindings(UiBindingRegistry uiBindingRegistry) {
			uiBindingRegistry.registerKeyBinding(new KeyEventMatcher(SWT.NONE, SWT.DEL), new DeleteDataAction());
			super.configureUiBindings(uiBindingRegistry);
		}

	}

	private class DeleteDataAction implements IKeyAction {

		@Override
		public void run(NatTable natTable, KeyEvent event) {
			SelectionLayer selectionLayer = ((BodyLayerStack) ((GridLayer) natTable
					.getLayer()).getBodyLayer()).getSelectionLayer();
			int nextYPos = 0;
			int nextXPos = 0;
			boolean atLeastOneDelete = false;
			for (PositionCoordinate cellPosition : selectionLayer
					.getSelectedCellPositions()) {
				atLeastOneDelete = true;
				nextXPos = Math.max(cellPosition.getColumnPosition() + 1,
						nextXPos);
				nextYPos = Math.max(cellPosition.getRowPosition() + 1,
						nextYPos);
				natTable.doCommand(new UpdateDataCommand(natTable, cellPosition
						.getColumnPosition() + 1,
						cellPosition.getRowPosition() + 1, null));

			}
			if (atLeastOneDelete) {
				natTable.doCommand(new SelectCellCommand(natTable, nextXPos,
						nextYPos, false, false));
			}

		}
	}

	@Override
	public void update(Observable o, Object arg) {
		refresh(true);
	}

}
