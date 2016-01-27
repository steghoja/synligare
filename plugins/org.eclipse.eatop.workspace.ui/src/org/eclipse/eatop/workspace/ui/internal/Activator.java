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
package org.eclipse.eatop.workspace.ui.internal;

import java.net.URL;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.EclipseUIPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * This is the central singleton for this plug-in.
 */
public final class Activator extends EMFPlugin {

	/**
	 * Keep track of the singleton.
	 */
	public static final Activator INSTANCE = new Activator();

	/**
	 * Keep track of the singleton.
	 */
	private static Implementation plugin;

	/**
	 * Create the instance.
	 */
	public Activator() {
		super(new ResourceLocator[] {});
	}

	/**
	 * Returns the singleton instance of the Eclipse plug-in.
	 * 
	 * @return the singleton instance.
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plug-in.
	 * 
	 * @return the singleton instance.
	 */
	public static Implementation getPlugin() {
		return plugin;
	}

	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 */
	public static class Implementation extends EclipseUIPlugin {

		private ScopedPreferenceStore commonPreferenceStore;
		private ScopedPreferenceStore workspacePreferenceStore;

		/**
		 * Creates an instance.
		 */
		public Implementation() {
			super();

			// Remember the static instance
			plugin = this;
		}

		@SuppressWarnings({ "restriction", "deprecation" })
		public IPreferenceStore getCommonPreferenceStore() {
			// Create the preference store lazily
			if (commonPreferenceStore == null) {
				EclipsePlugin targetActivator = org.eclipse.eatop.common.internal.Activator.getPlugin();
				commonPreferenceStore = new ScopedPreferenceStore(new InstanceScope(), targetActivator.getSymbolicName());
			}
			return commonPreferenceStore;
		}

		@SuppressWarnings("deprecation")
		public IPreferenceStore getWorkspacePreferenceStore() {
			// Create the preference store lazily
			if (workspacePreferenceStore == null) {
				EclipsePlugin targetActivator = org.eclipse.eatop.workspace.internal.Activator.getPlugin();
				workspacePreferenceStore = new ScopedPreferenceStore(new InstanceScope(), targetActivator.getSymbolicName());
			}
			return workspacePreferenceStore;
		}

		public ImageDescriptor getImageDescriptor(String key) {
			Object imageURL = getImage(key);
			if (imageURL instanceof URL) {
				return getImageDescriptor((URL) imageURL);
			}
			return null;
		}

		public ImageDescriptor getImageDescriptor(URL url) {
			// FIXME File bug to EMF: Impossible to use ExtendedImageRegistry.INSTANCE when Display.getCurrent() returns
			// null
			if (Display.getCurrent() != null) {
				return ExtendedImageRegistry.INSTANCE.getImageDescriptor(url);
			}
			return ImageDescriptor.createFromURL(url);
		}
	}
}