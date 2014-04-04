/**
 * <copyright>
 *  
 * Copyright (c) 2014 itemis and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 *  
 * Contributors: 
 *     itemis - Initial API and implementation
 *  
 * </copyright>
 * 
 */
package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;

public class AdjustRootEPackageNsURI extends PostProcessingTemplate {

	protected String version = null;

	public AdjustRootEPackageNsURI(String version) {
		super();
		this.version = version;
	}

	@Override
	public void execute() {
		String nsUri;
		if (version != null) {
			nsUri = getPrefix() + "/" + version; //$NON-NLS-1$
		} else {
			nsUri = getPrefix();
		}

		model.setNsURI(nsUri);

	}

	// To be override
	protected String getPrefix() {
		return ""; //$NON-NLS-1$
	}
}
