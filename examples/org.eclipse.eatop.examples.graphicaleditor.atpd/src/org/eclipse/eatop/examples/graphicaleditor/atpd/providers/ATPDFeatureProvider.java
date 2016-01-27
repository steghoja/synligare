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
package org.eclipse.eatop.examples.graphicaleditor.atpd.providers;

import org.eclipse.eatop.eastadl21.AnalysisFunctionType;
import org.eclipse.eatop.eastadl21.EAPackage;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.FunctionType;
import org.eclipse.eatop.eastadl21.FunctionalDevice;
import org.eclipse.eatop.examples.graphicaleditor.atpd.delete.DeleteFunctionPortsFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.ATPDLayoutEClassFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.ATPDUpdateFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.FunctionTypeLayout;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.add.AddAnalysisFunctionTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.add.AddAnalysisTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.add.AddFunctionClientServerPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.add.AddFunctionInFlowPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.add.AddFunctionOutFlowPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.add.AddFunctionPowerPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.add.AddFunctionalDeviceFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.add.AddPortsNameBoxFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.create.CreateAnalysisFunctionTypeFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.create.CreateAnalysisTypePackageFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.create.CreateFunctionClientPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.create.CreateFunctionInFlowPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.create.CreateFunctionInOutFlowPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.create.CreateFunctionOutFlowPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.create.CreateFunctionPowerPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.create.CreateFunctionServerPortFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.create.CreateFunctionalDeviceFeature;
import org.eclipse.eatop.examples.graphicaleditor.atpd.features.move.MovePortsFeature;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IMoveAnchorFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveAnchorContext;
import org.eclipse.graphiti.features.context.IPictogramElementContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;
import org.eclipse.graphiti.ui.features.DefaultFeatureProvider;


public class ATPDFeatureProvider extends DefaultFeatureProvider {
	public ATPDFeatureProvider(IDiagramTypeProvider dtp) {
		super(dtp);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		if (pictogramElement instanceof ContainerShape) {
			Object bo = getBusinessObjectForPictogramElement(pictogramElement);
			if (bo instanceof FunctionType) {
				return new ATPDUpdateFeature(this);
			}
		}
		return super.getUpdateFeature(context);
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof AnalysisFunctionType

		|| bo instanceof FunctionalDevice) {

			return new FunctionTypeLayout(this);
		}
		if (bo instanceof EAPackage || bo instanceof Text) {
			return new ATPDLayoutEClassFeature(this);
		}

		return super.getLayoutFeature(context);

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
		
		if (context.getNewObject() instanceof FunctionalDevice) {
			return new AddFunctionalDeviceFeature(this);
		} else if (context.getNewObject() instanceof AnalysisFunctionType) {
			return new AddAnalysisFunctionTypeFeature(this);
		} else if (context.getNewObject() instanceof FunctionFlowPort) {
			return new AddFunctionInFlowPortFeature(this);
		} else if (context.getNewObject() instanceof FunctionFlowPort) {
			return new AddFunctionOutFlowPortFeature(this);
		} else if (context.getNewObject() instanceof FunctionClientServerPort) {
			return new AddFunctionClientServerPortFeature(this);
		} else if (context.getNewObject() instanceof FunctionPowerPort) {
			return new AddFunctionPowerPortFeature(this);
		} else if (context.getNewObject() instanceof EAPackage) {
			return new AddAnalysisTypePackageFeature(this);
		} else if (context.getNewObject() instanceof Text) {
			return new AddPortsNameBoxFeature(this);
		}
		return super.getAddFeature(context);

	}

	@Override
	public ICreateFeature[] getCreateFeatures() {
		return new ICreateFeature[] {
				new CreateAnalysisTypePackageFeature(this),
				new CreateAnalysisFunctionTypeFeature(this),
				new CreateFunctionalDeviceFeature(this),
				new CreateFunctionInFlowPortFeature(this),
				new CreateFunctionOutFlowPortFeature(this),
				new CreateFunctionInOutFlowPortFeature(this),
				new CreateFunctionClientPortFeature(this),
				new CreateFunctionServerPortFeature(this),
				new CreateFunctionPowerPortFeature(this) };

	}
	
	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {

		IDeleteFeature ret = null;
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof ContainerShape ) {
			return null;
		}
		
		if(bo instanceof FunctionPort)
		{
			FunctionPort elementBo = (FunctionPort) bo ;
			elementBo.getShortName();
			return new DeleteFunctionPortsFeature(this);
			 
		}
		
		
		ret = new DefaultDeleteFeature(this);
		return ret;
	}
	
	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		PictogramElement pictogramElement = context.getPictogramElement();
		Object bo = getBusinessObjectForPictogramElement(pictogramElement);
		if (bo instanceof ContainerShape ) {
			return null;
		}
		return new DefaultRemoveFeature(this);

	}


	@Override
	public IMoveAnchorFeature getMoveAnchorFeature(IMoveAnchorContext context) {
		if (getBusinessObjectForPictogramElement(context.getAnchor()) instanceof FunctionPort) {
			return new MovePortsFeature(this);
		}
		return super.getMoveAnchorFeature(context);
	}

	@Override
	public IFeature[] getDragAndDropFeatures(IPictogramElementContext context) {
		// simply return all create connection features
		return getCreateConnectionFeatures();

	}

}
