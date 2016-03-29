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

import org.eclipse.eatop.examples.validation.ui.internal.l10n.Messages;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.ILiveValidator;
import org.eclipse.emf.validation.service.ModelValidationService;

public class LiveValidationContentAdapter extends EContentAdapter {

	/**
	 * Error message to display when an exception occured
	 */
	protected static final String MARKER_EXCEPTION = Messages.marker_exception;

	private ILiveValidator validator = null;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.util.EContentAdapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	@Override
	public void notifyChanged(final Notification notification) {
		super.notifyChanged(notification);

		if (validator == null) {
			validator = ModelValidationService.getInstance().newValidator(EvaluationMode.LIVE);
		}

		validator.validate(notification);

	}
}
