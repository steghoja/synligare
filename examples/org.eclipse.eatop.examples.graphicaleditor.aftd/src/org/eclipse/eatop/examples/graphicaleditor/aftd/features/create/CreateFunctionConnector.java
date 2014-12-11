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
package org.eclipse.eatop.examples.graphicaleditor.aftd.features.create;


import org.eclipse.eatop.eastadl21.AnalysisFunctionPrototype;
import org.eclipse.eatop.eastadl21.AnalysisFunctionType;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionClientServerPort;
import org.eclipse.eatop.eastadl21.FunctionConnector;
import org.eclipse.eatop.eastadl21.FunctionConnector_port;
import org.eclipse.eatop.eastadl21.FunctionFlowPort;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionPowerPort;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
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


public class CreateFunctionConnector extends AbstractCreateConnectionFeature{

	public boolean sLock = false;
	public boolean tLock = false;
	public boolean sameLock = false;
	public CreateFunctionConnector(IFeatureProvider fp) {
		super(fp, "FunctionConnector", "Create FunctionConnector Relationship");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
	
		FunctionPort source = getFunctionPort(context.getSourceAnchor());
		FunctionPort target = getFunctionPort(context.getTargetAnchor());
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
		FunctionPort source = (FunctionPort) getBusinessObjectForPictogramElement(context.getSourceAnchor());
		// get Interface which should be connected
		Anchor targetAnchor = context.getTargetAnchor();
		FunctionPort target = (FunctionPort) getBusinessObjectForPictogramElement(context.getTargetAnchor());

		if (source != null && target != null) {
			// get new business object                                   
			FunctionConnector eReference = createReference(source, target);
			//test1 --------------------------------------------------------------
			FunctionConnector_port fCP1 = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
			fCP1.setFunctionPort(source);
			EObject sAnchor = context.getSourceAnchor().eContainer();
			if (sAnchor instanceof ContainerShape) 
			{
				ContainerShape z = (ContainerShape) sAnchor;
				z.getLink().getBusinessObjects().get(0);
				if ( z.getLink().getBusinessObjects().get(0) instanceof AnalysisFunctionPrototype)
				{
					fCP1.setFunctionPrototype((FunctionPrototype) z.getLink().getBusinessObjects().get(0));
				}
				if(z instanceof Diagram){
					sLock = true;
				}
			}
			
			
			
			FunctionConnector_port fCP2 = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
			fCP2.setFunctionPort(target);
			EObject tAnchor = context.getTargetAnchor().eContainer();
			if (tAnchor instanceof ContainerShape ) 
			{
				ContainerShape z = (ContainerShape) tAnchor;
				z.getLink().getBusinessObjects().get(0);
				if ( z.getLink().getBusinessObjects().get(0) instanceof AnalysisFunctionPrototype)
				{
					fCP2.setFunctionPrototype((FunctionPrototype) z.getLink().getBusinessObjects().get(0));
				}
				if(z instanceof Diagram){
					tLock = true;
				}
			}
			
			
			//end Test1 ------------------------------------------------------------
			
			// add connection for business object
			if (!(sLock == true && tLock ==true)){
				EObject sOb = sourceAnchor.getLink().getBusinessObjects().get(0);
				EObject tOb = targetAnchor.getLink().getBusinessObjects().get(0);
				if(sOb instanceof FunctionFlowPort && tOb instanceof FunctionFlowPort){
					sameLock = true ;
				}
				if(sOb instanceof FunctionPowerPort && tOb instanceof FunctionPowerPort ){
					sameLock = true;
				}
				if(sOb instanceof FunctionClientServerPort && tOb instanceof FunctionClientServerPort){
					sameLock = true;
				}
				
				if(sameLock)
				{
					AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
					addContext.setNewObject(eReference);
					
					AnalysisFunctionType model = (AnalysisFunctionType) getDiagram().getLink().getBusinessObjects().get(0);
					DiagramUtil.addReferenceToBOResource(model, Eastadl21Factory.eINSTANCE.getEastadl21Package().getFunctionType_Connector(), eReference);
					
					// test1----------------------------------------------------------------
					DiagramUtil.addReferenceToBOResource(eReference, Eastadl21Factory.eINSTANCE.getEastadl21Package().getFunctionConnector_Port(), fCP1);
					DiagramUtil.addReferenceToBOResource(eReference, Eastadl21Factory.eINSTANCE.getEastadl21Package().getFunctionConnector_Port(), fCP2);
					
					// end test1 -----------------------------------------------------------

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
//		Diagram g = getDiagram();
//		FunctionPort getSourceAnchor = getFunctionPort(context.getSourceAnchor()) ;
//		PictogramLink getSourceGetLink = context.getSourceAnchor().getLink();
//		EObject getEContainer = context.getSourceAnchor().eContainer();
		Object object = getBusinessObjectForPictogramElement(context.getSourceAnchor());
		
		if (object instanceof FunctionPort   ) {
			if(context.getSourceAnchor().getLink() != null){
				if(context.getSourceAnchor().eContainer() != null){
					
					if (object instanceof FunctionPort) {
						return true;
					}
				}
			}
		}
		return false;
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
	private FunctionConnector createReference(FunctionPort source, FunctionPort target) {
		FunctionConnector reL=Eastadl21Factory.eINSTANCE.createFunctionConnector();
		@SuppressWarnings("unused")
		FunctionConnector_port j = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
		@SuppressWarnings("unused")
		FunctionConnector_port h = Eastadl21Factory.eINSTANCE.createFunctionConnector_port();
		return reL;
	}


   
}
