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
 *     itemis - Bug 444145 - Incorporate changes of Sphinx triming context information from proxy URIs when serializing proxyfied cross-document references
 *  
 * </copyright>
 * 
 */
package org.eclipse.eatop.serialization.internal;

import java.util.Map;

import org.eclipse.eatop.common.resource.impl.ExtendedEastADLResourceAdapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sphinx.emf.resource.ExtendedResource;

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
		URI proxyURI;
		if (oldStyleProxyURIs) {
			uriLiteral = uriLiteral.startsWith(ExtendedResource.URI_SEGMENT_SEPARATOR) ? uriLiteral : ExtendedResource.URI_SEGMENT_SEPARATOR
					+ uriLiteral;
			proxyURI = URI.createURI(uriLiteral);
			proxy.eSetProxyURI(proxyURI);
		} else {
			if (extendedResource != null) {
				// Create URI with class type
				proxyURI = ((ExtendedEastADLResourceAdapter) extendedResource).createURI(proxy, uriLiteral);
			} else {
				proxyURI = URI.createURI(uriLiteral);
			}

			if (uriHandler != null) {
				proxyURI = uriHandler.resolve(proxyURI);
			} else if (resolve
					&& proxyURI.isRelative()
					&& proxyURI.hasRelativePath()
					&& (extendedMetaData == null ? !packageRegistry.containsKey(proxyURI.trimFragment().toString()) : extendedMetaData
							.getPackage(proxyURI.trimFragment().toString()) == null)) {
				proxyURI = helper.resolve(proxyURI, resourceURI);
			}

			proxy.eSetProxyURI(proxyURI);
		}

		// Test for a same document reference that would usually be handled as an IDREF
		if (proxyURI.trimFragment().equals(resourceURI)) {
			sameDocumentProxies.add(proxy);
		}

		if (extendedResource != null) {
			extendedResource.augmentToContextAwareProxy(proxy);
		}
	}

}