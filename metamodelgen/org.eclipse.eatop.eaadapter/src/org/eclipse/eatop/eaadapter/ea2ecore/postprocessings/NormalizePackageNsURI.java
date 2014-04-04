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
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class NormalizePackageNsURI extends PostProcessingTemplate {

	// private static String prefix = ModelConstants.DEFAULT_NSURI;
	protected String version = null;

	public NormalizePackageNsURI(String version) {
		super();
		this.version = version;
	}

	@Override
	public void execute() {
		TreeIterator<EObject> iter = model.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof EPackage) {
				createNsURIForEPackage((EPackage) element);
			}
		}
		createNsURIForEPackage((EPackage) EcoreUtil.getRootContainer(model));

	}

	protected void createNsURIForEPackage(EPackage element) {
		String packagePath = createPathOfEPackage(element);
		String nsUri;
		if (version != null) {
			nsUri = getPrefix() + "/" + version; //$NON-NLS-1$
		} else {
			nsUri = getPrefix();
		}
		nsUri = nsUri + packagePath;
		element.setNsURI(nsUri);
	}

	// To be override
	public String getPrefix() {
		return ""; //$NON-NLS-1$
	}
}
