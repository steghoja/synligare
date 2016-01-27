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

import org.eclipse.eatop.eastadl21.Behavior;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.FunctionBehavior;
import org.eclipse.eatop.eastadl21.FunctionBehaviorKind;
import org.eclipse.eatop.eastadl21.util.Eastadl21Factory;
import org.eclipse.sphinx.testutils.EcoreEqualityAssert;

public class EReferenceContained1001ManyTest extends AbstracteEastadl21SerializationTestCase {

	@SuppressWarnings("nls")
	// Behavior.behavior
	public void testBehavior_behavior() throws Exception {
		EAXML eaxml = Eastadl21Factory.eINSTANCE.createEAXML();
		EAPackage packageA = Eastadl21Factory.eINSTANCE.createEAPackage();
		packageA.setShortName("packageA");

		Behavior behaviorA = Eastadl21Factory.eINSTANCE.createBehavior();
		behaviorA.setShortName("behaviorA");

		FunctionBehavior functionBehaviorA = Eastadl21Factory.eINSTANCE.createFunctionBehavior();
		functionBehaviorA.setShortName("behaviorFunctionBehaviorA");
		functionBehaviorA.setPath("pathA");
		functionBehaviorA.setRepresentation(FunctionBehaviorKind.SIMULINK);

		FunctionBehavior functionBehaviorB = Eastadl21Factory.eINSTANCE.createFunctionBehavior();
		functionBehaviorB.setShortName("behaviorFunctionBehaviorB");
		functionBehaviorB.setPath("pathB");
		functionBehaviorB.setRepresentation(FunctionBehaviorKind.SIMULINK);

		behaviorA.getBehavior().add(functionBehaviorA);
		behaviorA.getBehavior().add(functionBehaviorB);
		packageA.gGetElement().add(behaviorA);
		eaxml.getTopLevelPackage().add(packageA);
		saveWorkingFile(EA_21_TEST_FILE_NAME, eaxml);

		// Read and check
		EAXML eaxml2 = (EAXML) loadWorkingFile(EA_21_TEST_FILE_NAME);
		EcoreEqualityAssert.assertEquals(eaxml, eaxml2);
	}
}
