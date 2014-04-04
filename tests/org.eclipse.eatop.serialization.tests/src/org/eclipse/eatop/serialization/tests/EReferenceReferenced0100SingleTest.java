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

import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.FunctionBehavior;
import org.eclipse.eatop.eastadl21.FunctionBehaviorKind;
import org.eclipse.eatop.eastadl21.FunctionalDevice;
import org.eclipse.eatop.eastadl21.util.Eastadl21Factory;
import org.eclipse.sphinx.testutils.EcoreEqualityAssert;

public class EReferenceReferenced0100SingleTest extends AbstracteEastadl21SerializationTestCase {

	@SuppressWarnings("nls")
	// FunctionBehavior.function
	public void testFunctionBehavior_function() throws Exception {
		EAXML eaxml = Eastadl21Factory.eINSTANCE.createEAXML();
		EAPackage packageA = Eastadl21Factory.eINSTANCE.createEAPackage();
		packageA.setShortName("packageA");

		FunctionBehavior functionBehaviorA = Eastadl21Factory.eINSTANCE.createFunctionBehavior();
		functionBehaviorA.setShortName("functionBehaviorA");
		functionBehaviorA.setPath("pathA");
		functionBehaviorA.setRepresentation(FunctionBehaviorKind.SIMULINK);

		FunctionalDevice functionalDeviceA = Eastadl21Factory.eINSTANCE.createFunctionalDevice();
		functionalDeviceA.setShortName("functionalDeviceA");
		functionalDeviceA.setIsElementary(true);

		functionBehaviorA.setFunction(functionalDeviceA);

		packageA.gGetElement().add(functionBehaviorA);
		packageA.gGetElement().add(functionalDeviceA);
		eaxml.getTopLevelPackage().add(packageA);
		saveWorkingFile(EA_21_TEST_FILE_NAME, eaxml);

		// Read and check
		EAXML eaxml2 = (EAXML) loadWorkingFile(EA_21_TEST_FILE_NAME);
		EcoreEqualityAssert.assertEquals(eaxml, eaxml2);
	}
}
