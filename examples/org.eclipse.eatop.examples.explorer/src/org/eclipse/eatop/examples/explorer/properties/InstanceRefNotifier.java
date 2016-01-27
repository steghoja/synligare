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
package org.eclipse.eatop.examples.explorer.properties;

import org.eclipse.eatop.common.ui.util.InstanceRefAutoCompleter;
import org.eclipse.eatop.common.ui.util.ModelSearcher;
import org.eclipse.eatop.examples.explorer.internal.messages.Messages;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.viewers.TreePath;

public class InstanceRefNotifier extends EContentAdapter {

	private EObject eObject;
	private TreePath treePath;

	public void observe(EObject eo) {
		eObject = eo;
		eo.eAdapters().add(this);
	}

	@Override
	public void notifyChanged(Notification n) {
		unregister();
		EObject notifier = (EObject) n.getNotifier();
		boolean isInstanceRef = ModelSearcher.isInstanceRef(notifier);
		if (!isInstanceRef) {
			return;
		}
		EObject target = getInstanceRefTarget(notifier);
		EObject changedFeature = null;
		EObject newValue = null;
		if (n.getEventType() == Notification.SET) {
			changedFeature = (EObject) n.getFeature();
			newValue = (EObject) n.getNewValue();
		} else { // including calls to removal of this adapter
			return;
		}
		if (newValue == target) {
			EStructuralFeature contextFeature = null;
			for (EStructuralFeature feature : notifier.eClass().getEAllStructuralFeatures()) {
				EAnnotation annotation = feature.getEAnnotation(Messages.Annotation_Stereotype);
				if (annotation != null && annotation.getDetails().containsValue(Messages.InstanceRef_Context)) {
					contextFeature = feature;
					InstanceRefAutoCompleter.completeInstanceRefContext(notifier, contextFeature, treePath);
				}
			}
		}
	}

	private void unregister() {
		eObject.eAdapters().remove(this);
	}

	private static EObject getInstanceRefTarget(EObject instanceRef) {
		EObject target = null;
		for (EStructuralFeature feature : instanceRef.eClass().getEAllStructuralFeatures()) {
			EAnnotation annotation = feature.getEAnnotation(Messages.Annotation_Stereotype);
			if (annotation != null && annotation.getDetails().containsValue(Messages.InstanceRef_Target)) {
				target = (EObject) instanceRef.eGet(feature);
			}
		}
		return target;
	}

	public void setTreePath(TreePath treePath) {
		this.treePath = treePath;
	}
}
