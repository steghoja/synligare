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

import java.util.Collection;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.eatop.examples.validation.ui.Activator;
import org.eclipse.eatop.examples.validation.ui.internal.l10n.Messages;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * This action delegate calls upon the validation service to provide a batch validation of the selected EObjects and
 * their children.
 */
public class LiveValidationHandler extends AbstractHandler {

	/**
	 * Error message to display when an exception occured
	 */
	protected static final String MESSAGE_EXCEPTION = Messages.message_exception;

	/**
	 * Selected EObjects
	 */
	protected Collection<Resource> selectedResources = null;

	private boolean resourceHasAdapter(Resource r) {
		for (Adapter next : r.eAdapters()) {
			if (next instanceof LiveValidationContentAdapter) {
				return true;
			}
		}

		return false;
	}

	private Adapter resourceGetAdapter(Resource r) {
		for (Adapter next : r.eAdapters()) {
			if (next instanceof LiveValidationContentAdapter) {
				return next;
			}
		}

		return null;
	}

	@Override
	@SuppressWarnings({ "unchecked", "cast" })
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getCurrentSelectionChecked(event);
		selectedResources = null;

		try {
			if (selection instanceof IStructuredSelection) {

				IStructuredSelection structuredSelection = selection;
				selectedResources = structuredSelection.toList();

			}
		} catch (Exception e) {
			Activator.log(MESSAGE_EXCEPTION, e);
			throw new RuntimeException(e);
		}

		if (selectedResources != null) {
			HandlerUtil.toggleCommandState(event.getCommand());
			for (Object next : selectedResources) {
				if (next instanceof EObject) {
					EObject impl = (EObject) next;
					Resource r = impl.eResource();
					if (!resourceHasAdapter(r)) {
						EContentAdapter liveValidationContentAdapter = new LiveValidationContentAdapter();
						r.eAdapters().add(liveValidationContentAdapter);
					} else {
						r.eAdapters().remove(resourceGetAdapter(r));
					}
				}
			}
		}
		return null;
	}
}