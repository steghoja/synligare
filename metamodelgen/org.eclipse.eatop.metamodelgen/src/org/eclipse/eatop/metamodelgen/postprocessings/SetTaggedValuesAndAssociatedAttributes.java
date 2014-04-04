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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.eatop.eaadapter.util.SchemaUtil;
import org.eclipse.eatop.metamodelgen.util.IEASTADLConstants;
import org.eclipse.eatop.metamodelgen.util.ModelUtil;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.util.EcoreSwitch;

public class SetTaggedValuesAndAssociatedAttributes extends PostProcessingTemplate {

	protected Stack<String> containerNsPrefixStack = new Stack<String>();

	protected String containerNsPrefix = IEASTADLConstants.DEFAULT_NSPREFIX;

	protected Stack<String> containerNsUriStack = new Stack<String>();

	protected String defaultNsUri = null;
	protected String containerNsUri = IEASTADLConstants.DEFAULT_NSURI;
	protected String version = null;

	protected Set<String> patternCaseSet = new HashSet<String>();
	protected Map<String, Integer> patternCount = new HashMap<String, Integer>();

	public SetTaggedValuesAndAssociatedAttributes(String version) {

		defaultNsUri = IEASTADLConstants.DEFAULT_NSURI;
		this.version = version;

		if (version != null && version.length() > 0) {
			defaultNsUri = defaultNsUri + "/" + version; //$NON-NLS-1$
		}
		containerNsUri = defaultNsUri;
	}

	private EcoreSwitch<Object> eSwitch = new EcoreSwitch<Object>() {
		@Override
		public Object caseEPackage(EPackage object) {
			return configure(object);
		}

		@Override
		public Object caseEClass(EClass object) {
			return configure(object);
		}

		@Override
		public Object caseEStructuralFeature(EStructuralFeature object) {
			return configure(object);
		}

		@Override
		public Object caseEDataType(EDataType object) {
			return configure(object);
		}

		@Override
		public Object caseEEnumLiteral(EEnumLiteral object) {
			return configure(object);
		}

		@Override
		public Object caseEEnum(EEnum object) {
			return configure(object);
		}
	};

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
	protected void configureName(ENamedElement element) {

		String suffixSingualr = ""; //$NON-NLS-1$
		String suffixPlural = "S"; //$NON-NLS-1$

		// check if namedElement is an EReference
		if (element instanceof EReference) {
			EReference ref = (EReference) element;
			// check if reference is not a composition
			if (!ref.isContainment()) {
				// check if reference is a normal reference or a type reference
				if (IEASTADLConstants.STEREOTYPE_IS_OF_TYPE.equalsIgnoreCase(ModelUtil.getStereotype(ref))) {
					// is of type reference found
					suffixSingualr = IEASTADLConstants.SUFFIX_SINGULAR_TYPE_REF;
					suffixPlural = IEASTADLConstants.SUFFIX_PLURAL_TYPE_REF;
				} else {
					// normal ref
					suffixSingualr = IEASTADLConstants.SUFFIX_SINGULAR_REF;
					suffixPlural = IEASTADLConstants.SUFFIX_PLURAL_REF;
				}
			}
		}

		// set tagged values
		ModelUtil
				.addDefaultTaggedValue(element, IEASTADLConstants.TAGGED_VALUE_XML_NAME, SchemaUtil.buildXmlName(element.getName()) + suffixSingualr);

		ModelUtil.addDefaultTaggedValue(element, IEASTADLConstants.TAGGED_VALUE_XML_NAME_PLURAL, SchemaUtil.buildXmlName(element.getName())
				+ suffixPlural);

		ModelUtil.addDefaultTaggedValue(element, IEASTADLConstants.TAGGED_VALUE_XML_NS_PREFIX, containerNsPrefix);

		ModelUtil.addDefaultTaggedValue(element, IEASTADLConstants.TAGGED_VALUE_XML_NS_URI, containerNsUri);

	}

	/**
	 * Pushes current namespace data onto the stack and sets namespace data of the element as current namespace data
	 * 
	 * @param element
	 */
	protected void pushNs(ENamedElement element) {
		String nsPrefix = ModelUtil.getTaggedValue(element, IEASTADLConstants.TAGGED_VALUE_XML_NS_PREFIX);
		String nsUri = ModelUtil.getTaggedValue(element, IEASTADLConstants.TAGGED_VALUE_XML_NS_URI);

		containerNsPrefixStack.push(containerNsPrefix);
		containerNsPrefix = nsPrefix;

		containerNsUriStack.push(containerNsUri);
		containerNsUri = nsUri;
	}

