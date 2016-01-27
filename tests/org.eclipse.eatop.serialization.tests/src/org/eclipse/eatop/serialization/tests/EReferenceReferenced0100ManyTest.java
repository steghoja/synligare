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
import org.eclipse.eatop.eastadl21.QualityRequirement;
import org.eclipse.eatop.eastadl21.QualityRequirementKind;
import org.eclipse.eatop.eastadl21.Refine;
import org.eclipse.eatop.eastadl21.Refine_refinedBy;
import org.eclipse.eatop.eastadl21.util.Eastadl21Factory;
import org.eclipse.sphinx.testutils.EcoreEqualityAssert;

public class EReferenceReferenced0100ManyTest extends AbstracteEastadl21SerializationTestCase {

	@SuppressWarnings("nls")
	// Refine_refinedBy.identifiable_context
	public void testRefinerefinedBy_identifiablecontext() throws Exception {
		EAXML eaxml = Eastadl21Factory.eINSTANCE.createEAXML();
		EAPackage packageA = Eastadl21Factory.eINSTANCE.createEAPackage();
		packageA.setShortName("packageA");

		FunctionBehavior functionBehaviorA = Eastadl21Factory.eINSTANCE.createFunctionBehavior();
		functionBehaviorA.setShortName("functionBehaviorA");
		functionBehaviorA.setPath("pathA");
		functionBehaviorA.setRepresentation(FunctionBehaviorKind.UML);

		Refine refineA = Eastadl21Factory.eINSTANCE.createRefine();
		refineA.setShortName("refineA");

		Behavior behaviorA = Eastadl21Factory.eINSTANCE.createBehavior();
		behaviorA.setShortName("behaviorA");

		FunctionBehavior functionBehaviorB = Eastadl21Factory.eINSTANCE.createFunctionBehavior();
		functionBehaviorB.setShortName("functionBehaviorB");
		functionBehaviorB.setPath("pathB");
		functionBehaviorB.setRepresentation(FunctionBehaviorKind.MARTE);

		QualityRequirement qualityRequirementA = Eastadl21Factory.eINSTANCE.createQualityRequirement();
		qualityRequirementA.setShortName("qualityRequirementA");
		qualityRequirementA.setKind(QualityRequirementKind.SAFETY);

		Refine_refinedBy refinedByA = Eastadl21Factory.eINSTANCE.createRefine_refinedBy();
		refinedByA.setIdentifiable_target(functionBehaviorB);
		refinedByA.getIdentifiable_context().add(behaviorA);
		refinedByA.getIdentifiable_context().add(functionBehaviorB);

		refineA.getRefinedRequirement().add(qualityRequirementA);
		refineA.getRefinedBy().add(refinedByA);
		functionBehaviorA.getOwnedRelationship().add(refineA);

		packageA.gGetElement().add(functionBehaviorA);
		packageA.gGetElement().add(behaviorA);
		packageA.gGetElement().add(functionBehaviorB);
		packageA.gGetElement().add(qualityRequirementA);
		eaxml.getTopLevelPackage().add(packageA);
		saveWorkingFile(EA_21_TEST_FILE_NAME, eaxml);

		// Read and check
		EAXML eaxml2 = (EAXML) loadWorkingFile(EA_21_TEST_FILE_NAME);
		EcoreEqualityAssert.assertEquals(eaxml, eaxml2);
	}
}