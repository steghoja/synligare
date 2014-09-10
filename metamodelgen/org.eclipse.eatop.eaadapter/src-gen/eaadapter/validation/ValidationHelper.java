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
 * 		Continental AG, Matthias Nick - Initial API and implementation
 * </copyright>
 * 
 */
package eaadapter.validation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import eaadapter.EAElement;
import eaadapter.EAPackage;
import eaadapter.EARepository;

public class ValidationHelper {
	public static boolean isNameWellFormed(String name) {
		final Set<CharSequence> characters = new HashSet<CharSequence>();
		characters.add(" ");
		characters.add("-");
		characters.add(".");
		characters.add(":");
		characters.add("/");

		if (name == null || name.length() == 0) {
			return false;
		}

		for (CharSequence cs : characters) {
			if (name.contains(cs)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * creates the path of an <code>EAPackage</code><br>
	 * E.g. <code>http://package1/package2/package3</code>
	 * 
	 * @param pkg
	 * @return
	 */
	public static String createPathOfEAPackage(EAPackage pkg) {
		List<EAPackage> superPackages = new LinkedList<EAPackage>();
		EAPackage p = pkg;
		while (p.getSuperPackage() != null) {
			superPackages.add(0, p.getSuperPackage()); // insert to first place
			p = p.getSuperPackage();
		}

		String path = "";
		for (EAPackage p2 : superPackages) {
			path = path + p2.getName() + "/";
		}
		path = path + pkg.getName();

		return path;
	}

	public static EAElement findEAElementByID(int id, EARepository repository) {
		TreeIterator<EObject> iter = repository.eAllContents();
		while (iter.hasNext()) {
			EObject element = iter.next();
			if (element instanceof EAElement) {
				if (((EAElement) element).getId() == id) {
					return (EAElement) element;
				}
			}
		}
		return null;
	}

	public static boolean hasLeadingOrTrailingSpaces(String s) {
		if (s.trim().length() != s.length()) {
			return true;
		}
		return false;
	}

	public static boolean hasDuplicates(List<String> data) {
		if (data.size() < 2) {
			return false;
		}

		if (getDuplicates(data).size() > 0) {
			return true;
		}

		return false;
	}

	public static List<String> getDuplicates(List<String> data) {
		Set<String> original = new HashSet<String>();
		List<String> duplicates = new ArrayList<String>();
		for (String s : data) {
			if (!original.add(s)) {
				duplicates.add(s);
			}
		}
		return duplicates;
	}
}
