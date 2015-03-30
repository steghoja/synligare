package org.eclipse.eatop.examples.tableview.export;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.export.ExportConfigAttributes;
import org.eclipse.nebula.widgets.nattable.export.ILayerExporter;
import org.eclipse.nebula.widgets.nattable.export.NatExporter;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.swt.SWT;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
public class ExportHandler extends NatExporter {
	
	private Shell sh = null;
	private String defaultFileName;
	private String[] defaultFilterNames;
	private String[] defaultFilterExtensions;
	
	public ExportHandler(Shell shell, String defaultFileName,String[] defaultFilterNames, String[] defaultFilterExtensions) {
		super(shell);
		sh = shell;
		this.defaultFileName = defaultFileName;
		this.defaultFilterNames = defaultFilterNames;
		this.defaultFilterExtensions = defaultFilterExtensions;
	}
	
	
	/**
	 * Taken from parts of the FileOutputStreamProvider.getOutPutStream method
	 * @param shell
	 * @return
	 */
	public String getFileName(Shell shell) {
		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		
		String filterPath;
		String relativeFileName;
		
		int lastIndexOfFileSeparator = defaultFileName.lastIndexOf(File.separator);
		if (lastIndexOfFileSeparator >= 0) {
			filterPath = defaultFileName.substring(0, lastIndexOfFileSeparator);
			relativeFileName = defaultFileName.substring(lastIndexOfFileSeparator + 1);
		} else {
			filterPath = "/"; //$NON-NLS-1$
			relativeFileName = defaultFileName;
		}
		
		dialog.setFilterPath(filterPath);
		dialog.setOverwrite(true);
	
		dialog.setFileName(relativeFileName);
		dialog.setFilterNames(defaultFilterNames);
		dialog.setFilterExtensions(defaultFilterExtensions);
		String currentFileName = dialog.open();
		if (currentFileName == null) {
			return null;
		}
		return currentFileName;
	}

	
	/**
	 * Basically a copy of exportSingleLayer, made the method throw an exception if there was a problem when opening the filestream,
	 * This makes it possible to handle the situation when a file is opened and user tries to save to it.
	 */
	public void exportSingleLayerWithException(final ILayer layer, final IConfigRegistry configRegistry) throws FileNotFoundException {
		final ILayerExporter exporter = configRegistry.getConfigAttribute(ExportConfigAttributes.EXPORTER, DisplayMode.NORMAL);
		
		String fileName = getFileName(sh);
		if (fileName == null) {
			return;
		}
		/* 
		 * Had to remove usage of default method call, because the method didn't throw any exceptions.
		 * There was no way to separate a click on cancel and an exception when handling the files. 
		 */
		// Throws FileNotFoundException
		final OutputStream outputStream = new PrintStream(fileName);
		
		Runnable exportRunnable = new Runnable() {
			@Override
			public void run() {
				try {
					exporter.exportBegin(outputStream);
					
					exportLayer(exporter, outputStream, "", layer, configRegistry); //$NON-NLS-1$
					
					exporter.exportEnd(outputStream);
				} catch (IOException e) {
					throw new RuntimeException("Failed to export.", e); //$NON-NLS-1$
				} finally {
					try {
						outputStream.close();
					} catch (IOException e) {
						e.printStackTrace(System.err);
					}
				}
				
				if (exporter.getResult() != null && exporter.getResult() instanceof File) {
					Program.launch(((File)exporter.getResult()).getAbsolutePath());
				}
				
			}
		};
		
		if (sh != null) {
			// Run with the SWT display so that the progress bar can paint
			sh.getDisplay().asyncExec(exportRunnable);
		} else {
			exportRunnable.run();
		}
	}
	
}
