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
package org.eclipse.eatop.testutils;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.eatop.eastadl21.Eastadl21Package;
import org.eclipse.eatop.eastadl21.util.Eastadl21ResourceFactoryImpl;
import org.eclipse.eatop.testutils.internal.Activator;

@SuppressWarnings({ "nls" })
public abstract class AbstractEastadl21TestCase extends AbstractEastadlTestCase {

	protected static final String EA_21_TEST_FILE_NAME = "testEastadl21.eaxml";

	public AbstractEastadl21TestCase() {
		super(Eastadl21Package.eINSTANCE, new Eastadl21ResourceFactoryImpl());
	}

	@Override
	protected Plugin getTestPlugin() {
		return Activator.getPlugin();
	}

}