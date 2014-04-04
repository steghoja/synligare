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

import java.util.Map;

import org.eclipse.eatop.common.resource.EastADLURIFactory;
import org.eclipse.eatop.serialization.internal.util.IEastADLSerializationConstants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * A SAXXMLHandler that can handle SAX events from an EAST-ADL resource.
 */
public class EastADLSAXXMLHandler extends ExtendedXMLPersistenceMappingHandler {

	/**
	 * @param xmiResource
	 * @param helper
	 * @param options
	 */
	public EastADLSAXXMLHandler(XMLResource xmiResource, XMLHelper helper, Map<?, ?> options) {
		super(xmiResource, helper, options);
	}

	@Override
	protected void handleProxy(InternalEObject proxy, String uriLiteral) {
		int index = uriLiteral.lastIndexOf(IEastADLSerializationConstants.FRAGMENT_SEPARATOR); // "#"
		if (index != -1) {
			uriLiteral = uriLiteral.substring(index + 1);
		}
		index = uriLiteral.lastIndexOf(IEastADLSerializationConstants.QUERY_SEPARATOR + IEastADLSerializationConstants.DESTINATION_TYPE_KEY
				+ IEastADLSerializationConstants.KEY_VALUE_SEPARATOR); // "?type="
		if (index != -1) {
			uriLiteral = uriLiteral.substring(0, index);
		}

		URI proxyURI = EastADLURIFactory.createEastADLURI(uriLiteral, proxy.eClass().getName());
		proxy.eSetProxyURI(proxyURI);
	}
}