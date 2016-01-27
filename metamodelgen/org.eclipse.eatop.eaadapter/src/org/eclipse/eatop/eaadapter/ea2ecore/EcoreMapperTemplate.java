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

package org.eclipse.eatop.eaadapter.ea2ecore;

import org.eclipse.eatop.eaadapter.logging.Logger;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;

import eaadapter.EARepository;

public abstract class EcoreMapperTemplate {
	protected EPackage model;
	protected EARepository repository;
	protected EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
	protected Logger logger;

	public EcoreMapperTemplate(EARepository r) {
		repository = r;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setLoggingEnabled() {
		logger.setLoggingEnabled();
	}

	public void setLoggingDisabled() {
		logger.setLoggingDisabled();
	}

	public abstract EPackage execute() throws Exception;
}
