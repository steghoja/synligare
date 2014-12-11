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
 * 
 */
package org.eclipse.eatop.examples.graphicaleditor.provider;

import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.examples.graphicaleditor.features.CustomEAPackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.features.LayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.features.UpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.features.add.AddEAPackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.features.create.CreateEAPackageFeature;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class EastadlFeatureProvider extends DefaultFeatureProvider {
	public EastadlFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	private boolean containerAlreadyExists(IAddContext context) {
		/*
		 * the container of an object can't be dragged and dropped from the
		 * wizard if there is already a container of the same BO in the diagram
		 */
		if (context.getTargetContainer() instanceof Diagram)
			for (Shape cs : context.getTargetContainer().getChildren()) {
				if (cs.getLink() != null)
					if (cs.getLink().getBusinessObjects().size() > 0)
						if (cs.getLink().getBusinessObjects().get(0)
								.equals(context.getNewObject()))
							return true;

			}
		return false;
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		if (containerAlreadyExists(context))
			return super.getAddFeature(context);
		if (context.getNewObject() instanceof EAPackage) {
			return new AddEAPackageFeature(this);
		}
		return super.getAddFeature(context);

	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] { new CreateEAPackageFeature(this) };

	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof EAPackage) {
			return new LayoutFeature(this);
		}
		return super.getLayoutFeature(context);

	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			if (bo instanceof EAPackage) {
				return new UpdateFeature(this);
			}
		}
		return super.getUpdateFeature(context);

	}

	@Override
	public ICustomFeature[] getCustomFeatures(ICustomContext context) {
		return new ICustomFeature[] { new CustomEAPackageFeature(this) };

	}

}
