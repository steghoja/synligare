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
package org.eclipse.eatop.serialization.tests;

import org.eclipse.eatop.common.preferences.IEastADLPreferences;
import org.eclipse.eatop.testutils.AbstractEastadl21TestCase;

public abstract class AbstracteEastadl21SerializationTestCase extends AbstractEastadl21TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// Schema validation is activated to check that all elements have been serialized correctly.
		IEastADLPreferences.XSD_VALIDATION_ON_LOAD.set(true);
	}

}
