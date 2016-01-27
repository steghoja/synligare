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

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.ecore.EPackage;

public class DeletePackageGeneratedEcore extends PostProcessingTemplate {

	@Override
	public void execute() throws Exception {
		EPackage generatedPackage = model;
		EPackage domainModel = model.getESubpackages().get(0);

		if (!generatedPackage.getName().equalsIgnoreCase(IConstants.PACKAGE_GENERATEDECORE)) {
			logger.error(Messages.logger_NotRemoveGeneratedEcorePackage);
			return;
		}

		generatedPackage.setName(domainModel.getName());
		generatedPackage.setNsPrefix(domainModel.getNsPrefix());
		generatedPackage.setNsURI(domainModel.getNsURI());
		generatedPackage.getEAnnotations().addAll(domainModel.getEAnnotations());

		generatedPackage.getESubpackages().addAll(domainModel.getESubpackages());
		generatedPackage.getESubpackages().remove(domainModel);
	}
}
