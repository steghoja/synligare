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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sphinx.emf.properties.PropertyFilter;
import org.eclipse.sphinx.emf.ui.properties.filters.IPropertySourceFilter;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class InstanceRefPropertyFilter implements IPropertySourceFilter {

	public InstanceRefPropertyFilter() {
	}

	@Override
	public void setPropertyFilter(PropertyFilter propertyFilter) {
	}

	@Override
	public boolean isFilterForObject(Object object) {
		if (object instanceof EObject) {
			return ModelSearcher.canCreateInstanceRefChild((EObject) object) || ModelSearcher.isInstanceRef((EObject) object);
		}
		return false;
	}

	@Override
	public IPropertyDescriptor[] getAcceptedPropertyDescriptors(IPropertySource propertySource) {
		List<IPropertyDescriptor> l = new ArrayList<IPropertyDescriptor>();
		for (IPropertyDescriptor descriptor : propertySource.getPropertyDescriptors()) {
			if (descriptor instanceof InstanceRefPropertyDescriptor) {
				boolean valid = true;
				if (!((InstanceRefPropertyDescriptor) descriptor).shouldShowProperty(descriptor)) {
					valid = false;
				}
				if (valid) {
					l.add(descriptor);
				}
			} else { // normal view, not our custom instanceref editing, proceed as usual
				l.add(descriptor);
			}
		}
		return l.toArray(new IPropertyDescriptor[l.size()]);
	}

	@Override
	public boolean accept(Object owner, IPropertyDescriptor propertyDescriptor) {
		if (owner instanceof EObject) {
			EObject eo = (EObject) owner;
			if (!ModelSearcher.canCreateInstanceRefChild(eo)) {
				return false;
			}
			return true;
		}
		return false;
	}

}
