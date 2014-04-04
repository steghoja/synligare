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
import org.eclipse.eatop.eastadl21.Mode;
import org.eclipse.eatop.eastadl21.ModeGroup;
import org.eclipse.eatop.eastadl21.util.Eastadl21Factory;
import org.eclipse.sphinx.testutils.EcoreEqualityAssert;

public class EReferenceReferenced1100ManyTest extends AbstracteEastadl21SerializationTestCase {

	@SuppressWarnings("nls")
	// FunctionBehavior.mode
	public void testFunctionBehavior_mode() throws Exception {
		EAXML eaxml = Eastadl21Factory.eINSTANCE.createEAXML();
		EAPackage packageA = Eastadl21Factory.eINSTANCE.createEAPackage();
		packageA.setShortName("packageA");

		FunctionBehavior functionBehaviorA = Eastadl21Factory.eINSTANCE.createFunctionBehavior();
		functionBehaviorA.setShortName("functionBehaviorA");
		functionBehaviorA.setPath("pathA");
		functionBehaviorA.setRepresentation(FunctionBehaviorKind.SIMULINK);

		ModeGroup modeGroupA = Eastadl21Factory.eINSTANCE.createModeGroup();
		modeGroupA.setShortName("modeGroupA");
		modeGroupA.setText("textA");
		modeGroupA.setPrecondition("preConditionA");

		Mode modeA = Eastadl21Factory.eINSTANCE.createMode();
		modeA.setShortName("modeA");
		modeA.setCondition("conditionA");
		Mode modeB = Eastadl21Factory.eINSTANCE.createMode();
		modeB.setShortName("modeB");
		modeB.setCondition("conditionB");
		modeGroupA.getMode().add(modeA);
		modeGroupA.getMode().add(modeB);

		functionBehaviorA.getMode().add(modeA);
		functionBehaviorA.getMode().add(modeB);
		packageA.gGetElement().add(functionBehaviorA);
		packageA.gGetElement().add(modeGroupA);
		eaxml.getTopLevelPackage().add(packageA);
		saveWorkingFile(EA_21_TEST_FILE_NAME, eaxml);

		// Read and check
		EAXML eaxml2 = (EAXML) loadWorkingFile(EA_21_TEST_FILE_NAME);
		EcoreEqualityAssert.assertEquals(eaxml, eaxml2);
	}
}
