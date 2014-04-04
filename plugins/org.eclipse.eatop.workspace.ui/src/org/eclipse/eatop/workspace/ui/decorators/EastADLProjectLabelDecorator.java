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
package org.eclipse.eatop.workspace.ui.decorators;

import java.net.URL;
import java.util.MissingResourceException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.natures.EastADLNature;
import org.eclipse.eatop.workspace.preferences.IEastADLWorkspacePreferences;
import org.eclipse.eatop.workspace.ui.internal.Activator;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.sphinx.platform.util.PlatformLogUtil;

public class EastADLProjectLabelDecorator implements ILightweightLabelDecorator {

	/*
	 * @see org.eclipse.jface.viewers.ILightweightLabelDecorator#decorate(java.lang.Object,
	 * org.eclipse.jface.viewers.IDecoration)
	 */
	@Override
	public void decorate(Object element, IDecoration decoration) {
		// Handle only projects
		if (element instanceof IProject) {
			// Try to retrieve eastADL release descriptor from given project
			EastADLReleaseDescriptor eastADLRelease = IEastADLWorkspacePreferences.EAST_ADL_RELEASE.get((IProject) element);
			if (eastADLRelease != null && hasEastADLNatureOnly((IProject) element)) {
				// Adapt eastADL release descriptor in order to get its item label provider
				IItemLabelProvider itemLabelProvider = (IItemLabelProvider) Platform.getAdapterManager().loadAdapter(eastADLRelease,
						IItemLabelProvider.class.getName());
				if (itemLabelProvider != null) {
					try {
						// URL of the image given by item label provider
						Object imageURL = itemLabelProvider.getImage(eastADLRelease);
						if (imageURL instanceof URL) {
							// Obtain corresponding image descriptor from image URL
							ImageDescriptor imageDescriptor = Activator.getPlugin().getImageDescriptor((URL) imageURL);

							// Add image as top left decoration
							decoration.addOverlay(imageDescriptor, IDecoration.TOP_LEFT);
						}
					} catch (MissingResourceException ex) {
						PlatformLogUtil.logAsWarning(Activator.getPlugin(), ex);
					}
				}
			}
		}
	}

	/**
	 * Returns if the required project has and only has EAST-ADL nature
	 * 
	 * @param project
	 * @return
	 */
	public boolean hasEastADLNatureOnly(IProject project) {
		try {
			IProjectDescription description = project.getDescription();
			String[] natures = description.getNatureIds();
			if (project.hasNature(EastADLNature.ID) && natures.length == 1) {
				return true;
			}
		} catch (CoreException ex1) {
			return false;
		}
		return false;
	}

	/*
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/*
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void addListener(ILabelProviderListener listener) {
		// Do nothing
	}

	/*
	 * @see
	 * org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	@Override
	public void removeListener(ILabelProviderListener listener) {
		// Do nothing
	}

	/*
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		// Do nothing
	}
}
