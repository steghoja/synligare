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
import org.eclipse.eatop.eastadl21.InternalFaultPrototype;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;

	public class CreateErrorBehaviorToInternalFaultPrototypeFeature extends
			AbstractCreateConnectionFeature {

		public CreateErrorBehaviorToInternalFaultPrototypeFeature(IFeatureProvider fp) {
			super(fp, "ErrorBehavior to InternalFaultPrototype", "Create ErrorBehavior To InternalFaultPrototype");
		}

		@Override
		public boolean canCreate(ICreateConnectionContext context) {
			// return true if both anchors belong to an EClass
	        // and those EClasses are not identical
	        ErrorBehavior source = getErrorBehavior(context.getSourceAnchor());
	        InternalFaultPrototype target = getInternalFaultPrototype(context.getTargetAnchor());
	        if (source != null && target != null && source != target) {
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
	        InternalFaultPrototype InternalFaultPrototype = getInternalFaultPrototype(context.getTargetAnchor());
	        Assert.isNotNull(ErrorBehavior);
	        Assert.isNotNull(InternalFaultPrototype);
	        
	        if (ErrorBehavior != null && InternalFaultPrototype != null) 
	        {	          
	        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getErrorBehavior_InternalFault();
	        	DiagramUtil.addReferenceToBOResource(ErrorBehavior, referenceId, InternalFaultPrototype);
	        	AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
	        	newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
	        }
	        return newConnection;
		}

		
		private InternalFaultPrototype getInternalFaultPrototype(Anchor targetAnchor) {
			if (targetAnchor != null) {
	            Object object =
	                getBusinessObjectForPictogramElement(targetAnchor.getParent());
	            if (object instanceof InternalFaultPrototype) {
	                return (InternalFaultPrototype) object;
	            }
	        }
	        return null;
		}

		private ErrorBehavior getErrorBehavior(Anchor sourceAnchor) {
			if (sourceAnchor != null) {
	            Object object = getBusinessObjectForPictogramElement(sourceAnchor.getParent());
	            if (object instanceof ErrorBehavior) {
	                return (ErrorBehavior) object;
	            }
	        }
	        return null;
		}
		
	}