	/**
	 * Pops namespace data from stack and sets them as current namespace data. If stack is empty then the default
	 * namespace data will be used.
	 */
	protected void popNs() {
		if (containerNsPrefixStack.isEmpty()) {
			containerNsPrefix = IEASTADLConstants.DEFAULT_NSPREFIX;
		} else {
			containerNsPrefix = containerNsPrefixStack.pop();
		}

		if (containerNsUriStack.isEmpty()) {
			containerNsUri = defaultNsUri;
		} else {
			containerNsUri = containerNsUriStack.pop();
		}
	}

	/**
	 * Configures tagged values of a package.
	 * 
	 * @param ePackage
	 * @return ePackage
	 */
	private Object configure(EPackage ePackage) {
		configureName(ePackage);
		pushNs(ePackage);

		Iterator<EClassifier> clsIter = ePackage.getEClassifiers().iterator();
		while (clsIter.hasNext()) {
			eSwitch.doSwitch(clsIter.next());
		}

		Iterator<EPackage> subPckIter = ePackage.getESubpackages().iterator();
		while (subPckIter.hasNext()) {
			eSwitch.doSwitch(subPckIter.next());
		}

		popNs();
		return ePackage;
	}

	/**
	 * Configures tagged values of a EEnum
	 * 
	 * @param eEnum
	 * @return eEnum
	 */
	private Object configure(EEnum eEnum) {
		configureName(eEnum);
		pushNs(eEnum);

		Iterator<EEnumLiteral> iter = eEnum.getELiterals().iterator();
		while (iter.hasNext()) {
			eSwitch.doSwitch(iter.next());
		}

		popNs();
		return eEnum;
	}

	/**
	 * Configures tagged values of a EEnumLiteral
	 * 
	 * @param literal
	 * @return literal
	 */
	private Object configure(EEnumLiteral literal) {
		configureName(literal);
		return literal;
	}

	/**
	 * Configures tagged values of a primitive Datatype
	 * 
	 * @param dataType
	 * @return
	 */
	private Object configure(EDataType dataType) {
		configureName(dataType);
		return dataType;
	}

	/**
	 * Configures tagged values of a EClass
	 * 
	 * @param eClass
	 * @return eClass
	 */
	private Object configure(EClass eClass) {
		configureName(eClass);
		pushNs(eClass);

		Iterator<EStructuralFeature> iter = null;

		if (ModelUtil.getStereotype(eClass).equalsIgnoreCase(IEASTADLConstants.STEREOTYPE_ATPMIXED)
				|| ModelUtil.getStereotype(eClass).equalsIgnoreCase(IEASTADLConstants.STEREOTYPE_ATPMIXEDSTRING)) {

			iter = eClass.getEStructuralFeatures().iterator();
			while (iter.hasNext()) {
				EStructuralFeature feature = iter.next();

				feature.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
				feature.setLowerBound(0);

				ModelUtil.addDefaultTaggedValue(feature, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_WRAPPER_ELEMENT, Boolean.FALSE.toString());

				ModelUtil.addDefaultTaggedValue(feature, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_ELEMENT, Boolean.TRUE.toString());

				EClassifier eClassifier = feature.getEType();

				if (eClassifier instanceof EClass && hasConcreteSubclasses((EClass) eClassifier)) {
					// subclasses exists
					ModelUtil.addDefaultTaggedValue(feature, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_WRAPPER_ELEMENT, Boolean.FALSE.toString());

					ModelUtil.addDefaultTaggedValue(feature, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_ELEMENT, Boolean.TRUE.toString());

				} else {
					// subclasses exists
					ModelUtil.addDefaultTaggedValue(feature, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_WRAPPER_ELEMENT, Boolean.FALSE.toString());
					ModelUtil.addDefaultTaggedValue(feature, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_ELEMENT, Boolean.FALSE.toString());
				}

				// add pattern case
				String patternCase = getPatternCase(feature);
				patternCaseSet.add(patternCase);

				// add pattern map
				if (feature instanceof EReference) {
					String containment = getReferenceContainmentAndUpperBound((EReference) feature);
					String result = containment + " , " + patternCase; //$NON-NLS-1$
					if (patternCount.containsKey(result)) {
						Integer count = patternCount.get(result);
						patternCount.put(result, count + 1);
					} else {
						patternCount.put(result, 1);
					}
				} else if (feature instanceof EAttribute) {
					String upperBound = getAttributeUpperBound((EAttribute) feature);
					String result = upperBound + " , " + patternCase; //$NON-NLS-1$
					if (patternCount.containsKey(result)) {
						Integer count = patternCount.get(result);
						patternCount.put(result, count + 1);
					} else {
						patternCount.put(result, 1);
					}
				}
			}
		}

		iter = eClass.getEStructuralFeatures().iterator();
		while (iter.hasNext()) {
			eSwitch.doSwitch(iter.next());
		}

		popNs();
		return eClass;
	}

