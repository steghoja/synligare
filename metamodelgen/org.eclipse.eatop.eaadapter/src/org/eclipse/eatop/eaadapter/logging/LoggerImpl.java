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

package org.eclipse.eatop.eaadapter.logging;

public class LoggerImpl implements Logger {

	private boolean loggingEnabled = false;

	@Override
	public void info(String info) {
		if (loggingEnabled) {
			System.out.println(info);
		}
	}

	@Override
	public void warning(String warning) {
		if (loggingEnabled) {
			System.out.println(warning);
		}
	}

	@Override
	public void error(String error) {
		if (loggingEnabled) {
			System.err.println(error);
		}
	}

	@Override
	public void setLoggingEnabled() {
		loggingEnabled = true;
	}

	@Override
	public void setLoggingDisabled() {
		loggingEnabled = false;
	}

}
