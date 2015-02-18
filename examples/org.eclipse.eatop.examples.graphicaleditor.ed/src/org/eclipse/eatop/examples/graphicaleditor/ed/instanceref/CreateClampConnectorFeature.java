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
package org.eclipse.eatop.examples.graphicaleditor.ed.instanceref;


import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.ClampConnector;
import org.eclipse.eatop.eastadl21.ClampConnector_port;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.Environment;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;



public class CreateClampConnectorFeature extends
		AbstractCreateConnectionFeature {

	public CreateClampConnectorFeature(IFeatureProvider fp) {
		super(fp, "ClampConnector(FunctionPort to FunctionPort)", "Create ClampConnector");
	}
	
	private FunctionPort getFunctionPort(Anchor anchor) {
		if (anchor != null
        		&& anchor.getLink() != null) {
			Object object = anchor.getLink().getBusinessObjects().get(0);
			if (object instanceof FunctionPort) {
				return (FunctionPort) object;
			}
		}
		return null;
	}
	
	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a ComponentType
		
		if (getFunctionPort(context.getSourceAnchor()) != null ) {
				return true;			
		}		
		return false;
	}
		
	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// return true if both anchors belong to an EClass
		// and those EClasses are not identical

		// one of both in environment
		FunctionPort target = getFunctionPort(context.getTargetAnchor());
		FunctionPort source = getFunctionPort(context.getSourceAnchor());
		FunctionPrototype targetPrototype = null;
		FunctionPrototype sourcePrototype = null;

		// get SourcePrototype
		EObject sAnchor2 = context.getSourceAnchor().eContainer();
		if (sAnchor2 instanceof ContainerShape) {
			ContainerShape z = (ContainerShape) sAnchor2;
			z.getLink().getBusinessObjects().get(0);
			if (z.getLink().getBusinessObjects().get(0) instanceof FunctionPrototype) {
				sourcePrototype = (FunctionPrototype) z.getLink()
						.getBusinessObjects().get(0);
			}
		}

		if (source != null && target != null && source != target) {
			// EDFeatureProvider.FaultFailurePortToFunctionPort = true;

			// get TargetPrototype
			EObject sAnchor = context.getTargetAnchor().eContainer();
			if (sAnchor instanceof ContainerShape) 
			{
				ContainerShape z = (ContainerShape) sAnchor;
				z.getLink().getBusinessObjects().get(0);
				if (z.getLink().getBusinessObjects().get(0) instanceof FunctionPrototype) 
				{
					targetPrototype = (FunctionPrototype) z.getLink()
							.getBusinessObjects().get(0);
				}
			}

			
			
			// if both ports are of the same prototype return false
			if (targetPrototype.equals(sourcePrototype))
				return false;
			
			
			// if both prototypes are outside of an environment
			{
				boolean sourceInEnvironment = false;
				boolean targetInEnvironment = false;

				// SourcePrototype in Environment?
				ContainerShape sourceProtoContainerShape = (ContainerShape) context
						.getSourceAnchor().eContainer().eContainer();
				if (sourceProtoContainerShape.getLink().getBusinessObjects()
						.get(0) instanceof Environment) {
					EList<Shape> anchors = sourceProtoContainerShape
							.getChildren();
					Shape[] anchList = anchors
							.toArray(new Shape[anchors.size()]);
					for (int x = 0; x < anchList.length; x++) {
						if (anchList[x] instanceof ContainerShape) {
							ContainerShape containerShape2 = (ContainerShape) anchList[x];
							EObject eobjetc = containerShape2.getLink()
									.getBusinessObjects().get(0);
							if (eobjetc instanceof FunctionPrototype) {
								sourceInEnvironment = true;
							}
						}

					}
				}

				// TargetPrototype in Environment?
				ContainerShape targetProtoContainerShape = (ContainerShape) context
						.getTargetAnchor().eContainer().eContainer();
				if (targetProtoContainerShape.getLink().getBusinessObjects()
						.get(0) instanceof Environment) {
					EList<Shape> anchors = targetProtoContainerShape
							.getChildren();
					Shape[] anchList = anchors
							.toArray(new Shape[anchors.size()]);
					for (int x = 0; x < anchList.length; x++) {
						if (anchList[x] instanceof ContainerShape) {
							ContainerShape containerShape2 = (ContainerShape) anchList[x];
							EObject eobjetc = containerShape2.getLink()
									.getBusinessObjects().get(0);
							if (eobjetc instanceof FunctionPrototype) {
								targetInEnvironment = true;
							}
						}
					}
				}
				if (sourceInEnvironment == false
						&& targetInEnvironment == false)
					return false;
				else
					return true;
			}
		}	
		return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {
		Connection newConnection = null;
		
		//invisible ele
		ClampConnector_port invisible1 = Eastadl21Factory.eINSTANCE.createClampConnector_port();
		ClampConnector_port invisible2 = Eastadl21Factory.eINSTANCE.createClampConnector_port();
		
		//ele at ends
		FunctionPort fpTarget = (FunctionPort) getFunctionPort(context.getTargetAnchor());
		FunctionPort fpSource = (FunctionPort) getFunctionPort(context.getSourceAnchor());
			
		//ClampConnector
		ClampConnector eReference = Eastadl21Factory.eINSTANCE.createClampConnector();
		
		
		
		Assert.isNotNull(fpSource);
        Assert.isNotNull(fpTarget);
		if (fpSource != null && fpTarget != null) {
			

			
			//2 = target
			invisible1.setFunctionPort(fpTarget);
			invisible2.setFunctionPort(fpSource);
			
			
			//3. = context
			EObject sAnchor = context.getTargetAnchor().eContainer(); 
			if (sAnchor instanceof ContainerShape) 
			{
				ContainerShape z = (ContainerShape) sAnchor;
				z.getLink().getBusinessObjects().get(0);
				if (z.getLink().getBusinessObjects().get(0) instanceof FunctionPrototype)
				{
					invisible1.getFunctionPrototype().add(((FunctionPrototype) z.getLink().getBusinessObjects().get(0)));
				}
			}
			
			EObject sAnchor2 = context.getSourceAnchor().eContainer(); 
			if (sAnchor2 instanceof ContainerShape) 
			{
				ContainerShape z = (ContainerShape) sAnchor2;
				z.getLink().getBusinessObjects().get(0);
				if (z.getLink().getBusinessObjects().get(0) instanceof FunctionPrototype)
				{
					invisible2.getFunctionPrototype().add(((FunctionPrototype) z.getLink().getBusinessObjects().get(0)));
				}
			}
			
			
				
			//add at which env resource?
			Environment part = null;
			ContainerShape sourceProtoContainerShape = (ContainerShape) context
					.getSourceAnchor().eContainer().eContainer();
			ContainerShape targetProtoContainerShape = (ContainerShape) context
					.getTargetAnchor().eContainer().eContainer();
			if (sourceProtoContainerShape.getLink().getBusinessObjects().get(0) instanceof Environment) {
				part = (Environment) sourceProtoContainerShape.getLink()
						.getBusinessObjects().get(0);
			} else {
				part = (Environment) targetProtoContainerShape.getLink()
						.getBusinessObjects().get(0);
			}
			
			// add resource
			if(part != null)
			DiagramUtil.addReferenceToBOResource(part, Eastadl21Factory.eINSTANCE.getEastadl21Package().getEnvironment_ClampConnector(), eReference);
			
			AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
			//add 2 invis to cc
			DiagramUtil.addReferenceToBOResource(eReference, Eastadl21Factory.eINSTANCE.getEastadl21Package().getClampConnector_Port(), invisible1);
			DiagramUtil.addReferenceToBOResource(eReference, Eastadl21Factory.eINSTANCE.getEastadl21Package().getClampConnector_Port(), invisible2);
			addContext.setNewObject(eReference);
			
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);	
		}		
     		return newConnection;
	}	
}