	/**
	 * configures how a structural features (which is an Ecore EReference or an EAttribute or a UML Property) is mapped
	 * to XML. The following tagged values are set:
	 * <ul>
	 * <li>xml.roleWrapperElement</li>
	 * <li>xml.roleElement</li>
	 * <li>xml.typeWrapperElement</li>
	 * <li>xml.typeElement</li>
	 * </ul>
	 * 
	 * @param eStructuralFeature
	 */
	protected void configureElementMapping(EStructuralFeature eStructuralFeature) {

		boolean isContainment = eStructuralFeature instanceof EAttribute || ((EReference) eStructuralFeature).isContainment();

		// role
		String roleWrapperElement;
		String roleElement;
		if (eStructuralFeature.getUpperBound() > 1 || eStructuralFeature.getUpperBound() == ETypedElement.UNBOUNDED_MULTIPLICITY) {
			roleWrapperElement = Boolean.TRUE.toString();

			// for references, a role wrapper is default (the -REF element):
			roleElement = Boolean.toString(!isContainment);
		} else {
			roleWrapperElement = Boolean.FALSE.toString();
			roleElement = Boolean.TRUE.toString();
		}

		ModelUtil.addDefaultTaggedValue(eStructuralFeature, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_ELEMENT, roleElement);

		ModelUtil.addDefaultTaggedValue(eStructuralFeature, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_WRAPPER_ELEMENT, roleWrapperElement);

		// type:
		String typeElement;
		if (isContainment && eStructuralFeature.getEType() instanceof EClass) {

			if (hasConcreteSubclasses((EClass) eStructuralFeature.getEType())
					|| eStructuralFeature.getUpperBound() > 1
					|| eStructuralFeature.getUpperBound() == ETypedElement.UNBOUNDED_MULTIPLICITY
					|| IConstants.TRUE.equalsIgnoreCase(ModelUtil.getTaggedValue(eStructuralFeature.getEType(),
							IEASTADLConstants.TAGGED_VALUE_EXTENSION_POINT))) {
				typeElement = Boolean.TRUE.toString();
			} else {
				typeElement = Boolean.FALSE.toString();
			}
		} else {
			// dirty hack, refinement required for primitive datatypes, etc.
			typeElement = Boolean.FALSE.toString();
		}

		ModelUtil.addDefaultTaggedValue(eStructuralFeature, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_ELEMENT, typeElement);

		ModelUtil.addDefaultTaggedValue(eStructuralFeature, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_WRAPPER_ELEMENT, Boolean.FALSE.toString());

		// add pattern case
		String patternCase = getPatternCase(eStructuralFeature);
		patternCaseSet.add(patternCase);

		// add pattern map
		if (eStructuralFeature instanceof EReference) {
			String containment = getReferenceContainmentAndUpperBound((EReference) eStructuralFeature);
			String result = containment + " , " + patternCase; //$NON-NLS-1$
			if (patternCount.containsKey(result)) {
				Integer count = patternCount.get(result);
				patternCount.put(result, count + 1);
			} else {
				patternCount.put(result, 1);
			}
		} else if (eStructuralFeature instanceof EAttribute) {
			String upperBound = getAttributeUpperBound((EAttribute) eStructuralFeature);
			String result = upperBound + " , " + patternCase; //$NON-NLS-1$
			if (patternCount.containsKey(result)) {
				Integer count = patternCount.get(result);
				patternCount.put(result, count + 1);
			} else {
				patternCount.put(result, 1);
			}
		}
	}

	/**
	 * Configures tagged values of a EStructuralFeature
	 * 
	 * @param feature
	 * @return feature
	 */
	protected Object configure(EStructuralFeature feature) {
		configureName(feature);
		pushNs(feature);
		configureElementMapping(feature);
		popNs();
		return feature;
	}

