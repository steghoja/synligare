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


package org.eclipse.eatop.examples.graphicaleditor.depsconstrd.connection;

import org.eclipse.eatop.eastadl21.Eastadl21Factory;
import org.eclipse.eatop.eastadl21.FaultFailure;
import org.eclipse.eatop.eastadl21.SafetyConstraint;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.features.impl.AbstractCreateConnectionFeature;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.sphinx.graphiti.workspace.ui.util.DiagramUtil;


	public class CreateSafetyConstraintToFaultFailureFeature extends
			AbstractCreateConnectionFeature {

		public CreateSafetyConstraintToFaultFailureFeature(IFeatureProvider fp) {
			super(fp, "SafetyConstraint To FaultFailure", "Create SafetyConstraint To FaultFailure");
		}

		@Override
		public boolean canCreate(ICreateConnectionContext context) {
			// return true if both anchors belong to an EClass
	        // and those EClasses are not identical
	        SafetyConstraint source = getSafetyConstraint(context.getSourceAnchor());
	        FaultFailure target = getFaultFailure(context.getTargetAnchor());
	        if (source != null && target != null && source != target) {
	            return true;
	        }
	        return false;
		}

		@Override
		public boolean canStartConnection(ICreateConnectionContext context) {
			  // return true if start anchor belongs to a EClass
	        if (getSafetyConstraint(context.getSourceAnchor()) != null) {
	            return true;
	        }
	        return false;
		}
		
		@Override
		public Connection create(ICreateConnectionContext context) {
			
			Connection newConnection = null;
			 
	        // get EClasses which should be connected
	        SafetyConstraint SafetyConstraint = getSafetyConstraint(context.getSourceAnchor());
	        FaultFailure FaultFailure = getFaultFailure(context.getTargetAnchor());
	        Assert.isNotNull(SafetyConstraint);
	        Assert.isNotNull(
	        		FaultFailure);
	        
	        if (SafetyConstraint != null && FaultFailure != null) {
	           
	        	EReference referenceId = Eastadl21Factory.eINSTANCE.getEastadl21Package().getErrorBehavior_ExternalFault();
	        	DiagramUtil.addReferenceToBOResource((org.eclipse.emf.ecore.EObject) SafetyConstraint,
	        			referenceId, (org.eclipse.emf.ecore.EObject) FaultFailure);
	        	
	        	
	        	AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
	            newConnection = (Connection) getFeatureProvider().addIfPossible(addContext);
	        }
	        
	        return newConnection;
		}

		
		private FaultFailure getFaultFailure(Anchor targetAnchor) {
			if (targetAnchor != null) {
	            Object object =
	                getBusinessObjectForPictogramElement(targetAnchor.getParent());
	            if (object instanceof FaultFailure) {
	                return (FaultFailure) object;
	            }
	        }
	        return null;
		}

		private SafetyConstraint getSafetyConstraint(Anchor sourceAnchor) {
			if (sourceAnchor != null) {
	            Object object =
	                getBusinessObjectForPictogramElement(sourceAnchor.getParent());
	            if (object instanceof SafetyConstraint) {
	                return (SafetyConstraint) object;
	            }
	        }
	        return null;
		}
		
	}
