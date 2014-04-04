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
package org.eclipse.eatop.metamodelgen.templates.internal.genmodel.geastadl;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;

public class GOperationFactory {

	private static final String GEASTADL_MODEL_NAME = "geastadl"; //$NON-NLS-1$

	public static GOperationFactory INSTANCE = new GOperationFactory();

	public GOperation createGOperation(GenOperation genOperation) {
		if (isGOperation(genOperation)) {
			return new GOperation(genOperation);
		}
		return null;
	}

	private boolean isGOperation(GenOperation genOperation) {
		GenModel model = genOperation.getGenModel();
		return GEASTADL_MODEL_NAME.equals(model.getModelName());
	}

}