	@Override
	public void execute() {
		configure(model);
		// print pattern Case: 0023, 0013, 0012, 0010, Not allowed
		for (String patternCase : patternCaseSet) {
			System.out.print("\n Pattern case = " + patternCase); //$NON-NLS-1$
		}

		// print
		for (String pattern : patternCount.keySet()) {
			System.out.print("\n Pattern = " + pattern + " , count = " + patternCount.get(pattern)); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * Checks if a EClass has concrete sub classes.
	 * 
	 * @param eClass
	 *            super class
	 * @return true if eClass has sub classes
	 */
	private boolean hasConcreteSubclasses(EClass eClass) {
		Iterator<EObject> iterator = model.eAllContents();
		while (iterator.hasNext()) {
			EObject element = iterator.next();
			if (element instanceof EClass) {
				EClass potentialSubclass = (EClass) element;
				if (!potentialSubclass.isAbstract() && potentialSubclass.getESuperTypes().contains(eClass)) {
					return true;
				}
			}
		}
		return false;
	}

	private String getPatternCase(EModelElement eElement) {

		String roleWrapperElement = ModelUtil.getTaggedValue(eElement, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_WRAPPER_ELEMENT);
		String roleElement = ModelUtil.getTaggedValue(eElement, IEASTADLConstants.TAGGED_VALUE_XML_ROLE_ELEMENT);
		String typeWrapperElement = ModelUtil.getTaggedValue(eElement, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_WRAPPER_ELEMENT);
		String typeElement = ModelUtil.getTaggedValue(eElement, IEASTADLConstants.TAGGED_VALUE_XML_TYPE_ELEMENT);
		String patternCase = "Not allowed"; //$NON-NLS-1$

		if (roleWrapperElement.equals(IConstants.TRUE)) {// 1xxx
			if (roleElement.equals(IConstants.TRUE)) {// 11xx
				if (typeWrapperElement.equals(IConstants.TRUE)) {// 111x
					if (typeElement.equals(IConstants.TRUE)) {// 1111
						patternCase = "0008"; //$NON-NLS-1$
					} else {// 1110
						patternCase = "Not allowed1"; //$NON-NLS-1$
					}
				} else {// 110x
					if (typeElement.equals(IConstants.TRUE)) {// 1101
						patternCase = "0009"; //$NON-NLS-1$
					} else {// 1100
						patternCase = "0023"; //$NON-NLS-1$
					}
				}
			} else {// 10xx
				if (typeWrapperElement.equals(IConstants.TRUE)) {// 101x
					if (typeElement.equals(IConstants.TRUE)) {// 1011
						patternCase = "0022"; //$NON-NLS-1$
					} else {// 1010
						patternCase = "Not allowed2"; //$NON-NLS-1$
					}
				} else {// 100x
					if (typeElement.equals(IConstants.TRUE)) {// 1001
						patternCase = "0010"; //$NON-NLS-1$
					} else {// 1000
						patternCase = "Not allowed3"; //$NON-NLS-1$
					}
				}
			}
		} else {// 0xxx
			if (roleElement.equals(IConstants.TRUE)) {// 01xx
				if (typeWrapperElement.equals(IConstants.TRUE)) {// 011x
					if (typeElement.equals(IConstants.TRUE)) {// 0111
						patternCase = "0011"; //$NON-NLS-1$
					} else {// 0110
						patternCase = "Not allowed4"; //$NON-NLS-1$
					}
				} else {// 010x
					if (typeElement.equals(IConstants.TRUE)) {// 0101
						patternCase = "0012"; //$NON-NLS-1$
					} else {// 0100
						patternCase = "0013"; //$NON-NLS-1$
					}
				}
			} else {// 00xx
				if (typeWrapperElement.equals(IConstants.TRUE)) {// 001x
					if (typeElement.equals(IConstants.TRUE)) {// 0011
						patternCase = "0014"; //$NON-NLS-1$
					} else {// 0010
						patternCase = "Not allowed5"; //$NON-NLS-1$
					}
				} else {// 000x
					if (typeElement.equals(IConstants.TRUE)) {// 0001
						patternCase = "0015"; //$NON-NLS-1$
					} else {// 0000
						patternCase = "0016"; //$NON-NLS-1$
					}
				}
			}
		}

		return patternCase;
	}

	private String getReferenceContainmentAndUpperBound(EReference feature) {
		String result;
		Boolean isContainment = feature.isContainment();
		int upperBound = feature.getUpperBound();
		if (upperBound == 1) {
			result = "EReference , " + isContainment + " , 1"; //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			result = "EReference , " + isContainment + " , >1"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		return result;
	}

	private String getAttributeUpperBound(EAttribute feature) {
		String result;
		int upperBound = feature.getUpperBound();
		if (upperBound == 1) {
			result = "EAttribute , n/a, 1"; //$NON-NLS-1$
		} else {
			result = "EAttribute , n/a, >1"; //$NON-NLS-1$
		}
		return result;
	}

}
