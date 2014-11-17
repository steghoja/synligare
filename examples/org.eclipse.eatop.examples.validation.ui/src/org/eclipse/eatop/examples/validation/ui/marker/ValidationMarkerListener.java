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

package org.eclipse.eatop.examples.validation.ui.marker;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.eatop.examples.validation.ui.Activator;
import org.eclipse.eatop.examples.validation.ui.internal.l10n.Messages;
import org.eclipse.emf.validation.marker.MarkerUtil;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IValidationListener;
import org.eclipse.emf.validation.service.ValidationEvent;

public class ValidationMarkerListener implements IValidationListener {

	protected static final String MARKER_EXCEPTION = Messages.marker_exception;

	@Override
	public void validationOccurred(ValidationEvent event) {
		if (event.matches(IStatus.WARNING | IStatus.ERROR)) {
			// fabricate a multi-status for the MarkerUtil to consume
			List<IConstraintStatus> results = event.getValidationResults();
			MultiStatus multi = new MultiStatus(
					"org.eclipse.eatop.examples.validation.ui", 1, results.toArray(new IStatus[results.size()]), null, null); //$NON-NLS-1$

			try {
				// create problem markers on the appropriate resources
				MarkerUtil.createMarkers(multi);
			} catch (CoreException e) {
				// creation of problem markers failed for some reason
				Activator.log(MARKER_EXCEPTION, e);
			}
		}
	}

}
