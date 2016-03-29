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
package org.eclipse.eatop.metamodelgen.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class ModelUtil {

	/**
	 * Returns <code>true</code> if the given eElement or one of its ascendant's has the given stereotype.
	 * 
	 * @param eElement
	 * @param stereoType
	 * @return <code>true</code> if the given eElement or one of its ascendant's has the given stereotype.
	 */
	public static boolean hasStereotype(EModelElement eElement, String stereoType) {

		for (String st : getAllStereotypes(eElement)) {
			if (st.equals(stereoType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines stereotype of a model element.
	 * 
	 * @param eElement
	 *            which is assigned to 0 or more stereotypes
	 * @return Stereotype name or "".
	 */
	public static String getStereotype(EModelElement eElement) {
		String stereotype = EcoreUtil.getAnnotation(eElement, IEASTADLConstants.ANNOTATION_STEREOTYPE, IEASTADLConstants.ANNOTATION_STEREOTYPE);

		return stereotype == null ? "" : stereotype; //$NON-NLS-1$
	}

	/**
	 * Returns all stereotypes of a model element
	 * 
	 * @param eElement
	 *            which is assigned to zero or more stereotypes
	 * @return String[] with Stereotypes or an empty String[]
	 */
	public static String[] getStereotypes(EModelElement eElement) {
		String list = EcoreUtil.getAnnotation(eElement, IEASTADLConstants.ANNOTATION_STEREOTYPE, IEASTADLConstants.ANNOTATION_STEREOTYPE);

		String[] stereotypes = {};
		if (list != null) {
			stereotypes = list.split(","); //$NON-NLS-1$
		}

		return stereotypes;
	}

	/**
	 * Returns the stereotypes of the supplied model element and its ascendant's
	 * 
	 * @param stereotypes
	 *            Set<String> which buffers all the stereotypes
	 * @param eElement
	 *            which is assigned to zero or more stereotypes
	 */
	private static void getAllStereotypes(Set<String> stereotypes, EModelElement eElement) {
		stereotypes.addAll(Arrays.asList(getStereotypes(eElement)));

		if (eElement instanceof EClass) {
			EClass eclass = (EClass) eElement;
			for (EClass supertype : eclass.getEAllSuperTypes()) {
				getAllStereotypes(stereotypes, supertype);
			}
		}
	}

	/**
	 * Returns the stereotypes of the supplied model element and its super types
	 * 
	 * @param eElement
	 *            which is assigned to zero or more stereotypes
	 * @return String[] which contains the assigned stereotypes of the supplied model element and its super types
	 */
	public static String[] getAllStereotypes(EModelElement eElement) {
		Set<String> stereotypes = new HashSet<String>();
		if (eElement != null) {
			getAllStereotypes(stereotypes, eElement);
		}
		return stereotypes.toArray(new String[stereotypes.size()]);
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
		EcoreUtil.setAnnotation(eElement, IEASTADLConstants.ANNOTATION_STEREOTYPE, IEASTADLConstants.ANNOTATION_STEREOTYPE, stereotype);
	}

	/**
	 * Assigns tagged value to given model element.
	 * 
	 * @param eElement
	 *            element to be modified.
	 * @param tag
	 *            Tag to be set.
	 * @param value
	 *            Value of tag.
	 */
	public static void setTaggedValue(EModelElement eElement, String tag, String value) {
		EcoreUtil.setAnnotation(eElement, IEASTADLConstants.ANNOTATION_TAGGED_VALUES, tag, value);
	}

	/**
	 * Returns the tagged value of the given model element or null if none is set.
	 * 
	 * @param eElement
	 *            Model element to examine.
	 * @param tag
	 *            Tag to look for.
	 * @return Value for given tag. If not <code>null</code> it's not empty.
	 */
	public static final String getTaggedValue(EModelElement eElement, String tag) {

		if (eElement == null) {
			throw new IllegalArgumentException("eElement is null"); //$NON-NLS-1$
		}
		if (tag == null) {
			throw new IllegalArgumentException("tag is null"); //$NON-NLS-1$
		}

		String result = EcoreUtil.getAnnotation(eElement, IEASTADLConstants.ANNOTATION_TAGGED_VALUES, tag);
		if (result != null && result.length() == 0) {
			result = null;
		}
		return result;
	}

	/**
	 * Adds single default value for a tag that has not been explicitly defined on given element.
	 * 
	 * @param eClass
	 *            Model element under examination.
	 * @param tag
	 *            Name of tagged value.
	 * @param defValue
	 *            Default value to be used if tag does not yet exist.
	 */
	public static void addDefaultTaggedValue(ENamedElement eElement, String tag, String defValue) {
		// check tagged value existence:
		String val = getTaggedValue(eElement, tag);

		// set to default value if not yet specified in model:
		if (val == null) {
			setTaggedValue(eElement, tag, defValue);
		}
	}

	/**
	 * Returns the documentation of the given element
	 * 
	 * @param eElement
	 * @return documentation of the given element
	 */
	public static final String getDocumentation(EModelElement eElement) {
		return EcoreUtil.getDocumentation(eElement);
	}

	/**
	 * Returns a sorted list of all concrete sub classes (direct and indirect) of the given baseClass.
	 * 
	 * @param baseClass
	 *            Super class of returned sub classes
	 * @return list of concrete sub classes.
	 */
	public static final List<EClass> getConcreteSubclasses(EClass baseClass) {
		return getSubclasses(baseClass, true);
	}

	/**
	 * Returns a sorted array of all concrete sub classes (direct and indirect) of the given baseClass.
	 * 
	 * @param baseClass
	 *            Super class of returned sub classes
	 * @return list of sub classes.
	 */
	public static final List<EClass> getSubclasses(EClass baseClass) {
		return getSubclasses(baseClass, false);
	}

	/**
	 * Returns a sorted array of all concrete sub classes (direct and indirect) of the given baseClass.
	 * 
	 * @param baseClass
	 *            Super class of returned sub classes
	 * @return list of sub classes.
	 */
	public static final List<EClass> getSubclasses(EClass baseClass, boolean concrete) {

		TreeSet<EClass> subClasses = new TreeSet<EClass>(new Comparator<EClass>() {
			@Override
			public int compare(EClass o1, EClass o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		Iterator<EObject> iter = getRootPackage(baseClass).eAllContents();

		while (iter.hasNext()) {
			EObject o = iter.next();
			if (o instanceof EClass) {
				EClass eClass = (EClass) o;
				if (baseClass.isSuperTypeOf(eClass) && (!eClass.isAbstract() || !concrete)) {
					subClasses.add(eClass);
				}
			}
		}

		return new ArrayList<EClass>(subClasses);

	}

	/**
	 * Returns the root package of the model
	 * 
	 * @param eClassifier
	 *            where the search begins
	 * @return root package of the model
	 */
	private static final EPackage getRootPackage(EClassifier eClassifier) {

		EPackage root = eClassifier.getEPackage();
		EPackage ePackage = eClassifier.getEPackage();

		while (ePackage != null) {
			root = ePackage;
			ePackage = ePackage.getESuperPackage();
		}

		return root;
	}

	/**
	 * Returns all base classes of the given class.
	 * 
	 * @param eClass
	 *            The super classes of this EClass will be returned
	 * @return List of all base classes
	 */
	public static List<EClass> getBaseClasses(EClass eClass) {
		Set<EClass> baseClasses = new LinkedHashSet<EClass>();
		getBaseClasses(eClass, baseClasses);
		return new LinkedList<EClass>(baseClasses);
	}

	/**
	 * Adds all direct and indirect base classes to the given class set.
	 * 
	 * @param eClass
	 *            The super classes of this EClass will be add to the set
	 * @param classSet
	 *            Set which buffers the super classes.
	 */
	private static void getBaseClasses(EClass eClass, Set<EClass> classSet) {

		LinkedList<EClass> baseClasses = new LinkedList<EClass>(eClass.getESuperTypes());

		if (baseClasses.isEmpty()) {
			return;
		}

		// iterate over all base classes
		for (EClass baseClass : baseClasses) {
			EObject rootContainer = EcoreUtil.getRootContainer(baseClass);
			if (rootContainer instanceof ENamedElement && ((ENamedElement) rootContainer).getName().equals(IEASTADLConstants.GEASTADL_ROOT_PKG_NAME)) {
				continue;
			}
			// recursively add all base classes of the current base class to the set
			getBaseClasses(baseClass, classSet);
			// recursively add the current base class to the set
			classSet.add(baseClass);
		}
	}

	public static File findFileByName(File root, final String name) {
		File file = null;
		if (root.exists() && root.isDirectory()) {
			File[] files = root.listFiles(new FilenameFilter() {

				@Override
				public boolean accept(File arg0, String arg1) {
					return name.equals(arg1);
				}
			});
			if (files.length > 0) {
				file = files[0];
			}

			if (file == null) {
				files = root.listFiles(new FileFilter() {
					@Override
					public boolean accept(File arg0) {
						return arg0.isDirectory();
					}
				});
				if (files.length > 0) {
					for (File nRoot : files) {
						file = findFileByName(nRoot, name);
						if (file != null) {
							break;
						}
					}
				}
			}
		}

		return file;
	}
}
