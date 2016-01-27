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
package org.eclipse.eatop.workspace.internal.syncing;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.eatop.common.metamodel.EastADLReleaseDescriptor;
import org.eclipse.eatop.workspace.util.EastADLPlatformUtil;
import org.eclipse.sphinx.emf.model.ModelDescriptorRegistry;
import org.eclipse.sphinx.emf.workspace.syncing.IModelSyncRequest;
import org.eclipse.sphinx.platform.resources.syncing.AbstractResourceSynchronizerDelegate;

public class EastADLModelSynchronizerDelegate extends AbstractResourceSynchronizerDelegate<IModelSyncRequest> {

	@Override
	public void handleProjectDescriptionChanged(int eventType, IProject project) {
		if (eventType == IResourceChangeEvent.POST_CHANGE) {
			if (EastADLPlatformUtil.hasEastADLNature(project)
					&& ModelDescriptorRegistry.INSTANCE.getModels(project, EastADLReleaseDescriptor.INSTANCE).isEmpty()) {
				// TODO Enhance IModelSyncRequest such that handling of individual models in projects becomes possible
				syncRequest.addProjectToLoad(project);
			}
			if (!EastADLPlatformUtil.hasEastADLNature(project)
					&& !ModelDescriptorRegistry.INSTANCE.getModels(project, EastADLReleaseDescriptor.INSTANCE).isEmpty()) {
				// TODO Enhance IModelSyncRequest such that handling of individual models in projects becomes possible
				syncRequest.addProjectToUnload(project);
			}
		}
	}
}
