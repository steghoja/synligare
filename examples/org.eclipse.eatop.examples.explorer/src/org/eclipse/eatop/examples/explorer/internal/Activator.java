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
package org.eclipse.eatop.examples.explorer.internal;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * This is the central singleton for this plug-in.
 */
public class Activator extends EMFPlugin {

	/**
	 * Keep track of the singleton.
	 */
	public static final Activator INSTANCE = new Activator();

	public static final String PLUGIN_ID = "org.eclipse.eatop.examples.explorer"; //$NON-NLS-1$

	/**
	 * Keep track of the singleton.
	 */
	private static Implementation plugin;

	private static IEclipsePreferences prefs;

	/**
	 * Create the instance.
	 */
	public Activator() {
		super(new ResourceLocator[] {});
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 *
	 * @return the singleton instance.
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 *
	 * @return the singleton instance.
	 */
	public static Implementation getPlugin() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plug-in. This method does actually the same thing as getPlugin()
	 * and has been put in place for compatibility reasons with Activator classes which are not EMF-based but generated
	 * by PDE.
	 *
	 * @return the singleton instance.
	 */
	public static Implementation getDefault() {
		return plugin;
	}

	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 */
	public static class Implementation extends EclipseUIPlugin {
		/**
		 * Creates an instance.
		 */
		public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
			loadSettings();
		}

		private void loadSettings() {
			prefs = InstanceScope.INSTANCE.getNode(PLUGIN_ID);
		}

		public IEclipsePreferences getPreferences() {
			return prefs;
		}

		public static ImageDescriptor getImageDescriptor(String path) {
			return imageDescriptorFromPlugin(getDefault().getBundle().getSymbolicName(), path);
		}
	}
}
