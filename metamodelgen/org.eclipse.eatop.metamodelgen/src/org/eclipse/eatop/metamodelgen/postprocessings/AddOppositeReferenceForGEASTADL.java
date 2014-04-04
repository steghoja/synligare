/**
 * <copyright>
 * 
 * Copyright (c) 2014 Continental AG and others.
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 
 * which accompanies this distribution, and is
 * available at http://www.eclipse.org/org/documents/epl-v10.php
 * 
 * Contributors: 
 *     Continental AG - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.eatop.metamodelgen.postprocessings;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

public class AddOppositeReferenceForGEASTADL extends PostProcessingTemplate {

	/**
	 * This method is used to add a reference from EAPackableElement to EAPackage This reference is necessary for
	 * GEASTADL. If we won't add it, the code can't be generated.
	 */

	@Override
	public void execute() throws Exception {
		/**
		 * Do this postprocessing step only if it is a east-adl meta-model
		 */
		EClass eaPackagableElement = (EClass) findEClassifierByName(IEASTADLConstants.EAPACKAGEABLE_ELEMENT);
		if (eaPackagableElement == null) {
			throw new Exception("Could not find class 'EAPackageableElement'"); //$NON-NLS-1$
		}

		EClass eaPackage = (EClass) findEClassifierByName(IEASTADLConstants.EAPACAKGE);
		if (eaPackage == null) {
			throw new Exception("Could not find class 'EAPackage'"); //$NON-NLS-1$
		}

		EReference elementReference = (EReference) eaPackage.getEStructuralFeature(IEASTADLConstants.ELEMENT);
		if (elementReference == null) {
			throw new Exception("Could not find reference 'element' from EAPackage to EAPackagbleElement"); //$NON-NLS-1$
		}

		if (elementReference.getEOpposite() != null) {
			Assert.isTrue(
					elementReference.getEOpposite().getName().equals(IEASTADLConstants.EAPACKAGE_ELEMENT),
					String.format(
							"We have a opposite reference for association 'element' (EAPackage to EAPackageableElement), but the name is not equal to 'EAPackage_element', instead it is '%s'. However we need it for the generation process.", //$NON-NLS-1$
							elementReference.getEOpposite().getName()));
		} else {
			EReference opposite = ecoreFactory.createEReference();
			opposite.setName(IEASTADLConstants.EAPACKAGE_ELEMENT);
			opposite.setTransient(true);
			opposite.setLowerBound(0);
			opposite.setUpperBound(1);
			opposite.setEType(eaPackage);
			eaPackagableElement.getEStructuralFeatures().add(opposite);
			opposite.setEOpposite(elementReference);
			elementReference.setEOpposite(opposite);
		}
	}

}
