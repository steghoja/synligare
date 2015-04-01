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
package org.eclipse.eatop.examples.graphicaleditor.emtd.features.instanceref;


import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype;
import org.eclipse.eatop.eastadl21.ErrorModelPrototype_functionTarget;
import org.eclipse.eatop.eastadl21.FunctionPrototype;
import org.eclipse.core.runtime.Assert;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;


public class CreateErrorModelPrototypeToFunctionPrototypeFeature extends
		AbstractCreateConnectionFeature {

	public CreateErrorModelPrototypeToFunctionPrototypeFeature(IFeatureProvider fp) {
		super(fp, "ErrorModelPrototype to FunctionPrototype", "Create ErrorModelPrototypeToFunctionPrototype");
	}
	
	private ErrorModelPrototype getErrorModelPrototype(ICreateConnectionContext context) {
		
		
		
		Object obj = (ErrorModelPrototype) context.getSourcePictogramElement().getLink().getBusinessObjects().get(0);
		
		if(obj instanceof ErrorModelPrototype)
		return (ErrorModelPrototype) obj;
		
		return null;
		
        
	}

	private FunctionPrototype getFunctionPrototype(Anchor targetAnchor) 
	{
		if (targetAnchor != null) {
            Object object =
                getBusinessObjectForPictogramElement(targetAnchor.getParent());
            if (object instanceof FunctionPrototype) {
                return (FunctionPrototype) object;}}
        return null;
		
    }
	
	
	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// return true if start anchor belongs to a ComponentType

		if (context.getSourcePictogramElement().getLink().getBusinessObjects().get(0) instanceof ErrorModelPrototype ) {
				return true;			
		}
		
		return false;
	}
		
	@Override
	public boolean canCreate(ICreateConnectionContext context) 
	{
		
		// return true if both anchors belong to an EClass
        // and those EClasses are not identical
		
		ErrorModelPrototype source = (ErrorModelPrototype) context.getSourcePictogramElement().getLink().getBusinessObjects().get(0);
        FunctionPrototype target = getFunctionPrototype(context.getTargetAnchor());
        if (source != null && target != null && source != target) 
        {	
            return true;
        }
        return false;
	}

	
	
	@Override
	public Connection create(ICreateConnectionContext context) 
	{
		Connection newConnection = null;
		
		
		ErrorModelPrototype_functionTarget invisible = Eastadl21Factory.eINSTANCE.createErrorModelPrototype_functionTarget();
		
		
		ErrorModelPrototype empt = (ErrorModelPrototype) getErrorModelPrototype(context);
		FunctionPrototype fpt = (FunctionPrototype) getFunctionPrototype(context.getTargetAnchor());		
			
		
		empt.getFunctionTarget().add(invisible);
		
		Assert.isNotNull(empt);
        Assert.isNotNull(fpt);
		if (empt != null && fpt != null) 
		{
			
			invisible.getFunctionPrototype_context().add(fpt);
			
			invisible.setFunctionPrototype(fpt);

			
			//DiagramUtil.addReferenceToBOResource(empt, ErrormodelPackage.Literals.ERROR_MODEL_PROTOTYPE__TARGET, fpt);
			AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
			newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);						
		}		
     		return newConnection;
	}
}
