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
package org.eclipse.eatop.serialization.internal;

import org.eclipse.eatop.serialization.internal.util.IEastADLSerializationConstants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sphinx.emf.serialization.internal.XMLPersistenceMappingHelperImpl;

public class EastADLXMLHelperImpl extends XMLPersistenceMappingHelperImpl implements XMLHelper {

	/**
	 * Instantiates a new XMLHelper and sets its resource to given parameter value.
	 * 
	 * @param resource
	 *            The resource about which this helper will provide info.
	 */
	public EastADLXMLHelperImpl(XMLResource resource) {
		super(resource);
		setResource(resource);
	}

	@Override
	public String getHREF(EObject obj) {
		InternalEObject o = (InternalEObject) obj;

		URI objectURI = o.eProxyURI();
		if (objectURI == null) {
			Resource otherResource = obj.eResource();
			if (otherResource == null) {
				if (resource != null && resource.getID(obj) != null) {
					objectURI = getHREF(resource, obj);
				} else {
					objectURI = handleDanglingHREF(obj);
					if (objectURI == null) {
						return null;
					}
				}
			} else {
				objectURI = getHREF(otherResource, obj);
			}
		}

		objectURI = deresolve(objectURI);
		String result = objectURI.toString();

		// FIXME
		int index = result.lastIndexOf(IEastADLSerializationConstants.FRAGMENT_SEPARATOR); // "#"
		if (index != -1) {
			result = result.substring(index + 1);
		}
		index = result.lastIndexOf(IEastADLSerializationConstants.QUERY_SEPARATOR + IEastADLSerializationConstants.DESTINATION_TYPE_KEY
				+ IEastADLSerializationConstants.KEY_VALUE_SEPARATOR); // "?type="
		if (index != -1) {
			result = result.substring(0, index);
		}

		return result;
	}
}
