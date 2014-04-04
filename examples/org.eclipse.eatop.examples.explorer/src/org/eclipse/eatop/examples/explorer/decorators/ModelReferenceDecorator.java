/**
 * <copyright>
 * 
 * Copyright (c) 2014 Arccore and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *     Arccore - Initial API and implementation
 * 
 * </copyright>
 */

package org.eclipse.eatop.examples.explorer.decorators;

import java.util.Collection;

import org.eclipse.core.commands.operations.IOperationHistoryListener;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.examples.explorer.internal.Activator;
import org.eclipse.eatop.examples.explorer.internal.messages.Messages;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.sphinx.emf.domain.factory.EditingDomainFactoryListenerRegistry;
import org.eclipse.sphinx.emf.domain.factory.ITransactionalEditingDomainFactoryListener;
import org.eclipse.sphinx.emf.metamodel.MetaModelDescriptorRegistry;
import org.eclipse.sphinx.emf.util.WorkspaceTransactionUtil;
import org.eclipse.sphinx.emf.workspace.domain.WorkspaceEditingDomainManager;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class ModelReferenceDecorator implements ILightweightLabelDecorator, ITransactionalEditingDomainFactoryListener {

	private static ImageDescriptor referenceOverlay = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
			Messages.Path_ReferenceOverlayImage);
	private static ImageDescriptor instanceRefOverlay = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
			Messages.Path_InstanceRefOverlayImage);
	private IOperationHistoryListener affectedObjectsListener;

	public ModelReferenceDecorator() {
		// Create one ResourceListener that detects objects changed by EMF commands
		affectedObjectsListener = createAffectedObjectsListener();
		Assert.isNotNull(affectedObjectsListener);

		// Register this as an Editing Domain Factory Listener (dynamically)
		EditingDomainFactoryListenerRegistry.INSTANCE.addListener(MetaModelDescriptorRegistry.ANY_MM, null, this, null);

		// Register listener on already created editing domains
		for (TransactionalEditingDomain editingDomain : WorkspaceEditingDomainManager.INSTANCE.getEditingDomainMapping().getEditingDomains()) {
			// Register the ResourceListener that detects objects changed by EMF commands
			WorkspaceTransactionUtil.getOperationHistory(editingDomain).addOperationHistoryListener(affectedObjectsListener);
		}
	}

	@Override
	public void decorate(Object element, IDecoration decoration) {
		if (elementIsReferenced(element) && elementIsInstanceReferenced(element)) {
			decoration.addOverlay(referenceOverlay, IDecoration.TOP_LEFT);
			decoration.addOverlay(instanceRefOverlay, IDecoration.BOTTOM_RIGHT);
		} else if (elementIsReferenced(element)) {
			decoration.addOverlay(referenceOverlay, IDecoration.TOP_LEFT);
		} else if (elementIsInstanceReferenced(element)) {
			decoration.addOverlay(instanceRefOverlay, IDecoration.BOTTOM_RIGHT);
		} else {
			decoration.addOverlay(null);
		}
	}

	private boolean elementIsReferenced(Object element) {
		if (element instanceof EObject) {
			return ModelSearcher.findReferences((EObject) element).size() > 0;
		}
		return false;
	}

	private boolean elementIsInstanceReferenced(Object element) {
		if (element instanceof EObject) {
			return ModelSearcher.findInstanceReferences((EObject) element).size() > 0;
		}
		return false;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {

	}

	@Override
	public void dispose() {

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {

	}

	@Override
	public void postCreateEditingDomain(TransactionalEditingDomain editingDomain) {
		// Register the ResourceListener that detects objects changed by EMF commands
		WorkspaceTransactionUtil.getOperationHistory(editingDomain).addOperationHistoryListener(affectedObjectsListener);
	}

	@Override
	public void preDisposeEditingDomain(TransactionalEditingDomain editingDomain) {
		if (affectedObjectsListener != null) {
			WorkspaceTransactionUtil.getOperationHistory(editingDomain).removeOperationHistoryListener(affectedObjectsListener);
		}
	}

	protected IOperationHistoryListener createAffectedObjectsListener() {
		return new IOperationHistoryListener() {
			@Override
			public void historyNotification(final OperationHistoryEvent event) {
				if (event.getEventType() == OperationHistoryEvent.DONE || event.getEventType() == OperationHistoryEvent.UNDONE
						|| event.getEventType() == OperationHistoryEvent.REDONE) {
					handleOperationFinished(event.getOperation());
				}
			}

			private void handleOperationFinished(final IUndoableOperation operation) {
				// Make sure that each getWorkbenchxx returns non null
				IWorkbench workbench = PlatformUI.getWorkbench();
				if (workbench != null) {
					IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
					if (workbenchWindow != null) {
						IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
						if (workbenchPage != null) {
							IWorkbenchPart workbenchPart = workbenchPage.getActivePart();
							if (workbenchPart != null) {
								IWorkbenchPartSite site = workbenchPart.getSite();
								if (site != null) {
									site.getShell().getDisplay().asyncExec(new Runnable() {
										@Override
										public void run() {
											// Try to show the affected objects in viewer
											if (operation instanceof EMFCommandOperation) {
												Command command = ((EMFCommandOperation) operation).getCommand();
												if (command != null) {
													Collection<?> results = command.getResult();
													for (Object result : results) {
														if (result instanceof EObject) {
															// TODO: This updates all decorators. We know of the element
															// referencing the element(s)
															// that should be decorated, but how get them to update (the
															// referred elements have not been changed)?
															// Possibly by using Touch?
															PlatformUI.getWorkbench().getDecoratorManager().update(Messages.Decorator_ID);
														}
													}
												}
											}
										}
									});
								}
							}
						}
					}
				}

			}
		};
	}
}
