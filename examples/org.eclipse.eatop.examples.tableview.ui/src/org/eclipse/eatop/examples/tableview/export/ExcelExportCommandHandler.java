package org.eclipse.eatop.examples.tableview.export;

import java.io.FileNotFoundException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.nebula.widgets.nattable.command.AbstractLayerCommandHandler;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.export.command.ExportCommand;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.swt.widgets.Shell;

public class ExcelExportCommandHandler extends AbstractLayerCommandHandler<ExportCommand> {

	private final ILayer layer;

	public ExcelExportCommandHandler(ILayer layer) {
		this.layer = layer;
	}

	@Override
	public boolean doCommand(final ExportCommand command) {
		Shell shell = command.getShell();
		IConfigRegistry configRegistry = command.getConfigRegistry();
		try {
			new ExportHandler(shell, "table_export.xls", new String[] { "Excel Workbok (*.xls)" }, new String[] { "*.xls" }).exportSingleLayerWithException(layer, configRegistry);
		} catch (FileNotFoundException e) {
			MessageDialog.openWarning(shell, "Export was unsuccessful", "Unable to complete export of the table. \nCould possibly be caused by the selected file being opened in another program.");
		}
		return true;
	}

	public Class<ExportCommand> getCommandClass() {
		return ExportCommand.class;
	}
}
