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
package org.eclipse.eatop.metamodelgen.postprocessings;

import org.eclipse.eatop.eaadapter.ea2ecore.postprocessings.AdjustRootEPackageNsURI;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;

public class EASTADLAdjustRootEPackageNsURI extends AdjustRootEPackageNsURI {

	public EASTADLAdjustRootEPackageNsURI(String version) {
		super(version);
	}

	@Override
	public String getPrefix() {
		return IEASTADLConstants.DEFAULT_NSURI;
	}
}
