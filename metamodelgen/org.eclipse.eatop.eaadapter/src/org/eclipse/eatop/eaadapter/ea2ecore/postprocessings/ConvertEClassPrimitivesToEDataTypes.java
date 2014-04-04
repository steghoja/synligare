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

package org.eclipse.eatop.eaadapter.ea2ecore.postprocessings;

import java.util.Collection;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;

public class ConvertEClassPrimitivesToEDataTypes extends PostProcessingTemplate {
	/* Package name that contains the primitive data types */
	protected String PRIMITIVE_TYPES = IConstants.PACKAGE_PRIMITIVETYPES;

	public ConvertEClassPrimitivesToEDataTypes() {
		super();
	}

	public String getPackageName() {
		return PRIMITIVE_TYPES;
	}

	@Override
	public void execute() {
		EPackage pkg = findEPackageByName(getPackageName());

		if (pkg != null) {
			EList<EClassifier> eClassifiers = pkg.getEClassifiers();
			EList<EClass> eClasses = getAllEClassesFromClassifiers(eClassifiers);

			for (EClass eClass : eClasses) {
				EClassifier primitiveType = createPrimitiveTypeFromEClass(eClass);
				eClassifiers.add(primitiveType);

				TreeIterator<EObject> iter = model.eAllContents();
				while (iter.hasNext()) {
					EObject element = iter.next();
					if (element instanceof EReference) {
						EReference eRef = (EReference) element;
						if (eRef.getEType() == eClass) {
							EClass referencingEClass = (EClass) eRef.eContainer();
							referencingEClass.getEStructuralFeatures().remove(eRef);

							EAttribute attribute = EcoreFactory.eINSTANCE.createEAttribute();
							attribute.setName(eRef.getName());
							attribute.setEType(primitiveType);
							attribute.getEAnnotations().addAll(eRef.getEAnnotations());

							referencingEClass.getEStructuralFeatures().add(attribute);
						}
					}
				}
				eClassifiers.remove(eClass);
			}
		}
	}

	private EClassifier createPrimitiveTypeFromEClass(EClass eClass) {
		EDataType primitiveType = EcoreFactory.eINSTANCE.createEDataType();
		primitiveType.setName(eClass.getName());
		primitiveType.setInstanceTypeName(eClass.getName());
		primitiveType.getEAnnotations().addAll(eClass.getEAnnotations());
		return primitiveType;
	}

	private EList<EClass> getAllEClassesFromClassifiers(Collection<EClassifier> eClassifiers) {
		EList<EClass> classes = new BasicEList<EClass>();
		for (EClassifier classifier : eClassifiers) {
			if (classifier instanceof EClass) {
				classes.add((EClass) classifier);
			}
		}
		return classes;
	}
}
