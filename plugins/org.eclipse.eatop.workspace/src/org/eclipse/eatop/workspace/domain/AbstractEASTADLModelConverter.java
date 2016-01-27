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
package org.eclipse.eatop.workspace.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sphinx.emf.metamodel.IMetaModelDescriptor;
import org.eclipse.sphinx.emf.resource.AbstractModelConverter;
import org.jdom.JDOMException;

public abstract class AbstractEASTADLModelConverter extends AbstractModelConverter {

	@Override
	public IMetaModelDescriptor getResourceVersionFromPreferences(IProject project) {
		return IEastADLWorkspacePreferences.RESOURCE_VERSION.get(project);
	}

	/**
	 * Return true if the converter really does load convert. If false is returned the converter will choose to simply
	 * return the stream as it is received.
	 * 
	 * @return
	 */
	protected boolean shouldLoadConvert() {
		return true;
	}

	/*
	 * @see org.eclipse.sphinx.emf.resource.AbstractModelConverter#doConvertLoad(org.eclipse.emf.ecore.xmi.XMLResource,
	 * java.io.InputStream, java.util.Map)
	 */
	@Override
	protected InputStream doConvertLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options) throws IOException, JDOMException {
		if (shouldLoadConvert()) {
			return super.doConvertLoad(resource, inputStream, options);
		} else {
			return inputStream;
		}
	}
}
