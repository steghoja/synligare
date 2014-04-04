/**
 * <copyright>
 *  
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors: 
 *     itemis - Initial API and implementation
 *  
 * </copyright>
 * 
 */
package org.eclipse.eatop.metamodelgen.ui.handlers;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.eatop.metamodelgen.EASTADLEcoreMMGen;
import org.eclipse.eatop.metamodelgen.internal.Activator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class EASTADLMMGenUIHandler extends AbstractHandler {

	protected static String GENERATE_ECORE_ONLY_PARAMETER = "org.eclipse.eatop.metamodelgen.commandParameters.ecoreGenOnly"; //$NON-NLS-1$
	protected static String GENERATE_MM_EMF_CODE_PARAMETER = "org.eclipse.eatop.metamodelgen.commandParameters.mmImplGen"; //$NON-NLS-1$
	protected static String GENERATE_ALL_PARAMETER = "org.eclipse.eatop.metamodelgen.commandParameters.allGen"; //$NON-NLS-1$
	protected static String GENERATE_CDO = "org.eclipse.eatop.metamodelgen.commandParameters.cdoGen"; //$NON-NLS-1$

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Gets the selected file from explorer
		IFile selectedEapFile = unwrap(event, IFile.class);
		Shell shell = HandlerUtil.getActiveShell(event);

		if (selectedEapFile != null) {
			if (IConstants.EAP_FILE_EXTENSION.equals(selectedEapFile.getFileExtension())) {
				String generateEcoreOnly = event.getParameter(GENERATE_ECORE_ONLY_PARAMETER);
				String generateMMCode = event.getParameter(GENERATE_MM_EMF_CODE_PARAMETER);
				String generateAll = event.getParameter(GENERATE_ALL_PARAMETER);
				String generateCDO = event.getParameter(GENERATE_CDO);

				// Create EASTADLEcoreMMGen
				final EASTADLEcoreMMGen ecoreMMgen = new EASTADLEcoreMMGen(selectedEapFile, new Boolean(generateEcoreOnly).booleanValue(),
						new Boolean(generateMMCode).booleanValue(), new Boolean(generateAll).booleanValue(), new Boolean(generateCDO).booleanValue());

				// Progress monitor dialog
				ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
				try {
					dialog.run(true, true, new IRunnableWithProgress() {
						@Override
						public void run(IProgressMonitor monitor) {
							doRun(ecoreMMgen, monitor);
						}
					});
				} catch (InvocationTargetException e) {
					PlatformLogUtil.logAsError(Activator.getDefault(), e.getMessage());
				} catch (InterruptedException e) {
					PlatformLogUtil.logAsError(Activator.getDefault(), e.getMessage());
				}
			}
		} else {
			MessageDialog.openInformation(shell, "Info", "Please select a EAP source file"); //$NON-NLS-1$//$NON-NLS-2$
		}
		return null;
	}

	private void doRun(EASTADLEcoreMMGen ecoreMMgen, IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, "Generating EATOP Ecore file ...", 100); //$NON-NLS-1$
		if (progress.isCanceled()) {
			throw new OperationCanceledException();
		}

		try {
			ecoreMMgen.generate(monitor);
		} catch (Exception e) {
			PlatformLogUtil.logAsError(Activator.getDefault(), e);
		}
	}

	private <T> T unwrap(Object o, Class<T> clazz) {
		if (o instanceof ExecutionEvent) {
			o = HandlerUtil.getCurrentSelection((ExecutionEvent) o);
		}

		if (o instanceof IStructuredSelection) {
			o = ((IStructuredSelection) o).getFirstElement();
		}

		if (o instanceof IAdaptable) {
			o = ((IAdaptable) o).getAdapter(clazz);
		}

		if (clazz.isInstance(o)) {
			return clazz.cast(o);
		}

		return null;
	}

}
