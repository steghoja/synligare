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
import org.eclipse.eatop.examples.tableview.dataproviders.ColumnHeaderDataProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.EObjectAccessorComboBoxDataProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.EObjectAccessorReferenceDataProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.EObjectCornerDataProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.EObjectListDataProvider;
import org.eclipse.eatop.examples.tableview.dataproviders.RowHeaderDataProvider;
import org.eclipse.eatop.examples.tableview.export.ExcelExportCommandHandler;
import org.eclipse.eatop.examples.tableview.export.ExcelExportFormatter;
import org.eclipse.eatop.examples.tableview.export.ExcelExporter;
import org.eclipse.eatop.examples.tableview.ui.CommaSeparatedDisplayConverter;
import org.eclipse.eatop.examples.tableview.ui.EATopStyleConfiguration;
import org.eclipse.eatop.examples.tableview.ui.EditConfiguration;
import org.eclipse.eatop.examples.tableview.ui.GEatopDisplayConverter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.AbstractUiBindingConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.ConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.coordinate.PositionCoordinate;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.edit.command.UpdateDataCommand;
import org.eclipse.nebula.widgets.nattable.edit.config.DefaultEditBindings;
import org.eclipse.nebula.widgets.nattable.export.ExportConfigAttributes;
import org.eclipse.nebula.widgets.nattable.export.command.ExportCommand;
import org.eclipse.nebula.widgets.nattable.extension.poi.HSSFExcelExporter;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
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
import org.eclipse.nebula.widgets.nattable.painter.cell.decorator.CustomLineBorderDecorator;
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
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
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
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
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
	private StyledText styledText;
	private StackLayout stackLayout;
	private boolean latestResize = false;
	private EObjectCornerDataProvider cornerDataProvider;
	private ConfigRegistry configRegistry;
	public boolean multiLine = true;

	public Table(Composite parent, StackLayout stackLayout) {
		this.stackLayout = stackLayout;
		if (stackLayout != null) {
			stackLayout.topControl = natTable;
			styledText = new StyledText(parent, SWT.NONE);
			styledText.setText("No elements to display in the table. \nSelect an element or several elements which have some property in common to populate the table.");
		}
		
		createTable(parent);
	}

	public void dispose() {
		natTable.dispose();
		if (styledText != null) {
			styledText.dispose();
		}
	}

	public void setFocus() {
		if (natTable != null) {
			natTable.setFocus();			
		}
	}

	@SuppressWarnings("unchecked")
	public void updateTable(List<? extends EObject> data, boolean resize) {
		IEObjectAccessor accessor = AccessorManager.INSTANCE.createAccessor(data);

		if (accessor != null) {

			if (accessor instanceof PropertyAccessorEObjectAccessor) {
				((PropertyAccessorEObjectAccessor) accessor).addObserver(this);
			}

			if (dataProvider != null) {
				dataProvider.setColumnAccessor(accessor);
			}

			if (sortedList != null && data != null) {
				GlazedLists.replaceAll(sortedList, (List<EObject>)data, false);
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
				if (accessor.getColumnCount() == 2 && stackLayout != null) {
					stackLayout.topControl = styledText;
				} else if (stackLayout != null) {
					stackLayout.topControl = natTable;
				}
				refresh(resize);
			}

		}
	}

	public void refresh(boolean resize) {
		latestResize = resize;
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
		configRegistry = new ConfigRegistry();
		ColumnHeaderLayerStack columnHeaderLayer = new ColumnHeaderLayerStack(colHeaderDataProvider, bodyLayer, sortedList);
		RowHeaderLayerStack rowHeaderLayer = new RowHeaderLayerStack(rowHeaderDataProvider, bodyLayer);
		
		cornerDataProvider = new EObjectCornerDataProvider(colHeaderDataProvider, rowHeaderDataProvider, dataProvider);
		DataLayer dataLayer = new DataLayer(cornerDataProvider);
		dataLayer.setConfigLabelAccumulator(new BorderConfigLabelAccumulator());
		CornerLayer cornerLayer = new CornerLayer(dataLayer, rowHeaderLayer, columnHeaderLayer);
		
		GridLayer gridLayer = new GridLayer(bodyLayer, columnHeaderLayer, rowHeaderLayer, cornerLayer);
		// --- The table widget
		natTable = new NatTable(parent, gridLayer, false);
		// --- Styling
		natTable.addConfiguration(new EATopStyleConfiguration());
		natTable.addConfiguration(new RowHeaderResizeBindings());
		// --- Excel
		natTable.addConfiguration(new AbstractRegistryConfiguration() {
			@Override
			public void configureRegistry(IConfigRegistry configRegistry) {
				
				HSSFExcelExporter excelExporter = new ExcelExporter();
				excelExporter.setApplyVerticalTextConfiguration(true);
				excelExporter.setApplyBackgroundColor(true);
				configRegistry.registerConfigAttribute(ExportConfigAttributes.EXPORTER, excelExporter);
				configRegistry.registerConfigAttribute(ExportConfigAttributes.EXPORT_FORMATTER, new ExcelExportFormatter());				
			}
		});
		// --- Editing
		natTable.addConfiguration(new EditBindings());
		comboBoxDataProvider = new EObjectAccessorComboBoxDataProvider(dataProvider, emptyAccessor);
		referenceDataProvider = new EObjectAccessorReferenceDataProvider(emptyAccessor, dataProvider);
		natTable.addConfiguration(new EditConfiguration(comboBoxDataProvider, referenceDataProvider));
		// --- DisplayConverters
		displayConverter = new EObjectAccessorDisplayConverter(emptyAccessor);
		natTable.addConfiguration(new DisplayConverterConfiguration());
		
		natTable.setConfigRegistry(configRegistry);
		natTable.configure();
		natTable.setBackground(GUIHelper.COLOR_WHITE);
		natTable.unregisterCommandHandler(ExportCommand.class);
		natTable.registerCommandHandler(new ExcelExportCommandHandler(natTable.getLayer()));

	}
	
	private class DisplayConverterConfiguration extends AbstractRegistryConfiguration {

		@Override
		public void configureRegistry(IConfigRegistry configRegistry) {
			configRegistry.registerConfigAttribute(
					CellConfigAttributes.DISPLAY_CONVERTER,
					displayConverter);
			if (multiLine) {
				configRegistry.registerConfigAttribute(
						CellConfigAttributes.DISPLAY_CONVERTER, 
						new GEatopDisplayConverter(), 
						DisplayMode.NORMAL, 
						IConfigLabels.DISPLAYCONVERTER);
			} else {
				configRegistry.registerConfigAttribute(
						CellConfigAttributes.DISPLAY_CONVERTER, 
						new CommaSeparatedDisplayConverter(), 
						DisplayMode.NORMAL, 
						IConfigLabels.DISPLAYCONVERTER);
			}
			
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
	
	private class BorderConfigLabelAccumulator implements IConfigLabelAccumulator {
		
		@Override
		public void accumulateConfigLabels(LabelStack configLabels,
				int columnPosition, int rowPosition) {
			configLabels.addLabel(CustomLineBorderDecorator.BOTTOM_LINE_BORDER_LABEL);
			configLabels.addLabel(CustomLineBorderDecorator.RIGHT_LINE_BORDER_LABEL);
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
			// Fetches cell labels used to bind specific configuration
			configLabelAccumulator = new EObjectAccessorConfigLabelAccumulator(bodyDataLayer);
			bodyDataLayer.setConfigLabelAccumulator(configLabelAccumulator);
			
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
		public ColumnHeaderLayerStack(IDataProvider dataProvider, BodyLayerStack bodyLayer, SortedList<EObject> sortedList) {
			DataLayer dataLayer = new DataLayer(dataProvider);
			dataLayer.setConfigLabelAccumulator(new BorderConfigLabelAccumulator());
			ColumnHeaderLayer colHeaderLayer = new ColumnHeaderLayer(dataLayer, bodyLayer, bodyLayer.getSelectionLayer());
			SortHeaderLayer<EObject> sortHeaderLayer = new SortHeaderLayer<EObject>(colHeaderLayer, sortModel, false);
			sortHeaderLayer.addConfiguration(new SingleClickSortConfiguration());
			setUnderlyingLayer(sortHeaderLayer);
		}
	}

	private class RowHeaderLayerStack extends AbstractLayerTransform {
		public RowHeaderLayerStack(IDataProvider dataProvider, BodyLayerStack bodyLayer) {
			DataLayer dataLayer = new DataLayer(dataProvider, 100, 20);
			dataLayer.setConfigLabelAccumulator(new BorderConfigLabelAccumulator());
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
	

	public void saveToExcel() {
		ExportCommand cmd = new ExportCommand(natTable.getConfigRegistry(), natTable.getShell());
		natTable.doCommand(cmd);
	}

	@Override
	public void update(Observable o, Object arg) {
		refresh(latestResize);
	}

	public void setMultiLine(boolean multiLine) {
		this.multiLine = multiLine;
		natTable.configure();
		refresh(latestResize);
	}

}
