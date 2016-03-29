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
package org.eclipse.eatop.metamodelgen.templates.source;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.osgi.framework.Bundle;

@SuppressWarnings("nls")
public class EastADLSourceRevisions {
	private static EastADLSourceRevision sourceRevision;
	private static List<EastADLSourceRevision> sourceRevisions;

	/**
	 * Initializes the source revision object that is currently being generated and the list of all available revisions.
	 * The passed in source revision name marks the currently generated revision.
	 * 
	 * @param bundle
	 * @param rootPath
	 * @param sourceRevisionName
	 * @throws IOException
	 */
	public static void initialize(Bundle bundle, String rootPath, String sourceRevisionName) throws IOException {
		sourceRevisions = new ArrayList<EastADLSourceRevision>();

		Enumeration<URL> propertyFileURLs = bundle.findEntries(rootPath, "config.properties", true);

		while (propertyFileURLs.hasMoreElements()) {
			URL propURL = propertyFileURLs.nextElement();
			Properties properties = new Properties();
			properties.load(propURL.openStream());

			EastADLSourceRevision esr = new EastADLSourceRevision(properties);
			sourceRevisions.add(esr);

			if (propURL.getPath().contains(sourceRevisionName)) {
				sourceRevision = esr;
			}
		}

		if (sourceRevision == null) {
			throw new IllegalStateException("Source revision '" + sourceRevisionName + "' not found in source path '" + rootPath + "'.");
		}
	}

	/**
	 * Returns the source revision object of the revision that is currently being generated.
	 */
	public static EastADLSourceRevision get() {
		if (sourceRevision == null) {
			throw new IllegalStateException("Source revisions have not been initialized.");
		}

		return sourceRevision;
	}

	/**
	 * Returns all source revision objects belonging to the passed in major release version.
	 * 
	 * @param releaseOrdinal
	 * @return
	 */
	public static Iterable<EastADLSourceRevision> get(final int major) {
		if (sourceRevisions == null) {
			throw new IllegalStateException("Source revisions have not been initialized.");
		}

		List<EastADLSourceRevision> list = new ArrayList<EastADLSourceRevision>();
		for (EastADLSourceRevision sourceRevision : sourceRevisions) {
			if (sourceRevision.major == major) {
				list.add(sourceRevision);
			}
		}

		return list;
	}
}
