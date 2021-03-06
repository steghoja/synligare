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
package org.eclipse.eatop.examples.graphicaleditor.emtd.features.connection;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.ErrorBehavior;
import org.eclipse.eatop.eastadl21.ErrorModelType;
import org.eclipse.eatop.eastadl21.FaultInPort;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

	public class CreateErrorBehaviorToFaultInPortFeature extends
			AbstractCreateConnectionFeature {

		public CreateErrorBehaviorToFaultInPortFeature(IFeatureProvider fp) {
			super(fp, "ErrorBehavior to FaultInPort", "Create ErrorBehavior To FaultInPort");
		}

		@Override
		public boolean canCreate(ICreateConnectionContext context) {
			// return true if both anchors belong to an EClass
	        // and those EClasses are not identical
	        ErrorBehavior source = getErrorBehavior(context.getSourceAnchor());	        
	        FaultInPort target = getFaultInPort(context.getTargetAnchor());
	        
	        
	        if (source != null && target != null && source != target &&context.getTargetAnchor().getParent().getLink().getBusinessObjects().get(0) instanceof ErrorModelType) {
	        	
	            return true;
	        }
	        
	        return false;
		}

		
		
		
		@Override
		public boolean canStartConnection(ICreateConnectionContext context) {
			  // return true if start anchor belongs to a EClass
	        if (getErrorBehavior(context.getSourceAnchor()) != null) {
	            return true;
	        }
	        return false;
		}
		
		@Override
		public Connection create(ICreateConnectionContext context) {
			Connection newConnection = null;
			 
	        // get EClasses which should be connected
	        ErrorBehavior ErrorBehavior = getErrorBehavior(context.getSourceAnchor());
	        FaultInPort FaultInPort = getFaultInPort(context.getTargetAnchor());
	        Assert.isNotNull(ErrorBehavior);
	        Assert.isNotNull(
	        		FaultInPort);
	        
	        if (ErrorBehavior != null && FaultInPort != null) {
	           
	        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getErrorBehavior_ExternalFault();
	        	DiagramUtil.addReferenceToBOResource(ErrorBehavior, referenceId, FaultInPort);
	        	
	        	AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
	            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
	        }
	        
	        return newConnection;
		}

		
		
		
		private FaultInPort getFaultInPort(Anchor anchor) {
	        if (anchor != null
	        		&& anchor.getLink() != null) {
	            Object object = anchor.getLink().getBusinessObjects().get(0);
	            if (object instanceof FaultInPort) {
	                return (FaultInPort) object;
	            }
	        }
	        return null;
	    }

		private ErrorBehavior getErrorBehavior(Anchor sourceAnchor) {
			if (sourceAnchor != null) {
	            Object object =
	                getBusinessObjectForPictogramElement(sourceAnchor.getParent());
	            if (object instanceof ErrorBehavior) {
	                return (ErrorBehavior) object;
	            }
	        }
	        return null;
		}
		
	}
