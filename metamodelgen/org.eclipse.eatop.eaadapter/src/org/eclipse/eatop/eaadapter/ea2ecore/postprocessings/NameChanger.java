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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.eatop.eaadapter.ea2ecore.PostProcessingTemplate;
import org.eclipse.eatop.eaadapter.internal.messages.Messages;
import org.eclipse.eatop.eaadapter.util.IConstants;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class NameChanger extends PostProcessingTemplate {

	private static final Map<CharSequence, CharSequence> characters = new HashMap<CharSequence, CharSequence>();
	static {
		characters.put(" ", "_"); //$NON-NLS-1$ //$NON-NLS-2$
		characters.put("-", "_"); //$NON-NLS-1$ //$NON-NLS-2$
		characters.put(".", "_dot_"); //$NON-NLS-1$ //$NON-NLS-2$
		characters.put(":", "_colon_"); //$NON-NLS-1$ //$NON-NLS-2$
		characters.put("/", "_slash_"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	private int counter = 0;

	@Override
	public void execute() {
		logger.info(Messages.logger_StartNameChanger);
		EPackage rootElement = (EPackage) EcoreUtil.getRootContainer(model);
		List<ENamedElement> toBeDeletedElements = null;
		toBeDeletedElements = new ArrayList<ENamedElement>();

		if (!isNameWellFormed(rootElement)) {
			rootElement.setName(createWellFormedName(rootElement));
		}

		for (TreeIterator<EObject> iter = EcoreUtil.getAllProperContents(rootElement, true); iter.hasNext();) {
			EObject element = iter.next();
			if (element instanceof ENamedElement) {
				ENamedElement e = (ENamedElement) element;

				if (!isNameWellFormed(e)) {
					if (e instanceof EClass && !isNameSet(e)) {
						// A class with no name may be a note, a border or something else that is not relevant
						toBeDeletedElements.add(e);
					} else {
						String newName = createWellFormedName(e);
						System.out.println("Warning: new name set from " + e.getName() + " --> " + newName); //$NON-NLS-1$ //$NON-NLS-2$
						e.setName(newName);
					}
				}
			}
		}

		// Normally no the list of toBeDeletedElements should be empty as these elements are
		System.out.println("Number of useless classes that were transfered: " + toBeDeletedElements.size()); //$NON-NLS-1$
	}

	private String createWellFormedName(ENamedElement element) {
		String name = element.getName();
		name = name.trim();
		// Either name is not set
		if (!isNameSet(element)) {
			return IConstants.NAME_WAS_NOT_SET + counter++;
		}
		// Or characterset is not correct
		Iterator<?> it = characters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<?, ?> entry = (Map.Entry<?, ?>) it.next();
			name = name.replace((CharSequence) entry.getKey(), (CharSequence) entry.getValue());
		}
		return name;
	}

	private boolean isNameSet(ENamedElement element) {
		String name = element.getName();

		if (name.length() == 0) {
			return false;
		} else {
			return true;
		}
	}

	private boolean isNameWellFormed(ENamedElement element) {
		String name = element.getName();
		// Either name is not set
		if (!isNameSet(element)) {
			return false;
		}
		// Or character set not correct
		Iterator<?> it = characters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<?, ?> entry = (Map.Entry<?, ?>) it.next();
			if (name.contains((CharSequence) entry.getKey())) {
				return false;
			}
		}
		return true;
	}
}
