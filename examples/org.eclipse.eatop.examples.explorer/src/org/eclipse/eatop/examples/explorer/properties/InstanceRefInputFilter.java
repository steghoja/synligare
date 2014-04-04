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
package org.eclipse.eatop.examples.explorer.properties;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;

public class InstanceRefInputFilter implements IFilter {

	@Override
	public boolean select(Object toTest) {
		return toTest instanceof EObject && ModelSearcher.canCreateInstanceRefChild((EObject) toTest);
	}

}
