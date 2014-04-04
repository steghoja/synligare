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

import org.eclipse.eatop.eastadl21.EADirectionKind;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.EAString;
import org.eclipse.eatop.eastadl21.EAStringValue;
import org.eclipse.eatop.eastadl21.EAXML;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionalDevice;
import org.eclipse.eatop.eastadl21.util.Eastadl21Factory;
import org.eclipse.sphinx.testutils.EcoreEqualityAssert;

public class EReferenceContained0101SingleTest extends AbstracteEastadl21SerializationTestCase {

	@SuppressWarnings("nls")
	// FunctionFlowPort.defaultValue
	public void testFunctionFlowPort_defaultValue() throws Exception {
		EAXML eaxml = Eastadl21Factory.eINSTANCE.createEAXML();
		EAPackage packageA = Eastadl21Factory.eINSTANCE.createEAPackage();
		packageA.setShortName("packageA");

		EAString strA = Eastadl21Factory.eINSTANCE.createEAString();
		strA.setShortName("strA");

		EAString strB = Eastadl21Factory.eINSTANCE.createEAString();
		strB.setShortName("strB");

		FunctionalDevice functionalDeviceA = Eastadl21Factory.eINSTANCE.createFunctionalDevice();
		functionalDeviceA.setShortName("functionalDeviceA");
		functionalDeviceA.setIsElementary(true);

		FunctionFlowPort functionFlowPortA = Eastadl21Factory.eINSTANCE.createFunctionFlowPort();
		functionFlowPortA.setShortName("functionFlowPortA");
		functionFlowPortA.setDirection(EADirectionKind.OUT);
		functionFlowPortA.setType(strB);

		EAStringValue stringValueA = Eastadl21Factory.eINSTANCE.createEAStringValue();
		stringValueA.setType(strA);
		stringValueA.setValue("valueA");

		functionFlowPortA.setDefaultValue(stringValueA);
		functionalDeviceA.getPort().add(functionFlowPortA);

		packageA.gGetElement().add(strA);
		packageA.gGetElement().add(strB);
		packageA.gGetElement().add(functionalDeviceA);
		eaxml.getTopLevelPackage().add(packageA);
		saveWorkingFile(EA_21_TEST_FILE_NAME, eaxml);

		// Read and check
		EAXML eaxml2 = (EAXML) loadWorkingFile(EA_21_TEST_FILE_NAME);
		EcoreEqualityAssert.assertEquals(eaxml, eaxml2);
	}
}
