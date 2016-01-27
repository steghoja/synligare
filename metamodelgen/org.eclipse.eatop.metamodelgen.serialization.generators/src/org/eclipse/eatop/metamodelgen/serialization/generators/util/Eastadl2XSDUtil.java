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
package org.eclipse.eatop.metamodelgen.serialization.generators.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sphinx.emf.serialization.generators.util.Ecore2XSDUtil;
import org.eclipse.sphinx.emf.serialization.generators.util.IGeneratorConstants;

public class Eastadl2XSDUtil {

	public static String getInstanceRefClassName(EClass eClass) {
		String name = eClass.getName();

		// is stereotype instanceRef applied to eClass
		String stereotype = EcoreUtil.getAnnotation(eClass, ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
		if (ITaggedValueConstants.STEREOTYPE_INSTANCE_REF.equals(stereotype)) {
			// set XML name of instance ref class
			if (null != name) {
				// split at "_"
				String[] parts = name.split("_"); //$NON-NLS-1$
				StringBuffer buffer = new StringBuffer();
				for (int i = 0; i < parts.length; i++) {
					buffer.append(Ecore2XSDUtil.buildXmlName(parts[i]));
					if (i != parts.length - 1) {
						// to not add "--" for last element
						buffer.append("--"); //$NON-NLS-1$
					}
				}
				return buffer.toString();
			}
		}

		return Ecore2XSDUtil.buildXmlName(name);
	}

	public static String getInstanceRefClassNameSingle(EClass eClass) {
		String name = eClass.getName();

		// is stereotype instanceRef applied to eClass
		String stereotype = EcoreUtil.getAnnotation(eClass, ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
		if (ITaggedValueConstants.STEREOTYPE_INSTANCE_REF.equals(stereotype)) {
			return getInstanceRefClassName(eClass) + IGeneratorConstants.SUFFIX_NAME;
		}

		return Ecore2XSDUtil.buildXmlName(name);
	}

	public static String getInstanceRefClassNamePlural(EClass eClass) {
		String name = eClass.getName();

		// is stereotype instanceRef applied to eClass
		String stereotype = EcoreUtil.getAnnotation(eClass, ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE);
		if (ITaggedValueConstants.STEREOTYPE_INSTANCE_REF.equals(stereotype)) {
			return getInstanceRefClassName(eClass) + IGeneratorConstants.SUFFIX_NAME_PLURAL;
		}

		return Ecore2XSDUtil.buildXmlName(name);
	}

	/**
	 * Sets given stereotype of ecore model element, in form of an EAnnotation.
	 * 
	 * @param element
	 *            Hosting ecore model element.
	 * @param stereotype
	 *            Name of stereotype to apply.
	 */
	public static void setStereotype(EModelElement eElement, String stereotype) {
		EcoreUtil.setAnnotation(eElement, ITaggedValueConstants.ANNOTATION_STEREOTYPE, ITaggedValueConstants.ANNOTATION_STEREOTYPE, stereotype);
	}

	/**
	 * Sets the following tagged values of an ENamedElement
	 * <ul>
	 * <li>xml.name</li>
	 * <li>xml.namePlural</li>
	 * <li>xml.nsPrefix</li>
	 * <li>xml.nsUri</li>
	 * 
	 * @param element
	 */
	public static String getSingularName(ENamedElement element) {

		String suffixSingualr = ""; //$NON-NLS-1$

		// check if namedElement is an EReference
		if (element instanceof EReference) {
			EReference ref = (EReference) element;
			// check if reference is not a composition
			if (!ref.isContainment()) {
				// check if reference is a normal reference or a type reference
				String stereotype = EcoreUtil.getAnnotation(ref, ITaggedValueConstants.ANNOTATION_STEREOTYPE,
						ITaggedValueConstants.ANNOTATION_STEREOTYPE);
				if (ITaggedValueConstants.STEREOTYPE_IS_OF_TYPE.equalsIgnoreCase(stereotype)) {
					// is of type reference found
					suffixSingualr = IGeneratorConstants.SUFFIX_SINGULAR_TYPE_REF;
				} else {
					// normal ref
					suffixSingualr = IGeneratorConstants.SUFFIX_SINGULAR_REF;
				}
			}
		}

		return Ecore2XSDUtil.buildXmlName(element.getName()) + suffixSingualr;
	}

	/**
	 * Sets the following tagged values of an ENamedElement
	 * <ul>
	 * <li>xml.name</li>
	 * <li>xml.namePlural</li>
	 * <li>xml.nsPrefix</li>
	 * <li>xml.nsUri</li>
	 * 
	 * @param element
	 */
	public static String getPluralName(ENamedElement element) {

		String suffixPlural = "S"; //$NON-NLS-1$

		// check if namedElement is an EReference
		if (element instanceof EReference) {
			EReference ref = (EReference) element;
			// check if reference is not a composition
			if (!ref.isContainment()) {
				// check if reference is a normal reference or a type reference
				String stereotype = EcoreUtil.getAnnotation(ref, ITaggedValueConstants.ANNOTATION_STEREOTYPE,
						ITaggedValueConstants.ANNOTATION_STEREOTYPE);
				if (ITaggedValueConstants.STEREOTYPE_IS_OF_TYPE.equalsIgnoreCase(stereotype)) {
					// is of type reference found
					suffixPlural = IGeneratorConstants.SUFFIX_PLURAL_TYPE_REF;
				} else {
					// normal ref
					suffixPlural = IGeneratorConstants.SUFFIX_PLURAL_REF;
				}
			}
		}

		return Ecore2XSDUtil.buildXmlName(element.getName()) + suffixPlural;
	}

}
