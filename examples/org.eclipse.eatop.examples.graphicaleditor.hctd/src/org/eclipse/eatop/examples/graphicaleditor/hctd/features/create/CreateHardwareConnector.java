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
package org.eclipse.eatop.examples.graphicaleditor.hctd.features.create;

import org.eclipse.eatop.eastadl21.CommunicationHardwarePin;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.HardwareComponentPrototype;
import org.eclipse.eatop.eastadl21.HardwareComponentType;
import org.eclipse.eatop.eastadl21.HardwareConnector;
import org.eclipse.eatop.eastadl21.HardwareConnector_port;
import org.eclipse.eatop.eastadl21.HardwarePin;
import org.eclipse.eatop.eastadl21.IOHardwarePin;
import org.eclipse.eatop.eastadl21.PowerHardwarePin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


public class CreateHardwareConnector extends AbstractCreateConnectionFeature{

	public boolean sLock = false;
	public boolean tLock = false;
	public boolean sameLock = false;
	public CreateHardwareConnector(IFeatureProvider fp) {
		super(fp, "HardwareConnector", "Create HardwareConnector Relationship");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
	
		HardwarePin source = getHardwarePin(context.getSourceAnchor());
		HardwarePin target = getHardwarePin(context.getTargetAnchor());
	        if (source != null && target != null && source != target) {
	            return true;
	        }
	        return false;
	}

	@Override
	public Connection create(ICreateConnectionContext context) {

		Connection newConnection = null;
		// get ComponentType which should be connected
		Anchor sourceAnchor = context.getSourceAnchor();
		HardwarePin source = (HardwarePin) getBusinessObjectForPictogramElement(context.getSourceAnchor());
		// get Interface which should be connected
		Anchor targetAnchor = context.getTargetAnchor();
		HardwarePin target = (HardwarePin) getBusinessObjectForPictogramElement(context.getTargetAnchor());

		if (source != null && target != null) {
			// get new business object                                   
			HardwareConnector eReference = createReference(source, target);
			HardwareConnector_port fCP1 = Eastadl21Factory.eINSTANCE.createHardwareConnector_port();
			fCP1.setHardwarePin(source);
			
			
			EObject sAnchor = context.getSourceAnchor().eContainer();
			if (sAnchor instanceof ContainerShape ) 
			{
				ContainerShape z = (ContainerShape) sAnchor;
				z.getLink().getBusinessObjects().get(0);
				if ( z.getLink().getBusinessObjects().get(0) instanceof HardwareComponentPrototype)
				{
					fCP1.setHardwareComponentPrototype((HardwareComponentPrototype) z.getLink().getBusinessObjects().get(0));
				}
				if(z instanceof Diagram){
					sLock = true;
				}
			}
			
			
			
			HardwareConnector_port fCP2 = Eastadl21Factory.eINSTANCE.createHardwareConnector_port();
			fCP2.setHardwarePin(target);
			
			
			EObject tAnchor = context.getTargetAnchor().eContainer();
			if (tAnchor instanceof ContainerShape ) 
			{
				ContainerShape z = (ContainerShape) tAnchor;
				z.getLink().getBusinessObjects().get(0);
				if ( z.getLink().getBusinessObjects().get(0) instanceof HardwareComponentPrototype)
				{
					fCP2.setHardwareComponentPrototype((HardwareComponentPrototype) z.getLink().getBusinessObjects().get(0));
				}
				if(z instanceof Diagram){
					tLock = true;
				}
			}
			
			// add connection for business object
			if (!(sLock == true && tLock ==true)){
				EObject sOb = sourceAnchor.getLink().getBusinessObjects().get(0);
				EObject tOb = targetAnchor.getLink().getBusinessObjects().get(0);
				if(sOb instanceof IOHardwarePin && tOb instanceof IOHardwarePin){
					sameLock = true ;
				}
				if(sOb instanceof PowerHardwarePin && tOb instanceof PowerHardwarePin ){
					sameLock = true;
				}
				if(sOb instanceof CommunicationHardwarePin && tOb instanceof CommunicationHardwarePin){
					sameLock = true;
				}
				
				if(sameLock)
				{
					AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
					addContext.setNewObject(eReference);
					
					HardwareComponentType model = (HardwareComponentType) getDiagram().getLink().getBusinessObjects().get(0);
					DiagramUtil.addReferenceToBOResource(model,Eastadl21Factory.eINSTANCE.getEastadl21Package().getHardwareComponentType_Connector(), eReference);
					DiagramUtil.addReferenceToBOResource(eReference, Eastadl21Factory.eINSTANCE.getEastadl21Package().getHardwareConnector_Port(), fCP1);
					DiagramUtil.addReferenceToBOResource(eReference, Eastadl21Factory.eINSTANCE.getEastadl21Package().getHardwareConnector_Port(), fCP2);


					newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
				}
				
				
			}
			
		}
		
		sLock = false;
		tLock = false;
		sameLock = false;
		return newConnection;
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a ComponentType
		Object object = getBusinessObjectForPictogramElement(context.getSourceAnchor());
		if (object instanceof HardwarePin   ) {
			if(context.getSourceAnchor().getLink() != null){
				if(context.getSourceAnchor().eContainer() != null){

					if (object instanceof HardwarePin) {
						return true;
					}
				}
			}
		}
		return false;
	}
	private HardwarePin getHardwarePin(Anchor anchor) {
        if (anchor != null
        		&& anchor.getLink() != null) {
            Object object = anchor.getLink().getBusinessObjects().get(0);
            if (object instanceof HardwarePin) {
                return (HardwarePin) object;
            }
        }
        return null;
    }
	private HardwareConnector createReference(HardwarePin source, HardwarePin target) {
		HardwareConnector reL=Eastadl21Factory.eINSTANCE.createHardwareConnector();
		@SuppressWarnings("unused")
		FunctionConnector_port j = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
		@SuppressWarnings("unused")
		FunctionConnector_port h = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
		return reL;
	}


   
}
