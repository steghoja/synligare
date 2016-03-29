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

import java.util.Properties;

@SuppressWarnings("nls")
public class EastADLSourceRevision {

	public final int major;
	public final int minor;
	public final int revision;

	public EastADLSourceRevision(Properties properties) {
		major = Integer.parseInt(properties.getProperty("version.major"));
		minor = Integer.parseInt(properties.getProperty("version.minor"));
		revision = Integer.parseInt(properties.getProperty("version.revision"));
	}

	public int getRevisionOrdinal() {
		return major * 100 + minor * 10 + revision;
	}

	public String getNamespacePostfix() {
		return major + "." + minor + "." + revision;
	}

	public String getEPackageNsURIPostfixPattern() {
		return major + "\\\\." + minor + "\\\\." + revision + "(/\\\\w+)+";
	}

	public String getReleaseLabel() {
		return "EAST-ADL " + major + "." + minor + "." + revision;
	}
}
