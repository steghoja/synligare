/**
 * <copyright>
 *
 * Copyright (c) 2014 Arccore and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Arccore - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.eatop.examples.explorer;

import org.eclipse.emf.ecore.EObject;

public class ChildWrapper {

	// private List<Object> children;
	private EObject object;
	private EObject parent;

	public ChildWrapper(EObject object, EObject parent) {
		this.object = object;
		this.parent = parent;
	}

	public EObject getObject() {
		return object;
	}

	public EObject getParent() {
		return parent;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof ChildWrapper) {
			return ((ChildWrapper) other).getObject().equals(object);
		} else {
			return false;
		}
	}

}
