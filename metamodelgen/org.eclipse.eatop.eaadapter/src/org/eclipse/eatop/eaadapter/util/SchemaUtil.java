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
package org.eclipse.eatop.eaadapter.util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class SchemaUtil {

	/**
	 * Creates a name conform with XML tag name rules: e.g. "thisIsACamelStyleName" -> "THIS-IS-A-CAMEL-STYLE-NAME".
	 * 
	 * @param camelName
	 *            The original camel-style name.
	 * @return The name according to XML conventions.
	 */
	public static String buildXmlName(String camelName) {
		// get segments in name and rejoin them the defined XML way:
		LinkedList<?> nameSegments = getCamelNameSegments(camelName);
		Iterator<?> segIter = nameSegments.iterator();
		boolean isFirst = true;

		String xmlName = new String();
		while (segIter.hasNext()) {
			if (!isFirst) {
				xmlName += "-"; //$NON-NLS-1$
			} else {
				isFirst = false;
			}
			xmlName += ((String) segIter.next()).toUpperCase();
		}

		return xmlName;
	}

	/**
	 * Returns the list of segments used in a camel style name. If a sequence of single captialized characters is found
	 * it is returned until the beginning of a new word.
	 * 
	 * @param camelName
	 *            The camel-style name (e.g. "thisIsACamelStyleName").
	 * @return The list of string segments in the name (e.g. "this", "Is", "A", "Camel", "Style", "Name" ).
	 */
	@SuppressWarnings("unchecked")
	private static LinkedList<?> getCamelNameSegments(String camelName) {
		if (camelName == null || camelName.length() == 0) {
			return null;
		}

		// first build list of all segments starting with a captial letter:
		@SuppressWarnings("rawtypes")
		LinkedList segmentList = new LinkedList();
		String segment = new String();
		segment += camelName.charAt(0);
		boolean wasDigit = false;
		boolean wasNotLetterOrDigit = false;

		for (int i = 1; i < camelName.length(); i++) {
			char c = camelName.charAt(i);

			boolean isLetter = Character.isLetter(c);
			boolean isUpperCase = Character.isUpperCase(c);

			// a new segment starts with an uppercase letter, or if a non-usable character is found, or name switches
			// between character and digit:
			if (!isLetter || isUpperCase || wasDigit || wasNotLetterOrDigit) {
				// start new segment:
				if (segment.length() > 0) {
					segmentList.add(segment);
					segment = new String();
				}
			}

			segment += c;
			wasDigit = Character.isDigit(c);
			wasNotLetterOrDigit = !isLetter && !wasDigit;
		}

		// add final segment:
		if (segment.length() > 0) {
			segmentList.add(segment);
		}

		// link segments of single captial letters and sets of digits:
		@SuppressWarnings("rawtypes")
		ListIterator segmentIter = segmentList.listIterator(segmentList.size());
		String segment2 = (String) segmentIter.previous();

		while (segmentIter.hasPrevious()) {
			String segment1 = (String) segmentIter.previous();
			char c = segment1.charAt(0);

			// in case of a non-digit, non-letter character, we simply remove it:
			if (!Character.isDigit(c) && !Character.isLetter(c)) {
				segmentIter.remove();
				segment2 = segment1;
				continue;
			}

			char d = segment2.charAt(segment2.length() - 1);
			boolean doLink = Character.isUpperCase(c) && Character.isUpperCase(d);
			doLink |= Character.isDigit(c) && Character.isDigit(d);

			if (segment1.length() == 1 && doLink) {

				// combine segments 1 and 2:
				segment2 = segment1 + segment2;

				segmentIter.remove();
				segmentIter.next();
				segmentIter.set(segment2);
				segmentIter.previous();
			} else {
				segment2 = segment1;
			}
		}

		return segmentList;
	}
}
