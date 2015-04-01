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
package org.eclipse.eatop.examples.graphicaleditor.bd.features.create;

import org.eclipse.core.runtime.Assert;
import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FunctionPort;
import org.eclipse.eatop.eastadl21.FunctionTrigger;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;



public class CreateFunctionTriggerToFunctionPortFeature extends
		AbstractCreateConnectionFeature {

	public CreateFunctionTriggerToFunctionPortFeature(IFeatureProvider fp) {
		super(fp, "FunctionTrigger To FunctionPort", "Create FunctionTriggerToFunctionPort");
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
        FunctionTrigger source = getFunctionTrigger(context.getSourceAnchor());
        //FunctionPort target = getFunctionPort(context.getTargetAnchor());
        FunctionPort target = (FunctionPort) getBusinessObjectForPictogramElement(context.getTargetAnchor());
        
        if (source != null && target != null) 
        {
            return true;
        }
        return false;
    }

	@Override
	public Connection create(ICreateConnectionContext context) {
        Connection newConnection = null;
 
        
        FunctionTrigger ft = (FunctionTrigger) getFunctionTrigger(context.getSourceAnchor()); //this is source
        FunctionPort FunctionPort = (FunctionPort) getBusinessObjectForPictogramElement(context.getTargetAnchor());
		
        Assert.isNotNull(ft);
        Assert.isNotNull(FunctionPort);
 
        if (ft != null && FunctionPort != null) {
        	
        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getFunctionTrigger_Port();
        	DiagramUtil.addReferenceToBOResource(ft, referenceId, FunctionPort);
        	AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
        }
       
        return newConnection;
    }

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {

        if (getFunctionTrigger(context.getSourceAnchor()) != null) {
            return true;
        }
        return false;
    
	}
	
	private FunctionTrigger getFunctionTrigger(Anchor anchor) {
        if (anchor != null) {
            Object object = getBusinessObjectForPictogramElement(anchor.getParent());
            if (object instanceof FunctionTrigger) {
                return (FunctionTrigger) object;
            }
        }
        return null;
    }  
}
