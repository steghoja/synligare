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

import org.eclipse.eatop.eastadl21.AgeConstraint;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAString;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.EventChain;
import org.eclipse.eatop.eastadl21.ModeEvent;
import org.eclipse.eatop.eastadl21.Timing;
import org.eclipse.eatop.eastadl21.TimingExpression;
import org.eclipse.eatop.eastadl21.util.Eastadl21Factory;
import org.eclipse.sphinx.testutils.EcoreEqualityAssert;

public class EReferenceContained0100SingleTest extends AbstracteEastadl21SerializationTestCase {

	@SuppressWarnings("nls")
	// AgeConstraint.minimum
	public void testAgeConstraint_minimum() throws Exception {
		EAXML eaxml = Eastadl21Factory.eINSTANCE.createEAXML();
		EAPackage packageA = Eastadl21Factory.eINSTANCE.createEAPackage();
		packageA.setShortName("packageA");

		EAString strA = Eastadl21Factory.eINSTANCE.createEAString();
		strA.setShortName("strA");

		EAString strB = Eastadl21Factory.eINSTANCE.createEAString();
		strB.setShortName("strB");

		Timing timingA = Eastadl21Factory.eINSTANCE.createTiming();
		timingA.setShortName("timingA");

		EventChain eventChainA = Eastadl21Factory.eINSTANCE.createEventChain();
		eventChainA.setShortName("eventChainA");

		ModeEvent modeEventA = Eastadl21Factory.eINSTANCE.createModeEvent();
		modeEventA.setShortName("modeEventA");
		ModeEvent modeEventB = Eastadl21Factory.eINSTANCE.createModeEvent();
		modeEventB.setShortName("modeEventB");

		eventChainA.setResponse(modeEventA);
		eventChainA.setStimulus(modeEventB);
		timingA.getDescription().add(eventChainA);
		timingA.getDescription().add(modeEventA);
		timingA.getDescription().add(modeEventB);

		Timing timingB = Eastadl21Factory.eINSTANCE.createTiming();
		timingB.setShortName("timingB");

		AgeConstraint ageConstraintA = Eastadl21Factory.eINSTANCE.createAgeConstraint();
		ageConstraintA.setShortName("ageConstraintA");

		TimingExpression minExp = Eastadl21Factory.eINSTANCE.createTimingExpression();
		minExp.setType(strA);
		TimingExpression maxExp = Eastadl21Factory.eINSTANCE.createTimingExpression();
		maxExp.setType(strB);

		ageConstraintA.setMinimum(minExp);
		ageConstraintA.setMaximum(maxExp);
		ageConstraintA.setScope(eventChainA);

		timingB.getDescription().add(eventChainA);
		timingB.getConstraint().add(ageConstraintA);

		packageA.gGetElement().add(strA);
		packageA.gGetElement().add(strB);
		packageA.gGetElement().add(timingA);
		packageA.gGetElement().add(timingB);
		eaxml.getTopLevelPackage().add(packageA);
		saveWorkingFile(EA_21_TEST_FILE_NAME, eaxml);

		// Read and check
		EAXML eaxml2 = (EAXML) loadWorkingFile(EA_21_TEST_FILE_NAME);
		EcoreEqualityAssert.assertEquals(eaxml, eaxml2);
	}
}
