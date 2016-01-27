/**
 * <copyright>
 * 
 * Copyright (c) 10, 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */

package org.eclipse.eatop.examples.validation.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.eatop.examples.validation.ui.Activator;
import org.eclipse.eatop.examples.validation.ui.internal.l10n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sphinx.platform.ui.util.ExtendedPlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * This action delegate calls upon the validation service to provide a batch validation of the selected EObjects and
 * their children.
 */
public class BatchValidationHandler extends AbstractHandler {

	/**
	 * Error message to display when an exception occured
	 */
	protected static final String MESSAGE_EXCEPTION = Messages.message_exception;

	/**
	 * Selected EObjects
	 */
	protected Collection<EObject> selectedEObjects = null;

	private IBatchValidator validator = null;

	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelectionChecked(event);

		selectedEObjects = null;
		try {

			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = selection;
				selectedEObjects = structuredSelection.toList();
			}
		} catch (Exception e) {
			Activator.log(MESSAGE_EXCEPTION, e);
			throw new RuntimeException(e);
		}

		if (validator == null) {
			validator = ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);

			validator.setOption(IBatchValidator.OPTION_INCLUDE_LIVE_CONSTRAINTS, true);
			validator.setOption(IBatchValidator.OPTION_TRACK_RESOURCES, true);
		}

		try {
			IRunnableWithProgress operation = new WorkspaceModifyDelegatingOperation(new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					if (selectedEObjects != null) {
						validator.validate(selectedEObjects, monitor);
					}
				}
			});
			// Run the validation operation, and show progress
			new ProgressMonitorDialog(ExtendedPlatformUI.getActiveShell()).run(true, true, operation);
		} catch (Exception ex) {
			Activator.log(MESSAGE_EXCEPTION, ex);
		}

		return null;
	}

}
