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
package org.eclipse.eatop.examples.graphicaleditor.SModel.providers;

import org.eclipse.eatop.eastadl21.Allocation;
import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisLevel;
import org.eclipse.eatop.eastadl21.DesignFunctionPrototype;
import org.eclipse.eatop.eastadl21.DesignLevel;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.ImplementationLevel;
import org.eclipse.eatop.eastadl21.SystemModel;
import org.eclipse.eatop.eastadl21.VehicleLevel;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.SMDLayoutFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.SMDMoveFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.SMDResizeFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.SystemModelUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.add.AddAnalysisLevelFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.add.AddDesignLevelFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.add.AddImplementationLevelFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.add.AddSystemModelFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.add.AddVehilceLevelFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.create.CreateAllocationFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.create.CreateAnalysisFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.create.CreateDesignFunctionPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.create.CreateHardwareComponentPrototypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.create.CreateSystemModelFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.delete.RemoveLevelsFeature;
import org.eclipse.eatop.examples.graphicaleditor.SModel.features.delete.deleteLevelsFeature;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveShapeFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IResizeShapeFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;

public class SMDFeatureProvider extends DefaultFeatureProvider {
	public SMDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			if (bo instanceof SystemModel
					|| bo instanceof VehicleLevel 
					|| bo instanceof AnalysisLevel 
					|| bo instanceof DesignLevel 
					|| bo instanceof ImplementationLevel
					|| bo instanceof AnalysisFunctionPrototype
					|| bo instanceof DesignFunctionPrototype
					|| bo instanceof HardwareComponentPrototype
					|| bo instanceof Allocation) {
				return new SystemModelUpdateFeature(this);
			}
		}
		return super.getUpdateFeature(context);

	}

	@Override
	public IResizeShapeFeature getResizeShapeFeature(IResizeShapeContext context) {

		Shape shape = context.getShape();
		Object bo = getBusinessObjectForPictogramElement(shape);
		if (bo instanceof SystemModel
				|| bo instanceof AnalysisLevel 
				|| bo instanceof DesignLevel 
				|| bo instanceof VehicleLevel 
				|| bo instanceof ImplementationLevel
				|| bo instanceof AnalysisFunctionPrototype
				|| bo instanceof DesignFunctionPrototype
				|| bo instanceof HardwareComponentPrototype
				|| bo instanceof Allocation) {
			return new SMDResizeFeature(this);
		}

		return super.getResizeShapeFeature(context);

	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if ( bo instanceof VehicleLevel
				|| bo instanceof AnalysisLevel || bo instanceof DesignLevel
				|| bo instanceof ImplementationLevel) {
			return new SMDLayoutFeature(this);
		} else if (bo instanceof AnalysisFunctionPrototype
				|| bo instanceof DesignFunctionPrototype
				|| bo instanceof HardwareComponentPrototype) {
			return new org.eclipse.eatop.examples.graphicaleditor.SModel.features.AnalysisFunctionPrototypeLayout(
					this);
		}
		return super.getLayoutFeature(context);

	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {

		IDeleteFeature ret = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof VehicleLevel || bo instanceof AnalysisLevel
				|| bo instanceof DesignLevel || bo instanceof SystemModel
				|| bo instanceof ImplementationLevel) {

			return new deleteLevelsFeature(this);
		}
		ret = new DefaultDeleteFeature(this);
		return ret;
	}

	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof VehicleLevel || bo instanceof AnalysisLevel
				|| bo instanceof DesignLevel || bo instanceof SystemModel
				|| bo instanceof ImplementationLevel) {

			return new RemoveLevelsFeature(this);
		}
		return new DefaultRemoveFeature(this);

	}

	private boolean containerAlreadyExists(IAddContext context) {
		/*
		 * the container of an object can't be dragged and dropped from the
		 * wizard if there is already a container of the same BO in the diagram
		 */
		if (context.getTargetContainer() instanceof Diagram) {
			for (Shape cs : context.getTargetContainer().getChildren()) {
				if (cs.getLink() != null)
					if (cs.getLink().getBusinessObjects().size() > 0)
						if (cs.getLink().getBusinessObjects().get(0)
								.equals(context.getNewObject()))
							return true;

			}
		}

		return false;
	}
	
	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		
		if(containerAlreadyExists(context))
			return super.getAddFeature(context);
		
		if (context.getNewObject() instanceof SystemModel) {
			return new AddSystemModelFeature(this);
		} else if (context.getNewObject() instanceof VehicleLevel) {
			return new AddVehilceLevelFeature(this);
		} else if (context.getNewObject() instanceof AnalysisLevel) {
			return new AddAnalysisLevelFeature(this);
		} else if (context.getNewObject() instanceof DesignLevel) {
			return new AddDesignLevelFeature(this);
		} else if (context.getNewObject() instanceof ImplementationLevel) {
			return new AddImplementationLevelFeature(this);
		} else if (context.getNewObject() instanceof AnalysisFunctionPrototype) {
			return new org.eclipse.eatop.examples.graphicaleditor.SModel.features.add.AddAnalysisFunctionPrototypeFeature(
					this);
		} else if (context.getNewObject() instanceof DesignFunctionPrototype) {
			return new org.eclipse.eatop.examples.graphicaleditor.SModel.features.add.AddDesignFunctionPrototypeFeature(
					this);
		} else if (context.getNewObject() instanceof HardwareComponentPrototype) {
			return new org.eclipse.eatop.examples.graphicaleditor.SModel.features.add.AddHardwareComponentPrototypeFeature(
					this);
		}else if (context.getNewObject() instanceof Allocation) {
			return new org.eclipse.eatop.examples.graphicaleditor.SModel.features.add.AddAllocationFeature(
					this);
		}
		return super.getAddFeature(context);

	}

	@Override
	public ICreateFeature[] getCreateFeatures() {

		return new ICreateFeature[] { new CreateSystemModelFeature(this),
				new CreateAnalysisFunctionPrototypeFeature(this),
				new CreateDesignFunctionPrototypeFeature(this),
				new CreateHardwareComponentPrototypeFeature(this) ,
				new CreateAllocationFeature(this)};
	}

	@Override
	public IMoveShapeFeature getMoveShapeFeature(IMoveShapeContext context) {
		Shape shape = context.getShape();
		Object bo = getBusinessObjectForPictogramElement(shape);
		if (bo instanceof EClass) {
			return new SMDMoveFeature(this);
		}
		return super.getMoveShapeFeature(context);

	}

	@Override
	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		// simply return all create connection features
		return getCreateConnectionFeatures();

	}

}
