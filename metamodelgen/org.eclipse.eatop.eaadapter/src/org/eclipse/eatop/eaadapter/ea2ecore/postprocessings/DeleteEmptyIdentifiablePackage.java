/**
 * <copyright>
 * 
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import java.text.MessageFormat;
import java.util.Iterator;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.ecore.EPackage;

public class DeleteEmptyIdentifiablePackage extends PostProcessingTemplate {

	private void removeEmptyIdentifiablePackage(EPackage parent) {
		Iterator<EPackage> iter = parent.getESubpackages().iterator();
		while (iter.hasNext()) {
			EPackage ePackage = iter.next();
			if (IConstants.PACKAGE_IDENTIFIABLE.equals(ePackage.getName()) && ePackage.getESubpackages().size() <= 0
					&& ePackage.getEClassifiers().size() <= 0) {
				iter.remove();
				logger.info(MessageFormat.format(Messages.logger_RemoveEmptyIdentifiablePackage, ePackage.getNsURI().toString()));
			}
			removeEmptyIdentifiablePackage(ePackage);
		}
	}

	@Override
	public void execute() {
		removeEmptyIdentifiablePackage(model);
	}
}
